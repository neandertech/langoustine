package langoustine.lsp
package requests

import langoustine.*
import upickle.default.*
import langoustine.lsp.json.{*, given}

object callHierarchy:
  object incomingCalls extends LSPRequest("callHierarchy/incomingCalls"):
    type In = structures.CallHierarchyIncomingCallsParams
    type Out = (Vector[structures.CallHierarchyIncomingCall] | Null)
    given reader: Reader[In] = structures.CallHierarchyIncomingCallsParams.codec
    given writer: Writer[Out] = ???
  
  object outgoingCalls extends LSPRequest("callHierarchy/outgoingCalls"):
    type In = structures.CallHierarchyOutgoingCallsParams
    type Out = (Vector[structures.CallHierarchyOutgoingCall] | Null)
    given reader: Reader[In] = structures.CallHierarchyOutgoingCallsParams.codec
    given writer: Writer[Out] = ???
  
object client:
  object registerCapability extends LSPRequest("client/registerCapability"):
    type In = structures.RegistrationParams
    type Out = Null
    given reader: Reader[In] = structures.RegistrationParams.codec
    given writer: Writer[Out] = ???
  
  object unregisterCapability extends LSPRequest("client/unregisterCapability"):
    type In = structures.UnregistrationParams
    type Out = Null
    given reader: Reader[In] = structures.UnregistrationParams.codec
    given writer: Writer[Out] = ???
  
object codeAction:
  object resolve extends LSPRequest("codeAction/resolve"):
    type In = structures.CodeAction
    type Out = structures.CodeAction
    given reader: Reader[In] = structures.CodeAction.codec
    given writer: Writer[Out] = ???
  
object codeLens:
  object resolve extends LSPRequest("codeLens/resolve"):
    type In = structures.CodeLens
    type Out = structures.CodeLens
    given reader: Reader[In] = structures.CodeLens.codec
    given writer: Writer[Out] = ???
  
object completionItem:
  object resolve extends LSPRequest("completionItem/resolve"):
    type In = structures.CompletionItem
    type Out = structures.CompletionItem
    given reader: Reader[In] = structures.CompletionItem.codec
    given writer: Writer[Out] = ???
  
object documentLink:
  object resolve extends LSPRequest("documentLink/resolve"):
    type In = structures.DocumentLink
    type Out = structures.DocumentLink
    given reader: Reader[In] = structures.DocumentLink.codec
    given writer: Writer[Out] = ???
  
object initialize extends LSPRequest("initialize"):
  type In = structures.InitializeParams
  type Out = structures.InitializeResult
  given reader: Reader[In] = structures.InitializeParams.codec
  given writer: Writer[Out] = ???

object inlayHint:
  object resolve extends LSPRequest("inlayHint/resolve"):
    type In = structures.InlayHint
    type Out = structures.InlayHint
    given reader: Reader[In] = structures.InlayHint.codec
    given writer: Writer[Out] = ???
  
object shutdown extends LSPRequest("shutdown"):
  type In = Unit
  type Out = Null
  given reader: Reader[In] = unitReader
  given writer: Writer[Out] = ???

