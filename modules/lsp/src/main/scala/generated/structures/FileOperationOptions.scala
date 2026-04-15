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

/** Options for notifications/requests for user operations on files.
  *
  * @since 3.16.0
  *
  * @param didCreate
  *   The server is interested in receiving didCreateFiles notifications.
  *
  * @param willCreate
  *   The server is interested in receiving willCreateFiles requests.
  *
  * @param didRename
  *   The server is interested in receiving didRenameFiles notifications.
  *
  * @param willRename
  *   The server is interested in receiving willRenameFiles requests.
  *
  * @param didDelete
  *   The server is interested in receiving didDeleteFiles file notifications.
  *
  * @param willDelete
  *   The server is interested in receiving willDeleteFiles file requests.
  */
case class FileOperationOptions(
    didCreate: Option[structures.FileOperationRegistrationOptions] = None,
    willCreate: Option[structures.FileOperationRegistrationOptions] = None,
    didRename: Option[structures.FileOperationRegistrationOptions] = None,
    willRename: Option[structures.FileOperationRegistrationOptions] = None,
    didDelete: Option[structures.FileOperationRegistrationOptions] = None,
    willDelete: Option[structures.FileOperationRegistrationOptions] = None
)
object FileOperationOptions extends codecs.structures_FileOperationOptionsCodec
