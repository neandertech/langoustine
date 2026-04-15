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

/** Represents a folding range. To be valid, start and end line must be bigger
  * than zero and smaller than the number of lines in the document. Clients are
  * free to ignore invalid ranges.
  *
  * @param startLine
  *   The zero-based start line of the range to fold. The folded area starts
  *   after the line's last character. To be valid, the end must be zero or
  *   larger and smaller than the number of lines in the document.
  *
  * @param startCharacter
  *   The zero-based character offset from where the folded range starts. If not
  *   defined, defaults to the length of the start line.
  *
  * @param endLine
  *   The zero-based end line of the range to fold. The folded area ends with
  *   the line's last character. To be valid, the end must be zero or larger and
  *   smaller than the number of lines in the document.
  *
  * @param endCharacter
  *   The zero-based character offset before the folded range ends. If not
  *   defined, defaults to the length of the end line.
  *
  * @param kind
  *   Describes the kind of the folding range such as `comment' or 'region'. The
  *   kind is used to categorize folding ranges and used by commands like 'Fold
  *   all comments'. See {@link FoldingRangeKind} for an enumeration of
  *   standardized kinds.
  *
  * @param collapsedText
  *   The text that the client should show when the specified range is
  *   collapsed. If not defined or not supported by the client, a default will
  *   be chosen by the client.
  *
  * since 3.17.0
  */
case class FoldingRange(
    startLine: runtime.uinteger,
    startCharacter: Option[runtime.uinteger] = None,
    endLine: runtime.uinteger,
    endCharacter: Option[runtime.uinteger] = None,
    kind: Option[enumerations.FoldingRangeKind] = None,
    collapsedText: Option[String] = None
)
object FoldingRange extends codecs.structures_FoldingRangeCodec
