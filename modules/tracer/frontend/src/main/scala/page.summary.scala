package langoustine.tracer

import com.raquo.laminar.api.L.*
import com.github.plokhotnyuk.jsoniter_scala.core.*
import scala.scalajs.js.Date

import org.scalajs.dom

def summaryPage = div(
  Styles.summaryPage.container,
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
)
