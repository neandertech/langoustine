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

  def union2[T1, T2](
      decT1: Decoder[T1],
      decT2: Decoder[T2]
  ): Decoder[T1 | T2] =
    decT1
      .asInstanceOf[Decoder[T1 | T2]]
      .or(decT2.asInstanceOf[Decoder[T1 | T2]])

  def union3[T1, T2, T3](
      decT1: Decoder[T1],
      decT2: Decoder[T2],
      decT3: Decoder[T3]
  ): Decoder[T1 | T2 | T3] =
    decT1
      .asInstanceOf[Decoder[T1 | T2 | T3]]
      .or(decT2.asInstanceOf[Decoder[T1 | T2 | T3]])
      .or(decT3.asInstanceOf[Decoder[T1 | T2 | T3]])

  def union4[T1, T2, T3, T4](
      decT1: Decoder[T1],
      decT2: Decoder[T2],
      decT3: Decoder[T3],
      decT4: Decoder[T4]
  ): Decoder[T1 | T2 | T3 | T4] =
    decT1
      .asInstanceOf[Decoder[T1 | T2 | T3 | T4]]
      .or(decT2.asInstanceOf[Decoder[T1 | T2 | T3 | T4]])
      .or(decT3.asInstanceOf[Decoder[T1 | T2 | T3 | T4]])
      .or(decT4.asInstanceOf[Decoder[T1 | T2 | T3 | T4]])

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

  def union2[T1: Typeable, T2: Typeable](
      encT1: Encoder[T1],
      encT2: Encoder[T2]
  ): Encoder[T1 | T2] =
    Encoder.instance:
      case v: T1 => encT1(v)
      case v: T2 => encT2(v)

  def unionVec2[T1: Typeable, T2: Typeable](
      encT1: Encoder[T1],
      encT2: Encoder[T2]
  ): Encoder[Vector[T1] | Vector[T2]] =
    Encoder.instance: vec =>
      val erase = vec.asInstanceOf[Vector[Any]]
      if erase.length == 0 then Encoder.encodeVector[Unit]().apply(Vector.empty)
      else
        erase.head match
          case _: T1 =>
            Encoder
              .encodeVector[T1](using encT1)
              .apply(erase.asInstanceOf[Vector[T1]])
          case _: T2 =>
            Encoder
              .encodeVector[T2](using encT2)
              .apply(erase.asInstanceOf[Vector[T2]])
      end if

  def union3[T1: Typeable, T2: Typeable, T3: Typeable](
      encT1: Encoder[T1],
      encT2: Encoder[T2],
      encT3: Encoder[T3]
  ): Encoder[T1 | T2 | T3] =
    Encoder.instance:
      case v: T1 => encT1(v)
      case v: T2 => encT2(v)
      case v: T3 => encT3(v)

  def union4[T1: Typeable, T2: Typeable, T3: Typeable, T4: Typeable](
      encT1: Encoder[T1],
      encT2: Encoder[T2],
      encT3: Encoder[T3],
      encT4: Encoder[T4]
  ): Encoder[T1 | T2 | T3 | T4] =
    Encoder.instance:
      case v: T1 => encT1(v)
      case v: T2 => encT2(v)
      case v: T3 => encT3(v)
      case v: T4 => encT4(v)

end Enc
