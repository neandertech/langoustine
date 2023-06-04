package tests.runtime

import langoustine.lsp.runtime.*

object RuntimeTest extends weaver.FunSuite:
  test("Opt[A] is null at runtime"):
      assert.all(
        Opt.empty == null,
        Opt(null) == null,
        Opt("yo") != null
      ) 

  test("Opt[A] contains the value at runtime"):
      assert(Opt(25).asInstanceOf[Int] == 25) &&
        assert(Opt("hello").asInstanceOf[String] == "hello") &&
        assert(Opt("howdy").toOption.contains("howdy"))

  test("Opt[A] can be pattern matched on"):
      Opt("yo") match
        case Opt(str)  => expect(str == "yo")
        case Opt.empty => failure("expected value to be present")

  test("Empty Opt[A] can be pattern matched on"):
      Opt.empty match
        case Opt(str)  => failure("expected value to be present")
        case Opt.empty => success
  import scala.language.strictEquality

  test("Opt[A]: Checks under strict equality"):
      val patternMatchSome = Opt("yo") match
        case Opt(str)  => expect(str == "yo")
        case Opt.empty => failure("expected value to be present")

      val patterMatchNone = Opt.empty match
        case Opt(str)  => failure("expected value to be present")
        case Opt.empty => success

      val nullChecks =
        assert.all(Opt.empty == null, Opt(null) == null, Opt("yo") != null)

      patternMatchSome && patterMatchNone && nullChecks

end RuntimeTest
