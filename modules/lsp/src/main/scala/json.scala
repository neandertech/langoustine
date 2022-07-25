package langoustine.lsp

import langoustine.*
import upickle.default.*
import scala.util.NotGiven

object json:
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

  extension [T](r: Reader[T]) def widen[K >: T] = r.map(_.asInstanceOf[K])

  given nullReadWriter: ReadWriter[Null] = nullCodec
  given constStrReader[T <: String]: Reader[T] = 
    stringCodec.asInstanceOf[Reader[T]]
  val stringCodec = upickle.default.readwriter[String]
  val intCodec    = upickle.default.readwriter[Int]
  val nullCodec    = upickle.default.readwriter[Null]
end json

import scala.deriving.Mirror

import upickle.implicits.macros
import upickle.default.*
import upickle.core.*

object Pickle:
  inline def macroR[T](using m: Mirror.Of[T]): Reader[T] = inline m match
    case m: Mirror.ProductOf[T] =>
      val labels: List[String] = macros.fieldLabels[T]
      val visitors: List[Visitor[?, ?]] =
        macros
          .summonList[Tuple.Map[m.MirroredElemTypes, Reader]]
          .asInstanceOf[List[upickle.core.Visitor[?, ?]]]
      val defaultParams: Map[String, AnyRef] = macros.getDefaultParams[T]

      val reader = new CaseClassReader[T]:
        override def visitorForKey(key: String) =
          labels.zip(visitors).toMap.get(key) match
            case None    => upickle.core.NoOpVisitor
            case Some(v) => v

        override def make(params: Map[String, Any]): T =
          val values      = collection.mutable.ListBuffer.empty[AnyRef]
          val missingKeys = collection.mutable.ListBuffer.empty[String]

          labels.zip(visitors).map { case (fieldName, _) =>
            params.get(fieldName) match
              case Some(value) => values += value.asInstanceOf[AnyRef]
              case None =>
                defaultParams.get(fieldName) match
                  case Some(fallback) => values += fallback.asInstanceOf[AnyRef]
                  case None           => missingKeys += fieldName
          }

          if !missingKeys.isEmpty then
            throw new upickle.core.Abort(
              "missing keys in dictionary: " + missingKeys.mkString(", ")
            )

          val valuesArray = values.toArray
          m.fromProduct(new Product:
            def canEqual(that: Any): Boolean = true
            def productArity: Int            = valuesArray.length
            def productElement(i: Int): Any  = valuesArray(i)
          )
        end make

      reader

    // if macros.isMemberOfSealedHierarchy[T] then annotate(reader, macros.fullClassName[T])
    // else reader

    case m: Mirror.SumOf[T] =>
      println("OH NO")
      inline compiletime.erasedValue[T] match
        case _: scala.reflect.Enum =>
          val valueOf     = macros.enumValueOf[T]
          val description = macros.enumDescription[T]
          new EnumReader[T](valueOf, description)
        case _ =>
          val readers: List[Reader[? <: T]] = macros
            .summonList[Tuple.Map[m.MirroredElemTypes, Reader]]
            .asInstanceOf[List[Reader[? <: T]]]
          Reader.merge[T](readers*)
      end match
end Pickle
