package langoustine
package lsp
package tools

import langoustine.lsp.structures.Position
import scala.collection.SortedMap
import langoustine.lsp.structures.SemanticTokensLegend

// This encoder logic comes from
// https://microsoft.github.io/language-server-protocol/specifications/lsp/3.17/specification/#textDocument_semanticTokens
class SemanticTokensEncoder private (
    tokensIndex: Map[enumerations.SemanticTokenTypes, Int],
    modifiersIndex: Map[enumerations.SemanticTokenModifiers, Int]
):
  val legend = SemanticTokensLegend(
    tokenTypes = tokensIndex.keys.map(_.raw).toVector,
    tokenModifiers = modifiersIndex.keys.map(_.raw).toVector
  )

  def encode(v: Vector[SemanticToken]) =
    import types.*
    val sorted =
      v.sortBy(tok => (tok.position.line.value, tok.position.character.value))

    inline def quantifyModifiers(
        modifiers: Vector[enumerations.SemanticTokenModifiers]
    ): Int =
      modifiers.foldLeft(0) { case (cur, tok) =>
        cur | (1 << modifiersIndex(tok))
      }

    val intTokens = Vector.newBuilder[Int]
    intTokens.sizeHint(v.size * 5)

    case class State(prevLine: Int, prevChar: Int)

    v.foldLeft(State(0, 0)) { case (State(prevLine, prevChar), tok) =>
      val deltaLine = tok.position.line.value - prevLine
      val deltaStartChar =
        if tok.position.line.value == prevLine then
          tok.position.character.value - prevChar
        else tok.position.character.value

      val tokenTypeInt = tokensIndex(tok.tokenType)
      val modifierInt  = quantifyModifiers(tok.modifiers)

      intTokens.addOne(deltaLine)
      intTokens.addOne(deltaStartChar)
      intTokens.addOne(tok.length.value)
      intTokens.addOne(tokenTypeInt)
      intTokens.addOne(modifierInt)

      State(tok.position.line.value, tok.position.character.value)
    }

    structures.SemanticTokens(data =
      intTokens.result.map(runtime.uinteger.apply(_))
    )
  end encode

  private object types:
    opaque type TokenIndex = Int
    object TokenIndex extends OpaqueInt[TokenIndex]

    opaque type ModifierIndex = Int
    object ModifierIndex extends OpaqueInt[ModifierIndex]
end SemanticTokensEncoder

object SemanticTokensEncoder:
  def apply(
      tokenTypes: Vector[enumerations.SemanticTokenTypes],
      modifiers: Vector[enumerations.SemanticTokenModifiers]
  ) =
    new SemanticTokensEncoder(
      Map.from(tokenTypes.zipWithIndex),
      Map.from(modifiers.zipWithIndex)
    )

  private given Ordering[enumerations.SemanticTokenTypes] =
    Ordering.by(_.asInstanceOf[String])

  private given Ordering[enumerations.SemanticTokenModifiers] =
    Ordering.by(_.asInstanceOf[String])
end SemanticTokensEncoder
