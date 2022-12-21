package langoustine.tracer

enum Page:
  case Logs, Commands

enum ModalCommand:
  case Close, DownloadSnapshot
  case ShowCode(code: String)
  case ShowSummary

enum JsonMode:
  case Details, Raw
