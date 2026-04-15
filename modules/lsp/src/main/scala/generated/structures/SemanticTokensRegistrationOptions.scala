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

/** @since 3.16.0
  *
  * @param documentSelector
  *   A document selector to identify the scope of the registration. If set to
  *   null the document selector provided on the client side will be used.
  *
  * @param legend
  *   The legend used by the server
  *
  * @param range
  *   Server supports providing semantic tokens for a specific range of a
  *   document.
  *
  * @param full
  *   Server supports providing semantic tokens for a full document.
  *
  * @param id
  *   The id used to register the request. The id can be used to deregister the
  *   request again. See also Registration#id.
  */
case class SemanticTokensRegistrationOptions(
    documentSelector: Option[aliases.DocumentSelector] = None,
    legend: structures.SemanticTokensLegend,
    range: Option[(Boolean | SemanticTokensRegistrationOptions.S0)] = None,
    full: Option[(Boolean | SemanticTokensRegistrationOptions.S1)] = None,
    id: Option[String] = None
)
object SemanticTokensRegistrationOptions
    extends codecs.structures_SemanticTokensRegistrationOptionsCodec:
  case class S0(
  )
  object S0 extends codecs.structures_SemanticTokensRegistrationOptions_S0Codec

  /** @param delta
    *   The server supports deltas for full documents.
    */
  case class S1(
      delta: Option[Boolean] = None
  )
  object S1 extends codecs.structures_SemanticTokensRegistrationOptions_S1Codec
end SemanticTokensRegistrationOptions
