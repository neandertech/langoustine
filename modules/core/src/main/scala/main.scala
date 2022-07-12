package langoustine

import java.io.File
import scala.util.Using
import java.io.FileWriter
import java.nio.file.Paths

class Manager(mm: MetaModel):
  private type IdxVal = Enumeration | TypeAlias | Structure | Request

  val index: Map[String, IdxVal] =
    mm.enumerations.map(e => e.name.value -> e).toMap[String, IdxVal] ++
      mm.structures.map(s => s.name.value -> s).toMap ++
      mm.typeAliases.map(s => s.name.value -> s).toMap ++
      mm.requests.map(r => r.method.value -> r).toMap

  export mm.{structures, enumerations}

  def get(s: String) = index.get(s)

@main def tes(path: String) =
  import upickle.default.*
  import json.{*, given}
  val mm = Manager(read[MetaModel](new File("metaModel.json")))
  val re = Render(mm)

  import Render.*

  given Render.Config(indents = Indentation(0), indentSize = IndentationSize(2))

  val filter = Set("Location", "Range", "Position")

  val structuresPath = Paths.get(path, "lsp", "structures.scala").toFile()

  val out = Render.LineBuilder()
  re.structures(out)
  Using.resource(new FileWriter(structuresPath)) {fw => 
    fw.write(out.result)
  }
