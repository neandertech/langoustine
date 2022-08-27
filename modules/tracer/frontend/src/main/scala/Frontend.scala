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
end Api

@js.native
@JSGlobal
object hljs extends js.Object:
  def highlightAll(): Unit = js.native

object Frontend:

  val showing = Var(Option.empty[Message.Request | Message.Response])

  def cid(c: CallId) = c match
    case CallId.NumberId(n) => n.toString
    case CallId.StringId(s) => s

  def timeline(msgs: Vector[Message]) =
    import Message.*
    val fromClient = textAlign.left
    val fromServer = textAlign.right

    val request = Seq(
      padding         := "8px",
      backgroundColor := "lightgreen",
      fontSize        := "1.3rem"
    )
    val notif =
      Seq(padding := "8px", backgroundColor := "yellow", fontSize := "1.3rem")

    val base = Seq(borderBottom := "1px dotted lightgrey", width := "100%")

    import jsonrpclib.CallId

    inline def select(req: Message) =
      backgroundColor <-- showing.signal.map {
        case Some(`req`) => "black"
        case _           => ""
      }

    val noBorder = border := "1px solid darkgrey"

    div(
      display.flex,
      flexDirection.column,
      alignContent.stretch,
      borderLeft  := "1px solid black",
      borderRight := "1px solid black",
      div(
        width := "100%",
        display.flex,
        alignContent.spaceBetween,
        div(base, h2("client", marginTop := "0px")),
        div(base, h2("server", marginTop := "0px"), textAlign.right)
      ),
      msgs.reverse.collect {
        case rq @ Request(method, callId) =>
          div(
            base,
            fromClient,
            select(rq),
            button(
              noBorder,
              request,
              b(method),
              ": ",
              cid(callId),
              onClick.preventDefault.mapTo(rq) --> showing.someWriter
            )
          )
        case rp @ Response(id) =>
          div(
            select(rp),
            base,
            fromServer,
            button(
              noBorder,
              request,
              b("Response for"),
              ": ",
              cid(id),
              onClick.preventDefault.mapTo(rp) --> showing.someWriter
            )
          )
        case ClientNotification(method) =>
          div(base, button(noBorder, notif, fromClient, method))
        case ServerNotification(method) =>
          div(base, button(noBorder, notif, fromServer, method))
      }
    )
  end timeline

  val bus    = new EventBus[Double]
  val logBus = new EventBus[Double]

  val commandTracer =
    div(
      styleAttr := "display:flex; align-content:stretch; gap: 15px; height: 100%;",
      div(
        width           := "70%",
        borderRadius    := "5px",
        backgroundColor := "#0d1117",
        color.white,
        fontSize := "1.3rem",
        padding  := "10px",
        position := "sticky",
        top      := "0",
        overflow.scroll,
        child <-- showing.signal.flatMap {
          case None => Signal.fromValue(p(""))
          case Some(req: Message.Request) =>
            Signal.fromFuture(Api.request(cid(req.id))).map(_.flatten).map {
              case None => i("not found...")
              case Some(rmsg) =>
                val js = JSON.parse(
                  writeToStringReentrant(
                    rmsg
                  )
                )

                pre(
                  code(
                    className := "language-json",
                    JSON.stringify(js, space = 2),
                    onMountCallback(ctx => hljs.highlightAll())
                  )
                )
            }
          case Some(req: Message.Response) =>
            Signal.fromFuture(Api.response(cid(req.id))).map(_.flatten).map {
              case None => i("not found...")
              case Some(rmsg) =>
                val js = JSON.parse(
                  writeToStringReentrant(
                    rmsg
                  )
                )

                pre(
                  code(
                    className := "language-json",
                    JSON.stringify(js, space = 2),
                    onMountCallback(ctx => hljs.highlightAll())
                  )
                )
            }
        }
      ),
      div(
        width := "30%",
        child <-- bus.events.startWith(Date.now()).flatMap { _ =>
          Signal
            .fromFuture(Api.all)
            .map(_.toVector.flatten)
            .map(timeline)
        }
      )
    )

  enum Page:
    case Logs, Commands

  val page = Var(Page.Commands)

  val logs = Var(Vector.empty[String])

  val switcher =
    inline def thing(name: String, set: Page) =
      div(
        fontSize := "1.5rem",
        padding  := "10px",
        child <-- page.signal.map {
          case `set` => b(name)
          case other =>
            a(
              href := "#",
              onClick.preventDefault.mapTo(set) --> page.writer,
              name
            )
        }
      )

    div(
      display.flex,
      maxWidth := "300px",
      alignContent.stretch,
      thing("Interactions", Page.Commands),
      thing("Logs", Page.Logs)
    )
  end switcher

  val logTracer =
    div(
      width           := "95%",
      borderRadius    := "5px",
      backgroundColor := "black",
      color.white,
      fontSize := "1.3rem",
      padding  := "10px",
      overflow.scroll,
      pre(code(children <-- logs.signal.map { lines =>
        lines.map(p(_))
      }))
    )

  val myApp =
    div(
      fontFamily := "'Wotfard',Futura,-apple-system,sans-serif",
      h1("Langoustine tracer"),
      p("welcome to the future of LSP tooling"),
      switcher,
      child <-- page.signal.map {
        case Page.Commands =>
          commandTracer
        case Page.Logs =>
          logTracer
      }
    )

  def main(args: Array[String]): Unit =
    documentEvents.onDomContentLoaded.foreach { _ =>
      val hs   = dom.window.location.host
      val sock = new WebSocket(s"ws://$hs/api/ws/events")

      sock.addEventListener[Event](
        "message",
        (event: Event) =>
          event match
            case event: MessageEvent =>
              val data = event.data.toString
              readFromStringReentrant[TracerEvent](data) match
                case TracerEvent.Update => bus.emit(Date.now())
                case TracerEvent.LogLine(l) =>
                  logs.update(v => v.drop(v.length + 1 - 1000) :+ l)
                case TracerEvent.LogLines(l) =>
                  logs.update(v => v.drop(v.length + l.length - 1000) ++ l)
      )
      render(dom.document.getElementById("appContainer"), myApp)
    }(unsafeWindowOwner)
end Frontend
