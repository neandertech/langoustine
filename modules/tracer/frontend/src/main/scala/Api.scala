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
import scala.scalajs.js
import scala.scalajs.js.Thenable.Implicits.*

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import org.scalajs.dom
import org.scalajs.dom.Fetch.fetch

object Api:

  given JsonValueCodec[Vector[LspMessage]]     = JsonCodecMaker.make
  given rw: JsonValueCodec[Vector[RawMessage]] = JsonCodecMaker.make

  def all: Future[Vector[LspMessage]] =
    fetch("/api/all")
      .flatMap(_.text())
      .map(str => readFromStringReentrant[Vector[LspMessage]](str))

  def summary: Future[Summary] =
    fetch("/api/summary")
      .flatMap(_.text())
      .map(str => readFromStringReentrant[Summary](str))

  def rawAll: Future[Vector[RawMessage]] =
    fetch("/api/raw/all")
      .flatMap(_.text())
      .map(str => readFromStringReentrant[Vector[RawMessage]](str))

  def request(id: String): Future[Option[RawMessage]] =
    fetch(s"/api/raw/request/$id")
      .flatMap(resp =>
        if resp.status == 200 then
          resp
            .text()
            .map(readFromStringReentrant[RawMessage](_))
            .map(Option.apply)
        else Future.successful(None)
      )
  def response(id: String): Future[Option[RawMessage]] =
    fetch(s"/api/raw/response/$id")
      .flatMap(resp =>
        if resp.status == 200 then
          resp
            .text()
            .map(readFromStringReentrant[RawMessage](_))
            .map(Option.apply)
        else Future.successful(None)
      )

  def notification(id: String): Future[Option[RawMessage]] =
    fetch(s"/api/raw/notification/$id")
      .flatMap(resp =>
        if resp.status == 200 then
          resp
            .text()
            .map(readFromStringReentrant[RawMessage](_))
            .map(Option.apply)
        else Future.successful(None)
      )
end Api
