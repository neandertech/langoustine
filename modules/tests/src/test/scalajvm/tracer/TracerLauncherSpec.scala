package tests.tracer

import weaver.*
import cats.data.NonEmptyList

import langoustine.tracer.*

object TracerLauncherSpec extends SimpleIOSuite:
  test("doesn't start the server if process fails") {
    Launch(
      Config(-12315123, NonEmptyList.of("!@£!WIIACSNN£!)(ASNDASD"))
    ).attempt.map { result =>
      expect(result.isLeft)
    }
  }
