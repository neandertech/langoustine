package tests.lsp

import langoustine.lsp.tools.SemanticTokensEncoder
import langoustine.lsp.enumerations.{
  SemanticTokenTypes as T,
  SemanticTokenModifiers as M
}

import langoustine.lsp.all.*
import langoustine.lsp.tools.SemanticToken

object SemanticTokenTest extends weaver.FunSuite:
  test("fromRange") {
    val start = Position(5, 11)
    val end   = Position(5, 16)

    val token =
      SemanticToken.fromRange(Range(start, end), SemanticTokenTypes.variable)

    val token2 = SemanticToken.fromRange(
      Range(start, end),
      SemanticTokenTypes.variable,
      Vector(SemanticTokenModifiers.`abstract`)
    )

    expect.same(token.length, uinteger(5)) &&
    expect.same(token.position, start) &&
    expect.same(
      token2.modifiers,
      Vector(SemanticTokenModifiers.`abstract`)
    )
  }
end SemanticTokenTest
