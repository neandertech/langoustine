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

/** A special workspace symbol that supports locations without a range.
  *
  * See also SymbolInformation.
  *
  * @since 3.17.0
  *
  * @param location
  *   The location of the symbol. Whether a server is allowed to return a
  *   location without a range depends on the client capability
  *   `workspace.symbol.resolveSupport`.
  *
  * See SymbolInformation#location for more details.
  *
  * @param data
  *   A data entry field that is preserved on a workspace symbol between a
  *   workspace symbol request and a workspace symbol resolve request.
  *
  * @param name
  *   The name of this symbol.
  *
  * @param kind
  *   The kind of this symbol.
  *
  * @param tags
  *   Tags for this symbol.
  *
  * since 3.16.0
  *
  * @param containerName
  *   The name of the symbol containing this symbol. This information is for
  *   user interface purposes (e.g. to render a qualifier in the user interface
  *   if necessary). It can't be used to re-infer a hierarchy for the document
  *   symbols.
  */
case class WorkspaceSymbol(
    location: (structures.Location | WorkspaceSymbol.S0),
    data: Option[io.circe.Json] = None,
    name: String,
    kind: enumerations.SymbolKind,
    tags: Option[Vector[enumerations.SymbolTag]] = None,
    containerName: Option[String] = None
)
object WorkspaceSymbol extends codecs.structures_WorkspaceSymbolCodec:
  case class S0(
      uri: runtime.DocumentUri
  )
  object S0 extends codecs.structures_WorkspaceSymbol_S0Codec
