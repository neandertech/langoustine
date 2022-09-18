package langoustine.lsp.app

import jsonrpclib.fs2.*
import _root_.fs2 as FS2
import cats.effect.std.Dispatcher
import scala.concurrent.Future
import langoustine.lsp.*
import langoustine.lsp.requests.LSPRequest
import langoustine.lsp.requests.LSPNotification
import cats.effect.IO

private[app] class DispatcherCommunicate(
    disp: Dispatcher[IO],
    target: Communicate[IO]
) extends Communicate[Future]:
  override def notification[X <: LSPNotification](
      notif: X,
      in: notif.In
  ): Future[Unit] = disp.unsafeToFuture(target.notification(notif, in))
  override def request[X <: LSPRequest](req: X, in: req.In): Future[req.Out] =
    disp.unsafeToFuture(target.request(req, in))

  override def shutdown: Future[Unit] = disp.unsafeToFuture(target.shutdown)
end DispatcherCommunicate
