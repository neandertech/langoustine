package langoustine.lsp
package requests

import langoustine.*
import upickle.default.*
import langoustine.lsp.json.{*, given}
// format: off

sealed abstract class LSPRequest(val requestMethod: String):
  type In
  type Out

  given inputReader: Reader[In]
  given inputWriter: Writer[In]
  given outputWriter: Writer[Out]
  given outputReader: Reader[Out]

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
  
object callHierarchy:
  /**
   *  A request to resolve the incoming calls for a given `CallHierarchyItem`.
   *  
   *  @since 3.16.0
   */
  object incomingCalls extends LSPRequest("callHierarchy/incomingCalls"):
    type In = structures.CallHierarchyIncomingCallsParams
    type Out = Nullable[Vector[structures.CallHierarchyIncomingCall]]
    
    given inputReader: Reader[In] = 
      structures.CallHierarchyIncomingCallsParams.reader
    
    given inputWriter: Writer[In] = 
      structures.CallHierarchyIncomingCallsParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.CallHierarchyIncomingCall]](v.asInstanceOf[Vector[structures.CallHierarchyIncomingCall]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.CallHierarchyIncomingCall]], nullReadWriter)
  
  /**
   *  A request to resolve the outgoing calls for a given `CallHierarchyItem`.
   *  
   *  @since 3.16.0
   */
  object outgoingCalls extends LSPRequest("callHierarchy/outgoingCalls"):
    type In = structures.CallHierarchyOutgoingCallsParams
    type Out = Nullable[Vector[structures.CallHierarchyOutgoingCall]]
    
    given inputReader: Reader[In] = 
      structures.CallHierarchyOutgoingCallsParams.reader
    
    given inputWriter: Writer[In] = 
      structures.CallHierarchyOutgoingCallsParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.CallHierarchyOutgoingCall]](v.asInstanceOf[Vector[structures.CallHierarchyOutgoingCall]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.CallHierarchyOutgoingCall]], nullReadWriter)
  
object client:
  /**
   *  The `client/registerCapability` request is sent from the server to the client to register a new capability
   *  handler on the client side.
   */
  object registerCapability extends LSPRequest("client/registerCapability"):
    type In = structures.RegistrationParams
    type Out = Null
    
    given inputReader: Reader[In] = 
      structures.RegistrationParams.reader
    
    given inputWriter: Writer[In] = 
      structures.RegistrationParams.writer
    
    given outputWriter: Writer[Out] =
      nullReadWriter
    
    given outputReader: Reader[Out] =
      nullReadWriter
  
  /**
   *  The `client/unregisterCapability` request is sent from the server to the client to unregister a previously registered capability
   *  handler on the client side.
   */
  object unregisterCapability extends LSPRequest("client/unregisterCapability"):
    type In = structures.UnregistrationParams
    type Out = Null
    
    given inputReader: Reader[In] = 
      structures.UnregistrationParams.reader
    
    given inputWriter: Writer[In] = 
      structures.UnregistrationParams.writer
    
    given outputWriter: Writer[Out] =
      nullReadWriter
    
    given outputReader: Reader[Out] =
      nullReadWriter
  
object codeAction:
  /**
   *  Request to resolve additional information for a given code action.The request's
   *  parameter is of type [CodeAction](#CodeAction) the response
   *  is of type [CodeAction](#CodeAction) or a Thenable that resolves to such.
   */
  object resolve extends LSPRequest("codeAction/resolve"):
    type In = structures.CodeAction
    type Out = structures.CodeAction
    
    given inputReader: Reader[In] = 
      structures.CodeAction.reader
    
    given inputWriter: Writer[In] = 
      structures.CodeAction.writer
    
    given outputWriter: Writer[Out] =
      structures.CodeAction.writer
    
    given outputReader: Reader[Out] =
      structures.CodeAction.reader
  
object codeLens:
  /**
   *  A request to resolve a command for a given code lens.
   */
  object resolve extends LSPRequest("codeLens/resolve"):
    type In = structures.CodeLens
    type Out = structures.CodeLens
    
    given inputReader: Reader[In] = 
      structures.CodeLens.reader
    
    given inputWriter: Writer[In] = 
      structures.CodeLens.writer
    
    given outputWriter: Writer[Out] =
      structures.CodeLens.writer
    
    given outputReader: Reader[Out] =
      structures.CodeLens.reader
  
object completionItem:
  /**
   *  Request to resolve additional information for a given completion item.The request's
   *  parameter is of type [CompletionItem](#CompletionItem) the response
   *  is of type [CompletionItem](#CompletionItem) or a Thenable that resolves to such.
   */
  object resolve extends LSPRequest("completionItem/resolve"):
    type In = structures.CompletionItem
    type Out = structures.CompletionItem
    
    given inputReader: Reader[In] = 
      structures.CompletionItem.reader
    
    given inputWriter: Writer[In] = 
      structures.CompletionItem.writer
    
    given outputWriter: Writer[Out] =
      structures.CompletionItem.writer
    
    given outputReader: Reader[Out] =
      structures.CompletionItem.reader
  
