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
import cats.data.NonEmptyList

import com.comcast.ip4s.*

abstract class ServerSpec extends IOSuite:
  type Res = Feed
  override def sharedResource: Resource[cats.effect.IO, Res] =
    val create = Chan.synchronous[IO, Chunk[Byte]]

    Resource.eval((create, create, create).parTupled).flatMap {
      case (in, out, err) =>
        val server = TracerServer
          .create(
            in = in.stream.unchunks,
            out = out.stream.unchunks,
            err = err.stream.unchunks
          )
          .runResource(
            Config.create(NonEmptyList.of("echo")),
            Summary(System.getProperty("user.dir"), List("echo", "world"))
          )

        import org.http4s.jdkhttpclient.*

        val client =
          JdkHttpClient.simple[IO]

        val ws = JdkWSClient.simple[IO]

        val idState = Resource.eval(IO.ref(0L))

        (server, client, idState, ws).parMapN { case (s, c, ref, w) =>
          val genId =
            ref.getAndUpdate(_ + 1).map(MessageId.NumberId.apply).map(Some(_))

          Feed(in, out, err, Front(c, s.baseUri, w), genId)
        }
    }
  end sharedResource
end ServerSpec
