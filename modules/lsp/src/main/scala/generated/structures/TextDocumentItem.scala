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

/** An item to transfer a text document from the client to the server.
  *
  * @param uri
  *   The text document's uri.
  *
  * @param languageId
  *   The text document's language identifier.
  *
  * @param version
  *   The version number of this document (it will increase after each change,
  *   including undo/redo).
  *
  * @param text
  *   The content of the opened text document.
  */
case class TextDocumentItem(
    uri: runtime.DocumentUri,
    languageId: String,
    version: Int,
    text: String
)
object TextDocumentItem extends codecs.structures_TextDocumentItemCodec
