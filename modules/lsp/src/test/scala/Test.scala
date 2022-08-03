import upickle.default.*
import langoustine.lsp.structures.DocumentSymbol
import langoustine.lsp.notifications.textDocument

case class Sym(a: String, b: Vector[Sym])

object BaseSuite extends verify.BasicTestSuite:
  test("hello") {}
end BaseSuite
