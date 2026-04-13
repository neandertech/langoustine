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

import scala.util.NotGiven

import langoustine.*
import upickle.default.*

import io.circe.*

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.circe.JsoniterScalaCodec.*
import scala.reflect.TypeTest
import io.circe.Codec
import scala.util.boundary.Label
import io.circe.Decoder
import io.circe.DecodingFailure.Reason.WrongTypeExpectation
import scala.reflect.Typeable

private[lsp] class Dec(fields: Map[String, Json]):

  private def recoverError[T](
      e: Decoder.Result[T],
      field: String
  ): Decoder.Result[T] =
    // *sigh* O(N) addition because it's a list
    e.left.map(de => de.copy(history = de.history :+ CursorOp.DownField(field)))

  def getOpt[E](
      k: String,
      dec: Decoder[E]
  ): Decoder.Result[Option[E]] =
    if !fields.contains(k) then Right(None)
    else
      recoverError(Decoder.decodeOption(using dec).apply(fields(k).hcursor), k)

  inline private def getJsonUnsafe(k: String) =
    if !fields.contains(k) then
      Left(
        DecodingFailure(
          DecodingFailure.Reason.MissingField,
          List(CursorOp.DownField(k))
        )
      )
    else Right(fields(k))

  def get[E](
      k: String,
      dec: Decoder[E]
  ): Decoder.Result[E] =
    if !fields.contains(k) then
      Left(
        DecodingFailure(
          s"no such key $k",
          List(CursorOp.DownField(k))
        )
      )
    else recoverError(dec.apply(fields(k).hcursor), k)

  def getUnion2[T1, T2](
      k: String,
      decT1: Decoder[T1],
      decT2: Decoder[T2]
  ): Decoder.Result[T1 | T2] =
    getJsonUnsafe(k).flatMap: json =>
      val dec = Dec.merge[T1 | T2](decT1, decT2)

      recoverError(dec.apply(json.hcursor), k)

  def getUnion3[T1, T2, T3](
      k: String,
      decT1: Decoder[T1],
      decT2: Decoder[T2],
      decT3: Decoder[T3]
  ): Decoder.Result[T1 | T2 | T3] =
    getJsonUnsafe(k).flatMap: json =>
      val dec = Dec.merge[T1 | T2 | T3](decT1, decT2, decT3)

      recoverError(dec.apply(json.hcursor), k)

  def getUnion4[T1, T2, T3, T4](
      k: String,
      decT1: Decoder[T1],
      decT2: Decoder[T2],
      decT3: Decoder[T3],
      decT4: Decoder[T4]
  ): Decoder.Result[T1 | T2 | T3 | T4] =
    getJsonUnsafe(k).flatMap: json =>
      val dec = Dec.merge[T1 | T2 | T3 | T4](decT1, decT2, decT3, decT4)

      recoverError(dec.apply(json.hcursor), k)
end Dec

private[lsp] object Dec:

  // TODO: cache this
  def merge[T](dec1: Decoder[?], rest: Decoder[?]*): Decoder[T] =
    rest.foldLeft(dec1.asInstanceOf[Decoder[T]])((acc, el) =>
      acc.or(el.asInstanceOf[Decoder[T]])
    )

  def fromJsonObject[T](
      f: Dec => Either[DecodingFailure, T]
  ): Decoder[T] =

    new Decoder[T]:
      final override def apply(c: HCursor): Either[DecodingFailure, T] =
        c.value.asObject match
          case Some(obj) =>
            val fields = obj.toMap
            f(Dec(fields))
          case None =>
            Left(
              DecodingFailure(
                WrongTypeExpectation("object", c.value),
                c.history
              )
            )
end Dec

