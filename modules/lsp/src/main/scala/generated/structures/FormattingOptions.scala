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

/** Value-object describing what options formatting should use.
  *
  * @param tabSize
  *   Size of a tab in spaces.
  *
  * @param insertSpaces
  *   Prefer spaces over tabs.
  *
  * @param trimTrailingWhitespace
  *   Trim trailing whitespace on a line.
  *
  * since 3.15.0
  *
  * @param insertFinalNewline
  *   Insert a newline character at the end of the file if one does not exist.
  *
  * since 3.15.0
  *
  * @param trimFinalNewlines
  *   Trim all newlines after the final newline at the end of the file.
  *
  * since 3.15.0
  */
case class FormattingOptions(
    tabSize: runtime.uinteger,
    insertSpaces: Boolean,
    trimTrailingWhitespace: Option[Boolean] = None,
    insertFinalNewline: Option[Boolean] = None,
    trimFinalNewlines: Option[Boolean] = None
)
object FormattingOptions extends codecs.structures_FormattingOptionsCodec
