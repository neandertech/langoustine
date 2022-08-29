package langoustine.tracer

import com.raquo.laminar.api.L.*
import com.github.plokhotnyuk.jsoniter_scala.core.*

def switcher(page: Var[Page]) =
  inline def thing(name: String, set: Page) =
    div(
      child <-- page.signal.map {
        case `set` => div(Styles.pageSwitcher.focused, name)
        case other =>
          div(
            Styles.pageSwitcher.unfocused,
            a(
              Styles.pageSwitcher.link,
              href := "#",
              onClick.preventDefault.mapTo(set) --> page.writer,
              name
            )
          )
      }
    )

  div(
    display.flex,
    alignContent.flexEnd,
    columnGap := "10px",
    flexGrow  := 0,
    thing("Interactions", Page.Commands),
    thing("Logs", Page.Logs),
    thing("Server summary", Page.Summary)
  )
end switcher
