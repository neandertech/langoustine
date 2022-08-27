package langoustine.tracer

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*

import jsonrpclib.*

case class RawMessage private (
    jsonrpc: String,
    method: Option[String] = None,
    result: Option[Payload] = None,
    error: Option[ErrorPayload] = None,
    params: Option[Payload] = None,
    id: Option[CallId] = None
)

enum Direction:
  case ToServer, ToClient

enum TracerEvent:
  case LogLine(line: String)
  case LogLines(lines: Vector[String])
  case Update

object TracerEvent:
  given JsonValueCodec[TracerEvent] = JsonCodecMaker.make

enum Message:
  case Request(method: String, id: CallId) extends Message
  case Response(id: CallId)                extends Message
  case ClientNotification(method: String)  extends Message
  case ServerNotification(method: String)  extends Message

object Message:
  given JsonValueCodec[Message] = JsonCodecMaker.make

  def from(raw: RawMessage, direction: Direction): Option[Message] =
    raw.id match
      // notification
      case None =>
        direction match
          case Direction.ToServer =>
            raw.method.map(Message.ClientNotification.apply)
          case Direction.ToClient =>
            raw.method.map(Message.ServerNotification.apply)
      case Some(id) =>
        direction match
          case Direction.ToServer =>
            raw.method.map(Message.Request.apply(_, id))
          case Direction.ToClient =>
            raw.method match
              case None       => Some(Message.Response(id))
              case Some(what) => None

end Message

object RawMessage:
  import com.github.plokhotnyuk.jsoniter_scala.macros.*
  given JsonValueCodec[RawMessage] = JsonCodecMaker.make

  def create(
      method: Option[String] = None,
      result: Option[Payload] = None,
      error: Option[ErrorPayload] = None,
      params: Option[Payload] = None,
      id: Option[CallId] = None
  ) =
    RawMessage("2.0", method, result, error, params, id)
end RawMessage