object textDocument:
  object codeAction extends LSPRequest("textDocument/codeAction"):
    type In = structures.CodeActionParams
    type Out = (Vector[(structures.Command | structures.CodeAction)] | Null)
    given reader: Reader[In] = structures.CodeActionParams.codec
    given writer: Writer[Out] = ???
  
  object codeLens extends LSPRequest("textDocument/codeLens"):
    type In = structures.CodeLensParams
    type Out = (Vector[structures.CodeLens] | Null)
    given reader: Reader[In] = structures.CodeLensParams.codec
    given writer: Writer[Out] = ???
  
  object colorPresentation extends LSPRequest("textDocument/colorPresentation"):
    type In = structures.ColorPresentationParams
    type Out = Vector[structures.ColorPresentation]
    given reader: Reader[In] = structures.ColorPresentationParams.codec
    given writer: Writer[Out] = ???
  
  object completion extends LSPRequest("textDocument/completion"):
    type In = structures.CompletionParams
    type Out = (Vector[structures.CompletionItem] | structures.CompletionList | Null)
    given reader: Reader[In] = structures.CompletionParams.codec
    given writer: Writer[Out] = ???
  
  object declaration extends LSPRequest("textDocument/declaration"):
    type In = structures.DeclarationParams
    type Out = (aliases.Declaration | Vector[aliases.DeclarationLink] | Null)
    given reader: Reader[In] = structures.DeclarationParams.codec
    given writer: Writer[Out] = ???
  
  object definition extends LSPRequest("textDocument/definition"):
    type In = structures.DefinitionParams
    type Out = (aliases.Definition | Vector[aliases.DefinitionLink] | Null)
    given reader: Reader[In] = structures.DefinitionParams.codec
    given writer: Writer[Out] = ???
  
  object diagnostic extends LSPRequest("textDocument/diagnostic"):
    type In = structures.DocumentDiagnosticParams
    type Out = aliases.DocumentDiagnosticReport
    given reader: Reader[In] = structures.DocumentDiagnosticParams.codec
    given writer: Writer[Out] = ???
  
  object documentColor extends LSPRequest("textDocument/documentColor"):
    type In = structures.DocumentColorParams
    type Out = Vector[structures.ColorInformation]
    given reader: Reader[In] = structures.DocumentColorParams.codec
    given writer: Writer[Out] = ???
  
  object documentHighlight extends LSPRequest("textDocument/documentHighlight"):
    type In = structures.DocumentHighlightParams
    type Out = (Vector[structures.DocumentHighlight] | Null)
    given reader: Reader[In] = structures.DocumentHighlightParams.codec
    given writer: Writer[Out] = ???
  
  object documentLink extends LSPRequest("textDocument/documentLink"):
    type In = structures.DocumentLinkParams
    type Out = (Vector[structures.DocumentLink] | Null)
    given reader: Reader[In] = structures.DocumentLinkParams.codec
    given writer: Writer[Out] = ???
  
  object documentSymbol extends LSPRequest("textDocument/documentSymbol"):
    type In = structures.DocumentSymbolParams
    type Out = (Vector[structures.SymbolInformation] | Vector[structures.DocumentSymbol] | Null)
    given reader: Reader[In] = structures.DocumentSymbolParams.codec
    given writer: Writer[Out] = ???
  
  object foldingRange extends LSPRequest("textDocument/foldingRange"):
    type In = structures.FoldingRangeParams
    type Out = (Vector[structures.FoldingRange] | Null)
    given reader: Reader[In] = structures.FoldingRangeParams.codec
    given writer: Writer[Out] = ???
  
  object formatting extends LSPRequest("textDocument/formatting"):
    type In = structures.DocumentFormattingParams
    type Out = (Vector[structures.TextEdit] | Null)
    given reader: Reader[In] = structures.DocumentFormattingParams.codec
    given writer: Writer[Out] = ???
  
  object hover extends LSPRequest("textDocument/hover"):
    type In = structures.HoverParams
    type Out = (structures.Hover | Null)
    given reader: Reader[In] = structures.HoverParams.codec
    given writer: Writer[Out] = ???
  
  object implementation extends LSPRequest("textDocument/implementation"):
    type In = structures.ImplementationParams
    type Out = (aliases.Definition | Vector[aliases.DefinitionLink] | Null)
    given reader: Reader[In] = structures.ImplementationParams.codec
    given writer: Writer[Out] = ???
  
  object inlayHint extends LSPRequest("textDocument/inlayHint"):
    type In = structures.InlayHintParams
    type Out = (Vector[structures.InlayHint] | Null)
    given reader: Reader[In] = structures.InlayHintParams.codec
    given writer: Writer[Out] = ???
  
  object inlineValue extends LSPRequest("textDocument/inlineValue"):
    type In = structures.InlineValueParams
    type Out = (Vector[aliases.InlineValue] | Null)
    given reader: Reader[In] = structures.InlineValueParams.codec
    given writer: Writer[Out] = ???
  
  object linkedEditingRange extends LSPRequest("textDocument/linkedEditingRange"):
    type In = structures.LinkedEditingRangeParams
    type Out = (structures.LinkedEditingRanges | Null)
    given reader: Reader[In] = structures.LinkedEditingRangeParams.codec
    given writer: Writer[Out] = ???
  
  object moniker extends LSPRequest("textDocument/moniker"):
    type In = structures.MonikerParams
    type Out = (Vector[structures.Moniker] | Null)
    given reader: Reader[In] = structures.MonikerParams.codec
    given writer: Writer[Out] = ???
  
  object onTypeFormatting extends LSPRequest("textDocument/onTypeFormatting"):
    type In = structures.DocumentOnTypeFormattingParams
    type Out = (Vector[structures.TextEdit] | Null)
    given reader: Reader[In] = structures.DocumentOnTypeFormattingParams.codec
    given writer: Writer[Out] = ???
  
  object prepareCallHierarchy extends LSPRequest("textDocument/prepareCallHierarchy"):
    type In = structures.CallHierarchyPrepareParams
    type Out = (Vector[structures.CallHierarchyItem] | Null)
    given reader: Reader[In] = structures.CallHierarchyPrepareParams.codec
    given writer: Writer[Out] = ???
  
  object prepareRename extends LSPRequest("textDocument/prepareRename"):
    type In = structures.PrepareRenameParams
    type Out = (aliases.PrepareRenameResult | Null)
    given reader: Reader[In] = structures.PrepareRenameParams.codec
    given writer: Writer[Out] = ???
  
  object prepareTypeHierarchy extends LSPRequest("textDocument/prepareTypeHierarchy"):
    type In = structures.TypeHierarchyPrepareParams
    type Out = (Vector[structures.TypeHierarchyItem] | Null)
    given reader: Reader[In] = structures.TypeHierarchyPrepareParams.codec
    given writer: Writer[Out] = ???
  
  object rangeFormatting extends LSPRequest("textDocument/rangeFormatting"):
    type In = structures.DocumentRangeFormattingParams
    type Out = (Vector[structures.TextEdit] | Null)
    given reader: Reader[In] = structures.DocumentRangeFormattingParams.codec
    given writer: Writer[Out] = ???
  
  object references extends LSPRequest("textDocument/references"):
    type In = structures.ReferenceParams
    type Out = (Vector[structures.Location] | Null)
    given reader: Reader[In] = structures.ReferenceParams.codec
    given writer: Writer[Out] = ???
  
  object rename extends LSPRequest("textDocument/rename"):
    type In = structures.RenameParams
    type Out = (structures.WorkspaceEdit | Null)
    given reader: Reader[In] = structures.RenameParams.codec
    given writer: Writer[Out] = ???
  
  object selectionRange extends LSPRequest("textDocument/selectionRange"):
    type In = structures.SelectionRangeParams
    type Out = (Vector[structures.SelectionRange] | Null)
    given reader: Reader[In] = structures.SelectionRangeParams.codec
    given writer: Writer[Out] = ???
  
  object semanticTokens:
    object full extends LSPRequest("textDocument/semanticTokens/full"):
      type In = structures.SemanticTokensParams
      type Out = (structures.SemanticTokens | Null)
      given reader: Reader[In] = structures.SemanticTokensParams.codec
      given writer: Writer[Out] = ???
    
      object delta extends LSPRequest("textDocument/semanticTokens/full/delta"):
        type In = structures.SemanticTokensDeltaParams
        type Out = (structures.SemanticTokens | structures.SemanticTokensDelta | Null)
        given reader: Reader[In] = structures.SemanticTokensDeltaParams.codec
        given writer: Writer[Out] = ???
      
    object range extends LSPRequest("textDocument/semanticTokens/range"):
      type In = structures.SemanticTokensRangeParams
      type Out = (structures.SemanticTokens | Null)
      given reader: Reader[In] = structures.SemanticTokensRangeParams.codec
      given writer: Writer[Out] = ???
    
  object signatureHelp extends LSPRequest("textDocument/signatureHelp"):
    type In = structures.SignatureHelpParams
    type Out = (structures.SignatureHelp | Null)
    given reader: Reader[In] = structures.SignatureHelpParams.codec
    given writer: Writer[Out] = ???
  
  object typeDefinition extends LSPRequest("textDocument/typeDefinition"):
    type In = structures.TypeDefinitionParams
    type Out = (aliases.Definition | Vector[aliases.DefinitionLink] | Null)
    given reader: Reader[In] = structures.TypeDefinitionParams.codec
    given writer: Writer[Out] = ???
  
  object willSaveWaitUntil extends LSPRequest("textDocument/willSaveWaitUntil"):
    type In = structures.WillSaveTextDocumentParams
    type Out = (Vector[structures.TextEdit] | Null)
    given reader: Reader[In] = structures.WillSaveTextDocumentParams.codec
    given writer: Writer[Out] = ???
  
