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

/** How a completion was triggered
  */
opaque type CompletionTriggerKind = runtime.uinteger
object CompletionTriggerKind extends UIntEnum[CompletionTriggerKind]:
  /** Completion was triggered by typing an identifier (24x7 code complete),
    * manual invocation (e.g Ctrl+Space) or via API.
    */
  val Invoked = entry(1)

  /** Completion was triggered by a trigger character specified by the
    * `triggerCharacters` properties of the `CompletionRegistrationOptions`.
    */
  val TriggerCharacter = entry(2)

  /** Completion was re-triggered as current completion list is incomplete
    */
  val TriggerForIncompleteCompletions = entry(3)
  override def ALL                    = Set(
    Invoked,
    TriggerCharacter,
    TriggerForIncompleteCompletions
  )

  extension (self: CompletionTriggerKind)
    def name: String = (self.value: @switch) match
      case 1 => "Invoked"
      case 2 => "TriggerCharacter"
      case 3 => "TriggerForIncompleteCompletions"
end CompletionTriggerKind
