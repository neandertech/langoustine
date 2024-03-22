package langoustine.tracer

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*
import scala.util.Try
import jsonrpclib.{Payload, ErrorPayload}
import jsonrpclib.CallId
import jsonrpclib.InputMessage.*
import jsonrpclib.OutputMessage.*
import jsonrpclib.Message

enum TracerEvent:
  case LogLines(lines: Vector[LogMessage])
  case Update

object TracerEvent:
  given JsonValueCodec[TracerEvent] = JsonCodecMaker.make
