package langoustine.generate

import langoustine.meta.*

class Render(manager: Manager, packageName: String = "langoustine.lsp"):
  private val INDENT = "  "
  import Render.*
  import Types.*

  def codecsPrelude(out: LineBuilder)(using Config): Unit =
    inline def line: Config ?=> Appender = to(out)
    line(s"package $packageName")
    line(s"package codecs")
    line("")
    line("import upickle.default.*")
    line("import structures.*")
    line("import aliases.*")
    line("import requests.*")
    line("import json.{*, given}")
    line("import runtime.{*, given}")
  end codecsPrelude

  def requests(out: LineBuilder, codecsOut: LineBuilder)(using
      Config
  ): Unit =
    inline def line: Config ?=> Appender       = to(out)
    inline def codecsLine: Config ?=> Appender = to(codecsOut)
    line(s"package $packageName")
    line("")
    line("import langoustine.*")
    line("import upickle.default.*")
    line("import json.{*, given}")
    line("import runtime.{*, given}")
    line("// format: off")
    line("")

    line("object requests:")

    nest {

      val requestPrelude = """
    |sealed abstract class LSPRequest(val requestMethod: String):
    |  type In
    |  type Out
    |
    |  given inputReader: Reader[In]
    |  given inputWriter: Writer[In]
    |  given outputWriter: Writer[Out]
    |  given outputReader: Reader[Out]
    """.stripMargin.trim

      requestPrelude.linesIterator.foreach(line)

      line("")

      val notificationPrelude = """
    |sealed abstract class LSPNotification(val notificationMethod: String):
    |  type In
    |
    |  given inputReader: Reader[In]
    |  given inputWriter: Writer[In]
    """.stripMargin.trim

      notificationPrelude.linesIterator.foreach(line)

      line("")

      var currentScope = List.empty[String]

      given Context = Context.global(manager, List("requests"))

      extension (req: Notification | Request)
        def segs =
          req.methodName.value.split("/").toList

        def name = req.segs.last

        def methodName: RequestMethod =
          req match
            case n: Notification => n.method
            case r: Request      => r.method
      end extension

      def renderParams(p: ParamsType) =
        p match
          case ParamsType.None      => "Unit"
          case ParamsType.Single(t) => renderType(t)
          case ParamsType.Many(t) => t.map(renderType).mkString("(", ", ", ")")

      def renderReq(req: Request)(using Config) =
        req.documentation.toOption.foreach { doc =>
          commentWriter(out) { cw =>
            import cw.*

            commentLine(doc.value)
          }

        }

        val codecTraitName = s"requests_${req.method.value.replace('/', '_')}"

        line(
          s"object ${req.name} extends LSPRequest(\"${req.method.value}\") with codecs.$codecTraitName:"
        )
        nest {
          line(s"type In = ${renderParams(req.params)}")
          line(s"type Out = ${renderType(req.result)}")
          line("")

          codecsOut.topLevel {
            val path = req.segs
              .collect {
                case "$" => "$DOLLAR"
                case o   => o
              }
              .mkString(".")

            codecsLine("")
            codecsLine(
              s"private[lsp] trait $codecTraitName:"
            )
            nest {

              codecsLine(s"import $path.{In, Out}")

              val reader = req.params match
                case ParamsType.None =>
                  WriterDefinition.Expression("unitReader")
                case ParamsType.Single(t) => upickleReader1(t, "In")
                case ParamsType.Many(t) =>
                  WriterDefinition.Expression("Pickle.macroR")

              codecsLine(s"given inputReader: Reader[In] = ")
              nest {
                reader.write(codecsOut)
              }

              codecsLine("")

              codecsLine(s"given inputWriter: Writer[In] = ")
              nest {
                req.params match
                  case ParamsType.None => codecsLine("unitWriter")
                  case ParamsType.Single(t) =>
                    upickleWriter(t, Some("In")).write(codecsOut)
                  case ParamsType.Many(t) => codecsLine("Pickle.macroR")
              }

              codecsLine("")

              codecsLine(s"given outputWriter: Writer[Out] =")
              nest {
                upickleWriter(req.result, Some("Out")).write(codecsOut)
              }

              codecsLine("")

              codecsLine(
                s"given outputReader: Reader[Out] ="
              )
              nest {
                upickleReader1(req.result, "Out").write(codecsOut)
              }

            }
          }
        }
        line("")
      end renderReq

      def renderNotification(req: Notification)(using Config) =
        req.documentation.toOption.foreach { doc =>
          commentWriter(out) { cw =>
            import cw.*

            commentLine(doc.value)
          }
        }
        val codecTraitName =
          s"notifications_${req.method.value.replace('/', '_')}"
        line(
          s"object ${req.name} extends LSPNotification(\"${req.method.value}\") with codecs.$codecTraitName:"
        )
        nest {
          line(s"type In = ${renderParams(req.params)}")
          line("")
          codecsOut.topLevel {
            codecsLine("")

            val path = req.segs
              .collect {
                case "$" => "$DOLLAR"
                case o   => o
              }
              .mkString(".")

            codecsLine("")
            codecsLine(
              s"private[lsp] trait $codecTraitName:"
            )
            nest {
              codecsLine(s"import $path.In")

              val reader = req.params match
                case ParamsType.None =>
                  WriterDefinition.Expression("unitReader")
                case ParamsType.Single(t) => upickleReader1(t, "In")
                case ParamsType.Many(t) =>
                  WriterDefinition.Expression("Pickle.macroR")

              codecsLine(s"given inputReader: Reader[In] = ")
              nest {
                reader.write(codecsOut)
              }
              codecsLine(s"given inputWriter: Writer[In] = ")
              nest {
                req.params match
                  case ParamsType.None => codecsLine("unitWriter")
                  case ParamsType.Single(t) =>
                    upickleWriter(t, Some("In")).write(codecsOut)
                  case ParamsType.Many(t) => codecsLine("Pickle.macroR")
              }
            }
          }
        }
        line("")
      end renderNotification

      def rec(segments: List[String])(using Config): Unit =
        segments match
          case Nil => ()
          case h :: t =>
            val scopeName =
              h match
                case "$"   => "$DOLLAR"
                case other => other
            line(s"object $scopeName:")
            nest { rec(t) }

      extension [A](value: A) def unionise[T] = value.asInstanceOf[A | T]

      val sorted =
        (manager.requests.map(_.unionise[Notification]) ++
          manager.notifications.map(_.unionise[Request]))
          .sortBy(_.methodName.value)

      val names = sorted.map(_.segs).toSet

      inline def render(r: Notification | Request)(using Config) =
        r match
          case n: Notification => renderNotification(n)
          case r: Request      => renderReq(r)

      sorted.zipWithIndex.foreach { (req, i) =>
        val segs = req.segs
        scribe.info(s"$segs")

        val next = if i < sorted.size - 1 then Some(sorted(i + 1)) else None

        if segs.size == 1 then render(req)
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
            render(req)
          }
        end if
      }
    }

  end requests

  def structures(
      out: LineBuilder,
      codecsOut: LineBuilder
  )(using Config): Unit =
    inline def line: Config ?=> Appender = to(out)
    line(s"package $packageName")
    line("")
    line("import langoustine.*")
    line("import upickle.default.*")
    line("import json.{*, given}")
    line("import runtime.{*, given}")
    line("// format: off")
    line("")

    given Context = Context.global(manager, List("structures"))

    line("object structures:")

    nest {
      manager.structures.foreach { s =>
        structure(s, out, codecsOut)
        line("")
      }
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

  case class CommentWriter(commentLine: String => Unit)

  def commentWriter(b: LineBuilder)(f: CommentWriter => Unit)(using Config) =
    inline def line: Config ?=> Appender = to(b)
    line("/**")
    f(CommentWriter { s =>
      val lines = s.linesIterator
      lines.foreach { l =>
        line(" *  " + l.replace("*/", "").replace("/*", "")) // TAKE THAT
      }
    })
    line(" */")
  end commentWriter

  def structure(s: Structure, builder: LineBuilder, codecsOut: LineBuilder)(
      using Config
  )(using ctx: Context): Unit =
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

    val finalProperties = props.result()

    val hasDocs = s.documentation != Opt.empty ||
      allProperties.exists(_.documentation != Opt.empty)

    if hasDocs then
      commentWriter(builder) { cw =>
        import cw.*
        s.documentation.toOption.foreach { d =>
          commentLine(d.value)
          line("")
        }

        allProperties.foreach { prop =>
          commentLine(
            s"@param ${prop.name}"
          )
          prop.documentation.toOption.foreach { d =>
            d.value.linesIterator.foreach { l =>
              commentLine(s"  $l")
            }
            line("")
          }
        }
      }
    end if

    line(s"case class ${s.name}(")
    nest {
      finalProperties.zipWithIndex.foreach { (propLine, idx) =>
        if idx != finalProperties.length - 1 then line(propLine + ",")
        else line(propLine)
      }
    }
    line(")")
    val stls          = inlineStructures.result()
    val fqf           = (ctx.definitionScope :+ s.name).mkString(".")
    val fqfUnderscore = (ctx.definitionScope :+ s.name).mkString("_")

    if stls.nonEmpty then
      line(s"object ${s.name} extends codecs.$fqfUnderscore:")
    else line(s"object ${s.name} extends codecs.$fqfUnderscore")

    nest {
      val allUnions = propTypes.result().flatMap(collectOrTypes)

      inline def codecsLine: Config ?=> Appender = to(codecsOut)

      codecsOut.topLevel {
        codecsLine("")
        codecsLine(s"private[lsp] trait $fqfUnderscore:")
        nest {
          ctx.definitionScope match
            case Nil =>
            case scope =>
              codecsLine(s"import ${scope.mkString(".")}.*")
          allUnions.distinct.zipWithIndex.foreach {
            case (NullableType(_), _) =>
            case (ot, idx) =>
              val union = renderType(ot)
              codecsLine(
                s"private given rd$idx: Reader[$union] = "
              )
              nest {
                upickleReader1(ot, union).write(codecsOut)
              }
              codecsLine(s"private given wt$idx: Writer[$union] = ")
              nest {
                upickleWriter(ot, Some(union)).write(codecsOut)
              }
          }
          codecsLine(
            s"given reader: Reader[$fqf] = Pickle.macroR"
          )
          codecsLine(
            s"given writer: Writer[$fqf] = upickle.default.macroW"
          )
        }
        codecsLine("")
      }
      stls.foreach { case (rt, (newName, stl)) =>
        val struct = Structure(
          `extends` = Vector.empty,
          mixins = Vector.empty,
          properties = stl.value.properties,
          name = StructureName(newName)
        )

        given Context =
          ctx.copy(definitionScope = ctx.definitionScope :+ s.name.value)

        this.structure(struct, builder, codecsOut)
      }
    }
  end structure

  def upickleReader1(t: Type, widen: String, cast: Option[String] = None)(using
      Context,
      Config
  ): WriterDefinition =
    import WriterDefinition.*
    import Type.*
    val asInst = cast.map(s => s".asInstanceOf[Reader[$s]]").getOrElse("")
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

          line(constituents.mkString(s"badMerge[$widen](", ", ", s")$asInst"))
        }
      case BaseType(BaseTypes.NULL)    => Expression("nullReadWriter")
      case BaseType(BaseTypes.string)  => Expression(s"stringCodec$asInst")
      case BaseType(BaseTypes.integer) => Expression(s"intCodec$asInst")
      case rt @ ReferenceType(ref) if ref.value == "LSPAny" =>
        Expression("jsReader")
      case rt @ ReferenceType(ref) =>
        Expression(summon[Context].resolve(rt).value + s".reader$asInst")
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

          line(s"vectorReader[${renderType(at.element)}]$asInst")
        }
      case t => Expression(s"upickle.default.reader[${renderType(t)}]$asInst")
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

  def upickleWriter(
      t: Type,
      widen: Option[String] = None,
      cast: Option[String] = None
  )(using
      Context,
      Config
  ): WriterDefinition =
    import Type.*
    val asInst = cast.map(s => s".asInstanceOf[Writer[$s]]").getOrElse("")
    import WriterDefinition.*
    t match
      case BaseType(BaseTypes.NULL)    => Expression("nullReadWriter")
      case BaseType(BaseTypes.string)  => Expression(s"stringCodec$asInst")
      case BaseType(BaseTypes.integer) => Expression(s"intCodec$asInst")
      case rt @ ReferenceType(ref) if ref.value == "LSPAny" =>
        Expression("jsWriter")
      case rt: ReferenceType =>
        Expression(summon[Context].resolve(rt).value + s".writer$asInst")
      case _: BaseType | _: BooleanLiteralType | _: StringLiteralType =>
        Expression(s"upickle.default.writer[${renderType(t)}]$asInst")
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
                      line("case a if a == Opt.empty => ujson.Null")
                    case t =>
                      line(
                        s"case v: ${renderType(t)} => writeJs[${renderType(t)}](v)"
                      )
                  }
                }
              }

              line(s"}$asInst")

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

              line(s"vectorWriter[${renderType(ot.element)}]$asInst")
            }
          case _ => Expression("???")
        end match
    end match
  end upickleWriter

  def aliases(
      out: LineBuilder,
      codecsOut: LineBuilder
  )(using Config) =
    inline def line: Config ?=> Appender = to(out)

    line(s"package $packageName")
    line("")
    line("import langoustine.*")
    line("import json.{*, given}")
    line("import runtime.{*, given}")
    line("import upickle.default.*")
    line("import scala.reflect.*")
    line("// format: off")
    line("")

    given ctx: Context = Context.global(manager, List("aliases"))

    line(s"object aliases: ")
    nest {
      manager.aliases.foreach { alias =>
        val inlineStructures =
          Map
            .newBuilder[Type.ReferenceType, (String, Type.StructureLiteralType)]

        var inlineAnonymousStructures = 0
        val newType = alias.`type`.traverse {
          case stl: Type.StructureLiteralType =>
            val newTypeName = "S" + inlineAnonymousStructures
            val newType: Type.ReferenceType = Type.ReferenceType(
              TypeName(
                alias.name.value + "." + newTypeName
              )
            )

            inlineAnonymousStructures += 1
            inlineStructures += (newType) -> (newTypeName -> stl)
            TypeTraversal.Replace(newType)

          case other => TypeTraversal.Skip
        }

        if alias.name.value == "LSPArray" then
          line(s"case class LSPArray(elements: ${renderType(newType)})")
        else line(s"opaque type ${alias.name} = ${renderType(newType)}")

        val fqf           = (ctx.definitionScope :+ alias.name).mkString(".")
        val fqfUnderscore = (ctx.definitionScope :+ alias.name).mkString("_")

        val stls = inlineStructures.result()

        line(s"object ${alias.name} extends codecs.$fqfUnderscore:")

        inline def codecsLine: Config ?=> Appender = to(codecsOut)

        codecsOut.topLevel {
          codecsLine("")
          codecsLine(s"private[lsp] trait $fqfUnderscore:")
          nest {
            codecsLine("")

            codecsLine(s"given reader: Reader[${alias.name}] = ")
            nest {
              upickleReader1(
                newType,
                alias.name.value,
                cast = Some(alias.name.value)
              ).write(codecsOut)
            }

            codecsLine("")

            codecsLine(s"given writer: Writer[${alias.name}] =")
            nest {
              upickleWriter(
                newType,
                Some(alias.name.value),
                cast = Some(alias.name.value)
              ).write(codecsOut)
            }
          }
        }

        nest {
          import Type.*
          val constituents =
            newType match
              case OrType(items) => items
              case other         => Vector(other)

          constituents.foreach { tpe =>
            line(s"inline def apply(v: ${renderType(tpe)}): ${alias.name} = v")
          }

          line("")

          typeTestRender(alias.name.into(TypeName), newType).write(out)

          stls.foreach { case (rt, (newName, stl)) =>
            val struct = Structure(
              `extends` = Vector.empty,
              mixins = Vector.empty,
              properties = stl.value.properties,
              name = StructureName(newName)
            )

            given Context =
              ctx.copy(definitionScope =
                ctx.definitionScope :+ alias.name.value
              )

            this.structure(struct, out, codecsOut)
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
    line("import runtime.{*, given}")
    line("import json.{*, given}")
    line("import scala.reflect.Typeable")
    line("// format: off")
    line("")

    given ctx: Context = Context.global(manager, List("enumerations"))

    line(s"object $subPackage: ")
    nest {
      line(s"private val stringCodec = upickle.default.readwriter[String]")
      line(s"private val intCodec = upickle.default.readwriter[Int]")
      line("import upickle.{default => up}")
      line("")

      manager.enumerations.foreach { a =>
        val base = a.`type`.name
        import EnumerationTypeName as ET
        val underlying: Type.BaseType = Type.BaseType(
          base match
            case ET.string   => BaseTypes.string
            case ET.integer  => BaseTypes.integer
            case ET.uinteger => BaseTypes.uinteger
        )

        val impl = base match
          case ET.string   => "StringEnum"
          case ET.integer  => "IntEnum"
          case ET.uinteger => "UIntEnum"

        a.documentation.toOption.foreach { d =>
          commentWriter(out) { cw =>
            cw.commentLine(d.value)
          }
        }
        line(s"opaque type ${a.name} = ${renderType(underlying)}")
        if a.values.nonEmpty then
          line(s"object ${a.name} extends $impl[${a.name}]:")
          nest {
            a.values.foreach { entry =>
              val value =
                base match
                  case ET.string => ('"' + entry.value.stringValue + '"').trim
                  case _         => entry.value.intValue.toString

              entry.documentation.toOption.foreach { d =>
                commentWriter(out) { cw =>
                  cw.commentLine(d.value)
                }
              }
              line(s"val ${sanitise(entry.name.value)} = entry($value)")
            }

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
      def topLevel(f: Config ?=> Unit)(using c: Config) =
        f(using c.copy(indents = Indentation(0)))
      def appender: Config ?=> Appender = to(lb)
  end LineBuilder

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
      definitionScope: List[String]
  )

  object Context:
    def global(manager: Manager, scope: List[String]) =
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
      case ReferenceType(t) if t.value == "LSPAny" => "ujson.Value"
      case rt: ReferenceType =>
        ctx
          .resolve(rt)
          .value
      case rt: ArrayType    => s"Vector[${renderType(rt.element)}]"
      case NullableType(nt) => s"Opt[${renderType(nt)}]"
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

  def property(p: Property)(using Context) =
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
end Types

object Constants:
  val LICENCE =
    """
    |/*
    | * Copyright 2022 Neandertech
    | *
    | * Licensed under the Apache License, Version 2.0 (the "License");
    | * you may not use this file except in compliance with the License.
    | * You may obtain a copy of the License at
    | *
    | *     http://www.apache.org/licenses/LICENSE2.0
    | *
    | * Unless required by applicable law or agreed to in writing, software
    | * distributed under the License is distributed on an "AS IS" BASIS,
    | * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    | * See the License for the specific language governing permissions and
    | * limitations under the License.
    | */
    |""".stripMargin.trim
end Constants
