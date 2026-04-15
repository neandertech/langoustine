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

/** A notebook document filter denotes a notebook document by different
  * properties. The properties will be match against the notebook's URI (same as
  * with documents)
  *
  * @since 3.17.0
  */
opaque type NotebookDocumentFilter =
  (NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 |
    NotebookDocumentFilter.S2)
object NotebookDocumentFilter extends codecs.aliases_NotebookDocumentFilter:
  inline def apply(v: NotebookDocumentFilter.S0): NotebookDocumentFilter = v
  inline def apply(v: NotebookDocumentFilter.S1): NotebookDocumentFilter = v
  inline def apply(v: NotebookDocumentFilter.S2): NotebookDocumentFilter = v

  extension (v: NotebookDocumentFilter)
    inline def value: (NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 |
      NotebookDocumentFilter.S2) = v

  given Typeable[NotebookDocumentFilter] with
    def unapply(s: Any): Option[s.type & NotebookDocumentFilter] =
      s match
        case c: NotebookDocumentFilter.S0 =>
          Some(c.asInstanceOf[s.type & NotebookDocumentFilter.S0])
        case c: NotebookDocumentFilter.S1 =>
          Some(c.asInstanceOf[s.type & NotebookDocumentFilter.S1])
        case c: NotebookDocumentFilter.S2 =>
          Some(c.asInstanceOf[s.type & NotebookDocumentFilter.S2])
        case _ => Option.empty
  end given

  /** @param notebookType
    *   The type of the enclosing notebook.
    *
    * @param scheme
    *   A Uri {@link Uri.scheme scheme}, like `file` or `untitled`.
    *
    * @param pattern
    *   A glob pattern.
    */
  case class S0(
      notebookType: String,
      scheme: Option[String] = None,
      pattern: Option[String] = None
  )
  object S0 extends codecs.aliases_NotebookDocumentFilter_S0Codec

  /** @param notebookType
    *   The type of the enclosing notebook.
    *
    * @param scheme
    *   A Uri {@link Uri.scheme scheme}, like `file` or `untitled`.
    *
    * @param pattern
    *   A glob pattern.
    */
  case class S1(
      notebookType: Option[String] = None,
      scheme: String,
      pattern: Option[String] = None
  )
  object S1 extends codecs.aliases_NotebookDocumentFilter_S1Codec

  /** @param notebookType
    *   The type of the enclosing notebook.
    *
    * @param scheme
    *   A Uri {@link Uri.scheme scheme}, like `file` or `untitled`.
    *
    * @param pattern
    *   A glob pattern.
    */
  case class S2(
      notebookType: Option[String] = None,
      scheme: Option[String] = None,
      pattern: String
  )
  object S2 extends codecs.aliases_NotebookDocumentFilter_S2Codec
end NotebookDocumentFilter
