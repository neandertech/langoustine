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

/** A code action represents a change that can be performed in code, e.g. to fix
  * a problem or to refactor code.
  *
  * A CodeAction must set either `edit` and/or a `command`. If both are
  * supplied, the `edit` is applied first, then the `command` is executed.
  *
  * @param title
  *   A short, human-readable, title for this code action.
  *
  * @param kind
  *   The kind of the code action.
  *
  * Used to filter code actions.
  *
  * @param diagnostics
  *   The diagnostics that this code action resolves.
  *
  * @param isPreferred
  *   Marks this as a preferred action. Preferred actions are used by the
  *   `auto fix` command and can be targeted by keybindings.
  *
  * A quick fix should be marked preferred if it properly addresses the
  * underlying error. A refactoring should be marked preferred if it is the most
  * reasonable choice of actions to take.
  *
  * since 3.15.0
  *
  * @param disabled
  *   Marks that the code action cannot currently be applied.
  *
  * Clients should follow the following guidelines regarding disabled code
  * actions:
  *
  *   - Disabled code actions are not shown in automatic
  *     [lightbulbs](https://code.visualstudio.com/docs/editor/editingevolved#_code-action)
  *     code action menus.
  *   - Disabled actions are shown as faded out in the code action menu when the
  *     user requests a more specific type of code action, such as refactorings.
  *   - If the user has a
  *     [keybinding](https://code.visualstudio.com/docs/editor/refactoring#_keybindings-for-code-actions)
  *     that auto applies a code action and only disabled code actions are
  *     returned, the client should show the user an error message with `reason`
  *     in the editor.
  *
  * since 3.16.0
  *
  * @param edit
  *   The workspace edit this code action performs.
  *
  * @param command
  *   A command this code action executes. If a code action provides an edit and
  *   a command, first the edit is executed and then the command.
  *
  * @param data
  *   A data entry field that is preserved on a code action between a
  *   `textDocument/codeAction` and a `codeAction/resolve` request.
  *
  * since 3.16.0
  */
case class CodeAction(
    title: String,
    kind: Option[enumerations.CodeActionKind] = None,
    diagnostics: Option[Vector[structures.Diagnostic]] = None,
    isPreferred: Option[Boolean] = None,
    disabled: Option[CodeAction.Disabled] = None,
    edit: Option[structures.WorkspaceEdit] = None,
    command: Option[structures.Command] = None,
    data: Option[io.circe.Json] = None
)
object CodeAction extends codecs.structures_CodeActionCodec:
  /** @param reason
    *   Human readable description of why the code action is currently disabled.
    *
    * This is displayed in the code actions UI.
    */
  case class Disabled(
      reason: String
  )
  object Disabled extends codecs.structures_CodeAction_DisabledCodec
end CodeAction
