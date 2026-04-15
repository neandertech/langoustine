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

/** Defines the capabilities provided by the client.
  *
  * @param workspace
  *   Workspace specific client capabilities.
  *
  * @param textDocument
  *   Text document specific client capabilities.
  *
  * @param notebookDocument
  *   Capabilities specific to the notebook document support.
  *
  * since 3.17.0
  *
  * @param window
  *   Window specific client capabilities.
  *
  * @param general
  *   General client capabilities.
  *
  * since 3.16.0
  *
  * @param experimental
  *   Experimental client capabilities.
  */
case class ClientCapabilities(
    workspace: Option[structures.WorkspaceClientCapabilities] = None,
    textDocument: Option[structures.TextDocumentClientCapabilities] = None,
    notebookDocument: Option[structures.NotebookDocumentClientCapabilities] =
      None,
    window: Option[structures.WindowClientCapabilities] = None,
    general: Option[structures.GeneralClientCapabilities] = None,
    experimental: Option[io.circe.Json] = None
)
object ClientCapabilities extends codecs.structures_ClientCapabilitiesCodec
