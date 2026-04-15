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

opaque type TextDocumentFilter =
  (TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)
object TextDocumentFilter extends codecs.aliases_TextDocumentFilter:
  inline def apply(v: TextDocumentFilter.S0): TextDocumentFilter = v
  inline def apply(v: TextDocumentFilter.S1): TextDocumentFilter = v
  inline def apply(v: TextDocumentFilter.S2): TextDocumentFilter = v

  extension (v: TextDocumentFilter)
    inline def value: (TextDocumentFilter.S0 | TextDocumentFilter.S1 |
      TextDocumentFilter.S2) = v

  given Typeable[TextDocumentFilter] with
    def unapply(s: Any): Option[s.type & TextDocumentFilter] =
      s match
        case c: TextDocumentFilter.S0 =>
          Some(c.asInstanceOf[s.type & TextDocumentFilter.S0])
        case c: TextDocumentFilter.S1 =>
          Some(c.asInstanceOf[s.type & TextDocumentFilter.S1])
        case c: TextDocumentFilter.S2 =>
          Some(c.asInstanceOf[s.type & TextDocumentFilter.S2])
        case _ => Option.empty
  end given

  /** @param language
    *   A language id, like `typescript`.
    *
    * @param scheme
    *   A Uri {@link Uri.scheme scheme}, like `file` or `untitled`.
    *
    * @param pattern
    *   A glob pattern, like **​.{ts,js}. See TextDocumentFilter for examples.
    */
  case class S0(
      language: String,
      scheme: Option[String] = None,
      pattern: Option[String] = None
  )
  object S0 extends codecs.aliases_TextDocumentFilter_S0Codec

  /** @param language
    *   A language id, like `typescript`.
    *
    * @param scheme
    *   A Uri {@link Uri.scheme scheme}, like `file` or `untitled`.
    *
    * @param pattern
    *   A glob pattern, like **​.{ts,js}. See TextDocumentFilter for examples.
    */
  case class S1(
      language: Option[String] = None,
      scheme: String,
      pattern: Option[String] = None
  )
  object S1 extends codecs.aliases_TextDocumentFilter_S1Codec

  /** @param language
    *   A language id, like `typescript`.
    *
    * @param scheme
    *   A Uri {@link Uri.scheme scheme}, like `file` or `untitled`.
    *
    * @param pattern
    *   A glob pattern, like **​.{ts,js}. See TextDocumentFilter for examples.
    */
  case class S2(
      language: Option[String] = None,
      scheme: Option[String] = None,
      pattern: String
  )
  object S2 extends codecs.aliases_TextDocumentFilter_S2Codec
end TextDocumentFilter
