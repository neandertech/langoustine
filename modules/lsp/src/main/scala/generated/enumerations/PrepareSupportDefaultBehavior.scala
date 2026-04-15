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

opaque type PrepareSupportDefaultBehavior = runtime.uinteger
object PrepareSupportDefaultBehavior
    extends UIntEnum[PrepareSupportDefaultBehavior]:
  /** The client's default behavior is to select the identifier according the to
    * language's syntax rule.
    */
  val Identifier   = entry(1)
  override def ALL = Set(
    Identifier
  )

  extension (self: PrepareSupportDefaultBehavior)
    def name: String = (self.value: @switch) match
      case 1 => "Identifier"
end PrepareSupportDefaultBehavior
