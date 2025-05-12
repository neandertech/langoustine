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

package langoustine.lsp
package tools

import structures.*
import enumerations.*
import SemanticTokensEncoder.Error

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

  def encode(
      v: Vector[SemanticToken]
  ): Either[SemanticTokensEncoder.Error, SemanticTokens] =
    import types.*
    val sorted =
      v.sortBy(tok => (tok.position.line.value, tok.position.character.value))

    inline def quantifyModifiers(
        modifiers: Vector[enumerations.SemanticTokenModifiers]
    ): Int =
      modifiers.foldLeft(0) { case (cur, tok) =>
        cur | (1 << modifiersIndex.getOrElse(
          tok,
          throw Error.UnknownModifier(tok, legend.tokenModifiers)
        ))
      }

    val intTokens = Vector.newBuilder[Int]
    intTokens.sizeHint(v.size * 5)

    case class State(prevLine: Int, prevChar: Int)

    try
      v.foldLeft(State(0, 0)) { case (State(prevLine, prevChar), tok) =>
        val deltaLine = tok.position.line.value - prevLine
        val deltaStartChar =
          if tok.position.line.value == prevLine then
            tok.position.character.value - prevChar
          else tok.position.character.value

        val tokenTypeInt = tokensIndex.getOrElse(
          tok.tokenType,
          throw Error.UnknownToken(tok.tokenType, legend.tokenTypes)
        )
        val modifierInt = quantifyModifiers(tok.modifiers)

        intTokens.addOne(deltaLine)
        intTokens.addOne(deltaStartChar)
        intTokens.addOne(tok.length.value)
        intTokens.addOne(tokenTypeInt)
        intTokens.addOne(modifierInt)

        State(tok.position.line.value, tok.position.character.value)
      }

      Right(
        SemanticTokens(data = intTokens.result.map(runtime.uinteger.apply(_)))
      )
    catch
      case exc: SemanticTokensEncoder.Error =>
        Left(exc)
    end try
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

  enum Error(msg: String) extends Throwable(msg):
    case UnknownToken(
        token: enumerations.SemanticTokenTypes,
        registered: Vector[String]
    ) extends Error(
          s"Token `${token}` was used, which isn't among the registered tokens `${registered
              .mkString(", ")}`.\n" +
            "Usage of tokens not agreed with the client beforehand throws the dense encoding out of sync"
        )

    case UnknownModifier(
        modif: enumerations.SemanticTokenModifiers,
        registered: Vector[String]
    ) extends Error(
          s"Modifier `${modif}` was used, which isn't among the registered modifiers `${registered
              .mkString(", ")}`.\n" +
            "Usage of modifiers not agreed with the client beforehand throws the dense encoding out of sync"
        )
  end Error

  private given Ordering[enumerations.SemanticTokenTypes] =
    Ordering.by(_.asInstanceOf[String])

  private given Ordering[enumerations.SemanticTokenModifiers] =
    Ordering.by(_.asInstanceOf[String])
end SemanticTokensEncoder
