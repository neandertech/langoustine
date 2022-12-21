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

object Main extends IOApp:
  def run(args: List[String]): IO[ExitCode] =
    Config.command.parse(args, sys.env) match
      case Left(help) =>
        if help.errors.isEmpty then
          IO.consoleForIO.errorln(help).as(ExitCode.Success)
        else IO.consoleForIO.errorln(help).as(ExitCode.Error)

      case Right(c) =>
        Launch(c)

  end run

  def Launch(
      config: Config,
      in: fs2.Stream[IO, Byte] = fs2.io.stdin[IO](512),
      out: fs2.Pipe[IO, Byte, Nothing] = fs2.io.stdout[IO]
  ) =
    val byteTopic =
      Resource.eval(fs2.concurrent.Channel.synchronous[IO, Chunk[Byte]])

    val latch =
      Resource.eval(IO.deferred[org.http4s.Uri])

    val exitLatch = IO.deferred[Boolean]

    val currentFolder = System.getProperty("user.dir")

    exitLatch
      .flatMap { exits =>
        (byteTopic, byteTopic, byteTopic, latch).parTupled
          .flatMap { case (inBytes, outBytes, errBytes, outLatch) =>
            config.mode match
              case Mode.Replay(rc) =>
                val summary = Summary.Replay(rc.file.toString)
                Replay(
                  inBytes = inBytes,
                  outBytes = outBytes,
                  errBytes = errBytes,
                  replayConfig = rc,
                  bindConfig = config.bind,
                  summary = summary
                )

              case Mode.Trace(tc) =>
                val summary = Summary.Trace(currentFolder, tc.cmd.toList)
                Trace(
                  in = in,
                  out = out,
                  inBytes = inBytes,
                  outBytes = outBytes,
                  errBytes = errBytes,
                  outLatch = outLatch,
                  exits = exits,
                  traceConfig = tc,
                  bindConfig = config.bind,
                  summary = summary
                )

          }
          .use(_ => exits.get)
      }
      .as(ExitCode.Success)

  end Launch

end Main
