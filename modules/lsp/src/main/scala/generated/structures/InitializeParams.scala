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

// format:off
package langoustine.lsp
package structures

import langoustine.*
import runtime.{*, given}

/** @param processId
  *   The process Id of the parent process that started the server.
  *
  * Is `null` if the process has not been started by another process. If the
  * parent process is not alive then the server should exit.
  *
  * @param clientInfo
  *   Information about the client
  *
  * since 3.15.0
  *
  * @param locale
  *   The locale the client is currently showing the user interface in. This
  *   must not necessarily be the locale of the operating system.
  *
  * Uses IETF language tags as the value's syntax (See
  * https://en.wikipedia.org/wiki/IETF_language_tag)
  *
  * since 3.16.0
  *
  * @param rootPath
  *   The rootPath of the workspace. Is null if no folder is open.
  *
  * @deprecated
  *   in favour of rootUri.
  *
  * @param rootUri
  *   The rootUri of the workspace. Is null if no folder is open. If both
  *   `rootPath` and `rootUri` are set `rootUri` wins.
  *
  * @deprecated
  *   in favour of workspaceFolders.
  *
  * @param capabilities
  *   The capabilities provided by the client (editor or tool)
  *
  * @param initializationOptions
  *   User provided initialization options.
  *
  * @param trace
  *   The initial trace setting. If omitted trace is disabled ('off').
  *
  * @param workspaceFolders
  *   The workspace folders configured in the client when the server starts.
  *
  * This property is only available if the client supports workspace folders. It
  * can be `null` if the client supports workspace folders but none are
  * configured.
  *
  * since 3.6.0
  */
case class InitializeParams(
    processId: Option[Int] = None,
    clientInfo: Option[InitializeParams.ClientInfo] = None,
    locale: Option[String] = None,
    rootPath: Option[String] = None,
    rootUri: Option[runtime.DocumentUri] = None,
    capabilities: structures.ClientCapabilities,
    initializationOptions: Option[io.circe.Json] = None,
    trace: Option[enumerations.TraceValues] = None,
    workspaceFolders: Option[Vector[structures.WorkspaceFolder]] = None
)
object InitializeParams extends codecs.structures_InitializeParamsCodec:
  /** @param name
    *   The name of the client as defined by the client.
    *
    * @param version
    *   The client's version as defined by the client.
    */
  case class ClientInfo(
      name: String,
      version: Option[String] = None
  )
  object ClientInfo extends codecs.structures_InitializeParams_ClientInfoCodec
end InitializeParams
