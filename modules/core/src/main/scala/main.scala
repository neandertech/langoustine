package langoustine

import java.io.File

class Manager(mm: MetaModel):
  private type IdxVal = Enumeration | Type | Structure

  val index: Map[String, Enumeration | Type | Structure] =
    mm.enumerations.map(e => e.name.value -> e).toMap[String, IdxVal] ++
      mm.structures.map(s => s.name.value -> s).toMap ++
      mm.typeAliases.map(s => s.name.value -> s.`type`).toMap

  def get(s: String) = index.get(s)

class Render(manager: Manager):
  def structure(s: Structure) = ???


@main def tes =
  import upickle.default.*
  import json.{*, given}
  val mm = Manager(read[MetaModel](new File("metaModel.json")))

  println(mm.get("ImplementationParams"))
  println(mm.get("TextDocumentPositionParams"))
  println(mm.get("WorkDoneProgressParams"))
  
  trait ProgressToken
  trait WorkDoneProgress(workDoneToken: ProgressToken)
  trait ImplementationParams(reference: ProgressToken) 
    extends WorkDoneProgress

