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

import java.io.File

object json:
  import upickle.default.*
  given [Raw, Opaque](using
      bts: BasicallyTheSame[Raw, Opaque],
      rw: ReadWriter[Raw]
  ): ReadWriter[Opaque] =
    rw.bimap[Opaque](bts.reverse, bts.apply)

  import Type.*

  given [T <: String](using rw: ReadWriter[String]): ReadWriter[T] =
    rw.bimap(identity, _.asInstanceOf[T])

  given Reader[BaseTypes] = summon[Reader[String]].map {
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

  extension [X <: ujson.Value](js: X)
    def as[T](using cr: Reader[T]) = read[T](js)

  given Reader[ParamsType] = reader[ujson.Value].map { js =>
    js.objOpt match
      case None    => ParamsType.Many(read[Vector[Type]](js))
      case Some(o) => ParamsType.Single(read[Type](js))
  }

  given Reader[Property]         = macroR
  given Reader[Structure]        = macroR
  given Reader[StructureLiteral] = macroR
  given Reader[Request]          = macroR
  given Reader[Notification]     = macroR

  given Reader[BaseType]             = Pickle.macroR
  given Reader[ReferenceType]        = Pickle.macroR
  given Reader[ArrayType]            = Pickle.macroR
  given Reader[OrType]               = Pickle.macroR
  given Reader[AndType]              = Pickle.macroR
  given Reader[MapType]              = Pickle.macroR
  given Reader[StructureLiteralType] = Pickle.macroR
  given Reader[StringLiteralType]    = Pickle.macroR
  given Reader[TupleType]            = Pickle.macroR
  given Reader[EnumerationTypeName] =
    reader[String].map {
      case "string"   => EnumerationTypeName.string
      case "integer"  => EnumerationTypeName.integer
      case "uinteger" => EnumerationTypeName.uinteger
    }
  given Reader[EnumerationEntry] = Pickle.macroR
  given Reader[EnumerationType]  = Pickle.macroR
  given Reader[Enumeration]      = Pickle.macroR
  given Reader[TypeAlias]        = Pickle.macroR
  given Reader[MetaModel]        = Pickle.macroR

  given Reader[EnumerationItem] = reader[ujson.Value].map { v =>
    (v.strOpt.map(EnumerationItem.apply) orElse
      v.numOpt.map(t => EnumerationItem.apply(t.toInt))).get
  }

  given Reader[Type] = reader[ujson.Obj].map { obj =>
    val kind = obj("kind").str

    kind match
      case "reference"     => read[ReferenceType](obj)
      case "base"          => read[BaseType](obj)
      case "array"         => read[ArrayType](obj)
      case "or"            => read[OrType](obj)
      case "map"           => read[MapType](obj)
      case "literal"       => read[StructureLiteralType](obj)
      case "stringLiteral" => read[StringLiteralType](obj)
      case "tuple"         => read[TupleType](obj)
      case "and"           => read[AndType](obj)
    end match
  }
end json
