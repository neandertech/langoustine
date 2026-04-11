package jsonrpclib

import com.github.plokhotnyuk.jsoniter_scala.core._
import scala.annotation.switch

sealed trait CallId
object CallId {
  final case class NumberId(long: Long) extends CallId
  final case class StringId(string: String) extends CallId
  case object NullId extends CallId

  implicit val callIdRW: JsonValueCodec[CallId] = new JsonValueCodec[CallId] {
    def decodeValue(in: JsonReader, default: CallId): CallId = {
      val nt = in.nextToken()

      (nt: @switch) match {
        case 'n' => in.readNullOrError(default, "expected null")
        case '"' => in.rollbackToken(); StringId(in.readString(null))
        case _   => in.rollbackToken(); NumberId(in.readLong())

      }
    }

    def encodeValue(x: CallId, out: JsonWriter): Unit = x match {
      case NumberId(long)   => out.writeVal(long)
      case StringId(string) => out.writeVal(string)
      case NullId           => out.writeNull()
    }

    def nullValue: CallId = CallId.NullId
  }
}
