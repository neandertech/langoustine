package langoustine.generate

import langoustine.meta.*, Type.*

object Types:
  case class Context(
      resolve: Type.ReferenceType => TypeName,
      definitionScope: List[String]
  ):
    def inModified[A](f: Context => Context)(g: Context ?=> A) =
      g(using f(this))

  object Context:
    def global(manager: Manager, scope: List[String]) =
      val rtFallback = (rt: Type.ReferenceType) =>
        manager
          .get(rt.name.value)
          .map(_ match
            case _: Enumeration => s"enumerations.${rt.name.value}"
            case _: Structure   => s"structures.${rt.name.value}"
            case _: TypeAlias   => s"aliases.${rt.name.value}"
            case _              => s"${rt.name.value} /* qualifyName */")
          .map(TypeName.apply(_))
          .getOrElse(rt.name)

      Context(rtFallback, scope)
    end global
  end Context

  def renderBase(bt: Type.BaseType) =
    bt.name match
      case BaseTypes.NULL        => "Null"
      case BaseTypes.DocumentUri => "runtime.DocumentUri"
      case BaseTypes.Uri         => "runtime.Uri"
      case BaseTypes.RegExp      => "RegExp"
      case BaseTypes.string      => "String"
      case BaseTypes.boolean     => "Boolean"
      case BaseTypes.integer     => "Int"
      case BaseTypes.decimal     => "Float"
      case BaseTypes.uinteger    => "runtime.uinteger"

  object NullableType:
    def unapply(tpe: Type): Option[Type] =
      import Type.*
      tpe match
        case ot: Type.OrType =>
          val isNull = BaseType(BaseTypes.NULL)
          if ot.items.contains(isNull) then
            val filtered = ot.items.filterNot(_ == isNull)
            if filtered.size == 1 then filtered.headOption
            else Some(OrType(filtered))
          else None
        case _ => None
    end unapply
  end NullableType

  def renderType(
      tpe: Type
  )(using ctx: Context): String =
    import Type.*
    tpe match
      case bt: BaseType                            => renderBase(bt)
      case ReferenceType(t) if t.value == "LSPAny" => "io.circe.Json"
      case rt: ReferenceType                       =>
        ctx
          .resolve(rt)
          .value
      case rt: ArrayType    => s"Vector[${renderType(rt.element)}]"
      case NullableType(nt) => s"Option[${renderType(nt)}]"
      case rt: OrType if rt.items.length > 1 =>
        "(" + rt.items.map(renderType).mkString(" | ") + ")"
      case rt: OrType if rt.items.length == 1 =>
        renderType(rt.items.head)
      case tt: TupleType =>
        tt.items.map(renderType(_)).mkString("(", ", ", ")")
      case mt: MapType =>
        s"Map[${renderType(mt.key)}, ${renderType(mt.value)}]"

      case stl: StringLiteralType =>
        s""" "${stl.value}" """.trim
      case other => s"Any /*$other*/"
    end match
  end renderType

  def collectOrTypes(t: Type) =
    val allUnions = Vector.newBuilder[Type.OrType]
    t.traverse {
      case ot: Type.OrType =>
        allUnions += ot
        TypeTraversal.Skip
      case _ =>
        TypeTraversal.Skip
    }

    allUnions.result()
  end collectOrTypes

  def property(p: Property)(using Context) =
    import p.*
    val typeName = renderType(tpe)
    if p.optional == IsOptional.Yes && NullableType.unapply(tpe).isEmpty then
      s"${sanitise(name.value)}: Option[$typeName] = None"
    else
      tpe match
        case NullableType(nt) =>
          s"${sanitise(name.value)}: Option[${renderType(nt)}] = None"
        case _ => s"${sanitise(name.value)}: $typeName"
  end property

  def sanitise(name: String) =
    val prohibited =
      Set(
        "type",
        "class",
        "enum",
        "abstract",
        "def",
        "import",
        "export",
        "macro",
        "end"
      )

    if prohibited(name) then s"`$name`" else name
  end sanitise

  def simplifyOrType(t: Type) =
    t match
      case OrType(items) =>
        val (nullType, rest) =
          items.partition(_ == BaseType(BaseTypes.NULL))
        (
          nullType.nonEmpty,
          if rest.length == 1 then rest.head else OrType(rest)
        )
      case _ => (false, t)

  def simplifyProp(prop: Property) =
    val (isOption, simplified) = simplifyOrType(prop.`type`)
    prop.copy(
      `type` = simplified,
      optional = IsOptional(prop.optional.yes || isOption)
    )


  def deduplicateProperties(
      properties: Vector[Property]
  ): Vector[Property] =
    val order = Vector.newBuilder[PropertyName]
    val seen  = collection.mutable.Map.empty[PropertyName, Property]

    properties.foreach { case p =>
      if !seen.contains(p.name) then
        order += p.name
        seen += p.name -> p
      else
        val curType      = p.tpe
        val existingType = seen(p.name).tpe

        import Type.*

        if curType != existingType then
          val newType =
            (curType, existingType) match
              case (BaseType(BaseTypes.string), lit: StringLiteralType) =>
                lit
              case (lit: StringLiteralType, BaseType(BaseTypes.string)) =>
                lit
              case _ =>
                throw new Exception(
                  s"cannot reconcile types $curType and $existingType for same property ${p.name}"
                )

          seen.update(p.name, p.copy(`type` = newType))
        end if

    }

    order.result().flatMap(seen.get)
  end deduplicateProperties


end Types
