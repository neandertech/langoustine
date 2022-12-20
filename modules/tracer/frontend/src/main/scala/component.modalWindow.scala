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

enum ModalCommand:
  case Close, DownloadSnapshot
  case ShowCode(code: String)

def modalWindow(
    bus: EventBus[ModalCommand]
) =
  val closeOnClick =
    onClick.mapTo(ModalCommand.Close) --> bus.writer

  div(
    Styles.modal.container,
    closeOnClick,
    display <-- bus.events.startWith(ModalCommand.Close).map {
      case ModalCommand.Close => "none"
      case _                  => "block"
    },
    documentEvents.onKeyUp
      .filter(_.keyCode == 27)
      .mapTo(ModalCommand.Close) --> bus.writer,
    div(
      Styles.modal.content,
      onClick.stopPropagation --> { _ => },
      a(
        closeOnClick,
        href := "#",
        b("❌ Close")
      ),
      child <-- bus.events.collect {
        case ModalCommand.ShowCode(codeString) =>
          pre(
            code(
              className := "language-plaintext",
              codeString,
              onMountCallback(ctx => hljs.highlightAll())
            )
          )
        case ModalCommand.DownloadSnapshot =>
          val nm = Var("langoustine-tracer-snapshot")
          div(
            h2("Download snapshot"),
            p(
              b("Filename: "),
              input(
                tpe         := "text",
                placeholder := "langoustine-tracer-snapshot",
                widthAttr   := 250,
                onInput.mapToValue --> nm.writer,
                value <-- nm.signal
              )
            ),
            a(
              href := "#",
              Styles.modal.btn,
              onClick.preventDefault.mapTo(nm.now()) --> { v =>
                org.scalajs.dom.window.open(s"/api/snapshot?name=$v", "_blank")
              },
              "Download"
            )
          )
      }
    )
  )
end modalWindow
