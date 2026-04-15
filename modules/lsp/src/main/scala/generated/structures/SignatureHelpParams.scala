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

/** Parameters for a {@link SignatureHelpRequest}.
  *
  * @param context
  *   The signature help context. This is only available if the client specifies
  *   to send this using the client capability
  *   `textDocument.signatureHelp.contextSupport === true`
  *
  * since 3.15.0
  *
  * @param textDocument
  *   The text document.
  *
  * @param position
  *   The position inside the text document.
  *
  * @param workDoneToken
  *   An optional token that a server can use to report work done progress.
  */
case class SignatureHelpParams(
    context: Option[structures.SignatureHelpContext] = None,
    textDocument: structures.TextDocumentIdentifier,
    position: structures.Position,
    workDoneToken: Option[aliases.ProgressToken] = None
)
object SignatureHelpParams extends codecs.structures_SignatureHelpParamsCodec
