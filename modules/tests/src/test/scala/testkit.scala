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

package tests.core

import fs2.Stream
import jsonrpclib.fs2.FS2Channel
import langoustine.lsp.*

import jsonrpclib.*

import cats.MonadThrow
import langoustine.lsp.jsonrpcIntegration.given
import cats.effect.IO
import cats.effect.kernel.Ref

def setupChannels[Alg[_[_, _, _, _, _]]](
    mkServer: FS2Channel[IO] => List[Endpoint[IO]],
    mkClient: FS2Channel[IO] => List[Endpoint[IO]] = _ => List.empty
): Stream[IO, Channel[IO]] =
  for
    serverSideChannel <- FS2Channel.stream[IO]()
    clientSideChannel <- FS2Channel.stream[IO]()
    serverChannelWithEndpoints <- serverSideChannel.withEndpointsStream(
      mkServer(serverSideChannel)
    )
    clientChannelWithEndpoints <- clientSideChannel.withEndpointsStream(
      mkClient(clientSideChannel)
    )
    _ <- Stream(())
      .concurrently(
        clientChannelWithEndpoints.output
          .through(serverChannelWithEndpoints.input)
      )
      .concurrently(
        serverChannelWithEndpoints.output
          .through(clientChannelWithEndpoints.input)
      )
  yield clientChannelWithEndpoints

def request[F[_]: MonadThrow, T <: requests.LSPRequest](
    channel: Channel[F],
    req: T,
    in: req.In
) =
  val remoteCall = channel.simpleStub[req.In, req.Out](req.requestMethod)
  Stream.eval(remoteCall(in))
end request

def notification[F[_]: MonadThrow, T <: requests.LSPNotification](
    channel: Channel[F],
    req: T,
    in: req.In
) =
  val remoteCall = channel.notificationStub[req.In](req.notificationMethod)
  Stream.eval(remoteCall(in))
end notification

def collectNotificationEndpoint[F[_], T <: requests.LSPNotification](
    ref: Ref[F, Map[requests.LSPNotification, List[Any]]]
)(req: T) =
  Endpoint[F](req.notificationMethod)
    .notification[req.In](in =>
      ref.update { mp =>
        mp.updatedWith(req) { prev =>
          prev.map(_ :+ in).orElse(Some(List(in)))
        }
      }
    )

def getCaputured[F[_], T <: requests.LSPNotification](
    ref: Stream[F, Map[requests.LSPNotification, List[Any]]]
)(req: T) =
  ref
    .dropWhile(_.isEmpty)
    .map(_.get(req))
    .collectFirst { case Some(v) => v.asInstanceOf[List[req.In]] }
