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
    modifiers: Vector[enumerations.SemanticTokenModifiers]
)
