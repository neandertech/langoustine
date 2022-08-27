import weaver.*
import cats.effect.*

import jsonrpclib.*
import java.util.Base64
import langoustine.tracer.RawMessage

import _root_.fs2.concurrent.Channel as Chan
import _root_.fs2.*

import cats.syntax.all.*

import langoustine.tracer.*
import com.github.plokhotnyuk.jsoniter_scala.core.*
import jsonrpclib.CallId.NumberId

import org.http4s.client.*

import TracerServer.{*, given}
import org.http4s.Uri

import org.http4s.ember.client.*

case class Feed(
    in: Chan[IO, Chunk[Byte]],
    out: Chan[IO, Chunk[Byte]],
    err: Chan[IO, Chunk[Byte]],
    front: Front,
    genId: IO[Option[CallId]]
):
  def send(f: this.type => Chan[IO, Chunk[Byte]], rm: RawMessage) =
    val ser = writeToStringReentrant(rm)
    val str = s"Content-Length: ${ser.getBytes.length}\r\n\r\n$ser"

    f(this).send(Chunk.array(str.getBytes())).void

  def send(f: this.type => Chan[IO, Chunk[Byte]], str: String) =
    f(this).send(Chunk.array(str.getBytes))

  def sendLine(f: this.type => Chan[IO, Chunk[Byte]], str: String) =
    f(this).send(Chunk.array((str + "\n").getBytes))
end Feed

import org.http4s.client.websocket.*

case class Front(client: Client[IO], base: Uri, ws: WSClient[IO]):
  val wsBase = base.copy(scheme = Some(Uri.Scheme.unsafeFromString("ws")))

  def request(id: String) =
    client.expect[RawMessage](base.withPath(s"/api/raw/request/$id"))

  def request(callId: CallId) =
    client.expect[RawMessage](base.withPath(s"/api/raw/request/${cid(callId)}"))

  def request(callId: Option[CallId]) =
    client.expect[RawMessage](
      base.withPath(s"/api/raw/request/${cid(callId.get)}")
    )

  def response(id: String) =
    client.expect[RawMessage](base.withPath(s"/api/raw/response/$id"))

  def response(callId: CallId) =
    client.expect[RawMessage](
      base.withPath(s"/api/raw/response/${cid(callId)}")
    )

  def response(callId: Option[CallId]) =
    client.expect[RawMessage](
      base.withPath(s"/api/raw/response/${cid(callId.get)}")
    )

  def logs =
    client.expect[Vector[String]](base.withPath("/api/logs"))

  def allRaw =
    client.expect[Vector[RawMessage]](base.withPath(s"/api/raw/all"))

  private def cid(c: CallId) = c match
    case CallId.NumberId(n) => n.toString
    case CallId.StringId(s) => s
end Front

// Suites must be "objects" for them to be picked by the framework
object TracerServerSpec extends IOSuite:
  type Res = Feed
  override def sharedResource: Resource[cats.effect.IO, Res] =
    val create = Chan.synchronous[IO, Chunk[Byte]]

    Resource.eval((create, create, create).parTupled).flatMap {
      case (in, out, err) =>
        val server = TracerServer
          .create(
            in = in.stream.unchunks,
            out = out.stream.unchunks,
            err = err.stream.unchunks
          )
          .runResource(Map("--port" -> "9914"))

        import org.http4s.jdkhttpclient.*

        val client =
          JdkHttpClient.simple[IO]

        val ws = JdkWSClient.simple[IO]

        val idState = Resource.eval(IO.ref(0L))

        (server, client, idState, ws).parMapN { case (s, c, ref, w) =>
          val genId =
            ref.getAndUpdate(_ + 1).map(CallId.NumberId.apply).map(Some(_))

          Feed(in, out, err, Front(c, s.baseUri, w), genId)
        }
    }
  end sharedResource

  import scala.concurrent.duration.*

  test("Records incoming requests") { serv =>
    for
      msg1 <- serv.genId.map(id =>
        RawMessage.create(id = id, method = Some("howdy"))
      )

      msg2 <- serv.genId.map(id =>
        RawMessage.create(id = id, method = Some("dowdy"))
      )

      _ <- serv.send(_.in, msg1)
      _ <- serv.send(_.in, msg2)

      resp1 <- serv.front.request(msg1.id)
      resp2 <- serv.front.request(msg2.id)
      all   <- serv.front.allRaw
    yield expect.all(
      resp1 == msg1,
      resp2 == msg2,
      all.contains(msg1),
      all.contains(msg2)
    )
  }

  test("Records outgoing responses") { serv =>
    for
      msg1 <- serv.genId.map(id => RawMessage.create(id = id))

      msg2 <- serv.genId.map(id => RawMessage.create(id = id))

      _ <- serv.send(_.out, msg1)
      _ <- serv.send(_.out, msg2)

      resp1 <- serv.front.response(msg1.id)
      resp2 <- serv.front.response(msg2.id)
      all   <- serv.front.allRaw
    yield expect.all(
      resp1 == msg1,
      resp2 == msg2,
      all.contains(msg1),
      all.contains(msg2)
    )
  }

  test("Records stderr lines") { serv =>
    for
      _    <- serv.send(_.err, "hello\n")
      _    <- serv.send(_.err, "world\n")
      logs <- serv.front.logs
    yield expect.all(logs.containsSlice(Seq("hello", "world")))
  }

  test("Serves JavaScript at /assets/main.js") { serv =>
    serv.front.client
      .expect[String](serv.front.base.withPath("/assets/main.js"))
      .map { resp =>
        success
      }
  }
  test("Serves index.html at /") { serv =>
    serv.front.client
      .expect[String](serv.front.base)
      .map { resp =>
        expect.all(
          resp.contains("DOCTYPE html>"),
          resp.contains("/assets/main.js")
        )
      }
  }
  test("sends logs over websockets") { serv =>
    def logsAreOkay(v: Vector[TracerEvent]) = expect.all(
      v.collectFirst { case _: TracerEvent.LogLines =>
        true
      }.isDefined,
      v.contains(TracerEvent.LogLine("log1")),
      v.contains(TracerEvent.LogLine("log2")),
      v.collect {
        case TracerEvent.LogLines(v) => v
        case TracerEvent.LogLine(v)  => Vector(v)
      }.flatten
        .filter(v => v == "first" || v == "second")
        .toSet == Set("first", "second")
    )

  serv.front.ws
    .connectHighLevel(
      WSRequest(serv.front.wsBase.withPath("/api/ws/events"))
    )
    .use { conn =>
      for
        store <- IO.ref(Vector.empty[TracerEvent])

        _ <- serv.sendLine(_.err, "first")
        _ <- serv.sendLine(_.err, "second")

        all <- conn.receiveStream
          .collect { case WSFrame.Text(str, _) => str }
          .evalMap(str => IO(readFromStringReentrant[TracerEvent](str)))
          .filterNot(_ == TracerEvent.Update)
          .evalMap(ev => store.update(_ :+ ev))
          .concurrently(
            _root_.fs2.Stream
              .emits(Seq("log1", "log2"))
              .covary[IO]
              .evalMap(m => serv.sendLine(_.err, m))
          )
          .evalMap(_ => store.get)
          .takeWhile(logs => !logsAreOkay(logs).run.isValid)
          .compile
          .drain
          .attempt
          .timeout(5.seconds)
          .attempt

        received <- store.get
      yield logsAreOkay(received) and expect(all.isRight)
    }
  }

end TracerServerSpec
