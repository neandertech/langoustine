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

opaque type ProgressToken = (Int | String)
object ProgressToken extends codecs.aliases_ProgressToken:
  inline def apply(v: Int): ProgressToken    = v
  inline def apply(v: String): ProgressToken = v

  extension (v: ProgressToken) inline def value: (Int | String) = v

  given Typeable[ProgressToken] with
    def unapply(s: Any): Option[s.type & ProgressToken] =
      s match
        case c: Int    => Some(c.asInstanceOf[s.type & Int])
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _         => Option.empty
end ProgressToken
