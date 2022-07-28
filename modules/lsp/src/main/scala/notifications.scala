package langoustine.lsp
package notifications

import langoustine.*
import upickle.default.*
import langoustine.lsp.json.{*, given}

sealed abstract class LSPNotification(val notificationMethod: String):
  type In

  given reader: Reader[In]
object $ :
  object cancelRequest extends LSPNotification("$/cancelRequest"):
    type In = structures.CancelParams
    given reader: Reader[In] = structures.CancelParams.reader

  object logTrace extends LSPNotification("$/logTrace"):
    type In = structures.LogTraceParams
    given reader: Reader[In] = structures.LogTraceParams.reader

  object progress extends LSPNotification("$/progress"):
    type In = structures.ProgressParams
    given reader: Reader[In] = structures.ProgressParams.reader

  object setTrace extends LSPNotification("$/setTrace"):
    type In = structures.SetTraceParams
    given reader: Reader[In] = structures.SetTraceParams.reader
end $

object exit extends LSPNotification("exit"):
  type In = Unit
  given reader: Reader[In] = unitReader

object initialized extends LSPNotification("initialized"):
  type In = structures.InitializedParams
  given reader: Reader[In] = structures.InitializedParams.reader

object notebookDocument:
  object didChange extends LSPNotification("notebookDocument/didChange"):
    type In = structures.DidChangeNotebookDocumentParams
    given reader: Reader[In] = structures.DidChangeNotebookDocumentParams.reader

  object didClose extends LSPNotification("notebookDocument/didClose"):
    type In = structures.DidCloseNotebookDocumentParams
    given reader: Reader[In] = structures.DidCloseNotebookDocumentParams.reader

  object didOpen extends LSPNotification("notebookDocument/didOpen"):
    type In = structures.DidOpenNotebookDocumentParams
    given reader: Reader[In] = structures.DidOpenNotebookDocumentParams.reader

  object didSave extends LSPNotification("notebookDocument/didSave"):
    type In = structures.DidSaveNotebookDocumentParams
    given reader: Reader[In] = structures.DidSaveNotebookDocumentParams.reader
end notebookDocument

object telemetry:
  object event extends LSPNotification("telemetry/event"):
    type In = ujson.Value
    given reader: Reader[In] = jsReader

object textDocument:
  object didChange extends LSPNotification("textDocument/didChange"):
    type In = structures.DidChangeTextDocumentParams
    given reader: Reader[In] = structures.DidChangeTextDocumentParams.reader

  object didClose extends LSPNotification("textDocument/didClose"):
    type In = structures.DidCloseTextDocumentParams
    given reader: Reader[In] = structures.DidCloseTextDocumentParams.reader

  object didOpen extends LSPNotification("textDocument/didOpen"):
    type In = structures.DidOpenTextDocumentParams
    given reader: Reader[In] = structures.DidOpenTextDocumentParams.reader

  object didSave extends LSPNotification("textDocument/didSave"):
    type In = structures.DidSaveTextDocumentParams
    given reader: Reader[In] = structures.DidSaveTextDocumentParams.reader

  object publishDiagnostics
      extends LSPNotification("textDocument/publishDiagnostics"):
    type In = structures.PublishDiagnosticsParams
    given reader: Reader[In] = structures.PublishDiagnosticsParams.reader

  object willSave extends LSPNotification("textDocument/willSave"):
    type In = structures.WillSaveTextDocumentParams
    given reader: Reader[In] = structures.WillSaveTextDocumentParams.reader
end textDocument

object window:
  object logMessage extends LSPNotification("window/logMessage"):
    type In = structures.LogMessageParams
    given reader: Reader[In] = structures.LogMessageParams.reader

  object showMessage extends LSPNotification("window/showMessage"):
    type In = structures.ShowMessageParams
    given reader: Reader[In] = structures.ShowMessageParams.reader

  object workDoneProgress:
    object cancel extends LSPNotification("window/workDoneProgress/cancel"):
      type In = structures.WorkDoneProgressCancelParams
      given reader: Reader[In] = structures.WorkDoneProgressCancelParams.reader
end window

object workspace:
  object didChangeConfiguration
      extends LSPNotification("workspace/didChangeConfiguration"):
    type In = structures.DidChangeConfigurationParams
    given reader: Reader[In] = structures.DidChangeConfigurationParams.reader

  object didChangeWatchedFiles
      extends LSPNotification("workspace/didChangeWatchedFiles"):
    type In = structures.DidChangeWatchedFilesParams
    given reader: Reader[In] = structures.DidChangeWatchedFilesParams.reader

  object didChangeWorkspaceFolders
      extends LSPNotification("workspace/didChangeWorkspaceFolders"):
    type In = structures.DidChangeWorkspaceFoldersParams
    given reader: Reader[In] = structures.DidChangeWorkspaceFoldersParams.reader

  object didCreateFiles extends LSPNotification("workspace/didCreateFiles"):
    type In = structures.CreateFilesParams
    given reader: Reader[In] = structures.CreateFilesParams.reader

  object didDeleteFiles extends LSPNotification("workspace/didDeleteFiles"):
    type In = structures.DeleteFilesParams
    given reader: Reader[In] = structures.DeleteFilesParams.reader

  object didRenameFiles extends LSPNotification("workspace/didRenameFiles"):
    type In = structures.RenameFilesParams
    given reader: Reader[In] = structures.RenameFilesParams.reader
end workspace
