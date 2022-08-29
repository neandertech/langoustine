package langoustine.tracer

import cats.effect.IO
import cats.effect.std.Console
import cats.effect.Ref
import cats.effect.kernel.Clock
import com.comcast.ip4s.*
import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*
import fs2.concurrent.Channel
import fs2.concurrent.SignallingRef
import fs2.text
import jsonrpclib.Codec
import jsonrpclib.Payload
import jsonrpclib.fs2.lsp
import langoustine.tracer.{Message as LspMessage}
import org.http4s.*
import org.http4s.HttpApp
import org.http4s.dsl.*
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.headers.`Content-Type`
import org.http4s.server.Router
import org.http4s.server.websocket.WebSocketBuilder2
import org.http4s.websocket.WebSocketFrame
import scala.concurrent.duration.*
import cats.effect.kernel.Resource
import cats.effect.std.Random
import cats.effect.std.UUIDGen
import java.util.UUID
import org.http4s.server.middleware.ErrorHandling
import cats.syntax.all.*
import cats.data.Kleisli
import org.http4s.server.middleware.ErrorHandling
import TracerServer.{*, given}

class TracerServer private (
    in: fs2.Stream[IO, Byte],
    out: fs2.Stream[IO, Byte],
    err: fs2.Stream[IO, Byte]
):

  def runResource(config: Config, summary: Summary) =
    Resource.eval(State.create).flatMap { state =>
      val run = Server(
        wbs =>
          ErrorHandling
            .httpApp(handleErrors(app(wbs, state, summary))),
        config
      )
      val dump = dumpRequests(state)
        .concurrently(dumpResponses(state))
        .concurrently(dumpLogs(state))
        .compile
        .drain
        .background

      dump *> run
    }

  private def dumpRequests(state: State)  = dump(in, state, Direction.ToServer)
  private def dumpResponses(state: State) = dump(out, state, Direction.ToClient)

  private def dumpLogs(state: State) =
    err
      .through(text.utf8.decode[IO])
      .through(text.lines)
      .chunks
      .evalTap { chunk =>
        state.logBuf.update { lines =>
          lines.drop(lines.size + chunk.size - 10000) ++ chunk.toVector
        }
      }
      .unchunks
      .through(state.ch.sendAll)

  private def handleErrors(routes: HttpRoutes[IO]) =
    routes.orNotFound.onError { exc =>
      Kleisli(request => Console[IO].errorln(s"FAILED $request: $exc"))
    }

  import org.http4s.dsl.io.*

  private def app(
      wbs: WebSocketBuilder2[IO],
      state: State,
      summary: Summary
  ) =
    import state.*
    val apiRoutes = HttpRoutes.of[IO] {
      case GET -> Root / "summary" =>
        Ok(summary)

      case GET -> Root / "ws" / "events" =>
        val continuous =
          rf.discrete
            .as(TracerEvent.Update)
            .merge(raw.discrete.as(TracerEvent.Update))
            .merge(ch.stream.map(l => TracerEvent.LogLine(l)))

        val out = fs2.Stream.eval(logBuf.get).map(TracerEvent.LogLines(_))

        val updates =
          (out ++ continuous)
            .map(ev =>
              WebSocketFrame.Text(writeToStringReentrant[TracerEvent](ev))
            )

        val pings =
          fs2.Stream.repeatEval(IO(WebSocketFrame.Ping())).metered(1.second)

        wbs
          .withFilterPingPongs(false)
          .build(updates.merge(pings), _.map(_ => ()))

      case GET -> Root / "all" =>
        rf.get.map(_.sortBy(_.timestamp).map(_.value)).flatMap(Ok(_))

      case GET -> Root / "logs" =>
        logBuf.get.flatMap(Ok(_))

      case GET -> Root / "raw" / "all" =>
        raw.get.map(_.sortBy(_.timestamp).map(_.value)).flatMap(Ok(_))

      case GET -> Root / "raw" / "request" / id =>
        val asLong = id.toLongOption.map(MessageId.NumberId.apply)
        val asStr  = MessageId.StringId(id)

        inline def idMatches(idOpt: Option[MessageId]) =
          (asLong == idOpt || idOpt.contains(asStr))

        raw.get.flatMap { messages =>
          val found = messages.collectFirst {
            case Received(_, r) if idMatches(r.id) && r.method.nonEmpty =>
              r
          }

          found match
            case Some(rm) => Ok(rm)
            case None     => NotFound()
        }

      case GET -> Root / "raw" / "response" / id =>
        val asLong = id.toLongOption.map(MessageId.NumberId.apply)
        val asStr  = MessageId.StringId(id)

        inline def idMatches(idOpt: Option[MessageId]) =
          (asLong == idOpt || idOpt.contains(asStr))

        raw.get.flatMap { messages =>
          val found = messages.collectFirst {
            case Received(_, r) if idMatches(r.id) && r.method.isEmpty =>
              r
          }

          found match
            case Some(rm) => Ok(rm)
            case None     => NotFound()
        }

      case GET -> Root / "raw" / "notification" / id =>
        val asStr = MessageId.StringId(id)

        inline def idMatches(idOpt: Option[MessageId]) =
          idOpt.contains(asStr)

        raw.get.flatMap { messages =>
          val found = messages.collectFirst {
            case Received(_, r) if idMatches(r.id) => r
          }

          found match
            case Some(rm) => Ok(rm)
            case None     => NotFound()
        }
    }

    Router("/api" -> apiRoutes) <+> Static.routes
  end app

  private def Server(
      app: WebSocketBuilder2[IO] => HttpApp[IO],
      config: Config
  ): Resource[cats.effect.IO, server.Server] =
    EmberServerBuilder
      .default[IO]
      .withPort(
        Port.fromInt(config.port).get
      )
      .withHost(host"localhost")
      .withShutdownTimeout(1.second)
      .withHttpWebSocketApp(app)
      .build

