package langoustine.lsp

enum LangoustineError(msg: String, tr: Throwable = null)
    extends Throwable(msg, tr):
  case FailureParsing(in: ujson.Value, reason: Throwable = null)
      extends LangoustineError(s"Failed to parse input '$in' $reason", reason)
  case StubError(msg: String) extends LangoustineError(msg)
