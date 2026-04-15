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

opaque type LSPObject = Map[String, io.circe.Json]
object LSPObject extends codecs.aliases_LSPObject:
  inline def apply(v: Map[String, io.circe.Json]): LSPObject = v

  extension (v: LSPObject) inline def value: Map[String, io.circe.Json] = v

  given Typeable[LSPObject] with
    def unapply(s: Any): Option[s.type & LSPObject] =
      s match
        case c: Map[String, io.circe.Json] =>
          Some(c.asInstanceOf[s.type & Map[String, io.circe.Json]])
        case _ => Option.empty
end LSPObject
