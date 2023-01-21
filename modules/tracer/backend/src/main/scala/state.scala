package langoustine.tracer

import cats.effect.*
import fs2.concurrent.*
import cats.effect.std.*

import jsonrpclib.fs2.lsp
import jsonrpclib.{Payload}
import jsonrpclib.Codec
import langoustine.lsp.requests.window
import langoustine.lsp.enumerations

case class State(
    ch: Channel[IO, String],
    messages: SignallingRef[IO, Vector[ReceivedMessage]],
    logBuf: SignallingRef[IO, Vector[LogMessage]],
    responseIdMapping: Ref[IO, Map[MessageId, String]],
    random: UUIDGen[IO]
):
  private def decodeRaw(p: Payload) =
    IO(Codec.decode[RawMessage](Some(p))).flatMap {
      case Left(err) =>
        ch.send(s"[Tracer] hard failed to decode $p, error: $err").as(None)
      case Right(v) => Received.capture(v).map(Option.apply)
    }

  def hydrateFrom(
      stream: fs2.Stream[IO, Payload],
      direction: Direction
  ): fs2.Stream[cats.effect.IO, Received[RawMessage]] =
    stream
      .evalMap(decodeRaw)
      .collect { case Some(v) =>
        v
      }
      .evalTap { rawMessage =>
        random.randomUUID
          .map(u => MessageId.StringId("generated-" + u.toString))
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
                  case LspMessage.Request(method, id, _) =>
                    responseIdMapping
                      .update(_.updated(id, method))
                      .as(lspMessage)

                  case LspMessage.Response(id, _) =>
                    messages.update { received =>
                      received.collect {
                        case ReceivedMessage(ts, raw, req: LspMessage.Request)
                            if req.id == id =>
                          ReceivedMessage(ts, raw, req.copy(responded = true))
                        case other => other
                      }
                    } *>
                      responseIdMapping.get
                        .map(_.get(id))
                        .map(LspMessage.Response(id, _))

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
      responseIdMapping <- IO.ref(Map.empty[MessageId, String])
    yield State(ch, messages, logBuf, responseIdMapping, UUIDGen[IO])
end State
