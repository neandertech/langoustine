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
  * @param dynamicRegistration
  *   Whether implementation supports dynamic registration. If this is set to
  *   `true` the client supports the new
  *   `(TextDocumentRegistrationOptions & StaticRegistrationOptions)` return
  *   value for the corresponding server capability as well.
  *
  * @param requests
  *   Which requests the client supports and might send to the server depending
  *   on the server's capability. Please note that clients might not show
  *   semantic tokens or degrade some of the user experience if a range or full
  *   request is advertised by the client but not provided by the server. If for
  *   example the client capability `requests.full` and `request.range` are both
  *   set to true but the server only provides a range provider the client might
  *   not render a minimap correctly or might even decide to not show any
  *   semantic tokens at all.
  *
  * @param tokenTypes
  *   The token types that the client supports.
  *
  * @param tokenModifiers
  *   The token modifiers that the client supports.
  *
  * @param formats
  *   The token formats the clients supports.
  *
  * @param overlappingTokenSupport
  *   Whether the client supports tokens that can overlap each other.
  *
  * @param multilineTokenSupport
  *   Whether the client supports tokens that can span multiple lines.
  *
  * @param serverCancelSupport
  *   Whether the client allows the server to actively cancel a semantic token
  *   request, e.g. supports returning LSPErrorCodes.ServerCancelled. If a
  *   server does the client needs to retrigger the request.
  *
  * since 3.17.0
  *
  * @param augmentsSyntaxTokens
  *   Whether the client uses semantic tokens to augment existing syntax tokens.
  *   If set to `true` client side created syntax tokens and semantic tokens are
  *   both used for colorization. If set to `false` the client only uses the
  *   returned semantic tokens for colorization.
  *
  * If the value is `undefined` then the client behavior is not specified.
  *
  * since 3.17.0
  */
case class SemanticTokensClientCapabilities(
    dynamicRegistration: Option[Boolean] = None,
    requests: SemanticTokensClientCapabilities.Requests,
    tokenTypes: Vector[String],
    tokenModifiers: Vector[String],
    formats: Vector[enumerations.TokenFormat],
    overlappingTokenSupport: Option[Boolean] = None,
    multilineTokenSupport: Option[Boolean] = None,
    serverCancelSupport: Option[Boolean] = None,
    augmentsSyntaxTokens: Option[Boolean] = None
)
object SemanticTokensClientCapabilities
    extends codecs.structures_SemanticTokensClientCapabilitiesCodec:
  /** @param range
    *   The client will send the `textDocument/semanticTokens/range` request if
    *   the server provides a corresponding handler.
    *
    * @param full
    *   The client will send the `textDocument/semanticTokens/full` request if
    *   the server provides a corresponding handler.
    */
  case class Requests(
      range: Option[(Boolean | Requests.S0)] = None,
      full: Option[(Boolean | Requests.S1)] = None
  )
  object Requests
      extends codecs.structures_SemanticTokensClientCapabilities_RequestsCodec:
    case class S0(
    )
    object S0
        extends codecs.structures_SemanticTokensClientCapabilities_Requests_S0Codec

    /** @param delta
      *   The client will send the `textDocument/semanticTokens/full/delta`
      *   request if the server provides a corresponding handler.
      */
    case class S1(
        delta: Option[Boolean] = None
    )
    object S1
        extends codecs.structures_SemanticTokensClientCapabilities_Requests_S1Codec
  end Requests
end SemanticTokensClientCapabilities
