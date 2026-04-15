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

/** @param executionOrder
  *   A strict monotonically increasing value indicating the execution order of
  *   a cell inside a notebook.
  *
  * @param success
  *   Whether the execution was successful or not if known by the client.
  */
case class ExecutionSummary(
    executionOrder: runtime.uinteger,
    success: Option[Boolean] = None
)
object ExecutionSummary extends codecs.structures_ExecutionSummaryCodec
