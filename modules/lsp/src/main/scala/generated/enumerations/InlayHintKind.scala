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

/** Inlay hint kinds.
  *
  * @since 3.17.0
  */
opaque type InlayHintKind = runtime.uinteger
object InlayHintKind extends UIntEnum[InlayHintKind]:
  /** An inlay hint that for a type annotation.
    */
  val Type = entry(1)

  /** An inlay hint that is for a parameter.
    */
  val Parameter    = entry(2)
  override def ALL = Set(
    Type,
    Parameter
  )

  extension (self: InlayHintKind)
    def name: String = (self.value: @switch) match
      case 1 => "Type"
      case 2 => "Parameter"
end InlayHintKind
