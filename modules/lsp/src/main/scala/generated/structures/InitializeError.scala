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

/** The data type of the ResponseError if the initialize request fails.
  *
  * @param retry
  *   Indicates whether the client execute the following retry logic: (1) show
  *   the message provided by the ResponseError to the user (2) user selects
  *   retry or cancel (3) if user selected retry the initialize method is sent
  *   again.
  */
case class InitializeError(
    retry: Boolean
)
object InitializeError extends codecs.structures_InitializeErrorCodec
