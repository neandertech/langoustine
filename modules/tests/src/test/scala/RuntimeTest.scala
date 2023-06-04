package tests.runtime

import langoustine.lsp.runtime.*

object RuntimeTest extends weaver.FunSuite:
  test("Opt[A] is null at runtime"):
      assert(Opt.empty == null) &&
        assert(Opt(null) == null) // :shrug:

  test("Opt[A] contains the value at runtime"):
      assert(Opt(25).asInstanceOf[Int] == 25) &&
        assert(Opt("hello").asInstanceOf[String] == "hello") &&
        assert(Opt("howdy").toOption.contains("howdy"))

  test("Opt[A] can be pattern matched on"): 
    Opt("yo") match 
      case Opt(str) => expect(str == "yo")
      case Opt.empty => failure("expected value to be present")
end RuntimeTest
