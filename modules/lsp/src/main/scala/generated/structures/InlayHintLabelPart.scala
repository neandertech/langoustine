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

/** An inlay hint label part allows for interactive and composite labels of
  * inlay hints.
  *
  * @since 3.17.0
  *
  * @param value
  *   The value of this label part.
  *
  * @param tooltip
  *   The tooltip text when you hover over this label part. Depending on the
  *   client capability `inlayHint.resolveSupport` clients might resolve this
  *   property late using the resolve request.
  *
  * @param location
  *   An optional source code location that represents this label part.
  *
  * The editor will use this location for the hover and for code navigation
  * features: This part will become a clickable link that resolves to the
  * definition of the symbol at the given location (not necessarily the location
  * itself), it shows the hover that shows at the given location, and it shows a
  * context menu with further code navigation commands.
  *
  * Depending on the client capability `inlayHint.resolveSupport` clients might
  * resolve this property late using the resolve request.
  *
  * @param command
  *   An optional command for this label part.
  *
  * Depending on the client capability `inlayHint.resolveSupport` clients might
  * resolve this property late using the resolve request.
  */
case class InlayHintLabelPart(
    value: String,
    tooltip: Option[(String | structures.MarkupContent)] = None,
    location: Option[structures.Location] = None,
    command: Option[structures.Command] = None
)
object InlayHintLabelPart extends codecs.structures_InlayHintLabelPartCodec
