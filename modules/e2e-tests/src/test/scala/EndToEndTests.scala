package tests.e2e

import langoustine.lsp.all.*

import weaver.*

import com.github.plokhotnyuk.jsoniter_scala.core.*
import upickle.default.ReadWriter
import cats.effect.IO
import scala.sys.process.ProcessIO
import concurrent.duration.*
import scala.sys.process.BasicIO
import jsonrpclib.Payload

import cats.syntax.all.*
import scala.concurrent.Promise
import cats.effect.std.*
import cats.effect.kernel.Ref
import cats.effect.kernel.Deferred
import cats.effect.kernel.Resource
import java.lang.management.ManagementFactory

import langoustine.example.{MyCustomRequest, MyCustomNotification}
import fs2.io.process.Processes

case class Result(code: Int, stdout: List[String], stderr: List[String])

object EndToEndTests extends SimpleIOSuite:
  loggedTest("on Scala Native") { log =>
    val native = sys.env("EXAMPLE_NATIVE")

    verifyEndToEnd(Seq(native), log)
  }

  loggedTest("on JVM") { log =>
    val jar = sys.env("EXAMPLE_JVM")

    verifyEndToEnd(Seq("java", "-jar", jar), log)
  }

  loggedTest("on Scala.js (Node.js)") { log =>
    val js = sys.env("EXAMPLE_JS")

    verifyEndToEnd(Seq("node", js), log)
  }

  private def verifyEndToEnd(process: Seq[String], log: weaver.Log[IO]) =
    val str = sendRequest(
      initialize,
      InitializeParams(
        processId = Opt(25),
        rootUri = Opt(DocumentUri("hello")),
        capabilities = ClientCapabilities()
      )
    )

    val str1 = sendNotification(
      textDocument.didOpen,
      DidOpenTextDocumentParams(
        textDocument = TextDocumentItem(
          uri = DocumentUri("howdy"),
          languageId = "markdown",
          version = 1,
          text = "blabla"
        )
      )
    )

    val str2 = sendNotification(
      textDocument.didOpen,
      DidOpenTextDocumentParams(
        textDocument = TextDocumentItem(
          uri = DocumentUri("howdy1"),
          languageId = "markdown",
          version = 1,
          text = "blabla"
        )
      )
    )

    val str3 = sendNotification(MyCustomNotification, "hello!")
    val str4 = sendRequest(MyCustomRequest, "hello request!")

    val send = str + str1 + str2 + str3 + str4

    val timeout = 5.seconds

    def decode(o: String) =
      fs2.Stream
        .emits(o.getBytes)
        .through(jsonrpclib.fs2.lsp.decodePayloads[IO])
        .compile
        .toVector

    val procR = Processes[IO].spawn(
      fs2.io.process.ProcessBuilder(process.head, process.tail*)
    )

    procR.use { prc =>
      IO.ref("")
        .flatMap { fromStdout =>
          prc.stdout
            .through(fs2.text.utf8.decode)
            .evalMap(s => fromStdout.update(_ + s))
            .compile
            .drain
            .start *>
            fs2.Stream
              .emits(send.getBytes)
              .through(prc.stdin)
              .compile
              .drain *>
            IO.sleep(timeout) *>
            fromStdout.get
        }
        .flatMap(decode)
        .map { pays =>
          val showMsg     = pays.flatMap(asNotification(window.showMessage, _))
          val initialized = pays.flatMap(asResponse(initialize, _))

          expect.all(
            showMsg.toSet == Set(
              ShowMessageParams(MessageType.Info, "ready to initialise!"),
              ShowMessageParams(
                MessageType.Info,
                "In total, 1 files registered!"
              ),
              ShowMessageParams(
                MessageType.Info,
                "In total, 2 files registered!"
              ),
              ShowMessageParams(
                MessageType.Info,
                "Received custom notification: hello!"
              ),
              ShowMessageParams(
                MessageType.Info,
                "Received custom request: hello request!"
              )
            ),
            initialized == Vector(
              InitializeResult(
                capabilities = ServerCapabilities(textDocumentSync =
                  Opt(TextDocumentSyncKind.Full)
                ),
                serverInfo = Opt(InitializeResult.ServerInfo("My first LSP!"))
              )
            )
          )
        }
    }

  end verifyEndToEnd

end EndToEndTests

transparent inline def asNotification[T <: LSPNotification](
    t: T,
    p: Payload
): Option[t.In] =
  for
    js  <- scala.util.Try(ujson.read(writeToArray(p))).toOption
    o   <- js.objOpt
    p   <- o.get("params")
    res <- scala.util.Try(upickle.default.read[t.In](p)).toOption
    if o("method").str == t.notificationMethod
  yield res
end asNotification

transparent inline def asResponse[T <: LSPRequest](
    t: T,
    p: Payload
): Option[t.Out] =
  for
    js  <- scala.util.Try(ujson.read(writeToArray(p))).toOption
    o   <- js.objOpt
    p   <- o.get("result")
    res <- scala.util.Try(upickle.default.read[t.Out](p)).toOption
    if o("id").num.toInt == 1
  yield res
end asResponse

def sendRequest[T <: LSPRequest](req: T, rm: req.In) =
  val ser1 = upickle.default.writeJs(rm)

  import ujson.*

  val value =
    Obj(
      "id"      -> Num(1),
      "jsonrpc" -> Str("2.0"),
      "params"  -> ser1,
      "method"  -> Str(req.requestMethod)
    )

  val ser = upickle.default.write(value)
  s"Content-Length: ${ser.getBytes.length}\r\n\r\n$ser"
end sendRequest

def sendNotification[T <: LSPNotification](req: T, rm: req.In) =
  val ser1 = upickle.default.writeJs(rm)

  import ujson.*

  val value =
    Obj(
      "jsonrpc" -> Str("2.0"),
      "params"  -> ser1,
      "method"  -> Str(req.notificationMethod)
    )

  val ser = upickle.default.write(value)
  s"Content-Length: ${ser.getBytes.length}\r\n\r\n$ser"
end sendNotification
