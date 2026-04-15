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

opaque type GlobPattern = (aliases.Pattern | structures.RelativePattern)
object GlobPattern extends codecs.aliases_GlobPattern:
  inline def apply(v: aliases.Pattern): GlobPattern            = v
  inline def apply(v: structures.RelativePattern): GlobPattern = v

  extension (v: GlobPattern)
    inline def value: (aliases.Pattern | structures.RelativePattern) = v

  given Typeable[GlobPattern] with
    def unapply(s: Any): Option[s.type & GlobPattern] =
      s match
        case c: aliases.Pattern =>
          Some(c.asInstanceOf[s.type & aliases.Pattern])
        case c: structures.RelativePattern =>
          Some(c.asInstanceOf[s.type & structures.RelativePattern])
        case _ => Option.empty
end GlobPattern
