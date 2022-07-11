package langoustine

case class MetaModel(
    structures: Vector[Structure],
    enumerations: Vector[Enumeration],
    requests: Vector[Request],
    notifications: Vector[Notification],
    typeAliases: Vector[TypeAlias]
)

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
):
  def tpe = `type`

case class Structure(
    `extends`: Vector[Type] = Vector.empty,
    mixins: Vector[Type] = Vector.empty,
    name: StructureName,
    properties: Vector[Property] = Vector.empty
) {
  inline def extendz = `extends`
}

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

case class Enumeration(
    name: EnumerationName,
    `type`: EnumerationType,
    values: Vector[EnumerationEntry]
)

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

