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
  * @param textDocument
  *   The text document.
  *
  * @param previousResultId
  *   The result id of a previous response. The result Id can either point to a
  *   full response or a delta response depending on what was received last.
  *
  * @param workDoneToken
  *   An optional token that a server can use to report work done progress.
  *
  * @param partialResultToken
  *   An optional token that a server can use to report partial results (e.g.
  *   streaming) to the client.
  */
case class SemanticTokensDeltaParams(
    textDocument: structures.TextDocumentIdentifier,
    previousResultId: String,
    workDoneToken: Option[aliases.ProgressToken] = None,
    partialResultToken: Option[aliases.ProgressToken] = None
)
object SemanticTokensDeltaParams
    extends codecs.structures_SemanticTokensDeltaParamsCodec
