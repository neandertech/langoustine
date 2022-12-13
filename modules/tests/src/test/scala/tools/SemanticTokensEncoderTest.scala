package tests.lsp

import langoustine.lsp.tools.SemanticTokensEncoder
import langoustine.lsp.enumerations.{
  SemanticTokenTypes as T,
  SemanticTokenModifiers as M
}

import langoustine.lsp.all.*
import langoustine.lsp.tools.SemanticToken

object SemanticTokensEncoderTest extends weaver.FunSuite:
  test("legend") {
    val encoder = SemanticTokensEncoder(
      Vector(T.string, T.number, T.method),
      Vector(M.`abstract`, M.async)
    )

    expect.eql(
      encoder.legend.tokenTypes,
      Vector("string", "number", "method")
    ) &&
    expect.eql(
      encoder.legend.tokenModifiers,
      Vector("abstract", "async")
    )

  }

  test("Encode") {
    val encoder = SemanticTokensEncoder(
      Vector(T.property, T.`type`, T.`class`),
      Vector(M.`declaration`, M.`static`)
    )

    val tokens = Vector(
      SemanticToken(
        Position(2, 5),
        length = uinteger(3),
        tokenType = T.property,
        modifiers = Vector(M.declaration, M.static)
      ),
      SemanticToken(
        Position(2, 10),
        length = uinteger(4),
        tokenType = T.`type`,
        modifiers = Vector.empty
      ),
      SemanticToken(
        Position(5, 2),
        length = uinteger(7),
        tokenType = T.`class`,
        modifiers = Vector.empty
      )
    )

    val result = encoder.encode(tokens).data.map(_.value)

    // This example is from
    // https://microsoft.github.io/language-server-protocol/specifications/lsp/3.17/specification/#textDocument_semanticTokens
    expect.eql(
      result,
      Vector(2, 5, 3, 0, 3, // 1st token
        0, 5, 4, 1, 0,      // 2nd token
        3, 2, 7, 2, 0)      // 3rd token
    )
  }
end SemanticTokensEncoderTest