object documentLink:
  /**
   *  Request to resolve additional information for a given document link. The request's
   *  parameter is of type [DocumentLink](#DocumentLink) the response
   *  is of type [DocumentLink](#DocumentLink) or a Thenable that resolves to such.
   */
  object resolve extends LSPRequest("documentLink/resolve"):
    type In = structures.DocumentLink
    type Out = structures.DocumentLink
    
    given inputReader: Reader[In] = 
      structures.DocumentLink.reader
    
    given inputWriter: Writer[In] = 
      structures.DocumentLink.writer
    
    given outputWriter: Writer[Out] =
      structures.DocumentLink.writer
    
    given outputReader: Reader[Out] =
      structures.DocumentLink.reader
  
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
 *  The initialize request is sent from the client to the server.
 *  It is sent once as the request after starting up the server.
 *  The requests parameter is of type [InitializeParams](#InitializeParams)
 *  the response if of type [InitializeResult](#InitializeResult) of a Thenable that
 *  resolves to such.
 */
object initialize extends LSPRequest("initialize"):
  type In = structures.InitializeParams
  type Out = structures.InitializeResult
  
  given inputReader: Reader[In] = 
    structures.InitializeParams.reader
  
  given inputWriter: Writer[In] = 
    structures.InitializeParams.writer
  
  given outputWriter: Writer[Out] =
    structures.InitializeResult.writer
  
  given outputReader: Reader[Out] =
    structures.InitializeResult.reader

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

object inlayHint:
  /**
   *  A request to resolve additional properties for an inlay hint.
   *  The request's parameter is of type [InlayHint](#InlayHint), the response is
   *  of type [InlayHint](#InlayHint) or a Thenable that resolves to such.
   *  
   *  @since 3.17.0
   */
  object resolve extends LSPRequest("inlayHint/resolve"):
    type In = structures.InlayHint
    type Out = structures.InlayHint
    
    given inputReader: Reader[In] = 
      structures.InlayHint.reader
    
    given inputWriter: Writer[In] = 
      structures.InlayHint.writer
    
    given outputWriter: Writer[Out] =
      structures.InlayHint.writer
    
    given outputReader: Reader[Out] =
      structures.InlayHint.reader
  
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
  
/**
 *  A shutdown request is sent from the client to the server.
 *  It is sent once when the client decides to shutdown the
 *  server. The only notification that is sent after a shutdown request
 *  is the exit event.
 */
object shutdown extends LSPRequest("shutdown"):
  type In = Unit
  type Out = Null
  
  given inputReader: Reader[In] = 
    unitReader
  
  given inputWriter: Writer[In] = 
    unitWriter
  
  given outputWriter: Writer[Out] =
    nullReadWriter
  
  given outputReader: Reader[Out] =
    nullReadWriter

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
   *  A request to provide commands for the given text document and range.
   */
  object codeAction extends LSPRequest("textDocument/codeAction"):
    type In = structures.CodeActionParams
    type Out = Nullable[Vector[(structures.Command | structures.CodeAction)]]
    
    given inputReader: Reader[In] = 
      structures.CodeActionParams.reader
    
    given inputWriter: Writer[In] = 
      structures.CodeActionParams.writer
    
    given outputWriter: Writer[Out] =
      given Writer[(structures.Command | structures.CodeAction)] = 
        upickle.default.writer[ujson.Value].comap { _v => 
          (_v: @unchecked) match 
            case v: structures.Command => writeJs[structures.Command](v)
            case v: structures.CodeAction => writeJs[structures.CodeAction](v)
        }
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[(structures.Command | structures.CodeAction)]](v.asInstanceOf[Vector[(structures.Command | structures.CodeAction)]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      given Reader[(structures.Command | structures.CodeAction)] = 
        badMerge[(structures.Command | structures.CodeAction)](structures.Command.reader, structures.CodeAction.reader)
      badMerge[Out](upickle.default.reader[Vector[(structures.Command | structures.CodeAction)]], nullReadWriter)
  
  /**
   *  A request to provide code lens for the given text document.
   */
  object codeLens extends LSPRequest("textDocument/codeLens"):
    type In = structures.CodeLensParams
    type Out = Nullable[Vector[structures.CodeLens]]
    
    given inputReader: Reader[In] = 
      structures.CodeLensParams.reader
    
    given inputWriter: Writer[In] = 
      structures.CodeLensParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.CodeLens]](v.asInstanceOf[Vector[structures.CodeLens]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.CodeLens]], nullReadWriter)
  
  /**
   *  A request to list all presentation for a color. The request's
   *  parameter is of type [ColorPresentationParams](#ColorPresentationParams) the
   *  response is of type [ColorInformation[]](#ColorInformation) or a Thenable
   *  that resolves to such.
   */
  object colorPresentation extends LSPRequest("textDocument/colorPresentation"):
    type In = structures.ColorPresentationParams
    type Out = Vector[structures.ColorPresentation]
    
    given inputReader: Reader[In] = 
      structures.ColorPresentationParams.reader
    
    given inputWriter: Writer[In] = 
      structures.ColorPresentationParams.writer
    
    given outputWriter: Writer[Out] =
      vectorWriter[structures.ColorPresentation]
    
    given outputReader: Reader[Out] =
      vectorReader[structures.ColorPresentation]
  
  /**
   *  Request to request completion at a given text document position. The request's
   *  parameter is of type [TextDocumentPosition](#TextDocumentPosition) the response
   *  is of type [CompletionItem[]](#CompletionItem) or [CompletionList](#CompletionList)
   *  or a Thenable that resolves to such.
   *  
   *  The request can delay the computation of the [`detail`](#CompletionItem.detail)
   *  and [`documentation`](#CompletionItem.documentation) properties to the `completionItem/resolve`
   *  request. However, properties that are needed for the initial sorting and filtering, like `sortText`,
   *  `filterText`, `insertText`, and `textEdit`, must not be changed during resolve.
   */
  object completion extends LSPRequest("textDocument/completion"):
    type In = structures.CompletionParams
    type Out = (Vector[structures.CompletionItem] | structures.CompletionList | Null)
    
    given inputReader: Reader[In] = 
      structures.CompletionParams.reader
    
    given inputWriter: Writer[In] = 
      structures.CompletionParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.CompletionItem]](v.asInstanceOf[Vector[structures.CompletionItem]])
          case v: structures.CompletionList => writeJs[structures.CompletionList](v)
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.CompletionItem]], structures.CompletionList.reader, nullReadWriter)
  
  /**
   *  A request to resolve the type definition locations of a symbol at a given text
   *  document position. The request's parameter is of type [TextDocumentPositionParams]
   *  (#TextDocumentPositionParams) the response is of type [Declaration](#Declaration)
   *  or a typed array of [DeclarationLink](#DeclarationLink) or a Thenable that resolves
   *  to such.
   */
  object declaration extends LSPRequest("textDocument/declaration"):
    type In = structures.DeclarationParams
    type Out = (aliases.Declaration | Vector[aliases.DeclarationLink] | Null)
    
    given inputReader: Reader[In] = 
      structures.DeclarationParams.reader
    
    given inputWriter: Writer[In] = 
      structures.DeclarationParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: aliases.Declaration => writeJs[aliases.Declaration](v)
          case v: Vector[?] => writeJs[Vector[aliases.DeclarationLink]](v.asInstanceOf[Vector[aliases.DeclarationLink]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](aliases.Declaration.reader, upickle.default.reader[Vector[aliases.DeclarationLink]], nullReadWriter)
  
  /**
   *  A request to resolve the definition location of a symbol at a given text
   *  document position. The request's parameter is of type [TextDocumentPosition]
   *  (#TextDocumentPosition) the response is of either type [Definition](#Definition)
   *  or a typed array of [DefinitionLink](#DefinitionLink) or a Thenable that resolves
   *  to such.
   */
  object definition extends LSPRequest("textDocument/definition"):
    type In = structures.DefinitionParams
    type Out = (aliases.Definition | Vector[aliases.DefinitionLink] | Null)
    
    given inputReader: Reader[In] = 
      structures.DefinitionParams.reader
    
    given inputWriter: Writer[In] = 
      structures.DefinitionParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: aliases.Definition => writeJs[aliases.Definition](v)
          case v: Vector[?] => writeJs[Vector[aliases.DefinitionLink]](v.asInstanceOf[Vector[aliases.DefinitionLink]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](aliases.Definition.reader, upickle.default.reader[Vector[aliases.DefinitionLink]], nullReadWriter)
  
  /**
   *  The document diagnostic request definition.
   *  
   *  @since 3.17.0
   */
  object diagnostic extends LSPRequest("textDocument/diagnostic"):
    type In = structures.DocumentDiagnosticParams
    type Out = aliases.DocumentDiagnosticReport
    
    given inputReader: Reader[In] = 
      structures.DocumentDiagnosticParams.reader
    
    given inputWriter: Writer[In] = 
      structures.DocumentDiagnosticParams.writer
    
    given outputWriter: Writer[Out] =
      aliases.DocumentDiagnosticReport.writer
    
    given outputReader: Reader[Out] =
      aliases.DocumentDiagnosticReport.reader
  
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
   *  A request to list all color symbols found in a given text document. The request's
   *  parameter is of type [DocumentColorParams](#DocumentColorParams) the
   *  response is of type [ColorInformation[]](#ColorInformation) or a Thenable
   *  that resolves to such.
   */
  object documentColor extends LSPRequest("textDocument/documentColor"):
    type In = structures.DocumentColorParams
    type Out = Vector[structures.ColorInformation]
    
    given inputReader: Reader[In] = 
      structures.DocumentColorParams.reader
    
    given inputWriter: Writer[In] = 
      structures.DocumentColorParams.writer
    
    given outputWriter: Writer[Out] =
      vectorWriter[structures.ColorInformation]
    
    given outputReader: Reader[Out] =
      vectorReader[structures.ColorInformation]
  
  /**
   *  Request to resolve a [DocumentHighlight](#DocumentHighlight) for a given
   *  text document position. The request's parameter is of type [TextDocumentPosition]
   *  (#TextDocumentPosition) the request response is of type [DocumentHighlight[]]
   *  (#DocumentHighlight) or a Thenable that resolves to such.
   */
  object documentHighlight extends LSPRequest("textDocument/documentHighlight"):
    type In = structures.DocumentHighlightParams
    type Out = Nullable[Vector[structures.DocumentHighlight]]
    
    given inputReader: Reader[In] = 
      structures.DocumentHighlightParams.reader
    
    given inputWriter: Writer[In] = 
      structures.DocumentHighlightParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.DocumentHighlight]](v.asInstanceOf[Vector[structures.DocumentHighlight]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.DocumentHighlight]], nullReadWriter)
  
  /**
   *  A request to provide document links
   */
  object documentLink extends LSPRequest("textDocument/documentLink"):
    type In = structures.DocumentLinkParams
    type Out = Nullable[Vector[structures.DocumentLink]]
    
    given inputReader: Reader[In] = 
      structures.DocumentLinkParams.reader
    
    given inputWriter: Writer[In] = 
      structures.DocumentLinkParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.DocumentLink]](v.asInstanceOf[Vector[structures.DocumentLink]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.DocumentLink]], nullReadWriter)
  
  /**
   *  A request to list all symbols found in a given text document. The request's
   *  parameter is of type [TextDocumentIdentifier](#TextDocumentIdentifier) the
   *  response is of type [SymbolInformation[]](#SymbolInformation) or a Thenable
   *  that resolves to such.
   */
  object documentSymbol extends LSPRequest("textDocument/documentSymbol"):
    type In = structures.DocumentSymbolParams
    type Out = (Vector[structures.SymbolInformation] | Vector[structures.DocumentSymbol] | Null)
    
    given inputReader: Reader[In] = 
      structures.DocumentSymbolParams.reader
    
    given inputWriter: Writer[In] = 
      structures.DocumentSymbolParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.SymbolInformation]](v.asInstanceOf[Vector[structures.SymbolInformation]])
          case v: Vector[?] => writeJs[Vector[structures.DocumentSymbol]](v.asInstanceOf[Vector[structures.DocumentSymbol]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.SymbolInformation]], upickle.default.reader[Vector[structures.DocumentSymbol]], nullReadWriter)
  
  /**
   *  A request to provide folding ranges in a document. The request's
   *  parameter is of type [FoldingRangeParams](#FoldingRangeParams), the
   *  response is of type [FoldingRangeList](#FoldingRangeList) or a Thenable
   *  that resolves to such.
   */
  object foldingRange extends LSPRequest("textDocument/foldingRange"):
    type In = structures.FoldingRangeParams
    type Out = Nullable[Vector[structures.FoldingRange]]
    
    given inputReader: Reader[In] = 
      structures.FoldingRangeParams.reader
    
    given inputWriter: Writer[In] = 
      structures.FoldingRangeParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.FoldingRange]](v.asInstanceOf[Vector[structures.FoldingRange]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.FoldingRange]], nullReadWriter)
  
  /**
   *  A request to to format a whole document.
   */
  object formatting extends LSPRequest("textDocument/formatting"):
    type In = structures.DocumentFormattingParams
    type Out = Nullable[Vector[structures.TextEdit]]
    
    given inputReader: Reader[In] = 
      structures.DocumentFormattingParams.reader
    
    given inputWriter: Writer[In] = 
      structures.DocumentFormattingParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.TextEdit]](v.asInstanceOf[Vector[structures.TextEdit]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.TextEdit]], nullReadWriter)
  
  /**
   *  Request to request hover information at a given text document position. The request's
   *  parameter is of type [TextDocumentPosition](#TextDocumentPosition) the response is of
   *  type [Hover](#Hover) or a Thenable that resolves to such.
   */
  object hover extends LSPRequest("textDocument/hover"):
    type In = structures.HoverParams
    type Out = Nullable[structures.Hover]
    
    given inputReader: Reader[In] = 
      structures.HoverParams.reader
    
    given inputWriter: Writer[In] = 
      structures.HoverParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: structures.Hover => writeJs[structures.Hover](v)
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](structures.Hover.reader, nullReadWriter)
  
  /**
   *  A request to resolve the implementation locations of a symbol at a given text
   *  document position. The request's parameter is of type [TextDocumentPositionParams]
   *  (#TextDocumentPositionParams) the response is of type [Definition](#Definition) or a
   *  Thenable that resolves to such.
   */
  object implementation extends LSPRequest("textDocument/implementation"):
    type In = structures.ImplementationParams
    type Out = (aliases.Definition | Vector[aliases.DefinitionLink] | Null)
    
    given inputReader: Reader[In] = 
      structures.ImplementationParams.reader
    
    given inputWriter: Writer[In] = 
      structures.ImplementationParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: aliases.Definition => writeJs[aliases.Definition](v)
          case v: Vector[?] => writeJs[Vector[aliases.DefinitionLink]](v.asInstanceOf[Vector[aliases.DefinitionLink]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](aliases.Definition.reader, upickle.default.reader[Vector[aliases.DefinitionLink]], nullReadWriter)
  
  /**
   *  A request to provide inlay hints in a document. The request's parameter is of
   *  type [InlayHintsParams](#InlayHintsParams), the response is of type
   *  [InlayHint[]](#InlayHint[]) or a Thenable that resolves to such.
   *  
   *  @since 3.17.0
   */
  object inlayHint extends LSPRequest("textDocument/inlayHint"):
    type In = structures.InlayHintParams
    type Out = Nullable[Vector[structures.InlayHint]]
    
    given inputReader: Reader[In] = 
      structures.InlayHintParams.reader
    
    given inputWriter: Writer[In] = 
      structures.InlayHintParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.InlayHint]](v.asInstanceOf[Vector[structures.InlayHint]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.InlayHint]], nullReadWriter)
  
  /**
   *  A request to provide inline values in a document. The request's parameter is of
   *  type [InlineValueParams](#InlineValueParams), the response is of type
   *  [InlineValue[]](#InlineValue[]) or a Thenable that resolves to such.
   *  
   *  @since 3.17.0
   */
  object inlineValue extends LSPRequest("textDocument/inlineValue"):
    type In = structures.InlineValueParams
    type Out = Nullable[Vector[aliases.InlineValue]]
    
    given inputReader: Reader[In] = 
      structures.InlineValueParams.reader
    
    given inputWriter: Writer[In] = 
      structures.InlineValueParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[aliases.InlineValue]](v.asInstanceOf[Vector[aliases.InlineValue]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[aliases.InlineValue]], nullReadWriter)
  
  /**
   *  A request to provide ranges that can be edited together.
   *  
   *  @since 3.16.0
   */
  object linkedEditingRange extends LSPRequest("textDocument/linkedEditingRange"):
    type In = structures.LinkedEditingRangeParams
    type Out = Nullable[structures.LinkedEditingRanges]
    
    given inputReader: Reader[In] = 
      structures.LinkedEditingRangeParams.reader
    
    given inputWriter: Writer[In] = 
      structures.LinkedEditingRangeParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: structures.LinkedEditingRanges => writeJs[structures.LinkedEditingRanges](v)
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](structures.LinkedEditingRanges.reader, nullReadWriter)
  
  /**
   *  A request to get the moniker of a symbol at a given text document position.
   *  The request parameter is of type [TextDocumentPositionParams](#TextDocumentPositionParams).
   *  The response is of type [Moniker[]](#Moniker[]) or `null`.
   */
  object moniker extends LSPRequest("textDocument/moniker"):
    type In = structures.MonikerParams
    type Out = Nullable[Vector[structures.Moniker]]
    
    given inputReader: Reader[In] = 
      structures.MonikerParams.reader
    
    given inputWriter: Writer[In] = 
      structures.MonikerParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.Moniker]](v.asInstanceOf[Vector[structures.Moniker]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.Moniker]], nullReadWriter)
  
  /**
   *  A request to format a document on type.
   */
  object onTypeFormatting extends LSPRequest("textDocument/onTypeFormatting"):
    type In = structures.DocumentOnTypeFormattingParams
    type Out = Nullable[Vector[structures.TextEdit]]
    
    given inputReader: Reader[In] = 
      structures.DocumentOnTypeFormattingParams.reader
    
    given inputWriter: Writer[In] = 
      structures.DocumentOnTypeFormattingParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.TextEdit]](v.asInstanceOf[Vector[structures.TextEdit]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.TextEdit]], nullReadWriter)
  
  /**
   *  A request to result a `CallHierarchyItem` in a document at a given position.
   *  Can be used as an input to an incoming or outgoing call hierarchy.
   *  
   *  @since 3.16.0
   */
  object prepareCallHierarchy extends LSPRequest("textDocument/prepareCallHierarchy"):
    type In = structures.CallHierarchyPrepareParams
    type Out = Nullable[Vector[structures.CallHierarchyItem]]
    
    given inputReader: Reader[In] = 
      structures.CallHierarchyPrepareParams.reader
    
    given inputWriter: Writer[In] = 
      structures.CallHierarchyPrepareParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.CallHierarchyItem]](v.asInstanceOf[Vector[structures.CallHierarchyItem]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.CallHierarchyItem]], nullReadWriter)
  
  /**
   *  A request to test and perform the setup necessary for a rename.
   *  
   *  @since 3.16 - support for default behavior
   */
  object prepareRename extends LSPRequest("textDocument/prepareRename"):
    type In = structures.PrepareRenameParams
    type Out = Nullable[aliases.PrepareRenameResult]
    
    given inputReader: Reader[In] = 
      structures.PrepareRenameParams.reader
    
    given inputWriter: Writer[In] = 
      structures.PrepareRenameParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: aliases.PrepareRenameResult => writeJs[aliases.PrepareRenameResult](v)
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](aliases.PrepareRenameResult.reader, nullReadWriter)
  
  /**
   *  A request to result a `TypeHierarchyItem` in a document at a given position.
   *  Can be used as an input to a subtypes or supertypes type hierarchy.
   *  
   *  @since 3.17.0
   */
  object prepareTypeHierarchy extends LSPRequest("textDocument/prepareTypeHierarchy"):
    type In = structures.TypeHierarchyPrepareParams
    type Out = Nullable[Vector[structures.TypeHierarchyItem]]
    
    given inputReader: Reader[In] = 
      structures.TypeHierarchyPrepareParams.reader
    
    given inputWriter: Writer[In] = 
      structures.TypeHierarchyPrepareParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.TypeHierarchyItem]](v.asInstanceOf[Vector[structures.TypeHierarchyItem]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.TypeHierarchyItem]], nullReadWriter)
  
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
   *  A request to to format a range in a document.
   */
  object rangeFormatting extends LSPRequest("textDocument/rangeFormatting"):
    type In = structures.DocumentRangeFormattingParams
    type Out = Nullable[Vector[structures.TextEdit]]
    
    given inputReader: Reader[In] = 
      structures.DocumentRangeFormattingParams.reader
    
    given inputWriter: Writer[In] = 
      structures.DocumentRangeFormattingParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.TextEdit]](v.asInstanceOf[Vector[structures.TextEdit]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.TextEdit]], nullReadWriter)
  
  /**
   *  A request to resolve project-wide references for the symbol denoted
   *  by the given text document position. The request's parameter is of
   *  type [ReferenceParams](#ReferenceParams) the response is of type
   *  [Location[]](#Location) or a Thenable that resolves to such.
   */
  object references extends LSPRequest("textDocument/references"):
    type In = structures.ReferenceParams
    type Out = Nullable[Vector[structures.Location]]
    
    given inputReader: Reader[In] = 
      structures.ReferenceParams.reader
    
    given inputWriter: Writer[In] = 
      structures.ReferenceParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.Location]](v.asInstanceOf[Vector[structures.Location]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.Location]], nullReadWriter)
  
  /**
   *  A request to rename a symbol.
   */
  object rename extends LSPRequest("textDocument/rename"):
    type In = structures.RenameParams
    type Out = Nullable[structures.WorkspaceEdit]
    
    given inputReader: Reader[In] = 
      structures.RenameParams.reader
    
    given inputWriter: Writer[In] = 
      structures.RenameParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: structures.WorkspaceEdit => writeJs[structures.WorkspaceEdit](v)
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](structures.WorkspaceEdit.reader, nullReadWriter)
  
  /**
   *  A request to provide selection ranges in a document. The request's
   *  parameter is of type [SelectionRangeParams](#SelectionRangeParams), the
   *  response is of type [SelectionRange[]](#SelectionRange[]) or a Thenable
   *  that resolves to such.
   */
  object selectionRange extends LSPRequest("textDocument/selectionRange"):
    type In = structures.SelectionRangeParams
    type Out = Nullable[Vector[structures.SelectionRange]]
    
    given inputReader: Reader[In] = 
      structures.SelectionRangeParams.reader
    
    given inputWriter: Writer[In] = 
      structures.SelectionRangeParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.SelectionRange]](v.asInstanceOf[Vector[structures.SelectionRange]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.SelectionRange]], nullReadWriter)
  
  object semanticTokens:
    /**
     *  @since 3.16.0
     */
    object full extends LSPRequest("textDocument/semanticTokens/full"):
      type In = structures.SemanticTokensParams
      type Out = Nullable[structures.SemanticTokens]
      
      given inputReader: Reader[In] = 
        structures.SemanticTokensParams.reader
      
      given inputWriter: Writer[In] = 
        structures.SemanticTokensParams.writer
      
      given outputWriter: Writer[Out] =
        upickle.default.writer[ujson.Value].comap[Out] { _v => 
          (_v: @unchecked) match 
            case v: structures.SemanticTokens => writeJs[structures.SemanticTokens](v)
            case a if a == Nullable.NULL => ujson.Null
        }
      
      given outputReader: Reader[Out] =
        badMerge[Out](structures.SemanticTokens.reader, nullReadWriter)
    
      /**
       *  @since 3.16.0
       */
      object delta extends LSPRequest("textDocument/semanticTokens/full/delta"):
        type In = structures.SemanticTokensDeltaParams
        type Out = (structures.SemanticTokens | structures.SemanticTokensDelta | Null)
        
        given inputReader: Reader[In] = 
          structures.SemanticTokensDeltaParams.reader
        
        given inputWriter: Writer[In] = 
          structures.SemanticTokensDeltaParams.writer
        
        given outputWriter: Writer[Out] =
          upickle.default.writer[ujson.Value].comap[Out] { _v => 
            (_v: @unchecked) match 
              case v: structures.SemanticTokens => writeJs[structures.SemanticTokens](v)
              case v: structures.SemanticTokensDelta => writeJs[structures.SemanticTokensDelta](v)
              case a if a == Nullable.NULL => ujson.Null
          }
        
        given outputReader: Reader[Out] =
          badMerge[Out](structures.SemanticTokens.reader, structures.SemanticTokensDelta.reader, nullReadWriter)
      
    /**
     *  @since 3.16.0
     */
    object range extends LSPRequest("textDocument/semanticTokens/range"):
      type In = structures.SemanticTokensRangeParams
      type Out = Nullable[structures.SemanticTokens]
      
      given inputReader: Reader[In] = 
        structures.SemanticTokensRangeParams.reader
      
      given inputWriter: Writer[In] = 
        structures.SemanticTokensRangeParams.writer
      
      given outputWriter: Writer[Out] =
        upickle.default.writer[ujson.Value].comap[Out] { _v => 
          (_v: @unchecked) match 
            case v: structures.SemanticTokens => writeJs[structures.SemanticTokens](v)
            case a if a == Nullable.NULL => ujson.Null
        }
      
      given outputReader: Reader[Out] =
        badMerge[Out](structures.SemanticTokens.reader, nullReadWriter)
    
  object signatureHelp extends LSPRequest("textDocument/signatureHelp"):
    type In = structures.SignatureHelpParams
    type Out = Nullable[structures.SignatureHelp]
    
    given inputReader: Reader[In] = 
      structures.SignatureHelpParams.reader
    
    given inputWriter: Writer[In] = 
      structures.SignatureHelpParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: structures.SignatureHelp => writeJs[structures.SignatureHelp](v)
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](structures.SignatureHelp.reader, nullReadWriter)
  
  /**
   *  A request to resolve the type definition locations of a symbol at a given text
   *  document position. The request's parameter is of type [TextDocumentPositionParams]
   *  (#TextDocumentPositionParams) the response is of type [Definition](#Definition) or a
   *  Thenable that resolves to such.
   */
  object typeDefinition extends LSPRequest("textDocument/typeDefinition"):
    type In = structures.TypeDefinitionParams
    type Out = (aliases.Definition | Vector[aliases.DefinitionLink] | Null)
    
    given inputReader: Reader[In] = 
      structures.TypeDefinitionParams.reader
    
    given inputWriter: Writer[In] = 
      structures.TypeDefinitionParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: aliases.Definition => writeJs[aliases.Definition](v)
          case v: Vector[?] => writeJs[Vector[aliases.DefinitionLink]](v.asInstanceOf[Vector[aliases.DefinitionLink]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](aliases.Definition.reader, upickle.default.reader[Vector[aliases.DefinitionLink]], nullReadWriter)
  
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
  
  /**
   *  A document will save request is sent from the client to the server before
   *  the document is actually saved. The request can return an array of TextEdits
   *  which will be applied to the text document before it is saved. Please note that
   *  clients might drop results if computing the text edits took too long or if a
   *  server constantly fails on this request. This is done to keep the save fast and
   *  reliable.
   */
  object willSaveWaitUntil extends LSPRequest("textDocument/willSaveWaitUntil"):
    type In = structures.WillSaveTextDocumentParams
    type Out = Nullable[Vector[structures.TextEdit]]
    
    given inputReader: Reader[In] = 
      structures.WillSaveTextDocumentParams.reader
    
    given inputWriter: Writer[In] = 
      structures.WillSaveTextDocumentParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.TextEdit]](v.asInstanceOf[Vector[structures.TextEdit]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.TextEdit]], nullReadWriter)
  
