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

/** Describes the content type that a client supports in various result literals
  * like `Hover`, `ParameterInfo` or `CompletionItem`.
  *
  * Please note that `MarkupKinds` must not start with a `$`. This kinds are
  * reserved for internal usage.
  */
opaque type MarkupKind = String
object MarkupKind extends StringEnum[MarkupKind]:
  /** Plain text is supported as a content format
    */
  val PlainText = entry("plaintext")

  /** Markdown is supported as a content format
    */
  val Markdown     = entry("markdown")
  override def ALL = Set(
    PlainText,
    Markdown
  )
end MarkupKind
