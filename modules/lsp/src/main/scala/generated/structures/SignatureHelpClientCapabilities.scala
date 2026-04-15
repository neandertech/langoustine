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

/** Client Capabilities for a {@link SignatureHelpRequest}.
  *
  * @param dynamicRegistration
  *   Whether signature help supports dynamic registration.
  *
  * @param signatureInformation
  *   The client supports the following `SignatureInformation` specific
  *   properties.
  *
  * @param contextSupport
  *   The client supports to send additional context information for a
  *   `textDocument/signatureHelp` request. A client that opts into
  *   contextSupport will also support the `retriggerCharacters` on
  *   `SignatureHelpOptions`.
  *
  * since 3.15.0
  */
case class SignatureHelpClientCapabilities(
    dynamicRegistration: Option[Boolean] = None,
    signatureInformation: Option[
      SignatureHelpClientCapabilities.SignatureInformation
    ] = None,
    contextSupport: Option[Boolean] = None
)
object SignatureHelpClientCapabilities
    extends codecs.structures_SignatureHelpClientCapabilitiesCodec:
  /** @param documentationFormat
    *   Client supports the following content formats for the documentation
    *   property. The order describes the preferred format of the client.
    *
    * @param parameterInformation
    *   Client capabilities specific to parameter information.
    *
    * @param activeParameterSupport
    *   The client supports the `activeParameter` property on
    *   `SignatureInformation` literal.
    *
    * since 3.16.0
    */
  case class SignatureInformation(
      documentationFormat: Option[Vector[enumerations.MarkupKind]] = None,
      parameterInformation: Option[SignatureInformation.ParameterInformation] =
        None,
      activeParameterSupport: Option[Boolean] = None
  )
  object SignatureInformation
      extends codecs.structures_SignatureHelpClientCapabilities_SignatureInformationCodec:
    /** @param labelOffsetSupport
      *   The client supports processing label offsets instead of a simple label
      *   string.
      *
      * since 3.14.0
      */
    case class ParameterInformation(
        labelOffsetSupport: Option[Boolean] = None
    )
    object ParameterInformation
        extends codecs.structures_SignatureHelpClientCapabilities_SignatureInformation_ParameterInformationCodec
  end SignatureInformation
end SignatureHelpClientCapabilities
