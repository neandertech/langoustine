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

/** A diagnostic report indicating that the last returned report is still
  * accurate.
  *
  * @since 3.17.0
  *
  * @param kind
  *   A document diagnostic report indicating no changes to the last result. A
  *   server can only return `unchanged` if result ids are provided.
  *
  * @param resultId
  *   A result id which will be sent on the next diagnostic request for the same
  *   document.
  */
case class UnchangedDocumentDiagnosticReport(
    kind: "unchanged",
    resultId: String
)
object UnchangedDocumentDiagnosticReport
    extends codecs.structures_UnchangedDocumentDiagnosticReportCodec
