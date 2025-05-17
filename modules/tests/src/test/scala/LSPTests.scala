package tests.core

import langoustine.lsp.*
import jsonrpclib.Monadic
import jsonrpclib.fs2.catsMonadic
import scala.util.*

import langoustine.lsp.all.*

import fs2.Stream
import jsonrpclib.*
import cats.effect.IO
import scala.concurrent.duration.*

import weaver.*
import _root_.fs2.concurrent.SignallingRef

def basicServer[F[_]: Monadic] =
  LSPBuilder.create[F]

object LSPTests extends SimpleIOSuite:

  def testStream(name: TestName)(run: Stream[IO, Expectations]): Unit =
    test(name)(run.compile.lastOrError.timeout(10.second))

  testStream("initialize") {
    import requests.*

    val capabilities =
      ServerCapabilities(
        hoverProvider = Opt(true),
        documentSymbolProvider =
          Opt(DocumentSymbolOptions(label = Opt("howdy")))
      )

    for
      channel <- setupChannels(
        mkServer = channel =>
          basicServer[IO]
            .handleRequest(initialize) { in =>
              IO(InitializeResult(capabilities))
            }
            .build(Communicate.channel(channel, IO.unit))
      )
      response <- request(
        channel,
        initialize,
        InitializeParams(
          processId = Opt(25),
          rootUri = Opt(DocumentUri("/howdy")),
          capabilities = ClientCapabilities()
        )
      )
    yield expect.same(response.capabilities, capabilities)
    end for

  }

  testStream("didOpen") {
    import requests.*

    for
      ref <- Stream.eval(
        SignallingRef[IO, Map[LSPNotification, List[Any]]](Map.empty)
      )
      channel <- setupChannels(
        mkServer = channel =>
          basicServer[IO]
            .handleNotification(textDocument.didOpen) { in =>
              in.toClient.notification(
                window.showMessage,
                ShowMessageParams(
                  MessageType.Info,
                  s"you opened a ${in.params.textDocument.languageId} document from ${in.params.textDocument.uri}!"
                )
              )
            }
            .build(Communicate.channel(channel, IO.unit)),
        mkClient =
          _ => List(collectNotificationEndpoint(ref)(window.showMessage))
      )
      _ <- notification(
        channel,
        textDocument.didOpen,
        DidOpenTextDocumentParams(
          TextDocumentItem(
            uri = DocumentUri("/home/bla.txt"),
            languageId = "text",
            version = 0,
            text = "Hello!"
          )
        )
      )
      response <- getCaputured(ref.discrete)(window.showMessage)
    yield expect.same(
      response,
      List(
        ShowMessageParams(
          MessageType.Info,
          "you opened a text document from /home/bla.txt!"
        )
      )
    )
    end for
  }

  testStream("textDocument/documentSymbol") {
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

    for
      channel <- setupChannels(
        mkServer = channel =>
          basicServer[IO]
            .handleRequest(textDocument.documentSymbol) { _ =>
              IO(symbols)
            }
            .build(Communicate.channel(channel, IO.unit))
      )

      response <- request(
        channel,
        textDocument.documentSymbol,
        DocumentSymbolParams(
          TextDocumentIdentifier(DocumentUri("/home/bla.txt"))
        )
      )
    yield expect.same(response, symbols)
    end for

  }
end LSPTests
