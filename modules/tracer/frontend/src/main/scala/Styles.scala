package langoustine.tracer

import com.raquo.laminar.api.L.*

object Styles:
  val filterBox = Seq(
    margin       := "auto",
    padding      := "5px",
    fontSize     := "1.3rem",
    placeholder  := "filter...",
    borderRadius := "2px",
    border       := "2px solid lightgrey",
    position     := "sticky",
    top          := "0"
  )

  val jsonViewer = Seq(
    width           := "70%",
    borderRadius    := "5px",
    backgroundColor := "#0d1117",
    color.white,
    fontSize := "1.3rem",
    padding  := "10px",
    position := "sticky",
    top      := "0"
  )

  object timeline:
    val requestButton = Seq(
      padding         := "8px",
      backgroundColor := "lightgreen",
      fontSize        := "1.3rem",
      border          := "1px solid darkgrey"
    )

    val notificationButton =
      Seq(
        padding         := "8px",
        backgroundColor := "yellow",
        fontSize        := "1.3rem",
        border          := "1px solid darkgrey"
      )

    val row = Seq(borderBottom := "1px dotted lightgrey", width := "100%")
  end timeline
end Styles
