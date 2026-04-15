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

/** @since 3.17.0
  *
  * @param name
  *   The name of this item.
  *
  * @param kind
  *   The kind of this item.
  *
  * @param tags
  *   Tags for this item.
  *
  * @param detail
  *   More detail for this item, e.g. the signature of a function.
  *
  * @param uri
  *   The resource identifier of this item.
  *
  * @param range
  *   The range enclosing this symbol not including leading/trailing whitespace
  *   but everything else, e.g. comments and code.
  *
  * @param selectionRange
  *   The range that should be selected and revealed when this symbol is being
  *   picked, e.g. the name of a function. Must be contained by the
  *   {@link TypeHierarchyItem.range `range`}.
  *
  * @param data
  *   A data entry field that is preserved between a type hierarchy prepare and
  *   supertypes or subtypes requests. It could also be used to identify the
  *   type hierarchy in the server, helping improve the performance on resolving
  *   supertypes and subtypes.
  */
case class TypeHierarchyItem(
    name: String,
    kind: enumerations.SymbolKind,
    tags: Option[Vector[enumerations.SymbolTag]] = None,
    detail: Option[String] = None,
    uri: runtime.DocumentUri,
    range: structures.Range,
    selectionRange: structures.Range,
    data: Option[io.circe.Json] = None
)
object TypeHierarchyItem extends codecs.structures_TypeHierarchyItemCodec
