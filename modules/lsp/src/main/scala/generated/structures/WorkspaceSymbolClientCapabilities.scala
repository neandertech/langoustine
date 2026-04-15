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

/** Client capabilities for a {@link WorkspaceSymbolRequest}.
  *
  * @param dynamicRegistration
  *   Symbol request supports dynamic registration.
  *
  * @param symbolKind
  *   Specific capabilities for the `SymbolKind` in the `workspace/symbol`
  *   request.
  *
  * @param tagSupport
  *   The client supports tags on `SymbolInformation`. Clients supporting tags
  *   have to handle unknown tags gracefully.
  *
  * since 3.16.0
  *
  * @param resolveSupport
  *   The client support partial workspace symbols. The client will send the
  *   request `workspaceSymbol/resolve` to the server to resolve additional
  *   properties.
  *
  * since 3.17.0
  */
case class WorkspaceSymbolClientCapabilities(
    dynamicRegistration: Option[Boolean] = None,
    symbolKind: Option[WorkspaceSymbolClientCapabilities.SymbolKind] = None,
    tagSupport: Option[WorkspaceSymbolClientCapabilities.TagSupport] = None,
    resolveSupport: Option[WorkspaceSymbolClientCapabilities.ResolveSupport] =
      None
)
object WorkspaceSymbolClientCapabilities
    extends codecs.structures_WorkspaceSymbolClientCapabilitiesCodec:
  /** @param valueSet
    *   The symbol kind values the client supports. When this property exists
    *   the client also guarantees that it will handle values outside its set
    *   gracefully and falls back to a default value when unknown.
    *
    * If this property is not present the client only supports the symbol kinds
    * from `File` to `Array` as defined in the initial version of the protocol.
    */
  case class SymbolKind(
      valueSet: Option[Vector[enumerations.SymbolKind]] = None
  )
  object SymbolKind
      extends codecs.structures_WorkspaceSymbolClientCapabilities_SymbolKindCodec

  /** @param valueSet
    *   The tags supported by the client.
    */
  case class TagSupport(
      valueSet: Vector[enumerations.SymbolTag]
  )
  object TagSupport
      extends codecs.structures_WorkspaceSymbolClientCapabilities_TagSupportCodec

  /** @param properties
    *   The properties that a client can resolve lazily. Usually
    *   `location.range`
    */
  case class ResolveSupport(
      properties: Vector[String]
  )
  object ResolveSupport
      extends codecs.structures_WorkspaceSymbolClientCapabilities_ResolveSupportCodec
end WorkspaceSymbolClientCapabilities
