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

object $DOLLAR:
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
  
/**
 *  The exit event is sent from the client to the server to
 *  ask the server to exit its process.
 */
object exit extends LSPNotification("exit"):
  type In = Unit
  
  given inputReader: Reader[In] = 
    unitReader
  given inputWriter: Writer[In] = 
    unitWriter

/**
 *  The initialized notification is sent from the client to the
 *  server after the client is fully initialized and the server
 *  is allowed to send requests from the server to the client.
 */
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
  
  /**
   *  A notification sent when a notebook closes.
   *  
   *  @since 3.17.0
   */
  object didClose extends LSPNotification("notebookDocument/didClose"):
    type In = structures.DidCloseNotebookDocumentParams
    
    given inputReader: Reader[In] = 
      structures.DidCloseNotebookDocumentParams.reader
    given inputWriter: Writer[In] = 
      structures.DidCloseNotebookDocumentParams.writer
  
  /**
   *  A notification sent when a notebook opens.
   *  
   *  @since 3.17.0
   */
  object didOpen extends LSPNotification("notebookDocument/didOpen"):
    type In = structures.DidOpenNotebookDocumentParams
    
    given inputReader: Reader[In] = 
      structures.DidOpenNotebookDocumentParams.reader
    given inputWriter: Writer[In] = 
      structures.DidOpenNotebookDocumentParams.writer
  
  /**
   *  A notification sent when a notebook document is saved.
   *  
   *  @since 3.17.0
   */
  object didSave extends LSPNotification("notebookDocument/didSave"):
    type In = structures.DidSaveNotebookDocumentParams
    
    given inputReader: Reader[In] = 
      structures.DidSaveNotebookDocumentParams.reader
    given inputWriter: Writer[In] = 
      structures.DidSaveNotebookDocumentParams.writer
  
object telemetry:
  /**
   *  The telemetry event notification is sent from the server to the client to ask
   *  the client to log telemetry data.
   */
  object event extends LSPNotification("telemetry/event"):
    type In = ujson.Value
    
    given inputReader: Reader[In] = 
      jsReader
    given inputWriter: Writer[In] = 
      jsWriter
  
object textDocument:
  /**
   *  The document change notification is sent from the client to the server to signal
   *  changes to a text document.
   */
  object didChange extends LSPNotification("textDocument/didChange"):
    type In = structures.DidChangeTextDocumentParams
    
    given inputReader: Reader[In] = 
      structures.DidChangeTextDocumentParams.reader
    given inputWriter: Writer[In] = 
      structures.DidChangeTextDocumentParams.writer
  
  /**
   *  The document close notification is sent from the client to the server when
   *  the document got closed in the client. The document's truth now exists where
   *  the document's uri points to (e.g. if the document's uri is a file uri the
   *  truth now exists on disk). As with the open notification the close notification
   *  is about managing the document's content. Receiving a close notification
   *  doesn't mean that the document was open in an editor before. A close
   *  notification requires a previous open notification to be sent.
   */
  object didClose extends LSPNotification("textDocument/didClose"):
    type In = structures.DidCloseTextDocumentParams
    
    given inputReader: Reader[In] = 
      structures.DidCloseTextDocumentParams.reader
    given inputWriter: Writer[In] = 
      structures.DidCloseTextDocumentParams.writer
  
  /**
   *  The document open notification is sent from the client to the server to signal
   *  newly opened text documents. The document's truth is now managed by the client
   *  and the server must not try to read the document's truth using the document's
   *  uri. Open in this sense means it is managed by the client. It doesn't necessarily
   *  mean that its content is presented in an editor. An open notification must not
   *  be sent more than once without a corresponding close notification send before.
   *  This means open and close notification must be balanced and the max open count
   *  is one.
   */
  object didOpen extends LSPNotification("textDocument/didOpen"):
    type In = structures.DidOpenTextDocumentParams
    
    given inputReader: Reader[In] = 
      structures.DidOpenTextDocumentParams.reader
    given inputWriter: Writer[In] = 
      structures.DidOpenTextDocumentParams.writer
  
  /**
   *  The document save notification is sent from the client to the server when
   *  the document got saved in the client.
   */
  object didSave extends LSPNotification("textDocument/didSave"):
    type In = structures.DidSaveTextDocumentParams
    
    given inputReader: Reader[In] = 
      structures.DidSaveTextDocumentParams.reader
    given inputWriter: Writer[In] = 
      structures.DidSaveTextDocumentParams.writer
  
  /**
   *  Diagnostics notification are sent from the server to the client to signal
   *  results of validation runs.
   */
  object publishDiagnostics extends LSPNotification("textDocument/publishDiagnostics"):
    type In = structures.PublishDiagnosticsParams
    
    given inputReader: Reader[In] = 
      structures.PublishDiagnosticsParams.reader
    given inputWriter: Writer[In] = 
      structures.PublishDiagnosticsParams.writer
  
  /**
   *  A document will save notification is sent from the client to the server before
   *  the document is actually saved.
   */
  object willSave extends LSPNotification("textDocument/willSave"):
    type In = structures.WillSaveTextDocumentParams
    
    given inputReader: Reader[In] = 
      structures.WillSaveTextDocumentParams.reader
    given inputWriter: Writer[In] = 
      structures.WillSaveTextDocumentParams.writer
  
