package jsonrpclib
package internals

import java.io.InputStream
import java.io.OutputStream
import java.nio.charset.StandardCharsets

// Content-Length: ...\r\n
// \r\n
private[jsonrpclib] final case class LSPHeaders(contentLength: Int, mimeType: String, charset: String)

private[jsonrpclib] object LSPHeaders {

  def readNext(input: InputStream): Either[ProtocolError.ParseError, LSPHeaders] = {
    var keepRunning = true
    var newline = false
    var headerStringBuilder: StringBuilder = null
    val headerBuilder = new Builder(
      contentLength = -1,
      mimeType = Constants.JSON_MIME_TYPE,
      charset = StandardCharsets.UTF_8.displayName()
    )
    var result: Either[ProtocolError.ParseError, LSPHeaders] = null
    while (keepRunning) {
      val c = input.read()
      if (c == -1) {
        keepRunning = false
      } else if (c == '\n') {
        if (newline) {
          // two consecutive newlines have been read, which signals the starts of the message content
          if (headerBuilder.contentLength < 0) {
            result = Left(ProtocolError.ParseError(s"Missing ${Constants.CONTENT_LENGTH_HEADER} header"))
          } else {
            result = Right(LSPHeaders(headerBuilder.contentLength, headerBuilder.mimeType, headerBuilder.charset))
          }
          keepRunning = false
        } else if (headerStringBuilder != null) {
          parseHeader(headerStringBuilder.result(), headerBuilder)
          headerStringBuilder = null
        }
        newline = true
      } else if (c != '\r') {
        // Add the input to the current header line
        if (headerStringBuilder == null) {
          headerStringBuilder = new StringBuilder()
        }
        headerStringBuilder.append(c.toChar)
        newline = false;
      }
    }
    if (result == null) {
      Left(ProtocolError.ParseError("Could not parse LSP headers"))
    } else result
  }

  def write(x: LSPHeaders, out: OutputStream): Unit = {
    val dataOutputStream = new java.io.DataOutputStream(out)
    dataOutputStream.write(s"Content-Length: ${x.contentLength}\r\n".getBytes())
    dataOutputStream.write("\r\n".getBytes())
  }

  private class Builder(var contentLength: Int, var mimeType: String, var charset: String)

  private def parseHeader(line: String, headerBuilder: Builder): Unit = line match {
    case s"Content-Length: ${integer(length)}" => headerBuilder.contentLength = length
    case s"Content-type: ${mimeType}; charset=${charset}" =>
      headerBuilder.mimeType = mimeType;
      headerBuilder.charset = charset
    case _ => sys.error(s"Invalid header: $line")
  }

  object integer {
    def unapply(string: String): Option[Int] = try { Some(string.toInt) }
    catch { case _: Throwable => None }
  }

}
