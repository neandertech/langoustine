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

/** @param dynamicRegistration
  *   Whether text document synchronization supports dynamic registration.
  *
  * @param willSave
  *   The client supports sending will save notifications.
  *
  * @param willSaveWaitUntil
  *   The client supports sending a will save request and waits for a response
  *   providing text edits which will be applied to the document before it is
  *   saved.
  *
  * @param didSave
  *   The client supports did save notifications.
  */
case class TextDocumentSyncClientCapabilities(
    dynamicRegistration: Option[Boolean] = None,
    willSave: Option[Boolean] = None,
    willSaveWaitUntil: Option[Boolean] = None,
    didSave: Option[Boolean] = None
)
object TextDocumentSyncClientCapabilities
    extends codecs.structures_TextDocumentSyncClientCapabilitiesCodec
