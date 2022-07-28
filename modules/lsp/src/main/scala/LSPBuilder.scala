package langoustine

import scala.util.Try.apply
import scala.util.Try
import scala.util.Success
import upickle.default.{Reader, Writer}
import cats.syntax.all.*
import cats.MonadThrow

trait LSPBuilder[F[_]]:
  def handler[X <: LSPRequest](t: X)(
      f: (t.In, X) => F[t.Out | LSPError]
  ): LSPBuilder[F]

  def handleRest(f: [X <: LSPRequest] => X => F[Unit | LSPError]) = ???

  def build: JSONRPC.RequestMessage => F[JSONRPC.ResponseMessage]
