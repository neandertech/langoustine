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

/** Inlay hint client capabilities.
  *
  * @since 3.17.0
  *
  * @param dynamicRegistration
  *   Whether inlay hints support dynamic registration.
  *
  * @param resolveSupport
  *   Indicates which properties a client can resolve lazily on an inlay hint.
  */
case class InlayHintClientCapabilities(
    dynamicRegistration: Option[Boolean] = None,
    resolveSupport: Option[InlayHintClientCapabilities.ResolveSupport] = None
)
object InlayHintClientCapabilities
    extends codecs.structures_InlayHintClientCapabilitiesCodec:
  /** @param properties
    *   The properties that a client can resolve lazily.
    */
  case class ResolveSupport(
      properties: Vector[String]
  )
  object ResolveSupport
      extends codecs.structures_InlayHintClientCapabilities_ResolveSupportCodec
end InlayHintClientCapabilities
