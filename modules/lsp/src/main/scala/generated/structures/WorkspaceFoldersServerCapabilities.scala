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

/** @param supported
  *   The server has support for workspace folders
  *
  * @param changeNotifications
  *   Whether the server wants to receive workspace folder change notifications.
  *
  * If a string is provided the string is treated as an ID under which the
  * notification is registered on the client side. The ID can be used to
  * unregister for these events using the `client/unregisterCapability` request.
  */
case class WorkspaceFoldersServerCapabilities(
    supported: Option[Boolean] = None,
    changeNotifications: Option[(String | Boolean)] = None
)
object WorkspaceFoldersServerCapabilities
    extends codecs.structures_WorkspaceFoldersServerCapabilitiesCodec
