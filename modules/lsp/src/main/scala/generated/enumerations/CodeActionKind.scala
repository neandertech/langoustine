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

/** A set of predefined code action kinds
  */
opaque type CodeActionKind = String
object CodeActionKind extends StringEnum[CodeActionKind]:
  /** Empty kind.
    */
  val Empty = entry("")

  /** Base kind for quickfix actions: 'quickfix'
    */
  val QuickFix = entry("quickfix")

  /** Base kind for refactoring actions: 'refactor'
    */
  val Refactor = entry("refactor")

  /** Base kind for refactoring extraction actions: 'refactor.extract'
    *
    * Example extract actions:
    *
    *   - Extract method
    *   - Extract function
    *   - Extract variable
    *   - Extract interface from class
    *   - ...
    */
  val RefactorExtract = entry("refactor.extract")

  /** Base kind for refactoring inline actions: 'refactor.inline'
    *
    * Example inline actions:
    *
    *   - Inline function
    *   - Inline variable
    *   - Inline constant
    *   - ...
    */
  val RefactorInline = entry("refactor.inline")

  /** Base kind for refactoring rewrite actions: 'refactor.rewrite'
    *
    * Example rewrite actions:
    *
    *   - Convert JavaScript function to class
    *   - Add or remove parameter
    *   - Encapsulate field
    *   - Make method static
    *   - Move method to base class
    *   - ...
    */
  val RefactorRewrite = entry("refactor.rewrite")

  /** Base kind for source actions: `source`
    *
    * Source code actions apply to the entire file.
    */
  val Source = entry("source")

  /** Base kind for an organize imports source action: `source.organizeImports`
    */
  val SourceOrganizeImports = entry("source.organizeImports")

  /** Base kind for auto-fix source actions: `source.fixAll`.
    *
    * Fix all actions automatically fix errors that have a clear fix that do not
    * require user input. They should not suppress errors or perform unsafe
    * fixes such as generating new types or classes.
    *
    * @since 3.15.0
    */
  val SourceFixAll = entry("source.fixAll")
  override def ALL = Set(
    Empty,
    QuickFix,
    Refactor,
    RefactorExtract,
    RefactorInline,
    RefactorRewrite,
    Source,
    SourceOrganizeImports,
    SourceFixAll
  )
end CodeActionKind
