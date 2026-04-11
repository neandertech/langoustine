package jsonrpclib

import com.github.plokhotnyuk.jsoniter_scala.core.JsonReader
import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.core.JsonWriter

import java.util.Base64
import jsonrpclib.Payload.Data
import jsonrpclib.Payload.NullPayload

sealed trait Payload extends Product with Serializable {
  def stripNull: Option[Payload.Data] = this match {
    case d @ Data(_) => Some(d)
    case NullPayload => None
  }
}

object Payload {
  def apply(value: Array[Byte]) = {
    if (value == null) NullPayload
    else Data(value)
  }
  final case class Data(array: Array[Byte]) extends Payload {
    override def equals(other: Any) = other match {
      case bytes: Data => java.util.Arrays.equals(array, bytes.array)
      case _           => false
    }

    override lazy val hashCode: Int = java.util.Arrays.hashCode(array)

    override def toString = Base64.getEncoder.encodeToString(array)
  }

  case object NullPayload extends Payload

  implicit val payloadJsonValueCodec: JsonValueCodec[Payload] = new JsonValueCodec[Payload] {
    def decodeValue(in: JsonReader, default: Payload): Payload = {
      Data(in.readRawValAsBytes())
    }

    def encodeValue(bytes: Payload, out: JsonWriter): Unit =
      bytes match {
        case Data(array) => out.writeRawVal(array)
        case NullPayload => out.writeNull()

      }

    def nullValue: Payload = null
  }
}
