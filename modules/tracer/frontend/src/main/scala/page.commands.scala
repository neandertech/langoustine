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

import com.raquo.airstream.state.Var
import com.raquo.laminar.api.L.*

def commandTracer(
    bus: EventBus[Double],
    commandFilter: Var[Option[String]],
    messagesState: Var[Vector[LspMessage]],
    showing: Var[Option[LspMessage]],
    mode: Var[JsonMode],
    modalBus: EventBus[ModalCommand]
) =
  div(
    Styles.commandTracer.container,
    jsonViewer(showing, mode, modalBus),
    div(
      width <-- showing.signal
        .map(_.isDefined)
        .map(if _ then "40%" else "100%"),
      bus.events
        .debounce(500)
        .startWith(Date.now())
        .flatMap { _ =>
          EventStream
            .fromFuture(Api.all)
            .map(_.reverse)
        } --> messagesState.writer,
      timeline(messagesState, commandFilter, showing)
    )
  )
end commandTracer
