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

/** The kind of a completion entry.
  */
opaque type CompletionItemKind = runtime.uinteger
object CompletionItemKind extends UIntEnum[CompletionItemKind]:
  val Text          = entry(1)
  val Method        = entry(2)
  val Function      = entry(3)
  val Constructor   = entry(4)
  val Field         = entry(5)
  val Variable      = entry(6)
  val Class         = entry(7)
  val Interface     = entry(8)
  val Module        = entry(9)
  val Property      = entry(10)
  val Unit          = entry(11)
  val Value         = entry(12)
  val Enum          = entry(13)
  val Keyword       = entry(14)
  val Snippet       = entry(15)
  val Color         = entry(16)
  val File          = entry(17)
  val Reference     = entry(18)
  val Folder        = entry(19)
  val EnumMember    = entry(20)
  val Constant      = entry(21)
  val Struct        = entry(22)
  val Event         = entry(23)
  val Operator      = entry(24)
  val TypeParameter = entry(25)
  override def ALL  = Set(
    Text,
    Method,
    Function,
    Constructor,
    Field,
    Variable,
    Class,
    Interface,
    Module,
    Property,
    Unit,
    Value,
    Enum,
    Keyword,
    Snippet,
    Color,
    File,
    Reference,
    Folder,
    EnumMember,
    Constant,
    Struct,
    Event,
    Operator,
    TypeParameter
  )

  extension (self: CompletionItemKind)
    def name: String = (self.value: @switch) match
      case 1  => "Text"
      case 2  => "Method"
      case 3  => "Function"
      case 4  => "Constructor"
      case 5  => "Field"
      case 6  => "Variable"
      case 7  => "Class"
      case 8  => "Interface"
      case 9  => "Module"
      case 10 => "Property"
      case 11 => "Unit"
      case 12 => "Value"
      case 13 => "Enum"
      case 14 => "Keyword"
      case 15 => "Snippet"
      case 16 => "Color"
      case 17 => "File"
      case 18 => "Reference"
      case 19 => "Folder"
      case 20 => "EnumMember"
      case 21 => "Constant"
      case 22 => "Struct"
      case 23 => "Event"
      case 24 => "Operator"
      case 25 => "TypeParameter"
  end extension
end CompletionItemKind
