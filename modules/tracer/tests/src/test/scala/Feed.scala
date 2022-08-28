import weaver.*
import cats.effect.*
import jsonrpclib.*
import java.util.Base64
import langoustine.tracer.RawMessage
import _root_.fs2.concurrent.Channel as Chan
import _root_.fs2.*
import cats.syntax.all.*
import langoustine.tracer.*
import com.github.plokhotnyuk.jsoniter_scala.core.*
import org.http4s.client.*
import TracerServer.{*, given}
import org.http4s.Uri
import org.http4s.ember.client.*
import org.http4s.client.websocket.*

case class Feed(
    in: Chan[IO, Chunk[Byte]],
    out: Chan[IO, Chunk[Byte]],
    err: Chan[IO, Chunk[Byte]],
    front: Front,
    genId: IO[Option[MessageId]]
):
  def send(f: this.type => Chan[IO, Chunk[Byte]], rm: RawMessage) =
    val ser = writeToStringReentrant(rm)
    val str = s"Content-Length: ${ser.getBytes.length}\r\n\r\n$ser"

    f(this).send(Chunk.array(str.getBytes())).void

  def send(f: this.type => Chan[IO, Chunk[Byte]], str: String) =
    f(this).send(Chunk.array(str.getBytes))

  def sendLine(f: this.type => Chan[IO, Chunk[Byte]], str: String) =
    f(this).send(Chunk.array((str + "\n").getBytes))
end Feed
