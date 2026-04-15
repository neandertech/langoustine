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

/** How a signature help was triggered.
  *
  * @since 3.15.0
  */
opaque type SignatureHelpTriggerKind = runtime.uinteger
object SignatureHelpTriggerKind extends UIntEnum[SignatureHelpTriggerKind]:
  /** Signature help was invoked manually by the user or by a command.
    */
  val Invoked = entry(1)

  /** Signature help was triggered by a trigger character.
    */
  val TriggerCharacter = entry(2)

  /** Signature help was triggered by the cursor moving or by the document
    * content changing.
    */
  val ContentChange = entry(3)
  override def ALL  = Set(
    Invoked,
    TriggerCharacter,
    ContentChange
  )

  extension (self: SignatureHelpTriggerKind)
    def name: String = (self.value: @switch) match
      case 1 => "Invoked"
      case 2 => "TriggerCharacter"
      case 3 => "ContentChange"
end SignatureHelpTriggerKind
