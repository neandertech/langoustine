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

/** The message type
  */
opaque type MessageType = runtime.uinteger
object MessageType extends UIntEnum[MessageType]:
  /** An error message.
    */
  val Error = entry(1)

  /** A warning message.
    */
  val Warning = entry(2)

  /** An information message.
    */
  val Info = entry(3)

  /** A log message.
    */
  val Log = entry(4)

  /** A debug message.
    *
    * @since 3.18.0
    */
  val Debug        = entry(5)
  override def ALL = Set(
    Error,
    Warning,
    Info,
    Log,
    Debug
  )

  extension (self: MessageType)
    def name: String = (self.value: @switch) match
      case 1 => "Error"
      case 2 => "Warning"
      case 3 => "Info"
      case 4 => "Log"
      case 5 => "Debug"
end MessageType
