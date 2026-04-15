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

/** Represents reasons why a text document is saved.
  */
opaque type TextDocumentSaveReason = runtime.uinteger
object TextDocumentSaveReason extends UIntEnum[TextDocumentSaveReason]:
  /** Manually triggered, e.g. by the user pressing save, by starting debugging,
    * or by an API call.
    */
  val Manual = entry(1)

  /** Automatic after a delay.
    */
  val AfterDelay = entry(2)

  /** When the editor lost focus.
    */
  val FocusOut     = entry(3)
  override def ALL = Set(
    Manual,
    AfterDelay,
    FocusOut
  )

  extension (self: TextDocumentSaveReason)
    def name: String = (self.value: @switch) match
      case 1 => "Manual"
      case 2 => "AfterDelay"
      case 3 => "FocusOut"
end TextDocumentSaveReason
