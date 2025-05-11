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

package langoustine.tracer

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*
import jsonrpclib.CallId
import jsonrpclib.InputMessage.*

enum LspMessage(val id: CallId):
  case Request(
      method: String,
      override val id: CallId,
      responded: Boolean,
      direction: Direction
  ) extends LspMessage(id)

  case Response(
      override val id: CallId,
      method: Option[String],
      direction: Direction
  ) extends LspMessage(id)

  case Notification(
      generatedId: CallId,
      method: String,
      direction: Direction
  ) extends LspMessage(generatedId)

  def methodName: Option[String] = this match
    case r: Request      => Some(r.method)
    case r: Notification => Some(r.method)
    case r: Response     => r.method
end LspMessage

object LspMessage:
  given JsonValueCodec[LspMessage] = JsonCodecMaker.make

  def from(
      raw: RawMessage,
      direction: Direction,
      generatedId: CallId
  ): Option[LspMessage] =
    raw.id match
      // notification
      case None =>
        raw.method.map(
          LspMessage.Notification(generatedId, _, direction)
        )
      case Some(id) => // it's a request/response
        raw.method match
          case None =>
            Some(LspMessage.Response(id, None, direction))
          case Some(value) =>
            Some(LspMessage.Request(value, id, responded = false, direction))
end LspMessage
