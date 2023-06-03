package langoustine.lsp
import weaver.*
import langoustine.lsp.all.Opt

object RuntimeTests extends FunSuite:

  test("Opt(some).toOption") {
    assert.eql(Opt(1).toOption, Some(1))
  }

  test("Opt(none).toOption") {
    assert.eql(Opt.empty.toOption: Option[Int], None)
  }

  test("Opt(some).fold") {
    assert.eql(Opt(1).fold("empty")(_.toString), "1")
  }

  test("Opt(none).fold") {
    assert.eql(Opt.empty.fold("empty")(_.toString), "empty")
  }

end RuntimeTests
