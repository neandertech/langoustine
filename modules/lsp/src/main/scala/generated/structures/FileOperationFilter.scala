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

/** A filter to describe in which file operation requests or notifications the
  * server is interested in receiving.
  *
  * @since 3.16.0
  *
  * @param scheme
  *   A Uri scheme like `file` or `untitled`.
  *
  * @param pattern
  *   The actual file operation pattern.
  */
case class FileOperationFilter(
    scheme: Option[String] = None,
    pattern: structures.FileOperationPattern
)
object FileOperationFilter extends codecs.structures_FileOperationFilterCodec
