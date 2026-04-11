package jsonrpclib
package internals

import jsonrpclib.Endpoint.NotificationEndpoint
import jsonrpclib.Endpoint.RequestResponseEndpoint
import jsonrpclib.OutputMessage.ErrorMessage
import jsonrpclib.OutputMessage.ResponseMessage
import scala.util.Try

private[jsonrpclib] abstract class MessageDispatcher[F[_]](implicit F: Monadic[F]) extends Channel.MonadicChannel[F] {

  import F._

  protected def background[A](maybeCallId: Option[CallId], fa: F[A]): F[Unit]
  protected def reportError(params: Option[Payload], error: ProtocolError, method: String): F[Unit]
  protected def getEndpoint(method: String): F[Option[Endpoint[F]]]
  protected def sendMessage(message: Message): F[Unit]
  protected def nextCallId(): F[CallId]
  protected def createPromise[A](callId: CallId): F[(Try[A] => F[Unit], () => F[A])]
  protected def storePendingCall(callId: CallId, handle: OutputMessage => F[Unit]): F[Unit]
  protected def removePendingCall(callId: CallId): F[Option[OutputMessage => F[Unit]]]

  def notificationStub[In](method: String)(implicit inCodec: Codec[In]): In => F[Unit] = { (input: In) =>
    val encoded = inCodec.encode(input)
    val message = InputMessage.NotificationMessage(method, Some(encoded))
    sendMessage(message)
  }

  def stub[In, Err, Out](
      method: String
  )(implicit inCodec: Codec[In], errCodec: ErrorCodec[Err], outCodec: Codec[Out]): In => F[Either[Err, Out]] = {
    (input: In) =>
      val encoded = inCodec.encode(input)
      doFlatMap(nextCallId()) { callId =>
        val message = InputMessage.RequestMessage(method, callId, Some(encoded))
        doFlatMap(createPromise[Either[Err, Out]](callId)) { case (fulfill, future) =>
          val pc = createPendingCall(errCodec, outCodec, fulfill)
          doFlatMap(storePendingCall(callId, pc))(_ => doFlatMap(sendMessage(message))(_ => future()))
        }
      }
  }

  protected[jsonrpclib] def handleReceivedMessage(message: Message): F[Unit] = {
    message match {
      case im: InputMessage =>
        doFlatMap(getEndpoint(im.method)) {
          case Some(ep) => background(im.maybeCallId, executeInputMessage(im, ep))
          case None =>
            im.maybeCallId match {
              case None =>
                // notification : do nothing
                doPure(())
              case Some(callId) =>
                val error = ProtocolError.MethodNotFound(im.method)
                sendProtocolError(callId, error)
            }
        }
      case om: OutputMessage =>
        doFlatMap(removePendingCall(om.callId)) {
          case Some(pendingCall) => pendingCall(om)
          case None              => doPure(()) // TODO do something
        }
    }
  }

  protected def sendProtocolError(callId: CallId, pError: ProtocolError): F[Unit] =
    sendMessage(OutputMessage.errorFrom(callId, pError))
  protected def sendProtocolError(pError: ProtocolError): F[Unit] =
    sendProtocolError(CallId.NullId, pError)

  private def executeInputMessage(input: InputMessage, endpoint: Endpoint[F]): F[Unit] = {
    (input, endpoint) match {
      case (InputMessage.NotificationMessage(_, params), ep: NotificationEndpoint[F, in]) =>
        ep.inCodec.decode(params) match {
          case Right(value) => ep.run(input, value)
          case Left(value)  => reportError(params, value, ep.method)
        }
      case (InputMessage.RequestMessage(_, callId, params), ep: RequestResponseEndpoint[F, in, err, out]) =>
        ep.inCodec.decode(params) match {
          case Right(value) =>
            doFlatMap(ep.run(input, value)) {
              case Right(data) =>
                val responseData = ep.outCodec.encode(data)
                sendMessage(OutputMessage.ResponseMessage(callId, responseData))
              case Left(error) =>
                val errorPayload = ep.errCodec.encode(error)
                sendMessage(OutputMessage.ErrorMessage(callId, errorPayload))
            }
          case Left(pError) =>
            sendProtocolError(callId, pError)
        }
      case (InputMessage.NotificationMessage(_, _), ep: RequestResponseEndpoint[F, in, err, out]) =>
        val message = s"This ${ep.method} endpoint cannot process notifications, request is missing callId"
        val pError = ProtocolError.InvalidRequest(message)
        sendProtocolError(pError)
      case (InputMessage.RequestMessage(_, _, _), ep: NotificationEndpoint[F, in]) =>
        val message = s"This ${ep.method} endpoint expects notifications and cannot return a result"
        val pError = ProtocolError.InvalidRequest(message)
        sendProtocolError(pError)
    }
  }

  private def createPendingCall[Err, Out](
      errCodec: ErrorCodec[Err],
      outCodec: Codec[Out],
      fulfill: Try[Either[Err, Out]] => F[Unit]
  ): OutputMessage => F[Unit] = { (message: OutputMessage) =>
    message match {
      case ErrorMessage(_, errorPayload) =>
        errCodec.decode(errorPayload) match {
          case Left(_)      => fulfill(scala.util.Failure(errorPayload))
          case Right(value) => fulfill(scala.util.Success(Left(value)))
        }
      case ResponseMessage(_, data) =>
        outCodec.decode(Some(data)) match {
          case Left(decodeError) => fulfill(scala.util.Failure(decodeError))
          case Right(value)      => fulfill(scala.util.Success(Right(value)))
        }
    }
  }

}
