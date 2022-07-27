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

  trait Notification extends Message:
    def method: String
    def params: ujson.Value

  private case class NotificationImpl(method: String, params: ujson.Value) extends Notification

  object Notification:
    given Reader[Notification] = 
      upickle.default.macroR[NotificationImpl].map(_.asInstanceOf[Notification])

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
    def result: Option[ujson.Value]
    def error: Option[ResponseError]

  end ResponseMessage

  private case class RequestMessageImpl(
      id: Int | String,
      method: String,
      params: ujson.Value
  ) extends RequestMessage

  private class ResponseMessageImpl(
      val id: Int | String | Null,
      val result: Option[ujson.Value],
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
      result: Either[ResponseError, ujson.Value]
  ): ResponseMessage =
    ResponseMessageImpl(id, result.toOption, result.left.toOption)

  private case class ResponseErrorImpl(val code: Int, val message: String)
      extends ResponseError
  private object ResponseErrorImpl:
    given upickle.default.ReadWriter[ResponseErrorImpl] =
      upickle.default.macroRW

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

    s"Content-Length: ${str.length}\r\n\r\n$str"
  end render

  def render(req: ResponseMessage) =
    val data =
      req.result.map("result" -> _) orElse
        req.error.map(e =>
          "error" ->
            ujson.Obj(
              "code"    -> ujson.Num(e.code),
              "message" -> ujson.Str(e.message)
            )
        )

    val obj = ujson.Obj(
      "id" ->
        (req.id match
          case i: Int    => ujson.Num(i)
          case i: String => ujson.Str(i)
        ),
      (data.toSeq ++ Seq("jsonrpc" -> ujson.Str(req.jsonrpc)))*
    )
    val str = upickle.default.write(obj)

    s"Content-Length: ${str.length}\r\n\r\n$str"
  end render
end JSONRPC
