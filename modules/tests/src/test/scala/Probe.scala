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

package langoustine.testkit

import scala.concurrent.duration.*

import _root_.fs2.*
import _root_.fs2.concurrent.SignallingRef
import cats.effect.Deferred
import cats.effect.IO
import cats.effect.Ref
import cats.syntax.all.*
import jsonrpclib.InputMessage.NotificationMessage
import jsonrpclib.OutputMessage.ResponseMessage
import jsonrpclib.fs2.FS2Channel
import jsonrpclib.fs2.catsMonadic
import jsonrpclib.{fs2 as _, *}
import langoustine.lsp.*
import langoustine.lsp.all.*
import _root_.io.circe.Json
import langoustine.lsp.app.LSPCancelRequest

class Probe(
    messagesFromServer: SignallingRef[IO, Vector[Message]],
    send: Message => IO[Unit],
    pendingRequests: Ref[IO, Map[CallId, Deferred[IO, Message]]],
    log: weaver.Log[IO],
    timeouts: Timeouts
):

  def cancel(callId: CallId) =
    val msg = InputMessage.NotificationMessage(
      "$/cancelRequest",
      Some(Payload(Json.obj("id" -> CallId.codec(callId))))
    )
    send(msg)

  def notification[T <: LSPNotification](t: T, params: t.In): IO[Unit] =
    val msg = InputMessage.NotificationMessage(
      method = t.notificationMethod,
      params = Some(Payload(t.inputToJson.apply(params)))
    )

    send(msg)

  def waitForNotifications[T <: LSPNotification](
      t: T,
      count: Int
  ): IO[Vector[t.In]] =

    IO.ref(Vector.empty[t.In])
      .flatMap { ref =>
        def collect: PartialFunction[Message, IO[t.In]] =
          case InputMessage.NotificationMessage(method, payload)
              if method == t.notificationMethod =>
            IO.fromEither(
              t.inputFromJson.decodeJson(
                payload.map(_.data).getOrElse(_root_.io.circe.Json.Null)
              )
            )

        for
          initial <- messagesFromServer.get

          matching <- initial.traverseCollect(collect)

          _ <- log.info(
            s"Waiting for ${count} notifications with [${t.notificationMethod}]: collected ${matching.length} on startup"
          )

          _ <- (fs2.Stream.emit(matching.length) ++ messagesFromServer.discrete
            .evalMap { vec =>
              vec.traverseCollect(collect).flatMap { selected =>
                ref.set(selected).as(selected.length)
              }
            }
            .takeWhile(_ != count)).compile.drain

          all <- ref.get
        yield all
        end for
      }
      .timeout(timeouts.waitForNotifications)
  end waitForNotifications

  def requestAndForget[T <: LSPRequest](
      callId: CallId,
      t: T,
      params: t.In
  ): IO[Unit] =
    val msg = InputMessage.RequestMessage(
      method = t.requestMethod,
      callId = callId,
      params = Some(Payload(t.inputToJson.apply(params)))
    )

    send(msg)
  end requestAndForget

  def request[T <: LSPRequest](callId: CallId, t: T, params: t.In): IO[t.Out] =
    val msg = InputMessage.RequestMessage(
      method = t.requestMethod,
      callId = callId,
      params = Some(Payload(t.inputToJson.apply(params)))
    )

    send(msg) *> pendingRequests.get.flatMap(
      _.get(callId)
        .map(_.get.timeout(timeouts.waitForResponse).flatMap { msg =>
          msg match
            case ResponseMessage(callId, data) =>
              IO.fromEither(t.outputFromJson.decodeJson(data.data))
            case other =>
              IO.raiseError(
                RuntimeException(
                  s"unknown response type to call id $callId: $other"
                )
              )
        })
        .getOrElse(
          IO.raiseError(
            RuntimeException(s"no pending request for call id $callId")
          )
        )
    )
  end request
end Probe

case class Timeouts(
    waitForNotifications: Duration = 5.seconds,
    waitForResponse: Duration = 5.seconds
)

object Timeouts:
  def fromEnv(env: Map[String, String]) =
    if env.contains("CI") then Timeouts(10.seconds, 10.seconds)
    else Timeouts()

object Probe:

  def create(
      builder: LSPBuilder[IO],
      log: weaver.Log[IO],
      timeouts: Timeouts = Timeouts.fromEnv(sys.env)
  ) =
    for
      ch <- FS2Channel
        .resource[IO](cancelTemplate = Some(LSPCancelRequest.cancelTemplate))
        .flatMap(ch => ch.withEndpoints(builder.build(Communicate.channel(ch))))
      _     <- log.info("wut").toResource
      input <- fs2.concurrent.Channel.synchronous[IO, Message].toResource
      _     <- input.stream
        .evalTap(msg => log.info(s"Sending message $msg"))
        .through(ch.input)
        .compile
        .drain
        .background
      messagesFromServer <- SignallingRef[IO, Vector[Message]](
        Vector.empty[Message]
      ).toResource
      pendingRequests <- IO
        .ref(Map.empty[CallId, Deferred[IO, Message]])
        .toResource
      _ <- ch.output
        .evalTap(msg => log.info(s"Outputting message $msg"))
        .through(_.evalMap { msg =>
          msg.maybeCallId.traverse { cid =>
            pendingRequests.get.map(_.get(cid)).flatMap {
              case None =>
                IO.raiseError(
                  RuntimeException(s"no pending request for call id $cid")
                )
              case Some(dfr) => dfr.complete(msg)
            }
          } *> messagesFromServer.update(_ :+ msg)
        })
        .compile
        .drain
        .background
      send = (msg: Message) =>
        log.info(s"Sending message $msg") *>
          msg.maybeCallId
            .map(cid =>
              IO.deferred[Message]
                .flatMap(dfr => pendingRequests.update(_ + (cid -> dfr)))
            )
            .getOrElse(IO.unit) *>
          input
            .send(msg)
            .flatMap(
              _.fold(
                _ => IO.raiseError(RuntimeException("channel already closed")),
                _ => IO.unit
              )
            )
    yield Probe(messagesFromServer, send, pendingRequests, log, timeouts)
end Probe
