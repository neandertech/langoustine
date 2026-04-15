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

/** The result of a linked editing range request.
  *
  * @since 3.16.0
  *
  * @param ranges
  *   A list of ranges that can be edited together. The ranges must have
  *   identical length and contain identical text content. The ranges cannot
  *   overlap.
  *
  * @param wordPattern
  *   An optional word pattern (regular expression) that describes valid
  *   contents for the given ranges. If no pattern is provided, the client
  *   configuration's word pattern will be used.
  */
case class LinkedEditingRanges(
    ranges: Vector[structures.Range],
    wordPattern: Option[String] = None
)
object LinkedEditingRanges extends codecs.structures_LinkedEditingRangesCodec
