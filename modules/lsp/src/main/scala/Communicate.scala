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

package langoustine.lsp

import upickle.default.{Reader, Writer}
import cats.MonadThrow
import jsonrpclib.Endpoint
import jsonrpclib.Monadic
import jsonrpclib.Codec
import jsonrpclib.Payload
import jsonrpclib.Channel
import requests.*

trait Communicate[F[_]]:
  def notification[X <: LSPNotification](notif: X, in: notif.In): F[Unit]
  def request[X <: LSPRequest](req: X, in: req.In): F[req.Out]
  def shutdown: F[Unit]

  def request[X <: LSPRequest](req: PreparedRequest[X]): F[req.Out] =
    this.request[X](req.x, req.in)

  def notification[X <: LSPNotification](
      req: PreparedNotification[X]
  ): F[Unit] =
    this.notification[X](req.x, req.in)
end Communicate

object Communicate:
  import jsonrpcIntegration.given

  def channel[F[_]: Monadic](channel: Channel[F]) =
    new Communicate[F]:
      override def notification[X <: LSPNotification](
          notif: X,
          in: notif.In
      ): F[Unit] =
        channel.notificationStub(notif.notificationMethod).apply(in)

      override def request[X <: LSPRequest](req: X, in: req.In): F[req.Out] =
        channel.simpleStub(req.requestMethod).apply(in)

      override def shutdown: F[Unit] = summon[Monadic[F]].doPure(())

  def channel[F[_]: Monadic](channel: Channel[F], initiateShutdown: F[Unit]) =
    new Communicate[F]:
      override def notification[X <: LSPNotification](
          notif: X,
          in: notif.In
      ): F[Unit] =
        channel.notificationStub(notif.notificationMethod).apply(in)

      override def request[X <: LSPRequest](req: X, in: req.In): F[req.Out] =
        channel.simpleStub(req.requestMethod).apply(in)

      override def shutdown: F[Unit] = initiateShutdown

  def drop[F[_]: Monadic]: Communicate[F] =
    new:
      val F = summon[Monadic[F]]
      override def notification[X <: LSPNotification](
          notif: X,
          in: notif.In
      ): F[Unit] =
        F.doPure(())

      override def request[X <: LSPRequest](req: X, in: req.In): F[req.Out] =
        F.doRaiseError(
          LangoustineError.StubError(
            "A request call to a stubbed out Communicate interface was performed"
          )
        )

      override def shutdown = F.doPure(())

  private[lsp] class Delegate[F[_]](var to: Communicate[F])
      extends Communicate[F]:
    def notification[X <: LSPNotification](notif: X, in: notif.In): F[Unit] =
      to.notification(notif, in)

    def request[X <: LSPRequest](notif: X, in: notif.In): F[notif.Out] =
      to.request(notif, in)

    def shutdown: F[Unit] = to.shutdown

    def unsafeRedirect(other: Communicate[F]): Delegate[F] =
      synchronized { to = other }
      this
  end Delegate
end Communicate
