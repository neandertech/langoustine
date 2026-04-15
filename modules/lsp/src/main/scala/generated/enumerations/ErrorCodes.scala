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

/** Predefined error codes.
  */
opaque type ErrorCodes = Int
object ErrorCodes extends IntEnum[ErrorCodes]:
  val ParseError     = entry(-32700)
  val InvalidRequest = entry(-32600)
  val MethodNotFound = entry(-32601)
  val InvalidParams  = entry(-32602)
  val InternalError  = entry(-32603)

  /** Error code indicating that a server received a notification or request
    * before the server has received the `initialize` request.
    */
  val ServerNotInitialized = entry(-32002)
  val UnknownErrorCode     = entry(-32001)
  override def ALL         = Set(
    ParseError,
    InvalidRequest,
    MethodNotFound,
    InvalidParams,
    InternalError,
    ServerNotInitialized,
    UnknownErrorCode
  )

  extension (self: ErrorCodes)
    def name: String = (self: @switch) match
      case -32700 => "ParseError"
      case -32600 => "InvalidRequest"
      case -32601 => "MethodNotFound"
      case -32602 => "InvalidParams"
      case -32603 => "InternalError"
      case -32002 => "ServerNotInitialized"
      case -32001 => "UnknownErrorCode"
end ErrorCodes
