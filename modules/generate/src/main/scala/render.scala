package langoustine.generate

import langoustine.meta.*

class Render(manager: Manager, packageName: String = "langoustine.lsp"):
  private val INDENT = "  "
  import Render.*
  import Types.*

  private def property(p: Property)(using Context) =
    import p.*
    val typeName = renderType(tpe)
    if p.optional == IsOptional.Yes then
      s"${sanitise(name.value)}: Opt[$typeName] = Opt.empty"
    else s"${sanitise(name.value)}: $typeName"

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
        "macro"
      )

    if prohibited(name) then s"`$name`" else name
  end sanitise

  def notifications(builder: LineBuilder, subPackage: String = "notifications")(
      using Config
  ): Unit =
    inline def line: Config ?=> Appender = to(builder)
    line(s"package $packageName")
    line(s"package $subPackage")
    line("")
    line("import langoustine.*")
    line("import upickle.default.*")
    line("import langoustine.lsp.json.{*, given}")
    line("// format: off")
    line("")

    val prelude = """
    |sealed abstract class LSPNotification(val notificationMethod: String):
    |  type In
    |
    |  given inputReader: Reader[In]
    |  given inputWriter: Writer[In]
    """.stripMargin.trim

    prelude.linesIterator.foreach(line)

    var currentScope = List.empty[String]

    given Context = Context.global(manager, "notifications")

    extension (req: Notification)
      inline def segs = req.method.value.split("/").toList
      inline def name = req.segs.last

    def renderParams(p: ParamsType) =
      p match
        case ParamsType.None      => "Unit"
        case ParamsType.Single(t) => renderType(t)
        case ParamsType.Many(t)   => t.map(renderType).mkString("(", ", ", ")")

    def renderReq(req: Notification)(using Config) =
      line(
        s"object ${req.name} extends LSPNotification(\"${req.method.value}\"):"
      )
      nest {
        line(s"type In = ${renderParams(req.params)}")

        val reader = req.params match
          case ParamsType.None      => WriterDefinition.Expression("unitReader")
          case ParamsType.Single(t) => upickleReader1(t, "In")
          case ParamsType.Many(t) =>
            WriterDefinition.Expression("Pickle.macroR")

        line(s"given inputReader: Reader[In] = ")
        nest {
          reader.write(builder)
        }
        line(s"given inputWriter: Writer[In] = ")
        nest {
          req.params match
            case ParamsType.None => line("unitWriter")
            case ParamsType.Single(t) =>
              upickleWriter(t, Some("In")).write(builder)
            case ParamsType.Many(t) => line("Pickle.macroR")
        }
      }
      line("")
    end renderReq

    def rec(segments: List[String])(using Config): Unit =
      segments match
        case Nil => ()
        case h :: t =>
          line(s"object $h:")
          nest { rec(t) }

    val sorted = manager.notifications.sortBy(_.method.value)
    val names  = sorted.map(_.method.value.split("/").toList).toSet

    sorted.zipWithIndex.foreach { (req, i) =>
      val segs = req.segs

      val next = if i < sorted.size - 1 then Some(sorted(i + 1)) else None

      if segs.size == 1 then renderReq(req)
      else
        val last :: reversedFront = segs.reverse
        val front                 = reversedFront.reverse

        if front != currentScope then
          val same =
            currentScope.zip(front).takeWhile((a, b) => a == b).map(_._1)

          val nein =
            val prev = segs.dropRight(1)
            if names.contains(prev) then 1
            else 0

          deep(same.size) {
            rec(front.drop(same.size).dropRight(nein))
          }
          currentScope = front
        end if

        deep(currentScope.size) {
          renderReq(req)
        }
      end if
    }

  end notifications

  def requests(builder: LineBuilder, subPackage: String = "requests")(using
      Config
  ): Unit =
    inline def line: Config ?=> Appender = to(builder)
    line(s"package $packageName")
    line(s"package $subPackage")
    line("")
    line("import langoustine.*")
    line("import upickle.default.*")
    line("import langoustine.lsp.json.{*, given}")
    line("// format: off")
    line("")

    val prelude = """
    |sealed abstract class LSPRequest(val requestMethod: String):
    |  type In
    |  type Out
    |
    |  given inputReader: Reader[In]
    |  given inputWriter: Writer[In]
    |  given outputWriter: Writer[Out]
    |  given outputReader: Reader[Out]
    """.stripMargin.trim

    prelude.linesIterator.foreach(line)

    var currentScope = List.empty[String]

    given Context = Context.global(manager, "requests")

    extension (req: Request)
      inline def segs = req.method.value.split("/").toList
      inline def name = req.segs.last

    def renderParams(p: ParamsType) =
      p match
        case ParamsType.None      => "Unit"
        case ParamsType.Single(t) => renderType(t)
        case ParamsType.Many(t)   => t.map(renderType).mkString("(", ", ", ")")

    def renderReq(req: Request)(using Config) =
      line(s"object ${req.name} extends LSPRequest(\"${req.method.value}\"):")
      nest {
        line(s"type In = ${renderParams(req.params)}")
        line(s"type Out = ${renderType(req.result)}")
        line("")

        val reader = req.params match
          case ParamsType.None      => WriterDefinition.Expression("unitReader")
          case ParamsType.Single(t) => upickleReader1(t, "In")
          case ParamsType.Many(t) =>
            WriterDefinition.Expression("Pickle.macroR")

        line(s"given inputReader: Reader[In] = ")
        nest {
          reader.write(builder)
        }

        line("")

        line(s"given inputWriter: Writer[In] = ")
        nest {
          req.params match
            case ParamsType.None => line("unitWriter")
            case ParamsType.Single(t) =>
              upickleWriter(t, Some("In")).write(builder)
            case ParamsType.Many(t) => line("Pickle.macroR")
        }

        line("")

        line(s"given outputWriter: Writer[Out] =")
        nest {
          upickleWriter(req.result, Some("Out")).write(builder)
        }

        line("")

        line(
          s"given outputReader: Reader[Out] ="
        )
        nest {
          upickleReader1(req.result, "Out").write(builder)
        }
      }
      line("")
    end renderReq

    def rec(segments: List[String])(using Config): Unit =
      segments match
        case Nil => ()
        case h :: t =>
          line(s"object $h:")
          nest { rec(t) }

    val sorted = manager.requests.sortBy(_.method.value)
    val names  = sorted.map(_.method.value.split("/").toList).toSet

    sorted.zipWithIndex.foreach { (req, i) =>
      val segs = req.segs

      val next = if i < sorted.size - 1 then Some(sorted(i + 1)) else None

      if segs.size == 1 then renderReq(req)
      else
        val last :: reversedFront = segs.reverse
        val front                 = reversedFront.reverse

        if front != currentScope then
          val same =
            currentScope.zip(front).takeWhile((a, b) => a == b).map(_._1)

          val nein =
            val prev = segs.dropRight(1)
            if names.contains(prev) then 1
            else 0

          deep(same.size) {
            rec(front.drop(same.size).dropRight(nein))
          }
          currentScope = front
        end if

        deep(currentScope.size) {
          renderReq(req)
        }
      end if
    }

  end requests

  def structures(builder: LineBuilder, subPackage: String = "structures")(using
      Config
  ): Unit =
    inline def line: Config ?=> Appender = to(builder)
    line(s"package $packageName")
    line(s"package $subPackage")
    line("")
    line("import langoustine.*")
    line("import upickle.default.*")
    line("import langoustine.lsp.json.{*, given}")
    line("// format: off")
    line("")

    given Context = Context.global(manager, "structures")

    manager.structures.foreach { s =>
      structure(s, builder)
      line("")
    }
  end structures

  private def deduplicateProperties(
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

  private def typeTestRender(typeName: TypeName, tpe: Type)(using
      Config,
      Context
  ) = WriterDefinition.Definition { out =>
    inline def line: Config ?=> Appender = to(out)

    import Type.*

    tpe match
      case OrType(components) =>
        line(s"given Typeable[$typeName] with")
        nest {
          line(s"def unapply(s: Any): Option[s.type & $typeName] = ")
          nest {
            line("s match")
            components.foreach { tpe =>
              val (patternCase, varName) =
                tpe match
                  case _: ArrayType             => ("c: Vector[?]", "c")
                  case BaseType(BaseTypes.NULL) => ("null", "null")
                  case _ => ("c: " + renderType(tpe), "c")
              line(
                s"case $patternCase => Some($varName.asInstanceOf[s.type & ${renderType(tpe)}])"
              )
            }
            line("case _ => Option.empty")
          }
        }
      case tpe =>
        line(s"given Typeable[$typeName] with")
        nest {
          line(s"def unapply(s: Any): Option[s.type & $typeName] = ")
          nest {
            line("s match")
            val (patternCase, varName) =
              tpe match
                case _: ArrayType             => ("c: Vector[?]", "c")
                case BaseType(BaseTypes.NULL) => ("null", "null")
                case _                        => ("c: " + renderType(tpe), "c")
            line(
              s"case $patternCase => Some($varName.asInstanceOf[s.type & ${renderType(tpe)}])"
            )
            line("case _ => Option.empty")
          }
        }
    end match
  }
  end typeTestRender

  def structure(s: Structure, builder: LineBuilder)(using Config)(using
      ctx: Context
  ): Unit =
    val props = Vector.newBuilder[String]
    val inlineStructures =
      Map.newBuilder[Type.ReferenceType, (String, Type.StructureLiteralType)]

    inline def line: Config ?=> Appender = to(builder)

    var inlineAnonymousStructures = 0

    def refProperties(t: Type): Vector[Property] =
      t match
        case rt: Type.ReferenceType =>
          manager
            .get(rt.name.value)
            .toVector
            .collect { case s: Structure =>
              s.properties
            }
            .flatten
        case _ => Vector.empty

    val allProperties =
      s.properties ++
        s.`extends`.flatMap(refProperties) ++
        s.mixins.flatMap(refProperties)

    val propTypes = Vector.newBuilder[Type]

    deduplicateProperties(allProperties).foreach { p =>
      p.tpe match
        case stl: Type.StructureLiteralType =>
          val newTypeName = p.name.value.capitalize
          val newType: Type.ReferenceType = Type.ReferenceType(
            TypeName(
              s.name.value + "." + newTypeName
            )
          )
          propTypes += newType
          props += property(p.copy(`type` = newType))
          inlineStructures += newType -> (newTypeName -> stl)
        case other =>
          val newType = other.traverse {
            case stl: Type.StructureLiteralType =>
              val newTypeName = "S" + inlineAnonymousStructures
              val newType: Type.ReferenceType = Type.ReferenceType(
                TypeName(
                  s.name.value + "." + newTypeName
                )
              )

              inlineAnonymousStructures += 1
              inlineStructures += (newType) -> (newTypeName -> stl)
              TypeTraversal.Replace(newType)

            case other => TypeTraversal.Skip
          }
          propTypes += newType
          props += property(p.copy(`type` = newType))
    }

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
      val allUnions = propTypes.result().flatMap(collectOrTypes)

      allUnions.distinct.zipWithIndex.foreach {
        case (NullableType(_), _) =>
        case (ot, idx) =>
          val union = renderType(ot)
          line(
            s"private given rd$idx: Reader[$union] = "
          )
          nest {
            upickleReader1(ot, union).write(builder)
          }
          line(s"private given wt$idx: Writer[$union] = ")
          nest {
            upickleWriter(ot, Some(union)).write(builder)
          }
      }
      line(
        s"given reader: Reader[${ctx.definitionScope}.${s.name}] = Pickle.macroR"
      )
      line(
        s"given writer: Writer[${ctx.definitionScope}.${s.name}] = upickle.default.macroW"
      )
      stls.foreach { case (rt, (newName, stl)) =>
        val struct = Structure(
          `extends` = Vector.empty,
          mixins = Vector.empty,
          properties = stl.value.properties,
          name = StructureName(newName)
        )

        given Context =
          ctx.copy(definitionScope = ctx.definitionScope + "." + s.name.value)

        this.structure(struct, builder)
      }
    }
  end structure

  def upickleReader1(t: Type, widen: String)(using
      Context,
      Config
  ): WriterDefinition =
    import WriterDefinition.*
    import Type.*
    t match
      case ot: OrType =>
        val constituents = ot.items.map(upickleReader(_))

        Definition { out =>
          inline def line: Config ?=> Appender = to(out)
          val allOrs                           = collectOrTypes(ot).distinct
          allOrs.filterNot(_ == ot).map { tpe =>
            line(s"given Reader[${renderType(tpe)}] = ")
            nest {
              upickleReader1(tpe, renderType(tpe)).write(out)
            }
          }

          line(constituents.mkString(s"badMerge[$widen](", ", ", ")"))
        }
      case BaseType(BaseTypes.NULL)    => Expression("nullReadWriter")
      case BaseType(BaseTypes.string)  => Expression("stringCodec")
      case BaseType(BaseTypes.integer) => Expression("intCodec")
      case rt @ ReferenceType(ref) if ref.value == "LSPAny" =>
        Expression("jsReader")
      case rt @ ReferenceType(ref) =>
        Expression(summon[Context].resolve(rt).value + ".reader")
      case _: AndType => Expression(s"??? /* TODO: $t  */")
      case at: ArrayType =>
        Definition { out =>
          inline def line: Config ?=> Appender = to(out)
          val allOrs                           = collectOrTypes(at).distinct
          allOrs.map { tpe =>
            line(s"given Reader[${renderType(tpe)}] = ")
            nest {
              upickleReader1(tpe, renderType(tpe)).write(out)
            }
          }

          line(s"vectorReader[${renderType(at.element)}]")
        }
      case t => Expression(s"upickle.default.reader[${renderType(t)}]")
    end match
  end upickleReader1

  def upickleReader(t: Type, widen: Option[String] = None)(using
      Context
  ): String =
    import Type.*
    t match
      case ot: OrType =>
        val w            = widen.map(a => s".widen[${a}]").getOrElse("")
        val constituents = ot.items.map(upickleReader(_)).map(_ + w)
        constituents.mkString(s"badMerge(", ", ", ")")
      case BaseType(BaseTypes.NULL)    => "nullReadWriter"
      case BaseType(BaseTypes.string)  => "stringCodec"
      case BaseType(BaseTypes.integer) => "intCodec"
      case rt @ ReferenceType(ref) if ref.value == "LSPAny" =>
        "jsReader"
      case rt @ ReferenceType(ref) =>
        summon[Context].resolve(rt).value + ".reader"
      case _: AndType => s"??? /* TODO: $t  */"
      case t          => s"upickle.default.reader[${renderType(t)}]"
    end match
  end upickleReader

  enum WriterDefinition:
    case Expression(str: String)
    case Definition(f: LineBuilder => Unit)

    def write(out: LineBuilder)(using Config) =
      this match
        case Expression(str) => to(out)(str)
        case Definition(f)   => f(out)

  def upickleWriter(t: Type, widen: Option[String] = None)(using
      Context,
      Config
  ): WriterDefinition =
    import Type.*
    import WriterDefinition.*
    t match
      case BaseType(BaseTypes.NULL)    => Expression("nullReadWriter")
      case BaseType(BaseTypes.string)  => Expression("stringCodec")
      case BaseType(BaseTypes.integer) => Expression("intCodec")
      case rt @ ReferenceType(ref) if ref.value == "LSPAny" =>
        Expression("jsWriter")
      case rt: ReferenceType =>
        Expression(summon[Context].resolve(rt).value + ".writer")
      case _: BaseType | _: BooleanLiteralType | _: StringLiteralType =>
        Expression(s"upickle.default.writer[${renderType(t)}]")
      case other =>
        val allOrs = collectOrTypes(other).distinct
        other match
          case ot: OrType =>
            Definition { out =>
              inline def line: Config ?=> Appender = to(out)
              allOrs.filterNot(_ == ot).map { tpe =>
                line(s"given Writer[${renderType(tpe)}] = ")
                nest {
                  upickleWriter(tpe).write(out)
                }
              }

              val w = widen.map(a => s"[$a]").getOrElse("")
              line(s"upickle.default.writer[ujson.Value].comap$w { _v => ")
              nest {
                line("(_v: @unchecked) match ")
                nest {
                  ot.items.map {
                    case at: ArrayType =>
                      val typeName = renderType(at)
                      line(
                        s"case v: Vector[?] => writeJs[$typeName](v.asInstanceOf[$typeName])"
                      )
                    case BaseType(BaseTypes.NULL) =>
                      line("case a if a == Nullable.NULL => ujson.Null")
                    case t =>
                      line(
                        s"case v: ${renderType(t)} => writeJs[${renderType(t)}](v)"
                      )
                  }
                }
              }

              line("}")

            }
          case ot: ArrayType =>
            Definition { out =>
              inline def line: Config ?=> Appender = to(out)

              allOrs.filterNot(_ == ot).map { tpe =>
                line(s"given Writer[${renderType(tpe)}] = ")
                nest {
                  upickleWriter(tpe).write(out)
                }
              }

              line(s"vectorWriter[${renderType(ot.element)}]")
            }
          case _ => Expression("???")
        end match
    end match
  end upickleWriter

  def aliases(out: LineBuilder, subPackage: String = "aliases")(using Config) =
    inline def line: Config ?=> Appender = to(out)

    line(s"package $packageName")
    line("")
    line("import langoustine.*")
    line("import langoustine.lsp.json.{*, given}")
    line("import upickle.default.*")
    line("import scala.reflect.*")
    line("// format: off")
    line("")

    given ctx: Context = Context.global(manager, "aliases")

    line(s"object $subPackage: ")
    nest {
      manager.aliases.foreach { a =>
        val inlineStructures =
          Map
            .newBuilder[Type.ReferenceType, (String, Type.StructureLiteralType)]

        var inlineAnonymousStructures = 0
        val newType = a.`type`.traverse {
          case stl: Type.StructureLiteralType =>
            val newTypeName = "S" + inlineAnonymousStructures
            val newType: Type.ReferenceType = Type.ReferenceType(
              TypeName(
                a.name.value + "." + newTypeName
              )
            )

            inlineAnonymousStructures += 1
            inlineStructures += (newType) -> (newTypeName -> stl)
            TypeTraversal.Replace(newType)

          case other => TypeTraversal.Skip
        }

        if a.name.value == "LSPArray" then
          line(s"case class LSPArray(elements: ${renderType(newType)})")
        else line(s"opaque type ${a.name} = ${renderType(newType)}")

        val stls = inlineStructures.result()
        line(s"object ${a.name}:")
        nest {
          import Type.*
          val constituents =
            newType match
              case OrType(items) => items
              case other         => Vector(other)

          constituents.foreach { tpe =>
            line(s"inline def apply(v: ${renderType(tpe)}): ${a.name} = v")
          }

          line("")

          line(s"given reader: Reader[${a.name}] = ")
          nest {
            upickleReader1(newType, a.name.value).write(out)
          }

          line("")

          line(s"given writer: Writer[${a.name}] =")
          nest {
            upickleWriter(
              newType,
              Some(a.name.value)
            ).write(out)
          }

          line("")

          typeTestRender(a.name.into(TypeName), newType).write(out)

          stls.foreach { case (rt, (newName, stl)) =>
            val struct = Structure(
              `extends` = Vector.empty,
              mixins = Vector.empty,
              properties = stl.value.properties,
              name = StructureName(newName)
            )

            given Context = ctx.copy(definitionScope =
              ctx.definitionScope + "." + a.name.value
            )

            this.structure(struct, out)
          }
        }
        line("")
      }
    }
  end aliases

  def enumerations(out: LineBuilder, subPackage: String = "enumerations")(using
      Config
  ) =
    inline def line: Config ?=> Appender = to(out)

    line(s"package $packageName")
    line("")
    line("import langoustine.*")
    line("import scala.reflect.Typeable")
    line("// format: off")
    line("")

    given ctx: Context = Context.global(manager, "enumerations")

    line(s"object $subPackage: ")
    nest {
      line(s"private val stringCodec = upickle.default.readwriter[String]")
      line(s"private val intCodec = upickle.default.readwriter[Int]")
      line("import upickle.{default => up}")

      manager.enumerations.foreach { a =>
        val base = a.`type`.name
        import EnumerationTypeName as ET
        val underlying: Type.BaseType = Type.BaseType(
          base match
            case ET.string   => BaseTypes.string
            case ET.integer  => BaseTypes.integer
            case ET.uinteger => BaseTypes.uinteger
        )
        line(s"opaque type ${a.name} = ${renderType(underlying)}")
        if a.values.nonEmpty then
          line(s"object ${a.name}:")
          nest {
            val codecVal =
              if base == ET.string then "stringCodec" else "intCodec"
            line(
              s"given reader: up.Reader[${a.name}] = $codecVal.asInstanceOf[up.Reader[${a.name}]]"
            )
            line(
              s"given writer: up.Writer[${a.name}] = $codecVal.asInstanceOf[up.Writer[${a.name}]]"
            )
            typeTestRender(a.name.into(TypeName), underlying).write(out)
            a.values.foreach { entry =>
              val value =
                base match
                  case ET.string => ('"' + entry.value.stringValue + '"').trim
                  case _         => entry.value.intValue.toString
              line(s"inline def ${sanitise(entry.name.value)} = entry($value)")
            }

            line("")

            if base == ET.uinteger then
              line(
                s"private inline def entry(n: Int): ${a.name} = RuntimeBase.uinteger(n)"
              )
            else
              line(
                s"private inline def entry(v: ${renderType(underlying)}): ${a.name} = v"
              )
          }
        end if
        line("")
      }
    }
  end enumerations
