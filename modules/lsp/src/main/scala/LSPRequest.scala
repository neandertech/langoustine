package langoustine

import upickle.default.*

abstract class LSPRequest(val requestMethod: String):
  type In
  type Out

  given reader: Reader[In]
  given writer: Writer[Out]