object typeHierarchy:
  object subtypes extends LSPRequest("typeHierarchy/subtypes"):
    type In = structures.TypeHierarchySubtypesParams
    type Out = (Vector[structures.TypeHierarchyItem] | Null)
    given reader: Reader[In] = structures.TypeHierarchySubtypesParams.codec
    given writer: Writer[Out] = ???
  
  object supertypes extends LSPRequest("typeHierarchy/supertypes"):
    type In = structures.TypeHierarchySupertypesParams
    type Out = (Vector[structures.TypeHierarchyItem] | Null)
    given reader: Reader[In] = structures.TypeHierarchySupertypesParams.codec
    given writer: Writer[Out] = ???
  
object window:
  object showDocument extends LSPRequest("window/showDocument"):
    type In = structures.ShowDocumentParams
    type Out = structures.ShowDocumentResult
    given reader: Reader[In] = structures.ShowDocumentParams.codec
    given writer: Writer[Out] = ???
  
  object showMessageRequest extends LSPRequest("window/showMessageRequest"):
    type In = structures.ShowMessageRequestParams
    type Out = (structures.MessageActionItem | Null)
    given reader: Reader[In] = structures.ShowMessageRequestParams.codec
    given writer: Writer[Out] = ???
  
  object workDoneProgress:
    object create extends LSPRequest("window/workDoneProgress/create"):
      type In = structures.WorkDoneProgressCreateParams
      type Out = Null
      given reader: Reader[In] = structures.WorkDoneProgressCreateParams.codec
      given writer: Writer[Out] = ???
    