end Render

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

  def deep(count: Int)(f: Config ?=> Unit)(using config: Config) =
    f(using config.copy(indents = config.indents.map(_ + count)))

  def to(sb: LineBuilder)(using config: Config): Appender =
    import LineBuilder.*
    line => sb.appendLine(indent(using config) + line)

  type Appender = Config ?=> String => Unit
end Render

object Types:
  case class Context(
      resolve: Type.ReferenceType => TypeName,
      definitionScope: String
  )

  object Context:
    def global(manager: Manager, scope: String) =
      val rtFallback = (rt: Type.ReferenceType) =>
        manager
          .get(rt.name.value)
          .map(_ match
            case _: Enumeration => s"enumerations.${rt.name.value}"
            case _: Structure   => s"structures.${rt.name.value}"
            case _: TypeAlias   => s"aliases.${rt.name.value}"
            case _              => s"${rt.name.value} /* qualifyName */"
          )
          .map(TypeName.apply(_))
          .getOrElse(rt.name)

      Context(rtFallback, scope)
    end global
  end Context

  def renderBase(bt: Type.BaseType) =
    bt.name match
      case BaseTypes.NULL        => "Null"
      case BaseTypes.DocumentUri => "RuntimeBase.DocumentUri"
      case BaseTypes.Uri         => "RuntimeBase.DocumentUri"
      case BaseTypes.RegExp      => "RuntimeBase.RegExp"
      case BaseTypes.string      => "String"
      case BaseTypes.boolean     => "Boolean"
      case BaseTypes.integer     => "Int"
      case BaseTypes.decimal     => "Float"
      case BaseTypes.uinteger    => "RuntimeBase.uinteger"

  object NullableType:
    def unapply(tpe: Type): Option[Type] =
      import Type.*
      tpe match
        case ot: Type.OrType =>
          val isNull = BaseType(BaseTypes.NULL)
          if ot.items.size == 2 && ot.items.contains(isNull) then
            ot.items.filterNot(_ == isNull).headOption
          else None
        case _ => None
  end NullableType

  def renderType(
      tpe: Type
  )(using ctx: Context): String =
    import Type.*
    tpe match
      case bt: BaseType                            => renderBase(bt)
      case ReferenceType(t) if t.value == "LSPAny" => "ujson.Value"
      case rt: ReferenceType =>
        ctx
          .resolve(rt)
          .value
      case rt: ArrayType    => s"Vector[${renderType(rt.element)}]"
      case NullableType(nt) => s"Nullable[${renderType(nt)}]"
      case rt: OrType =>
        "(" + rt.items.map(renderType).mkString(" | ") + ")"
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
end Types
