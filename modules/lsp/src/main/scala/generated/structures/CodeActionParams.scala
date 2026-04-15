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

/** The parameters of a {@link CodeActionRequest}.
  *
  * @param textDocument
  *   The document in which the command was invoked.
  *
  * @param range
  *   The range for which the command was invoked.
  *
  * @param context
  *   Context carrying additional information.
  *
  * @param workDoneToken
  *   An optional token that a server can use to report work done progress.
  *
  * @param partialResultToken
  *   An optional token that a server can use to report partial results (e.g.
  *   streaming) to the client.
  */
case class CodeActionParams(
    textDocument: structures.TextDocumentIdentifier,
    range: structures.Range,
    context: structures.CodeActionContext,
    workDoneToken: Option[aliases.ProgressToken] = None,
    partialResultToken: Option[aliases.ProgressToken] = None
)
object CodeActionParams extends codecs.structures_CodeActionParamsCodec
