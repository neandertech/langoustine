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

  test("textDocument/documentSymbol") {
    import requests.*

    val symbols: Opt[Vector[DocumentSymbol]] =
      Opt(
        Vector(
          DocumentSymbol(
            name = "root",
            kind = SymbolKind.Array,
            range = Range(Position(0, 0), Position(0, 5)),
            selectionRange = Range(Position(0, 0), Position(0, 5)),
            children = Opt(
              Vector(
                DocumentSymbol(
                  name = "child",
                  kind = SymbolKind.String,
                  range = Range(Position(1, 0), Position(1, 5)),
                  selectionRange = Range(Position(1, 0), Position(1, 5))
                )
              )
            )
          )
        )
      )

    val server = basicServer[Try].handleRequest(textDocument.documentSymbol) {
      in =>
        Try {
          symbols
        }
    }

    val (response, _) = request(
      server,
      CallId.StringId("resp1"),
      textDocument.documentSymbol,
      DocumentSymbolParams(
        TextDocumentIdentifier(DocumentUri("/home/bla.txt"))
      )
    ).get

    expect.same(
      response,
      symbols
    )
  }
end LSPTests
