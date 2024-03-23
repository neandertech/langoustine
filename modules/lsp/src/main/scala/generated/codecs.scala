/*
 * Copyright 2022 Neandertech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package langoustine.lsp
package codecs

import upickle.default.*
import aliases.*
import json.{*, given}
import runtime.{*, given}


private[lsp] trait notifications_$_cancelRequest:
  import requests.$DOLLAR.cancelRequest.In
  given inputReader: Reader[In] = 
    structures.CancelParams.reader
  given inputWriter: Writer[In] = 
    structures.CancelParams.writer


private[lsp] trait notifications_$_logTrace:
  import requests.$DOLLAR.logTrace.In
  given inputReader: Reader[In] = 
    structures.LogTraceParams.reader
  given inputWriter: Writer[In] = 
    structures.LogTraceParams.writer


private[lsp] trait notifications_$_progress:
  import requests.$DOLLAR.progress.In
  given inputReader: Reader[In] = 
    structures.ProgressParams.reader
  given inputWriter: Writer[In] = 
    structures.ProgressParams.writer


private[lsp] trait notifications_$_setTrace:
  import requests.$DOLLAR.setTrace.In
  given inputReader: Reader[In] = 
    structures.SetTraceParams.reader
  given inputWriter: Writer[In] = 
    structures.SetTraceParams.writer

private[lsp] trait requests_callHierarchy_incomingCalls:
  import requests.callHierarchy.incomingCalls.{In, Out}
  given inputReader: Reader[In] = 
    structures.CallHierarchyIncomingCallsParams.reader
  
  given inputWriter: Writer[In] = 
    structures.CallHierarchyIncomingCallsParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.CallHierarchyIncomingCall]](v.asInstanceOf[Vector[structures.CallHierarchyIncomingCall]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.CallHierarchyIncomingCall]], nullReadWriter)

private[lsp] trait requests_callHierarchy_outgoingCalls:
  import requests.callHierarchy.outgoingCalls.{In, Out}
  given inputReader: Reader[In] = 
    structures.CallHierarchyOutgoingCallsParams.reader
  
  given inputWriter: Writer[In] = 
    structures.CallHierarchyOutgoingCallsParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.CallHierarchyOutgoingCall]](v.asInstanceOf[Vector[structures.CallHierarchyOutgoingCall]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.CallHierarchyOutgoingCall]], nullReadWriter)

private[lsp] trait requests_client_registerCapability:
  import requests.client.registerCapability.{In, Out}
  given inputReader: Reader[In] = 
    structures.RegistrationParams.reader
  
  given inputWriter: Writer[In] = 
    structures.RegistrationParams.writer
  
  given outputWriter: Writer[Out] =
    nullReadWriter
  
  given outputReader: Reader[Out] =
    nullReadWriter

private[lsp] trait requests_client_unregisterCapability:
  import requests.client.unregisterCapability.{In, Out}
  given inputReader: Reader[In] = 
    structures.UnregistrationParams.reader
  
  given inputWriter: Writer[In] = 
    structures.UnregistrationParams.writer
  
  given outputWriter: Writer[Out] =
    nullReadWriter
  
  given outputReader: Reader[Out] =
    nullReadWriter

private[lsp] trait requests_codeAction_resolve:
  import requests.codeAction.resolve.{In, Out}
  given inputReader: Reader[In] = 
    structures.CodeAction.reader
  
  given inputWriter: Writer[In] = 
    structures.CodeAction.writer
  
  given outputWriter: Writer[Out] =
    structures.CodeAction.writer
  
  given outputReader: Reader[Out] =
    structures.CodeAction.reader

private[lsp] trait requests_codeLens_resolve:
  import requests.codeLens.resolve.{In, Out}
  given inputReader: Reader[In] = 
    structures.CodeLens.reader
  
  given inputWriter: Writer[In] = 
    structures.CodeLens.writer
  
  given outputWriter: Writer[Out] =
    structures.CodeLens.writer
  
  given outputReader: Reader[Out] =
    structures.CodeLens.reader

private[lsp] trait requests_completionItem_resolve:
  import requests.completionItem.resolve.{In, Out}
  given inputReader: Reader[In] = 
    structures.CompletionItem.reader
  
  given inputWriter: Writer[In] = 
    structures.CompletionItem.writer
  
  given outputWriter: Writer[Out] =
    structures.CompletionItem.writer
  
  given outputReader: Reader[Out] =
    structures.CompletionItem.reader

private[lsp] trait requests_documentLink_resolve:
  import requests.documentLink.resolve.{In, Out}
  given inputReader: Reader[In] = 
    structures.DocumentLink.reader
  
  given inputWriter: Writer[In] = 
    structures.DocumentLink.writer
  
  given outputWriter: Writer[Out] =
    structures.DocumentLink.writer
  
  given outputReader: Reader[Out] =
    structures.DocumentLink.reader


private[lsp] trait notifications_exit:
  import requests.exit.In
  given inputReader: Reader[In] = 
    unitReader
  given inputWriter: Writer[In] = 
    unitWriter

private[lsp] trait requests_initialize:
  import requests.initialize.{In, Out}
  given inputReader: Reader[In] = 
    structures.InitializeParams.reader
  
  given inputWriter: Writer[In] = 
    structures.InitializeParams.writer
  
  given outputWriter: Writer[Out] =
    structures.InitializeResult.writer
  
  given outputReader: Reader[Out] =
    structures.InitializeResult.reader


private[lsp] trait notifications_initialized:
  import requests.initialized.In
  given inputReader: Reader[In] = 
    structures.InitializedParams.reader
  given inputWriter: Writer[In] = 
    structures.InitializedParams.writer

private[lsp] trait requests_inlayHint_resolve:
  import requests.inlayHint.resolve.{In, Out}
  given inputReader: Reader[In] = 
    structures.InlayHint.reader
  
  given inputWriter: Writer[In] = 
    structures.InlayHint.writer
  
  given outputWriter: Writer[Out] =
    structures.InlayHint.writer
  
  given outputReader: Reader[Out] =
    structures.InlayHint.reader


private[lsp] trait notifications_notebookDocument_didChange:
  import requests.notebookDocument.didChange.In
  given inputReader: Reader[In] = 
    structures.DidChangeNotebookDocumentParams.reader
  given inputWriter: Writer[In] = 
    structures.DidChangeNotebookDocumentParams.writer


private[lsp] trait notifications_notebookDocument_didClose:
  import requests.notebookDocument.didClose.In
  given inputReader: Reader[In] = 
    structures.DidCloseNotebookDocumentParams.reader
  given inputWriter: Writer[In] = 
    structures.DidCloseNotebookDocumentParams.writer


private[lsp] trait notifications_notebookDocument_didOpen:
  import requests.notebookDocument.didOpen.In
  given inputReader: Reader[In] = 
    structures.DidOpenNotebookDocumentParams.reader
  given inputWriter: Writer[In] = 
    structures.DidOpenNotebookDocumentParams.writer


private[lsp] trait notifications_notebookDocument_didSave:
  import requests.notebookDocument.didSave.In
  given inputReader: Reader[In] = 
    structures.DidSaveNotebookDocumentParams.reader
  given inputWriter: Writer[In] = 
    structures.DidSaveNotebookDocumentParams.writer

private[lsp] trait requests_shutdown:
  import requests.shutdown.{In, Out}
  given inputReader: Reader[In] = 
    unitReader
  
  given inputWriter: Writer[In] = 
    unitWriter
  
  given outputWriter: Writer[Out] =
    nullReadWriter
  
  given outputReader: Reader[Out] =
    nullReadWriter


private[lsp] trait notifications_telemetry_event:
  import requests.telemetry.event.In
  given inputReader: Reader[In] = 
    jsReader
  given inputWriter: Writer[In] = 
    jsWriter

private[lsp] trait requests_textDocument_codeAction:
  import requests.textDocument.codeAction.{In, Out}
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
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    given Reader[(structures.Command | structures.CodeAction)] = 
      badMerge[(structures.Command | structures.CodeAction)](structures.Command.reader, structures.CodeAction.reader)
    badMerge[Out](upickle.default.reader[Vector[(structures.Command | structures.CodeAction)]], nullReadWriter)

private[lsp] trait requests_textDocument_codeLens:
  import requests.textDocument.codeLens.{In, Out}
  given inputReader: Reader[In] = 
    structures.CodeLensParams.reader
  
  given inputWriter: Writer[In] = 
    structures.CodeLensParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.CodeLens]](v.asInstanceOf[Vector[structures.CodeLens]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.CodeLens]], nullReadWriter)

private[lsp] trait requests_textDocument_colorPresentation:
  import requests.textDocument.colorPresentation.{In, Out}
  given inputReader: Reader[In] = 
    structures.ColorPresentationParams.reader
  
  given inputWriter: Writer[In] = 
    structures.ColorPresentationParams.writer
  
  given outputWriter: Writer[Out] =
    vectorWriter[structures.ColorPresentation]
  
  given outputReader: Reader[Out] =
    vectorReader[structures.ColorPresentation]

private[lsp] trait requests_textDocument_completion:
  import requests.textDocument.completion.{In, Out}
  given inputReader: Reader[In] = 
    structures.CompletionParams.reader
  
  given inputWriter: Writer[In] = 
    structures.CompletionParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.CompletionItem]](v.asInstanceOf[Vector[structures.CompletionItem]])
        case v: structures.CompletionList => writeJs[structures.CompletionList](v)
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.CompletionItem]], structures.CompletionList.reader, nullReadWriter)

private[lsp] trait requests_textDocument_declaration:
  import requests.textDocument.declaration.{In, Out}
  given inputReader: Reader[In] = 
    structures.DeclarationParams.reader
  
  given inputWriter: Writer[In] = 
    structures.DeclarationParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[aliases.DeclarationLink]](v.asInstanceOf[Vector[aliases.DeclarationLink]])
        case v: aliases.Declaration => writeJs[aliases.Declaration](v)
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](aliases.Declaration.reader, upickle.default.reader[Vector[aliases.DeclarationLink]], nullReadWriter)

private[lsp] trait requests_textDocument_definition:
  import requests.textDocument.definition.{In, Out}
  given inputReader: Reader[In] = 
    structures.DefinitionParams.reader
  
  given inputWriter: Writer[In] = 
    structures.DefinitionParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[aliases.DefinitionLink]](v.asInstanceOf[Vector[aliases.DefinitionLink]])
        case v: aliases.Definition => writeJs[aliases.Definition](v)
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](aliases.Definition.reader, upickle.default.reader[Vector[aliases.DefinitionLink]], nullReadWriter)

private[lsp] trait requests_textDocument_diagnostic:
  import requests.textDocument.diagnostic.{In, Out}
  given inputReader: Reader[In] = 
    structures.DocumentDiagnosticParams.reader
  
  given inputWriter: Writer[In] = 
    structures.DocumentDiagnosticParams.writer
  
  given outputWriter: Writer[Out] =
    aliases.DocumentDiagnosticReport.writer
  
  given outputReader: Reader[Out] =
    aliases.DocumentDiagnosticReport.reader


private[lsp] trait notifications_textDocument_didChange:
  import requests.textDocument.didChange.In
  given inputReader: Reader[In] = 
    structures.DidChangeTextDocumentParams.reader
  given inputWriter: Writer[In] = 
    structures.DidChangeTextDocumentParams.writer


private[lsp] trait notifications_textDocument_didClose:
  import requests.textDocument.didClose.In
  given inputReader: Reader[In] = 
    structures.DidCloseTextDocumentParams.reader
  given inputWriter: Writer[In] = 
    structures.DidCloseTextDocumentParams.writer


private[lsp] trait notifications_textDocument_didOpen:
  import requests.textDocument.didOpen.In
  given inputReader: Reader[In] = 
    structures.DidOpenTextDocumentParams.reader
  given inputWriter: Writer[In] = 
    structures.DidOpenTextDocumentParams.writer


private[lsp] trait notifications_textDocument_didSave:
  import requests.textDocument.didSave.In
  given inputReader: Reader[In] = 
    structures.DidSaveTextDocumentParams.reader
  given inputWriter: Writer[In] = 
    structures.DidSaveTextDocumentParams.writer

private[lsp] trait requests_textDocument_documentColor:
  import requests.textDocument.documentColor.{In, Out}
  given inputReader: Reader[In] = 
    structures.DocumentColorParams.reader
  
  given inputWriter: Writer[In] = 
    structures.DocumentColorParams.writer
  
  given outputWriter: Writer[Out] =
    vectorWriter[structures.ColorInformation]
  
  given outputReader: Reader[Out] =
    vectorReader[structures.ColorInformation]

private[lsp] trait requests_textDocument_documentHighlight:
  import requests.textDocument.documentHighlight.{In, Out}
  given inputReader: Reader[In] = 
    structures.DocumentHighlightParams.reader
  
  given inputWriter: Writer[In] = 
    structures.DocumentHighlightParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.DocumentHighlight]](v.asInstanceOf[Vector[structures.DocumentHighlight]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.DocumentHighlight]], nullReadWriter)

private[lsp] trait requests_textDocument_documentLink:
  import requests.textDocument.documentLink.{In, Out}
  given inputReader: Reader[In] = 
    structures.DocumentLinkParams.reader
  
  given inputWriter: Writer[In] = 
    structures.DocumentLinkParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.DocumentLink]](v.asInstanceOf[Vector[structures.DocumentLink]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.DocumentLink]], nullReadWriter)

private[lsp] trait requests_textDocument_documentSymbol:
  import requests.textDocument.documentSymbol.{In, Out}
  given inputReader: Reader[In] = 
    structures.DocumentSymbolParams.reader
  
  given inputWriter: Writer[In] = 
    structures.DocumentSymbolParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => 
          v.headOption match
            case None => writeJs[Vector[structures.SymbolInformation]](v.asInstanceOf[Vector[structures.SymbolInformation]])
            case Some(_: structures.SymbolInformation) => writeJs[Vector[structures.SymbolInformation]](v.asInstanceOf[Vector[structures.SymbolInformation]])
            case Some(_: structures.DocumentSymbol) => writeJs[Vector[structures.DocumentSymbol]](v.asInstanceOf[Vector[structures.DocumentSymbol]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.SymbolInformation]], upickle.default.reader[Vector[structures.DocumentSymbol]], nullReadWriter)

private[lsp] trait requests_textDocument_foldingRange:
  import requests.textDocument.foldingRange.{In, Out}
  given inputReader: Reader[In] = 
    structures.FoldingRangeParams.reader
  
  given inputWriter: Writer[In] = 
    structures.FoldingRangeParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.FoldingRange]](v.asInstanceOf[Vector[structures.FoldingRange]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.FoldingRange]], nullReadWriter)

private[lsp] trait requests_textDocument_formatting:
  import requests.textDocument.formatting.{In, Out}
  given inputReader: Reader[In] = 
    structures.DocumentFormattingParams.reader
  
  given inputWriter: Writer[In] = 
    structures.DocumentFormattingParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.TextEdit]](v.asInstanceOf[Vector[structures.TextEdit]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.TextEdit]], nullReadWriter)

private[lsp] trait requests_textDocument_hover:
  import requests.textDocument.hover.{In, Out}
  given inputReader: Reader[In] = 
    structures.HoverParams.reader
  
  given inputWriter: Writer[In] = 
    structures.HoverParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: structures.Hover => writeJs[structures.Hover](v)
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](structures.Hover.reader, nullReadWriter)

private[lsp] trait requests_textDocument_implementation:
  import requests.textDocument.implementation.{In, Out}
  given inputReader: Reader[In] = 
    structures.ImplementationParams.reader
  
  given inputWriter: Writer[In] = 
    structures.ImplementationParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[aliases.DefinitionLink]](v.asInstanceOf[Vector[aliases.DefinitionLink]])
        case v: aliases.Definition => writeJs[aliases.Definition](v)
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](aliases.Definition.reader, upickle.default.reader[Vector[aliases.DefinitionLink]], nullReadWriter)

private[lsp] trait requests_textDocument_inlayHint:
  import requests.textDocument.inlayHint.{In, Out}
  given inputReader: Reader[In] = 
    structures.InlayHintParams.reader
  
  given inputWriter: Writer[In] = 
    structures.InlayHintParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.InlayHint]](v.asInstanceOf[Vector[structures.InlayHint]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.InlayHint]], nullReadWriter)

private[lsp] trait requests_textDocument_inlineValue:
  import requests.textDocument.inlineValue.{In, Out}
  given inputReader: Reader[In] = 
    structures.InlineValueParams.reader
  
  given inputWriter: Writer[In] = 
    structures.InlineValueParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[aliases.InlineValue]](v.asInstanceOf[Vector[aliases.InlineValue]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[aliases.InlineValue]], nullReadWriter)

private[lsp] trait requests_textDocument_linkedEditingRange:
  import requests.textDocument.linkedEditingRange.{In, Out}
  given inputReader: Reader[In] = 
    structures.LinkedEditingRangeParams.reader
  
  given inputWriter: Writer[In] = 
    structures.LinkedEditingRangeParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: structures.LinkedEditingRanges => writeJs[structures.LinkedEditingRanges](v)
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](structures.LinkedEditingRanges.reader, nullReadWriter)

private[lsp] trait requests_textDocument_moniker:
  import requests.textDocument.moniker.{In, Out}
  given inputReader: Reader[In] = 
    structures.MonikerParams.reader
  
  given inputWriter: Writer[In] = 
    structures.MonikerParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.Moniker]](v.asInstanceOf[Vector[structures.Moniker]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.Moniker]], nullReadWriter)

private[lsp] trait requests_textDocument_onTypeFormatting:
  import requests.textDocument.onTypeFormatting.{In, Out}
  given inputReader: Reader[In] = 
    structures.DocumentOnTypeFormattingParams.reader
  
  given inputWriter: Writer[In] = 
    structures.DocumentOnTypeFormattingParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.TextEdit]](v.asInstanceOf[Vector[structures.TextEdit]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.TextEdit]], nullReadWriter)

private[lsp] trait requests_textDocument_prepareCallHierarchy:
  import requests.textDocument.prepareCallHierarchy.{In, Out}
  given inputReader: Reader[In] = 
    structures.CallHierarchyPrepareParams.reader
  
  given inputWriter: Writer[In] = 
    structures.CallHierarchyPrepareParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.CallHierarchyItem]](v.asInstanceOf[Vector[structures.CallHierarchyItem]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.CallHierarchyItem]], nullReadWriter)

private[lsp] trait requests_textDocument_prepareRename:
  import requests.textDocument.prepareRename.{In, Out}
  given inputReader: Reader[In] = 
    structures.PrepareRenameParams.reader
  
  given inputWriter: Writer[In] = 
    structures.PrepareRenameParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: aliases.PrepareRenameResult => writeJs[aliases.PrepareRenameResult](v)
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](aliases.PrepareRenameResult.reader, nullReadWriter)

private[lsp] trait requests_textDocument_prepareTypeHierarchy:
  import requests.textDocument.prepareTypeHierarchy.{In, Out}
  given inputReader: Reader[In] = 
    structures.TypeHierarchyPrepareParams.reader
  
  given inputWriter: Writer[In] = 
    structures.TypeHierarchyPrepareParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.TypeHierarchyItem]](v.asInstanceOf[Vector[structures.TypeHierarchyItem]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.TypeHierarchyItem]], nullReadWriter)


private[lsp] trait notifications_textDocument_publishDiagnostics:
  import requests.textDocument.publishDiagnostics.In
  given inputReader: Reader[In] = 
    structures.PublishDiagnosticsParams.reader
  given inputWriter: Writer[In] = 
    structures.PublishDiagnosticsParams.writer

private[lsp] trait requests_textDocument_rangeFormatting:
  import requests.textDocument.rangeFormatting.{In, Out}
  given inputReader: Reader[In] = 
    structures.DocumentRangeFormattingParams.reader
  
  given inputWriter: Writer[In] = 
    structures.DocumentRangeFormattingParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.TextEdit]](v.asInstanceOf[Vector[structures.TextEdit]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.TextEdit]], nullReadWriter)

private[lsp] trait requests_textDocument_references:
  import requests.textDocument.references.{In, Out}
  given inputReader: Reader[In] = 
    structures.ReferenceParams.reader
  
  given inputWriter: Writer[In] = 
    structures.ReferenceParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.Location]](v.asInstanceOf[Vector[structures.Location]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.Location]], nullReadWriter)

private[lsp] trait requests_textDocument_rename:
  import requests.textDocument.rename.{In, Out}
  given inputReader: Reader[In] = 
    structures.RenameParams.reader
  
  given inputWriter: Writer[In] = 
    structures.RenameParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: structures.WorkspaceEdit => writeJs[structures.WorkspaceEdit](v)
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](structures.WorkspaceEdit.reader, nullReadWriter)

private[lsp] trait requests_textDocument_selectionRange:
  import requests.textDocument.selectionRange.{In, Out}
  given inputReader: Reader[In] = 
    structures.SelectionRangeParams.reader
  
  given inputWriter: Writer[In] = 
    structures.SelectionRangeParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.SelectionRange]](v.asInstanceOf[Vector[structures.SelectionRange]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.SelectionRange]], nullReadWriter)

private[lsp] trait requests_textDocument_semanticTokens_full:
  import requests.textDocument.semanticTokens.full.{In, Out}
  given inputReader: Reader[In] = 
    structures.SemanticTokensParams.reader
  
  given inputWriter: Writer[In] = 
    structures.SemanticTokensParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: structures.SemanticTokens => writeJs[structures.SemanticTokens](v)
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](structures.SemanticTokens.reader, nullReadWriter)

private[lsp] trait requests_textDocument_semanticTokens_full_delta:
  import requests.textDocument.semanticTokens.full.delta.{In, Out}
  given inputReader: Reader[In] = 
    structures.SemanticTokensDeltaParams.reader
  
  given inputWriter: Writer[In] = 
    structures.SemanticTokensDeltaParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: structures.SemanticTokens => writeJs[structures.SemanticTokens](v)
        case v: structures.SemanticTokensDelta => writeJs[structures.SemanticTokensDelta](v)
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](structures.SemanticTokens.reader, structures.SemanticTokensDelta.reader, nullReadWriter)

private[lsp] trait requests_textDocument_semanticTokens_range:
  import requests.textDocument.semanticTokens.range.{In, Out}
  given inputReader: Reader[In] = 
    structures.SemanticTokensRangeParams.reader
  
  given inputWriter: Writer[In] = 
    structures.SemanticTokensRangeParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: structures.SemanticTokens => writeJs[structures.SemanticTokens](v)
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](structures.SemanticTokens.reader, nullReadWriter)

private[lsp] trait requests_textDocument_signatureHelp:
  import requests.textDocument.signatureHelp.{In, Out}
  given inputReader: Reader[In] = 
    structures.SignatureHelpParams.reader
  
  given inputWriter: Writer[In] = 
    structures.SignatureHelpParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: structures.SignatureHelp => writeJs[structures.SignatureHelp](v)
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](structures.SignatureHelp.reader, nullReadWriter)

private[lsp] trait requests_textDocument_typeDefinition:
  import requests.textDocument.typeDefinition.{In, Out}
  given inputReader: Reader[In] = 
    structures.TypeDefinitionParams.reader
  
  given inputWriter: Writer[In] = 
    structures.TypeDefinitionParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[aliases.DefinitionLink]](v.asInstanceOf[Vector[aliases.DefinitionLink]])
        case v: aliases.Definition => writeJs[aliases.Definition](v)
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](aliases.Definition.reader, upickle.default.reader[Vector[aliases.DefinitionLink]], nullReadWriter)


private[lsp] trait notifications_textDocument_willSave:
  import requests.textDocument.willSave.In
  given inputReader: Reader[In] = 
    structures.WillSaveTextDocumentParams.reader
  given inputWriter: Writer[In] = 
    structures.WillSaveTextDocumentParams.writer

private[lsp] trait requests_textDocument_willSaveWaitUntil:
  import requests.textDocument.willSaveWaitUntil.{In, Out}
  given inputReader: Reader[In] = 
    structures.WillSaveTextDocumentParams.reader
  
  given inputWriter: Writer[In] = 
    structures.WillSaveTextDocumentParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.TextEdit]](v.asInstanceOf[Vector[structures.TextEdit]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.TextEdit]], nullReadWriter)

private[lsp] trait requests_typeHierarchy_subtypes:
  import requests.typeHierarchy.subtypes.{In, Out}
  given inputReader: Reader[In] = 
    structures.TypeHierarchySubtypesParams.reader
  
  given inputWriter: Writer[In] = 
    structures.TypeHierarchySubtypesParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.TypeHierarchyItem]](v.asInstanceOf[Vector[structures.TypeHierarchyItem]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.TypeHierarchyItem]], nullReadWriter)

private[lsp] trait requests_typeHierarchy_supertypes:
  import requests.typeHierarchy.supertypes.{In, Out}
  given inputReader: Reader[In] = 
    structures.TypeHierarchySupertypesParams.reader
  
  given inputWriter: Writer[In] = 
    structures.TypeHierarchySupertypesParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.TypeHierarchyItem]](v.asInstanceOf[Vector[structures.TypeHierarchyItem]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.TypeHierarchyItem]], nullReadWriter)


private[lsp] trait notifications_window_logMessage:
  import requests.window.logMessage.In
  given inputReader: Reader[In] = 
    structures.LogMessageParams.reader
  given inputWriter: Writer[In] = 
    structures.LogMessageParams.writer

private[lsp] trait requests_window_showDocument:
  import requests.window.showDocument.{In, Out}
  given inputReader: Reader[In] = 
    structures.ShowDocumentParams.reader
  
  given inputWriter: Writer[In] = 
    structures.ShowDocumentParams.writer
  
  given outputWriter: Writer[Out] =
    structures.ShowDocumentResult.writer
  
  given outputReader: Reader[Out] =
    structures.ShowDocumentResult.reader


private[lsp] trait notifications_window_showMessage:
  import requests.window.showMessage.In
  given inputReader: Reader[In] = 
    structures.ShowMessageParams.reader
  given inputWriter: Writer[In] = 
    structures.ShowMessageParams.writer

private[lsp] trait requests_window_showMessageRequest:
  import requests.window.showMessageRequest.{In, Out}
  given inputReader: Reader[In] = 
    structures.ShowMessageRequestParams.reader
  
  given inputWriter: Writer[In] = 
    structures.ShowMessageRequestParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: structures.MessageActionItem => writeJs[structures.MessageActionItem](v)
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](structures.MessageActionItem.reader, nullReadWriter)


private[lsp] trait notifications_window_workDoneProgress_cancel:
  import requests.window.workDoneProgress.cancel.In
  given inputReader: Reader[In] = 
    structures.WorkDoneProgressCancelParams.reader
  given inputWriter: Writer[In] = 
    structures.WorkDoneProgressCancelParams.writer

private[lsp] trait requests_window_workDoneProgress_create:
  import requests.window.workDoneProgress.create.{In, Out}
  given inputReader: Reader[In] = 
    structures.WorkDoneProgressCreateParams.reader
  
  given inputWriter: Writer[In] = 
    structures.WorkDoneProgressCreateParams.writer
  
  given outputWriter: Writer[Out] =
    nullReadWriter
  
  given outputReader: Reader[Out] =
    nullReadWriter

private[lsp] trait requests_workspace_applyEdit:
  import requests.workspace.applyEdit.{In, Out}
  given inputReader: Reader[In] = 
    structures.ApplyWorkspaceEditParams.reader
  
  given inputWriter: Writer[In] = 
    structures.ApplyWorkspaceEditParams.writer
  
  given outputWriter: Writer[Out] =
    structures.ApplyWorkspaceEditResult.writer
  
  given outputReader: Reader[Out] =
    structures.ApplyWorkspaceEditResult.reader

private[lsp] trait requests_workspace_codeLens_refresh:
  import requests.workspace.codeLens.refresh.{In, Out}
  given inputReader: Reader[In] = 
    unitReader
  
  given inputWriter: Writer[In] = 
    unitWriter
  
  given outputWriter: Writer[Out] =
    nullReadWriter
  
  given outputReader: Reader[Out] =
    nullReadWriter

private[lsp] trait requests_workspace_configuration:
  import requests.workspace.configuration.{In, Out}
  given inputReader: Reader[In] = 
    structures.ConfigurationParams.reader
  
  given inputWriter: Writer[In] = 
    structures.ConfigurationParams.writer
  
  given outputWriter: Writer[Out] =
    vectorWriter[ujson.Value]
  
  given outputReader: Reader[Out] =
    vectorReader[ujson.Value]

private[lsp] trait requests_workspace_diagnostic:
  import requests.workspace.diagnostic.{In, Out}
  given inputReader: Reader[In] = 
    structures.WorkspaceDiagnosticParams.reader
  
  given inputWriter: Writer[In] = 
    structures.WorkspaceDiagnosticParams.writer
  
  given outputWriter: Writer[Out] =
    structures.WorkspaceDiagnosticReport.writer
  
  given outputReader: Reader[Out] =
    structures.WorkspaceDiagnosticReport.reader

private[lsp] trait requests_workspace_diagnostic_refresh:
  import requests.workspace.diagnostic.refresh.{In, Out}
  given inputReader: Reader[In] = 
    unitReader
  
  given inputWriter: Writer[In] = 
    unitWriter
  
  given outputWriter: Writer[Out] =
    nullReadWriter
  
  given outputReader: Reader[Out] =
    nullReadWriter


private[lsp] trait notifications_workspace_didChangeConfiguration:
  import requests.workspace.didChangeConfiguration.In
  given inputReader: Reader[In] = 
    structures.DidChangeConfigurationParams.reader
  given inputWriter: Writer[In] = 
    structures.DidChangeConfigurationParams.writer


private[lsp] trait notifications_workspace_didChangeWatchedFiles:
  import requests.workspace.didChangeWatchedFiles.In
  given inputReader: Reader[In] = 
    structures.DidChangeWatchedFilesParams.reader
  given inputWriter: Writer[In] = 
    structures.DidChangeWatchedFilesParams.writer


private[lsp] trait notifications_workspace_didChangeWorkspaceFolders:
  import requests.workspace.didChangeWorkspaceFolders.In
  given inputReader: Reader[In] = 
    structures.DidChangeWorkspaceFoldersParams.reader
  given inputWriter: Writer[In] = 
    structures.DidChangeWorkspaceFoldersParams.writer


private[lsp] trait notifications_workspace_didCreateFiles:
  import requests.workspace.didCreateFiles.In
  given inputReader: Reader[In] = 
    structures.CreateFilesParams.reader
  given inputWriter: Writer[In] = 
    structures.CreateFilesParams.writer


private[lsp] trait notifications_workspace_didDeleteFiles:
  import requests.workspace.didDeleteFiles.In
  given inputReader: Reader[In] = 
    structures.DeleteFilesParams.reader
  given inputWriter: Writer[In] = 
    structures.DeleteFilesParams.writer


private[lsp] trait notifications_workspace_didRenameFiles:
  import requests.workspace.didRenameFiles.In
  given inputReader: Reader[In] = 
    structures.RenameFilesParams.reader
  given inputWriter: Writer[In] = 
    structures.RenameFilesParams.writer

private[lsp] trait requests_workspace_executeCommand:
  import requests.workspace.executeCommand.{In, Out}
  given inputReader: Reader[In] = 
    structures.ExecuteCommandParams.reader
  
  given inputWriter: Writer[In] = 
    structures.ExecuteCommandParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: ujson.Value => writeJs[ujson.Value](v)
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](jsReader, nullReadWriter)

private[lsp] trait requests_workspace_inlayHint_refresh:
  import requests.workspace.inlayHint.refresh.{In, Out}
  given inputReader: Reader[In] = 
    unitReader
  
  given inputWriter: Writer[In] = 
    unitWriter
  
  given outputWriter: Writer[Out] =
    nullReadWriter
  
  given outputReader: Reader[Out] =
    nullReadWriter

private[lsp] trait requests_workspace_inlineValue_refresh:
  import requests.workspace.inlineValue.refresh.{In, Out}
  given inputReader: Reader[In] = 
    unitReader
  
  given inputWriter: Writer[In] = 
    unitWriter
  
  given outputWriter: Writer[Out] =
    nullReadWriter
  
  given outputReader: Reader[Out] =
    nullReadWriter

private[lsp] trait requests_workspace_semanticTokens_refresh:
  import requests.workspace.semanticTokens.refresh.{In, Out}
  given inputReader: Reader[In] = 
    unitReader
  
  given inputWriter: Writer[In] = 
    unitWriter
  
  given outputWriter: Writer[Out] =
    nullReadWriter
  
  given outputReader: Reader[Out] =
    nullReadWriter

private[lsp] trait requests_workspace_symbol:
  import requests.workspace.symbol.{In, Out}
  given inputReader: Reader[In] = 
    structures.WorkspaceSymbolParams.reader
  
  given inputWriter: Writer[In] = 
    structures.WorkspaceSymbolParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => 
          v.headOption match
            case None => writeJs[Vector[structures.SymbolInformation]](v.asInstanceOf[Vector[structures.SymbolInformation]])
            case Some(_: structures.SymbolInformation) => writeJs[Vector[structures.SymbolInformation]](v.asInstanceOf[Vector[structures.SymbolInformation]])
            case Some(_: structures.WorkspaceSymbol) => writeJs[Vector[structures.WorkspaceSymbol]](v.asInstanceOf[Vector[structures.WorkspaceSymbol]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.SymbolInformation]], upickle.default.reader[Vector[structures.WorkspaceSymbol]], nullReadWriter)

private[lsp] trait requests_workspace_willCreateFiles:
  import requests.workspace.willCreateFiles.{In, Out}
  given inputReader: Reader[In] = 
    structures.CreateFilesParams.reader
  
  given inputWriter: Writer[In] = 
    structures.CreateFilesParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: structures.WorkspaceEdit => writeJs[structures.WorkspaceEdit](v)
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](structures.WorkspaceEdit.reader, nullReadWriter)

private[lsp] trait requests_workspace_willDeleteFiles:
  import requests.workspace.willDeleteFiles.{In, Out}
  given inputReader: Reader[In] = 
    structures.DeleteFilesParams.reader
  
  given inputWriter: Writer[In] = 
    structures.DeleteFilesParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: structures.WorkspaceEdit => writeJs[structures.WorkspaceEdit](v)
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](structures.WorkspaceEdit.reader, nullReadWriter)

private[lsp] trait requests_workspace_willRenameFiles:
  import requests.workspace.willRenameFiles.{In, Out}
  given inputReader: Reader[In] = 
    structures.RenameFilesParams.reader
  
  given inputWriter: Writer[In] = 
    structures.RenameFilesParams.writer
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: structures.WorkspaceEdit => writeJs[structures.WorkspaceEdit](v)
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](structures.WorkspaceEdit.reader, nullReadWriter)

private[lsp] trait requests_workspace_workspaceFolders:
  import requests.workspace.workspaceFolders.{In, Out}
  given inputReader: Reader[In] = 
    unitReader
  
  given inputWriter: Writer[In] = 
    unitWriter
  
  given outputWriter: Writer[Out] =
    upickle.default.writer[ujson.Value].comap[Out] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.WorkspaceFolder]](v.asInstanceOf[Vector[structures.WorkspaceFolder]])
        case a if a == Opt.empty => ujson.Null
    }
  
  given outputReader: Reader[Out] =
    badMerge[Out](upickle.default.reader[Vector[structures.WorkspaceFolder]], nullReadWriter)

private[lsp] trait requests_workspaceSymbol_resolve:
  import requests.workspaceSymbol.resolve.{In, Out}
  given inputReader: Reader[In] = 
    structures.WorkspaceSymbol.reader
  
  given inputWriter: Writer[In] = 
    structures.WorkspaceSymbol.writer
  
  given outputWriter: Writer[Out] =
    structures.WorkspaceSymbol.writer
  
  given outputReader: Reader[Out] =
    structures.WorkspaceSymbol.reader

private[lsp] trait structures_AnnotatedTextEditCodec:
  import structures.*
  given reader: Reader[structures.AnnotatedTextEdit] = Pickle.macroR
  given writer: Writer[structures.AnnotatedTextEdit] = upickle.default.macroW


private[lsp] trait structures_ApplyWorkspaceEditParamsCodec:
  import structures.*
  given reader: Reader[structures.ApplyWorkspaceEditParams] = Pickle.macroR
  given writer: Writer[structures.ApplyWorkspaceEditParams] = upickle.default.macroW


private[lsp] trait structures_ApplyWorkspaceEditResultCodec:
  import structures.*
  given reader: Reader[structures.ApplyWorkspaceEditResult] = Pickle.macroR
  given writer: Writer[structures.ApplyWorkspaceEditResult] = upickle.default.macroW


private[lsp] trait structures_BaseSymbolInformationCodec:
  import structures.*
  given reader: Reader[structures.BaseSymbolInformation] = Pickle.macroR
  given writer: Writer[structures.BaseSymbolInformation] = upickle.default.macroW


private[lsp] trait structures_CallHierarchyClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.CallHierarchyClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_CallHierarchyIncomingCallCodec:
  import structures.*
  given reader: Reader[structures.CallHierarchyIncomingCall] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyIncomingCall] = upickle.default.macroW


private[lsp] trait structures_CallHierarchyIncomingCallsParamsCodec:
  import structures.*
  given reader: Reader[structures.CallHierarchyIncomingCallsParams] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyIncomingCallsParams] = upickle.default.macroW


private[lsp] trait structures_CallHierarchyItemCodec:
  import structures.*
  given reader: Reader[structures.CallHierarchyItem] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyItem] = upickle.default.macroW


private[lsp] trait structures_CallHierarchyOptionsCodec:
  import structures.*
  given reader: Reader[structures.CallHierarchyOptions] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyOptions] = upickle.default.macroW


private[lsp] trait structures_CallHierarchyOutgoingCallCodec:
  import structures.*
  given reader: Reader[structures.CallHierarchyOutgoingCall] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyOutgoingCall] = upickle.default.macroW


private[lsp] trait structures_CallHierarchyOutgoingCallsParamsCodec:
  import structures.*
  given reader: Reader[structures.CallHierarchyOutgoingCallsParams] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyOutgoingCallsParams] = upickle.default.macroW


private[lsp] trait structures_CallHierarchyPrepareParamsCodec:
  import structures.*
  given reader: Reader[structures.CallHierarchyPrepareParams] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyPrepareParams] = upickle.default.macroW


private[lsp] trait structures_CallHierarchyRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.CallHierarchyRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_CancelParamsCodec:
  import structures.*
  private given rd0: Reader[(Int | String)] = 
    badMerge[(Int | String)](intCodec, stringCodec)
  private given wt0: Writer[(Int | String)] = 
    upickle.default.writer[ujson.Value].comap[(Int | String)] { _v => 
      (_v: @unchecked) match 
        case v: Int => writeJs[Int](v)
        case v: String => writeJs[String](v)
    }
  given reader: Reader[structures.CancelParams] = Pickle.macroR
  given writer: Writer[structures.CancelParams] = upickle.default.macroW


private[lsp] trait structures_ChangeAnnotationCodec:
  import structures.*
  given reader: Reader[structures.ChangeAnnotation] = Pickle.macroR
  given writer: Writer[structures.ChangeAnnotation] = upickle.default.macroW


private[lsp] trait structures_ClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.ClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_CodeActionCodec:
  import structures.*
  given reader: Reader[structures.CodeAction] = Pickle.macroR
  given writer: Writer[structures.CodeAction] = upickle.default.macroW


private[lsp] trait structures_CodeAction_DisabledCodec:
  import structures.CodeAction.*
  given reader: Reader[structures.CodeAction.Disabled] = Pickle.macroR
  given writer: Writer[structures.CodeAction.Disabled] = upickle.default.macroW


private[lsp] trait structures_CodeActionClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.CodeActionClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.CodeActionClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_CodeActionClientCapabilities_CodeActionLiteralSupportCodec:
  import structures.CodeActionClientCapabilities.*
  given reader: Reader[structures.CodeActionClientCapabilities.CodeActionLiteralSupport] = Pickle.macroR
  given writer: Writer[structures.CodeActionClientCapabilities.CodeActionLiteralSupport] = upickle.default.macroW


private[lsp] trait structures_CodeActionClientCapabilities_CodeActionLiteralSupport_CodeActionKindCodec:
  import structures.CodeActionClientCapabilities.CodeActionLiteralSupport.*
  given reader: Reader[structures.CodeActionClientCapabilities.CodeActionLiteralSupport.CodeActionKind] = Pickle.macroR
  given writer: Writer[structures.CodeActionClientCapabilities.CodeActionLiteralSupport.CodeActionKind] = upickle.default.macroW


private[lsp] trait structures_CodeActionClientCapabilities_ResolveSupportCodec:
  import structures.CodeActionClientCapabilities.*
  given reader: Reader[structures.CodeActionClientCapabilities.ResolveSupport] = Pickle.macroR
  given writer: Writer[structures.CodeActionClientCapabilities.ResolveSupport] = upickle.default.macroW


private[lsp] trait structures_CodeActionContextCodec:
  import structures.*
  given reader: Reader[structures.CodeActionContext] = Pickle.macroR
  given writer: Writer[structures.CodeActionContext] = upickle.default.macroW


private[lsp] trait structures_CodeActionOptionsCodec:
  import structures.*
  given reader: Reader[structures.CodeActionOptions] = Pickle.macroR
  given writer: Writer[structures.CodeActionOptions] = upickle.default.macroW


private[lsp] trait structures_CodeActionParamsCodec:
  import structures.*
  given reader: Reader[structures.CodeActionParams] = Pickle.macroR
  given writer: Writer[structures.CodeActionParams] = upickle.default.macroW


private[lsp] trait structures_CodeActionRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.CodeActionRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.CodeActionRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_CodeDescriptionCodec:
  import structures.*
  given reader: Reader[structures.CodeDescription] = Pickle.macroR
  given writer: Writer[structures.CodeDescription] = upickle.default.macroW


private[lsp] trait structures_CodeLensCodec:
  import structures.*
  given reader: Reader[structures.CodeLens] = Pickle.macroR
  given writer: Writer[structures.CodeLens] = upickle.default.macroW


private[lsp] trait structures_CodeLensClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.CodeLensClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.CodeLensClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_CodeLensOptionsCodec:
  import structures.*
  given reader: Reader[structures.CodeLensOptions] = Pickle.macroR
  given writer: Writer[structures.CodeLensOptions] = upickle.default.macroW


private[lsp] trait structures_CodeLensParamsCodec:
  import structures.*
  given reader: Reader[structures.CodeLensParams] = Pickle.macroR
  given writer: Writer[structures.CodeLensParams] = upickle.default.macroW


private[lsp] trait structures_CodeLensRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.CodeLensRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.CodeLensRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_CodeLensWorkspaceClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.CodeLensWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.CodeLensWorkspaceClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_ColorCodec:
  import structures.*
  given reader: Reader[structures.Color] = Pickle.macroR
  given writer: Writer[structures.Color] = upickle.default.macroW


private[lsp] trait structures_ColorInformationCodec:
  import structures.*
  given reader: Reader[structures.ColorInformation] = Pickle.macroR
  given writer: Writer[structures.ColorInformation] = upickle.default.macroW


private[lsp] trait structures_ColorPresentationCodec:
  import structures.*
  given reader: Reader[structures.ColorPresentation] = Pickle.macroR
  given writer: Writer[structures.ColorPresentation] = upickle.default.macroW


private[lsp] trait structures_ColorPresentationParamsCodec:
  import structures.*
  given reader: Reader[structures.ColorPresentationParams] = Pickle.macroR
  given writer: Writer[structures.ColorPresentationParams] = upickle.default.macroW


private[lsp] trait structures_CommandCodec:
  import structures.*
  given reader: Reader[structures.Command] = Pickle.macroR
  given writer: Writer[structures.Command] = upickle.default.macroW


private[lsp] trait structures_CompletionClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.CompletionClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.CompletionClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_CompletionClientCapabilities_CompletionItemCodec:
  import structures.CompletionClientCapabilities.*
  given reader: Reader[structures.CompletionClientCapabilities.CompletionItem] = Pickle.macroR
  given writer: Writer[structures.CompletionClientCapabilities.CompletionItem] = upickle.default.macroW


private[lsp] trait structures_CompletionClientCapabilities_CompletionItem_TagSupportCodec:
  import structures.CompletionClientCapabilities.CompletionItem.*
  given reader: Reader[structures.CompletionClientCapabilities.CompletionItem.TagSupport] = Pickle.macroR
  given writer: Writer[structures.CompletionClientCapabilities.CompletionItem.TagSupport] = upickle.default.macroW


private[lsp] trait structures_CompletionClientCapabilities_CompletionItem_ResolveSupportCodec:
  import structures.CompletionClientCapabilities.CompletionItem.*
  given reader: Reader[structures.CompletionClientCapabilities.CompletionItem.ResolveSupport] = Pickle.macroR
  given writer: Writer[structures.CompletionClientCapabilities.CompletionItem.ResolveSupport] = upickle.default.macroW


private[lsp] trait structures_CompletionClientCapabilities_CompletionItem_InsertTextModeSupportCodec:
  import structures.CompletionClientCapabilities.CompletionItem.*
  given reader: Reader[structures.CompletionClientCapabilities.CompletionItem.InsertTextModeSupport] = Pickle.macroR
  given writer: Writer[structures.CompletionClientCapabilities.CompletionItem.InsertTextModeSupport] = upickle.default.macroW


private[lsp] trait structures_CompletionClientCapabilities_CompletionItemKindCodec:
  import structures.CompletionClientCapabilities.*
  given reader: Reader[structures.CompletionClientCapabilities.CompletionItemKind] = Pickle.macroR
  given writer: Writer[structures.CompletionClientCapabilities.CompletionItemKind] = upickle.default.macroW


private[lsp] trait structures_CompletionClientCapabilities_CompletionListCodec:
  import structures.CompletionClientCapabilities.*
  given reader: Reader[structures.CompletionClientCapabilities.CompletionList] = Pickle.macroR
  given writer: Writer[structures.CompletionClientCapabilities.CompletionList] = upickle.default.macroW


private[lsp] trait structures_CompletionContextCodec:
  import structures.*
  given reader: Reader[structures.CompletionContext] = Pickle.macroR
  given writer: Writer[structures.CompletionContext] = upickle.default.macroW


private[lsp] trait structures_CompletionItemCodec:
  import structures.*
  private given rd0: Reader[(String | structures.MarkupContent)] = 
    badMerge[(String | structures.MarkupContent)](stringCodec, structures.MarkupContent.reader)
  private given wt0: Writer[(String | structures.MarkupContent)] = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: structures.MarkupContent => writeJs[structures.MarkupContent](v)
    }
  private given rd1: Reader[(structures.TextEdit | structures.InsertReplaceEdit)] = 
    badMerge[(structures.TextEdit | structures.InsertReplaceEdit)](structures.TextEdit.reader, structures.InsertReplaceEdit.reader)
  private given wt1: Writer[(structures.TextEdit | structures.InsertReplaceEdit)] = 
    upickle.default.writer[ujson.Value].comap[(structures.TextEdit | structures.InsertReplaceEdit)] { _v => 
      (_v: @unchecked) match 
        case v: structures.TextEdit => writeJs[structures.TextEdit](v)
        case v: structures.InsertReplaceEdit => writeJs[structures.InsertReplaceEdit](v)
    }
  given reader: Reader[structures.CompletionItem] = Pickle.macroR
  given writer: Writer[structures.CompletionItem] = upickle.default.macroW


private[lsp] trait structures_CompletionItemLabelDetailsCodec:
  import structures.*
  given reader: Reader[structures.CompletionItemLabelDetails] = Pickle.macroR
  given writer: Writer[structures.CompletionItemLabelDetails] = upickle.default.macroW


private[lsp] trait structures_CompletionListCodec:
  import structures.*
  given reader: Reader[structures.CompletionList] = Pickle.macroR
  given writer: Writer[structures.CompletionList] = upickle.default.macroW


private[lsp] trait structures_CompletionList_ItemDefaultsCodec:
  import structures.CompletionList.*
  private given rd0: Reader[(structures.Range | ItemDefaults.S0)] = 
    badMerge[(structures.Range | ItemDefaults.S0)](structures.Range.reader, ItemDefaults.S0.reader)
  private given wt0: Writer[(structures.Range | ItemDefaults.S0)] = 
    upickle.default.writer[ujson.Value].comap[(structures.Range | ItemDefaults.S0)] { _v => 
      (_v: @unchecked) match 
        case v: structures.Range => writeJs[structures.Range](v)
        case v: ItemDefaults.S0 => writeJs[ItemDefaults.S0](v)
    }
  given reader: Reader[structures.CompletionList.ItemDefaults] = Pickle.macroR
  given writer: Writer[structures.CompletionList.ItemDefaults] = upickle.default.macroW


private[lsp] trait structures_CompletionList_ItemDefaults_S0Codec:
  import structures.CompletionList.ItemDefaults.*
  given reader: Reader[structures.CompletionList.ItemDefaults.S0] = Pickle.macroR
  given writer: Writer[structures.CompletionList.ItemDefaults.S0] = upickle.default.macroW


private[lsp] trait structures_CompletionOptionsCodec:
  import structures.*
  given reader: Reader[structures.CompletionOptions] = Pickle.macroR
  given writer: Writer[structures.CompletionOptions] = upickle.default.macroW


private[lsp] trait structures_CompletionOptions_CompletionItemCodec:
  import structures.CompletionOptions.*
  given reader: Reader[structures.CompletionOptions.CompletionItem] = Pickle.macroR
  given writer: Writer[structures.CompletionOptions.CompletionItem] = upickle.default.macroW


private[lsp] trait structures_CompletionParamsCodec:
  import structures.*
  given reader: Reader[structures.CompletionParams] = Pickle.macroR
  given writer: Writer[structures.CompletionParams] = upickle.default.macroW


private[lsp] trait structures_CompletionRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.CompletionRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.CompletionRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_CompletionRegistrationOptions_CompletionItemCodec:
  import structures.CompletionRegistrationOptions.*
  given reader: Reader[structures.CompletionRegistrationOptions.CompletionItem] = Pickle.macroR
  given writer: Writer[structures.CompletionRegistrationOptions.CompletionItem] = upickle.default.macroW


private[lsp] trait structures_ConfigurationItemCodec:
  import structures.*
  given reader: Reader[structures.ConfigurationItem] = Pickle.macroR
  given writer: Writer[structures.ConfigurationItem] = upickle.default.macroW


private[lsp] trait structures_ConfigurationParamsCodec:
  import structures.*
  given reader: Reader[structures.ConfigurationParams] = Pickle.macroR
  given writer: Writer[structures.ConfigurationParams] = upickle.default.macroW


private[lsp] trait structures_CreateFileCodec:
  import structures.*
  given reader: Reader[structures.CreateFile] = Pickle.macroR
  given writer: Writer[structures.CreateFile] = upickle.default.macroW


private[lsp] trait structures_CreateFileOptionsCodec:
  import structures.*
  given reader: Reader[structures.CreateFileOptions] = Pickle.macroR
  given writer: Writer[structures.CreateFileOptions] = upickle.default.macroW


private[lsp] trait structures_CreateFilesParamsCodec:
  import structures.*
  given reader: Reader[structures.CreateFilesParams] = Pickle.macroR
  given writer: Writer[structures.CreateFilesParams] = upickle.default.macroW


private[lsp] trait structures_DeclarationClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.DeclarationClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DeclarationClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_DeclarationOptionsCodec:
  import structures.*
  given reader: Reader[structures.DeclarationOptions] = Pickle.macroR
  given writer: Writer[structures.DeclarationOptions] = upickle.default.macroW


private[lsp] trait structures_DeclarationParamsCodec:
  import structures.*
  given reader: Reader[structures.DeclarationParams] = Pickle.macroR
  given writer: Writer[structures.DeclarationParams] = upickle.default.macroW


private[lsp] trait structures_DeclarationRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.DeclarationRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DeclarationRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_DefinitionClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.DefinitionClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DefinitionClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_DefinitionOptionsCodec:
  import structures.*
  given reader: Reader[structures.DefinitionOptions] = Pickle.macroR
  given writer: Writer[structures.DefinitionOptions] = upickle.default.macroW


private[lsp] trait structures_DefinitionParamsCodec:
  import structures.*
  given reader: Reader[structures.DefinitionParams] = Pickle.macroR
  given writer: Writer[structures.DefinitionParams] = upickle.default.macroW


private[lsp] trait structures_DefinitionRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.DefinitionRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DefinitionRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_DeleteFileCodec:
  import structures.*
  given reader: Reader[structures.DeleteFile] = Pickle.macroR
  given writer: Writer[structures.DeleteFile] = upickle.default.macroW


private[lsp] trait structures_DeleteFileOptionsCodec:
  import structures.*
  given reader: Reader[structures.DeleteFileOptions] = Pickle.macroR
  given writer: Writer[structures.DeleteFileOptions] = upickle.default.macroW


private[lsp] trait structures_DeleteFilesParamsCodec:
  import structures.*
  given reader: Reader[structures.DeleteFilesParams] = Pickle.macroR
  given writer: Writer[structures.DeleteFilesParams] = upickle.default.macroW


private[lsp] trait structures_DiagnosticCodec:
  import structures.*
  private given rd0: Reader[(Int | String)] = 
    badMerge[(Int | String)](intCodec, stringCodec)
  private given wt0: Writer[(Int | String)] = 
    upickle.default.writer[ujson.Value].comap[(Int | String)] { _v => 
      (_v: @unchecked) match 
        case v: Int => writeJs[Int](v)
        case v: String => writeJs[String](v)
    }
  given reader: Reader[structures.Diagnostic] = Pickle.macroR
  given writer: Writer[structures.Diagnostic] = upickle.default.macroW


private[lsp] trait structures_DiagnosticClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.DiagnosticClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DiagnosticClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_DiagnosticOptionsCodec:
  import structures.*
  given reader: Reader[structures.DiagnosticOptions] = Pickle.macroR
  given writer: Writer[structures.DiagnosticOptions] = upickle.default.macroW


private[lsp] trait structures_DiagnosticRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.DiagnosticRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DiagnosticRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_DiagnosticRelatedInformationCodec:
  import structures.*
  given reader: Reader[structures.DiagnosticRelatedInformation] = Pickle.macroR
  given writer: Writer[structures.DiagnosticRelatedInformation] = upickle.default.macroW


private[lsp] trait structures_DiagnosticServerCancellationDataCodec:
  import structures.*
  given reader: Reader[structures.DiagnosticServerCancellationData] = Pickle.macroR
  given writer: Writer[structures.DiagnosticServerCancellationData] = upickle.default.macroW


private[lsp] trait structures_DiagnosticWorkspaceClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.DiagnosticWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DiagnosticWorkspaceClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_DidChangeConfigurationClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.DidChangeConfigurationClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DidChangeConfigurationClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_DidChangeConfigurationParamsCodec:
  import structures.*
  given reader: Reader[structures.DidChangeConfigurationParams] = Pickle.macroR
  given writer: Writer[structures.DidChangeConfigurationParams] = upickle.default.macroW


private[lsp] trait structures_DidChangeConfigurationRegistrationOptionsCodec:
  import structures.*
  private given rd0: Reader[(String | Vector[String])] = 
    badMerge[(String | Vector[String])](stringCodec, upickle.default.reader[Vector[String]])
  private given wt0: Writer[(String | Vector[String])] = 
    upickle.default.writer[ujson.Value].comap[(String | Vector[String])] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[String]](v.asInstanceOf[Vector[String]])
        case v: String => writeJs[String](v)
    }
  given reader: Reader[structures.DidChangeConfigurationRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DidChangeConfigurationRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_DidChangeNotebookDocumentParamsCodec:
  import structures.*
  given reader: Reader[structures.DidChangeNotebookDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidChangeNotebookDocumentParams] = upickle.default.macroW


private[lsp] trait structures_DidChangeTextDocumentParamsCodec:
  import structures.*
  given reader: Reader[structures.DidChangeTextDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidChangeTextDocumentParams] = upickle.default.macroW


private[lsp] trait structures_DidChangeWatchedFilesClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.DidChangeWatchedFilesClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DidChangeWatchedFilesClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_DidChangeWatchedFilesParamsCodec:
  import structures.*
  given reader: Reader[structures.DidChangeWatchedFilesParams] = Pickle.macroR
  given writer: Writer[structures.DidChangeWatchedFilesParams] = upickle.default.macroW


private[lsp] trait structures_DidChangeWatchedFilesRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.DidChangeWatchedFilesRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DidChangeWatchedFilesRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_DidChangeWorkspaceFoldersParamsCodec:
  import structures.*
  given reader: Reader[structures.DidChangeWorkspaceFoldersParams] = Pickle.macroR
  given writer: Writer[structures.DidChangeWorkspaceFoldersParams] = upickle.default.macroW


private[lsp] trait structures_DidCloseNotebookDocumentParamsCodec:
  import structures.*
  given reader: Reader[structures.DidCloseNotebookDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidCloseNotebookDocumentParams] = upickle.default.macroW


private[lsp] trait structures_DidCloseTextDocumentParamsCodec:
  import structures.*
  given reader: Reader[structures.DidCloseTextDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidCloseTextDocumentParams] = upickle.default.macroW


private[lsp] trait structures_DidOpenNotebookDocumentParamsCodec:
  import structures.*
  given reader: Reader[structures.DidOpenNotebookDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidOpenNotebookDocumentParams] = upickle.default.macroW


private[lsp] trait structures_DidOpenTextDocumentParamsCodec:
  import structures.*
  given reader: Reader[structures.DidOpenTextDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidOpenTextDocumentParams] = upickle.default.macroW


private[lsp] trait structures_DidSaveNotebookDocumentParamsCodec:
  import structures.*
  given reader: Reader[structures.DidSaveNotebookDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidSaveNotebookDocumentParams] = upickle.default.macroW


private[lsp] trait structures_DidSaveTextDocumentParamsCodec:
  import structures.*
  given reader: Reader[structures.DidSaveTextDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidSaveTextDocumentParams] = upickle.default.macroW


private[lsp] trait structures_DocumentColorClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.DocumentColorClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentColorClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_DocumentColorOptionsCodec:
  import structures.*
  given reader: Reader[structures.DocumentColorOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentColorOptions] = upickle.default.macroW


private[lsp] trait structures_DocumentColorParamsCodec:
  import structures.*
  given reader: Reader[structures.DocumentColorParams] = Pickle.macroR
  given writer: Writer[structures.DocumentColorParams] = upickle.default.macroW


private[lsp] trait structures_DocumentColorRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.DocumentColorRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentColorRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_DocumentDiagnosticParamsCodec:
  import structures.*
  given reader: Reader[structures.DocumentDiagnosticParams] = Pickle.macroR
  given writer: Writer[structures.DocumentDiagnosticParams] = upickle.default.macroW


private[lsp] trait structures_DocumentDiagnosticReportPartialResultCodec:
  import structures.*
  private given rd0: Reader[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = 
    badMerge[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)](structures.FullDocumentDiagnosticReport.reader, structures.UnchangedDocumentDiagnosticReport.reader)
  private given wt0: Writer[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = 
    upickle.default.writer[ujson.Value].comap[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] { _v => 
      (_v: @unchecked) match 
        case v: structures.FullDocumentDiagnosticReport => writeJs[structures.FullDocumentDiagnosticReport](v)
        case v: structures.UnchangedDocumentDiagnosticReport => writeJs[structures.UnchangedDocumentDiagnosticReport](v)
    }
  given reader: Reader[structures.DocumentDiagnosticReportPartialResult] = Pickle.macroR
  given writer: Writer[structures.DocumentDiagnosticReportPartialResult] = upickle.default.macroW


private[lsp] trait structures_DocumentFormattingClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.DocumentFormattingClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentFormattingClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_DocumentFormattingOptionsCodec:
  import structures.*
  given reader: Reader[structures.DocumentFormattingOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentFormattingOptions] = upickle.default.macroW


private[lsp] trait structures_DocumentFormattingParamsCodec:
  import structures.*
  given reader: Reader[structures.DocumentFormattingParams] = Pickle.macroR
  given writer: Writer[structures.DocumentFormattingParams] = upickle.default.macroW


private[lsp] trait structures_DocumentFormattingRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.DocumentFormattingRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentFormattingRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_DocumentHighlightCodec:
  import structures.*
  given reader: Reader[structures.DocumentHighlight] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlight] = upickle.default.macroW


private[lsp] trait structures_DocumentHighlightClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.DocumentHighlightClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlightClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_DocumentHighlightOptionsCodec:
  import structures.*
  given reader: Reader[structures.DocumentHighlightOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlightOptions] = upickle.default.macroW


private[lsp] trait structures_DocumentHighlightParamsCodec:
  import structures.*
  given reader: Reader[structures.DocumentHighlightParams] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlightParams] = upickle.default.macroW


private[lsp] trait structures_DocumentHighlightRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.DocumentHighlightRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlightRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_DocumentLinkCodec:
  import structures.*
  given reader: Reader[structures.DocumentLink] = Pickle.macroR
  given writer: Writer[structures.DocumentLink] = upickle.default.macroW


private[lsp] trait structures_DocumentLinkClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.DocumentLinkClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentLinkClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_DocumentLinkOptionsCodec:
  import structures.*
  given reader: Reader[structures.DocumentLinkOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentLinkOptions] = upickle.default.macroW


private[lsp] trait structures_DocumentLinkParamsCodec:
  import structures.*
  given reader: Reader[structures.DocumentLinkParams] = Pickle.macroR
  given writer: Writer[structures.DocumentLinkParams] = upickle.default.macroW


private[lsp] trait structures_DocumentLinkRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.DocumentLinkRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentLinkRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_DocumentOnTypeFormattingClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.DocumentOnTypeFormattingClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentOnTypeFormattingClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_DocumentOnTypeFormattingOptionsCodec:
  import structures.*
  given reader: Reader[structures.DocumentOnTypeFormattingOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentOnTypeFormattingOptions] = upickle.default.macroW


private[lsp] trait structures_DocumentOnTypeFormattingParamsCodec:
  import structures.*
  given reader: Reader[structures.DocumentOnTypeFormattingParams] = Pickle.macroR
  given writer: Writer[structures.DocumentOnTypeFormattingParams] = upickle.default.macroW


private[lsp] trait structures_DocumentOnTypeFormattingRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.DocumentOnTypeFormattingRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentOnTypeFormattingRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_DocumentRangeFormattingClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.DocumentRangeFormattingClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentRangeFormattingClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_DocumentRangeFormattingOptionsCodec:
  import structures.*
  given reader: Reader[structures.DocumentRangeFormattingOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentRangeFormattingOptions] = upickle.default.macroW


private[lsp] trait structures_DocumentRangeFormattingParamsCodec:
  import structures.*
  given reader: Reader[structures.DocumentRangeFormattingParams] = Pickle.macroR
  given writer: Writer[structures.DocumentRangeFormattingParams] = upickle.default.macroW


private[lsp] trait structures_DocumentRangeFormattingRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.DocumentRangeFormattingRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentRangeFormattingRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_DocumentSymbolCodec:
  import structures.*
  given reader: Reader[structures.DocumentSymbol] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbol] = upickle.default.macroW


private[lsp] trait structures_DocumentSymbolClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.DocumentSymbolClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbolClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_DocumentSymbolClientCapabilities_SymbolKindCodec:
  import structures.DocumentSymbolClientCapabilities.*
  given reader: Reader[structures.DocumentSymbolClientCapabilities.SymbolKind] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbolClientCapabilities.SymbolKind] = upickle.default.macroW


private[lsp] trait structures_DocumentSymbolClientCapabilities_TagSupportCodec:
  import structures.DocumentSymbolClientCapabilities.*
  given reader: Reader[structures.DocumentSymbolClientCapabilities.TagSupport] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbolClientCapabilities.TagSupport] = upickle.default.macroW


private[lsp] trait structures_DocumentSymbolOptionsCodec:
  import structures.*
  given reader: Reader[structures.DocumentSymbolOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbolOptions] = upickle.default.macroW


private[lsp] trait structures_DocumentSymbolParamsCodec:
  import structures.*
  given reader: Reader[structures.DocumentSymbolParams] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbolParams] = upickle.default.macroW


private[lsp] trait structures_DocumentSymbolRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.DocumentSymbolRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbolRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_ExecuteCommandClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.ExecuteCommandClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ExecuteCommandClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_ExecuteCommandOptionsCodec:
  import structures.*
  given reader: Reader[structures.ExecuteCommandOptions] = Pickle.macroR
  given writer: Writer[structures.ExecuteCommandOptions] = upickle.default.macroW


private[lsp] trait structures_ExecuteCommandParamsCodec:
  import structures.*
  given reader: Reader[structures.ExecuteCommandParams] = Pickle.macroR
  given writer: Writer[structures.ExecuteCommandParams] = upickle.default.macroW


private[lsp] trait structures_ExecuteCommandRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.ExecuteCommandRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.ExecuteCommandRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_ExecutionSummaryCodec:
  import structures.*
  given reader: Reader[structures.ExecutionSummary] = Pickle.macroR
  given writer: Writer[structures.ExecutionSummary] = upickle.default.macroW


private[lsp] trait structures_FileCreateCodec:
  import structures.*
  given reader: Reader[structures.FileCreate] = Pickle.macroR
  given writer: Writer[structures.FileCreate] = upickle.default.macroW


private[lsp] trait structures_FileDeleteCodec:
  import structures.*
  given reader: Reader[structures.FileDelete] = Pickle.macroR
  given writer: Writer[structures.FileDelete] = upickle.default.macroW


private[lsp] trait structures_FileEventCodec:
  import structures.*
  given reader: Reader[structures.FileEvent] = Pickle.macroR
  given writer: Writer[structures.FileEvent] = upickle.default.macroW


private[lsp] trait structures_FileOperationClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.FileOperationClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.FileOperationClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_FileOperationFilterCodec:
  import structures.*
  given reader: Reader[structures.FileOperationFilter] = Pickle.macroR
  given writer: Writer[structures.FileOperationFilter] = upickle.default.macroW


private[lsp] trait structures_FileOperationOptionsCodec:
  import structures.*
  given reader: Reader[structures.FileOperationOptions] = Pickle.macroR
  given writer: Writer[structures.FileOperationOptions] = upickle.default.macroW


private[lsp] trait structures_FileOperationPatternCodec:
  import structures.*
  given reader: Reader[structures.FileOperationPattern] = Pickle.macroR
  given writer: Writer[structures.FileOperationPattern] = upickle.default.macroW


private[lsp] trait structures_FileOperationPatternOptionsCodec:
  import structures.*
  given reader: Reader[structures.FileOperationPatternOptions] = Pickle.macroR
  given writer: Writer[structures.FileOperationPatternOptions] = upickle.default.macroW


private[lsp] trait structures_FileOperationRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.FileOperationRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.FileOperationRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_FileRenameCodec:
  import structures.*
  given reader: Reader[structures.FileRename] = Pickle.macroR
  given writer: Writer[structures.FileRename] = upickle.default.macroW


private[lsp] trait structures_FileSystemWatcherCodec:
  import structures.*
  given reader: Reader[structures.FileSystemWatcher] = Pickle.macroR
  given writer: Writer[structures.FileSystemWatcher] = upickle.default.macroW


private[lsp] trait structures_FoldingRangeCodec:
  import structures.*
  given reader: Reader[structures.FoldingRange] = Pickle.macroR
  given writer: Writer[structures.FoldingRange] = upickle.default.macroW


private[lsp] trait structures_FoldingRangeClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.FoldingRangeClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.FoldingRangeClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_FoldingRangeClientCapabilities_FoldingRangeKindCodec:
  import structures.FoldingRangeClientCapabilities.*
  given reader: Reader[structures.FoldingRangeClientCapabilities.FoldingRangeKind] = Pickle.macroR
  given writer: Writer[structures.FoldingRangeClientCapabilities.FoldingRangeKind] = upickle.default.macroW


private[lsp] trait structures_FoldingRangeClientCapabilities_FoldingRangeCodec:
  import structures.FoldingRangeClientCapabilities.*
  given reader: Reader[structures.FoldingRangeClientCapabilities.FoldingRange] = Pickle.macroR
  given writer: Writer[structures.FoldingRangeClientCapabilities.FoldingRange] = upickle.default.macroW


private[lsp] trait structures_FoldingRangeOptionsCodec:
  import structures.*
  given reader: Reader[structures.FoldingRangeOptions] = Pickle.macroR
  given writer: Writer[structures.FoldingRangeOptions] = upickle.default.macroW


private[lsp] trait structures_FoldingRangeParamsCodec:
  import structures.*
  given reader: Reader[structures.FoldingRangeParams] = Pickle.macroR
  given writer: Writer[structures.FoldingRangeParams] = upickle.default.macroW


private[lsp] trait structures_FoldingRangeRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.FoldingRangeRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.FoldingRangeRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_FormattingOptionsCodec:
  import structures.*
  given reader: Reader[structures.FormattingOptions] = Pickle.macroR
  given writer: Writer[structures.FormattingOptions] = upickle.default.macroW


private[lsp] trait structures_FullDocumentDiagnosticReportCodec:
  import structures.*
  given reader: Reader[structures.FullDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.FullDocumentDiagnosticReport] = upickle.default.macroW


private[lsp] trait structures_GeneralClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.GeneralClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.GeneralClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_GeneralClientCapabilities_StaleRequestSupportCodec:
  import structures.GeneralClientCapabilities.*
  given reader: Reader[structures.GeneralClientCapabilities.StaleRequestSupport] = Pickle.macroR
  given writer: Writer[structures.GeneralClientCapabilities.StaleRequestSupport] = upickle.default.macroW


private[lsp] trait structures_HoverCodec:
  import structures.*
  private given rd0: Reader[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])] = 
    badMerge[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])](structures.MarkupContent.reader, aliases.MarkedString.reader, upickle.default.reader[Vector[aliases.MarkedString]])
  private given wt0: Writer[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])] = 
    upickle.default.writer[ujson.Value].comap[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[aliases.MarkedString]](v.asInstanceOf[Vector[aliases.MarkedString]])
        case v: structures.MarkupContent => writeJs[structures.MarkupContent](v)
        case v: aliases.MarkedString => writeJs[aliases.MarkedString](v)
    }
  given reader: Reader[structures.Hover] = Pickle.macroR
  given writer: Writer[structures.Hover] = upickle.default.macroW


private[lsp] trait structures_HoverClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.HoverClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.HoverClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_HoverOptionsCodec:
  import structures.*
  given reader: Reader[structures.HoverOptions] = Pickle.macroR
  given writer: Writer[structures.HoverOptions] = upickle.default.macroW


private[lsp] trait structures_HoverParamsCodec:
  import structures.*
  given reader: Reader[structures.HoverParams] = Pickle.macroR
  given writer: Writer[structures.HoverParams] = upickle.default.macroW


private[lsp] trait structures_HoverRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.HoverRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.HoverRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_ImplementationClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.ImplementationClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ImplementationClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_ImplementationOptionsCodec:
  import structures.*
  given reader: Reader[structures.ImplementationOptions] = Pickle.macroR
  given writer: Writer[structures.ImplementationOptions] = upickle.default.macroW


private[lsp] trait structures_ImplementationParamsCodec:
  import structures.*
  given reader: Reader[structures.ImplementationParams] = Pickle.macroR
  given writer: Writer[structures.ImplementationParams] = upickle.default.macroW


private[lsp] trait structures_ImplementationRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.ImplementationRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.ImplementationRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_InitializeErrorCodec:
  import structures.*
  given reader: Reader[structures.InitializeError] = Pickle.macroR
  given writer: Writer[structures.InitializeError] = upickle.default.macroW


private[lsp] trait structures_InitializeParamsCodec:
  import structures.*
  given reader: Reader[structures.InitializeParams] = Pickle.macroR
  given writer: Writer[structures.InitializeParams] = upickle.default.macroW


private[lsp] trait structures_InitializeParams_ClientInfoCodec:
  import structures.InitializeParams.*
  given reader: Reader[structures.InitializeParams.ClientInfo] = Pickle.macroR
  given writer: Writer[structures.InitializeParams.ClientInfo] = upickle.default.macroW


private[lsp] trait structures_InitializeResultCodec:
  import structures.*
  given reader: Reader[structures.InitializeResult] = Pickle.macroR
  given writer: Writer[structures.InitializeResult] = upickle.default.macroW


private[lsp] trait structures_InitializeResult_ServerInfoCodec:
  import structures.InitializeResult.*
  given reader: Reader[structures.InitializeResult.ServerInfo] = Pickle.macroR
  given writer: Writer[structures.InitializeResult.ServerInfo] = upickle.default.macroW


private[lsp] trait structures_InitializedParamsCodec:
  import structures.*
  given reader: Reader[structures.InitializedParams] = Pickle.macroR
  given writer: Writer[structures.InitializedParams] = upickle.default.macroW


private[lsp] trait structures_InlayHintCodec:
  import structures.*
  private given rd0: Reader[(String | Vector[structures.InlayHintLabelPart])] = 
    badMerge[(String | Vector[structures.InlayHintLabelPart])](stringCodec, upickle.default.reader[Vector[structures.InlayHintLabelPart]])
  private given wt0: Writer[(String | Vector[structures.InlayHintLabelPart])] = 
    upickle.default.writer[ujson.Value].comap[(String | Vector[structures.InlayHintLabelPart])] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.InlayHintLabelPart]](v.asInstanceOf[Vector[structures.InlayHintLabelPart]])
        case v: String => writeJs[String](v)
    }
  private given rd1: Reader[(String | structures.MarkupContent)] = 
    badMerge[(String | structures.MarkupContent)](stringCodec, structures.MarkupContent.reader)
  private given wt1: Writer[(String | structures.MarkupContent)] = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: structures.MarkupContent => writeJs[structures.MarkupContent](v)
    }
  given reader: Reader[structures.InlayHint] = Pickle.macroR
  given writer: Writer[structures.InlayHint] = upickle.default.macroW


private[lsp] trait structures_InlayHintClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.InlayHintClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.InlayHintClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_InlayHintClientCapabilities_ResolveSupportCodec:
  import structures.InlayHintClientCapabilities.*
  given reader: Reader[structures.InlayHintClientCapabilities.ResolveSupport] = Pickle.macroR
  given writer: Writer[structures.InlayHintClientCapabilities.ResolveSupport] = upickle.default.macroW


private[lsp] trait structures_InlayHintLabelPartCodec:
  import structures.*
  private given rd0: Reader[(String | structures.MarkupContent)] = 
    badMerge[(String | structures.MarkupContent)](stringCodec, structures.MarkupContent.reader)
  private given wt0: Writer[(String | structures.MarkupContent)] = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: structures.MarkupContent => writeJs[structures.MarkupContent](v)
    }
  given reader: Reader[structures.InlayHintLabelPart] = Pickle.macroR
  given writer: Writer[structures.InlayHintLabelPart] = upickle.default.macroW


private[lsp] trait structures_InlayHintOptionsCodec:
  import structures.*
  given reader: Reader[structures.InlayHintOptions] = Pickle.macroR
  given writer: Writer[structures.InlayHintOptions] = upickle.default.macroW


private[lsp] trait structures_InlayHintParamsCodec:
  import structures.*
  given reader: Reader[structures.InlayHintParams] = Pickle.macroR
  given writer: Writer[structures.InlayHintParams] = upickle.default.macroW


private[lsp] trait structures_InlayHintRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.InlayHintRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.InlayHintRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_InlayHintWorkspaceClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.InlayHintWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.InlayHintWorkspaceClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_InlineValueClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.InlineValueClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.InlineValueClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_InlineValueContextCodec:
  import structures.*
  given reader: Reader[structures.InlineValueContext] = Pickle.macroR
  given writer: Writer[structures.InlineValueContext] = upickle.default.macroW


private[lsp] trait structures_InlineValueEvaluatableExpressionCodec:
  import structures.*
  given reader: Reader[structures.InlineValueEvaluatableExpression] = Pickle.macroR
  given writer: Writer[structures.InlineValueEvaluatableExpression] = upickle.default.macroW


private[lsp] trait structures_InlineValueOptionsCodec:
  import structures.*
  given reader: Reader[structures.InlineValueOptions] = Pickle.macroR
  given writer: Writer[structures.InlineValueOptions] = upickle.default.macroW


private[lsp] trait structures_InlineValueParamsCodec:
  import structures.*
  given reader: Reader[structures.InlineValueParams] = Pickle.macroR
  given writer: Writer[structures.InlineValueParams] = upickle.default.macroW


private[lsp] trait structures_InlineValueRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.InlineValueRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.InlineValueRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_InlineValueTextCodec:
  import structures.*
  given reader: Reader[structures.InlineValueText] = Pickle.macroR
  given writer: Writer[structures.InlineValueText] = upickle.default.macroW


private[lsp] trait structures_InlineValueVariableLookupCodec:
  import structures.*
  given reader: Reader[structures.InlineValueVariableLookup] = Pickle.macroR
  given writer: Writer[structures.InlineValueVariableLookup] = upickle.default.macroW


private[lsp] trait structures_InlineValueWorkspaceClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.InlineValueWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.InlineValueWorkspaceClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_InsertReplaceEditCodec:
  import structures.*
  given reader: Reader[structures.InsertReplaceEdit] = Pickle.macroR
  given writer: Writer[structures.InsertReplaceEdit] = upickle.default.macroW


private[lsp] trait structures_LinkedEditingRangeClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.LinkedEditingRangeClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.LinkedEditingRangeClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_LinkedEditingRangeOptionsCodec:
  import structures.*
  given reader: Reader[structures.LinkedEditingRangeOptions] = Pickle.macroR
  given writer: Writer[structures.LinkedEditingRangeOptions] = upickle.default.macroW


private[lsp] trait structures_LinkedEditingRangeParamsCodec:
  import structures.*
  given reader: Reader[structures.LinkedEditingRangeParams] = Pickle.macroR
  given writer: Writer[structures.LinkedEditingRangeParams] = upickle.default.macroW


private[lsp] trait structures_LinkedEditingRangeRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.LinkedEditingRangeRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.LinkedEditingRangeRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_LinkedEditingRangesCodec:
  import structures.*
  given reader: Reader[structures.LinkedEditingRanges] = Pickle.macroR
  given writer: Writer[structures.LinkedEditingRanges] = upickle.default.macroW


private[lsp] trait structures_LocationCodec:
  import structures.*
  given reader: Reader[structures.Location] = Pickle.macroR
  given writer: Writer[structures.Location] = upickle.default.macroW


private[lsp] trait structures_LocationLinkCodec:
  import structures.*
  given reader: Reader[structures.LocationLink] = Pickle.macroR
  given writer: Writer[structures.LocationLink] = upickle.default.macroW


private[lsp] trait structures_LogMessageParamsCodec:
  import structures.*
  given reader: Reader[structures.LogMessageParams] = Pickle.macroR
  given writer: Writer[structures.LogMessageParams] = upickle.default.macroW


private[lsp] trait structures_LogTraceParamsCodec:
  import structures.*
  given reader: Reader[structures.LogTraceParams] = Pickle.macroR
  given writer: Writer[structures.LogTraceParams] = upickle.default.macroW


private[lsp] trait structures_MarkdownClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.MarkdownClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.MarkdownClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_MarkupContentCodec:
  import structures.*
  given reader: Reader[structures.MarkupContent] = Pickle.macroR
  given writer: Writer[structures.MarkupContent] = upickle.default.macroW


private[lsp] trait structures_MessageActionItemCodec:
  import structures.*
  given reader: Reader[structures.MessageActionItem] = Pickle.macroR
  given writer: Writer[structures.MessageActionItem] = upickle.default.macroW


private[lsp] trait structures_MonikerCodec:
  import structures.*
  given reader: Reader[structures.Moniker] = Pickle.macroR
  given writer: Writer[structures.Moniker] = upickle.default.macroW


private[lsp] trait structures_MonikerClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.MonikerClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.MonikerClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_MonikerOptionsCodec:
  import structures.*
  given reader: Reader[structures.MonikerOptions] = Pickle.macroR
  given writer: Writer[structures.MonikerOptions] = upickle.default.macroW


private[lsp] trait structures_MonikerParamsCodec:
  import structures.*
  given reader: Reader[structures.MonikerParams] = Pickle.macroR
  given writer: Writer[structures.MonikerParams] = upickle.default.macroW


private[lsp] trait structures_MonikerRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.MonikerRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.MonikerRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_NotebookCellCodec:
  import structures.*
  given reader: Reader[structures.NotebookCell] = Pickle.macroR
  given writer: Writer[structures.NotebookCell] = upickle.default.macroW


private[lsp] trait structures_NotebookCellArrayChangeCodec:
  import structures.*
  given reader: Reader[structures.NotebookCellArrayChange] = Pickle.macroR
  given writer: Writer[structures.NotebookCellArrayChange] = upickle.default.macroW


private[lsp] trait structures_NotebookCellTextDocumentFilterCodec:
  import structures.*
  private given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = 
    badMerge[(String | aliases.NotebookDocumentFilter)](stringCodec, aliases.NotebookDocumentFilter.reader)
  private given wt0: Writer[(String | aliases.NotebookDocumentFilter)] = 
    upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: aliases.NotebookDocumentFilter => writeJs[aliases.NotebookDocumentFilter](v)
    }
  given reader: Reader[structures.NotebookCellTextDocumentFilter] = Pickle.macroR
  given writer: Writer[structures.NotebookCellTextDocumentFilter] = upickle.default.macroW


private[lsp] trait structures_NotebookDocumentCodec:
  import structures.*
  given reader: Reader[structures.NotebookDocument] = Pickle.macroR
  given writer: Writer[structures.NotebookDocument] = upickle.default.macroW


private[lsp] trait structures_NotebookDocumentChangeEventCodec:
  import structures.*
  given reader: Reader[structures.NotebookDocumentChangeEvent] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentChangeEvent] = upickle.default.macroW


private[lsp] trait structures_NotebookDocumentChangeEvent_CellsCodec:
  import structures.NotebookDocumentChangeEvent.*
  given reader: Reader[structures.NotebookDocumentChangeEvent.Cells] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentChangeEvent.Cells] = upickle.default.macroW


private[lsp] trait structures_NotebookDocumentChangeEvent_Cells_StructureCodec:
  import structures.NotebookDocumentChangeEvent.Cells.*
  given reader: Reader[structures.NotebookDocumentChangeEvent.Cells.Structure] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentChangeEvent.Cells.Structure] = upickle.default.macroW


private[lsp] trait structures_NotebookDocumentChangeEvent_Cells_S0Codec:
  import structures.NotebookDocumentChangeEvent.Cells.*
  given reader: Reader[structures.NotebookDocumentChangeEvent.Cells.S0] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentChangeEvent.Cells.S0] = upickle.default.macroW


private[lsp] trait structures_NotebookDocumentClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.NotebookDocumentClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_NotebookDocumentIdentifierCodec:
  import structures.*
  given reader: Reader[structures.NotebookDocumentIdentifier] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentIdentifier] = upickle.default.macroW


private[lsp] trait structures_NotebookDocumentSyncClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.NotebookDocumentSyncClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_NotebookDocumentSyncOptionsCodec:
  import structures.*
  private given rd0: Reader[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)] = 
    badMerge[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)](NotebookDocumentSyncOptions.S0.reader, NotebookDocumentSyncOptions.S1.reader)
  private given wt0: Writer[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)] = 
    upickle.default.writer[ujson.Value].comap[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)] { _v => 
      (_v: @unchecked) match 
        case v: NotebookDocumentSyncOptions.S0 => writeJs[NotebookDocumentSyncOptions.S0](v)
        case v: NotebookDocumentSyncOptions.S1 => writeJs[NotebookDocumentSyncOptions.S1](v)
    }
  given reader: Reader[structures.NotebookDocumentSyncOptions] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncOptions] = upickle.default.macroW


private[lsp] trait structures_NotebookDocumentSyncOptions_S0Codec:
  import structures.NotebookDocumentSyncOptions.*
  private given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = 
    badMerge[(String | aliases.NotebookDocumentFilter)](stringCodec, aliases.NotebookDocumentFilter.reader)
  private given wt0: Writer[(String | aliases.NotebookDocumentFilter)] = 
    upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: aliases.NotebookDocumentFilter => writeJs[aliases.NotebookDocumentFilter](v)
    }
  given reader: Reader[structures.NotebookDocumentSyncOptions.S0] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncOptions.S0] = upickle.default.macroW


private[lsp] trait structures_NotebookDocumentSyncOptions_S0_S0Codec:
  import structures.NotebookDocumentSyncOptions.S0.*
  given reader: Reader[structures.NotebookDocumentSyncOptions.S0.S0] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncOptions.S0.S0] = upickle.default.macroW


private[lsp] trait structures_NotebookDocumentSyncOptions_S1Codec:
  import structures.NotebookDocumentSyncOptions.*
  private given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = 
    badMerge[(String | aliases.NotebookDocumentFilter)](stringCodec, aliases.NotebookDocumentFilter.reader)
  private given wt0: Writer[(String | aliases.NotebookDocumentFilter)] = 
    upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: aliases.NotebookDocumentFilter => writeJs[aliases.NotebookDocumentFilter](v)
    }
  given reader: Reader[structures.NotebookDocumentSyncOptions.S1] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncOptions.S1] = upickle.default.macroW


private[lsp] trait structures_NotebookDocumentSyncOptions_S1_S0Codec:
  import structures.NotebookDocumentSyncOptions.S1.*
  given reader: Reader[structures.NotebookDocumentSyncOptions.S1.S0] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncOptions.S1.S0] = upickle.default.macroW


private[lsp] trait structures_NotebookDocumentSyncRegistrationOptionsCodec:
  import structures.*
  private given rd0: Reader[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)] = 
    badMerge[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)](NotebookDocumentSyncRegistrationOptions.S0.reader, NotebookDocumentSyncRegistrationOptions.S1.reader)
  private given wt0: Writer[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)] = 
    upickle.default.writer[ujson.Value].comap[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)] { _v => 
      (_v: @unchecked) match 
        case v: NotebookDocumentSyncRegistrationOptions.S0 => writeJs[NotebookDocumentSyncRegistrationOptions.S0](v)
        case v: NotebookDocumentSyncRegistrationOptions.S1 => writeJs[NotebookDocumentSyncRegistrationOptions.S1](v)
    }
  given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_NotebookDocumentSyncRegistrationOptions_S0Codec:
  import structures.NotebookDocumentSyncRegistrationOptions.*
  private given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = 
    badMerge[(String | aliases.NotebookDocumentFilter)](stringCodec, aliases.NotebookDocumentFilter.reader)
  private given wt0: Writer[(String | aliases.NotebookDocumentFilter)] = 
    upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: aliases.NotebookDocumentFilter => writeJs[aliases.NotebookDocumentFilter](v)
    }
  given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions.S0] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions.S0] = upickle.default.macroW


private[lsp] trait structures_NotebookDocumentSyncRegistrationOptions_S0_S0Codec:
  import structures.NotebookDocumentSyncRegistrationOptions.S0.*
  given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions.S0.S0] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions.S0.S0] = upickle.default.macroW


private[lsp] trait structures_NotebookDocumentSyncRegistrationOptions_S1Codec:
  import structures.NotebookDocumentSyncRegistrationOptions.*
  private given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = 
    badMerge[(String | aliases.NotebookDocumentFilter)](stringCodec, aliases.NotebookDocumentFilter.reader)
  private given wt0: Writer[(String | aliases.NotebookDocumentFilter)] = 
    upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: aliases.NotebookDocumentFilter => writeJs[aliases.NotebookDocumentFilter](v)
    }
  given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions.S1] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions.S1] = upickle.default.macroW


private[lsp] trait structures_NotebookDocumentSyncRegistrationOptions_S1_S0Codec:
  import structures.NotebookDocumentSyncRegistrationOptions.S1.*
  given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions.S1.S0] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions.S1.S0] = upickle.default.macroW


private[lsp] trait structures_OptionalVersionedTextDocumentIdentifierCodec:
  import structures.*
  given reader: Reader[structures.OptionalVersionedTextDocumentIdentifier] = Pickle.macroR
  given writer: Writer[structures.OptionalVersionedTextDocumentIdentifier] = upickle.default.macroW


private[lsp] trait structures_ParameterInformationCodec:
  import structures.*
  private given rd0: Reader[(String | (runtime.uinteger, runtime.uinteger))] = 
    badMerge[(String | (runtime.uinteger, runtime.uinteger))](stringCodec, upickle.default.reader[(runtime.uinteger, runtime.uinteger)])
  private given wt0: Writer[(String | (runtime.uinteger, runtime.uinteger))] = 
    upickle.default.writer[ujson.Value].comap[(String | (runtime.uinteger, runtime.uinteger))] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: (runtime.uinteger, runtime.uinteger) => writeJs[(runtime.uinteger, runtime.uinteger)](v)
    }
  private given rd1: Reader[(String | structures.MarkupContent)] = 
    badMerge[(String | structures.MarkupContent)](stringCodec, structures.MarkupContent.reader)
  private given wt1: Writer[(String | structures.MarkupContent)] = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: structures.MarkupContent => writeJs[structures.MarkupContent](v)
    }
  given reader: Reader[structures.ParameterInformation] = Pickle.macroR
  given writer: Writer[structures.ParameterInformation] = upickle.default.macroW


private[lsp] trait structures_PartialResultParamsCodec:
  import structures.*
  given reader: Reader[structures.PartialResultParams] = Pickle.macroR
  given writer: Writer[structures.PartialResultParams] = upickle.default.macroW


private[lsp] trait structures_PositionCodec:
  import structures.*
  given reader: Reader[structures.Position] = Pickle.macroR
  given writer: Writer[structures.Position] = upickle.default.macroW


private[lsp] trait structures_PrepareRenameParamsCodec:
  import structures.*
  given reader: Reader[structures.PrepareRenameParams] = Pickle.macroR
  given writer: Writer[structures.PrepareRenameParams] = upickle.default.macroW


private[lsp] trait structures_PreviousResultIdCodec:
  import structures.*
  given reader: Reader[structures.PreviousResultId] = Pickle.macroR
  given writer: Writer[structures.PreviousResultId] = upickle.default.macroW


private[lsp] trait structures_ProgressParamsCodec:
  import structures.*
  given reader: Reader[structures.ProgressParams] = Pickle.macroR
  given writer: Writer[structures.ProgressParams] = upickle.default.macroW


private[lsp] trait structures_PublishDiagnosticsClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.PublishDiagnosticsClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.PublishDiagnosticsClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_PublishDiagnosticsClientCapabilities_TagSupportCodec:
  import structures.PublishDiagnosticsClientCapabilities.*
  given reader: Reader[structures.PublishDiagnosticsClientCapabilities.TagSupport] = Pickle.macroR
  given writer: Writer[structures.PublishDiagnosticsClientCapabilities.TagSupport] = upickle.default.macroW


private[lsp] trait structures_PublishDiagnosticsParamsCodec:
  import structures.*
  given reader: Reader[structures.PublishDiagnosticsParams] = Pickle.macroR
  given writer: Writer[structures.PublishDiagnosticsParams] = upickle.default.macroW


private[lsp] trait structures_RangeCodec:
  import structures.*
  given reader: Reader[structures.Range] = Pickle.macroR
  given writer: Writer[structures.Range] = upickle.default.macroW


private[lsp] trait structures_ReferenceClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.ReferenceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ReferenceClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_ReferenceContextCodec:
  import structures.*
  given reader: Reader[structures.ReferenceContext] = Pickle.macroR
  given writer: Writer[structures.ReferenceContext] = upickle.default.macroW


private[lsp] trait structures_ReferenceOptionsCodec:
  import structures.*
  given reader: Reader[structures.ReferenceOptions] = Pickle.macroR
  given writer: Writer[structures.ReferenceOptions] = upickle.default.macroW


private[lsp] trait structures_ReferenceParamsCodec:
  import structures.*
  given reader: Reader[structures.ReferenceParams] = Pickle.macroR
  given writer: Writer[structures.ReferenceParams] = upickle.default.macroW


private[lsp] trait structures_ReferenceRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.ReferenceRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.ReferenceRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_RegistrationCodec:
  import structures.*
  given reader: Reader[structures.Registration] = Pickle.macroR
  given writer: Writer[structures.Registration] = upickle.default.macroW


private[lsp] trait structures_RegistrationParamsCodec:
  import structures.*
  given reader: Reader[structures.RegistrationParams] = Pickle.macroR
  given writer: Writer[structures.RegistrationParams] = upickle.default.macroW


private[lsp] trait structures_RegularExpressionsClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.RegularExpressionsClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.RegularExpressionsClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_RelatedFullDocumentDiagnosticReportCodec:
  import structures.*
  private given rd0: Reader[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = 
    badMerge[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)](structures.FullDocumentDiagnosticReport.reader, structures.UnchangedDocumentDiagnosticReport.reader)
  private given wt0: Writer[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = 
    upickle.default.writer[ujson.Value].comap[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] { _v => 
      (_v: @unchecked) match 
        case v: structures.FullDocumentDiagnosticReport => writeJs[structures.FullDocumentDiagnosticReport](v)
        case v: structures.UnchangedDocumentDiagnosticReport => writeJs[structures.UnchangedDocumentDiagnosticReport](v)
    }
  given reader: Reader[structures.RelatedFullDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.RelatedFullDocumentDiagnosticReport] = upickle.default.macroW


private[lsp] trait structures_RelatedUnchangedDocumentDiagnosticReportCodec:
  import structures.*
  private given rd0: Reader[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = 
    badMerge[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)](structures.FullDocumentDiagnosticReport.reader, structures.UnchangedDocumentDiagnosticReport.reader)
  private given wt0: Writer[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = 
    upickle.default.writer[ujson.Value].comap[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] { _v => 
      (_v: @unchecked) match 
        case v: structures.FullDocumentDiagnosticReport => writeJs[structures.FullDocumentDiagnosticReport](v)
        case v: structures.UnchangedDocumentDiagnosticReport => writeJs[structures.UnchangedDocumentDiagnosticReport](v)
    }
  given reader: Reader[structures.RelatedUnchangedDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.RelatedUnchangedDocumentDiagnosticReport] = upickle.default.macroW


private[lsp] trait structures_RelativePatternCodec:
  import structures.*
  private given rd0: Reader[(structures.WorkspaceFolder | runtime.Uri)] = 
    badMerge[(structures.WorkspaceFolder | runtime.Uri)](structures.WorkspaceFolder.reader, upickle.default.reader[runtime.Uri])
  private given wt0: Writer[(structures.WorkspaceFolder | runtime.Uri)] = 
    upickle.default.writer[ujson.Value].comap[(structures.WorkspaceFolder | runtime.Uri)] { _v => 
      (_v: @unchecked) match 
        case v: structures.WorkspaceFolder => writeJs[structures.WorkspaceFolder](v)
        case v: runtime.Uri => writeJs[runtime.Uri](v)
    }
  given reader: Reader[structures.RelativePattern] = Pickle.macroR
  given writer: Writer[structures.RelativePattern] = upickle.default.macroW


private[lsp] trait structures_RenameClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.RenameClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.RenameClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_RenameFileCodec:
  import structures.*
  given reader: Reader[structures.RenameFile] = Pickle.macroR
  given writer: Writer[structures.RenameFile] = upickle.default.macroW


private[lsp] trait structures_RenameFileOptionsCodec:
  import structures.*
  given reader: Reader[structures.RenameFileOptions] = Pickle.macroR
  given writer: Writer[structures.RenameFileOptions] = upickle.default.macroW


private[lsp] trait structures_RenameFilesParamsCodec:
  import structures.*
  given reader: Reader[structures.RenameFilesParams] = Pickle.macroR
  given writer: Writer[structures.RenameFilesParams] = upickle.default.macroW


private[lsp] trait structures_RenameOptionsCodec:
  import structures.*
  given reader: Reader[structures.RenameOptions] = Pickle.macroR
  given writer: Writer[structures.RenameOptions] = upickle.default.macroW


private[lsp] trait structures_RenameParamsCodec:
  import structures.*
  given reader: Reader[structures.RenameParams] = Pickle.macroR
  given writer: Writer[structures.RenameParams] = upickle.default.macroW


private[lsp] trait structures_RenameRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.RenameRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.RenameRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_ResourceOperationCodec:
  import structures.*
  given reader: Reader[structures.ResourceOperation] = Pickle.macroR
  given writer: Writer[structures.ResourceOperation] = upickle.default.macroW


private[lsp] trait structures_SaveOptionsCodec:
  import structures.*
  given reader: Reader[structures.SaveOptions] = Pickle.macroR
  given writer: Writer[structures.SaveOptions] = upickle.default.macroW


private[lsp] trait structures_SelectionRangeCodec:
  import structures.*
  given reader: Reader[structures.SelectionRange] = Pickle.macroR
  given writer: Writer[structures.SelectionRange] = upickle.default.macroW


private[lsp] trait structures_SelectionRangeClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.SelectionRangeClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.SelectionRangeClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_SelectionRangeOptionsCodec:
  import structures.*
  given reader: Reader[structures.SelectionRangeOptions] = Pickle.macroR
  given writer: Writer[structures.SelectionRangeOptions] = upickle.default.macroW


private[lsp] trait structures_SelectionRangeParamsCodec:
  import structures.*
  given reader: Reader[structures.SelectionRangeParams] = Pickle.macroR
  given writer: Writer[structures.SelectionRangeParams] = upickle.default.macroW


private[lsp] trait structures_SelectionRangeRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.SelectionRangeRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.SelectionRangeRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensCodec:
  import structures.*
  given reader: Reader[structures.SemanticTokens] = Pickle.macroR
  given writer: Writer[structures.SemanticTokens] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.SemanticTokensClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensClientCapabilities_RequestsCodec:
  import structures.SemanticTokensClientCapabilities.*
  private given rd0: Reader[(Boolean | Requests.S0)] = 
    badMerge[(Boolean | Requests.S0)](upickle.default.reader[Boolean], Requests.S0.reader)
  private given wt0: Writer[(Boolean | Requests.S0)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | Requests.S0)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: Requests.S0 => writeJs[Requests.S0](v)
    }
  private given rd1: Reader[(Boolean | Requests.S1)] = 
    badMerge[(Boolean | Requests.S1)](upickle.default.reader[Boolean], Requests.S1.reader)
  private given wt1: Writer[(Boolean | Requests.S1)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | Requests.S1)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: Requests.S1 => writeJs[Requests.S1](v)
    }
  given reader: Reader[structures.SemanticTokensClientCapabilities.Requests] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensClientCapabilities.Requests] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensClientCapabilities_Requests_S0Codec:
  import structures.SemanticTokensClientCapabilities.Requests.*
  given reader: Reader[structures.SemanticTokensClientCapabilities.Requests.S0] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensClientCapabilities.Requests.S0] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensClientCapabilities_Requests_S1Codec:
  import structures.SemanticTokensClientCapabilities.Requests.*
  given reader: Reader[structures.SemanticTokensClientCapabilities.Requests.S1] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensClientCapabilities.Requests.S1] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensDeltaCodec:
  import structures.*
  given reader: Reader[structures.SemanticTokensDelta] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensDelta] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensDeltaParamsCodec:
  import structures.*
  given reader: Reader[structures.SemanticTokensDeltaParams] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensDeltaParams] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensDeltaPartialResultCodec:
  import structures.*
  given reader: Reader[structures.SemanticTokensDeltaPartialResult] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensDeltaPartialResult] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensEditCodec:
  import structures.*
  given reader: Reader[structures.SemanticTokensEdit] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensEdit] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensLegendCodec:
  import structures.*
  given reader: Reader[structures.SemanticTokensLegend] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensLegend] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensOptionsCodec:
  import structures.*
  private given rd0: Reader[(Boolean | SemanticTokensOptions.S0)] = 
    badMerge[(Boolean | SemanticTokensOptions.S0)](upickle.default.reader[Boolean], SemanticTokensOptions.S0.reader)
  private given wt0: Writer[(Boolean | SemanticTokensOptions.S0)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | SemanticTokensOptions.S0)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: SemanticTokensOptions.S0 => writeJs[SemanticTokensOptions.S0](v)
    }
  private given rd1: Reader[(Boolean | SemanticTokensOptions.S1)] = 
    badMerge[(Boolean | SemanticTokensOptions.S1)](upickle.default.reader[Boolean], SemanticTokensOptions.S1.reader)
  private given wt1: Writer[(Boolean | SemanticTokensOptions.S1)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | SemanticTokensOptions.S1)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: SemanticTokensOptions.S1 => writeJs[SemanticTokensOptions.S1](v)
    }
  given reader: Reader[structures.SemanticTokensOptions] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensOptions] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensOptions_S0Codec:
  import structures.SemanticTokensOptions.*
  given reader: Reader[structures.SemanticTokensOptions.S0] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensOptions.S0] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensOptions_S1Codec:
  import structures.SemanticTokensOptions.*
  given reader: Reader[structures.SemanticTokensOptions.S1] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensOptions.S1] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensParamsCodec:
  import structures.*
  given reader: Reader[structures.SemanticTokensParams] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensParams] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensPartialResultCodec:
  import structures.*
  given reader: Reader[structures.SemanticTokensPartialResult] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensPartialResult] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensRangeParamsCodec:
  import structures.*
  given reader: Reader[structures.SemanticTokensRangeParams] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensRangeParams] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensRegistrationOptionsCodec:
  import structures.*
  private given rd1: Reader[(Boolean | SemanticTokensRegistrationOptions.S0)] = 
    badMerge[(Boolean | SemanticTokensRegistrationOptions.S0)](upickle.default.reader[Boolean], SemanticTokensRegistrationOptions.S0.reader)
  private given wt1: Writer[(Boolean | SemanticTokensRegistrationOptions.S0)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | SemanticTokensRegistrationOptions.S0)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: SemanticTokensRegistrationOptions.S0 => writeJs[SemanticTokensRegistrationOptions.S0](v)
    }
  private given rd2: Reader[(Boolean | SemanticTokensRegistrationOptions.S1)] = 
    badMerge[(Boolean | SemanticTokensRegistrationOptions.S1)](upickle.default.reader[Boolean], SemanticTokensRegistrationOptions.S1.reader)
  private given wt2: Writer[(Boolean | SemanticTokensRegistrationOptions.S1)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | SemanticTokensRegistrationOptions.S1)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: SemanticTokensRegistrationOptions.S1 => writeJs[SemanticTokensRegistrationOptions.S1](v)
    }
  given reader: Reader[structures.SemanticTokensRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensRegistrationOptions_S0Codec:
  import structures.SemanticTokensRegistrationOptions.*
  given reader: Reader[structures.SemanticTokensRegistrationOptions.S0] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensRegistrationOptions.S0] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensRegistrationOptions_S1Codec:
  import structures.SemanticTokensRegistrationOptions.*
  given reader: Reader[structures.SemanticTokensRegistrationOptions.S1] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensRegistrationOptions.S1] = upickle.default.macroW


private[lsp] trait structures_SemanticTokensWorkspaceClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.SemanticTokensWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensWorkspaceClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_ServerCapabilitiesCodec:
  import structures.*
  private given rd0: Reader[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)] = 
    badMerge[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)](structures.TextDocumentSyncOptions.reader, enumerations.TextDocumentSyncKind.reader)
  private given wt0: Writer[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)] = 
    upickle.default.writer[ujson.Value].comap[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)] { _v => 
      (_v: @unchecked) match 
        case v: structures.TextDocumentSyncOptions => writeJs[structures.TextDocumentSyncOptions](v)
        case v: enumerations.TextDocumentSyncKind => writeJs[enumerations.TextDocumentSyncKind](v)
    }
  private given rd1: Reader[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)] = 
    badMerge[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)](structures.NotebookDocumentSyncOptions.reader, structures.NotebookDocumentSyncRegistrationOptions.reader)
  private given wt1: Writer[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: structures.NotebookDocumentSyncOptions => writeJs[structures.NotebookDocumentSyncOptions](v)
        case v: structures.NotebookDocumentSyncRegistrationOptions => writeJs[structures.NotebookDocumentSyncRegistrationOptions](v)
    }
  private given rd2: Reader[(Boolean | structures.HoverOptions)] = 
    badMerge[(Boolean | structures.HoverOptions)](upickle.default.reader[Boolean], structures.HoverOptions.reader)
  private given wt2: Writer[(Boolean | structures.HoverOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.HoverOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.HoverOptions => writeJs[structures.HoverOptions](v)
    }
  private given rd3: Reader[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)] = 
    badMerge[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)](upickle.default.reader[Boolean], structures.DeclarationOptions.reader, structures.DeclarationRegistrationOptions.reader)
  private given wt3: Writer[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DeclarationOptions => writeJs[structures.DeclarationOptions](v)
        case v: structures.DeclarationRegistrationOptions => writeJs[structures.DeclarationRegistrationOptions](v)
    }
  private given rd4: Reader[(Boolean | structures.DefinitionOptions)] = 
    badMerge[(Boolean | structures.DefinitionOptions)](upickle.default.reader[Boolean], structures.DefinitionOptions.reader)
  private given wt4: Writer[(Boolean | structures.DefinitionOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DefinitionOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DefinitionOptions => writeJs[structures.DefinitionOptions](v)
    }
  private given rd5: Reader[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)] = 
    badMerge[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)](upickle.default.reader[Boolean], structures.TypeDefinitionOptions.reader, structures.TypeDefinitionRegistrationOptions.reader)
  private given wt5: Writer[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.TypeDefinitionOptions => writeJs[structures.TypeDefinitionOptions](v)
        case v: structures.TypeDefinitionRegistrationOptions => writeJs[structures.TypeDefinitionRegistrationOptions](v)
    }
  private given rd6: Reader[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)] = 
    badMerge[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)](upickle.default.reader[Boolean], structures.ImplementationOptions.reader, structures.ImplementationRegistrationOptions.reader)
  private given wt6: Writer[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.ImplementationOptions => writeJs[structures.ImplementationOptions](v)
        case v: structures.ImplementationRegistrationOptions => writeJs[structures.ImplementationRegistrationOptions](v)
    }
  private given rd7: Reader[(Boolean | structures.ReferenceOptions)] = 
    badMerge[(Boolean | structures.ReferenceOptions)](upickle.default.reader[Boolean], structures.ReferenceOptions.reader)
  private given wt7: Writer[(Boolean | structures.ReferenceOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.ReferenceOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.ReferenceOptions => writeJs[structures.ReferenceOptions](v)
    }
  private given rd8: Reader[(Boolean | structures.DocumentHighlightOptions)] = 
    badMerge[(Boolean | structures.DocumentHighlightOptions)](upickle.default.reader[Boolean], structures.DocumentHighlightOptions.reader)
  private given wt8: Writer[(Boolean | structures.DocumentHighlightOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentHighlightOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DocumentHighlightOptions => writeJs[structures.DocumentHighlightOptions](v)
    }
  private given rd9: Reader[(Boolean | structures.DocumentSymbolOptions)] = 
    badMerge[(Boolean | structures.DocumentSymbolOptions)](upickle.default.reader[Boolean], structures.DocumentSymbolOptions.reader)
  private given wt9: Writer[(Boolean | structures.DocumentSymbolOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentSymbolOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DocumentSymbolOptions => writeJs[structures.DocumentSymbolOptions](v)
    }
  private given rd10: Reader[(Boolean | structures.CodeActionOptions)] = 
    badMerge[(Boolean | structures.CodeActionOptions)](upickle.default.reader[Boolean], structures.CodeActionOptions.reader)
  private given wt10: Writer[(Boolean | structures.CodeActionOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.CodeActionOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.CodeActionOptions => writeJs[structures.CodeActionOptions](v)
    }
  private given rd11: Reader[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)] = 
    badMerge[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)](upickle.default.reader[Boolean], structures.DocumentColorOptions.reader, structures.DocumentColorRegistrationOptions.reader)
  private given wt11: Writer[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DocumentColorOptions => writeJs[structures.DocumentColorOptions](v)
        case v: structures.DocumentColorRegistrationOptions => writeJs[structures.DocumentColorRegistrationOptions](v)
    }
  private given rd12: Reader[(Boolean | structures.WorkspaceSymbolOptions)] = 
    badMerge[(Boolean | structures.WorkspaceSymbolOptions)](upickle.default.reader[Boolean], structures.WorkspaceSymbolOptions.reader)
  private given wt12: Writer[(Boolean | structures.WorkspaceSymbolOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.WorkspaceSymbolOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.WorkspaceSymbolOptions => writeJs[structures.WorkspaceSymbolOptions](v)
    }
  private given rd13: Reader[(Boolean | structures.DocumentFormattingOptions)] = 
    badMerge[(Boolean | structures.DocumentFormattingOptions)](upickle.default.reader[Boolean], structures.DocumentFormattingOptions.reader)
  private given wt13: Writer[(Boolean | structures.DocumentFormattingOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentFormattingOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DocumentFormattingOptions => writeJs[structures.DocumentFormattingOptions](v)
    }
  private given rd14: Reader[(Boolean | structures.DocumentRangeFormattingOptions)] = 
    badMerge[(Boolean | structures.DocumentRangeFormattingOptions)](upickle.default.reader[Boolean], structures.DocumentRangeFormattingOptions.reader)
  private given wt14: Writer[(Boolean | structures.DocumentRangeFormattingOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentRangeFormattingOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DocumentRangeFormattingOptions => writeJs[structures.DocumentRangeFormattingOptions](v)
    }
  private given rd15: Reader[(Boolean | structures.RenameOptions)] = 
    badMerge[(Boolean | structures.RenameOptions)](upickle.default.reader[Boolean], structures.RenameOptions.reader)
  private given wt15: Writer[(Boolean | structures.RenameOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.RenameOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.RenameOptions => writeJs[structures.RenameOptions](v)
    }
  private given rd16: Reader[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)] = 
    badMerge[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)](upickle.default.reader[Boolean], structures.FoldingRangeOptions.reader, structures.FoldingRangeRegistrationOptions.reader)
  private given wt16: Writer[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.FoldingRangeOptions => writeJs[structures.FoldingRangeOptions](v)
        case v: structures.FoldingRangeRegistrationOptions => writeJs[structures.FoldingRangeRegistrationOptions](v)
    }
  private given rd17: Reader[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)] = 
    badMerge[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)](upickle.default.reader[Boolean], structures.SelectionRangeOptions.reader, structures.SelectionRangeRegistrationOptions.reader)
  private given wt17: Writer[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.SelectionRangeOptions => writeJs[structures.SelectionRangeOptions](v)
        case v: structures.SelectionRangeRegistrationOptions => writeJs[structures.SelectionRangeRegistrationOptions](v)
    }
  private given rd18: Reader[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)] = 
    badMerge[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)](upickle.default.reader[Boolean], structures.CallHierarchyOptions.reader, structures.CallHierarchyRegistrationOptions.reader)
  private given wt18: Writer[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.CallHierarchyOptions => writeJs[structures.CallHierarchyOptions](v)
        case v: structures.CallHierarchyRegistrationOptions => writeJs[structures.CallHierarchyRegistrationOptions](v)
    }
  private given rd19: Reader[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)] = 
    badMerge[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)](upickle.default.reader[Boolean], structures.LinkedEditingRangeOptions.reader, structures.LinkedEditingRangeRegistrationOptions.reader)
  private given wt19: Writer[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.LinkedEditingRangeOptions => writeJs[structures.LinkedEditingRangeOptions](v)
        case v: structures.LinkedEditingRangeRegistrationOptions => writeJs[structures.LinkedEditingRangeRegistrationOptions](v)
    }
  private given rd20: Reader[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)] = 
    badMerge[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)](structures.SemanticTokensOptions.reader, structures.SemanticTokensRegistrationOptions.reader)
  private given wt20: Writer[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: structures.SemanticTokensOptions => writeJs[structures.SemanticTokensOptions](v)
        case v: structures.SemanticTokensRegistrationOptions => writeJs[structures.SemanticTokensRegistrationOptions](v)
    }
  private given rd21: Reader[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)] = 
    badMerge[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)](upickle.default.reader[Boolean], structures.MonikerOptions.reader, structures.MonikerRegistrationOptions.reader)
  private given wt21: Writer[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.MonikerOptions => writeJs[structures.MonikerOptions](v)
        case v: structures.MonikerRegistrationOptions => writeJs[structures.MonikerRegistrationOptions](v)
    }
  private given rd22: Reader[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)] = 
    badMerge[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)](upickle.default.reader[Boolean], structures.TypeHierarchyOptions.reader, structures.TypeHierarchyRegistrationOptions.reader)
  private given wt22: Writer[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.TypeHierarchyOptions => writeJs[structures.TypeHierarchyOptions](v)
        case v: structures.TypeHierarchyRegistrationOptions => writeJs[structures.TypeHierarchyRegistrationOptions](v)
    }
  private given rd23: Reader[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)] = 
    badMerge[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)](upickle.default.reader[Boolean], structures.InlineValueOptions.reader, structures.InlineValueRegistrationOptions.reader)
  private given wt23: Writer[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.InlineValueOptions => writeJs[structures.InlineValueOptions](v)
        case v: structures.InlineValueRegistrationOptions => writeJs[structures.InlineValueRegistrationOptions](v)
    }
  private given rd24: Reader[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)] = 
    badMerge[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)](upickle.default.reader[Boolean], structures.InlayHintOptions.reader, structures.InlayHintRegistrationOptions.reader)
  private given wt24: Writer[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.InlayHintOptions => writeJs[structures.InlayHintOptions](v)
        case v: structures.InlayHintRegistrationOptions => writeJs[structures.InlayHintRegistrationOptions](v)
    }
  private given rd25: Reader[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)] = 
    badMerge[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)](structures.DiagnosticOptions.reader, structures.DiagnosticRegistrationOptions.reader)
  private given wt25: Writer[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: structures.DiagnosticOptions => writeJs[structures.DiagnosticOptions](v)
        case v: structures.DiagnosticRegistrationOptions => writeJs[structures.DiagnosticRegistrationOptions](v)
    }
  given reader: Reader[structures.ServerCapabilities] = Pickle.macroR
  given writer: Writer[structures.ServerCapabilities] = upickle.default.macroW


private[lsp] trait structures_ServerCapabilities_WorkspaceCodec:
  import structures.ServerCapabilities.*
  given reader: Reader[structures.ServerCapabilities.Workspace] = Pickle.macroR
  given writer: Writer[structures.ServerCapabilities.Workspace] = upickle.default.macroW


private[lsp] trait structures_SetTraceParamsCodec:
  import structures.*
  given reader: Reader[structures.SetTraceParams] = Pickle.macroR
  given writer: Writer[structures.SetTraceParams] = upickle.default.macroW


private[lsp] trait structures_ShowDocumentClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.ShowDocumentClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ShowDocumentClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_ShowDocumentParamsCodec:
  import structures.*
  given reader: Reader[structures.ShowDocumentParams] = Pickle.macroR
  given writer: Writer[structures.ShowDocumentParams] = upickle.default.macroW


private[lsp] trait structures_ShowDocumentResultCodec:
  import structures.*
  given reader: Reader[structures.ShowDocumentResult] = Pickle.macroR
  given writer: Writer[structures.ShowDocumentResult] = upickle.default.macroW


private[lsp] trait structures_ShowMessageParamsCodec:
  import structures.*
  given reader: Reader[structures.ShowMessageParams] = Pickle.macroR
  given writer: Writer[structures.ShowMessageParams] = upickle.default.macroW


private[lsp] trait structures_ShowMessageRequestClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.ShowMessageRequestClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ShowMessageRequestClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_ShowMessageRequestClientCapabilities_MessageActionItemCodec:
  import structures.ShowMessageRequestClientCapabilities.*
  given reader: Reader[structures.ShowMessageRequestClientCapabilities.MessageActionItem] = Pickle.macroR
  given writer: Writer[structures.ShowMessageRequestClientCapabilities.MessageActionItem] = upickle.default.macroW


private[lsp] trait structures_ShowMessageRequestParamsCodec:
  import structures.*
  given reader: Reader[structures.ShowMessageRequestParams] = Pickle.macroR
  given writer: Writer[structures.ShowMessageRequestParams] = upickle.default.macroW


private[lsp] trait structures_SignatureHelpCodec:
  import structures.*
  given reader: Reader[structures.SignatureHelp] = Pickle.macroR
  given writer: Writer[structures.SignatureHelp] = upickle.default.macroW


private[lsp] trait structures_SignatureHelpClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.SignatureHelpClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_SignatureHelpClientCapabilities_SignatureInformationCodec:
  import structures.SignatureHelpClientCapabilities.*
  given reader: Reader[structures.SignatureHelpClientCapabilities.SignatureInformation] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpClientCapabilities.SignatureInformation] = upickle.default.macroW


private[lsp] trait structures_SignatureHelpClientCapabilities_SignatureInformation_ParameterInformationCodec:
  import structures.SignatureHelpClientCapabilities.SignatureInformation.*
  given reader: Reader[structures.SignatureHelpClientCapabilities.SignatureInformation.ParameterInformation] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpClientCapabilities.SignatureInformation.ParameterInformation] = upickle.default.macroW


private[lsp] trait structures_SignatureHelpContextCodec:
  import structures.*
  given reader: Reader[structures.SignatureHelpContext] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpContext] = upickle.default.macroW


private[lsp] trait structures_SignatureHelpOptionsCodec:
  import structures.*
  given reader: Reader[structures.SignatureHelpOptions] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpOptions] = upickle.default.macroW


private[lsp] trait structures_SignatureHelpParamsCodec:
  import structures.*
  given reader: Reader[structures.SignatureHelpParams] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpParams] = upickle.default.macroW


private[lsp] trait structures_SignatureHelpRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.SignatureHelpRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_SignatureInformationCodec:
  import structures.*
  private given rd0: Reader[(String | structures.MarkupContent)] = 
    badMerge[(String | structures.MarkupContent)](stringCodec, structures.MarkupContent.reader)
  private given wt0: Writer[(String | structures.MarkupContent)] = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: structures.MarkupContent => writeJs[structures.MarkupContent](v)
    }
  given reader: Reader[structures.SignatureInformation] = Pickle.macroR
  given writer: Writer[structures.SignatureInformation] = upickle.default.macroW


private[lsp] trait structures_StaticRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.StaticRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.StaticRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_SymbolInformationCodec:
  import structures.*
  given reader: Reader[structures.SymbolInformation] = Pickle.macroR
  given writer: Writer[structures.SymbolInformation] = upickle.default.macroW


private[lsp] trait structures_TextDocumentChangeRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.TextDocumentChangeRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.TextDocumentChangeRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_TextDocumentClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.TextDocumentClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.TextDocumentClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_TextDocumentEditCodec:
  import structures.*
  private given rd0: Reader[(structures.TextEdit | structures.AnnotatedTextEdit)] = 
    badMerge[(structures.TextEdit | structures.AnnotatedTextEdit)](structures.TextEdit.reader, structures.AnnotatedTextEdit.reader)
  private given wt0: Writer[(structures.TextEdit | structures.AnnotatedTextEdit)] = 
    upickle.default.writer[ujson.Value].comap[(structures.TextEdit | structures.AnnotatedTextEdit)] { _v => 
      (_v: @unchecked) match 
        case v: structures.TextEdit => writeJs[structures.TextEdit](v)
        case v: structures.AnnotatedTextEdit => writeJs[structures.AnnotatedTextEdit](v)
    }
  given reader: Reader[structures.TextDocumentEdit] = Pickle.macroR
  given writer: Writer[structures.TextDocumentEdit] = upickle.default.macroW


private[lsp] trait structures_TextDocumentIdentifierCodec:
  import structures.*
  given reader: Reader[structures.TextDocumentIdentifier] = Pickle.macroR
  given writer: Writer[structures.TextDocumentIdentifier] = upickle.default.macroW


private[lsp] trait structures_TextDocumentItemCodec:
  import structures.*
  given reader: Reader[structures.TextDocumentItem] = Pickle.macroR
  given writer: Writer[structures.TextDocumentItem] = upickle.default.macroW


private[lsp] trait structures_TextDocumentPositionParamsCodec:
  import structures.*
  given reader: Reader[structures.TextDocumentPositionParams] = Pickle.macroR
  given writer: Writer[structures.TextDocumentPositionParams] = upickle.default.macroW


private[lsp] trait structures_TextDocumentRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.TextDocumentRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.TextDocumentRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_TextDocumentSaveRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.TextDocumentSaveRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.TextDocumentSaveRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_TextDocumentSyncClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.TextDocumentSyncClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.TextDocumentSyncClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_TextDocumentSyncOptionsCodec:
  import structures.*
  private given rd0: Reader[(Boolean | structures.SaveOptions)] = 
    badMerge[(Boolean | structures.SaveOptions)](upickle.default.reader[Boolean], structures.SaveOptions.reader)
  private given wt0: Writer[(Boolean | structures.SaveOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.SaveOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.SaveOptions => writeJs[structures.SaveOptions](v)
    }
  given reader: Reader[structures.TextDocumentSyncOptions] = Pickle.macroR
  given writer: Writer[structures.TextDocumentSyncOptions] = upickle.default.macroW


private[lsp] trait structures_TextEditCodec:
  import structures.*
  given reader: Reader[structures.TextEdit] = Pickle.macroR
  given writer: Writer[structures.TextEdit] = upickle.default.macroW


private[lsp] trait structures_TypeDefinitionClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.TypeDefinitionClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.TypeDefinitionClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_TypeDefinitionOptionsCodec:
  import structures.*
  given reader: Reader[structures.TypeDefinitionOptions] = Pickle.macroR
  given writer: Writer[structures.TypeDefinitionOptions] = upickle.default.macroW


private[lsp] trait structures_TypeDefinitionParamsCodec:
  import structures.*
  given reader: Reader[structures.TypeDefinitionParams] = Pickle.macroR
  given writer: Writer[structures.TypeDefinitionParams] = upickle.default.macroW


private[lsp] trait structures_TypeDefinitionRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.TypeDefinitionRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.TypeDefinitionRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_TypeHierarchyClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.TypeHierarchyClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchyClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_TypeHierarchyItemCodec:
  import structures.*
  given reader: Reader[structures.TypeHierarchyItem] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchyItem] = upickle.default.macroW


private[lsp] trait structures_TypeHierarchyOptionsCodec:
  import structures.*
  given reader: Reader[structures.TypeHierarchyOptions] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchyOptions] = upickle.default.macroW


private[lsp] trait structures_TypeHierarchyPrepareParamsCodec:
  import structures.*
  given reader: Reader[structures.TypeHierarchyPrepareParams] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchyPrepareParams] = upickle.default.macroW


private[lsp] trait structures_TypeHierarchyRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.TypeHierarchyRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchyRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_TypeHierarchySubtypesParamsCodec:
  import structures.*
  given reader: Reader[structures.TypeHierarchySubtypesParams] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchySubtypesParams] = upickle.default.macroW


private[lsp] trait structures_TypeHierarchySupertypesParamsCodec:
  import structures.*
  given reader: Reader[structures.TypeHierarchySupertypesParams] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchySupertypesParams] = upickle.default.macroW


private[lsp] trait structures_UnchangedDocumentDiagnosticReportCodec:
  import structures.*
  given reader: Reader[structures.UnchangedDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.UnchangedDocumentDiagnosticReport] = upickle.default.macroW


private[lsp] trait structures_UnregistrationCodec:
  import structures.*
  given reader: Reader[structures.Unregistration] = Pickle.macroR
  given writer: Writer[structures.Unregistration] = upickle.default.macroW


private[lsp] trait structures_UnregistrationParamsCodec:
  import structures.*
  given reader: Reader[structures.UnregistrationParams] = Pickle.macroR
  given writer: Writer[structures.UnregistrationParams] = upickle.default.macroW


private[lsp] trait structures_VersionedNotebookDocumentIdentifierCodec:
  import structures.*
  given reader: Reader[structures.VersionedNotebookDocumentIdentifier] = Pickle.macroR
  given writer: Writer[structures.VersionedNotebookDocumentIdentifier] = upickle.default.macroW


private[lsp] trait structures_VersionedTextDocumentIdentifierCodec:
  import structures.*
  given reader: Reader[structures.VersionedTextDocumentIdentifier] = Pickle.macroR
  given writer: Writer[structures.VersionedTextDocumentIdentifier] = upickle.default.macroW


private[lsp] trait structures_WillSaveTextDocumentParamsCodec:
  import structures.*
  given reader: Reader[structures.WillSaveTextDocumentParams] = Pickle.macroR
  given writer: Writer[structures.WillSaveTextDocumentParams] = upickle.default.macroW


private[lsp] trait structures_WindowClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.WindowClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.WindowClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_WorkDoneProgressBeginCodec:
  import structures.*
  given reader: Reader[structures.WorkDoneProgressBegin] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressBegin] = upickle.default.macroW


private[lsp] trait structures_WorkDoneProgressCancelParamsCodec:
  import structures.*
  given reader: Reader[structures.WorkDoneProgressCancelParams] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressCancelParams] = upickle.default.macroW


private[lsp] trait structures_WorkDoneProgressCreateParamsCodec:
  import structures.*
  given reader: Reader[structures.WorkDoneProgressCreateParams] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressCreateParams] = upickle.default.macroW


private[lsp] trait structures_WorkDoneProgressEndCodec:
  import structures.*
  given reader: Reader[structures.WorkDoneProgressEnd] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressEnd] = upickle.default.macroW


private[lsp] trait structures_WorkDoneProgressOptionsCodec:
  import structures.*
  given reader: Reader[structures.WorkDoneProgressOptions] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressOptions] = upickle.default.macroW


private[lsp] trait structures_WorkDoneProgressParamsCodec:
  import structures.*
  given reader: Reader[structures.WorkDoneProgressParams] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressParams] = upickle.default.macroW


private[lsp] trait structures_WorkDoneProgressReportCodec:
  import structures.*
  given reader: Reader[structures.WorkDoneProgressReport] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressReport] = upickle.default.macroW


private[lsp] trait structures_WorkspaceClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.WorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.WorkspaceClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_WorkspaceDiagnosticParamsCodec:
  import structures.*
  given reader: Reader[structures.WorkspaceDiagnosticParams] = Pickle.macroR
  given writer: Writer[structures.WorkspaceDiagnosticParams] = upickle.default.macroW


private[lsp] trait structures_WorkspaceDiagnosticReportCodec:
  import structures.*
  given reader: Reader[structures.WorkspaceDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.WorkspaceDiagnosticReport] = upickle.default.macroW


private[lsp] trait structures_WorkspaceDiagnosticReportPartialResultCodec:
  import structures.*
  given reader: Reader[structures.WorkspaceDiagnosticReportPartialResult] = Pickle.macroR
  given writer: Writer[structures.WorkspaceDiagnosticReportPartialResult] = upickle.default.macroW


private[lsp] trait structures_WorkspaceEditCodec:
  import structures.*
  private given rd0: Reader[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)] = 
    badMerge[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)](structures.TextDocumentEdit.reader, structures.CreateFile.reader, structures.RenameFile.reader, structures.DeleteFile.reader)
  private given wt0: Writer[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)] = 
    upickle.default.writer[ujson.Value].comap[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)] { _v => 
      (_v: @unchecked) match 
        case v: structures.TextDocumentEdit => writeJs[structures.TextDocumentEdit](v)
        case v: structures.CreateFile => writeJs[structures.CreateFile](v)
        case v: structures.RenameFile => writeJs[structures.RenameFile](v)
        case v: structures.DeleteFile => writeJs[structures.DeleteFile](v)
    }
  given reader: Reader[structures.WorkspaceEdit] = Pickle.macroR
  given writer: Writer[structures.WorkspaceEdit] = upickle.default.macroW


private[lsp] trait structures_WorkspaceEditClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.WorkspaceEditClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.WorkspaceEditClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_WorkspaceEditClientCapabilities_ChangeAnnotationSupportCodec:
  import structures.WorkspaceEditClientCapabilities.*
  given reader: Reader[structures.WorkspaceEditClientCapabilities.ChangeAnnotationSupport] = Pickle.macroR
  given writer: Writer[structures.WorkspaceEditClientCapabilities.ChangeAnnotationSupport] = upickle.default.macroW


private[lsp] trait structures_WorkspaceFolderCodec:
  import structures.*
  given reader: Reader[structures.WorkspaceFolder] = Pickle.macroR
  given writer: Writer[structures.WorkspaceFolder] = upickle.default.macroW


private[lsp] trait structures_WorkspaceFoldersChangeEventCodec:
  import structures.*
  given reader: Reader[structures.WorkspaceFoldersChangeEvent] = Pickle.macroR
  given writer: Writer[structures.WorkspaceFoldersChangeEvent] = upickle.default.macroW


private[lsp] trait structures_WorkspaceFoldersInitializeParamsCodec:
  import structures.*
  given reader: Reader[structures.WorkspaceFoldersInitializeParams] = Pickle.macroR
  given writer: Writer[structures.WorkspaceFoldersInitializeParams] = upickle.default.macroW


private[lsp] trait structures_WorkspaceFoldersServerCapabilitiesCodec:
  import structures.*
  private given rd0: Reader[(String | Boolean)] = 
    badMerge[(String | Boolean)](stringCodec, upickle.default.reader[Boolean])
  private given wt0: Writer[(String | Boolean)] = 
    upickle.default.writer[ujson.Value].comap[(String | Boolean)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: Boolean => writeJs[Boolean](v)
    }
  given reader: Reader[structures.WorkspaceFoldersServerCapabilities] = Pickle.macroR
  given writer: Writer[structures.WorkspaceFoldersServerCapabilities] = upickle.default.macroW


private[lsp] trait structures_WorkspaceFullDocumentDiagnosticReportCodec:
  import structures.*
  given reader: Reader[structures.WorkspaceFullDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.WorkspaceFullDocumentDiagnosticReport] = upickle.default.macroW


private[lsp] trait structures_WorkspaceSymbolCodec:
  import structures.*
  private given rd0: Reader[(structures.Location | WorkspaceSymbol.S0)] = 
    badMerge[(structures.Location | WorkspaceSymbol.S0)](structures.Location.reader, WorkspaceSymbol.S0.reader)
  private given wt0: Writer[(structures.Location | WorkspaceSymbol.S0)] = 
    upickle.default.writer[ujson.Value].comap[(structures.Location | WorkspaceSymbol.S0)] { _v => 
      (_v: @unchecked) match 
        case v: structures.Location => writeJs[structures.Location](v)
        case v: WorkspaceSymbol.S0 => writeJs[WorkspaceSymbol.S0](v)
    }
  given reader: Reader[structures.WorkspaceSymbol] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbol] = upickle.default.macroW


private[lsp] trait structures_WorkspaceSymbol_S0Codec:
  import structures.WorkspaceSymbol.*
  given reader: Reader[structures.WorkspaceSymbol.S0] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbol.S0] = upickle.default.macroW


private[lsp] trait structures_WorkspaceSymbolClientCapabilitiesCodec:
  import structures.*
  given reader: Reader[structures.WorkspaceSymbolClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbolClientCapabilities] = upickle.default.macroW


private[lsp] trait structures_WorkspaceSymbolClientCapabilities_SymbolKindCodec:
  import structures.WorkspaceSymbolClientCapabilities.*
  given reader: Reader[structures.WorkspaceSymbolClientCapabilities.SymbolKind] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbolClientCapabilities.SymbolKind] = upickle.default.macroW


private[lsp] trait structures_WorkspaceSymbolClientCapabilities_TagSupportCodec:
  import structures.WorkspaceSymbolClientCapabilities.*
  given reader: Reader[structures.WorkspaceSymbolClientCapabilities.TagSupport] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbolClientCapabilities.TagSupport] = upickle.default.macroW


private[lsp] trait structures_WorkspaceSymbolClientCapabilities_ResolveSupportCodec:
  import structures.WorkspaceSymbolClientCapabilities.*
  given reader: Reader[structures.WorkspaceSymbolClientCapabilities.ResolveSupport] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbolClientCapabilities.ResolveSupport] = upickle.default.macroW


private[lsp] trait structures_WorkspaceSymbolOptionsCodec:
  import structures.*
  given reader: Reader[structures.WorkspaceSymbolOptions] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbolOptions] = upickle.default.macroW


private[lsp] trait structures_WorkspaceSymbolParamsCodec:
  import structures.*
  given reader: Reader[structures.WorkspaceSymbolParams] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbolParams] = upickle.default.macroW


private[lsp] trait structures_WorkspaceSymbolRegistrationOptionsCodec:
  import structures.*
  given reader: Reader[structures.WorkspaceSymbolRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbolRegistrationOptions] = upickle.default.macroW


private[lsp] trait structures_WorkspaceUnchangedDocumentDiagnosticReportCodec:
  import structures.*
  given reader: Reader[structures.WorkspaceUnchangedDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.WorkspaceUnchangedDocumentDiagnosticReport] = upickle.default.macroW


private[lsp] trait structures__InitializeParamsCodec:
  import structures.*
  given reader: Reader[structures._InitializeParams] = Pickle.macroR
  given writer: Writer[structures._InitializeParams] = upickle.default.macroW


private[lsp] trait structures__InitializeParams_ClientInfoCodec:
  import structures._InitializeParams.*
  given reader: Reader[structures._InitializeParams.ClientInfo] = Pickle.macroR
  given writer: Writer[structures._InitializeParams.ClientInfo] = upickle.default.macroW


private[lsp] trait aliases_ChangeAnnotationIdentifier:
  
  given reader: Reader[ChangeAnnotationIdentifier] = 
    stringCodec.asInstanceOf[Reader[ChangeAnnotationIdentifier]]
  
  given writer: Writer[ChangeAnnotationIdentifier] =
    stringCodec.asInstanceOf[Writer[ChangeAnnotationIdentifier]]

private[lsp] trait aliases_Declaration:
  
  given reader: Reader[Declaration] = 
    badMerge[Declaration](structures.Location.reader, upickle.default.reader[Vector[structures.Location]]).asInstanceOf[Reader[Declaration]]
  
  given writer: Writer[Declaration] =
    upickle.default.writer[ujson.Value].comap[Declaration] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.Location]](v.asInstanceOf[Vector[structures.Location]])
        case v: structures.Location => writeJs[structures.Location](v)
    }.asInstanceOf[Writer[Declaration]]

private[lsp] trait aliases_DeclarationLink:
  
  given reader: Reader[DeclarationLink] = 
    structures.LocationLink.reader.asInstanceOf[Reader[DeclarationLink]]
  
  given writer: Writer[DeclarationLink] =
    structures.LocationLink.writer.asInstanceOf[Writer[DeclarationLink]]

private[lsp] trait aliases_Definition:
  
  given reader: Reader[Definition] = 
    badMerge[Definition](structures.Location.reader, upickle.default.reader[Vector[structures.Location]]).asInstanceOf[Reader[Definition]]
  
  given writer: Writer[Definition] =
    upickle.default.writer[ujson.Value].comap[Definition] { _v => 
      (_v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.Location]](v.asInstanceOf[Vector[structures.Location]])
        case v: structures.Location => writeJs[structures.Location](v)
    }.asInstanceOf[Writer[Definition]]

private[lsp] trait aliases_DefinitionLink:
  
  given reader: Reader[DefinitionLink] = 
    structures.LocationLink.reader.asInstanceOf[Reader[DefinitionLink]]
  
  given writer: Writer[DefinitionLink] =
    structures.LocationLink.writer.asInstanceOf[Writer[DefinitionLink]]

private[lsp] trait aliases_DocumentDiagnosticReport:
  
  given reader: Reader[DocumentDiagnosticReport] = 
    badMerge[DocumentDiagnosticReport](structures.RelatedFullDocumentDiagnosticReport.reader, structures.RelatedUnchangedDocumentDiagnosticReport.reader).asInstanceOf[Reader[DocumentDiagnosticReport]]
  
  given writer: Writer[DocumentDiagnosticReport] =
    upickle.default.writer[ujson.Value].comap[DocumentDiagnosticReport] { _v => 
      (_v: @unchecked) match 
        case v: structures.RelatedFullDocumentDiagnosticReport => writeJs[structures.RelatedFullDocumentDiagnosticReport](v)
        case v: structures.RelatedUnchangedDocumentDiagnosticReport => writeJs[structures.RelatedUnchangedDocumentDiagnosticReport](v)
    }.asInstanceOf[Writer[DocumentDiagnosticReport]]

private[lsp] trait aliases_DocumentFilter:
  
  given reader: Reader[DocumentFilter] = 
    badMerge[DocumentFilter](aliases.TextDocumentFilter.reader, structures.NotebookCellTextDocumentFilter.reader).asInstanceOf[Reader[DocumentFilter]]
  
  given writer: Writer[DocumentFilter] =
    upickle.default.writer[ujson.Value].comap[DocumentFilter] { _v => 
      (_v: @unchecked) match 
        case v: aliases.TextDocumentFilter => writeJs[aliases.TextDocumentFilter](v)
        case v: structures.NotebookCellTextDocumentFilter => writeJs[structures.NotebookCellTextDocumentFilter](v)
    }.asInstanceOf[Writer[DocumentFilter]]

private[lsp] trait aliases_DocumentSelector:
  
  given reader: Reader[DocumentSelector] = 
    vectorReader[aliases.DocumentFilter].asInstanceOf[Reader[DocumentSelector]]
  
  given writer: Writer[DocumentSelector] =
    vectorWriter[aliases.DocumentFilter].asInstanceOf[Writer[DocumentSelector]]

private[lsp] trait aliases_GlobPattern:
  
  given reader: Reader[GlobPattern] = 
    badMerge[GlobPattern](aliases.Pattern.reader, structures.RelativePattern.reader).asInstanceOf[Reader[GlobPattern]]
  
  given writer: Writer[GlobPattern] =
    upickle.default.writer[ujson.Value].comap[GlobPattern] { _v => 
      (_v: @unchecked) match 
        case v: aliases.Pattern => writeJs[aliases.Pattern](v)
        case v: structures.RelativePattern => writeJs[structures.RelativePattern](v)
    }.asInstanceOf[Writer[GlobPattern]]

private[lsp] trait aliases_InlineValue:
  
  given reader: Reader[InlineValue] = 
    badMerge[InlineValue](structures.InlineValueText.reader, structures.InlineValueVariableLookup.reader, structures.InlineValueEvaluatableExpression.reader).asInstanceOf[Reader[InlineValue]]
  
  given writer: Writer[InlineValue] =
    upickle.default.writer[ujson.Value].comap[InlineValue] { _v => 
      (_v: @unchecked) match 
        case v: structures.InlineValueText => writeJs[structures.InlineValueText](v)
        case v: structures.InlineValueVariableLookup => writeJs[structures.InlineValueVariableLookup](v)
        case v: structures.InlineValueEvaluatableExpression => writeJs[structures.InlineValueEvaluatableExpression](v)
    }.asInstanceOf[Writer[InlineValue]]

private[lsp] trait aliases_LSPObject:
  
  given reader: Reader[LSPObject] = 
    upickle.default.reader[Map[String, ujson.Value]].asInstanceOf[Reader[LSPObject]]
  
  given writer: Writer[LSPObject] =
    ???

private[lsp] trait aliases_MarkedString:
  
  given reader: Reader[MarkedString] = 
    badMerge[MarkedString](stringCodec, MarkedString.S0.reader).asInstanceOf[Reader[MarkedString]]
  
  given writer: Writer[MarkedString] =
    upickle.default.writer[ujson.Value].comap[MarkedString] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: MarkedString.S0 => writeJs[MarkedString.S0](v)
    }.asInstanceOf[Writer[MarkedString]]

private[lsp] trait aliases_MarkedString_S0Codec:
  import aliases.MarkedString.*
  given reader: Reader[aliases.MarkedString.S0] = Pickle.macroR
  given writer: Writer[aliases.MarkedString.S0] = upickle.default.macroW


private[lsp] trait aliases_NotebookDocumentFilter:
  
  given reader: Reader[NotebookDocumentFilter] = 
    badMerge[NotebookDocumentFilter](NotebookDocumentFilter.S0.reader, NotebookDocumentFilter.S1.reader, NotebookDocumentFilter.S2.reader).asInstanceOf[Reader[NotebookDocumentFilter]]
  
  given writer: Writer[NotebookDocumentFilter] =
    upickle.default.writer[ujson.Value].comap[NotebookDocumentFilter] { _v => 
      (_v: @unchecked) match 
        case v: NotebookDocumentFilter.S0 => writeJs[NotebookDocumentFilter.S0](v)
        case v: NotebookDocumentFilter.S1 => writeJs[NotebookDocumentFilter.S1](v)
        case v: NotebookDocumentFilter.S2 => writeJs[NotebookDocumentFilter.S2](v)
    }.asInstanceOf[Writer[NotebookDocumentFilter]]

private[lsp] trait aliases_NotebookDocumentFilter_S0Codec:
  import aliases.NotebookDocumentFilter.*
  given reader: Reader[aliases.NotebookDocumentFilter.S0] = Pickle.macroR
  given writer: Writer[aliases.NotebookDocumentFilter.S0] = upickle.default.macroW


private[lsp] trait aliases_NotebookDocumentFilter_S1Codec:
  import aliases.NotebookDocumentFilter.*
  given reader: Reader[aliases.NotebookDocumentFilter.S1] = Pickle.macroR
  given writer: Writer[aliases.NotebookDocumentFilter.S1] = upickle.default.macroW


private[lsp] trait aliases_NotebookDocumentFilter_S2Codec:
  import aliases.NotebookDocumentFilter.*
  given reader: Reader[aliases.NotebookDocumentFilter.S2] = Pickle.macroR
  given writer: Writer[aliases.NotebookDocumentFilter.S2] = upickle.default.macroW


private[lsp] trait aliases_Pattern:
  
  given reader: Reader[Pattern] = 
    stringCodec.asInstanceOf[Reader[Pattern]]
  
  given writer: Writer[Pattern] =
    stringCodec.asInstanceOf[Writer[Pattern]]

private[lsp] trait aliases_PrepareRenameResult:
  
  given reader: Reader[PrepareRenameResult] = 
    badMerge[PrepareRenameResult](structures.Range.reader, PrepareRenameResult.S0.reader, PrepareRenameResult.S1.reader).asInstanceOf[Reader[PrepareRenameResult]]
  
  given writer: Writer[PrepareRenameResult] =
    upickle.default.writer[ujson.Value].comap[PrepareRenameResult] { _v => 
      (_v: @unchecked) match 
        case v: structures.Range => writeJs[structures.Range](v)
        case v: PrepareRenameResult.S0 => writeJs[PrepareRenameResult.S0](v)
        case v: PrepareRenameResult.S1 => writeJs[PrepareRenameResult.S1](v)
    }.asInstanceOf[Writer[PrepareRenameResult]]

private[lsp] trait aliases_PrepareRenameResult_S0Codec:
  import aliases.PrepareRenameResult.*
  given reader: Reader[aliases.PrepareRenameResult.S0] = Pickle.macroR
  given writer: Writer[aliases.PrepareRenameResult.S0] = upickle.default.macroW


private[lsp] trait aliases_PrepareRenameResult_S1Codec:
  import aliases.PrepareRenameResult.*
  given reader: Reader[aliases.PrepareRenameResult.S1] = Pickle.macroR
  given writer: Writer[aliases.PrepareRenameResult.S1] = upickle.default.macroW


private[lsp] trait aliases_ProgressToken:
  
  given reader: Reader[ProgressToken] = 
    badMerge[ProgressToken](intCodec, stringCodec).asInstanceOf[Reader[ProgressToken]]
  
  given writer: Writer[ProgressToken] =
    upickle.default.writer[ujson.Value].comap[ProgressToken] { _v => 
      (_v: @unchecked) match 
        case v: Int => writeJs[Int](v)
        case v: String => writeJs[String](v)
    }.asInstanceOf[Writer[ProgressToken]]

private[lsp] trait aliases_TextDocumentContentChangeEvent:
  
  given reader: Reader[TextDocumentContentChangeEvent] = 
    badMerge[TextDocumentContentChangeEvent](TextDocumentContentChangeEvent.S0.reader, TextDocumentContentChangeEvent.S1.reader).asInstanceOf[Reader[TextDocumentContentChangeEvent]]
  
  given writer: Writer[TextDocumentContentChangeEvent] =
    upickle.default.writer[ujson.Value].comap[TextDocumentContentChangeEvent] { _v => 
      (_v: @unchecked) match 
        case v: TextDocumentContentChangeEvent.S0 => writeJs[TextDocumentContentChangeEvent.S0](v)
        case v: TextDocumentContentChangeEvent.S1 => writeJs[TextDocumentContentChangeEvent.S1](v)
    }.asInstanceOf[Writer[TextDocumentContentChangeEvent]]

private[lsp] trait aliases_TextDocumentContentChangeEvent_S0Codec:
  import aliases.TextDocumentContentChangeEvent.*
  given reader: Reader[aliases.TextDocumentContentChangeEvent.S0] = Pickle.macroR
  given writer: Writer[aliases.TextDocumentContentChangeEvent.S0] = upickle.default.macroW


private[lsp] trait aliases_TextDocumentContentChangeEvent_S1Codec:
  import aliases.TextDocumentContentChangeEvent.*
  given reader: Reader[aliases.TextDocumentContentChangeEvent.S1] = Pickle.macroR
  given writer: Writer[aliases.TextDocumentContentChangeEvent.S1] = upickle.default.macroW


private[lsp] trait aliases_TextDocumentFilter:
  
  given reader: Reader[TextDocumentFilter] = 
    badMerge[TextDocumentFilter](TextDocumentFilter.S0.reader, TextDocumentFilter.S1.reader, TextDocumentFilter.S2.reader).asInstanceOf[Reader[TextDocumentFilter]]
  
  given writer: Writer[TextDocumentFilter] =
    upickle.default.writer[ujson.Value].comap[TextDocumentFilter] { _v => 
      (_v: @unchecked) match 
        case v: TextDocumentFilter.S0 => writeJs[TextDocumentFilter.S0](v)
        case v: TextDocumentFilter.S1 => writeJs[TextDocumentFilter.S1](v)
        case v: TextDocumentFilter.S2 => writeJs[TextDocumentFilter.S2](v)
    }.asInstanceOf[Writer[TextDocumentFilter]]

private[lsp] trait aliases_TextDocumentFilter_S0Codec:
  import aliases.TextDocumentFilter.*
  given reader: Reader[aliases.TextDocumentFilter.S0] = Pickle.macroR
  given writer: Writer[aliases.TextDocumentFilter.S0] = upickle.default.macroW


private[lsp] trait aliases_TextDocumentFilter_S1Codec:
  import aliases.TextDocumentFilter.*
  given reader: Reader[aliases.TextDocumentFilter.S1] = Pickle.macroR
  given writer: Writer[aliases.TextDocumentFilter.S1] = upickle.default.macroW


private[lsp] trait aliases_TextDocumentFilter_S2Codec:
  import aliases.TextDocumentFilter.*
  given reader: Reader[aliases.TextDocumentFilter.S2] = Pickle.macroR
  given writer: Writer[aliases.TextDocumentFilter.S2] = upickle.default.macroW


private[lsp] trait aliases_WorkspaceDocumentDiagnosticReport:
  
  given reader: Reader[WorkspaceDocumentDiagnosticReport] = 
    badMerge[WorkspaceDocumentDiagnosticReport](structures.WorkspaceFullDocumentDiagnosticReport.reader, structures.WorkspaceUnchangedDocumentDiagnosticReport.reader).asInstanceOf[Reader[WorkspaceDocumentDiagnosticReport]]
  
  given writer: Writer[WorkspaceDocumentDiagnosticReport] =
    upickle.default.writer[ujson.Value].comap[WorkspaceDocumentDiagnosticReport] { _v => 
      (_v: @unchecked) match 
        case v: structures.WorkspaceFullDocumentDiagnosticReport => writeJs[structures.WorkspaceFullDocumentDiagnosticReport](v)
        case v: structures.WorkspaceUnchangedDocumentDiagnosticReport => writeJs[structures.WorkspaceUnchangedDocumentDiagnosticReport](v)
    }.asInstanceOf[Writer[WorkspaceDocumentDiagnosticReport]]
