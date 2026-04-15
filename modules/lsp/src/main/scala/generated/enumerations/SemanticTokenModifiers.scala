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

/** A set of predefined token modifiers. This set is not fixed an clients can
  * specify additional token types via the corresponding client capabilities.
  *
  * @since 3.16.0
  */
opaque type SemanticTokenModifiers = String
object SemanticTokenModifiers extends StringEnum[SemanticTokenModifiers]:
  val declaration    = entry("declaration")
  val definition     = entry("definition")
  val readonly       = entry("readonly")
  val static         = entry("static")
  val deprecated     = entry("deprecated")
  val `abstract`     = entry("abstract")
  val async          = entry("async")
  val modification   = entry("modification")
  val documentation  = entry("documentation")
  val defaultLibrary = entry("defaultLibrary")
  override def ALL   = Set(
    declaration,
    definition,
    readonly,
    static,
    deprecated,
    `abstract`,
    async,
    modification,
    documentation,
    defaultLibrary
  )
end SemanticTokenModifiers
