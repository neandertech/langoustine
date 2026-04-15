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

/** Represents the signature of something callable. A signature can have a
  * label, like a function-name, a doc-comment, and a set of parameters.
  *
  * @param label
  *   The label of this signature. Will be shown in the UI.
  *
  * @param documentation
  *   The human-readable doc-comment of this signature. Will be shown in the UI
  *   but can be omitted.
  *
  * @param parameters
  *   The parameters of this signature.
  *
  * @param activeParameter
  *   The index of the active parameter.
  *
  * If provided, this is used in place of `SignatureHelp.activeParameter`.
  *
  * since 3.16.0
  */
case class SignatureInformation(
    label: String,
    documentation: Option[(String | structures.MarkupContent)] = None,
    parameters: Option[Vector[structures.ParameterInformation]] = None,
    activeParameter: Option[runtime.uinteger] = None
)
object SignatureInformation extends codecs.structures_SignatureInformationCodec
