package langoustine.generate

import langoustine.meta.*
import langoustine.generate.StructureRenderConfig.PrivateCodecs
import cats.syntax.all
import langoustine.meta.Type.OrType
import scala.util.boundary

import Types.*

def renderAlias(manager: Manager, out: LineBuilder, codecsOut: LineBuilder, packageName: String, alias: TypeAlias)(using
    Config
)(using ctx: Context): Unit =
  inline def line: Config ?=> Appender = to(out)


  val inlineStructures =
    Map
      .newBuilder[
        Type.ReferenceType,
        (String, Type.StructureLiteralType)
      ]

  var inlineAnonymousStructures = 0
  val newType                   = alias.`type`.traverse {
    case stl: Type.StructureLiteralType =>
      val newTypeName                 = "S" + inlineAnonymousStructures
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

      codecsLine(s"given fromJson: Decoder[${alias.name}] = ")
      nest {
        codecsLine(
          circeDecoderReference(
            newType
          ) + s".asInstanceOf[Decoder[${alias.name}]]"
        )
      }

      codecsLine("")

      codecsLine(s"given toJson: Encoder[${alias.name}] =")
      nest {
        codecsLine(
          circeEncoderReference(
            newType
          ) + s".asInstanceOf[Encoder[${alias.name}]]"
        )
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

    line(
      s"extension (v: ${alias.name}) inline def value: ${renderType(newType)} = v"
    )

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
        ctx.copy(definitionScope = ctx.definitionScope :+ alias.name.value)

      renderStructure(manager, struct, out, codecsOut)
    }
  }
end renderAlias
