package langoustine.tracer

import org.scalajs.dom.*
import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.raquo.airstream.state.Var
import com.raquo.airstream.eventbus.EventBus
import scala.scalajs.js.Date

def listenToWebsockets(
    logs: Var[Vector[String]],
    bus: EventBus[Double],
    host: String
) =
  val sock = new WebSocket(s"ws://$host/api/ws/events")

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
end listenToWebsockets
