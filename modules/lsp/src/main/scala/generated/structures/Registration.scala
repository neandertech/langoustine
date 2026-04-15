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

/** General parameters to register for a notification or to register a provider.
  *
  * @param id
  *   The id used to register the request. The id can be used to deregister the
  *   request again.
  *
  * @param method
  *   The method / capability to register for.
  *
  * @param registerOptions
  *   Options necessary for the registration.
  */
case class Registration(
    id: String,
    method: String,
    registerOptions: Option[io.circe.Json] = None
)
object Registration extends codecs.structures_RegistrationCodec
