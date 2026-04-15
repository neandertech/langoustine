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

/** Additional information that describes document changes.
  *
  * @since 3.16.0
  *
  * @param label
  *   A human-readable string describing the actual change. The string is
  *   rendered prominent in the user interface.
  *
  * @param needsConfirmation
  *   A flag which indicates that user confirmation is needed before applying
  *   the change.
  *
  * @param description
  *   A human-readable string which is rendered less prominent in the user
  *   interface.
  */
case class ChangeAnnotation(
    label: String,
    needsConfirmation: Option[Boolean] = None,
    description: Option[String] = None
)
object ChangeAnnotation extends codecs.structures_ChangeAnnotationCodec
