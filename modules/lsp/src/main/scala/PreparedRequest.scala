package langoustine.lsp

import requests.*

class PreparedRequest[X <: LSPRequest](val x: X, val in: x.In):
  type Out = x.Out
