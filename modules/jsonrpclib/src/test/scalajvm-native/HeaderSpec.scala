package jsonrpclib.internals

import weaver.*
import java.io.ByteArrayInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import jsonrpclib.ProtocolError
import java.io.IOException
import java.io.UncheckedIOException

object HeaderSpec extends FunSuite:
  test("headers (all)") {
    val result = read(
      "Content-Length: 123\r",
      "Content-type: application/vscode-jsonrpc; charset=utf-8\r",
      "\r",
      "foo..."
    )
    val expected =
      Result(LSPHeaders(123, "application/vscode-jsonrpc", "utf-8"), "foo...")
    expect.same(result, Right(expected))
  }

  test("headers (only content-)") {
    val result = read(
      "Content-Length: 123\r",
      "\r",
      "foo..."
    )
    val expected =
      Result(LSPHeaders(123, "application/json", "UTF-8"), "foo...")
    expect.same(result, Right(expected))
  }

  test("no header)") {
    val result = read(
      "foo"
    )
    val expected = ProtocolError.ParseError("Could not parse LSP headers")
    expect.same(result, Left(expected))
  }

  test("missing content-length") {
    val result = read(
      "Content-type: application/vscode-jsonrpc; charset=utf-8\r",
      "\r",
      "foo..."
    )
    val expected = ProtocolError.ParseError("Missing Content-Length header")
    expect.same(result, Left(expected))
  }

  case class Result(header: LSPHeaders, rest: String)
  def read(lines: String*): Either[ProtocolError.ParseError, Result] =
    val inputStream = new ByteArrayInputStream(lines.mkString("\n").getBytes());
    try
      val maybeHeaders = LSPHeaders.readNext(inputStream)
      maybeHeaders.map { headers =>
        // Consuming the rest to check that the header parsing did not read more of the input stream than it should
        val bufferedReader =
          new BufferedReader(new InputStreamReader(inputStream))
        val iter = new Iterator[String]:
          var nextLine: String = null;

          def hasNext: Boolean =
            if nextLine != null then true;
            else
              try
                nextLine = bufferedReader.readLine();
                nextLine != null
              catch case e: IOException => throw new UncheckedIOException(e)

          def next(): String =
            if nextLine != null || hasNext then
              val line = nextLine;
              nextLine = null;
              return line;
            else throw new NoSuchElementException()
        ;
        val rest = iter.mkString("\n")
        Result(headers, rest)
      }
    finally inputStream.close()
    end try
  end read
end HeaderSpec
