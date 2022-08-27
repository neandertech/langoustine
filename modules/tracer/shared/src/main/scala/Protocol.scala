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
  case Request(method: String, id: CallId, params: Option[Payload])
      extends Message
  case Response(id: CallId, result: Either[ErrorPayload, Option[Payload]])
      extends Message
  case Notification(
      method: String,
      params: Option[Payload],
      error: Option[ErrorPayload],
      direction: Direction
  ) extends Message

  def methodName: Option[String] = this match
    case r: Request      => Some(r.method)
    case r: Notification => Some(r.method)
    case _               => None
end Message

object Message:
  given JsonValueCodec[Message] = JsonCodecMaker.make

  def makeResult(rm: RawMessage): Either[ErrorPayload, Option[Payload]] =
    rm.error match
      case None     => Right(rm.result)
      case Some(er) => Left(er)

  def from(raw: RawMessage, direction: Direction): Option[Message] =
    raw.id match
      // notification
      case None =>
        raw.method.map(
          Message.Notification(_, raw.params, raw.error, direction)
        )
      case Some(id) =>
        direction match
          case Direction.ToServer =>
            raw.method.map(Message.Request.apply(_, id, raw.params))
          case Direction.ToClient =>
            raw.method match
              case None       => Some(Message.Response(id, makeResult(raw)))
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

case class Summary(workingFolder: String, serverCommand: List[String])
object Summary:
  given JsonValueCodec[Summary] = JsonCodecMaker.make
