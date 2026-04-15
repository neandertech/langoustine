package langoustine.generate

import langoustine.meta.*, Type.*, Types.*

import util.boundary

def circeDecoderForStruct(name: StructureName, props: Vector[Property])(using
    Context,
    Config
) =
  import WriterDefinition.*

  Definition { out =>
    inline def line: Config ?=> Appender = to(out)

    val simplifiedProps = props.map(simplifyProp)

    if simplifiedProps.isEmpty then line(s"Decoder.const(${name}())")
    else
      val recursiveProps =
        simplifiedProps.filter: prop =>
          boundary[Boolean]:
            prop.tpe.traverse:
              case rt @ ReferenceType(nm) if nm.value == name.value =>
                boundary.break(
                  summon[Context].resolve(rt).value.startsWith("structures.")
                )
                TypeTraversal.Skip
              case _ => TypeTraversal.Skip
            false

      line(
        "// cache all decoders for this type when fromJson first initialised"
      )

      if recursiveProps.nonEmpty then
        line(s"Decoder.recursive: decode_${name} => ")
        nest {
          renderProps:
            case ReferenceType(nm) if nm.value == name.value =>
              Some(s"decode_${name}")
            case _ => None
        }
      else renderProps(_ => None)

      def renderProps(refTypeOverride: ReferenceType => Option[String])(using
          Config
      ) =
        simplifiedProps.foreach { prop =>
          line(
            s"val decode_${prop.name}: Decoder[${renderType(prop.tpe)}] = ${circeDecoderReference(prop.tpe, refTypeOverride)}"
          )
        }

        line("Dec.fromJsonObject: dec =>")
        nest {
          line("for")
          nest {
            simplifiedProps.foreach { prop =>
              val func  = if prop.optional.yes then "getOpt" else "get"
              val codec = s"decode_${prop.name}"
              codec match
                case s: String =>
                  line(
                    s"${sanitise(prop.name.value)} <- dec.$func(\"${prop.name}\", $codec)"
                  )
              end match

            }
          }
          line(s"yield $name(")
          nest {
            props.foreach { prop =>
              line(s"${sanitise(prop.name.value)},")
            }
          }
          line(")")
        }
      end renderProps
    end if

  }
end circeDecoderForStruct
