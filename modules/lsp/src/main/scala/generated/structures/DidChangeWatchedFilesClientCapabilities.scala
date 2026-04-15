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
  *   Did change watched files notification supports dynamic registration.
  *   Please note that the current protocol doesn't support static configuration
  *   for file changes from the server side.
  *
  * @param relativePatternSupport
  *   Whether the client has support for
  *   {@link RelativePattern relative pattern} or not.
  *
  * since 3.17.0
  */
case class DidChangeWatchedFilesClientCapabilities(
    dynamicRegistration: Option[Boolean] = None,
    relativePatternSupport: Option[Boolean] = None
)
object DidChangeWatchedFilesClientCapabilities
    extends codecs.structures_DidChangeWatchedFilesClientCapabilitiesCodec
