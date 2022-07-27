package langoustine

import scala.util.Try.apply
import scala.util.Try
import scala.util.Success
import upickle.default.{Reader, Writer}
import cats.syntax.all.*
import cats.MonadThrow

enum LSPError(msg: String, tr: Throwable = null) extends Throwable(msg, tr):
  case NotImplementedError(method: String) extends LSPError("Not implemented")
  case FailureParsing(in: ujson.Value, reason: Throwable = null)
      extends LSPError(s"Failed to parse input '$in' $reason", reason)
