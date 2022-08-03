package langoustine.meta

case class MetaModel(
    structures: Vector[Structure],
    enumerations: Vector[Enumeration],
    requests: Vector[Request],
    notifications: Vector[Notification],
    typeAliases: Vector[TypeAlias]
)

opaque type StructureName = String
object StructureName extends OpaqueString[StructureName]

opaque type StructureDescription = String
object StructureDescription extends OpaqueString[StructureDescription]

opaque type PropertyName = String
object PropertyName extends OpaqueString[PropertyName]

opaque type PropertyDescription = String
object PropertyDescription extends OpaqueString[PropertyDescription]

opaque type RequestDescription = String
object RequestDescription extends OpaqueString[RequestDescription]

opaque type NotificationDescription = String
object NotificationDescription extends OpaqueString[NotificationDescription]

opaque type IsOptional = Boolean
object IsOptional extends YesNo[IsOptional]

opaque type RequestMethod = String
object RequestMethod extends OpaqueString[RequestMethod]

opaque type EnumerationName = String
object EnumerationName extends OpaqueString[EnumerationName]

opaque type EnumerationDocumentation = String
object EnumerationDocumentation extends OpaqueString[EnumerationDocumentation]

opaque type EnumerationEntryDocumentation = String
object EnumerationEntryDocumentation
    extends OpaqueString[EnumerationEntryDocumentation]

opaque type EnumerationItemName = String
object EnumerationItemName extends OpaqueString[EnumerationItemName]

enum ParamsType:
  case Single(t: Type)
  case Many(v: Vector[Type])
  case None

case class Request(
    params: ParamsType = ParamsType.None,
    method: RequestMethod,
    result: Type,
    documentation: Opt[RequestDescription] = Opt.empty
)

case class Notification(
    method: RequestMethod,
    params: ParamsType = ParamsType.None,
    documentation: Opt[NotificationDescription] = Opt.empty
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

case class EnumerationEntry(
    name: EnumerationItemName,
    value: EnumerationItem,
    documentation: Opt[EnumerationEntryDocumentation] = Opt.empty
)

case class Property(
    name: PropertyName,
    optional: IsOptional = IsOptional.No,
    `type`: Type,
    documentation: Opt[PropertyDescription] = Opt.empty
):
  def tpe = `type`

case class Structure(
    `extends`: Vector[Type] = Vector.empty,
    mixins: Vector[Type] = Vector.empty,
    name: StructureName,
    properties: Vector[Property] = Vector.empty,
    documentation: Opt[StructureDescription] = Opt.empty
):
  inline def extendz = `extends`

opaque type TypeAliasName = String
object TypeAliasName extends OpaqueString[TypeAliasName]

case class TypeAlias(name: TypeAliasName, `type`: Type)

case class StructureLiteral(
    properties: Vector[Property]
)

opaque type Opt[+A] = A | Null
object Opt:
  import upickle.default.*
  inline def empty: Opt[Nothing]    = null
  inline def apply[A](a: A): Opt[A] = a

  extension [A](o: Opt[A])
    inline def toOption: Option[A] =
      o match
        case null => None
        case _    => Some(o.asInstanceOf[A])

  given [A](using
      rd: Reader[A]
  ): Reader[Opt[A]] =
    rd.asInstanceOf[Reader[Opt[A]]]

  given [A](using
      wt: Writer[A]
  ): Writer[Opt[A]] =
    wt.asInstanceOf[Writer[Opt[A]]]
end Opt

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
    values: Vector[EnumerationEntry],
    documentation: Opt[EnumerationDocumentation] = Opt.empty
)

enum Type(
    kind: "base" | "reference" | "and" | "or" | "array" | "booleanLiteral" |
      "map" | "literal" | "stringLiteral" | "tuple"
):
  case BaseType(name: BaseTypes)          extends Type("base")
  case ReferenceType(name: TypeName)      extends Type("reference")
  case AndType(items: Vector[Type])       extends Type("and")
  case OrType(items: Vector[Type])        extends Type("or")
  case ArrayType(element: Type)           extends Type("array")
  case BooleanLiteralType(value: Boolean) extends Type("booleanLiteral")
  case MapType(key: Type, value: Type)    extends Type("map")
  case StructureLiteralType(value: StructureLiteral) extends Type("literal")
  case StringLiteralType(value: String) extends Type("stringLiteral")
  case TupleType(items: Vector[Type])   extends Type("tuple")

  def traverse(f: Type => TypeTraversal) =
    TypeTraversal(this)(f)
end Type

enum TypeTraversal:
  case Skip
  case Replace(other: Type)

object TypeTraversal:
  def apply(t: Type)(decider: Type => TypeTraversal): Type =
    val f = (t: Type) =>
      decider(t) match
        case TypeTraversal.Skip           => t
        case TypeTraversal.Replace(other) => other

    val app = (t: Type) => apply(t)(decider)
    import Type.*
    t match
      case tt: TupleType => f(TupleType(tt.items.map(app).map(f)))
      case at: AndType   => f(AndType(at.items.map(app).map(f)))
      case at: OrType    => f(OrType(at.items.map(app).map(f)))
      case at: ArrayType => f(ArrayType(f(app(at.element))))
      case at: MapType   => f(MapType(f(app(at.key)), f(app(at.value))))
      case other         => f(other)
  end apply
end TypeTraversal
