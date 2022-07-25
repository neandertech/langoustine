package langoustine

import java.io.File
import scala.util.Using
import java.io.FileWriter
import java.nio.file.Paths

@main def generate(path: String) =
  import upickle.default.*
  import json.{*, given}
  val metaModel = read[MetaModel](new File("metaModel.json"))
  val mm = Manager(metaModel)
  val re = Render(mm)

  import Render.*

  given Render.Config(indents = Indentation(0), indentSize = IndentationSize(2))

  val filter = Set("Location", "Range", "Position")

  val structuresPath = Paths.get(path, "structures.scala").toFile()

  println(mm.get("SemanticTokensRegistrationOptions"))
  println(mm.get("TextDocumentRegistrationOptions"))
  println(mm.get("StaticRegistrationOptions"))
  println(mm.get("SemanticTokenOptions"))

  def inFile(s: File)(f: LineBuilder => Unit) =
    val out = Render.LineBuilder()
    f(out)
    Using.resource(new FileWriter(s)) { fw =>
      fw.write(out.result)
    }

  // inFile(Paths.get(path, "json.scala").toFile()) { out =>
  //   re.json(out)
  // }

  inFile(Paths.get(path, "structures.scala").toFile()) { out =>
    re.structures(out)
  }

  inFile(Paths.get(path, "aliases.scala").toFile()) { out =>
    re.aliases(out)
  }

  inFile(Paths.get(path, "enumerations.scala").toFile()) { out =>
    re.enumerations(out)
  }
end generate
