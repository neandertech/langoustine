package langoustine.generate

import langoustine.meta.*, Type.*, Types.*

def renderEnumeration(a: Enumeration, out: LineBuilder)(using
    Config,
    Context
) =

  inline def line: Config ?=> Appender = to(out)
  val base                             = a.`type`.name
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
            case ET.string   =>
              sys.error(
                "impossible - should be prevented by the previous case"
              )

          line("")

          line(
            s"extension (self: ${a.name}) def name: String = ($unwrap: @switch) match {"
          )
          nest {
            a.values.foreach { entry =>
              line(
                s"case ${entry.value.intValue} => \"${entry.name}\""
              )
            }
          }
          line("}")

      end match
    }
  end if
end renderEnumeration
