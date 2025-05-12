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

package langoustine.tracer

import cats.data.Kleisli
import cats.effect.*
import cats.syntax.all.*
import fs2.text
import jsonrpclib.Message
import org.http4s.HttpApp
import org.http4s.HttpRoutes
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server
import org.http4s.server.middleware.ErrorHandling
import org.http4s.server.websocket.WebSocketBuilder2

import concurrent.duration.*

class TracerServer private (
    in: fs2.Stream[IO, Message],
    out: fs2.Stream[IO, Message],
    err: fs2.Stream[IO, Byte]
):

  def run(
      config: BindConfig,
      summary: Summary,
      await: org.http4s.server.Server => IO[Unit]
  ) =
    fs2.Stream.eval(State.create).flatMap { state =>
      val run =
        fs2.Stream.resource(
          Server(
            wbs =>
              ErrorHandling
                .httpApp[IO](handleErrors(api(wbs, state, summary))),
            config
          )
        )

      val dumpRequests  = state.hydrateFrom(in, Direction.ToServer)
      val dumpResponses = state.hydrateFrom(out, Direction.ToClient)

      val dump = dumpRequests
        .concurrently(dumpResponses)
        .concurrently(dumpLogs(state))

      run.evalMap(await).concurrently(dump)
    }

  private def dumpLogs(state: State) =
    err
      .through(text.utf8.decode[IO])
      .through(text.lines)
      .chunks
      .evalTap { chunk =>
        Received.capture(chunk).flatMap { chunk =>
          state.logBuf.update { lines =>
            lines ++
              chunk.value.toVector
                .map(LogMessage.Stderr(_, chunk.timestamp))
          }
        }
      }
      .unchunks
      .through(state.ch.sendAll)

  private def handleErrors(routes: HttpRoutes[IO]) =
    routes.orNotFound.onError { exc =>
      Kleisli(request => Logging.error(s"FAILED $request: $exc"))
    }

  private def Server(
      app: WebSocketBuilder2[IO] => HttpApp[IO],
      config: BindConfig
  ): Resource[cats.effect.IO, org.http4s.server.Server] =
    EmberServerBuilder
      .default[IO]
      .withPort(config.port)
      .withHost(config.host)
      .withShutdownTimeout(100.millis)
      .withHttpWebSocketApp(app)
      .build

end TracerServer

object TracerServer:
  def create(
      in: fs2.Stream[IO, Message],
      out: fs2.Stream[IO, Message],
      err: fs2.Stream[IO, Byte]
  ): TracerServer =
    TracerServer(in, out, err)

end TracerServer

case class Received[T](timestamp: Long, value: T)
object Received:
  def capture[T](t: T) =
    IO.realTimeInstant.map(lng => Received(lng.toEpochMilli(), t))
  def captureF[T](t: IO[T]) =
    IO.realTimeInstant.flatMap(lng => t.map(Received(lng.toEpochMilli(), _)))
