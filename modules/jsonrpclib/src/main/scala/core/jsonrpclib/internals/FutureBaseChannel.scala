package jsonrpclib

import jsonrpclib.internals._

import java.util.concurrent.atomic.AtomicLong
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.concurrent.Promise
import scala.util.Try

abstract class FutureBasedChannel(endpoints: List[Endpoint[Future]])(implicit ec: ExecutionContext)
    extends MessageDispatcher[Future] {

  override def createPromise[A](callId: CallId): Future[(Try[A] => Future[Unit], () => Future[A])] = Future.successful {
    val promise = Promise[A]()
    val fulfill: Try[A] => Future[Unit] = (a: Try[A]) => Future.successful(promise.complete(a))
    val future: () => Future[A] = () => promise.future
    (fulfill, future)
  }

  protected def storePendingCall(callId: CallId, handle: OutputMessage => Future[Unit]): Future[Unit] =
    Future.successful { val _ = pending.put(callId, handle) }
  protected def removePendingCall(callId: CallId): Future[Option[OutputMessage => Future[Unit]]] =
    Future.successful { Option(pending.remove(callId)) }
  protected def getEndpoint(method: String): Future[Option[Endpoint[Future]]] =
    Future.successful(endpointsMap.get(method))
  protected def sendMessage(message: Message): Future[Unit] = {
    sendPayload(Codec.encode(message)).map(_ => ())
  }
  protected def nextCallId(): Future[CallId] = Future.successful(CallId.NumberId(nextID.incrementAndGet()))

  private[this] val endpointsMap: Map[String, Endpoint[Future]] = endpoints.map(ep => ep.method -> ep).toMap
  private[this] val pending = new java.util.concurrent.ConcurrentHashMap[CallId, OutputMessage => Future[Unit]]
  private[this] val nextID = new AtomicLong(0L)
  // @volatile
  // private[this] var closeReason: Throwable = _

  def sendPayload(msg: Payload): Future[Unit] = ???
  def reportError(params: Option[Payload], error: ProtocolError, method: String): Future[Unit] = ???

}
