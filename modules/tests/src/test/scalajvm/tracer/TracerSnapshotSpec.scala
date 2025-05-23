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
