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

import cats.effect.IO
import cats.effect.std.Dispatcher
import langoustine.lsp.*
import langoustine.lsp.all.*

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
