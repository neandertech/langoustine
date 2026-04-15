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

/** A parameter literal used in requests to pass a text document and a position
  * inside that document.
  *
  * @param textDocument
  *   The text document.
  *
  * @param position
  *   The position inside the text document.
  */
case class TextDocumentPositionParams(
    textDocument: structures.TextDocumentIdentifier,
    position: structures.Position
)
object TextDocumentPositionParams
    extends codecs.structures_TextDocumentPositionParamsCodec
