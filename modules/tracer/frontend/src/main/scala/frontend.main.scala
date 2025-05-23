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

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

import com.raquo.laminar.api.L.*
import jsonrpclib.CallId
import org.scalajs.dom

@js.native
@JSGlobal
object hljs extends js.Object:
  def highlightAll(): Unit = js.native

def cid(c: CallId) = c match
  case CallId.NumberId(n) => n.toString
  case CallId.StringId(s) => s

def uniqueId(m: LspMessage) = m match
  case _: LspMessage.Request      => "request-" + cid(m.id)
  case _: LspMessage.Response     => "response-" + cid(m.id)
  case _: LspMessage.Notification => "notification-" + cid(m.id)

object Frontend:
  val page                                   = Var(Page.Commands)
  val logs                                   = Var(Vector.empty[LogMessage])
  val bus                                    = new EventBus[Double]
  val commandFilter: Var[Option[String]]     = Var(Option.empty[String])
  val logFilter: Var[Option[String]]         = Var(Option.empty[String])
  val messagesState: Var[Vector[LspMessage]] = Var(Vector.empty[LspMessage])
  val showing: Var[Option[LspMessage]]       = Var(Option.empty[LspMessage])
  val mode                                   = Var(JsonMode.Details)
  val modalBus                               = EventBus[ModalCommand]()

  val app =
    div(
      modalWindow(modalBus),
      div(
        Styles.staticContainer,
        div(
          Styles.dynamicContainer,
          div(
            display.flex,
            justifyContent.spaceBetween,
            div(
              h1(marginTop := "0px", "Langoustine tracer")
            ),
            switcher(page, modalBus)
          ),
          child <-- page.signal.map {
            case Page.Commands =>
              commandTracer(
                bus,
                commandFilter,
                messagesState,
                showing,
                mode,
                modalBus
              )
            case Page.Logs =>
              logsTracer(logs, logFilter)
          }
        )
      )
    )

  def main(args: Array[String]): Unit =
    documentEvents.onDomContentLoaded.foreach { _ =>
      listenToWebsockets(logs, bus, dom.window.location.host)

      render(dom.document.getElementById("appContainer"), app)
    }(unsafeWindowOwner)
end Frontend
