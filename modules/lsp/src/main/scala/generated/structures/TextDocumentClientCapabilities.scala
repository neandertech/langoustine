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

// format:off
package langoustine.lsp
package structures

import langoustine.*
import runtime.{*, given}

/** Text document specific client capabilities.
  *
  * @param synchronization
  *   Defines which synchronization capabilities the client supports.
  *
  * @param completion
  *   Capabilities specific to the `textDocument/completion` request.
  *
  * @param hover
  *   Capabilities specific to the `textDocument/hover` request.
  *
  * @param signatureHelp
  *   Capabilities specific to the `textDocument/signatureHelp` request.
  *
  * @param declaration
  *   Capabilities specific to the `textDocument/declaration` request.
  *
  * since 3.14.0
  *
  * @param definition
  *   Capabilities specific to the `textDocument/definition` request.
  *
  * @param typeDefinition
  *   Capabilities specific to the `textDocument/typeDefinition` request.
  *
  * since 3.6.0
  *
  * @param implementation
  *   Capabilities specific to the `textDocument/implementation` request.
  *
  * since 3.6.0
  *
  * @param references
  *   Capabilities specific to the `textDocument/references` request.
  *
  * @param documentHighlight
  *   Capabilities specific to the `textDocument/documentHighlight` request.
  *
  * @param documentSymbol
  *   Capabilities specific to the `textDocument/documentSymbol` request.
  *
  * @param codeAction
  *   Capabilities specific to the `textDocument/codeAction` request.
  *
  * @param codeLens
  *   Capabilities specific to the `textDocument/codeLens` request.
  *
  * @param documentLink
  *   Capabilities specific to the `textDocument/documentLink` request.
  *
  * @param colorProvider
  *   Capabilities specific to the `textDocument/documentColor` and the
  *   `textDocument/colorPresentation` request.
  *
  * since 3.6.0
  *
  * @param formatting
  *   Capabilities specific to the `textDocument/formatting` request.
  *
  * @param rangeFormatting
  *   Capabilities specific to the `textDocument/rangeFormatting` request.
  *
  * @param onTypeFormatting
  *   Capabilities specific to the `textDocument/onTypeFormatting` request.
  *
  * @param rename
  *   Capabilities specific to the `textDocument/rename` request.
  *
  * @param foldingRange
  *   Capabilities specific to the `textDocument/foldingRange` request.
  *
  * since 3.10.0
  *
  * @param selectionRange
  *   Capabilities specific to the `textDocument/selectionRange` request.
  *
  * since 3.15.0
  *
  * @param publishDiagnostics
  *   Capabilities specific to the `textDocument/publishDiagnostics`
  *   notification.
  *
  * @param callHierarchy
  *   Capabilities specific to the various call hierarchy requests.
  *
  * since 3.16.0
  *
  * @param semanticTokens
  *   Capabilities specific to the various semantic token request.
  *
  * since 3.16.0
  *
  * @param linkedEditingRange
  *   Capabilities specific to the `textDocument/linkedEditingRange` request.
  *
  * since 3.16.0
  *
  * @param moniker
  *   Client capabilities specific to the `textDocument/moniker` request.
  *
  * since 3.16.0
  *
  * @param typeHierarchy
  *   Capabilities specific to the various type hierarchy requests.
  *
  * since 3.17.0
  *
  * @param inlineValue
  *   Capabilities specific to the `textDocument/inlineValue` request.
  *
  * since 3.17.0
  *
  * @param inlayHint
  *   Capabilities specific to the `textDocument/inlayHint` request.
  *
  * since 3.17.0
  *
  * @param diagnostic
  *   Capabilities specific to the diagnostic pull model.
  *
  * since 3.17.0
  */
case class TextDocumentClientCapabilities(
    synchronization: Option[structures.TextDocumentSyncClientCapabilities] =
      None,
    completion: Option[structures.CompletionClientCapabilities] = None,
    hover: Option[structures.HoverClientCapabilities] = None,
    signatureHelp: Option[structures.SignatureHelpClientCapabilities] = None,
    declaration: Option[structures.DeclarationClientCapabilities] = None,
    definition: Option[structures.DefinitionClientCapabilities] = None,
    typeDefinition: Option[structures.TypeDefinitionClientCapabilities] = None,
    implementation: Option[structures.ImplementationClientCapabilities] = None,
    references: Option[structures.ReferenceClientCapabilities] = None,
    documentHighlight: Option[structures.DocumentHighlightClientCapabilities] =
      None,
    documentSymbol: Option[structures.DocumentSymbolClientCapabilities] = None,
    codeAction: Option[structures.CodeActionClientCapabilities] = None,
    codeLens: Option[structures.CodeLensClientCapabilities] = None,
    documentLink: Option[structures.DocumentLinkClientCapabilities] = None,
    colorProvider: Option[structures.DocumentColorClientCapabilities] = None,
    formatting: Option[structures.DocumentFormattingClientCapabilities] = None,
    rangeFormatting: Option[
      structures.DocumentRangeFormattingClientCapabilities
    ] = None,
    onTypeFormatting: Option[
      structures.DocumentOnTypeFormattingClientCapabilities
    ] = None,
    rename: Option[structures.RenameClientCapabilities] = None,
    foldingRange: Option[structures.FoldingRangeClientCapabilities] = None,
    selectionRange: Option[structures.SelectionRangeClientCapabilities] = None,
    publishDiagnostics: Option[
      structures.PublishDiagnosticsClientCapabilities
    ] = None,
    callHierarchy: Option[structures.CallHierarchyClientCapabilities] = None,
    semanticTokens: Option[structures.SemanticTokensClientCapabilities] = None,
    linkedEditingRange: Option[
      structures.LinkedEditingRangeClientCapabilities
    ] = None,
    moniker: Option[structures.MonikerClientCapabilities] = None,
    typeHierarchy: Option[structures.TypeHierarchyClientCapabilities] = None,
    inlineValue: Option[structures.InlineValueClientCapabilities] = None,
    inlayHint: Option[structures.InlayHintClientCapabilities] = None,
    diagnostic: Option[structures.DiagnosticClientCapabilities] = None
)
object TextDocumentClientCapabilities
    extends codecs.structures_TextDocumentClientCapabilitiesCodec
