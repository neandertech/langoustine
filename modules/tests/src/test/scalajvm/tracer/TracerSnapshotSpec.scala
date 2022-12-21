package tests.tracer

import langoustine.tracer.*
import TracerServer.{*, given}
import fs2.concurrent.Channel as Chan
import cats.effect.*
import cats.syntax.all.*
import com.github.plokhotnyuk.jsoniter_scala.core.*
import java.util.Base64
import weaver.*

object TracerSnapshotSpec extends ServerSpec:
  import scala.concurrent.duration.*

  test("Serves a snapshot with correct information") { serv =>
    serv.sendLine(_.err, "hello") *>
      serv.sendLine(_.err, "world") *>
      serv.front
        .snapshot(None)
        .flatMap(_.body)
        .through(fs2.compression.Compression[IO].gunzip())
        .flatMap(_.content)
        .through(fs2.text.utf8.decode)
        .through(fs2.text.lines)
        .evalMap(l => IO(readFromString[SnapshotItem](l)))
        .compile
        .toVector
        .map { s =>
          val logs = s.collect { case SnapshotItem.Log(msg) =>
            msg.value
          }
          expect.same(logs, Vector("hello", "world"))
        }

  }
end TracerSnapshotSpec
