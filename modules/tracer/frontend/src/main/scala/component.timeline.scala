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

def timeline(
    messagesState: Var[Vector[LspMessage]],
    commandFilter: Var[Option[String]],
    showing: Var[Option[LspMessage]]
) =
  import LspMessage.*

  import scala.util.chaining.*

  val filteredMessages: Signal[Vector[LspMessage]] =
    messagesState.signal.combineWithFn(commandFilter.signal) {
      (messages, filter) =>
        messages
          .filter(m =>
            filter.isEmpty || m.methodName.exists(
              _.toLowerCase.contains(filter.getOrElse(""))
            )
          )
    }

  val splitStream =
    filteredMessages
      .split(e => uniqueId(e))(renderMessage(showing))

  div(
    display.flex,
    flexDirection.column,
    overflow.auto,
    div(
      Styles.timeline.clientServerHeader,
      div(h2("client", marginTop := "0px"), width                  := "100%"),
      div(h2("server", marginTop := "0px"), textAlign.right, width := "100%")
    ),
    input(
      Styles.filterBox,
      onInput.mapToValue.map(s =>
        Option.when(s.nonEmpty)(s.trim.toLowerCase)
      ) --> commandFilter.writer
    ),
    children <-- splitStream
  )
end timeline
