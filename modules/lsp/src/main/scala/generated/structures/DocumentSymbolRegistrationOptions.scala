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

/** Registration options for a {@link DocumentSymbolRequest}.
  *
  * @param documentSelector
  *   A document selector to identify the scope of the registration. If set to
  *   null the document selector provided on the client side will be used.
  *
  * @param label
  *   A human-readable string that is shown when multiple outlines trees are
  *   shown for the same document.
  *
  * since 3.16.0
  */
case class DocumentSymbolRegistrationOptions(
    documentSelector: Option[aliases.DocumentSelector] = None,
    label: Option[String] = None
)
object DocumentSymbolRegistrationOptions
    extends codecs.structures_DocumentSymbolRegistrationOptionsCodec
