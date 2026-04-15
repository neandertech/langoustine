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

/** Describes textual changes on a text document. A TextDocumentEdit describes
  * all changes on a document version Si and after they are applied move the
  * document to version Si+1. So the creator of a TextDocumentEdit doesn't need
  * to sort the array of edits or do any kind of ordering. However the edits
  * must be non overlapping.
  *
  * @param textDocument
  *   The text document to change.
  *
  * @param edits
  *   The edits to be applied.
  *
  * since 3.16.0 - support for AnnotatedTextEdit. This is guarded using a client
  * capability.
  */
case class TextDocumentEdit(
    textDocument: structures.OptionalVersionedTextDocumentIdentifier,
    edits: Vector[(structures.TextEdit | structures.AnnotatedTextEdit)]
)
object TextDocumentEdit extends codecs.structures_TextDocumentEditCodec
