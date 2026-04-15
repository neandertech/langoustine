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

/** Contains additional diagnostic information about the context in which a
  * {@link CodeActionProvider.provideCodeActions code action} is run.
  *
  * @param diagnostics
  *   An array of diagnostics known on the client side overlapping the range
  *   provided to the `textDocument/codeAction` request. They are provided so
  *   that the server knows which errors are currently presented to the user for
  *   the given range. There is no guarantee that these accurately reflect the
  *   error state of the resource. The primary parameter to compute code actions
  *   is the provided range.
  *
  * @param only
  *   Requested kind of actions to return.
  *
  * Actions not of this kind are filtered out by the client before being shown.
  * So servers can omit computing them.
  *
  * @param triggerKind
  *   The reason why code actions were requested.
  *
  * since 3.17.0
  */
case class CodeActionContext(
    diagnostics: Vector[structures.Diagnostic],
    only: Option[Vector[enumerations.CodeActionKind]] = None,
    triggerKind: Option[enumerations.CodeActionTriggerKind] = None
)
object CodeActionContext extends codecs.structures_CodeActionContextCodec
