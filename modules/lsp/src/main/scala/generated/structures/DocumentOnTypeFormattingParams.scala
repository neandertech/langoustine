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

/** The parameters of a {@link DocumentOnTypeFormattingRequest}.
  *
  * @param textDocument
  *   The document to format.
  *
  * @param position
  *   The position around which the on type formatting should happen. This is
  *   not necessarily the exact position where the character denoted by the
  *   property `ch` got typed.
  *
  * @param ch
  *   The character that has been typed that triggered the formatting on type
  *   request. That is not necessarily the last character that got inserted into
  *   the document since the client could auto insert characters as well (e.g.
  *   like automatic brace completion).
  *
  * @param options
  *   The formatting options.
  */
case class DocumentOnTypeFormattingParams(
    textDocument: structures.TextDocumentIdentifier,
    position: structures.Position,
    ch: String,
    options: structures.FormattingOptions
)
object DocumentOnTypeFormattingParams
    extends codecs.structures_DocumentOnTypeFormattingParamsCodec
