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

/** Workspace specific client capabilities.
  *
  * @param applyEdit
  *   The client supports applying batch edits to the workspace by supporting
  *   the request 'workspace/applyEdit'
  *
  * @param workspaceEdit
  *   Capabilities specific to `WorkspaceEdit`s.
  *
  * @param didChangeConfiguration
  *   Capabilities specific to the `workspace/didChangeConfiguration`
  *   notification.
  *
  * @param didChangeWatchedFiles
  *   Capabilities specific to the `workspace/didChangeWatchedFiles`
  *   notification.
  *
  * @param symbol
  *   Capabilities specific to the `workspace/symbol` request.
  *
  * @param executeCommand
  *   Capabilities specific to the `workspace/executeCommand` request.
  *
  * @param workspaceFolders
  *   The client has support for workspace folders.
  *
  * since 3.6.0
  *
  * @param configuration
  *   The client supports `workspace/configuration` requests.
  *
  * since 3.6.0
  *
  * @param semanticTokens
  *   Capabilities specific to the semantic token requests scoped to the
  *   workspace.
  *
  * since 3.16.0.
  *
  * @param codeLens
  *   Capabilities specific to the code lens requests scoped to the workspace.
  *
  * since 3.16.0.
  *
  * @param fileOperations
  *   The client has support for file notifications/requests for user operations
  *   on files.
  *
  * Since 3.16.0
  *
  * @param inlineValue
  *   Capabilities specific to the inline values requests scoped to the
  *   workspace.
  *
  * since 3.17.0.
  *
  * @param inlayHint
  *   Capabilities specific to the inlay hint requests scoped to the workspace.
  *
  * since 3.17.0.
  *
  * @param diagnostics
  *   Capabilities specific to the diagnostic requests scoped to the workspace.
  *
  * since 3.17.0.
  */
case class WorkspaceClientCapabilities(
    applyEdit: Option[Boolean] = None,
    workspaceEdit: Option[structures.WorkspaceEditClientCapabilities] = None,
    didChangeConfiguration: Option[
      structures.DidChangeConfigurationClientCapabilities
    ] = None,
    didChangeWatchedFiles: Option[
      structures.DidChangeWatchedFilesClientCapabilities
    ] = None,
    symbol: Option[structures.WorkspaceSymbolClientCapabilities] = None,
    executeCommand: Option[structures.ExecuteCommandClientCapabilities] = None,
    workspaceFolders: Option[Boolean] = None,
    configuration: Option[Boolean] = None,
    semanticTokens: Option[
      structures.SemanticTokensWorkspaceClientCapabilities
    ] = None,
    codeLens: Option[structures.CodeLensWorkspaceClientCapabilities] = None,
    fileOperations: Option[structures.FileOperationClientCapabilities] = None,
    inlineValue: Option[structures.InlineValueWorkspaceClientCapabilities] =
      None,
    inlayHint: Option[structures.InlayHintWorkspaceClientCapabilities] = None,
    diagnostics: Option[structures.DiagnosticWorkspaceClientCapabilities] = None
)
object WorkspaceClientCapabilities
    extends codecs.structures_WorkspaceClientCapabilitiesCodec
