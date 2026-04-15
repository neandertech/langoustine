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

package langoustine.lsp
package enumerations

import runtime.{*, given}
import io.circe.*
import scala.reflect.Typeable
import scala.annotation.switch

/** The document diagnostic report kinds.
  *
  * @since 3.17.0
  */
opaque type DocumentDiagnosticReportKind = String
object DocumentDiagnosticReportKind
    extends StringEnum[DocumentDiagnosticReportKind]:
  /** A diagnostic report with a full set of problems.
    */
  val Full = entry("full")

  /** A report indicating that the last returned report is still accurate.
    */
  val Unchanged    = entry("unchanged")
  override def ALL = Set(
    Full,
    Unchanged
  )
end DocumentDiagnosticReportKind
