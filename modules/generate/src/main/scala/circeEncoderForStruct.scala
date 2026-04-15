package langoustine.generate

import langoustine.meta.*, Type.*, Types.*

import util.boundary

def circeEncoderForStruct(name: StructureName, props: Vector[Property])(using
    Context,
    Config
) =
  import WriterDefinition.*

  Definition { out =>
    inline def line: Config ?=> Appender = to(out)

    def encode(prop: Property, value: String)(using Config) =
      line(
        s"enc.field(\"${prop.name}\", $value, encode_${prop.name})"
      )

    val simplifiedProps = props.map(simplifyProp)

    if simplifiedProps.isEmpty then line("Encoder.instance(_ => Json.obj())")
    else
      val recursiveProps =
        simplifiedProps.filter: prop =>
          util.boundary[Boolean]:
            prop.tpe.traverse:
              case rt @ ReferenceType(nm) if nm.value == name.value =>
                boundary.break(
                  summon[Context].resolve(rt).value.startsWith("structures.")
                )
                TypeTraversal.Skip
              case _ => TypeTraversal.Skip
            false

      line(
        "// cache all encoders for this type when toJson first initialised"
      )

      if recursiveProps.nonEmpty then
        line(s"Encoder.recursive: encode_${name} => ")
        nest {
          renderProps:
            case ReferenceType(nm) if nm.value == name.value =>
              Some(s"encode_${name}")
            case _ => None
        }
      else renderProps(_ => None)

      def renderProps(refTypeOverride: ReferenceType => Option[String])(using
          Config
      ) =
        simplifiedProps.foreach { prop =>
          line(
            s"val encode_${prop.name}: Encoder[${renderType(prop.tpe)}] = ${circeEncoderReference(prop.tpe, refTypeOverride)}"
          )
        }

        line("Enc.toJsonObject: (enc, a) =>")
        nest {
          simplifiedProps.foreach { prop =>
            if prop.optional.yes then
              line(s"a.${sanitise(prop.name.value)}.foreach: v =>")
              nest {
                encode(prop, "v")
              }
            else encode(prop, "a." + sanitise(prop.name.value))

          }
        }
      end renderProps
    end if
  }
end circeEncoderForStruct
