package langoustine.tracer

import com.raquo.laminar.api.L.*
import com.github.plokhotnyuk.jsoniter_scala.core.*

def timeline(
    messagesState: Var[Vector[Message]],
    commandFilter: Var[Option[String]],
    showing: Var[Option[Message]]
) =
  import Message.*

  import scala.util.chaining.*

  val filteredMessages: Signal[Vector[Message]] =
    messagesState.signal.combineWithFn(commandFilter.signal) {
      (messages, filter) =>
        messages
          .filter(m =>
            filter.isEmpty || m.methodName.exists(
              _.toLowerCase.contains(filter.getOrElse(""))
            )
          )
    }

  val splitStream =
    filteredMessages
      .split(e => uniqueId(e))(renderMessage(showing))
      .debugSpyEvents(els => println(els.length))

  div(
    display.flex,
    flexDirection.column,
    overflow.auto,
    div(
      Styles.timeline.clientServerHeader,
      div(h2("client", marginTop := "0px"), width                  := "100%"),
      div(h2("server", marginTop := "0px"), textAlign.right, width := "100%")
    ),
    input(
      Styles.filterBox,
      onInput.mapToValue.map(s =>
        Option.when(s.nonEmpty)(s.trim.toLowerCase)
      ) --> commandFilter.writer
    ),
    children <-- splitStream
  )
end timeline
