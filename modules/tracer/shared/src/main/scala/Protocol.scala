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

import jsonrpclib.*
import scala.util.Try

case class RawMessage(
    jsonrpc: String,
    method: Option[String] = None,
    result: Option[Payload] = None,
    error: Option[ErrorPayload] = None,
    params: Option[Payload] = None,
    id: Option[MessageId] = None
)

enum Direction:
  case ToServer, ToClient

enum TracerEvent:
  case LogLine(line: String)
  case LogLines(lines: Vector[String])
  case Update

object TracerEvent:
  given JsonValueCodec[TracerEvent] = JsonCodecMaker.make

enum Message(val id: MessageId):
  case Request(method: String, override val id: MessageId, responded: Boolean)
      extends Message(id)

  case Response(override val id: MessageId, method: Option[String])
      extends Message(id)

  case Notification(
      generatedId: MessageId,
      method: String,
      direction: Direction
  ) extends Message(generatedId)

  def methodName: Option[String] = this match
    case r: Request      => Some(r.method)
    case r: Notification => Some(r.method)
    case r: Response     => r.method

end Message

object Message:
  given JsonValueCodec[Message] = JsonCodecMaker.make

  def from(
      raw: RawMessage,
      direction: Direction,
      generatedId: MessageId
  ): Option[Message] =
    raw.id match
      // notification
      case None =>
        raw.method.map(
          Message.Notification(generatedId, _, direction)
        )
      case Some(id) =>
        direction match
          case Direction.ToServer =>
            raw.method.map(Message.Request.apply(_, id, responded = false))
          case Direction.ToClient =>
            raw.method match
              case None       => Some(Message.Response(id, None))
              case Some(what) => None

end Message

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

case class Summary(workingFolder: String, serverCommand: List[String])
object Summary:
  given JsonValueCodec[Summary] = JsonCodecMaker.make
