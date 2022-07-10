/*
 * Copyright 2020 Anton Sviridov
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

package langoustine

import java.io.File

opaque type StructureName = String
object StructureName extends OpaqueString[StructureName]

opaque type PropertyName = String
object PropertyName extends OpaqueString[PropertyName]

opaque type IsOptional = Boolean
object IsOptional extends YesNo[IsOptional]

opaque type RequestMethod = String
object RequestMethod extends OpaqueString[RequestMethod]

opaque type EnumerationName = String
object EnumerationName extends OpaqueString[EnumerationName]

opaque type EnumerationItemName = String
object EnumerationItemName extends OpaqueString[EnumerationItemName]

enum ParamsType:
  case Single(t: Type)
  case Many(v: Vector[Type])
  case None

case class Request(
    params: ParamsType = ParamsType.None,
    method: RequestMethod,
    result: Type
)

case class Notification(
    method: RequestMethod
)

case class EnumerationType(
    kind: "base",
    name: EnumerationTypeName
)

enum EnumerationTypeName:
  case string, integer, uinteger

case class Enumeration(
    name: EnumerationName,
    `type`: EnumerationType,
    values: Vector[EnumerationEntry]
)

opaque type EnumerationItem = Int | String
object EnumerationItem:
  def apply(i: Int): EnumerationItem    = i
  def apply(s: String): EnumerationItem = s

  extension (t: EnumerationItem)
    def intValue    = t.asInstanceOf[Int]
    def stringValue = t.asInstanceOf[String]

case class EnumerationEntry(name: EnumerationItemName, value: EnumerationItem)

case class Property(
    name: PropertyName,
    optional: IsOptional = IsOptional.No,
    `type`: Type
)

case class Structure(
    `extends`: Vector[Type] = Vector.empty,
    mixins: Vector[Type] = Vector.empty,
    name: StructureName,
    properties: Vector[Property] = Vector.empty
):
  inline def extendz = `extends`

opaque type TypeAliasName = String
object TypeAliasName extends OpaqueString[TypeAliasName]

case class TypeAlias(name: TypeAliasName, `type`: Type)

case class StructureLiteral(
    properties: Vector[Property]
)

enum BaseTypes:
  case Uri, DocumentUri,
    integer,
    uinteger, decimal, RegExp, string, boolean,
    NULL

opaque type TypeName = String
object TypeName extends OpaqueString[TypeName]

enum Type:
  case BaseType(kind: "base", name: BaseTypes)
  case ReferenceType(kind: "reference", name: TypeName)
  case AndType(kind: "and", items: Vector[Type])
  case OrType(kind: "or", items: Vector[Type])
  case ArrayType(kind: "array", element: Type)
  case BooleanLiteralType(kind: "booleanLiteral", value: Boolean)
  case MapType(kind: "map", key: Type, value: Type)
  case StructureLiteralType(kind: "literal", value: StructureLiteral)
  case StringLiteralType(kind: "stringLiteral", value: String)
  case TupleType(kind: "tuple", items: Vector[Type])

case class MetaModel(
    structures: Vector[Structure],
    enumerations: Vector[Enumeration],
    requests: Vector[Request],
    notifications: Vector[Notification],
    typeAliases: Vector[TypeAlias]
)

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
    case "Uri"         => BaseTypes.Uri
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
  }

  import ujson.*

@main def hello =
  import upickle.default._
  import json.{*, given}
  val mm = ujson.read(new File("metaModel.json"))
  val m = read[MetaModel](mm, trace = true)

  println(m.requests.take(5).foreach(println))
