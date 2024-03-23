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

import jsonrpclib.Monadic
import jsonrpclib.Codec
import jsonrpclib.Payload
import jsonrpclib.Channel
import requests.*

trait LSPBuilder[F[_]]:
  def handleRequest[X <: LSPRequest](t: X)(
      f: Invocation[t.In, F] => F[t.Out]
  ): LSPBuilder[F]

  def handleNotification[X <: LSPNotification](t: X)(
      f: Invocation[t.In, F] => F[Unit]
  ): LSPBuilder[F]

  @deprecated(
    "This method is deprecated in favour of one using Invocation",
    "0.1.0"
  )
  def handleRequest[X <: LSPRequest](t: X)(
      f: (t.In, Communicate[F]) => F[t.Out]
  ): LSPBuilder[F] = handleRequest(t)(invok => f(invok.params, invok.toClient))

  @deprecated(
    "This method is deprecated in favour of one using Invocation",
    "0.1.0"
  )
  def handleNotification[X <: LSPNotification](t: X)(
      f: (t.In, Communicate[F]) => F[Unit]
  ): LSPBuilder[F] =
    handleNotification(t)(invok => f(invok.params, invok.toClient))

  def build(comm: Communicate[F]): List[Endpoint[F]]

  def bind[T <: Channel[F]](channel: T, communicate: Communicate[F])(using
      Monadic[F]
  ): F[T] =
    val Fm        = summon[Monadic[F]]
    val endpoints = build(communicate)

    endpoints match
      case Nil => Fm.doPure(channel)
      case h :: t =>
        var curr = channel.mountEndpoint(h)
        t.foreach { e =>
          curr = Fm.doFlatMap(curr)(_ => channel.mountEndpoint(e))
        }

        Fm.doFlatMap(curr)(_ => Fm.doPure(channel))
    end match
  end bind

  def bind[T <: Channel[F]](channel: T, shutdown: F[Unit])(using
      Monadic[F]
  ): F[T] =
    bind(channel, Communicate.channel(channel, shutdown))
  end bind

end LSPBuilder

object LSPBuilder:
  def create[F[_]: Monadic]: LSPBuilder[F] =
    ImmutableLSPBuilder.create[F]

  def create[F[_]: Monadic](log: scribe.Logger): LSPBuilder[F] =
    ImmutableLSPBuilder.create[F](log)
