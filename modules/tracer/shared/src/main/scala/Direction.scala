package langoustine.tracer

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*
import scala.util.Try
import jsonrpclib.{Payload, ErrorPayload}
import jsonrpclib.CallId
import jsonrpclib.InputMessage.*
import jsonrpclib.OutputMessage.*
import jsonrpclib.Message

enum Direction:
  case ToServer, ToClient

  def reverse: Direction = this match
    case ToServer => ToClient
    case ToClient => ToServer
