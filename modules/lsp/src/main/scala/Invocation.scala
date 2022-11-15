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

package langoustine.lsp

import upickle.default.{Reader, Writer}
import cats.MonadThrow
import jsonrpclib.Endpoint
import requests.{LSPRequest, LSPNotification}
import jsonrpclib.Monadic
import jsonrpclib.Codec
import jsonrpclib.Payload
import jsonrpclib.Channel

trait Invocation[P, F[_]]:
  def params: P
  def toClient: Communicate[F]

object Invocation:
  private[lsp] case class Impl[P, F[_]](params: P, toClient: Communicate[F])
      extends Invocation[P, F]
