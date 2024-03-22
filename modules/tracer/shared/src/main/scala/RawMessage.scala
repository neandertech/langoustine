package langoustine.tracer

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*
import scala.util.Try
import jsonrpclib.{Payload, ErrorPayload}
import jsonrpclib.CallId
import jsonrpclib.InputMessage.*
import jsonrpclib.OutputMessage.*
import jsonrpclib.Message

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
