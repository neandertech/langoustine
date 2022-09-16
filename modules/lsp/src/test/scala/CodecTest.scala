import upickle.default.*
import langoustine.lsp.*
import jsonrpclib.Monadic
import scala.util.*
import cats.MonadThrow

import langoustine.lsp.all.*
import cats.Monad

import jsonrpclib.*

object CodecTest extends weaver.FunSuite:
  test("documentSymbol codec") {

    val out1 = Opt(
      Vector(
        SymbolInformation(
          deprecated = Opt(true),
          name = "Howdy1",
          kind = SymbolKind.Method,
          location = Location(
            DocumentUri(""),
            Range(Position.documentBeginning, Position.documentBeginning)
          )
        )
      )
    )

    val out2 = Opt(
      Vector(
        DocumentSymbol(
          name = "Howdy",
          kind = SymbolKind.Class,
          range = Range(
            Position.documentBeginning,
            Position.documentBeginning
          ),
          selectionRange =
            Range(Position.documentBeginning, Position.documentBeginning)
        )
      )
    )

    val unionWriter = textDocument.documentSymbol.outputWriter
    val unionReader = textDocument.documentSymbol.outputReader

    import upickle.default.*

    val written1 = write(out1)(using unionWriter)
    val written2 = write(out2)(using unionWriter)

    val read1 = read(written1)(using unionReader)
    val read2 = read(written2)(using unionReader)

    expect.same(read1, out1) and
      expect.same(read2, out2)
  }

  test("workspace/configuration codec (and types construction)") {
    val req = workspace.configuration
    val in = workspace.configuration.WorkspaceConfigurationInput(
      items = Vector(ConfigurationItem(Opt("hello"))),
      partialResultToken = Opt(ProgressToken("helllooooo"))
    )

    import req.WorkspaceConfigurationInput

    expect.same(read[WorkspaceConfigurationInput](write(in)), in)

  }
end CodecTest
