package langoustine.lsp

import jsonrpclib.*
import scala.util.Try

import upickle.default.*

import util.chaining.*

private[lsp] object jsonrpcIntegration:
  given codec[T: Reader: Writer]: Codec[T] =
    new Codec[T]:
      override def decode(
          payload: Option[Payload]
      ): Either[ProtocolError, T] =
        System.err.println(s"Decoding $payload")
        payload
          .map(_.array)
          .flatMap { arr =>
            Try(read[T](arr, trace = true)).toOption
          }
          .toRight(ProtocolError.InvalidParams("oopsie daisy"))
          .tap(System.err.println(_))
      end decode

      override def encode(a: T): Payload =
        Payload(write(a).getBytes)

  def handlerToEndpoint[F[_]: Monadic, T <: requests.LSPRequest](req: T)(
      f: req.In => F[req.Out]
  ): Endpoint[F] =
    Endpoint(req.requestMethod).simple(f)

  def handlerToNotification[F[_]: Monadic, T <: notifications.LSPNotification](
      req: T
  )(
      f: req.In => F[Unit]
  ): Endpoint[F] = Endpoint(req.notificationMethod).notification(f)
end jsonrpcIntegration
