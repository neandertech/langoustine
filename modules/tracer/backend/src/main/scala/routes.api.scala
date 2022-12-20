package langoustine.tracer

import cats.effect.*
import org.http4s.*
import org.http4s.headers.*
import org.http4s.server.*
import org.http4s.server.websocket.*
import org.http4s.websocket.*
import org.http4s.dsl.io.*
import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*
import concurrent.duration.*
import org.typelevel.ci.*
import cats.syntax.all.*

object SnapshotNameMatcher
    extends OptionalQueryParamDecoderMatcher[String]("name")

private object codecs:
  given msgCodec: JsonValueCodec[Vector[LspMessage]] = JsonCodecMaker.make
  given rawCodec: JsonValueCodec[Vector[RawMessage]] = JsonCodecMaker.make

  given JsonValueCodec[Vector[String]] = JsonCodecMaker.make
  given jsonEncoder[T: JsonValueCodec]: EntityEncoder[IO, T] =
    EntityEncoder
      .byteArrayEncoder[IO]
      .contramap((data: T) => writeToArray(data))
      .withContentType(
        `Content-Type`(MediaType.application.json, Some(Charset.`UTF-8`))
      )

  given jsonDecoder[T: JsonValueCodec]: EntityDecoder[IO, T] =
    EntityDecoder.byteArrayDecoder[IO].map(readFromArray[T](_))
end codecs

def api(
    wbs: WebSocketBuilder2[IO],
    state: State,
    summary: Summary
) =
  import state.*
  import codecs.given
  val apiRoutes = HttpRoutes.of[IO] {
    case GET -> Root / "summary" =>
      Ok(summary)

    case GET -> Root / "ws" / "events" =>
      val continuous =
        messages.discrete
          .as(TracerEvent.Update)
          .merge(messages.discrete.as(TracerEvent.Update))
          .merge(ch.stream.map(l => TracerEvent.LogLine(l)))

      val out = fs2.Stream.eval(logBuf.get).map(TracerEvent.LogLines(_))

      val updates =
        (out ++ continuous)
          .map(ev =>
            WebSocketFrame.Text(writeToStringReentrant[TracerEvent](ev))
          )

      val pings =
        fs2.Stream.repeatEval(IO(WebSocketFrame.Ping())).metered(1.second)

      wbs
        .withFilterPingPongs(false)
        .build(updates.merge(pings), _.map(_ => ()))

    case GET -> Root / "all" =>
      messages.get.map(_.sortBy(_.timestamp).map(_.decoded)).flatMap(Ok(_))

    case GET -> Root / "logs" =>
      logBuf.get.flatMap(Ok(_))

    case GET -> Root / "raw" / "all" =>
      messages.get.map(_.sortBy(_.timestamp).map(_.raw)).flatMap(Ok(_))

    case GET -> Root / "snapshot" :? SnapshotNameMatcher(nameMaybe) =>
      given JsonValueCodec[Vector[ReceivedMessage]] = JsonCodecMaker.make

      val filename = nameMaybe
        .getOrElse("langoustine-tracer-snapshot")
        + ".jsonl.gz"

      messages.get.map(_.sortBy(_.timestamp)).flatMap { received =>
        val bytes = fs2.Stream
          .emits(received)
          .covary[IO]
          .evalMap(m => IO(writeToStringReentrant(m)))
          .intersperse("\n")
          .through(fs2.text.utf8.encode)
          .through(fs2.compression.Compression[IO].gzip())

        Ok(
          bytes,
          org.http4s.headers.`Content-Disposition`(
            s"attachment",
            Map(ci"filename" -> filename)
          )
        )
      }

    case GET -> Root / "raw" / "request" / id =>
      val asLong = id.toLongOption.map(MessageId.NumberId.apply)
      val asStr  = MessageId.StringId(id)

      inline def idMatches(idOpt: Option[MessageId]) =
        (asLong == idOpt || idOpt.contains(asStr))

      messages.get.flatMap { messages =>
        val found = messages.collectFirst {
          case rm if idMatches(rm.raw.id) && rm.raw.method.nonEmpty =>
            rm.raw
        }

        found match
          case Some(rm) => Ok(rm)
          case None     => NotFound()
      }

    case GET -> Root / "raw" / "response" / id =>
      val asLong = id.toLongOption.map(MessageId.NumberId.apply)
      val asStr  = MessageId.StringId(id)

      inline def idMatches(idOpt: Option[MessageId]) =
        (asLong == idOpt || idOpt.contains(asStr))

      messages.get.flatMap { messages =>
        val found = messages.collectFirst {
          case rm if idMatches(rm.raw.id) && rm.raw.method.isEmpty =>
            rm.raw
        }

        found match
          case Some(rm) => Ok(rm)
          case None     => NotFound()
      }

    case GET -> Root / "raw" / "notification" / id =>
      val asStr = MessageId.StringId(id)

      inline def idMatches(idOpt: Option[MessageId]) =
        idOpt.contains(asStr)

      messages.get.flatMap { messages =>
        val found = messages.collectFirst {
          case rm if idMatches(rm.raw.id) => rm.raw
        }

        found match
          case Some(rm) => Ok(rm)
          case None     => NotFound()
      }
  }

  Router("/api" -> apiRoutes) <+> Static.routes
end api
