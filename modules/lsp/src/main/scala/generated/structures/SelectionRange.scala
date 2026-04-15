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

/** A selection range represents a part of a selection hierarchy. A selection
  * range may have a parent selection range that contains it.
  *
  * @param range
  *   The {@link Range range} of this selection range.
  *
  * @param parent
  *   The parent selection range containing this range. Therefore `parent.range`
  *   must contain `this.range`.
  */
case class SelectionRange(
    range: structures.Range,
    parent: Option[structures.SelectionRange] = None
)
object SelectionRange extends codecs.structures_SelectionRangeCodec
