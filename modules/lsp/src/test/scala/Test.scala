import langoustine.*
import langoustine.lsp.*
import scala.io.Source

import upickle.default.*

def requestMessage(request: LSPRequest) =
  val res = Source.fromResource(s"${request.requestMethod}.json")
  upickle.default.read[JSONRPC.RequestMessage](res.getLines.mkString("\n"))

import requests.*

import langoustine.lsp.json.*

import verify.*

object SomethingTest extends BasicTestSuite:
  test("addition") {
    val json = """
  [{"name":"/Users/velvetbaldmime/projects/langoustine",
    "uri":"file:///Users/velvetbaldmime/projects/langoustine"}]
  """

    given rd4: Reader[(Vector[structures.WorkspaceFolder] | Null)] = badMerge(
      upickle.default
        .reader[Vector[structures.WorkspaceFolder]]
        .widen[(Vector[structures.WorkspaceFolder] | Null)],
      nullReadWriter.widen[(Vector[structures.WorkspaceFolder] | Null)]
    )

    given wt4: Writer[(Vector[structures.WorkspaceFolder] | Null)] =
      upickle.default
        .writer[ujson.Value]
        .comap[(Vector[structures.WorkspaceFolder] | Null)] { v =>
          (v: @unchecked) match
            case v: Vector[?] =>
              writeJs[Vector[structures.WorkspaceFolder]](
                v.asInstanceOf[Vector[structures.WorkspaceFolder]]
              )
            case null => ujson.Null
        }
    read[Opt[(Vector[structures.WorkspaceFolder] | Null)]](json)
  }
end SomethingTest
