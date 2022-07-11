package langoustine

import scala.util.Try.apply
import scala.util.Try
import scala.util.Success
import upickle.default.Reader
import cats.MonadThrow

object JSONRPC:
  trait Message:
    def jsonrpc: "2.0" = "2.0"

  trait ResponseError:
    def code: Int
    def message: String

  trait RequestMessage extends Message:
    def id: Int | String
    def method: String
    def params: String

  trait ResponseMessage extends Message:
    def id: Int | String | Null
    def result: Option[String]
    def error: Option[ResponseError]

  private class RequestMessageImpl(
      val id: Int | String,
      val method: String,
      val params: String
  ) extends RequestMessage

  private class ResponseMessageImpl(
      val id: Int | String | Null,
      val result: Option[String],
      val error: Option[ResponseError]
  ) extends ResponseMessage

  def request(
      id: Int | String,
      method: String,
      params: String
  ): RequestMessage =
    RequestMessageImpl(id, method, params)

  def response(
      id: Int | String | Null,
      result: Either[ResponseError, String]
  ): ResponseMessage =
    ResponseMessageImpl(id, result.toOption, result.left.toOption)

  private class ResponseErrorImpl(val code: Int, val message: String)
      extends ResponseError

  def error(code: Int, message: String): ResponseError =
    ResponseErrorImpl(code, message)

  type Handler[F[_]] = RequestMessage => F[ResponseMessage]

