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

/** Additional details for a completion item label.
  *
  * @since 3.17.0
  *
  * @param detail
  *   An optional string which is rendered less prominently directly after
  *   {@link CompletionItem.label label}, without any spacing. Should be used
  *   for function signatures and type annotations.
  *
  * @param description
  *   An optional string which is rendered less prominently after
  *   {@link CompletionItem.detail}. Should be used for fully qualified names
  *   and file paths.
  */
case class CompletionItemLabelDetails(
    detail: Option[String] = None,
    description: Option[String] = None
)
object CompletionItemLabelDetails
    extends codecs.structures_CompletionItemLabelDetailsCodec
