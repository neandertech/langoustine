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

/** A notebook document.
  *
  * @since 3.17.0
  *
  * @param uri
  *   The notebook document's uri.
  *
  * @param notebookType
  *   The type of the notebook.
  *
  * @param version
  *   The version number of this document (it will increase after each change,
  *   including undo/redo).
  *
  * @param metadata
  *   Additional metadata stored with the notebook document.
  *
  * Note: should always be an object literal (e.g. LSPObject)
  *
  * @param cells
  *   The cells of a notebook.
  */
case class NotebookDocument(
    uri: runtime.Uri,
    notebookType: String,
    version: Int,
    metadata: Option[aliases.LSPObject] = None,
    cells: Vector[structures.NotebookCell]
)
object NotebookDocument extends codecs.structures_NotebookDocumentCodec
