package langoustine.generate

import langoustine.meta.*, Type.*, Types.*


import langoustine.generate.StructureRenderConfig.PrivateCodecs

def renderStructure(
    manager: Manager,
    structure: Structure,
    builder: LineBuilder,
    codecsOut: LineBuilder
)(using
    renderConfig: Config,
    structConfig: StructureRenderConfig =
      StructureRenderConfig(privateCodecs = PrivateCodecs.No),
    ctx: Context
): Unit =
  val props            = Vector.newBuilder[String]
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

  val properties = deduplicateProperties(allProperties)

  val propTypes = Vector.newBuilder[Type]

  properties.foreach { p =>
    p.tpe match
      case stl: Type.StructureLiteralType =>
        val newTypeName                 = p.name.value.capitalize
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
            val newTypeName                 = "S" + inlineAnonymousStructures
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
  val stls           = inlineStructures.result()
  val fqf            = (ctx.definitionScope :+ structure.name).mkString(".")
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
          case Nil   =>
          case scope =>
            codecsLine(s"import ${scope.mkString(".")}.*")

        val propsWithCorrectTypes = properties.zip(propTypes.result()).map {
          case (p, t) => p.copy(`type` = t)
        }

        codecsLine(s"given fromJson: Decoder[${structure.name}] = ")
        nest {
          circeDecoderForStruct(structure.name, propsWithCorrectTypes).write(
            codecsOut
          )
        }

        codecsLine(s"given toJson: Encoder[${structure.name}] = ")
        nest {
          circeEncoderForStruct(structure.name, propsWithCorrectTypes).write(
            codecsOut
          )
        }

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
        ctx.copy(definitionScope = ctx.definitionScope :+ structure.name.value)

      renderStructure(manager, struct, builder, codecsOut)
    }
  }
