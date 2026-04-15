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

/** A notebook cell.
  *
  * A cell's document URI must be unique across ALL notebook cells and can
  * therefore be used to uniquely identify a notebook cell or the cell's text
  * document.
  *
  * @since 3.17.0
  *
  * @param kind
  *   The cell's kind
  *
  * @param document
  *   The URI of the cell's text document content.
  *
  * @param metadata
  *   Additional metadata stored with the cell.
  *
  * Note: should always be an object literal (e.g. LSPObject)
  *
  * @param executionSummary
  *   Additional execution summary information if supported by the client.
  */
case class NotebookCell(
    kind: enumerations.NotebookCellKind,
    document: runtime.DocumentUri,
    metadata: Option[aliases.LSPObject] = None,
    executionSummary: Option[structures.ExecutionSummary] = None
)
object NotebookCell extends codecs.structures_NotebookCellCodec
