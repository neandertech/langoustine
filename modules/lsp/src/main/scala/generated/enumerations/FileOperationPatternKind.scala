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

/** A pattern kind describing if a glob pattern matches a file a folder or both.
  *
  * @since 3.16.0
  */
opaque type FileOperationPatternKind = String
object FileOperationPatternKind extends StringEnum[FileOperationPatternKind]:
  /** The pattern matches a file only.
    */
  val file = entry("file")

  /** The pattern matches a folder only.
    */
  val folder       = entry("folder")
  override def ALL = Set(
    file,
    folder
  )
end FileOperationPatternKind
