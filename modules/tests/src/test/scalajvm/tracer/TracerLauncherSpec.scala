package tests.tracer

import weaver.*
import cats.data.NonEmptyList

import langoustine.tracer.*

object TracerLauncherSpec extends SimpleIOSuite:
  test("doesn't start the server if process fails") {
    LangoustineTracer
      .Launch(
        Config.trace(NonEmptyList.of("!@£!WIIACSNN£!)(ASNDASD"))
      )
      .compile
      .drain
      .attempt
      .map { result =>
        expect(result.isLeft)
      }
  }
end TracerLauncherSpec
