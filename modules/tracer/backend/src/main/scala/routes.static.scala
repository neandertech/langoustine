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

import org.http4s.*
import cats.effect.*
import org.http4s.dsl.io.*
import java.nio.file.Paths

object Static:
  def routes =
    val indexHtml = StaticFile
      .fromResource[IO](
        "assets/index.html",
        None,
        preferGzipped = true
      )
      .getOrElseF(NotFound())

    HttpRoutes.of[IO] {
      case req @ GET -> Root / "assets" / filename
          if filename.endsWith(".js") ||
            filename.endsWith(".js.map") ||
            filename.endsWith(".svg") =>
        StaticFile
          .fromResource[IO](
            Paths.get("assets", filename).toString,
            Some(req),
            preferGzipped = true
          )
          .getOrElseF(NotFound())
      case req @ GET -> Root        => indexHtml
      case req if req.method == GET => indexHtml
    }
  end routes
end Static
