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

    expect.all(
      withDefaults.map(_.port) == Right(Defaults.port),
      withDefaults.map(_.host) == Right(Defaults.host),
      withDefaults.map(_.cmd) == Right(lspCommand)
    )
  }
end TracerCLISpec
