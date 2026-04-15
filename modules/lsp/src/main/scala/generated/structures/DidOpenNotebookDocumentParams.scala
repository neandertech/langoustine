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

/** The params sent in an open notebook document notification.
  *
  * @since 3.17.0
  *
  * @param notebookDocument
  *   The notebook document that got opened.
  *
  * @param cellTextDocuments
  *   The text documents that represent the content of a notebook cell.
  */
case class DidOpenNotebookDocumentParams(
    notebookDocument: structures.NotebookDocument,
    cellTextDocuments: Vector[structures.TextDocumentItem]
)
object DidOpenNotebookDocumentParams
    extends codecs.structures_DidOpenNotebookDocumentParamsCodec
