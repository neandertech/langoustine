package jsonrpclib

import com.github.plokhotnyuk.jsoniter_scala.core.JsonReader
import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.core.JsonWriter

sealed trait Message { def maybeCallId: Option[CallId] }
sealed trait InputMessage extends Message { def method: String }
sealed trait OutputMessage extends Message {
  def callId: CallId; final override def maybeCallId: Option[CallId] = Some(callId)
}

object InputMessage {
  case class RequestMessage(method: String, callId: CallId, params: Option[Payload]) extends InputMessage {
    def maybeCallId: Option[CallId] = Some(callId)
  }
  case class NotificationMessage(method: String, params: Option[Payload]) extends InputMessage {
    def maybeCallId: Option[CallId] = None
  }
}
object OutputMessage {
  def errorFrom(callId: CallId, protocolError: ProtocolError): OutputMessage =
    ErrorMessage(callId, ErrorPayload(protocolError.code, protocolError.getMessage(), None))

  case class ErrorMessage(callId: CallId, payload: ErrorPayload) extends OutputMessage
  case class ResponseMessage(callId: CallId, data: Payload) extends OutputMessage
}

object Message {

  implicit val messageJsonValueCodecs: JsonValueCodec[Message] = new JsonValueCodec[Message] {
    val rawMessageCodec = implicitly[JsonValueCodec[internals.RawMessage]]
    def decodeValue(in: JsonReader, default: Message): Message =
      rawMessageCodec.decodeValue(in, null).toMessage match {
        case Left(error)  => throw error
        case Right(value) => value
      }
    def encodeValue(x: Message, out: JsonWriter): Unit =
      rawMessageCodec.encodeValue(internals.RawMessage.from(x), out)
    def nullValue: Message = null
  }
}
