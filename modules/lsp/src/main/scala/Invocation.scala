package langoustine.lsp

import upickle.default.{Reader, Writer}
import cats.MonadThrow
import jsonrpclib.Endpoint
import requests.{LSPRequest, LSPNotification}
import jsonrpclib.Monadic
import jsonrpclib.Codec
import jsonrpclib.Payload
import jsonrpclib.Channel

trait Invocation[P, F[_]]:
  def params: P
  def toClient: Communicate[F]

object Invocation:
  private[lsp] case class Impl[P, F[_]](params: P, toClient: Communicate[F])
      extends Invocation[P, F]
