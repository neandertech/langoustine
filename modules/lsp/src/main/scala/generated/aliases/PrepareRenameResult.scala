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

opaque type PrepareRenameResult =
  (structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)
object PrepareRenameResult extends codecs.aliases_PrepareRenameResult:
  inline def apply(v: structures.Range): PrepareRenameResult       = v
  inline def apply(v: PrepareRenameResult.S0): PrepareRenameResult = v
  inline def apply(v: PrepareRenameResult.S1): PrepareRenameResult = v

  extension (v: PrepareRenameResult)
    inline def value
        : (structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1) =
      v

  given Typeable[PrepareRenameResult] with
    def unapply(s: Any): Option[s.type & PrepareRenameResult] =
      s match
        case c: structures.Range =>
          Some(c.asInstanceOf[s.type & structures.Range])
        case c: PrepareRenameResult.S0 =>
          Some(c.asInstanceOf[s.type & PrepareRenameResult.S0])
        case c: PrepareRenameResult.S1 =>
          Some(c.asInstanceOf[s.type & PrepareRenameResult.S1])
        case _ => Option.empty
  end given
  case class S0(
      range: structures.Range,
      placeholder: String
  )
  object S0 extends codecs.aliases_PrepareRenameResult_S0Codec
  case class S1(
      defaultBehavior: Boolean
  )
  object S1 extends codecs.aliases_PrepareRenameResult_S1Codec
end PrepareRenameResult
