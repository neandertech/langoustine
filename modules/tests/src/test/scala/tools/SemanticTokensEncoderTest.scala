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

  val encoder = SemanticTokensEncoder(
    Vector(T.property, T.`type`, T.`class`),
    Vector(M.`declaration`, M.`static`)
  )

  test("encode") {
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

    val result = encoder.encode(tokens).map(_.data.map(_.value))

    // This example is from
    // https://microsoft.github.io/language-server-protocol/specifications/lsp/3.17/specification/#textDocument_semanticTokens
    expect.same(
      result,
      Right(
        Vector(2, 5, 3, 0, 3, // 1st token
          0, 5, 4, 1, 0,      // 2nd token
          3, 2, 7, 2, 0)      // 3rd token
      )
    )
  }

  test("encode empty") {
    expect.same(encoder.encode(Vector.empty).map(_.data), Right(Vector.empty))
  }

  test("fail to encode unknown token") {
    expect.same(
      encoder.encode(
        Vector(
          SemanticToken(
            Position(5, 2),
            length = uinteger(7),
            tokenType = T.parameter,
            modifiers = Vector.empty
          )
        )
      ),
      Left(
        SemanticTokensEncoder.Error
          .UnknownToken(T.parameter, encoder.legend.tokenTypes)
      )
    )
  }

  test("fail to encode unknown modifier") {
    expect.same(
      encoder.encode(
        Vector(
          SemanticToken(
            Position(5, 2),
            length = uinteger(7),
            tokenType = T.`class`,
            modifiers = Vector(M.`abstract`)
          )
        )
      ),
      Left(
        SemanticTokensEncoder.Error
          .UnknownModifier(M.`abstract`, encoder.legend.tokenModifiers)
      )
    )
  }
end SemanticTokensEncoderTest
