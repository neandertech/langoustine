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

/** Options specific to a notebook plus its cells to be synced to the server.
  *
  * If a selector provides a notebook document filter but no cell selector all
  * cells of a matching notebook document will be synced.
  *
  * If a selector provides no notebook document filter but only a cell selector
  * all notebook document that contain at least one matching cell will be
  * synced.
  *
  * @since 3.17.0
  *
  * @param notebookSelector
  *   The notebooks to be synced
  *
  * @param save
  *   Whether save notification should be forwarded to the server. Will only be
  *   honored if mode === `notebook`.
  */
case class NotebookDocumentSyncOptions(
    notebookSelector: Vector[
      (NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)
    ],
    save: Option[Boolean] = None
)
object NotebookDocumentSyncOptions
    extends codecs.structures_NotebookDocumentSyncOptionsCodec:
  /** @param notebook
    *   The notebook to be synced If a string value is provided it matches
    *   against the notebook type. '*' matches every notebook.
    *
    * @param cells
    *   The cells of the matching notebook to be synced.
    */
  case class S0(
      notebook: (String | aliases.NotebookDocumentFilter),
      cells: Option[Vector[S0.S0]] = None
  )
  object S0 extends codecs.structures_NotebookDocumentSyncOptions_S0Codec:
    case class S0(
        language: String
    )
    object S0 extends codecs.structures_NotebookDocumentSyncOptions_S0_S0Codec

  /** @param notebook
    *   The notebook to be synced If a string value is provided it matches
    *   against the notebook type. '*' matches every notebook.
    *
    * @param cells
    *   The cells of the matching notebook to be synced.
    */
  case class S1(
      notebook: Option[(String | aliases.NotebookDocumentFilter)] = None,
      cells: Vector[S1.S0]
  )
  object S1 extends codecs.structures_NotebookDocumentSyncOptions_S1Codec:
    case class S0(
        language: String
    )
    object S0 extends codecs.structures_NotebookDocumentSyncOptions_S1_S0Codec
end NotebookDocumentSyncOptions
