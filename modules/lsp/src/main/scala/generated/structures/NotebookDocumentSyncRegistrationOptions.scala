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

/** Registration options specific to a notebook.
  *
  * @since 3.17.0
  *
  * @param notebookSelector
  *   The notebooks to be synced
  *
  * @param save
  *   Whether save notification should be forwarded to the server. Will only be
  *   honored if mode === `notebook`.
  *
  * @param id
  *   The id used to register the request. The id can be used to deregister the
  *   request again. See also Registration#id.
  */
case class NotebookDocumentSyncRegistrationOptions(
    notebookSelector: Vector[
      (NotebookDocumentSyncRegistrationOptions.S0 |
        NotebookDocumentSyncRegistrationOptions.S1)
    ],
    save: Option[Boolean] = None,
    id: Option[String] = None
)
object NotebookDocumentSyncRegistrationOptions
    extends codecs.structures_NotebookDocumentSyncRegistrationOptionsCodec:
  /** @param notebook
    *   The notebook to be synced If a string value is provided it matches
    *   against the notebook type. '*' matches every notebook.
    *
    * @param cells
    *   The cells of the matching notebook to be synced.
    */
  case class S0(
      notebook: (String | aliases.NotebookDocumentFilter),
      cells: Option[Vector[S0.S0]] = None
  )
  object S0
      extends codecs.structures_NotebookDocumentSyncRegistrationOptions_S0Codec:
    case class S0(
        language: String
    )
    object S0
        extends codecs.structures_NotebookDocumentSyncRegistrationOptions_S0_S0Codec

  /** @param notebook
    *   The notebook to be synced If a string value is provided it matches
    *   against the notebook type. '*' matches every notebook.
    *
    * @param cells
    *   The cells of the matching notebook to be synced.
    */
  case class S1(
      notebook: Option[(String | aliases.NotebookDocumentFilter)] = None,
      cells: Vector[S1.S0]
  )
  object S1
      extends codecs.structures_NotebookDocumentSyncRegistrationOptions_S1Codec:
    case class S0(
        language: String
    )
    object S0
        extends codecs.structures_NotebookDocumentSyncRegistrationOptions_S1_S0Codec
end NotebookDocumentSyncRegistrationOptions
