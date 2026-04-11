package jsonrpclib

sealed trait StubTemplate[In, Err, Out]
object StubTemplate {
  def notification[In](method: String)(implicit inCodec: Codec[In]): StubTemplate[In, Nothing, Unit] =
    NotificationTemplate[In](method, inCodec)

  def simple[In, Out](
      method: String
  )(implicit inCodec: Codec[In], outCodec: Codec[Out]): StubTemplate[In, ErrorPayload, Out] =
    RequestResponseTemplate[In, ErrorPayload, Out](method, inCodec, ErrorCodec.errorPayloadCodec, outCodec)

  final case class NotificationTemplate[In](
      method: String,
      inCodec: Codec[In]
  ) extends StubTemplate[In, Nothing, Unit]

  final case class RequestResponseTemplate[In, Err, Out](
      method: String,
      inCodec: Codec[In],
      errCodec: ErrorCodec[Err],
      outCodec: Codec[Out]
  ) extends StubTemplate[In, Err, Out]
}
