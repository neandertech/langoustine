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

opaque type WorkspaceDocumentDiagnosticReport =
  (structures.WorkspaceFullDocumentDiagnosticReport |
    structures.WorkspaceUnchangedDocumentDiagnosticReport)
object WorkspaceDocumentDiagnosticReport
    extends codecs.aliases_WorkspaceDocumentDiagnosticReport:
  inline def apply(
      v: structures.WorkspaceFullDocumentDiagnosticReport
  ): WorkspaceDocumentDiagnosticReport = v
  inline def apply(
      v: structures.WorkspaceUnchangedDocumentDiagnosticReport
  ): WorkspaceDocumentDiagnosticReport = v

  extension (v: WorkspaceDocumentDiagnosticReport)
    inline def value: (structures.WorkspaceFullDocumentDiagnosticReport |
      structures.WorkspaceUnchangedDocumentDiagnosticReport) = v

  given Typeable[WorkspaceDocumentDiagnosticReport] with
    def unapply(s: Any): Option[s.type & WorkspaceDocumentDiagnosticReport] =
      s match
        case c: structures.WorkspaceFullDocumentDiagnosticReport =>
          Some(
            c.asInstanceOf[
              s.type & structures.WorkspaceFullDocumentDiagnosticReport
            ]
          )
        case c: structures.WorkspaceUnchangedDocumentDiagnosticReport =>
          Some(
            c.asInstanceOf[
              s.type & structures.WorkspaceUnchangedDocumentDiagnosticReport
            ]
          )
        case _ => Option.empty
  end given
end WorkspaceDocumentDiagnosticReport
