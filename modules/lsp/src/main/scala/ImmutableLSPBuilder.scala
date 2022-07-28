package langoustine.lsp

import scala.util.Try.apply
import scala.util.Try
import scala.util.Success
import upickle.default.{Reader, Writer}
import cats.syntax.all.*
import cats.MonadThrow
import JSONRPC.*

import requests.LSPRequest
import notifications.LSPNotification

case class ImmutableLSPBuilder[F[_]: MonadThrow] private (
    requestHandlers: Map[String, JSONRPC.RequestHandler[F]],
    notificationHandlers: Map[String, JSONRPC.NotificationHandler[F]],
    logger: scribe.Logger
) extends LSPBuilder[F]:

  val F = MonadThrow[F]

  def handleNotification[X <: LSPNotification](t: X)(
      f: (t.In, X) => F[Unit | LSPError]
  ) = copy(
    notificationHandlers = notificationHandlers.updated(
      t.notificationMethod,
      { (msg: JSONRPC.Notification) =>
        val inBytes = msg.params

        logger.info(
          s"Received a ${msg.method} notification",
          msg.params.toString
        )

        F.catchNonFatal(
          upickle.default.read[t.In](inBytes, trace = true)
        ).flatMap(f(_, t))
          .void

      }
    )
  )

  def handleRequest[X <: LSPRequest](t: X)(
      f: (t.In, X) => F[t.Out | LSPError]
  ) = copy(
    requestHandlers = requestHandlers.updated(
      t.requestMethod,
      { (msg: JSONRPC.RequestMessage) =>
        val inBytes = msg.params

        logger.info(
          s"Received a ${msg.method} request (id = ${msg.id}) ",
          msg.params.toString
        )

        F.catchNonFatal(
          upickle.default.read[t.In](inBytes, trace = true)
        ).flatMap(f(_, t))
          .map {
            case e: LSPError => Left(JSONRPC.error(0, e.toString))
            case s =>
              Right(
                upickle.default.writeJs[t.Out](s.asInstanceOf[t.Out])(using
                  t.writer
                )
              )
          }
          .map(JSONRPC.response(msg.id, _))
      }
    )
  )

  def build = rqm =>
    rqm match
      case req: RequestMessage =>
        requestHandlers
          .get(req.method)
          .map(_(req).map(Option.apply))
          .getOrElse(
            MonadThrow[F].raiseError(
              LSPError.NotImplementedError(
                s"Request handler for '${req.method}' is not implemented"
              )
            )
          )
      case req: Notification =>
        notificationHandlers
          .get(req.method)
          .map(_(req).map(_ => None))
          .getOrElse(
            MonadThrow[F].raiseError(
              LSPError.NotImplementedError(
                s"Notification handler for '${req.method}' is not implemented"
              )
            )
          )
end ImmutableLSPBuilder

object ImmutableLSPBuilder:
  def create[F[_]: MonadThrow]: LSPBuilder[F] =
    new ImmutableLSPBuilder[F](Map.empty, Map.empty, scribe.Logger("LSP"))

  def create[F[_]: MonadThrow](log: scribe.Logger): LSPBuilder[F] =
    new ImmutableLSPBuilder[F](Map.empty, Map.empty, log)
