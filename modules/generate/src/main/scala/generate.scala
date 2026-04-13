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
import java.nio.file.Files

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.circe.JsoniterScalaCodec.*

case class Config(
    out: String,
    schema: String,
    @decline_derive.Name("files") filesOut: Option[String] = None
) derives CommandApplication

@main def run(args: String*) =
  import upickle.default.*
  import json.{*, given}
  val config    = CommandApplication.parseOrExit[Config](args)
  val metaModel = readFromArray[io.circe.Json](
    Files.readAllBytes(Paths.get(config.schema))
  ).as[MetaModel].fold(throw _, identity)

  val allowedStructs: String => Boolean =
    Set(
      "WorkspaceFolder",
      "Position",
      "Range",
      "SemanticTokensLegend",
      "SemanticTokens",
      "InlayHint",
      "InlayHintLabelPart",
      "MarkupContent",
      "Command",
      "TextEdit",
      "Location",
      "CompletionItem",
      "CompletionItemLabelDetails",
      "InsertReplaceEdit"
    ).map(StructureName.apply)

  val filtered =
    metaModel
      .copy(typeAliases =
        metaModel.typeAliases
          .filterNot(a =>
            a.name.value == "LSPAny" || a.name.value == "LSPArray"
          )
      )
      .copy(structures =
        metaModel.structures.filter(s => allowedStructs(s.name.value))
      )
      .copy(
        requests =
          metaModel.requests.filter(_.method.value == "completionItem/resolve"),
        notifications = Vector.empty,
        typeAliases = Vector.empty
      )

  println(filtered.requests.length)
  val mm = Manager(filtered)
  val re = Render(mm)

  import Render.*

  given Render.Config(indents = Indentation(0), indentSize = IndentationSize(2))

  val files = List.newBuilder[Path]

  def inFile(s: String)(f: LineBuilder => Unit) =
    val out = Render.LineBuilder()

    val path = Paths.get(config.out, s)

    out.appendLine("// format:off")

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

    // inFile("aliases.scala") { out =>
    //   re.aliases(out, codecsOut)
    // }

    inFile("enumerations.scala") { out =>
      re.enumerations(out)
    }

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
