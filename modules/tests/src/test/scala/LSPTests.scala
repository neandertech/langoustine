package tests.core

import langoustine.lsp.*
import jsonrpclib.Monadic
import scala.util.*

import langoustine.lsp.all.*

import fs2.Stream
import jsonrpclib.*
import jsonrpclib.fs2.FS2Channel
import cats.effect.IO
import scala.concurrent.duration.*
import langoustine.lsp.jsonrpcIntegration.given

import weaver.*
import _root_.fs2.concurrent.SignallingRef

def basicServer[F[_]: Monadic] =
  LSPBuilder.create[F]

object LSPTests extends SimpleIOSuite:

  def setupChannels[Alg[_[_, _, _, _, _]]](
      mkServer: FS2Channel[IO] => List[Endpoint[IO]],
      mkClient: FS2Channel[IO] => List[Endpoint[IO]] = _ => List.empty
  ): Stream[IO, Channel[IO]] =
    for
      serverSideChannel <- FS2Channel.stream[IO]()
      clientSideChannel <- FS2Channel.stream[IO]()
      serverChannelWithEndpoints <- serverSideChannel.withEndpointsStream(
        mkServer(serverSideChannel)
      )
      clientChannelWithEndpoints <- clientSideChannel.withEndpointsStream(
        mkClient(clientSideChannel)
      )
      _ <- Stream(())
        .concurrently(
          clientChannelWithEndpoints.output
            .through(serverChannelWithEndpoints.input)
        )
        .concurrently(
          serverChannelWithEndpoints.output
            .through(clientChannelWithEndpoints.input)
        )
    yield clientChannelWithEndpoints

  def testRes(name: TestName)(run: Stream[IO, Expectations]): Unit =
    test(name)(run.compile.lastOrError.timeout(10.second))

  testRes("initialize") {
    import requests.*

    val capabilities =
      ServerCapabilities(
        hoverProvider = Opt(true),
        documentSymbolProvider =
          Opt(DocumentSymbolOptions(label = Opt("howdy")))
      )

    for
      c <- setupChannels(
        mkServer = cc =>
          basicServer[IO]
            .handleRequest(initialize) { in =>
              IO(InitializeResult(capabilities))
            }
            .build(Communicate.channel(cc, IO.unit))
      )

      remoteCall = c.simpleStub[initialize.In, initialize.Out](
        initialize.requestMethod
      )
      response <- Stream.eval(
        remoteCall(
          InitializeParams(
            processId = Opt(25),
            rootUri = Opt(DocumentUri("/howdy")),
            capabilities = ClientCapabilities()
          )
        )
      )
    yield expect.same(response.capabilities, capabilities)
    end for

  }

  testRes("didOpen") {
    import requests.*

    for
      ref <- Stream.eval(
        SignallingRef[IO, Map[LSPNotification, List[Any]]](Map.empty)
      )
      c <- setupChannels(
        mkServer = cc =>
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
            .build(Communicate.channel(cc, IO.unit)),
        mkClient = _ =>
          List(
            Endpoint[IO](window.showMessage.notificationMethod)
              .notification[window.showMessage.In](in =>
                ref.update { mp =>
                  mp.updatedWith(window.showMessage) { prev =>
                    prev.map(_ :+ in).orElse(Some(List(in)))
                  }
                }
              )
          )
      )

      remoteCall = c.notificationStub[textDocument.didOpen.In](
        textDocument.didOpen.notificationMethod
      )
      _ <- Stream.eval(
        remoteCall(
          DidOpenTextDocumentParams(
            TextDocumentItem(
              uri = DocumentUri("/home/bla.txt"),
              languageId = "text",
              version = 0,
              text = "Hello!"
            )
          )
        )
      )
      response <- ref.discrete
        .dropWhile(_.isEmpty)
        .map(_.get(window.showMessage))
        .collectFirst { case Some(v) => v }
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

  testRes("textDocument/documentSymbol") {
    import requests.*
    import textDocument.documentSymbol.given

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

      remoteCall = channel.simpleStub[
        textDocument.documentSymbol.In,
        textDocument.documentSymbol.Out
      ](
        textDocument.documentSymbol.requestMethod
      )
      response <- Stream.eval(
        remoteCall(
          DocumentSymbolParams(
            TextDocumentIdentifier(DocumentUri("/home/bla.txt"))
          )
        )
      )
    yield expect.same(response, symbols)
    end for

  }
end LSPTests
