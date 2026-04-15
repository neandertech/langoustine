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

/** @since 3.16.0
  *
  * @param refreshSupport
  *   Whether the client implementation supports a refresh request sent from the
  *   server to the client.
  *
  * Note that this event is global and will force the client to refresh all code
  * lenses currently shown. It should be used with absolute care and is useful
  * for situation where a server for example detect a project wide change that
  * requires such a calculation.
  */
case class CodeLensWorkspaceClientCapabilities(
    refreshSupport: Option[Boolean] = None
)
object CodeLensWorkspaceClientCapabilities
    extends codecs.structures_CodeLensWorkspaceClientCapabilitiesCodec
