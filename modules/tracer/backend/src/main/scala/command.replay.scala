package langoustine.tracer

import fs2.*
import fs2.concurrent.*
import cats.effect.*
import cats.syntax.all.*
import com.github.plokhotnyuk.jsoniter_scala.core.*
import jsonrpclib.Payload
import jsonrpclib.Message

def Replay(
    inBytes: Topic[IO, Message],
    outBytes: Topic[IO, Message],
    errBytes: Topic[IO, Chunk[Byte]],
    replayConfig: ReplayConfig,
    bindConfig: BindConfig,
    summary: Summary
) =
  def runServer(latch: Deferred[IO, Boolean]) =
    TracerServer
      .create(
        inBytes.subscribe(1),
        outBytes.subscribe(1),
        errBytes.subscribe(1).unchunks
      )
      .run(
        bindConfig,
        summary,
        await = server =>
          Logging.info(s"Server is ready at ${server.baseUri}") *> latch
            .complete(true) *> IO.never
      )

  def sendStuff(latch: Deferred[IO, Boolean]) =
    fs2.Stream.eval(latch.get) >>
      fs2.io.file
        .readAll[IO](replayConfig.file.toNioPath, 2048)
        .through(fs2.compression.Compression[IO].gunzip())
        .flatMap(_.content)
        .through(fs2.text.utf8.decode)
        .through(fs2.text.lines)
        .evalMap { line =>
          IO(readFromStringReentrant[SnapshotItem](line))
        }
        .evalMap {
          case SnapshotItem.Message(msg) =>
            inline def redirect(stream: Topic[IO, Message]) =
              msg.raw.toMessage match
                case None =>
                  Logging.error(
                    s"Failed to construct a jsonrpclib Message from ${msg.raw}"
                  )
                case Some(m) => stream.publish1(m)

            msg.decoded match
              case rq: LspMessage.Request =>
                rq.direction match
                  case Direction.ToServer =>
                    redirect(inBytes)
                  case Direction.ToClient =>
                    redirect(outBytes)

              case rp: LspMessage.Response =>
                rp.direction match
                  case Direction.ToServer =>
                    redirect(inBytes)
                  case Direction.ToClient =>
                    redirect(outBytes)
              case LspMessage.Notification(generatedId, method, direction) =>
                direction match
                  case Direction.ToServer => redirect(inBytes)
                  case Direction.ToClient => redirect(outBytes)
            end match
          case SnapshotItem.Log(msg) =>
            msg match
              case LogMessage.Stderr(value, timestamp) =>
                errBytes.publish1(Chunk.array((value + "\n").getBytes))

        }
  fs2.Stream.eval(IO.deferred[Boolean]).flatMap { latch =>
    runServer(latch).concurrently(sendStuff(latch))
  }

end Replay
