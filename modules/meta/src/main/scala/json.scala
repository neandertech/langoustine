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

package langoustine.meta

// import com.github.plokhotnyuk.jsoniter_scala.macros.*
// import com.github.plokhotnyuk.jsoniter_scala.core.*
//
import io.circe.*
import io.circe.derivation.Configuration

object json:
  given [Raw, Opaque](using
      bts: BasicallyTheSame[Raw, Opaque],
      rw: Codec[Raw]
  ): Codec[Opaque] =
    rw.iemap[Opaque](r => Right(bts(r)))(bts.reverse)

  import Type.*

  given Decoder[BaseTypes] = Decoder.decodeString.map {
    case "DocumentUri" => BaseTypes.DocumentUri
    case "Uri" | "URI" => BaseTypes.Uri
    case "integer"     => BaseTypes.integer
    case "uinteger"    => BaseTypes.uinteger
    case "decimal"     => BaseTypes.decimal
    case "RegExp"      => BaseTypes.RegExp
    case "string"      => BaseTypes.string
    case "boolean"     => BaseTypes.boolean
    case "null"        => BaseTypes.NULL
  }

  // extension [X <: ujson.Value](js: X)
  //   def as[T](using cr: Reader[T]) = read[T](js)

  given Decoder[ParamsType] = Decoder.decodeJson.flatMap { js =>
    js.asObject match
      // js.objOpt match
      case None    => Decoder[Vector[Type]].map(ParamsType.Many(_))
      case Some(o) => Decoder[Type].map(ParamsType.Single(_))
  }

  given Configuration = Configuration.default.withDefaults

  given Decoder[Property]         = Decoder.derivedConfigured
  given Decoder[Structure]        = Decoder.derivedConfigured
  given Decoder[StructureLiteral] = Decoder.derivedConfigured
  given Decoder[Request]          = Decoder.derivedConfigured
  given Decoder[Notification]     = Decoder.derivedConfigured

  given Decoder[BaseType]             = Decoder.derivedConfigured
  given Decoder[ReferenceType]        = Decoder.derivedConfigured
  given Decoder[ArrayType]            = Decoder.derivedConfigured
  given Decoder[OrType]               = Decoder.derivedConfigured
  given Decoder[AndType]              = Decoder.derivedConfigured
  given Decoder[MapType]              = Decoder.derivedConfigured
  given Decoder[StructureLiteralType] = Decoder.derivedConfigured
  given Decoder[StringLiteralType]    = Decoder.derivedConfigured
  given Decoder[TupleType]            = Decoder.derivedConfigured
  given Decoder[EnumerationTypeName]  =
    Decoder.decodeString.map {
      case "string"   => EnumerationTypeName.string
      case "integer"  => EnumerationTypeName.integer
      case "uinteger" => EnumerationTypeName.uinteger
    }
  given Decoder[EnumerationEntry] = Decoder.derivedConfigured
  given Decoder[EnumerationType]  = Decoder.derivedConfigured
  given Decoder[Enumeration]      = Decoder.derivedConfigured
  given Decoder[TypeAlias]        = Decoder.derivedConfigured
  given Decoder[MetaModel]        = Decoder.derivedConfigured

  given Decoder[EnumerationItem] = Decoder.decodeString
    .map(EnumerationItem.apply)
    .or(Decoder.decodeInt.map(EnumerationItem.apply))
  given Decoder[Type] = Decoder.decodeJsonObject.flatMap { obj =>
    val kind = obj("kind").flatMap(_.asString).getOrElse(???)

    import cats.syntax.functor.*

    println(s"reading $obj")

    kind match
      case "reference"     => Decoder[ReferenceType].widen
      case "base"          => Decoder[BaseType].widen
      case "array"         => Decoder[ArrayType].widen
      case "or"            => Decoder[OrType].widen
      case "map"           => Decoder[MapType].widen
      case "literal"       => Decoder[StructureLiteralType].widen
      case "stringLiteral" => Decoder[StringLiteralType].widen
      case "tuple"         => Decoder[TupleType].widen
      case "and"           => Decoder[AndType].widen
    end match
  }
end json
