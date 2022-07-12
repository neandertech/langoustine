package langoustine

class Render(manager: Manager, packageName: String = "langoustine.lsp"):
  private val INDENT = "  "
  import Render.*
  private def renderBase(bt: Type.BaseType) =
    bt.name match
      case BaseTypes.NULL        => "Null"
      case BaseTypes.DocumentUri => "RuntimeBase.DocumentUri"
      case BaseTypes.string      => "String"
      case BaseTypes.boolean     => "Boolean"
      case BaseTypes.integer     => "Int"
      case BaseTypes.uinteger    => "RuntimeBase.uinteger"
      case _                     => s"Any /*Base: ${bt.name}*/"

  case class Context(resolve: Type.ReferenceType => TypeName)

  object Context:
    def global =
      val rtFallback = (rt: Type.ReferenceType) =>
        manager
          .get(rt.name.value)
          .map(_ match {
            case _: Enumeration => s"enumerations.${rt.name.value}"
            case _: Structure   => s"structures.${rt.name.value}"
            case _: TypeAlias   => s"aliases.${rt.name.value}"
            case _              => s"${rt.name.value} /* qualifyName */"
          })
          .map(TypeName.apply(_))
          .getOrElse(rt.name)

      Context(rtFallback)

  private def renderType(
      tpe: Type
  )(using ctx: Context): String =
    import Type.*
    tpe match
      case bt: BaseType => renderBase(bt)
      case rt: ReferenceType =>
        ctx
          .resolve(rt)
          .value
      case rt: ArrayType => s"Vector[${renderType(rt.element)}]"
      case rt: OrType =>
        "(" + rt.items.map(renderType).mkString(" | ") + ")"
      case tt: TupleType =>
        tt.items.map(renderType(_)).mkString("(", ", ", ")")
      case other => s"Any /*$other*/"

  def property(p: Property)(using Context) =
    import p.*
    val typeName = renderType(tpe)
    s"$name: $typeName"

  def structures(builder: LineBuilder, subPackage: String = "structures")(using
      Config
  ): Unit =
    inline def line: Config ?=> Appender = to(builder)
    line(s"package $packageName")
    line(s"package $subPackage")
    line("")

    manager.structures.foreach { s =>
      structure(s, builder)
      line("")
    }

  def structure(s: Structure, builder: LineBuilder)(using Config): Unit =
    val props = Vector.newBuilder[String]
    val inlineStructures =
      Map.newBuilder[Type.ReferenceType, (String, Type.StructureLiteralType)]

    inline def line: Config ?=> Appender = to(builder)

    given ctx: Context = Context.global

    var inlineAnonymousStructures = 0

    s.properties.foreach { p =>
      p.tpe match
        case stl: Type.StructureLiteralType =>
          val newTypeName = p.name.value.capitalize
          val newType: Type.ReferenceType = Type.ReferenceType(
            "reference",
            TypeName(
              s.name.value + "." + newTypeName
            )
          )
          props += property(p.copy(`type` = newType))
          inlineStructures += newType -> (newTypeName -> stl)
        case other =>
          val newType = other.traverse {
            case stl: Type.StructureLiteralType =>
              val newTypeName = "S" + inlineAnonymousStructures
              val newType: Type.ReferenceType = Type.ReferenceType(
                "reference",
                TypeName(
                  s.name.value + "." + newTypeName
                )
              )

              inlineAnonymousStructures += 1
              inlineStructures += (newType) -> (newTypeName -> stl)
              TypeTraversal.Replace(newType)

            case other => TypeTraversal.Skip
          }
          props += property(p.copy(`type` = newType))
    }

    val renderedProperties = props.result()
    line(s"case class ${s.name}(")
    nest {
      val result = props.result()
      result.zipWithIndex.foreach { (propLine, idx) =>
        if idx != result.length - 1 then line(propLine + ",")
        else line(propLine)
      }
    }
    line(")")
    val stls = inlineStructures.result()
    line(s"object ${s.name}:")
    nest {
      line(s"given upickle.default.Reader[${s.name}] = Pickle.macroR")
      stls.foreach { case (rt, (newName, stl)) =>
        val struct = Structure(
          `extends` = Vector.empty,
          mixins = Vector.empty,
          properties = stl.value.properties,
          name = StructureName(newName)
        )

        this.structure(struct, builder)
      }
    }

object Render:
  opaque type LineBuilder = StringBuilder
  object LineBuilder:
    private val SEP          = System.lineSeparator()
    def apply(): LineBuilder = new StringBuilder
    extension (lb: LineBuilder)
      def result: String                     = lb.result
      def appendLine(s: String): LineBuilder = lb.append(s + SEP)
      def emptyLine: LineBuilder             = lb.append(SEP)
      def emptyLines(n: Int): LineBuilder    = lb.append(SEP * n)
      def append(s: String): LineBuilder     = lb.append(s)

  case class Config(indents: Indentation, indentSize: IndentationSize)

  opaque type IndentationSize = Int
  object IndentationSize extends OpaqueNum[IndentationSize]

  opaque type Indentation = Int
  object Indentation extends OpaqueNum[Indentation]

  import IndentationSize.*

  def indent(using c: Config): String =
    (" " * (c.indentSize.value * c.indents.value))

  def nest(f: Config ?=> Unit)(using config: Config) =
    f(using config.copy(indents = config.indents.map(_ + 1)))

  def to(sb: LineBuilder)(using config: Config): Appender =
    import LineBuilder.*
    line => sb.appendLine(indent(using config) + line)

  type Appender = Config ?=> String => Unit
