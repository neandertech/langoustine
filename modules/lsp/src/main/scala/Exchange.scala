package langoustine.lsp

import cats.MonadThrow
import java.io.InputStream
import java.io.OutputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter

import JSONRPC.*
import scala.util.Try
import java.io.CharArrayReader
import java.nio.charset.StandardCharsets
import java.nio.CharBuffer

enum Action:
  case Response(msg: ResponseMessage)
  case Shutdown
  case Ignore

class Exchange(logger: scribe.Logger):
  private enum State:
    case Start
    case ReceivedContentLength(l: Int)

  import State.*

  def bind(
      in: InputStream,
      out: OutputStream,
      build: RequestMessage | Notification => Action
  ) =
    var state: State = Start
    val reader = new BufferedReader(
      new InputStreamReader(in, StandardCharsets.US_ASCII)
    )
    val writer      = new OutputStreamWriter(out)
    var keepRunning = true
    val sb          = new StringBuilder

    def readLine() =
      var keepRunning = true
      while keepRunning do
        val next = in.read()
        keepRunning = next != '\r'
        if keepRunning then sb.append(next.toChar)
      sb.result()

    try

      while keepRunning do
        state match
          case Start =>
            val line = reader.readLine().trim

            state = line match
              case s"Content-Length: $num" =>
                logger.info(s"Expecting minimum $num characters")
                State.ReceivedContentLength(num.toInt)
              case other =>
                logger.error(s"Received $line")
                Start

          case ReceivedContentLength(l) =>
            assert(reader.read() == '\r')
            assert(reader.read() == '\n')

            val buf = Array.ofDim[Char](l)

            var acc  = 0
            var over = false

            while acc != l && !over do
              val numBytes = reader.read(buf, acc, l - acc)

              logger.info(s"Buffer: $acc, expected $l, read $numBytes")
              if numBytes != -1 then acc += numBytes
              over = numBytes == -1

            logger.info(s"Actually read: $acc bytes")

            var request: Option[JSONRPC.RequestMessage | JSONRPC.Notification] =
              None

            val str = new String(buf)
            logger.info(s"Received ${str.length} bytes: '$str'")
            try
              request = Try(
                upickle.default.read[JSONRPC.RequestMessage](str)
              ).toOption orElse
                Try(upickle.default.read[JSONRPC.Notification](str)).toOption
              request.foreach { request =>
                build(request) match
                  case Action.Response(resp) =>
                    val rendered = JSONRPC.render(resp)
                    logger.info(rendered)

                    writer.write(rendered)
                    writer.flush()
                  case Action.Shutdown =>
                    keepRunning = false
                  case Action.Ignore =>
              }
            catch
              case exc =>
                logger.error(
                  s"Failed to execute ${request}",
                  exc
                )
            end try
            state = Start
        end match

      end while
    catch
      case exc =>
        logger.error(s"Server crashed, boo :(", exc)
    end try
  end bind
end Exchange
