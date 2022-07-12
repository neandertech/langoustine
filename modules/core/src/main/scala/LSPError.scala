package langoustine

import scala.util.Try.apply
import scala.util.Try
import scala.util.Success
import upickle.default.{Reader, Writer}
import cats.syntax.all._
import cats.MonadThrow

enum LSPError(msg: String) extends Throwable(msg):
  case NotImplementedError(method: String) extends LSPError("Not implemented")
  case FailureParsing(in: ujson.Value) extends LSPError("Failed to parse input")
