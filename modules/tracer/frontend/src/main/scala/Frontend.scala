package langoustine.tracer

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.*
import scala.scalajs.js
import scala.scalajs.js.Thenable.Implicits.*
import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import com.raquo.laminar.api.L.*
import langoustine.tracer.Message
import org.scalajs.dom
import org.scalajs.dom.Event
import org.scalajs.dom.Fetch.fetch
import org.scalajs.dom.MessageEvent
import org.scalajs.dom.WebSocket
import scala.scalajs.js.Date
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal
object hljs extends js.Object:
  def highlightAll(): Unit = js.native

enum Page:
  case Logs, Commands

object Frontend:

  val showing       = Var(Option.empty[Message])
  val page          = Var(Page.Commands)
  val logs          = Var(Vector.empty[String])
  val commandFilter = Var(Option.empty[String])
  val logFilter     = Var(Option.empty[String])
  val bus           = new EventBus[Double]
  val logBus        = new EventBus[Double]

  def cid(c: MessageId) = c match
    case MessageId.NumberId(n) => n.toString
    case MessageId.StringId(s) => s

  def timeline(msgs: Vector[Message]) =
    import Message.*
    val fromClient = textAlign.left
    val fromServer = textAlign.right

    inline def select(req: Message) =
      backgroundColor <-- showing.signal.map {
        case Some(`req`) => "black"
        case _           => ""
      }

    div(
      display.flex,
      flexDirection.column,
      borderLeft  := "1px solid black",
      borderRight := "1px solid black",
      overflow.scroll,
      div(
        width := "100%",
        display.flex,
        alignContent.spaceBetween,
        div(h2("client", marginTop := "0px"), width                  := "100%"),
        div(h2("server", marginTop := "0px"), textAlign.right, width := "100%")
      ),
      children <-- commandFilter.signal.map { filter =>
        msgs
          .filter(m =>
            filter.isEmpty || m.methodName.exists(
              _.toLowerCase.contains(filter.getOrElse(""))
            )
          )
          .reverse
          .collect {
            case rq: Request =>
              div(
                Styles.timeline.row,
                fromClient,
                select(rq),
                button(
                  Styles.timeline.requestButton,
                  b(rq.method),
                  ": ",
                  cid(rq.id),
                  onClick.preventDefault.mapTo(rq) --> showing.someWriter
                )
              )
            case rp: Response =>
              div(
                select(rp),
                Styles.timeline.row,
                fromServer,
                button(
                  Styles.timeline.requestButton,
                  b("Response for"),
                  ": ",
                  cid(rp.id),
                  onClick.preventDefault.mapTo(rp) --> showing.someWriter
                )
              )
            case cm: Notification =>
              div(
                select(cm),
                Styles.timeline.row,
                if (cm.direction == Direction.ToClient)
                then fromServer
                else fromClient,
                button(
                  Styles.timeline.notificationButton,
                  cm.method,
                  onClick.preventDefault.mapTo(cm) --> showing.someWriter
                )
              )
          }
      }
    )
  end timeline

  def displayJson[T: JsonValueCodec](rmsg: T) =
    val js =
      writeToString(
        rmsg,
        WriterConfig.withIndentionStep(4)
      )

    pre(
      code(
        className := "language-json",
        JSON.stringify(JSON.parse(js), space = 2),
        onMountCallback(ctx => hljs.highlightAll())
      )
    )
  end displayJson

  import jsonrpclib.{ErrorPayload, Payload}

  def displayErr(ep: ErrorPayload) =
    div(b(color := "pink", "Error"), displayJson(ep))

  given JsonValueCodec[Option[Payload]] = JsonCodecMaker.make

  def displayPayload(name: String, op: Option[Payload]) =
    div(b(color := "lightgreen", name, displayJson(op)))

  val commandTracer =
    div(
      display.flex,
      alignItems.flexStart,
      columnGap := "15px",
      div(
        Styles.jsonViewer,
        display <-- showing.signal
          .map(_.isDefined)
          .map(if (_) then "block" else "none"),
        overflow.scroll,
        child <-- showing.signal.flatMap {
          case None =>
            Signal.fromValue(
              i("Select any operation on the right to see its result")
            )

          case Some(req: Message.Request) =>
            Signal
              .fromFuture(Api.request(cid(req.id)))
              .map(_.flatten)
              .map { raw =>
                displayPayload("Params", raw.flatMap(_.params))
              }

          case Some(req: Message.Response) =>
            Signal
              .fromFuture(Api.response(cid(req.id)))
              .map(_.flatten)
              .map {
                case None => i("...")
                case Some(raw) =>
                  def makeResult(
                      rm: RawMessage
                  ): Either[ErrorPayload, Option[Payload]] =
                    rm.error match
                      case None     => Right(rm.result)
                      case Some(er) => Left(er)
                  makeResult(raw) match
                    case Left(ep) =>
                      displayErr(ep)
                    case Right(op) =>
                      displayPayload("Result", op)
              }

          case Some(cm: Message.Notification) =>
            Signal
              .fromFuture(Api.notification(cid(cm.generatedId)))
              .map(_.flatten)
              .map {
                case None => i("...")
                case Some(raw) =>
                  (raw.params, raw.error) match
                    case (None, None) => i("no error or payload")
                    case (_, Some(err)) =>
                      displayErr(err)
                    case (p, None) =>
                      displayPayload("Params", p)
              }
        }
      ),
      div(
        width <-- showing.signal
          .map(_.isDefined)
          .map(if (_) then "30%" else "100%"),
        input(
          Styles.filterBox,
          onInput.mapToValue.map(s =>
            Option.when(s.nonEmpty)(s.trim.toLowerCase)
          ) --> commandFilter.writer
        ),
        child <-- bus.events
          .startWith(Date.now())
          .flatMap { _ =>
            Signal
              .fromFuture(Api.all)
              .map(_.toVector.flatten)
              .map(timeline)
          }
      )
    )

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
      input(
        Styles.filterBox,
        onInput.mapToValue.map(s =>
          Option.when(s.nonEmpty)(s.trim.toLowerCase)
        ) --> logFilter.writer
      ),
      pre(
        code(
          children <--
            logs.signal.combineWith(logFilter.signal).map { case (lines, f) =>
              lines
                .filter(l => f.isEmpty || f.exists(l.toLowerCase().contains))
                .map(p(_))
            }
        )
      )
    )

  val app =
    div(
      fontFamily := "'Wotfard',Futura,-apple-system,sans-serif",
      margin     := "0px",
      padding    := "0px",
      height     := "100vh",
      div(
        display.flex,
        justifyContent.spaceBetween,
        div(
          h1(marginTop := "0px", "Langoustine tracer"),
          p(
            "welcome to the future of LSP tooling"
          )
        ),
        child.maybe <-- Signal.fromFuture(Api.summary).map {
          _.map { summary =>
            val cmd = summary.serverCommand.mkString(" ")
            dom.document.title = s"Tracer: $cmd"
            div(
              marginLeft := "15px",
              p(b("In folder: "), summary.workingFolder),
              p(b("LSP command: "), cmd)
            )
          }
        }
      ),
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
      render(dom.document.getElementById("appContainer"), app)
    }(unsafeWindowOwner)
end Frontend
