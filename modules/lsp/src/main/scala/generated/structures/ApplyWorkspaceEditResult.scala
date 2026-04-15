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

/** The result returned from the apply workspace edit request.
  *
  * @since 3.17
  *   renamed from ApplyWorkspaceEditResponse
  *
  * @param applied
  *   Indicates whether the edit was applied or not.
  *
  * @param failureReason
  *   An optional textual description for why the edit was not applied. This may
  *   be used by the server for diagnostic logging or to provide a suitable
  *   error for a request that triggered the edit.
  *
  * @param failedChange
  *   Depending on the client's failure handling strategy `failedChange` might
  *   contain the index of the change that failed. This property is only
  *   available if the client signals a `failureHandlingStrategy` in its client
  *   capabilities.
  */
case class ApplyWorkspaceEditResult(
    applied: Boolean,
    failureReason: Option[String] = None,
    failedChange: Option[runtime.uinteger] = None
)
object ApplyWorkspaceEditResult
    extends codecs.structures_ApplyWorkspaceEditResultCodec
