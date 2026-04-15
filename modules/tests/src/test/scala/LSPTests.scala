/*
 * Copyright 2022 Neandertech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tests.core

import scala.concurrent.duration.*

import cats.effect.IO
import jsonrpclib.fs2.catsMonadic
import jsonrpclib.{fs2 as _, *}
import langoustine.lsp.*
import langoustine.lsp.all.*
import langoustine.lsp.structures.InitializeParams.ClientInfo
import langoustine.lsp.structures.InitializeResult.ServerInfo

object LSPTests extends weaver.SimpleIOSuite:

  def serverTest(tn: weaver.TestName)(
      f: weaver.Log[IO] => IO[weaver.Expectations]
  ): Unit =
    test(tn)((res, log) => f(log))

  serverTest("initialize"): log =>
    Probe
      .create(
        basicServer
          .handleRequest(initialize): in =>
            IO(
              InitializeResult(
                ServerCapabilities(
                  hoverProvider = Opt(true),
                  documentSymbolProvider =
                    Opt(DocumentSymbolOptions(label = Opt("howdy")))
                ),
                serverInfo = Some(
                  ServerInfo(name =
                    in.params.clientInfo.get.name + " responder"
                  )
                )
              )
            ),
        log
      )
      .use: probe =>
        val expected =
          InitializeResult(
            ServerCapabilities(
              hoverProvider = Some(true),
              documentSymbolProvider =
                Some(DocumentSymbolOptions(label = Some("howdy")))
            ),
            serverInfo = Some(ServerInfo("shrimp responder"))
          )

        for response <- probe.request(
            CallId.StringId("resp1"),
            initialize,
            InitializeParams(
              processId = Opt(25),
              rootUri = Opt(DocumentUri("/howdy")),
              capabilities = ClientCapabilities(),
              clientInfo = Some(ClientInfo("shrimp"))
            )
          )
        yield expect.same(expected, response)
        end for

  serverTest("didOpen") { log =>
    import requests.*

    val server = basicServer.handleNotification(textDocument.didOpen) { in =>
      log.info("in handler") *>
        in.toClient.notification(
          window.showMessage(
            ShowMessageParams(
              MessageType.Info,
              s"you opened a ${in.params.textDocument.languageId} document from ${in.params.textDocument.uri}!"
            )
          )
        ) *>
        IO.sleep(100.millis) *>
        in.toClient.notification(
          window.showMessage(
            ShowMessageParams(
              MessageType.Info,
              "And hello to yous"
            )
          )
        )
    }

    Probe
      .create(server, log)
      .use: probe =>
        for
          _ <- probe.notification(
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
          nots <- probe.waitForNotifications(window.showMessage, 2)
          (first, second) = (nots.head, nots.tail.head)
        yield expect.same(
          ShowMessageParams(
            MessageType.Info,
            s"you opened a text document from /home/bla.txt!"
          ),
          first
        ) && expect.same(
          ShowMessageParams(
            MessageType.Info,
            "And hello to yous"
          ),
          second
        )

  }

  serverTest("documentSymbol") { log =>
    val symbols: Option[Vector[DocumentSymbol]] =
      Option(
        Vector(
          DocumentSymbol(
            name = "root",
            kind = SymbolKind.Array,
            range = Range(Position(0, 0), Position(0, 5)),
            selectionRange = Range(Position(0, 0), Position(0, 5)),
            children = Option(
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

    end symbols

    try textDocument.documentSymbol.outputToJson(symbols)
    catch
      case e: Throwable =>
        e.printStackTrace()

    textDocument.documentSymbol.outputToJson(symbols)

    val server = basicServer.handleRequest(textDocument.documentSymbol) { in =>
      IO(symbols)
    }

    Probe.create(server, log).use { probe =>
      for response <- probe.request(
          CallId.StringId("symbols"),
          textDocument.documentSymbol,
          DocumentSymbolParams(
            TextDocumentIdentifier(DocumentUri("/home/bla.txt"))
          )
        )
      yield expect.same(symbols, response)
    }

  }
end LSPTests

def basicServer =
  LSPBuilder.create[IO]

//   test("textDocument/documentSymbol") {
//     import requests.*

//     val symbols: Option[Vector[DocumentSymbol]] =
//       Opt(
//         Vector(
//           DocumentSymbol(
//             name = "root",
//             kind = SymbolKind.Array,
//             range = Range(Position(0, 0), Position(0, 5)),
//             selectionRange = Range(Position(0, 0), Position(0, 5)),
//             children = Opt(
//               Vector(
//                 DocumentSymbol(
//                   name = "child",
//                   kind = SymbolKind.String,
//                   range = Range(Position(1, 0), Position(1, 5)),
//                   selectionRange = Range(Position(1, 0), Position(1, 5))
//                 )
//               )
//             )
//           )
//         )
//       )

//     val server = basicServer[Try].handleRequest(textDocument.documentSymbol) {
//       in =>
//         Try {
//           symbols
//         }
//     }

//     val (response, _) = request(
//       server,
//       CallId.StringId("resp1"),
//       textDocument.documentSymbol,
//       DocumentSymbolParams(
//         TextDocumentIdentifier(DocumentUri("/home/bla.txt"))
//       )
//     ).get

//     expect.same(
//       response,
//       symbols
//     )
//   }
// end LSPTests
