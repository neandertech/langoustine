import langoustine.lsp.*
import structures.*

import upickle.default.*

@main def hello =
  val range =
    """
    {"start": {"line": 0, "character": 5}, "end": {"line": 15, "character": 10}}
    """
  val location =
    s"""
    {"uri": "http://rickroll.in", "range": $range}
    """

  println(read[Range](range))
  println(read[Location](location))

end hello
