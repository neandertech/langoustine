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

opaque type FailureHandlingKind = String
object FailureHandlingKind extends StringEnum[FailureHandlingKind]:
  /** Applying the workspace change is simply aborted if one of the changes
    * provided fails. All operations executed before the failing operation stay
    * executed.
    */
  val Abort = entry("abort")

  /** All operations are executed transactional. That means they either all
    * succeed or no changes at all are applied to the workspace.
    */
  val Transactional = entry("transactional")

  /** If the workspace edit contains only textual file changes they are executed
    * transactional. If resource changes (create, rename or delete file) are
    * part of the change the failure handling strategy is abort.
    */
  val TextOnlyTransactional = entry("textOnlyTransactional")

  /** The client tries to undo the operations already executed. But there is no
    * guarantee that this is succeeding.
    */
  val Undo         = entry("undo")
  override def ALL = Set(
    Abort,
    Transactional,
    TextOnlyTransactional,
    Undo
  )
end FailureHandlingKind
