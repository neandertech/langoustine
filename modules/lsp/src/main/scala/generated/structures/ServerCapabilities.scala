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

/** Defines the capabilities provided by a language server.
  *
  * @param positionEncoding
  *   The position encoding the server picked from the encodings offered by the
  *   client via the client capability `general.positionEncodings`.
  *
  * If the client didn't provide any position encodings the only valid value
  * that a server can return is 'utf-16'.
  *
  * If omitted it defaults to 'utf-16'.
  *
  * since 3.17.0
  *
  * @param textDocumentSync
  *   Defines how text documents are synced. Is either a detailed structure
  *   defining each notification or for backwards compatibility the
  *   TextDocumentSyncKind number.
  *
  * @param notebookDocumentSync
  *   Defines how notebook documents are synced.
  *
  * since 3.17.0
  *
  * @param completionProvider
  *   The server provides completion support.
  *
  * @param hoverProvider
  *   The server provides hover support.
  *
  * @param signatureHelpProvider
  *   The server provides signature help support.
  *
  * @param declarationProvider
  *   The server provides Goto Declaration support.
  *
  * @param definitionProvider
  *   The server provides goto definition support.
  *
  * @param typeDefinitionProvider
  *   The server provides Goto Type Definition support.
  *
  * @param implementationProvider
  *   The server provides Goto Implementation support.
  *
  * @param referencesProvider
  *   The server provides find references support.
  *
  * @param documentHighlightProvider
  *   The server provides document highlight support.
  *
  * @param documentSymbolProvider
  *   The server provides document symbol support.
  *
  * @param codeActionProvider
  *   The server provides code actions. CodeActionOptions may only be specified
  *   if the client states that it supports `codeActionLiteralSupport` in its
  *   initial `initialize` request.
  *
  * @param codeLensProvider
  *   The server provides code lens.
  *
  * @param documentLinkProvider
  *   The server provides document link support.
  *
  * @param colorProvider
  *   The server provides color provider support.
  *
  * @param workspaceSymbolProvider
  *   The server provides workspace symbol support.
  *
  * @param documentFormattingProvider
  *   The server provides document formatting.
  *
  * @param documentRangeFormattingProvider
  *   The server provides document range formatting.
  *
  * @param documentOnTypeFormattingProvider
  *   The server provides document formatting on typing.
  *
  * @param renameProvider
  *   The server provides rename support. RenameOptions may only be specified if
  *   the client states that it supports `prepareSupport` in its initial
  *   `initialize` request.
  *
  * @param foldingRangeProvider
  *   The server provides folding provider support.
  *
  * @param selectionRangeProvider
  *   The server provides selection range support.
  *
  * @param executeCommandProvider
  *   The server provides execute command support.
  *
  * @param callHierarchyProvider
  *   The server provides call hierarchy support.
  *
  * since 3.16.0
  *
  * @param linkedEditingRangeProvider
  *   The server provides linked editing range support.
  *
  * since 3.16.0
  *
  * @param semanticTokensProvider
  *   The server provides semantic tokens support.
  *
  * since 3.16.0
  *
  * @param monikerProvider
  *   The server provides moniker support.
  *
  * since 3.16.0
  *
  * @param typeHierarchyProvider
  *   The server provides type hierarchy support.
  *
  * since 3.17.0
  *
  * @param inlineValueProvider
  *   The server provides inline values.
  *
  * since 3.17.0
  *
  * @param inlayHintProvider
  *   The server provides inlay hints.
  *
  * since 3.17.0
  *
  * @param diagnosticProvider
  *   The server has support for pull model diagnostics.
  *
  * since 3.17.0
  *
  * @param workspace
  *   Workspace specific server capabilities.
  *
  * @param experimental
  *   Experimental server capabilities.
  */
