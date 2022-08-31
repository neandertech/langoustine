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

  def inFile(s: String)(f: LineBuilder => Unit) =
    val out = Render.LineBuilder()
    out.appendLine(Constants.LICENCE)
    out.appendLine("")

    f(out)
    Using.resource(new FileWriter(Paths.get(path, s).toFile())) { fw =>
      fw.write(out.result)
    }
  end inFile

  inFile("codecs.scala") { codecsOut =>

    re.codecsPrelude(codecsOut)

    inFile("requests.scala") { out =>
      re.requests(out, codecsOut)
    }

    inFile("structures.scala") { out =>
      re.structures(out, codecsOut)
    }

    inFile("aliases.scala") { out =>
      re.aliases(out, codecsOut)
    }

    inFile("enumerations.scala") { out =>
      re.enumerations(out)
    }
  }
end run
