package langoustine

import scala.util.Try.apply
import scala.util.Try
import scala.util.Success
import upickle.default.{Reader, Writer}
import cats.syntax.all.*
import cats.MonadThrow
import langoustine.JSONRPC.RequestMessage
import langoustine.JSONRPC.ResponseMessage

case class ImmutableLSPBuilder[F[_]: MonadThrow] private (
    mp: Map[String, JSONRPC.Handler[F]],
    logger: scribe.Logger
) extends LSPBuilder[F]:

  val F = MonadThrow[F]

  def handler[X <: LSPRequest](t: X)(
      f: (t.In, X) => F[t.Out | LSPError]
  ) = copy(
    mp.updated(
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
                upickle.default.write[t.Out](s.asInstanceOf[t.Out])(using
                  t.writer
                )
              )
          }
          .map(JSONRPC.response(msg.id, _))
      }
    )
  )

  def build: RequestMessage => F[ResponseMessage] = rqm =>
    mp.get(rqm.method) match
      case Some(handler) => handler(rqm)
      case None =>
        throw LSPError.NotImplementedError(
          s"Method ${rqm.method} is not implemented"
        )
end ImmutableLSPBuilder

object ImmutableLSPBuilder:
  def create[F[_]: MonadThrow]: LSPBuilder[F] =
    new ImmutableLSPBuilder[F](Map.empty, scribe.Logger("LSP"))

  def create[F[_]: MonadThrow](log: scribe.Logger): LSPBuilder[F] =
    new ImmutableLSPBuilder[F](Map.empty, log)
