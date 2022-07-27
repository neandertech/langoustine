
import upickle.default.*
import langoustine.ImmutableLSPBuilder
import scala.util.*
import langoustine.lsp.*
import langoustine.JSONRPC
import requests.*
import json.*
import structures.*

@main def hello(name: String) =
  import scribe.file.*

  val logger = scribe
    .Logger("LSP")
    .orphan()
    .withHandler(writer =
      FileWriter(
        "langoustine.log"
      ).flushAlways
    )
    .replace()

  logger.info(s"Starting $name")

  val server = ImmutableLSPBuilder
    .create[Try](logger)
    .handler(initialize) { (in, req) =>
      Success {
        InitializeResult(
          ServerCapabilities(
            hoverProvider = Opt(true)
          ),
          Opt(
            InitializeResult
              .ServerInfo(name = "langoustine", version = Opt("0.0.1"))
          )
        )
      }
    }
    .handler(textDocument.hover) { (in, req) =>
      import aliases.MarkedString
      Success {
        Nullable {
          Hover(contents = Vector(MarkedString("Hello"), MarkedString("World")))
        }
      }
    }
    .build

  Exchange(logger).bind(
    System.in,
    System.out,
    req =>
      if req.method == "shutdown" then Action.Shutdown
      else
        server(req) match
          case Success(resp) =>
            println(resp)
            Action.Response(resp)
          case Failure(ex) =>
            logger.error(
              s"Failed to respond to ${req.method} (id = ${req.id}",
              ex
            )
            Action.Ignore
  )

end hello
