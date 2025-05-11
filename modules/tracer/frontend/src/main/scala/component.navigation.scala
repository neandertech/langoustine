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

def switcher(page: Var[Page], modalBus: EventBus[ModalCommand]) =
  inline def pageLink(name: String, set: Page) =
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

  inline def modalLink(name: String, send: ModalCommand) = div(
    div(
      Styles.pageSwitcher.modal,
      // img(
      //   src    := "/assets/download-icon.svg",
      //   width  := "20px",
      //   height := "20px"
      // ),
      a(
        Styles.pageSwitcher.link,
        onClick.preventDefault.mapTo(send) --> modalBus.writer,
        href := "#",
        name
      )
    )
  )

  div(
    Styles.pageSwitcher.container,
    pageLink("Interactions", Page.Commands),
    pageLink("Logs", Page.Logs),
    modalLink("Download snapshot", ModalCommand.DownloadSnapshot),
    modalLink("Server summary", ModalCommand.ShowSummary)
  )
end switcher
