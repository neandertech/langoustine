package tests.core

import upickle.default.*
import langoustine.lsp.*
import jsonrpclib.Monadic
import scala.util.*
import cats.MonadThrow

import langoustine.lsp.all.*
import cats.Monad

def basicServer[F[_]: Monadic] =
  LSPBuilder.create[F]

import jsonrpclib.*

object LSPTests extends weaver.FunSuite:
  test("initialize") {

    import requests.*

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
      CallId.StringId("resp1"),
      initialize,
      InitializeParams(
        processId = Opt(25),
        rootUri = Opt(DocumentUri("/howdy")),
        capabilities = ClientCapabilities()
      )
    ).get

    expect.same(response.capabilities, capabilities)

  }

  test("didOpen") {
    import requests.*

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

    expect.same(
      back.collect(window.showMessage).get,
      List(
        ShowMessageParams(
          MessageType.Info,
          "you opened a text document from /home/bla.txt!"
        )
      )
    )

  }
end LSPTests
