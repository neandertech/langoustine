package langoustine

import scala.util.Try.apply
import scala.util.Try
import scala.util.Success

import upickle.default.{Reader, Writer}
import cats.syntax.all._

sealed abstract class LSPRequest(val requestMethod: String):
  type In
  type Out

  given reader: Reader[In]
  given writer: Writer[Out] = upickle.default.writer[String].comap(_.toString)

trait Definition
trait DefinitionLink
case class ImplementationParams(h: String)
case class DocumentParams(dp: Int)

extension [T](r: Reader[T]) def widen[K >: T] = r.map(_.asInstanceOf[K])

def badMerge[T](r1: Reader[T], rest: Reader[T]*): Reader[T] =
  upickle.default.reader[ujson.Value].map { json =>
    var t: T | Null = null
    (r1 +: rest).foreach { reader =>
      if t == null then
        try t = upickle.default.read[T](json)(using reader)
        catch case exc => ()

    }
    if t != null then t.nn else throw new LSPError.FailureParsing(json)
  }

object textDocument:
  object implementation extends LSPRequest("textDocument/implementation"):
    type In  = ImplementationParams | DocumentParams
    type Out = Definition | Vector[DefinitionLink] | BaseTypes.NULL.type

    object Out:
      def apply(d: Definition): Out             = d
      def apply(d: Vector[DefinitionLink]): Out = d
      def NULL: Out                             = BaseTypes.NULL

    export Out.apply as respondWith
    export Out.NULL as respondWithNull

    given reader: Reader[In] = badMerge(
      Pickle.macroR[ImplementationParams].widen[In],
      Pickle.macroR[DocumentParams].widen[In]
    )

  object definition extends LSPRequest("textDocument/definition"):
    opaque type Out = Definition | Vector[DefinitionLink] | BaseTypes.NULL.type
    object Out:
      def apply(d: Definition): Out             = d
      def apply(d: Vector[DefinitionLink]): Out = d
      def NULL: Out                             = BaseTypes.NULL

    opaque type In = DocumentParams

    given reader: Reader[In] = Pickle.macroR

import cats.MonadThrow

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
    JSONRPC.request(25, "textDocument/implementation", """{"dp": "bla!"}""")

  val req1 =
    JSONRPC.request(26, "textDocument/definition", """{"dp": 152}""")

  println(builder(req).map(_.result))
  println(builder(req1).map(_.result))
