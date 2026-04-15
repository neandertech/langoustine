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

/** Capabilities relating to events from file operations by the user in the
  * client.
  *
  * These events do not come from the file system, they come from user
  * operations like renaming a file in the UI.
  *
  * @since 3.16.0
  *
  * @param dynamicRegistration
  *   Whether the client supports dynamic registration for file
  *   requests/notifications.
  *
  * @param didCreate
  *   The client has support for sending didCreateFiles notifications.
  *
  * @param willCreate
  *   The client has support for sending willCreateFiles requests.
  *
  * @param didRename
  *   The client has support for sending didRenameFiles notifications.
  *
  * @param willRename
  *   The client has support for sending willRenameFiles requests.
  *
  * @param didDelete
  *   The client has support for sending didDeleteFiles notifications.
  *
  * @param willDelete
  *   The client has support for sending willDeleteFiles requests.
  */
case class FileOperationClientCapabilities(
    dynamicRegistration: Option[Boolean] = None,
    didCreate: Option[Boolean] = None,
    willCreate: Option[Boolean] = None,
    didRename: Option[Boolean] = None,
    willRename: Option[Boolean] = None,
    didDelete: Option[Boolean] = None,
    willDelete: Option[Boolean] = None
)
object FileOperationClientCapabilities
    extends codecs.structures_FileOperationClientCapabilitiesCodec
