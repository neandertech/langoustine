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

/** Represents a parameter of a callable-signature. A parameter can have a label
  * and a doc-comment.
  *
  * @param label
  *   The label of this parameter information.
  *
  * Either a string or an inclusive start and exclusive end offsets within its
  * containing signature label. (see SignatureInformation.label). The offsets
  * are based on a UTF-16 string representation as `Position` and `Range` does.
  *
  * *Note*: a label of type string should be a substring of its containing
  * signature label. Its intended use case is to highlight the parameter label
  * part in the `SignatureInformation.label`.
  *
  * @param documentation
  *   The human-readable doc-comment of this parameter. Will be shown in the UI
  *   but can be omitted.
  */
case class ParameterInformation(
    label: (String | (runtime.uinteger, runtime.uinteger)),
    documentation: Option[(String | structures.MarkupContent)] = None
)
object ParameterInformation extends codecs.structures_ParameterInformationCodec
