package langoustine

import scala.util.Try.apply
import scala.util.Try
import scala.util.Success

import upickle.default.{Reader, Writer}
import cats.syntax.all._

enum LSPError:
  case NotImplementedError

opaque type ReqHandler[F[_], I, O] = I => F[O | LSPError]
object ReqHandler:
  def apply[F[_], I, O](f: I => F[O | LSPError]): ReqHandler[F, I, O] = f

sealed abstract class Req(val requestMethod: String):
  type In
  type Out

  given reader: Reader[In]
  given writer: Writer[Out] = upickle.default.writer[String].comap(_.toString)

trait Definition
trait DefinitionLink
case class ImplementationParams(h: String)
case class DocumentParams(dp: Int)

object textDocument:
  object implementation extends Req("textDocument/implementation"):
    type Out = Definition | Vector[DefinitionLink] | BaseTypes.NULL.type
    object Out:
      def apply(d: Definition): Out             = d
      def apply(d: Vector[DefinitionLink]): Out = d
      def NULL: Out                             = BaseTypes.NULL

    export Out.apply as respondWith
    export Out.NULL as respondWithNull

    type In = ImplementationParams

    given reader: Reader[In] = Pickle.macroR

  object definition extends Req("textDocument/definition"):
    opaque type Out = Definition | Vector[DefinitionLink] | BaseTypes.NULL.type
    object Out:
      def apply(d: Definition): Out             = d
      def apply(d: Vector[DefinitionLink]): Out = d
      def NULL: Out                             = BaseTypes.NULL

    opaque type In = DocumentParams

    given reader: Reader[In] = Pickle.macroR

trait LSPBuilder[F[_]]:
  def handler[X <: Req](t: X)(
      f: (t.In, X) => F[t.Out | LSPError]
  ): LSPBuilder[F]

  def build: JSONRPC.RequestMessage => F[JSONRPC.ResponseMessage]

import cats.MonadThrow

case class ImmutableLSPBuilder[F[_]: MonadThrow] private (
    mp: Map[String, JSONRPC.Handler[F]]
) extends LSPBuilder[F]:

  val F = MonadThrow[F]

  def handler[X <: Req](t: X)(
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

@main def yep =
  val builder = ImmutableLSPBuilder
    .create[Try]
    .handler(textDocument.implementation) { (in, req) =>
      Success(req.respondWithNull)
    }
    .handler(textDocument.definition) { (in, req) =>
      Success(req.Out(Vector.empty))
    }
    .build

  val req =
    JSONRPC.request(25, "textDocument/implementation", """{"h": "bla!"}""")

  val req1 =
    JSONRPC.request(26, "textDocument/definition", """{"dp": 152}""")

  println(builder(req).map(_.result))
  println(builder(req1).map(_.result))
