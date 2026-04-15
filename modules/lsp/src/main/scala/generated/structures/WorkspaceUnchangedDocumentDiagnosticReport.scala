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

/** An unchanged document diagnostic report for a workspace diagnostic result.
  *
  * @since 3.17.0
  *
  * @param uri
  *   The URI for which diagnostic information is reported.
  *
  * @param version
  *   The version number for which the diagnostics are reported. If the document
  *   is not marked as open `null` can be provided.
  *
  * @param kind
  *   A document diagnostic report indicating no changes to the last result. A
  *   server can only return `unchanged` if result ids are provided.
  *
  * @param resultId
  *   A result id which will be sent on the next diagnostic request for the same
  *   document.
  */
case class WorkspaceUnchangedDocumentDiagnosticReport(
    uri: runtime.DocumentUri,
    version: Option[Int] = None,
    kind: "unchanged",
    resultId: String
)
object WorkspaceUnchangedDocumentDiagnosticReport
    extends codecs.structures_WorkspaceUnchangedDocumentDiagnosticReportCodec
