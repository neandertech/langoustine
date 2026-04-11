package jsonrpclib
package fs2

import _root_.fs2.Pipe
import _root_.fs2.Stream
import cats.Applicative
import cats.Functor
import cats.Monad
import cats.MonadThrow
import cats.effect.Fiber
import cats.effect.kernel._
import cats.effect.std.Supervisor
import cats.syntax.all._
import cats.effect.syntax.all._
import jsonrpclib.internals.MessageDispatcher

import scala.util.Try
import java.util.regex.Pattern

trait FS2Channel[F[_]] extends Channel[F] {

  def input: Pipe[F, Message, Unit]
  def inputOrBounce: Pipe[F, Either[ProtocolError, Message], Unit]
  def output: Stream[F, Message]

  def withEndpoint(endpoint: Endpoint[F])(implicit F: Functor[F]): Resource[F, FS2Channel[F]] =
    Resource.make(mountEndpoint(endpoint))(_ => unmountEndpoint(endpoint.method)).map(_ => this)

  def withEndpointStream(endpoint: Endpoint[F])(implicit F: MonadCancelThrow[F]): Stream[F, FS2Channel[F]] =
    Stream.resource(withEndpoint(endpoint))

  def withEndpoints(endpoint: Endpoint[F], rest: Endpoint[F]*)(implicit F: Monad[F]): Resource[F, FS2Channel[F]] =
    withEndpoints(endpoint +: rest)

  def withEndpoints(endpoints: Seq[Endpoint[F]])(implicit F: Monad[F]): Resource[F, FS2Channel[F]] =
    endpoints.toList.traverse_(withEndpoint).as(this)

  def withEndpointStream(endpoint: Endpoint[F], rest: Endpoint[F]*)(implicit
      F: MonadCancelThrow[F]
  ): Stream[F, FS2Channel[F]] =
    Stream.resource(withEndpoints(endpoint, rest: _*))

  def withEndpointsStream(endpoints: Seq[Endpoint[F]])(implicit F: MonadCancelThrow[F]): Stream[F, FS2Channel[F]] =
    Stream.resource(withEndpoints(endpoints))

}

object FS2Channel {

  def apply[F[_]: Concurrent](
      bufferSize: Int = 2048,
      cancelTemplate: Option[CancelTemplate] = None
  ): Stream[F, FS2Channel[F]] = {
    for {
      supervisor <- Stream.resource(Supervisor[F])
      ref <- Ref[F].of(State[F](Map.empty, Map.empty, Map.empty, Vector.empty, 0)).toStream
      queue <- cats.effect.std.Queue.bounded[F, Message](bufferSize).toStream
      impl = new Impl(queue, ref, supervisor, cancelTemplate)

      // Creating a bespoke endpoint to receive cancelation requests
      maybeCancelEndpoint: Option[Endpoint[F]] = cancelTemplate.map { ct =>
        implicit val codec: Codec[ct.C] = ct.codec
        Endpoint[F](ct.method).notification[ct.C] { request =>
          val callId = ct.toCallId(request)
          impl.cancel(callId)
        }
      }
      // mounting the cancelation endpoint
      _ <- maybeCancelEndpoint.traverse_(ep => impl.mountEndpoint(ep)).toStream
    } yield impl
  }

  private case class State[F[_]](
      runningCalls: Map[CallId, Fiber[F, Throwable, Unit]],
      pendingCalls: Map[CallId, OutputMessage => F[Unit]],
      endpoints: Map[String, Endpoint[F]],
      globEndpoints: Vector[(Pattern, Endpoint[F])],
      counter: Long
  ) {
    def nextCallId: (State[F], CallId) = (this.copy(counter = counter + 1), CallId.NumberId(counter))
    def storePendingCall(callId: CallId, handle: OutputMessage => F[Unit]): State[F] =
      this.copy(pendingCalls = pendingCalls + (callId -> handle))
    def removePendingCall(callId: CallId): (State[F], Option[OutputMessage => F[Unit]]) = {
      val result = pendingCalls.get(callId)
      (this.copy(pendingCalls = pendingCalls.removed(callId)), result)
    }
    def mountEndpoint(endpoint: Endpoint[F]): Either[ConflictingMethodError, State[F]] = {
      import endpoint.method
      if (method.contains("*")) {
        val parts = method
          .split("\\*", -1)
          .map { // Don't discard trailing empty string, if any.
            case ""  => ""
            case str => Pattern.quote(str)
          }
        val glob = Pattern.compile(parts.mkString(".*"))
        Right(this.copy(globEndpoints = globEndpoints :+ (glob -> endpoint)))
      } else {
        endpoints.get(endpoint.method) match {
          case None    => Right(this.copy(endpoints = endpoints + (endpoint.method -> endpoint)))
          case Some(_) => Left(ConflictingMethodError(endpoint.method))
        }
      }
    }
    def getEndpoint(method: String): Option[Endpoint[F]] = {
      endpoints.get(method).orElse(globEndpoints.find(_._1.matcher(method).matches()).map(_._2))
    }
    def removeEndpoint(method: String): State[F] =
      copy(endpoints = endpoints.removed(method))

    def addRunningCall(callId: CallId, fiber: Fiber[F, Throwable, Unit]): State[F] =
      copy(runningCalls = runningCalls + (callId -> fiber))

    def removeRunningCall(callId: CallId): State[F] =
      copy(runningCalls = runningCalls - callId)
  }

