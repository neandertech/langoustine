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

/** The result of a hover request.
  *
  * @param contents
  *   The hover's content
  *
  * @param range
  *   An optional range inside the text document that is used to visualize the
  *   hover, e.g. by changing the background color.
  */
case class Hover(
    contents: (structures.MarkupContent | aliases.MarkedString |
      Vector[aliases.MarkedString]),
    range: Option[structures.Range] = None
)
object Hover extends codecs.structures_HoverCodec
