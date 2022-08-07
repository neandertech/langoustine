import upickle.default.*
import langoustine.lsp.*
import jsonrpclib.Monadic
import scala.util.*
import cats.MonadThrow

import structures.*
import enumerations.*
import langoustine.lsp.json.*
import cats.Monad

def basicServer[F[_]: Monadic] =
  LSPBuilder.create[F]

import jsonrpclib.*

object LSPSuite extends verify.BasicTestSuite:
  test("initialize") {

    import requests.*
    import RuntimeBase.*

    val capabilities =
      ServerCapabilities(
        hoverProvider = Opt(true),
        documentSymbolProvider =
          Opt(DocumentSymbolOptions(label = Opt("howdy")))
      )

    val server = basicServer[Try].handleRequest(initialize) { in =>
      Try {
        InitializeResult(capabilities)
      }
    }

    val (response, _) = request(
      server,
      initialize,
      InitializeParams(
        processId = Opt(25),
        rootUri = Opt(DocumentUri("/howdy")),
        capabilities = ClientCapabilities()
      )
    ).get

    assert(response.capabilities == capabilities)

  }

  test("didOpen") {
    import requests.*
    import RuntimeBase.*

    val server = basicServer[Try].handleNotification(textDocument.didOpen) {
      in =>
        in.toClient.notification(
          window.showMessage,
          ShowMessageParams(
            MessageType.Info,
            s"you opened a ${in.params.textDocument.languageId} document from ${in.params.textDocument.uri}!"
          )
        )
    }

    val back = notification(
      server,
      textDocument.didOpen,
      DidOpenTextDocumentParams(
        TextDocumentItem(
          uri = DocumentUri("/home/bla.txt"),
          languageId = "text",
          version = 0,
          text = "Hello!"
        )
      )
    ).get

    assert(
      back.collect(window.showMessage).get == List(
        ShowMessageParams(
          MessageType.Info,
          "you opened a text document from /home/bla.txt!"
        )
      )
    )

  }
end LSPSuite
