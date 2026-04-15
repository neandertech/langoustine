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

/** Provider options for a {@link CodeActionRequest}.
  *
  * @param codeActionKinds
  *   CodeActionKinds that this server may return.
  *
  * The list of kinds may be generic, such as `CodeActionKind.Refactor`, or the
  * server may list out every specific kind they provide.
  *
  * @param resolveProvider
  *   The server provides support to resolve additional information for a code
  *   action.
  *
  * since 3.16.0
  *
  * @param workDoneProgress
  */
case class CodeActionOptions(
    codeActionKinds: Option[Vector[enumerations.CodeActionKind]] = None,
    resolveProvider: Option[Boolean] = None,
    workDoneProgress: Option[Boolean] = None
)
object CodeActionOptions extends codecs.structures_CodeActionOptionsCodec
