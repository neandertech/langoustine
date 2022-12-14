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