case class ServerCapabilities(
    positionEncoding: Option[enumerations.PositionEncodingKind] = None,
    textDocumentSync: Option[
      (structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)
    ] = None,
    notebookDocumentSync: Option[
      (structures.NotebookDocumentSyncOptions |
        structures.NotebookDocumentSyncRegistrationOptions)
    ] = None,
    completionProvider: Option[structures.CompletionOptions] = None,
    hoverProvider: Option[(Boolean | structures.HoverOptions)] = None,
    signatureHelpProvider: Option[structures.SignatureHelpOptions] = None,
    declarationProvider: Option[
      (Boolean | structures.DeclarationOptions |
        structures.DeclarationRegistrationOptions)
    ] = None,
    definitionProvider: Option[(Boolean | structures.DefinitionOptions)] = None,
    typeDefinitionProvider: Option[
      (Boolean | structures.TypeDefinitionOptions |
        structures.TypeDefinitionRegistrationOptions)
    ] = None,
    implementationProvider: Option[
      (Boolean | structures.ImplementationOptions |
        structures.ImplementationRegistrationOptions)
    ] = None,
    referencesProvider: Option[(Boolean | structures.ReferenceOptions)] = None,
    documentHighlightProvider: Option[
      (Boolean | structures.DocumentHighlightOptions)
    ] = None,
    documentSymbolProvider: Option[
      (Boolean | structures.DocumentSymbolOptions)
    ] = None,
    codeActionProvider: Option[(Boolean | structures.CodeActionOptions)] = None,
    codeLensProvider: Option[structures.CodeLensOptions] = None,
    documentLinkProvider: Option[structures.DocumentLinkOptions] = None,
    colorProvider: Option[
      (Boolean | structures.DocumentColorOptions |
        structures.DocumentColorRegistrationOptions)
    ] = None,
    workspaceSymbolProvider: Option[
      (Boolean | structures.WorkspaceSymbolOptions)
    ] = None,
    documentFormattingProvider: Option[
      (Boolean | structures.DocumentFormattingOptions)
    ] = None,
    documentRangeFormattingProvider: Option[
      (Boolean | structures.DocumentRangeFormattingOptions)
    ] = None,
    documentOnTypeFormattingProvider: Option[
      structures.DocumentOnTypeFormattingOptions
    ] = None,
    renameProvider: Option[(Boolean | structures.RenameOptions)] = None,
    foldingRangeProvider: Option[
      (Boolean | structures.FoldingRangeOptions |
        structures.FoldingRangeRegistrationOptions)
    ] = None,
    selectionRangeProvider: Option[
      (Boolean | structures.SelectionRangeOptions |
        structures.SelectionRangeRegistrationOptions)
    ] = None,
    executeCommandProvider: Option[structures.ExecuteCommandOptions] = None,
    callHierarchyProvider: Option[
      (Boolean | structures.CallHierarchyOptions |
        structures.CallHierarchyRegistrationOptions)
    ] = None,
    linkedEditingRangeProvider: Option[
      (Boolean | structures.LinkedEditingRangeOptions |
        structures.LinkedEditingRangeRegistrationOptions)
    ] = None,
    semanticTokensProvider: Option[
      (structures.SemanticTokensOptions |
        structures.SemanticTokensRegistrationOptions)
    ] = None,
    monikerProvider: Option[
      (Boolean | structures.MonikerOptions |
        structures.MonikerRegistrationOptions)
    ] = None,
    typeHierarchyProvider: Option[
      (Boolean | structures.TypeHierarchyOptions |
        structures.TypeHierarchyRegistrationOptions)
    ] = None,
    inlineValueProvider: Option[
      (Boolean | structures.InlineValueOptions |
        structures.InlineValueRegistrationOptions)
    ] = None,
    inlayHintProvider: Option[
      (Boolean | structures.InlayHintOptions |
        structures.InlayHintRegistrationOptions)
    ] = None,
    diagnosticProvider: Option[
      (structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)
    ] = None,
    workspace: Option[ServerCapabilities.Workspace] = None,
    experimental: Option[io.circe.Json] = None
)
object ServerCapabilities extends codecs.structures_ServerCapabilitiesCodec:
  /** @param workspaceFolders
    *   The server supports workspace folder.
    *
    * since 3.6.0
    *
    * @param fileOperations
    *   The server is interested in notifications/requests for operations on
    *   files.
    *
    * since 3.16.0
    */
  case class Workspace(
      workspaceFolders: Option[structures.WorkspaceFoldersServerCapabilities] =
        None,
      fileOperations: Option[structures.FileOperationOptions] = None
  )
  object Workspace extends codecs.structures_ServerCapabilities_WorkspaceCodec
end ServerCapabilities
