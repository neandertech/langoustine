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

/** A relative pattern is a helper to construct glob patterns that are matched
  * relatively to a base URI. The common value for a `baseUri` is a workspace
  * folder root, but it can be another absolute URI as well.
  *
  * @since 3.17.0
  *
  * @param baseUri
  *   A workspace folder or a base URI to which this pattern will be matched
  *   against relatively.
  *
  * @param pattern
  *   The actual glob pattern;
  */
case class RelativePattern(
    baseUri: (structures.WorkspaceFolder | runtime.Uri),
    pattern: aliases.Pattern
)
object RelativePattern extends codecs.structures_RelativePatternCodec
