import langoustine.lsp.*
import structures.*

import upickle.default.*
import langoustine.ImmutableLSPBuilder
import scala.util.*
import langoustine.JSONRPC
import java.io.BufferedReader
import java.io.InputStreamReader
import langoustine.lsp.requests.initialize
import requests.*
import java.io.OutputStream
import cats.MonadThrow.apply
import java.io.InputStream
import cats.MonadThrow

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
    .handler(codeLens.resolve) { (in, req) =>
      println(in)
      Success(in)
    }
    .handler(initialize) { (in, req) =>
      logger.info(in.toString)
      Failure(new RuntimeException("hello"))
    }
    .build

  Exchange(logger).bind(System.in, System.out, req => println(server(req)))

end hello
