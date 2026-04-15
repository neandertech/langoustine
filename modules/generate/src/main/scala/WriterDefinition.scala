package langoustine.generate

import langoustine.meta.*
import langoustine.generate.StructureRenderConfig.PrivateCodecs
import cats.syntax.all
import langoustine.meta.Type.OrType
import scala.util.boundary

enum WriterDefinition:
  case Expression(str: String)
  case Definition(f: LineBuilder => Unit)

  def write(out: LineBuilder)(using Config) =
    this match
      case Expression(str) => to(out)(str)
      case Definition(f)   => f(out)
