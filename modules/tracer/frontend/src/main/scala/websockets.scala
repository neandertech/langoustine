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

import scala.scalajs.js.Date

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.raquo.airstream.eventbus.EventBus
import com.raquo.airstream.state.Var
import org.scalajs.dom.*

def listenToWebsockets(
    logs: Var[Vector[LogMessage]],
    bus: EventBus[Double],
    host: String
) =
  val sock = new WebSocket(s"ws://$host/api/ws/events")

  sock.addEventListener[Event](
    "message",
    (event: Event) =>
      event match
        case event: MessageEvent =>
          val data = event.data.toString
          readFromStringReentrant[TracerEvent](data) match
            case TracerEvent.Update => bus.emit(Date.now())
            case TracerEvent.LogLines(l) =>
              logs.update(v => v.drop(v.length + l.length - 1000) ++ l)
  )
end listenToWebsockets
