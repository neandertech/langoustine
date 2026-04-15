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

/** A symbol kind.
  */
opaque type SymbolKind = runtime.uinteger
object SymbolKind extends UIntEnum[SymbolKind]:
  val File          = entry(1)
  val Module        = entry(2)
  val Namespace     = entry(3)
  val Package       = entry(4)
  val Class         = entry(5)
  val Method        = entry(6)
  val Property      = entry(7)
  val Field         = entry(8)
  val Constructor   = entry(9)
  val Enum          = entry(10)
  val Interface     = entry(11)
  val Function      = entry(12)
  val Variable      = entry(13)
  val Constant      = entry(14)
  val String        = entry(15)
  val Number        = entry(16)
  val Boolean       = entry(17)
  val Array         = entry(18)
  val Object        = entry(19)
  val Key           = entry(20)
  val Null          = entry(21)
  val EnumMember    = entry(22)
  val Struct        = entry(23)
  val Event         = entry(24)
  val Operator      = entry(25)
  val TypeParameter = entry(26)
  override def ALL  = Set(
    File,
    Module,
    Namespace,
    Package,
    Class,
    Method,
    Property,
    Field,
    Constructor,
    Enum,
    Interface,
    Function,
    Variable,
    Constant,
    String,
    Number,
    Boolean,
    Array,
    Object,
    Key,
    Null,
    EnumMember,
    Struct,
    Event,
    Operator,
    TypeParameter
  )

  extension (self: SymbolKind)
    def name: String = (self.value: @switch) match
      case 1  => "File"
      case 2  => "Module"
      case 3  => "Namespace"
      case 4  => "Package"
      case 5  => "Class"
      case 6  => "Method"
      case 7  => "Property"
      case 8  => "Field"
      case 9  => "Constructor"
      case 10 => "Enum"
      case 11 => "Interface"
      case 12 => "Function"
      case 13 => "Variable"
      case 14 => "Constant"
      case 15 => "String"
      case 16 => "Number"
      case 17 => "Boolean"
      case 18 => "Array"
      case 19 => "Object"
      case 20 => "Key"
      case 21 => "Null"
      case 22 => "EnumMember"
      case 23 => "Struct"
      case 24 => "Event"
      case 25 => "Operator"
      case 26 => "TypeParameter"
  end extension
end SymbolKind
