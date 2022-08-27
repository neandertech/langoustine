package langoustine
package tracer

import jsonrpclib.fs2.*
import cats.effect.IOApp
import cats.effect.ExitCode
import cats.effect.IO
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
      fs2.Stream.eval(fs2.concurrent.Channel.unbounded[IO, Chunk[Byte]])

    byteTopic
      .flatMap { inBytes =>
        byteTopic.flatMap { outBytes =>
          byteTopic.flatMap { errBytes =>
            ChildProcess
              .spawn[IO](restArgs*)
              .flatMap { child =>

                val redirectInput =
                  in.chunks.evalTap(inBytes.send).unchunks.through(child.stdin)

                val redirectOutput =
                  child.stdout.chunks
                    .evalTap(outBytes.send)
                    .unchunks
                    .through(out)

                TracerServer
                  .create(
                    inBytes.stream.unchunks,
                    outBytes.stream.unchunks,
                    errBytes.stream.unchunks
                  )
                  .runStream(argMap)
                  .flatMap { _ =>
                    redirectInput
                      .concurrently(redirectOutput)
                      .concurrently(
                        child.stderr.chunks.evalTap(errBytes.send)
                      )
                  }
              }
          }
        }
      }
      .compile
      .drain
      .as(ExitCode(0))
  end run
end Tracer
