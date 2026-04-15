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

/** A code lens represents a {@link Command command} that should be shown along
  * with source text, like the number of references, a way to run tests, etc.
  *
  * A code lens is _unresolved_ when no command is associated to it. For
  * performance reasons the creation of a code lens and resolving should be done
  * in two stages.
  *
  * @param range
  *   The range in which this code lens is valid. Should only span a single
  *   line.
  *
  * @param command
  *   The command this code lens represents.
  *
  * @param data
  *   A data entry field that is preserved on a code lens item between a
  *   {@link CodeLensRequest} and a {@link CodeLensResolveRequest}
  */
case class CodeLens(
    range: structures.Range,
    command: Option[structures.Command] = None,
    data: Option[io.circe.Json] = None
)
object CodeLens extends codecs.structures_CodeLensCodec
