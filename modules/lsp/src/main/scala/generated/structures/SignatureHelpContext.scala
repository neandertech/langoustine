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

/** Additional information about the context in which a signature help request
  * was triggered.
  *
  * @since 3.15.0
  *
  * @param triggerKind
  *   Action that caused signature help to be triggered.
  *
  * @param triggerCharacter
  *   Character that caused signature help to be triggered.
  *
  * This is undefined when
  * `triggerKind !== SignatureHelpTriggerKind.TriggerCharacter`
  *
  * @param isRetrigger
  *   `true` if signature help was already showing when it was triggered.
  *
  * Retriggers occurs when the signature help is already active and can be
  * caused by actions such as typing a trigger character, a cursor move, or
  * document content changes.
  *
  * @param activeSignatureHelp
  *   The currently active `SignatureHelp`.
  *
  * The `activeSignatureHelp` has its `SignatureHelp.activeSignature` field
  * updated based on the user navigating through available signatures.
  */
case class SignatureHelpContext(
    triggerKind: enumerations.SignatureHelpTriggerKind,
    triggerCharacter: Option[String] = None,
    isRetrigger: Boolean,
    activeSignatureHelp: Option[structures.SignatureHelp] = None
)
object SignatureHelpContext extends codecs.structures_SignatureHelpContextCodec