end TracerServer

object TracerServer:
  given msgCodec: JsonValueCodec[Vector[LspMessage]] = JsonCodecMaker.make
  given rawCodec: JsonValueCodec[Vector[RawMessage]] = JsonCodecMaker.make

  given JsonValueCodec[Vector[String]] = JsonCodecMaker.make
  given jsonEncoder[T: JsonValueCodec]: EntityEncoder[IO, T] =
    EntityEncoder
      .byteArrayEncoder[IO]
      .contramap((data: T) => writeToArray(data))
      .withContentType(
        `Content-Type`(MediaType.application.json, Some(Charset.`UTF-8`))
      )

  given jsonDecoder[T: JsonValueCodec]: EntityDecoder[IO, T] =
    EntityDecoder.byteArrayDecoder[IO].map(readFromArray[T](_))

  def create(
      in: fs2.Stream[IO, Byte],
      out: fs2.Stream[IO, Byte],
      err: fs2.Stream[IO, Byte]
  ): TracerServer =
    TracerServer(in, out, err)

  case class State(
      ch: Channel[IO, String],
      rf: SignallingRef[IO, Vector[Received[LspMessage]]],
      raw: SignallingRef[IO, Vector[Received[RawMessage]]],
      logBuf: Ref[IO, Vector[String]],
      responseIdMapping: Ref[IO, Map[MessageId, String]],
      random: UUIDGen[IO]
  )

  object State:
    def create =
      for
        raw      <- SignallingRef[IO].of(Vector.empty[Received[RawMessage]])
        rf       <- SignallingRef[IO].of(Vector.empty[Received[LspMessage]])
        logBuf   <- IO.ref(Vector.empty[String])
        ch       <- Channel.bounded[IO, String](128)
        rpid     <- IO.ref(Map.empty[MessageId, String])
      yield State(ch, rf, raw, logBuf, rpid, UUIDGen[IO])
  end State

  def dump(stream: fs2.Stream[IO, Byte], state: State, direction: Direction) =
    import state.*
    stream
      .through(lsp.decodePayloads[IO])
      .evalMap(decodeRaw(_, state))
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
            raw.update(_ :+ receivedRawMessage) *> {
              val msg = LspMessage
                .from(rawMessage.value, direction, generatedId)
              val receivedMsg = msg.map(Received(rawMessage.timestamp, _))

              LspMessage
                .from(rawMessage.value, direction, generatedId)
                .map { lspMessage =>

                  val withUpdatedMapping = lspMessage match
                    case LspMessage.Request(method, id, _) =>
                      responseIdMapping
                        .update(_.updated(id, method))
                        .as(lspMessage)

                    case LspMessage.Response(id, _) =>
                      rf.update { received =>
                        received.collect {
                          case Received(ts, req: LspMessage.Request)
                              if req.id == id =>
                            Received(ts, req.copy(responded = true))
                          case other => other
                        }
                      } *>
                        responseIdMapping.get
                          .map(_.get(id))
                          .map(LspMessage.Response(id, _))

                    case other => IO.pure(other)

                  withUpdatedMapping
                    .flatMap(msg =>
                      rf.update(_ :+ Received(rawMessage.timestamp, msg))
                    )

                }
                .getOrElse(IO.unit)
            }
          }
      }
  end dump

  case class Received[T](timestamp: Long, value: T)
  object Received:
    def capture[T](t: T) = IO.monotonic.map(lng => Received(lng.toMillis, t))
    def captureF[T](t: IO[T]) =
      IO.monotonic.flatMap(lng => t.map(Received(lng.toMillis, _)))

  def decodeRaw(p: Payload, st: State) =
    IO(Codec.decode[RawMessage](Some(p))).flatMap {
      case Left(err) =>
        st.ch.send(s"[Tracer] hard failed to decode $p, error: $err").as(None)
      case Right(v) => Received.capture(v).map(Option.apply)
    }

end TracerServer
