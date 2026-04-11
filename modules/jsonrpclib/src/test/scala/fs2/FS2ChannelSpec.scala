package jsonrpclib.fs2

import cats.effect.IO
import cats.syntax.all._
import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import fs2.Stream
import jsonrpclib._
import weaver._

import scala.concurrent.duration._

object FS2ChannelSpec extends SimpleIOSuite {

  case class IntWrapper(int: Int)
  object IntWrapper {
    implicit val jcodec: JsonValueCodec[IntWrapper] = JsonCodecMaker.make
  }

  case class CancelRequest(callId: CallId)
  object CancelRequest {
    implicit val jcodec: JsonValueCodec[CancelRequest] = JsonCodecMaker.make
  }

  def testRes(name: TestName)(run: Stream[IO, Expectations]): Unit =
    test(name)(run.compile.lastOrError.timeout(10.second))

  type ClientSideChannel = FS2Channel[IO]
  def setup(endpoints: Endpoint[IO]*) = setupAux(endpoints, None)
  def setup(cancelTemplate: CancelTemplate, endpoints: Endpoint[IO]*) = setupAux(endpoints, Some(cancelTemplate))
  def setupAux(endpoints: Seq[Endpoint[IO]], cancelTemplate: Option[CancelTemplate]): Stream[IO, ClientSideChannel] = {
    for {
      serverSideChannel <- FS2Channel[IO](cancelTemplate = cancelTemplate)
      clientSideChannel <- FS2Channel[IO](cancelTemplate = cancelTemplate)
      _ <- serverSideChannel.withEndpointsStream(endpoints)
      _ <- Stream(())
        .concurrently(clientSideChannel.output.through(serverSideChannel.input))
        .concurrently(serverSideChannel.output.through(clientSideChannel.input))
    } yield {
      clientSideChannel
    }
  }

  testRes("Round trip") {
    val endpoint: Endpoint[IO] = Endpoint[IO]("inc").simple((int: IntWrapper) => IO(IntWrapper(int.int + 1)))

    for {
      clientSideChannel <- setup(endpoint)
      remoteFunction = clientSideChannel.simpleStub[IntWrapper, IntWrapper]("inc")
      result <- remoteFunction(IntWrapper(1)).toStream
    } yield {
      expect.same(result, IntWrapper(2))
    }
  }

  testRes("Round trip (glob)") {
    val endpoint: Endpoint[IO] = Endpoint[IO]("**").simple((int: IntWrapper) => IO(IntWrapper(int.int + 1)))

    for {
      clientSideChannel <- setup(endpoint)
      remoteFunction = clientSideChannel.simpleStub[IntWrapper, IntWrapper]("inc/test")
      result <- remoteFunction(IntWrapper(1)).toStream
    } yield {
      expect.same(result, IntWrapper(2))
    }
  }

  testRes("Globs have lower priority than strict endpoints") {
    val endpoint: Endpoint[IO] = Endpoint[IO]("inc").simple((int: IntWrapper) => IO(IntWrapper(int.int + 1)))
    val globEndpoint: Endpoint[IO] =
      Endpoint[IO]("**").simple((_: IntWrapper) => IO.raiseError[IntWrapper](new Throwable("Boom")))

    for {
      clientSideChannel <- setup(globEndpoint, endpoint)
      remoteFunction = clientSideChannel.simpleStub[IntWrapper, IntWrapper]("inc")
      result <- remoteFunction(IntWrapper(1)).toStream
    } yield {
      expect.same(result, IntWrapper(2))
    }
  }

  testRes("Endpoint not mounted") {

    for {
      clientSideChannel <- setup()
      remoteFunction = clientSideChannel.simpleStub[IntWrapper, IntWrapper]("inc")
      result <- remoteFunction(IntWrapper(1)).attempt.toStream
    } yield {
      expect.same(result, Left(ErrorPayload(-32601, "Method inc not found", None)))
    }
  }

  testRes("Concurrency") {

    val endpoint: Endpoint[IO] =
      Endpoint[IO]("inc").simple { (int: IntWrapper) =>
        IO.sleep((1000 - int.int * 100).millis) >> IO(IntWrapper(int.int + 1))
      }

    for {
      clientSideChannel <- setup(endpoint)
      remoteFunction = clientSideChannel.simpleStub[IntWrapper, IntWrapper]("inc")
      timedResults <- (1 to 10).toList.map(IntWrapper(_)).parTraverse(remoteFunction).timed.toStream
    } yield {
      val (time, results) = timedResults
      expect.same(results, (2 to 11).toList.map(IntWrapper(_))) &&
      expect(time < 2.seconds)
    }
  }

  testRes("cancelation propagates") {
    val cancelTemplate = CancelTemplate.make[CancelRequest]("$/cancel", _.callId, CancelRequest(_))

    for {
      canceledPromise <- IO.deferred[IntWrapper].toStream
      endpoint: Endpoint[IO] = Endpoint[IO]("never").simple((int: IntWrapper) =>
        IO.never.as(int).onCancel(canceledPromise.complete(int).void)
      )

      clientSideChannel <- setup(cancelTemplate, endpoint)
      remoteFunction = clientSideChannel.simpleStub[IntWrapper, IntWrapper]("never")
      // Timeing-out client-call to verify cancelation progagates to server
      _ <- IO.race(remoteFunction(IntWrapper(23)), IO.sleep(1.second)).toStream
      result <- canceledPromise.get.toStream
    } yield {
      expect.same(result, IntWrapper(23))
    }
  }

}
