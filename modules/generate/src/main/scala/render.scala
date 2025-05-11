package langoustine.generate

import langoustine.meta.*
import langoustine.generate.StructureRenderConfig.PrivateCodecs

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
    line("import aliases.*")
    line("import json.{*, given}")
    line("import runtime.{*, given}")
  end codecsPrelude

  def exports(out: LineBuilder)(using Config): Unit =
    inline def line: Config ?=> Appender = to(out)
    line(s"package $packageName")
    line(s"package all")
    line("")

    // export runtime.DocumentUri
// export runtime.Uri
// export runtime.uinteger
// export runtime.Opt

    line("// Structures")
    manager.structures
      .filterNot(_.proposed)
      .foreach: struct =>
        line(s"export structures.${struct.name}")

    line("// Aliases")
    manager.aliases
      .filterNot(_.proposed)
      .foreach: struct =>
        line(s"export aliases.${struct.name}")

    line("// Enumerations")
    manager.enumerations
      .filterNot(_.proposed)
      .foreach: struct =>
        line(s"export enumerations.${struct.name}")

    val runtime = List("DocumentUri", "Uri", "uinteger", "Opt")

    line("// runtime")
    runtime.foreach: struct =>
      line(s"export runtime.$struct")

    val requests = List(
      "LSPRequest",
      "LSPNotification",
      "CustomRequest",
      "CustomNotification"
    )

    line("// request base classes")
    requests.foreach: struct =>
      line(s"export requests.$struct")

    val bases =
      (manager.requests.map(_.method).flatMap(_.value.split("/").headOption) ++
        manager.notifications
          .map(_.method)
          .flatMap(_.value.split("/").headOption)).distinct.collect:
        case "$"   => "$DOLLAR"
        case other => other

    line("// request base scopes")
    bases.foreach: struct =>
      line(s"export requests.$struct")

  end exports

  def requests(out: LineBuilder, codecsOut: LineBuilder)(using
      Config
  ): Unit =
    inline def line: Config ?=> Appender       = to(out)
    inline def codecsLine: Config ?=> Appender = to(codecsOut)
    line(s"package $packageName")
    line(s"package requests")
    line("")
    line("import langoustine.*")
    line("import upickle.default.*")
    line("import json.{*, given}")
    line("import runtime.{*, given}")
    line("")

    val requestPrelude = """
        |sealed abstract class LSPRequest(val requestMethod: String):
        |  type In
        |  type Out
        |
        |  given inputReader: Reader[In]
        |  given inputWriter: Writer[In]
        |  given outputWriter: Writer[Out]
        |  given outputReader: Reader[Out]
        |
        |  def apply(in: In): PreparedRequest[this.type] = PreparedRequest(this,in)
        """.stripMargin.trim

    requestPrelude.linesIterator.foreach(line)

    line("")

    val customRequestPrelude =
      """
        |abstract class CustomRequest[I, O](method: String)(using ir: ReadWriter[I], or: ReadWriter[O]) extends LSPRequest(method):
        |   override type In = I
        |   override type Out = O
        |
        |   override given inputReader: Reader[In] = ir
        |
        |   override given inputWriter: Writer[In] = ir
        |
        |   override given outputWriter: Writer[Out] = or
        |
        |   override given outputReader: Reader[Out] = or
        |
        |abstract class CustomNotification[I](method: String)(using ir: ReadWriter[I]) extends LSPNotification(method):
        |   override type In = I
        |
        |   override given inputReader: Reader[In] = ir
        |
        |   override given inputWriter: Writer[In] = ir
        """.stripMargin.trim

    customRequestPrelude.linesIterator.foreach(line)

    line("")

    val notificationPrelude = """
    |sealed abstract class LSPNotification(val notificationMethod: String):
    |  type In
    |
    |  given inputReader: Reader[In]
    |  given inputWriter: Writer[In]
    |
    |  def apply(in: In): PreparedNotification[this.type] = PreparedNotification(this,in)
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
        case ParamsType.Many(t)   => t.map(renderType).mkString("(", ", ", ")")

    def renderReq(req: Request)(using Config) =
      req.documentation.toOption.foreach { doc =>
        commentWriter(out) { cw =>
          import cw.*

          commentLine(doc.value.replace("@since", "since"))
        }

      }

      val codecTraitName = s"requests_${req.method.value.replace('/', '_')}"

      import Type.*

      def rewriteAndType(at: AndType, structName: StructureName) =
        val resolved = at.items.collect { case r: ReferenceType =>
          manager.get(r.name.value).collect { case s: Structure =>
            s
          }

        }.flatten

        if resolved.length == at.items.length then
          Some(Structure(name = structName, mixins = at.items))
        else
          scribe.error(
            s"Found an AndType I cannot render, in request $req, resolved consistutents: $resolved"
          )
          None
      end rewriteAndType

      def rewriteAndParmas(p: ParamsType, structName: StructureName) =
        p match
          case ParamsType.Single(at: AndType) =>
            rewriteAndType(at, structName)
          case _ => None

      line(
        s"object ${req.name} extends LSPRequest(\"${req.method.value}\") with codecs.$codecTraitName:"
      )
      nest {
        val inStructName =
          StructureName(req.segs.map(_.capitalize).mkString + "Input")

        val outStructName =
          StructureName(req.segs.map(_.capitalize).mkString + "Output")

        val inStruct = rewriteAndParmas(req.params, inStructName)
        val outStruct = Option(req.result).collect { case at: AndType =>
          rewriteAndType(at, outStructName)
        }.flatten

        val inTypeStr = inStruct match
          case None        => renderParams(req.params)
          case Some(value) => inStructName.value

        line(s"type In = $inTypeStr")

        outStruct match
          case Some(structure) =>
            line(s"type Out = $outStructName")
          case _ =>
            line(s"type Out = ${renderType(req.result)}")

        line("")

        line(
          s"override def apply(in: $inTypeStr): PreparedRequest[this.type] = super.apply(in)"
        )

        summon[Context].inModified(
          _.copy(definitionScope = "requests" :: req.segs)
        ) {
          inStruct.foreach {
            given StructureRenderConfig = StructureRenderConfig.default
              .copy(privateCodecs = PrivateCodecs.Yes)
            structure(_, out, codecsOut)
          }

          outStruct.foreach {
            given StructureRenderConfig = StructureRenderConfig.default
              .copy(privateCodecs = PrivateCodecs.Yes)
            structure(_, out, codecsOut)
          }
        }

        codecsOut.topLevel {
          val path = (List("requests") ++ req.segs)
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

            val reader =
              inStruct match
                case None =>
                  req.params match
                    case ParamsType.None =>
                      WriterDefinition.Expression("unitReader")
                    case ParamsType.Single(t) => upickleReader1(t, "In")
                    case ParamsType.Many(t) =>
                      WriterDefinition.Expression("Pickle.macroR")
                case Some(s) =>
                  WriterDefinition.Expression(s"$path.${s.name.value}.reader")

            codecsLine(s"given inputReader: Reader[In] = ")
            nest {
              reader.write(codecsOut)
            }

            codecsLine("")

            codecsLine(s"given inputWriter: Writer[In] = ")
            nest {
              inStruct match
                case None =>
                  req.params match
                    case ParamsType.None => codecsLine("unitWriter")
                    case ParamsType.Single(t) =>
                      upickleWriter(t, Some("In")).write(codecsOut)
                    case ParamsType.Many(t) => codecsLine("Pickle.macroR")
                case Some(s) =>
                  codecsLine(s"$path.${s.name.value}.writer")
            }

            codecsLine("")

            codecsLine(s"given outputWriter: Writer[Out] =")
            nest {
              outStruct match
                case None =>
                  upickleWriter(req.result, Some("Out")).write(codecsOut)
                case Some(s) =>
                  codecsLine(s"$path.${s.name.value}.writer")
            }

            codecsLine("")

            codecsLine(
              s"given outputReader: Reader[Out] ="
            )
            nest {
              outStruct match
                case None =>
                  upickleReader1(req.result, "Out").write(codecsOut)
                case Some(s) =>
                  codecsLine(s"$path.${s.name.value}.reader")
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
        val inTypeStr = renderParams(req.params)
        line(s"type In = ${inTypeStr}")

        line("")

        line(
          s"override def apply(in: $inTypeStr): PreparedNotification[this.type] = super.apply(in)"
        )

        codecsOut.topLevel {
          codecsLine("")

          val path = (List("requests") ++ req.segs)
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

            val reader =
              req.params match
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
      (manager.requests.filterNot(_.proposed).map(_.unionise[Notification]) ++
        manager.notifications.filterNot(_.proposed).map(_.unionise[Request]))
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
        val last :: reversedFront = segs.reverse: @unchecked
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

  end requests

  def structures(
      out: LineBuilder,
      codecsOut: LineBuilder
  )(using Config): Unit =
    inline def line: Config ?=> Appender = to(out)
    line(s"package $packageName")
    line(s"package structures")
    line("")
    line("import langoustine.*")
    line("import upickle.default.*")
    line("import json.{*, given}")
    line("import runtime.{*, given}")
    line("")

    given Context = Context.global(manager, List("structures"))

    manager.structures.filterNot(_.proposed).sortBy(_.name.value).foreach { s =>
      structure(s, out, codecsOut)
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

  def structure(
      structure: Structure,
      builder: LineBuilder,
      codecsOut: LineBuilder
  )(using
      renderConfig: Config,
      structConfig: StructureRenderConfig =
        StructureRenderConfig(privateCodecs = PrivateCodecs.No),
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
      (structure.properties ++
        structure.`extends`.flatMap(refProperties) ++
        structure.mixins.flatMap(refProperties)).filterNot(_.proposed)

    val propTypes = Vector.newBuilder[Type]

    deduplicateProperties(allProperties).foreach { p =>
      p.tpe match
        case stl: Type.StructureLiteralType =>
          val newTypeName = p.name.value.capitalize
          val newType: Type.ReferenceType = Type.ReferenceType(
            TypeName(
              structure.name.value + "." + newTypeName
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
                  structure.name.value + "." + newTypeName
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

    val hasDocs = structure.documentation != Opt.empty ||
      allProperties.exists(_.documentation != Opt.empty)

    if hasDocs then
      commentWriter(builder) { cw =>
        import cw.*
        structure.documentation.toOption.foreach { d =>
          commentLine(d.value)
          line("")
        }

        allProperties.foreach { prop =>
          commentLine(
            s"@param ${prop.name}"
          )
          prop.documentation.toOption.foreach { d =>
            d.value.linesIterator.foreach { l =>
              val escaped = l.replace("@since", "since")
              commentLine(s"  $escaped")
            }
            line("")
          }
        }
      }
    end if

    line(s"case class ${structure.name}(")
    nest {
      finalProperties.zipWithIndex.foreach { (propLine, idx) =>
        if idx != finalProperties.length - 1 then line(propLine + ",")
        else line(propLine)
      }
    }
    line(")")
    val stls = inlineStructures.result()
    val fqf  = (ctx.definitionScope :+ structure.name).mkString(".")
    val codecTraitName =
      (ctx.definitionScope :+ structure.name).mkString("_") + "Codec"

    val extensions =
      if ctx.definitionScope == List(
          "structures"
        ) && structure.name.value == "Position"
      then " with extensions.PositionSyntax"
      else ""

    if stls.nonEmpty then
      line(
        s"object ${structure.name} extends codecs.$codecTraitName$extensions:"
      )
    else
      line(
        s"object ${structure.name} extends codecs.$codecTraitName$extensions"
      )

    nest {
      val allUnions = propTypes.result().flatMap(collectOrTypes)

      inline def codecsLine: Config ?=> Appender = to(codecsOut)

      val codecPublicity =
        if structConfig.privateCodecs.yes then "private[lsp] " else ""

      codecsOut.topLevel {
        codecsLine("")
        codecsLine(s"private[lsp] trait $codecTraitName:")
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
            s"${codecPublicity}given reader: Reader[$fqf] = Pickle.macroR"
          )
          codecsLine(
            s"${codecPublicity}given writer: Writer[$fqf] = upickle.default.macroW"
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
          ctx.copy(definitionScope =
            ctx.definitionScope :+ structure.name.value
          )

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
                  val (vectors, nonVectors) = ot.items.partition {
                    case at: ArrayType => true
                    case _             => false
                  }

                  vectors.collect { case at: ArrayType => at }.toList match
                    case Nil =>
                    case at :: Nil =>
                      val typeName = renderType(at)
                      line(
                        s"case v: Vector[?] => writeJs[$typeName](v.asInstanceOf[$typeName])"
                      )
                    case many @ (at :: _) =>
                      val first = renderType(at)
                      line("case v: Vector[?] => ")
                      nest {
                        line("v.headOption match")
                        nest {
                          line(
                            s"case None => writeJs[$first](v.asInstanceOf[$first])"
                          )
                          many.foreach { arrayType =>
                            val rendered        = renderType(arrayType)
                            val renderedElement = renderType(arrayType.element)
                            line(
                              s"case Some(_: $renderedElement) => writeJs[$rendered](v.asInstanceOf[$rendered])"
                            )
                          }
                        }
                      }
                  end match

                  nonVectors.map {
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
    line("")

    given ctx: Context = Context.global(manager, List("aliases"))

    line(s"object aliases: ")
    nest {
      manager.aliases.filter(!_.proposed).sortBy(_.name.value).foreach {
        alias =>
          val inlineStructures =
            Map
              .newBuilder[
                Type.ReferenceType,
                (String, Type.StructureLiteralType)
              ]

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
              line(
                s"inline def apply(v: ${renderType(tpe)}): ${alias.name} = v"
              )
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

  def enumerations(out: LineBuilder)(using
      Config
  ) =
    inline def line: Config ?=> Appender = to(out)

    line(s"package $packageName")
    line(s"package enumerations")
    line("")
    line("import runtime.{*, given}")
    line("import json.{*, given}")
    line("import scala.reflect.Typeable")
    line("import scala.annotation.switch")
    line("")

    given ctx: Context = Context.global(manager, List("enumerations"))

    // nest {
    line(s"private val stringCodec = upickle.default.readwriter[String]")
    line(s"private val intCodec = upickle.default.readwriter[Int]")
    line("import upickle.{default => up}")
    line("")

    manager.enumerations.filterNot(_.proposed).foreach { a =>
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
          val rendered = List.newBuilder[String]
          a.values.filter(!_.proposed).foreach { entry =>
            val value =
              base match
                case ET.string => ('"' + entry.value.stringValue + '"').trim
                case _         => entry.value.intValue.toString

            entry.documentation.toOption.foreach { d =>
              commentWriter(out) { cw =>
                cw.commentLine(d.value)
              }
            }
            val entryName = sanitise(entry.name.value)
            rendered += entryName
            line(s"val ${entryName} = entry($value)")
          }

          line("override def ALL = Set(")
          nest {
            line(rendered.result.mkString(", "))
          }
          line(")")

          // `.name` extension
          base match
            case ET.string =>
              // do nothing: strings have a .name by virtue of being strings and extending from StringEnum
              ()

            case _ =>
              val unwrap = base match
                case ET.integer  => "self"
                case ET.uinteger => "self.value"
                case ET.string =>
                  sys.error(
                    "impossible - should be prevented by the previous case"
                  )

              line("")

              line(
                s"extension (self: ${a.name}) def name: String = ($unwrap: @switch) match {"
              )
              nest {
                a.values.foreach { entry =>
                  val lhs = base match
                    case ET.string => '"' + entry.value.stringValue + '"'
                    case _         => entry.value.intValue.toString

                  line(
                    s"case $lhs => \"${entry.name}\""
                  )
                }
              }
              line("}")

          end match
        }
      end if
      line("")
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

case class StructureRenderConfig(privateCodecs: PrivateCodecs)
object StructureRenderConfig:
  inline def default = StructureRenderConfig(privateCodecs = PrivateCodecs.No)

  opaque type PrivateCodecs = Boolean
  object PrivateCodecs extends YesNo[PrivateCodecs]
