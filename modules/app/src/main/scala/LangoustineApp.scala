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

package langoustine.lsp.app

import scala.concurrent.Future

import _root_.fs2 as FS2
import cats.effect.ExitCode
import cats.effect.IO
import cats.effect.IOApp
import cats.effect.kernel.Resource
import cats.effect.std.Dispatcher
import cats.syntax.all.*
import jsonrpclib.Channel
import jsonrpclib.Endpoint
import jsonrpclib.Endpoint.NotificationEndpoint
import jsonrpclib.Endpoint.RequestResponseEndpoint
import jsonrpclib.fs2.*
import langoustine.lsp.Communicate
import langoustine.lsp.LSPBuilder

trait LangoustineApp extends IOApp with LangoustineApp.Config:
  def server(args: List[String]): Resource[IO, LSPBuilder[IO]]

  override def run(args: List[String]): IO[ExitCode] =
    FS2.Stream
      .resource(server(args))
      .flatMap { builder =>
        LangoustineApp.stream(lspBufferSize, builder, in, out)
      }
      .compile
      .drain
      .as(ExitCode.Success)
end LangoustineApp

object LangoustineApp:
  opaque type Shutdown = IO[Unit]
  object Shutdown:
    extension (s: Shutdown) inline def initiate: IO[Unit] = s

  opaque type LSPServerBuilder[F[_]] =
    (Channel[F], Shutdown) => Resource[F, Unit]
  object LSPServerBuilder:
    inline def apply[F[_]](
        fn: (Channel[F], Shutdown) => Resource[F, Unit]
    ): LSPServerBuilder[F] = fn

  trait Config extends LangoustineAppPlatform:
    def lspBufferSize: Int                           = 2048
    def out: FS2.Pipe[cats.effect.IO, Byte, Nothing] = FS2.io.stdout[IO]

  trait FromFuture extends IOApp with Config:
    def server(args: List[String]): Future[LSPBuilder[Future]]

    private def bindFutureServer(
        builder: LSPBuilder[Future],
        to: Channel[IO],
        shutdown: Shutdown
    ): Resource[cats.effect.IO, Unit] =
      Dispatcher[IO].evalMap { disp =>
        val comms       = Communicate.channel(to, shutdown)
        val futureComms = DispatcherCommunicate(disp, comms)
        val endpoints   = builder.build(futureComms)

        (endpoints)
          .map {
            _.mapK(
              new jsonrpclib.PolyFunction[Future, IO]:
                def apply[A0](fa: => Future[A0]): IO[A0] =
                  IO.fromFuture(IO(fa))
            )
          }
          .traverse(ep => to.mountEndpoint(ep))
      }.void

    override def run(args: List[String]): IO[ExitCode] =
      fs2.Stream
        .resource(
          Resource
            .eval(IO.fromFuture(IO(server(args))))
            .map { builder => (channel: Channel[IO], shutdown: IO[Unit]) =>
              bindFutureServer(builder, channel, shutdown)
            }
        )
        .flatMap { chFun =>
          LangoustineApp.stream(lspBufferSize, chFun, in, out)
        }
        .compile
        .drain
        .as(ExitCode.Success)
  end FromFuture

  object FromFuture:
    trait Simple extends FromFuture:
      def server: Future[LSPBuilder[Future]]
      def server(args: List[String]): Future[LSPBuilder[Future]] = server

  trait Simple extends LangoustineApp:
    def server: IO[LSPBuilder[IO]]

    override def server(
        args: List[String]
    ): Resource[cats.effect.IO, LSPBuilder[cats.effect.IO]] =
      Resource.eval(server)

  private[app] def stream(
      bufferSize: Int,
      builder: LSPBuilder[IO] | LSPServerBuilder[IO],
      in: FS2.Stream[IO, Byte],
      out: FS2.Pipe[IO, Byte, Nothing]
  ): FS2.Stream[cats.effect.IO, Unit] =
    fs2.Stream
      .eval(IO.deferred[Boolean])
      .flatMap { latch =>
        FS2Channel
          .stream[IO](bufferSize, None)
          .flatMap { channel =>
            (builder: @unchecked) match
              case l: LSPBuilder[IO] =>
                FS2.Stream.resource(
                  Resource.eval(l.bind(channel, latch.complete(true).void))
                )
              case other: LSPServerBuilder[IO] =>
                FS2.Stream
                  .resource(other(channel, latch.complete(true).void))
                  .as(channel)
          }
          .flatMap(channel =>
            fs2.Stream
              .eval(latch.get)
              .concurrently(
                in
                  .through(lsp.decodeMessages)
                  .evalMap(IO.fromEither)
                  .through(channel.input)
              )
              .concurrently(
                channel.output
                  .through(lsp.encodeMessages)
                  .through(out)
              )
          )
      }
      .void
end LangoustineApp
