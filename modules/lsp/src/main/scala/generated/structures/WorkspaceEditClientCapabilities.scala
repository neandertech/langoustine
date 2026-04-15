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

/** @param documentChanges
  *   The client supports versioned document changes in `WorkspaceEdit`s
  *
  * @param resourceOperations
  *   The resource operations the client supports. Clients should at least
  *   support 'create', 'rename' and 'delete' files and folders.
  *
  * since 3.13.0
  *
  * @param failureHandling
  *   The failure handling strategy of a client if applying the workspace edit
  *   fails.
  *
  * since 3.13.0
  *
  * @param normalizesLineEndings
  *   Whether the client normalizes line endings to the client specific setting.
  *   If set to `true` the client will normalize line ending characters in a
  *   workspace edit to the client-specified new line character.
  *
  * since 3.16.0
  *
  * @param changeAnnotationSupport
  *   Whether the client in general supports change annotations on text edits,
  *   create file, rename file and delete file changes.
  *
  * since 3.16.0
  */
case class WorkspaceEditClientCapabilities(
    documentChanges: Option[Boolean] = None,
    resourceOperations: Option[Vector[enumerations.ResourceOperationKind]] =
      None,
    failureHandling: Option[enumerations.FailureHandlingKind] = None,
    normalizesLineEndings: Option[Boolean] = None,
    changeAnnotationSupport: Option[
      WorkspaceEditClientCapabilities.ChangeAnnotationSupport
    ] = None
)
object WorkspaceEditClientCapabilities
    extends codecs.structures_WorkspaceEditClientCapabilitiesCodec:
  /** @param groupsOnLabel
    *   Whether the client groups edits with equal labels into tree nodes, for
    *   instance all edits labelled with "Changes in Strings" would be a tree
    *   node.
    */
  case class ChangeAnnotationSupport(
      groupsOnLabel: Option[Boolean] = None
  )
  object ChangeAnnotationSupport
      extends codecs.structures_WorkspaceEditClientCapabilities_ChangeAnnotationSupportCodec
end WorkspaceEditClientCapabilities
