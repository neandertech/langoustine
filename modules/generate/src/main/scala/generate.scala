package langoustine.generate

import scala.util.Using
import java.io.FileWriter
import java.nio.file.Paths

import langoustine.meta.{*, given}

import decline_derive.*
import java.nio.file.Path
import java.nio.file.Files

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.circe.JsoniterScalaCodec.*

case class CLI(
    out: String,
    schema: String,
    @decline_derive.Name("files") filesOut: Option[String] = None
) derives CommandApplication

@main def run(args: String*) =
  val config    = CommandApplication.parseOrExit[CLI](args)
  val metaModel = readFromArray[io.circe.Json](
    Files.readAllBytes(Paths.get(config.schema))
  ).as[MetaModel].fold(throw _, identity)

  val filtered =
    metaModel
      .copy(typeAliases =
        metaModel.typeAliases
          .filterNot(a =>
            a.name.value == "LSPAny" || a.name.value == "LSPArray"
          )
      )

  val mm = Manager(filtered)
  val re = Render(mm)

  given Config(indents = Indentation(0), indentSize = IndentationSize(2))

  val files = List.newBuilder[Path]

  def inFile(s: String)(f: LineBuilder => Unit) =
    val out = LineBuilder()

    val path = Paths.get(config.out, s)
    Files.createDirectories(path.getParent())

    out.appendLine("// format:off")

    f(out)
    Using.resource(new FileWriter(path.toFile())) { fw =>
      fw.write(out.result)
    }

    files += path
  end inFile

  def writeFile(s: String, out: LineBuilder) = 
    val path = Paths.get(config.out, s)

    Files.createDirectories(path.getParent())

    Using.resource(new FileWriter(path.toFile())) { fw =>
      fw.write(out.result)
    }

    files += path
  end writeFile


  inFile("codecs.scala") { codecsOut =>
    re.codecsPrelude(codecsOut)

    inFile("requests.scala") { out =>
      re.requests(out, codecsOut)
    }

    re.structures(codecsOut).foreach(writeFile)

    re.aliases(codecsOut).foreach(writeFile)

    re.enumerations().foreach(writeFile)

    inFile("all.scala") { out =>
      re.exports(out)
    }
  }

  config.filesOut.foreach: path =>
    val filesPath = Paths.get(path)

    Files.createDirectories(filesPath.getParent())

    Using.resource(new FileWriter(filesPath.toFile())) { fw =>
      fw.write(files.result.mkString("\n"))
    }

end run
