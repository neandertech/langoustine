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

/** A document highlight kind.
  */
opaque type DocumentHighlightKind = runtime.uinteger
object DocumentHighlightKind extends UIntEnum[DocumentHighlightKind]:
  /** A textual occurrence.
    */
  val Text = entry(1)

  /** Read-access of a symbol, like reading a variable.
    */
  val Read = entry(2)

  /** Write-access of a symbol, like writing to a variable.
    */
  val Write        = entry(3)
  override def ALL = Set(
    Text,
    Read,
    Write
  )

  extension (self: DocumentHighlightKind)
    def name: String = (self.value: @switch) match
      case 1 => "Text"
      case 2 => "Read"
      case 3 => "Write"
end DocumentHighlightKind
