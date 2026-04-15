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

/** Show message request client capabilities
  *
  * @param messageActionItem
  *   Capabilities specific to the `MessageActionItem` type.
  */
case class ShowMessageRequestClientCapabilities(
    messageActionItem: Option[
      ShowMessageRequestClientCapabilities.MessageActionItem
    ] = None
)
object ShowMessageRequestClientCapabilities
    extends codecs.structures_ShowMessageRequestClientCapabilitiesCodec:
  /** @param additionalPropertiesSupport
    *   Whether the client supports additional attributes which are preserved
    *   and send back to the server in the request's response.
    */
  case class MessageActionItem(
      additionalPropertiesSupport: Option[Boolean] = None
  )
  object MessageActionItem
      extends codecs.structures_ShowMessageRequestClientCapabilities_MessageActionItemCodec
end ShowMessageRequestClientCapabilities
