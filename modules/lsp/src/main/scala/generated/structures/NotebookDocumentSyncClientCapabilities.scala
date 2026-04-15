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

/** Notebook specific client capabilities.
  *
  * @since 3.17.0
  *
  * @param dynamicRegistration
  *   Whether implementation supports dynamic registration. If this is set to
  *   `true` the client supports the new
  *   `(TextDocumentRegistrationOptions & StaticRegistrationOptions)` return
  *   value for the corresponding server capability as well.
  *
  * @param executionSummarySupport
  *   The client supports sending execution summary data per cell.
  */
case class NotebookDocumentSyncClientCapabilities(
    dynamicRegistration: Option[Boolean] = None,
    executionSummarySupport: Option[Boolean] = None
)
object NotebookDocumentSyncClientCapabilities
    extends codecs.structures_NotebookDocumentSyncClientCapabilitiesCodec
