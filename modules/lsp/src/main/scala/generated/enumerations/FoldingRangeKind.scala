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

/** A set of predefined range kinds.
  */
opaque type FoldingRangeKind = String
object FoldingRangeKind extends StringEnum[FoldingRangeKind]:
  /** Folding range for a comment
    */
  val Comment = entry("comment")

  /** Folding range for an import or include
    */
  val Imports = entry("imports")

  /** Folding range for a region (e.g. `#region`)
    */
  val Region       = entry("region")
  override def ALL = Set(
    Comment,
    Imports,
    Region
  )
end FoldingRangeKind