object workspace:
  object applyEdit extends LSPRequest("workspace/applyEdit"):
    type In = structures.ApplyWorkspaceEditParams
    type Out = structures.ApplyWorkspaceEditResult
    given reader: Reader[In] = structures.ApplyWorkspaceEditParams.codec
    given writer: Writer[Out] = ???
  
  object codeLens:
    object refresh extends LSPRequest("workspace/codeLens/refresh"):
      type In = Unit
      type Out = Null
      given reader: Reader[In] = unitReader
      given writer: Writer[Out] = ???
    
  object configuration extends LSPRequest("workspace/configuration"):
    type In = Any /*AndType(and,Vector(ReferenceType(reference,ConfigurationParams), ReferenceType(reference,PartialResultParams)))*/
    type Out = Vector[aliases.LSPAny]
    given reader: Reader[In] = reader[Any /*AndType(and,Vector(ReferenceType(reference,ConfigurationParams), ReferenceType(reference,PartialResultParams)))*/]
    given writer: Writer[Out] = ???
  
  object diagnostic extends LSPRequest("workspace/diagnostic"):
    type In = structures.WorkspaceDiagnosticParams
    type Out = structures.WorkspaceDiagnosticReport
    given reader: Reader[In] = structures.WorkspaceDiagnosticParams.codec
    given writer: Writer[Out] = ???
  
    object refresh extends LSPRequest("workspace/diagnostic/refresh"):
      type In = Unit
      type Out = Null
      given reader: Reader[In] = unitReader
      given writer: Writer[Out] = ???
    
  object executeCommand extends LSPRequest("workspace/executeCommand"):
    type In = structures.ExecuteCommandParams
    type Out = (aliases.LSPAny | Null)
    given reader: Reader[In] = structures.ExecuteCommandParams.codec
    given writer: Writer[Out] = ???
  
  object inlayHint:
    object refresh extends LSPRequest("workspace/inlayHint/refresh"):
      type In = Unit
      type Out = Null
      given reader: Reader[In] = unitReader
      given writer: Writer[Out] = ???
    
  object inlineValue:
    object refresh extends LSPRequest("workspace/inlineValue/refresh"):
      type In = Unit
      type Out = Null
      given reader: Reader[In] = unitReader
      given writer: Writer[Out] = ???
    
  object semanticTokens:
    object refresh extends LSPRequest("workspace/semanticTokens/refresh"):
      type In = Unit
      type Out = Null
      given reader: Reader[In] = unitReader
      given writer: Writer[Out] = ???
    
  object symbol extends LSPRequest("workspace/symbol"):
    type In = structures.WorkspaceSymbolParams
    type Out = (Vector[structures.SymbolInformation] | Vector[structures.WorkspaceSymbol] | Null)
    given reader: Reader[In] = structures.WorkspaceSymbolParams.codec
    given writer: Writer[Out] = ???
  
  object willCreateFiles extends LSPRequest("workspace/willCreateFiles"):
    type In = structures.CreateFilesParams
    type Out = (structures.WorkspaceEdit | Null)
    given reader: Reader[In] = structures.CreateFilesParams.codec
    given writer: Writer[Out] = ???
  
  object willDeleteFiles extends LSPRequest("workspace/willDeleteFiles"):
    type In = structures.DeleteFilesParams
    type Out = (structures.WorkspaceEdit | Null)
    given reader: Reader[In] = structures.DeleteFilesParams.codec
    given writer: Writer[Out] = ???
  
  object willRenameFiles extends LSPRequest("workspace/willRenameFiles"):
    type In = structures.RenameFilesParams
    type Out = (structures.WorkspaceEdit | Null)
    given reader: Reader[In] = structures.RenameFilesParams.codec
    given writer: Writer[Out] = ???
  
  object workspaceFolders extends LSPRequest("workspace/workspaceFolders"):
    type In = Unit
    type Out = (Vector[structures.WorkspaceFolder] | Null)
    given reader: Reader[In] = unitReader
    given writer: Writer[Out] = ???
  
object workspaceSymbol:
  object resolve extends LSPRequest("workspaceSymbol/resolve"):
    type In = structures.WorkspaceSymbol
    type Out = structures.WorkspaceSymbol
    given reader: Reader[In] = structures.WorkspaceSymbol.codec
    given writer: Writer[Out] = ???
  
