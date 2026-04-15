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

/** The publish diagnostic client capabilities.
  *
  * @param relatedInformation
  *   Whether the clients accepts diagnostics with related information.
  *
  * @param tagSupport
  *   Client supports the tag property to provide meta data about a diagnostic.
  *   Clients supporting tags have to handle unknown tags gracefully.
  *
  * since 3.15.0
  *
  * @param versionSupport
  *   Whether the client interprets the version property of the
  *   `textDocument/publishDiagnostics` notification's parameter.
  *
  * since 3.15.0
  *
  * @param codeDescriptionSupport
  *   Client supports a codeDescription property
  *
  * since 3.16.0
  *
  * @param dataSupport
  *   Whether code action supports the `data` property which is preserved
  *   between a `textDocument/publishDiagnostics` and `textDocument/codeAction`
  *   request.
  *
  * since 3.16.0
  */
case class PublishDiagnosticsClientCapabilities(
    relatedInformation: Option[Boolean] = None,
    tagSupport: Option[PublishDiagnosticsClientCapabilities.TagSupport] = None,
    versionSupport: Option[Boolean] = None,
    codeDescriptionSupport: Option[Boolean] = None,
    dataSupport: Option[Boolean] = None
)
object PublishDiagnosticsClientCapabilities
    extends codecs.structures_PublishDiagnosticsClientCapabilitiesCodec:
  /** @param valueSet
    *   The tags supported by the client.
    */
  case class TagSupport(
      valueSet: Vector[enumerations.DiagnosticTag]
  )
  object TagSupport
      extends codecs.structures_PublishDiagnosticsClientCapabilities_TagSupportCodec
end PublishDiagnosticsClientCapabilities
