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

case class Front(client: Client[IO], base: Uri, ws: WSClient[IO]):
  val wsBase = base.copy(scheme = Some(Uri.Scheme.unsafeFromString("ws")))

  def request(id: String) =
    client.expect[RawMessage](base.withPath(s"/api/raw/request/$id"))

  def request(callId: MessageId) =
    client.expect[RawMessage](base.withPath(s"/api/raw/request/${cid(callId)}"))

  def summary =
    client.expect[Summary](base.withPath(s"/api/summary"))

  def request(callId: Option[MessageId]) =
    client.expect[RawMessage](
      base.withPath(s"/api/raw/request/${cid(callId.get)}")
    )

  def response(id: String) =
    client.expect[RawMessage](base.withPath(s"/api/raw/response/$id"))

  def response(callId: MessageId) =
    client.expect[RawMessage](
      base.withPath(s"/api/raw/response/${cid(callId)}")
    )

  def response(callId: Option[MessageId]) =
    client.expect[RawMessage](
      base.withPath(s"/api/raw/response/${cid(callId.get)}")
    )

  def logs =
    client.expect[Vector[String]](base.withPath("/api/logs"))

  def allRaw =
    client.expect[Vector[RawMessage]](base.withPath(s"/api/raw/all"))

  private def cid(c: MessageId) = c match
    case MessageId.NumberId(n) => n.toString
    case MessageId.StringId(s) => s
end Front
