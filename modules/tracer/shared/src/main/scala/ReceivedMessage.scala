package langoustine.tracer

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*
import scala.util.Try
import jsonrpclib.{Payload, ErrorPayload}
import jsonrpclib.CallId
import jsonrpclib.InputMessage.*
import jsonrpclib.OutputMessage.*
import jsonrpclib.Message

case class ReceivedMessage(
    timestamp: Long,
    raw: RawMessage,
    decoded: LspMessage
)

object ReceivedMessage:
  given JsonValueCodec[ReceivedMessage] = JsonCodecMaker.make
