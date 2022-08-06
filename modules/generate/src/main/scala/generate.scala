package langoustine.generate

import java.io.File
import scala.util.Using
import java.io.FileWriter
import java.nio.file.Paths
import upickle.default.*
import scala.reflect.TypeTest

import langoustine.meta.*

@main def run(path: String) =
  import upickle.default.*
  import json.{*, given}
  val metaModel = read[MetaModel](new File("metaModel.json"))
  val filtered =
    metaModel.copy(typeAliases =
      metaModel.typeAliases
        .filterNot(a => a.name.value == "LSPAny" || a.name.value == "LSPArray"),
    )
  val mm = Manager(filtered)
  val re = Render(mm)

  import Render.*

  given Render.Config(indents = Indentation(0), indentSize = IndentationSize(2))

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
end run
