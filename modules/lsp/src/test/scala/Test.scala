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
import scala.util.*

import langoustine.lsp.structures.*

object SomethingTest extends BasicTestSuite:
  test("what") {
    val json = """
      [{"name":"/Users/velvetbaldmime/projects/langoustine",
        "uri":"file:///Users/velvetbaldmime/projects/langoustine"}]
      """

    val init = requestMessage(initialize)

    val logger = scribe.Logger("hi").orphan().clearHandlers().replace()

    // val server = ImmutableLSPBuilder
    //   .create[Try](logger)
    //   .handler(initialize) { (in, req) =>
    //     logger.info("hello!")

    //     val response = InitializeResult(
    //       ServerCapabilities(),
    //       Opt(
    //         InitializeResult
    //           .ServerInfo(name = "langoustine", version = Opt("0.0.1"))
    //       )
    //     )

    //     Success(response)

    //   }
    //   .build

    // println(server.apply(init))


    // val x = locally {
    //   upickle.default
    //     .reader[structures.WorkspaceFolder]
    // }

    // given rd4: Reader[(Vector[structures.WorkspaceFolder] | Null)] = badMerge(
    //   x.widen[(Vector[structures.WorkspaceFolder] | Null)],
    //   nullReadWriter.widen[(Vector[structures.WorkspaceFolder] | Null)]
    // )

    // given wt4: Writer[(Vector[structures.WorkspaceFolder] | Null)] =
    //   upickle.default
    //     .writer[ujson.Value]
    //     .comap[(Vector[structures.WorkspaceFolder] | Null)] { v =>
    //       (v: @unchecked) match
    //         case v: Vector[?] =>
    //           writeJs[Vector[structures.WorkspaceFolder]](
    //             v.asInstanceOf[Vector[structures.WorkspaceFolder]]
    //           )
    //         case null => ujson.Null
    //     }
    // read[Opt[(Vector[structures.WorkspaceFolder] | Null)]](json)
    println(read[Nullable[Vector[structures.WorkspaceFolder]]](json))
  }
end SomethingTest
