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

/** A text document identifier to optionally denote a specific version of a text
  * document.
  *
  * @param version
  *   The version number of this document. If a versioned text document
  *   identifier is sent from the server to the client and the file is not open
  *   in the editor (the server has not received an open notification before)
  *   the server can send `null` to indicate that the version is unknown and the
  *   content on disk is the truth (as specified with document content
  *   ownership).
  *
  * @param uri
  *   The text document's uri.
  */
case class OptionalVersionedTextDocumentIdentifier(
    version: Option[Int] = None,
    uri: runtime.DocumentUri
)
object OptionalVersionedTextDocumentIdentifier
    extends codecs.structures_OptionalVersionedTextDocumentIdentifierCodec
