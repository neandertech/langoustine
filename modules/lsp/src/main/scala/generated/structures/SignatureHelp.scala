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

/** Signature help represents the signature of something callable. There can be
  * multiple signature but only one active and only one active parameter.
  *
  * @param signatures
  *   One or more signatures.
  *
  * @param activeSignature
  *   The active signature. If omitted or the value lies outside the range of
  *   `signatures` the value defaults to zero or is ignored if the
  *   `SignatureHelp` has no signatures.
  *
  * Whenever possible implementors should make an active decision about the
  * active signature and shouldn't rely on a default value.
  *
  * In future version of the protocol this property might become mandatory to
  * better express this.
  *
  * @param activeParameter
  *   The active parameter of the active signature. If omitted or the value lies
  *   outside the range of `signatures[activeSignature].parameters` defaults to
  *   0 if the active signature has parameters. If the active signature has no
  *   parameters it is ignored. In future version of the protocol this property
  *   might become mandatory to better express the active parameter if the
  *   active signature does have any.
  */
case class SignatureHelp(
    signatures: Vector[structures.SignatureInformation],
    activeSignature: Option[runtime.uinteger] = None,
    activeParameter: Option[runtime.uinteger] = None
)
object SignatureHelp extends codecs.structures_SignatureHelpCodec
