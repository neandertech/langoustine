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

// format:off
package langoustine.lsp
package structures

import langoustine.*
import runtime.{*, given}

/** @param dynamicRegistration
  *   Whether implementation supports dynamic registration for folding range
  *   providers. If this is set to `true` the client supports the new
  *   `FoldingRangeRegistrationOptions` return value for the corresponding
  *   server capability as well.
  *
  * @param rangeLimit
  *   The maximum number of folding ranges that the client prefers to receive
  *   per document. The value serves as a hint, servers are free to follow the
  *   limit.
  *
  * @param lineFoldingOnly
  *   If set, the client signals that it only supports folding complete lines.
  *   If set, client will ignore specified `startCharacter` and `endCharacter`
  *   properties in a FoldingRange.
  *
  * @param foldingRangeKind
  *   Specific options for the folding range kind.
  *
  * since 3.17.0
  *
  * @param foldingRange
  *   Specific options for the folding range.
  *
  * since 3.17.0
  */
case class FoldingRangeClientCapabilities(
    dynamicRegistration: Option[Boolean] = None,
    rangeLimit: Option[runtime.uinteger] = None,
    lineFoldingOnly: Option[Boolean] = None,
    foldingRangeKind: Option[FoldingRangeClientCapabilities.FoldingRangeKind] =
      None,
    foldingRange: Option[FoldingRangeClientCapabilities.FoldingRange] = None
)
object FoldingRangeClientCapabilities
    extends codecs.structures_FoldingRangeClientCapabilitiesCodec:
  /** @param valueSet
    *   The folding range kind values the client supports. When this property
    *   exists the client also guarantees that it will handle values outside its
    *   set gracefully and falls back to a default value when unknown.
    */
  case class FoldingRangeKind(
      valueSet: Option[Vector[enumerations.FoldingRangeKind]] = None
  )
  object FoldingRangeKind
      extends codecs.structures_FoldingRangeClientCapabilities_FoldingRangeKindCodec

  /** @param collapsedText
    *   If set, the client signals that it supports setting collapsedText on
    *   folding ranges to display custom labels instead of the default text.
    *
    * since 3.17.0
    */
  case class FoldingRange(
      collapsedText: Option[Boolean] = None
  )
  object FoldingRange
      extends codecs.structures_FoldingRangeClientCapabilities_FoldingRangeCodec
end FoldingRangeClientCapabilities
