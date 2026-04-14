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

import io.circe.Decoder
import io.circe.scalajs.decodeJs
import org.scalajs.dom
import org.scalajs.dom.Fetch.fetch

object Api:

  def read[T: Decoder](s: js.Any) = decodeJs[T](s).fold(throw _, identity)

  def all: Future[Vector[LspMessage]] =
    fetch("/api/all")
      .flatMap(_.json())
      .map(read[Vector[LspMessage]])

  def summary: Future[Summary] =
    fetch("/api/summary")
      .flatMap(_.json())
      .map(read[Summary])

  def rawAll: Future[Vector[RawMessage]] =
    fetch("/api/raw/all")
      .flatMap(_.json())
      .map(read[Vector[RawMessage]])

  def request(id: String): Future[Option[RawMessage]] =
    fetch(s"/api/raw/request/$id")
      .flatMap(resp =>
        if resp.status == 200 then
          resp
            .json()
            .map(read[RawMessage])
            .map(Option.apply)
        else Future.successful(None)
      )
  def response(id: String): Future[Option[RawMessage]] =
    fetch(s"/api/raw/response/$id")
      .flatMap(resp =>
        if resp.status == 200 then
          resp
            .json()
            .map(read[RawMessage])
            .map(Option.apply)
        else Future.successful(None)
      )

  def notification(id: String): Future[Option[RawMessage]] =
    fetch(s"/api/raw/notification/$id")
      .flatMap(resp =>
        if resp.status == 200 then
          resp
            .json()
            .map(read[RawMessage])
            .map(Option.apply)
        else Future.successful(None)
      )
end Api
