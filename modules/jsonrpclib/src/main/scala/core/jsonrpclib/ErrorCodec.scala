package jsonrpclib

trait ErrorCodec[E] {

  def encode(a: E): ErrorPayload
  def decode(error: ErrorPayload): Either[ProtocolError, E]

}

object ErrorCodec {
  implicit val errorPayloadCodec: ErrorCodec[ErrorPayload] = new ErrorCodec[ErrorPayload] {
    def encode(a: ErrorPayload): ErrorPayload = a
    def decode(error: ErrorPayload): Either[ProtocolError, ErrorPayload] = Right(error)
  }
}
