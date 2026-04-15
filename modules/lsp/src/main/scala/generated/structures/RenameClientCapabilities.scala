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

/** @param dynamicRegistration
  *   Whether rename supports dynamic registration.
  *
  * @param prepareSupport
  *   Client supports testing for validity of rename operations before
  *   execution.
  *
  * since 3.12.0
  *
  * @param prepareSupportDefaultBehavior
  *   Client supports the default behavior result.
  *
  * The value indicates the default behavior used by the client.
  *
  * since 3.16.0
  *
  * @param honorsChangeAnnotations
  *   Whether the client honors the change annotations in text edits and
  *   resource operations returned via the rename request's workspace edit by
  *   for example presenting the workspace edit in the user interface and asking
  *   for confirmation.
  *
  * since 3.16.0
  */
case class RenameClientCapabilities(
    dynamicRegistration: Option[Boolean] = None,
    prepareSupport: Option[Boolean] = None,
    prepareSupportDefaultBehavior: Option[
      enumerations.PrepareSupportDefaultBehavior
    ] = None,
    honorsChangeAnnotations: Option[Boolean] = None
)
object RenameClientCapabilities
    extends codecs.structures_RenameClientCapabilitiesCodec
