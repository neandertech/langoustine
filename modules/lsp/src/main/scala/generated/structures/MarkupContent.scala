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

/** A `MarkupContent` literal represents a string value which content is
  * interpreted base on its kind flag. Currently the protocol supports
  * `plaintext` and `markdown` as markup kinds.
  *
  * If the kind is `markdown` then the value can contain fenced code blocks like
  * in GitHub issues. See
  * https://help.github.com/articles/creating-and-highlighting-code-blocks/#syntax-highlighting
  *
  * Here is an example how such a string can be constructed using JavaScript /
  * TypeScript:
  * ```ts
  * let markdown: MarkdownContent = {
  *  kind: MarkupKind.Markdown,
  *  value: [
  *    '# Header',
  *    'Some text',
  *    '```typescript',
  *    'someCode();',
  *    '```'
  *  ].join('\n')
  * };
  * ```
  *
  * *Please Note* that clients might sanitize the return markdown. A client
  * could decide to remove HTML from the markdown to avoid script execution.
  *
  * @param kind
  *   The type of the Markup
  *
  * @param value
  *   The content itself
  */
case class MarkupContent(
    kind: enumerations.MarkupKind,
    value: String
)
object MarkupContent extends codecs.structures_MarkupContentCodec
