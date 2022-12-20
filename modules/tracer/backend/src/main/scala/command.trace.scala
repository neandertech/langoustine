package langoustine.tracer

import fs2.*
import fs2.concurrent.*
import cats.effect.*
import cats.syntax.all.*
import org.http4s.EntityEncoder
import jsonrpclib.Payload
import jsonrpclib.ErrorPayload
import jsonrpclib.CallId
import langoustine.lsp.requests.textDocument
import langoustine.lsp.requests.window
import langoustine.lsp.structures.ShowMessageParams
import com.github.plokhotnyuk.jsoniter_scala.core.*
import fs2.concurrent.Channel


def Trace(
    in: fs2.Stream[IO, Byte],
    out: fs2.Pipe[IO, Byte, Nothing],
    inBytes: Channel[IO, Chunk[Byte]],
    outBytes: Channel[IO, Chunk[Byte]],
    errBytes: Channel[IO, Chunk[Byte]],
    outLatch: Deferred[IO, org.http4s.Uri],
    exits: Deferred[IO, Boolean],
    traceConfig: TraceConfig,
    bindConfig: BindConfig,
    summary: Summary
) =
  langoustine.ChildProcess.resource[IO](traceConfig.cmd.toList*).flatMap {
    child =>

      import langoustine.lsp.jsonrpcIntegration.given

      def msg(uri: org.http4s.Uri) =
        s"Langoustine Tracer started at ${uri}"

      def sendHello(uri: org.http4s.Uri) =
        val params = ShowMessageParams(
          langoustine.lsp.enumerations.MessageType.Info,
          msg(uri)
        )
        val serialised = jsonrpclib.Codec.encode(params)
        val asStr      = writeToStringReentrant(serialised)
        val preamble =
          s"""{"method": "window/showMessage", "params": $asStr}"""
        val length  = preamble.getBytes.length
        val message = s"Content-Length: $length\r\n\r\n$preamble"

        fs2.Stream
          .chunk(Chunk.array(message.getBytes))
          .through(out)
          .compile
          .drain
      end sendHello

      def log(msg: String) =
        errBytes.send(Chunk.array(("[tracer] " + msg + "\n").getBytes))

      val redirectInput =
        in.chunks
          .evalTap(inBytes.send)
          .unchunks
          .through(child.stdin)
          .compile
          .drain
          .flatTap(_ =>
            log("process stdin finished, shutting down tracer") *>
              child.terminate *>
              exits.complete(true)
          )
          .background

      val redirectOutput =
        (outLatch.get.flatMap(sendHello) *>
          child.stdout.chunks
            .evalTap(outBytes.send)
            .unchunks
            .through(out)
            .compile
            .drain
            .flatTap(_ => log("process stdout finished"))).background

      val redirectLogs =
        child.stderr.chunks
          .evalTap(errBytes.send)
          .compile
          .drain
          .flatTap(_ => log("process stderr finished"))
          .background

      val server = TracerServer
        .create(
          inBytes.stream.unchunks,
          outBytes.stream.unchunks,
          errBytes.stream.unchunks
        )
        .runResource(bindConfig, summary)
        .evalTap(server =>
          IO.consoleForIO.errorln(msg(server.baseUri)) *>
            outLatch.complete(server.baseUri)
        )

      (redirectInput, redirectOutput, redirectLogs).parTupled *> server
  }
