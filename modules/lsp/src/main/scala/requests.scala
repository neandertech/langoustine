package langoustine.lsp
package requests

import langoustine.*
import upickle.default.*
import langoustine.lsp.json.{*, given}

object callHierarchy:
  object incomingCalls extends LSPRequest("callHierarchy/incomingCalls"):
    type In = structures.CallHierarchyIncomingCallsParams
    type Out = (Vector[structures.CallHierarchyIncomingCall] | Null)
    private val _reader: Reader[In] = structures.CallHierarchyIncomingCallsParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.CallHierarchyIncomingCall] => write(v)(using upickle.default.writer[Vector[structures.CallHierarchyIncomingCall]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object outgoingCalls extends LSPRequest("callHierarchy/outgoingCalls"):
    type In = structures.CallHierarchyOutgoingCallsParams
    type Out = (Vector[structures.CallHierarchyOutgoingCall] | Null)
    private val _reader: Reader[In] = structures.CallHierarchyOutgoingCallsParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.CallHierarchyOutgoingCall] => write(v)(using upickle.default.writer[Vector[structures.CallHierarchyOutgoingCall]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
object client:
  object registerCapability extends LSPRequest("client/registerCapability"):
    type In = structures.RegistrationParams
    type Out = Null
    private val _reader: Reader[In] = structures.RegistrationParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = nullReadWriter
    given writer: Writer[Out] = _writer
  
  object unregisterCapability extends LSPRequest("client/unregisterCapability"):
    type In = structures.UnregistrationParams
    type Out = Null
    private val _reader: Reader[In] = structures.UnregistrationParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = nullReadWriter
    given writer: Writer[Out] = _writer
  
object codeAction:
  object resolve extends LSPRequest("codeAction/resolve"):
    type In = structures.CodeAction
    type Out = structures.CodeAction
    private val _reader: Reader[In] = structures.CodeAction.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = structures.CodeAction.writer
    given writer: Writer[Out] = _writer
  
object codeLens:
  object resolve extends LSPRequest("codeLens/resolve"):
    type In = structures.CodeLens
    type Out = structures.CodeLens
    private val _reader: Reader[In] = structures.CodeLens.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = structures.CodeLens.writer
    given writer: Writer[Out] = _writer
  
object completionItem:
  object resolve extends LSPRequest("completionItem/resolve"):
    type In = structures.CompletionItem
    type Out = structures.CompletionItem
    private val _reader: Reader[In] = structures.CompletionItem.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = structures.CompletionItem.writer
    given writer: Writer[Out] = _writer
  
object documentLink:
  object resolve extends LSPRequest("documentLink/resolve"):
    type In = structures.DocumentLink
    type Out = structures.DocumentLink
    private val _reader: Reader[In] = structures.DocumentLink.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = structures.DocumentLink.writer
    given writer: Writer[Out] = _writer
  
object initialize extends LSPRequest("initialize"):
  type In = structures.InitializeParams
  type Out = structures.InitializeResult
  private val _reader: Reader[In] = structures.InitializeParams.reader
  given reader: Reader[In] = _reader
  private val _writer: Writer[Out] = structures.InitializeResult.writer
  given writer: Writer[Out] = _writer

object inlayHint:
  object resolve extends LSPRequest("inlayHint/resolve"):
    type In = structures.InlayHint
    type Out = structures.InlayHint
    private val _reader: Reader[In] = structures.InlayHint.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = structures.InlayHint.writer
    given writer: Writer[Out] = _writer
  
object shutdown extends LSPRequest("shutdown"):
  type In = Unit
  type Out = Null
  private val _reader: Reader[In] = unitReader
  given reader: Reader[In] = _reader
  private val _writer: Writer[Out] = nullReadWriter
  given writer: Writer[Out] = _writer

object textDocument:
  object codeAction extends LSPRequest("textDocument/codeAction"):
    type In = structures.CodeActionParams
    type Out = (Vector[(structures.Command | structures.CodeAction)] | Null)
    private val _reader: Reader[In] = structures.CodeActionParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[(structures.Command | structures.CodeAction)] => write(v)(using upickle.default.writer[Vector[(structures.Command | structures.CodeAction)]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object codeLens extends LSPRequest("textDocument/codeLens"):
    type In = structures.CodeLensParams
    type Out = (Vector[structures.CodeLens] | Null)
    private val _reader: Reader[In] = structures.CodeLensParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.CodeLens] => write(v)(using upickle.default.writer[Vector[structures.CodeLens]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object colorPresentation extends LSPRequest("textDocument/colorPresentation"):
    type In = structures.ColorPresentationParams
    type Out = Vector[structures.ColorPresentation]
    private val _reader: Reader[In] = structures.ColorPresentationParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[Vector[structures.ColorPresentation]]
    given writer: Writer[Out] = _writer
  
  object completion extends LSPRequest("textDocument/completion"):
    type In = structures.CompletionParams
    type Out = (Vector[structures.CompletionItem] | structures.CompletionList | Null)
    private val _reader: Reader[In] = structures.CompletionParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.CompletionItem] => write(v)(using upickle.default.writer[Vector[structures.CompletionItem]]); case v: structures.CompletionList => write(v)(using structures.CompletionList.writer); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object declaration extends LSPRequest("textDocument/declaration"):
    type In = structures.DeclarationParams
    type Out = (aliases.Declaration | Vector[aliases.DeclarationLink] | Null)
    private val _reader: Reader[In] = structures.DeclarationParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: aliases.Declaration => write(v)(using aliases.Declaration.writer); case v: Vector[aliases.DeclarationLink] => write(v)(using upickle.default.writer[Vector[aliases.DeclarationLink]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object definition extends LSPRequest("textDocument/definition"):
    type In = structures.DefinitionParams
    type Out = (aliases.Definition | Vector[aliases.DefinitionLink] | Null)
    private val _reader: Reader[In] = structures.DefinitionParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: aliases.Definition => write(v)(using aliases.Definition.writer); case v: Vector[aliases.DefinitionLink] => write(v)(using upickle.default.writer[Vector[aliases.DefinitionLink]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object diagnostic extends LSPRequest("textDocument/diagnostic"):
    type In = structures.DocumentDiagnosticParams
    type Out = aliases.DocumentDiagnosticReport
    private val _reader: Reader[In] = structures.DocumentDiagnosticParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = aliases.DocumentDiagnosticReport.writer
    given writer: Writer[Out] = _writer
  
  object documentColor extends LSPRequest("textDocument/documentColor"):
    type In = structures.DocumentColorParams
    type Out = Vector[structures.ColorInformation]
    private val _reader: Reader[In] = structures.DocumentColorParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[Vector[structures.ColorInformation]]
    given writer: Writer[Out] = _writer
  
  object documentHighlight extends LSPRequest("textDocument/documentHighlight"):
    type In = structures.DocumentHighlightParams
    type Out = (Vector[structures.DocumentHighlight] | Null)
    private val _reader: Reader[In] = structures.DocumentHighlightParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.DocumentHighlight] => write(v)(using upickle.default.writer[Vector[structures.DocumentHighlight]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object documentLink extends LSPRequest("textDocument/documentLink"):
    type In = structures.DocumentLinkParams
    type Out = (Vector[structures.DocumentLink] | Null)
    private val _reader: Reader[In] = structures.DocumentLinkParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.DocumentLink] => write(v)(using upickle.default.writer[Vector[structures.DocumentLink]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object documentSymbol extends LSPRequest("textDocument/documentSymbol"):
    type In = structures.DocumentSymbolParams
    type Out = (Vector[structures.SymbolInformation] | Vector[structures.DocumentSymbol] | Null)
    private val _reader: Reader[In] = structures.DocumentSymbolParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.SymbolInformation] => write(v)(using upickle.default.writer[Vector[structures.SymbolInformation]]); case v: Vector[structures.DocumentSymbol] => write(v)(using upickle.default.writer[Vector[structures.DocumentSymbol]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object foldingRange extends LSPRequest("textDocument/foldingRange"):
    type In = structures.FoldingRangeParams
    type Out = (Vector[structures.FoldingRange] | Null)
    private val _reader: Reader[In] = structures.FoldingRangeParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.FoldingRange] => write(v)(using upickle.default.writer[Vector[structures.FoldingRange]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object formatting extends LSPRequest("textDocument/formatting"):
    type In = structures.DocumentFormattingParams
    type Out = (Vector[structures.TextEdit] | Null)
    private val _reader: Reader[In] = structures.DocumentFormattingParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.TextEdit] => write(v)(using upickle.default.writer[Vector[structures.TextEdit]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object hover extends LSPRequest("textDocument/hover"):
    type In = structures.HoverParams
    type Out = (structures.Hover | Null)
    private val _reader: Reader[In] = structures.HoverParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: structures.Hover => write(v)(using structures.Hover.writer); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object implementation extends LSPRequest("textDocument/implementation"):
    type In = structures.ImplementationParams
    type Out = (aliases.Definition | Vector[aliases.DefinitionLink] | Null)
    private val _reader: Reader[In] = structures.ImplementationParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: aliases.Definition => write(v)(using aliases.Definition.writer); case v: Vector[aliases.DefinitionLink] => write(v)(using upickle.default.writer[Vector[aliases.DefinitionLink]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object inlayHint extends LSPRequest("textDocument/inlayHint"):
    type In = structures.InlayHintParams
    type Out = (Vector[structures.InlayHint] | Null)
    private val _reader: Reader[In] = structures.InlayHintParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.InlayHint] => write(v)(using upickle.default.writer[Vector[structures.InlayHint]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object inlineValue extends LSPRequest("textDocument/inlineValue"):
    type In = structures.InlineValueParams
    type Out = (Vector[aliases.InlineValue] | Null)
    private val _reader: Reader[In] = structures.InlineValueParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[aliases.InlineValue] => write(v)(using upickle.default.writer[Vector[aliases.InlineValue]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object linkedEditingRange extends LSPRequest("textDocument/linkedEditingRange"):
    type In = structures.LinkedEditingRangeParams
    type Out = (structures.LinkedEditingRanges | Null)
    private val _reader: Reader[In] = structures.LinkedEditingRangeParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: structures.LinkedEditingRanges => write(v)(using structures.LinkedEditingRanges.writer); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object moniker extends LSPRequest("textDocument/moniker"):
    type In = structures.MonikerParams
    type Out = (Vector[structures.Moniker] | Null)
    private val _reader: Reader[In] = structures.MonikerParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.Moniker] => write(v)(using upickle.default.writer[Vector[structures.Moniker]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object onTypeFormatting extends LSPRequest("textDocument/onTypeFormatting"):
    type In = structures.DocumentOnTypeFormattingParams
    type Out = (Vector[structures.TextEdit] | Null)
    private val _reader: Reader[In] = structures.DocumentOnTypeFormattingParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.TextEdit] => write(v)(using upickle.default.writer[Vector[structures.TextEdit]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object prepareCallHierarchy extends LSPRequest("textDocument/prepareCallHierarchy"):
    type In = structures.CallHierarchyPrepareParams
    type Out = (Vector[structures.CallHierarchyItem] | Null)
    private val _reader: Reader[In] = structures.CallHierarchyPrepareParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.CallHierarchyItem] => write(v)(using upickle.default.writer[Vector[structures.CallHierarchyItem]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object prepareRename extends LSPRequest("textDocument/prepareRename"):
    type In = structures.PrepareRenameParams
    type Out = (aliases.PrepareRenameResult | Null)
    private val _reader: Reader[In] = structures.PrepareRenameParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: aliases.PrepareRenameResult => write(v)(using aliases.PrepareRenameResult.writer); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object prepareTypeHierarchy extends LSPRequest("textDocument/prepareTypeHierarchy"):
    type In = structures.TypeHierarchyPrepareParams
    type Out = (Vector[structures.TypeHierarchyItem] | Null)
    private val _reader: Reader[In] = structures.TypeHierarchyPrepareParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.TypeHierarchyItem] => write(v)(using upickle.default.writer[Vector[structures.TypeHierarchyItem]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object rangeFormatting extends LSPRequest("textDocument/rangeFormatting"):
    type In = structures.DocumentRangeFormattingParams
    type Out = (Vector[structures.TextEdit] | Null)
    private val _reader: Reader[In] = structures.DocumentRangeFormattingParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.TextEdit] => write(v)(using upickle.default.writer[Vector[structures.TextEdit]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object references extends LSPRequest("textDocument/references"):
    type In = structures.ReferenceParams
    type Out = (Vector[structures.Location] | Null)
    private val _reader: Reader[In] = structures.ReferenceParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.Location] => write(v)(using upickle.default.writer[Vector[structures.Location]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object rename extends LSPRequest("textDocument/rename"):
    type In = structures.RenameParams
    type Out = (structures.WorkspaceEdit | Null)
    private val _reader: Reader[In] = structures.RenameParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: structures.WorkspaceEdit => write(v)(using structures.WorkspaceEdit.writer); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object selectionRange extends LSPRequest("textDocument/selectionRange"):
    type In = structures.SelectionRangeParams
    type Out = (Vector[structures.SelectionRange] | Null)
    private val _reader: Reader[In] = structures.SelectionRangeParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.SelectionRange] => write(v)(using upickle.default.writer[Vector[structures.SelectionRange]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object semanticTokens:
    object full extends LSPRequest("textDocument/semanticTokens/full"):
      type In = structures.SemanticTokensParams
      type Out = (structures.SemanticTokens | Null)
      private val _reader: Reader[In] = structures.SemanticTokensParams.reader
      given reader: Reader[In] = _reader
      private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: structures.SemanticTokens => write(v)(using structures.SemanticTokens.writer); case null => ujson.Null; }
      given writer: Writer[Out] = _writer
    
      object delta extends LSPRequest("textDocument/semanticTokens/full/delta"):
        type In = structures.SemanticTokensDeltaParams
        type Out = (structures.SemanticTokens | structures.SemanticTokensDelta | Null)
        private val _reader: Reader[In] = structures.SemanticTokensDeltaParams.reader
        given reader: Reader[In] = _reader
        private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: structures.SemanticTokens => write(v)(using structures.SemanticTokens.writer); case v: structures.SemanticTokensDelta => write(v)(using structures.SemanticTokensDelta.writer); case null => ujson.Null; }
        given writer: Writer[Out] = _writer
      
    object range extends LSPRequest("textDocument/semanticTokens/range"):
      type In = structures.SemanticTokensRangeParams
      type Out = (structures.SemanticTokens | Null)
      private val _reader: Reader[In] = structures.SemanticTokensRangeParams.reader
      given reader: Reader[In] = _reader
      private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: structures.SemanticTokens => write(v)(using structures.SemanticTokens.writer); case null => ujson.Null; }
      given writer: Writer[Out] = _writer
    
  object signatureHelp extends LSPRequest("textDocument/signatureHelp"):
    type In = structures.SignatureHelpParams
    type Out = (structures.SignatureHelp | Null)
    private val _reader: Reader[In] = structures.SignatureHelpParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: structures.SignatureHelp => write(v)(using structures.SignatureHelp.writer); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object typeDefinition extends LSPRequest("textDocument/typeDefinition"):
    type In = structures.TypeDefinitionParams
    type Out = (aliases.Definition | Vector[aliases.DefinitionLink] | Null)
    private val _reader: Reader[In] = structures.TypeDefinitionParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: aliases.Definition => write(v)(using aliases.Definition.writer); case v: Vector[aliases.DefinitionLink] => write(v)(using upickle.default.writer[Vector[aliases.DefinitionLink]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object willSaveWaitUntil extends LSPRequest("textDocument/willSaveWaitUntil"):
    type In = structures.WillSaveTextDocumentParams
    type Out = (Vector[structures.TextEdit] | Null)
    private val _reader: Reader[In] = structures.WillSaveTextDocumentParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.TextEdit] => write(v)(using upickle.default.writer[Vector[structures.TextEdit]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
object typeHierarchy:
  object subtypes extends LSPRequest("typeHierarchy/subtypes"):
    type In = structures.TypeHierarchySubtypesParams
    type Out = (Vector[structures.TypeHierarchyItem] | Null)
    private val _reader: Reader[In] = structures.TypeHierarchySubtypesParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.TypeHierarchyItem] => write(v)(using upickle.default.writer[Vector[structures.TypeHierarchyItem]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object supertypes extends LSPRequest("typeHierarchy/supertypes"):
    type In = structures.TypeHierarchySupertypesParams
    type Out = (Vector[structures.TypeHierarchyItem] | Null)
    private val _reader: Reader[In] = structures.TypeHierarchySupertypesParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.TypeHierarchyItem] => write(v)(using upickle.default.writer[Vector[structures.TypeHierarchyItem]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
object window:
  object showDocument extends LSPRequest("window/showDocument"):
    type In = structures.ShowDocumentParams
    type Out = structures.ShowDocumentResult
    private val _reader: Reader[In] = structures.ShowDocumentParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = structures.ShowDocumentResult.writer
    given writer: Writer[Out] = _writer
  
  object showMessageRequest extends LSPRequest("window/showMessageRequest"):
    type In = structures.ShowMessageRequestParams
    type Out = (structures.MessageActionItem | Null)
    private val _reader: Reader[In] = structures.ShowMessageRequestParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: structures.MessageActionItem => write(v)(using structures.MessageActionItem.writer); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object workDoneProgress:
    object create extends LSPRequest("window/workDoneProgress/create"):
      type In = structures.WorkDoneProgressCreateParams
      type Out = Null
      private val _reader: Reader[In] = structures.WorkDoneProgressCreateParams.reader
      given reader: Reader[In] = _reader
      private val _writer: Writer[Out] = nullReadWriter
      given writer: Writer[Out] = _writer
    
object workspace:
  object applyEdit extends LSPRequest("workspace/applyEdit"):
    type In = structures.ApplyWorkspaceEditParams
    type Out = structures.ApplyWorkspaceEditResult
    private val _reader: Reader[In] = structures.ApplyWorkspaceEditParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = structures.ApplyWorkspaceEditResult.writer
    given writer: Writer[Out] = _writer
  
  object codeLens:
    object refresh extends LSPRequest("workspace/codeLens/refresh"):
      type In = Unit
      type Out = Null
      private val _reader: Reader[In] = unitReader
      given reader: Reader[In] = _reader
      private val _writer: Writer[Out] = nullReadWriter
      given writer: Writer[Out] = _writer
    
  object configuration extends LSPRequest("workspace/configuration"):
    type In = Any /*AndType(and,Vector(ReferenceType(reference,ConfigurationParams), ReferenceType(reference,PartialResultParams)))*/
    type Out = Vector[aliases.LSPAny]
    private val _reader: Reader[In] = upickle.default.reader[Any /*AndType(and,Vector(ReferenceType(reference,ConfigurationParams), ReferenceType(reference,PartialResultParams)))*/]
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[Vector[aliases.LSPAny]]
    given writer: Writer[Out] = _writer
  
  object diagnostic extends LSPRequest("workspace/diagnostic"):
    type In = structures.WorkspaceDiagnosticParams
    type Out = structures.WorkspaceDiagnosticReport
    private val _reader: Reader[In] = structures.WorkspaceDiagnosticParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = structures.WorkspaceDiagnosticReport.writer
    given writer: Writer[Out] = _writer
  
    object refresh extends LSPRequest("workspace/diagnostic/refresh"):
      type In = Unit
      type Out = Null
      private val _reader: Reader[In] = unitReader
      given reader: Reader[In] = _reader
      private val _writer: Writer[Out] = nullReadWriter
      given writer: Writer[Out] = _writer
    
  object executeCommand extends LSPRequest("workspace/executeCommand"):
    type In = structures.ExecuteCommandParams
    type Out = (aliases.LSPAny | Null)
    private val _reader: Reader[In] = structures.ExecuteCommandParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: aliases.LSPAny => write(v)(using aliases.LSPAny.writer); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object inlayHint:
    object refresh extends LSPRequest("workspace/inlayHint/refresh"):
      type In = Unit
      type Out = Null
      private val _reader: Reader[In] = unitReader
      given reader: Reader[In] = _reader
      private val _writer: Writer[Out] = nullReadWriter
      given writer: Writer[Out] = _writer
    
  object inlineValue:
    object refresh extends LSPRequest("workspace/inlineValue/refresh"):
      type In = Unit
      type Out = Null
      private val _reader: Reader[In] = unitReader
      given reader: Reader[In] = _reader
      private val _writer: Writer[Out] = nullReadWriter
      given writer: Writer[Out] = _writer
    
  object semanticTokens:
    object refresh extends LSPRequest("workspace/semanticTokens/refresh"):
      type In = Unit
      type Out = Null
      private val _reader: Reader[In] = unitReader
      given reader: Reader[In] = _reader
      private val _writer: Writer[Out] = nullReadWriter
      given writer: Writer[Out] = _writer
    
  object symbol extends LSPRequest("workspace/symbol"):
    type In = structures.WorkspaceSymbolParams
    type Out = (Vector[structures.SymbolInformation] | Vector[structures.WorkspaceSymbol] | Null)
    private val _reader: Reader[In] = structures.WorkspaceSymbolParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.SymbolInformation] => write(v)(using upickle.default.writer[Vector[structures.SymbolInformation]]); case v: Vector[structures.WorkspaceSymbol] => write(v)(using upickle.default.writer[Vector[structures.WorkspaceSymbol]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object willCreateFiles extends LSPRequest("workspace/willCreateFiles"):
    type In = structures.CreateFilesParams
    type Out = (structures.WorkspaceEdit | Null)
    private val _reader: Reader[In] = structures.CreateFilesParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: structures.WorkspaceEdit => write(v)(using structures.WorkspaceEdit.writer); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object willDeleteFiles extends LSPRequest("workspace/willDeleteFiles"):
    type In = structures.DeleteFilesParams
    type Out = (structures.WorkspaceEdit | Null)
    private val _reader: Reader[In] = structures.DeleteFilesParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: structures.WorkspaceEdit => write(v)(using structures.WorkspaceEdit.writer); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object willRenameFiles extends LSPRequest("workspace/willRenameFiles"):
    type In = structures.RenameFilesParams
    type Out = (structures.WorkspaceEdit | Null)
    private val _reader: Reader[In] = structures.RenameFilesParams.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: structures.WorkspaceEdit => write(v)(using structures.WorkspaceEdit.writer); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
  object workspaceFolders extends LSPRequest("workspace/workspaceFolders"):
    type In = Unit
    type Out = (Vector[structures.WorkspaceFolder] | Null)
    private val _reader: Reader[In] = unitReader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = upickle.default.writer[ujson.Value].comap[Out] {case v: Vector[structures.WorkspaceFolder] => write(v)(using upickle.default.writer[Vector[structures.WorkspaceFolder]]); case null => ujson.Null; }
    given writer: Writer[Out] = _writer
  
object workspaceSymbol:
  object resolve extends LSPRequest("workspaceSymbol/resolve"):
    type In = structures.WorkspaceSymbol
    type Out = structures.WorkspaceSymbol
    private val _reader: Reader[In] = structures.WorkspaceSymbol.reader
    given reader: Reader[In] = _reader
    private val _writer: Writer[Out] = structures.WorkspaceSymbol.writer
    given writer: Writer[Out] = _writer
  
