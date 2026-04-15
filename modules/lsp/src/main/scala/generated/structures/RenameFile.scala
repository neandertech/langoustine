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

/** Rename file operation
  *
  * @param kind
  *   A rename
  *
  * @param oldUri
  *   The old (existing) location.
  *
  * @param newUri
  *   The new location.
  *
  * @param options
  *   Rename options.
  *
  * @param kind
  *   The resource operation kind.
  *
  * @param annotationId
  *   An optional annotation identifier describing the operation.
  *
  * since 3.16.0
  */
case class RenameFile(
    kind: "rename",
    oldUri: runtime.DocumentUri,
    newUri: runtime.DocumentUri,
    options: Option[structures.RenameFileOptions] = None,
    annotationId: Option[aliases.ChangeAnnotationIdentifier] = None
)
object RenameFile extends codecs.structures_RenameFileCodec