  private class Impl[F[_]](
      private val queue: cats.effect.std.Queue[F, Message],
      private val state: Ref[F, FS2Channel.State[F]],
      supervisor: Supervisor[F],
      maybeCancelTemplate: Option[CancelTemplate]
  )(implicit F: Concurrent[F])
      extends MessageDispatcher[F]
      with FS2Channel[F] {

    def output: Stream[F, Message] = Stream.fromQueueUnterminated(queue)
    def inputOrBounce: Pipe[F, Either[ProtocolError, Message], Unit] = _.evalMap {
      case Left(error)    => sendProtocolError(error)
      case Right(message) => handleReceivedMessage(message)
    }
    def input: Pipe[F, Message, Unit] = _.evalMap(handleReceivedMessage)

    def mountEndpoint(endpoint: Endpoint[F]): F[Unit] = state
      .modify(s =>
        s.mountEndpoint(endpoint) match {
          case Left(error)  => (s, MonadThrow[F].raiseError[Unit](error))
          case Right(value) => (value, Applicative[F].unit)
        }
      )
      .flatMap(identity)

    def unmountEndpoint(method: String): F[Unit] = state.update(_.removeEndpoint(method))

    protected[fs2] def cancel(callId: CallId): F[Unit] = state.get.map(_.runningCalls.get(callId)).flatMap {
      case None        => F.unit
      case Some(fiber) => fiber.cancel
    }

    protected def background[A](maybeCallId: Option[CallId], fa: F[A]): F[Unit] =
      maybeCallId match {
        case None => supervisor.supervise(fa).void
        case Some(callId) =>
          val runAndClean = fa.void.guarantee(state.update(_.removeRunningCall(callId)))
          supervisor.supervise(runAndClean).flatMap { fiber =>
            state.update(_.addRunningCall(callId, fiber))
          }
      }
    protected def reportError(params: Option[Payload], error: ProtocolError, method: String): F[Unit] = ???
    protected def getEndpoint(method: String): F[Option[Endpoint[F]]] = state.get.map(_.getEndpoint(method))
    protected def sendMessage(message: Message): F[Unit] = queue.offer(message)

    protected def nextCallId(): F[CallId] = state.modify(_.nextCallId)
    protected def createPromise[A](callId: CallId): F[(Try[A] => F[Unit], () => F[A])] = Deferred[F, Try[A]].map {
      promise =>
        def compile(trya: Try[A]): F[Unit] = promise.complete(trya).void
        def get(): F[A] = promise.get.flatMap(_.liftTo[F])
        (compile(_), () => get().onCancel(cancelRequest(callId)))
    }
    protected def storePendingCall(callId: CallId, handle: OutputMessage => F[Unit]): F[Unit] =
      state.update(_.storePendingCall(callId, handle))
    protected def removePendingCall(callId: CallId): F[Option[OutputMessage => F[Unit]]] =
      state.modify(_.removePendingCall(callId))

    private val cancelRequest: CallId => F[Unit] = maybeCancelTemplate
      .map { cancelTemplate =>
        val stub = notificationStub(cancelTemplate.method)(cancelTemplate.codec)
        stub.compose(cancelTemplate.fromCallId(_))
      }
      .getOrElse((_: CallId) => F.unit)
  }
}
