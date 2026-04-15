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

/** A change event for a notebook document.
  *
  * @since 3.17.0
  *
  * @param metadata
  *   The changed meta data if any.
  *
  * Note: should always be an object literal (e.g. LSPObject)
  *
  * @param cells
  *   Changes to cells
  */
case class NotebookDocumentChangeEvent(
    metadata: Option[aliases.LSPObject] = None,
    cells: Option[NotebookDocumentChangeEvent.Cells] = None
)
object NotebookDocumentChangeEvent
    extends codecs.structures_NotebookDocumentChangeEventCodec:
  /** @param structure
    *   Changes to the cell structure to add or remove cells.
    *
    * @param data
    *   Changes to notebook cells properties like its kind, execution summary or
    *   metadata.
    *
    * @param textContent
    *   Changes to the text content of notebook cells.
    */
  case class Cells(
      structure: Option[Cells.Structure] = None,
      data: Option[Vector[structures.NotebookCell]] = None,
      textContent: Option[Vector[Cells.S0]] = None
  )
  object Cells extends codecs.structures_NotebookDocumentChangeEvent_CellsCodec:
    /** @param array
      *   The change to the cell array.
      *
      * @param didOpen
      *   Additional opened cell text documents.
      *
      * @param didClose
      *   Additional closed cell text documents.
      */
    case class Structure(
        array: structures.NotebookCellArrayChange,
        didOpen: Option[Vector[structures.TextDocumentItem]] = None,
        didClose: Option[Vector[structures.TextDocumentIdentifier]] = None
    )
    object Structure
        extends codecs.structures_NotebookDocumentChangeEvent_Cells_StructureCodec
    case class S0(
        document: structures.VersionedTextDocumentIdentifier,
        changes: Vector[aliases.TextDocumentContentChangeEvent]
    )
    object S0
        extends codecs.structures_NotebookDocumentChangeEvent_Cells_S0Codec
  end Cells
end NotebookDocumentChangeEvent
