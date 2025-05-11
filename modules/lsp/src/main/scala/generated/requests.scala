/*
 * Copyright 2022 Neandertech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package langoustine.lsp
package requests

import langoustine.*
import upickle.default.*

import runtime.*

sealed abstract class LSPRequest(val requestMethod: String):
  type In
  type Out

  given inputReader: Reader[In]
  given inputWriter: Writer[In]
  given outputWriter: Writer[Out]
  given outputReader: Reader[Out]

  def apply(in: In): PreparedRequest[this.type] = PreparedRequest(this, in)
end LSPRequest

abstract class CustomRequest[I, O](method: String)(using
    ir: ReadWriter[I],
    or: ReadWriter[O]
) extends LSPRequest(method):
  override type In  = I
  override type Out = O

  override given inputReader: Reader[In] = ir

  override given inputWriter: Writer[In] = ir

  override given outputWriter: Writer[Out] = or

  override given outputReader: Reader[Out] = or
end CustomRequest

abstract class CustomNotification[I](method: String)(using ir: ReadWriter[I])
    extends LSPNotification(method):
  override type In = I

  override given inputReader: Reader[In] = ir

  override given inputWriter: Writer[In] = ir

sealed abstract class LSPNotification(val notificationMethod: String):
  type In

  given inputReader: Reader[In]
  given inputWriter: Writer[In]

  def apply(in: In): PreparedNotification[this.type] =
    PreparedNotification(this, in)

object $DOLLAR:
  object cancelRequest
      extends LSPNotification("$/cancelRequest")
      with codecs.notifications_$_cancelRequest:
    type In = structures.CancelParams

    override def apply(
        in: structures.CancelParams
    ): PreparedNotification[this.type] = super.apply(in)

  object logTrace
      extends LSPNotification("$/logTrace")
      with codecs.notifications_$_logTrace:
    type In = structures.LogTraceParams

    override def apply(
        in: structures.LogTraceParams
    ): PreparedNotification[this.type] = super.apply(in)

  object progress
      extends LSPNotification("$/progress")
      with codecs.notifications_$_progress:
    type In = structures.ProgressParams

    override def apply(
        in: structures.ProgressParams
    ): PreparedNotification[this.type] = super.apply(in)

  object setTrace
      extends LSPNotification("$/setTrace")
      with codecs.notifications_$_setTrace:
    type In = structures.SetTraceParams

    override def apply(
        in: structures.SetTraceParams
    ): PreparedNotification[this.type] = super.apply(in)
end $DOLLAR

object callHierarchy:
  /** A request to resolve the incoming calls for a given `CallHierarchyItem`.
    *
    * since 3.16.0
    */
  object incomingCalls
      extends LSPRequest("callHierarchy/incomingCalls")
      with codecs.requests_callHierarchy_incomingCalls:
    type In  = structures.CallHierarchyIncomingCallsParams
    type Out = Opt[Vector[structures.CallHierarchyIncomingCall]]

    override def apply(
        in: structures.CallHierarchyIncomingCallsParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to resolve the outgoing calls for a given `CallHierarchyItem`.
    *
    * since 3.16.0
    */
  object outgoingCalls
      extends LSPRequest("callHierarchy/outgoingCalls")
      with codecs.requests_callHierarchy_outgoingCalls:
    type In  = structures.CallHierarchyOutgoingCallsParams
    type Out = Opt[Vector[structures.CallHierarchyOutgoingCall]]

    override def apply(
        in: structures.CallHierarchyOutgoingCallsParams
    ): PreparedRequest[this.type] = super.apply(in)
end callHierarchy

object client:
  /** The `client/registerCapability` request is sent from the server to the
    * client to register a new capability handler on the client side.
    */
  object registerCapability
      extends LSPRequest("client/registerCapability")
      with codecs.requests_client_registerCapability:
    type In  = structures.RegistrationParams
    type Out = Null

    override def apply(
        in: structures.RegistrationParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** The `client/unregisterCapability` request is sent from the server to the
    * client to unregister a previously registered capability handler on the
    * client side.
    */
  object unregisterCapability
      extends LSPRequest("client/unregisterCapability")
      with codecs.requests_client_unregisterCapability:
    type In  = structures.UnregistrationParams
    type Out = Null

    override def apply(
        in: structures.UnregistrationParams
    ): PreparedRequest[this.type] = super.apply(in)
end client

object codeAction:
  /** Request to resolve additional information for a given code action.The
    * request's parameter is of type {@link CodeAction} the response is of type
    * {@link CodeAction} or a Thenable that resolves to such.
    */
  object resolve
      extends LSPRequest("codeAction/resolve")
      with codecs.requests_codeAction_resolve:
    type In  = structures.CodeAction
    type Out = structures.CodeAction

    override def apply(in: structures.CodeAction): PreparedRequest[this.type] =
      super.apply(in)
end codeAction

object codeLens:
  /** A request to resolve a command for a given code lens.
    */
  object resolve
      extends LSPRequest("codeLens/resolve")
      with codecs.requests_codeLens_resolve:
    type In  = structures.CodeLens
    type Out = structures.CodeLens

    override def apply(in: structures.CodeLens): PreparedRequest[this.type] =
      super.apply(in)
end codeLens

object completionItem:
  /** Request to resolve additional information for a given completion item.The
    * request's parameter is of type {@link CompletionItem} the response is of
    * type {@link CompletionItem} or a Thenable that resolves to such.
    */
  object resolve
      extends LSPRequest("completionItem/resolve")
      with codecs.requests_completionItem_resolve:
    type In  = structures.CompletionItem
    type Out = structures.CompletionItem

    override def apply(
        in: structures.CompletionItem
    ): PreparedRequest[this.type] = super.apply(in)
end completionItem

object documentLink:
  /** Request to resolve additional information for a given document link. The
    * request's parameter is of type {@link DocumentLink} the response is of
    * type {@link DocumentLink} or a Thenable that resolves to such.
    */
  object resolve
      extends LSPRequest("documentLink/resolve")
      with codecs.requests_documentLink_resolve:
    type In  = structures.DocumentLink
    type Out = structures.DocumentLink

    override def apply(
        in: structures.DocumentLink
    ): PreparedRequest[this.type] = super.apply(in)
end documentLink

/** The exit event is sent from the client to the server to ask the server to
  * exit its process.
  */
object exit extends LSPNotification("exit") with codecs.notifications_exit:
  type In = Unit

  override def apply(in: Unit): PreparedNotification[this.type] =
    super.apply(in)

/** The initialize request is sent from the client to the server. It is sent
  * once as the request after starting up the server. The requests parameter is
  * of type {@link InitializeParams} the response if of type
  * {@link InitializeResult} of a Thenable that resolves to such.
  */
object initialize
    extends LSPRequest("initialize")
    with codecs.requests_initialize:
  type In  = structures.InitializeParams
  type Out = structures.InitializeResult

  override def apply(
      in: structures.InitializeParams
  ): PreparedRequest[this.type] = super.apply(in)

/** The initialized notification is sent from the client to the server after the
  * client is fully initialized and the server is allowed to send requests from
  * the server to the client.
  */
object initialized
    extends LSPNotification("initialized")
    with codecs.notifications_initialized:
  type In = structures.InitializedParams

  override def apply(
      in: structures.InitializedParams
  ): PreparedNotification[this.type] = super.apply(in)

object inlayHint:
  /** A request to resolve additional properties for an inlay hint. The
    * request's parameter is of type {@link InlayHint}, the response is of type
    * {@link InlayHint} or a Thenable that resolves to such.
    *
    * since 3.17.0
    */
  object resolve
      extends LSPRequest("inlayHint/resolve")
      with codecs.requests_inlayHint_resolve:
    type In  = structures.InlayHint
    type Out = structures.InlayHint

    override def apply(in: structures.InlayHint): PreparedRequest[this.type] =
      super.apply(in)
end inlayHint

object notebookDocument:
  object didChange
      extends LSPNotification("notebookDocument/didChange")
      with codecs.notifications_notebookDocument_didChange:
    type In = structures.DidChangeNotebookDocumentParams

    override def apply(
        in: structures.DidChangeNotebookDocumentParams
    ): PreparedNotification[this.type] = super.apply(in)

  /** A notification sent when a notebook closes.
    *
    * @since 3.17.0
    */
  object didClose
      extends LSPNotification("notebookDocument/didClose")
      with codecs.notifications_notebookDocument_didClose:
    type In = structures.DidCloseNotebookDocumentParams

    override def apply(
        in: structures.DidCloseNotebookDocumentParams
    ): PreparedNotification[this.type] = super.apply(in)

  /** A notification sent when a notebook opens.
    *
    * @since 3.17.0
    */
  object didOpen
      extends LSPNotification("notebookDocument/didOpen")
      with codecs.notifications_notebookDocument_didOpen:
    type In = structures.DidOpenNotebookDocumentParams

    override def apply(
        in: structures.DidOpenNotebookDocumentParams
    ): PreparedNotification[this.type] = super.apply(in)

  /** A notification sent when a notebook document is saved.
    *
    * @since 3.17.0
    */
  object didSave
      extends LSPNotification("notebookDocument/didSave")
      with codecs.notifications_notebookDocument_didSave:
    type In = structures.DidSaveNotebookDocumentParams

    override def apply(
        in: structures.DidSaveNotebookDocumentParams
    ): PreparedNotification[this.type] = super.apply(in)
end notebookDocument

/** A shutdown request is sent from the client to the server. It is sent once
  * when the client decides to shutdown the server. The only notification that
  * is sent after a shutdown request is the exit event.
  */
object shutdown extends LSPRequest("shutdown") with codecs.requests_shutdown:
  type In  = Unit
  type Out = Null

  override def apply(in: Unit): PreparedRequest[this.type] = super.apply(in)

object telemetry:
  /** The telemetry event notification is sent from the server to the client to
    * ask the client to log telemetry data.
    */
  object event
      extends LSPNotification("telemetry/event")
      with codecs.notifications_telemetry_event:
    type In = ujson.Value

    override def apply(in: ujson.Value): PreparedNotification[this.type] =
      super.apply(in)
end telemetry

object textDocument:
  /** A request to provide commands for the given text document and range.
    */
  object codeAction
      extends LSPRequest("textDocument/codeAction")
      with codecs.requests_textDocument_codeAction:
    type In  = structures.CodeActionParams
    type Out = Opt[Vector[(structures.Command | structures.CodeAction)]]

    override def apply(
        in: structures.CodeActionParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to provide code lens for the given text document.
    */
  object codeLens
      extends LSPRequest("textDocument/codeLens")
      with codecs.requests_textDocument_codeLens:
    type In  = structures.CodeLensParams
    type Out = Opt[Vector[structures.CodeLens]]

    override def apply(
        in: structures.CodeLensParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to list all presentation for a color. The request's parameter is
    * of type {@link ColorPresentationParams} the response is of type
    * {@link ColorInformation ColorInformation[]} or a Thenable that resolves to
    * such.
    */
  object colorPresentation
      extends LSPRequest("textDocument/colorPresentation")
      with codecs.requests_textDocument_colorPresentation:
    type In  = structures.ColorPresentationParams
    type Out = Vector[structures.ColorPresentation]

    override def apply(
        in: structures.ColorPresentationParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** Request to request completion at a given text document position. The
    * request's parameter is of type {@link TextDocumentPosition} the response
    * is of type {@link CompletionItem CompletionItem[]} or
    * {@link CompletionList} or a Thenable that resolves to such.
    *
    * The request can delay the computation of the
    * {@link CompletionItem.detail `detail`} and
    * {@link CompletionItem.documentation `documentation`} properties to the
    * `completionItem/resolve` request. However, properties that are needed for
    * the initial sorting and filtering, like `sortText`, `filterText`,
    * `insertText`, and `textEdit`, must not be changed during resolve.
    */
  object completion
      extends LSPRequest("textDocument/completion")
      with codecs.requests_textDocument_completion:
    type In = structures.CompletionParams
    type Out =
      Opt[(Vector[structures.CompletionItem] | structures.CompletionList)]

    override def apply(
        in: structures.CompletionParams
    ): PreparedRequest[this.type] = super.apply(in)
  end completion

  /** A request to resolve the type definition locations of a symbol at a given
    * text document position. The request's parameter is of type
    * {@link TextDocumentPositionParams} the response is of type
    * {@link Declaration} or a typed array of {@link DeclarationLink} or a
    * Thenable that resolves to such.
    */
  object declaration
      extends LSPRequest("textDocument/declaration")
      with codecs.requests_textDocument_declaration:
    type In  = structures.DeclarationParams
    type Out = Opt[(aliases.Declaration | Vector[aliases.DeclarationLink])]

    override def apply(
        in: structures.DeclarationParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to resolve the definition location of a symbol at a given text
    * document position. The request's parameter is of type
    * {@link TextDocumentPosition} the response is of either type
    * {@link Definition} or a typed array of {@link DefinitionLink} or a
    * Thenable that resolves to such.
    */
  object definition
      extends LSPRequest("textDocument/definition")
      with codecs.requests_textDocument_definition:
    type In  = structures.DefinitionParams
    type Out = Opt[(aliases.Definition | Vector[aliases.DefinitionLink])]

    override def apply(
        in: structures.DefinitionParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** The document diagnostic request definition.
    *
    * since 3.17.0
    */
  object diagnostic
      extends LSPRequest("textDocument/diagnostic")
      with codecs.requests_textDocument_diagnostic:
    type In  = structures.DocumentDiagnosticParams
    type Out = aliases.DocumentDiagnosticReport

    override def apply(
        in: structures.DocumentDiagnosticParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** The document change notification is sent from the client to the server to
    * signal changes to a text document.
    */
  object didChange
      extends LSPNotification("textDocument/didChange")
      with codecs.notifications_textDocument_didChange:
    type In = structures.DidChangeTextDocumentParams

    override def apply(
        in: structures.DidChangeTextDocumentParams
    ): PreparedNotification[this.type] = super.apply(in)

  /** The document close notification is sent from the client to the server when
    * the document got closed in the client. The document's truth now exists
    * where the document's uri points to (e.g. if the document's uri is a file
    * uri the truth now exists on disk). As with the open notification the close
    * notification is about managing the document's content. Receiving a close
    * notification doesn't mean that the document was open in an editor before.
    * A close notification requires a previous open notification to be sent.
    */
  object didClose
      extends LSPNotification("textDocument/didClose")
      with codecs.notifications_textDocument_didClose:
    type In = structures.DidCloseTextDocumentParams

    override def apply(
        in: structures.DidCloseTextDocumentParams
    ): PreparedNotification[this.type] = super.apply(in)

  /** The document open notification is sent from the client to the server to
    * signal newly opened text documents. The document's truth is now managed by
    * the client and the server must not try to read the document's truth using
    * the document's uri. Open in this sense means it is managed by the client.
    * It doesn't necessarily mean that its content is presented in an editor. An
    * open notification must not be sent more than once without a corresponding
    * close notification send before. This means open and close notification
    * must be balanced and the max open count is one.
    */
  object didOpen
      extends LSPNotification("textDocument/didOpen")
      with codecs.notifications_textDocument_didOpen:
    type In = structures.DidOpenTextDocumentParams

    override def apply(
        in: structures.DidOpenTextDocumentParams
    ): PreparedNotification[this.type] = super.apply(in)

  /** The document save notification is sent from the client to the server when
    * the document got saved in the client.
    */
  object didSave
      extends LSPNotification("textDocument/didSave")
      with codecs.notifications_textDocument_didSave:
    type In = structures.DidSaveTextDocumentParams

    override def apply(
        in: structures.DidSaveTextDocumentParams
    ): PreparedNotification[this.type] = super.apply(in)

  /** A request to list all color symbols found in a given text document. The
    * request's parameter is of type {@link DocumentColorParams} the response is
    * of type {@link ColorInformation ColorInformation[]} or a Thenable that
    * resolves to such.
    */
  object documentColor
      extends LSPRequest("textDocument/documentColor")
      with codecs.requests_textDocument_documentColor:
    type In  = structures.DocumentColorParams
    type Out = Vector[structures.ColorInformation]

    override def apply(
        in: structures.DocumentColorParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** Request to resolve a {@link DocumentHighlight} for a given text document
    * position. The request's parameter is of type {@link TextDocumentPosition}
    * the request response is an array of type {@link DocumentHighlight} or a
    * Thenable that resolves to such.
    */
  object documentHighlight
      extends LSPRequest("textDocument/documentHighlight")
      with codecs.requests_textDocument_documentHighlight:
    type In  = structures.DocumentHighlightParams
    type Out = Opt[Vector[structures.DocumentHighlight]]

    override def apply(
        in: structures.DocumentHighlightParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to provide document links
    */
  object documentLink
      extends LSPRequest("textDocument/documentLink")
      with codecs.requests_textDocument_documentLink:
    type In  = structures.DocumentLinkParams
    type Out = Opt[Vector[structures.DocumentLink]]

    override def apply(
        in: structures.DocumentLinkParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to list all symbols found in a given text document. The
    * request's parameter is of type {@link TextDocumentIdentifier} the response
    * is of type {@link SymbolInformation SymbolInformation[]} or a Thenable
    * that resolves to such.
    */
  object documentSymbol
      extends LSPRequest("textDocument/documentSymbol")
      with codecs.requests_textDocument_documentSymbol:
    type In = structures.DocumentSymbolParams
    type Out = Opt[
      (Vector[structures.SymbolInformation] | Vector[structures.DocumentSymbol])
    ]

    override def apply(
        in: structures.DocumentSymbolParams
    ): PreparedRequest[this.type] = super.apply(in)
  end documentSymbol

  /** A request to provide folding ranges in a document. The request's parameter
    * is of type {@link FoldingRangeParams}, the response is of type
    * {@link FoldingRangeList} or a Thenable that resolves to such.
    */
  object foldingRange
      extends LSPRequest("textDocument/foldingRange")
      with codecs.requests_textDocument_foldingRange:
    type In  = structures.FoldingRangeParams
    type Out = Opt[Vector[structures.FoldingRange]]

    override def apply(
        in: structures.FoldingRangeParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to format a whole document.
    */
  object formatting
      extends LSPRequest("textDocument/formatting")
      with codecs.requests_textDocument_formatting:
    type In  = structures.DocumentFormattingParams
    type Out = Opt[Vector[structures.TextEdit]]

    override def apply(
        in: structures.DocumentFormattingParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** Request to request hover information at a given text document position.
    * The request's parameter is of type {@link TextDocumentPosition} the
    * response is of type {@link Hover} or a Thenable that resolves to such.
    */
  object hover
      extends LSPRequest("textDocument/hover")
      with codecs.requests_textDocument_hover:
    type In  = structures.HoverParams
    type Out = Opt[structures.Hover]

    override def apply(in: structures.HoverParams): PreparedRequest[this.type] =
      super.apply(in)

  /** A request to resolve the implementation locations of a symbol at a given
    * text document position. The request's parameter is of type
    * {@link TextDocumentPositionParams} the response is of type
    * {@link Definition} or a Thenable that resolves to such.
    */
  object implementation
      extends LSPRequest("textDocument/implementation")
      with codecs.requests_textDocument_implementation:
    type In  = structures.ImplementationParams
    type Out = Opt[(aliases.Definition | Vector[aliases.DefinitionLink])]

    override def apply(
        in: structures.ImplementationParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to provide inlay hints in a document. The request's parameter is
    * of type {@link InlayHintsParams}, the response is of type
    * {@link InlayHint InlayHint[]} or a Thenable that resolves to such.
    *
    * since 3.17.0
    */
  object inlayHint
      extends LSPRequest("textDocument/inlayHint")
      with codecs.requests_textDocument_inlayHint:
    type In  = structures.InlayHintParams
    type Out = Opt[Vector[structures.InlayHint]]

    override def apply(
        in: structures.InlayHintParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to provide inline values in a document. The request's parameter
    * is of type {@link InlineValueParams}, the response is of type
    * {@link InlineValue InlineValue[]} or a Thenable that resolves to such.
    *
    * since 3.17.0
    */
  object inlineValue
      extends LSPRequest("textDocument/inlineValue")
      with codecs.requests_textDocument_inlineValue:
    type In  = structures.InlineValueParams
    type Out = Opt[Vector[aliases.InlineValue]]

    override def apply(
        in: structures.InlineValueParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to provide ranges that can be edited together.
    *
    * since 3.16.0
    */
  object linkedEditingRange
      extends LSPRequest("textDocument/linkedEditingRange")
      with codecs.requests_textDocument_linkedEditingRange:
    type In  = structures.LinkedEditingRangeParams
    type Out = Opt[structures.LinkedEditingRanges]

    override def apply(
        in: structures.LinkedEditingRangeParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to get the moniker of a symbol at a given text document
    * position. The request parameter is of type
    * {@link TextDocumentPositionParams}. The response is of type
    * {@link Moniker Moniker[]} or `null`.
    */
  object moniker
      extends LSPRequest("textDocument/moniker")
      with codecs.requests_textDocument_moniker:
    type In  = structures.MonikerParams
    type Out = Opt[Vector[structures.Moniker]]

    override def apply(
        in: structures.MonikerParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to format a document on type.
    */
  object onTypeFormatting
      extends LSPRequest("textDocument/onTypeFormatting")
      with codecs.requests_textDocument_onTypeFormatting:
    type In  = structures.DocumentOnTypeFormattingParams
    type Out = Opt[Vector[structures.TextEdit]]

    override def apply(
        in: structures.DocumentOnTypeFormattingParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to result a `CallHierarchyItem` in a document at a given
    * position. Can be used as an input to an incoming or outgoing call
    * hierarchy.
    *
    * since 3.16.0
    */
  object prepareCallHierarchy
      extends LSPRequest("textDocument/prepareCallHierarchy")
      with codecs.requests_textDocument_prepareCallHierarchy:
    type In  = structures.CallHierarchyPrepareParams
    type Out = Opt[Vector[structures.CallHierarchyItem]]

    override def apply(
        in: structures.CallHierarchyPrepareParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to test and perform the setup necessary for a rename.
    *
    * since 3.16 - support for default behavior
    */
  object prepareRename
      extends LSPRequest("textDocument/prepareRename")
      with codecs.requests_textDocument_prepareRename:
    type In  = structures.PrepareRenameParams
    type Out = Opt[aliases.PrepareRenameResult]

    override def apply(
        in: structures.PrepareRenameParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to result a `TypeHierarchyItem` in a document at a given
    * position. Can be used as an input to a subtypes or supertypes type
    * hierarchy.
    *
    * since 3.17.0
    */
  object prepareTypeHierarchy
      extends LSPRequest("textDocument/prepareTypeHierarchy")
      with codecs.requests_textDocument_prepareTypeHierarchy:
    type In  = structures.TypeHierarchyPrepareParams
    type Out = Opt[Vector[structures.TypeHierarchyItem]]

    override def apply(
        in: structures.TypeHierarchyPrepareParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** Diagnostics notification are sent from the server to the client to signal
    * results of validation runs.
    */
  object publishDiagnostics
      extends LSPNotification("textDocument/publishDiagnostics")
      with codecs.notifications_textDocument_publishDiagnostics:
    type In = structures.PublishDiagnosticsParams

    override def apply(
        in: structures.PublishDiagnosticsParams
    ): PreparedNotification[this.type] = super.apply(in)

  /** A request to format a range in a document.
    */
  object rangeFormatting
      extends LSPRequest("textDocument/rangeFormatting")
      with codecs.requests_textDocument_rangeFormatting:
    type In  = structures.DocumentRangeFormattingParams
    type Out = Opt[Vector[structures.TextEdit]]

    override def apply(
        in: structures.DocumentRangeFormattingParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to resolve project-wide references for the symbol denoted by the
    * given text document position. The request's parameter is of type
    * {@link ReferenceParams} the response is of type
    * {@link Location Location[]} or a Thenable that resolves to such.
    */
  object references
      extends LSPRequest("textDocument/references")
      with codecs.requests_textDocument_references:
    type In  = structures.ReferenceParams
    type Out = Opt[Vector[structures.Location]]

    override def apply(
        in: structures.ReferenceParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to rename a symbol.
    */
  object rename
      extends LSPRequest("textDocument/rename")
      with codecs.requests_textDocument_rename:
    type In  = structures.RenameParams
    type Out = Opt[structures.WorkspaceEdit]

    override def apply(
        in: structures.RenameParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to provide selection ranges in a document. The request's
    * parameter is of type {@link SelectionRangeParams}, the response is of type
    * {@link SelectionRange SelectionRange[]} or a Thenable that resolves to
    * such.
    */
  object selectionRange
      extends LSPRequest("textDocument/selectionRange")
      with codecs.requests_textDocument_selectionRange:
    type In  = structures.SelectionRangeParams
    type Out = Opt[Vector[structures.SelectionRange]]

    override def apply(
        in: structures.SelectionRangeParams
    ): PreparedRequest[this.type] = super.apply(in)

  object semanticTokens:
    /** since 3.16.0
      */
    object full
        extends LSPRequest("textDocument/semanticTokens/full")
        with codecs.requests_textDocument_semanticTokens_full:
      type In  = structures.SemanticTokensParams
      type Out = Opt[structures.SemanticTokens]

      override def apply(
          in: structures.SemanticTokensParams
      ): PreparedRequest[this.type] = super.apply(in)

      /** since 3.16.0
        */
      object delta
          extends LSPRequest("textDocument/semanticTokens/full/delta")
          with codecs.requests_textDocument_semanticTokens_full_delta:
        type In = structures.SemanticTokensDeltaParams
        type Out =
          Opt[(structures.SemanticTokens | structures.SemanticTokensDelta)]

        override def apply(
            in: structures.SemanticTokensDeltaParams
        ): PreparedRequest[this.type] = super.apply(in)
      end delta
    end full

    /** since 3.16.0
      */
    object range
        extends LSPRequest("textDocument/semanticTokens/range")
        with codecs.requests_textDocument_semanticTokens_range:
      type In  = structures.SemanticTokensRangeParams
      type Out = Opt[structures.SemanticTokens]

      override def apply(
          in: structures.SemanticTokensRangeParams
      ): PreparedRequest[this.type] = super.apply(in)
  end semanticTokens

  object signatureHelp
      extends LSPRequest("textDocument/signatureHelp")
      with codecs.requests_textDocument_signatureHelp:
    type In  = structures.SignatureHelpParams
    type Out = Opt[structures.SignatureHelp]

    override def apply(
        in: structures.SignatureHelpParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to resolve the type definition locations of a symbol at a given
    * text document position. The request's parameter is of type
    * {@link TextDocumentPositionParams} the response is of type
    * {@link Definition} or a Thenable that resolves to such.
    */
  object typeDefinition
      extends LSPRequest("textDocument/typeDefinition")
      with codecs.requests_textDocument_typeDefinition:
    type In  = structures.TypeDefinitionParams
    type Out = Opt[(aliases.Definition | Vector[aliases.DefinitionLink])]

    override def apply(
        in: structures.TypeDefinitionParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A document will save notification is sent from the client to the server
    * before the document is actually saved.
    */
  object willSave
      extends LSPNotification("textDocument/willSave")
      with codecs.notifications_textDocument_willSave:
    type In = structures.WillSaveTextDocumentParams

    override def apply(
        in: structures.WillSaveTextDocumentParams
    ): PreparedNotification[this.type] = super.apply(in)

  /** A document will save request is sent from the client to the server before
    * the document is actually saved. The request can return an array of
    * TextEdits which will be applied to the text document before it is saved.
    * Please note that clients might drop results if computing the text edits
    * took too long or if a server constantly fails on this request. This is
    * done to keep the save fast and reliable.
    */
  object willSaveWaitUntil
      extends LSPRequest("textDocument/willSaveWaitUntil")
      with codecs.requests_textDocument_willSaveWaitUntil:
    type In  = structures.WillSaveTextDocumentParams
    type Out = Opt[Vector[structures.TextEdit]]

    override def apply(
        in: structures.WillSaveTextDocumentParams
    ): PreparedRequest[this.type] = super.apply(in)
end textDocument

object typeHierarchy:
  /** A request to resolve the subtypes for a given `TypeHierarchyItem`.
    *
    * since 3.17.0
    */
  object subtypes
      extends LSPRequest("typeHierarchy/subtypes")
      with codecs.requests_typeHierarchy_subtypes:
    type In  = structures.TypeHierarchySubtypesParams
    type Out = Opt[Vector[structures.TypeHierarchyItem]]

    override def apply(
        in: structures.TypeHierarchySubtypesParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** A request to resolve the supertypes for a given `TypeHierarchyItem`.
    *
    * since 3.17.0
    */
  object supertypes
      extends LSPRequest("typeHierarchy/supertypes")
      with codecs.requests_typeHierarchy_supertypes:
    type In  = structures.TypeHierarchySupertypesParams
    type Out = Opt[Vector[structures.TypeHierarchyItem]]

    override def apply(
        in: structures.TypeHierarchySupertypesParams
    ): PreparedRequest[this.type] = super.apply(in)
end typeHierarchy

object window:
  /** The log message notification is sent from the server to the client to ask
    * the client to log a particular message.
    */
  object logMessage
      extends LSPNotification("window/logMessage")
      with codecs.notifications_window_logMessage:
    type In = structures.LogMessageParams

    override def apply(
        in: structures.LogMessageParams
    ): PreparedNotification[this.type] = super.apply(in)

  /** A request to show a document. This request might open an external program
    * depending on the value of the URI to open. For example a request to open
    * `https://code.visualstudio.com/` will very likely open the URI in a WEB
    * browser.
    *
    * since 3.16.0
    */
  object showDocument
      extends LSPRequest("window/showDocument")
      with codecs.requests_window_showDocument:
    type In  = structures.ShowDocumentParams
    type Out = structures.ShowDocumentResult

    override def apply(
        in: structures.ShowDocumentParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** The show message notification is sent from a server to a client to ask the
    * client to display a particular message in the user interface.
    */
  object showMessage
      extends LSPNotification("window/showMessage")
      with codecs.notifications_window_showMessage:
    type In = structures.ShowMessageParams

    override def apply(
        in: structures.ShowMessageParams
    ): PreparedNotification[this.type] = super.apply(in)

  /** The show message request is sent from the server to the client to show a
    * message and a set of options actions to the user.
    */
  object showMessageRequest
      extends LSPRequest("window/showMessageRequest")
      with codecs.requests_window_showMessageRequest:
    type In  = structures.ShowMessageRequestParams
    type Out = Opt[structures.MessageActionItem]

    override def apply(
        in: structures.ShowMessageRequestParams
    ): PreparedRequest[this.type] = super.apply(in)

  object workDoneProgress:
    /** The `window/workDoneProgress/cancel` notification is sent from the
      * client to the server to cancel a progress initiated on the server side.
      */
    object cancel
        extends LSPNotification("window/workDoneProgress/cancel")
        with codecs.notifications_window_workDoneProgress_cancel:
      type In = structures.WorkDoneProgressCancelParams

      override def apply(
          in: structures.WorkDoneProgressCancelParams
      ): PreparedNotification[this.type] = super.apply(in)

    /** The `window/workDoneProgress/create` request is sent from the server to
      * the client to initiate progress reporting from the server.
      */
    object create
        extends LSPRequest("window/workDoneProgress/create")
        with codecs.requests_window_workDoneProgress_create:
      type In  = structures.WorkDoneProgressCreateParams
      type Out = Null

      override def apply(
          in: structures.WorkDoneProgressCreateParams
      ): PreparedRequest[this.type] = super.apply(in)
  end workDoneProgress
end window

object workspace:
  /** A request sent from the server to the client to modified certain
    * resources.
    */
  object applyEdit
      extends LSPRequest("workspace/applyEdit")
      with codecs.requests_workspace_applyEdit:
    type In  = structures.ApplyWorkspaceEditParams
    type Out = structures.ApplyWorkspaceEditResult

    override def apply(
        in: structures.ApplyWorkspaceEditParams
    ): PreparedRequest[this.type] = super.apply(in)

  object codeLens:
    /** A request to refresh all code actions
      *
      * since 3.16.0
      */
    object refresh
        extends LSPRequest("workspace/codeLens/refresh")
        with codecs.requests_workspace_codeLens_refresh:
      type In  = Unit
      type Out = Null

      override def apply(in: Unit): PreparedRequest[this.type] = super.apply(in)
  end codeLens

  /** The 'workspace/configuration' request is sent from the server to the
    * client to fetch a certain configuration setting.
    *
    * This pull model replaces the old push model where the client signaled
    * configuration change via an event. If the server still needs to react to
    * configuration changes (since the server caches the result of
    * `workspace/configuration` requests) the server should register for an
    * empty configuration change event and empty the cache if such an event is
    * received.
    */
  object configuration
      extends LSPRequest("workspace/configuration")
      with codecs.requests_workspace_configuration:
    type In  = structures.ConfigurationParams
    type Out = Vector[ujson.Value]

    override def apply(
        in: structures.ConfigurationParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** The workspace diagnostic request definition.
    *
    * since 3.17.0
    */
  object diagnostic
      extends LSPRequest("workspace/diagnostic")
      with codecs.requests_workspace_diagnostic:
    type In  = structures.WorkspaceDiagnosticParams
    type Out = structures.WorkspaceDiagnosticReport

    override def apply(
        in: structures.WorkspaceDiagnosticParams
    ): PreparedRequest[this.type] = super.apply(in)

    /** The diagnostic refresh request definition.
      *
      * since 3.17.0
      */
    object refresh
        extends LSPRequest("workspace/diagnostic/refresh")
        with codecs.requests_workspace_diagnostic_refresh:
      type In  = Unit
      type Out = Null

      override def apply(in: Unit): PreparedRequest[this.type] = super.apply(in)
  end diagnostic

  /** The configuration change notification is sent from the client to the
    * server when the client's configuration has changed. The notification
    * contains the changed configuration as defined by the language client.
    */
  object didChangeConfiguration
      extends LSPNotification("workspace/didChangeConfiguration")
      with codecs.notifications_workspace_didChangeConfiguration:
    type In = structures.DidChangeConfigurationParams

    override def apply(
        in: structures.DidChangeConfigurationParams
    ): PreparedNotification[this.type] = super.apply(in)

  /** The watched files notification is sent from the client to the server when
    * the client detects changes to file watched by the language client.
    */
  object didChangeWatchedFiles
      extends LSPNotification("workspace/didChangeWatchedFiles")
      with codecs.notifications_workspace_didChangeWatchedFiles:
    type In = structures.DidChangeWatchedFilesParams

    override def apply(
        in: structures.DidChangeWatchedFilesParams
    ): PreparedNotification[this.type] = super.apply(in)

  /** The `workspace/didChangeWorkspaceFolders` notification is sent from the
    * client to the server when the workspace folder configuration changes.
    */
  object didChangeWorkspaceFolders
      extends LSPNotification("workspace/didChangeWorkspaceFolders")
      with codecs.notifications_workspace_didChangeWorkspaceFolders:
    type In = structures.DidChangeWorkspaceFoldersParams

    override def apply(
        in: structures.DidChangeWorkspaceFoldersParams
    ): PreparedNotification[this.type] = super.apply(in)

  /** The did create files notification is sent from the client to the server
    * when files were created from within the client.
    *
    * @since 3.16.0
    */
  object didCreateFiles
      extends LSPNotification("workspace/didCreateFiles")
      with codecs.notifications_workspace_didCreateFiles:
    type In = structures.CreateFilesParams

    override def apply(
        in: structures.CreateFilesParams
    ): PreparedNotification[this.type] = super.apply(in)

  /** The will delete files request is sent from the client to the server before
    * files are actually deleted as long as the deletion is triggered from
    * within the client.
    *
    * @since 3.16.0
    */
  object didDeleteFiles
      extends LSPNotification("workspace/didDeleteFiles")
      with codecs.notifications_workspace_didDeleteFiles:
    type In = structures.DeleteFilesParams

    override def apply(
        in: structures.DeleteFilesParams
    ): PreparedNotification[this.type] = super.apply(in)

  /** The did rename files notification is sent from the client to the server
    * when files were renamed from within the client.
    *
    * @since 3.16.0
    */
  object didRenameFiles
      extends LSPNotification("workspace/didRenameFiles")
      with codecs.notifications_workspace_didRenameFiles:
    type In = structures.RenameFilesParams

    override def apply(
        in: structures.RenameFilesParams
    ): PreparedNotification[this.type] = super.apply(in)

  /** A request send from the client to the server to execute a command. The
    * request might return a workspace edit which the client will apply to the
    * workspace.
    */
  object executeCommand
      extends LSPRequest("workspace/executeCommand")
      with codecs.requests_workspace_executeCommand:
    type In  = structures.ExecuteCommandParams
    type Out = Opt[ujson.Value]

    override def apply(
        in: structures.ExecuteCommandParams
    ): PreparedRequest[this.type] = super.apply(in)

  object inlayHint:
    /** since 3.17.0
      */
    object refresh
        extends LSPRequest("workspace/inlayHint/refresh")
        with codecs.requests_workspace_inlayHint_refresh:
      type In  = Unit
      type Out = Null

      override def apply(in: Unit): PreparedRequest[this.type] = super.apply(in)
  end inlayHint

  object inlineValue:
    /** since 3.17.0
      */
    object refresh
        extends LSPRequest("workspace/inlineValue/refresh")
        with codecs.requests_workspace_inlineValue_refresh:
      type In  = Unit
      type Out = Null

      override def apply(in: Unit): PreparedRequest[this.type] = super.apply(in)
  end inlineValue

  object semanticTokens:
    /** since 3.16.0
      */
    object refresh
        extends LSPRequest("workspace/semanticTokens/refresh")
        with codecs.requests_workspace_semanticTokens_refresh:
      type In  = Unit
      type Out = Null

      override def apply(in: Unit): PreparedRequest[this.type] = super.apply(in)
  end semanticTokens

  /** A request to list project-wide symbols matching the query string given by
    * the {@link WorkspaceSymbolParams}. The response is of type
    * {@link SymbolInformation SymbolInformation[]} or a Thenable that resolves
    * to such.
    *
    * since 3.17.0 - support for WorkspaceSymbol in the returned data. Clients
    * need to advertise support for WorkspaceSymbols via the client capability
    * `workspace.symbol.resolveSupport`.
    */
  object symbol
      extends LSPRequest("workspace/symbol")
      with codecs.requests_workspace_symbol:
    type In = structures.WorkspaceSymbolParams
    type Out = Opt[
      (Vector[structures.SymbolInformation] |
        Vector[structures.WorkspaceSymbol])
    ]

    override def apply(
        in: structures.WorkspaceSymbolParams
    ): PreparedRequest[this.type] = super.apply(in)
  end symbol

  /** The will create files request is sent from the client to the server before
    * files are actually created as long as the creation is triggered from
    * within the client.
    *
    * The request can return a `WorkspaceEdit` which will be applied to
    * workspace before the files are created. Hence the `WorkspaceEdit` can not
    * manipulate the content of the file to be created.
    *
    * since 3.16.0
    */
  object willCreateFiles
      extends LSPRequest("workspace/willCreateFiles")
      with codecs.requests_workspace_willCreateFiles:
    type In  = structures.CreateFilesParams
    type Out = Opt[structures.WorkspaceEdit]

    override def apply(
        in: structures.CreateFilesParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** The did delete files notification is sent from the client to the server
    * when files were deleted from within the client.
    *
    * since 3.16.0
    */
  object willDeleteFiles
      extends LSPRequest("workspace/willDeleteFiles")
      with codecs.requests_workspace_willDeleteFiles:
    type In  = structures.DeleteFilesParams
    type Out = Opt[structures.WorkspaceEdit]

    override def apply(
        in: structures.DeleteFilesParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** The will rename files request is sent from the client to the server before
    * files are actually renamed as long as the rename is triggered from within
    * the client.
    *
    * since 3.16.0
    */
  object willRenameFiles
      extends LSPRequest("workspace/willRenameFiles")
      with codecs.requests_workspace_willRenameFiles:
    type In  = structures.RenameFilesParams
    type Out = Opt[structures.WorkspaceEdit]

    override def apply(
        in: structures.RenameFilesParams
    ): PreparedRequest[this.type] = super.apply(in)

  /** The `workspace/workspaceFolders` is sent from the server to the client to
    * fetch the open workspace folders.
    */
  object workspaceFolders
      extends LSPRequest("workspace/workspaceFolders")
      with codecs.requests_workspace_workspaceFolders:
    type In  = Unit
    type Out = Opt[Vector[structures.WorkspaceFolder]]

    override def apply(in: Unit): PreparedRequest[this.type] = super.apply(in)
end workspace

object workspaceSymbol:
  /** A request to resolve the range inside the workspace symbol's location.
    *
    * since 3.17.0
    */
  object resolve
      extends LSPRequest("workspaceSymbol/resolve")
      with codecs.requests_workspaceSymbol_resolve:
    type In  = structures.WorkspaceSymbol
    type Out = structures.WorkspaceSymbol

    override def apply(
        in: structures.WorkspaceSymbol
    ): PreparedRequest[this.type] = super.apply(in)
end workspaceSymbol
