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

package langoustine.tracer

import cats.effect.*
import cats.effect.std.*
import fs2.concurrent.*
import jsonrpclib.CallId

import jsonrpclib.Payload
import jsonrpclib.Codec
import jsonrpclib.InputMessage.NotificationMessage
import jsonrpclib.InputMessage.RequestMessage
import jsonrpclib.Message
import jsonrpclib.OutputMessage.ErrorMessage
import jsonrpclib.OutputMessage.ResponseMessage
import jsonrpclib.Payload
import langoustine.lsp.enumerations
import langoustine.lsp.requests.window

case class State(
    ch: Channel[IO, String],
    messages: SignallingRef[IO, Vector[ReceivedMessage]],
    logBuf: SignallingRef[IO, Vector[LogMessage]],
    responseIdMapping: Ref[IO, Map[CallId, String]],
    random: UUIDGen[IO]
):
  private def decodeRaw(p: Message): RawMessage =
    p match
      case RequestMessage(method, callId, params) =>
        RawMessage.create(
          method = Some(method),
          params = params,
          id = Some(callId)
        )
      case NotificationMessage(method, params) =>
        RawMessage.create(method = Some(method), params = params)
      case ErrorMessage(callId, payload) =>
        RawMessage.create(id = Some(callId), error = Some(payload))
      case ResponseMessage(callId, data) =>
        RawMessage.create(result = Some(data), id = Some(callId))

      // IO(Codec.decode[RawMessage](Some(p))).flatMap {
      //   case Left(err) =>
      //     ch.send(s"[Tracer] hard failed to decode $p, error: $err").as(None)
      //   case Right(v) => Received.capture(v).map(Option.apply)
      // }

  def hydrateFrom(
      stream: fs2.Stream[IO, Message],
      direction: Direction
  ): fs2.Stream[cats.effect.IO, Received[RawMessage]] =
    stream
      .map(decodeRaw)
      .evalMap(Received.capture)
      .evalTap { rawMessage =>
        random.randomUUID
          .map(u => CallId.StringId("generated-" + u.toString))
          .flatMap { generatedId =>
            val receivedRawMessage = rawMessage.copy(value =
              rawMessage.value
                .copy(id = rawMessage.value.id.orElse(Some(generatedId)))
            )

            val msg = LspMessage
              .from(receivedRawMessage.value, direction, generatedId)

            LspMessage
              .from(rawMessage.value, direction, generatedId)
              .map { lspMessage =>

                val withUpdatedMapping = lspMessage match

                  case LspMessage.Request(method, id, _, _) =>
                    responseIdMapping
                      .update(_.updated(id, method))
                      .as(lspMessage)

                  case LspMessage.Response(id, _, direction) =>
                    messages.update { received =>
                      received.collect {
                        case ReceivedMessage(
                              ts,
                              raw,
                              req: LspMessage.Request
                            ) if req.id == id =>
                          ReceivedMessage(ts, raw, req.copy(responded = true))
                        case other => other
                      }
                    } *>
                      responseIdMapping.get
                        .map(_.get(id))
                        .map(LspMessage.Response(id, _, direction))

                  case notif: LspMessage.Notification =>
                    import langoustine.lsp.jsonrpcIntegration.given

                    val special =
                      if notif.method == "window/logMessage" then
                        IO
                          .fromEither(
                            Codec.decode[window.logMessage.In](
                              rawMessage.value.params
                            )
                          )
                          .flatMap(Received.capture(_))
                          .flatMap { case Received(ts, lmp) =>
                            if lmp.`type` == enumerations.MessageType.Log then
                              logBuf.update(
                                _ :+ LogMessage.Window(lmp.message, ts)
                              )
                            else IO.unit
                          }
                          .handleErrorWith { err =>
                            Logging.error(
                              s"Failed to decode ${rawMessage.value} as window/logMessage",
                              err
                            )
                          }
                      else IO.unit

                    special.as(notif)
                  case other =>
                    Logging
                      .info(s"With updated mapping: ${other}")
                      .as(lspMessage)

                withUpdatedMapping
                  .flatMap { msg =>
                    messages.update(
                      _ :+ ReceivedMessage(
                        receivedRawMessage.timestamp,
                        receivedRawMessage.value,
                        msg
                      )
                    )
                  }

              }
              .getOrElse(IO.unit)

          }
      }
end State

object State:
  def create =
    for
      messages          <- SignallingRef[IO].of(Vector.empty[ReceivedMessage])
      logBuf            <- SignallingRef[IO].of(Vector.empty[LogMessage])
      ch                <- Channel.bounded[IO, String](128)
      responseIdMapping <- IO.ref(Map.empty[CallId, String])
    yield State(ch, messages, logBuf, responseIdMapping, UUIDGen[IO])
end State
