package langoustine.lsp
package notifications

import langoustine.*
import upickle.default.*
import langoustine.lsp.json.{*, given}
// format: off

sealed abstract class LSPNotification(val notificationMethod: String):
  type In

  given inputReader: Reader[In]
  given inputWriter: Writer[In]
object $:
  object cancelRequest extends LSPNotification("$/cancelRequest"):
    type In = structures.CancelParams
    given inputReader: Reader[In] = 
      structures.CancelParams.reader
    given inputWriter: Writer[In] = 
      structures.CancelParams.writer
  
  object logTrace extends LSPNotification("$/logTrace"):
    type In = structures.LogTraceParams
    given inputReader: Reader[In] = 
      structures.LogTraceParams.reader
    given inputWriter: Writer[In] = 
      structures.LogTraceParams.writer
  
  object progress extends LSPNotification("$/progress"):
    type In = structures.ProgressParams
    given inputReader: Reader[In] = 
      structures.ProgressParams.reader
    given inputWriter: Writer[In] = 
      structures.ProgressParams.writer
  
  object setTrace extends LSPNotification("$/setTrace"):
    type In = structures.SetTraceParams
    given inputReader: Reader[In] = 
      structures.SetTraceParams.reader
    given inputWriter: Writer[In] = 
      structures.SetTraceParams.writer
  
object exit extends LSPNotification("exit"):
  type In = Unit
  given inputReader: Reader[In] = 
    unitReader
  given inputWriter: Writer[In] = 
    unitWriter

object initialized extends LSPNotification("initialized"):
  type In = structures.InitializedParams
  given inputReader: Reader[In] = 
    structures.InitializedParams.reader
  given inputWriter: Writer[In] = 
    structures.InitializedParams.writer

object notebookDocument:
  object didChange extends LSPNotification("notebookDocument/didChange"):
    type In = structures.DidChangeNotebookDocumentParams
    given inputReader: Reader[In] = 
      structures.DidChangeNotebookDocumentParams.reader
    given inputWriter: Writer[In] = 
      structures.DidChangeNotebookDocumentParams.writer
  
  object didClose extends LSPNotification("notebookDocument/didClose"):
    type In = structures.DidCloseNotebookDocumentParams
    given inputReader: Reader[In] = 
      structures.DidCloseNotebookDocumentParams.reader
    given inputWriter: Writer[In] = 
      structures.DidCloseNotebookDocumentParams.writer
  
  object didOpen extends LSPNotification("notebookDocument/didOpen"):
    type In = structures.DidOpenNotebookDocumentParams
    given inputReader: Reader[In] = 
      structures.DidOpenNotebookDocumentParams.reader
    given inputWriter: Writer[In] = 
      structures.DidOpenNotebookDocumentParams.writer
  
  object didSave extends LSPNotification("notebookDocument/didSave"):
    type In = structures.DidSaveNotebookDocumentParams
    given inputReader: Reader[In] = 
      structures.DidSaveNotebookDocumentParams.reader
    given inputWriter: Writer[In] = 
      structures.DidSaveNotebookDocumentParams.writer
  
object telemetry:
  object event extends LSPNotification("telemetry/event"):
    type In = ujson.Value
    given inputReader: Reader[In] = 
      jsReader
    given inputWriter: Writer[In] = 
      jsWriter
  
