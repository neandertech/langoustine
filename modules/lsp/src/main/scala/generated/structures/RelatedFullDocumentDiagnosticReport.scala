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

/** A full diagnostic report with a set of related documents.
  *
  * @since 3.17.0
  *
  * @param relatedDocuments
  *   Diagnostics of related documents. This information is useful in
  *   programming languages where code in a file A can generate diagnostics in a
  *   file B which A depends on. An example of such a language is C/C++ where
  *   marco definitions in a file a.cpp and result in errors in a header file
  *   b.hpp.
  *
  * since 3.17.0
  *
  * @param kind
  *   A full document diagnostic report.
  *
  * @param resultId
  *   An optional result id. If provided it will be sent on the next diagnostic
  *   request for the same document.
  *
  * @param items
  *   The actual items.
  */
case class RelatedFullDocumentDiagnosticReport(
    relatedDocuments: Option[Map[
      runtime.DocumentUri,
      (structures.FullDocumentDiagnosticReport |
        structures.UnchangedDocumentDiagnosticReport)
    ]] = None,
    kind: "full",
    resultId: Option[String] = None,
    items: Vector[structures.Diagnostic]
)
object RelatedFullDocumentDiagnosticReport
    extends codecs.structures_RelatedFullDocumentDiagnosticReportCodec
