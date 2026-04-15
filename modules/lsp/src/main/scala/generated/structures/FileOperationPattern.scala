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

/** A pattern to describe in which file operation requests or notifications the
  * server is interested in receiving.
  *
  * @since 3.16.0
  *
  * @param glob
  *   The glob pattern to match. Glob patterns can have the following syntax:
  *   - `*` to match one or more characters in a path segment
  *   - `?` to match on one character in a path segment
  *   - `**` to match any number of path segments, including none
  *   - `{}` to group sub patterns into an OR expression. (e.g. `**​.{ts,js}`
  *     matches all TypeScript and JavaScript files)
  *   - `[]` to declare a range of characters to match in a path segment (e.g.,
  *     `example.[0-9]` to match on `example.0`, `example.1`, …)
  *   - `[!...]` to negate a range of characters to match in a path segment
  *     (e.g., `example.[!0-9]` to match on `example.a`, `example.b`, but not
  *     `example.0`)
  *
  * @param matches
  *   Whether to match files or folders with this pattern.
  *
  * Matches both if undefined.
  *
  * @param options
  *   Additional options used during matching.
  */
case class FileOperationPattern(
    glob: String,
    matches: Option[enumerations.FileOperationPatternKind] = None,
    options: Option[structures.FileOperationPatternOptions] = None
)
object FileOperationPattern extends codecs.structures_FileOperationPatternCodec
