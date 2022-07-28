package langoustine.lsp

import scala.util.Try.apply
import scala.util.Try
import scala.util.Success
import upickle.default.{Reader, Writer}
import cats.syntax.all.*
import cats.MonadThrow

import requests.LSPRequest
import notifications.LSPNotification

trait LSPBuilder[F[_]]:
  def handleRequest[X <: LSPRequest](t: X)(
      f: (t.In, X) => F[t.Out | LSPError]
  ): LSPBuilder[F]

  def handleNotification[X <: LSPNotification](t: X)(
      f: (t.In, X) => F[Unit | LSPError]
  ): LSPBuilder[F]

  def build: (JSONRPC.RequestMessage | JSONRPC.Notification) => F[
    Option[JSONRPC.ResponseMessage]
  ]
end LSPBuilder
