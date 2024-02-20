package tests.tracer

import langoustine.tracer.*
import TracerServer.{*, given}
import cats.effect.*
import cats.syntax.all.*
import com.github.plokhotnyuk.jsoniter_scala.core.*
import java.util.Base64
import org.http4s.Uri
import org.http4s.client.*
import org.http4s.client.websocket.*
import scala.concurrent.duration.*
import weaver.*
import jsonrpclib.Payload

object TracerServerSpec extends ServerSpec:

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

  test("Records outgoing requests and incoming responses") { serv =>
    for
      id <- serv.genId

      serverReq1 = RawMessage.create(id = id, method = Some("window/howdy"))
      _ <- serv.send(_.out, serverReq1)

      clientResp1 = RawMessage.create(
        id = id,
        result = Some(Payload("{}".getBytes()))
      )
      _ <- serv.send(_.in, clientResp1)

      resp1 <- serv.front.response(id)
      req1  <- serv.front.request(id)
      all   <- serv.front.allRaw
    yield expect.all(
      resp1 == clientResp1,
      req1 == serverReq1,
      all.contains(clientResp1),
      all.contains(serverReq1)
    )
  }

  test("Records outgoing responses") { serv =>
    for
      msg1 <- serv.genId.map(id =>
        RawMessage.create(id = id, result = Some(Payload("{}".getBytes())))
      )
      msg2 <- serv.genId.map(id =>
        RawMessage.create(id = id, result = Some(Payload("{}".getBytes())))
      )

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
      _    <- serv.sendLine(_.err, "hello")
      _    <- serv.sendLine(_.err, "world")
      logs <- serv.front.logs
    yield expect.all(logs.map(_.value).containsSlice(Seq("hello", "world")))
  }

  test("Serves summary at /api/summary") { serv =>
    serv.front.summary.map {
      case summary: Summary.Trace =>
        expect.all(
          summary.workingFolder == System.getProperty("user.dir"),
          summary.serverCommand == List("echo", "world")
        )
      case other => failure(s"Expected Summary.Trace got $other instead")
    }
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
      v.collect { case TracerEvent.LogLines(v) =>
        v
      }.flatten
        .map(_.value)
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
