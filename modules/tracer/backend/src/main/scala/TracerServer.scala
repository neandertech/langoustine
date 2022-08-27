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
import jsonrpclib.CallId
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

class TracerServer private (
    in: fs2.Stream[IO, Byte],
    out: fs2.Stream[IO, Byte],
    err: fs2.Stream[IO, Byte]
):
  def dumpRequests(state: State) =
    import state.*
    in
      .through(lsp.decodePayloads[IO])
      .evalMap(decodeRaw(_, state))
      .collect { case Some(v) =>
        v
      }
      .evalTap(rw => raw.update(_ :+ rw))
      .evalMap(rm =>
        rf.update(
          _ ++ LspMessage
            .from(rm.value, Direction.ToServer)
            .map(Received(rm.timestamp, _))
        )
      )
  end dumpRequests

  def decodeRaw(p: Payload, st: State) =
    IO(Codec.decode[RawMessage](Some(p))).flatMap {
      case Left(err) =>
        st.ch.send(s"[Tracer] hard failed to decode $p, error: $err").as(None)
      case Right(v) => Received.capture(v).map(Option.apply)
    }

  def dumpResponses(state: State) =
    import state.*
    out
      .through(lsp.decodePayloads[IO])
      .evalMap(decodeRaw(_, state))
      .collect { case Some(v) =>
        v
      }
      .evalTap(rw => raw.update(_ :+ rw))
      .evalMap(rm =>
        rf.update(
          _ ++ LspMessage
            .from(rm.value, Direction.ToClient)
            .map(Received(rm.timestamp, _))
        )
      )
  end dumpResponses

  def dumpLogs(state: State) =
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

  import cats.syntax.all.*
  import cats.data.Kleisli
  import org.http4s.server.middleware.ErrorHandling

  def handleErrors(routes: HttpRoutes[IO]) =
    routes.orNotFound.onError { exc =>
      Kleisli(request => Console[IO].errorln(s"FAILED $request: $exc"))
    }

  case class State(
      ch: Channel[IO, String],
      rf: SignallingRef[IO, Vector[Received[LspMessage]]],
      raw: SignallingRef[IO, Vector[Received[RawMessage]]],
      logBuf: Ref[IO, Vector[String]]
  )

  object State:
    def create =
      for
        raw    <- SignallingRef[IO].of(Vector.empty[Received[RawMessage]])
        rf     <- SignallingRef[IO].of(Vector.empty[Received[LspMessage]])
        logBuf <- IO.ref(Vector.empty[String])
        ch     <- Channel.bounded[IO, String](128)
      yield State(ch, rf, raw, logBuf)

  def runStream(config: Config) =
    fs2.Stream.resource(runResource(config))

  def runResource(config: Config) =
    Resource.eval(State.create).flatMap { state =>
      val run = Server(
        wbs =>
          ErrorHandling
            .httpApp(handleErrors(app(wbs, state))),
        config
      )
      val dump = dumpRequests(state)
        .concurrently(dumpResponses(state))
        .concurrently(dumpLogs(state))
        .compile
        .drain
        .background

      (run, dump).parMapN((s, _) => s)
    }

  import org.http4s.dsl.io.*

  import org.http4s.dsl.io.*

  import TracerServer.given

  case class Received[T](timestamp: Long, value: T)
  object Received:
    def capture[T](t: T) = IO.monotonic.map(lng => Received(lng.toMillis, t))
    def captureF[T](t: IO[T]) =
      IO.monotonic.flatMap(lng => t.map(Received(lng.toMillis, _)))

  def app(
      wbs: WebSocketBuilder2[IO],
      state: State
  ) =
    import state.*
    val apiRoutes = HttpRoutes.of[IO] {
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
        val asLong = id.toLongOption.map(CallId.NumberId.apply)
        val asStr  = CallId.StringId(id)

        raw.get.flatMap { messages =>
          val found = messages.collectFirst {
            case Received(_, r)
                if (asLong == r.id || r.id.contains(
                  asStr
                )) && r.method.nonEmpty =>
              r
          }

          found match
            case Some(rm) => Ok(rm)
            case None     => NotFound()
        }
      case GET -> Root / "raw" / "response" / id =>
        val asLong = id.toLongOption.map(CallId.NumberId.apply)
        val asStr  = CallId.StringId(id)

        raw.get.flatMap { messages =>
          val found = messages.collectFirst {
            case Received(_, r)
                if (asLong == r.id || r.id.contains(asStr))
                  && r.method.isEmpty =>
              r
          }

          found match
            case Some(rm) => Ok(rm)
            case None     => NotFound()
        }
    }

    Router("/api" -> apiRoutes) <+> Static.routes
  end app

  def Server(
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
  given msg: JsonValueCodec[Vector[LspMessage]] = JsonCodecMaker.make
  given raw: JsonValueCodec[Vector[RawMessage]] = JsonCodecMaker.make

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
end TracerServer
