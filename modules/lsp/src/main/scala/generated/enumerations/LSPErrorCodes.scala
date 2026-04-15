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

opaque type LSPErrorCodes = Int
object LSPErrorCodes extends IntEnum[LSPErrorCodes]:
  /** A request failed but it was syntactically correct, e.g the method name was
    * known and the parameters were valid. The error message should contain
    * human readable information about why the request failed.
    *
    * @since 3.17.0
    */
  val RequestFailed = entry(-32803)

  /** The server cancelled the request. This error code should only be used for
    * requests that explicitly support being server cancellable.
    *
    * @since 3.17.0
    */
  val ServerCancelled = entry(-32802)

  /** The server detected that the content of a document got modified outside
    * normal conditions. A server should NOT send this error code if it detects
    * a content change in it unprocessed messages. The result even computed on
    * an older state might still be useful for the client.
    *
    * If a client decides that a result is not of any use anymore the client
    * should cancel the request.
    */
  val ContentModified = entry(-32801)

  /** The client has canceled a request and a server has detected the cancel.
    */
  val RequestCancelled = entry(-32800)
  override def ALL     = Set(
    RequestFailed,
    ServerCancelled,
    ContentModified,
    RequestCancelled
  )

  extension (self: LSPErrorCodes)
    def name: String = (self: @switch) match
      case -32803 => "RequestFailed"
      case -32802 => "ServerCancelled"
      case -32801 => "ContentModified"
      case -32800 => "RequestCancelled"
end LSPErrorCodes
