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

/** @param label
  *   The label of this color presentation. It will be shown on the color picker
  *   header. By default this is also the text that is inserted when selecting
  *   this color presentation.
  *
  * @param textEdit
  *   An {@link TextEdit edit} which is applied to a document when selecting
  *   this presentation for the color. When `falsy` the
  *   {@link ColorPresentation.label label} is used.
  *
  * @param additionalTextEdits
  *   An optional array of additional {@link TextEdit text edits} that are
  *   applied when selecting this color presentation. Edits must not overlap
  *   with the main {@link ColorPresentation.textEdit edit} nor with themselves.
  */
case class ColorPresentation(
    label: String,
    textEdit: Option[structures.TextEdit] = None,
    additionalTextEdits: Option[Vector[structures.TextEdit]] = None
)
object ColorPresentation extends codecs.structures_ColorPresentationCodec
