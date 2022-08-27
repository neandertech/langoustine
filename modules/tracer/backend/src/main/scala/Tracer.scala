package langoustine
package tracer

import jsonrpclib.fs2.*
import cats.effect.*
import cats.syntax.all.*
import fs2.Chunk

import org.http4s.EntityEncoder
import jsonrpclib.Payload
import jsonrpclib.ErrorPayload
import jsonrpclib.CallId

object Tracer extends IOApp:
  def run(args: List[String]): IO[ExitCode] =
    val in  = fs2.io.stdin[IO](512)
    val out = fs2.io.stdout[IO]

    val tracerArgs = args.takeWhile(_.startsWith("!")).map(_.drop(1))

    val restArgs = args.drop(tracerArgs.length)

    val argMap = tracerArgs
      .grouped(2)
      .collect { case k :: v :: Nil =>
        k -> v
      }
      .toMap

    val byteTopic =
      Resource.eval(fs2.concurrent.Channel.synchronous[IO, Chunk[Byte]])

    (byteTopic, byteTopic, byteTopic).parTupled
      .flatMap { case (inBytes, outBytes, errBytes) =>
        ChildProcess.resource[IO](restArgs*).flatMap { child =>
          val redirectInput =
            in.chunks
              .evalTap(inBytes.send)
              .unchunks
              .through(child.stdin)
              .compile
              .drain
              .background

          val redirectOutput =
            child.stdout.chunks
              .evalTap(outBytes.send)
              .unchunks
              .through(out)
              .compile
              .drain
              .background

          val redirectLogs =
            child.stderr.chunks
              .evalTap(errBytes.send)
              .compile
              .drain
              .background

          val server = TracerServer
            .create(
              inBytes.stream.unchunks,
              outBytes.stream.unchunks,
              errBytes.stream.unchunks
            )
            .runResource(argMap)
            .evalTap(IO.consoleForIO.errorln)

          (redirectInput, redirectOutput, redirectLogs, server).parTupled
        }

      }
      .useForever
      .as(ExitCode.Success)

  end run
end Tracer
