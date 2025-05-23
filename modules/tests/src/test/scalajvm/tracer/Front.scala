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

package tests.tracer

import weaver.*
import cats.effect.*
import jsonrpclib.*
import java.util.Base64
import langoustine.tracer.RawMessage
import _root_.fs2.concurrent.Channel as Chan
import _root_.fs2.*
import cats.syntax.all.*
import langoustine.tracer.*
import com.github.plokhotnyuk.jsoniter_scala.core.*
import org.http4s.client.*
import TracerServer.{*, given}
import org.http4s.Uri
import org.http4s.client.websocket.*
import langoustine.tracer.codecs.given

import org.http4s.dsl.io.*
import org.http4s.client.dsl.io.*

case class Front(client: Client[IO], base: Uri, ws: WSClient[IO]):
  val wsBase = base.copy(scheme = Some(Uri.Scheme.unsafeFromString("ws")))

  def request(id: String) =
    client.expect[RawMessage](base.withPath(s"/api/raw/request/$id"))

  def request(callId: CallId) =
    client.expect[RawMessage](base.withPath(s"/api/raw/request/${cid(callId)}"))

  def summary =
    client.expect[Summary](base.withPath(s"/api/summary"))

  def request(callId: Option[CallId]) =
    client.expect[RawMessage](
      base.withPath(s"/api/raw/request/${cid(callId.get)}")
    )

  def response(id: String) =
    client.expect[RawMessage](base.withPath(s"/api/raw/response/$id"))

  def response(callId: CallId) =
    client.expect[RawMessage](
      base.withPath(s"/api/raw/response/${cid(callId)}")
    )

  def response(callId: Option[CallId]) =
    client.expect[RawMessage](
      base.withPath(s"/api/raw/response/${cid(callId.get)}")
    )

  def logs =
    client.expect[Vector[LogMessage]](base.withPath("/api/logs"))

  def allRaw =
    client.expect[Vector[RawMessage]](base.withPath(s"/api/raw/all"))

  def snapshot(name: Option[String]) =
    client.stream(GET(base.withPath("/api/snapshot")))

  private def cid(c: CallId) = c match
    case CallId.NumberId(n) => n.toString
    case CallId.StringId(s) => s
end Front
