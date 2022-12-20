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

import cats.effect.IO
import cats.effect.std.Console
import cats.effect.Ref
import cats.effect.kernel.Clock
import com.comcast.ip4s.*
import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*
import fs2.concurrent.Channel
import fs2.concurrent.SignallingRef
import fs2.text
import jsonrpclib.Codec
import jsonrpclib.Payload
import jsonrpclib.fs2.lsp
import org.http4s.*
import org.http4s.HttpApp
import org.http4s.dsl.*
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.headers.`Content-Type`
import org.http4s.server.Router
import org.http4s.server.websocket.WebSocketBuilder2
import org.http4s.websocket.WebSocketFrame
import scala.concurrent.duration.*
import cats.effect.kernel.Resource
import cats.effect.std.Random
import cats.effect.std.UUIDGen
import java.util.UUID
import org.http4s.server.middleware.ErrorHandling
import cats.syntax.all.*
import cats.data.Kleisli
import org.http4s.server.middleware.ErrorHandling
import TracerServer.{*, given}
import org.http4s.server.middleware.GZip
import org.typelevel.ci.CIString
import org.http4s.dsl.impl.OptionalQueryParamMatcher
import org.http4s.dsl.impl.OptionalQueryParamDecoderMatcher

class TracerServer private (
    in: fs2.Stream[IO, Byte],
    out: fs2.Stream[IO, Byte],
    err: fs2.Stream[IO, Byte]
):

  def runResource(config: BindConfig, summary: Summary) =
    Resource.eval(State.create).flatMap { state =>
      val run = Server(
        wbs =>
          ErrorHandling
            .httpApp(handleErrors(api(wbs, state, summary))),
        config
      )

      val dump = dumpRequests(state)
        .concurrently(dumpResponses(state))
        .concurrently(dumpLogs(state))
        .compile
        .drain
        .background

      dump *> run
    }

  private def dumpRequests(state: State) =
    state.hydrateFrom(in, Direction.ToServer)
  private def dumpResponses(state: State) =
    state.hydrateFrom(out, Direction.ToClient)

  private def dumpLogs(state: State) =
    err
      .through(text.utf8.decode[IO])
      .through(text.lines)
      .chunks
      .evalTap { chunk =>
        state.logBuf.update { lines =>
          lines.drop(lines.size + chunk.size - 10000) ++ chunk.toVector
        }
      }
      .unchunks
      .through(state.ch.sendAll)

  private def handleErrors(routes: HttpRoutes[IO]) =
    routes.orNotFound.onError { exc =>
      Kleisli(request => Console[IO].errorln(s"FAILED $request: $exc"))
    }

  import org.http4s.dsl.io.*

  private def Server(
      app: WebSocketBuilder2[IO] => HttpApp[IO],
      config: BindConfig
  ): Resource[cats.effect.IO, server.Server] =
    EmberServerBuilder
      .default[IO]
      .withPort(config.port)
      .withHost(config.host)
      .withShutdownTimeout(1.second)
      .withHttpWebSocketApp(app)
      .build

end TracerServer

object TracerServer:
  def create(
      in: fs2.Stream[IO, Byte],
      out: fs2.Stream[IO, Byte],
      err: fs2.Stream[IO, Byte]
  ): TracerServer =
    TracerServer(in, out, err)

end TracerServer

case class Received[T](timestamp: Long, value: T)
object Received:
  def capture[T](t: T) = IO.monotonic.map(lng => Received(lng.toMillis, t))
  def captureF[T](t: IO[T]) =
    IO.monotonic.flatMap(lng => t.map(Received(lng.toMillis, _)))
