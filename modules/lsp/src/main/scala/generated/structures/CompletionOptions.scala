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

/** Completion options.
  *
  * @param triggerCharacters
  *   Most tools trigger completion request automatically without explicitly
  *   requesting it using a keyboard shortcut (e.g. Ctrl+Space). Typically they
  *   do so when the user starts to type an identifier. For example if the user
  *   types `c` in a JavaScript file code complete will automatically pop up
  *   present `console` besides others as a completion item. Characters that
  *   make up identifiers don't need to be listed here.
  *
  * If code complete should automatically be trigger on characters not being
  * valid inside an identifier (for example `.` in JavaScript) list them in
  * `triggerCharacters`.
  *
  * @param allCommitCharacters
  *   The list of all possible characters that commit a completion. This field
  *   can be used if clients don't support individual commit characters per
  *   completion item. See
  *   `ClientCapabilities.textDocument.completion.completionItem.commitCharactersSupport`
  *
  * If a server provides both `allCommitCharacters` and commit characters on an
  * individual completion item the ones on the completion item win.
  *
  * since 3.2.0
  *
  * @param resolveProvider
  *   The server provides support to resolve additional information for a
  *   completion item.
  *
  * @param completionItem
  *   The server supports the following `CompletionItem` specific capabilities.
  *
  * since 3.17.0
  *
  * @param workDoneProgress
  */
case class CompletionOptions(
    triggerCharacters: Option[Vector[String]] = None,
    allCommitCharacters: Option[Vector[String]] = None,
    resolveProvider: Option[Boolean] = None,
    completionItem: Option[CompletionOptions.CompletionItem] = None,
    workDoneProgress: Option[Boolean] = None
)
object CompletionOptions extends codecs.structures_CompletionOptionsCodec:
  /** @param labelDetailsSupport
    *   The server has support for completion item label details (see also
    *   `CompletionItemLabelDetails`) when receiving a completion item in a
    *   resolve call.
    *
    * since 3.17.0
    */
  case class CompletionItem(
      labelDetailsSupport: Option[Boolean] = None
  )
  object CompletionItem
      extends codecs.structures_CompletionOptions_CompletionItemCodec
end CompletionOptions
