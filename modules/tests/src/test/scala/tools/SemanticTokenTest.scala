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
