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

/** Defines how the host (editor) should sync document changes to the language
  * server.
  */
opaque type TextDocumentSyncKind = runtime.uinteger
object TextDocumentSyncKind extends UIntEnum[TextDocumentSyncKind]:
  /** Documents should not be synced at all.
    */
  val None = entry(0)

  /** Documents are synced by always sending the full content of the document.
    */
  val Full = entry(1)

  /** Documents are synced by sending the full content on open. After that only
    * incremental updates to the document are send.
    */
  val Incremental  = entry(2)
  override def ALL = Set(
    None,
    Full,
    Incremental
  )

  extension (self: TextDocumentSyncKind)
    def name: String = (self.value: @switch) match
      case 0 => "None"
      case 1 => "Full"
      case 2 => "Incremental"
end TextDocumentSyncKind
