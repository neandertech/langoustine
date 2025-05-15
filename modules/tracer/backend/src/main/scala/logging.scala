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

import scribe.Level

object Logging:
  private lazy val io =
    if sys.env.contains("LANGOUSTINE_TRACER_DEBUG") then
      scribe.Logger.root
        .clearHandlers()
        .withHandler(
          writer = scribe.writer.SystemErrWriter,
          outputFormat = scribe.output.format.ASCIIOutputFormat
        )
        .withMinimumLevel(Level.Debug)
        .replace()
    else
      scribe.Logger.root
        .clearHandlers()
        .withHandler(
          writer = scribe.writer.SystemErrWriter,
          outputFormat = scribe.output.format.ASCIIOutputFormat
        )
        .replace()
    end if

    scribe.cats.io
  end io

  export io.{debug, info, warn, error}

end Logging
