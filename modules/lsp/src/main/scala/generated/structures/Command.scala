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

/** Represents a reference to a command. Provides a title which will be used to
  * represent a command in the UI and, optionally, an array of arguments which
  * will be passed to the command handler function when invoked.
  *
  * @param title
  *   Title of the command, like `save`.
  *
  * @param command
  *   The identifier of the actual command handler.
  *
  * @param arguments
  *   Arguments that the command handler should be invoked with.
  */
case class Command(
    title: String,
    command: String,
    arguments: Option[Vector[io.circe.Json]] = None
)
object Command extends codecs.structures_CommandCodec
