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

/** Client workspace capabilities specific to inlay hints.
  *
  * @since 3.17.0
  *
  * @param refreshSupport
  *   Whether the client implementation supports a refresh request sent from the
  *   server to the client.
  *
  * Note that this event is global and will force the client to refresh all
  * inlay hints currently shown. It should be used with absolute care and is
  * useful for situation where a server for example detects a project wide
  * change that requires such a calculation.
  */
case class InlayHintWorkspaceClientCapabilities(
    refreshSupport: Option[Boolean] = None
)
object InlayHintWorkspaceClientCapabilities
    extends codecs.structures_InlayHintWorkspaceClientCapabilitiesCodec
