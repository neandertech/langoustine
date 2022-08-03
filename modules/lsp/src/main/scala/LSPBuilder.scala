package langoustine.lsp

import scala.util.Try.apply
import scala.util.Try
import scala.util.Success
import upickle.default.{Reader, Writer}
import cats.MonadThrow

import jsonrpclib.Endpoint

import requests.LSPRequest
import notifications.LSPNotification
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

trait LSPBuilder[F[_]]:
  def handleRequest[X <: LSPRequest](t: X)(
      f: (t.In, Communicate[F]) => F[t.Out]
  ): LSPBuilder[F]

  def handleNotification[X <: LSPNotification](t: X)(
      f: t.In => F[Unit]
  ): LSPBuilder[F]

  def build(comm: Communicate[F]): List[Endpoint[F]]

  def bind(channel: Channel[F])(using Monadic[F]): F[Channel[F]] =
    val Fm         = summon[Monadic[F]]
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
