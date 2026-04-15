package langoustine.generate

import langoustine.meta.*, Type.*, Types.*

def typeTestRender(typeName: TypeName, tpe: Type)(using
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
                case _                        => ("c: " + renderType(tpe), "c")
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