object window:
  /**
   *  The log message notification is sent from the server to the client to ask
   *  the client to log a particular message.
   */
  object logMessage extends LSPNotification("window/logMessage"):
    type In = structures.LogMessageParams
    
    given inputReader: Reader[In] = 
      structures.LogMessageParams.reader
    given inputWriter: Writer[In] = 
      structures.LogMessageParams.writer
  
  /**
   *  The show message notification is sent from a server to a client to ask
   *  the client to display a particular message in the user interface.
   */
  object showMessage extends LSPNotification("window/showMessage"):
    type In = structures.ShowMessageParams
    
    given inputReader: Reader[In] = 
      structures.ShowMessageParams.reader
    given inputWriter: Writer[In] = 
      structures.ShowMessageParams.writer
  
  object workDoneProgress:
    /**
     *  The `window/workDoneProgress/cancel` notification is sent from  the client to the server to cancel a progress
     *  initiated on the server side.
     */
    object cancel extends LSPNotification("window/workDoneProgress/cancel"):
      type In = structures.WorkDoneProgressCancelParams
      
      given inputReader: Reader[In] = 
        structures.WorkDoneProgressCancelParams.reader
      given inputWriter: Writer[In] = 
        structures.WorkDoneProgressCancelParams.writer
    
object workspace:
  /**
   *  The configuration change notification is sent from the client to the server
   *  when the client's configuration has changed. The notification contains
   *  the changed configuration as defined by the language client.
   */
  object didChangeConfiguration extends LSPNotification("workspace/didChangeConfiguration"):
    type In = structures.DidChangeConfigurationParams
    
    given inputReader: Reader[In] = 
      structures.DidChangeConfigurationParams.reader
    given inputWriter: Writer[In] = 
      structures.DidChangeConfigurationParams.writer
  
  /**
   *  The watched files notification is sent from the client to the server when
   *  the client detects changes to file watched by the language client.
   */
  object didChangeWatchedFiles extends LSPNotification("workspace/didChangeWatchedFiles"):
    type In = structures.DidChangeWatchedFilesParams
    
    given inputReader: Reader[In] = 
      structures.DidChangeWatchedFilesParams.reader
    given inputWriter: Writer[In] = 
      structures.DidChangeWatchedFilesParams.writer
  
  /**
   *  The `workspace/didChangeWorkspaceFolders` notification is sent from the client to the server when the workspace
   *  folder configuration changes.
   */
  object didChangeWorkspaceFolders extends LSPNotification("workspace/didChangeWorkspaceFolders"):
    type In = structures.DidChangeWorkspaceFoldersParams
    
    given inputReader: Reader[In] = 
      structures.DidChangeWorkspaceFoldersParams.reader
    given inputWriter: Writer[In] = 
      structures.DidChangeWorkspaceFoldersParams.writer
  
  /**
   *  The did create files notification is sent from the client to the server when
   *  files were created from within the client.
   *  
   *  @since 3.16.0
   */
  object didCreateFiles extends LSPNotification("workspace/didCreateFiles"):
    type In = structures.CreateFilesParams
    
    given inputReader: Reader[In] = 
      structures.CreateFilesParams.reader
    given inputWriter: Writer[In] = 
      structures.CreateFilesParams.writer
  
  /**
   *  The will delete files request is sent from the client to the server before files are actually
   *  deleted as long as the deletion is triggered from within the client.
   *  
   *  @since 3.16.0
   */
  object didDeleteFiles extends LSPNotification("workspace/didDeleteFiles"):
    type In = structures.DeleteFilesParams
    
    given inputReader: Reader[In] = 
      structures.DeleteFilesParams.reader
    given inputWriter: Writer[In] = 
      structures.DeleteFilesParams.writer
  
  /**
   *  The did rename files notification is sent from the client to the server when
   *  files were renamed from within the client.
   *  
   *  @since 3.16.0
   */
  object didRenameFiles extends LSPNotification("workspace/didRenameFiles"):
    type In = structures.RenameFilesParams
    
    given inputReader: Reader[In] = 
      structures.RenameFilesParams.reader
    given inputWriter: Writer[In] = 
      structures.RenameFilesParams.writer
  
