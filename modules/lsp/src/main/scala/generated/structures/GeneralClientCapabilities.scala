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

/** General client capabilities.
  *
  * @since 3.16.0
  *
  * @param staleRequestSupport
  *   Client capability that signals how the client handles stale requests (e.g.
  *   a request for which the client will not process the response anymore since
  *   the information is outdated).
  *
  * since 3.17.0
  *
  * @param regularExpressions
  *   Client capabilities specific to regular expressions.
  *
  * since 3.16.0
  *
  * @param markdown
  *   Client capabilities specific to the client's markdown parser.
  *
  * since 3.16.0
  *
  * @param positionEncodings
  *   The position encodings supported by the client. Client and server have to
  *   agree on the same position encoding to ensure that offsets (e.g. character
  *   position in a line) are interpreted the same on both sides.
  *
  * To keep the protocol backwards compatible the following applies: if the
  * value 'utf-16' is missing from the array of position encodings servers can
  * assume that the client supports UTF-16. UTF-16 is therefore a mandatory
  * encoding.
  *
  * If omitted it defaults to ['utf-16'].
  *
  * Implementation considerations: since the conversion from one encoding into
  * another requires the content of the file / line the conversion is best done
  * where the file is read which is usually on the server side.
  *
  * since 3.17.0
  */
case class GeneralClientCapabilities(
    staleRequestSupport: Option[GeneralClientCapabilities.StaleRequestSupport] =
      None,
    regularExpressions: Option[
      structures.RegularExpressionsClientCapabilities
    ] = None,
    markdown: Option[structures.MarkdownClientCapabilities] = None,
    positionEncodings: Option[Vector[enumerations.PositionEncodingKind]] = None
)
object GeneralClientCapabilities
    extends codecs.structures_GeneralClientCapabilitiesCodec:
  /** @param cancel
    *   The client will actively cancel the request.
    *
    * @param retryOnContentModified
    *   The list of requests for which the client will retry the request if it
    *   receives a response with error code `ContentModified`
    */
  case class StaleRequestSupport(
      cancel: Boolean,
      retryOnContentModified: Vector[String]
  )
  object StaleRequestSupport
      extends codecs.structures_GeneralClientCapabilities_StaleRequestSupportCodec
end GeneralClientCapabilities
