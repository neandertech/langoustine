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

/** Inlay hint information.
  *
  * @since 3.17.0
  *
  * @param position
  *   The position of this hint.
  *
  * If multiple hints have the same position, they will be shown in the order
  * they appear in the response.
  *
  * @param label
  *   The label of this hint. A human readable string or an array of
  *   InlayHintLabelPart label parts.
  *
  * *Note* that neither the string nor the label part can be empty.
  *
  * @param kind
  *   The kind of this hint. Can be omitted in which case the client should fall
  *   back to a reasonable default.
  *
  * @param textEdits
  *   Optional text edits that are performed when accepting this inlay hint.
  *
  * *Note* that edits are expected to change the document so that the inlay hint
  * (or its nearest variant) is now part of the document and the inlay hint
  * itself is now obsolete.
  *
  * @param tooltip
  *   The tooltip text when you hover over this item.
  *
  * @param paddingLeft
  *   Render padding before the hint.
  *
  * Note: Padding should use the editor's background color, not the background
  * color of the hint itself. That means padding can be used to visually
  * align/separate an inlay hint.
  *
  * @param paddingRight
  *   Render padding after the hint.
  *
  * Note: Padding should use the editor's background color, not the background
  * color of the hint itself. That means padding can be used to visually
  * align/separate an inlay hint.
  *
  * @param data
  *   A data entry field that is preserved on an inlay hint between a
  *   `textDocument/inlayHint` and a `inlayHint/resolve` request.
  */
case class InlayHint(
    position: structures.Position,
    label: (String | Vector[structures.InlayHintLabelPart]),
    kind: Option[enumerations.InlayHintKind] = None,
    textEdits: Option[Vector[structures.TextEdit]] = None,
    tooltip: Option[(String | structures.MarkupContent)] = None,
    paddingLeft: Option[Boolean] = None,
    paddingRight: Option[Boolean] = None,
    data: Option[io.circe.Json] = None
)
object InlayHint extends codecs.structures_InlayHintCodec
