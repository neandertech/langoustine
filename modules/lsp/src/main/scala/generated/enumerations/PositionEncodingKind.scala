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

package langoustine.lsp
package enumerations

import runtime.{*, given}
import io.circe.*
import scala.reflect.Typeable
import scala.annotation.switch

/** A set of predefined position encoding kinds.
  *
  * @since 3.17.0
  */
opaque type PositionEncodingKind = String
object PositionEncodingKind extends StringEnum[PositionEncodingKind]:
  /** Character offsets count UTF-8 code units (e.g. bytes).
    */
  val UTF8 = entry("utf-8")

  /** Character offsets count UTF-16 code units.
    *
    * This is the default and must always be supported by servers
    */
  val UTF16 = entry("utf-16")

  /** Character offsets count UTF-32 code units.
    *
    * Implementation note: these are the same as Unicode codepoints, so this
    * `PositionEncodingKind` may also be used for an encoding-agnostic
    * representation of character offsets.
    */
  val UTF32        = entry("utf-32")
  override def ALL = Set(
    UTF8,
    UTF16,
    UTF32
  )
end PositionEncodingKind
