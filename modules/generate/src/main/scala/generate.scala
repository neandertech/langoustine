package langoustine

import java.io.File
import scala.util.Using
import java.io.FileWriter
import java.nio.file.Paths

@main def generate(path: String) =
  import upickle.default.*
  import json.{*, given}
  val metaModel = read[MetaModel](new File("metaModel.json"))
  val mm        = Manager(metaModel)
  val re        = Render(mm)

  import Render.*

  given Render.Config(indents = Indentation(0), indentSize = IndentationSize(2))

  // metaModel.requests.foreach(println)

  def inFile(s: File)(f: LineBuilder => Unit) =
    val out = Render.LineBuilder()
    f(out)
    Using.resource(new FileWriter(s)) { fw =>
      fw.write(out.result)
    }

  inFile(Paths.get(path, "requests.scala").toFile()) { out =>
    re.requests(out)
  }

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
