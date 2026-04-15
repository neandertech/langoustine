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

/** @param openClose
  *   Open and close notifications are sent to the server. If omitted open close
  *   notification should not be sent.
  *
  * @param change
  *   Change notifications are sent to the server. See
  *   TextDocumentSyncKind.None, TextDocumentSyncKind.Full and
  *   TextDocumentSyncKind.Incremental. If omitted it defaults to
  *   TextDocumentSyncKind.None.
  *
  * @param willSave
  *   If present will save notifications are sent to the server. If omitted the
  *   notification should not be sent.
  *
  * @param willSaveWaitUntil
  *   If present will save wait until requests are sent to the server. If
  *   omitted the request should not be sent.
  *
  * @param save
  *   If present save notifications are sent to the server. If omitted the
  *   notification should not be sent.
  */
case class TextDocumentSyncOptions(
    openClose: Option[Boolean] = None,
    change: Option[enumerations.TextDocumentSyncKind] = None,
    willSave: Option[Boolean] = None,
    willSaveWaitUntil: Option[Boolean] = None,
    save: Option[(Boolean | structures.SaveOptions)] = None
)
object TextDocumentSyncOptions
    extends codecs.structures_TextDocumentSyncOptionsCodec
