package langoustine.lsp
package requests

import langoustine.*
import upickle.default.*
import langoustine.lsp.json.{*, given}

object callHierarchy:
  object incomingCalls extends LSPRequest("callHierarchy/incomingCalls"):
    type In = structures.CallHierarchyIncomingCallsParams
    type Out = (Vector[structures.CallHierarchyIncomingCall] | Null)
    given reader: Reader[In] = structures.CallHierarchyIncomingCallsParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.CallHierarchyIncomingCall]](v.asInstanceOf[Vector[structures.CallHierarchyIncomingCall]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object outgoingCalls extends LSPRequest("callHierarchy/outgoingCalls"):
    type In = structures.CallHierarchyOutgoingCallsParams
    type Out = (Vector[structures.CallHierarchyOutgoingCall] | Null)
    given reader: Reader[In] = structures.CallHierarchyOutgoingCallsParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.CallHierarchyOutgoingCall]](v.asInstanceOf[Vector[structures.CallHierarchyOutgoingCall]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
object client:
  object registerCapability extends LSPRequest("client/registerCapability"):
    type In = structures.RegistrationParams
    type Out = Null
    given reader: Reader[In] = structures.RegistrationParams.reader
    private val _writer: Writer[Out] = 
      nullReadWriter
    given writer: Writer[Out] = _writer
  
  object unregisterCapability extends LSPRequest("client/unregisterCapability"):
    type In = structures.UnregistrationParams
    type Out = Null
    given reader: Reader[In] = structures.UnregistrationParams.reader
    private val _writer: Writer[Out] = 
      nullReadWriter
    given writer: Writer[Out] = _writer
  
object codeAction:
  object resolve extends LSPRequest("codeAction/resolve"):
    type In = structures.CodeAction
    type Out = structures.CodeAction
    given reader: Reader[In] = structures.CodeAction.reader
    private val _writer: Writer[Out] = 
      structures.CodeAction.writer
    given writer: Writer[Out] = _writer
  
object codeLens:
  object resolve extends LSPRequest("codeLens/resolve"):
    type In = structures.CodeLens
    type Out = structures.CodeLens
    given reader: Reader[In] = structures.CodeLens.reader
    private val _writer: Writer[Out] = 
      structures.CodeLens.writer
    given writer: Writer[Out] = _writer
  
object completionItem:
  object resolve extends LSPRequest("completionItem/resolve"):
    type In = structures.CompletionItem
    type Out = structures.CompletionItem
    given reader: Reader[In] = structures.CompletionItem.reader
    private val _writer: Writer[Out] = 
      structures.CompletionItem.writer
    given writer: Writer[Out] = _writer
  
object documentLink:
  object resolve extends LSPRequest("documentLink/resolve"):
    type In = structures.DocumentLink
    type Out = structures.DocumentLink
    given reader: Reader[In] = structures.DocumentLink.reader
    private val _writer: Writer[Out] = 
      structures.DocumentLink.writer
    given writer: Writer[Out] = _writer
  
object initialize extends LSPRequest("initialize"):
  type In = structures.InitializeParams
  type Out = structures.InitializeResult
  given reader: Reader[In] = structures.InitializeParams.reader
  private val _writer: Writer[Out] = 
    structures.InitializeResult.writer
  given writer: Writer[Out] = _writer

object inlayHint:
  object resolve extends LSPRequest("inlayHint/resolve"):
    type In = structures.InlayHint
    type Out = structures.InlayHint
    given reader: Reader[In] = structures.InlayHint.reader
    private val _writer: Writer[Out] = 
      structures.InlayHint.writer
    given writer: Writer[Out] = _writer
  
object shutdown extends LSPRequest("shutdown"):
  type In = Unit
  type Out = Null
  given reader: Reader[In] = unitReader
  private val _writer: Writer[Out] = 
    nullReadWriter
  given writer: Writer[Out] = _writer

object textDocument:
  object codeAction extends LSPRequest("textDocument/codeAction"):
    type In = structures.CodeActionParams
    type Out = (Vector[(structures.Command | structures.CodeAction)] | Null)
    given reader: Reader[In] = structures.CodeActionParams.reader
    private val _writer: Writer[Out] = 
      given Writer[(structures.Command | structures.CodeAction)] = 
        upickle.default.writer[ujson.Value].comap { v => 
          (v: @unchecked) match 
            case v: structures.Command => writeJs(v)
            case v: structures.CodeAction => writeJs(v)
        }
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[(structures.Command | structures.CodeAction)]](v.asInstanceOf[Vector[(structures.Command | structures.CodeAction)]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object codeLens extends LSPRequest("textDocument/codeLens"):
    type In = structures.CodeLensParams
    type Out = (Vector[structures.CodeLens] | Null)
    given reader: Reader[In] = structures.CodeLensParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.CodeLens]](v.asInstanceOf[Vector[structures.CodeLens]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object colorPresentation extends LSPRequest("textDocument/colorPresentation"):
    type In = structures.ColorPresentationParams
    type Out = Vector[structures.ColorPresentation]
    given reader: Reader[In] = structures.ColorPresentationParams.reader
    private val _writer: Writer[Out] = 
      ??? /* arr */
    given writer: Writer[Out] = _writer
  
  object completion extends LSPRequest("textDocument/completion"):
    type In = structures.CompletionParams
    type Out = (Vector[structures.CompletionItem] | structures.CompletionList | Null)
    given reader: Reader[In] = structures.CompletionParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.CompletionItem]](v.asInstanceOf[Vector[structures.CompletionItem]])
          case v: structures.CompletionList => writeJs(v)
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object declaration extends LSPRequest("textDocument/declaration"):
    type In = structures.DeclarationParams
    type Out = (aliases.Declaration | Vector[aliases.DeclarationLink] | Null)
    given reader: Reader[In] = structures.DeclarationParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: aliases.Declaration => writeJs(v)
          case v: Vector[?] => writeJs[Vector[aliases.DeclarationLink]](v.asInstanceOf[Vector[aliases.DeclarationLink]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object definition extends LSPRequest("textDocument/definition"):
    type In = structures.DefinitionParams
    type Out = (aliases.Definition | Vector[aliases.DefinitionLink] | Null)
    given reader: Reader[In] = structures.DefinitionParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: aliases.Definition => writeJs(v)
          case v: Vector[?] => writeJs[Vector[aliases.DefinitionLink]](v.asInstanceOf[Vector[aliases.DefinitionLink]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object diagnostic extends LSPRequest("textDocument/diagnostic"):
    type In = structures.DocumentDiagnosticParams
    type Out = aliases.DocumentDiagnosticReport
    given reader: Reader[In] = structures.DocumentDiagnosticParams.reader
    private val _writer: Writer[Out] = 
      aliases.DocumentDiagnosticReport.writer
    given writer: Writer[Out] = _writer
  
  object documentColor extends LSPRequest("textDocument/documentColor"):
    type In = structures.DocumentColorParams
    type Out = Vector[structures.ColorInformation]
    given reader: Reader[In] = structures.DocumentColorParams.reader
    private val _writer: Writer[Out] = 
      ??? /* arr */
    given writer: Writer[Out] = _writer
  
  object documentHighlight extends LSPRequest("textDocument/documentHighlight"):
    type In = structures.DocumentHighlightParams
    type Out = (Vector[structures.DocumentHighlight] | Null)
    given reader: Reader[In] = structures.DocumentHighlightParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.DocumentHighlight]](v.asInstanceOf[Vector[structures.DocumentHighlight]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object documentLink extends LSPRequest("textDocument/documentLink"):
    type In = structures.DocumentLinkParams
    type Out = (Vector[structures.DocumentLink] | Null)
    given reader: Reader[In] = structures.DocumentLinkParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.DocumentLink]](v.asInstanceOf[Vector[structures.DocumentLink]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object documentSymbol extends LSPRequest("textDocument/documentSymbol"):
    type In = structures.DocumentSymbolParams
    type Out = (Vector[structures.SymbolInformation] | Vector[structures.DocumentSymbol] | Null)
    given reader: Reader[In] = structures.DocumentSymbolParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.SymbolInformation]](v.asInstanceOf[Vector[structures.SymbolInformation]])
          case v: Vector[?] => writeJs[Vector[structures.DocumentSymbol]](v.asInstanceOf[Vector[structures.DocumentSymbol]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object foldingRange extends LSPRequest("textDocument/foldingRange"):
    type In = structures.FoldingRangeParams
    type Out = (Vector[structures.FoldingRange] | Null)
    given reader: Reader[In] = structures.FoldingRangeParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.FoldingRange]](v.asInstanceOf[Vector[structures.FoldingRange]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object formatting extends LSPRequest("textDocument/formatting"):
    type In = structures.DocumentFormattingParams
    type Out = (Vector[structures.TextEdit] | Null)
    given reader: Reader[In] = structures.DocumentFormattingParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.TextEdit]](v.asInstanceOf[Vector[structures.TextEdit]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object hover extends LSPRequest("textDocument/hover"):
    type In = structures.HoverParams
    type Out = (structures.Hover | Null)
    given reader: Reader[In] = structures.HoverParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: structures.Hover => writeJs(v)
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object implementation extends LSPRequest("textDocument/implementation"):
    type In = structures.ImplementationParams
    type Out = (aliases.Definition | Vector[aliases.DefinitionLink] | Null)
    given reader: Reader[In] = structures.ImplementationParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: aliases.Definition => writeJs(v)
          case v: Vector[?] => writeJs[Vector[aliases.DefinitionLink]](v.asInstanceOf[Vector[aliases.DefinitionLink]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object inlayHint extends LSPRequest("textDocument/inlayHint"):
    type In = structures.InlayHintParams
    type Out = (Vector[structures.InlayHint] | Null)
    given reader: Reader[In] = structures.InlayHintParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.InlayHint]](v.asInstanceOf[Vector[structures.InlayHint]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object inlineValue extends LSPRequest("textDocument/inlineValue"):
    type In = structures.InlineValueParams
    type Out = (Vector[aliases.InlineValue] | Null)
    given reader: Reader[In] = structures.InlineValueParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[aliases.InlineValue]](v.asInstanceOf[Vector[aliases.InlineValue]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object linkedEditingRange extends LSPRequest("textDocument/linkedEditingRange"):
    type In = structures.LinkedEditingRangeParams
    type Out = (structures.LinkedEditingRanges | Null)
    given reader: Reader[In] = structures.LinkedEditingRangeParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: structures.LinkedEditingRanges => writeJs(v)
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object moniker extends LSPRequest("textDocument/moniker"):
    type In = structures.MonikerParams
    type Out = (Vector[structures.Moniker] | Null)
    given reader: Reader[In] = structures.MonikerParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.Moniker]](v.asInstanceOf[Vector[structures.Moniker]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object onTypeFormatting extends LSPRequest("textDocument/onTypeFormatting"):
    type In = structures.DocumentOnTypeFormattingParams
    type Out = (Vector[structures.TextEdit] | Null)
    given reader: Reader[In] = structures.DocumentOnTypeFormattingParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.TextEdit]](v.asInstanceOf[Vector[structures.TextEdit]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object prepareCallHierarchy extends LSPRequest("textDocument/prepareCallHierarchy"):
    type In = structures.CallHierarchyPrepareParams
    type Out = (Vector[structures.CallHierarchyItem] | Null)
    given reader: Reader[In] = structures.CallHierarchyPrepareParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.CallHierarchyItem]](v.asInstanceOf[Vector[structures.CallHierarchyItem]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object prepareRename extends LSPRequest("textDocument/prepareRename"):
    type In = structures.PrepareRenameParams
    type Out = (aliases.PrepareRenameResult | Null)
    given reader: Reader[In] = structures.PrepareRenameParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: aliases.PrepareRenameResult => writeJs(v)
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object prepareTypeHierarchy extends LSPRequest("textDocument/prepareTypeHierarchy"):
    type In = structures.TypeHierarchyPrepareParams
    type Out = (Vector[structures.TypeHierarchyItem] | Null)
    given reader: Reader[In] = structures.TypeHierarchyPrepareParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.TypeHierarchyItem]](v.asInstanceOf[Vector[structures.TypeHierarchyItem]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object rangeFormatting extends LSPRequest("textDocument/rangeFormatting"):
    type In = structures.DocumentRangeFormattingParams
    type Out = (Vector[structures.TextEdit] | Null)
    given reader: Reader[In] = structures.DocumentRangeFormattingParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.TextEdit]](v.asInstanceOf[Vector[structures.TextEdit]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object references extends LSPRequest("textDocument/references"):
    type In = structures.ReferenceParams
    type Out = (Vector[structures.Location] | Null)
    given reader: Reader[In] = structures.ReferenceParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.Location]](v.asInstanceOf[Vector[structures.Location]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object rename extends LSPRequest("textDocument/rename"):
    type In = structures.RenameParams
    type Out = (structures.WorkspaceEdit | Null)
    given reader: Reader[In] = structures.RenameParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: structures.WorkspaceEdit => writeJs(v)
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object selectionRange extends LSPRequest("textDocument/selectionRange"):
    type In = structures.SelectionRangeParams
    type Out = (Vector[structures.SelectionRange] | Null)
    given reader: Reader[In] = structures.SelectionRangeParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.SelectionRange]](v.asInstanceOf[Vector[structures.SelectionRange]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object semanticTokens:
    object full extends LSPRequest("textDocument/semanticTokens/full"):
      type In = structures.SemanticTokensParams
      type Out = (structures.SemanticTokens | Null)
      given reader: Reader[In] = structures.SemanticTokensParams.reader
      private val _writer: Writer[Out] = 
        upickle.default.writer[ujson.Value].comap[Out] { v => 
          (v: @unchecked) match 
            case v: structures.SemanticTokens => writeJs(v)
            case null => ujson.Null
        }
      given writer: Writer[Out] = _writer
    
      object delta extends LSPRequest("textDocument/semanticTokens/full/delta"):
        type In = structures.SemanticTokensDeltaParams
        type Out = (structures.SemanticTokens | structures.SemanticTokensDelta | Null)
        given reader: Reader[In] = structures.SemanticTokensDeltaParams.reader
        private val _writer: Writer[Out] = 
          upickle.default.writer[ujson.Value].comap[Out] { v => 
            (v: @unchecked) match 
              case v: structures.SemanticTokens => writeJs(v)
              case v: structures.SemanticTokensDelta => writeJs(v)
              case null => ujson.Null
          }
        given writer: Writer[Out] = _writer
      
    object range extends LSPRequest("textDocument/semanticTokens/range"):
      type In = structures.SemanticTokensRangeParams
      type Out = (structures.SemanticTokens | Null)
      given reader: Reader[In] = structures.SemanticTokensRangeParams.reader
      private val _writer: Writer[Out] = 
        upickle.default.writer[ujson.Value].comap[Out] { v => 
          (v: @unchecked) match 
            case v: structures.SemanticTokens => writeJs(v)
            case null => ujson.Null
        }
      given writer: Writer[Out] = _writer
    
  object signatureHelp extends LSPRequest("textDocument/signatureHelp"):
    type In = structures.SignatureHelpParams
    type Out = (structures.SignatureHelp | Null)
    given reader: Reader[In] = structures.SignatureHelpParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: structures.SignatureHelp => writeJs(v)
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object typeDefinition extends LSPRequest("textDocument/typeDefinition"):
    type In = structures.TypeDefinitionParams
    type Out = (aliases.Definition | Vector[aliases.DefinitionLink] | Null)
    given reader: Reader[In] = structures.TypeDefinitionParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: aliases.Definition => writeJs(v)
          case v: Vector[?] => writeJs[Vector[aliases.DefinitionLink]](v.asInstanceOf[Vector[aliases.DefinitionLink]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object willSaveWaitUntil extends LSPRequest("textDocument/willSaveWaitUntil"):
    type In = structures.WillSaveTextDocumentParams
    type Out = (Vector[structures.TextEdit] | Null)
    given reader: Reader[In] = structures.WillSaveTextDocumentParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.TextEdit]](v.asInstanceOf[Vector[structures.TextEdit]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
object typeHierarchy:
  object subtypes extends LSPRequest("typeHierarchy/subtypes"):
    type In = structures.TypeHierarchySubtypesParams
    type Out = (Vector[structures.TypeHierarchyItem] | Null)
    given reader: Reader[In] = structures.TypeHierarchySubtypesParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.TypeHierarchyItem]](v.asInstanceOf[Vector[structures.TypeHierarchyItem]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object supertypes extends LSPRequest("typeHierarchy/supertypes"):
    type In = structures.TypeHierarchySupertypesParams
    type Out = (Vector[structures.TypeHierarchyItem] | Null)
    given reader: Reader[In] = structures.TypeHierarchySupertypesParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.TypeHierarchyItem]](v.asInstanceOf[Vector[structures.TypeHierarchyItem]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
object window:
  object showDocument extends LSPRequest("window/showDocument"):
    type In = structures.ShowDocumentParams
    type Out = structures.ShowDocumentResult
    given reader: Reader[In] = structures.ShowDocumentParams.reader
    private val _writer: Writer[Out] = 
      structures.ShowDocumentResult.writer
    given writer: Writer[Out] = _writer
  
  object showMessageRequest extends LSPRequest("window/showMessageRequest"):
    type In = structures.ShowMessageRequestParams
    type Out = (structures.MessageActionItem | Null)
    given reader: Reader[In] = structures.ShowMessageRequestParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: structures.MessageActionItem => writeJs(v)
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object workDoneProgress:
    object create extends LSPRequest("window/workDoneProgress/create"):
      type In = structures.WorkDoneProgressCreateParams
      type Out = Null
      given reader: Reader[In] = structures.WorkDoneProgressCreateParams.reader
      private val _writer: Writer[Out] = 
        nullReadWriter
      given writer: Writer[Out] = _writer
    
object workspace:
  object applyEdit extends LSPRequest("workspace/applyEdit"):
    type In = structures.ApplyWorkspaceEditParams
    type Out = structures.ApplyWorkspaceEditResult
    given reader: Reader[In] = structures.ApplyWorkspaceEditParams.reader
    private val _writer: Writer[Out] = 
      structures.ApplyWorkspaceEditResult.writer
    given writer: Writer[Out] = _writer
  
  object codeLens:
    object refresh extends LSPRequest("workspace/codeLens/refresh"):
      type In = Unit
      type Out = Null
      given reader: Reader[In] = unitReader
      private val _writer: Writer[Out] = 
        nullReadWriter
      given writer: Writer[Out] = _writer
    
  object configuration extends LSPRequest("workspace/configuration"):
    type In = Any /*AndType(Vector(ReferenceType(ConfigurationParams), ReferenceType(PartialResultParams)))*/
    type Out = Vector[ujson.Value]
    given reader: Reader[In] = ??? /* TODO: AndType(Vector(ReferenceType(ConfigurationParams), ReferenceType(PartialResultParams)))  */
    private val _writer: Writer[Out] = 
      ??? /* arr */
    given writer: Writer[Out] = _writer
  
  object diagnostic extends LSPRequest("workspace/diagnostic"):
    type In = structures.WorkspaceDiagnosticParams
    type Out = structures.WorkspaceDiagnosticReport
    given reader: Reader[In] = structures.WorkspaceDiagnosticParams.reader
    private val _writer: Writer[Out] = 
      structures.WorkspaceDiagnosticReport.writer
    given writer: Writer[Out] = _writer
  
    object refresh extends LSPRequest("workspace/diagnostic/refresh"):
      type In = Unit
      type Out = Null
      given reader: Reader[In] = unitReader
      private val _writer: Writer[Out] = 
        nullReadWriter
      given writer: Writer[Out] = _writer
    
  object executeCommand extends LSPRequest("workspace/executeCommand"):
    type In = structures.ExecuteCommandParams
    type Out = (ujson.Value | Null)
    given reader: Reader[In] = structures.ExecuteCommandParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: ujson.Value => writeJs(v)
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object inlayHint:
    object refresh extends LSPRequest("workspace/inlayHint/refresh"):
      type In = Unit
      type Out = Null
      given reader: Reader[In] = unitReader
      private val _writer: Writer[Out] = 
        nullReadWriter
      given writer: Writer[Out] = _writer
    
  object inlineValue:
    object refresh extends LSPRequest("workspace/inlineValue/refresh"):
      type In = Unit
      type Out = Null
      given reader: Reader[In] = unitReader
      private val _writer: Writer[Out] = 
        nullReadWriter
      given writer: Writer[Out] = _writer
    
  object semanticTokens:
    object refresh extends LSPRequest("workspace/semanticTokens/refresh"):
      type In = Unit
      type Out = Null
      given reader: Reader[In] = unitReader
      private val _writer: Writer[Out] = 
        nullReadWriter
      given writer: Writer[Out] = _writer
    
  object symbol extends LSPRequest("workspace/symbol"):
    type In = structures.WorkspaceSymbolParams
    type Out = (Vector[structures.SymbolInformation] | Vector[structures.WorkspaceSymbol] | Null)
    given reader: Reader[In] = structures.WorkspaceSymbolParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.SymbolInformation]](v.asInstanceOf[Vector[structures.SymbolInformation]])
          case v: Vector[?] => writeJs[Vector[structures.WorkspaceSymbol]](v.asInstanceOf[Vector[structures.WorkspaceSymbol]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object willCreateFiles extends LSPRequest("workspace/willCreateFiles"):
    type In = structures.CreateFilesParams
    type Out = (structures.WorkspaceEdit | Null)
    given reader: Reader[In] = structures.CreateFilesParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: structures.WorkspaceEdit => writeJs(v)
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object willDeleteFiles extends LSPRequest("workspace/willDeleteFiles"):
    type In = structures.DeleteFilesParams
    type Out = (structures.WorkspaceEdit | Null)
    given reader: Reader[In] = structures.DeleteFilesParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: structures.WorkspaceEdit => writeJs(v)
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object willRenameFiles extends LSPRequest("workspace/willRenameFiles"):
    type In = structures.RenameFilesParams
    type Out = (structures.WorkspaceEdit | Null)
    given reader: Reader[In] = structures.RenameFilesParams.reader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: structures.WorkspaceEdit => writeJs(v)
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
  object workspaceFolders extends LSPRequest("workspace/workspaceFolders"):
    type In = Unit
    type Out = (Vector[structures.WorkspaceFolder] | Null)
    given reader: Reader[In] = unitReader
    private val _writer: Writer[Out] = 
      upickle.default.writer[ujson.Value].comap[Out] { v => 
        (v: @unchecked) match 
          case v: Vector[?] => writeJs[Vector[structures.WorkspaceFolder]](v.asInstanceOf[Vector[structures.WorkspaceFolder]])
          case null => ujson.Null
      }
    given writer: Writer[Out] = _writer
  
object workspaceSymbol:
  object resolve extends LSPRequest("workspaceSymbol/resolve"):
    type In = structures.WorkspaceSymbol
    type Out = structures.WorkspaceSymbol
    given reader: Reader[In] = structures.WorkspaceSymbol.reader
    private val _writer: Writer[Out] = 
      structures.WorkspaceSymbol.writer
    given writer: Writer[Out] = _writer
  
