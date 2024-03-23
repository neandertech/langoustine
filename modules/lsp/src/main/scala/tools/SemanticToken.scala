package langoustine.lsp
package tools

import langoustine.lsp.structures.*
import scala.collection.SortedMap

case class SemanticToken(
    position: Position,
    length: runtime.uinteger,
    tokenType: enumerations.SemanticTokenTypes,
    modifiers: Vector[enumerations.SemanticTokenModifiers] = Vector.empty
)

object SemanticToken:
  def fromRange(
      range: structures.Range,
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
