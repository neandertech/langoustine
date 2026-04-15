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

/** The diagnostic tags.
  *
  * @since 3.15.0
  */
opaque type DiagnosticTag = runtime.uinteger
object DiagnosticTag extends UIntEnum[DiagnosticTag]:
  /** Unused or unnecessary code.
    *
    * Clients are allowed to render diagnostics with this tag faded out instead
    * of having an error squiggle.
    */
  val Unnecessary = entry(1)

  /** Deprecated or obsolete code.
    *
    * Clients are allowed to rendered diagnostics with this tag strike through.
    */
  val Deprecated   = entry(2)
  override def ALL = Set(
    Unnecessary,
    Deprecated
  )

  extension (self: DiagnosticTag)
    def name: String = (self.value: @switch) match
      case 1 => "Unnecessary"
      case 2 => "Deprecated"
end DiagnosticTag
