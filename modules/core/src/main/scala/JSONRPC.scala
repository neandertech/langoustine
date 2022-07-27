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
    def params: ujson.Value

  object RequestMessage:
    private given Reader[Int | String] =
      upickle.default.reader[ujson.Value].map[Int | String] {
        case ujson.Str(s) => s
        case ujson.Num(v) => v.toInt
        case u            => throw LSPError.FailureParsing(u)
      }
    given Reader[RequestMessage] = upickle.default
      .macroR[RequestMessageImpl]
      .map(_.asInstanceOf[RequestMessage])
  end RequestMessage

  trait ResponseMessage extends Message:
    def id: Int | String | Null
    def result: Option[String]
    def error: Option[ResponseError]

  end ResponseMessage

  private case class RequestMessageImpl(
      id: Int | String,
      method: String,
      params: ujson.Value
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

  def render(req: RequestMessage) =
    val obj = ujson.Obj(
      "id" ->
        (req.id match
          case i: Int    => ujson.Num(i)
          case i: String => ujson.Str(i)
        ),
      "method"  -> ujson.Str(req.method),
      "jsonrpc" -> ujson.Str(req.jsonrpc)
    )
    val str = upickle.default.write(obj)

    s"Content-Length: ${str.length}\n\n$str"
  end render
end JSONRPC
