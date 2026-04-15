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

/** A workspace folder inside a client.
  *
  * @param uri
  *   The associated URI for this workspace folder.
  *
  * @param name
  *   The name of the workspace folder. Used to refer to this workspace folder
  *   in the user interface.
  */
case class WorkspaceFolder(
    uri: runtime.Uri,
    name: String
)
object WorkspaceFolder extends codecs.structures_WorkspaceFolderCodec
