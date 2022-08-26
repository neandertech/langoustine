package langoustine.tracer

import jsonrpclib.fs2.*
import cats.effect.IOApp
import cats.effect.ExitCode
import cats.effect.IO
import fs2.Chunk
import org.http4s.EntityEncoder
import jsonrpclib.Payload
import jsonrpclib.ErrorPayload
import jsonrpclib.CallId
import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import cats.effect.kernel.Ref
import cats.effect.std.Console
import jsonrpclib.Codec
import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*
import org.http4s.headers.*

import org.http4s.*
import org.http4s.headers.`Content-Type`
import org.http4s.dsl.*

import org.http4s.ember.server.EmberServerBuilder
import cats.effect.IO
import org.http4s.HttpApp
import scala.concurrent.duration.*

import com.comcast.ip4s.*
import org.http4s.server.Router

import langoustine.tracer.{Message as LspMessage}
import org.http4s.server.websocket.WebSocketBuilder2
import org.http4s.websocket.WebSocketFrame
import cats.effect.kernel.Clock
import fs2.concurrent.SignallingRef
import fs2.concurrent.Channel
import fs2.text

class TracerServer private (
    in: fs2.Stream[IO, Byte],
    out: fs2.Stream[IO, Byte],
    err: fs2.Stream[IO, Byte]
):
  def dumpRequests(state: State) =
    import state.*
    in
      .through(lsp.decodePayloads[IO])
      .evalMap(p =>
        IO.monotonic
          .map(_.toMillis.toLong)
          .flatMap(lng =>
            IO.fromEither(Codec.decode[RawMessage](Some(p)))
              .map(Received(lng, _))
          )
      )
      .evalTap(rw => raw.update(_ :+ rw))
      .evalMap(rm =>
        rf.update(
          _ ++ LspMessage
            .from(rm.value, Direction.ToServer)
            .map(Received(rm.timestamp, _))
        )
      )
  end dumpRequests

  def dumpResponses(state: State) =
    import state.*
    out
      .through(lsp.decodePayloads[IO])
      .evalMap(p =>
        IO.monotonic
          .map(_.toMillis.toLong)
          .flatMap(lng =>
            IO.fromEither(Codec.decode[RawMessage](Some(p)))
              .map(Received(lng, _))
          )
      )
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

  def run(argMap: Map[String, String]) =
    fs2.Stream.eval(State.create).flatMap { state =>
      fs2.Stream
        .resource(
          Server(
            wbs =>
              ErrorHandling
                .httpApp(handleErrors(app(wbs, state))),
            argMap
          )
        )
        .flatMap { _ =>
          dumpRequests(state)
            .concurrently(dumpResponses(state))
            .concurrently(dumpLogs(state))
        }
    }

  import org.http4s.dsl.io.*

  import TracerServer.given

  case class Received[T](timestamp: Long, value: T)

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

      case GET -> Root / "raw" / "all" =>
        raw.get.map(_.sortBy(_.timestamp).map(_.value)).flatMap(Ok(_))

      case GET -> Root / "raw" / "request" / id =>
        val asLong = id.toLongOption.map(CallId.NumberId.apply)
        val asStr  = CallId.StringId(id)

        raw.get.flatMap { messages =>
          val found = messages.collectFirst {
            case Received(_, r) if asLong == r.id || r.id.contains(asStr) =>
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
      argMap: Map[String, String]
  ) =
    EmberServerBuilder
      .default[IO]
      .withPort(
        argMap.get("--port").flatMap(Port.fromString).getOrElse(port"9977")
      )
      .withHost(host"localhost")
      .withShutdownTimeout(1.second)
      .withHttpWebSocketApp(app)
      .build

end TracerServer

object TracerServer:
  given msg: JsonValueCodec[Vector[LspMessage]] = JsonCodecMaker.make
  given raw: JsonValueCodec[Vector[RawMessage]] = JsonCodecMaker.make

  given jsonEncoder[T: JsonValueCodec]: EntityEncoder[IO, T] =
    EntityEncoder
      .byteArrayEncoder[IO]
      .contramap((data: T) => writeToArray(data))
      .withContentType(
        `Content-Type`(MediaType.application.json, Some(Charset.`UTF-8`))
      )

  def create(
      in: fs2.Stream[IO, Byte],
      out: fs2.Stream[IO, Byte],
      err: fs2.Stream[IO, Byte]
  ): TracerServer =
    TracerServer(in, out, err)
end TracerServer
