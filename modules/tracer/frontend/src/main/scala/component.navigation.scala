/*
 * Copyright 2022 Neandertech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