object typeHierarchy:
  /**
   *  A request to resolve the subtypes for a given `TypeHierarchyItem`.
   *  
   *  @since 3.17.0
   */
  object subtypes extends LSPRequest("typeHierarchy/subtypes"):
    type In = structures.TypeHierarchySubtypesParams
    type Out = Nullable[Vector[structures.TypeHierarchyItem]]
    
    given inputReader: Reader[In] = 
      structures.TypeHierarchySubtypesParams.reader
    
    given inputWriter: Writer[In] = 
      structures.TypeHierarchySubtypesParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.TypeHierarchyItem]](v.asInstanceOf[Vector[structures.TypeHierarchyItem]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.TypeHierarchyItem]], nullReadWriter)
  
  /**
   *  A request to resolve the supertypes for a given `TypeHierarchyItem`.
   *  
   *  @since 3.17.0
   */
  object supertypes extends LSPRequest("typeHierarchy/supertypes"):
    type In = structures.TypeHierarchySupertypesParams
    type Out = Nullable[Vector[structures.TypeHierarchyItem]]
    
    given inputReader: Reader[In] = 
      structures.TypeHierarchySupertypesParams.reader
    
    given inputWriter: Writer[In] = 
      structures.TypeHierarchySupertypesParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.TypeHierarchyItem]](v.asInstanceOf[Vector[structures.TypeHierarchyItem]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.TypeHierarchyItem]], nullReadWriter)
  
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
   *  A request to show a document. This request might open an
   *  external program depending on the value of the URI to open.
   *  For example a request to open `https://code.visualstudio.com/`
   *  will very likely open the URI in a WEB browser.
   *  
   *  @since 3.16.0
   */
  object showDocument extends LSPRequest("window/showDocument"):
    type In = structures.ShowDocumentParams
    type Out = structures.ShowDocumentResult
    
    given inputReader: Reader[In] = 
      structures.ShowDocumentParams.reader
    
    given inputWriter: Writer[In] = 
      structures.ShowDocumentParams.writer
    
    given outputWriter: Writer[Out] =
      structures.ShowDocumentResult.writer
    
    given outputReader: Reader[Out] =
      structures.ShowDocumentResult.reader
  
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
  
  /**
   *  The show message request is sent from the server to the client to show a message
   *  and a set of options actions to the user.
   */
  object showMessageRequest extends LSPRequest("window/showMessageRequest"):
    type In = structures.ShowMessageRequestParams
    type Out = Nullable[structures.MessageActionItem]
    
    given inputReader: Reader[In] = 
      structures.ShowMessageRequestParams.reader
    
    given inputWriter: Writer[In] = 
      structures.ShowMessageRequestParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: structures.MessageActionItem => writeJs[structures.MessageActionItem](v)
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](structures.MessageActionItem.reader, nullReadWriter)
  
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
    
    /**
     *  The `window/workDoneProgress/create` request is sent from the server to the client to initiate progress
     *  reporting from the server.
     */
    object create extends LSPRequest("window/workDoneProgress/create"):
      type In = structures.WorkDoneProgressCreateParams
      type Out = Null
      
      given inputReader: Reader[In] = 
        structures.WorkDoneProgressCreateParams.reader
      
      given inputWriter: Writer[In] = 
        structures.WorkDoneProgressCreateParams.writer
      
      given outputWriter: Writer[Out] =
        nullReadWriter
      
      given outputReader: Reader[Out] =
        nullReadWriter
    
