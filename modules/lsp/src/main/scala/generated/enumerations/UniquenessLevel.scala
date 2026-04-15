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

/** Moniker uniqueness level to define scope of the moniker.
  *
  * @since 3.16.0
  */
opaque type UniquenessLevel = String
object UniquenessLevel extends StringEnum[UniquenessLevel]:
  /** The moniker is only unique inside a document
    */
  val document = entry("document")

  /** The moniker is unique inside a project for which a dump got created
    */
  val project = entry("project")

  /** The moniker is unique inside the group to which a project belongs
    */
  val group = entry("group")

  /** The moniker is unique inside the moniker scheme.
    */
  val scheme = entry("scheme")

  /** The moniker is globally unique
    */
  val global       = entry("global")
  override def ALL = Set(
    document,
    project,
    group,
    scheme,
    global
  )
end UniquenessLevel
