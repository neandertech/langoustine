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

import scala.util.Try.apply
import scala.util.Try
import scala.util.Success
import upickle.default.{Reader, Writer}
import cats.syntax.all.*
import cats.MonadThrow

import jsonrpclib.*

import requests.{LSPRequest, LSPNotification}

private[lsp] case class ImmutableLSPBuilder[F[_]: Monadic] private (
    endpoints: List[Endpoint[F]],
    logger: scribe.Logger,
    notifyDelegate: Communicate.Delegate[F]
) extends LSPBuilder[F]:

  override def handleNotification[X <: LSPNotification](t: X)(
      f: Invocation[t.In, F] => F[Unit]
  ) = copy(
    endpoints = endpoints :+ jsonrpcIntegration.handlerToNotification(t) { in =>
      f(Invocation.Impl(in, notifyDelegate))
    }
  )

  override def handleRequest[X <: LSPRequest](t: X)(
      f: Invocation[t.In, F] => F[t.Out]
  ) = copy(
    endpoints = endpoints :+ jsonrpcIntegration.handlerToEndpoint(t) { in =>
      f(Invocation.Impl(in, notifyDelegate))
    }
  )

  override def build(notifications: Communicate[F]) =
    notifyDelegate.unsafeRedirect(notifications)
    endpoints

end ImmutableLSPBuilder

private[lsp] object ImmutableLSPBuilder:
  def create[F[_]: Monadic]: LSPBuilder[F] =
    new ImmutableLSPBuilder[F](
      Nil,
      scribe.Logger("LSP"),
      new Communicate.Delegate[F](Communicate.drop[F])
    )

  def create[F[_]: Monadic](log: scribe.Logger): LSPBuilder[F] =
    new ImmutableLSPBuilder[F](
      Nil,
      log,
      new Communicate.Delegate[F](Communicate.drop[F])
    )
end ImmutableLSPBuilder
