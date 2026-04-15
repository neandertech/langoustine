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

/** Params to show a resource in the UI.
  *
  * @since 3.16.0
  *
  * @param uri
  *   The uri to show.
  *
  * @param external
  *   Indicates to show the resource in an external program. To show, for
  *   example, `https://code.visualstudio.com/` in the default WEB browser set
  *   `external` to `true`.
  *
  * @param takeFocus
  *   An optional property to indicate whether the editor showing the document
  *   should take focus or not. Clients might ignore this property if an
  *   external program is started.
  *
  * @param selection
  *   An optional selection range if the document is a text document. Clients
  *   might ignore the property if an external program is started or the file is
  *   not a text file.
  */
case class ShowDocumentParams(
    uri: runtime.Uri,
    external: Option[Boolean] = None,
    takeFocus: Option[Boolean] = None,
    selection: Option[structures.Range] = None
)
object ShowDocumentParams extends codecs.structures_ShowDocumentParamsCodec
