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
object callHierarchy:
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

object inlayHint:
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

object textDocument:
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
  
