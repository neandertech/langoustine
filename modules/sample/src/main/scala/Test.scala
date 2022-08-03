import upickle.default.*
import scala.util.*
import langoustine.lsp.*
import requests.*
import json.*
import structures.*
import notifications as nt
import scala.concurrent.Future

// @main def hello(name: String) =
// import scribe.file.*
// val logger = scribe
//   .Logger("LSP")
//   .orphan()
//   .withHandler(writer =
//     FileWriter(
//       "langoustine.log"
//     ).flushAlways
//   )
//   .replace()

// logger.info(s"Startin $name")

// try

//   val server = ImmutableLSPBuilder
//     .create[Future](logger)
//     .handleRequest(initialize) { (in, req) =>
//       Success {
//         InitializeResult(
//           ServerCapabilities(
//             hoverProvider = Opt(true),
//             definitionProvider = Opt(true),
//             documentSymbolProvider = Opt(true),
//             textDocumentSync =
//               Opt(TextDocumentSyncOptions(openClose = Opt(true)))
//           ),
//           Opt(
//             InitializeResult
//               .ServerInfo(name = "langoustine", version = Opt("0.0.1"))
//           )
//         )
//       }
//     }
//     .handleRequest(shutdown) { (in, req) =>
//       logger.info("shutting down!")
//       Success(null)
//     }
//     .handleNotification(nt.textDocument.didOpen) { (in, rq) =>
//       logger.info(s"Opened document ${in.textDocument.uri}")
//       Success(())
//     }
//     .handleNotification(nt.initialized) { (in, rq) =>
//       logger.info(in.toString)
//       Success(())
//     }
//     .handleRequest(textDocument.hover) { (in, req) =>
//       import aliases.MarkedString
//       Success {
//         Nullable {
//           Hover(contents =
//             Vector(MarkedString("Hello"), MarkedString("World"))
//           )
//         }
//       }
//     }
//     .handleRequest(textDocument.documentSymbol) { (in, req) =>
//       import aliases.MarkedString
//       Success {
//         Nullable {
//           Hover(contents =
//             Vector(MarkedString("Hello"), MarkedString("World"))
//           )
//         }
//       }
//     }
//     .build

//   Exchange(logger).bind(
//     System.in,
//     System.out,
//     { req =>
//       val response = server(req)

//       import JSONRPC.*

//       req match
//         case rqm: RequestMessage if rqm.method == shutdown.requestMethod =>
//           Action.Shutdown
//         case _ =>
//           response match
//             case Success(Some(resp)) =>
//               Action.Response(resp)
//             case Failure(ex) =>
//               req match
//                 case r: RequestMessage =>
//                   logger.error(
//                     s"Failed to handle to '${r.method}' (id = ${r.id}",
//                     ex
//                   )
//                 case r: Notification =>
//                   logger.error(
//                     s"Failed to handle to '${r.method}' notification",
//                     ex
//                   )
//               end match
//               Action.Ignore
//             case _ => Action.Ignore
//           end match
//       end match
//     }
//   )
// catch
//   case exc =>
//     logger.error("Server failed HARD", exc)
// end try

// end hello
