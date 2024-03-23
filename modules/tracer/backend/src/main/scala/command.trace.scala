package langoustine.tracer

import fs2.*
import fs2.concurrent.*
import cats.effect.*
import cats.syntax.all.*
import org.http4s.EntityEncoder
import jsonrpclib.Payload
import jsonrpclib.ErrorPayload
import jsonrpclib.CallId
import langoustine.lsp.all.textDocument
import langoustine.lsp.all.window
import langoustine.lsp.all.ShowMessageParams
import com.github.plokhotnyuk.jsoniter_scala.core.*
import fs2.concurrent.Channel
import jsonrpclib.fs2.FS2Channel
import langoustine.lsp.Communicate
import org.http4s.server.Server
import jsonrpclib.fs2.lsp
import scala.util.NotGiven
import jsonrpclib.Message

extension (s: fs2.Stream[IO, Payload])
  def debugAs(name: String) =
    s.evalTap(el =>
      Logging.debug(s"[$name (payload)]: ${new String(el.array)}")
    )

extension [A](s: fs2.Stream[IO, A])
  inline def debugAs(name: String)(using NotGiven[A =:= Payload]) =
    s.evalTap(el => Logging.debug(s"[$name]: $el"))

def Trace(
    in: fs2.Stream[IO, Byte],
    out: fs2.Pipe[IO, Byte, Nothing],
    err: fs2.Pipe[IO, Byte, Nothing],
    inBytes: Topic[IO, Message],
    outBytes: Topic[IO, Message],
    errBytes: Topic[IO, Chunk[Byte]],
    outLatch: Deferred[IO, org.http4s.Uri],
    exits: Deferred[IO, Boolean],
    traceConfig: TraceConfig,
    bindConfig: BindConfig,
    summary: Summary
) =
  fs2.Stream.eval(IO.deferred[Communicate[IO]]).flatMap { comms =>
    langoustine.ChildProcess
      .spawn[IO](traceConfig.cmd.toList*)
      .flatMap { child =>

        import langoustine.lsp.jsonrpcIntegration.given

        def log(msg: String) =
          errBytes.publish1(Chunk.array(("[tracer] " + msg + "\n").getBytes))

        import jsonrpclib.fs2.*

        val inPayloads  = inBytes.subscribe(1024)
        val outPayloads = outBytes.subscribe(1024)
        val errStream   = errBytes.subscribe(1024)

        val channel = FS2Channel[IO](2048, None).flatMap { rpcChannel =>
          val communicate = Communicate.channel(rpcChannel, IO.unit)

          val readIn = inPayloads
            .debugAs("payloads coming in")
            .filter {
              case msg: jsonrpclib.InputMessage =>
                msg.method.startsWith("langoustine/")
              case _ => true

            }
            .through(rpcChannel.input)

          val writeOut = rpcChannel.output
            .debugAs("payloads going out")
            .through(outBytes.publish)

          fs2.Stream.eval(comms.complete(communicate)) ++
            readIn
              .concurrently(writeOut)
        }

        val captureStdin =
          in
            .through(lsp.decodeMessages)
            .evalMap {
              case Left(err) =>
                Logging
                  .error(s"Failed to decode message from stdin: $err")
                  .as(None)
              case Right(payload) => IO.pure(Some(payload))
            }
            .unNone
            .debugAs("stdin LSP payloads")
            .through(inBytes.publish)
            .onFinalize(
              Logging.info("process stdin finished, shutting down tracer") *>
                child.terminate *>
                exits.complete(true).void
            )

        val redirectStdin =
          inPayloads
            .debugAs("writing to child stdin")
            .through(lsp.encodeMessages)
            .through(child.stdin)

        val captureStdout =
          child.stdout
            .through(lsp.decodeMessages)
            .evalMap {
              case Left(err) =>
                Logging
                  .error(
                    s"Failed to decode message from target LSP's stdout: $err"
                  )
                  .as(None)
              case Right(payload) => IO.pure(Some(payload))
            }
            .unNone
            .debugAs("reading from child's stdout")
            .through(outBytes.publish)

        val redirectStdout =
          outPayloads
            .debugAs("writing to real stdout")
            .through(lsp.encodeMessages)
            .through(out)
            .onFinalize(Logging.info("process stdout finished").void)

        val captureStderr =
          child.stderr.chunks
            .through(errBytes.publish)
            .onFinalize(Logging.info("process stderr finished").void)

        val redirectStderr =
          errStream
            .debugAs("writing to real stderr")
            .unchunks
            .through(err)

        val server =
          TracerServer
            .create(
              inPayloads,
              outPayloads,
              errStream.unchunks
            )
            .run(
              bindConfig,
              summary,
              await = server =>
                outLatch.complete(server.baseUri).attempt.void *>
                  Logging.info(s"Server started at ${server.baseUri}") *>
                  exits.get.void
            )

        val redirects = fs2
          .Stream(
            captureStdin.void,
            captureStdout.void,
            captureStderr.void,
            redirectStdin.void,
            redirectStderr.void,
            redirectStdout.void
          )
          .parJoinUnbounded

        server
          .concurrently(fs2.Stream.eval(outLatch.get).void ++ redirects)
          .concurrently(channel)
          .concurrently(
            fs2.Stream.eval(comms.get.parProduct(outLatch.get)).evalMap {
              (comm, serverUri) =>
                import langoustine.lsp.all.*
                if canOpenBrowser then

                  val open =
                    MessageActionItem(
                      s"Open $serverUri in the browser"
                    )
                  val nothing = MessageActionItem("Nothing")

                  comm
                    .request(
                      window.showMessageRequest,
                      ShowMessageRequestParams(
                        langoustine.lsp.enumerations.MessageType.Info,
                        message =
                          s"Langoustine tracer has started on $serverUri, what would you like to do?",
                        actions = Opt(
                          Vector(
                            open,
                            nothing
                          )
                        )
                      )
                    )
                    .flatTap { out =>
                      Logging.info(out.toString) >> {
                        if out == Opt(open) then
                          openBrowser(serverUri).attempt
                            .flatMap(r => Logging.info(r.toString))
                        else IO.unit
                      }
                    }
                else
                  comm.notification(
                    window.showMessage,
                    ShowMessageParams(
                      `type` = MessageType.Info,
                      message = s"Langoustine tracer is live at $serverUri"
                    )
                  )
                end if
            }
          )

      }
  }
