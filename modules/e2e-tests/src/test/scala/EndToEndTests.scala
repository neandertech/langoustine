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

package tests.e2e

import langoustine.lsp.all.*

import weaver.*

import com.github.plokhotnyuk.jsoniter_scala.core.*
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
        processId = Some(25),
        rootUri = Some(DocumentUri("hello")),
        rootPath = Some("hello"),
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

    langoustine.ChildProcess.resource[IO](process*).use { prc =>
      IO.ref("")
        .flatMap { fromStdout =>
          prc.stdout
            .through(fs2.text.utf8.decode)
            .evalMap(s => fromStdout.update(_ + s))
            .compile
            .drain
            .start *>
            fs2.Stream.emits(send.getBytes).through(prc.stdin).compile.drain *>
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
                  Some(TextDocumentSyncKind.Full)
                ),
                serverInfo = Some(InitializeResult.ServerInfo("My first LSP!"))
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
    js  <- Some(p.data)
    o   <- js.asObject
    p   <- o("params")
    res <- t.inputFromJson
      .decodeJson(p)
      .toOption
    meth <- o("method").flatMap(_.asString)
    if meth == t.notificationMethod
  yield res
end asNotification

transparent inline def asResponse[T <: LSPRequest](
    t: T,
    p: Payload
): Option[t.Out] =
  for
    js  <- Some(p.data)
    o   <- js.asObject
    p   <- o("result")
    res <- t.outputFromJson
      .decodeJson(p)
      .toOption
    id <- o("id").flatMap(_.asNumber)
    if id.toInt.contains(1)
  yield res
end asResponse

def sendRequest[T <: LSPRequest](req: T, rm: req.In) =
  val ser1 = req.inputToJson(rm)

  import io.circe.*

  val value =
    Json.obj(
      "id"      -> Json.fromInt(1),
      "jsonrpc" -> Json.fromString("2.0"),
      "params"  -> ser1,
      "method"  -> Json.fromString(req.requestMethod)
    )

  val ser = value.noSpaces

  s"Content-Length: ${ser.getBytes.length}\r\n\r\n$ser"
end sendRequest

def sendNotification[T <: LSPNotification](req: T, rm: req.In) =
  val ser1 = req.inputToJson(rm)

  import io.circe.*

  val value =
    Json.obj(
      "jsonrpc" -> Json.fromString("2.0"),
      "params"  -> ser1,
      "method"  -> Json.fromString(req.notificationMethod)
    )

  val ser = value.noSpaces
  s"Content-Length: ${ser.getBytes.length}\r\n\r\n$ser"
end sendNotification
