package langoustine.tracer
import fs2.*
import fs2.concurrent.*
import cats.effect.*
import cats.syntax.all.*

def Replay(
    inBytes: Channel[IO, Chunk[Byte]],
    outBytes: Channel[IO, Chunk[Byte]],
    errBytes: Channel[IO, Chunk[Byte]],
    replayConfig: ReplayConfig,
    bindConfig: BindConfig,
    summary: Summary
) =
  val runServer =
    TracerServer
      .create(
        inBytes.stream.unchunks,
        outBytes.stream.unchunks,
        errBytes.stream.unchunks
      )
      .runResource(bindConfig, summary)
      .evalTap(server => IO.consoleForIO.errorln(server.baseUri))

  import com.github.plokhotnyuk.jsoniter_scala.core.*

  val sendStuff =
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
          inline def redirect(stream: Channel[IO, Chunk[Byte]]) =
            val raw     = writeToString(msg.raw)
            val length  = raw.getBytes.length
            val message = s"Content-Length: $length\r\n\r\n$raw"

            val chunk = Chunk.array(message.getBytes())
            stream.send(chunk)

          msg.decoded match
            case LspMessage.Request(method, id, responded) =>
              redirect(inBytes)
            case LspMessage.Response(id, method) =>
              redirect(outBytes)
            case LspMessage.Notification(generatedId, method, direction) =>
              direction match
                case Direction.ToServer => redirect(inBytes)
                case Direction.ToClient => redirect(outBytes)
          end match
        case SnapshotItem.Log(msg) =>
          msg match
            case LogMessage.Stderr(value, timestamp) =>
              errBytes.send(Chunk.array((value + "\n").getBytes))

      }

  (sendStuff.compile.drain.background, runServer).parTupled
end Replay
