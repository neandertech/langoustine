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

/** Defines whether the insert text in a completion item should be interpreted
  * as plain text or a snippet.
  */
opaque type InsertTextFormat = runtime.uinteger
object InsertTextFormat extends UIntEnum[InsertTextFormat]:
  /** The primary text to be inserted is treated as a plain string.
    */
  val PlainText = entry(1)

  /** The primary text to be inserted is treated as a snippet.
    *
    * A snippet can define tab stops and placeholders with `$1`, `$2` and
    * `${3:foo}`. `$0` defines the final tab stop, it defaults to the end of the
    * snippet. Placeholders with equal identifiers are linked, that is typing in
    * one will update others too.
    *
    * See also:
    * https://microsoft.github.io/language-server-protocol/specifications/specification-current/#snippet_syntax
    */
  val Snippet      = entry(2)
  override def ALL = Set(
    PlainText,
    Snippet
  )

  extension (self: InsertTextFormat)
    def name: String = (self.value: @switch) match
      case 1 => "PlainText"
      case 2 => "Snippet"
end InsertTextFormat
