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

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.given
import scala.scalajs.js
import scala.scalajs.js.Thenable.Implicits.*
import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import com.raquo.laminar.api.L.*
import langoustine.tracer.Message
import org.scalajs.dom
import org.scalajs.dom.Event
import org.scalajs.dom.Fetch.fetch
import org.scalajs.dom.MessageEvent
import org.scalajs.dom.WebSocket
import scala.scalajs.js.Date
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.JSGlobal
import com.raquo.airstream.core.Signal

@js.native
@JSGlobal
object hljs extends js.Object:
  def highlightAll(): Unit = js.native

enum Page:
  case Logs, Commands, Summary

def cid(c: MessageId) = c match
  case MessageId.NumberId(n) => n.toString
  case MessageId.StringId(s) => s

def uniqueId(m: Message) = m match
  case _: Message.Request      => "request-" + cid(m.id)
  case _: Message.Response     => "response-" + cid(m.id)
  case _: Message.Notification => "notification-" + cid(m.id)

object Frontend:
  val page                                = Var(Page.Commands)
  val logs                                = Var(Vector.empty[String])
  val bus                                 = new EventBus[Double]
  val commandFilter: Var[Option[String]]  = Var(Option.empty[String])
  val logFilter: Var[Option[String]]      = Var(Option.empty[String])
  val messagesState: Var[Vector[Message]] = Var(Vector.empty[Message])
  val showing: Var[Option[Message]]       = Var(Option.empty[Message])
  val mode                                = Var(JsonMode.Details)
  val downloadBus                         = EventBus[DownloadEvent]()

  val app =
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
          switcher(page, downloadBus)
        ),
        child <-- page.signal.map {
          case Page.Commands =>
            commandTracer(bus, commandFilter, messagesState, showing, mode)
          case Page.Logs =>
            logsTracer(logs, logFilter)
          case Page.Summary =>
            summaryPage
        }
      )
    )

  def main(args: Array[String]): Unit =
    documentEvents.onDomContentLoaded.foreach { _ =>
      listenToWebsockets(logs, bus, dom.window.location.host)

      render(dom.document.getElementById("appContainer"), app)
    }(unsafeWindowOwner)
end Frontend
