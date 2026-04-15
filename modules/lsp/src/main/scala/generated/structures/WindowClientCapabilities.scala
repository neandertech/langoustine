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

/** @param workDoneProgress
  *   It indicates whether the client supports server initiated progress using
  *   the `window/workDoneProgress/create` request.
  *
  * The capability also controls Whether client supports handling of progress
  * notifications. If set servers are allowed to report a `workDoneProgress`
  * property in the request specific server capabilities.
  *
  * since 3.15.0
  *
  * @param showMessage
  *   Capabilities specific to the showMessage request.
  *
  * since 3.16.0
  *
  * @param showDocument
  *   Capabilities specific to the showDocument request.
  *
  * since 3.16.0
  */
case class WindowClientCapabilities(
    workDoneProgress: Option[Boolean] = None,
    showMessage: Option[structures.ShowMessageRequestClientCapabilities] = None,
    showDocument: Option[structures.ShowDocumentClientCapabilities] = None
)
object WindowClientCapabilities
    extends codecs.structures_WindowClientCapabilitiesCodec
