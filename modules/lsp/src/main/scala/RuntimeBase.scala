package langoustine.lsp

import langoustine.*

object RuntimeBase:
  opaque type DocumentUri = String
  object DocumentUri extends OpaqueString[DocumentUri]

  opaque type Uri = String
  object Uri extends OpaqueString[Uri]

  opaque type uinteger = Int 
  object uinteger extends OpaqueInt[uinteger]