private[lsp] class Enc:
  val builder = Iterable.newBuilder[(String, Json)]
  def field[T](k: String, value: T, enc: Encoder[T]): Unit =
    builder += k -> enc(value)

  def union2[T1: Typeable, T2: Typeable](
      k: String,
      value: T1 | T2,
      encT1: Encoder[T1],
      encT2: Encoder[T2]
  ): Unit =
    value match
      case v: T1 => builder += k -> encT1(v)
      case v: T2 => builder += k -> encT2(v)

  def union3[T1: Typeable, T2: Typeable, T3: Typeable](
      k: String,
      value: T1 | T2 | T3,
      encT1: Encoder[T1],
      encT2: Encoder[T2],
      encT3: Encoder[T3]
  ): Unit =
    value match
      case v: T1 => builder += k -> encT1(v)
      case v: T2 => builder += k -> encT2(v)
      case v: T3 => builder += k -> encT3(v)

  def union4[T1: Typeable, T2: Typeable, T3: Typeable, T4: Typeable](
      k: String,
      value: T1 | T2 | T3 | T4,
      encT1: Encoder[T1],
      encT2: Encoder[T2],
      encT3: Encoder[T3],
      encT4: Encoder[T4]
  ): Unit =
    value match
      case v: T1 => builder += k -> encT1(v)
      case v: T2 => builder += k -> encT2(v)
      case v: T3 => builder += k -> encT3(v)
      case v: T4 => builder += k -> encT4(v)

  def result(): Json =
    Json.fromFields(builder.result())
end Enc

private[lsp] object Enc:
  def toJsonObject[T](f: (Enc, T) => Unit): Encoder[T] =
    Encoder.instance[T]: t =>
      val enc = new Enc
      f(enc, t)
      enc.result()
end Enc

// private[lsp] object json:
//   val valueReader = upickle.default.readwriter[ujson.Value]
//   def badMerge[T](r1: => Reader[?], rest: Reader[?]*): Reader[T] =
//     valueReader.map { json =>
//       var t     = Option.empty[T]
//       val stack = Vector.newBuilder[Throwable]

//       (r1 +: rest).foreach { reader =>
//         if t.isEmpty then
//           try
//             t = Some(
//               read[T](json, trace = true)(using reader.asInstanceOf[Reader[T]])
//             )
//           catch
//             case exc =>
//               stack += exc
//       }
//       t.getOrElse(
//         throw new LangoustineError.FailureParsing(
//           json,
//           stack.result().headOption.getOrElse(null)
//         )
//       )
//     }

//   extension [T](r: Reader[T]) def widen[K >: T] = r.map(_.asInstanceOf[K])

//   val nullReadWriter: ReadWriter[Null] =
//     readwriter[ujson.Value].bimap[Null](_ => ujson.Null, _ => null)

//   given constStrReader[T <: String](using NotGiven[T =:= String]): Reader[T] =
//     stringCodec.asInstanceOf[Reader[T]]

//   given constStrWriter[T <: String](using NotGiven[T =:= String]): Writer[T] =
//     stringCodec.asInstanceOf[Writer[T]]

//   val stringCodec = summon[ReadWriter[String]]
//   val intCodec    = summon[ReadWriter[Int]]
//   val unitReader  = summon[ReadWriter[Unit]]
//   val unitWriter  = summon[ReadWriter[Unit]]
//   val jsReader    = reader[ujson.Value]
//   val jsWriter    = writer[ujson.Value]

//   def vectorWriter[T: Writer]: Writer[Vector[T]] = summon[Writer[Vector[T]]]
//   def vectorReader[T: Reader]: Reader[Vector[T]] = summon[Reader[Vector[T]]]

// end json

import upickle.implicits.macros
import upickle.core.*

