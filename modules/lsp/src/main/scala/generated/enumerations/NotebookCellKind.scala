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

/** A notebook cell kind.
  *
  * @since 3.17.0
  */
opaque type NotebookCellKind = runtime.uinteger
object NotebookCellKind extends UIntEnum[NotebookCellKind]:
  /** A markup-cell is formatted source that is used for display.
    */
  val Markup = entry(1)

  /** A code-cell is source code.
    */
  val Code         = entry(2)
  override def ALL = Set(
    Markup,
    Code
  )

  extension (self: NotebookCellKind)
    def name: String = (self.value: @switch) match
      case 1 => "Markup"
      case 2 => "Code"
end NotebookCellKind
