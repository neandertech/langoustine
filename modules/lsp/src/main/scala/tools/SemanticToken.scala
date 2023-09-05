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

package langoustine
package lsp
package tools

import langoustine.lsp.structures.Position
import scala.collection.SortedMap
import langoustine.lsp.structures.SemanticTokensLegend

case class SemanticToken(
    position: Position,
    length: lsp.runtime.uinteger,
    tokenType: enumerations.SemanticTokenTypes,
    modifiers: Vector[enumerations.SemanticTokenModifiers] = Vector.empty
)

object SemanticToken:
  def fromRange(
      range: lsp.structures.Range,
      tokenType: enumerations.SemanticTokenTypes,
      modifiers: Vector[enumerations.SemanticTokenModifiers] = Vector.empty
  ) =
    SemanticToken(
      position = range.start,
      length = range.end.character.map(_ - range.start.character.value),
      tokenType = tokenType,
      modifiers = modifiers
    )
end SemanticToken
