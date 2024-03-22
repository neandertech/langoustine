package langoustine.tracer

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*
import scala.util.Try
import jsonrpclib.{Payload, ErrorPayload}
import jsonrpclib.CallId
import jsonrpclib.InputMessage.*
import jsonrpclib.OutputMessage.*
import jsonrpclib.Message

enum Summary:
  case Trace(workingFolder: String, serverCommand: List[String])
  case Replay(file: String)

object Summary:
  given JsonValueCodec[Summary] = JsonCodecMaker.make
