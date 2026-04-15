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

/** A range in a text document expressed as (zero-based) start and end
  * positions.
  *
  * If you want to specify a range that contains a line including the line
  * ending character(s) then use an end position denoting the start of the next
  * line. For example:
  * ```ts
  * {
  *     start: { line: 5, character: 23 }
  *     end : { line 6, character : 0 }
  * }
  * ```
  *
  * @param start
  *   The range's start position.
  *
  * @param end
  *   The range's end position.
  */
case class Range(
    start: structures.Position,
    `end`: structures.Position
)
object Range extends codecs.structures_RangeCodec
