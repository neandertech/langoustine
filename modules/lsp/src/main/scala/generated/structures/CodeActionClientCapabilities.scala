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

/** The Client Capabilities of a {@link CodeActionRequest}.
  *
  * @param dynamicRegistration
  *   Whether code action supports dynamic registration.
  *
  * @param codeActionLiteralSupport
  *   The client support code action literals of type `CodeAction` as a valid
  *   response of the `textDocument/codeAction` request. If the property is not
  *   set the request can only return `Command` literals.
  *
  * since 3.8.0
  *
  * @param isPreferredSupport
  *   Whether code action supports the `isPreferred` property.
  *
  * since 3.15.0
  *
  * @param disabledSupport
  *   Whether code action supports the `disabled` property.
  *
  * since 3.16.0
  *
  * @param dataSupport
  *   Whether code action supports the `data` property which is preserved
  *   between a `textDocument/codeAction` and a `codeAction/resolve` request.
  *
  * since 3.16.0
  *
  * @param resolveSupport
  *   Whether the client supports resolving additional code action properties
  *   via a separate `codeAction/resolve` request.
  *
  * since 3.16.0
  *
  * @param honorsChangeAnnotations
  *   Whether the client honors the change annotations in text edits and
  *   resource operations returned via the `CodeAction#edit` property by for
  *   example presenting the workspace edit in the user interface and asking for
  *   confirmation.
  *
  * since 3.16.0
  */
case class CodeActionClientCapabilities(
    dynamicRegistration: Option[Boolean] = None,
    codeActionLiteralSupport: Option[
      CodeActionClientCapabilities.CodeActionLiteralSupport
    ] = None,
    isPreferredSupport: Option[Boolean] = None,
    disabledSupport: Option[Boolean] = None,
    dataSupport: Option[Boolean] = None,
    resolveSupport: Option[CodeActionClientCapabilities.ResolveSupport] = None,
    honorsChangeAnnotations: Option[Boolean] = None
)
object CodeActionClientCapabilities
    extends codecs.structures_CodeActionClientCapabilitiesCodec:
  /** @param codeActionKind
    *   The code action kind is support with the following value set.
    */
  case class CodeActionLiteralSupport(
      codeActionKind: CodeActionLiteralSupport.CodeActionKind
  )
  object CodeActionLiteralSupport
      extends codecs.structures_CodeActionClientCapabilities_CodeActionLiteralSupportCodec:
    /** @param valueSet
      *   The code action kind values the client supports. When this property
      *   exists the client also guarantees that it will handle values outside
      *   its set gracefully and falls back to a default value when unknown.
      */
    case class CodeActionKind(
        valueSet: Vector[enumerations.CodeActionKind]
    )
    object CodeActionKind
        extends codecs.structures_CodeActionClientCapabilities_CodeActionLiteralSupport_CodeActionKindCodec
  end CodeActionLiteralSupport

  /** @param properties
    *   The properties that a client can resolve lazily.
    */
  case class ResolveSupport(
      properties: Vector[String]
  )
  object ResolveSupport
      extends codecs.structures_CodeActionClientCapabilities_ResolveSupportCodec
end CodeActionClientCapabilities
