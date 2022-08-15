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

import requests.{LSPRequest, LSPNotification}
import jsonrpclib.Monadic
import jsonrpclib.Codec
import jsonrpclib.Payload
import jsonrpclib.Channel

trait Communicate[F[_]]:
  def notification[X <: LSPNotification](notif: X, in: notif.In): F[Unit]
  def request[X <: LSPRequest](req: X, in: req.In): F[req.Out]

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

  private[lsp] class Delegate[F[_]](var to: Communicate[F])
      extends Communicate[F]:
    def notification[X <: LSPNotification](notif: X, in: notif.In): F[Unit] =
      to.notification(notif, in)

    def request[X <: LSPRequest](notif: X, in: notif.In): F[notif.Out] =
      to.request(notif, in)

    def unsafeRedirect(other: Communicate[F]): Delegate[F] =
      synchronized { to = other }
      this
  end Delegate
end Communicate

trait Invocation[P, F[_]]:
  def params: P
  def toClient: Communicate[F]

object Invocation:
  private[lsp] case class Impl[P, F[_]](params: P, toClient: Communicate[F])
      extends Invocation[P, F]

trait LSPBuilder[F[_]]:
  def handleRequest[X <: LSPRequest](t: X)(
      f: Invocation[t.In, F] => F[t.Out]
  ): LSPBuilder[F]

  def handleNotification[X <: LSPNotification](t: X)(
      f: Invocation[t.In, F] => F[Unit]
  ): LSPBuilder[F]

  @deprecated(
    "This method is deprecated in favour of one using Invocation",
    "0.1.0"
  )
  def handleRequest[X <: LSPRequest](t: X)(
      f: (t.In, Communicate[F]) => F[t.Out]
  ): LSPBuilder[F] = handleRequest(t)(invok => f(invok.params, invok.toClient))

  @deprecated(
    "This method is deprecated in favour of one using Invocation",
    "0.1.0"
  )
  def handleNotification[X <: LSPNotification](t: X)(
      f: (t.In, Communicate[F]) => F[Unit]
  ): LSPBuilder[F] =
    handleNotification(t)(invok => f(invok.params, invok.toClient))

  def build(comm: Communicate[F]): List[Endpoint[F]]

  def bind[T <: Channel[F]](channel: T)(using Monadic[F]): F[T] =
    val Fm        = summon[Monadic[F]]
    val endpoints = build(Communicate.channel(channel))

    endpoints match
      case Nil => Fm.doPure(channel)
      case h :: t =>
        var curr = channel.mountEndpoint(h)
        t.foreach { e =>
          curr = Fm.doFlatMap(curr)(_ => channel.mountEndpoint(e))
        }

        Fm.doFlatMap(curr)(_ => Fm.doPure(channel))
    end match
  end bind

end LSPBuilder

object LSPBuilder:
  def create[F[_]: Monadic]: LSPBuilder[F] =
    ImmutableLSPBuilder.create[F]

  def create[F[_]: Monadic](log: scribe.Logger): LSPBuilder[F] =
    ImmutableLSPBuilder.create[F](log)
