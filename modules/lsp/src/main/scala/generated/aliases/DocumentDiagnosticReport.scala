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
package aliases

import langoustine.*
import runtime.{*, given}
import io.circe.*
import scala.reflect.*

opaque type DocumentDiagnosticReport =
  (structures.RelatedFullDocumentDiagnosticReport |
    structures.RelatedUnchangedDocumentDiagnosticReport)
object DocumentDiagnosticReport extends codecs.aliases_DocumentDiagnosticReport:
  inline def apply(
      v: structures.RelatedFullDocumentDiagnosticReport
  ): DocumentDiagnosticReport = v
  inline def apply(
      v: structures.RelatedUnchangedDocumentDiagnosticReport
  ): DocumentDiagnosticReport = v

  extension (v: DocumentDiagnosticReport)
    inline def value: (structures.RelatedFullDocumentDiagnosticReport |
      structures.RelatedUnchangedDocumentDiagnosticReport) = v

  given Typeable[DocumentDiagnosticReport] with
    def unapply(s: Any): Option[s.type & DocumentDiagnosticReport] =
      s match
        case c: structures.RelatedFullDocumentDiagnosticReport =>
          Some(
            c.asInstanceOf[
              s.type & structures.RelatedFullDocumentDiagnosticReport
            ]
          )
        case c: structures.RelatedUnchangedDocumentDiagnosticReport =>
          Some(
            c.asInstanceOf[
              s.type & structures.RelatedUnchangedDocumentDiagnosticReport
            ]
          )
        case _ => Option.empty
  end given
end DocumentDiagnosticReport