object textDocument:
  object didChange extends LSPNotification("textDocument/didChange"):
    type In = structures.DidChangeTextDocumentParams
    given inputReader: Reader[In] = 
      structures.DidChangeTextDocumentParams.reader
    given inputWriter: Writer[In] = 
      structures.DidChangeTextDocumentParams.writer
  
  object didClose extends LSPNotification("textDocument/didClose"):
    type In = structures.DidCloseTextDocumentParams
    given inputReader: Reader[In] = 
      structures.DidCloseTextDocumentParams.reader
    given inputWriter: Writer[In] = 
      structures.DidCloseTextDocumentParams.writer
  
  object didOpen extends LSPNotification("textDocument/didOpen"):
    type In = structures.DidOpenTextDocumentParams
    given inputReader: Reader[In] = 
      structures.DidOpenTextDocumentParams.reader
    given inputWriter: Writer[In] = 
      structures.DidOpenTextDocumentParams.writer
  
  object didSave extends LSPNotification("textDocument/didSave"):
    type In = structures.DidSaveTextDocumentParams
    given inputReader: Reader[In] = 
      structures.DidSaveTextDocumentParams.reader
    given inputWriter: Writer[In] = 
      structures.DidSaveTextDocumentParams.writer
  
  object publishDiagnostics extends LSPNotification("textDocument/publishDiagnostics"):
    type In = structures.PublishDiagnosticsParams
    given inputReader: Reader[In] = 
      structures.PublishDiagnosticsParams.reader
    given inputWriter: Writer[In] = 
      structures.PublishDiagnosticsParams.writer
  
  object willSave extends LSPNotification("textDocument/willSave"):
    type In = structures.WillSaveTextDocumentParams
    given inputReader: Reader[In] = 
      structures.WillSaveTextDocumentParams.reader
    given inputWriter: Writer[In] = 
      structures.WillSaveTextDocumentParams.writer
  
object window:
  object logMessage extends LSPNotification("window/logMessage"):
    type In = structures.LogMessageParams
    given inputReader: Reader[In] = 
      structures.LogMessageParams.reader
    given inputWriter: Writer[In] = 
      structures.LogMessageParams.writer
  
  object showMessage extends LSPNotification("window/showMessage"):
    type In = structures.ShowMessageParams
    given inputReader: Reader[In] = 
      structures.ShowMessageParams.reader
    given inputWriter: Writer[In] = 
      structures.ShowMessageParams.writer
  
  object workDoneProgress:
    object cancel extends LSPNotification("window/workDoneProgress/cancel"):
      type In = structures.WorkDoneProgressCancelParams
      given inputReader: Reader[In] = 
        structures.WorkDoneProgressCancelParams.reader
      given inputWriter: Writer[In] = 
        structures.WorkDoneProgressCancelParams.writer
    
object workspace:
  object didChangeConfiguration extends LSPNotification("workspace/didChangeConfiguration"):
    type In = structures.DidChangeConfigurationParams
    given inputReader: Reader[In] = 
      structures.DidChangeConfigurationParams.reader
    given inputWriter: Writer[In] = 
      structures.DidChangeConfigurationParams.writer
  
  object didChangeWatchedFiles extends LSPNotification("workspace/didChangeWatchedFiles"):
    type In = structures.DidChangeWatchedFilesParams
    given inputReader: Reader[In] = 
      structures.DidChangeWatchedFilesParams.reader
    given inputWriter: Writer[In] = 
      structures.DidChangeWatchedFilesParams.writer
  
  object didChangeWorkspaceFolders extends LSPNotification("workspace/didChangeWorkspaceFolders"):
    type In = structures.DidChangeWorkspaceFoldersParams
    given inputReader: Reader[In] = 
      structures.DidChangeWorkspaceFoldersParams.reader
    given inputWriter: Writer[In] = 
      structures.DidChangeWorkspaceFoldersParams.writer
  
  object didCreateFiles extends LSPNotification("workspace/didCreateFiles"):
    type In = structures.CreateFilesParams
    given inputReader: Reader[In] = 
      structures.CreateFilesParams.reader
    given inputWriter: Writer[In] = 
      structures.CreateFilesParams.writer
  
  object didDeleteFiles extends LSPNotification("workspace/didDeleteFiles"):
    type In = structures.DeleteFilesParams
    given inputReader: Reader[In] = 
      structures.DeleteFilesParams.reader
    given inputWriter: Writer[In] = 
      structures.DeleteFilesParams.writer
  
  object didRenameFiles extends LSPNotification("workspace/didRenameFiles"):
    type In = structures.RenameFilesParams
    given inputReader: Reader[In] = 
      structures.RenameFilesParams.reader
    given inputWriter: Writer[In] = 
      structures.RenameFilesParams.writer
  
