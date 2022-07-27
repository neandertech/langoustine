import langoustine.lsp.*
import structures.*

import upickle.default.*
import langoustine.ImmutableLSPBuilder
import scala.util.*
import langoustine.JSONRPC

@main def hello =
  val range =
    """
    {"start": {"line": 0, "character": 5}, "end": {"line": 15, "character": 10}}
    """
  val location =
    s"""
    {"uri": "http://rickroll.in", "range": $range}
    """

  import requests.*

  val in = structures.CodeLens(
    range = read[Range](range),
    command = Command("hello", "world", Vector.empty),
    data = ujson.Str("yo")
  )

  val builder = ImmutableLSPBuilder
    .create[Try]
    .handler(codeLens.resolve) { (in, req) =>
      println(in)
      Success(in)
    }
    .build

  val req =
    JSONRPC.request(25, "codeLens/resolve", write(in))

  val req1 =
    JSONRPC.request(26, "textDocument/definition", """{"dp": 152}""")

end hello
