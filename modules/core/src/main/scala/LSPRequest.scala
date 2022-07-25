package langoustine

import upickle.default.*

sealed abstract class LSPRequest(val requestMethod: String):
  type In
  type Out

  given reader: Reader[In]
  given writer: Writer[Out] = upickle.default.writer[String].comap(_.toString)
