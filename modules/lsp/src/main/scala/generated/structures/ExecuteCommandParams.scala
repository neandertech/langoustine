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

/** The parameters of a {@link ExecuteCommandRequest}.
  *
  * @param command
  *   The identifier of the actual command handler.
  *
  * @param arguments
  *   Arguments that the command should be invoked with.
  *
  * @param workDoneToken
  *   An optional token that a server can use to report work done progress.
  */
case class ExecuteCommandParams(
    command: String,
    arguments: Option[Vector[io.circe.Json]] = None,
    workDoneToken: Option[aliases.ProgressToken] = None
)
object ExecuteCommandParams extends codecs.structures_ExecuteCommandParamsCodec
