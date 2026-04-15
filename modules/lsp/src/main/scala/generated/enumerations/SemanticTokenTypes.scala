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

/** A set of predefined token types. This set is not fixed an clients can
  * specify additional token types via the corresponding client capabilities.
  *
  * @since 3.16.0
  */
opaque type SemanticTokenTypes = String
object SemanticTokenTypes extends StringEnum[SemanticTokenTypes]:
  val namespace = entry("namespace")

  /** Represents a generic type. Acts as a fallback for types which can't be
    * mapped to a specific type like class or enum.
    */
  val `type`        = entry("type")
  val `class`       = entry("class")
  val `enum`        = entry("enum")
  val interface     = entry("interface")
  val struct        = entry("struct")
  val typeParameter = entry("typeParameter")
  val parameter     = entry("parameter")
  val variable      = entry("variable")
  val property      = entry("property")
  val enumMember    = entry("enumMember")
  val event         = entry("event")
  val function      = entry("function")
  val method        = entry("method")
  val `macro`       = entry("macro")
  val keyword       = entry("keyword")
  val modifier      = entry("modifier")
  val comment       = entry("comment")
  val string        = entry("string")
  val number        = entry("number")
  val regexp        = entry("regexp")
  val operator      = entry("operator")

  /** @since 3.17.0
    */
  val decorator    = entry("decorator")
  override def ALL = Set(
    namespace,
    `type`,
    `class`,
    `enum`,
    interface,
    struct,
    typeParameter,
    parameter,
    variable,
    property,
    enumMember,
    event,
    function,
    method,
    `macro`,
    keyword,
    modifier,
    comment,
    string,
    number,
    regexp,
    operator,
    decorator
  )
end SemanticTokenTypes
