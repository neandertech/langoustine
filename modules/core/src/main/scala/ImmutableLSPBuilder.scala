package langoustine

import scala.util.Try.apply
import scala.util.Try
import scala.util.Success
import upickle.default.{Reader, Writer}
import cats.syntax.all._
import cats.MonadThrow

case class ImmutableLSPBuilder[F[_]: MonadThrow] private (
    mp: Map[String, JSONRPC.Handler[F]]
) extends LSPBuilder[F]:

  val F = MonadThrow[F]

  def handler[X <: LSPRequest](t: X)(
      f: (t.In, X) => F[t.Out | LSPError]
  ) = copy(
    mp.updated(
      t.requestMethod,
      { (msg: JSONRPC.RequestMessage) =>
        val inBytes = msg.params
        F.catchNonFatal(
          upickle.default.read[t.In](msg.params)(using t.reader)
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

  def build = rqm =>
    mp.get(rqm.method) match
      case Some(handler) => handler(rqm)
      case None          => ???

object ImmutableLSPBuilder:
  def create[F[_]: MonadThrow]: LSPBuilder[F] =
    new ImmutableLSPBuilder[F](Map.empty)
