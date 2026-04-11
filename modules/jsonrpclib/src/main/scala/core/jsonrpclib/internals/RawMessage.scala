package jsonrpclib
package internals

import com.github.plokhotnyuk.jsoniter_scala.core._
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import com.github.plokhotnyuk.jsoniter_scala.macros.CodecMakerConfig

private[jsonrpclib] case class RawMessage(
    jsonrpc: String,
    method: Option[String] = None,
    result: Option[Option[Payload]] = None,
    error: Option[ErrorPayload] = None,
    params: Option[Payload] = None,
    id: Option[CallId] = None
) {

  def toMessage: Either[ProtocolError, Message] = (id, method) match {
    case (Some(callId), Some(method)) =>
      Right(InputMessage.RequestMessage(method, callId, params))
    case (None, Some(method)) =>
      Right(InputMessage.NotificationMessage(method, params))
    case (Some(callId), None) =>
      (error, result) match {
        case (Some(error), _) => Right(OutputMessage.ErrorMessage(callId, error))
        case (_, Some(data))  => Right(OutputMessage.ResponseMessage(callId, data.getOrElse(Payload.NullPayload)))
        case (None, None) =>
          Left(
            ProtocolError.InvalidRequest(
              "call id was set and method unset, but neither result, nor error fields were present"
            )
          )
      }
    case (None, None) =>
      Left(
        ProtocolError.InvalidRequest(
          "neither call id nor method were set"
        )
      )
  }
}

private[jsonrpclib] object RawMessage {

  val `2.0` = "2.0"

  def from(message: Message): RawMessage = message match {
    case InputMessage.NotificationMessage(method, params) => RawMessage(`2.0`, method = Some(method), params = params)
    case InputMessage.RequestMessage(method, callId, params) =>
      RawMessage(`2.0`, method = Some(method), params = params, id = Some(callId))
    case OutputMessage.ErrorMessage(callId, errorPayload) =>
      RawMessage(`2.0`, error = Some(errorPayload), id = Some(callId))
    case OutputMessage.ResponseMessage(callId, data) =>
      RawMessage(`2.0`, result = Some(data.stripNull), id = Some(callId))
  }

  implicit val rawMessageJsonValueCodecs: JsonValueCodec[RawMessage] =
    JsonCodecMaker.make(CodecMakerConfig.withSkipNestedOptionValues(true))

}
