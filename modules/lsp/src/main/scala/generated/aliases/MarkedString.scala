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

opaque type MarkedString = (String | MarkedString.S0)
object MarkedString extends codecs.aliases_MarkedString:
  inline def apply(v: String): MarkedString          = v
  inline def apply(v: MarkedString.S0): MarkedString = v

  extension (v: MarkedString) inline def value: (String | MarkedString.S0) = v

  given Typeable[MarkedString] with
    def unapply(s: Any): Option[s.type & MarkedString] =
      s match
        case c: String          => Some(c.asInstanceOf[s.type & String])
        case c: MarkedString.S0 =>
          Some(c.asInstanceOf[s.type & MarkedString.S0])
        case _ => Option.empty
  case class S0(
      language: String,
      value: String
  )
  object S0 extends codecs.aliases_MarkedString_S0Codec
end MarkedString
