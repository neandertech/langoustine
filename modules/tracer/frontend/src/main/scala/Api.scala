package langoustine.tracer

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.*
import scala.scalajs.js
import scala.scalajs.js.Thenable.Implicits.*
import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import com.raquo.laminar.api.L.*
import jsonrpclib.CallId
import langoustine.tracer.Message
import org.scalajs.dom
import org.scalajs.dom.Event
import org.scalajs.dom.Fetch.fetch
import org.scalajs.dom.MessageEvent
import org.scalajs.dom.WebSocket
import scala.scalajs.js.Date
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.JSGlobal

object Api:

  given JsonValueCodec[Vector[Message]]        = JsonCodecMaker.make
  given rw: JsonValueCodec[Vector[RawMessage]] = JsonCodecMaker.make

  def all: Future[Vector[Message]] =
    fetch("/api/all")
      .flatMap(_.text())
      .map(str => readFromStringReentrant[Vector[Message]](str))

  def summary: Future[Summary] =
    fetch("/api/summary")
      .flatMap(_.text())
      .map(str => readFromStringReentrant[Summary](str))


  def rawAll: Future[Vector[RawMessage]] =
    fetch("/api/raw/all")
      .flatMap(_.text())
      .map(str => readFromStringReentrant[Vector[RawMessage]](str))

  def request(id: String): Future[Option[RawMessage]] =
    fetch(s"/api/raw/request/$id")
      .flatMap(resp =>
        if resp.status == 200 then
          resp
            .text()
            .map(readFromStringReentrant[RawMessage](_))
            .map(Option.apply)
        else Future.successful(None)
      )
  def response(id: String): Future[Option[RawMessage]] =
    fetch(s"/api/raw/response/$id")
      .flatMap(resp =>
        if resp.status == 200 then
          resp
            .text()
            .map(readFromStringReentrant[RawMessage](_))
            .map(Option.apply)
        else Future.successful(None)
      )

  def notification(id: String): Future[Option[RawMessage]] =
    fetch(s"/api/raw/notification/$id")
      .flatMap(resp =>
        if resp.status == 200 then
          resp
            .text()
            .map(readFromStringReentrant[RawMessage](_))
            .map(Option.apply)
        else Future.successful(None)
      )
end Api
