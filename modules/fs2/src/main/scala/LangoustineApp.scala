package langoustine
package lsp
package fs2

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
      .guarantee(IO.consoleForIO.errorln("Terminating server"))
      .as(ExitCode.Success)
end LangoustineApp

def bind(builder: LSPBuilder[Future], to: Channel[IO]) =
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

private class DispatcherCommunicate(
    disp: Dispatcher[IO],
    target: Communicate[IO]
) extends Communicate[Future]:
  override def notification[X <: LSPNotification](
      notif: X,
      in: notif.In
  ): Future[Unit] = disp.unsafeToFuture(target.notification(notif, in))
  override def request[X <: LSPRequest](req: X, in: req.In): Future[req.Out] =
    disp.unsafeToFuture(target.request(req, in))
end DispatcherCommunicate

object LangoustineApp:
  trait Config extends LangoustineAppPlatform:
    def lspBufferSize: Int                           = 2048
    def out: FS2.Pipe[cats.effect.IO, Byte, Nothing] = FS2.io.stdout[IO]

  trait FromFuture extends IOApp with Config:
    def server(args: List[String]): Future[LSPBuilder[Future]]

    override def run(args: List[String]): IO[ExitCode] =
      FS2.Stream
        .resource(
          Resource
            .eval(IO.fromFuture(IO(server(args))))
            .map { builder => (channel: Channel[IO]) =>
              bind(builder, channel)
            }
        )
        .flatMap { chFun =>
          LangoustineApp.create(lspBufferSize, chFun, in, out)
        }
        .compile
        .drain
        .guarantee(IO.consoleForIO.errorln("Terminating server"))
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

  private[fs2] def create(
      bufferSize: Int,
      builder: LSPBuilder[IO] | (Channel[IO] => Resource[IO, Unit]),
      in: FS2.Stream[IO, Byte],
      out: FS2.Pipe[IO, Byte, Nothing]
  ): FS2.Stream[cats.effect.IO, Nothing] =
    FS2Channel[IO](bufferSize, None)
      .flatMap { channel =>
        builder match
          case l: LSPBuilder[IO] =>
            FS2.Stream.resource(Resource.eval(l.bind(channel)))
          case other: (Channel[IO] => Resource[IO, Unit]) =>
            FS2.Stream.resource(other(channel)).as(channel)
      }
      .flatMap(channel =>
        FS2.Stream
          .eval(IO.never) // running the server forever
          .concurrently(
            in
              .through(lsp.decodePayloads)
              .evalTap(IO.consoleForIO.errorln(_))
              .through(channel.input)
          )
          .concurrently(
            channel.output
              .through(lsp.encodePayloads)
              .evalTap(IO.consoleForIO.errorln(_))
              .through(out)
          )
      )
end LangoustineApp
