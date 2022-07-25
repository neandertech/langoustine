package langoustine.lsp

import langoustine.*

import upickle.default.*

object RuntimeBase:
  import langoustine.lsp.json.*

  opaque type DocumentUri = String
  object DocumentUri extends OpaqueString[DocumentUri]:
    given ReadWriter[DocumentUri] =
      stringCodec.asInstanceOf[ReadWriter[DocumentUri]]

  opaque type Uri = String
  object Uri extends OpaqueString[Uri]:
    given ReadWriter[Uri] = stringCodec.asInstanceOf[ReadWriter[Uri]]

  opaque type uinteger = Int
  object uinteger extends OpaqueInt[uinteger]:
    given ReadWriter[uinteger] = intCodec.asInstanceOf[ReadWriter[uinteger]]

end RuntimeBase
