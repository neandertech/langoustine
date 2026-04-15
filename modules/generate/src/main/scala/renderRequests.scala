package langoustine.generate

import langoustine.meta.*, Type.*, Types.*
import langoustine.generate.StructureRenderConfig.PrivateCodecs

def renderRequests(
    manager: Manager,
    packageName: String,
    out: LineBuilder,
    codecsOut: LineBuilder
)(using
    Config
): Unit =
  inline def line: Config ?=> Appender       = to(out)
  inline def codecsLine: Config ?=> Appender = to(codecsOut)
  line(s"package $packageName")
  line(s"package requests")
  line("")
  line("import langoustine.*")
  line("import io.circe.{Decoder, Encoder}")
  line("import runtime.{*, given}")
  line("import structures.*")
  line("")

  val requestPrelude = """
        |sealed abstract class LSPRequest(val requestMethod: String):
        |  type In
        |  type Out
        |
        |  given inputFromJson: Decoder[In]
        |  given inputToJson: Encoder[In]
        |  given outputToJson: Encoder[Out]
        |  given outputFromJson: Decoder[Out]
        |
        |  def apply(in: In): PreparedRequest[this.type] = PreparedRequest(this,in)
        """.stripMargin.trim

  requestPrelude.linesIterator.foreach(line)

  line("")

  val customRequestPrelude =
    """
        |abstract class CustomRequest[I, O](method: String)(using ifr: Decoder[I], ito: Encoder[I], ofr: Decoder[O], oto: Encoder[O]) extends LSPRequest(method):
        |   override type In = I
        |   override type Out = O
        |
        |   override given inputFromJson: Decoder[In] = ifr
        |
        |   override given inputToJson: Encoder[In] = ito
        |
        |   override given outputToJson: Encoder[Out] = oto
        |
        |   override given outputFromJson: Decoder[Out] = ofr
        |
        |abstract class CustomNotification[I](method: String)(using ifr: Decoder[I], ito: Encoder[I]) extends LSPNotification(method):
        |   override type In = I
        |
        |   override given inputFromJson: Decoder[In] = ifr
        |
        |   override given inputToJson: Encoder[In] = ito
        """.stripMargin.trim

  customRequestPrelude.linesIterator.foreach(line)

  line("")

  val notificationPrelude = """
    |sealed abstract class LSPNotification(val notificationMethod: String):
    |  type In
    |
    |  given inputFromJson: Decoder[In]
    |  given inputToJson: Encoder[In]
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

      val inStruct  = rewriteAndParmas(req.params, inStructName)
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
          renderStructure(manager, _, out, codecsOut)
        }

        outStruct.foreach {
          given StructureRenderConfig = StructureRenderConfig.default
            .copy(privateCodecs = PrivateCodecs.Yes)
          renderStructure(manager, _, out, codecsOut)
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
                    WriterDefinition.Expression("Decoder.const(())")
                  case ParamsType.Single(t) =>
                    WriterDefinition.Expression(circeDecoderReference(t))
                  case ParamsType.Many(t) =>
                    WriterDefinition.Expression(
                      circeDecoderReference(OrType(t))
                    )
              case Some(s) =>
                WriterDefinition.Expression(s"$path.${s.name.value}.fromJson")

          codecsLine(s"given inputFromJson: Decoder[In] = ")
          nest {
            reader.write(codecsOut)
          }

          codecsLine("")

          codecsLine(s"given inputToJson: Encoder[In] = ")
          nest {
            inStruct match
              case None =>
                req.params match
                  case ParamsType.None      => codecsLine("Encoder.encodeUnit")
                  case ParamsType.Single(t) =>
                    codecsLine(circeEncoderReference(t))
                  case ParamsType.Many(t) => codecsLine("Pickle.macroR")
              case Some(s) =>
                codecsLine(s"$path.${s.name.value}.toJson")
          }

          codecsLine("")

          codecsLine(s"given outputToJson: Encoder[Out] =")
          nest {
            outStruct match
              case None =>
                val (isOption, tpe) = simplifyOrType(req.result)
                if isOption then
                  codecsLine(
                    s"Encoder.encodeOption(${circeEncoderReference(tpe)})"
                  )
                else codecsLine(circeEncoderReference(tpe))
              case Some(s) =>
                codecsLine(s"$path.${s.name.value}.toJson")
          }

          codecsLine("")

          codecsLine(
            s"given outputFromJson: Decoder[Out] ="
          )
          nest {
            outStruct match
              case None =>
                val (isOption, tpe) = simplifyOrType(req.result)
                if isOption then
                  codecsLine(
                    s"Decoder.decodeOption(${circeDecoderReference(tpe)})"
                  )
                else codecsLine(circeDecoderReference(tpe))
              case Some(s) =>
                codecsLine(s"$path.${s.name.value}.fromJson")
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
            // inStruct match
            //   case None =>
            req.params match
              case ParamsType.None =>
                WriterDefinition.Expression("Decoder.const(())")
              case ParamsType.Single(t) =>
                WriterDefinition.Expression(circeDecoderReference(t))
              case ParamsType.Many(t) =>
                WriterDefinition.Expression(circeDecoderReference(OrType(t)))
          // case Some(s) =>
          //   WriterDefinition.Expression(s"$path.${s.name.value}.fromJson")

          codecsLine(s"given inputFromJson: Decoder[In] = ")
          nest {
            reader.write(codecsOut)
          }

          codecsLine(s"given inputToJson: Encoder[In] = ")
          nest {
            // inStruct match
            //   case None =>
            req.params match
              case ParamsType.None      => codecsLine("Encoder.encodeUnit")
              case ParamsType.Single(t) =>
                codecsLine(circeEncoderReference(t))
              case ParamsType.Many(t) =>
                codecsLine(circeEncoderReference(OrType(t)))

            // case Some(s) =>
            //   codecsLine(s"$path.${s.name.value}.toJson")
          }

        }
      }
    }
    line("")
  end renderNotification

  def rec(segments: List[String])(using Config): Unit =
    segments match
      case Nil    => ()
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
end renderRequests