private[lsp] object Pickle:
  // import scala.deriving.*
  // import scala.compiletime.*
  // inline final def summonLabelsRec[T <: Tuple]: List[String] =
  //   inline erasedValue[T] match
  //     case _: EmptyTuple => Nil
  //     case _: (t *: ts) =>
  //       constValue[t].asInstanceOf[String] :: summonLabelsRec[ts]

  // inline final def summonDecoder[A]: Reader[A] = summonFrom {
  //   case decodeA: Reader[A] => decodeA
  //   case _: Mirror.Of[A]    => macroR[A]
  // }

  // inline final def summonDecodersRec[T <: Tuple]: List[Reader[?]] =
  //   inline erasedValue[T] match
  //     case _: EmptyTuple => Nil
  //     case _: (t *: ts)  => summonDecoder[t] :: summonDecodersRec[ts]

  // private[lsp] class MyCaseClassReader[T](
  //     labels: List[String],
  //     // keep lazy for recursive datatypes
  //     visitors: => List[Visitor[?, ?]],
  //     defaultParams: Map[String, AnyRef],
  //     fromArray: Array[Any] => T
  // ) extends CaseClassReader[T]:
  //   override def visitorForKey(key: String) =
  //     labels.zip(visitors).toMap.get(key) match
  //       case None    => upickle.core.NoOpVisitor
  //       case Some(v) => v

  //   override def make(params: Map[String, Any]): T =
  //     val values      = collection.mutable.ListBuffer.empty[AnyRef]
  //     val missingKeys = collection.mutable.ListBuffer.empty[String]

  //     labels.zip(visitors).map { case (fieldName, _) =>
  //       params.get(fieldName) match
  //         case Some(value) => values += value.asInstanceOf[AnyRef]
  //         case None =>
  //           defaultParams.get(fieldName) match
  //             case Some(fallback) => values += fallback.asInstanceOf[AnyRef]
  //             case None           => missingKeys += fieldName
  //     }

  //     if !missingKeys.isEmpty then
  //       throw new upickle.core.Abort(
  //         "missing keys in dictionary: " + missingKeys.mkString(", ")
  //       )

  //     fromArray(values.toArray)
  //   end make
  // end MyCaseClassReader
  //
  import scala.deriving.*
  inline def macroR[T](using m: Mirror.Of[T]): Reader[T] =
    upickle.default.macroR[T]

  // inline def macroR[T](using m: Mirror.Of[T]): Reader[T] = inline m match
  //   case m: Mirror.ProductOf[T] =>
  //     val labels: List[String] =
  //       summonLabelsRec[m.MirroredElemLabels] // macros.fieldLabels[T]
  //     lazy val visitors: List[Visitor[?, ?]] =
  //       summonDecodersRec[m.MirroredElemTypes]
  //         .asInstanceOf[List[upickle.core.Visitor[?, ?]]]
  //     val defaultParams: Map[String, AnyRef] = extractDefaultValues[T]

  //     MyCaseClassReader[T](
  //       labels,
  //       visitors,
  //       defaultParams,
  //       arr => m.fromProduct(Tuple.fromArray(arr))
  //     )

  //   case m: Mirror.SumOf[T] =>
  //     inline compiletime.erasedValue[T] match
  //       case _: scala.reflect.Enum =>
  //         val valueOf     = macros.enumValueOf[T]
  //         val description = macros.enumDescription[T]
  //         new EnumReader[T](valueOf, description)
  //       case _ =>
  //         val readers: List[Reader[? <: T]] = macros
  //           .summonList[Tuple.Map[m.MirroredElemTypes, Reader]]
  //           .asInstanceOf[List[Reader[? <: T]]]
  //         Reader.merge[T](readers*)
  //     end match
  // end macroR

  // import scala.quoted.*

  // private def extractDefaultValues[T: Type](using
  //     Quotes
  // ): Map[String, Expr[Any]] =
  //   import quotes.reflect.*

  //   val sym  = TypeRepr.of[T].typeSymbol
  //   val comp = sym.companionClass

  //   if comp == Symbol.noSymbol then return Map.empty

  //   val mod = Ref(sym.companionModule)
  //   val typeArgs = TypeRepr.of[T] match
  //     case AppliedType(_, args) => args
  //     case _                    => Nil

  //   // Get parameters with default values
  //   val paramsWithDefaults = sym.caseFields.zipWithIndex.collect {
  //     case (field, idx) if field.flags.is(Flags.HasDefault) => (field.name, idx)
  //   }

  //   if paramsWithDefaults.isEmpty then return Map.empty

  //   // Get default value methods from companion
  //   val body = comp.tree.asInstanceOf[ClassDef].body
  //   val defaultMethods = body.collect {
  //     case deff @ DefDef(name, _, _, _)
  //         if name.startsWith("$lessinit$greater$default") =>
  //       val methodNum = name
  //         .stripPrefix("$lessinit$greater$default$")
  //         .toIntOption
  //         .getOrElse(-1)
  //       (methodNum, deff.symbol)
  //   }.toMap

  //   // Map parameter names to their default values
  //   paramsWithDefaults.map { case (paramName, idx) =>
  //     val methodIdx = idx + 1 // Default methods are 1-indexed
  //     defaultMethods.get(methodIdx) match
  //       case Some(symbol) =>
  //         val ref = mod.select(symbol)
  //         val applied =
  //           if typeArgs.nonEmpty then ref.appliedToTypes(typeArgs)
  //           else ref
  //         paramName -> applied.asExpr
  //       case None =>
  //         paramName -> '{ null }.asExprOf[Any]
  //     end match
  //   }.toMap
  // end extractDefaultValues
end Pickle
