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

/** The parameters passed via an apply workspace edit request.
  *
  * @param label
  *   An optional label of the workspace edit. This label is presented in the
  *   user interface for example on an undo stack to undo the workspace edit.
  *
  * @param edit
  *   The edits to apply.
  */
case class ApplyWorkspaceEditParams(
    label: Option[String] = None,
    edit: structures.WorkspaceEdit
)
object ApplyWorkspaceEditParams
    extends codecs.structures_ApplyWorkspaceEditParamsCodec
