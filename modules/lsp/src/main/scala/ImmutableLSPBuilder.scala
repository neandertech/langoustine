package langoustine.lsp

import scala.util.Try.apply
import scala.util.Try
import scala.util.Success
import upickle.default.{Reader, Writer}
import cats.syntax.all.*
import cats.MonadThrow

import jsonrpclib.*

import requests.{LSPRequest, LSPNotification}

private[lsp] case class ImmutableLSPBuilder[F[_]: Monadic] private (
    endpoints: List[Endpoint[F]],
    logger: scribe.Logger,
    notifyDelegate: Communicate.Delegate[F]
) extends LSPBuilder[F]:

  override def handleNotification[X <: LSPNotification](t: X)(
      f: Invocation[t.In, F] => F[Unit]
  ) = copy(
    endpoints = endpoints :+ jsonrpcIntegration.handlerToNotification(t) { in =>
      f(Invocation.Impl(in, notifyDelegate))
    }
  )

  override def handleRequest[X <: LSPRequest](t: X)(
      f: Invocation[t.In, F] => F[t.Out]
  ) = copy(
    endpoints = endpoints :+ jsonrpcIntegration.handlerToEndpoint(t) { in =>
      f(Invocation.Impl(in, notifyDelegate))
    }
  )

  override def build(notifications: Communicate[F]) =
    notifyDelegate.unsafeRedirect(notifications)
    endpoints

end ImmutableLSPBuilder

private[lsp] object ImmutableLSPBuilder:
  def create[F[_]: Monadic]: LSPBuilder[F] =
    new ImmutableLSPBuilder[F](
      Nil,
      scribe.Logger("LSP"),
      new Communicate.Delegate[F](Communicate.drop[F])
    )

  def create[F[_]: Monadic](log: scribe.Logger): LSPBuilder[F] =
    new ImmutableLSPBuilder[F](
      Nil,
      log,
      new Communicate.Delegate[F](Communicate.drop[F])
    )
end ImmutableLSPBuilder
