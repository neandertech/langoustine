package langoustine.generate

import java.io.File
import scala.util.Using
import java.io.FileWriter
import java.nio.file.Paths
import upickle.default.*
import scala.reflect.TypeTest

import langoustine.meta.*

import decline_derive.*
import java.nio.file.Path
case class Config(
    out: String,
    schema: String,
    @decline_derive.Name("files") filesOut: Option[String] = None
) derives CommandApplication

@main def run(args: String*) =
  import upickle.default.*
  import json.{*, given}
  val config    = CommandApplication.parseOrExit[Config](args)
  val metaModel = read[MetaModel](new File(config.schema))
  val filtered =
    metaModel.copy(typeAliases =
      metaModel.typeAliases
        .filterNot(a => a.name.value == "LSPAny" || a.name.value == "LSPArray")
    )
  val mm = Manager(filtered)
  val re = Render(mm)

  import Render.*

  given Render.Config(indents = Indentation(0), indentSize = IndentationSize(2))

  val files = List.newBuilder[Path]

  def inFile(s: String)(f: LineBuilder => Unit) =
    val out = Render.LineBuilder()
    out.appendLine(Constants.LICENCE)
    out.appendLine("")

    val path = Paths.get(config.out, s)

    f(out)
    Using.resource(new FileWriter(path.toFile())) { fw =>
      fw.write(out.result)
    }

    files += path
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

    inFile("all.scala") { out =>
      re.exports(out)
    }
  }

  config.filesOut.foreach: path =>

    Using.resource(new FileWriter(Paths.get(path).toFile())) { fw =>
      fw.write(files.result.mkString("\n"))
    }

end run
