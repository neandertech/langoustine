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

object Tracer extends IOApp:
  def run(args: List[String]): IO[ExitCode] =
    Config.command.parse(args, sys.env) match
      case Left(help) =>
        if help.errors.isEmpty then
          IO.consoleForIO.errorln(help).as(ExitCode.Success)
        else IO.consoleForIO.errorln(help).as(ExitCode.Error)

      case Right(c) =>
        Launch(c)

  end run
end Tracer

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

  val currentFolder  = System.getProperty("user.dir")
  val currentCommand = config.cmd.toList
  val summary        = Summary(currentFolder, currentCommand)

  exitLatch
    .flatMap { exits =>
      (byteTopic, byteTopic, byteTopic, latch).parTupled
        .flatMap { case (inBytes, outBytes, errBytes, outLatch) =>
          langoustine.ChildProcess.resource[IO](config.cmd.toList*).flatMap {
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
                .runResource(config, summary)
                .evalTap(server =>
                  IO.consoleForIO.errorln(msg(server.baseUri)) *>
                    outLatch.complete(server.baseUri)
                )

              (redirectInput, redirectOutput, redirectLogs).parTupled *> server
          }
        }
        .use(_ => exits.get)
    }
    .as(ExitCode.Success)
end Launch
