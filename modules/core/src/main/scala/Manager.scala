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

  export mm.{structures, enumerations, typeAliases as aliases}

  def get(s: String) = index.get(s)

// @main def tes(path: String) =
//   import upickle.default.*
//   import json.{*, given}
//   val mm = Manager(read[MetaModel](new File("metaModel.json")))
//   val re = Render(mm)

//   import Render.*

//   given Render.Config(indents = Indentation(0), indentSize = IndentationSize(2))

//   val filter = Set("Location", "Range", "Position")

//   val structuresPath = Paths.get(path, "structures.scala").toFile()

//   println(mm.get("URI"))

//   def inFile(s: File)(f: LineBuilder => Unit) = {
//     val out = Render.LineBuilder()
//     f(out)
//     Using.resource(new FileWriter(s)) { fw =>
//       fw.write(out.result)
//     }
//   }

//   inFile(Paths.get(path, "structures.scala").toFile()) { out =>
//     re.structures(out)
//   }

//   inFile(Paths.get(path, "aliases.scala").toFile()) { out =>
//     re.aliases(out)
//   }

//   inFile(Paths.get(path, "enumerations.scala").toFile()) { out =>
//     re.enumerations(out)
//   }
