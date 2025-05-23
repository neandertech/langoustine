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
import cats.effect.IO
import cats.data.NonEmptyList

import com.comcast.ip4s.*
import langoustine.tracer.Config.Defaults

object TracerCLISpec extends weaver.SimpleIOSuite:
  val lspCommand = NonEmptyList.of("launch", "me")

  pureTest("CLI rejects invalid port") {
    expect.all(
      Config.command.parse(Seq("--port", "-1000") ++ lspCommand.toList).isLeft,
      Config.command.parse(Seq("--port", "hello") ++ lspCommand.toList).isLeft,
      Config.command
        .parse(
          Seq(
            "--port",
            (Integer.MAX_VALUE.toLong * 2).toString
          ) ++ lspCommand.toList
        )
        .isLeft
    )
  }

  pureTest("CLI rejects invalid host") {
    expect.all(
      Config.command
        .parse(Seq("--host", "123,123,123") ++ lspCommand.toList)
        .isLeft,
      Config.command
        .parse(Seq("--host", "/asd/ads/ads") ++ lspCommand.toList)
        .isLeft,
      Config.command.parse(Seq("--host", "") ++ lspCommand.toList).isLeft,
      Config.command
        .parse(Seq("--host", "-12312312") ++ lspCommand.toList)
        .isLeft
    )
  }

  pureTest("CLI sets expected defaults") {
    val withDefaults = Config.command.parse(lspCommand.toList)

    Config.command.parse(lspCommand.toList) match
      case Left(value) => failure(s"got $value")
      case Right(Config(Mode.Trace(TraceConfig(cmd)), bind, _)) =>
        expect.all(
          bind.port == Defaults.port,
          bind.host == Defaults.host,
          cmd == lspCommand
        )
  }
end TracerCLISpec
