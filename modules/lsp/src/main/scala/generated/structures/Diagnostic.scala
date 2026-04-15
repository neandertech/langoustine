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

/** Represents a diagnostic, such as a compiler error or warning. Diagnostic
  * objects are only valid in the scope of a resource.
  *
  * @param range
  *   The range at which the message applies
  *
  * @param severity
  *   The diagnostic's severity. Can be omitted. If omitted it is up to the
  *   client to interpret diagnostics as error, warning, info or hint.
  *
  * @param code
  *   The diagnostic's code, which usually appear in the user interface.
  *
  * @param codeDescription
  *   An optional property to describe the error code. Requires the code field
  *   (above) to be present/not null.
  *
  * since 3.16.0
  *
  * @param source
  *   A human-readable string describing the source of this diagnostic, e.g.
  *   'typescript' or 'super lint'. It usually appears in the user interface.
  *
  * @param message
  *   The diagnostic's message. It usually appears in the user interface
  *
  * @param tags
  *   Additional metadata about the diagnostic.
  *
  * since 3.15.0
  *
  * @param relatedInformation
  *   An array of related diagnostic information, e.g. when symbol-names within
  *   a scope collide all definitions can be marked via this property.
  *
  * @param data
  *   A data entry field that is preserved between a
  *   `textDocument/publishDiagnostics` notification and
  *   `textDocument/codeAction` request.
  *
  * since 3.16.0
  */
case class Diagnostic(
    range: structures.Range,
    severity: Option[enumerations.DiagnosticSeverity] = None,
    code: Option[(Int | String)] = None,
    codeDescription: Option[structures.CodeDescription] = None,
    source: Option[String] = None,
    message: String,
    tags: Option[Vector[enumerations.DiagnosticTag]] = None,
    relatedInformation: Option[
      Vector[structures.DiagnosticRelatedInformation]
    ] = None,
    data: Option[io.circe.Json] = None
)
object Diagnostic extends codecs.structures_DiagnosticCodec
