package langoustine.meta

import java.io.File
import scala.util.Using
import java.io.FileWriter
import java.nio.file.Paths

class Manager(mm: MetaModel):
  private type IdxVal = Enumeration | TypeAlias | Structure | Request |
    Notification

  val index: Map[String, IdxVal] =
    mm.enumerations.map(e => e.name.value -> e).toMap[String, IdxVal] ++
      mm.structures.map(s => s.name.value -> s).toMap ++
      mm.typeAliases.map(s => s.name.value -> s).toMap ++
      mm.requests.map(r => r.method.value -> r).toMap ++
      mm.notifications.map(r => r.method.value -> r).toMap

  export mm.{
    structures,
    enumerations,
    typeAliases as aliases,
    requests,
    notifications
  }

  def get(s: String) = index.get(s)
end Manager
