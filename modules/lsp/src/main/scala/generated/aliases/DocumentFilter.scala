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
package aliases

import langoustine.*
import runtime.{*, given}
import io.circe.*
import scala.reflect.*

/**  A document filter describes a top level text document or
  *  a notebook cell document.
  *
  *  @since 3.17.0 - proposed support for NotebookCellTextDocumentFilter.
  */
opaque type DocumentFilter =
  (aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)
object DocumentFilter extends codecs.aliases_DocumentFilter:
  inline def apply(v: aliases.TextDocumentFilter): DocumentFilter = v
  inline def apply(
      v: structures.NotebookCellTextDocumentFilter
  ): DocumentFilter = v

  extension (v: DocumentFilter)
    inline def value: (aliases.TextDocumentFilter |
      structures.NotebookCellTextDocumentFilter) = v

  given Typeable[DocumentFilter] with
    def unapply(s: Any): Option[s.type & DocumentFilter] =
      s match
        case c: aliases.TextDocumentFilter =>
          Some(c.asInstanceOf[s.type & aliases.TextDocumentFilter])
        case c: structures.NotebookCellTextDocumentFilter =>
          Some(
            c.asInstanceOf[s.type & structures.NotebookCellTextDocumentFilter]
          )
        case _ => Option.empty
  end given
end DocumentFilter
