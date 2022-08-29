package langoustine.tracer

import com.raquo.laminar.api.L.*
import com.github.plokhotnyuk.jsoniter_scala.core.*
import scala.scalajs.js.Date

def logsTracer(logs: Var[Vector[String]], logFilter: Var[Option[String]]) =
  import Message.*
  div(
    Styles.logTracer.container,
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
end logsTracer
