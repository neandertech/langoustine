package langoustine.lsp.app

import cats.effect.IOApp
import cats.effect.ExitCode
import cats.effect.IO
import jsonrpclib.fs2.*
import _root_.fs2 as FS2
import cats.effect.std.Dispatcher
import scala.concurrent.Future
import langoustine.lsp.requests.LSPNotification
import langoustine.lsp.requests.LSPRequest
import jsonrpclib.Channel
import jsonrpclib.Endpoint.NotificationEndpoint
import jsonrpclib.Endpoint.RequestResponseEndpoint
import jsonrpclib.Endpoint
import cats.effect.kernel.Resource
import cats.syntax.all.*
import langoustine.lsp.LSPBuilder
import langoustine.lsp.Communicate

trait LangoustineApp extends IOApp with LangoustineApp.Config:
  def server(args: List[String]): IO[LSPBuilder[IO]]

  override def run(args: List[String]): IO[ExitCode] =
    FS2.Stream
      .eval(server(args))
      .flatMap { builder =>
        LangoustineApp.create(lspBufferSize, builder, in, out)
      }
      .compile
      .drain
      .as(ExitCode.Success)
end LangoustineApp

object LangoustineApp:
  opaque type Shutdown = IO[Unit]
  object Shutdown:
    extension (s: Shutdown) def initiate: IO[Unit] = s

  trait Config extends LangoustineAppPlatform:
    def lspBufferSize: Int                           = 2048
    def out: FS2.Pipe[cats.effect.IO, Byte, Nothing] = FS2.io.stdout[IO]

  trait FromFuture extends IOApp with Config:
    def server(args: List[String]): Future[LSPBuilder[Future]]

    private def bindFutureServer(builder: LSPBuilder[Future], to: Channel[IO]) =
      Dispatcher[IO].evalMap { disp =>
        val comms       = Communicate.channel(to)
        val futureComms = DispatcherCommunicate(disp, comms)
        val endpoints   = builder.build(futureComms)

        (endpoints: @unchecked)
          .map {
            case n @ NotificationEndpoint[Future, Any](method, run, inCodec) =>
              n.copy(run = (in) => IO.fromFuture(IO(n.run(in))))
            case r @ RequestResponseEndpoint[Future, Any, Any, Any](
                  method,
                  run,
                  inCodec,
                  errCodec,
                  outCodec
                ) =>
              r.copy(run = (in) => IO.fromFuture(IO(r.run(in))))
          }
          .traverse(ep => to.mountEndpoint(ep))
      }.void

    override def run(args: List[String]): IO[ExitCode] =
      FS2.Stream
        .resource(
          Resource
            .eval(IO.fromFuture(IO(server(args))))
            .map { builder => (channel: Channel[IO]) =>
              bindFutureServer(builder, channel)
            }
        )
        .flatMap { chFun =>
          LangoustineApp.create(lspBufferSize, chFun, in, out)
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

    override def server(args: List[String]): IO[LSPBuilder[cats.effect.IO]] =
      server

  private[app] def create(
      bufferSize: Int,
      builder: LSPBuilder[IO] | (Channel[IO] => Resource[IO, Unit]),
      in: Shutdown => FS2.Stream[IO, Byte],
      out: FS2.Pipe[IO, Byte, Nothing]
  ): FS2.Stream[cats.effect.IO, Unit] =
    FS2Channel[IO](bufferSize, None)
      .flatMap { channel =>
        builder match
          case l: LSPBuilder[IO] =>
            FS2.Stream.resource(Resource.eval(l.bind(channel)))
          case other: (Channel[IO] => Resource[IO, Unit]) =>
            FS2.Stream.resource(other(channel)).as(channel)
      }
      .flatMap(channel =>
        fs2.Stream
          .eval(IO.never)
          .concurrently(
            in(IO.unit).through(lsp.decodePayloads).through(channel.input)
          )
          .concurrently(
            channel.output
              .through(lsp.encodePayloads)
              .through(out)
          )

      // fs2.Stream
      //   .eval(IO.deferred[Boolean])
      //   .flatMap { latch =>
      //     fs2.Stream
      //       .eval(latch.get)
      //       .concurrently(
      //         in(latch.complete(true).void)
      //           .through(lsp.decodePayloads)
      //           .through(channel.input)
      //       )
      //   }
      //   .concurrently(
      //     channel.output
      //       .evalTap(IO.consoleForIO.errorln)
      //       .through(lsp.encodePayloads)
      //       .through(out)
      //   )
      //   .void
      )
end LangoustineApp
