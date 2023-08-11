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

import scala.util.Try
import jsonrpclib.{Payload, ErrorPayload}

case class ReceivedMessage(
    timestamp: Long,
    raw: RawMessage,
    decoded: LspMessage
)

object ReceivedMessage:
  given JsonValueCodec[ReceivedMessage] = JsonCodecMaker.make

case class RawMessage(
    jsonrpc: String,
    method: Option[String] = None,
    result: Option[Payload] = None,
    error: Option[ErrorPayload] = None,
    params: Option[Payload] = None,
    id: Option[MessageId] = None
)

object RawMessage:
  import com.github.plokhotnyuk.jsoniter_scala.macros.*

  given JsonValueCodec[RawMessage] = JsonCodecMaker.make

  def create(
      method: Option[String] = None,
      result: Option[Payload] = None,
      error: Option[ErrorPayload] = None,
      params: Option[Payload] = None,
      id: Option[MessageId] = None
  ) =
    RawMessage("2.0", method, result, error, params, id)
end RawMessage

enum Direction:
  case ToServer, ToClient

  def reverse: Direction = this match
    case ToServer => ToClient
    case ToClient => ToServer

enum LogMessage(val value: String):
  case Window(override val value: String, timestamp: Long)
      extends LogMessage(value)
  case Stderr(override val value: String, timestamp: Long)
      extends LogMessage(value)

object LogMessage:
  given JsonValueCodec[LogMessage]         = JsonCodecMaker.make
  given JsonValueCodec[Vector[LogMessage]] = JsonCodecMaker.make

enum TracerEvent:
  case LogLines(lines: Vector[LogMessage])
  case Update

object TracerEvent:
  given JsonValueCodec[TracerEvent] = JsonCodecMaker.make

enum LspMessage(val id: MessageId):
  case Request(
      method: String,
      override val id: MessageId,
      responded: Boolean,
      direction: Direction
  ) extends LspMessage(id)

  case Response(
      override val id: MessageId,
      method: Option[String],
      direction: Direction
  ) extends LspMessage(id)

  case Notification(
      generatedId: MessageId,
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
      generatedId: MessageId
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

enum MessageId:
  case StringId(id: String)
  case NumberId(id: Long)
  case NullId

object MessageId:
  given JsonValueCodec[MessageId] = new JsonValueCodec[MessageId]:
    def decodeValue(in: JsonReader, default: MessageId): MessageId =
      Try(in.readLong())
        .map(MessageId.NumberId.apply)
        .orElse(Try {
          in.rollbackToken()
          in.readString(null)
        }.map(MessageId.StringId.apply))
        .orElse(scala.util.Success(default))
        .get

    import MessageId.*

    def encodeValue(x: MessageId, out: JsonWriter): Unit = x match
      case NumberId(long)   => out.writeVal(long)
      case StringId(string) => out.writeVal(string)
      case NullId           => out.writeNull()

    def nullValue: MessageId = MessageId.NullId
end MessageId

enum Summary:
  case Trace(workingFolder: String, serverCommand: List[String])
  case Replay(file: String)
object Summary:
  given JsonValueCodec[Summary] = JsonCodecMaker.make
