package jsonrpclib

sealed trait Endpoint[F[_]] {
  def method: String
}

object Endpoint {

  type MethodPattern = String
  type Method = String

  def apply[F[_]](method: Method): PartiallyAppliedEndpoint[F] = new PartiallyAppliedEndpoint[F](method)

  class PartiallyAppliedEndpoint[F[_]](method: MethodPattern) {
    def apply[In, Err, Out](
        run: In => F[Either[Err, Out]]
    )(implicit inCodec: Codec[In], errCodec: ErrorCodec[Err], outCodec: Codec[Out]): Endpoint[F] =
      RequestResponseEndpoint(method, (_: InputMessage, in: In) => run(in), inCodec, errCodec, outCodec)

    def full[In, Err, Out](
        run: (InputMessage, In) => F[Either[Err, Out]]
    )(implicit inCodec: Codec[In], errCodec: ErrorCodec[Err], outCodec: Codec[Out]): Endpoint[F] =
      RequestResponseEndpoint(method, run, inCodec, errCodec, outCodec)

    def simple[In, Out](
        run: In => F[Out]
    )(implicit F: Monadic[F], inCodec: Codec[In], outCodec: Codec[Out]) =
      apply[In, ErrorPayload, Out](in =>
        F.doFlatMap(F.doAttempt(run(in))) {
          case Left(error)  => F.doPure(Left(ErrorPayload(0, error.getMessage(), None)))
          case Right(value) => F.doPure(Right(value))
        }
      )

    def notification[In](run: In => F[Unit])(implicit inCodec: Codec[In]): Endpoint[F] =
      NotificationEndpoint(method, (_: InputMessage, in: In) => run(in), inCodec)

    def notificationFull[In](run: (InputMessage, In) => F[Unit])(implicit inCodec: Codec[In]): Endpoint[F] =
      NotificationEndpoint(method, run, inCodec)

  }

  final case class NotificationEndpoint[F[_], In](
      method: MethodPattern,
      run: (InputMessage, In) => F[Unit],
      inCodec: Codec[In]
  ) extends Endpoint[F]

  final case class RequestResponseEndpoint[F[_], In, Err, Out](
      method: Method,
      run: (InputMessage, In) => F[Either[Err, Out]],
      inCodec: Codec[In],
      errCodec: ErrorCodec[Err],
      outCodec: Codec[Out]
  ) extends Endpoint[F]

}
