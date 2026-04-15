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

/** Server Capabilities for a {@link SignatureHelpRequest}.
  *
  * @param triggerCharacters
  *   List of characters that trigger signature help automatically.
  *
  * @param retriggerCharacters
  *   List of characters that re-trigger signature help.
  *
  * These trigger characters are only active when signature help is already
  * showing. All trigger characters are also counted as re-trigger characters.
  *
  * since 3.15.0
  *
  * @param workDoneProgress
  */
case class SignatureHelpOptions(
    triggerCharacters: Option[Vector[String]] = None,
    retriggerCharacters: Option[Vector[String]] = None,
    workDoneProgress: Option[Boolean] = None
)
object SignatureHelpOptions extends codecs.structures_SignatureHelpOptionsCodec
