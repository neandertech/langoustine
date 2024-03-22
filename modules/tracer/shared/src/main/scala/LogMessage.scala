package langoustine.tracer

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*
import scala.util.Try
import jsonrpclib.{Payload, ErrorPayload}
import jsonrpclib.CallId
import jsonrpclib.InputMessage.*
import jsonrpclib.OutputMessage.*
import jsonrpclib.Message

enum LogMessage(val value: String):
  case Window(override val value: String, timestamp: Long)
      extends LogMessage(value)
  case Stderr(override val value: String, timestamp: Long)
      extends LogMessage(value)

object LogMessage:
  given JsonValueCodec[LogMessage]         = JsonCodecMaker.make
  given JsonValueCodec[Vector[LogMessage]] = JsonCodecMaker.make
