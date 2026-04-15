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

/** An event describing a change to a text document. If only a text is provided
  * it is considered to be the full content of the document.
  */
opaque type TextDocumentContentChangeEvent =
  (TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)
object TextDocumentContentChangeEvent
    extends codecs.aliases_TextDocumentContentChangeEvent:
  inline def apply(
      v: TextDocumentContentChangeEvent.S0
  ): TextDocumentContentChangeEvent = v
  inline def apply(
      v: TextDocumentContentChangeEvent.S1
  ): TextDocumentContentChangeEvent = v

  extension (v: TextDocumentContentChangeEvent)
    inline def value: (TextDocumentContentChangeEvent.S0 |
      TextDocumentContentChangeEvent.S1) = v

  given Typeable[TextDocumentContentChangeEvent] with
    def unapply(s: Any): Option[s.type & TextDocumentContentChangeEvent] =
      s match
        case c: TextDocumentContentChangeEvent.S0 =>
          Some(c.asInstanceOf[s.type & TextDocumentContentChangeEvent.S0])
        case c: TextDocumentContentChangeEvent.S1 =>
          Some(c.asInstanceOf[s.type & TextDocumentContentChangeEvent.S1])
        case _ => Option.empty

  /** @param range
    *   The range of the document that changed.
    *
    * @param rangeLength
    *   The optional length of the range that got replaced.
    *
    * @deprecated
    *   use range instead.
    *
    * @param text
    *   The new text for the provided range.
    */
  case class S0(
      range: structures.Range,
      rangeLength: Option[runtime.uinteger] = None,
      text: String
  )
  object S0 extends codecs.aliases_TextDocumentContentChangeEvent_S0Codec

  /** @param text
    *   The new text of the whole document.
    */
  case class S1(
      text: String
  )
  object S1 extends codecs.aliases_TextDocumentContentChangeEvent_S1Codec
end TextDocumentContentChangeEvent
