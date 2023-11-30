package langoustine.example

import langoustine.lsp.*
import langoustine.lsp.all.*
import langoustine.lsp.app.*
import jsonrpclib.fs2.*

import cats.effect.*

object MyServer extends LangoustineApp.Simple:
  override def server =
    IO.ref(Set.empty[String]).map(myLSP)

def myLSP(files: Ref[IO, Set[String]]) =
  LSPBuilder
    .create[IO]
    .handleRequest(initialize) { in =>
      sendMessage(in.toClient, "ready to initialise!") *>
        IO {
          InitializeResult(
            capabilities = ServerCapabilities(textDocumentSync =
              Opt(TextDocumentSyncKind.Full)
            ),
            serverInfo = Opt(InitializeResult.ServerInfo("My first LSP!"))
          )
        }
    }
    .handleNotification(textDocument.didOpen) { in =>
      val documentUri = in.params.textDocument.uri.value
      files.updateAndGet(_ + documentUri).map(_.size).flatMap { count =>
        sendMessage(in.toClient, s"In total, $count files registered!")
      }
    }
    .handleNotification(MyCustomNotification) { in =>
      sendMessage(in.toClient, s"Received custom notification: ${in.params}")
    }
    .handleRequest(MyCustomRequest) { in =>
      sendMessage(in.toClient, s"Received custom request: ${in.params}") *>
        files.get.map(_.size)
    }

object MyCustomNotification
    extends CustomNotification[String]("custom/myNotification")

object MyCustomRequest extends CustomRequest[String, Int]("custom/myRequest")

def sendMessage(back: Communicate[IO], msg: String) =
  back.notification(
    window.showMessage(ShowMessageParams(MessageType.Info, msg))
  )
