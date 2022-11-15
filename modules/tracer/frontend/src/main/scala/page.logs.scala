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
import scala.scalajs.js.Date

def logsTracer(logs: Var[Vector[String]], logFilter: Var[Option[String]]) =
  div(
    Styles.logTracer.container,
    input(
      Styles.filterBox,
      onInput.mapToValue.map(s =>
        Option.when(s.nonEmpty)(s.trim.toLowerCase)
      ) --> logFilter.writer
    ),
    pre(
      code(
        children <--
          logs.signal
            .combineWithFn(logFilter.signal) { case (lines, f) =>
              lines
                .filter(l => f.isEmpty || f.exists(l.toLowerCase().contains))
            }
            .map(_.map(p(_)))
      )
    )
  )
end logsTracer
