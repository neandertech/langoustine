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

/** The diagnostic's severity.
  */
opaque type DiagnosticSeverity = runtime.uinteger
object DiagnosticSeverity extends UIntEnum[DiagnosticSeverity]:
  /** Reports an error.
    */
  val Error = entry(1)

  /** Reports a warning.
    */
  val Warning = entry(2)

  /** Reports an information.
    */
  val Information = entry(3)

  /** Reports a hint.
    */
  val Hint         = entry(4)
  override def ALL = Set(
    Error,
    Warning,
    Information,
    Hint
  )

  extension (self: DiagnosticSeverity)
    def name: String = (self.value: @switch) match
      case 1 => "Error"
      case 2 => "Warning"
      case 3 => "Information"
      case 4 => "Hint"
end DiagnosticSeverity
