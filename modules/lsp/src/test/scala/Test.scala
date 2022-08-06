import upickle.default.*
import langoustine.lsp.structures.DocumentSymbol
import langoustine.lsp.requests.*
import langoustine.lsp.RuntimeBase

case class Sym(a: String, b: Vector[Sym])

object BaseSuite extends verify.BasicTestSuite:
  test("hello") {
    RuntimeBase.uinteger(25).value
  }
end BaseSuite
