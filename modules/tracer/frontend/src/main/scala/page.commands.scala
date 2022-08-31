package langoustine.tracer

import com.raquo.laminar.api.L.*
import com.github.plokhotnyuk.jsoniter_scala.core.*
import scala.scalajs.js.Date
import com.raquo.airstream.state.Var

def commandTracer(
    bus: EventBus[Double],
    commandFilter: Var[Option[String]],
    messagesState: Var[Vector[Message]],
    showing: Var[Option[Message]]
) =
  import Message.*
  div(
    Styles.commandTracer.container,
    jsonViewer(showing),
    div(
      width <-- showing.signal
        .map(_.isDefined)
        .map(if (_) then "40%" else "100%"),
      bus.events
        .debounce(500)
        .startWith(Date.now())
        .flatMap { _ =>
          EventStream
            .fromFuture(Api.all)
            .map(_.reverse)
        } --> messagesState.writer,
      timeline(messagesState, commandFilter, showing)
    )
  )
end commandTracer
