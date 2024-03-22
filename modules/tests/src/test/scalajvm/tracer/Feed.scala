package tests.tracer

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
import org.http4s.client.websocket.*
import _root_.fs2.concurrent.Topic

case class Feed(
    in: Topic[IO, Message],
    out: Topic[IO, Message],
    err: Topic[IO, Chunk[Byte]],
    front: Front,
    genId: IO[Option[CallId]]
):
  def send(f: this.type => Topic[IO, Message], rm: RawMessage) =
    val ser = writeToStringReentrant(rm)

    rm.toMessage match
      case None =>
        IO.raiseError(
          new RuntimeException(
            s"Failed to convert raw message `$rm` to jsonrpclib Message"
          )
        )
      case Some(value) =>
        f(this).publish1(value)
    end match
  end send

  def send(f: this.type => Topic[IO, Chunk[Byte]], str: String) =
    f(this).publish1(Chunk.array(str.getBytes))

  def sendLine(f: this.type => Topic[IO, Chunk[Byte]], str: String) =
    f(this).publish1(Chunk.array((str + "\n").getBytes))
end Feed
