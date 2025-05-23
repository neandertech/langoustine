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

package tests.tracer

import cats.effect.*
import cats.effect.syntax.all.*
import jsonrpclib.Payload
import fs2.concurrent.Topic
import cats.syntax.all.*
import fs2.Chunk
import langoustine.tracer.*
import org.http4s.Uri
import jsonrpclib.Message
import jsonrpclib.CallId

abstract class ServerSpec extends weaver.IOSuite:
  type Res = Feed
  override def sharedResource: Resource[cats.effect.IO, Res] =
    val create  = Topic[IO, Message]
    val channel = Topic[IO, Chunk[Byte]]

    Resource
      .eval((create, create, channel, IO.deferred[Uri]).parTupled)
      .flatMap { case (in, out, err, uri) =>
        val server =
          TracerServer
            .create(
              in = in.subscribe(1),
              out = out.subscribe(1),
              err = err.subscribe(1).unchunks
            )
            .run(
              Config.Defaults.bind,
              Summary
                .Trace(System.getProperty("user.dir"), List("echo", "world")),
              await = serv => uri.complete(serv.baseUri) *> IO.never
            )
            .compile
            .drain
            .background

        import org.http4s.jdkhttpclient.*

        val client =
          JdkHttpClient.simple[IO].toResource

        val ws = JdkWSClient.simple[IO].toResource

        val idState = IO.ref(0L).toResource

        (client, idState, ws, uri.get.toResource)
          .parMapN { case (c, ref, w, baseUri) =>
            val genId =
              ref
                .getAndUpdate(_ + 1)
                .map(CallId.NumberId.apply)
                .map(Some(_))

            Feed(in, out, err, Front(c, baseUri, w), genId)
          }
          .parProductL(server)
      }
  end sharedResource
end ServerSpec
