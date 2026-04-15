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

/** The reason why code actions were requested.
  *
  * @since 3.17.0
  */
opaque type CodeActionTriggerKind = runtime.uinteger
object CodeActionTriggerKind extends UIntEnum[CodeActionTriggerKind]:
  /** Code actions were explicitly requested by the user or by an extension.
    */
  val Invoked = entry(1)

  /** Code actions were requested automatically.
    *
    * This typically happens when current selection in a file changes, but can
    * also be triggered when file content changes.
    */
  val Automatic    = entry(2)
  override def ALL = Set(
    Invoked,
    Automatic
  )

  extension (self: CodeActionTriggerKind)
    def name: String = (self.value: @switch) match
      case 1 => "Invoked"
      case 2 => "Automatic"
end CodeActionTriggerKind
