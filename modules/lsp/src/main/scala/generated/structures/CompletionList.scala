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

/** Represents a collection of {@link CompletionItem completion items} to be
  * presented in the editor.
  *
  * @param isIncomplete
  *   This list it not complete. Further typing results in recomputing this
  *   list.
  *
  * Recomputed lists have all their items replaced (not appended) in the
  * incomplete completion sessions.
  *
  * @param itemDefaults
  *   In many cases the items of an actual completion result share the same
  *   value for properties like `commitCharacters` or the range of a text edit.
  *   A completion list can therefore define item defaults which will be used if
  *   a completion item itself doesn't specify the value.
  *
  * If a completion list specifies a default value and a completion item also
  * specifies a corresponding value the one from the item is used.
  *
  * Servers are only allowed to return default values if the client signals
  * support for this via the `completionList.itemDefaults` capability.
  *
  * since 3.17.0
  *
  * @param items
  *   The completion items.
  */
case class CompletionList(
    isIncomplete: Boolean,
    itemDefaults: Option[CompletionList.ItemDefaults] = None,
    items: Vector[structures.CompletionItem]
)
object CompletionList extends codecs.structures_CompletionListCodec:
  /** @param commitCharacters
    *   A default commit character set.
    *
    * since 3.17.0
    *
    * @param editRange
    *   A default edit range.
    *
    * since 3.17.0
    *
    * @param insertTextFormat
    *   A default insert text format.
    *
    * since 3.17.0
    *
    * @param insertTextMode
    *   A default insert text mode.
    *
    * since 3.17.0
    *
    * @param data
    *   A default data value.
    *
    * since 3.17.0
    */
  case class ItemDefaults(
      commitCharacters: Option[Vector[String]] = None,
      editRange: Option[(structures.Range | ItemDefaults.S0)] = None,
      insertTextFormat: Option[enumerations.InsertTextFormat] = None,
      insertTextMode: Option[enumerations.InsertTextMode] = None,
      data: Option[io.circe.Json] = None
  )
  object ItemDefaults
      extends codecs.structures_CompletionList_ItemDefaultsCodec:
    case class S0(
        insert: structures.Range,
        replace: structures.Range
    )
    object S0 extends codecs.structures_CompletionList_ItemDefaults_S0Codec
end CompletionList
