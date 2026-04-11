package jsonrpclib

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker

case class ErrorPayload(code: Int, message: String, data: Option[Payload]) extends Throwable {
  override def getMessage(): String = s"JsonRPC Error $code: $message"
}

object ErrorPayload {

  implicit val rawMessageStubJsonValueCodecs: JsonValueCodec[ErrorPayload] =
    JsonCodecMaker.make

}
