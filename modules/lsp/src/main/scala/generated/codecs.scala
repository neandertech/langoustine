// format:off
package langoustine.lsp
package codecs
import io.circe.{Decoder, Encoder, KeyDecoder, KeyEncoder, Json}
import aliases.*
import enumerations.*
import structures.*
import runtime.{*, given}

private[lsp] trait requests_callHierarchy_incomingCalls:
  import requests.callHierarchy.incomingCalls.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.CallHierarchyIncomingCallsParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.CallHierarchyIncomingCallsParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(structures.CallHierarchyIncomingCall.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(structures.CallHierarchyIncomingCall.fromJson))

private[lsp] trait requests_callHierarchy_outgoingCalls:
  import requests.callHierarchy.outgoingCalls.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.CallHierarchyOutgoingCallsParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.CallHierarchyOutgoingCallsParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(structures.CallHierarchyOutgoingCall.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(structures.CallHierarchyOutgoingCall.fromJson))

private[lsp] trait requests_client_registerCapability:
  import requests.client.registerCapability.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.RegistrationParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.RegistrationParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.instance[Null](_ => Json.Null)
  
  given outputFromJson: Decoder[Out] =
    Decoder.const[Null](null)

private[lsp] trait requests_client_unregisterCapability:
  import requests.client.unregisterCapability.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.UnregistrationParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.UnregistrationParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.instance[Null](_ => Json.Null)
  
  given outputFromJson: Decoder[Out] =
    Decoder.const[Null](null)

private[lsp] trait requests_codeAction_resolve:
  import requests.codeAction.resolve.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.CodeAction.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.CodeAction.toJson
  
  given outputToJson: Encoder[Out] =
    structures.CodeAction.toJson
  
  given outputFromJson: Decoder[Out] =
    structures.CodeAction.fromJson

private[lsp] trait requests_codeLens_resolve:
  import requests.codeLens.resolve.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.CodeLens.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.CodeLens.toJson
  
  given outputToJson: Encoder[Out] =
    structures.CodeLens.toJson
  
  given outputFromJson: Decoder[Out] =
    structures.CodeLens.fromJson

private[lsp] trait requests_completionItem_resolve:
  import requests.completionItem.resolve.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.CompletionItem.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.CompletionItem.toJson
  
  given outputToJson: Encoder[Out] =
    structures.CompletionItem.toJson
  
  given outputFromJson: Decoder[Out] =
    structures.CompletionItem.fromJson

private[lsp] trait requests_documentLink_resolve:
  import requests.documentLink.resolve.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.DocumentLink.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.DocumentLink.toJson
  
  given outputToJson: Encoder[Out] =
    structures.DocumentLink.toJson
  
  given outputFromJson: Decoder[Out] =
    structures.DocumentLink.fromJson

private[lsp] trait requests_initialize:
  import requests.initialize.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.InitializeParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.InitializeParams.toJson
  
  given outputToJson: Encoder[Out] =
    structures.InitializeResult.toJson
  
  given outputFromJson: Decoder[Out] =
    structures.InitializeResult.fromJson

private[lsp] trait requests_inlayHint_resolve:
  import requests.inlayHint.resolve.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.InlayHint.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.InlayHint.toJson
  
  given outputToJson: Encoder[Out] =
    structures.InlayHint.toJson
  
  given outputFromJson: Decoder[Out] =
    structures.InlayHint.fromJson

private[lsp] trait requests_shutdown:
  import requests.shutdown.{In, Out}
  given inputFromJson: Decoder[In] = 
    Decoder.const(())
  
  given inputToJson: Encoder[In] = 
    Encoder.encodeUnit
  
  given outputToJson: Encoder[Out] =
    Encoder.instance[Null](_ => Json.Null)
  
  given outputFromJson: Decoder[Out] =
    Decoder.const[Null](null)

private[lsp] trait requests_textDocument_codeAction:
  import requests.textDocument.codeAction.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.CodeActionParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.CodeActionParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(Enc.union2[structures.Command, structures.CodeAction](structures.Command.toJson,structures.CodeAction.toJson)))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(Dec.union2[structures.Command, structures.CodeAction](structures.Command.fromJson,structures.CodeAction.fromJson)))

private[lsp] trait requests_textDocument_codeLens:
  import requests.textDocument.codeLens.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.CodeLensParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.CodeLensParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(structures.CodeLens.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(structures.CodeLens.fromJson))

private[lsp] trait requests_textDocument_colorPresentation:
  import requests.textDocument.colorPresentation.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.ColorPresentationParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.ColorPresentationParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeVector(structures.ColorPresentation.toJson)
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeVector(structures.ColorPresentation.fromJson)

private[lsp] trait requests_textDocument_completion:
  import requests.textDocument.completion.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.CompletionParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.CompletionParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Enc.union2[Vector[structures.CompletionItem], structures.CompletionList](Encoder.encodeVector(structures.CompletionItem.toJson),structures.CompletionList.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Dec.union2[Vector[structures.CompletionItem], structures.CompletionList](Decoder.decodeVector(structures.CompletionItem.fromJson),structures.CompletionList.fromJson))

private[lsp] trait requests_textDocument_declaration:
  import requests.textDocument.declaration.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.DeclarationParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.DeclarationParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Enc.union2[aliases.Declaration, Vector[aliases.DeclarationLink]](aliases.Declaration.toJson,Encoder.encodeVector(aliases.DeclarationLink.toJson)))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Dec.union2[aliases.Declaration, Vector[aliases.DeclarationLink]](aliases.Declaration.fromJson,Decoder.decodeVector(aliases.DeclarationLink.fromJson)))

private[lsp] trait requests_textDocument_definition:
  import requests.textDocument.definition.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.DefinitionParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.DefinitionParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Enc.union2[aliases.Definition, Vector[aliases.DefinitionLink]](aliases.Definition.toJson,Encoder.encodeVector(aliases.DefinitionLink.toJson)))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Dec.union2[aliases.Definition, Vector[aliases.DefinitionLink]](aliases.Definition.fromJson,Decoder.decodeVector(aliases.DefinitionLink.fromJson)))

private[lsp] trait requests_textDocument_diagnostic:
  import requests.textDocument.diagnostic.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.DocumentDiagnosticParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.DocumentDiagnosticParams.toJson
  
  given outputToJson: Encoder[Out] =
    aliases.DocumentDiagnosticReport.toJson
  
  given outputFromJson: Decoder[Out] =
    aliases.DocumentDiagnosticReport.fromJson

private[lsp] trait requests_textDocument_documentColor:
  import requests.textDocument.documentColor.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.DocumentColorParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.DocumentColorParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeVector(structures.ColorInformation.toJson)
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeVector(structures.ColorInformation.fromJson)

private[lsp] trait requests_textDocument_documentHighlight:
  import requests.textDocument.documentHighlight.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.DocumentHighlightParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.DocumentHighlightParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(structures.DocumentHighlight.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(structures.DocumentHighlight.fromJson))

private[lsp] trait requests_textDocument_documentLink:
  import requests.textDocument.documentLink.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.DocumentLinkParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.DocumentLinkParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(structures.DocumentLink.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(structures.DocumentLink.fromJson))

private[lsp] trait requests_textDocument_documentSymbol:
  import requests.textDocument.documentSymbol.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.DocumentSymbolParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.DocumentSymbolParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Enc.union2[Vector[structures.SymbolInformation], Vector[structures.DocumentSymbol]](Encoder.encodeVector(structures.SymbolInformation.toJson),Encoder.encodeVector(structures.DocumentSymbol.toJson)))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Dec.union2[Vector[structures.SymbolInformation], Vector[structures.DocumentSymbol]](Decoder.decodeVector(structures.SymbolInformation.fromJson),Decoder.decodeVector(structures.DocumentSymbol.fromJson)))

private[lsp] trait requests_textDocument_foldingRange:
  import requests.textDocument.foldingRange.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.FoldingRangeParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.FoldingRangeParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(structures.FoldingRange.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(structures.FoldingRange.fromJson))

private[lsp] trait requests_textDocument_formatting:
  import requests.textDocument.formatting.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.DocumentFormattingParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.DocumentFormattingParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(structures.TextEdit.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(structures.TextEdit.fromJson))

private[lsp] trait requests_textDocument_hover:
  import requests.textDocument.hover.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.HoverParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.HoverParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(structures.Hover.toJson)
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(structures.Hover.fromJson)

private[lsp] trait requests_textDocument_implementation:
  import requests.textDocument.implementation.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.ImplementationParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.ImplementationParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Enc.union2[aliases.Definition, Vector[aliases.DefinitionLink]](aliases.Definition.toJson,Encoder.encodeVector(aliases.DefinitionLink.toJson)))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Dec.union2[aliases.Definition, Vector[aliases.DefinitionLink]](aliases.Definition.fromJson,Decoder.decodeVector(aliases.DefinitionLink.fromJson)))

private[lsp] trait requests_textDocument_inlayHint:
  import requests.textDocument.inlayHint.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.InlayHintParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.InlayHintParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(structures.InlayHint.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(structures.InlayHint.fromJson))

private[lsp] trait requests_textDocument_inlineValue:
  import requests.textDocument.inlineValue.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.InlineValueParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.InlineValueParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(aliases.InlineValue.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(aliases.InlineValue.fromJson))

private[lsp] trait requests_textDocument_linkedEditingRange:
  import requests.textDocument.linkedEditingRange.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.LinkedEditingRangeParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.LinkedEditingRangeParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(structures.LinkedEditingRanges.toJson)
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(structures.LinkedEditingRanges.fromJson)

private[lsp] trait requests_textDocument_moniker:
  import requests.textDocument.moniker.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.MonikerParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.MonikerParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(structures.Moniker.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(structures.Moniker.fromJson))

private[lsp] trait requests_textDocument_onTypeFormatting:
  import requests.textDocument.onTypeFormatting.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.DocumentOnTypeFormattingParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.DocumentOnTypeFormattingParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(structures.TextEdit.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(structures.TextEdit.fromJson))

private[lsp] trait requests_textDocument_prepareCallHierarchy:
  import requests.textDocument.prepareCallHierarchy.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.CallHierarchyPrepareParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.CallHierarchyPrepareParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(structures.CallHierarchyItem.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(structures.CallHierarchyItem.fromJson))

private[lsp] trait requests_textDocument_prepareRename:
  import requests.textDocument.prepareRename.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.PrepareRenameParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.PrepareRenameParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(aliases.PrepareRenameResult.toJson)
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(aliases.PrepareRenameResult.fromJson)

private[lsp] trait requests_textDocument_prepareTypeHierarchy:
  import requests.textDocument.prepareTypeHierarchy.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.TypeHierarchyPrepareParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.TypeHierarchyPrepareParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(structures.TypeHierarchyItem.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(structures.TypeHierarchyItem.fromJson))

private[lsp] trait requests_textDocument_rangeFormatting:
  import requests.textDocument.rangeFormatting.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.DocumentRangeFormattingParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.DocumentRangeFormattingParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(structures.TextEdit.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(structures.TextEdit.fromJson))

private[lsp] trait requests_textDocument_references:
  import requests.textDocument.references.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.ReferenceParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.ReferenceParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(structures.Location.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(structures.Location.fromJson))

private[lsp] trait requests_textDocument_rename:
  import requests.textDocument.rename.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.RenameParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.RenameParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(structures.WorkspaceEdit.toJson)
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(structures.WorkspaceEdit.fromJson)

private[lsp] trait requests_textDocument_selectionRange:
  import requests.textDocument.selectionRange.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.SelectionRangeParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.SelectionRangeParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(structures.SelectionRange.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(structures.SelectionRange.fromJson))

private[lsp] trait requests_textDocument_semanticTokens_full:
  import requests.textDocument.semanticTokens.full.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.SemanticTokensParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.SemanticTokensParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(structures.SemanticTokens.toJson)
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(structures.SemanticTokens.fromJson)

private[lsp] trait requests_textDocument_semanticTokens_full_delta:
  import requests.textDocument.semanticTokens.full.delta.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.SemanticTokensDeltaParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.SemanticTokensDeltaParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Enc.union2[structures.SemanticTokens, structures.SemanticTokensDelta](structures.SemanticTokens.toJson,structures.SemanticTokensDelta.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Dec.union2[structures.SemanticTokens, structures.SemanticTokensDelta](structures.SemanticTokens.fromJson,structures.SemanticTokensDelta.fromJson))

private[lsp] trait requests_textDocument_semanticTokens_range:
  import requests.textDocument.semanticTokens.range.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.SemanticTokensRangeParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.SemanticTokensRangeParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(structures.SemanticTokens.toJson)
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(structures.SemanticTokens.fromJson)

private[lsp] trait requests_textDocument_signatureHelp:
  import requests.textDocument.signatureHelp.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.SignatureHelpParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.SignatureHelpParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(structures.SignatureHelp.toJson)
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(structures.SignatureHelp.fromJson)

private[lsp] trait requests_textDocument_typeDefinition:
  import requests.textDocument.typeDefinition.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.TypeDefinitionParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.TypeDefinitionParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Enc.union2[aliases.Definition, Vector[aliases.DefinitionLink]](aliases.Definition.toJson,Encoder.encodeVector(aliases.DefinitionLink.toJson)))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Dec.union2[aliases.Definition, Vector[aliases.DefinitionLink]](aliases.Definition.fromJson,Decoder.decodeVector(aliases.DefinitionLink.fromJson)))

private[lsp] trait requests_textDocument_willSaveWaitUntil:
  import requests.textDocument.willSaveWaitUntil.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.WillSaveTextDocumentParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.WillSaveTextDocumentParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(structures.TextEdit.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(structures.TextEdit.fromJson))

private[lsp] trait requests_typeHierarchy_subtypes:
  import requests.typeHierarchy.subtypes.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.TypeHierarchySubtypesParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.TypeHierarchySubtypesParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(structures.TypeHierarchyItem.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(structures.TypeHierarchyItem.fromJson))

private[lsp] trait requests_typeHierarchy_supertypes:
  import requests.typeHierarchy.supertypes.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.TypeHierarchySupertypesParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.TypeHierarchySupertypesParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(structures.TypeHierarchyItem.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(structures.TypeHierarchyItem.fromJson))

private[lsp] trait requests_window_showDocument:
  import requests.window.showDocument.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.ShowDocumentParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.ShowDocumentParams.toJson
  
  given outputToJson: Encoder[Out] =
    structures.ShowDocumentResult.toJson
  
  given outputFromJson: Decoder[Out] =
    structures.ShowDocumentResult.fromJson

private[lsp] trait requests_window_showMessageRequest:
  import requests.window.showMessageRequest.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.ShowMessageRequestParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.ShowMessageRequestParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(structures.MessageActionItem.toJson)
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(structures.MessageActionItem.fromJson)

private[lsp] trait requests_window_workDoneProgress_create:
  import requests.window.workDoneProgress.create.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.WorkDoneProgressCreateParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.WorkDoneProgressCreateParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.instance[Null](_ => Json.Null)
  
  given outputFromJson: Decoder[Out] =
    Decoder.const[Null](null)

private[lsp] trait requests_workspace_applyEdit:
  import requests.workspace.applyEdit.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.ApplyWorkspaceEditParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.ApplyWorkspaceEditParams.toJson
  
  given outputToJson: Encoder[Out] =
    structures.ApplyWorkspaceEditResult.toJson
  
  given outputFromJson: Decoder[Out] =
    structures.ApplyWorkspaceEditResult.fromJson

private[lsp] trait requests_workspace_codeLens_refresh:
  import requests.workspace.codeLens.refresh.{In, Out}
  given inputFromJson: Decoder[In] = 
    Decoder.const(())
  
  given inputToJson: Encoder[In] = 
    Encoder.encodeUnit
  
  given outputToJson: Encoder[Out] =
    Encoder.instance[Null](_ => Json.Null)
  
  given outputFromJson: Decoder[Out] =
    Decoder.const[Null](null)

private[lsp] trait requests_workspace_configuration:
  import requests.workspace.configuration.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.ConfigurationParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.ConfigurationParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeVector(Encoder.encodeJson)
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeVector(Decoder.decodeJson)

private[lsp] trait requests_workspace_diagnostic:
  import requests.workspace.diagnostic.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.WorkspaceDiagnosticParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.WorkspaceDiagnosticParams.toJson
  
  given outputToJson: Encoder[Out] =
    structures.WorkspaceDiagnosticReport.toJson
  
  given outputFromJson: Decoder[Out] =
    structures.WorkspaceDiagnosticReport.fromJson

private[lsp] trait requests_workspace_diagnostic_refresh:
  import requests.workspace.diagnostic.refresh.{In, Out}
  given inputFromJson: Decoder[In] = 
    Decoder.const(())
  
  given inputToJson: Encoder[In] = 
    Encoder.encodeUnit
  
  given outputToJson: Encoder[Out] =
    Encoder.instance[Null](_ => Json.Null)
  
  given outputFromJson: Decoder[Out] =
    Decoder.const[Null](null)

private[lsp] trait requests_workspace_executeCommand:
  import requests.workspace.executeCommand.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.ExecuteCommandParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.ExecuteCommandParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeJson)
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeJson)

private[lsp] trait requests_workspace_inlayHint_refresh:
  import requests.workspace.inlayHint.refresh.{In, Out}
  given inputFromJson: Decoder[In] = 
    Decoder.const(())
  
  given inputToJson: Encoder[In] = 
    Encoder.encodeUnit
  
  given outputToJson: Encoder[Out] =
    Encoder.instance[Null](_ => Json.Null)
  
  given outputFromJson: Decoder[Out] =
    Decoder.const[Null](null)

private[lsp] trait requests_workspace_inlineValue_refresh:
  import requests.workspace.inlineValue.refresh.{In, Out}
  given inputFromJson: Decoder[In] = 
    Decoder.const(())
  
  given inputToJson: Encoder[In] = 
    Encoder.encodeUnit
  
  given outputToJson: Encoder[Out] =
    Encoder.instance[Null](_ => Json.Null)
  
  given outputFromJson: Decoder[Out] =
    Decoder.const[Null](null)

private[lsp] trait requests_workspace_semanticTokens_refresh:
  import requests.workspace.semanticTokens.refresh.{In, Out}
  given inputFromJson: Decoder[In] = 
    Decoder.const(())
  
  given inputToJson: Encoder[In] = 
    Encoder.encodeUnit
  
  given outputToJson: Encoder[Out] =
    Encoder.instance[Null](_ => Json.Null)
  
  given outputFromJson: Decoder[Out] =
    Decoder.const[Null](null)

private[lsp] trait requests_workspace_symbol:
  import requests.workspace.symbol.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.WorkspaceSymbolParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.WorkspaceSymbolParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Enc.union2[Vector[structures.SymbolInformation], Vector[structures.WorkspaceSymbol]](Encoder.encodeVector(structures.SymbolInformation.toJson),Encoder.encodeVector(structures.WorkspaceSymbol.toJson)))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Dec.union2[Vector[structures.SymbolInformation], Vector[structures.WorkspaceSymbol]](Decoder.decodeVector(structures.SymbolInformation.fromJson),Decoder.decodeVector(structures.WorkspaceSymbol.fromJson)))

private[lsp] trait requests_workspace_willCreateFiles:
  import requests.workspace.willCreateFiles.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.CreateFilesParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.CreateFilesParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(structures.WorkspaceEdit.toJson)
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(structures.WorkspaceEdit.fromJson)

private[lsp] trait requests_workspace_willDeleteFiles:
  import requests.workspace.willDeleteFiles.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.DeleteFilesParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.DeleteFilesParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(structures.WorkspaceEdit.toJson)
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(structures.WorkspaceEdit.fromJson)

private[lsp] trait requests_workspace_willRenameFiles:
  import requests.workspace.willRenameFiles.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.RenameFilesParams.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.RenameFilesParams.toJson
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(structures.WorkspaceEdit.toJson)
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(structures.WorkspaceEdit.fromJson)

private[lsp] trait requests_workspace_workspaceFolders:
  import requests.workspace.workspaceFolders.{In, Out}
  given inputFromJson: Decoder[In] = 
    Decoder.const(())
  
  given inputToJson: Encoder[In] = 
    Encoder.encodeUnit
  
  given outputToJson: Encoder[Out] =
    Encoder.encodeOption(Encoder.encodeVector(structures.WorkspaceFolder.toJson))
  
  given outputFromJson: Decoder[Out] =
    Decoder.decodeOption(Decoder.decodeVector(structures.WorkspaceFolder.fromJson))

private[lsp] trait requests_workspaceSymbol_resolve:
  import requests.workspaceSymbol.resolve.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.WorkspaceSymbol.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.WorkspaceSymbol.toJson
  
  given outputToJson: Encoder[Out] =
    structures.WorkspaceSymbol.toJson
  
  given outputFromJson: Decoder[Out] =
    structures.WorkspaceSymbol.fromJson

private[lsp] trait structures_AnnotatedTextEditCodec:
  import structures.*
  given fromJson: Decoder[AnnotatedTextEdit] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_annotationId: Decoder[aliases.ChangeAnnotationIdentifier] = aliases.ChangeAnnotationIdentifier.fromJson
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_newText: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        annotationId <- dec.get("annotationId", decode_annotationId)
        range <- dec.get("range", decode_range)
        newText <- dec.get("newText", decode_newText)
      yield AnnotatedTextEdit(
        annotationId,
        range,
        newText,
      )
  given toJson: Encoder[AnnotatedTextEdit] = 
    // cache all encoders for this type when toJson first initialised
    val encode_annotationId: Encoder[aliases.ChangeAnnotationIdentifier] = aliases.ChangeAnnotationIdentifier.toJson
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_newText: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("annotationId", a.annotationId, encode_annotationId)
      enc.field("range", a.range, encode_range)
      enc.field("newText", a.newText, encode_newText)


private[lsp] trait structures_ApplyWorkspaceEditParamsCodec:
  import structures.*
  given fromJson: Decoder[ApplyWorkspaceEditParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_label: Decoder[String] = Decoder.decodeString
    val decode_edit: Decoder[structures.WorkspaceEdit] = structures.WorkspaceEdit.fromJson
    Dec.fromJsonObject: dec =>
      for
        label <- dec.getOpt("label", decode_label)
        edit <- dec.get("edit", decode_edit)
      yield ApplyWorkspaceEditParams(
        label,
        edit,
      )
  given toJson: Encoder[ApplyWorkspaceEditParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_label: Encoder[String] = Encoder.encodeString
    val encode_edit: Encoder[structures.WorkspaceEdit] = structures.WorkspaceEdit.toJson
    Enc.toJsonObject: (enc, a) =>
      a.label.foreach: v =>
        enc.field("label", v, encode_label)
      enc.field("edit", a.edit, encode_edit)


private[lsp] trait structures_ApplyWorkspaceEditResultCodec:
  import structures.*
  given fromJson: Decoder[ApplyWorkspaceEditResult] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_applied: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_failureReason: Decoder[String] = Decoder.decodeString
    val decode_failedChange: Decoder[runtime.uinteger] = uinteger.fromJson
    Dec.fromJsonObject: dec =>
      for
        applied <- dec.get("applied", decode_applied)
        failureReason <- dec.getOpt("failureReason", decode_failureReason)
        failedChange <- dec.getOpt("failedChange", decode_failedChange)
      yield ApplyWorkspaceEditResult(
        applied,
        failureReason,
        failedChange,
      )
  given toJson: Encoder[ApplyWorkspaceEditResult] = 
    // cache all encoders for this type when toJson first initialised
    val encode_applied: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_failureReason: Encoder[String] = Encoder.encodeString
    val encode_failedChange: Encoder[runtime.uinteger] = uinteger.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("applied", a.applied, encode_applied)
      a.failureReason.foreach: v =>
        enc.field("failureReason", v, encode_failureReason)
      a.failedChange.foreach: v =>
        enc.field("failedChange", v, encode_failedChange)


private[lsp] trait structures_BaseSymbolInformationCodec:
  import structures.*
  given fromJson: Decoder[BaseSymbolInformation] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_name: Decoder[String] = Decoder.decodeString
    val decode_kind: Decoder[enumerations.SymbolKind] = enumerations.SymbolKind.fromJson
    val decode_tags: Decoder[Vector[enumerations.SymbolTag]] = Decoder.decodeVector(enumerations.SymbolTag.fromJson)
    val decode_containerName: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        name <- dec.get("name", decode_name)
        kind <- dec.get("kind", decode_kind)
        tags <- dec.getOpt("tags", decode_tags)
        containerName <- dec.getOpt("containerName", decode_containerName)
      yield BaseSymbolInformation(
        name,
        kind,
        tags,
        containerName,
      )
  given toJson: Encoder[BaseSymbolInformation] = 
    // cache all encoders for this type when toJson first initialised
    val encode_name: Encoder[String] = Encoder.encodeString
    val encode_kind: Encoder[enumerations.SymbolKind] = enumerations.SymbolKind.toJson
    val encode_tags: Encoder[Vector[enumerations.SymbolTag]] = Encoder.encodeVector(enumerations.SymbolTag.toJson)
    val encode_containerName: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("name", a.name, encode_name)
      enc.field("kind", a.kind, encode_kind)
      a.tags.foreach: v =>
        enc.field("tags", v, encode_tags)
      a.containerName.foreach: v =>
        enc.field("containerName", v, encode_containerName)


private[lsp] trait structures_CallHierarchyClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[CallHierarchyClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
      yield CallHierarchyClientCapabilities(
        dynamicRegistration,
      )
  given toJson: Encoder[CallHierarchyClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)


private[lsp] trait structures_CallHierarchyIncomingCallCodec:
  import structures.*
  given fromJson: Decoder[CallHierarchyIncomingCall] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_from: Decoder[structures.CallHierarchyItem] = structures.CallHierarchyItem.fromJson
    val decode_fromRanges: Decoder[Vector[structures.Range]] = Decoder.decodeVector(structures.Range.fromJson)
    Dec.fromJsonObject: dec =>
      for
        from <- dec.get("from", decode_from)
        fromRanges <- dec.get("fromRanges", decode_fromRanges)
      yield CallHierarchyIncomingCall(
        from,
        fromRanges,
      )
  given toJson: Encoder[CallHierarchyIncomingCall] = 
    // cache all encoders for this type when toJson first initialised
    val encode_from: Encoder[structures.CallHierarchyItem] = structures.CallHierarchyItem.toJson
    val encode_fromRanges: Encoder[Vector[structures.Range]] = Encoder.encodeVector(structures.Range.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("from", a.from, encode_from)
      enc.field("fromRanges", a.fromRanges, encode_fromRanges)


private[lsp] trait structures_CallHierarchyIncomingCallsParamsCodec:
  import structures.*
  given fromJson: Decoder[CallHierarchyIncomingCallsParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_item: Decoder[structures.CallHierarchyItem] = structures.CallHierarchyItem.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        item <- dec.get("item", decode_item)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield CallHierarchyIncomingCallsParams(
        item,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[CallHierarchyIncomingCallsParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_item: Encoder[structures.CallHierarchyItem] = structures.CallHierarchyItem.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("item", a.item, encode_item)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_CallHierarchyItemCodec:
  import structures.*
  given fromJson: Decoder[CallHierarchyItem] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_name: Decoder[String] = Decoder.decodeString
    val decode_kind: Decoder[enumerations.SymbolKind] = enumerations.SymbolKind.fromJson
    val decode_tags: Decoder[Vector[enumerations.SymbolTag]] = Decoder.decodeVector(enumerations.SymbolTag.fromJson)
    val decode_detail: Decoder[String] = Decoder.decodeString
    val decode_uri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_selectionRange: Decoder[structures.Range] = structures.Range.fromJson
    val decode_data: Decoder[io.circe.Json] = Decoder.decodeJson
    Dec.fromJsonObject: dec =>
      for
        name <- dec.get("name", decode_name)
        kind <- dec.get("kind", decode_kind)
        tags <- dec.getOpt("tags", decode_tags)
        detail <- dec.getOpt("detail", decode_detail)
        uri <- dec.get("uri", decode_uri)
        range <- dec.get("range", decode_range)
        selectionRange <- dec.get("selectionRange", decode_selectionRange)
        data <- dec.getOpt("data", decode_data)
      yield CallHierarchyItem(
        name,
        kind,
        tags,
        detail,
        uri,
        range,
        selectionRange,
        data,
      )
  given toJson: Encoder[CallHierarchyItem] = 
    // cache all encoders for this type when toJson first initialised
    val encode_name: Encoder[String] = Encoder.encodeString
    val encode_kind: Encoder[enumerations.SymbolKind] = enumerations.SymbolKind.toJson
    val encode_tags: Encoder[Vector[enumerations.SymbolTag]] = Encoder.encodeVector(enumerations.SymbolTag.toJson)
    val encode_detail: Encoder[String] = Encoder.encodeString
    val encode_uri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_selectionRange: Encoder[structures.Range] = structures.Range.toJson
    val encode_data: Encoder[io.circe.Json] = Encoder.encodeJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("name", a.name, encode_name)
      enc.field("kind", a.kind, encode_kind)
      a.tags.foreach: v =>
        enc.field("tags", v, encode_tags)
      a.detail.foreach: v =>
        enc.field("detail", v, encode_detail)
      enc.field("uri", a.uri, encode_uri)
      enc.field("range", a.range, encode_range)
      enc.field("selectionRange", a.selectionRange, encode_selectionRange)
      a.data.foreach: v =>
        enc.field("data", v, encode_data)


private[lsp] trait structures_CallHierarchyOptionsCodec:
  import structures.*
  given fromJson: Decoder[CallHierarchyOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield CallHierarchyOptions(
        workDoneProgress,
      )
  given toJson: Encoder[CallHierarchyOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_CallHierarchyOutgoingCallCodec:
  import structures.*
  given fromJson: Decoder[CallHierarchyOutgoingCall] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_to: Decoder[structures.CallHierarchyItem] = structures.CallHierarchyItem.fromJson
    val decode_fromRanges: Decoder[Vector[structures.Range]] = Decoder.decodeVector(structures.Range.fromJson)
    Dec.fromJsonObject: dec =>
      for
        to <- dec.get("to", decode_to)
        fromRanges <- dec.get("fromRanges", decode_fromRanges)
      yield CallHierarchyOutgoingCall(
        to,
        fromRanges,
      )
  given toJson: Encoder[CallHierarchyOutgoingCall] = 
    // cache all encoders for this type when toJson first initialised
    val encode_to: Encoder[structures.CallHierarchyItem] = structures.CallHierarchyItem.toJson
    val encode_fromRanges: Encoder[Vector[structures.Range]] = Encoder.encodeVector(structures.Range.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("to", a.to, encode_to)
      enc.field("fromRanges", a.fromRanges, encode_fromRanges)


private[lsp] trait structures_CallHierarchyOutgoingCallsParamsCodec:
  import structures.*
  given fromJson: Decoder[CallHierarchyOutgoingCallsParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_item: Decoder[structures.CallHierarchyItem] = structures.CallHierarchyItem.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        item <- dec.get("item", decode_item)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield CallHierarchyOutgoingCallsParams(
        item,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[CallHierarchyOutgoingCallsParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_item: Encoder[structures.CallHierarchyItem] = structures.CallHierarchyItem.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("item", a.item, encode_item)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_CallHierarchyPrepareParamsCodec:
  import structures.*
  given fromJson: Decoder[CallHierarchyPrepareParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_position: Decoder[structures.Position] = structures.Position.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        position <- dec.get("position", decode_position)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
      yield CallHierarchyPrepareParams(
        textDocument,
        position,
        workDoneToken,
      )
  given toJson: Encoder[CallHierarchyPrepareParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_position: Encoder[structures.Position] = structures.Position.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("position", a.position, encode_position)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)


private[lsp] trait structures_CallHierarchyRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[CallHierarchyRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_id: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        id <- dec.getOpt("id", decode_id)
      yield CallHierarchyRegistrationOptions(
        documentSelector,
        id,
      )
  given toJson: Encoder[CallHierarchyRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_id: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.id.foreach: v =>
        enc.field("id", v, encode_id)


private[lsp] trait structures_CancelParamsCodec:
  import structures.*
  given fromJson: Decoder[CancelParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_id: Decoder[(Int | String)] = Dec.union2[Int, String](Decoder.decodeInt,Decoder.decodeString)
    Dec.fromJsonObject: dec =>
      for
        id <- dec.get("id", decode_id)
      yield CancelParams(
        id,
      )
  given toJson: Encoder[CancelParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_id: Encoder[(Int | String)] = Enc.union2[Int, String](Encoder.encodeInt,Encoder.encodeString)
    Enc.toJsonObject: (enc, a) =>
      enc.field("id", a.id, encode_id)


private[lsp] trait structures_ChangeAnnotationCodec:
  import structures.*
  given fromJson: Decoder[ChangeAnnotation] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_label: Decoder[String] = Decoder.decodeString
    val decode_needsConfirmation: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_description: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        label <- dec.get("label", decode_label)
        needsConfirmation <- dec.getOpt("needsConfirmation", decode_needsConfirmation)
        description <- dec.getOpt("description", decode_description)
      yield ChangeAnnotation(
        label,
        needsConfirmation,
        description,
      )
  given toJson: Encoder[ChangeAnnotation] = 
    // cache all encoders for this type when toJson first initialised
    val encode_label: Encoder[String] = Encoder.encodeString
    val encode_needsConfirmation: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_description: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("label", a.label, encode_label)
      a.needsConfirmation.foreach: v =>
        enc.field("needsConfirmation", v, encode_needsConfirmation)
      a.description.foreach: v =>
        enc.field("description", v, encode_description)


private[lsp] trait structures_ClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[ClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workspace: Decoder[structures.WorkspaceClientCapabilities] = structures.WorkspaceClientCapabilities.fromJson
    val decode_textDocument: Decoder[structures.TextDocumentClientCapabilities] = structures.TextDocumentClientCapabilities.fromJson
    val decode_notebookDocument: Decoder[structures.NotebookDocumentClientCapabilities] = structures.NotebookDocumentClientCapabilities.fromJson
    val decode_window: Decoder[structures.WindowClientCapabilities] = structures.WindowClientCapabilities.fromJson
    val decode_general: Decoder[structures.GeneralClientCapabilities] = structures.GeneralClientCapabilities.fromJson
    val decode_experimental: Decoder[io.circe.Json] = Decoder.decodeJson
    Dec.fromJsonObject: dec =>
      for
        workspace <- dec.getOpt("workspace", decode_workspace)
        textDocument <- dec.getOpt("textDocument", decode_textDocument)
        notebookDocument <- dec.getOpt("notebookDocument", decode_notebookDocument)
        window <- dec.getOpt("window", decode_window)
        general <- dec.getOpt("general", decode_general)
        experimental <- dec.getOpt("experimental", decode_experimental)
      yield ClientCapabilities(
        workspace,
        textDocument,
        notebookDocument,
        window,
        general,
        experimental,
      )
  given toJson: Encoder[ClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workspace: Encoder[structures.WorkspaceClientCapabilities] = structures.WorkspaceClientCapabilities.toJson
    val encode_textDocument: Encoder[structures.TextDocumentClientCapabilities] = structures.TextDocumentClientCapabilities.toJson
    val encode_notebookDocument: Encoder[structures.NotebookDocumentClientCapabilities] = structures.NotebookDocumentClientCapabilities.toJson
    val encode_window: Encoder[structures.WindowClientCapabilities] = structures.WindowClientCapabilities.toJson
    val encode_general: Encoder[structures.GeneralClientCapabilities] = structures.GeneralClientCapabilities.toJson
    val encode_experimental: Encoder[io.circe.Json] = Encoder.encodeJson
    Enc.toJsonObject: (enc, a) =>
      a.workspace.foreach: v =>
        enc.field("workspace", v, encode_workspace)
      a.textDocument.foreach: v =>
        enc.field("textDocument", v, encode_textDocument)
      a.notebookDocument.foreach: v =>
        enc.field("notebookDocument", v, encode_notebookDocument)
      a.window.foreach: v =>
        enc.field("window", v, encode_window)
      a.general.foreach: v =>
        enc.field("general", v, encode_general)
      a.experimental.foreach: v =>
        enc.field("experimental", v, encode_experimental)


private[lsp] trait structures_CodeActionCodec:
  import structures.*
  given fromJson: Decoder[CodeAction] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_title: Decoder[String] = Decoder.decodeString
    val decode_kind: Decoder[enumerations.CodeActionKind] = enumerations.CodeActionKind.fromJson
    val decode_diagnostics: Decoder[Vector[structures.Diagnostic]] = Decoder.decodeVector(structures.Diagnostic.fromJson)
    val decode_isPreferred: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_disabled: Decoder[CodeAction.Disabled] = CodeAction.Disabled.fromJson
    val decode_edit: Decoder[structures.WorkspaceEdit] = structures.WorkspaceEdit.fromJson
    val decode_command: Decoder[structures.Command] = structures.Command.fromJson
    val decode_data: Decoder[io.circe.Json] = Decoder.decodeJson
    Dec.fromJsonObject: dec =>
      for
        title <- dec.get("title", decode_title)
        kind <- dec.getOpt("kind", decode_kind)
        diagnostics <- dec.getOpt("diagnostics", decode_diagnostics)
        isPreferred <- dec.getOpt("isPreferred", decode_isPreferred)
        disabled <- dec.getOpt("disabled", decode_disabled)
        edit <- dec.getOpt("edit", decode_edit)
        command <- dec.getOpt("command", decode_command)
        data <- dec.getOpt("data", decode_data)
      yield CodeAction(
        title,
        kind,
        diagnostics,
        isPreferred,
        disabled,
        edit,
        command,
        data,
      )
  given toJson: Encoder[CodeAction] = 
    // cache all encoders for this type when toJson first initialised
    val encode_title: Encoder[String] = Encoder.encodeString
    val encode_kind: Encoder[enumerations.CodeActionKind] = enumerations.CodeActionKind.toJson
    val encode_diagnostics: Encoder[Vector[structures.Diagnostic]] = Encoder.encodeVector(structures.Diagnostic.toJson)
    val encode_isPreferred: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_disabled: Encoder[CodeAction.Disabled] = CodeAction.Disabled.toJson
    val encode_edit: Encoder[structures.WorkspaceEdit] = structures.WorkspaceEdit.toJson
    val encode_command: Encoder[structures.Command] = structures.Command.toJson
    val encode_data: Encoder[io.circe.Json] = Encoder.encodeJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("title", a.title, encode_title)
      a.kind.foreach: v =>
        enc.field("kind", v, encode_kind)
      a.diagnostics.foreach: v =>
        enc.field("diagnostics", v, encode_diagnostics)
      a.isPreferred.foreach: v =>
        enc.field("isPreferred", v, encode_isPreferred)
      a.disabled.foreach: v =>
        enc.field("disabled", v, encode_disabled)
      a.edit.foreach: v =>
        enc.field("edit", v, encode_edit)
      a.command.foreach: v =>
        enc.field("command", v, encode_command)
      a.data.foreach: v =>
        enc.field("data", v, encode_data)


private[lsp] trait structures_CodeAction_DisabledCodec:
  import structures.CodeAction.*
  given fromJson: Decoder[Disabled] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_reason: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        reason <- dec.get("reason", decode_reason)
      yield Disabled(
        reason,
      )
  given toJson: Encoder[Disabled] = 
    // cache all encoders for this type when toJson first initialised
    val encode_reason: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("reason", a.reason, encode_reason)


private[lsp] trait structures_CodeActionClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[CodeActionClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_codeActionLiteralSupport: Decoder[CodeActionClientCapabilities.CodeActionLiteralSupport] = CodeActionClientCapabilities.CodeActionLiteralSupport.fromJson
    val decode_isPreferredSupport: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_disabledSupport: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_dataSupport: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_resolveSupport: Decoder[CodeActionClientCapabilities.ResolveSupport] = CodeActionClientCapabilities.ResolveSupport.fromJson
    val decode_honorsChangeAnnotations: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        codeActionLiteralSupport <- dec.getOpt("codeActionLiteralSupport", decode_codeActionLiteralSupport)
        isPreferredSupport <- dec.getOpt("isPreferredSupport", decode_isPreferredSupport)
        disabledSupport <- dec.getOpt("disabledSupport", decode_disabledSupport)
        dataSupport <- dec.getOpt("dataSupport", decode_dataSupport)
        resolveSupport <- dec.getOpt("resolveSupport", decode_resolveSupport)
        honorsChangeAnnotations <- dec.getOpt("honorsChangeAnnotations", decode_honorsChangeAnnotations)
      yield CodeActionClientCapabilities(
        dynamicRegistration,
        codeActionLiteralSupport,
        isPreferredSupport,
        disabledSupport,
        dataSupport,
        resolveSupport,
        honorsChangeAnnotations,
      )
  given toJson: Encoder[CodeActionClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_codeActionLiteralSupport: Encoder[CodeActionClientCapabilities.CodeActionLiteralSupport] = CodeActionClientCapabilities.CodeActionLiteralSupport.toJson
    val encode_isPreferredSupport: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_disabledSupport: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_dataSupport: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_resolveSupport: Encoder[CodeActionClientCapabilities.ResolveSupport] = CodeActionClientCapabilities.ResolveSupport.toJson
    val encode_honorsChangeAnnotations: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      a.codeActionLiteralSupport.foreach: v =>
        enc.field("codeActionLiteralSupport", v, encode_codeActionLiteralSupport)
      a.isPreferredSupport.foreach: v =>
        enc.field("isPreferredSupport", v, encode_isPreferredSupport)
      a.disabledSupport.foreach: v =>
        enc.field("disabledSupport", v, encode_disabledSupport)
      a.dataSupport.foreach: v =>
        enc.field("dataSupport", v, encode_dataSupport)
      a.resolveSupport.foreach: v =>
        enc.field("resolveSupport", v, encode_resolveSupport)
      a.honorsChangeAnnotations.foreach: v =>
        enc.field("honorsChangeAnnotations", v, encode_honorsChangeAnnotations)


private[lsp] trait structures_CodeActionClientCapabilities_CodeActionLiteralSupportCodec:
  import structures.CodeActionClientCapabilities.*
  given fromJson: Decoder[CodeActionLiteralSupport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_codeActionKind: Decoder[CodeActionLiteralSupport.CodeActionKind] = CodeActionLiteralSupport.CodeActionKind.fromJson
    Dec.fromJsonObject: dec =>
      for
        codeActionKind <- dec.get("codeActionKind", decode_codeActionKind)
      yield CodeActionLiteralSupport(
        codeActionKind,
      )
  given toJson: Encoder[CodeActionLiteralSupport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_codeActionKind: Encoder[CodeActionLiteralSupport.CodeActionKind] = CodeActionLiteralSupport.CodeActionKind.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("codeActionKind", a.codeActionKind, encode_codeActionKind)


private[lsp] trait structures_CodeActionClientCapabilities_CodeActionLiteralSupport_CodeActionKindCodec:
  import structures.CodeActionClientCapabilities.CodeActionLiteralSupport.*
  given fromJson: Decoder[CodeActionKind] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_valueSet: Decoder[Vector[enumerations.CodeActionKind]] = Decoder.decodeVector(enumerations.CodeActionKind.fromJson)
    Dec.fromJsonObject: dec =>
      for
        valueSet <- dec.get("valueSet", decode_valueSet)
      yield CodeActionKind(
        valueSet,
      )
  given toJson: Encoder[CodeActionKind] = 
    // cache all encoders for this type when toJson first initialised
    val encode_valueSet: Encoder[Vector[enumerations.CodeActionKind]] = Encoder.encodeVector(enumerations.CodeActionKind.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("valueSet", a.valueSet, encode_valueSet)


private[lsp] trait structures_CodeActionClientCapabilities_ResolveSupportCodec:
  import structures.CodeActionClientCapabilities.*
  given fromJson: Decoder[ResolveSupport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_properties: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    Dec.fromJsonObject: dec =>
      for
        properties <- dec.get("properties", decode_properties)
      yield ResolveSupport(
        properties,
      )
  given toJson: Encoder[ResolveSupport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_properties: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    Enc.toJsonObject: (enc, a) =>
      enc.field("properties", a.properties, encode_properties)


private[lsp] trait structures_CodeActionContextCodec:
  import structures.*
  given fromJson: Decoder[CodeActionContext] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_diagnostics: Decoder[Vector[structures.Diagnostic]] = Decoder.decodeVector(structures.Diagnostic.fromJson)
    val decode_only: Decoder[Vector[enumerations.CodeActionKind]] = Decoder.decodeVector(enumerations.CodeActionKind.fromJson)
    val decode_triggerKind: Decoder[enumerations.CodeActionTriggerKind] = enumerations.CodeActionTriggerKind.fromJson
    Dec.fromJsonObject: dec =>
      for
        diagnostics <- dec.get("diagnostics", decode_diagnostics)
        only <- dec.getOpt("only", decode_only)
        triggerKind <- dec.getOpt("triggerKind", decode_triggerKind)
      yield CodeActionContext(
        diagnostics,
        only,
        triggerKind,
      )
  given toJson: Encoder[CodeActionContext] = 
    // cache all encoders for this type when toJson first initialised
    val encode_diagnostics: Encoder[Vector[structures.Diagnostic]] = Encoder.encodeVector(structures.Diagnostic.toJson)
    val encode_only: Encoder[Vector[enumerations.CodeActionKind]] = Encoder.encodeVector(enumerations.CodeActionKind.toJson)
    val encode_triggerKind: Encoder[enumerations.CodeActionTriggerKind] = enumerations.CodeActionTriggerKind.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("diagnostics", a.diagnostics, encode_diagnostics)
      a.only.foreach: v =>
        enc.field("only", v, encode_only)
      a.triggerKind.foreach: v =>
        enc.field("triggerKind", v, encode_triggerKind)


private[lsp] trait structures_CodeActionOptionsCodec:
  import structures.*
  given fromJson: Decoder[CodeActionOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_codeActionKinds: Decoder[Vector[enumerations.CodeActionKind]] = Decoder.decodeVector(enumerations.CodeActionKind.fromJson)
    val decode_resolveProvider: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        codeActionKinds <- dec.getOpt("codeActionKinds", decode_codeActionKinds)
        resolveProvider <- dec.getOpt("resolveProvider", decode_resolveProvider)
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield CodeActionOptions(
        codeActionKinds,
        resolveProvider,
        workDoneProgress,
      )
  given toJson: Encoder[CodeActionOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_codeActionKinds: Encoder[Vector[enumerations.CodeActionKind]] = Encoder.encodeVector(enumerations.CodeActionKind.toJson)
    val encode_resolveProvider: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.codeActionKinds.foreach: v =>
        enc.field("codeActionKinds", v, encode_codeActionKinds)
      a.resolveProvider.foreach: v =>
        enc.field("resolveProvider", v, encode_resolveProvider)
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_CodeActionParamsCodec:
  import structures.*
  given fromJson: Decoder[CodeActionParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_context: Decoder[structures.CodeActionContext] = structures.CodeActionContext.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        range <- dec.get("range", decode_range)
        context <- dec.get("context", decode_context)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield CodeActionParams(
        textDocument,
        range,
        context,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[CodeActionParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_context: Encoder[structures.CodeActionContext] = structures.CodeActionContext.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("range", a.range, encode_range)
      enc.field("context", a.context, encode_context)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_CodeActionRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[CodeActionRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_codeActionKinds: Decoder[Vector[enumerations.CodeActionKind]] = Decoder.decodeVector(enumerations.CodeActionKind.fromJson)
    val decode_resolveProvider: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        codeActionKinds <- dec.getOpt("codeActionKinds", decode_codeActionKinds)
        resolveProvider <- dec.getOpt("resolveProvider", decode_resolveProvider)
      yield CodeActionRegistrationOptions(
        documentSelector,
        codeActionKinds,
        resolveProvider,
      )
  given toJson: Encoder[CodeActionRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_codeActionKinds: Encoder[Vector[enumerations.CodeActionKind]] = Encoder.encodeVector(enumerations.CodeActionKind.toJson)
    val encode_resolveProvider: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.codeActionKinds.foreach: v =>
        enc.field("codeActionKinds", v, encode_codeActionKinds)
      a.resolveProvider.foreach: v =>
        enc.field("resolveProvider", v, encode_resolveProvider)


private[lsp] trait structures_CodeDescriptionCodec:
  import structures.*
  given fromJson: Decoder[CodeDescription] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_href: Decoder[runtime.Uri] = Uri.fromJson
    Dec.fromJsonObject: dec =>
      for
        href <- dec.get("href", decode_href)
      yield CodeDescription(
        href,
      )
  given toJson: Encoder[CodeDescription] = 
    // cache all encoders for this type when toJson first initialised
    val encode_href: Encoder[runtime.Uri] = Uri.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("href", a.href, encode_href)


private[lsp] trait structures_CodeLensCodec:
  import structures.*
  given fromJson: Decoder[CodeLens] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_command: Decoder[structures.Command] = structures.Command.fromJson
    val decode_data: Decoder[io.circe.Json] = Decoder.decodeJson
    Dec.fromJsonObject: dec =>
      for
        range <- dec.get("range", decode_range)
        command <- dec.getOpt("command", decode_command)
        data <- dec.getOpt("data", decode_data)
      yield CodeLens(
        range,
        command,
        data,
      )
  given toJson: Encoder[CodeLens] = 
    // cache all encoders for this type when toJson first initialised
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_command: Encoder[structures.Command] = structures.Command.toJson
    val encode_data: Encoder[io.circe.Json] = Encoder.encodeJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("range", a.range, encode_range)
      a.command.foreach: v =>
        enc.field("command", v, encode_command)
      a.data.foreach: v =>
        enc.field("data", v, encode_data)


private[lsp] trait structures_CodeLensClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[CodeLensClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
      yield CodeLensClientCapabilities(
        dynamicRegistration,
      )
  given toJson: Encoder[CodeLensClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)


private[lsp] trait structures_CodeLensOptionsCodec:
  import structures.*
  given fromJson: Decoder[CodeLensOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_resolveProvider: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        resolveProvider <- dec.getOpt("resolveProvider", decode_resolveProvider)
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield CodeLensOptions(
        resolveProvider,
        workDoneProgress,
      )
  given toJson: Encoder[CodeLensOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_resolveProvider: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.resolveProvider.foreach: v =>
        enc.field("resolveProvider", v, encode_resolveProvider)
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_CodeLensParamsCodec:
  import structures.*
  given fromJson: Decoder[CodeLensParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield CodeLensParams(
        textDocument,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[CodeLensParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_CodeLensRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[CodeLensRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_resolveProvider: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        resolveProvider <- dec.getOpt("resolveProvider", decode_resolveProvider)
      yield CodeLensRegistrationOptions(
        documentSelector,
        resolveProvider,
      )
  given toJson: Encoder[CodeLensRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_resolveProvider: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.resolveProvider.foreach: v =>
        enc.field("resolveProvider", v, encode_resolveProvider)


private[lsp] trait structures_CodeLensWorkspaceClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[CodeLensWorkspaceClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_refreshSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        refreshSupport <- dec.getOpt("refreshSupport", decode_refreshSupport)
      yield CodeLensWorkspaceClientCapabilities(
        refreshSupport,
      )
  given toJson: Encoder[CodeLensWorkspaceClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_refreshSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.refreshSupport.foreach: v =>
        enc.field("refreshSupport", v, encode_refreshSupport)


private[lsp] trait structures_ColorCodec:
  import structures.*
  given fromJson: Decoder[Color] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_red: Decoder[Float] = Decoder.decodeFloat
    val decode_green: Decoder[Float] = Decoder.decodeFloat
    val decode_blue: Decoder[Float] = Decoder.decodeFloat
    val decode_alpha: Decoder[Float] = Decoder.decodeFloat
    Dec.fromJsonObject: dec =>
      for
        red <- dec.get("red", decode_red)
        green <- dec.get("green", decode_green)
        blue <- dec.get("blue", decode_blue)
        alpha <- dec.get("alpha", decode_alpha)
      yield Color(
        red,
        green,
        blue,
        alpha,
      )
  given toJson: Encoder[Color] = 
    // cache all encoders for this type when toJson first initialised
    val encode_red: Encoder[Float] = Encoder.encodeFloat
    val encode_green: Encoder[Float] = Encoder.encodeFloat
    val encode_blue: Encoder[Float] = Encoder.encodeFloat
    val encode_alpha: Encoder[Float] = Encoder.encodeFloat
    Enc.toJsonObject: (enc, a) =>
      enc.field("red", a.red, encode_red)
      enc.field("green", a.green, encode_green)
      enc.field("blue", a.blue, encode_blue)
      enc.field("alpha", a.alpha, encode_alpha)


private[lsp] trait structures_ColorInformationCodec:
  import structures.*
  given fromJson: Decoder[ColorInformation] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_color: Decoder[structures.Color] = structures.Color.fromJson
    Dec.fromJsonObject: dec =>
      for
        range <- dec.get("range", decode_range)
        color <- dec.get("color", decode_color)
      yield ColorInformation(
        range,
        color,
      )
  given toJson: Encoder[ColorInformation] = 
    // cache all encoders for this type when toJson first initialised
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_color: Encoder[structures.Color] = structures.Color.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("range", a.range, encode_range)
      enc.field("color", a.color, encode_color)


private[lsp] trait structures_ColorPresentationCodec:
  import structures.*
  given fromJson: Decoder[ColorPresentation] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_label: Decoder[String] = Decoder.decodeString
    val decode_textEdit: Decoder[structures.TextEdit] = structures.TextEdit.fromJson
    val decode_additionalTextEdits: Decoder[Vector[structures.TextEdit]] = Decoder.decodeVector(structures.TextEdit.fromJson)
    Dec.fromJsonObject: dec =>
      for
        label <- dec.get("label", decode_label)
        textEdit <- dec.getOpt("textEdit", decode_textEdit)
        additionalTextEdits <- dec.getOpt("additionalTextEdits", decode_additionalTextEdits)
      yield ColorPresentation(
        label,
        textEdit,
        additionalTextEdits,
      )
  given toJson: Encoder[ColorPresentation] = 
    // cache all encoders for this type when toJson first initialised
    val encode_label: Encoder[String] = Encoder.encodeString
    val encode_textEdit: Encoder[structures.TextEdit] = structures.TextEdit.toJson
    val encode_additionalTextEdits: Encoder[Vector[structures.TextEdit]] = Encoder.encodeVector(structures.TextEdit.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("label", a.label, encode_label)
      a.textEdit.foreach: v =>
        enc.field("textEdit", v, encode_textEdit)
      a.additionalTextEdits.foreach: v =>
        enc.field("additionalTextEdits", v, encode_additionalTextEdits)


private[lsp] trait structures_ColorPresentationParamsCodec:
  import structures.*
  given fromJson: Decoder[ColorPresentationParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_color: Decoder[structures.Color] = structures.Color.fromJson
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        color <- dec.get("color", decode_color)
        range <- dec.get("range", decode_range)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield ColorPresentationParams(
        textDocument,
        color,
        range,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[ColorPresentationParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_color: Encoder[structures.Color] = structures.Color.toJson
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("color", a.color, encode_color)
      enc.field("range", a.range, encode_range)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_CommandCodec:
  import structures.*
  given fromJson: Decoder[Command] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_title: Decoder[String] = Decoder.decodeString
    val decode_command: Decoder[String] = Decoder.decodeString
    val decode_arguments: Decoder[Vector[io.circe.Json]] = Decoder.decodeVector(Decoder.decodeJson)
    Dec.fromJsonObject: dec =>
      for
        title <- dec.get("title", decode_title)
        command <- dec.get("command", decode_command)
        arguments <- dec.getOpt("arguments", decode_arguments)
      yield Command(
        title,
        command,
        arguments,
      )
  given toJson: Encoder[Command] = 
    // cache all encoders for this type when toJson first initialised
    val encode_title: Encoder[String] = Encoder.encodeString
    val encode_command: Encoder[String] = Encoder.encodeString
    val encode_arguments: Encoder[Vector[io.circe.Json]] = Encoder.encodeVector(Encoder.encodeJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("title", a.title, encode_title)
      enc.field("command", a.command, encode_command)
      a.arguments.foreach: v =>
        enc.field("arguments", v, encode_arguments)


private[lsp] trait structures_CompletionClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[CompletionClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_completionItem: Decoder[CompletionClientCapabilities.CompletionItem] = CompletionClientCapabilities.CompletionItem.fromJson
    val decode_completionItemKind: Decoder[CompletionClientCapabilities.CompletionItemKind] = CompletionClientCapabilities.CompletionItemKind.fromJson
    val decode_insertTextMode: Decoder[enumerations.InsertTextMode] = enumerations.InsertTextMode.fromJson
    val decode_contextSupport: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_completionList: Decoder[CompletionClientCapabilities.CompletionList] = CompletionClientCapabilities.CompletionList.fromJson
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        completionItem <- dec.getOpt("completionItem", decode_completionItem)
        completionItemKind <- dec.getOpt("completionItemKind", decode_completionItemKind)
        insertTextMode <- dec.getOpt("insertTextMode", decode_insertTextMode)
        contextSupport <- dec.getOpt("contextSupport", decode_contextSupport)
        completionList <- dec.getOpt("completionList", decode_completionList)
      yield CompletionClientCapabilities(
        dynamicRegistration,
        completionItem,
        completionItemKind,
        insertTextMode,
        contextSupport,
        completionList,
      )
  given toJson: Encoder[CompletionClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_completionItem: Encoder[CompletionClientCapabilities.CompletionItem] = CompletionClientCapabilities.CompletionItem.toJson
    val encode_completionItemKind: Encoder[CompletionClientCapabilities.CompletionItemKind] = CompletionClientCapabilities.CompletionItemKind.toJson
    val encode_insertTextMode: Encoder[enumerations.InsertTextMode] = enumerations.InsertTextMode.toJson
    val encode_contextSupport: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_completionList: Encoder[CompletionClientCapabilities.CompletionList] = CompletionClientCapabilities.CompletionList.toJson
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      a.completionItem.foreach: v =>
        enc.field("completionItem", v, encode_completionItem)
      a.completionItemKind.foreach: v =>
        enc.field("completionItemKind", v, encode_completionItemKind)
      a.insertTextMode.foreach: v =>
        enc.field("insertTextMode", v, encode_insertTextMode)
      a.contextSupport.foreach: v =>
        enc.field("contextSupport", v, encode_contextSupport)
      a.completionList.foreach: v =>
        enc.field("completionList", v, encode_completionList)


private[lsp] trait structures_CompletionClientCapabilities_CompletionItemCodec:
  import structures.CompletionClientCapabilities.*
  given fromJson: Decoder[CompletionItem] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_snippetSupport: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_commitCharactersSupport: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_documentationFormat: Decoder[Vector[enumerations.MarkupKind]] = Decoder.decodeVector(enumerations.MarkupKind.fromJson)
    val decode_deprecatedSupport: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_preselectSupport: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_tagSupport: Decoder[CompletionItem.TagSupport] = CompletionItem.TagSupport.fromJson
    val decode_insertReplaceSupport: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_resolveSupport: Decoder[CompletionItem.ResolveSupport] = CompletionItem.ResolveSupport.fromJson
    val decode_insertTextModeSupport: Decoder[CompletionItem.InsertTextModeSupport] = CompletionItem.InsertTextModeSupport.fromJson
    val decode_labelDetailsSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        snippetSupport <- dec.getOpt("snippetSupport", decode_snippetSupport)
        commitCharactersSupport <- dec.getOpt("commitCharactersSupport", decode_commitCharactersSupport)
        documentationFormat <- dec.getOpt("documentationFormat", decode_documentationFormat)
        deprecatedSupport <- dec.getOpt("deprecatedSupport", decode_deprecatedSupport)
        preselectSupport <- dec.getOpt("preselectSupport", decode_preselectSupport)
        tagSupport <- dec.getOpt("tagSupport", decode_tagSupport)
        insertReplaceSupport <- dec.getOpt("insertReplaceSupport", decode_insertReplaceSupport)
        resolveSupport <- dec.getOpt("resolveSupport", decode_resolveSupport)
        insertTextModeSupport <- dec.getOpt("insertTextModeSupport", decode_insertTextModeSupport)
        labelDetailsSupport <- dec.getOpt("labelDetailsSupport", decode_labelDetailsSupport)
      yield CompletionItem(
        snippetSupport,
        commitCharactersSupport,
        documentationFormat,
        deprecatedSupport,
        preselectSupport,
        tagSupport,
        insertReplaceSupport,
        resolveSupport,
        insertTextModeSupport,
        labelDetailsSupport,
      )
  given toJson: Encoder[CompletionItem] = 
    // cache all encoders for this type when toJson first initialised
    val encode_snippetSupport: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_commitCharactersSupport: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_documentationFormat: Encoder[Vector[enumerations.MarkupKind]] = Encoder.encodeVector(enumerations.MarkupKind.toJson)
    val encode_deprecatedSupport: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_preselectSupport: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_tagSupport: Encoder[CompletionItem.TagSupport] = CompletionItem.TagSupport.toJson
    val encode_insertReplaceSupport: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_resolveSupport: Encoder[CompletionItem.ResolveSupport] = CompletionItem.ResolveSupport.toJson
    val encode_insertTextModeSupport: Encoder[CompletionItem.InsertTextModeSupport] = CompletionItem.InsertTextModeSupport.toJson
    val encode_labelDetailsSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.snippetSupport.foreach: v =>
        enc.field("snippetSupport", v, encode_snippetSupport)
      a.commitCharactersSupport.foreach: v =>
        enc.field("commitCharactersSupport", v, encode_commitCharactersSupport)
      a.documentationFormat.foreach: v =>
        enc.field("documentationFormat", v, encode_documentationFormat)
      a.deprecatedSupport.foreach: v =>
        enc.field("deprecatedSupport", v, encode_deprecatedSupport)
      a.preselectSupport.foreach: v =>
        enc.field("preselectSupport", v, encode_preselectSupport)
      a.tagSupport.foreach: v =>
        enc.field("tagSupport", v, encode_tagSupport)
      a.insertReplaceSupport.foreach: v =>
        enc.field("insertReplaceSupport", v, encode_insertReplaceSupport)
      a.resolveSupport.foreach: v =>
        enc.field("resolveSupport", v, encode_resolveSupport)
      a.insertTextModeSupport.foreach: v =>
        enc.field("insertTextModeSupport", v, encode_insertTextModeSupport)
      a.labelDetailsSupport.foreach: v =>
        enc.field("labelDetailsSupport", v, encode_labelDetailsSupport)


private[lsp] trait structures_CompletionClientCapabilities_CompletionItem_TagSupportCodec:
  import structures.CompletionClientCapabilities.CompletionItem.*
  given fromJson: Decoder[TagSupport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_valueSet: Decoder[Vector[enumerations.CompletionItemTag]] = Decoder.decodeVector(enumerations.CompletionItemTag.fromJson)
    Dec.fromJsonObject: dec =>
      for
        valueSet <- dec.get("valueSet", decode_valueSet)
      yield TagSupport(
        valueSet,
      )
  given toJson: Encoder[TagSupport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_valueSet: Encoder[Vector[enumerations.CompletionItemTag]] = Encoder.encodeVector(enumerations.CompletionItemTag.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("valueSet", a.valueSet, encode_valueSet)


private[lsp] trait structures_CompletionClientCapabilities_CompletionItem_ResolveSupportCodec:
  import structures.CompletionClientCapabilities.CompletionItem.*
  given fromJson: Decoder[ResolveSupport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_properties: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    Dec.fromJsonObject: dec =>
      for
        properties <- dec.get("properties", decode_properties)
      yield ResolveSupport(
        properties,
      )
  given toJson: Encoder[ResolveSupport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_properties: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    Enc.toJsonObject: (enc, a) =>
      enc.field("properties", a.properties, encode_properties)


private[lsp] trait structures_CompletionClientCapabilities_CompletionItem_InsertTextModeSupportCodec:
  import structures.CompletionClientCapabilities.CompletionItem.*
  given fromJson: Decoder[InsertTextModeSupport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_valueSet: Decoder[Vector[enumerations.InsertTextMode]] = Decoder.decodeVector(enumerations.InsertTextMode.fromJson)
    Dec.fromJsonObject: dec =>
      for
        valueSet <- dec.get("valueSet", decode_valueSet)
      yield InsertTextModeSupport(
        valueSet,
      )
  given toJson: Encoder[InsertTextModeSupport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_valueSet: Encoder[Vector[enumerations.InsertTextMode]] = Encoder.encodeVector(enumerations.InsertTextMode.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("valueSet", a.valueSet, encode_valueSet)


private[lsp] trait structures_CompletionClientCapabilities_CompletionItemKindCodec:
  import structures.CompletionClientCapabilities.*
  given fromJson: Decoder[CompletionItemKind] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_valueSet: Decoder[Vector[enumerations.CompletionItemKind]] = Decoder.decodeVector(enumerations.CompletionItemKind.fromJson)
    Dec.fromJsonObject: dec =>
      for
        valueSet <- dec.getOpt("valueSet", decode_valueSet)
      yield CompletionItemKind(
        valueSet,
      )
  given toJson: Encoder[CompletionItemKind] = 
    // cache all encoders for this type when toJson first initialised
    val encode_valueSet: Encoder[Vector[enumerations.CompletionItemKind]] = Encoder.encodeVector(enumerations.CompletionItemKind.toJson)
    Enc.toJsonObject: (enc, a) =>
      a.valueSet.foreach: v =>
        enc.field("valueSet", v, encode_valueSet)


private[lsp] trait structures_CompletionClientCapabilities_CompletionListCodec:
  import structures.CompletionClientCapabilities.*
  given fromJson: Decoder[CompletionList] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_itemDefaults: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    Dec.fromJsonObject: dec =>
      for
        itemDefaults <- dec.getOpt("itemDefaults", decode_itemDefaults)
      yield CompletionList(
        itemDefaults,
      )
  given toJson: Encoder[CompletionList] = 
    // cache all encoders for this type when toJson first initialised
    val encode_itemDefaults: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    Enc.toJsonObject: (enc, a) =>
      a.itemDefaults.foreach: v =>
        enc.field("itemDefaults", v, encode_itemDefaults)


private[lsp] trait structures_CompletionContextCodec:
  import structures.*
  given fromJson: Decoder[CompletionContext] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_triggerKind: Decoder[enumerations.CompletionTriggerKind] = enumerations.CompletionTriggerKind.fromJson
    val decode_triggerCharacter: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        triggerKind <- dec.get("triggerKind", decode_triggerKind)
        triggerCharacter <- dec.getOpt("triggerCharacter", decode_triggerCharacter)
      yield CompletionContext(
        triggerKind,
        triggerCharacter,
      )
  given toJson: Encoder[CompletionContext] = 
    // cache all encoders for this type when toJson first initialised
    val encode_triggerKind: Encoder[enumerations.CompletionTriggerKind] = enumerations.CompletionTriggerKind.toJson
    val encode_triggerCharacter: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("triggerKind", a.triggerKind, encode_triggerKind)
      a.triggerCharacter.foreach: v =>
        enc.field("triggerCharacter", v, encode_triggerCharacter)


private[lsp] trait structures_CompletionItemCodec:
  import structures.*
  given fromJson: Decoder[CompletionItem] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_label: Decoder[String] = Decoder.decodeString
    val decode_labelDetails: Decoder[structures.CompletionItemLabelDetails] = structures.CompletionItemLabelDetails.fromJson
    val decode_kind: Decoder[enumerations.CompletionItemKind] = enumerations.CompletionItemKind.fromJson
    val decode_tags: Decoder[Vector[enumerations.CompletionItemTag]] = Decoder.decodeVector(enumerations.CompletionItemTag.fromJson)
    val decode_detail: Decoder[String] = Decoder.decodeString
    val decode_documentation: Decoder[(String | structures.MarkupContent)] = Dec.union2[String, structures.MarkupContent](Decoder.decodeString,structures.MarkupContent.fromJson)
    val decode_deprecated: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_preselect: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_sortText: Decoder[String] = Decoder.decodeString
    val decode_filterText: Decoder[String] = Decoder.decodeString
    val decode_insertText: Decoder[String] = Decoder.decodeString
    val decode_insertTextFormat: Decoder[enumerations.InsertTextFormat] = enumerations.InsertTextFormat.fromJson
    val decode_insertTextMode: Decoder[enumerations.InsertTextMode] = enumerations.InsertTextMode.fromJson
    val decode_textEdit: Decoder[(structures.TextEdit | structures.InsertReplaceEdit)] = Dec.union2[structures.TextEdit, structures.InsertReplaceEdit](structures.TextEdit.fromJson,structures.InsertReplaceEdit.fromJson)
    val decode_textEditText: Decoder[String] = Decoder.decodeString
    val decode_additionalTextEdits: Decoder[Vector[structures.TextEdit]] = Decoder.decodeVector(structures.TextEdit.fromJson)
    val decode_commitCharacters: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    val decode_command: Decoder[structures.Command] = structures.Command.fromJson
    val decode_data: Decoder[io.circe.Json] = Decoder.decodeJson
    Dec.fromJsonObject: dec =>
      for
        label <- dec.get("label", decode_label)
        labelDetails <- dec.getOpt("labelDetails", decode_labelDetails)
        kind <- dec.getOpt("kind", decode_kind)
        tags <- dec.getOpt("tags", decode_tags)
        detail <- dec.getOpt("detail", decode_detail)
        documentation <- dec.getOpt("documentation", decode_documentation)
        deprecated <- dec.getOpt("deprecated", decode_deprecated)
        preselect <- dec.getOpt("preselect", decode_preselect)
        sortText <- dec.getOpt("sortText", decode_sortText)
        filterText <- dec.getOpt("filterText", decode_filterText)
        insertText <- dec.getOpt("insertText", decode_insertText)
        insertTextFormat <- dec.getOpt("insertTextFormat", decode_insertTextFormat)
        insertTextMode <- dec.getOpt("insertTextMode", decode_insertTextMode)
        textEdit <- dec.getOpt("textEdit", decode_textEdit)
        textEditText <- dec.getOpt("textEditText", decode_textEditText)
        additionalTextEdits <- dec.getOpt("additionalTextEdits", decode_additionalTextEdits)
        commitCharacters <- dec.getOpt("commitCharacters", decode_commitCharacters)
        command <- dec.getOpt("command", decode_command)
        data <- dec.getOpt("data", decode_data)
      yield CompletionItem(
        label,
        labelDetails,
        kind,
        tags,
        detail,
        documentation,
        deprecated,
        preselect,
        sortText,
        filterText,
        insertText,
        insertTextFormat,
        insertTextMode,
        textEdit,
        textEditText,
        additionalTextEdits,
        commitCharacters,
        command,
        data,
      )
  given toJson: Encoder[CompletionItem] = 
    // cache all encoders for this type when toJson first initialised
    val encode_label: Encoder[String] = Encoder.encodeString
    val encode_labelDetails: Encoder[structures.CompletionItemLabelDetails] = structures.CompletionItemLabelDetails.toJson
    val encode_kind: Encoder[enumerations.CompletionItemKind] = enumerations.CompletionItemKind.toJson
    val encode_tags: Encoder[Vector[enumerations.CompletionItemTag]] = Encoder.encodeVector(enumerations.CompletionItemTag.toJson)
    val encode_detail: Encoder[String] = Encoder.encodeString
    val encode_documentation: Encoder[(String | structures.MarkupContent)] = Enc.union2[String, structures.MarkupContent](Encoder.encodeString,structures.MarkupContent.toJson)
    val encode_deprecated: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_preselect: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_sortText: Encoder[String] = Encoder.encodeString
    val encode_filterText: Encoder[String] = Encoder.encodeString
    val encode_insertText: Encoder[String] = Encoder.encodeString
    val encode_insertTextFormat: Encoder[enumerations.InsertTextFormat] = enumerations.InsertTextFormat.toJson
    val encode_insertTextMode: Encoder[enumerations.InsertTextMode] = enumerations.InsertTextMode.toJson
    val encode_textEdit: Encoder[(structures.TextEdit | structures.InsertReplaceEdit)] = Enc.union2[structures.TextEdit, structures.InsertReplaceEdit](structures.TextEdit.toJson,structures.InsertReplaceEdit.toJson)
    val encode_textEditText: Encoder[String] = Encoder.encodeString
    val encode_additionalTextEdits: Encoder[Vector[structures.TextEdit]] = Encoder.encodeVector(structures.TextEdit.toJson)
    val encode_commitCharacters: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    val encode_command: Encoder[structures.Command] = structures.Command.toJson
    val encode_data: Encoder[io.circe.Json] = Encoder.encodeJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("label", a.label, encode_label)
      a.labelDetails.foreach: v =>
        enc.field("labelDetails", v, encode_labelDetails)
      a.kind.foreach: v =>
        enc.field("kind", v, encode_kind)
      a.tags.foreach: v =>
        enc.field("tags", v, encode_tags)
      a.detail.foreach: v =>
        enc.field("detail", v, encode_detail)
      a.documentation.foreach: v =>
        enc.field("documentation", v, encode_documentation)
      a.deprecated.foreach: v =>
        enc.field("deprecated", v, encode_deprecated)
      a.preselect.foreach: v =>
        enc.field("preselect", v, encode_preselect)
      a.sortText.foreach: v =>
        enc.field("sortText", v, encode_sortText)
      a.filterText.foreach: v =>
        enc.field("filterText", v, encode_filterText)
      a.insertText.foreach: v =>
        enc.field("insertText", v, encode_insertText)
      a.insertTextFormat.foreach: v =>
        enc.field("insertTextFormat", v, encode_insertTextFormat)
      a.insertTextMode.foreach: v =>
        enc.field("insertTextMode", v, encode_insertTextMode)
      a.textEdit.foreach: v =>
        enc.field("textEdit", v, encode_textEdit)
      a.textEditText.foreach: v =>
        enc.field("textEditText", v, encode_textEditText)
      a.additionalTextEdits.foreach: v =>
        enc.field("additionalTextEdits", v, encode_additionalTextEdits)
      a.commitCharacters.foreach: v =>
        enc.field("commitCharacters", v, encode_commitCharacters)
      a.command.foreach: v =>
        enc.field("command", v, encode_command)
      a.data.foreach: v =>
        enc.field("data", v, encode_data)


private[lsp] trait structures_CompletionItemLabelDetailsCodec:
  import structures.*
  given fromJson: Decoder[CompletionItemLabelDetails] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_detail: Decoder[String] = Decoder.decodeString
    val decode_description: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        detail <- dec.getOpt("detail", decode_detail)
        description <- dec.getOpt("description", decode_description)
      yield CompletionItemLabelDetails(
        detail,
        description,
      )
  given toJson: Encoder[CompletionItemLabelDetails] = 
    // cache all encoders for this type when toJson first initialised
    val encode_detail: Encoder[String] = Encoder.encodeString
    val encode_description: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.detail.foreach: v =>
        enc.field("detail", v, encode_detail)
      a.description.foreach: v =>
        enc.field("description", v, encode_description)


private[lsp] trait structures_CompletionListCodec:
  import structures.*
  given fromJson: Decoder[CompletionList] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_isIncomplete: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_itemDefaults: Decoder[CompletionList.ItemDefaults] = CompletionList.ItemDefaults.fromJson
    val decode_items: Decoder[Vector[structures.CompletionItem]] = Decoder.decodeVector(structures.CompletionItem.fromJson)
    Dec.fromJsonObject: dec =>
      for
        isIncomplete <- dec.get("isIncomplete", decode_isIncomplete)
        itemDefaults <- dec.getOpt("itemDefaults", decode_itemDefaults)
        items <- dec.get("items", decode_items)
      yield CompletionList(
        isIncomplete,
        itemDefaults,
        items,
      )
  given toJson: Encoder[CompletionList] = 
    // cache all encoders for this type when toJson first initialised
    val encode_isIncomplete: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_itemDefaults: Encoder[CompletionList.ItemDefaults] = CompletionList.ItemDefaults.toJson
    val encode_items: Encoder[Vector[structures.CompletionItem]] = Encoder.encodeVector(structures.CompletionItem.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("isIncomplete", a.isIncomplete, encode_isIncomplete)
      a.itemDefaults.foreach: v =>
        enc.field("itemDefaults", v, encode_itemDefaults)
      enc.field("items", a.items, encode_items)


private[lsp] trait structures_CompletionList_ItemDefaultsCodec:
  import structures.CompletionList.*
  given fromJson: Decoder[ItemDefaults] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_commitCharacters: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    val decode_editRange: Decoder[(structures.Range | ItemDefaults.S0)] = Dec.union2[structures.Range, ItemDefaults.S0](structures.Range.fromJson,ItemDefaults.S0.fromJson)
    val decode_insertTextFormat: Decoder[enumerations.InsertTextFormat] = enumerations.InsertTextFormat.fromJson
    val decode_insertTextMode: Decoder[enumerations.InsertTextMode] = enumerations.InsertTextMode.fromJson
    val decode_data: Decoder[io.circe.Json] = Decoder.decodeJson
    Dec.fromJsonObject: dec =>
      for
        commitCharacters <- dec.getOpt("commitCharacters", decode_commitCharacters)
        editRange <- dec.getOpt("editRange", decode_editRange)
        insertTextFormat <- dec.getOpt("insertTextFormat", decode_insertTextFormat)
        insertTextMode <- dec.getOpt("insertTextMode", decode_insertTextMode)
        data <- dec.getOpt("data", decode_data)
      yield ItemDefaults(
        commitCharacters,
        editRange,
        insertTextFormat,
        insertTextMode,
        data,
      )
  given toJson: Encoder[ItemDefaults] = 
    // cache all encoders for this type when toJson first initialised
    val encode_commitCharacters: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    val encode_editRange: Encoder[(structures.Range | ItemDefaults.S0)] = Enc.union2[structures.Range, ItemDefaults.S0](structures.Range.toJson,ItemDefaults.S0.toJson)
    val encode_insertTextFormat: Encoder[enumerations.InsertTextFormat] = enumerations.InsertTextFormat.toJson
    val encode_insertTextMode: Encoder[enumerations.InsertTextMode] = enumerations.InsertTextMode.toJson
    val encode_data: Encoder[io.circe.Json] = Encoder.encodeJson
    Enc.toJsonObject: (enc, a) =>
      a.commitCharacters.foreach: v =>
        enc.field("commitCharacters", v, encode_commitCharacters)
      a.editRange.foreach: v =>
        enc.field("editRange", v, encode_editRange)
      a.insertTextFormat.foreach: v =>
        enc.field("insertTextFormat", v, encode_insertTextFormat)
      a.insertTextMode.foreach: v =>
        enc.field("insertTextMode", v, encode_insertTextMode)
      a.data.foreach: v =>
        enc.field("data", v, encode_data)


private[lsp] trait structures_CompletionList_ItemDefaults_S0Codec:
  import structures.CompletionList.ItemDefaults.*
  given fromJson: Decoder[S0] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_insert: Decoder[structures.Range] = structures.Range.fromJson
    val decode_replace: Decoder[structures.Range] = structures.Range.fromJson
    Dec.fromJsonObject: dec =>
      for
        insert <- dec.get("insert", decode_insert)
        replace <- dec.get("replace", decode_replace)
      yield S0(
        insert,
        replace,
      )
  given toJson: Encoder[S0] = 
    // cache all encoders for this type when toJson first initialised
    val encode_insert: Encoder[structures.Range] = structures.Range.toJson
    val encode_replace: Encoder[structures.Range] = structures.Range.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("insert", a.insert, encode_insert)
      enc.field("replace", a.replace, encode_replace)


private[lsp] trait structures_CompletionOptionsCodec:
  import structures.*
  given fromJson: Decoder[CompletionOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_triggerCharacters: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    val decode_allCommitCharacters: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    val decode_resolveProvider: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_completionItem: Decoder[CompletionOptions.CompletionItem] = CompletionOptions.CompletionItem.fromJson
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        triggerCharacters <- dec.getOpt("triggerCharacters", decode_triggerCharacters)
        allCommitCharacters <- dec.getOpt("allCommitCharacters", decode_allCommitCharacters)
        resolveProvider <- dec.getOpt("resolveProvider", decode_resolveProvider)
        completionItem <- dec.getOpt("completionItem", decode_completionItem)
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield CompletionOptions(
        triggerCharacters,
        allCommitCharacters,
        resolveProvider,
        completionItem,
        workDoneProgress,
      )
  given toJson: Encoder[CompletionOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_triggerCharacters: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    val encode_allCommitCharacters: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    val encode_resolveProvider: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_completionItem: Encoder[CompletionOptions.CompletionItem] = CompletionOptions.CompletionItem.toJson
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.triggerCharacters.foreach: v =>
        enc.field("triggerCharacters", v, encode_triggerCharacters)
      a.allCommitCharacters.foreach: v =>
        enc.field("allCommitCharacters", v, encode_allCommitCharacters)
      a.resolveProvider.foreach: v =>
        enc.field("resolveProvider", v, encode_resolveProvider)
      a.completionItem.foreach: v =>
        enc.field("completionItem", v, encode_completionItem)
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_CompletionOptions_CompletionItemCodec:
  import structures.CompletionOptions.*
  given fromJson: Decoder[CompletionItem] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_labelDetailsSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        labelDetailsSupport <- dec.getOpt("labelDetailsSupport", decode_labelDetailsSupport)
      yield CompletionItem(
        labelDetailsSupport,
      )
  given toJson: Encoder[CompletionItem] = 
    // cache all encoders for this type when toJson first initialised
    val encode_labelDetailsSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.labelDetailsSupport.foreach: v =>
        enc.field("labelDetailsSupport", v, encode_labelDetailsSupport)


private[lsp] trait structures_CompletionParamsCodec:
  import structures.*
  given fromJson: Decoder[CompletionParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_context: Decoder[structures.CompletionContext] = structures.CompletionContext.fromJson
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_position: Decoder[structures.Position] = structures.Position.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        context <- dec.getOpt("context", decode_context)
        textDocument <- dec.get("textDocument", decode_textDocument)
        position <- dec.get("position", decode_position)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield CompletionParams(
        context,
        textDocument,
        position,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[CompletionParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_context: Encoder[structures.CompletionContext] = structures.CompletionContext.toJson
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_position: Encoder[structures.Position] = structures.Position.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      a.context.foreach: v =>
        enc.field("context", v, encode_context)
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("position", a.position, encode_position)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_CompletionRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[CompletionRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_triggerCharacters: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    val decode_allCommitCharacters: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    val decode_resolveProvider: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_completionItem: Decoder[CompletionRegistrationOptions.CompletionItem] = CompletionRegistrationOptions.CompletionItem.fromJson
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        triggerCharacters <- dec.getOpt("triggerCharacters", decode_triggerCharacters)
        allCommitCharacters <- dec.getOpt("allCommitCharacters", decode_allCommitCharacters)
        resolveProvider <- dec.getOpt("resolveProvider", decode_resolveProvider)
        completionItem <- dec.getOpt("completionItem", decode_completionItem)
      yield CompletionRegistrationOptions(
        documentSelector,
        triggerCharacters,
        allCommitCharacters,
        resolveProvider,
        completionItem,
      )
  given toJson: Encoder[CompletionRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_triggerCharacters: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    val encode_allCommitCharacters: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    val encode_resolveProvider: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_completionItem: Encoder[CompletionRegistrationOptions.CompletionItem] = CompletionRegistrationOptions.CompletionItem.toJson
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.triggerCharacters.foreach: v =>
        enc.field("triggerCharacters", v, encode_triggerCharacters)
      a.allCommitCharacters.foreach: v =>
        enc.field("allCommitCharacters", v, encode_allCommitCharacters)
      a.resolveProvider.foreach: v =>
        enc.field("resolveProvider", v, encode_resolveProvider)
      a.completionItem.foreach: v =>
        enc.field("completionItem", v, encode_completionItem)


private[lsp] trait structures_CompletionRegistrationOptions_CompletionItemCodec:
  import structures.CompletionRegistrationOptions.*
  given fromJson: Decoder[CompletionItem] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_labelDetailsSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        labelDetailsSupport <- dec.getOpt("labelDetailsSupport", decode_labelDetailsSupport)
      yield CompletionItem(
        labelDetailsSupport,
      )
  given toJson: Encoder[CompletionItem] = 
    // cache all encoders for this type when toJson first initialised
    val encode_labelDetailsSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.labelDetailsSupport.foreach: v =>
        enc.field("labelDetailsSupport", v, encode_labelDetailsSupport)


private[lsp] trait structures_ConfigurationItemCodec:
  import structures.*
  given fromJson: Decoder[ConfigurationItem] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_scopeUri: Decoder[runtime.Uri] = Uri.fromJson
    val decode_section: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        scopeUri <- dec.getOpt("scopeUri", decode_scopeUri)
        section <- dec.getOpt("section", decode_section)
      yield ConfigurationItem(
        scopeUri,
        section,
      )
  given toJson: Encoder[ConfigurationItem] = 
    // cache all encoders for this type when toJson first initialised
    val encode_scopeUri: Encoder[runtime.Uri] = Uri.toJson
    val encode_section: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.scopeUri.foreach: v =>
        enc.field("scopeUri", v, encode_scopeUri)
      a.section.foreach: v =>
        enc.field("section", v, encode_section)


private[lsp] trait structures_ConfigurationParamsCodec:
  import structures.*
  given fromJson: Decoder[ConfigurationParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_items: Decoder[Vector[structures.ConfigurationItem]] = Decoder.decodeVector(structures.ConfigurationItem.fromJson)
    Dec.fromJsonObject: dec =>
      for
        items <- dec.get("items", decode_items)
      yield ConfigurationParams(
        items,
      )
  given toJson: Encoder[ConfigurationParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_items: Encoder[Vector[structures.ConfigurationItem]] = Encoder.encodeVector(structures.ConfigurationItem.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("items", a.items, encode_items)


private[lsp] trait structures_CreateFileCodec:
  import structures.*
  given fromJson: Decoder[CreateFile] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_kind: Decoder["create"] = Decoder.decodeLiteralString["create"]
    val decode_uri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    val decode_options: Decoder[structures.CreateFileOptions] = structures.CreateFileOptions.fromJson
    val decode_annotationId: Decoder[aliases.ChangeAnnotationIdentifier] = aliases.ChangeAnnotationIdentifier.fromJson
    Dec.fromJsonObject: dec =>
      for
        kind <- dec.get("kind", decode_kind)
        uri <- dec.get("uri", decode_uri)
        options <- dec.getOpt("options", decode_options)
        annotationId <- dec.getOpt("annotationId", decode_annotationId)
      yield CreateFile(
        kind,
        uri,
        options,
        annotationId,
      )
  given toJson: Encoder[CreateFile] = 
    // cache all encoders for this type when toJson first initialised
    val encode_kind: Encoder["create"] = Encoder.encodeLiteralString["create"]
    val encode_uri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    val encode_options: Encoder[structures.CreateFileOptions] = structures.CreateFileOptions.toJson
    val encode_annotationId: Encoder[aliases.ChangeAnnotationIdentifier] = aliases.ChangeAnnotationIdentifier.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("kind", a.kind, encode_kind)
      enc.field("uri", a.uri, encode_uri)
      a.options.foreach: v =>
        enc.field("options", v, encode_options)
      a.annotationId.foreach: v =>
        enc.field("annotationId", v, encode_annotationId)


private[lsp] trait structures_CreateFileOptionsCodec:
  import structures.*
  given fromJson: Decoder[CreateFileOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_overwrite: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_ignoreIfExists: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        overwrite <- dec.getOpt("overwrite", decode_overwrite)
        ignoreIfExists <- dec.getOpt("ignoreIfExists", decode_ignoreIfExists)
      yield CreateFileOptions(
        overwrite,
        ignoreIfExists,
      )
  given toJson: Encoder[CreateFileOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_overwrite: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_ignoreIfExists: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.overwrite.foreach: v =>
        enc.field("overwrite", v, encode_overwrite)
      a.ignoreIfExists.foreach: v =>
        enc.field("ignoreIfExists", v, encode_ignoreIfExists)


private[lsp] trait structures_CreateFilesParamsCodec:
  import structures.*
  given fromJson: Decoder[CreateFilesParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_files: Decoder[Vector[structures.FileCreate]] = Decoder.decodeVector(structures.FileCreate.fromJson)
    Dec.fromJsonObject: dec =>
      for
        files <- dec.get("files", decode_files)
      yield CreateFilesParams(
        files,
      )
  given toJson: Encoder[CreateFilesParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_files: Encoder[Vector[structures.FileCreate]] = Encoder.encodeVector(structures.FileCreate.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("files", a.files, encode_files)


private[lsp] trait structures_DeclarationClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[DeclarationClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_linkSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        linkSupport <- dec.getOpt("linkSupport", decode_linkSupport)
      yield DeclarationClientCapabilities(
        dynamicRegistration,
        linkSupport,
      )
  given toJson: Encoder[DeclarationClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_linkSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      a.linkSupport.foreach: v =>
        enc.field("linkSupport", v, encode_linkSupport)


private[lsp] trait structures_DeclarationOptionsCodec:
  import structures.*
  given fromJson: Decoder[DeclarationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield DeclarationOptions(
        workDoneProgress,
      )
  given toJson: Encoder[DeclarationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_DeclarationParamsCodec:
  import structures.*
  given fromJson: Decoder[DeclarationParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_position: Decoder[structures.Position] = structures.Position.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        position <- dec.get("position", decode_position)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield DeclarationParams(
        textDocument,
        position,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[DeclarationParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_position: Encoder[structures.Position] = structures.Position.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("position", a.position, encode_position)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_DeclarationRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[DeclarationRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_id: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        id <- dec.getOpt("id", decode_id)
      yield DeclarationRegistrationOptions(
        documentSelector,
        id,
      )
  given toJson: Encoder[DeclarationRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_id: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.id.foreach: v =>
        enc.field("id", v, encode_id)


private[lsp] trait structures_DefinitionClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[DefinitionClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_linkSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        linkSupport <- dec.getOpt("linkSupport", decode_linkSupport)
      yield DefinitionClientCapabilities(
        dynamicRegistration,
        linkSupport,
      )
  given toJson: Encoder[DefinitionClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_linkSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      a.linkSupport.foreach: v =>
        enc.field("linkSupport", v, encode_linkSupport)


private[lsp] trait structures_DefinitionOptionsCodec:
  import structures.*
  given fromJson: Decoder[DefinitionOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield DefinitionOptions(
        workDoneProgress,
      )
  given toJson: Encoder[DefinitionOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_DefinitionParamsCodec:
  import structures.*
  given fromJson: Decoder[DefinitionParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_position: Decoder[structures.Position] = structures.Position.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        position <- dec.get("position", decode_position)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield DefinitionParams(
        textDocument,
        position,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[DefinitionParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_position: Encoder[structures.Position] = structures.Position.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("position", a.position, encode_position)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_DefinitionRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[DefinitionRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
      yield DefinitionRegistrationOptions(
        documentSelector,
      )
  given toJson: Encoder[DefinitionRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)


private[lsp] trait structures_DeleteFileCodec:
  import structures.*
  given fromJson: Decoder[DeleteFile] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_kind: Decoder["delete"] = Decoder.decodeLiteralString["delete"]
    val decode_uri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    val decode_options: Decoder[structures.DeleteFileOptions] = structures.DeleteFileOptions.fromJson
    val decode_annotationId: Decoder[aliases.ChangeAnnotationIdentifier] = aliases.ChangeAnnotationIdentifier.fromJson
    Dec.fromJsonObject: dec =>
      for
        kind <- dec.get("kind", decode_kind)
        uri <- dec.get("uri", decode_uri)
        options <- dec.getOpt("options", decode_options)
        annotationId <- dec.getOpt("annotationId", decode_annotationId)
      yield DeleteFile(
        kind,
        uri,
        options,
        annotationId,
      )
  given toJson: Encoder[DeleteFile] = 
    // cache all encoders for this type when toJson first initialised
    val encode_kind: Encoder["delete"] = Encoder.encodeLiteralString["delete"]
    val encode_uri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    val encode_options: Encoder[structures.DeleteFileOptions] = structures.DeleteFileOptions.toJson
    val encode_annotationId: Encoder[aliases.ChangeAnnotationIdentifier] = aliases.ChangeAnnotationIdentifier.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("kind", a.kind, encode_kind)
      enc.field("uri", a.uri, encode_uri)
      a.options.foreach: v =>
        enc.field("options", v, encode_options)
      a.annotationId.foreach: v =>
        enc.field("annotationId", v, encode_annotationId)


private[lsp] trait structures_DeleteFileOptionsCodec:
  import structures.*
  given fromJson: Decoder[DeleteFileOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_recursive: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_ignoreIfNotExists: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        recursive <- dec.getOpt("recursive", decode_recursive)
        ignoreIfNotExists <- dec.getOpt("ignoreIfNotExists", decode_ignoreIfNotExists)
      yield DeleteFileOptions(
        recursive,
        ignoreIfNotExists,
      )
  given toJson: Encoder[DeleteFileOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_recursive: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_ignoreIfNotExists: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.recursive.foreach: v =>
        enc.field("recursive", v, encode_recursive)
      a.ignoreIfNotExists.foreach: v =>
        enc.field("ignoreIfNotExists", v, encode_ignoreIfNotExists)


private[lsp] trait structures_DeleteFilesParamsCodec:
  import structures.*
  given fromJson: Decoder[DeleteFilesParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_files: Decoder[Vector[structures.FileDelete]] = Decoder.decodeVector(structures.FileDelete.fromJson)
    Dec.fromJsonObject: dec =>
      for
        files <- dec.get("files", decode_files)
      yield DeleteFilesParams(
        files,
      )
  given toJson: Encoder[DeleteFilesParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_files: Encoder[Vector[structures.FileDelete]] = Encoder.encodeVector(structures.FileDelete.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("files", a.files, encode_files)


private[lsp] trait structures_DiagnosticCodec:
  import structures.*
  given fromJson: Decoder[Diagnostic] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_severity: Decoder[enumerations.DiagnosticSeverity] = enumerations.DiagnosticSeverity.fromJson
    val decode_code: Decoder[(Int | String)] = Dec.union2[Int, String](Decoder.decodeInt,Decoder.decodeString)
    val decode_codeDescription: Decoder[structures.CodeDescription] = structures.CodeDescription.fromJson
    val decode_source: Decoder[String] = Decoder.decodeString
    val decode_message: Decoder[String] = Decoder.decodeString
    val decode_tags: Decoder[Vector[enumerations.DiagnosticTag]] = Decoder.decodeVector(enumerations.DiagnosticTag.fromJson)
    val decode_relatedInformation: Decoder[Vector[structures.DiagnosticRelatedInformation]] = Decoder.decodeVector(structures.DiagnosticRelatedInformation.fromJson)
    val decode_data: Decoder[io.circe.Json] = Decoder.decodeJson
    Dec.fromJsonObject: dec =>
      for
        range <- dec.get("range", decode_range)
        severity <- dec.getOpt("severity", decode_severity)
        code <- dec.getOpt("code", decode_code)
        codeDescription <- dec.getOpt("codeDescription", decode_codeDescription)
        source <- dec.getOpt("source", decode_source)
        message <- dec.get("message", decode_message)
        tags <- dec.getOpt("tags", decode_tags)
        relatedInformation <- dec.getOpt("relatedInformation", decode_relatedInformation)
        data <- dec.getOpt("data", decode_data)
      yield Diagnostic(
        range,
        severity,
        code,
        codeDescription,
        source,
        message,
        tags,
        relatedInformation,
        data,
      )
  given toJson: Encoder[Diagnostic] = 
    // cache all encoders for this type when toJson first initialised
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_severity: Encoder[enumerations.DiagnosticSeverity] = enumerations.DiagnosticSeverity.toJson
    val encode_code: Encoder[(Int | String)] = Enc.union2[Int, String](Encoder.encodeInt,Encoder.encodeString)
    val encode_codeDescription: Encoder[structures.CodeDescription] = structures.CodeDescription.toJson
    val encode_source: Encoder[String] = Encoder.encodeString
    val encode_message: Encoder[String] = Encoder.encodeString
    val encode_tags: Encoder[Vector[enumerations.DiagnosticTag]] = Encoder.encodeVector(enumerations.DiagnosticTag.toJson)
    val encode_relatedInformation: Encoder[Vector[structures.DiagnosticRelatedInformation]] = Encoder.encodeVector(structures.DiagnosticRelatedInformation.toJson)
    val encode_data: Encoder[io.circe.Json] = Encoder.encodeJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("range", a.range, encode_range)
      a.severity.foreach: v =>
        enc.field("severity", v, encode_severity)
      a.code.foreach: v =>
        enc.field("code", v, encode_code)
      a.codeDescription.foreach: v =>
        enc.field("codeDescription", v, encode_codeDescription)
      a.source.foreach: v =>
        enc.field("source", v, encode_source)
      enc.field("message", a.message, encode_message)
      a.tags.foreach: v =>
        enc.field("tags", v, encode_tags)
      a.relatedInformation.foreach: v =>
        enc.field("relatedInformation", v, encode_relatedInformation)
      a.data.foreach: v =>
        enc.field("data", v, encode_data)


private[lsp] trait structures_DiagnosticClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[DiagnosticClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_relatedDocumentSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        relatedDocumentSupport <- dec.getOpt("relatedDocumentSupport", decode_relatedDocumentSupport)
      yield DiagnosticClientCapabilities(
        dynamicRegistration,
        relatedDocumentSupport,
      )
  given toJson: Encoder[DiagnosticClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_relatedDocumentSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      a.relatedDocumentSupport.foreach: v =>
        enc.field("relatedDocumentSupport", v, encode_relatedDocumentSupport)


private[lsp] trait structures_DiagnosticOptionsCodec:
  import structures.*
  given fromJson: Decoder[DiagnosticOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_identifier: Decoder[String] = Decoder.decodeString
    val decode_interFileDependencies: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_workspaceDiagnostics: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        identifier <- dec.getOpt("identifier", decode_identifier)
        interFileDependencies <- dec.get("interFileDependencies", decode_interFileDependencies)
        workspaceDiagnostics <- dec.get("workspaceDiagnostics", decode_workspaceDiagnostics)
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield DiagnosticOptions(
        identifier,
        interFileDependencies,
        workspaceDiagnostics,
        workDoneProgress,
      )
  given toJson: Encoder[DiagnosticOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_identifier: Encoder[String] = Encoder.encodeString
    val encode_interFileDependencies: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_workspaceDiagnostics: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.identifier.foreach: v =>
        enc.field("identifier", v, encode_identifier)
      enc.field("interFileDependencies", a.interFileDependencies, encode_interFileDependencies)
      enc.field("workspaceDiagnostics", a.workspaceDiagnostics, encode_workspaceDiagnostics)
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_DiagnosticRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[DiagnosticRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_identifier: Decoder[String] = Decoder.decodeString
    val decode_interFileDependencies: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_workspaceDiagnostics: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_id: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        identifier <- dec.getOpt("identifier", decode_identifier)
        interFileDependencies <- dec.get("interFileDependencies", decode_interFileDependencies)
        workspaceDiagnostics <- dec.get("workspaceDiagnostics", decode_workspaceDiagnostics)
        id <- dec.getOpt("id", decode_id)
      yield DiagnosticRegistrationOptions(
        documentSelector,
        identifier,
        interFileDependencies,
        workspaceDiagnostics,
        id,
      )
  given toJson: Encoder[DiagnosticRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_identifier: Encoder[String] = Encoder.encodeString
    val encode_interFileDependencies: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_workspaceDiagnostics: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_id: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.identifier.foreach: v =>
        enc.field("identifier", v, encode_identifier)
      enc.field("interFileDependencies", a.interFileDependencies, encode_interFileDependencies)
      enc.field("workspaceDiagnostics", a.workspaceDiagnostics, encode_workspaceDiagnostics)
      a.id.foreach: v =>
        enc.field("id", v, encode_id)


private[lsp] trait structures_DiagnosticRelatedInformationCodec:
  import structures.*
  given fromJson: Decoder[DiagnosticRelatedInformation] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_location: Decoder[structures.Location] = structures.Location.fromJson
    val decode_message: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        location <- dec.get("location", decode_location)
        message <- dec.get("message", decode_message)
      yield DiagnosticRelatedInformation(
        location,
        message,
      )
  given toJson: Encoder[DiagnosticRelatedInformation] = 
    // cache all encoders for this type when toJson first initialised
    val encode_location: Encoder[structures.Location] = structures.Location.toJson
    val encode_message: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("location", a.location, encode_location)
      enc.field("message", a.message, encode_message)


private[lsp] trait structures_DiagnosticServerCancellationDataCodec:
  import structures.*
  given fromJson: Decoder[DiagnosticServerCancellationData] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_retriggerRequest: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        retriggerRequest <- dec.get("retriggerRequest", decode_retriggerRequest)
      yield DiagnosticServerCancellationData(
        retriggerRequest,
      )
  given toJson: Encoder[DiagnosticServerCancellationData] = 
    // cache all encoders for this type when toJson first initialised
    val encode_retriggerRequest: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      enc.field("retriggerRequest", a.retriggerRequest, encode_retriggerRequest)


private[lsp] trait structures_DiagnosticWorkspaceClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[DiagnosticWorkspaceClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_refreshSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        refreshSupport <- dec.getOpt("refreshSupport", decode_refreshSupport)
      yield DiagnosticWorkspaceClientCapabilities(
        refreshSupport,
      )
  given toJson: Encoder[DiagnosticWorkspaceClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_refreshSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.refreshSupport.foreach: v =>
        enc.field("refreshSupport", v, encode_refreshSupport)


private[lsp] trait structures_DidChangeConfigurationClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[DidChangeConfigurationClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
      yield DidChangeConfigurationClientCapabilities(
        dynamicRegistration,
      )
  given toJson: Encoder[DidChangeConfigurationClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)


private[lsp] trait structures_DidChangeConfigurationParamsCodec:
  import structures.*
  given fromJson: Decoder[DidChangeConfigurationParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_settings: Decoder[io.circe.Json] = Decoder.decodeJson
    Dec.fromJsonObject: dec =>
      for
        settings <- dec.get("settings", decode_settings)
      yield DidChangeConfigurationParams(
        settings,
      )
  given toJson: Encoder[DidChangeConfigurationParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_settings: Encoder[io.circe.Json] = Encoder.encodeJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("settings", a.settings, encode_settings)


private[lsp] trait structures_DidChangeConfigurationRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[DidChangeConfigurationRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_section: Decoder[(String | Vector[String])] = Dec.union2[String, Vector[String]](Decoder.decodeString,Decoder.decodeVector(Decoder.decodeString))
    Dec.fromJsonObject: dec =>
      for
        section <- dec.getOpt("section", decode_section)
      yield DidChangeConfigurationRegistrationOptions(
        section,
      )
  given toJson: Encoder[DidChangeConfigurationRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_section: Encoder[(String | Vector[String])] = Enc.union2[String, Vector[String]](Encoder.encodeString,Encoder.encodeVector(Encoder.encodeString))
    Enc.toJsonObject: (enc, a) =>
      a.section.foreach: v =>
        enc.field("section", v, encode_section)


private[lsp] trait structures_DidChangeNotebookDocumentParamsCodec:
  import structures.*
  given fromJson: Decoder[DidChangeNotebookDocumentParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_notebookDocument: Decoder[structures.VersionedNotebookDocumentIdentifier] = structures.VersionedNotebookDocumentIdentifier.fromJson
    val decode_change: Decoder[structures.NotebookDocumentChangeEvent] = structures.NotebookDocumentChangeEvent.fromJson
    Dec.fromJsonObject: dec =>
      for
        notebookDocument <- dec.get("notebookDocument", decode_notebookDocument)
        change <- dec.get("change", decode_change)
      yield DidChangeNotebookDocumentParams(
        notebookDocument,
        change,
      )
  given toJson: Encoder[DidChangeNotebookDocumentParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_notebookDocument: Encoder[structures.VersionedNotebookDocumentIdentifier] = structures.VersionedNotebookDocumentIdentifier.toJson
    val encode_change: Encoder[structures.NotebookDocumentChangeEvent] = structures.NotebookDocumentChangeEvent.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("notebookDocument", a.notebookDocument, encode_notebookDocument)
      enc.field("change", a.change, encode_change)


private[lsp] trait structures_DidChangeTextDocumentParamsCodec:
  import structures.*
  given fromJson: Decoder[DidChangeTextDocumentParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.VersionedTextDocumentIdentifier] = structures.VersionedTextDocumentIdentifier.fromJson
    val decode_contentChanges: Decoder[Vector[aliases.TextDocumentContentChangeEvent]] = Decoder.decodeVector(aliases.TextDocumentContentChangeEvent.fromJson)
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        contentChanges <- dec.get("contentChanges", decode_contentChanges)
      yield DidChangeTextDocumentParams(
        textDocument,
        contentChanges,
      )
  given toJson: Encoder[DidChangeTextDocumentParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.VersionedTextDocumentIdentifier] = structures.VersionedTextDocumentIdentifier.toJson
    val encode_contentChanges: Encoder[Vector[aliases.TextDocumentContentChangeEvent]] = Encoder.encodeVector(aliases.TextDocumentContentChangeEvent.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("contentChanges", a.contentChanges, encode_contentChanges)


private[lsp] trait structures_DidChangeWatchedFilesClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[DidChangeWatchedFilesClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_relativePatternSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        relativePatternSupport <- dec.getOpt("relativePatternSupport", decode_relativePatternSupport)
      yield DidChangeWatchedFilesClientCapabilities(
        dynamicRegistration,
        relativePatternSupport,
      )
  given toJson: Encoder[DidChangeWatchedFilesClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_relativePatternSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      a.relativePatternSupport.foreach: v =>
        enc.field("relativePatternSupport", v, encode_relativePatternSupport)


private[lsp] trait structures_DidChangeWatchedFilesParamsCodec:
  import structures.*
  given fromJson: Decoder[DidChangeWatchedFilesParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_changes: Decoder[Vector[structures.FileEvent]] = Decoder.decodeVector(structures.FileEvent.fromJson)
    Dec.fromJsonObject: dec =>
      for
        changes <- dec.get("changes", decode_changes)
      yield DidChangeWatchedFilesParams(
        changes,
      )
  given toJson: Encoder[DidChangeWatchedFilesParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_changes: Encoder[Vector[structures.FileEvent]] = Encoder.encodeVector(structures.FileEvent.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("changes", a.changes, encode_changes)


private[lsp] trait structures_DidChangeWatchedFilesRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[DidChangeWatchedFilesRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_watchers: Decoder[Vector[structures.FileSystemWatcher]] = Decoder.decodeVector(structures.FileSystemWatcher.fromJson)
    Dec.fromJsonObject: dec =>
      for
        watchers <- dec.get("watchers", decode_watchers)
      yield DidChangeWatchedFilesRegistrationOptions(
        watchers,
      )
  given toJson: Encoder[DidChangeWatchedFilesRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_watchers: Encoder[Vector[structures.FileSystemWatcher]] = Encoder.encodeVector(structures.FileSystemWatcher.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("watchers", a.watchers, encode_watchers)


private[lsp] trait structures_DidChangeWorkspaceFoldersParamsCodec:
  import structures.*
  given fromJson: Decoder[DidChangeWorkspaceFoldersParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_event: Decoder[structures.WorkspaceFoldersChangeEvent] = structures.WorkspaceFoldersChangeEvent.fromJson
    Dec.fromJsonObject: dec =>
      for
        event <- dec.get("event", decode_event)
      yield DidChangeWorkspaceFoldersParams(
        event,
      )
  given toJson: Encoder[DidChangeWorkspaceFoldersParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_event: Encoder[structures.WorkspaceFoldersChangeEvent] = structures.WorkspaceFoldersChangeEvent.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("event", a.event, encode_event)


private[lsp] trait structures_DidCloseNotebookDocumentParamsCodec:
  import structures.*
  given fromJson: Decoder[DidCloseNotebookDocumentParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_notebookDocument: Decoder[structures.NotebookDocumentIdentifier] = structures.NotebookDocumentIdentifier.fromJson
    val decode_cellTextDocuments: Decoder[Vector[structures.TextDocumentIdentifier]] = Decoder.decodeVector(structures.TextDocumentIdentifier.fromJson)
    Dec.fromJsonObject: dec =>
      for
        notebookDocument <- dec.get("notebookDocument", decode_notebookDocument)
        cellTextDocuments <- dec.get("cellTextDocuments", decode_cellTextDocuments)
      yield DidCloseNotebookDocumentParams(
        notebookDocument,
        cellTextDocuments,
      )
  given toJson: Encoder[DidCloseNotebookDocumentParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_notebookDocument: Encoder[structures.NotebookDocumentIdentifier] = structures.NotebookDocumentIdentifier.toJson
    val encode_cellTextDocuments: Encoder[Vector[structures.TextDocumentIdentifier]] = Encoder.encodeVector(structures.TextDocumentIdentifier.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("notebookDocument", a.notebookDocument, encode_notebookDocument)
      enc.field("cellTextDocuments", a.cellTextDocuments, encode_cellTextDocuments)


private[lsp] trait structures_DidCloseTextDocumentParamsCodec:
  import structures.*
  given fromJson: Decoder[DidCloseTextDocumentParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
      yield DidCloseTextDocumentParams(
        textDocument,
      )
  given toJson: Encoder[DidCloseTextDocumentParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)


private[lsp] trait structures_DidOpenNotebookDocumentParamsCodec:
  import structures.*
  given fromJson: Decoder[DidOpenNotebookDocumentParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_notebookDocument: Decoder[structures.NotebookDocument] = structures.NotebookDocument.fromJson
    val decode_cellTextDocuments: Decoder[Vector[structures.TextDocumentItem]] = Decoder.decodeVector(structures.TextDocumentItem.fromJson)
    Dec.fromJsonObject: dec =>
      for
        notebookDocument <- dec.get("notebookDocument", decode_notebookDocument)
        cellTextDocuments <- dec.get("cellTextDocuments", decode_cellTextDocuments)
      yield DidOpenNotebookDocumentParams(
        notebookDocument,
        cellTextDocuments,
      )
  given toJson: Encoder[DidOpenNotebookDocumentParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_notebookDocument: Encoder[structures.NotebookDocument] = structures.NotebookDocument.toJson
    val encode_cellTextDocuments: Encoder[Vector[structures.TextDocumentItem]] = Encoder.encodeVector(structures.TextDocumentItem.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("notebookDocument", a.notebookDocument, encode_notebookDocument)
      enc.field("cellTextDocuments", a.cellTextDocuments, encode_cellTextDocuments)


private[lsp] trait structures_DidOpenTextDocumentParamsCodec:
  import structures.*
  given fromJson: Decoder[DidOpenTextDocumentParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentItem] = structures.TextDocumentItem.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
      yield DidOpenTextDocumentParams(
        textDocument,
      )
  given toJson: Encoder[DidOpenTextDocumentParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentItem] = structures.TextDocumentItem.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)


private[lsp] trait structures_DidSaveNotebookDocumentParamsCodec:
  import structures.*
  given fromJson: Decoder[DidSaveNotebookDocumentParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_notebookDocument: Decoder[structures.NotebookDocumentIdentifier] = structures.NotebookDocumentIdentifier.fromJson
    Dec.fromJsonObject: dec =>
      for
        notebookDocument <- dec.get("notebookDocument", decode_notebookDocument)
      yield DidSaveNotebookDocumentParams(
        notebookDocument,
      )
  given toJson: Encoder[DidSaveNotebookDocumentParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_notebookDocument: Encoder[structures.NotebookDocumentIdentifier] = structures.NotebookDocumentIdentifier.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("notebookDocument", a.notebookDocument, encode_notebookDocument)


private[lsp] trait structures_DidSaveTextDocumentParamsCodec:
  import structures.*
  given fromJson: Decoder[DidSaveTextDocumentParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_text: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        text <- dec.getOpt("text", decode_text)
      yield DidSaveTextDocumentParams(
        textDocument,
        text,
      )
  given toJson: Encoder[DidSaveTextDocumentParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_text: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      a.text.foreach: v =>
        enc.field("text", v, encode_text)


private[lsp] trait structures_DocumentColorClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[DocumentColorClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
      yield DocumentColorClientCapabilities(
        dynamicRegistration,
      )
  given toJson: Encoder[DocumentColorClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)


private[lsp] trait structures_DocumentColorOptionsCodec:
  import structures.*
  given fromJson: Decoder[DocumentColorOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield DocumentColorOptions(
        workDoneProgress,
      )
  given toJson: Encoder[DocumentColorOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_DocumentColorParamsCodec:
  import structures.*
  given fromJson: Decoder[DocumentColorParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield DocumentColorParams(
        textDocument,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[DocumentColorParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_DocumentColorRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[DocumentColorRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_id: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        id <- dec.getOpt("id", decode_id)
      yield DocumentColorRegistrationOptions(
        documentSelector,
        id,
      )
  given toJson: Encoder[DocumentColorRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_id: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.id.foreach: v =>
        enc.field("id", v, encode_id)


private[lsp] trait structures_DocumentDiagnosticParamsCodec:
  import structures.*
  given fromJson: Decoder[DocumentDiagnosticParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_identifier: Decoder[String] = Decoder.decodeString
    val decode_previousResultId: Decoder[String] = Decoder.decodeString
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        identifier <- dec.getOpt("identifier", decode_identifier)
        previousResultId <- dec.getOpt("previousResultId", decode_previousResultId)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield DocumentDiagnosticParams(
        textDocument,
        identifier,
        previousResultId,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[DocumentDiagnosticParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_identifier: Encoder[String] = Encoder.encodeString
    val encode_previousResultId: Encoder[String] = Encoder.encodeString
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      a.identifier.foreach: v =>
        enc.field("identifier", v, encode_identifier)
      a.previousResultId.foreach: v =>
        enc.field("previousResultId", v, encode_previousResultId)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_DocumentDiagnosticReportPartialResultCodec:
  import structures.*
  given fromJson: Decoder[DocumentDiagnosticReportPartialResult] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_relatedDocuments: Decoder[Map[runtime.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)]] = Decoder.decodeMap(KeyDecoder.decodeKeyString.map(runtime.DocumentUri.apply), Dec.union2[structures.FullDocumentDiagnosticReport, structures.UnchangedDocumentDiagnosticReport](structures.FullDocumentDiagnosticReport.fromJson,structures.UnchangedDocumentDiagnosticReport.fromJson))
    Dec.fromJsonObject: dec =>
      for
        relatedDocuments <- dec.get("relatedDocuments", decode_relatedDocuments)
      yield DocumentDiagnosticReportPartialResult(
        relatedDocuments,
      )
  given toJson: Encoder[DocumentDiagnosticReportPartialResult] = 
    // cache all encoders for this type when toJson first initialised
    val encode_relatedDocuments: Encoder[Map[runtime.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)]] = Encoder.encodeMap(KeyEncoder.encodeKeyString.contramap(_.value), Enc.union2[structures.FullDocumentDiagnosticReport, structures.UnchangedDocumentDiagnosticReport](structures.FullDocumentDiagnosticReport.toJson,structures.UnchangedDocumentDiagnosticReport.toJson))
    Enc.toJsonObject: (enc, a) =>
      enc.field("relatedDocuments", a.relatedDocuments, encode_relatedDocuments)


private[lsp] trait structures_DocumentFormattingClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[DocumentFormattingClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
      yield DocumentFormattingClientCapabilities(
        dynamicRegistration,
      )
  given toJson: Encoder[DocumentFormattingClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)


private[lsp] trait structures_DocumentFormattingOptionsCodec:
  import structures.*
  given fromJson: Decoder[DocumentFormattingOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield DocumentFormattingOptions(
        workDoneProgress,
      )
  given toJson: Encoder[DocumentFormattingOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_DocumentFormattingParamsCodec:
  import structures.*
  given fromJson: Decoder[DocumentFormattingParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_options: Decoder[structures.FormattingOptions] = structures.FormattingOptions.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        options <- dec.get("options", decode_options)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
      yield DocumentFormattingParams(
        textDocument,
        options,
        workDoneToken,
      )
  given toJson: Encoder[DocumentFormattingParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_options: Encoder[structures.FormattingOptions] = structures.FormattingOptions.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("options", a.options, encode_options)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)


private[lsp] trait structures_DocumentFormattingRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[DocumentFormattingRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
      yield DocumentFormattingRegistrationOptions(
        documentSelector,
      )
  given toJson: Encoder[DocumentFormattingRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)


private[lsp] trait structures_DocumentHighlightCodec:
  import structures.*
  given fromJson: Decoder[DocumentHighlight] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_kind: Decoder[enumerations.DocumentHighlightKind] = enumerations.DocumentHighlightKind.fromJson
    Dec.fromJsonObject: dec =>
      for
        range <- dec.get("range", decode_range)
        kind <- dec.getOpt("kind", decode_kind)
      yield DocumentHighlight(
        range,
        kind,
      )
  given toJson: Encoder[DocumentHighlight] = 
    // cache all encoders for this type when toJson first initialised
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_kind: Encoder[enumerations.DocumentHighlightKind] = enumerations.DocumentHighlightKind.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("range", a.range, encode_range)
      a.kind.foreach: v =>
        enc.field("kind", v, encode_kind)


private[lsp] trait structures_DocumentHighlightClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[DocumentHighlightClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
      yield DocumentHighlightClientCapabilities(
        dynamicRegistration,
      )
  given toJson: Encoder[DocumentHighlightClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)


private[lsp] trait structures_DocumentHighlightOptionsCodec:
  import structures.*
  given fromJson: Decoder[DocumentHighlightOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield DocumentHighlightOptions(
        workDoneProgress,
      )
  given toJson: Encoder[DocumentHighlightOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_DocumentHighlightParamsCodec:
  import structures.*
  given fromJson: Decoder[DocumentHighlightParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_position: Decoder[structures.Position] = structures.Position.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        position <- dec.get("position", decode_position)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield DocumentHighlightParams(
        textDocument,
        position,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[DocumentHighlightParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_position: Encoder[structures.Position] = structures.Position.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("position", a.position, encode_position)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_DocumentHighlightRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[DocumentHighlightRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
      yield DocumentHighlightRegistrationOptions(
        documentSelector,
      )
  given toJson: Encoder[DocumentHighlightRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)


private[lsp] trait structures_DocumentLinkCodec:
  import structures.*
  given fromJson: Decoder[DocumentLink] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_target: Decoder[runtime.Uri] = Uri.fromJson
    val decode_tooltip: Decoder[String] = Decoder.decodeString
    val decode_data: Decoder[io.circe.Json] = Decoder.decodeJson
    Dec.fromJsonObject: dec =>
      for
        range <- dec.get("range", decode_range)
        target <- dec.getOpt("target", decode_target)
        tooltip <- dec.getOpt("tooltip", decode_tooltip)
        data <- dec.getOpt("data", decode_data)
      yield DocumentLink(
        range,
        target,
        tooltip,
        data,
      )
  given toJson: Encoder[DocumentLink] = 
    // cache all encoders for this type when toJson first initialised
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_target: Encoder[runtime.Uri] = Uri.toJson
    val encode_tooltip: Encoder[String] = Encoder.encodeString
    val encode_data: Encoder[io.circe.Json] = Encoder.encodeJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("range", a.range, encode_range)
      a.target.foreach: v =>
        enc.field("target", v, encode_target)
      a.tooltip.foreach: v =>
        enc.field("tooltip", v, encode_tooltip)
      a.data.foreach: v =>
        enc.field("data", v, encode_data)


private[lsp] trait structures_DocumentLinkClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[DocumentLinkClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_tooltipSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        tooltipSupport <- dec.getOpt("tooltipSupport", decode_tooltipSupport)
      yield DocumentLinkClientCapabilities(
        dynamicRegistration,
        tooltipSupport,
      )
  given toJson: Encoder[DocumentLinkClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_tooltipSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      a.tooltipSupport.foreach: v =>
        enc.field("tooltipSupport", v, encode_tooltipSupport)


private[lsp] trait structures_DocumentLinkOptionsCodec:
  import structures.*
  given fromJson: Decoder[DocumentLinkOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_resolveProvider: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        resolveProvider <- dec.getOpt("resolveProvider", decode_resolveProvider)
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield DocumentLinkOptions(
        resolveProvider,
        workDoneProgress,
      )
  given toJson: Encoder[DocumentLinkOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_resolveProvider: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.resolveProvider.foreach: v =>
        enc.field("resolveProvider", v, encode_resolveProvider)
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_DocumentLinkParamsCodec:
  import structures.*
  given fromJson: Decoder[DocumentLinkParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield DocumentLinkParams(
        textDocument,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[DocumentLinkParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_DocumentLinkRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[DocumentLinkRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_resolveProvider: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        resolveProvider <- dec.getOpt("resolveProvider", decode_resolveProvider)
      yield DocumentLinkRegistrationOptions(
        documentSelector,
        resolveProvider,
      )
  given toJson: Encoder[DocumentLinkRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_resolveProvider: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.resolveProvider.foreach: v =>
        enc.field("resolveProvider", v, encode_resolveProvider)


private[lsp] trait structures_DocumentOnTypeFormattingClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[DocumentOnTypeFormattingClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
      yield DocumentOnTypeFormattingClientCapabilities(
        dynamicRegistration,
      )
  given toJson: Encoder[DocumentOnTypeFormattingClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)


private[lsp] trait structures_DocumentOnTypeFormattingOptionsCodec:
  import structures.*
  given fromJson: Decoder[DocumentOnTypeFormattingOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_firstTriggerCharacter: Decoder[String] = Decoder.decodeString
    val decode_moreTriggerCharacter: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    Dec.fromJsonObject: dec =>
      for
        firstTriggerCharacter <- dec.get("firstTriggerCharacter", decode_firstTriggerCharacter)
        moreTriggerCharacter <- dec.getOpt("moreTriggerCharacter", decode_moreTriggerCharacter)
      yield DocumentOnTypeFormattingOptions(
        firstTriggerCharacter,
        moreTriggerCharacter,
      )
  given toJson: Encoder[DocumentOnTypeFormattingOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_firstTriggerCharacter: Encoder[String] = Encoder.encodeString
    val encode_moreTriggerCharacter: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    Enc.toJsonObject: (enc, a) =>
      enc.field("firstTriggerCharacter", a.firstTriggerCharacter, encode_firstTriggerCharacter)
      a.moreTriggerCharacter.foreach: v =>
        enc.field("moreTriggerCharacter", v, encode_moreTriggerCharacter)


private[lsp] trait structures_DocumentOnTypeFormattingParamsCodec:
  import structures.*
  given fromJson: Decoder[DocumentOnTypeFormattingParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_position: Decoder[structures.Position] = structures.Position.fromJson
    val decode_ch: Decoder[String] = Decoder.decodeString
    val decode_options: Decoder[structures.FormattingOptions] = structures.FormattingOptions.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        position <- dec.get("position", decode_position)
        ch <- dec.get("ch", decode_ch)
        options <- dec.get("options", decode_options)
      yield DocumentOnTypeFormattingParams(
        textDocument,
        position,
        ch,
        options,
      )
  given toJson: Encoder[DocumentOnTypeFormattingParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_position: Encoder[structures.Position] = structures.Position.toJson
    val encode_ch: Encoder[String] = Encoder.encodeString
    val encode_options: Encoder[structures.FormattingOptions] = structures.FormattingOptions.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("position", a.position, encode_position)
      enc.field("ch", a.ch, encode_ch)
      enc.field("options", a.options, encode_options)


private[lsp] trait structures_DocumentOnTypeFormattingRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[DocumentOnTypeFormattingRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_firstTriggerCharacter: Decoder[String] = Decoder.decodeString
    val decode_moreTriggerCharacter: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        firstTriggerCharacter <- dec.get("firstTriggerCharacter", decode_firstTriggerCharacter)
        moreTriggerCharacter <- dec.getOpt("moreTriggerCharacter", decode_moreTriggerCharacter)
      yield DocumentOnTypeFormattingRegistrationOptions(
        documentSelector,
        firstTriggerCharacter,
        moreTriggerCharacter,
      )
  given toJson: Encoder[DocumentOnTypeFormattingRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_firstTriggerCharacter: Encoder[String] = Encoder.encodeString
    val encode_moreTriggerCharacter: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      enc.field("firstTriggerCharacter", a.firstTriggerCharacter, encode_firstTriggerCharacter)
      a.moreTriggerCharacter.foreach: v =>
        enc.field("moreTriggerCharacter", v, encode_moreTriggerCharacter)


private[lsp] trait structures_DocumentRangeFormattingClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[DocumentRangeFormattingClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
      yield DocumentRangeFormattingClientCapabilities(
        dynamicRegistration,
      )
  given toJson: Encoder[DocumentRangeFormattingClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)


private[lsp] trait structures_DocumentRangeFormattingOptionsCodec:
  import structures.*
  given fromJson: Decoder[DocumentRangeFormattingOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield DocumentRangeFormattingOptions(
        workDoneProgress,
      )
  given toJson: Encoder[DocumentRangeFormattingOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_DocumentRangeFormattingParamsCodec:
  import structures.*
  given fromJson: Decoder[DocumentRangeFormattingParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_options: Decoder[structures.FormattingOptions] = structures.FormattingOptions.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        range <- dec.get("range", decode_range)
        options <- dec.get("options", decode_options)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
      yield DocumentRangeFormattingParams(
        textDocument,
        range,
        options,
        workDoneToken,
      )
  given toJson: Encoder[DocumentRangeFormattingParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_options: Encoder[structures.FormattingOptions] = structures.FormattingOptions.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("range", a.range, encode_range)
      enc.field("options", a.options, encode_options)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)


private[lsp] trait structures_DocumentRangeFormattingRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[DocumentRangeFormattingRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
      yield DocumentRangeFormattingRegistrationOptions(
        documentSelector,
      )
  given toJson: Encoder[DocumentRangeFormattingRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)


private[lsp] trait structures_DocumentSymbolCodec:
  import structures.*
  given fromJson: Decoder[DocumentSymbol] = 
    // cache all decoders for this type when fromJson first initialised
    Decoder.recursive: decode_DocumentSymbol => 
      val decode_name: Decoder[String] = Decoder.decodeString
      val decode_detail: Decoder[String] = Decoder.decodeString
      val decode_kind: Decoder[enumerations.SymbolKind] = enumerations.SymbolKind.fromJson
      val decode_tags: Decoder[Vector[enumerations.SymbolTag]] = Decoder.decodeVector(enumerations.SymbolTag.fromJson)
      val decode_deprecated: Decoder[Boolean] = Decoder.decodeBoolean
      val decode_range: Decoder[structures.Range] = structures.Range.fromJson
      val decode_selectionRange: Decoder[structures.Range] = structures.Range.fromJson
      val decode_children: Decoder[Vector[structures.DocumentSymbol]] = Decoder.decodeVector(decode_DocumentSymbol)
      Dec.fromJsonObject: dec =>
        for
          name <- dec.get("name", decode_name)
          detail <- dec.getOpt("detail", decode_detail)
          kind <- dec.get("kind", decode_kind)
          tags <- dec.getOpt("tags", decode_tags)
          deprecated <- dec.getOpt("deprecated", decode_deprecated)
          range <- dec.get("range", decode_range)
          selectionRange <- dec.get("selectionRange", decode_selectionRange)
          children <- dec.getOpt("children", decode_children)
        yield DocumentSymbol(
          name,
          detail,
          kind,
          tags,
          deprecated,
          range,
          selectionRange,
          children,
        )
  given toJson: Encoder[DocumentSymbol] = 
    // cache all encoders for this type when toJson first initialised
    Encoder.recursive: encode_DocumentSymbol => 
      val encode_name: Encoder[String] = Encoder.encodeString
      val encode_detail: Encoder[String] = Encoder.encodeString
      val encode_kind: Encoder[enumerations.SymbolKind] = enumerations.SymbolKind.toJson
      val encode_tags: Encoder[Vector[enumerations.SymbolTag]] = Encoder.encodeVector(enumerations.SymbolTag.toJson)
      val encode_deprecated: Encoder[Boolean] = Encoder.encodeBoolean
      val encode_range: Encoder[structures.Range] = structures.Range.toJson
      val encode_selectionRange: Encoder[structures.Range] = structures.Range.toJson
      val encode_children: Encoder[Vector[structures.DocumentSymbol]] = Encoder.encodeVector(encode_DocumentSymbol)
      Enc.toJsonObject: (enc, a) =>
        enc.field("name", a.name, encode_name)
        a.detail.foreach: v =>
          enc.field("detail", v, encode_detail)
        enc.field("kind", a.kind, encode_kind)
        a.tags.foreach: v =>
          enc.field("tags", v, encode_tags)
        a.deprecated.foreach: v =>
          enc.field("deprecated", v, encode_deprecated)
        enc.field("range", a.range, encode_range)
        enc.field("selectionRange", a.selectionRange, encode_selectionRange)
        a.children.foreach: v =>
          enc.field("children", v, encode_children)


private[lsp] trait structures_DocumentSymbolClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[DocumentSymbolClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_symbolKind: Decoder[DocumentSymbolClientCapabilities.SymbolKind] = DocumentSymbolClientCapabilities.SymbolKind.fromJson
    val decode_hierarchicalDocumentSymbolSupport: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_tagSupport: Decoder[DocumentSymbolClientCapabilities.TagSupport] = DocumentSymbolClientCapabilities.TagSupport.fromJson
    val decode_labelSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        symbolKind <- dec.getOpt("symbolKind", decode_symbolKind)
        hierarchicalDocumentSymbolSupport <- dec.getOpt("hierarchicalDocumentSymbolSupport", decode_hierarchicalDocumentSymbolSupport)
        tagSupport <- dec.getOpt("tagSupport", decode_tagSupport)
        labelSupport <- dec.getOpt("labelSupport", decode_labelSupport)
      yield DocumentSymbolClientCapabilities(
        dynamicRegistration,
        symbolKind,
        hierarchicalDocumentSymbolSupport,
        tagSupport,
        labelSupport,
      )
  given toJson: Encoder[DocumentSymbolClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_symbolKind: Encoder[DocumentSymbolClientCapabilities.SymbolKind] = DocumentSymbolClientCapabilities.SymbolKind.toJson
    val encode_hierarchicalDocumentSymbolSupport: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_tagSupport: Encoder[DocumentSymbolClientCapabilities.TagSupport] = DocumentSymbolClientCapabilities.TagSupport.toJson
    val encode_labelSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      a.symbolKind.foreach: v =>
        enc.field("symbolKind", v, encode_symbolKind)
      a.hierarchicalDocumentSymbolSupport.foreach: v =>
        enc.field("hierarchicalDocumentSymbolSupport", v, encode_hierarchicalDocumentSymbolSupport)
      a.tagSupport.foreach: v =>
        enc.field("tagSupport", v, encode_tagSupport)
      a.labelSupport.foreach: v =>
        enc.field("labelSupport", v, encode_labelSupport)


private[lsp] trait structures_DocumentSymbolClientCapabilities_SymbolKindCodec:
  import structures.DocumentSymbolClientCapabilities.*
  given fromJson: Decoder[SymbolKind] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_valueSet: Decoder[Vector[enumerations.SymbolKind]] = Decoder.decodeVector(enumerations.SymbolKind.fromJson)
    Dec.fromJsonObject: dec =>
      for
        valueSet <- dec.getOpt("valueSet", decode_valueSet)
      yield SymbolKind(
        valueSet,
      )
  given toJson: Encoder[SymbolKind] = 
    // cache all encoders for this type when toJson first initialised
    val encode_valueSet: Encoder[Vector[enumerations.SymbolKind]] = Encoder.encodeVector(enumerations.SymbolKind.toJson)
    Enc.toJsonObject: (enc, a) =>
      a.valueSet.foreach: v =>
        enc.field("valueSet", v, encode_valueSet)


private[lsp] trait structures_DocumentSymbolClientCapabilities_TagSupportCodec:
  import structures.DocumentSymbolClientCapabilities.*
  given fromJson: Decoder[TagSupport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_valueSet: Decoder[Vector[enumerations.SymbolTag]] = Decoder.decodeVector(enumerations.SymbolTag.fromJson)
    Dec.fromJsonObject: dec =>
      for
        valueSet <- dec.get("valueSet", decode_valueSet)
      yield TagSupport(
        valueSet,
      )
  given toJson: Encoder[TagSupport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_valueSet: Encoder[Vector[enumerations.SymbolTag]] = Encoder.encodeVector(enumerations.SymbolTag.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("valueSet", a.valueSet, encode_valueSet)


private[lsp] trait structures_DocumentSymbolOptionsCodec:
  import structures.*
  given fromJson: Decoder[DocumentSymbolOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_label: Decoder[String] = Decoder.decodeString
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        label <- dec.getOpt("label", decode_label)
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield DocumentSymbolOptions(
        label,
        workDoneProgress,
      )
  given toJson: Encoder[DocumentSymbolOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_label: Encoder[String] = Encoder.encodeString
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.label.foreach: v =>
        enc.field("label", v, encode_label)
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_DocumentSymbolParamsCodec:
  import structures.*
  given fromJson: Decoder[DocumentSymbolParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield DocumentSymbolParams(
        textDocument,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[DocumentSymbolParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_DocumentSymbolRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[DocumentSymbolRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_label: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        label <- dec.getOpt("label", decode_label)
      yield DocumentSymbolRegistrationOptions(
        documentSelector,
        label,
      )
  given toJson: Encoder[DocumentSymbolRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_label: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.label.foreach: v =>
        enc.field("label", v, encode_label)


private[lsp] trait structures_ExecuteCommandClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[ExecuteCommandClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
      yield ExecuteCommandClientCapabilities(
        dynamicRegistration,
      )
  given toJson: Encoder[ExecuteCommandClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)


private[lsp] trait structures_ExecuteCommandOptionsCodec:
  import structures.*
  given fromJson: Decoder[ExecuteCommandOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_commands: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        commands <- dec.get("commands", decode_commands)
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield ExecuteCommandOptions(
        commands,
        workDoneProgress,
      )
  given toJson: Encoder[ExecuteCommandOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_commands: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      enc.field("commands", a.commands, encode_commands)
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_ExecuteCommandParamsCodec:
  import structures.*
  given fromJson: Decoder[ExecuteCommandParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_command: Decoder[String] = Decoder.decodeString
    val decode_arguments: Decoder[Vector[io.circe.Json]] = Decoder.decodeVector(Decoder.decodeJson)
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        command <- dec.get("command", decode_command)
        arguments <- dec.getOpt("arguments", decode_arguments)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
      yield ExecuteCommandParams(
        command,
        arguments,
        workDoneToken,
      )
  given toJson: Encoder[ExecuteCommandParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_command: Encoder[String] = Encoder.encodeString
    val encode_arguments: Encoder[Vector[io.circe.Json]] = Encoder.encodeVector(Encoder.encodeJson)
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("command", a.command, encode_command)
      a.arguments.foreach: v =>
        enc.field("arguments", v, encode_arguments)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)


private[lsp] trait structures_ExecuteCommandRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[ExecuteCommandRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_commands: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    Dec.fromJsonObject: dec =>
      for
        commands <- dec.get("commands", decode_commands)
      yield ExecuteCommandRegistrationOptions(
        commands,
      )
  given toJson: Encoder[ExecuteCommandRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_commands: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    Enc.toJsonObject: (enc, a) =>
      enc.field("commands", a.commands, encode_commands)


private[lsp] trait structures_ExecutionSummaryCodec:
  import structures.*
  given fromJson: Decoder[ExecutionSummary] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_executionOrder: Decoder[runtime.uinteger] = uinteger.fromJson
    val decode_success: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        executionOrder <- dec.get("executionOrder", decode_executionOrder)
        success <- dec.getOpt("success", decode_success)
      yield ExecutionSummary(
        executionOrder,
        success,
      )
  given toJson: Encoder[ExecutionSummary] = 
    // cache all encoders for this type when toJson first initialised
    val encode_executionOrder: Encoder[runtime.uinteger] = uinteger.toJson
    val encode_success: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      enc.field("executionOrder", a.executionOrder, encode_executionOrder)
      a.success.foreach: v =>
        enc.field("success", v, encode_success)


private[lsp] trait structures_FileCreateCodec:
  import structures.*
  given fromJson: Decoder[FileCreate] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_uri: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        uri <- dec.get("uri", decode_uri)
      yield FileCreate(
        uri,
      )
  given toJson: Encoder[FileCreate] = 
    // cache all encoders for this type when toJson first initialised
    val encode_uri: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("uri", a.uri, encode_uri)


private[lsp] trait structures_FileDeleteCodec:
  import structures.*
  given fromJson: Decoder[FileDelete] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_uri: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        uri <- dec.get("uri", decode_uri)
      yield FileDelete(
        uri,
      )
  given toJson: Encoder[FileDelete] = 
    // cache all encoders for this type when toJson first initialised
    val encode_uri: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("uri", a.uri, encode_uri)


private[lsp] trait structures_FileEventCodec:
  import structures.*
  given fromJson: Decoder[FileEvent] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_uri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    val decode_type: Decoder[enumerations.FileChangeType] = enumerations.FileChangeType.fromJson
    Dec.fromJsonObject: dec =>
      for
        uri <- dec.get("uri", decode_uri)
        `type` <- dec.get("type", decode_type)
      yield FileEvent(
        uri,
        `type`,
      )
  given toJson: Encoder[FileEvent] = 
    // cache all encoders for this type when toJson first initialised
    val encode_uri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    val encode_type: Encoder[enumerations.FileChangeType] = enumerations.FileChangeType.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("uri", a.uri, encode_uri)
      enc.field("type", a.`type`, encode_type)


private[lsp] trait structures_FileOperationClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[FileOperationClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_didCreate: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_willCreate: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_didRename: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_willRename: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_didDelete: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_willDelete: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        didCreate <- dec.getOpt("didCreate", decode_didCreate)
        willCreate <- dec.getOpt("willCreate", decode_willCreate)
        didRename <- dec.getOpt("didRename", decode_didRename)
        willRename <- dec.getOpt("willRename", decode_willRename)
        didDelete <- dec.getOpt("didDelete", decode_didDelete)
        willDelete <- dec.getOpt("willDelete", decode_willDelete)
      yield FileOperationClientCapabilities(
        dynamicRegistration,
        didCreate,
        willCreate,
        didRename,
        willRename,
        didDelete,
        willDelete,
      )
  given toJson: Encoder[FileOperationClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_didCreate: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_willCreate: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_didRename: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_willRename: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_didDelete: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_willDelete: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      a.didCreate.foreach: v =>
        enc.field("didCreate", v, encode_didCreate)
      a.willCreate.foreach: v =>
        enc.field("willCreate", v, encode_willCreate)
      a.didRename.foreach: v =>
        enc.field("didRename", v, encode_didRename)
      a.willRename.foreach: v =>
        enc.field("willRename", v, encode_willRename)
      a.didDelete.foreach: v =>
        enc.field("didDelete", v, encode_didDelete)
      a.willDelete.foreach: v =>
        enc.field("willDelete", v, encode_willDelete)


private[lsp] trait structures_FileOperationFilterCodec:
  import structures.*
  given fromJson: Decoder[FileOperationFilter] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_scheme: Decoder[String] = Decoder.decodeString
    val decode_pattern: Decoder[structures.FileOperationPattern] = structures.FileOperationPattern.fromJson
    Dec.fromJsonObject: dec =>
      for
        scheme <- dec.getOpt("scheme", decode_scheme)
        pattern <- dec.get("pattern", decode_pattern)
      yield FileOperationFilter(
        scheme,
        pattern,
      )
  given toJson: Encoder[FileOperationFilter] = 
    // cache all encoders for this type when toJson first initialised
    val encode_scheme: Encoder[String] = Encoder.encodeString
    val encode_pattern: Encoder[structures.FileOperationPattern] = structures.FileOperationPattern.toJson
    Enc.toJsonObject: (enc, a) =>
      a.scheme.foreach: v =>
        enc.field("scheme", v, encode_scheme)
      enc.field("pattern", a.pattern, encode_pattern)


private[lsp] trait structures_FileOperationOptionsCodec:
  import structures.*
  given fromJson: Decoder[FileOperationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_didCreate: Decoder[structures.FileOperationRegistrationOptions] = structures.FileOperationRegistrationOptions.fromJson
    val decode_willCreate: Decoder[structures.FileOperationRegistrationOptions] = structures.FileOperationRegistrationOptions.fromJson
    val decode_didRename: Decoder[structures.FileOperationRegistrationOptions] = structures.FileOperationRegistrationOptions.fromJson
    val decode_willRename: Decoder[structures.FileOperationRegistrationOptions] = structures.FileOperationRegistrationOptions.fromJson
    val decode_didDelete: Decoder[structures.FileOperationRegistrationOptions] = structures.FileOperationRegistrationOptions.fromJson
    val decode_willDelete: Decoder[structures.FileOperationRegistrationOptions] = structures.FileOperationRegistrationOptions.fromJson
    Dec.fromJsonObject: dec =>
      for
        didCreate <- dec.getOpt("didCreate", decode_didCreate)
        willCreate <- dec.getOpt("willCreate", decode_willCreate)
        didRename <- dec.getOpt("didRename", decode_didRename)
        willRename <- dec.getOpt("willRename", decode_willRename)
        didDelete <- dec.getOpt("didDelete", decode_didDelete)
        willDelete <- dec.getOpt("willDelete", decode_willDelete)
      yield FileOperationOptions(
        didCreate,
        willCreate,
        didRename,
        willRename,
        didDelete,
        willDelete,
      )
  given toJson: Encoder[FileOperationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_didCreate: Encoder[structures.FileOperationRegistrationOptions] = structures.FileOperationRegistrationOptions.toJson
    val encode_willCreate: Encoder[structures.FileOperationRegistrationOptions] = structures.FileOperationRegistrationOptions.toJson
    val encode_didRename: Encoder[structures.FileOperationRegistrationOptions] = structures.FileOperationRegistrationOptions.toJson
    val encode_willRename: Encoder[structures.FileOperationRegistrationOptions] = structures.FileOperationRegistrationOptions.toJson
    val encode_didDelete: Encoder[structures.FileOperationRegistrationOptions] = structures.FileOperationRegistrationOptions.toJson
    val encode_willDelete: Encoder[structures.FileOperationRegistrationOptions] = structures.FileOperationRegistrationOptions.toJson
    Enc.toJsonObject: (enc, a) =>
      a.didCreate.foreach: v =>
        enc.field("didCreate", v, encode_didCreate)
      a.willCreate.foreach: v =>
        enc.field("willCreate", v, encode_willCreate)
      a.didRename.foreach: v =>
        enc.field("didRename", v, encode_didRename)
      a.willRename.foreach: v =>
        enc.field("willRename", v, encode_willRename)
      a.didDelete.foreach: v =>
        enc.field("didDelete", v, encode_didDelete)
      a.willDelete.foreach: v =>
        enc.field("willDelete", v, encode_willDelete)


private[lsp] trait structures_FileOperationPatternCodec:
  import structures.*
  given fromJson: Decoder[FileOperationPattern] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_glob: Decoder[String] = Decoder.decodeString
    val decode_matches: Decoder[enumerations.FileOperationPatternKind] = enumerations.FileOperationPatternKind.fromJson
    val decode_options: Decoder[structures.FileOperationPatternOptions] = structures.FileOperationPatternOptions.fromJson
    Dec.fromJsonObject: dec =>
      for
        glob <- dec.get("glob", decode_glob)
        matches <- dec.getOpt("matches", decode_matches)
        options <- dec.getOpt("options", decode_options)
      yield FileOperationPattern(
        glob,
        matches,
        options,
      )
  given toJson: Encoder[FileOperationPattern] = 
    // cache all encoders for this type when toJson first initialised
    val encode_glob: Encoder[String] = Encoder.encodeString
    val encode_matches: Encoder[enumerations.FileOperationPatternKind] = enumerations.FileOperationPatternKind.toJson
    val encode_options: Encoder[structures.FileOperationPatternOptions] = structures.FileOperationPatternOptions.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("glob", a.glob, encode_glob)
      a.matches.foreach: v =>
        enc.field("matches", v, encode_matches)
      a.options.foreach: v =>
        enc.field("options", v, encode_options)


private[lsp] trait structures_FileOperationPatternOptionsCodec:
  import structures.*
  given fromJson: Decoder[FileOperationPatternOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_ignoreCase: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        ignoreCase <- dec.getOpt("ignoreCase", decode_ignoreCase)
      yield FileOperationPatternOptions(
        ignoreCase,
      )
  given toJson: Encoder[FileOperationPatternOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_ignoreCase: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.ignoreCase.foreach: v =>
        enc.field("ignoreCase", v, encode_ignoreCase)


private[lsp] trait structures_FileOperationRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[FileOperationRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_filters: Decoder[Vector[structures.FileOperationFilter]] = Decoder.decodeVector(structures.FileOperationFilter.fromJson)
    Dec.fromJsonObject: dec =>
      for
        filters <- dec.get("filters", decode_filters)
      yield FileOperationRegistrationOptions(
        filters,
      )
  given toJson: Encoder[FileOperationRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_filters: Encoder[Vector[structures.FileOperationFilter]] = Encoder.encodeVector(structures.FileOperationFilter.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("filters", a.filters, encode_filters)


private[lsp] trait structures_FileRenameCodec:
  import structures.*
  given fromJson: Decoder[FileRename] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_oldUri: Decoder[String] = Decoder.decodeString
    val decode_newUri: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        oldUri <- dec.get("oldUri", decode_oldUri)
        newUri <- dec.get("newUri", decode_newUri)
      yield FileRename(
        oldUri,
        newUri,
      )
  given toJson: Encoder[FileRename] = 
    // cache all encoders for this type when toJson first initialised
    val encode_oldUri: Encoder[String] = Encoder.encodeString
    val encode_newUri: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("oldUri", a.oldUri, encode_oldUri)
      enc.field("newUri", a.newUri, encode_newUri)


private[lsp] trait structures_FileSystemWatcherCodec:
  import structures.*
  given fromJson: Decoder[FileSystemWatcher] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_globPattern: Decoder[aliases.GlobPattern] = aliases.GlobPattern.fromJson
    val decode_kind: Decoder[enumerations.WatchKind] = enumerations.WatchKind.fromJson
    Dec.fromJsonObject: dec =>
      for
        globPattern <- dec.get("globPattern", decode_globPattern)
        kind <- dec.getOpt("kind", decode_kind)
      yield FileSystemWatcher(
        globPattern,
        kind,
      )
  given toJson: Encoder[FileSystemWatcher] = 
    // cache all encoders for this type when toJson first initialised
    val encode_globPattern: Encoder[aliases.GlobPattern] = aliases.GlobPattern.toJson
    val encode_kind: Encoder[enumerations.WatchKind] = enumerations.WatchKind.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("globPattern", a.globPattern, encode_globPattern)
      a.kind.foreach: v =>
        enc.field("kind", v, encode_kind)


private[lsp] trait structures_FoldingRangeCodec:
  import structures.*
  given fromJson: Decoder[FoldingRange] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_startLine: Decoder[runtime.uinteger] = uinteger.fromJson
    val decode_startCharacter: Decoder[runtime.uinteger] = uinteger.fromJson
    val decode_endLine: Decoder[runtime.uinteger] = uinteger.fromJson
    val decode_endCharacter: Decoder[runtime.uinteger] = uinteger.fromJson
    val decode_kind: Decoder[enumerations.FoldingRangeKind] = enumerations.FoldingRangeKind.fromJson
    val decode_collapsedText: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        startLine <- dec.get("startLine", decode_startLine)
        startCharacter <- dec.getOpt("startCharacter", decode_startCharacter)
        endLine <- dec.get("endLine", decode_endLine)
        endCharacter <- dec.getOpt("endCharacter", decode_endCharacter)
        kind <- dec.getOpt("kind", decode_kind)
        collapsedText <- dec.getOpt("collapsedText", decode_collapsedText)
      yield FoldingRange(
        startLine,
        startCharacter,
        endLine,
        endCharacter,
        kind,
        collapsedText,
      )
  given toJson: Encoder[FoldingRange] = 
    // cache all encoders for this type when toJson first initialised
    val encode_startLine: Encoder[runtime.uinteger] = uinteger.toJson
    val encode_startCharacter: Encoder[runtime.uinteger] = uinteger.toJson
    val encode_endLine: Encoder[runtime.uinteger] = uinteger.toJson
    val encode_endCharacter: Encoder[runtime.uinteger] = uinteger.toJson
    val encode_kind: Encoder[enumerations.FoldingRangeKind] = enumerations.FoldingRangeKind.toJson
    val encode_collapsedText: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("startLine", a.startLine, encode_startLine)
      a.startCharacter.foreach: v =>
        enc.field("startCharacter", v, encode_startCharacter)
      enc.field("endLine", a.endLine, encode_endLine)
      a.endCharacter.foreach: v =>
        enc.field("endCharacter", v, encode_endCharacter)
      a.kind.foreach: v =>
        enc.field("kind", v, encode_kind)
      a.collapsedText.foreach: v =>
        enc.field("collapsedText", v, encode_collapsedText)


private[lsp] trait structures_FoldingRangeClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[FoldingRangeClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_rangeLimit: Decoder[runtime.uinteger] = uinteger.fromJson
    val decode_lineFoldingOnly: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_foldingRangeKind: Decoder[FoldingRangeClientCapabilities.FoldingRangeKind] = FoldingRangeClientCapabilities.FoldingRangeKind.fromJson
    val decode_foldingRange: Decoder[FoldingRangeClientCapabilities.FoldingRange] = FoldingRangeClientCapabilities.FoldingRange.fromJson
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        rangeLimit <- dec.getOpt("rangeLimit", decode_rangeLimit)
        lineFoldingOnly <- dec.getOpt("lineFoldingOnly", decode_lineFoldingOnly)
        foldingRangeKind <- dec.getOpt("foldingRangeKind", decode_foldingRangeKind)
        foldingRange <- dec.getOpt("foldingRange", decode_foldingRange)
      yield FoldingRangeClientCapabilities(
        dynamicRegistration,
        rangeLimit,
        lineFoldingOnly,
        foldingRangeKind,
        foldingRange,
      )
  given toJson: Encoder[FoldingRangeClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_rangeLimit: Encoder[runtime.uinteger] = uinteger.toJson
    val encode_lineFoldingOnly: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_foldingRangeKind: Encoder[FoldingRangeClientCapabilities.FoldingRangeKind] = FoldingRangeClientCapabilities.FoldingRangeKind.toJson
    val encode_foldingRange: Encoder[FoldingRangeClientCapabilities.FoldingRange] = FoldingRangeClientCapabilities.FoldingRange.toJson
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      a.rangeLimit.foreach: v =>
        enc.field("rangeLimit", v, encode_rangeLimit)
      a.lineFoldingOnly.foreach: v =>
        enc.field("lineFoldingOnly", v, encode_lineFoldingOnly)
      a.foldingRangeKind.foreach: v =>
        enc.field("foldingRangeKind", v, encode_foldingRangeKind)
      a.foldingRange.foreach: v =>
        enc.field("foldingRange", v, encode_foldingRange)


private[lsp] trait structures_FoldingRangeClientCapabilities_FoldingRangeKindCodec:
  import structures.FoldingRangeClientCapabilities.*
  given fromJson: Decoder[FoldingRangeKind] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_valueSet: Decoder[Vector[enumerations.FoldingRangeKind]] = Decoder.decodeVector(enumerations.FoldingRangeKind.fromJson)
    Dec.fromJsonObject: dec =>
      for
        valueSet <- dec.getOpt("valueSet", decode_valueSet)
      yield FoldingRangeKind(
        valueSet,
      )
  given toJson: Encoder[FoldingRangeKind] = 
    // cache all encoders for this type when toJson first initialised
    val encode_valueSet: Encoder[Vector[enumerations.FoldingRangeKind]] = Encoder.encodeVector(enumerations.FoldingRangeKind.toJson)
    Enc.toJsonObject: (enc, a) =>
      a.valueSet.foreach: v =>
        enc.field("valueSet", v, encode_valueSet)


private[lsp] trait structures_FoldingRangeClientCapabilities_FoldingRangeCodec:
  import structures.FoldingRangeClientCapabilities.*
  given fromJson: Decoder[FoldingRange] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_collapsedText: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        collapsedText <- dec.getOpt("collapsedText", decode_collapsedText)
      yield FoldingRange(
        collapsedText,
      )
  given toJson: Encoder[FoldingRange] = 
    // cache all encoders for this type when toJson first initialised
    val encode_collapsedText: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.collapsedText.foreach: v =>
        enc.field("collapsedText", v, encode_collapsedText)


private[lsp] trait structures_FoldingRangeOptionsCodec:
  import structures.*
  given fromJson: Decoder[FoldingRangeOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield FoldingRangeOptions(
        workDoneProgress,
      )
  given toJson: Encoder[FoldingRangeOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_FoldingRangeParamsCodec:
  import structures.*
  given fromJson: Decoder[FoldingRangeParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield FoldingRangeParams(
        textDocument,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[FoldingRangeParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_FoldingRangeRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[FoldingRangeRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_id: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        id <- dec.getOpt("id", decode_id)
      yield FoldingRangeRegistrationOptions(
        documentSelector,
        id,
      )
  given toJson: Encoder[FoldingRangeRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_id: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.id.foreach: v =>
        enc.field("id", v, encode_id)


private[lsp] trait structures_FormattingOptionsCodec:
  import structures.*
  given fromJson: Decoder[FormattingOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_tabSize: Decoder[runtime.uinteger] = uinteger.fromJson
    val decode_insertSpaces: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_trimTrailingWhitespace: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_insertFinalNewline: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_trimFinalNewlines: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        tabSize <- dec.get("tabSize", decode_tabSize)
        insertSpaces <- dec.get("insertSpaces", decode_insertSpaces)
        trimTrailingWhitespace <- dec.getOpt("trimTrailingWhitespace", decode_trimTrailingWhitespace)
        insertFinalNewline <- dec.getOpt("insertFinalNewline", decode_insertFinalNewline)
        trimFinalNewlines <- dec.getOpt("trimFinalNewlines", decode_trimFinalNewlines)
      yield FormattingOptions(
        tabSize,
        insertSpaces,
        trimTrailingWhitespace,
        insertFinalNewline,
        trimFinalNewlines,
      )
  given toJson: Encoder[FormattingOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_tabSize: Encoder[runtime.uinteger] = uinteger.toJson
    val encode_insertSpaces: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_trimTrailingWhitespace: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_insertFinalNewline: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_trimFinalNewlines: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      enc.field("tabSize", a.tabSize, encode_tabSize)
      enc.field("insertSpaces", a.insertSpaces, encode_insertSpaces)
      a.trimTrailingWhitespace.foreach: v =>
        enc.field("trimTrailingWhitespace", v, encode_trimTrailingWhitespace)
      a.insertFinalNewline.foreach: v =>
        enc.field("insertFinalNewline", v, encode_insertFinalNewline)
      a.trimFinalNewlines.foreach: v =>
        enc.field("trimFinalNewlines", v, encode_trimFinalNewlines)


private[lsp] trait structures_FullDocumentDiagnosticReportCodec:
  import structures.*
  given fromJson: Decoder[FullDocumentDiagnosticReport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_kind: Decoder["full"] = Decoder.decodeLiteralString["full"]
    val decode_resultId: Decoder[String] = Decoder.decodeString
    val decode_items: Decoder[Vector[structures.Diagnostic]] = Decoder.decodeVector(structures.Diagnostic.fromJson)
    Dec.fromJsonObject: dec =>
      for
        kind <- dec.get("kind", decode_kind)
        resultId <- dec.getOpt("resultId", decode_resultId)
        items <- dec.get("items", decode_items)
      yield FullDocumentDiagnosticReport(
        kind,
        resultId,
        items,
      )
  given toJson: Encoder[FullDocumentDiagnosticReport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_kind: Encoder["full"] = Encoder.encodeLiteralString["full"]
    val encode_resultId: Encoder[String] = Encoder.encodeString
    val encode_items: Encoder[Vector[structures.Diagnostic]] = Encoder.encodeVector(structures.Diagnostic.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("kind", a.kind, encode_kind)
      a.resultId.foreach: v =>
        enc.field("resultId", v, encode_resultId)
      enc.field("items", a.items, encode_items)


private[lsp] trait structures_GeneralClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[GeneralClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_staleRequestSupport: Decoder[GeneralClientCapabilities.StaleRequestSupport] = GeneralClientCapabilities.StaleRequestSupport.fromJson
    val decode_regularExpressions: Decoder[structures.RegularExpressionsClientCapabilities] = structures.RegularExpressionsClientCapabilities.fromJson
    val decode_markdown: Decoder[structures.MarkdownClientCapabilities] = structures.MarkdownClientCapabilities.fromJson
    val decode_positionEncodings: Decoder[Vector[enumerations.PositionEncodingKind]] = Decoder.decodeVector(enumerations.PositionEncodingKind.fromJson)
    Dec.fromJsonObject: dec =>
      for
        staleRequestSupport <- dec.getOpt("staleRequestSupport", decode_staleRequestSupport)
        regularExpressions <- dec.getOpt("regularExpressions", decode_regularExpressions)
        markdown <- dec.getOpt("markdown", decode_markdown)
        positionEncodings <- dec.getOpt("positionEncodings", decode_positionEncodings)
      yield GeneralClientCapabilities(
        staleRequestSupport,
        regularExpressions,
        markdown,
        positionEncodings,
      )
  given toJson: Encoder[GeneralClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_staleRequestSupport: Encoder[GeneralClientCapabilities.StaleRequestSupport] = GeneralClientCapabilities.StaleRequestSupport.toJson
    val encode_regularExpressions: Encoder[structures.RegularExpressionsClientCapabilities] = structures.RegularExpressionsClientCapabilities.toJson
    val encode_markdown: Encoder[structures.MarkdownClientCapabilities] = structures.MarkdownClientCapabilities.toJson
    val encode_positionEncodings: Encoder[Vector[enumerations.PositionEncodingKind]] = Encoder.encodeVector(enumerations.PositionEncodingKind.toJson)
    Enc.toJsonObject: (enc, a) =>
      a.staleRequestSupport.foreach: v =>
        enc.field("staleRequestSupport", v, encode_staleRequestSupport)
      a.regularExpressions.foreach: v =>
        enc.field("regularExpressions", v, encode_regularExpressions)
      a.markdown.foreach: v =>
        enc.field("markdown", v, encode_markdown)
      a.positionEncodings.foreach: v =>
        enc.field("positionEncodings", v, encode_positionEncodings)


private[lsp] trait structures_GeneralClientCapabilities_StaleRequestSupportCodec:
  import structures.GeneralClientCapabilities.*
  given fromJson: Decoder[StaleRequestSupport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_cancel: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_retryOnContentModified: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    Dec.fromJsonObject: dec =>
      for
        cancel <- dec.get("cancel", decode_cancel)
        retryOnContentModified <- dec.get("retryOnContentModified", decode_retryOnContentModified)
      yield StaleRequestSupport(
        cancel,
        retryOnContentModified,
      )
  given toJson: Encoder[StaleRequestSupport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_cancel: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_retryOnContentModified: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    Enc.toJsonObject: (enc, a) =>
      enc.field("cancel", a.cancel, encode_cancel)
      enc.field("retryOnContentModified", a.retryOnContentModified, encode_retryOnContentModified)


private[lsp] trait structures_HoverCodec:
  import structures.*
  given fromJson: Decoder[Hover] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_contents: Decoder[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])] = Dec.union3[structures.MarkupContent, aliases.MarkedString, Vector[aliases.MarkedString]](structures.MarkupContent.fromJson,aliases.MarkedString.fromJson, Decoder.decodeVector(aliases.MarkedString.fromJson))
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    Dec.fromJsonObject: dec =>
      for
        contents <- dec.get("contents", decode_contents)
        range <- dec.getOpt("range", decode_range)
      yield Hover(
        contents,
        range,
      )
  given toJson: Encoder[Hover] = 
    // cache all encoders for this type when toJson first initialised
    val encode_contents: Encoder[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])] = Enc.union3[structures.MarkupContent, aliases.MarkedString, Vector[aliases.MarkedString]](structures.MarkupContent.toJson,aliases.MarkedString.toJson, Encoder.encodeVector(aliases.MarkedString.toJson))
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("contents", a.contents, encode_contents)
      a.range.foreach: v =>
        enc.field("range", v, encode_range)


private[lsp] trait structures_HoverClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[HoverClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_contentFormat: Decoder[Vector[enumerations.MarkupKind]] = Decoder.decodeVector(enumerations.MarkupKind.fromJson)
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        contentFormat <- dec.getOpt("contentFormat", decode_contentFormat)
      yield HoverClientCapabilities(
        dynamicRegistration,
        contentFormat,
      )
  given toJson: Encoder[HoverClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_contentFormat: Encoder[Vector[enumerations.MarkupKind]] = Encoder.encodeVector(enumerations.MarkupKind.toJson)
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      a.contentFormat.foreach: v =>
        enc.field("contentFormat", v, encode_contentFormat)


private[lsp] trait structures_HoverOptionsCodec:
  import structures.*
  given fromJson: Decoder[HoverOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield HoverOptions(
        workDoneProgress,
      )
  given toJson: Encoder[HoverOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_HoverParamsCodec:
  import structures.*
  given fromJson: Decoder[HoverParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_position: Decoder[structures.Position] = structures.Position.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        position <- dec.get("position", decode_position)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
      yield HoverParams(
        textDocument,
        position,
        workDoneToken,
      )
  given toJson: Encoder[HoverParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_position: Encoder[structures.Position] = structures.Position.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("position", a.position, encode_position)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)


private[lsp] trait structures_HoverRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[HoverRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
      yield HoverRegistrationOptions(
        documentSelector,
      )
  given toJson: Encoder[HoverRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)


private[lsp] trait structures_ImplementationClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[ImplementationClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_linkSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        linkSupport <- dec.getOpt("linkSupport", decode_linkSupport)
      yield ImplementationClientCapabilities(
        dynamicRegistration,
        linkSupport,
      )
  given toJson: Encoder[ImplementationClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_linkSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      a.linkSupport.foreach: v =>
        enc.field("linkSupport", v, encode_linkSupport)


private[lsp] trait structures_ImplementationOptionsCodec:
  import structures.*
  given fromJson: Decoder[ImplementationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield ImplementationOptions(
        workDoneProgress,
      )
  given toJson: Encoder[ImplementationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_ImplementationParamsCodec:
  import structures.*
  given fromJson: Decoder[ImplementationParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_position: Decoder[structures.Position] = structures.Position.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        position <- dec.get("position", decode_position)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield ImplementationParams(
        textDocument,
        position,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[ImplementationParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_position: Encoder[structures.Position] = structures.Position.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("position", a.position, encode_position)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_ImplementationRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[ImplementationRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_id: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        id <- dec.getOpt("id", decode_id)
      yield ImplementationRegistrationOptions(
        documentSelector,
        id,
      )
  given toJson: Encoder[ImplementationRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_id: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.id.foreach: v =>
        enc.field("id", v, encode_id)


private[lsp] trait structures_InitializeErrorCodec:
  import structures.*
  given fromJson: Decoder[InitializeError] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_retry: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        retry <- dec.get("retry", decode_retry)
      yield InitializeError(
        retry,
      )
  given toJson: Encoder[InitializeError] = 
    // cache all encoders for this type when toJson first initialised
    val encode_retry: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      enc.field("retry", a.retry, encode_retry)


private[lsp] trait structures_InitializeParamsCodec:
  import structures.*
  given fromJson: Decoder[InitializeParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_processId: Decoder[Int] = Decoder.decodeInt
    val decode_clientInfo: Decoder[InitializeParams.ClientInfo] = InitializeParams.ClientInfo.fromJson
    val decode_locale: Decoder[String] = Decoder.decodeString
    val decode_rootPath: Decoder[String] = Decoder.decodeString
    val decode_rootUri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    val decode_capabilities: Decoder[structures.ClientCapabilities] = structures.ClientCapabilities.fromJson
    val decode_initializationOptions: Decoder[io.circe.Json] = Decoder.decodeJson
    val decode_trace: Decoder[enumerations.TraceValues] = enumerations.TraceValues.fromJson
    val decode_workspaceFolders: Decoder[Vector[structures.WorkspaceFolder]] = Decoder.decodeVector(structures.WorkspaceFolder.fromJson)
    Dec.fromJsonObject: dec =>
      for
        processId <- dec.getOpt("processId", decode_processId)
        clientInfo <- dec.getOpt("clientInfo", decode_clientInfo)
        locale <- dec.getOpt("locale", decode_locale)
        rootPath <- dec.getOpt("rootPath", decode_rootPath)
        rootUri <- dec.getOpt("rootUri", decode_rootUri)
        capabilities <- dec.get("capabilities", decode_capabilities)
        initializationOptions <- dec.getOpt("initializationOptions", decode_initializationOptions)
        trace <- dec.getOpt("trace", decode_trace)
        workspaceFolders <- dec.getOpt("workspaceFolders", decode_workspaceFolders)
      yield InitializeParams(
        processId,
        clientInfo,
        locale,
        rootPath,
        rootUri,
        capabilities,
        initializationOptions,
        trace,
        workspaceFolders,
      )
  given toJson: Encoder[InitializeParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_processId: Encoder[Int] = Encoder.encodeInt
    val encode_clientInfo: Encoder[InitializeParams.ClientInfo] = InitializeParams.ClientInfo.toJson
    val encode_locale: Encoder[String] = Encoder.encodeString
    val encode_rootPath: Encoder[String] = Encoder.encodeString
    val encode_rootUri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    val encode_capabilities: Encoder[structures.ClientCapabilities] = structures.ClientCapabilities.toJson
    val encode_initializationOptions: Encoder[io.circe.Json] = Encoder.encodeJson
    val encode_trace: Encoder[enumerations.TraceValues] = enumerations.TraceValues.toJson
    val encode_workspaceFolders: Encoder[Vector[structures.WorkspaceFolder]] = Encoder.encodeVector(structures.WorkspaceFolder.toJson)
    Enc.toJsonObject: (enc, a) =>
      a.processId.foreach: v =>
        enc.field("processId", v, encode_processId)
      a.clientInfo.foreach: v =>
        enc.field("clientInfo", v, encode_clientInfo)
      a.locale.foreach: v =>
        enc.field("locale", v, encode_locale)
      a.rootPath.foreach: v =>
        enc.field("rootPath", v, encode_rootPath)
      a.rootUri.foreach: v =>
        enc.field("rootUri", v, encode_rootUri)
      enc.field("capabilities", a.capabilities, encode_capabilities)
      a.initializationOptions.foreach: v =>
        enc.field("initializationOptions", v, encode_initializationOptions)
      a.trace.foreach: v =>
        enc.field("trace", v, encode_trace)
      a.workspaceFolders.foreach: v =>
        enc.field("workspaceFolders", v, encode_workspaceFolders)


private[lsp] trait structures_InitializeParams_ClientInfoCodec:
  import structures.InitializeParams.*
  given fromJson: Decoder[ClientInfo] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_name: Decoder[String] = Decoder.decodeString
    val decode_version: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        name <- dec.get("name", decode_name)
        version <- dec.getOpt("version", decode_version)
      yield ClientInfo(
        name,
        version,
      )
  given toJson: Encoder[ClientInfo] = 
    // cache all encoders for this type when toJson first initialised
    val encode_name: Encoder[String] = Encoder.encodeString
    val encode_version: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("name", a.name, encode_name)
      a.version.foreach: v =>
        enc.field("version", v, encode_version)


private[lsp] trait structures_InitializeResultCodec:
  import structures.*
  given fromJson: Decoder[InitializeResult] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_capabilities: Decoder[structures.ServerCapabilities] = structures.ServerCapabilities.fromJson
    val decode_serverInfo: Decoder[InitializeResult.ServerInfo] = InitializeResult.ServerInfo.fromJson
    Dec.fromJsonObject: dec =>
      for
        capabilities <- dec.get("capabilities", decode_capabilities)
        serverInfo <- dec.getOpt("serverInfo", decode_serverInfo)
      yield InitializeResult(
        capabilities,
        serverInfo,
      )
  given toJson: Encoder[InitializeResult] = 
    // cache all encoders for this type when toJson first initialised
    val encode_capabilities: Encoder[structures.ServerCapabilities] = structures.ServerCapabilities.toJson
    val encode_serverInfo: Encoder[InitializeResult.ServerInfo] = InitializeResult.ServerInfo.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("capabilities", a.capabilities, encode_capabilities)
      a.serverInfo.foreach: v =>
        enc.field("serverInfo", v, encode_serverInfo)


private[lsp] trait structures_InitializeResult_ServerInfoCodec:
  import structures.InitializeResult.*
  given fromJson: Decoder[ServerInfo] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_name: Decoder[String] = Decoder.decodeString
    val decode_version: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        name <- dec.get("name", decode_name)
        version <- dec.getOpt("version", decode_version)
      yield ServerInfo(
        name,
        version,
      )
  given toJson: Encoder[ServerInfo] = 
    // cache all encoders for this type when toJson first initialised
    val encode_name: Encoder[String] = Encoder.encodeString
    val encode_version: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("name", a.name, encode_name)
      a.version.foreach: v =>
        enc.field("version", v, encode_version)


private[lsp] trait structures_InitializedParamsCodec:
  import structures.*
  given fromJson: Decoder[InitializedParams] = 
    Decoder.const(InitializedParams())
  given toJson: Encoder[InitializedParams] = 
    Encoder.instance(_ => Json.obj())


private[lsp] trait structures_InlayHintCodec:
  import structures.*
  given fromJson: Decoder[InlayHint] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_position: Decoder[structures.Position] = structures.Position.fromJson
    val decode_label: Decoder[(String | Vector[structures.InlayHintLabelPart])] = Dec.union2[String, Vector[structures.InlayHintLabelPart]](Decoder.decodeString,Decoder.decodeVector(structures.InlayHintLabelPart.fromJson))
    val decode_kind: Decoder[enumerations.InlayHintKind] = enumerations.InlayHintKind.fromJson
    val decode_textEdits: Decoder[Vector[structures.TextEdit]] = Decoder.decodeVector(structures.TextEdit.fromJson)
    val decode_tooltip: Decoder[(String | structures.MarkupContent)] = Dec.union2[String, structures.MarkupContent](Decoder.decodeString,structures.MarkupContent.fromJson)
    val decode_paddingLeft: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_paddingRight: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_data: Decoder[io.circe.Json] = Decoder.decodeJson
    Dec.fromJsonObject: dec =>
      for
        position <- dec.get("position", decode_position)
        label <- dec.get("label", decode_label)
        kind <- dec.getOpt("kind", decode_kind)
        textEdits <- dec.getOpt("textEdits", decode_textEdits)
        tooltip <- dec.getOpt("tooltip", decode_tooltip)
        paddingLeft <- dec.getOpt("paddingLeft", decode_paddingLeft)
        paddingRight <- dec.getOpt("paddingRight", decode_paddingRight)
        data <- dec.getOpt("data", decode_data)
      yield InlayHint(
        position,
        label,
        kind,
        textEdits,
        tooltip,
        paddingLeft,
        paddingRight,
        data,
      )
  given toJson: Encoder[InlayHint] = 
    // cache all encoders for this type when toJson first initialised
    val encode_position: Encoder[structures.Position] = structures.Position.toJson
    val encode_label: Encoder[(String | Vector[structures.InlayHintLabelPart])] = Enc.union2[String, Vector[structures.InlayHintLabelPart]](Encoder.encodeString,Encoder.encodeVector(structures.InlayHintLabelPart.toJson))
    val encode_kind: Encoder[enumerations.InlayHintKind] = enumerations.InlayHintKind.toJson
    val encode_textEdits: Encoder[Vector[structures.TextEdit]] = Encoder.encodeVector(structures.TextEdit.toJson)
    val encode_tooltip: Encoder[(String | structures.MarkupContent)] = Enc.union2[String, structures.MarkupContent](Encoder.encodeString,structures.MarkupContent.toJson)
    val encode_paddingLeft: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_paddingRight: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_data: Encoder[io.circe.Json] = Encoder.encodeJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("position", a.position, encode_position)
      enc.field("label", a.label, encode_label)
      a.kind.foreach: v =>
        enc.field("kind", v, encode_kind)
      a.textEdits.foreach: v =>
        enc.field("textEdits", v, encode_textEdits)
      a.tooltip.foreach: v =>
        enc.field("tooltip", v, encode_tooltip)
      a.paddingLeft.foreach: v =>
        enc.field("paddingLeft", v, encode_paddingLeft)
      a.paddingRight.foreach: v =>
        enc.field("paddingRight", v, encode_paddingRight)
      a.data.foreach: v =>
        enc.field("data", v, encode_data)


private[lsp] trait structures_InlayHintClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[InlayHintClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_resolveSupport: Decoder[InlayHintClientCapabilities.ResolveSupport] = InlayHintClientCapabilities.ResolveSupport.fromJson
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        resolveSupport <- dec.getOpt("resolveSupport", decode_resolveSupport)
      yield InlayHintClientCapabilities(
        dynamicRegistration,
        resolveSupport,
      )
  given toJson: Encoder[InlayHintClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_resolveSupport: Encoder[InlayHintClientCapabilities.ResolveSupport] = InlayHintClientCapabilities.ResolveSupport.toJson
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      a.resolveSupport.foreach: v =>
        enc.field("resolveSupport", v, encode_resolveSupport)


private[lsp] trait structures_InlayHintClientCapabilities_ResolveSupportCodec:
  import structures.InlayHintClientCapabilities.*
  given fromJson: Decoder[ResolveSupport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_properties: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    Dec.fromJsonObject: dec =>
      for
        properties <- dec.get("properties", decode_properties)
      yield ResolveSupport(
        properties,
      )
  given toJson: Encoder[ResolveSupport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_properties: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    Enc.toJsonObject: (enc, a) =>
      enc.field("properties", a.properties, encode_properties)


private[lsp] trait structures_InlayHintLabelPartCodec:
  import structures.*
  given fromJson: Decoder[InlayHintLabelPart] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_value: Decoder[String] = Decoder.decodeString
    val decode_tooltip: Decoder[(String | structures.MarkupContent)] = Dec.union2[String, structures.MarkupContent](Decoder.decodeString,structures.MarkupContent.fromJson)
    val decode_location: Decoder[structures.Location] = structures.Location.fromJson
    val decode_command: Decoder[structures.Command] = structures.Command.fromJson
    Dec.fromJsonObject: dec =>
      for
        value <- dec.get("value", decode_value)
        tooltip <- dec.getOpt("tooltip", decode_tooltip)
        location <- dec.getOpt("location", decode_location)
        command <- dec.getOpt("command", decode_command)
      yield InlayHintLabelPart(
        value,
        tooltip,
        location,
        command,
      )
  given toJson: Encoder[InlayHintLabelPart] = 
    // cache all encoders for this type when toJson first initialised
    val encode_value: Encoder[String] = Encoder.encodeString
    val encode_tooltip: Encoder[(String | structures.MarkupContent)] = Enc.union2[String, structures.MarkupContent](Encoder.encodeString,structures.MarkupContent.toJson)
    val encode_location: Encoder[structures.Location] = structures.Location.toJson
    val encode_command: Encoder[structures.Command] = structures.Command.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("value", a.value, encode_value)
      a.tooltip.foreach: v =>
        enc.field("tooltip", v, encode_tooltip)
      a.location.foreach: v =>
        enc.field("location", v, encode_location)
      a.command.foreach: v =>
        enc.field("command", v, encode_command)


private[lsp] trait structures_InlayHintOptionsCodec:
  import structures.*
  given fromJson: Decoder[InlayHintOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_resolveProvider: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        resolveProvider <- dec.getOpt("resolveProvider", decode_resolveProvider)
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield InlayHintOptions(
        resolveProvider,
        workDoneProgress,
      )
  given toJson: Encoder[InlayHintOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_resolveProvider: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.resolveProvider.foreach: v =>
        enc.field("resolveProvider", v, encode_resolveProvider)
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_InlayHintParamsCodec:
  import structures.*
  given fromJson: Decoder[InlayHintParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        range <- dec.get("range", decode_range)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
      yield InlayHintParams(
        textDocument,
        range,
        workDoneToken,
      )
  given toJson: Encoder[InlayHintParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("range", a.range, encode_range)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)


private[lsp] trait structures_InlayHintRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[InlayHintRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_resolveProvider: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_id: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        resolveProvider <- dec.getOpt("resolveProvider", decode_resolveProvider)
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        id <- dec.getOpt("id", decode_id)
      yield InlayHintRegistrationOptions(
        resolveProvider,
        documentSelector,
        id,
      )
  given toJson: Encoder[InlayHintRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_resolveProvider: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_id: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.resolveProvider.foreach: v =>
        enc.field("resolveProvider", v, encode_resolveProvider)
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.id.foreach: v =>
        enc.field("id", v, encode_id)


private[lsp] trait structures_InlayHintWorkspaceClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[InlayHintWorkspaceClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_refreshSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        refreshSupport <- dec.getOpt("refreshSupport", decode_refreshSupport)
      yield InlayHintWorkspaceClientCapabilities(
        refreshSupport,
      )
  given toJson: Encoder[InlayHintWorkspaceClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_refreshSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.refreshSupport.foreach: v =>
        enc.field("refreshSupport", v, encode_refreshSupport)


private[lsp] trait structures_InlineValueClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[InlineValueClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
      yield InlineValueClientCapabilities(
        dynamicRegistration,
      )
  given toJson: Encoder[InlineValueClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)


private[lsp] trait structures_InlineValueContextCodec:
  import structures.*
  given fromJson: Decoder[InlineValueContext] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_frameId: Decoder[Int] = Decoder.decodeInt
    val decode_stoppedLocation: Decoder[structures.Range] = structures.Range.fromJson
    Dec.fromJsonObject: dec =>
      for
        frameId <- dec.get("frameId", decode_frameId)
        stoppedLocation <- dec.get("stoppedLocation", decode_stoppedLocation)
      yield InlineValueContext(
        frameId,
        stoppedLocation,
      )
  given toJson: Encoder[InlineValueContext] = 
    // cache all encoders for this type when toJson first initialised
    val encode_frameId: Encoder[Int] = Encoder.encodeInt
    val encode_stoppedLocation: Encoder[structures.Range] = structures.Range.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("frameId", a.frameId, encode_frameId)
      enc.field("stoppedLocation", a.stoppedLocation, encode_stoppedLocation)


private[lsp] trait structures_InlineValueEvaluatableExpressionCodec:
  import structures.*
  given fromJson: Decoder[InlineValueEvaluatableExpression] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_expression: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        range <- dec.get("range", decode_range)
        expression <- dec.getOpt("expression", decode_expression)
      yield InlineValueEvaluatableExpression(
        range,
        expression,
      )
  given toJson: Encoder[InlineValueEvaluatableExpression] = 
    // cache all encoders for this type when toJson first initialised
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_expression: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("range", a.range, encode_range)
      a.expression.foreach: v =>
        enc.field("expression", v, encode_expression)


private[lsp] trait structures_InlineValueOptionsCodec:
  import structures.*
  given fromJson: Decoder[InlineValueOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield InlineValueOptions(
        workDoneProgress,
      )
  given toJson: Encoder[InlineValueOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_InlineValueParamsCodec:
  import structures.*
  given fromJson: Decoder[InlineValueParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_context: Decoder[structures.InlineValueContext] = structures.InlineValueContext.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        range <- dec.get("range", decode_range)
        context <- dec.get("context", decode_context)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
      yield InlineValueParams(
        textDocument,
        range,
        context,
        workDoneToken,
      )
  given toJson: Encoder[InlineValueParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_context: Encoder[structures.InlineValueContext] = structures.InlineValueContext.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("range", a.range, encode_range)
      enc.field("context", a.context, encode_context)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)


private[lsp] trait structures_InlineValueRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[InlineValueRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_id: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        id <- dec.getOpt("id", decode_id)
      yield InlineValueRegistrationOptions(
        documentSelector,
        id,
      )
  given toJson: Encoder[InlineValueRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_id: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.id.foreach: v =>
        enc.field("id", v, encode_id)


private[lsp] trait structures_InlineValueTextCodec:
  import structures.*
  given fromJson: Decoder[InlineValueText] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_text: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        range <- dec.get("range", decode_range)
        text <- dec.get("text", decode_text)
      yield InlineValueText(
        range,
        text,
      )
  given toJson: Encoder[InlineValueText] = 
    // cache all encoders for this type when toJson first initialised
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_text: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("range", a.range, encode_range)
      enc.field("text", a.text, encode_text)


private[lsp] trait structures_InlineValueVariableLookupCodec:
  import structures.*
  given fromJson: Decoder[InlineValueVariableLookup] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_variableName: Decoder[String] = Decoder.decodeString
    val decode_caseSensitiveLookup: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        range <- dec.get("range", decode_range)
        variableName <- dec.getOpt("variableName", decode_variableName)
        caseSensitiveLookup <- dec.get("caseSensitiveLookup", decode_caseSensitiveLookup)
      yield InlineValueVariableLookup(
        range,
        variableName,
        caseSensitiveLookup,
      )
  given toJson: Encoder[InlineValueVariableLookup] = 
    // cache all encoders for this type when toJson first initialised
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_variableName: Encoder[String] = Encoder.encodeString
    val encode_caseSensitiveLookup: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      enc.field("range", a.range, encode_range)
      a.variableName.foreach: v =>
        enc.field("variableName", v, encode_variableName)
      enc.field("caseSensitiveLookup", a.caseSensitiveLookup, encode_caseSensitiveLookup)


private[lsp] trait structures_InlineValueWorkspaceClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[InlineValueWorkspaceClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_refreshSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        refreshSupport <- dec.getOpt("refreshSupport", decode_refreshSupport)
      yield InlineValueWorkspaceClientCapabilities(
        refreshSupport,
      )
  given toJson: Encoder[InlineValueWorkspaceClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_refreshSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.refreshSupport.foreach: v =>
        enc.field("refreshSupport", v, encode_refreshSupport)


private[lsp] trait structures_InsertReplaceEditCodec:
  import structures.*
  given fromJson: Decoder[InsertReplaceEdit] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_newText: Decoder[String] = Decoder.decodeString
    val decode_insert: Decoder[structures.Range] = structures.Range.fromJson
    val decode_replace: Decoder[structures.Range] = structures.Range.fromJson
    Dec.fromJsonObject: dec =>
      for
        newText <- dec.get("newText", decode_newText)
        insert <- dec.get("insert", decode_insert)
        replace <- dec.get("replace", decode_replace)
      yield InsertReplaceEdit(
        newText,
        insert,
        replace,
      )
  given toJson: Encoder[InsertReplaceEdit] = 
    // cache all encoders for this type when toJson first initialised
    val encode_newText: Encoder[String] = Encoder.encodeString
    val encode_insert: Encoder[structures.Range] = structures.Range.toJson
    val encode_replace: Encoder[structures.Range] = structures.Range.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("newText", a.newText, encode_newText)
      enc.field("insert", a.insert, encode_insert)
      enc.field("replace", a.replace, encode_replace)


private[lsp] trait structures_LinkedEditingRangeClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[LinkedEditingRangeClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
      yield LinkedEditingRangeClientCapabilities(
        dynamicRegistration,
      )
  given toJson: Encoder[LinkedEditingRangeClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)


private[lsp] trait structures_LinkedEditingRangeOptionsCodec:
  import structures.*
  given fromJson: Decoder[LinkedEditingRangeOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield LinkedEditingRangeOptions(
        workDoneProgress,
      )
  given toJson: Encoder[LinkedEditingRangeOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_LinkedEditingRangeParamsCodec:
  import structures.*
  given fromJson: Decoder[LinkedEditingRangeParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_position: Decoder[structures.Position] = structures.Position.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        position <- dec.get("position", decode_position)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
      yield LinkedEditingRangeParams(
        textDocument,
        position,
        workDoneToken,
      )
  given toJson: Encoder[LinkedEditingRangeParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_position: Encoder[structures.Position] = structures.Position.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("position", a.position, encode_position)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)


private[lsp] trait structures_LinkedEditingRangeRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[LinkedEditingRangeRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_id: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        id <- dec.getOpt("id", decode_id)
      yield LinkedEditingRangeRegistrationOptions(
        documentSelector,
        id,
      )
  given toJson: Encoder[LinkedEditingRangeRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_id: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.id.foreach: v =>
        enc.field("id", v, encode_id)


private[lsp] trait structures_LinkedEditingRangesCodec:
  import structures.*
  given fromJson: Decoder[LinkedEditingRanges] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_ranges: Decoder[Vector[structures.Range]] = Decoder.decodeVector(structures.Range.fromJson)
    val decode_wordPattern: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        ranges <- dec.get("ranges", decode_ranges)
        wordPattern <- dec.getOpt("wordPattern", decode_wordPattern)
      yield LinkedEditingRanges(
        ranges,
        wordPattern,
      )
  given toJson: Encoder[LinkedEditingRanges] = 
    // cache all encoders for this type when toJson first initialised
    val encode_ranges: Encoder[Vector[structures.Range]] = Encoder.encodeVector(structures.Range.toJson)
    val encode_wordPattern: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("ranges", a.ranges, encode_ranges)
      a.wordPattern.foreach: v =>
        enc.field("wordPattern", v, encode_wordPattern)


private[lsp] trait structures_LocationCodec:
  import structures.*
  given fromJson: Decoder[Location] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_uri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    Dec.fromJsonObject: dec =>
      for
        uri <- dec.get("uri", decode_uri)
        range <- dec.get("range", decode_range)
      yield Location(
        uri,
        range,
      )
  given toJson: Encoder[Location] = 
    // cache all encoders for this type when toJson first initialised
    val encode_uri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("uri", a.uri, encode_uri)
      enc.field("range", a.range, encode_range)


private[lsp] trait structures_LocationLinkCodec:
  import structures.*
  given fromJson: Decoder[LocationLink] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_originSelectionRange: Decoder[structures.Range] = structures.Range.fromJson
    val decode_targetUri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    val decode_targetRange: Decoder[structures.Range] = structures.Range.fromJson
    val decode_targetSelectionRange: Decoder[structures.Range] = structures.Range.fromJson
    Dec.fromJsonObject: dec =>
      for
        originSelectionRange <- dec.getOpt("originSelectionRange", decode_originSelectionRange)
        targetUri <- dec.get("targetUri", decode_targetUri)
        targetRange <- dec.get("targetRange", decode_targetRange)
        targetSelectionRange <- dec.get("targetSelectionRange", decode_targetSelectionRange)
      yield LocationLink(
        originSelectionRange,
        targetUri,
        targetRange,
        targetSelectionRange,
      )
  given toJson: Encoder[LocationLink] = 
    // cache all encoders for this type when toJson first initialised
    val encode_originSelectionRange: Encoder[structures.Range] = structures.Range.toJson
    val encode_targetUri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    val encode_targetRange: Encoder[structures.Range] = structures.Range.toJson
    val encode_targetSelectionRange: Encoder[structures.Range] = structures.Range.toJson
    Enc.toJsonObject: (enc, a) =>
      a.originSelectionRange.foreach: v =>
        enc.field("originSelectionRange", v, encode_originSelectionRange)
      enc.field("targetUri", a.targetUri, encode_targetUri)
      enc.field("targetRange", a.targetRange, encode_targetRange)
      enc.field("targetSelectionRange", a.targetSelectionRange, encode_targetSelectionRange)


private[lsp] trait structures_LogMessageParamsCodec:
  import structures.*
  given fromJson: Decoder[LogMessageParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_type: Decoder[enumerations.MessageType] = enumerations.MessageType.fromJson
    val decode_message: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        `type` <- dec.get("type", decode_type)
        message <- dec.get("message", decode_message)
      yield LogMessageParams(
        `type`,
        message,
      )
  given toJson: Encoder[LogMessageParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_type: Encoder[enumerations.MessageType] = enumerations.MessageType.toJson
    val encode_message: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("type", a.`type`, encode_type)
      enc.field("message", a.message, encode_message)


private[lsp] trait structures_LogTraceParamsCodec:
  import structures.*
  given fromJson: Decoder[LogTraceParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_message: Decoder[String] = Decoder.decodeString
    val decode_verbose: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        message <- dec.get("message", decode_message)
        verbose <- dec.getOpt("verbose", decode_verbose)
      yield LogTraceParams(
        message,
        verbose,
      )
  given toJson: Encoder[LogTraceParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_message: Encoder[String] = Encoder.encodeString
    val encode_verbose: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("message", a.message, encode_message)
      a.verbose.foreach: v =>
        enc.field("verbose", v, encode_verbose)


private[lsp] trait structures_MarkdownClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[MarkdownClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_parser: Decoder[String] = Decoder.decodeString
    val decode_version: Decoder[String] = Decoder.decodeString
    val decode_allowedTags: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    Dec.fromJsonObject: dec =>
      for
        parser <- dec.get("parser", decode_parser)
        version <- dec.getOpt("version", decode_version)
        allowedTags <- dec.getOpt("allowedTags", decode_allowedTags)
      yield MarkdownClientCapabilities(
        parser,
        version,
        allowedTags,
      )
  given toJson: Encoder[MarkdownClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_parser: Encoder[String] = Encoder.encodeString
    val encode_version: Encoder[String] = Encoder.encodeString
    val encode_allowedTags: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    Enc.toJsonObject: (enc, a) =>
      enc.field("parser", a.parser, encode_parser)
      a.version.foreach: v =>
        enc.field("version", v, encode_version)
      a.allowedTags.foreach: v =>
        enc.field("allowedTags", v, encode_allowedTags)


private[lsp] trait structures_MarkupContentCodec:
  import structures.*
  given fromJson: Decoder[MarkupContent] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_kind: Decoder[enumerations.MarkupKind] = enumerations.MarkupKind.fromJson
    val decode_value: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        kind <- dec.get("kind", decode_kind)
        value <- dec.get("value", decode_value)
      yield MarkupContent(
        kind,
        value,
      )
  given toJson: Encoder[MarkupContent] = 
    // cache all encoders for this type when toJson first initialised
    val encode_kind: Encoder[enumerations.MarkupKind] = enumerations.MarkupKind.toJson
    val encode_value: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("kind", a.kind, encode_kind)
      enc.field("value", a.value, encode_value)


private[lsp] trait structures_MessageActionItemCodec:
  import structures.*
  given fromJson: Decoder[MessageActionItem] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_title: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        title <- dec.get("title", decode_title)
      yield MessageActionItem(
        title,
      )
  given toJson: Encoder[MessageActionItem] = 
    // cache all encoders for this type when toJson first initialised
    val encode_title: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("title", a.title, encode_title)


private[lsp] trait structures_MonikerCodec:
  import structures.*
  given fromJson: Decoder[Moniker] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_scheme: Decoder[String] = Decoder.decodeString
    val decode_identifier: Decoder[String] = Decoder.decodeString
    val decode_unique: Decoder[enumerations.UniquenessLevel] = enumerations.UniquenessLevel.fromJson
    val decode_kind: Decoder[enumerations.MonikerKind] = enumerations.MonikerKind.fromJson
    Dec.fromJsonObject: dec =>
      for
        scheme <- dec.get("scheme", decode_scheme)
        identifier <- dec.get("identifier", decode_identifier)
        unique <- dec.get("unique", decode_unique)
        kind <- dec.getOpt("kind", decode_kind)
      yield Moniker(
        scheme,
        identifier,
        unique,
        kind,
      )
  given toJson: Encoder[Moniker] = 
    // cache all encoders for this type when toJson first initialised
    val encode_scheme: Encoder[String] = Encoder.encodeString
    val encode_identifier: Encoder[String] = Encoder.encodeString
    val encode_unique: Encoder[enumerations.UniquenessLevel] = enumerations.UniquenessLevel.toJson
    val encode_kind: Encoder[enumerations.MonikerKind] = enumerations.MonikerKind.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("scheme", a.scheme, encode_scheme)
      enc.field("identifier", a.identifier, encode_identifier)
      enc.field("unique", a.unique, encode_unique)
      a.kind.foreach: v =>
        enc.field("kind", v, encode_kind)


private[lsp] trait structures_MonikerClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[MonikerClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
      yield MonikerClientCapabilities(
        dynamicRegistration,
      )
  given toJson: Encoder[MonikerClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)


private[lsp] trait structures_MonikerOptionsCodec:
  import structures.*
  given fromJson: Decoder[MonikerOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield MonikerOptions(
        workDoneProgress,
      )
  given toJson: Encoder[MonikerOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_MonikerParamsCodec:
  import structures.*
  given fromJson: Decoder[MonikerParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_position: Decoder[structures.Position] = structures.Position.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        position <- dec.get("position", decode_position)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield MonikerParams(
        textDocument,
        position,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[MonikerParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_position: Encoder[structures.Position] = structures.Position.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("position", a.position, encode_position)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_MonikerRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[MonikerRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
      yield MonikerRegistrationOptions(
        documentSelector,
      )
  given toJson: Encoder[MonikerRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)


private[lsp] trait structures_NotebookCellCodec:
  import structures.*
  given fromJson: Decoder[NotebookCell] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_kind: Decoder[enumerations.NotebookCellKind] = enumerations.NotebookCellKind.fromJson
    val decode_document: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    val decode_metadata: Decoder[aliases.LSPObject] = aliases.LSPObject.fromJson
    val decode_executionSummary: Decoder[structures.ExecutionSummary] = structures.ExecutionSummary.fromJson
    Dec.fromJsonObject: dec =>
      for
        kind <- dec.get("kind", decode_kind)
        document <- dec.get("document", decode_document)
        metadata <- dec.getOpt("metadata", decode_metadata)
        executionSummary <- dec.getOpt("executionSummary", decode_executionSummary)
      yield NotebookCell(
        kind,
        document,
        metadata,
        executionSummary,
      )
  given toJson: Encoder[NotebookCell] = 
    // cache all encoders for this type when toJson first initialised
    val encode_kind: Encoder[enumerations.NotebookCellKind] = enumerations.NotebookCellKind.toJson
    val encode_document: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    val encode_metadata: Encoder[aliases.LSPObject] = aliases.LSPObject.toJson
    val encode_executionSummary: Encoder[structures.ExecutionSummary] = structures.ExecutionSummary.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("kind", a.kind, encode_kind)
      enc.field("document", a.document, encode_document)
      a.metadata.foreach: v =>
        enc.field("metadata", v, encode_metadata)
      a.executionSummary.foreach: v =>
        enc.field("executionSummary", v, encode_executionSummary)


private[lsp] trait structures_NotebookCellArrayChangeCodec:
  import structures.*
  given fromJson: Decoder[NotebookCellArrayChange] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_start: Decoder[runtime.uinteger] = uinteger.fromJson
    val decode_deleteCount: Decoder[runtime.uinteger] = uinteger.fromJson
    val decode_cells: Decoder[Vector[structures.NotebookCell]] = Decoder.decodeVector(structures.NotebookCell.fromJson)
    Dec.fromJsonObject: dec =>
      for
        start <- dec.get("start", decode_start)
        deleteCount <- dec.get("deleteCount", decode_deleteCount)
        cells <- dec.getOpt("cells", decode_cells)
      yield NotebookCellArrayChange(
        start,
        deleteCount,
        cells,
      )
  given toJson: Encoder[NotebookCellArrayChange] = 
    // cache all encoders for this type when toJson first initialised
    val encode_start: Encoder[runtime.uinteger] = uinteger.toJson
    val encode_deleteCount: Encoder[runtime.uinteger] = uinteger.toJson
    val encode_cells: Encoder[Vector[structures.NotebookCell]] = Encoder.encodeVector(structures.NotebookCell.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("start", a.start, encode_start)
      enc.field("deleteCount", a.deleteCount, encode_deleteCount)
      a.cells.foreach: v =>
        enc.field("cells", v, encode_cells)


private[lsp] trait structures_NotebookCellTextDocumentFilterCodec:
  import structures.*
  given fromJson: Decoder[NotebookCellTextDocumentFilter] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_notebook: Decoder[(String | aliases.NotebookDocumentFilter)] = Dec.union2[String, aliases.NotebookDocumentFilter](Decoder.decodeString,aliases.NotebookDocumentFilter.fromJson)
    val decode_language: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        notebook <- dec.get("notebook", decode_notebook)
        language <- dec.getOpt("language", decode_language)
      yield NotebookCellTextDocumentFilter(
        notebook,
        language,
      )
  given toJson: Encoder[NotebookCellTextDocumentFilter] = 
    // cache all encoders for this type when toJson first initialised
    val encode_notebook: Encoder[(String | aliases.NotebookDocumentFilter)] = Enc.union2[String, aliases.NotebookDocumentFilter](Encoder.encodeString,aliases.NotebookDocumentFilter.toJson)
    val encode_language: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("notebook", a.notebook, encode_notebook)
      a.language.foreach: v =>
        enc.field("language", v, encode_language)


private[lsp] trait structures_NotebookDocumentCodec:
  import structures.*
  given fromJson: Decoder[NotebookDocument] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_uri: Decoder[runtime.Uri] = Uri.fromJson
    val decode_notebookType: Decoder[String] = Decoder.decodeString
    val decode_version: Decoder[Int] = Decoder.decodeInt
    val decode_metadata: Decoder[aliases.LSPObject] = aliases.LSPObject.fromJson
    val decode_cells: Decoder[Vector[structures.NotebookCell]] = Decoder.decodeVector(structures.NotebookCell.fromJson)
    Dec.fromJsonObject: dec =>
      for
        uri <- dec.get("uri", decode_uri)
        notebookType <- dec.get("notebookType", decode_notebookType)
        version <- dec.get("version", decode_version)
        metadata <- dec.getOpt("metadata", decode_metadata)
        cells <- dec.get("cells", decode_cells)
      yield NotebookDocument(
        uri,
        notebookType,
        version,
        metadata,
        cells,
      )
  given toJson: Encoder[NotebookDocument] = 
    // cache all encoders for this type when toJson first initialised
    val encode_uri: Encoder[runtime.Uri] = Uri.toJson
    val encode_notebookType: Encoder[String] = Encoder.encodeString
    val encode_version: Encoder[Int] = Encoder.encodeInt
    val encode_metadata: Encoder[aliases.LSPObject] = aliases.LSPObject.toJson
    val encode_cells: Encoder[Vector[structures.NotebookCell]] = Encoder.encodeVector(structures.NotebookCell.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("uri", a.uri, encode_uri)
      enc.field("notebookType", a.notebookType, encode_notebookType)
      enc.field("version", a.version, encode_version)
      a.metadata.foreach: v =>
        enc.field("metadata", v, encode_metadata)
      enc.field("cells", a.cells, encode_cells)


private[lsp] trait structures_NotebookDocumentChangeEventCodec:
  import structures.*
  given fromJson: Decoder[NotebookDocumentChangeEvent] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_metadata: Decoder[aliases.LSPObject] = aliases.LSPObject.fromJson
    val decode_cells: Decoder[NotebookDocumentChangeEvent.Cells] = NotebookDocumentChangeEvent.Cells.fromJson
    Dec.fromJsonObject: dec =>
      for
        metadata <- dec.getOpt("metadata", decode_metadata)
        cells <- dec.getOpt("cells", decode_cells)
      yield NotebookDocumentChangeEvent(
        metadata,
        cells,
      )
  given toJson: Encoder[NotebookDocumentChangeEvent] = 
    // cache all encoders for this type when toJson first initialised
    val encode_metadata: Encoder[aliases.LSPObject] = aliases.LSPObject.toJson
    val encode_cells: Encoder[NotebookDocumentChangeEvent.Cells] = NotebookDocumentChangeEvent.Cells.toJson
    Enc.toJsonObject: (enc, a) =>
      a.metadata.foreach: v =>
        enc.field("metadata", v, encode_metadata)
      a.cells.foreach: v =>
        enc.field("cells", v, encode_cells)


private[lsp] trait structures_NotebookDocumentChangeEvent_CellsCodec:
  import structures.NotebookDocumentChangeEvent.*
  given fromJson: Decoder[Cells] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_structure: Decoder[Cells.Structure] = Cells.Structure.fromJson
    val decode_data: Decoder[Vector[structures.NotebookCell]] = Decoder.decodeVector(structures.NotebookCell.fromJson)
    val decode_textContent: Decoder[Vector[Cells.S0]] = Decoder.decodeVector(Cells.S0.fromJson)
    Dec.fromJsonObject: dec =>
      for
        structure <- dec.getOpt("structure", decode_structure)
        data <- dec.getOpt("data", decode_data)
        textContent <- dec.getOpt("textContent", decode_textContent)
      yield Cells(
        structure,
        data,
        textContent,
      )
  given toJson: Encoder[Cells] = 
    // cache all encoders for this type when toJson first initialised
    val encode_structure: Encoder[Cells.Structure] = Cells.Structure.toJson
    val encode_data: Encoder[Vector[structures.NotebookCell]] = Encoder.encodeVector(structures.NotebookCell.toJson)
    val encode_textContent: Encoder[Vector[Cells.S0]] = Encoder.encodeVector(Cells.S0.toJson)
    Enc.toJsonObject: (enc, a) =>
      a.structure.foreach: v =>
        enc.field("structure", v, encode_structure)
      a.data.foreach: v =>
        enc.field("data", v, encode_data)
      a.textContent.foreach: v =>
        enc.field("textContent", v, encode_textContent)


private[lsp] trait structures_NotebookDocumentChangeEvent_Cells_StructureCodec:
  import structures.NotebookDocumentChangeEvent.Cells.*
  given fromJson: Decoder[Structure] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_array: Decoder[structures.NotebookCellArrayChange] = structures.NotebookCellArrayChange.fromJson
    val decode_didOpen: Decoder[Vector[structures.TextDocumentItem]] = Decoder.decodeVector(structures.TextDocumentItem.fromJson)
    val decode_didClose: Decoder[Vector[structures.TextDocumentIdentifier]] = Decoder.decodeVector(structures.TextDocumentIdentifier.fromJson)
    Dec.fromJsonObject: dec =>
      for
        array <- dec.get("array", decode_array)
        didOpen <- dec.getOpt("didOpen", decode_didOpen)
        didClose <- dec.getOpt("didClose", decode_didClose)
      yield Structure(
        array,
        didOpen,
        didClose,
      )
  given toJson: Encoder[Structure] = 
    // cache all encoders for this type when toJson first initialised
    val encode_array: Encoder[structures.NotebookCellArrayChange] = structures.NotebookCellArrayChange.toJson
    val encode_didOpen: Encoder[Vector[structures.TextDocumentItem]] = Encoder.encodeVector(structures.TextDocumentItem.toJson)
    val encode_didClose: Encoder[Vector[structures.TextDocumentIdentifier]] = Encoder.encodeVector(structures.TextDocumentIdentifier.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("array", a.array, encode_array)
      a.didOpen.foreach: v =>
        enc.field("didOpen", v, encode_didOpen)
      a.didClose.foreach: v =>
        enc.field("didClose", v, encode_didClose)


private[lsp] trait structures_NotebookDocumentChangeEvent_Cells_S0Codec:
  import structures.NotebookDocumentChangeEvent.Cells.*
  given fromJson: Decoder[S0] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_document: Decoder[structures.VersionedTextDocumentIdentifier] = structures.VersionedTextDocumentIdentifier.fromJson
    val decode_changes: Decoder[Vector[aliases.TextDocumentContentChangeEvent]] = Decoder.decodeVector(aliases.TextDocumentContentChangeEvent.fromJson)
    Dec.fromJsonObject: dec =>
      for
        document <- dec.get("document", decode_document)
        changes <- dec.get("changes", decode_changes)
      yield S0(
        document,
        changes,
      )
  given toJson: Encoder[S0] = 
    // cache all encoders for this type when toJson first initialised
    val encode_document: Encoder[structures.VersionedTextDocumentIdentifier] = structures.VersionedTextDocumentIdentifier.toJson
    val encode_changes: Encoder[Vector[aliases.TextDocumentContentChangeEvent]] = Encoder.encodeVector(aliases.TextDocumentContentChangeEvent.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("document", a.document, encode_document)
      enc.field("changes", a.changes, encode_changes)


private[lsp] trait structures_NotebookDocumentClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[NotebookDocumentClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_synchronization: Decoder[structures.NotebookDocumentSyncClientCapabilities] = structures.NotebookDocumentSyncClientCapabilities.fromJson
    Dec.fromJsonObject: dec =>
      for
        synchronization <- dec.get("synchronization", decode_synchronization)
      yield NotebookDocumentClientCapabilities(
        synchronization,
      )
  given toJson: Encoder[NotebookDocumentClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_synchronization: Encoder[structures.NotebookDocumentSyncClientCapabilities] = structures.NotebookDocumentSyncClientCapabilities.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("synchronization", a.synchronization, encode_synchronization)


private[lsp] trait structures_NotebookDocumentIdentifierCodec:
  import structures.*
  given fromJson: Decoder[NotebookDocumentIdentifier] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_uri: Decoder[runtime.Uri] = Uri.fromJson
    Dec.fromJsonObject: dec =>
      for
        uri <- dec.get("uri", decode_uri)
      yield NotebookDocumentIdentifier(
        uri,
      )
  given toJson: Encoder[NotebookDocumentIdentifier] = 
    // cache all encoders for this type when toJson first initialised
    val encode_uri: Encoder[runtime.Uri] = Uri.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("uri", a.uri, encode_uri)


private[lsp] trait structures_NotebookDocumentSyncClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[NotebookDocumentSyncClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_executionSummarySupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        executionSummarySupport <- dec.getOpt("executionSummarySupport", decode_executionSummarySupport)
      yield NotebookDocumentSyncClientCapabilities(
        dynamicRegistration,
        executionSummarySupport,
      )
  given toJson: Encoder[NotebookDocumentSyncClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_executionSummarySupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      a.executionSummarySupport.foreach: v =>
        enc.field("executionSummarySupport", v, encode_executionSummarySupport)


private[lsp] trait structures_NotebookDocumentSyncOptionsCodec:
  import structures.*
  given fromJson: Decoder[NotebookDocumentSyncOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_notebookSelector: Decoder[Vector[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)]] = Decoder.decodeVector(Dec.union2[NotebookDocumentSyncOptions.S0, NotebookDocumentSyncOptions.S1](NotebookDocumentSyncOptions.S0.fromJson,NotebookDocumentSyncOptions.S1.fromJson))
    val decode_save: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        notebookSelector <- dec.get("notebookSelector", decode_notebookSelector)
        save <- dec.getOpt("save", decode_save)
      yield NotebookDocumentSyncOptions(
        notebookSelector,
        save,
      )
  given toJson: Encoder[NotebookDocumentSyncOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_notebookSelector: Encoder[Vector[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)]] = Encoder.encodeVector(Enc.union2[NotebookDocumentSyncOptions.S0, NotebookDocumentSyncOptions.S1](NotebookDocumentSyncOptions.S0.toJson,NotebookDocumentSyncOptions.S1.toJson))
    val encode_save: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      enc.field("notebookSelector", a.notebookSelector, encode_notebookSelector)
      a.save.foreach: v =>
        enc.field("save", v, encode_save)


private[lsp] trait structures_NotebookDocumentSyncOptions_S0Codec:
  import structures.NotebookDocumentSyncOptions.*
  given fromJson: Decoder[S0] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_notebook: Decoder[(String | aliases.NotebookDocumentFilter)] = Dec.union2[String, aliases.NotebookDocumentFilter](Decoder.decodeString,aliases.NotebookDocumentFilter.fromJson)
    val decode_cells: Decoder[Vector[S0.S0]] = Decoder.decodeVector(S0.S0.fromJson)
    Dec.fromJsonObject: dec =>
      for
        notebook <- dec.get("notebook", decode_notebook)
        cells <- dec.getOpt("cells", decode_cells)
      yield S0(
        notebook,
        cells,
      )
  given toJson: Encoder[S0] = 
    // cache all encoders for this type when toJson first initialised
    val encode_notebook: Encoder[(String | aliases.NotebookDocumentFilter)] = Enc.union2[String, aliases.NotebookDocumentFilter](Encoder.encodeString,aliases.NotebookDocumentFilter.toJson)
    val encode_cells: Encoder[Vector[S0.S0]] = Encoder.encodeVector(S0.S0.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("notebook", a.notebook, encode_notebook)
      a.cells.foreach: v =>
        enc.field("cells", v, encode_cells)


private[lsp] trait structures_NotebookDocumentSyncOptions_S0_S0Codec:
  import structures.NotebookDocumentSyncOptions.S0.*
  given fromJson: Decoder[S0] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_language: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        language <- dec.get("language", decode_language)
      yield S0(
        language,
      )
  given toJson: Encoder[S0] = 
    // cache all encoders for this type when toJson first initialised
    val encode_language: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("language", a.language, encode_language)


private[lsp] trait structures_NotebookDocumentSyncOptions_S1Codec:
  import structures.NotebookDocumentSyncOptions.*
  given fromJson: Decoder[S1] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_notebook: Decoder[(String | aliases.NotebookDocumentFilter)] = Dec.union2[String, aliases.NotebookDocumentFilter](Decoder.decodeString,aliases.NotebookDocumentFilter.fromJson)
    val decode_cells: Decoder[Vector[S1.S0]] = Decoder.decodeVector(S1.S0.fromJson)
    Dec.fromJsonObject: dec =>
      for
        notebook <- dec.getOpt("notebook", decode_notebook)
        cells <- dec.get("cells", decode_cells)
      yield S1(
        notebook,
        cells,
      )
  given toJson: Encoder[S1] = 
    // cache all encoders for this type when toJson first initialised
    val encode_notebook: Encoder[(String | aliases.NotebookDocumentFilter)] = Enc.union2[String, aliases.NotebookDocumentFilter](Encoder.encodeString,aliases.NotebookDocumentFilter.toJson)
    val encode_cells: Encoder[Vector[S1.S0]] = Encoder.encodeVector(S1.S0.toJson)
    Enc.toJsonObject: (enc, a) =>
      a.notebook.foreach: v =>
        enc.field("notebook", v, encode_notebook)
      enc.field("cells", a.cells, encode_cells)


private[lsp] trait structures_NotebookDocumentSyncOptions_S1_S0Codec:
  import structures.NotebookDocumentSyncOptions.S1.*
  given fromJson: Decoder[S0] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_language: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        language <- dec.get("language", decode_language)
      yield S0(
        language,
      )
  given toJson: Encoder[S0] = 
    // cache all encoders for this type when toJson first initialised
    val encode_language: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("language", a.language, encode_language)


private[lsp] trait structures_NotebookDocumentSyncRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[NotebookDocumentSyncRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_notebookSelector: Decoder[Vector[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)]] = Decoder.decodeVector(Dec.union2[NotebookDocumentSyncRegistrationOptions.S0, NotebookDocumentSyncRegistrationOptions.S1](NotebookDocumentSyncRegistrationOptions.S0.fromJson,NotebookDocumentSyncRegistrationOptions.S1.fromJson))
    val decode_save: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_id: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        notebookSelector <- dec.get("notebookSelector", decode_notebookSelector)
        save <- dec.getOpt("save", decode_save)
        id <- dec.getOpt("id", decode_id)
      yield NotebookDocumentSyncRegistrationOptions(
        notebookSelector,
        save,
        id,
      )
  given toJson: Encoder[NotebookDocumentSyncRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_notebookSelector: Encoder[Vector[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)]] = Encoder.encodeVector(Enc.union2[NotebookDocumentSyncRegistrationOptions.S0, NotebookDocumentSyncRegistrationOptions.S1](NotebookDocumentSyncRegistrationOptions.S0.toJson,NotebookDocumentSyncRegistrationOptions.S1.toJson))
    val encode_save: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_id: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("notebookSelector", a.notebookSelector, encode_notebookSelector)
      a.save.foreach: v =>
        enc.field("save", v, encode_save)
      a.id.foreach: v =>
        enc.field("id", v, encode_id)


private[lsp] trait structures_NotebookDocumentSyncRegistrationOptions_S0Codec:
  import structures.NotebookDocumentSyncRegistrationOptions.*
  given fromJson: Decoder[S0] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_notebook: Decoder[(String | aliases.NotebookDocumentFilter)] = Dec.union2[String, aliases.NotebookDocumentFilter](Decoder.decodeString,aliases.NotebookDocumentFilter.fromJson)
    val decode_cells: Decoder[Vector[S0.S0]] = Decoder.decodeVector(S0.S0.fromJson)
    Dec.fromJsonObject: dec =>
      for
        notebook <- dec.get("notebook", decode_notebook)
        cells <- dec.getOpt("cells", decode_cells)
      yield S0(
        notebook,
        cells,
      )
  given toJson: Encoder[S0] = 
    // cache all encoders for this type when toJson first initialised
    val encode_notebook: Encoder[(String | aliases.NotebookDocumentFilter)] = Enc.union2[String, aliases.NotebookDocumentFilter](Encoder.encodeString,aliases.NotebookDocumentFilter.toJson)
    val encode_cells: Encoder[Vector[S0.S0]] = Encoder.encodeVector(S0.S0.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("notebook", a.notebook, encode_notebook)
      a.cells.foreach: v =>
        enc.field("cells", v, encode_cells)


private[lsp] trait structures_NotebookDocumentSyncRegistrationOptions_S0_S0Codec:
  import structures.NotebookDocumentSyncRegistrationOptions.S0.*
  given fromJson: Decoder[S0] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_language: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        language <- dec.get("language", decode_language)
      yield S0(
        language,
      )
  given toJson: Encoder[S0] = 
    // cache all encoders for this type when toJson first initialised
    val encode_language: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("language", a.language, encode_language)


private[lsp] trait structures_NotebookDocumentSyncRegistrationOptions_S1Codec:
  import structures.NotebookDocumentSyncRegistrationOptions.*
  given fromJson: Decoder[S1] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_notebook: Decoder[(String | aliases.NotebookDocumentFilter)] = Dec.union2[String, aliases.NotebookDocumentFilter](Decoder.decodeString,aliases.NotebookDocumentFilter.fromJson)
    val decode_cells: Decoder[Vector[S1.S0]] = Decoder.decodeVector(S1.S0.fromJson)
    Dec.fromJsonObject: dec =>
      for
        notebook <- dec.getOpt("notebook", decode_notebook)
        cells <- dec.get("cells", decode_cells)
      yield S1(
        notebook,
        cells,
      )
  given toJson: Encoder[S1] = 
    // cache all encoders for this type when toJson first initialised
    val encode_notebook: Encoder[(String | aliases.NotebookDocumentFilter)] = Enc.union2[String, aliases.NotebookDocumentFilter](Encoder.encodeString,aliases.NotebookDocumentFilter.toJson)
    val encode_cells: Encoder[Vector[S1.S0]] = Encoder.encodeVector(S1.S0.toJson)
    Enc.toJsonObject: (enc, a) =>
      a.notebook.foreach: v =>
        enc.field("notebook", v, encode_notebook)
      enc.field("cells", a.cells, encode_cells)


private[lsp] trait structures_NotebookDocumentSyncRegistrationOptions_S1_S0Codec:
  import structures.NotebookDocumentSyncRegistrationOptions.S1.*
  given fromJson: Decoder[S0] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_language: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        language <- dec.get("language", decode_language)
      yield S0(
        language,
      )
  given toJson: Encoder[S0] = 
    // cache all encoders for this type when toJson first initialised
    val encode_language: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("language", a.language, encode_language)


private[lsp] trait structures_OptionalVersionedTextDocumentIdentifierCodec:
  import structures.*
  given fromJson: Decoder[OptionalVersionedTextDocumentIdentifier] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_version: Decoder[Int] = Decoder.decodeInt
    val decode_uri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    Dec.fromJsonObject: dec =>
      for
        version <- dec.getOpt("version", decode_version)
        uri <- dec.get("uri", decode_uri)
      yield OptionalVersionedTextDocumentIdentifier(
        version,
        uri,
      )
  given toJson: Encoder[OptionalVersionedTextDocumentIdentifier] = 
    // cache all encoders for this type when toJson first initialised
    val encode_version: Encoder[Int] = Encoder.encodeInt
    val encode_uri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    Enc.toJsonObject: (enc, a) =>
      a.version.foreach: v =>
        enc.field("version", v, encode_version)
      enc.field("uri", a.uri, encode_uri)


private[lsp] trait structures_ParameterInformationCodec:
  import structures.*
  given fromJson: Decoder[ParameterInformation] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_label: Decoder[(String | (runtime.uinteger, runtime.uinteger))] = Dec.union2[String, (runtime.uinteger, runtime.uinteger)](Decoder.decodeString,Decoder.decodeTuple2(uinteger.fromJson, uinteger.fromJson))
    val decode_documentation: Decoder[(String | structures.MarkupContent)] = Dec.union2[String, structures.MarkupContent](Decoder.decodeString,structures.MarkupContent.fromJson)
    Dec.fromJsonObject: dec =>
      for
        label <- dec.get("label", decode_label)
        documentation <- dec.getOpt("documentation", decode_documentation)
      yield ParameterInformation(
        label,
        documentation,
      )
  given toJson: Encoder[ParameterInformation] = 
    // cache all encoders for this type when toJson first initialised
    val encode_label: Encoder[(String | (runtime.uinteger, runtime.uinteger))] = Enc.union2[String, (runtime.uinteger, runtime.uinteger)](Encoder.encodeString,Encoder.encodeTuple2(uinteger.toJson, uinteger.toJson))
    val encode_documentation: Encoder[(String | structures.MarkupContent)] = Enc.union2[String, structures.MarkupContent](Encoder.encodeString,structures.MarkupContent.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("label", a.label, encode_label)
      a.documentation.foreach: v =>
        enc.field("documentation", v, encode_documentation)


private[lsp] trait structures_PartialResultParamsCodec:
  import structures.*
  given fromJson: Decoder[PartialResultParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield PartialResultParams(
        partialResultToken,
      )
  given toJson: Encoder[PartialResultParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_PositionCodec:
  import structures.*
  given fromJson: Decoder[Position] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_line: Decoder[runtime.uinteger] = uinteger.fromJson
    val decode_character: Decoder[runtime.uinteger] = uinteger.fromJson
    Dec.fromJsonObject: dec =>
      for
        line <- dec.get("line", decode_line)
        character <- dec.get("character", decode_character)
      yield Position(
        line,
        character,
      )
  given toJson: Encoder[Position] = 
    // cache all encoders for this type when toJson first initialised
    val encode_line: Encoder[runtime.uinteger] = uinteger.toJson
    val encode_character: Encoder[runtime.uinteger] = uinteger.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("line", a.line, encode_line)
      enc.field("character", a.character, encode_character)


private[lsp] trait structures_PrepareRenameParamsCodec:
  import structures.*
  given fromJson: Decoder[PrepareRenameParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_position: Decoder[structures.Position] = structures.Position.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        position <- dec.get("position", decode_position)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
      yield PrepareRenameParams(
        textDocument,
        position,
        workDoneToken,
      )
  given toJson: Encoder[PrepareRenameParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_position: Encoder[structures.Position] = structures.Position.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("position", a.position, encode_position)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)


private[lsp] trait structures_PreviousResultIdCodec:
  import structures.*
  given fromJson: Decoder[PreviousResultId] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_uri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    val decode_value: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        uri <- dec.get("uri", decode_uri)
        value <- dec.get("value", decode_value)
      yield PreviousResultId(
        uri,
        value,
      )
  given toJson: Encoder[PreviousResultId] = 
    // cache all encoders for this type when toJson first initialised
    val encode_uri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    val encode_value: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("uri", a.uri, encode_uri)
      enc.field("value", a.value, encode_value)


private[lsp] trait structures_ProgressParamsCodec:
  import structures.*
  given fromJson: Decoder[ProgressParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_token: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_value: Decoder[io.circe.Json] = Decoder.decodeJson
    Dec.fromJsonObject: dec =>
      for
        token <- dec.get("token", decode_token)
        value <- dec.get("value", decode_value)
      yield ProgressParams(
        token,
        value,
      )
  given toJson: Encoder[ProgressParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_token: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_value: Encoder[io.circe.Json] = Encoder.encodeJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("token", a.token, encode_token)
      enc.field("value", a.value, encode_value)


private[lsp] trait structures_PublishDiagnosticsClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[PublishDiagnosticsClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_relatedInformation: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_tagSupport: Decoder[PublishDiagnosticsClientCapabilities.TagSupport] = PublishDiagnosticsClientCapabilities.TagSupport.fromJson
    val decode_versionSupport: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_codeDescriptionSupport: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_dataSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        relatedInformation <- dec.getOpt("relatedInformation", decode_relatedInformation)
        tagSupport <- dec.getOpt("tagSupport", decode_tagSupport)
        versionSupport <- dec.getOpt("versionSupport", decode_versionSupport)
        codeDescriptionSupport <- dec.getOpt("codeDescriptionSupport", decode_codeDescriptionSupport)
        dataSupport <- dec.getOpt("dataSupport", decode_dataSupport)
      yield PublishDiagnosticsClientCapabilities(
        relatedInformation,
        tagSupport,
        versionSupport,
        codeDescriptionSupport,
        dataSupport,
      )
  given toJson: Encoder[PublishDiagnosticsClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_relatedInformation: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_tagSupport: Encoder[PublishDiagnosticsClientCapabilities.TagSupport] = PublishDiagnosticsClientCapabilities.TagSupport.toJson
    val encode_versionSupport: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_codeDescriptionSupport: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_dataSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.relatedInformation.foreach: v =>
        enc.field("relatedInformation", v, encode_relatedInformation)
      a.tagSupport.foreach: v =>
        enc.field("tagSupport", v, encode_tagSupport)
      a.versionSupport.foreach: v =>
        enc.field("versionSupport", v, encode_versionSupport)
      a.codeDescriptionSupport.foreach: v =>
        enc.field("codeDescriptionSupport", v, encode_codeDescriptionSupport)
      a.dataSupport.foreach: v =>
        enc.field("dataSupport", v, encode_dataSupport)


private[lsp] trait structures_PublishDiagnosticsClientCapabilities_TagSupportCodec:
  import structures.PublishDiagnosticsClientCapabilities.*
  given fromJson: Decoder[TagSupport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_valueSet: Decoder[Vector[enumerations.DiagnosticTag]] = Decoder.decodeVector(enumerations.DiagnosticTag.fromJson)
    Dec.fromJsonObject: dec =>
      for
        valueSet <- dec.get("valueSet", decode_valueSet)
      yield TagSupport(
        valueSet,
      )
  given toJson: Encoder[TagSupport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_valueSet: Encoder[Vector[enumerations.DiagnosticTag]] = Encoder.encodeVector(enumerations.DiagnosticTag.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("valueSet", a.valueSet, encode_valueSet)


private[lsp] trait structures_PublishDiagnosticsParamsCodec:
  import structures.*
  given fromJson: Decoder[PublishDiagnosticsParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_uri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    val decode_version: Decoder[Int] = Decoder.decodeInt
    val decode_diagnostics: Decoder[Vector[structures.Diagnostic]] = Decoder.decodeVector(structures.Diagnostic.fromJson)
    Dec.fromJsonObject: dec =>
      for
        uri <- dec.get("uri", decode_uri)
        version <- dec.getOpt("version", decode_version)
        diagnostics <- dec.get("diagnostics", decode_diagnostics)
      yield PublishDiagnosticsParams(
        uri,
        version,
        diagnostics,
      )
  given toJson: Encoder[PublishDiagnosticsParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_uri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    val encode_version: Encoder[Int] = Encoder.encodeInt
    val encode_diagnostics: Encoder[Vector[structures.Diagnostic]] = Encoder.encodeVector(structures.Diagnostic.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("uri", a.uri, encode_uri)
      a.version.foreach: v =>
        enc.field("version", v, encode_version)
      enc.field("diagnostics", a.diagnostics, encode_diagnostics)


private[lsp] trait structures_RangeCodec:
  import structures.*
  given fromJson: Decoder[Range] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_start: Decoder[structures.Position] = structures.Position.fromJson
    val decode_end: Decoder[structures.Position] = structures.Position.fromJson
    Dec.fromJsonObject: dec =>
      for
        start <- dec.get("start", decode_start)
        `end` <- dec.get("end", decode_end)
      yield Range(
        start,
        `end`,
      )
  given toJson: Encoder[Range] = 
    // cache all encoders for this type when toJson first initialised
    val encode_start: Encoder[structures.Position] = structures.Position.toJson
    val encode_end: Encoder[structures.Position] = structures.Position.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("start", a.start, encode_start)
      enc.field("end", a.`end`, encode_end)


private[lsp] trait structures_ReferenceClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[ReferenceClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
      yield ReferenceClientCapabilities(
        dynamicRegistration,
      )
  given toJson: Encoder[ReferenceClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)


private[lsp] trait structures_ReferenceContextCodec:
  import structures.*
  given fromJson: Decoder[ReferenceContext] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_includeDeclaration: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        includeDeclaration <- dec.get("includeDeclaration", decode_includeDeclaration)
      yield ReferenceContext(
        includeDeclaration,
      )
  given toJson: Encoder[ReferenceContext] = 
    // cache all encoders for this type when toJson first initialised
    val encode_includeDeclaration: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      enc.field("includeDeclaration", a.includeDeclaration, encode_includeDeclaration)


private[lsp] trait structures_ReferenceOptionsCodec:
  import structures.*
  given fromJson: Decoder[ReferenceOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield ReferenceOptions(
        workDoneProgress,
      )
  given toJson: Encoder[ReferenceOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_ReferenceParamsCodec:
  import structures.*
  given fromJson: Decoder[ReferenceParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_context: Decoder[structures.ReferenceContext] = structures.ReferenceContext.fromJson
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_position: Decoder[structures.Position] = structures.Position.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        context <- dec.get("context", decode_context)
        textDocument <- dec.get("textDocument", decode_textDocument)
        position <- dec.get("position", decode_position)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield ReferenceParams(
        context,
        textDocument,
        position,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[ReferenceParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_context: Encoder[structures.ReferenceContext] = structures.ReferenceContext.toJson
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_position: Encoder[structures.Position] = structures.Position.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("context", a.context, encode_context)
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("position", a.position, encode_position)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_ReferenceRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[ReferenceRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
      yield ReferenceRegistrationOptions(
        documentSelector,
      )
  given toJson: Encoder[ReferenceRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)


private[lsp] trait structures_RegistrationCodec:
  import structures.*
  given fromJson: Decoder[Registration] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_id: Decoder[String] = Decoder.decodeString
    val decode_method: Decoder[String] = Decoder.decodeString
    val decode_registerOptions: Decoder[io.circe.Json] = Decoder.decodeJson
    Dec.fromJsonObject: dec =>
      for
        id <- dec.get("id", decode_id)
        method <- dec.get("method", decode_method)
        registerOptions <- dec.getOpt("registerOptions", decode_registerOptions)
      yield Registration(
        id,
        method,
        registerOptions,
      )
  given toJson: Encoder[Registration] = 
    // cache all encoders for this type when toJson first initialised
    val encode_id: Encoder[String] = Encoder.encodeString
    val encode_method: Encoder[String] = Encoder.encodeString
    val encode_registerOptions: Encoder[io.circe.Json] = Encoder.encodeJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("id", a.id, encode_id)
      enc.field("method", a.method, encode_method)
      a.registerOptions.foreach: v =>
        enc.field("registerOptions", v, encode_registerOptions)


private[lsp] trait structures_RegistrationParamsCodec:
  import structures.*
  given fromJson: Decoder[RegistrationParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_registrations: Decoder[Vector[structures.Registration]] = Decoder.decodeVector(structures.Registration.fromJson)
    Dec.fromJsonObject: dec =>
      for
        registrations <- dec.get("registrations", decode_registrations)
      yield RegistrationParams(
        registrations,
      )
  given toJson: Encoder[RegistrationParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_registrations: Encoder[Vector[structures.Registration]] = Encoder.encodeVector(structures.Registration.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("registrations", a.registrations, encode_registrations)


private[lsp] trait structures_RegularExpressionsClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[RegularExpressionsClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_engine: Decoder[String] = Decoder.decodeString
    val decode_version: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        engine <- dec.get("engine", decode_engine)
        version <- dec.getOpt("version", decode_version)
      yield RegularExpressionsClientCapabilities(
        engine,
        version,
      )
  given toJson: Encoder[RegularExpressionsClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_engine: Encoder[String] = Encoder.encodeString
    val encode_version: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("engine", a.engine, encode_engine)
      a.version.foreach: v =>
        enc.field("version", v, encode_version)


private[lsp] trait structures_RelatedFullDocumentDiagnosticReportCodec:
  import structures.*
  given fromJson: Decoder[RelatedFullDocumentDiagnosticReport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_relatedDocuments: Decoder[Map[runtime.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)]] = Decoder.decodeMap(KeyDecoder.decodeKeyString.map(runtime.DocumentUri.apply), Dec.union2[structures.FullDocumentDiagnosticReport, structures.UnchangedDocumentDiagnosticReport](structures.FullDocumentDiagnosticReport.fromJson,structures.UnchangedDocumentDiagnosticReport.fromJson))
    val decode_kind: Decoder["full"] = Decoder.decodeLiteralString["full"]
    val decode_resultId: Decoder[String] = Decoder.decodeString
    val decode_items: Decoder[Vector[structures.Diagnostic]] = Decoder.decodeVector(structures.Diagnostic.fromJson)
    Dec.fromJsonObject: dec =>
      for
        relatedDocuments <- dec.getOpt("relatedDocuments", decode_relatedDocuments)
        kind <- dec.get("kind", decode_kind)
        resultId <- dec.getOpt("resultId", decode_resultId)
        items <- dec.get("items", decode_items)
      yield RelatedFullDocumentDiagnosticReport(
        relatedDocuments,
        kind,
        resultId,
        items,
      )
  given toJson: Encoder[RelatedFullDocumentDiagnosticReport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_relatedDocuments: Encoder[Map[runtime.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)]] = Encoder.encodeMap(KeyEncoder.encodeKeyString.contramap(_.value), Enc.union2[structures.FullDocumentDiagnosticReport, structures.UnchangedDocumentDiagnosticReport](structures.FullDocumentDiagnosticReport.toJson,structures.UnchangedDocumentDiagnosticReport.toJson))
    val encode_kind: Encoder["full"] = Encoder.encodeLiteralString["full"]
    val encode_resultId: Encoder[String] = Encoder.encodeString
    val encode_items: Encoder[Vector[structures.Diagnostic]] = Encoder.encodeVector(structures.Diagnostic.toJson)
    Enc.toJsonObject: (enc, a) =>
      a.relatedDocuments.foreach: v =>
        enc.field("relatedDocuments", v, encode_relatedDocuments)
      enc.field("kind", a.kind, encode_kind)
      a.resultId.foreach: v =>
        enc.field("resultId", v, encode_resultId)
      enc.field("items", a.items, encode_items)


private[lsp] trait structures_RelatedUnchangedDocumentDiagnosticReportCodec:
  import structures.*
  given fromJson: Decoder[RelatedUnchangedDocumentDiagnosticReport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_relatedDocuments: Decoder[Map[runtime.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)]] = Decoder.decodeMap(KeyDecoder.decodeKeyString.map(runtime.DocumentUri.apply), Dec.union2[structures.FullDocumentDiagnosticReport, structures.UnchangedDocumentDiagnosticReport](structures.FullDocumentDiagnosticReport.fromJson,structures.UnchangedDocumentDiagnosticReport.fromJson))
    val decode_kind: Decoder["unchanged"] = Decoder.decodeLiteralString["unchanged"]
    val decode_resultId: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        relatedDocuments <- dec.getOpt("relatedDocuments", decode_relatedDocuments)
        kind <- dec.get("kind", decode_kind)
        resultId <- dec.get("resultId", decode_resultId)
      yield RelatedUnchangedDocumentDiagnosticReport(
        relatedDocuments,
        kind,
        resultId,
      )
  given toJson: Encoder[RelatedUnchangedDocumentDiagnosticReport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_relatedDocuments: Encoder[Map[runtime.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)]] = Encoder.encodeMap(KeyEncoder.encodeKeyString.contramap(_.value), Enc.union2[structures.FullDocumentDiagnosticReport, structures.UnchangedDocumentDiagnosticReport](structures.FullDocumentDiagnosticReport.toJson,structures.UnchangedDocumentDiagnosticReport.toJson))
    val encode_kind: Encoder["unchanged"] = Encoder.encodeLiteralString["unchanged"]
    val encode_resultId: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.relatedDocuments.foreach: v =>
        enc.field("relatedDocuments", v, encode_relatedDocuments)
      enc.field("kind", a.kind, encode_kind)
      enc.field("resultId", a.resultId, encode_resultId)


private[lsp] trait structures_RelativePatternCodec:
  import structures.*
  given fromJson: Decoder[RelativePattern] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_baseUri: Decoder[(structures.WorkspaceFolder | runtime.Uri)] = Dec.union2[structures.WorkspaceFolder, runtime.Uri](structures.WorkspaceFolder.fromJson,Uri.fromJson)
    val decode_pattern: Decoder[aliases.Pattern] = aliases.Pattern.fromJson
    Dec.fromJsonObject: dec =>
      for
        baseUri <- dec.get("baseUri", decode_baseUri)
        pattern <- dec.get("pattern", decode_pattern)
      yield RelativePattern(
        baseUri,
        pattern,
      )
  given toJson: Encoder[RelativePattern] = 
    // cache all encoders for this type when toJson first initialised
    val encode_baseUri: Encoder[(structures.WorkspaceFolder | runtime.Uri)] = Enc.union2[structures.WorkspaceFolder, runtime.Uri](structures.WorkspaceFolder.toJson,Uri.toJson)
    val encode_pattern: Encoder[aliases.Pattern] = aliases.Pattern.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("baseUri", a.baseUri, encode_baseUri)
      enc.field("pattern", a.pattern, encode_pattern)


private[lsp] trait structures_RenameClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[RenameClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_prepareSupport: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_prepareSupportDefaultBehavior: Decoder[enumerations.PrepareSupportDefaultBehavior] = enumerations.PrepareSupportDefaultBehavior.fromJson
    val decode_honorsChangeAnnotations: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        prepareSupport <- dec.getOpt("prepareSupport", decode_prepareSupport)
        prepareSupportDefaultBehavior <- dec.getOpt("prepareSupportDefaultBehavior", decode_prepareSupportDefaultBehavior)
        honorsChangeAnnotations <- dec.getOpt("honorsChangeAnnotations", decode_honorsChangeAnnotations)
      yield RenameClientCapabilities(
        dynamicRegistration,
        prepareSupport,
        prepareSupportDefaultBehavior,
        honorsChangeAnnotations,
      )
  given toJson: Encoder[RenameClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_prepareSupport: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_prepareSupportDefaultBehavior: Encoder[enumerations.PrepareSupportDefaultBehavior] = enumerations.PrepareSupportDefaultBehavior.toJson
    val encode_honorsChangeAnnotations: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      a.prepareSupport.foreach: v =>
        enc.field("prepareSupport", v, encode_prepareSupport)
      a.prepareSupportDefaultBehavior.foreach: v =>
        enc.field("prepareSupportDefaultBehavior", v, encode_prepareSupportDefaultBehavior)
      a.honorsChangeAnnotations.foreach: v =>
        enc.field("honorsChangeAnnotations", v, encode_honorsChangeAnnotations)


private[lsp] trait structures_RenameFileCodec:
  import structures.*
  given fromJson: Decoder[RenameFile] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_kind: Decoder["rename"] = Decoder.decodeLiteralString["rename"]
    val decode_oldUri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    val decode_newUri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    val decode_options: Decoder[structures.RenameFileOptions] = structures.RenameFileOptions.fromJson
    val decode_annotationId: Decoder[aliases.ChangeAnnotationIdentifier] = aliases.ChangeAnnotationIdentifier.fromJson
    Dec.fromJsonObject: dec =>
      for
        kind <- dec.get("kind", decode_kind)
        oldUri <- dec.get("oldUri", decode_oldUri)
        newUri <- dec.get("newUri", decode_newUri)
        options <- dec.getOpt("options", decode_options)
        annotationId <- dec.getOpt("annotationId", decode_annotationId)
      yield RenameFile(
        kind,
        oldUri,
        newUri,
        options,
        annotationId,
      )
  given toJson: Encoder[RenameFile] = 
    // cache all encoders for this type when toJson first initialised
    val encode_kind: Encoder["rename"] = Encoder.encodeLiteralString["rename"]
    val encode_oldUri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    val encode_newUri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    val encode_options: Encoder[structures.RenameFileOptions] = structures.RenameFileOptions.toJson
    val encode_annotationId: Encoder[aliases.ChangeAnnotationIdentifier] = aliases.ChangeAnnotationIdentifier.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("kind", a.kind, encode_kind)
      enc.field("oldUri", a.oldUri, encode_oldUri)
      enc.field("newUri", a.newUri, encode_newUri)
      a.options.foreach: v =>
        enc.field("options", v, encode_options)
      a.annotationId.foreach: v =>
        enc.field("annotationId", v, encode_annotationId)


private[lsp] trait structures_RenameFileOptionsCodec:
  import structures.*
  given fromJson: Decoder[RenameFileOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_overwrite: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_ignoreIfExists: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        overwrite <- dec.getOpt("overwrite", decode_overwrite)
        ignoreIfExists <- dec.getOpt("ignoreIfExists", decode_ignoreIfExists)
      yield RenameFileOptions(
        overwrite,
        ignoreIfExists,
      )
  given toJson: Encoder[RenameFileOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_overwrite: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_ignoreIfExists: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.overwrite.foreach: v =>
        enc.field("overwrite", v, encode_overwrite)
      a.ignoreIfExists.foreach: v =>
        enc.field("ignoreIfExists", v, encode_ignoreIfExists)


private[lsp] trait structures_RenameFilesParamsCodec:
  import structures.*
  given fromJson: Decoder[RenameFilesParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_files: Decoder[Vector[structures.FileRename]] = Decoder.decodeVector(structures.FileRename.fromJson)
    Dec.fromJsonObject: dec =>
      for
        files <- dec.get("files", decode_files)
      yield RenameFilesParams(
        files,
      )
  given toJson: Encoder[RenameFilesParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_files: Encoder[Vector[structures.FileRename]] = Encoder.encodeVector(structures.FileRename.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("files", a.files, encode_files)


private[lsp] trait structures_RenameOptionsCodec:
  import structures.*
  given fromJson: Decoder[RenameOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_prepareProvider: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        prepareProvider <- dec.getOpt("prepareProvider", decode_prepareProvider)
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield RenameOptions(
        prepareProvider,
        workDoneProgress,
      )
  given toJson: Encoder[RenameOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_prepareProvider: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.prepareProvider.foreach: v =>
        enc.field("prepareProvider", v, encode_prepareProvider)
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_RenameParamsCodec:
  import structures.*
  given fromJson: Decoder[RenameParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_position: Decoder[structures.Position] = structures.Position.fromJson
    val decode_newName: Decoder[String] = Decoder.decodeString
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        position <- dec.get("position", decode_position)
        newName <- dec.get("newName", decode_newName)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
      yield RenameParams(
        textDocument,
        position,
        newName,
        workDoneToken,
      )
  given toJson: Encoder[RenameParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_position: Encoder[structures.Position] = structures.Position.toJson
    val encode_newName: Encoder[String] = Encoder.encodeString
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("position", a.position, encode_position)
      enc.field("newName", a.newName, encode_newName)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)


private[lsp] trait structures_RenameRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[RenameRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_prepareProvider: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        prepareProvider <- dec.getOpt("prepareProvider", decode_prepareProvider)
      yield RenameRegistrationOptions(
        documentSelector,
        prepareProvider,
      )
  given toJson: Encoder[RenameRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_prepareProvider: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.prepareProvider.foreach: v =>
        enc.field("prepareProvider", v, encode_prepareProvider)


private[lsp] trait structures_ResourceOperationCodec:
  import structures.*
  given fromJson: Decoder[ResourceOperation] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_kind: Decoder[String] = Decoder.decodeString
    val decode_annotationId: Decoder[aliases.ChangeAnnotationIdentifier] = aliases.ChangeAnnotationIdentifier.fromJson
    Dec.fromJsonObject: dec =>
      for
        kind <- dec.get("kind", decode_kind)
        annotationId <- dec.getOpt("annotationId", decode_annotationId)
      yield ResourceOperation(
        kind,
        annotationId,
      )
  given toJson: Encoder[ResourceOperation] = 
    // cache all encoders for this type when toJson first initialised
    val encode_kind: Encoder[String] = Encoder.encodeString
    val encode_annotationId: Encoder[aliases.ChangeAnnotationIdentifier] = aliases.ChangeAnnotationIdentifier.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("kind", a.kind, encode_kind)
      a.annotationId.foreach: v =>
        enc.field("annotationId", v, encode_annotationId)


private[lsp] trait structures_SaveOptionsCodec:
  import structures.*
  given fromJson: Decoder[SaveOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_includeText: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        includeText <- dec.getOpt("includeText", decode_includeText)
      yield SaveOptions(
        includeText,
      )
  given toJson: Encoder[SaveOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_includeText: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.includeText.foreach: v =>
        enc.field("includeText", v, encode_includeText)


private[lsp] trait structures_SelectionRangeCodec:
  import structures.*
  given fromJson: Decoder[SelectionRange] = 
    // cache all decoders for this type when fromJson first initialised
    Decoder.recursive: decode_SelectionRange => 
      val decode_range: Decoder[structures.Range] = structures.Range.fromJson
      val decode_parent: Decoder[structures.SelectionRange] = decode_SelectionRange
      Dec.fromJsonObject: dec =>
        for
          range <- dec.get("range", decode_range)
          parent <- dec.getOpt("parent", decode_parent)
        yield SelectionRange(
          range,
          parent,
        )
  given toJson: Encoder[SelectionRange] = 
    // cache all encoders for this type when toJson first initialised
    Encoder.recursive: encode_SelectionRange => 
      val encode_range: Encoder[structures.Range] = structures.Range.toJson
      val encode_parent: Encoder[structures.SelectionRange] = encode_SelectionRange
      Enc.toJsonObject: (enc, a) =>
        enc.field("range", a.range, encode_range)
        a.parent.foreach: v =>
          enc.field("parent", v, encode_parent)


private[lsp] trait structures_SelectionRangeClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[SelectionRangeClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
      yield SelectionRangeClientCapabilities(
        dynamicRegistration,
      )
  given toJson: Encoder[SelectionRangeClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)


private[lsp] trait structures_SelectionRangeOptionsCodec:
  import structures.*
  given fromJson: Decoder[SelectionRangeOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield SelectionRangeOptions(
        workDoneProgress,
      )
  given toJson: Encoder[SelectionRangeOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_SelectionRangeParamsCodec:
  import structures.*
  given fromJson: Decoder[SelectionRangeParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_positions: Decoder[Vector[structures.Position]] = Decoder.decodeVector(structures.Position.fromJson)
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        positions <- dec.get("positions", decode_positions)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield SelectionRangeParams(
        textDocument,
        positions,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[SelectionRangeParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_positions: Encoder[Vector[structures.Position]] = Encoder.encodeVector(structures.Position.toJson)
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("positions", a.positions, encode_positions)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_SelectionRangeRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[SelectionRangeRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_id: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        id <- dec.getOpt("id", decode_id)
      yield SelectionRangeRegistrationOptions(
        documentSelector,
        id,
      )
  given toJson: Encoder[SelectionRangeRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_id: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.id.foreach: v =>
        enc.field("id", v, encode_id)


private[lsp] trait structures_SemanticTokensCodec:
  import structures.*
  given fromJson: Decoder[SemanticTokens] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_resultId: Decoder[String] = Decoder.decodeString
    val decode_data: Decoder[Vector[runtime.uinteger]] = Decoder.decodeVector(uinteger.fromJson)
    Dec.fromJsonObject: dec =>
      for
        resultId <- dec.getOpt("resultId", decode_resultId)
        data <- dec.get("data", decode_data)
      yield SemanticTokens(
        resultId,
        data,
      )
  given toJson: Encoder[SemanticTokens] = 
    // cache all encoders for this type when toJson first initialised
    val encode_resultId: Encoder[String] = Encoder.encodeString
    val encode_data: Encoder[Vector[runtime.uinteger]] = Encoder.encodeVector(uinteger.toJson)
    Enc.toJsonObject: (enc, a) =>
      a.resultId.foreach: v =>
        enc.field("resultId", v, encode_resultId)
      enc.field("data", a.data, encode_data)


private[lsp] trait structures_SemanticTokensClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[SemanticTokensClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_requests: Decoder[SemanticTokensClientCapabilities.Requests] = SemanticTokensClientCapabilities.Requests.fromJson
    val decode_tokenTypes: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    val decode_tokenModifiers: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    val decode_formats: Decoder[Vector[enumerations.TokenFormat]] = Decoder.decodeVector(enumerations.TokenFormat.fromJson)
    val decode_overlappingTokenSupport: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_multilineTokenSupport: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_serverCancelSupport: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_augmentsSyntaxTokens: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        requests <- dec.get("requests", decode_requests)
        tokenTypes <- dec.get("tokenTypes", decode_tokenTypes)
        tokenModifiers <- dec.get("tokenModifiers", decode_tokenModifiers)
        formats <- dec.get("formats", decode_formats)
        overlappingTokenSupport <- dec.getOpt("overlappingTokenSupport", decode_overlappingTokenSupport)
        multilineTokenSupport <- dec.getOpt("multilineTokenSupport", decode_multilineTokenSupport)
        serverCancelSupport <- dec.getOpt("serverCancelSupport", decode_serverCancelSupport)
        augmentsSyntaxTokens <- dec.getOpt("augmentsSyntaxTokens", decode_augmentsSyntaxTokens)
      yield SemanticTokensClientCapabilities(
        dynamicRegistration,
        requests,
        tokenTypes,
        tokenModifiers,
        formats,
        overlappingTokenSupport,
        multilineTokenSupport,
        serverCancelSupport,
        augmentsSyntaxTokens,
      )
  given toJson: Encoder[SemanticTokensClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_requests: Encoder[SemanticTokensClientCapabilities.Requests] = SemanticTokensClientCapabilities.Requests.toJson
    val encode_tokenTypes: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    val encode_tokenModifiers: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    val encode_formats: Encoder[Vector[enumerations.TokenFormat]] = Encoder.encodeVector(enumerations.TokenFormat.toJson)
    val encode_overlappingTokenSupport: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_multilineTokenSupport: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_serverCancelSupport: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_augmentsSyntaxTokens: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      enc.field("requests", a.requests, encode_requests)
      enc.field("tokenTypes", a.tokenTypes, encode_tokenTypes)
      enc.field("tokenModifiers", a.tokenModifiers, encode_tokenModifiers)
      enc.field("formats", a.formats, encode_formats)
      a.overlappingTokenSupport.foreach: v =>
        enc.field("overlappingTokenSupport", v, encode_overlappingTokenSupport)
      a.multilineTokenSupport.foreach: v =>
        enc.field("multilineTokenSupport", v, encode_multilineTokenSupport)
      a.serverCancelSupport.foreach: v =>
        enc.field("serverCancelSupport", v, encode_serverCancelSupport)
      a.augmentsSyntaxTokens.foreach: v =>
        enc.field("augmentsSyntaxTokens", v, encode_augmentsSyntaxTokens)


private[lsp] trait structures_SemanticTokensClientCapabilities_RequestsCodec:
  import structures.SemanticTokensClientCapabilities.*
  given fromJson: Decoder[Requests] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_range: Decoder[(Boolean | Requests.S0)] = Dec.union2[Boolean, Requests.S0](Decoder.decodeBoolean,Requests.S0.fromJson)
    val decode_full: Decoder[(Boolean | Requests.S1)] = Dec.union2[Boolean, Requests.S1](Decoder.decodeBoolean,Requests.S1.fromJson)
    Dec.fromJsonObject: dec =>
      for
        range <- dec.getOpt("range", decode_range)
        full <- dec.getOpt("full", decode_full)
      yield Requests(
        range,
        full,
      )
  given toJson: Encoder[Requests] = 
    // cache all encoders for this type when toJson first initialised
    val encode_range: Encoder[(Boolean | Requests.S0)] = Enc.union2[Boolean, Requests.S0](Encoder.encodeBoolean,Requests.S0.toJson)
    val encode_full: Encoder[(Boolean | Requests.S1)] = Enc.union2[Boolean, Requests.S1](Encoder.encodeBoolean,Requests.S1.toJson)
    Enc.toJsonObject: (enc, a) =>
      a.range.foreach: v =>
        enc.field("range", v, encode_range)
      a.full.foreach: v =>
        enc.field("full", v, encode_full)


private[lsp] trait structures_SemanticTokensClientCapabilities_Requests_S0Codec:
  import structures.SemanticTokensClientCapabilities.Requests.*
  given fromJson: Decoder[S0] = 
    Decoder.const(S0())
  given toJson: Encoder[S0] = 
    Encoder.instance(_ => Json.obj())


private[lsp] trait structures_SemanticTokensClientCapabilities_Requests_S1Codec:
  import structures.SemanticTokensClientCapabilities.Requests.*
  given fromJson: Decoder[S1] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_delta: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        delta <- dec.getOpt("delta", decode_delta)
      yield S1(
        delta,
      )
  given toJson: Encoder[S1] = 
    // cache all encoders for this type when toJson first initialised
    val encode_delta: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.delta.foreach: v =>
        enc.field("delta", v, encode_delta)


private[lsp] trait structures_SemanticTokensDeltaCodec:
  import structures.*
  given fromJson: Decoder[SemanticTokensDelta] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_resultId: Decoder[String] = Decoder.decodeString
    val decode_edits: Decoder[Vector[structures.SemanticTokensEdit]] = Decoder.decodeVector(structures.SemanticTokensEdit.fromJson)
    Dec.fromJsonObject: dec =>
      for
        resultId <- dec.getOpt("resultId", decode_resultId)
        edits <- dec.get("edits", decode_edits)
      yield SemanticTokensDelta(
        resultId,
        edits,
      )
  given toJson: Encoder[SemanticTokensDelta] = 
    // cache all encoders for this type when toJson first initialised
    val encode_resultId: Encoder[String] = Encoder.encodeString
    val encode_edits: Encoder[Vector[structures.SemanticTokensEdit]] = Encoder.encodeVector(structures.SemanticTokensEdit.toJson)
    Enc.toJsonObject: (enc, a) =>
      a.resultId.foreach: v =>
        enc.field("resultId", v, encode_resultId)
      enc.field("edits", a.edits, encode_edits)


private[lsp] trait structures_SemanticTokensDeltaParamsCodec:
  import structures.*
  given fromJson: Decoder[SemanticTokensDeltaParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_previousResultId: Decoder[String] = Decoder.decodeString
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        previousResultId <- dec.get("previousResultId", decode_previousResultId)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield SemanticTokensDeltaParams(
        textDocument,
        previousResultId,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[SemanticTokensDeltaParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_previousResultId: Encoder[String] = Encoder.encodeString
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("previousResultId", a.previousResultId, encode_previousResultId)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_SemanticTokensDeltaPartialResultCodec:
  import structures.*
  given fromJson: Decoder[SemanticTokensDeltaPartialResult] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_edits: Decoder[Vector[structures.SemanticTokensEdit]] = Decoder.decodeVector(structures.SemanticTokensEdit.fromJson)
    Dec.fromJsonObject: dec =>
      for
        edits <- dec.get("edits", decode_edits)
      yield SemanticTokensDeltaPartialResult(
        edits,
      )
  given toJson: Encoder[SemanticTokensDeltaPartialResult] = 
    // cache all encoders for this type when toJson first initialised
    val encode_edits: Encoder[Vector[structures.SemanticTokensEdit]] = Encoder.encodeVector(structures.SemanticTokensEdit.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("edits", a.edits, encode_edits)


private[lsp] trait structures_SemanticTokensEditCodec:
  import structures.*
  given fromJson: Decoder[SemanticTokensEdit] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_start: Decoder[runtime.uinteger] = uinteger.fromJson
    val decode_deleteCount: Decoder[runtime.uinteger] = uinteger.fromJson
    val decode_data: Decoder[Vector[runtime.uinteger]] = Decoder.decodeVector(uinteger.fromJson)
    Dec.fromJsonObject: dec =>
      for
        start <- dec.get("start", decode_start)
        deleteCount <- dec.get("deleteCount", decode_deleteCount)
        data <- dec.getOpt("data", decode_data)
      yield SemanticTokensEdit(
        start,
        deleteCount,
        data,
      )
  given toJson: Encoder[SemanticTokensEdit] = 
    // cache all encoders for this type when toJson first initialised
    val encode_start: Encoder[runtime.uinteger] = uinteger.toJson
    val encode_deleteCount: Encoder[runtime.uinteger] = uinteger.toJson
    val encode_data: Encoder[Vector[runtime.uinteger]] = Encoder.encodeVector(uinteger.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("start", a.start, encode_start)
      enc.field("deleteCount", a.deleteCount, encode_deleteCount)
      a.data.foreach: v =>
        enc.field("data", v, encode_data)


private[lsp] trait structures_SemanticTokensLegendCodec:
  import structures.*
  given fromJson: Decoder[SemanticTokensLegend] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_tokenTypes: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    val decode_tokenModifiers: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    Dec.fromJsonObject: dec =>
      for
        tokenTypes <- dec.get("tokenTypes", decode_tokenTypes)
        tokenModifiers <- dec.get("tokenModifiers", decode_tokenModifiers)
      yield SemanticTokensLegend(
        tokenTypes,
        tokenModifiers,
      )
  given toJson: Encoder[SemanticTokensLegend] = 
    // cache all encoders for this type when toJson first initialised
    val encode_tokenTypes: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    val encode_tokenModifiers: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    Enc.toJsonObject: (enc, a) =>
      enc.field("tokenTypes", a.tokenTypes, encode_tokenTypes)
      enc.field("tokenModifiers", a.tokenModifiers, encode_tokenModifiers)


private[lsp] trait structures_SemanticTokensOptionsCodec:
  import structures.*
  given fromJson: Decoder[SemanticTokensOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_legend: Decoder[structures.SemanticTokensLegend] = structures.SemanticTokensLegend.fromJson
    val decode_range: Decoder[(Boolean | SemanticTokensOptions.S0)] = Dec.union2[Boolean, SemanticTokensOptions.S0](Decoder.decodeBoolean,SemanticTokensOptions.S0.fromJson)
    val decode_full: Decoder[(Boolean | SemanticTokensOptions.S1)] = Dec.union2[Boolean, SemanticTokensOptions.S1](Decoder.decodeBoolean,SemanticTokensOptions.S1.fromJson)
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        legend <- dec.get("legend", decode_legend)
        range <- dec.getOpt("range", decode_range)
        full <- dec.getOpt("full", decode_full)
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield SemanticTokensOptions(
        legend,
        range,
        full,
        workDoneProgress,
      )
  given toJson: Encoder[SemanticTokensOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_legend: Encoder[structures.SemanticTokensLegend] = structures.SemanticTokensLegend.toJson
    val encode_range: Encoder[(Boolean | SemanticTokensOptions.S0)] = Enc.union2[Boolean, SemanticTokensOptions.S0](Encoder.encodeBoolean,SemanticTokensOptions.S0.toJson)
    val encode_full: Encoder[(Boolean | SemanticTokensOptions.S1)] = Enc.union2[Boolean, SemanticTokensOptions.S1](Encoder.encodeBoolean,SemanticTokensOptions.S1.toJson)
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      enc.field("legend", a.legend, encode_legend)
      a.range.foreach: v =>
        enc.field("range", v, encode_range)
      a.full.foreach: v =>
        enc.field("full", v, encode_full)
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_SemanticTokensOptions_S0Codec:
  import structures.SemanticTokensOptions.*
  given fromJson: Decoder[S0] = 
    Decoder.const(S0())
  given toJson: Encoder[S0] = 
    Encoder.instance(_ => Json.obj())


private[lsp] trait structures_SemanticTokensOptions_S1Codec:
  import structures.SemanticTokensOptions.*
  given fromJson: Decoder[S1] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_delta: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        delta <- dec.getOpt("delta", decode_delta)
      yield S1(
        delta,
      )
  given toJson: Encoder[S1] = 
    // cache all encoders for this type when toJson first initialised
    val encode_delta: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.delta.foreach: v =>
        enc.field("delta", v, encode_delta)


private[lsp] trait structures_SemanticTokensParamsCodec:
  import structures.*
  given fromJson: Decoder[SemanticTokensParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield SemanticTokensParams(
        textDocument,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[SemanticTokensParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_SemanticTokensPartialResultCodec:
  import structures.*
  given fromJson: Decoder[SemanticTokensPartialResult] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_data: Decoder[Vector[runtime.uinteger]] = Decoder.decodeVector(uinteger.fromJson)
    Dec.fromJsonObject: dec =>
      for
        data <- dec.get("data", decode_data)
      yield SemanticTokensPartialResult(
        data,
      )
  given toJson: Encoder[SemanticTokensPartialResult] = 
    // cache all encoders for this type when toJson first initialised
    val encode_data: Encoder[Vector[runtime.uinteger]] = Encoder.encodeVector(uinteger.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("data", a.data, encode_data)


private[lsp] trait structures_SemanticTokensRangeParamsCodec:
  import structures.*
  given fromJson: Decoder[SemanticTokensRangeParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        range <- dec.get("range", decode_range)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield SemanticTokensRangeParams(
        textDocument,
        range,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[SemanticTokensRangeParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("range", a.range, encode_range)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_SemanticTokensRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[SemanticTokensRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_legend: Decoder[structures.SemanticTokensLegend] = structures.SemanticTokensLegend.fromJson
    val decode_range: Decoder[(Boolean | SemanticTokensRegistrationOptions.S0)] = Dec.union2[Boolean, SemanticTokensRegistrationOptions.S0](Decoder.decodeBoolean,SemanticTokensRegistrationOptions.S0.fromJson)
    val decode_full: Decoder[(Boolean | SemanticTokensRegistrationOptions.S1)] = Dec.union2[Boolean, SemanticTokensRegistrationOptions.S1](Decoder.decodeBoolean,SemanticTokensRegistrationOptions.S1.fromJson)
    val decode_id: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        legend <- dec.get("legend", decode_legend)
        range <- dec.getOpt("range", decode_range)
        full <- dec.getOpt("full", decode_full)
        id <- dec.getOpt("id", decode_id)
      yield SemanticTokensRegistrationOptions(
        documentSelector,
        legend,
        range,
        full,
        id,
      )
  given toJson: Encoder[SemanticTokensRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_legend: Encoder[structures.SemanticTokensLegend] = structures.SemanticTokensLegend.toJson
    val encode_range: Encoder[(Boolean | SemanticTokensRegistrationOptions.S0)] = Enc.union2[Boolean, SemanticTokensRegistrationOptions.S0](Encoder.encodeBoolean,SemanticTokensRegistrationOptions.S0.toJson)
    val encode_full: Encoder[(Boolean | SemanticTokensRegistrationOptions.S1)] = Enc.union2[Boolean, SemanticTokensRegistrationOptions.S1](Encoder.encodeBoolean,SemanticTokensRegistrationOptions.S1.toJson)
    val encode_id: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      enc.field("legend", a.legend, encode_legend)
      a.range.foreach: v =>
        enc.field("range", v, encode_range)
      a.full.foreach: v =>
        enc.field("full", v, encode_full)
      a.id.foreach: v =>
        enc.field("id", v, encode_id)


private[lsp] trait structures_SemanticTokensRegistrationOptions_S0Codec:
  import structures.SemanticTokensRegistrationOptions.*
  given fromJson: Decoder[S0] = 
    Decoder.const(S0())
  given toJson: Encoder[S0] = 
    Encoder.instance(_ => Json.obj())


private[lsp] trait structures_SemanticTokensRegistrationOptions_S1Codec:
  import structures.SemanticTokensRegistrationOptions.*
  given fromJson: Decoder[S1] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_delta: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        delta <- dec.getOpt("delta", decode_delta)
      yield S1(
        delta,
      )
  given toJson: Encoder[S1] = 
    // cache all encoders for this type when toJson first initialised
    val encode_delta: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.delta.foreach: v =>
        enc.field("delta", v, encode_delta)


private[lsp] trait structures_SemanticTokensWorkspaceClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[SemanticTokensWorkspaceClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_refreshSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        refreshSupport <- dec.getOpt("refreshSupport", decode_refreshSupport)
      yield SemanticTokensWorkspaceClientCapabilities(
        refreshSupport,
      )
  given toJson: Encoder[SemanticTokensWorkspaceClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_refreshSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.refreshSupport.foreach: v =>
        enc.field("refreshSupport", v, encode_refreshSupport)


private[lsp] trait structures_ServerCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[ServerCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_positionEncoding: Decoder[enumerations.PositionEncodingKind] = enumerations.PositionEncodingKind.fromJson
    val decode_textDocumentSync: Decoder[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)] = Dec.union2[structures.TextDocumentSyncOptions, enumerations.TextDocumentSyncKind](structures.TextDocumentSyncOptions.fromJson,enumerations.TextDocumentSyncKind.fromJson)
    val decode_notebookDocumentSync: Decoder[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)] = Dec.union2[structures.NotebookDocumentSyncOptions, structures.NotebookDocumentSyncRegistrationOptions](structures.NotebookDocumentSyncOptions.fromJson,structures.NotebookDocumentSyncRegistrationOptions.fromJson)
    val decode_completionProvider: Decoder[structures.CompletionOptions] = structures.CompletionOptions.fromJson
    val decode_hoverProvider: Decoder[(Boolean | structures.HoverOptions)] = Dec.union2[Boolean, structures.HoverOptions](Decoder.decodeBoolean,structures.HoverOptions.fromJson)
    val decode_signatureHelpProvider: Decoder[structures.SignatureHelpOptions] = structures.SignatureHelpOptions.fromJson
    val decode_declarationProvider: Decoder[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)] = Dec.union3[Boolean, structures.DeclarationOptions, structures.DeclarationRegistrationOptions](Decoder.decodeBoolean,structures.DeclarationOptions.fromJson, structures.DeclarationRegistrationOptions.fromJson)
    val decode_definitionProvider: Decoder[(Boolean | structures.DefinitionOptions)] = Dec.union2[Boolean, structures.DefinitionOptions](Decoder.decodeBoolean,structures.DefinitionOptions.fromJson)
    val decode_typeDefinitionProvider: Decoder[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)] = Dec.union3[Boolean, structures.TypeDefinitionOptions, structures.TypeDefinitionRegistrationOptions](Decoder.decodeBoolean,structures.TypeDefinitionOptions.fromJson, structures.TypeDefinitionRegistrationOptions.fromJson)
    val decode_implementationProvider: Decoder[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)] = Dec.union3[Boolean, structures.ImplementationOptions, structures.ImplementationRegistrationOptions](Decoder.decodeBoolean,structures.ImplementationOptions.fromJson, structures.ImplementationRegistrationOptions.fromJson)
    val decode_referencesProvider: Decoder[(Boolean | structures.ReferenceOptions)] = Dec.union2[Boolean, structures.ReferenceOptions](Decoder.decodeBoolean,structures.ReferenceOptions.fromJson)
    val decode_documentHighlightProvider: Decoder[(Boolean | structures.DocumentHighlightOptions)] = Dec.union2[Boolean, structures.DocumentHighlightOptions](Decoder.decodeBoolean,structures.DocumentHighlightOptions.fromJson)
    val decode_documentSymbolProvider: Decoder[(Boolean | structures.DocumentSymbolOptions)] = Dec.union2[Boolean, structures.DocumentSymbolOptions](Decoder.decodeBoolean,structures.DocumentSymbolOptions.fromJson)
    val decode_codeActionProvider: Decoder[(Boolean | structures.CodeActionOptions)] = Dec.union2[Boolean, structures.CodeActionOptions](Decoder.decodeBoolean,structures.CodeActionOptions.fromJson)
    val decode_codeLensProvider: Decoder[structures.CodeLensOptions] = structures.CodeLensOptions.fromJson
    val decode_documentLinkProvider: Decoder[structures.DocumentLinkOptions] = structures.DocumentLinkOptions.fromJson
    val decode_colorProvider: Decoder[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)] = Dec.union3[Boolean, structures.DocumentColorOptions, structures.DocumentColorRegistrationOptions](Decoder.decodeBoolean,structures.DocumentColorOptions.fromJson, structures.DocumentColorRegistrationOptions.fromJson)
    val decode_workspaceSymbolProvider: Decoder[(Boolean | structures.WorkspaceSymbolOptions)] = Dec.union2[Boolean, structures.WorkspaceSymbolOptions](Decoder.decodeBoolean,structures.WorkspaceSymbolOptions.fromJson)
    val decode_documentFormattingProvider: Decoder[(Boolean | structures.DocumentFormattingOptions)] = Dec.union2[Boolean, structures.DocumentFormattingOptions](Decoder.decodeBoolean,structures.DocumentFormattingOptions.fromJson)
    val decode_documentRangeFormattingProvider: Decoder[(Boolean | structures.DocumentRangeFormattingOptions)] = Dec.union2[Boolean, structures.DocumentRangeFormattingOptions](Decoder.decodeBoolean,structures.DocumentRangeFormattingOptions.fromJson)
    val decode_documentOnTypeFormattingProvider: Decoder[structures.DocumentOnTypeFormattingOptions] = structures.DocumentOnTypeFormattingOptions.fromJson
    val decode_renameProvider: Decoder[(Boolean | structures.RenameOptions)] = Dec.union2[Boolean, structures.RenameOptions](Decoder.decodeBoolean,structures.RenameOptions.fromJson)
    val decode_foldingRangeProvider: Decoder[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)] = Dec.union3[Boolean, structures.FoldingRangeOptions, structures.FoldingRangeRegistrationOptions](Decoder.decodeBoolean,structures.FoldingRangeOptions.fromJson, structures.FoldingRangeRegistrationOptions.fromJson)
    val decode_selectionRangeProvider: Decoder[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)] = Dec.union3[Boolean, structures.SelectionRangeOptions, structures.SelectionRangeRegistrationOptions](Decoder.decodeBoolean,structures.SelectionRangeOptions.fromJson, structures.SelectionRangeRegistrationOptions.fromJson)
    val decode_executeCommandProvider: Decoder[structures.ExecuteCommandOptions] = structures.ExecuteCommandOptions.fromJson
    val decode_callHierarchyProvider: Decoder[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)] = Dec.union3[Boolean, structures.CallHierarchyOptions, structures.CallHierarchyRegistrationOptions](Decoder.decodeBoolean,structures.CallHierarchyOptions.fromJson, structures.CallHierarchyRegistrationOptions.fromJson)
    val decode_linkedEditingRangeProvider: Decoder[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)] = Dec.union3[Boolean, structures.LinkedEditingRangeOptions, structures.LinkedEditingRangeRegistrationOptions](Decoder.decodeBoolean,structures.LinkedEditingRangeOptions.fromJson, structures.LinkedEditingRangeRegistrationOptions.fromJson)
    val decode_semanticTokensProvider: Decoder[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)] = Dec.union2[structures.SemanticTokensOptions, structures.SemanticTokensRegistrationOptions](structures.SemanticTokensOptions.fromJson,structures.SemanticTokensRegistrationOptions.fromJson)
    val decode_monikerProvider: Decoder[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)] = Dec.union3[Boolean, structures.MonikerOptions, structures.MonikerRegistrationOptions](Decoder.decodeBoolean,structures.MonikerOptions.fromJson, structures.MonikerRegistrationOptions.fromJson)
    val decode_typeHierarchyProvider: Decoder[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)] = Dec.union3[Boolean, structures.TypeHierarchyOptions, structures.TypeHierarchyRegistrationOptions](Decoder.decodeBoolean,structures.TypeHierarchyOptions.fromJson, structures.TypeHierarchyRegistrationOptions.fromJson)
    val decode_inlineValueProvider: Decoder[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)] = Dec.union3[Boolean, structures.InlineValueOptions, structures.InlineValueRegistrationOptions](Decoder.decodeBoolean,structures.InlineValueOptions.fromJson, structures.InlineValueRegistrationOptions.fromJson)
    val decode_inlayHintProvider: Decoder[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)] = Dec.union3[Boolean, structures.InlayHintOptions, structures.InlayHintRegistrationOptions](Decoder.decodeBoolean,structures.InlayHintOptions.fromJson, structures.InlayHintRegistrationOptions.fromJson)
    val decode_diagnosticProvider: Decoder[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)] = Dec.union2[structures.DiagnosticOptions, structures.DiagnosticRegistrationOptions](structures.DiagnosticOptions.fromJson,structures.DiagnosticRegistrationOptions.fromJson)
    val decode_workspace: Decoder[ServerCapabilities.Workspace] = ServerCapabilities.Workspace.fromJson
    val decode_experimental: Decoder[io.circe.Json] = Decoder.decodeJson
    Dec.fromJsonObject: dec =>
      for
        positionEncoding <- dec.getOpt("positionEncoding", decode_positionEncoding)
        textDocumentSync <- dec.getOpt("textDocumentSync", decode_textDocumentSync)
        notebookDocumentSync <- dec.getOpt("notebookDocumentSync", decode_notebookDocumentSync)
        completionProvider <- dec.getOpt("completionProvider", decode_completionProvider)
        hoverProvider <- dec.getOpt("hoverProvider", decode_hoverProvider)
        signatureHelpProvider <- dec.getOpt("signatureHelpProvider", decode_signatureHelpProvider)
        declarationProvider <- dec.getOpt("declarationProvider", decode_declarationProvider)
        definitionProvider <- dec.getOpt("definitionProvider", decode_definitionProvider)
        typeDefinitionProvider <- dec.getOpt("typeDefinitionProvider", decode_typeDefinitionProvider)
        implementationProvider <- dec.getOpt("implementationProvider", decode_implementationProvider)
        referencesProvider <- dec.getOpt("referencesProvider", decode_referencesProvider)
        documentHighlightProvider <- dec.getOpt("documentHighlightProvider", decode_documentHighlightProvider)
        documentSymbolProvider <- dec.getOpt("documentSymbolProvider", decode_documentSymbolProvider)
        codeActionProvider <- dec.getOpt("codeActionProvider", decode_codeActionProvider)
        codeLensProvider <- dec.getOpt("codeLensProvider", decode_codeLensProvider)
        documentLinkProvider <- dec.getOpt("documentLinkProvider", decode_documentLinkProvider)
        colorProvider <- dec.getOpt("colorProvider", decode_colorProvider)
        workspaceSymbolProvider <- dec.getOpt("workspaceSymbolProvider", decode_workspaceSymbolProvider)
        documentFormattingProvider <- dec.getOpt("documentFormattingProvider", decode_documentFormattingProvider)
        documentRangeFormattingProvider <- dec.getOpt("documentRangeFormattingProvider", decode_documentRangeFormattingProvider)
        documentOnTypeFormattingProvider <- dec.getOpt("documentOnTypeFormattingProvider", decode_documentOnTypeFormattingProvider)
        renameProvider <- dec.getOpt("renameProvider", decode_renameProvider)
        foldingRangeProvider <- dec.getOpt("foldingRangeProvider", decode_foldingRangeProvider)
        selectionRangeProvider <- dec.getOpt("selectionRangeProvider", decode_selectionRangeProvider)
        executeCommandProvider <- dec.getOpt("executeCommandProvider", decode_executeCommandProvider)
        callHierarchyProvider <- dec.getOpt("callHierarchyProvider", decode_callHierarchyProvider)
        linkedEditingRangeProvider <- dec.getOpt("linkedEditingRangeProvider", decode_linkedEditingRangeProvider)
        semanticTokensProvider <- dec.getOpt("semanticTokensProvider", decode_semanticTokensProvider)
        monikerProvider <- dec.getOpt("monikerProvider", decode_monikerProvider)
        typeHierarchyProvider <- dec.getOpt("typeHierarchyProvider", decode_typeHierarchyProvider)
        inlineValueProvider <- dec.getOpt("inlineValueProvider", decode_inlineValueProvider)
        inlayHintProvider <- dec.getOpt("inlayHintProvider", decode_inlayHintProvider)
        diagnosticProvider <- dec.getOpt("diagnosticProvider", decode_diagnosticProvider)
        workspace <- dec.getOpt("workspace", decode_workspace)
        experimental <- dec.getOpt("experimental", decode_experimental)
      yield ServerCapabilities(
        positionEncoding,
        textDocumentSync,
        notebookDocumentSync,
        completionProvider,
        hoverProvider,
        signatureHelpProvider,
        declarationProvider,
        definitionProvider,
        typeDefinitionProvider,
        implementationProvider,
        referencesProvider,
        documentHighlightProvider,
        documentSymbolProvider,
        codeActionProvider,
        codeLensProvider,
        documentLinkProvider,
        colorProvider,
        workspaceSymbolProvider,
        documentFormattingProvider,
        documentRangeFormattingProvider,
        documentOnTypeFormattingProvider,
        renameProvider,
        foldingRangeProvider,
        selectionRangeProvider,
        executeCommandProvider,
        callHierarchyProvider,
        linkedEditingRangeProvider,
        semanticTokensProvider,
        monikerProvider,
        typeHierarchyProvider,
        inlineValueProvider,
        inlayHintProvider,
        diagnosticProvider,
        workspace,
        experimental,
      )
  given toJson: Encoder[ServerCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_positionEncoding: Encoder[enumerations.PositionEncodingKind] = enumerations.PositionEncodingKind.toJson
    val encode_textDocumentSync: Encoder[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)] = Enc.union2[structures.TextDocumentSyncOptions, enumerations.TextDocumentSyncKind](structures.TextDocumentSyncOptions.toJson,enumerations.TextDocumentSyncKind.toJson)
    val encode_notebookDocumentSync: Encoder[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)] = Enc.union2[structures.NotebookDocumentSyncOptions, structures.NotebookDocumentSyncRegistrationOptions](structures.NotebookDocumentSyncOptions.toJson,structures.NotebookDocumentSyncRegistrationOptions.toJson)
    val encode_completionProvider: Encoder[structures.CompletionOptions] = structures.CompletionOptions.toJson
    val encode_hoverProvider: Encoder[(Boolean | structures.HoverOptions)] = Enc.union2[Boolean, structures.HoverOptions](Encoder.encodeBoolean,structures.HoverOptions.toJson)
    val encode_signatureHelpProvider: Encoder[structures.SignatureHelpOptions] = structures.SignatureHelpOptions.toJson
    val encode_declarationProvider: Encoder[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)] = Enc.union3[Boolean, structures.DeclarationOptions, structures.DeclarationRegistrationOptions](Encoder.encodeBoolean,structures.DeclarationOptions.toJson, structures.DeclarationRegistrationOptions.toJson)
    val encode_definitionProvider: Encoder[(Boolean | structures.DefinitionOptions)] = Enc.union2[Boolean, structures.DefinitionOptions](Encoder.encodeBoolean,structures.DefinitionOptions.toJson)
    val encode_typeDefinitionProvider: Encoder[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)] = Enc.union3[Boolean, structures.TypeDefinitionOptions, structures.TypeDefinitionRegistrationOptions](Encoder.encodeBoolean,structures.TypeDefinitionOptions.toJson, structures.TypeDefinitionRegistrationOptions.toJson)
    val encode_implementationProvider: Encoder[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)] = Enc.union3[Boolean, structures.ImplementationOptions, structures.ImplementationRegistrationOptions](Encoder.encodeBoolean,structures.ImplementationOptions.toJson, structures.ImplementationRegistrationOptions.toJson)
    val encode_referencesProvider: Encoder[(Boolean | structures.ReferenceOptions)] = Enc.union2[Boolean, structures.ReferenceOptions](Encoder.encodeBoolean,structures.ReferenceOptions.toJson)
    val encode_documentHighlightProvider: Encoder[(Boolean | structures.DocumentHighlightOptions)] = Enc.union2[Boolean, structures.DocumentHighlightOptions](Encoder.encodeBoolean,structures.DocumentHighlightOptions.toJson)
    val encode_documentSymbolProvider: Encoder[(Boolean | structures.DocumentSymbolOptions)] = Enc.union2[Boolean, structures.DocumentSymbolOptions](Encoder.encodeBoolean,structures.DocumentSymbolOptions.toJson)
    val encode_codeActionProvider: Encoder[(Boolean | structures.CodeActionOptions)] = Enc.union2[Boolean, structures.CodeActionOptions](Encoder.encodeBoolean,structures.CodeActionOptions.toJson)
    val encode_codeLensProvider: Encoder[structures.CodeLensOptions] = structures.CodeLensOptions.toJson
    val encode_documentLinkProvider: Encoder[structures.DocumentLinkOptions] = structures.DocumentLinkOptions.toJson
    val encode_colorProvider: Encoder[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)] = Enc.union3[Boolean, structures.DocumentColorOptions, structures.DocumentColorRegistrationOptions](Encoder.encodeBoolean,structures.DocumentColorOptions.toJson, structures.DocumentColorRegistrationOptions.toJson)
    val encode_workspaceSymbolProvider: Encoder[(Boolean | structures.WorkspaceSymbolOptions)] = Enc.union2[Boolean, structures.WorkspaceSymbolOptions](Encoder.encodeBoolean,structures.WorkspaceSymbolOptions.toJson)
    val encode_documentFormattingProvider: Encoder[(Boolean | structures.DocumentFormattingOptions)] = Enc.union2[Boolean, structures.DocumentFormattingOptions](Encoder.encodeBoolean,structures.DocumentFormattingOptions.toJson)
    val encode_documentRangeFormattingProvider: Encoder[(Boolean | structures.DocumentRangeFormattingOptions)] = Enc.union2[Boolean, structures.DocumentRangeFormattingOptions](Encoder.encodeBoolean,structures.DocumentRangeFormattingOptions.toJson)
    val encode_documentOnTypeFormattingProvider: Encoder[structures.DocumentOnTypeFormattingOptions] = structures.DocumentOnTypeFormattingOptions.toJson
    val encode_renameProvider: Encoder[(Boolean | structures.RenameOptions)] = Enc.union2[Boolean, structures.RenameOptions](Encoder.encodeBoolean,structures.RenameOptions.toJson)
    val encode_foldingRangeProvider: Encoder[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)] = Enc.union3[Boolean, structures.FoldingRangeOptions, structures.FoldingRangeRegistrationOptions](Encoder.encodeBoolean,structures.FoldingRangeOptions.toJson, structures.FoldingRangeRegistrationOptions.toJson)
    val encode_selectionRangeProvider: Encoder[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)] = Enc.union3[Boolean, structures.SelectionRangeOptions, structures.SelectionRangeRegistrationOptions](Encoder.encodeBoolean,structures.SelectionRangeOptions.toJson, structures.SelectionRangeRegistrationOptions.toJson)
    val encode_executeCommandProvider: Encoder[structures.ExecuteCommandOptions] = structures.ExecuteCommandOptions.toJson
    val encode_callHierarchyProvider: Encoder[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)] = Enc.union3[Boolean, structures.CallHierarchyOptions, structures.CallHierarchyRegistrationOptions](Encoder.encodeBoolean,structures.CallHierarchyOptions.toJson, structures.CallHierarchyRegistrationOptions.toJson)
    val encode_linkedEditingRangeProvider: Encoder[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)] = Enc.union3[Boolean, structures.LinkedEditingRangeOptions, structures.LinkedEditingRangeRegistrationOptions](Encoder.encodeBoolean,structures.LinkedEditingRangeOptions.toJson, structures.LinkedEditingRangeRegistrationOptions.toJson)
    val encode_semanticTokensProvider: Encoder[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)] = Enc.union2[structures.SemanticTokensOptions, structures.SemanticTokensRegistrationOptions](structures.SemanticTokensOptions.toJson,structures.SemanticTokensRegistrationOptions.toJson)
    val encode_monikerProvider: Encoder[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)] = Enc.union3[Boolean, structures.MonikerOptions, structures.MonikerRegistrationOptions](Encoder.encodeBoolean,structures.MonikerOptions.toJson, structures.MonikerRegistrationOptions.toJson)
    val encode_typeHierarchyProvider: Encoder[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)] = Enc.union3[Boolean, structures.TypeHierarchyOptions, structures.TypeHierarchyRegistrationOptions](Encoder.encodeBoolean,structures.TypeHierarchyOptions.toJson, structures.TypeHierarchyRegistrationOptions.toJson)
    val encode_inlineValueProvider: Encoder[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)] = Enc.union3[Boolean, structures.InlineValueOptions, structures.InlineValueRegistrationOptions](Encoder.encodeBoolean,structures.InlineValueOptions.toJson, structures.InlineValueRegistrationOptions.toJson)
    val encode_inlayHintProvider: Encoder[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)] = Enc.union3[Boolean, structures.InlayHintOptions, structures.InlayHintRegistrationOptions](Encoder.encodeBoolean,structures.InlayHintOptions.toJson, structures.InlayHintRegistrationOptions.toJson)
    val encode_diagnosticProvider: Encoder[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)] = Enc.union2[structures.DiagnosticOptions, structures.DiagnosticRegistrationOptions](structures.DiagnosticOptions.toJson,structures.DiagnosticRegistrationOptions.toJson)
    val encode_workspace: Encoder[ServerCapabilities.Workspace] = ServerCapabilities.Workspace.toJson
    val encode_experimental: Encoder[io.circe.Json] = Encoder.encodeJson
    Enc.toJsonObject: (enc, a) =>
      a.positionEncoding.foreach: v =>
        enc.field("positionEncoding", v, encode_positionEncoding)
      a.textDocumentSync.foreach: v =>
        enc.field("textDocumentSync", v, encode_textDocumentSync)
      a.notebookDocumentSync.foreach: v =>
        enc.field("notebookDocumentSync", v, encode_notebookDocumentSync)
      a.completionProvider.foreach: v =>
        enc.field("completionProvider", v, encode_completionProvider)
      a.hoverProvider.foreach: v =>
        enc.field("hoverProvider", v, encode_hoverProvider)
      a.signatureHelpProvider.foreach: v =>
        enc.field("signatureHelpProvider", v, encode_signatureHelpProvider)
      a.declarationProvider.foreach: v =>
        enc.field("declarationProvider", v, encode_declarationProvider)
      a.definitionProvider.foreach: v =>
        enc.field("definitionProvider", v, encode_definitionProvider)
      a.typeDefinitionProvider.foreach: v =>
        enc.field("typeDefinitionProvider", v, encode_typeDefinitionProvider)
      a.implementationProvider.foreach: v =>
        enc.field("implementationProvider", v, encode_implementationProvider)
      a.referencesProvider.foreach: v =>
        enc.field("referencesProvider", v, encode_referencesProvider)
      a.documentHighlightProvider.foreach: v =>
        enc.field("documentHighlightProvider", v, encode_documentHighlightProvider)
      a.documentSymbolProvider.foreach: v =>
        enc.field("documentSymbolProvider", v, encode_documentSymbolProvider)
      a.codeActionProvider.foreach: v =>
        enc.field("codeActionProvider", v, encode_codeActionProvider)
      a.codeLensProvider.foreach: v =>
        enc.field("codeLensProvider", v, encode_codeLensProvider)
      a.documentLinkProvider.foreach: v =>
        enc.field("documentLinkProvider", v, encode_documentLinkProvider)
      a.colorProvider.foreach: v =>
        enc.field("colorProvider", v, encode_colorProvider)
      a.workspaceSymbolProvider.foreach: v =>
        enc.field("workspaceSymbolProvider", v, encode_workspaceSymbolProvider)
      a.documentFormattingProvider.foreach: v =>
        enc.field("documentFormattingProvider", v, encode_documentFormattingProvider)
      a.documentRangeFormattingProvider.foreach: v =>
        enc.field("documentRangeFormattingProvider", v, encode_documentRangeFormattingProvider)
      a.documentOnTypeFormattingProvider.foreach: v =>
        enc.field("documentOnTypeFormattingProvider", v, encode_documentOnTypeFormattingProvider)
      a.renameProvider.foreach: v =>
        enc.field("renameProvider", v, encode_renameProvider)
      a.foldingRangeProvider.foreach: v =>
        enc.field("foldingRangeProvider", v, encode_foldingRangeProvider)
      a.selectionRangeProvider.foreach: v =>
        enc.field("selectionRangeProvider", v, encode_selectionRangeProvider)
      a.executeCommandProvider.foreach: v =>
        enc.field("executeCommandProvider", v, encode_executeCommandProvider)
      a.callHierarchyProvider.foreach: v =>
        enc.field("callHierarchyProvider", v, encode_callHierarchyProvider)
      a.linkedEditingRangeProvider.foreach: v =>
        enc.field("linkedEditingRangeProvider", v, encode_linkedEditingRangeProvider)
      a.semanticTokensProvider.foreach: v =>
        enc.field("semanticTokensProvider", v, encode_semanticTokensProvider)
      a.monikerProvider.foreach: v =>
        enc.field("monikerProvider", v, encode_monikerProvider)
      a.typeHierarchyProvider.foreach: v =>
        enc.field("typeHierarchyProvider", v, encode_typeHierarchyProvider)
      a.inlineValueProvider.foreach: v =>
        enc.field("inlineValueProvider", v, encode_inlineValueProvider)
      a.inlayHintProvider.foreach: v =>
        enc.field("inlayHintProvider", v, encode_inlayHintProvider)
      a.diagnosticProvider.foreach: v =>
        enc.field("diagnosticProvider", v, encode_diagnosticProvider)
      a.workspace.foreach: v =>
        enc.field("workspace", v, encode_workspace)
      a.experimental.foreach: v =>
        enc.field("experimental", v, encode_experimental)


private[lsp] trait structures_ServerCapabilities_WorkspaceCodec:
  import structures.ServerCapabilities.*
  given fromJson: Decoder[Workspace] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workspaceFolders: Decoder[structures.WorkspaceFoldersServerCapabilities] = structures.WorkspaceFoldersServerCapabilities.fromJson
    val decode_fileOperations: Decoder[structures.FileOperationOptions] = structures.FileOperationOptions.fromJson
    Dec.fromJsonObject: dec =>
      for
        workspaceFolders <- dec.getOpt("workspaceFolders", decode_workspaceFolders)
        fileOperations <- dec.getOpt("fileOperations", decode_fileOperations)
      yield Workspace(
        workspaceFolders,
        fileOperations,
      )
  given toJson: Encoder[Workspace] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workspaceFolders: Encoder[structures.WorkspaceFoldersServerCapabilities] = structures.WorkspaceFoldersServerCapabilities.toJson
    val encode_fileOperations: Encoder[structures.FileOperationOptions] = structures.FileOperationOptions.toJson
    Enc.toJsonObject: (enc, a) =>
      a.workspaceFolders.foreach: v =>
        enc.field("workspaceFolders", v, encode_workspaceFolders)
      a.fileOperations.foreach: v =>
        enc.field("fileOperations", v, encode_fileOperations)


private[lsp] trait structures_SetTraceParamsCodec:
  import structures.*
  given fromJson: Decoder[SetTraceParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_value: Decoder[enumerations.TraceValues] = enumerations.TraceValues.fromJson
    Dec.fromJsonObject: dec =>
      for
        value <- dec.get("value", decode_value)
      yield SetTraceParams(
        value,
      )
  given toJson: Encoder[SetTraceParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_value: Encoder[enumerations.TraceValues] = enumerations.TraceValues.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("value", a.value, encode_value)


private[lsp] trait structures_ShowDocumentClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[ShowDocumentClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_support: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        support <- dec.get("support", decode_support)
      yield ShowDocumentClientCapabilities(
        support,
      )
  given toJson: Encoder[ShowDocumentClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_support: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      enc.field("support", a.support, encode_support)


private[lsp] trait structures_ShowDocumentParamsCodec:
  import structures.*
  given fromJson: Decoder[ShowDocumentParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_uri: Decoder[runtime.Uri] = Uri.fromJson
    val decode_external: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_takeFocus: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_selection: Decoder[structures.Range] = structures.Range.fromJson
    Dec.fromJsonObject: dec =>
      for
        uri <- dec.get("uri", decode_uri)
        external <- dec.getOpt("external", decode_external)
        takeFocus <- dec.getOpt("takeFocus", decode_takeFocus)
        selection <- dec.getOpt("selection", decode_selection)
      yield ShowDocumentParams(
        uri,
        external,
        takeFocus,
        selection,
      )
  given toJson: Encoder[ShowDocumentParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_uri: Encoder[runtime.Uri] = Uri.toJson
    val encode_external: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_takeFocus: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_selection: Encoder[structures.Range] = structures.Range.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("uri", a.uri, encode_uri)
      a.external.foreach: v =>
        enc.field("external", v, encode_external)
      a.takeFocus.foreach: v =>
        enc.field("takeFocus", v, encode_takeFocus)
      a.selection.foreach: v =>
        enc.field("selection", v, encode_selection)


private[lsp] trait structures_ShowDocumentResultCodec:
  import structures.*
  given fromJson: Decoder[ShowDocumentResult] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_success: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        success <- dec.get("success", decode_success)
      yield ShowDocumentResult(
        success,
      )
  given toJson: Encoder[ShowDocumentResult] = 
    // cache all encoders for this type when toJson first initialised
    val encode_success: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      enc.field("success", a.success, encode_success)


private[lsp] trait structures_ShowMessageParamsCodec:
  import structures.*
  given fromJson: Decoder[ShowMessageParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_type: Decoder[enumerations.MessageType] = enumerations.MessageType.fromJson
    val decode_message: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        `type` <- dec.get("type", decode_type)
        message <- dec.get("message", decode_message)
      yield ShowMessageParams(
        `type`,
        message,
      )
  given toJson: Encoder[ShowMessageParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_type: Encoder[enumerations.MessageType] = enumerations.MessageType.toJson
    val encode_message: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("type", a.`type`, encode_type)
      enc.field("message", a.message, encode_message)


private[lsp] trait structures_ShowMessageRequestClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[ShowMessageRequestClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_messageActionItem: Decoder[ShowMessageRequestClientCapabilities.MessageActionItem] = ShowMessageRequestClientCapabilities.MessageActionItem.fromJson
    Dec.fromJsonObject: dec =>
      for
        messageActionItem <- dec.getOpt("messageActionItem", decode_messageActionItem)
      yield ShowMessageRequestClientCapabilities(
        messageActionItem,
      )
  given toJson: Encoder[ShowMessageRequestClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_messageActionItem: Encoder[ShowMessageRequestClientCapabilities.MessageActionItem] = ShowMessageRequestClientCapabilities.MessageActionItem.toJson
    Enc.toJsonObject: (enc, a) =>
      a.messageActionItem.foreach: v =>
        enc.field("messageActionItem", v, encode_messageActionItem)


private[lsp] trait structures_ShowMessageRequestClientCapabilities_MessageActionItemCodec:
  import structures.ShowMessageRequestClientCapabilities.*
  given fromJson: Decoder[MessageActionItem] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_additionalPropertiesSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        additionalPropertiesSupport <- dec.getOpt("additionalPropertiesSupport", decode_additionalPropertiesSupport)
      yield MessageActionItem(
        additionalPropertiesSupport,
      )
  given toJson: Encoder[MessageActionItem] = 
    // cache all encoders for this type when toJson first initialised
    val encode_additionalPropertiesSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.additionalPropertiesSupport.foreach: v =>
        enc.field("additionalPropertiesSupport", v, encode_additionalPropertiesSupport)


private[lsp] trait structures_ShowMessageRequestParamsCodec:
  import structures.*
  given fromJson: Decoder[ShowMessageRequestParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_type: Decoder[enumerations.MessageType] = enumerations.MessageType.fromJson
    val decode_message: Decoder[String] = Decoder.decodeString
    val decode_actions: Decoder[Vector[structures.MessageActionItem]] = Decoder.decodeVector(structures.MessageActionItem.fromJson)
    Dec.fromJsonObject: dec =>
      for
        `type` <- dec.get("type", decode_type)
        message <- dec.get("message", decode_message)
        actions <- dec.getOpt("actions", decode_actions)
      yield ShowMessageRequestParams(
        `type`,
        message,
        actions,
      )
  given toJson: Encoder[ShowMessageRequestParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_type: Encoder[enumerations.MessageType] = enumerations.MessageType.toJson
    val encode_message: Encoder[String] = Encoder.encodeString
    val encode_actions: Encoder[Vector[structures.MessageActionItem]] = Encoder.encodeVector(structures.MessageActionItem.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("type", a.`type`, encode_type)
      enc.field("message", a.message, encode_message)
      a.actions.foreach: v =>
        enc.field("actions", v, encode_actions)


private[lsp] trait structures_SignatureHelpCodec:
  import structures.*
  given fromJson: Decoder[SignatureHelp] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_signatures: Decoder[Vector[structures.SignatureInformation]] = Decoder.decodeVector(structures.SignatureInformation.fromJson)
    val decode_activeSignature: Decoder[runtime.uinteger] = uinteger.fromJson
    val decode_activeParameter: Decoder[runtime.uinteger] = uinteger.fromJson
    Dec.fromJsonObject: dec =>
      for
        signatures <- dec.get("signatures", decode_signatures)
        activeSignature <- dec.getOpt("activeSignature", decode_activeSignature)
        activeParameter <- dec.getOpt("activeParameter", decode_activeParameter)
      yield SignatureHelp(
        signatures,
        activeSignature,
        activeParameter,
      )
  given toJson: Encoder[SignatureHelp] = 
    // cache all encoders for this type when toJson first initialised
    val encode_signatures: Encoder[Vector[structures.SignatureInformation]] = Encoder.encodeVector(structures.SignatureInformation.toJson)
    val encode_activeSignature: Encoder[runtime.uinteger] = uinteger.toJson
    val encode_activeParameter: Encoder[runtime.uinteger] = uinteger.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("signatures", a.signatures, encode_signatures)
      a.activeSignature.foreach: v =>
        enc.field("activeSignature", v, encode_activeSignature)
      a.activeParameter.foreach: v =>
        enc.field("activeParameter", v, encode_activeParameter)


private[lsp] trait structures_SignatureHelpClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[SignatureHelpClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_signatureInformation: Decoder[SignatureHelpClientCapabilities.SignatureInformation] = SignatureHelpClientCapabilities.SignatureInformation.fromJson
    val decode_contextSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        signatureInformation <- dec.getOpt("signatureInformation", decode_signatureInformation)
        contextSupport <- dec.getOpt("contextSupport", decode_contextSupport)
      yield SignatureHelpClientCapabilities(
        dynamicRegistration,
        signatureInformation,
        contextSupport,
      )
  given toJson: Encoder[SignatureHelpClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_signatureInformation: Encoder[SignatureHelpClientCapabilities.SignatureInformation] = SignatureHelpClientCapabilities.SignatureInformation.toJson
    val encode_contextSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      a.signatureInformation.foreach: v =>
        enc.field("signatureInformation", v, encode_signatureInformation)
      a.contextSupport.foreach: v =>
        enc.field("contextSupport", v, encode_contextSupport)


private[lsp] trait structures_SignatureHelpClientCapabilities_SignatureInformationCodec:
  import structures.SignatureHelpClientCapabilities.*
  given fromJson: Decoder[SignatureInformation] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentationFormat: Decoder[Vector[enumerations.MarkupKind]] = Decoder.decodeVector(enumerations.MarkupKind.fromJson)
    val decode_parameterInformation: Decoder[SignatureInformation.ParameterInformation] = SignatureInformation.ParameterInformation.fromJson
    val decode_activeParameterSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        documentationFormat <- dec.getOpt("documentationFormat", decode_documentationFormat)
        parameterInformation <- dec.getOpt("parameterInformation", decode_parameterInformation)
        activeParameterSupport <- dec.getOpt("activeParameterSupport", decode_activeParameterSupport)
      yield SignatureInformation(
        documentationFormat,
        parameterInformation,
        activeParameterSupport,
      )
  given toJson: Encoder[SignatureInformation] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentationFormat: Encoder[Vector[enumerations.MarkupKind]] = Encoder.encodeVector(enumerations.MarkupKind.toJson)
    val encode_parameterInformation: Encoder[SignatureInformation.ParameterInformation] = SignatureInformation.ParameterInformation.toJson
    val encode_activeParameterSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.documentationFormat.foreach: v =>
        enc.field("documentationFormat", v, encode_documentationFormat)
      a.parameterInformation.foreach: v =>
        enc.field("parameterInformation", v, encode_parameterInformation)
      a.activeParameterSupport.foreach: v =>
        enc.field("activeParameterSupport", v, encode_activeParameterSupport)


private[lsp] trait structures_SignatureHelpClientCapabilities_SignatureInformation_ParameterInformationCodec:
  import structures.SignatureHelpClientCapabilities.SignatureInformation.*
  given fromJson: Decoder[ParameterInformation] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_labelOffsetSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        labelOffsetSupport <- dec.getOpt("labelOffsetSupport", decode_labelOffsetSupport)
      yield ParameterInformation(
        labelOffsetSupport,
      )
  given toJson: Encoder[ParameterInformation] = 
    // cache all encoders for this type when toJson first initialised
    val encode_labelOffsetSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.labelOffsetSupport.foreach: v =>
        enc.field("labelOffsetSupport", v, encode_labelOffsetSupport)


private[lsp] trait structures_SignatureHelpContextCodec:
  import structures.*
  given fromJson: Decoder[SignatureHelpContext] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_triggerKind: Decoder[enumerations.SignatureHelpTriggerKind] = enumerations.SignatureHelpTriggerKind.fromJson
    val decode_triggerCharacter: Decoder[String] = Decoder.decodeString
    val decode_isRetrigger: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_activeSignatureHelp: Decoder[structures.SignatureHelp] = structures.SignatureHelp.fromJson
    Dec.fromJsonObject: dec =>
      for
        triggerKind <- dec.get("triggerKind", decode_triggerKind)
        triggerCharacter <- dec.getOpt("triggerCharacter", decode_triggerCharacter)
        isRetrigger <- dec.get("isRetrigger", decode_isRetrigger)
        activeSignatureHelp <- dec.getOpt("activeSignatureHelp", decode_activeSignatureHelp)
      yield SignatureHelpContext(
        triggerKind,
        triggerCharacter,
        isRetrigger,
        activeSignatureHelp,
      )
  given toJson: Encoder[SignatureHelpContext] = 
    // cache all encoders for this type when toJson first initialised
    val encode_triggerKind: Encoder[enumerations.SignatureHelpTriggerKind] = enumerations.SignatureHelpTriggerKind.toJson
    val encode_triggerCharacter: Encoder[String] = Encoder.encodeString
    val encode_isRetrigger: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_activeSignatureHelp: Encoder[structures.SignatureHelp] = structures.SignatureHelp.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("triggerKind", a.triggerKind, encode_triggerKind)
      a.triggerCharacter.foreach: v =>
        enc.field("triggerCharacter", v, encode_triggerCharacter)
      enc.field("isRetrigger", a.isRetrigger, encode_isRetrigger)
      a.activeSignatureHelp.foreach: v =>
        enc.field("activeSignatureHelp", v, encode_activeSignatureHelp)


private[lsp] trait structures_SignatureHelpOptionsCodec:
  import structures.*
  given fromJson: Decoder[SignatureHelpOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_triggerCharacters: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    val decode_retriggerCharacters: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        triggerCharacters <- dec.getOpt("triggerCharacters", decode_triggerCharacters)
        retriggerCharacters <- dec.getOpt("retriggerCharacters", decode_retriggerCharacters)
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield SignatureHelpOptions(
        triggerCharacters,
        retriggerCharacters,
        workDoneProgress,
      )
  given toJson: Encoder[SignatureHelpOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_triggerCharacters: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    val encode_retriggerCharacters: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.triggerCharacters.foreach: v =>
        enc.field("triggerCharacters", v, encode_triggerCharacters)
      a.retriggerCharacters.foreach: v =>
        enc.field("retriggerCharacters", v, encode_retriggerCharacters)
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_SignatureHelpParamsCodec:
  import structures.*
  given fromJson: Decoder[SignatureHelpParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_context: Decoder[structures.SignatureHelpContext] = structures.SignatureHelpContext.fromJson
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_position: Decoder[structures.Position] = structures.Position.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        context <- dec.getOpt("context", decode_context)
        textDocument <- dec.get("textDocument", decode_textDocument)
        position <- dec.get("position", decode_position)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
      yield SignatureHelpParams(
        context,
        textDocument,
        position,
        workDoneToken,
      )
  given toJson: Encoder[SignatureHelpParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_context: Encoder[structures.SignatureHelpContext] = structures.SignatureHelpContext.toJson
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_position: Encoder[structures.Position] = structures.Position.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      a.context.foreach: v =>
        enc.field("context", v, encode_context)
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("position", a.position, encode_position)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)


private[lsp] trait structures_SignatureHelpRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[SignatureHelpRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_triggerCharacters: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    val decode_retriggerCharacters: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        triggerCharacters <- dec.getOpt("triggerCharacters", decode_triggerCharacters)
        retriggerCharacters <- dec.getOpt("retriggerCharacters", decode_retriggerCharacters)
      yield SignatureHelpRegistrationOptions(
        documentSelector,
        triggerCharacters,
        retriggerCharacters,
      )
  given toJson: Encoder[SignatureHelpRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_triggerCharacters: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    val encode_retriggerCharacters: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.triggerCharacters.foreach: v =>
        enc.field("triggerCharacters", v, encode_triggerCharacters)
      a.retriggerCharacters.foreach: v =>
        enc.field("retriggerCharacters", v, encode_retriggerCharacters)


private[lsp] trait structures_SignatureInformationCodec:
  import structures.*
  given fromJson: Decoder[SignatureInformation] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_label: Decoder[String] = Decoder.decodeString
    val decode_documentation: Decoder[(String | structures.MarkupContent)] = Dec.union2[String, structures.MarkupContent](Decoder.decodeString,structures.MarkupContent.fromJson)
    val decode_parameters: Decoder[Vector[structures.ParameterInformation]] = Decoder.decodeVector(structures.ParameterInformation.fromJson)
    val decode_activeParameter: Decoder[runtime.uinteger] = uinteger.fromJson
    Dec.fromJsonObject: dec =>
      for
        label <- dec.get("label", decode_label)
        documentation <- dec.getOpt("documentation", decode_documentation)
        parameters <- dec.getOpt("parameters", decode_parameters)
        activeParameter <- dec.getOpt("activeParameter", decode_activeParameter)
      yield SignatureInformation(
        label,
        documentation,
        parameters,
        activeParameter,
      )
  given toJson: Encoder[SignatureInformation] = 
    // cache all encoders for this type when toJson first initialised
    val encode_label: Encoder[String] = Encoder.encodeString
    val encode_documentation: Encoder[(String | structures.MarkupContent)] = Enc.union2[String, structures.MarkupContent](Encoder.encodeString,structures.MarkupContent.toJson)
    val encode_parameters: Encoder[Vector[structures.ParameterInformation]] = Encoder.encodeVector(structures.ParameterInformation.toJson)
    val encode_activeParameter: Encoder[runtime.uinteger] = uinteger.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("label", a.label, encode_label)
      a.documentation.foreach: v =>
        enc.field("documentation", v, encode_documentation)
      a.parameters.foreach: v =>
        enc.field("parameters", v, encode_parameters)
      a.activeParameter.foreach: v =>
        enc.field("activeParameter", v, encode_activeParameter)


private[lsp] trait structures_StaticRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[StaticRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_id: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        id <- dec.getOpt("id", decode_id)
      yield StaticRegistrationOptions(
        id,
      )
  given toJson: Encoder[StaticRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_id: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.id.foreach: v =>
        enc.field("id", v, encode_id)


private[lsp] trait structures_SymbolInformationCodec:
  import structures.*
  given fromJson: Decoder[SymbolInformation] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_deprecated: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_location: Decoder[structures.Location] = structures.Location.fromJson
    val decode_name: Decoder[String] = Decoder.decodeString
    val decode_kind: Decoder[enumerations.SymbolKind] = enumerations.SymbolKind.fromJson
    val decode_tags: Decoder[Vector[enumerations.SymbolTag]] = Decoder.decodeVector(enumerations.SymbolTag.fromJson)
    val decode_containerName: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        deprecated <- dec.getOpt("deprecated", decode_deprecated)
        location <- dec.get("location", decode_location)
        name <- dec.get("name", decode_name)
        kind <- dec.get("kind", decode_kind)
        tags <- dec.getOpt("tags", decode_tags)
        containerName <- dec.getOpt("containerName", decode_containerName)
      yield SymbolInformation(
        deprecated,
        location,
        name,
        kind,
        tags,
        containerName,
      )
  given toJson: Encoder[SymbolInformation] = 
    // cache all encoders for this type when toJson first initialised
    val encode_deprecated: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_location: Encoder[structures.Location] = structures.Location.toJson
    val encode_name: Encoder[String] = Encoder.encodeString
    val encode_kind: Encoder[enumerations.SymbolKind] = enumerations.SymbolKind.toJson
    val encode_tags: Encoder[Vector[enumerations.SymbolTag]] = Encoder.encodeVector(enumerations.SymbolTag.toJson)
    val encode_containerName: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.deprecated.foreach: v =>
        enc.field("deprecated", v, encode_deprecated)
      enc.field("location", a.location, encode_location)
      enc.field("name", a.name, encode_name)
      enc.field("kind", a.kind, encode_kind)
      a.tags.foreach: v =>
        enc.field("tags", v, encode_tags)
      a.containerName.foreach: v =>
        enc.field("containerName", v, encode_containerName)


private[lsp] trait structures_TextDocumentChangeRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[TextDocumentChangeRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_syncKind: Decoder[enumerations.TextDocumentSyncKind] = enumerations.TextDocumentSyncKind.fromJson
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    Dec.fromJsonObject: dec =>
      for
        syncKind <- dec.get("syncKind", decode_syncKind)
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
      yield TextDocumentChangeRegistrationOptions(
        syncKind,
        documentSelector,
      )
  given toJson: Encoder[TextDocumentChangeRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_syncKind: Encoder[enumerations.TextDocumentSyncKind] = enumerations.TextDocumentSyncKind.toJson
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("syncKind", a.syncKind, encode_syncKind)
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)


private[lsp] trait structures_TextDocumentClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[TextDocumentClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_synchronization: Decoder[structures.TextDocumentSyncClientCapabilities] = structures.TextDocumentSyncClientCapabilities.fromJson
    val decode_completion: Decoder[structures.CompletionClientCapabilities] = structures.CompletionClientCapabilities.fromJson
    val decode_hover: Decoder[structures.HoverClientCapabilities] = structures.HoverClientCapabilities.fromJson
    val decode_signatureHelp: Decoder[structures.SignatureHelpClientCapabilities] = structures.SignatureHelpClientCapabilities.fromJson
    val decode_declaration: Decoder[structures.DeclarationClientCapabilities] = structures.DeclarationClientCapabilities.fromJson
    val decode_definition: Decoder[structures.DefinitionClientCapabilities] = structures.DefinitionClientCapabilities.fromJson
    val decode_typeDefinition: Decoder[structures.TypeDefinitionClientCapabilities] = structures.TypeDefinitionClientCapabilities.fromJson
    val decode_implementation: Decoder[structures.ImplementationClientCapabilities] = structures.ImplementationClientCapabilities.fromJson
    val decode_references: Decoder[structures.ReferenceClientCapabilities] = structures.ReferenceClientCapabilities.fromJson
    val decode_documentHighlight: Decoder[structures.DocumentHighlightClientCapabilities] = structures.DocumentHighlightClientCapabilities.fromJson
    val decode_documentSymbol: Decoder[structures.DocumentSymbolClientCapabilities] = structures.DocumentSymbolClientCapabilities.fromJson
    val decode_codeAction: Decoder[structures.CodeActionClientCapabilities] = structures.CodeActionClientCapabilities.fromJson
    val decode_codeLens: Decoder[structures.CodeLensClientCapabilities] = structures.CodeLensClientCapabilities.fromJson
    val decode_documentLink: Decoder[structures.DocumentLinkClientCapabilities] = structures.DocumentLinkClientCapabilities.fromJson
    val decode_colorProvider: Decoder[structures.DocumentColorClientCapabilities] = structures.DocumentColorClientCapabilities.fromJson
    val decode_formatting: Decoder[structures.DocumentFormattingClientCapabilities] = structures.DocumentFormattingClientCapabilities.fromJson
    val decode_rangeFormatting: Decoder[structures.DocumentRangeFormattingClientCapabilities] = structures.DocumentRangeFormattingClientCapabilities.fromJson
    val decode_onTypeFormatting: Decoder[structures.DocumentOnTypeFormattingClientCapabilities] = structures.DocumentOnTypeFormattingClientCapabilities.fromJson
    val decode_rename: Decoder[structures.RenameClientCapabilities] = structures.RenameClientCapabilities.fromJson
    val decode_foldingRange: Decoder[structures.FoldingRangeClientCapabilities] = structures.FoldingRangeClientCapabilities.fromJson
    val decode_selectionRange: Decoder[structures.SelectionRangeClientCapabilities] = structures.SelectionRangeClientCapabilities.fromJson
    val decode_publishDiagnostics: Decoder[structures.PublishDiagnosticsClientCapabilities] = structures.PublishDiagnosticsClientCapabilities.fromJson
    val decode_callHierarchy: Decoder[structures.CallHierarchyClientCapabilities] = structures.CallHierarchyClientCapabilities.fromJson
    val decode_semanticTokens: Decoder[structures.SemanticTokensClientCapabilities] = structures.SemanticTokensClientCapabilities.fromJson
    val decode_linkedEditingRange: Decoder[structures.LinkedEditingRangeClientCapabilities] = structures.LinkedEditingRangeClientCapabilities.fromJson
    val decode_moniker: Decoder[structures.MonikerClientCapabilities] = structures.MonikerClientCapabilities.fromJson
    val decode_typeHierarchy: Decoder[structures.TypeHierarchyClientCapabilities] = structures.TypeHierarchyClientCapabilities.fromJson
    val decode_inlineValue: Decoder[structures.InlineValueClientCapabilities] = structures.InlineValueClientCapabilities.fromJson
    val decode_inlayHint: Decoder[structures.InlayHintClientCapabilities] = structures.InlayHintClientCapabilities.fromJson
    val decode_diagnostic: Decoder[structures.DiagnosticClientCapabilities] = structures.DiagnosticClientCapabilities.fromJson
    Dec.fromJsonObject: dec =>
      for
        synchronization <- dec.getOpt("synchronization", decode_synchronization)
        completion <- dec.getOpt("completion", decode_completion)
        hover <- dec.getOpt("hover", decode_hover)
        signatureHelp <- dec.getOpt("signatureHelp", decode_signatureHelp)
        declaration <- dec.getOpt("declaration", decode_declaration)
        definition <- dec.getOpt("definition", decode_definition)
        typeDefinition <- dec.getOpt("typeDefinition", decode_typeDefinition)
        implementation <- dec.getOpt("implementation", decode_implementation)
        references <- dec.getOpt("references", decode_references)
        documentHighlight <- dec.getOpt("documentHighlight", decode_documentHighlight)
        documentSymbol <- dec.getOpt("documentSymbol", decode_documentSymbol)
        codeAction <- dec.getOpt("codeAction", decode_codeAction)
        codeLens <- dec.getOpt("codeLens", decode_codeLens)
        documentLink <- dec.getOpt("documentLink", decode_documentLink)
        colorProvider <- dec.getOpt("colorProvider", decode_colorProvider)
        formatting <- dec.getOpt("formatting", decode_formatting)
        rangeFormatting <- dec.getOpt("rangeFormatting", decode_rangeFormatting)
        onTypeFormatting <- dec.getOpt("onTypeFormatting", decode_onTypeFormatting)
        rename <- dec.getOpt("rename", decode_rename)
        foldingRange <- dec.getOpt("foldingRange", decode_foldingRange)
        selectionRange <- dec.getOpt("selectionRange", decode_selectionRange)
        publishDiagnostics <- dec.getOpt("publishDiagnostics", decode_publishDiagnostics)
        callHierarchy <- dec.getOpt("callHierarchy", decode_callHierarchy)
        semanticTokens <- dec.getOpt("semanticTokens", decode_semanticTokens)
        linkedEditingRange <- dec.getOpt("linkedEditingRange", decode_linkedEditingRange)
        moniker <- dec.getOpt("moniker", decode_moniker)
        typeHierarchy <- dec.getOpt("typeHierarchy", decode_typeHierarchy)
        inlineValue <- dec.getOpt("inlineValue", decode_inlineValue)
        inlayHint <- dec.getOpt("inlayHint", decode_inlayHint)
        diagnostic <- dec.getOpt("diagnostic", decode_diagnostic)
      yield TextDocumentClientCapabilities(
        synchronization,
        completion,
        hover,
        signatureHelp,
        declaration,
        definition,
        typeDefinition,
        implementation,
        references,
        documentHighlight,
        documentSymbol,
        codeAction,
        codeLens,
        documentLink,
        colorProvider,
        formatting,
        rangeFormatting,
        onTypeFormatting,
        rename,
        foldingRange,
        selectionRange,
        publishDiagnostics,
        callHierarchy,
        semanticTokens,
        linkedEditingRange,
        moniker,
        typeHierarchy,
        inlineValue,
        inlayHint,
        diagnostic,
      )
  given toJson: Encoder[TextDocumentClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_synchronization: Encoder[structures.TextDocumentSyncClientCapabilities] = structures.TextDocumentSyncClientCapabilities.toJson
    val encode_completion: Encoder[structures.CompletionClientCapabilities] = structures.CompletionClientCapabilities.toJson
    val encode_hover: Encoder[structures.HoverClientCapabilities] = structures.HoverClientCapabilities.toJson
    val encode_signatureHelp: Encoder[structures.SignatureHelpClientCapabilities] = structures.SignatureHelpClientCapabilities.toJson
    val encode_declaration: Encoder[structures.DeclarationClientCapabilities] = structures.DeclarationClientCapabilities.toJson
    val encode_definition: Encoder[structures.DefinitionClientCapabilities] = structures.DefinitionClientCapabilities.toJson
    val encode_typeDefinition: Encoder[structures.TypeDefinitionClientCapabilities] = structures.TypeDefinitionClientCapabilities.toJson
    val encode_implementation: Encoder[structures.ImplementationClientCapabilities] = structures.ImplementationClientCapabilities.toJson
    val encode_references: Encoder[structures.ReferenceClientCapabilities] = structures.ReferenceClientCapabilities.toJson
    val encode_documentHighlight: Encoder[structures.DocumentHighlightClientCapabilities] = structures.DocumentHighlightClientCapabilities.toJson
    val encode_documentSymbol: Encoder[structures.DocumentSymbolClientCapabilities] = structures.DocumentSymbolClientCapabilities.toJson
    val encode_codeAction: Encoder[structures.CodeActionClientCapabilities] = structures.CodeActionClientCapabilities.toJson
    val encode_codeLens: Encoder[structures.CodeLensClientCapabilities] = structures.CodeLensClientCapabilities.toJson
    val encode_documentLink: Encoder[structures.DocumentLinkClientCapabilities] = structures.DocumentLinkClientCapabilities.toJson
    val encode_colorProvider: Encoder[structures.DocumentColorClientCapabilities] = structures.DocumentColorClientCapabilities.toJson
    val encode_formatting: Encoder[structures.DocumentFormattingClientCapabilities] = structures.DocumentFormattingClientCapabilities.toJson
    val encode_rangeFormatting: Encoder[structures.DocumentRangeFormattingClientCapabilities] = structures.DocumentRangeFormattingClientCapabilities.toJson
    val encode_onTypeFormatting: Encoder[structures.DocumentOnTypeFormattingClientCapabilities] = structures.DocumentOnTypeFormattingClientCapabilities.toJson
    val encode_rename: Encoder[structures.RenameClientCapabilities] = structures.RenameClientCapabilities.toJson
    val encode_foldingRange: Encoder[structures.FoldingRangeClientCapabilities] = structures.FoldingRangeClientCapabilities.toJson
    val encode_selectionRange: Encoder[structures.SelectionRangeClientCapabilities] = structures.SelectionRangeClientCapabilities.toJson
    val encode_publishDiagnostics: Encoder[structures.PublishDiagnosticsClientCapabilities] = structures.PublishDiagnosticsClientCapabilities.toJson
    val encode_callHierarchy: Encoder[structures.CallHierarchyClientCapabilities] = structures.CallHierarchyClientCapabilities.toJson
    val encode_semanticTokens: Encoder[structures.SemanticTokensClientCapabilities] = structures.SemanticTokensClientCapabilities.toJson
    val encode_linkedEditingRange: Encoder[structures.LinkedEditingRangeClientCapabilities] = structures.LinkedEditingRangeClientCapabilities.toJson
    val encode_moniker: Encoder[structures.MonikerClientCapabilities] = structures.MonikerClientCapabilities.toJson
    val encode_typeHierarchy: Encoder[structures.TypeHierarchyClientCapabilities] = structures.TypeHierarchyClientCapabilities.toJson
    val encode_inlineValue: Encoder[structures.InlineValueClientCapabilities] = structures.InlineValueClientCapabilities.toJson
    val encode_inlayHint: Encoder[structures.InlayHintClientCapabilities] = structures.InlayHintClientCapabilities.toJson
    val encode_diagnostic: Encoder[structures.DiagnosticClientCapabilities] = structures.DiagnosticClientCapabilities.toJson
    Enc.toJsonObject: (enc, a) =>
      a.synchronization.foreach: v =>
        enc.field("synchronization", v, encode_synchronization)
      a.completion.foreach: v =>
        enc.field("completion", v, encode_completion)
      a.hover.foreach: v =>
        enc.field("hover", v, encode_hover)
      a.signatureHelp.foreach: v =>
        enc.field("signatureHelp", v, encode_signatureHelp)
      a.declaration.foreach: v =>
        enc.field("declaration", v, encode_declaration)
      a.definition.foreach: v =>
        enc.field("definition", v, encode_definition)
      a.typeDefinition.foreach: v =>
        enc.field("typeDefinition", v, encode_typeDefinition)
      a.implementation.foreach: v =>
        enc.field("implementation", v, encode_implementation)
      a.references.foreach: v =>
        enc.field("references", v, encode_references)
      a.documentHighlight.foreach: v =>
        enc.field("documentHighlight", v, encode_documentHighlight)
      a.documentSymbol.foreach: v =>
        enc.field("documentSymbol", v, encode_documentSymbol)
      a.codeAction.foreach: v =>
        enc.field("codeAction", v, encode_codeAction)
      a.codeLens.foreach: v =>
        enc.field("codeLens", v, encode_codeLens)
      a.documentLink.foreach: v =>
        enc.field("documentLink", v, encode_documentLink)
      a.colorProvider.foreach: v =>
        enc.field("colorProvider", v, encode_colorProvider)
      a.formatting.foreach: v =>
        enc.field("formatting", v, encode_formatting)
      a.rangeFormatting.foreach: v =>
        enc.field("rangeFormatting", v, encode_rangeFormatting)
      a.onTypeFormatting.foreach: v =>
        enc.field("onTypeFormatting", v, encode_onTypeFormatting)
      a.rename.foreach: v =>
        enc.field("rename", v, encode_rename)
      a.foldingRange.foreach: v =>
        enc.field("foldingRange", v, encode_foldingRange)
      a.selectionRange.foreach: v =>
        enc.field("selectionRange", v, encode_selectionRange)
      a.publishDiagnostics.foreach: v =>
        enc.field("publishDiagnostics", v, encode_publishDiagnostics)
      a.callHierarchy.foreach: v =>
        enc.field("callHierarchy", v, encode_callHierarchy)
      a.semanticTokens.foreach: v =>
        enc.field("semanticTokens", v, encode_semanticTokens)
      a.linkedEditingRange.foreach: v =>
        enc.field("linkedEditingRange", v, encode_linkedEditingRange)
      a.moniker.foreach: v =>
        enc.field("moniker", v, encode_moniker)
      a.typeHierarchy.foreach: v =>
        enc.field("typeHierarchy", v, encode_typeHierarchy)
      a.inlineValue.foreach: v =>
        enc.field("inlineValue", v, encode_inlineValue)
      a.inlayHint.foreach: v =>
        enc.field("inlayHint", v, encode_inlayHint)
      a.diagnostic.foreach: v =>
        enc.field("diagnostic", v, encode_diagnostic)


private[lsp] trait structures_TextDocumentEditCodec:
  import structures.*
  given fromJson: Decoder[TextDocumentEdit] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.OptionalVersionedTextDocumentIdentifier] = structures.OptionalVersionedTextDocumentIdentifier.fromJson
    val decode_edits: Decoder[Vector[(structures.TextEdit | structures.AnnotatedTextEdit)]] = Decoder.decodeVector(Dec.union2[structures.TextEdit, structures.AnnotatedTextEdit](structures.TextEdit.fromJson,structures.AnnotatedTextEdit.fromJson))
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        edits <- dec.get("edits", decode_edits)
      yield TextDocumentEdit(
        textDocument,
        edits,
      )
  given toJson: Encoder[TextDocumentEdit] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.OptionalVersionedTextDocumentIdentifier] = structures.OptionalVersionedTextDocumentIdentifier.toJson
    val encode_edits: Encoder[Vector[(structures.TextEdit | structures.AnnotatedTextEdit)]] = Encoder.encodeVector(Enc.union2[structures.TextEdit, structures.AnnotatedTextEdit](structures.TextEdit.toJson,structures.AnnotatedTextEdit.toJson))
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("edits", a.edits, encode_edits)


private[lsp] trait structures_TextDocumentIdentifierCodec:
  import structures.*
  given fromJson: Decoder[TextDocumentIdentifier] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_uri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    Dec.fromJsonObject: dec =>
      for
        uri <- dec.get("uri", decode_uri)
      yield TextDocumentIdentifier(
        uri,
      )
  given toJson: Encoder[TextDocumentIdentifier] = 
    // cache all encoders for this type when toJson first initialised
    val encode_uri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("uri", a.uri, encode_uri)


private[lsp] trait structures_TextDocumentItemCodec:
  import structures.*
  given fromJson: Decoder[TextDocumentItem] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_uri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    val decode_languageId: Decoder[String] = Decoder.decodeString
    val decode_version: Decoder[Int] = Decoder.decodeInt
    val decode_text: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        uri <- dec.get("uri", decode_uri)
        languageId <- dec.get("languageId", decode_languageId)
        version <- dec.get("version", decode_version)
        text <- dec.get("text", decode_text)
      yield TextDocumentItem(
        uri,
        languageId,
        version,
        text,
      )
  given toJson: Encoder[TextDocumentItem] = 
    // cache all encoders for this type when toJson first initialised
    val encode_uri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    val encode_languageId: Encoder[String] = Encoder.encodeString
    val encode_version: Encoder[Int] = Encoder.encodeInt
    val encode_text: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("uri", a.uri, encode_uri)
      enc.field("languageId", a.languageId, encode_languageId)
      enc.field("version", a.version, encode_version)
      enc.field("text", a.text, encode_text)


private[lsp] trait structures_TextDocumentPositionParamsCodec:
  import structures.*
  given fromJson: Decoder[TextDocumentPositionParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_position: Decoder[structures.Position] = structures.Position.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        position <- dec.get("position", decode_position)
      yield TextDocumentPositionParams(
        textDocument,
        position,
      )
  given toJson: Encoder[TextDocumentPositionParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_position: Encoder[structures.Position] = structures.Position.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("position", a.position, encode_position)


private[lsp] trait structures_TextDocumentRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[TextDocumentRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
      yield TextDocumentRegistrationOptions(
        documentSelector,
      )
  given toJson: Encoder[TextDocumentRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)


private[lsp] trait structures_TextDocumentSaveRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[TextDocumentSaveRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_includeText: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        includeText <- dec.getOpt("includeText", decode_includeText)
      yield TextDocumentSaveRegistrationOptions(
        documentSelector,
        includeText,
      )
  given toJson: Encoder[TextDocumentSaveRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_includeText: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.includeText.foreach: v =>
        enc.field("includeText", v, encode_includeText)


private[lsp] trait structures_TextDocumentSyncClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[TextDocumentSyncClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_willSave: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_willSaveWaitUntil: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_didSave: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        willSave <- dec.getOpt("willSave", decode_willSave)
        willSaveWaitUntil <- dec.getOpt("willSaveWaitUntil", decode_willSaveWaitUntil)
        didSave <- dec.getOpt("didSave", decode_didSave)
      yield TextDocumentSyncClientCapabilities(
        dynamicRegistration,
        willSave,
        willSaveWaitUntil,
        didSave,
      )
  given toJson: Encoder[TextDocumentSyncClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_willSave: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_willSaveWaitUntil: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_didSave: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      a.willSave.foreach: v =>
        enc.field("willSave", v, encode_willSave)
      a.willSaveWaitUntil.foreach: v =>
        enc.field("willSaveWaitUntil", v, encode_willSaveWaitUntil)
      a.didSave.foreach: v =>
        enc.field("didSave", v, encode_didSave)


private[lsp] trait structures_TextDocumentSyncOptionsCodec:
  import structures.*
  given fromJson: Decoder[TextDocumentSyncOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_openClose: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_change: Decoder[enumerations.TextDocumentSyncKind] = enumerations.TextDocumentSyncKind.fromJson
    val decode_willSave: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_willSaveWaitUntil: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_save: Decoder[(Boolean | structures.SaveOptions)] = Dec.union2[Boolean, structures.SaveOptions](Decoder.decodeBoolean,structures.SaveOptions.fromJson)
    Dec.fromJsonObject: dec =>
      for
        openClose <- dec.getOpt("openClose", decode_openClose)
        change <- dec.getOpt("change", decode_change)
        willSave <- dec.getOpt("willSave", decode_willSave)
        willSaveWaitUntil <- dec.getOpt("willSaveWaitUntil", decode_willSaveWaitUntil)
        save <- dec.getOpt("save", decode_save)
      yield TextDocumentSyncOptions(
        openClose,
        change,
        willSave,
        willSaveWaitUntil,
        save,
      )
  given toJson: Encoder[TextDocumentSyncOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_openClose: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_change: Encoder[enumerations.TextDocumentSyncKind] = enumerations.TextDocumentSyncKind.toJson
    val encode_willSave: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_willSaveWaitUntil: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_save: Encoder[(Boolean | structures.SaveOptions)] = Enc.union2[Boolean, structures.SaveOptions](Encoder.encodeBoolean,structures.SaveOptions.toJson)
    Enc.toJsonObject: (enc, a) =>
      a.openClose.foreach: v =>
        enc.field("openClose", v, encode_openClose)
      a.change.foreach: v =>
        enc.field("change", v, encode_change)
      a.willSave.foreach: v =>
        enc.field("willSave", v, encode_willSave)
      a.willSaveWaitUntil.foreach: v =>
        enc.field("willSaveWaitUntil", v, encode_willSaveWaitUntil)
      a.save.foreach: v =>
        enc.field("save", v, encode_save)


private[lsp] trait structures_TextEditCodec:
  import structures.*
  given fromJson: Decoder[TextEdit] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_newText: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        range <- dec.get("range", decode_range)
        newText <- dec.get("newText", decode_newText)
      yield TextEdit(
        range,
        newText,
      )
  given toJson: Encoder[TextEdit] = 
    // cache all encoders for this type when toJson first initialised
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_newText: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("range", a.range, encode_range)
      enc.field("newText", a.newText, encode_newText)


private[lsp] trait structures_TypeDefinitionClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[TypeDefinitionClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_linkSupport: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        linkSupport <- dec.getOpt("linkSupport", decode_linkSupport)
      yield TypeDefinitionClientCapabilities(
        dynamicRegistration,
        linkSupport,
      )
  given toJson: Encoder[TypeDefinitionClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_linkSupport: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      a.linkSupport.foreach: v =>
        enc.field("linkSupport", v, encode_linkSupport)


private[lsp] trait structures_TypeDefinitionOptionsCodec:
  import structures.*
  given fromJson: Decoder[TypeDefinitionOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield TypeDefinitionOptions(
        workDoneProgress,
      )
  given toJson: Encoder[TypeDefinitionOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_TypeDefinitionParamsCodec:
  import structures.*
  given fromJson: Decoder[TypeDefinitionParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_position: Decoder[structures.Position] = structures.Position.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        position <- dec.get("position", decode_position)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield TypeDefinitionParams(
        textDocument,
        position,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[TypeDefinitionParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_position: Encoder[structures.Position] = structures.Position.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("position", a.position, encode_position)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_TypeDefinitionRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[TypeDefinitionRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_id: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        id <- dec.getOpt("id", decode_id)
      yield TypeDefinitionRegistrationOptions(
        documentSelector,
        id,
      )
  given toJson: Encoder[TypeDefinitionRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_id: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.id.foreach: v =>
        enc.field("id", v, encode_id)


private[lsp] trait structures_TypeHierarchyClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[TypeHierarchyClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
      yield TypeHierarchyClientCapabilities(
        dynamicRegistration,
      )
  given toJson: Encoder[TypeHierarchyClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)


private[lsp] trait structures_TypeHierarchyItemCodec:
  import structures.*
  given fromJson: Decoder[TypeHierarchyItem] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_name: Decoder[String] = Decoder.decodeString
    val decode_kind: Decoder[enumerations.SymbolKind] = enumerations.SymbolKind.fromJson
    val decode_tags: Decoder[Vector[enumerations.SymbolTag]] = Decoder.decodeVector(enumerations.SymbolTag.fromJson)
    val decode_detail: Decoder[String] = Decoder.decodeString
    val decode_uri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_selectionRange: Decoder[structures.Range] = structures.Range.fromJson
    val decode_data: Decoder[io.circe.Json] = Decoder.decodeJson
    Dec.fromJsonObject: dec =>
      for
        name <- dec.get("name", decode_name)
        kind <- dec.get("kind", decode_kind)
        tags <- dec.getOpt("tags", decode_tags)
        detail <- dec.getOpt("detail", decode_detail)
        uri <- dec.get("uri", decode_uri)
        range <- dec.get("range", decode_range)
        selectionRange <- dec.get("selectionRange", decode_selectionRange)
        data <- dec.getOpt("data", decode_data)
      yield TypeHierarchyItem(
        name,
        kind,
        tags,
        detail,
        uri,
        range,
        selectionRange,
        data,
      )
  given toJson: Encoder[TypeHierarchyItem] = 
    // cache all encoders for this type when toJson first initialised
    val encode_name: Encoder[String] = Encoder.encodeString
    val encode_kind: Encoder[enumerations.SymbolKind] = enumerations.SymbolKind.toJson
    val encode_tags: Encoder[Vector[enumerations.SymbolTag]] = Encoder.encodeVector(enumerations.SymbolTag.toJson)
    val encode_detail: Encoder[String] = Encoder.encodeString
    val encode_uri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_selectionRange: Encoder[structures.Range] = structures.Range.toJson
    val encode_data: Encoder[io.circe.Json] = Encoder.encodeJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("name", a.name, encode_name)
      enc.field("kind", a.kind, encode_kind)
      a.tags.foreach: v =>
        enc.field("tags", v, encode_tags)
      a.detail.foreach: v =>
        enc.field("detail", v, encode_detail)
      enc.field("uri", a.uri, encode_uri)
      enc.field("range", a.range, encode_range)
      enc.field("selectionRange", a.selectionRange, encode_selectionRange)
      a.data.foreach: v =>
        enc.field("data", v, encode_data)


private[lsp] trait structures_TypeHierarchyOptionsCodec:
  import structures.*
  given fromJson: Decoder[TypeHierarchyOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield TypeHierarchyOptions(
        workDoneProgress,
      )
  given toJson: Encoder[TypeHierarchyOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_TypeHierarchyPrepareParamsCodec:
  import structures.*
  given fromJson: Decoder[TypeHierarchyPrepareParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_position: Decoder[structures.Position] = structures.Position.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        position <- dec.get("position", decode_position)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
      yield TypeHierarchyPrepareParams(
        textDocument,
        position,
        workDoneToken,
      )
  given toJson: Encoder[TypeHierarchyPrepareParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_position: Encoder[structures.Position] = structures.Position.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("position", a.position, encode_position)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)


private[lsp] trait structures_TypeHierarchyRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[TypeHierarchyRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentSelector: Decoder[aliases.DocumentSelector] = aliases.DocumentSelector.fromJson
    val decode_id: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        documentSelector <- dec.getOpt("documentSelector", decode_documentSelector)
        id <- dec.getOpt("id", decode_id)
      yield TypeHierarchyRegistrationOptions(
        documentSelector,
        id,
      )
  given toJson: Encoder[TypeHierarchyRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentSelector: Encoder[aliases.DocumentSelector] = aliases.DocumentSelector.toJson
    val encode_id: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.documentSelector.foreach: v =>
        enc.field("documentSelector", v, encode_documentSelector)
      a.id.foreach: v =>
        enc.field("id", v, encode_id)


private[lsp] trait structures_TypeHierarchySubtypesParamsCodec:
  import structures.*
  given fromJson: Decoder[TypeHierarchySubtypesParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_item: Decoder[structures.TypeHierarchyItem] = structures.TypeHierarchyItem.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        item <- dec.get("item", decode_item)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield TypeHierarchySubtypesParams(
        item,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[TypeHierarchySubtypesParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_item: Encoder[structures.TypeHierarchyItem] = structures.TypeHierarchyItem.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("item", a.item, encode_item)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_TypeHierarchySupertypesParamsCodec:
  import structures.*
  given fromJson: Decoder[TypeHierarchySupertypesParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_item: Decoder[structures.TypeHierarchyItem] = structures.TypeHierarchyItem.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        item <- dec.get("item", decode_item)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield TypeHierarchySupertypesParams(
        item,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[TypeHierarchySupertypesParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_item: Encoder[structures.TypeHierarchyItem] = structures.TypeHierarchyItem.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("item", a.item, encode_item)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_UnchangedDocumentDiagnosticReportCodec:
  import structures.*
  given fromJson: Decoder[UnchangedDocumentDiagnosticReport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_kind: Decoder["unchanged"] = Decoder.decodeLiteralString["unchanged"]
    val decode_resultId: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        kind <- dec.get("kind", decode_kind)
        resultId <- dec.get("resultId", decode_resultId)
      yield UnchangedDocumentDiagnosticReport(
        kind,
        resultId,
      )
  given toJson: Encoder[UnchangedDocumentDiagnosticReport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_kind: Encoder["unchanged"] = Encoder.encodeLiteralString["unchanged"]
    val encode_resultId: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("kind", a.kind, encode_kind)
      enc.field("resultId", a.resultId, encode_resultId)


private[lsp] trait structures_UnregistrationCodec:
  import structures.*
  given fromJson: Decoder[Unregistration] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_id: Decoder[String] = Decoder.decodeString
    val decode_method: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        id <- dec.get("id", decode_id)
        method <- dec.get("method", decode_method)
      yield Unregistration(
        id,
        method,
      )
  given toJson: Encoder[Unregistration] = 
    // cache all encoders for this type when toJson first initialised
    val encode_id: Encoder[String] = Encoder.encodeString
    val encode_method: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("id", a.id, encode_id)
      enc.field("method", a.method, encode_method)


private[lsp] trait structures_UnregistrationParamsCodec:
  import structures.*
  given fromJson: Decoder[UnregistrationParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_unregisterations: Decoder[Vector[structures.Unregistration]] = Decoder.decodeVector(structures.Unregistration.fromJson)
    Dec.fromJsonObject: dec =>
      for
        unregisterations <- dec.get("unregisterations", decode_unregisterations)
      yield UnregistrationParams(
        unregisterations,
      )
  given toJson: Encoder[UnregistrationParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_unregisterations: Encoder[Vector[structures.Unregistration]] = Encoder.encodeVector(structures.Unregistration.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("unregisterations", a.unregisterations, encode_unregisterations)


private[lsp] trait structures_VersionedNotebookDocumentIdentifierCodec:
  import structures.*
  given fromJson: Decoder[VersionedNotebookDocumentIdentifier] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_version: Decoder[Int] = Decoder.decodeInt
    val decode_uri: Decoder[runtime.Uri] = Uri.fromJson
    Dec.fromJsonObject: dec =>
      for
        version <- dec.get("version", decode_version)
        uri <- dec.get("uri", decode_uri)
      yield VersionedNotebookDocumentIdentifier(
        version,
        uri,
      )
  given toJson: Encoder[VersionedNotebookDocumentIdentifier] = 
    // cache all encoders for this type when toJson first initialised
    val encode_version: Encoder[Int] = Encoder.encodeInt
    val encode_uri: Encoder[runtime.Uri] = Uri.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("version", a.version, encode_version)
      enc.field("uri", a.uri, encode_uri)


private[lsp] trait structures_VersionedTextDocumentIdentifierCodec:
  import structures.*
  given fromJson: Decoder[VersionedTextDocumentIdentifier] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_version: Decoder[Int] = Decoder.decodeInt
    val decode_uri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    Dec.fromJsonObject: dec =>
      for
        version <- dec.get("version", decode_version)
        uri <- dec.get("uri", decode_uri)
      yield VersionedTextDocumentIdentifier(
        version,
        uri,
      )
  given toJson: Encoder[VersionedTextDocumentIdentifier] = 
    // cache all encoders for this type when toJson first initialised
    val encode_version: Encoder[Int] = Encoder.encodeInt
    val encode_uri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("version", a.version, encode_version)
      enc.field("uri", a.uri, encode_uri)


private[lsp] trait structures_WillSaveTextDocumentParamsCodec:
  import structures.*
  given fromJson: Decoder[WillSaveTextDocumentParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_textDocument: Decoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.fromJson
    val decode_reason: Decoder[enumerations.TextDocumentSaveReason] = enumerations.TextDocumentSaveReason.fromJson
    Dec.fromJsonObject: dec =>
      for
        textDocument <- dec.get("textDocument", decode_textDocument)
        reason <- dec.get("reason", decode_reason)
      yield WillSaveTextDocumentParams(
        textDocument,
        reason,
      )
  given toJson: Encoder[WillSaveTextDocumentParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_textDocument: Encoder[structures.TextDocumentIdentifier] = structures.TextDocumentIdentifier.toJson
    val encode_reason: Encoder[enumerations.TextDocumentSaveReason] = enumerations.TextDocumentSaveReason.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("textDocument", a.textDocument, encode_textDocument)
      enc.field("reason", a.reason, encode_reason)


private[lsp] trait structures_WindowClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[WindowClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_showMessage: Decoder[structures.ShowMessageRequestClientCapabilities] = structures.ShowMessageRequestClientCapabilities.fromJson
    val decode_showDocument: Decoder[structures.ShowDocumentClientCapabilities] = structures.ShowDocumentClientCapabilities.fromJson
    Dec.fromJsonObject: dec =>
      for
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
        showMessage <- dec.getOpt("showMessage", decode_showMessage)
        showDocument <- dec.getOpt("showDocument", decode_showDocument)
      yield WindowClientCapabilities(
        workDoneProgress,
        showMessage,
        showDocument,
      )
  given toJson: Encoder[WindowClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_showMessage: Encoder[structures.ShowMessageRequestClientCapabilities] = structures.ShowMessageRequestClientCapabilities.toJson
    val encode_showDocument: Encoder[structures.ShowDocumentClientCapabilities] = structures.ShowDocumentClientCapabilities.toJson
    Enc.toJsonObject: (enc, a) =>
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)
      a.showMessage.foreach: v =>
        enc.field("showMessage", v, encode_showMessage)
      a.showDocument.foreach: v =>
        enc.field("showDocument", v, encode_showDocument)


private[lsp] trait structures_WorkDoneProgressBeginCodec:
  import structures.*
  given fromJson: Decoder[WorkDoneProgressBegin] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_kind: Decoder["begin"] = Decoder.decodeLiteralString["begin"]
    val decode_title: Decoder[String] = Decoder.decodeString
    val decode_cancellable: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_message: Decoder[String] = Decoder.decodeString
    val decode_percentage: Decoder[runtime.uinteger] = uinteger.fromJson
    Dec.fromJsonObject: dec =>
      for
        kind <- dec.get("kind", decode_kind)
        title <- dec.get("title", decode_title)
        cancellable <- dec.getOpt("cancellable", decode_cancellable)
        message <- dec.getOpt("message", decode_message)
        percentage <- dec.getOpt("percentage", decode_percentage)
      yield WorkDoneProgressBegin(
        kind,
        title,
        cancellable,
        message,
        percentage,
      )
  given toJson: Encoder[WorkDoneProgressBegin] = 
    // cache all encoders for this type when toJson first initialised
    val encode_kind: Encoder["begin"] = Encoder.encodeLiteralString["begin"]
    val encode_title: Encoder[String] = Encoder.encodeString
    val encode_cancellable: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_message: Encoder[String] = Encoder.encodeString
    val encode_percentage: Encoder[runtime.uinteger] = uinteger.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("kind", a.kind, encode_kind)
      enc.field("title", a.title, encode_title)
      a.cancellable.foreach: v =>
        enc.field("cancellable", v, encode_cancellable)
      a.message.foreach: v =>
        enc.field("message", v, encode_message)
      a.percentage.foreach: v =>
        enc.field("percentage", v, encode_percentage)


private[lsp] trait structures_WorkDoneProgressCancelParamsCodec:
  import structures.*
  given fromJson: Decoder[WorkDoneProgressCancelParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_token: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        token <- dec.get("token", decode_token)
      yield WorkDoneProgressCancelParams(
        token,
      )
  given toJson: Encoder[WorkDoneProgressCancelParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_token: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("token", a.token, encode_token)


private[lsp] trait structures_WorkDoneProgressCreateParamsCodec:
  import structures.*
  given fromJson: Decoder[WorkDoneProgressCreateParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_token: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        token <- dec.get("token", decode_token)
      yield WorkDoneProgressCreateParams(
        token,
      )
  given toJson: Encoder[WorkDoneProgressCreateParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_token: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("token", a.token, encode_token)


private[lsp] trait structures_WorkDoneProgressEndCodec:
  import structures.*
  given fromJson: Decoder[WorkDoneProgressEnd] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_kind: Decoder["end"] = Decoder.decodeLiteralString["end"]
    val decode_message: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        kind <- dec.get("kind", decode_kind)
        message <- dec.getOpt("message", decode_message)
      yield WorkDoneProgressEnd(
        kind,
        message,
      )
  given toJson: Encoder[WorkDoneProgressEnd] = 
    // cache all encoders for this type when toJson first initialised
    val encode_kind: Encoder["end"] = Encoder.encodeLiteralString["end"]
    val encode_message: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("kind", a.kind, encode_kind)
      a.message.foreach: v =>
        enc.field("message", v, encode_message)


private[lsp] trait structures_WorkDoneProgressOptionsCodec:
  import structures.*
  given fromJson: Decoder[WorkDoneProgressOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield WorkDoneProgressOptions(
        workDoneProgress,
      )
  given toJson: Encoder[WorkDoneProgressOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_WorkDoneProgressParamsCodec:
  import structures.*
  given fromJson: Decoder[WorkDoneProgressParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
      yield WorkDoneProgressParams(
        workDoneToken,
      )
  given toJson: Encoder[WorkDoneProgressParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)


private[lsp] trait structures_WorkDoneProgressReportCodec:
  import structures.*
  given fromJson: Decoder[WorkDoneProgressReport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_kind: Decoder["report"] = Decoder.decodeLiteralString["report"]
    val decode_cancellable: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_message: Decoder[String] = Decoder.decodeString
    val decode_percentage: Decoder[runtime.uinteger] = uinteger.fromJson
    Dec.fromJsonObject: dec =>
      for
        kind <- dec.get("kind", decode_kind)
        cancellable <- dec.getOpt("cancellable", decode_cancellable)
        message <- dec.getOpt("message", decode_message)
        percentage <- dec.getOpt("percentage", decode_percentage)
      yield WorkDoneProgressReport(
        kind,
        cancellable,
        message,
        percentage,
      )
  given toJson: Encoder[WorkDoneProgressReport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_kind: Encoder["report"] = Encoder.encodeLiteralString["report"]
    val encode_cancellable: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_message: Encoder[String] = Encoder.encodeString
    val encode_percentage: Encoder[runtime.uinteger] = uinteger.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("kind", a.kind, encode_kind)
      a.cancellable.foreach: v =>
        enc.field("cancellable", v, encode_cancellable)
      a.message.foreach: v =>
        enc.field("message", v, encode_message)
      a.percentage.foreach: v =>
        enc.field("percentage", v, encode_percentage)


private[lsp] trait structures_WorkspaceClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[WorkspaceClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_applyEdit: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_workspaceEdit: Decoder[structures.WorkspaceEditClientCapabilities] = structures.WorkspaceEditClientCapabilities.fromJson
    val decode_didChangeConfiguration: Decoder[structures.DidChangeConfigurationClientCapabilities] = structures.DidChangeConfigurationClientCapabilities.fromJson
    val decode_didChangeWatchedFiles: Decoder[structures.DidChangeWatchedFilesClientCapabilities] = structures.DidChangeWatchedFilesClientCapabilities.fromJson
    val decode_symbol: Decoder[structures.WorkspaceSymbolClientCapabilities] = structures.WorkspaceSymbolClientCapabilities.fromJson
    val decode_executeCommand: Decoder[structures.ExecuteCommandClientCapabilities] = structures.ExecuteCommandClientCapabilities.fromJson
    val decode_workspaceFolders: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_configuration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_semanticTokens: Decoder[structures.SemanticTokensWorkspaceClientCapabilities] = structures.SemanticTokensWorkspaceClientCapabilities.fromJson
    val decode_codeLens: Decoder[structures.CodeLensWorkspaceClientCapabilities] = structures.CodeLensWorkspaceClientCapabilities.fromJson
    val decode_fileOperations: Decoder[structures.FileOperationClientCapabilities] = structures.FileOperationClientCapabilities.fromJson
    val decode_inlineValue: Decoder[structures.InlineValueWorkspaceClientCapabilities] = structures.InlineValueWorkspaceClientCapabilities.fromJson
    val decode_inlayHint: Decoder[structures.InlayHintWorkspaceClientCapabilities] = structures.InlayHintWorkspaceClientCapabilities.fromJson
    val decode_diagnostics: Decoder[structures.DiagnosticWorkspaceClientCapabilities] = structures.DiagnosticWorkspaceClientCapabilities.fromJson
    Dec.fromJsonObject: dec =>
      for
        applyEdit <- dec.getOpt("applyEdit", decode_applyEdit)
        workspaceEdit <- dec.getOpt("workspaceEdit", decode_workspaceEdit)
        didChangeConfiguration <- dec.getOpt("didChangeConfiguration", decode_didChangeConfiguration)
        didChangeWatchedFiles <- dec.getOpt("didChangeWatchedFiles", decode_didChangeWatchedFiles)
        symbol <- dec.getOpt("symbol", decode_symbol)
        executeCommand <- dec.getOpt("executeCommand", decode_executeCommand)
        workspaceFolders <- dec.getOpt("workspaceFolders", decode_workspaceFolders)
        configuration <- dec.getOpt("configuration", decode_configuration)
        semanticTokens <- dec.getOpt("semanticTokens", decode_semanticTokens)
        codeLens <- dec.getOpt("codeLens", decode_codeLens)
        fileOperations <- dec.getOpt("fileOperations", decode_fileOperations)
        inlineValue <- dec.getOpt("inlineValue", decode_inlineValue)
        inlayHint <- dec.getOpt("inlayHint", decode_inlayHint)
        diagnostics <- dec.getOpt("diagnostics", decode_diagnostics)
      yield WorkspaceClientCapabilities(
        applyEdit,
        workspaceEdit,
        didChangeConfiguration,
        didChangeWatchedFiles,
        symbol,
        executeCommand,
        workspaceFolders,
        configuration,
        semanticTokens,
        codeLens,
        fileOperations,
        inlineValue,
        inlayHint,
        diagnostics,
      )
  given toJson: Encoder[WorkspaceClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_applyEdit: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_workspaceEdit: Encoder[structures.WorkspaceEditClientCapabilities] = structures.WorkspaceEditClientCapabilities.toJson
    val encode_didChangeConfiguration: Encoder[structures.DidChangeConfigurationClientCapabilities] = structures.DidChangeConfigurationClientCapabilities.toJson
    val encode_didChangeWatchedFiles: Encoder[structures.DidChangeWatchedFilesClientCapabilities] = structures.DidChangeWatchedFilesClientCapabilities.toJson
    val encode_symbol: Encoder[structures.WorkspaceSymbolClientCapabilities] = structures.WorkspaceSymbolClientCapabilities.toJson
    val encode_executeCommand: Encoder[structures.ExecuteCommandClientCapabilities] = structures.ExecuteCommandClientCapabilities.toJson
    val encode_workspaceFolders: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_configuration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_semanticTokens: Encoder[structures.SemanticTokensWorkspaceClientCapabilities] = structures.SemanticTokensWorkspaceClientCapabilities.toJson
    val encode_codeLens: Encoder[structures.CodeLensWorkspaceClientCapabilities] = structures.CodeLensWorkspaceClientCapabilities.toJson
    val encode_fileOperations: Encoder[structures.FileOperationClientCapabilities] = structures.FileOperationClientCapabilities.toJson
    val encode_inlineValue: Encoder[structures.InlineValueWorkspaceClientCapabilities] = structures.InlineValueWorkspaceClientCapabilities.toJson
    val encode_inlayHint: Encoder[structures.InlayHintWorkspaceClientCapabilities] = structures.InlayHintWorkspaceClientCapabilities.toJson
    val encode_diagnostics: Encoder[structures.DiagnosticWorkspaceClientCapabilities] = structures.DiagnosticWorkspaceClientCapabilities.toJson
    Enc.toJsonObject: (enc, a) =>
      a.applyEdit.foreach: v =>
        enc.field("applyEdit", v, encode_applyEdit)
      a.workspaceEdit.foreach: v =>
        enc.field("workspaceEdit", v, encode_workspaceEdit)
      a.didChangeConfiguration.foreach: v =>
        enc.field("didChangeConfiguration", v, encode_didChangeConfiguration)
      a.didChangeWatchedFiles.foreach: v =>
        enc.field("didChangeWatchedFiles", v, encode_didChangeWatchedFiles)
      a.symbol.foreach: v =>
        enc.field("symbol", v, encode_symbol)
      a.executeCommand.foreach: v =>
        enc.field("executeCommand", v, encode_executeCommand)
      a.workspaceFolders.foreach: v =>
        enc.field("workspaceFolders", v, encode_workspaceFolders)
      a.configuration.foreach: v =>
        enc.field("configuration", v, encode_configuration)
      a.semanticTokens.foreach: v =>
        enc.field("semanticTokens", v, encode_semanticTokens)
      a.codeLens.foreach: v =>
        enc.field("codeLens", v, encode_codeLens)
      a.fileOperations.foreach: v =>
        enc.field("fileOperations", v, encode_fileOperations)
      a.inlineValue.foreach: v =>
        enc.field("inlineValue", v, encode_inlineValue)
      a.inlayHint.foreach: v =>
        enc.field("inlayHint", v, encode_inlayHint)
      a.diagnostics.foreach: v =>
        enc.field("diagnostics", v, encode_diagnostics)


private[lsp] trait structures_WorkspaceDiagnosticParamsCodec:
  import structures.*
  given fromJson: Decoder[WorkspaceDiagnosticParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_identifier: Decoder[String] = Decoder.decodeString
    val decode_previousResultIds: Decoder[Vector[structures.PreviousResultId]] = Decoder.decodeVector(structures.PreviousResultId.fromJson)
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        identifier <- dec.getOpt("identifier", decode_identifier)
        previousResultIds <- dec.get("previousResultIds", decode_previousResultIds)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield WorkspaceDiagnosticParams(
        identifier,
        previousResultIds,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[WorkspaceDiagnosticParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_identifier: Encoder[String] = Encoder.encodeString
    val encode_previousResultIds: Encoder[Vector[structures.PreviousResultId]] = Encoder.encodeVector(structures.PreviousResultId.toJson)
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      a.identifier.foreach: v =>
        enc.field("identifier", v, encode_identifier)
      enc.field("previousResultIds", a.previousResultIds, encode_previousResultIds)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_WorkspaceDiagnosticReportCodec:
  import structures.*
  given fromJson: Decoder[WorkspaceDiagnosticReport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_items: Decoder[Vector[aliases.WorkspaceDocumentDiagnosticReport]] = Decoder.decodeVector(aliases.WorkspaceDocumentDiagnosticReport.fromJson)
    Dec.fromJsonObject: dec =>
      for
        items <- dec.get("items", decode_items)
      yield WorkspaceDiagnosticReport(
        items,
      )
  given toJson: Encoder[WorkspaceDiagnosticReport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_items: Encoder[Vector[aliases.WorkspaceDocumentDiagnosticReport]] = Encoder.encodeVector(aliases.WorkspaceDocumentDiagnosticReport.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("items", a.items, encode_items)


private[lsp] trait structures_WorkspaceDiagnosticReportPartialResultCodec:
  import structures.*
  given fromJson: Decoder[WorkspaceDiagnosticReportPartialResult] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_items: Decoder[Vector[aliases.WorkspaceDocumentDiagnosticReport]] = Decoder.decodeVector(aliases.WorkspaceDocumentDiagnosticReport.fromJson)
    Dec.fromJsonObject: dec =>
      for
        items <- dec.get("items", decode_items)
      yield WorkspaceDiagnosticReportPartialResult(
        items,
      )
  given toJson: Encoder[WorkspaceDiagnosticReportPartialResult] = 
    // cache all encoders for this type when toJson first initialised
    val encode_items: Encoder[Vector[aliases.WorkspaceDocumentDiagnosticReport]] = Encoder.encodeVector(aliases.WorkspaceDocumentDiagnosticReport.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("items", a.items, encode_items)


private[lsp] trait structures_WorkspaceEditCodec:
  import structures.*
  given fromJson: Decoder[WorkspaceEdit] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_changes: Decoder[Map[runtime.DocumentUri, Vector[structures.TextEdit]]] = Decoder.decodeMap(KeyDecoder.decodeKeyString.map(runtime.DocumentUri.apply), Decoder.decodeVector(structures.TextEdit.fromJson))
    val decode_documentChanges: Decoder[Vector[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)]] = Decoder.decodeVector(Dec.union4[structures.TextDocumentEdit, structures.CreateFile, structures.RenameFile, structures.DeleteFile](structures.TextDocumentEdit.fromJson,structures.CreateFile.fromJson, structures.RenameFile.fromJson, structures.DeleteFile.fromJson))
    val decode_changeAnnotations: Decoder[Map[aliases.ChangeAnnotationIdentifier, structures.ChangeAnnotation]] = Decoder.decodeMap(KeyDecoder.decodeKeyString.map(aliases.ChangeAnnotationIdentifier.apply), structures.ChangeAnnotation.fromJson)
    Dec.fromJsonObject: dec =>
      for
        changes <- dec.getOpt("changes", decode_changes)
        documentChanges <- dec.getOpt("documentChanges", decode_documentChanges)
        changeAnnotations <- dec.getOpt("changeAnnotations", decode_changeAnnotations)
      yield WorkspaceEdit(
        changes,
        documentChanges,
        changeAnnotations,
      )
  given toJson: Encoder[WorkspaceEdit] = 
    // cache all encoders for this type when toJson first initialised
    val encode_changes: Encoder[Map[runtime.DocumentUri, Vector[structures.TextEdit]]] = Encoder.encodeMap(KeyEncoder.encodeKeyString.contramap(_.value), Encoder.encodeVector(structures.TextEdit.toJson))
    val encode_documentChanges: Encoder[Vector[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)]] = Encoder.encodeVector(Enc.union4[structures.TextDocumentEdit, structures.CreateFile, structures.RenameFile, structures.DeleteFile](structures.TextDocumentEdit.toJson,structures.CreateFile.toJson, structures.RenameFile.toJson, structures.DeleteFile.toJson))
    val encode_changeAnnotations: Encoder[Map[aliases.ChangeAnnotationIdentifier, structures.ChangeAnnotation]] = Encoder.encodeMap(KeyEncoder.encodeKeyString.contramap(_.value), structures.ChangeAnnotation.toJson)
    Enc.toJsonObject: (enc, a) =>
      a.changes.foreach: v =>
        enc.field("changes", v, encode_changes)
      a.documentChanges.foreach: v =>
        enc.field("documentChanges", v, encode_documentChanges)
      a.changeAnnotations.foreach: v =>
        enc.field("changeAnnotations", v, encode_changeAnnotations)


private[lsp] trait structures_WorkspaceEditClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[WorkspaceEditClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_documentChanges: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_resourceOperations: Decoder[Vector[enumerations.ResourceOperationKind]] = Decoder.decodeVector(enumerations.ResourceOperationKind.fromJson)
    val decode_failureHandling: Decoder[enumerations.FailureHandlingKind] = enumerations.FailureHandlingKind.fromJson
    val decode_normalizesLineEndings: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_changeAnnotationSupport: Decoder[WorkspaceEditClientCapabilities.ChangeAnnotationSupport] = WorkspaceEditClientCapabilities.ChangeAnnotationSupport.fromJson
    Dec.fromJsonObject: dec =>
      for
        documentChanges <- dec.getOpt("documentChanges", decode_documentChanges)
        resourceOperations <- dec.getOpt("resourceOperations", decode_resourceOperations)
        failureHandling <- dec.getOpt("failureHandling", decode_failureHandling)
        normalizesLineEndings <- dec.getOpt("normalizesLineEndings", decode_normalizesLineEndings)
        changeAnnotationSupport <- dec.getOpt("changeAnnotationSupport", decode_changeAnnotationSupport)
      yield WorkspaceEditClientCapabilities(
        documentChanges,
        resourceOperations,
        failureHandling,
        normalizesLineEndings,
        changeAnnotationSupport,
      )
  given toJson: Encoder[WorkspaceEditClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_documentChanges: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_resourceOperations: Encoder[Vector[enumerations.ResourceOperationKind]] = Encoder.encodeVector(enumerations.ResourceOperationKind.toJson)
    val encode_failureHandling: Encoder[enumerations.FailureHandlingKind] = enumerations.FailureHandlingKind.toJson
    val encode_normalizesLineEndings: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_changeAnnotationSupport: Encoder[WorkspaceEditClientCapabilities.ChangeAnnotationSupport] = WorkspaceEditClientCapabilities.ChangeAnnotationSupport.toJson
    Enc.toJsonObject: (enc, a) =>
      a.documentChanges.foreach: v =>
        enc.field("documentChanges", v, encode_documentChanges)
      a.resourceOperations.foreach: v =>
        enc.field("resourceOperations", v, encode_resourceOperations)
      a.failureHandling.foreach: v =>
        enc.field("failureHandling", v, encode_failureHandling)
      a.normalizesLineEndings.foreach: v =>
        enc.field("normalizesLineEndings", v, encode_normalizesLineEndings)
      a.changeAnnotationSupport.foreach: v =>
        enc.field("changeAnnotationSupport", v, encode_changeAnnotationSupport)


private[lsp] trait structures_WorkspaceEditClientCapabilities_ChangeAnnotationSupportCodec:
  import structures.WorkspaceEditClientCapabilities.*
  given fromJson: Decoder[ChangeAnnotationSupport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_groupsOnLabel: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        groupsOnLabel <- dec.getOpt("groupsOnLabel", decode_groupsOnLabel)
      yield ChangeAnnotationSupport(
        groupsOnLabel,
      )
  given toJson: Encoder[ChangeAnnotationSupport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_groupsOnLabel: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.groupsOnLabel.foreach: v =>
        enc.field("groupsOnLabel", v, encode_groupsOnLabel)


private[lsp] trait structures_WorkspaceFolderCodec:
  import structures.*
  given fromJson: Decoder[WorkspaceFolder] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_uri: Decoder[runtime.Uri] = Uri.fromJson
    val decode_name: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        uri <- dec.get("uri", decode_uri)
        name <- dec.get("name", decode_name)
      yield WorkspaceFolder(
        uri,
        name,
      )
  given toJson: Encoder[WorkspaceFolder] = 
    // cache all encoders for this type when toJson first initialised
    val encode_uri: Encoder[runtime.Uri] = Uri.toJson
    val encode_name: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("uri", a.uri, encode_uri)
      enc.field("name", a.name, encode_name)


private[lsp] trait structures_WorkspaceFoldersChangeEventCodec:
  import structures.*
  given fromJson: Decoder[WorkspaceFoldersChangeEvent] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_added: Decoder[Vector[structures.WorkspaceFolder]] = Decoder.decodeVector(structures.WorkspaceFolder.fromJson)
    val decode_removed: Decoder[Vector[structures.WorkspaceFolder]] = Decoder.decodeVector(structures.WorkspaceFolder.fromJson)
    Dec.fromJsonObject: dec =>
      for
        added <- dec.get("added", decode_added)
        removed <- dec.get("removed", decode_removed)
      yield WorkspaceFoldersChangeEvent(
        added,
        removed,
      )
  given toJson: Encoder[WorkspaceFoldersChangeEvent] = 
    // cache all encoders for this type when toJson first initialised
    val encode_added: Encoder[Vector[structures.WorkspaceFolder]] = Encoder.encodeVector(structures.WorkspaceFolder.toJson)
    val encode_removed: Encoder[Vector[structures.WorkspaceFolder]] = Encoder.encodeVector(structures.WorkspaceFolder.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("added", a.added, encode_added)
      enc.field("removed", a.removed, encode_removed)


private[lsp] trait structures_WorkspaceFoldersInitializeParamsCodec:
  import structures.*
  given fromJson: Decoder[WorkspaceFoldersInitializeParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_workspaceFolders: Decoder[Vector[structures.WorkspaceFolder]] = Decoder.decodeVector(structures.WorkspaceFolder.fromJson)
    Dec.fromJsonObject: dec =>
      for
        workspaceFolders <- dec.getOpt("workspaceFolders", decode_workspaceFolders)
      yield WorkspaceFoldersInitializeParams(
        workspaceFolders,
      )
  given toJson: Encoder[WorkspaceFoldersInitializeParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_workspaceFolders: Encoder[Vector[structures.WorkspaceFolder]] = Encoder.encodeVector(structures.WorkspaceFolder.toJson)
    Enc.toJsonObject: (enc, a) =>
      a.workspaceFolders.foreach: v =>
        enc.field("workspaceFolders", v, encode_workspaceFolders)


private[lsp] trait structures_WorkspaceFoldersServerCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[WorkspaceFoldersServerCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_supported: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_changeNotifications: Decoder[(String | Boolean)] = Dec.union2[String, Boolean](Decoder.decodeString,Decoder.decodeBoolean)
    Dec.fromJsonObject: dec =>
      for
        supported <- dec.getOpt("supported", decode_supported)
        changeNotifications <- dec.getOpt("changeNotifications", decode_changeNotifications)
      yield WorkspaceFoldersServerCapabilities(
        supported,
        changeNotifications,
      )
  given toJson: Encoder[WorkspaceFoldersServerCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_supported: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_changeNotifications: Encoder[(String | Boolean)] = Enc.union2[String, Boolean](Encoder.encodeString,Encoder.encodeBoolean)
    Enc.toJsonObject: (enc, a) =>
      a.supported.foreach: v =>
        enc.field("supported", v, encode_supported)
      a.changeNotifications.foreach: v =>
        enc.field("changeNotifications", v, encode_changeNotifications)


private[lsp] trait structures_WorkspaceFullDocumentDiagnosticReportCodec:
  import structures.*
  given fromJson: Decoder[WorkspaceFullDocumentDiagnosticReport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_uri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    val decode_version: Decoder[Int] = Decoder.decodeInt
    val decode_kind: Decoder["full"] = Decoder.decodeLiteralString["full"]
    val decode_resultId: Decoder[String] = Decoder.decodeString
    val decode_items: Decoder[Vector[structures.Diagnostic]] = Decoder.decodeVector(structures.Diagnostic.fromJson)
    Dec.fromJsonObject: dec =>
      for
        uri <- dec.get("uri", decode_uri)
        version <- dec.getOpt("version", decode_version)
        kind <- dec.get("kind", decode_kind)
        resultId <- dec.getOpt("resultId", decode_resultId)
        items <- dec.get("items", decode_items)
      yield WorkspaceFullDocumentDiagnosticReport(
        uri,
        version,
        kind,
        resultId,
        items,
      )
  given toJson: Encoder[WorkspaceFullDocumentDiagnosticReport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_uri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    val encode_version: Encoder[Int] = Encoder.encodeInt
    val encode_kind: Encoder["full"] = Encoder.encodeLiteralString["full"]
    val encode_resultId: Encoder[String] = Encoder.encodeString
    val encode_items: Encoder[Vector[structures.Diagnostic]] = Encoder.encodeVector(structures.Diagnostic.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("uri", a.uri, encode_uri)
      a.version.foreach: v =>
        enc.field("version", v, encode_version)
      enc.field("kind", a.kind, encode_kind)
      a.resultId.foreach: v =>
        enc.field("resultId", v, encode_resultId)
      enc.field("items", a.items, encode_items)


private[lsp] trait structures_WorkspaceSymbolCodec:
  import structures.*
  given fromJson: Decoder[WorkspaceSymbol] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_location: Decoder[(structures.Location | WorkspaceSymbol.S0)] = Dec.union2[structures.Location, WorkspaceSymbol.S0](structures.Location.fromJson,WorkspaceSymbol.S0.fromJson)
    val decode_data: Decoder[io.circe.Json] = Decoder.decodeJson
    val decode_name: Decoder[String] = Decoder.decodeString
    val decode_kind: Decoder[enumerations.SymbolKind] = enumerations.SymbolKind.fromJson
    val decode_tags: Decoder[Vector[enumerations.SymbolTag]] = Decoder.decodeVector(enumerations.SymbolTag.fromJson)
    val decode_containerName: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        location <- dec.get("location", decode_location)
        data <- dec.getOpt("data", decode_data)
        name <- dec.get("name", decode_name)
        kind <- dec.get("kind", decode_kind)
        tags <- dec.getOpt("tags", decode_tags)
        containerName <- dec.getOpt("containerName", decode_containerName)
      yield WorkspaceSymbol(
        location,
        data,
        name,
        kind,
        tags,
        containerName,
      )
  given toJson: Encoder[WorkspaceSymbol] = 
    // cache all encoders for this type when toJson first initialised
    val encode_location: Encoder[(structures.Location | WorkspaceSymbol.S0)] = Enc.union2[structures.Location, WorkspaceSymbol.S0](structures.Location.toJson,WorkspaceSymbol.S0.toJson)
    val encode_data: Encoder[io.circe.Json] = Encoder.encodeJson
    val encode_name: Encoder[String] = Encoder.encodeString
    val encode_kind: Encoder[enumerations.SymbolKind] = enumerations.SymbolKind.toJson
    val encode_tags: Encoder[Vector[enumerations.SymbolTag]] = Encoder.encodeVector(enumerations.SymbolTag.toJson)
    val encode_containerName: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("location", a.location, encode_location)
      a.data.foreach: v =>
        enc.field("data", v, encode_data)
      enc.field("name", a.name, encode_name)
      enc.field("kind", a.kind, encode_kind)
      a.tags.foreach: v =>
        enc.field("tags", v, encode_tags)
      a.containerName.foreach: v =>
        enc.field("containerName", v, encode_containerName)


private[lsp] trait structures_WorkspaceSymbol_S0Codec:
  import structures.WorkspaceSymbol.*
  given fromJson: Decoder[S0] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_uri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    Dec.fromJsonObject: dec =>
      for
        uri <- dec.get("uri", decode_uri)
      yield S0(
        uri,
      )
  given toJson: Encoder[S0] = 
    // cache all encoders for this type when toJson first initialised
    val encode_uri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("uri", a.uri, encode_uri)


private[lsp] trait structures_WorkspaceSymbolClientCapabilitiesCodec:
  import structures.*
  given fromJson: Decoder[WorkspaceSymbolClientCapabilities] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_dynamicRegistration: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_symbolKind: Decoder[WorkspaceSymbolClientCapabilities.SymbolKind] = WorkspaceSymbolClientCapabilities.SymbolKind.fromJson
    val decode_tagSupport: Decoder[WorkspaceSymbolClientCapabilities.TagSupport] = WorkspaceSymbolClientCapabilities.TagSupport.fromJson
    val decode_resolveSupport: Decoder[WorkspaceSymbolClientCapabilities.ResolveSupport] = WorkspaceSymbolClientCapabilities.ResolveSupport.fromJson
    Dec.fromJsonObject: dec =>
      for
        dynamicRegistration <- dec.getOpt("dynamicRegistration", decode_dynamicRegistration)
        symbolKind <- dec.getOpt("symbolKind", decode_symbolKind)
        tagSupport <- dec.getOpt("tagSupport", decode_tagSupport)
        resolveSupport <- dec.getOpt("resolveSupport", decode_resolveSupport)
      yield WorkspaceSymbolClientCapabilities(
        dynamicRegistration,
        symbolKind,
        tagSupport,
        resolveSupport,
      )
  given toJson: Encoder[WorkspaceSymbolClientCapabilities] = 
    // cache all encoders for this type when toJson first initialised
    val encode_dynamicRegistration: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_symbolKind: Encoder[WorkspaceSymbolClientCapabilities.SymbolKind] = WorkspaceSymbolClientCapabilities.SymbolKind.toJson
    val encode_tagSupport: Encoder[WorkspaceSymbolClientCapabilities.TagSupport] = WorkspaceSymbolClientCapabilities.TagSupport.toJson
    val encode_resolveSupport: Encoder[WorkspaceSymbolClientCapabilities.ResolveSupport] = WorkspaceSymbolClientCapabilities.ResolveSupport.toJson
    Enc.toJsonObject: (enc, a) =>
      a.dynamicRegistration.foreach: v =>
        enc.field("dynamicRegistration", v, encode_dynamicRegistration)
      a.symbolKind.foreach: v =>
        enc.field("symbolKind", v, encode_symbolKind)
      a.tagSupport.foreach: v =>
        enc.field("tagSupport", v, encode_tagSupport)
      a.resolveSupport.foreach: v =>
        enc.field("resolveSupport", v, encode_resolveSupport)


private[lsp] trait structures_WorkspaceSymbolClientCapabilities_SymbolKindCodec:
  import structures.WorkspaceSymbolClientCapabilities.*
  given fromJson: Decoder[SymbolKind] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_valueSet: Decoder[Vector[enumerations.SymbolKind]] = Decoder.decodeVector(enumerations.SymbolKind.fromJson)
    Dec.fromJsonObject: dec =>
      for
        valueSet <- dec.getOpt("valueSet", decode_valueSet)
      yield SymbolKind(
        valueSet,
      )
  given toJson: Encoder[SymbolKind] = 
    // cache all encoders for this type when toJson first initialised
    val encode_valueSet: Encoder[Vector[enumerations.SymbolKind]] = Encoder.encodeVector(enumerations.SymbolKind.toJson)
    Enc.toJsonObject: (enc, a) =>
      a.valueSet.foreach: v =>
        enc.field("valueSet", v, encode_valueSet)


private[lsp] trait structures_WorkspaceSymbolClientCapabilities_TagSupportCodec:
  import structures.WorkspaceSymbolClientCapabilities.*
  given fromJson: Decoder[TagSupport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_valueSet: Decoder[Vector[enumerations.SymbolTag]] = Decoder.decodeVector(enumerations.SymbolTag.fromJson)
    Dec.fromJsonObject: dec =>
      for
        valueSet <- dec.get("valueSet", decode_valueSet)
      yield TagSupport(
        valueSet,
      )
  given toJson: Encoder[TagSupport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_valueSet: Encoder[Vector[enumerations.SymbolTag]] = Encoder.encodeVector(enumerations.SymbolTag.toJson)
    Enc.toJsonObject: (enc, a) =>
      enc.field("valueSet", a.valueSet, encode_valueSet)


private[lsp] trait structures_WorkspaceSymbolClientCapabilities_ResolveSupportCodec:
  import structures.WorkspaceSymbolClientCapabilities.*
  given fromJson: Decoder[ResolveSupport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_properties: Decoder[Vector[String]] = Decoder.decodeVector(Decoder.decodeString)
    Dec.fromJsonObject: dec =>
      for
        properties <- dec.get("properties", decode_properties)
      yield ResolveSupport(
        properties,
      )
  given toJson: Encoder[ResolveSupport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_properties: Encoder[Vector[String]] = Encoder.encodeVector(Encoder.encodeString)
    Enc.toJsonObject: (enc, a) =>
      enc.field("properties", a.properties, encode_properties)


private[lsp] trait structures_WorkspaceSymbolOptionsCodec:
  import structures.*
  given fromJson: Decoder[WorkspaceSymbolOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_resolveProvider: Decoder[Boolean] = Decoder.decodeBoolean
    val decode_workDoneProgress: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        resolveProvider <- dec.getOpt("resolveProvider", decode_resolveProvider)
        workDoneProgress <- dec.getOpt("workDoneProgress", decode_workDoneProgress)
      yield WorkspaceSymbolOptions(
        resolveProvider,
        workDoneProgress,
      )
  given toJson: Encoder[WorkspaceSymbolOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_resolveProvider: Encoder[Boolean] = Encoder.encodeBoolean
    val encode_workDoneProgress: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.resolveProvider.foreach: v =>
        enc.field("resolveProvider", v, encode_resolveProvider)
      a.workDoneProgress.foreach: v =>
        enc.field("workDoneProgress", v, encode_workDoneProgress)


private[lsp] trait structures_WorkspaceSymbolParamsCodec:
  import structures.*
  given fromJson: Decoder[WorkspaceSymbolParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_query: Decoder[String] = Decoder.decodeString
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    val decode_partialResultToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        query <- dec.get("query", decode_query)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
        partialResultToken <- dec.getOpt("partialResultToken", decode_partialResultToken)
      yield WorkspaceSymbolParams(
        query,
        workDoneToken,
        partialResultToken,
      )
  given toJson: Encoder[WorkspaceSymbolParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_query: Encoder[String] = Encoder.encodeString
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    val encode_partialResultToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      enc.field("query", a.query, encode_query)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)
      a.partialResultToken.foreach: v =>
        enc.field("partialResultToken", v, encode_partialResultToken)


private[lsp] trait structures_WorkspaceSymbolRegistrationOptionsCodec:
  import structures.*
  given fromJson: Decoder[WorkspaceSymbolRegistrationOptions] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_resolveProvider: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        resolveProvider <- dec.getOpt("resolveProvider", decode_resolveProvider)
      yield WorkspaceSymbolRegistrationOptions(
        resolveProvider,
      )
  given toJson: Encoder[WorkspaceSymbolRegistrationOptions] = 
    // cache all encoders for this type when toJson first initialised
    val encode_resolveProvider: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      a.resolveProvider.foreach: v =>
        enc.field("resolveProvider", v, encode_resolveProvider)


private[lsp] trait structures_WorkspaceUnchangedDocumentDiagnosticReportCodec:
  import structures.*
  given fromJson: Decoder[WorkspaceUnchangedDocumentDiagnosticReport] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_uri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    val decode_version: Decoder[Int] = Decoder.decodeInt
    val decode_kind: Decoder["unchanged"] = Decoder.decodeLiteralString["unchanged"]
    val decode_resultId: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        uri <- dec.get("uri", decode_uri)
        version <- dec.getOpt("version", decode_version)
        kind <- dec.get("kind", decode_kind)
        resultId <- dec.get("resultId", decode_resultId)
      yield WorkspaceUnchangedDocumentDiagnosticReport(
        uri,
        version,
        kind,
        resultId,
      )
  given toJson: Encoder[WorkspaceUnchangedDocumentDiagnosticReport] = 
    // cache all encoders for this type when toJson first initialised
    val encode_uri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    val encode_version: Encoder[Int] = Encoder.encodeInt
    val encode_kind: Encoder["unchanged"] = Encoder.encodeLiteralString["unchanged"]
    val encode_resultId: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("uri", a.uri, encode_uri)
      a.version.foreach: v =>
        enc.field("version", v, encode_version)
      enc.field("kind", a.kind, encode_kind)
      enc.field("resultId", a.resultId, encode_resultId)


private[lsp] trait structures__InitializeParamsCodec:
  import structures.*
  given fromJson: Decoder[_InitializeParams] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_processId: Decoder[Int] = Decoder.decodeInt
    val decode_clientInfo: Decoder[_InitializeParams.ClientInfo] = _InitializeParams.ClientInfo.fromJson
    val decode_locale: Decoder[String] = Decoder.decodeString
    val decode_rootPath: Decoder[String] = Decoder.decodeString
    val decode_rootUri: Decoder[runtime.DocumentUri] = DocumentUri.fromJson
    val decode_capabilities: Decoder[structures.ClientCapabilities] = structures.ClientCapabilities.fromJson
    val decode_initializationOptions: Decoder[io.circe.Json] = Decoder.decodeJson
    val decode_trace: Decoder[enumerations.TraceValues] = enumerations.TraceValues.fromJson
    val decode_workDoneToken: Decoder[aliases.ProgressToken] = aliases.ProgressToken.fromJson
    Dec.fromJsonObject: dec =>
      for
        processId <- dec.getOpt("processId", decode_processId)
        clientInfo <- dec.getOpt("clientInfo", decode_clientInfo)
        locale <- dec.getOpt("locale", decode_locale)
        rootPath <- dec.getOpt("rootPath", decode_rootPath)
        rootUri <- dec.getOpt("rootUri", decode_rootUri)
        capabilities <- dec.get("capabilities", decode_capabilities)
        initializationOptions <- dec.getOpt("initializationOptions", decode_initializationOptions)
        trace <- dec.getOpt("trace", decode_trace)
        workDoneToken <- dec.getOpt("workDoneToken", decode_workDoneToken)
      yield _InitializeParams(
        processId,
        clientInfo,
        locale,
        rootPath,
        rootUri,
        capabilities,
        initializationOptions,
        trace,
        workDoneToken,
      )
  given toJson: Encoder[_InitializeParams] = 
    // cache all encoders for this type when toJson first initialised
    val encode_processId: Encoder[Int] = Encoder.encodeInt
    val encode_clientInfo: Encoder[_InitializeParams.ClientInfo] = _InitializeParams.ClientInfo.toJson
    val encode_locale: Encoder[String] = Encoder.encodeString
    val encode_rootPath: Encoder[String] = Encoder.encodeString
    val encode_rootUri: Encoder[runtime.DocumentUri] = DocumentUri.toJson
    val encode_capabilities: Encoder[structures.ClientCapabilities] = structures.ClientCapabilities.toJson
    val encode_initializationOptions: Encoder[io.circe.Json] = Encoder.encodeJson
    val encode_trace: Encoder[enumerations.TraceValues] = enumerations.TraceValues.toJson
    val encode_workDoneToken: Encoder[aliases.ProgressToken] = aliases.ProgressToken.toJson
    Enc.toJsonObject: (enc, a) =>
      a.processId.foreach: v =>
        enc.field("processId", v, encode_processId)
      a.clientInfo.foreach: v =>
        enc.field("clientInfo", v, encode_clientInfo)
      a.locale.foreach: v =>
        enc.field("locale", v, encode_locale)
      a.rootPath.foreach: v =>
        enc.field("rootPath", v, encode_rootPath)
      a.rootUri.foreach: v =>
        enc.field("rootUri", v, encode_rootUri)
      enc.field("capabilities", a.capabilities, encode_capabilities)
      a.initializationOptions.foreach: v =>
        enc.field("initializationOptions", v, encode_initializationOptions)
      a.trace.foreach: v =>
        enc.field("trace", v, encode_trace)
      a.workDoneToken.foreach: v =>
        enc.field("workDoneToken", v, encode_workDoneToken)


private[lsp] trait structures__InitializeParams_ClientInfoCodec:
  import structures._InitializeParams.*
  given fromJson: Decoder[ClientInfo] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_name: Decoder[String] = Decoder.decodeString
    val decode_version: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        name <- dec.get("name", decode_name)
        version <- dec.getOpt("version", decode_version)
      yield ClientInfo(
        name,
        version,
      )
  given toJson: Encoder[ClientInfo] = 
    // cache all encoders for this type when toJson first initialised
    val encode_name: Encoder[String] = Encoder.encodeString
    val encode_version: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("name", a.name, encode_name)
      a.version.foreach: v =>
        enc.field("version", v, encode_version)


private[lsp] trait aliases_ChangeAnnotationIdentifier:
  
  given fromJson: Decoder[ChangeAnnotationIdentifier] = 
    Decoder.decodeString.asInstanceOf[Decoder[ChangeAnnotationIdentifier]]
  
  given toJson: Encoder[ChangeAnnotationIdentifier] =
    Encoder.encodeString.asInstanceOf[Encoder[ChangeAnnotationIdentifier]]

private[lsp] trait aliases_Declaration:
  
  given fromJson: Decoder[Declaration] = 
    Dec.union2[structures.Location, Vector[structures.Location]](structures.Location.fromJson,Decoder.decodeVector(structures.Location.fromJson)).asInstanceOf[Decoder[Declaration]]
  
  given toJson: Encoder[Declaration] =
    Enc.union2[structures.Location, Vector[structures.Location]](structures.Location.toJson,Encoder.encodeVector(structures.Location.toJson)).asInstanceOf[Encoder[Declaration]]

private[lsp] trait aliases_DeclarationLink:
  
  given fromJson: Decoder[DeclarationLink] = 
    structures.LocationLink.fromJson.asInstanceOf[Decoder[DeclarationLink]]
  
  given toJson: Encoder[DeclarationLink] =
    structures.LocationLink.toJson.asInstanceOf[Encoder[DeclarationLink]]

private[lsp] trait aliases_Definition:
  
  given fromJson: Decoder[Definition] = 
    Dec.union2[structures.Location, Vector[structures.Location]](structures.Location.fromJson,Decoder.decodeVector(structures.Location.fromJson)).asInstanceOf[Decoder[Definition]]
  
  given toJson: Encoder[Definition] =
    Enc.union2[structures.Location, Vector[structures.Location]](structures.Location.toJson,Encoder.encodeVector(structures.Location.toJson)).asInstanceOf[Encoder[Definition]]

private[lsp] trait aliases_DefinitionLink:
  
  given fromJson: Decoder[DefinitionLink] = 
    structures.LocationLink.fromJson.asInstanceOf[Decoder[DefinitionLink]]
  
  given toJson: Encoder[DefinitionLink] =
    structures.LocationLink.toJson.asInstanceOf[Encoder[DefinitionLink]]

private[lsp] trait aliases_DocumentDiagnosticReport:
  
  given fromJson: Decoder[DocumentDiagnosticReport] = 
    Dec.union2[structures.RelatedFullDocumentDiagnosticReport, structures.RelatedUnchangedDocumentDiagnosticReport](structures.RelatedFullDocumentDiagnosticReport.fromJson,structures.RelatedUnchangedDocumentDiagnosticReport.fromJson).asInstanceOf[Decoder[DocumentDiagnosticReport]]
  
  given toJson: Encoder[DocumentDiagnosticReport] =
    Enc.union2[structures.RelatedFullDocumentDiagnosticReport, structures.RelatedUnchangedDocumentDiagnosticReport](structures.RelatedFullDocumentDiagnosticReport.toJson,structures.RelatedUnchangedDocumentDiagnosticReport.toJson).asInstanceOf[Encoder[DocumentDiagnosticReport]]

private[lsp] trait aliases_DocumentFilter:
  
  given fromJson: Decoder[DocumentFilter] = 
    Dec.union2[aliases.TextDocumentFilter, structures.NotebookCellTextDocumentFilter](aliases.TextDocumentFilter.fromJson,structures.NotebookCellTextDocumentFilter.fromJson).asInstanceOf[Decoder[DocumentFilter]]
  
  given toJson: Encoder[DocumentFilter] =
    Enc.union2[aliases.TextDocumentFilter, structures.NotebookCellTextDocumentFilter](aliases.TextDocumentFilter.toJson,structures.NotebookCellTextDocumentFilter.toJson).asInstanceOf[Encoder[DocumentFilter]]

private[lsp] trait aliases_DocumentSelector:
  
  given fromJson: Decoder[DocumentSelector] = 
    Decoder.decodeVector(aliases.DocumentFilter.fromJson).asInstanceOf[Decoder[DocumentSelector]]
  
  given toJson: Encoder[DocumentSelector] =
    Encoder.encodeVector(aliases.DocumentFilter.toJson).asInstanceOf[Encoder[DocumentSelector]]

private[lsp] trait aliases_GlobPattern:
  
  given fromJson: Decoder[GlobPattern] = 
    Dec.union2[aliases.Pattern, structures.RelativePattern](aliases.Pattern.fromJson,structures.RelativePattern.fromJson).asInstanceOf[Decoder[GlobPattern]]
  
  given toJson: Encoder[GlobPattern] =
    Enc.union2[aliases.Pattern, structures.RelativePattern](aliases.Pattern.toJson,structures.RelativePattern.toJson).asInstanceOf[Encoder[GlobPattern]]

private[lsp] trait aliases_InlineValue:
  
  given fromJson: Decoder[InlineValue] = 
    Dec.union3[structures.InlineValueText, structures.InlineValueVariableLookup, structures.InlineValueEvaluatableExpression](structures.InlineValueText.fromJson,structures.InlineValueVariableLookup.fromJson, structures.InlineValueEvaluatableExpression.fromJson).asInstanceOf[Decoder[InlineValue]]
  
  given toJson: Encoder[InlineValue] =
    Enc.union3[structures.InlineValueText, structures.InlineValueVariableLookup, structures.InlineValueEvaluatableExpression](structures.InlineValueText.toJson,structures.InlineValueVariableLookup.toJson, structures.InlineValueEvaluatableExpression.toJson).asInstanceOf[Encoder[InlineValue]]

private[lsp] trait aliases_LSPObject:
  
  given fromJson: Decoder[LSPObject] = 
    Decoder.decodeMap(KeyDecoder.decodeKeyString, Decoder.decodeJson).asInstanceOf[Decoder[LSPObject]]
  
  given toJson: Encoder[LSPObject] =
    Encoder.encodeMap(KeyEncoder.encodeKeyString, Encoder.encodeJson).asInstanceOf[Encoder[LSPObject]]

private[lsp] trait aliases_MarkedString:
  
  given fromJson: Decoder[MarkedString] = 
    Dec.union2[String, MarkedString.S0](Decoder.decodeString,MarkedString.S0.fromJson).asInstanceOf[Decoder[MarkedString]]
  
  given toJson: Encoder[MarkedString] =
    Enc.union2[String, MarkedString.S0](Encoder.encodeString,MarkedString.S0.toJson).asInstanceOf[Encoder[MarkedString]]

private[lsp] trait aliases_MarkedString_S0Codec:
  import aliases.MarkedString.*
  given fromJson: Decoder[S0] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_language: Decoder[String] = Decoder.decodeString
    val decode_value: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        language <- dec.get("language", decode_language)
        value <- dec.get("value", decode_value)
      yield S0(
        language,
        value,
      )
  given toJson: Encoder[S0] = 
    // cache all encoders for this type when toJson first initialised
    val encode_language: Encoder[String] = Encoder.encodeString
    val encode_value: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("language", a.language, encode_language)
      enc.field("value", a.value, encode_value)


private[lsp] trait aliases_NotebookDocumentFilter:
  
  given fromJson: Decoder[NotebookDocumentFilter] = 
    Dec.union3[NotebookDocumentFilter.S0, NotebookDocumentFilter.S1, NotebookDocumentFilter.S2](NotebookDocumentFilter.S0.fromJson,NotebookDocumentFilter.S1.fromJson, NotebookDocumentFilter.S2.fromJson).asInstanceOf[Decoder[NotebookDocumentFilter]]
  
  given toJson: Encoder[NotebookDocumentFilter] =
    Enc.union3[NotebookDocumentFilter.S0, NotebookDocumentFilter.S1, NotebookDocumentFilter.S2](NotebookDocumentFilter.S0.toJson,NotebookDocumentFilter.S1.toJson, NotebookDocumentFilter.S2.toJson).asInstanceOf[Encoder[NotebookDocumentFilter]]

private[lsp] trait aliases_NotebookDocumentFilter_S0Codec:
  import aliases.NotebookDocumentFilter.*
  given fromJson: Decoder[S0] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_notebookType: Decoder[String] = Decoder.decodeString
    val decode_scheme: Decoder[String] = Decoder.decodeString
    val decode_pattern: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        notebookType <- dec.get("notebookType", decode_notebookType)
        scheme <- dec.getOpt("scheme", decode_scheme)
        pattern <- dec.getOpt("pattern", decode_pattern)
      yield S0(
        notebookType,
        scheme,
        pattern,
      )
  given toJson: Encoder[S0] = 
    // cache all encoders for this type when toJson first initialised
    val encode_notebookType: Encoder[String] = Encoder.encodeString
    val encode_scheme: Encoder[String] = Encoder.encodeString
    val encode_pattern: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("notebookType", a.notebookType, encode_notebookType)
      a.scheme.foreach: v =>
        enc.field("scheme", v, encode_scheme)
      a.pattern.foreach: v =>
        enc.field("pattern", v, encode_pattern)


private[lsp] trait aliases_NotebookDocumentFilter_S1Codec:
  import aliases.NotebookDocumentFilter.*
  given fromJson: Decoder[S1] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_notebookType: Decoder[String] = Decoder.decodeString
    val decode_scheme: Decoder[String] = Decoder.decodeString
    val decode_pattern: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        notebookType <- dec.getOpt("notebookType", decode_notebookType)
        scheme <- dec.get("scheme", decode_scheme)
        pattern <- dec.getOpt("pattern", decode_pattern)
      yield S1(
        notebookType,
        scheme,
        pattern,
      )
  given toJson: Encoder[S1] = 
    // cache all encoders for this type when toJson first initialised
    val encode_notebookType: Encoder[String] = Encoder.encodeString
    val encode_scheme: Encoder[String] = Encoder.encodeString
    val encode_pattern: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.notebookType.foreach: v =>
        enc.field("notebookType", v, encode_notebookType)
      enc.field("scheme", a.scheme, encode_scheme)
      a.pattern.foreach: v =>
        enc.field("pattern", v, encode_pattern)


private[lsp] trait aliases_NotebookDocumentFilter_S2Codec:
  import aliases.NotebookDocumentFilter.*
  given fromJson: Decoder[S2] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_notebookType: Decoder[String] = Decoder.decodeString
    val decode_scheme: Decoder[String] = Decoder.decodeString
    val decode_pattern: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        notebookType <- dec.getOpt("notebookType", decode_notebookType)
        scheme <- dec.getOpt("scheme", decode_scheme)
        pattern <- dec.get("pattern", decode_pattern)
      yield S2(
        notebookType,
        scheme,
        pattern,
      )
  given toJson: Encoder[S2] = 
    // cache all encoders for this type when toJson first initialised
    val encode_notebookType: Encoder[String] = Encoder.encodeString
    val encode_scheme: Encoder[String] = Encoder.encodeString
    val encode_pattern: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.notebookType.foreach: v =>
        enc.field("notebookType", v, encode_notebookType)
      a.scheme.foreach: v =>
        enc.field("scheme", v, encode_scheme)
      enc.field("pattern", a.pattern, encode_pattern)


private[lsp] trait aliases_Pattern:
  
  given fromJson: Decoder[Pattern] = 
    Decoder.decodeString.asInstanceOf[Decoder[Pattern]]
  
  given toJson: Encoder[Pattern] =
    Encoder.encodeString.asInstanceOf[Encoder[Pattern]]

private[lsp] trait aliases_PrepareRenameResult:
  
  given fromJson: Decoder[PrepareRenameResult] = 
    Dec.union3[structures.Range, PrepareRenameResult.S0, PrepareRenameResult.S1](structures.Range.fromJson,PrepareRenameResult.S0.fromJson, PrepareRenameResult.S1.fromJson).asInstanceOf[Decoder[PrepareRenameResult]]
  
  given toJson: Encoder[PrepareRenameResult] =
    Enc.union3[structures.Range, PrepareRenameResult.S0, PrepareRenameResult.S1](structures.Range.toJson,PrepareRenameResult.S0.toJson, PrepareRenameResult.S1.toJson).asInstanceOf[Encoder[PrepareRenameResult]]

private[lsp] trait aliases_PrepareRenameResult_S0Codec:
  import aliases.PrepareRenameResult.*
  given fromJson: Decoder[S0] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_placeholder: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        range <- dec.get("range", decode_range)
        placeholder <- dec.get("placeholder", decode_placeholder)
      yield S0(
        range,
        placeholder,
      )
  given toJson: Encoder[S0] = 
    // cache all encoders for this type when toJson first initialised
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_placeholder: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("range", a.range, encode_range)
      enc.field("placeholder", a.placeholder, encode_placeholder)


private[lsp] trait aliases_PrepareRenameResult_S1Codec:
  import aliases.PrepareRenameResult.*
  given fromJson: Decoder[S1] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_defaultBehavior: Decoder[Boolean] = Decoder.decodeBoolean
    Dec.fromJsonObject: dec =>
      for
        defaultBehavior <- dec.get("defaultBehavior", decode_defaultBehavior)
      yield S1(
        defaultBehavior,
      )
  given toJson: Encoder[S1] = 
    // cache all encoders for this type when toJson first initialised
    val encode_defaultBehavior: Encoder[Boolean] = Encoder.encodeBoolean
    Enc.toJsonObject: (enc, a) =>
      enc.field("defaultBehavior", a.defaultBehavior, encode_defaultBehavior)


private[lsp] trait aliases_ProgressToken:
  
  given fromJson: Decoder[ProgressToken] = 
    Dec.union2[Int, String](Decoder.decodeInt,Decoder.decodeString).asInstanceOf[Decoder[ProgressToken]]
  
  given toJson: Encoder[ProgressToken] =
    Enc.union2[Int, String](Encoder.encodeInt,Encoder.encodeString).asInstanceOf[Encoder[ProgressToken]]

private[lsp] trait aliases_TextDocumentContentChangeEvent:
  
  given fromJson: Decoder[TextDocumentContentChangeEvent] = 
    Dec.union2[TextDocumentContentChangeEvent.S0, TextDocumentContentChangeEvent.S1](TextDocumentContentChangeEvent.S0.fromJson,TextDocumentContentChangeEvent.S1.fromJson).asInstanceOf[Decoder[TextDocumentContentChangeEvent]]
  
  given toJson: Encoder[TextDocumentContentChangeEvent] =
    Enc.union2[TextDocumentContentChangeEvent.S0, TextDocumentContentChangeEvent.S1](TextDocumentContentChangeEvent.S0.toJson,TextDocumentContentChangeEvent.S1.toJson).asInstanceOf[Encoder[TextDocumentContentChangeEvent]]

private[lsp] trait aliases_TextDocumentContentChangeEvent_S0Codec:
  import aliases.TextDocumentContentChangeEvent.*
  given fromJson: Decoder[S0] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_range: Decoder[structures.Range] = structures.Range.fromJson
    val decode_rangeLength: Decoder[runtime.uinteger] = uinteger.fromJson
    val decode_text: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        range <- dec.get("range", decode_range)
        rangeLength <- dec.getOpt("rangeLength", decode_rangeLength)
        text <- dec.get("text", decode_text)
      yield S0(
        range,
        rangeLength,
        text,
      )
  given toJson: Encoder[S0] = 
    // cache all encoders for this type when toJson first initialised
    val encode_range: Encoder[structures.Range] = structures.Range.toJson
    val encode_rangeLength: Encoder[runtime.uinteger] = uinteger.toJson
    val encode_text: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("range", a.range, encode_range)
      a.rangeLength.foreach: v =>
        enc.field("rangeLength", v, encode_rangeLength)
      enc.field("text", a.text, encode_text)


private[lsp] trait aliases_TextDocumentContentChangeEvent_S1Codec:
  import aliases.TextDocumentContentChangeEvent.*
  given fromJson: Decoder[S1] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_text: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        text <- dec.get("text", decode_text)
      yield S1(
        text,
      )
  given toJson: Encoder[S1] = 
    // cache all encoders for this type when toJson first initialised
    val encode_text: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("text", a.text, encode_text)


private[lsp] trait aliases_TextDocumentFilter:
  
  given fromJson: Decoder[TextDocumentFilter] = 
    Dec.union3[TextDocumentFilter.S0, TextDocumentFilter.S1, TextDocumentFilter.S2](TextDocumentFilter.S0.fromJson,TextDocumentFilter.S1.fromJson, TextDocumentFilter.S2.fromJson).asInstanceOf[Decoder[TextDocumentFilter]]
  
  given toJson: Encoder[TextDocumentFilter] =
    Enc.union3[TextDocumentFilter.S0, TextDocumentFilter.S1, TextDocumentFilter.S2](TextDocumentFilter.S0.toJson,TextDocumentFilter.S1.toJson, TextDocumentFilter.S2.toJson).asInstanceOf[Encoder[TextDocumentFilter]]

private[lsp] trait aliases_TextDocumentFilter_S0Codec:
  import aliases.TextDocumentFilter.*
  given fromJson: Decoder[S0] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_language: Decoder[String] = Decoder.decodeString
    val decode_scheme: Decoder[String] = Decoder.decodeString
    val decode_pattern: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        language <- dec.get("language", decode_language)
        scheme <- dec.getOpt("scheme", decode_scheme)
        pattern <- dec.getOpt("pattern", decode_pattern)
      yield S0(
        language,
        scheme,
        pattern,
      )
  given toJson: Encoder[S0] = 
    // cache all encoders for this type when toJson first initialised
    val encode_language: Encoder[String] = Encoder.encodeString
    val encode_scheme: Encoder[String] = Encoder.encodeString
    val encode_pattern: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      enc.field("language", a.language, encode_language)
      a.scheme.foreach: v =>
        enc.field("scheme", v, encode_scheme)
      a.pattern.foreach: v =>
        enc.field("pattern", v, encode_pattern)


private[lsp] trait aliases_TextDocumentFilter_S1Codec:
  import aliases.TextDocumentFilter.*
  given fromJson: Decoder[S1] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_language: Decoder[String] = Decoder.decodeString
    val decode_scheme: Decoder[String] = Decoder.decodeString
    val decode_pattern: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        language <- dec.getOpt("language", decode_language)
        scheme <- dec.get("scheme", decode_scheme)
        pattern <- dec.getOpt("pattern", decode_pattern)
      yield S1(
        language,
        scheme,
        pattern,
      )
  given toJson: Encoder[S1] = 
    // cache all encoders for this type when toJson first initialised
    val encode_language: Encoder[String] = Encoder.encodeString
    val encode_scheme: Encoder[String] = Encoder.encodeString
    val encode_pattern: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.language.foreach: v =>
        enc.field("language", v, encode_language)
      enc.field("scheme", a.scheme, encode_scheme)
      a.pattern.foreach: v =>
        enc.field("pattern", v, encode_pattern)


private[lsp] trait aliases_TextDocumentFilter_S2Codec:
  import aliases.TextDocumentFilter.*
  given fromJson: Decoder[S2] = 
    // cache all decoders for this type when fromJson first initialised
    val decode_language: Decoder[String] = Decoder.decodeString
    val decode_scheme: Decoder[String] = Decoder.decodeString
    val decode_pattern: Decoder[String] = Decoder.decodeString
    Dec.fromJsonObject: dec =>
      for
        language <- dec.getOpt("language", decode_language)
        scheme <- dec.getOpt("scheme", decode_scheme)
        pattern <- dec.get("pattern", decode_pattern)
      yield S2(
        language,
        scheme,
        pattern,
      )
  given toJson: Encoder[S2] = 
    // cache all encoders for this type when toJson first initialised
    val encode_language: Encoder[String] = Encoder.encodeString
    val encode_scheme: Encoder[String] = Encoder.encodeString
    val encode_pattern: Encoder[String] = Encoder.encodeString
    Enc.toJsonObject: (enc, a) =>
      a.language.foreach: v =>
        enc.field("language", v, encode_language)
      a.scheme.foreach: v =>
        enc.field("scheme", v, encode_scheme)
      enc.field("pattern", a.pattern, encode_pattern)


private[lsp] trait aliases_WorkspaceDocumentDiagnosticReport:
  
  given fromJson: Decoder[WorkspaceDocumentDiagnosticReport] = 
    Dec.union2[structures.WorkspaceFullDocumentDiagnosticReport, structures.WorkspaceUnchangedDocumentDiagnosticReport](structures.WorkspaceFullDocumentDiagnosticReport.fromJson,structures.WorkspaceUnchangedDocumentDiagnosticReport.fromJson).asInstanceOf[Decoder[WorkspaceDocumentDiagnosticReport]]
  
  given toJson: Encoder[WorkspaceDocumentDiagnosticReport] =
    Enc.union2[structures.WorkspaceFullDocumentDiagnosticReport, structures.WorkspaceUnchangedDocumentDiagnosticReport](structures.WorkspaceFullDocumentDiagnosticReport.toJson,structures.WorkspaceUnchangedDocumentDiagnosticReport.toJson).asInstanceOf[Encoder[WorkspaceDocumentDiagnosticReport]]
