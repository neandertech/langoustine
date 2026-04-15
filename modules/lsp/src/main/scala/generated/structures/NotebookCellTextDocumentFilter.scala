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

/** A notebook cell text document filter denotes a cell text document by
  * different properties.
  *
  * @since 3.17.0
  *
  * @param notebook
  *   A filter that matches against the notebook containing the notebook cell.
  *   If a string value is provided it matches against the notebook type. '*'
  *   matches every notebook.
  *
  * @param language
  *   A language id like `python`.
  *
  * Will be matched against the language id of the notebook cell document. '*'
  * matches every language.
  */
case class NotebookCellTextDocumentFilter(
    notebook: (String | aliases.NotebookDocumentFilter),
    language: Option[String] = None
)
object NotebookCellTextDocumentFilter
    extends codecs.structures_NotebookCellTextDocumentFilterCodec
