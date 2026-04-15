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

/** Represents information about programming constructs like variables, classes,
  * interfaces etc.
  *
  * @param deprecated
  *   Indicates if this symbol is deprecated.
  *
  * @deprecated
  *   Use tags instead
  *
  * @param location
  *   The location of this symbol. The location's range is used by a tool to
  *   reveal the location in the editor. If the symbol is selected in the tool
  *   the range's start information is used to position the cursor. So the range
  *   usually spans more than the actual symbol's name and does normally include
  *   things like visibility modifiers.
  *
  * The range doesn't have to denote a node range in the sense of an abstract
  * syntax tree. It can therefore not be used to re-construct a hierarchy of the
  * symbols.
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
case class SymbolInformation(
    deprecated: Option[Boolean] = None,
    location: structures.Location,
    name: String,
    kind: enumerations.SymbolKind,
    tags: Option[Vector[enumerations.SymbolTag]] = None,
    containerName: Option[String] = None
)
object SymbolInformation extends codecs.structures_SymbolInformationCodec
