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

/** Represents programming constructs like variables, classes, interfaces etc.
  * that appear in a document. Document symbols can be hierarchical and they
  * have two ranges: one that encloses its definition and one that points to its
  * most interesting range, e.g. the range of an identifier.
  *
  * @param name
  *   The name of this symbol. Will be displayed in the user interface and
  *   therefore must not be an empty string or a string only consisting of white
  *   spaces.
  *
  * @param detail
  *   More detail for this symbol, e.g the signature of a function.
  *
  * @param kind
  *   The kind of this symbol.
  *
  * @param tags
  *   Tags for this document symbol.
  *
  * since 3.16.0
  *
  * @param deprecated
  *   Indicates if this symbol is deprecated.
  *
  * @deprecated
  *   Use tags instead
  *
  * @param range
  *   The range enclosing this symbol not including leading/trailing whitespace
  *   but everything else like comments. This information is typically used to
  *   determine if the clients cursor is inside the symbol to reveal in the
  *   symbol in the UI.
  *
  * @param selectionRange
  *   The range that should be selected and revealed when this symbol is being
  *   picked, e.g the name of a function. Must be contained by the `range`.
  *
  * @param children
  *   Children of this symbol, e.g. properties of a class.
  */
case class DocumentSymbol(
    name: String,
    detail: Option[String] = None,
    kind: enumerations.SymbolKind,
    tags: Option[Vector[enumerations.SymbolTag]] = None,
    deprecated: Option[Boolean] = None,
    range: structures.Range,
    selectionRange: structures.Range,
    children: Option[Vector[structures.DocumentSymbol]] = None
)
object DocumentSymbol extends codecs.structures_DocumentSymbolCodec
