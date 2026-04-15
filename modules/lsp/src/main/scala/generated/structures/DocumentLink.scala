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

/** A document link is a range in a text document that links to an internal or
  * external resource, like another text document or a web site.
  *
  * @param range
  *   The range this link applies to.
  *
  * @param target
  *   The uri this link points to. If missing a resolve request is sent later.
  *
  * @param tooltip
  *   The tooltip text when you hover over this link.
  *
  * If a tooltip is provided, is will be displayed in a string that includes
  * instructions on how to trigger the link, such as `{0} (ctrl + click)`. The
  * specific instructions vary depending on OS, user settings, and localization.
  *
  * since 3.15.0
  *
  * @param data
  *   A data entry field that is preserved on a document link between a
  *   DocumentLinkRequest and a DocumentLinkResolveRequest.
  */
case class DocumentLink(
    range: structures.Range,
    target: Option[runtime.Uri] = None,
    tooltip: Option[String] = None,
    data: Option[io.circe.Json] = None
)
object DocumentLink extends codecs.structures_DocumentLinkCodec
