/*
 * Copyright 2022 Neandertech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package langoustine
package tracer

import jsonrpclib.fs2.*
import cats.effect.*
import cats.effect.implicits.*
import cats.syntax.all.*
import fs2.Chunk

import org.http4s.EntityEncoder
import jsonrpclib.Payload
import jsonrpclib.ErrorPayload
import jsonrpclib.CallId
import langoustine.lsp.requests.textDocument
import langoustine.lsp.requests.window
import langoustine.lsp.structures.ShowMessageParams
import com.github.plokhotnyuk.jsoniter_scala.core.*
import fs2.concurrent.Channel
import jsonrpclib.Message

object LangoustineTracer extends IOApp:
  def run(args: List[String]): IO[ExitCode] =
    Config.command.parse(args, sys.env) match
      case Left(help) =>
        if help.errors.isEmpty then
          IO.consoleForIO.errorln(help).as(ExitCode.Success)
        else IO.consoleForIO.errorln(help).as(ExitCode.Error)

      case Right(c) =>
        Launch(c, in = stdin(2048)).compile.drain.as(ExitCode.Success)

  end run

  private def stdin[F[_]](
      bufSize: Int
  )(implicit F: Async[F]): fs2.Stream[F, Byte] =
    fs2.Stream.eval(Channel.synchronous[F, Chunk[Byte]]).flatMap { ch =>
      fs2.Stream.bracket {
        fs2.io
          .readInputStream(F.blocking(System.in), bufSize, false)
          .chunks
          .through(ch.sendAll)
          .compile
          .drain
          .start
      } { fiber =>
        // cancelation may hang so we leak :(
        fiber.cancel.start.void
      } >> ch.stream.unchunks
    }

  def Launch(
      config: Config,
      in: fs2.Stream[IO, Byte] = fs2.io.stdin[IO](512),
      out: fs2.Pipe[IO, Byte, Nothing] = fs2.io.stdout[IO],
      err: fs2.Pipe[IO, Byte, Nothing] = fs2.io.stderr[IO]
  ) =
    val payloadTopic =
      fs2.concurrent.Topic[IO, Message]

    val byteTopic =
      fs2.concurrent.Topic[IO, Chunk[Byte]]

    val latch =
      IO.deferred[org.http4s.Uri]

    val exitLatch = IO.deferred[Boolean]

    val currentFolder = System.getProperty("user.dir")

    if config.debug then
      scribe.Logger.root.withMinimumLevel(scribe.Level.Debug).replace()

    fs2.Stream
      .eval(
        (payloadTopic, payloadTopic, byteTopic, latch, exitLatch).parTupled
      )
      .flatMap { case (inBytes, outBytes, errBytes, outLatch, exits) =>
        config.mode match
          case Mode.Replay(rc) =>
            Replay(
              inBytes = inBytes,
              outBytes = outBytes,
              errBytes = errBytes,
              replayConfig = rc,
              bindConfig = config.bind,
              summary = Summary.Replay(rc.file.toString)
            )

          case Mode.Trace(tc) =>
            Trace(
              in = in,
              out = out,
              err = err,
              inBytes = inBytes,
              outBytes = outBytes,
              errBytes = errBytes,
              outLatch = outLatch,
              exits = exits,
              traceConfig = tc,
              bindConfig = config.bind,
              summary = Summary.Trace(currentFolder, tc.cmd.toList)
            )
      }

  end Launch

end LangoustineTracer
