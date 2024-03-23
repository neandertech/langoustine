package langoustine.lsp
import requests.*

class PreparedNotification[X <: LSPNotification](val x: X, val in: x.In):
  type In = x.In
