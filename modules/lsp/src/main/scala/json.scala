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

import langoustine.*
import upickle.default.*
import scala.util.NotGiven

private[lsp] object json:
  val valueReader = upickle.default.readwriter[ujson.Value]
  def badMerge[T](r1: => Reader[?], rest: Reader[?]*): Reader[T] =
    valueReader.map { json =>
      var t: T | Null = null
      val stack       = Vector.newBuilder[Throwable]

      (r1 +: rest).foreach { reader =>
        if t == null then
          try
            t =
              read[T](json, trace = true)(using reader.asInstanceOf[Reader[T]])
          catch
            case exc =>
              stack += exc
      }
      if t != null then t.nn
      else
        throw new LangoustineError.FailureParsing(
          json,
          stack.result().headOption.getOrElse(null)
        )
    }

  extension [T](r: Reader[T]) def widen[K >: T] = r.map(_.asInstanceOf[K])

  val nullReadWriter: ReadWriter[Null] =
    readwriter[ujson.Value].bimap[Null](_ => ujson.Null, _ => null)

  given constStrReader[T <: String](using NotGiven[T =:= String]): Reader[T] =
    stringCodec.asInstanceOf[Reader[T]]

  given constStrWriter[T <: String](using NotGiven[T =:= String]): Writer[T] =
    stringCodec.asInstanceOf[Writer[T]]

  val stringCodec = summon[ReadWriter[String]]
  val intCodec    = summon[ReadWriter[Int]]
  val unitReader  = summon[ReadWriter[Unit]]
  val unitWriter  = summon[ReadWriter[Unit]]
  val jsReader    = reader[ujson.Value]
  val jsWriter    = writer[ujson.Value]

  def vectorWriter[T: Writer]: Writer[Vector[T]] = summon[Writer[Vector[T]]]
  def vectorReader[T: Reader]: Reader[Vector[T]] = summon[Reader[Vector[T]]]

end json

import scala.deriving.Mirror

import upickle.implicits.macros
import upickle.default.*
import upickle.core.*

private[lsp] object Pickle:
  import scala.deriving.*
  import scala.compiletime.*
  inline final def summonLabelsRec[T <: Tuple]: List[String] =
    inline erasedValue[T] match
      case _: EmptyTuple => Nil
      case _: (t *: ts) =>
        constValue[t].asInstanceOf[String] :: summonLabelsRec[ts]

  inline final def summonDecoder[A]: Reader[A] = summonFrom {
    case decodeA: Reader[A] => decodeA
    case _: Mirror.Of[A]    => macroR[A]
  }

  inline final def summonDecodersRec[T <: Tuple]: List[Reader[?]] =
    inline erasedValue[T] match
      case _: EmptyTuple => Nil
      case _: (t *: ts)  => summonDecoder[t] :: summonDecodersRec[ts]

  inline def macroR[T](using m: Mirror.Of[T]): Reader[T] = inline m match
    case m: Mirror.ProductOf[T] =>
      val labels: List[String] =
        summonLabelsRec[m.MirroredElemLabels] // macros.fieldLabels[T]
      lazy val visitors: List[Visitor[?, ?]] =
        summonDecodersRec[m.MirroredElemTypes]
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
