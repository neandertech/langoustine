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

opaque type WatchKind = runtime.uinteger
object WatchKind extends UIntEnum[WatchKind]:
  /** Interested in create events.
    */
  val Create = entry(1)

  /** Interested in change events
    */
  val Change = entry(2)

  /** Interested in delete events
    */
  val Delete       = entry(4)
  override def ALL = Set(
    Create,
    Change,
    Delete
  )

  extension (self: WatchKind)
    def name: String = (self.value: @switch) match
      case 1 => "Create"
      case 2 => "Change"
      case 4 => "Delete"
end WatchKind
