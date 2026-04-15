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

/** Diagnostic registration options.
  *
  * @since 3.17.0
  *
  * @param documentSelector
  *   A document selector to identify the scope of the registration. If set to
  *   null the document selector provided on the client side will be used.
  *
  * @param identifier
  *   An optional identifier under which the diagnostics are managed by the
  *   client.
  *
  * @param interFileDependencies
  *   Whether the language has inter file dependencies meaning that editing code
  *   in one file can result in a different diagnostic set in another file.
  *   Inter file dependencies are common for most programming languages and
  *   typically uncommon for linters.
  *
  * @param workspaceDiagnostics
  *   The server provides support for workspace diagnostics as well.
  *
  * @param id
  *   The id used to register the request. The id can be used to deregister the
  *   request again. See also Registration#id.
  */
case class DiagnosticRegistrationOptions(
    documentSelector: Option[aliases.DocumentSelector] = None,
    identifier: Option[String] = None,
    interFileDependencies: Boolean,
    workspaceDiagnostics: Boolean,
    id: Option[String] = None
)
object DiagnosticRegistrationOptions
    extends codecs.structures_DiagnosticRegistrationOptionsCodec
