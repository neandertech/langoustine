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
import jsonrpclib.CallId
import jsonrpclib.ErrorPayload
import jsonrpclib.InputMessage.*
import jsonrpclib.Message
import jsonrpclib.OutputMessage.*
import jsonrpclib.Payload

case class RawMessage(
    jsonrpc: String,
    method: Option[String] = None,
    result: Option[Payload] = None,
    error: Option[ErrorPayload] = None,
    params: Option[Payload] = None,
    id: Option[CallId] = None
):
  def toMessage: Option[Message] =
    id match
      // notification
      case None =>
        method.map(
          NotificationMessage(_, params)
        )
      case Some(id) => // it's a request/response
        method match
          case None =>
            error
              .map(ErrorMessage(id, _))
              .orElse(
                result.map(
                  ResponseMessage(id, _)
                )
              )
          case Some(value) =>
            Some(RequestMessage(value, id, params))
end RawMessage

object RawMessage:
  import com.github.plokhotnyuk.jsoniter_scala.macros.*

  given JsonValueCodec[RawMessage] = ??? // JsonCodecMaker.make

  def create(
      method: Option[String] = None,
      result: Option[Payload] = None,
      error: Option[ErrorPayload] = None,
      params: Option[Payload] = None,
      id: Option[CallId] = None
  ) =
    RawMessage("2.0", method, result, error, params, id)
end RawMessage
