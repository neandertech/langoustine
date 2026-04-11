package jsonrpclib

import com.github.plokhotnyuk.jsoniter_scala.core._

trait Codec[A] {

  def encode(a: A): Payload
  def decode(payload: Option[Payload]): Either[ProtocolError, A]

}

object Codec {

  def encode[A](a: A)(implicit codec: Codec[A]): Payload = codec.encode(a)
  def decode[A](payload: Option[Payload])(implicit codec: Codec[A]): Either[ProtocolError, A] = codec.decode(payload)

  implicit def fromJsonCodec[A](implicit jsonCodec: JsonValueCodec[A]): Codec[A] = new Codec[A] {
    def encode(a: A): Payload = {
      Payload(writeToArray(a))
    }

    def decode(payload: Option[Payload]): Either[ProtocolError, A] = {
      try {
        payload match {
          case Some(Payload.Data(payload)) => Right(readFromArray(payload))
          case Some(Payload.NullPayload)   => Right(readFromArray(nullArray))
          case None                        => Left(ProtocolError.ParseError("Expected to decode a payload"))
        }
      } catch { case e: JsonReaderException => Left(ProtocolError.ParseError(e.getMessage())) }
    }
  }

  private val nullArray = "null".getBytes()

}