object workspace:
  /**
   *  A request sent from the server to the client to modified certain resources.
   */
  object applyEdit extends LSPRequest("workspace/applyEdit"):
    type In = structures.ApplyWorkspaceEditParams
    type Out = structures.ApplyWorkspaceEditResult
    
    given inputReader: Reader[In] = 
      structures.ApplyWorkspaceEditParams.reader
    
    given inputWriter: Writer[In] = 
      structures.ApplyWorkspaceEditParams.writer
    
    given outputWriter: Writer[Out] =
      structures.ApplyWorkspaceEditResult.writer
    
    given outputReader: Reader[Out] =
      structures.ApplyWorkspaceEditResult.reader
  
  object codeLens:
    /**
     *  A request to refresh all code actions
     *  
     *  @since 3.16.0
     */
    object refresh extends LSPRequest("workspace/codeLens/refresh"):
      type In = Unit
      type Out = Null
      
      given inputReader: Reader[In] = 
        unitReader
      
      given inputWriter: Writer[In] = 
        unitWriter
      
      given outputWriter: Writer[Out] =
        nullReadWriter
      
      given outputReader: Reader[Out] =
        nullReadWriter
    
  /**
   *  The 'workspace/configuration' request is sent from the server to the client to fetch a certain
   *  configuration setting.
   *  
   *  This pull model replaces the old push model were the client signaled configuration change via an
   *  event. If the server still needs to react to configuration changes (since the server caches the
   *  result of `workspace/configuration` requests) the server should register for an empty configuration
   *  change event and empty the cache if such an event is received.
   */
  object configuration extends LSPRequest("workspace/configuration"):
    type In = Any /*AndType(Vector(ReferenceType(ConfigurationParams), ReferenceType(PartialResultParams)))*/
    type Out = Vector[ujson.Value]
    
    given inputReader: Reader[In] = 
      ??? /* TODO: AndType(Vector(ReferenceType(ConfigurationParams), ReferenceType(PartialResultParams)))  */
    
    given inputWriter: Writer[In] = 
      ???
    
    given outputWriter: Writer[Out] =
      vectorWriter[ujson.Value]
    
    given outputReader: Reader[Out] =
      vectorReader[ujson.Value]
  
  /**
   *  The workspace diagnostic request definition.
   *  
   *  @since 3.17.0
   */
  object diagnostic extends LSPRequest("workspace/diagnostic"):
    type In = structures.WorkspaceDiagnosticParams
    type Out = structures.WorkspaceDiagnosticReport
    
    given inputReader: Reader[In] = 
      structures.WorkspaceDiagnosticParams.reader
    
    given inputWriter: Writer[In] = 
      structures.WorkspaceDiagnosticParams.writer
    
    given outputWriter: Writer[Out] =
      structures.WorkspaceDiagnosticReport.writer
    
    given outputReader: Reader[Out] =
      structures.WorkspaceDiagnosticReport.reader
  
    /**
     *  The diagnostic refresh request definition.
     *  
     *  @since 3.17.0
     */
    object refresh extends LSPRequest("workspace/diagnostic/refresh"):
      type In = Unit
      type Out = Null
      
      given inputReader: Reader[In] = 
        unitReader
      
      given inputWriter: Writer[In] = 
        unitWriter
      
      given outputWriter: Writer[Out] =
        nullReadWriter
      
      given outputReader: Reader[Out] =
        nullReadWriter
    
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
  
  /**
   *  A request send from the client to the server to execute a command. The request might return
   *  a workspace edit which the client will apply to the workspace.
   */
  object executeCommand extends LSPRequest("workspace/executeCommand"):
    type In = structures.ExecuteCommandParams
    type Out = Nullable[ujson.Value]
    
    given inputReader: Reader[In] = 
      structures.ExecuteCommandParams.reader
    
    given inputWriter: Writer[In] = 
      structures.ExecuteCommandParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: ujson.Value => writeJs[ujson.Value](v)
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](jsReader, nullReadWriter)
  
  object inlayHint:
    /**
     *  @since 3.17.0
     */
    object refresh extends LSPRequest("workspace/inlayHint/refresh"):
      type In = Unit
      type Out = Null
      
      given inputReader: Reader[In] = 
        unitReader
      
      given inputWriter: Writer[In] = 
        unitWriter
      
      given outputWriter: Writer[Out] =
        nullReadWriter
      
      given outputReader: Reader[Out] =
        nullReadWriter
    
  object inlineValue:
    /**
     *  @since 3.17.0
     */
    object refresh extends LSPRequest("workspace/inlineValue/refresh"):
      type In = Unit
      type Out = Null
      
      given inputReader: Reader[In] = 
        unitReader
      
      given inputWriter: Writer[In] = 
        unitWriter
      
      given outputWriter: Writer[Out] =
        nullReadWriter
      
      given outputReader: Reader[Out] =
        nullReadWriter
    
  object semanticTokens:
    /**
     *  @since 3.16.0
     */
    object refresh extends LSPRequest("workspace/semanticTokens/refresh"):
      type In = Unit
      type Out = Null
      
      given inputReader: Reader[In] = 
        unitReader
      
      given inputWriter: Writer[In] = 
        unitWriter
      
      given outputWriter: Writer[Out] =
        nullReadWriter
      
      given outputReader: Reader[Out] =
        nullReadWriter
    
  /**
   *  A request to list project-wide symbols matching the query string given
   *  by the [WorkspaceSymbolParams](#WorkspaceSymbolParams). The response is
   *  of type [SymbolInformation[]](#SymbolInformation) or a Thenable that
   *  resolves to such.
   *  
   *  @since 3.17.0 - support for WorkspaceSymbol in the returned data. Clients
   *   need to advertise support for WorkspaceSymbols via the client capability
   *   `workspace.symbol.resolveSupport`.
   */
  object symbol extends LSPRequest("workspace/symbol"):
    type In = structures.WorkspaceSymbolParams
    type Out = (Vector[structures.SymbolInformation] | Vector[structures.WorkspaceSymbol] | Null)
    
    given inputReader: Reader[In] = 
      structures.WorkspaceSymbolParams.reader
    
    given inputWriter: Writer[In] = 
      structures.WorkspaceSymbolParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.SymbolInformation]](v.asInstanceOf[Vector[structures.SymbolInformation]])
          case v: Vector[?] => writeJs[Vector[structures.WorkspaceSymbol]](v.asInstanceOf[Vector[structures.WorkspaceSymbol]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.SymbolInformation]], upickle.default.reader[Vector[structures.WorkspaceSymbol]], nullReadWriter)
  
  /**
   *  The will create files request is sent from the client to the server before files are actually
   *  created as long as the creation is triggered from within the client.
   *  
   *  @since 3.16.0
   */
  object willCreateFiles extends LSPRequest("workspace/willCreateFiles"):
    type In = structures.CreateFilesParams
    type Out = Nullable[structures.WorkspaceEdit]
    
    given inputReader: Reader[In] = 
      structures.CreateFilesParams.reader
    
    given inputWriter: Writer[In] = 
      structures.CreateFilesParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: structures.WorkspaceEdit => writeJs[structures.WorkspaceEdit](v)
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](structures.WorkspaceEdit.reader, nullReadWriter)
  
  /**
   *  The did delete files notification is sent from the client to the server when
   *  files were deleted from within the client.
   *  
   *  @since 3.16.0
   */
  object willDeleteFiles extends LSPRequest("workspace/willDeleteFiles"):
    type In = structures.DeleteFilesParams
    type Out = Nullable[structures.WorkspaceEdit]
    
    given inputReader: Reader[In] = 
      structures.DeleteFilesParams.reader
    
    given inputWriter: Writer[In] = 
      structures.DeleteFilesParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: structures.WorkspaceEdit => writeJs[structures.WorkspaceEdit](v)
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](structures.WorkspaceEdit.reader, nullReadWriter)
  
  /**
   *  The will rename files request is sent from the client to the server before files are actually
   *  renamed as long as the rename is triggered from within the client.
   *  
   *  @since 3.16.0
   */
  object willRenameFiles extends LSPRequest("workspace/willRenameFiles"):
    type In = structures.RenameFilesParams
    type Out = Nullable[structures.WorkspaceEdit]
    
    given inputReader: Reader[In] = 
      structures.RenameFilesParams.reader
    
    given inputWriter: Writer[In] = 
      structures.RenameFilesParams.writer
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: structures.WorkspaceEdit => writeJs[structures.WorkspaceEdit](v)
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](structures.WorkspaceEdit.reader, nullReadWriter)
  
  /**
   *  The `workspace/workspaceFolders` is sent from the server to the client to fetch the open workspace folders.
   */
  object workspaceFolders extends LSPRequest("workspace/workspaceFolders"):
    type In = Unit
    type Out = Nullable[Vector[structures.WorkspaceFolder]]
    
    given inputReader: Reader[In] = 
      unitReader
    
    given inputWriter: Writer[In] = 
      unitWriter
    
    given outputWriter: Writer[Out] =
      upickle.default.writer[ujson.Value].comap[Out] { _v => 
        (_v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.WorkspaceFolder]](v.asInstanceOf[Vector[structures.WorkspaceFolder]])
          case a if a == Nullable.NULL => ujson.Null
      }
    
    given outputReader: Reader[Out] =
      badMerge[Out](upickle.default.reader[Vector[structures.WorkspaceFolder]], nullReadWriter)
  
object workspaceSymbol:
  /**
   *  A request to resolve the range inside the workspace
   *  symbol's location.
   *  
   *  @since 3.17.0
   */
  object resolve extends LSPRequest("workspaceSymbol/resolve"):
    type In = structures.WorkspaceSymbol
    type Out = structures.WorkspaceSymbol
    
    given inputReader: Reader[In] = 
      structures.WorkspaceSymbol.reader
    
    given inputWriter: Writer[In] = 
      structures.WorkspaceSymbol.writer
    
    given outputWriter: Writer[Out] =
      structures.WorkspaceSymbol.writer
    
    given outputReader: Reader[Out] =
      structures.WorkspaceSymbol.reader
  
