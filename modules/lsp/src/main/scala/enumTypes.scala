package langoustine.lsp

import upickle.default.*
import scala.reflect.*

trait IntEnum[T](using ev: T =:= Int):
  private val intCodec    = upickle.default.readwriter[Int]
  given reader: Reader[T] = intCodec.asInstanceOf[Reader[T]]
  given writer: Writer[T] = intCodec.asInstanceOf[Writer[T]]

  given Typeable[T] with
    def unapply(s: Any): Option[s.type & T] =
      s match
        case c: Int => Some(c.asInstanceOf[s.type & T])
        case _      => Option.empty

  protected inline def entry(n: Int): T =
    n.asInstanceOf[T]
end IntEnum

trait StringEnum[T](using ev: T =:= String):
  private val stringCodec = upickle.default.readwriter[String]
  given reader: Reader[T] = stringCodec.asInstanceOf[Reader[T]]
  given writer: Writer[T] = stringCodec.asInstanceOf[Writer[T]]

  given Typeable[T] with
    def unapply(s: Any): Option[s.type & T] =
      s match
        case c: String => Some(c.asInstanceOf[s.type & T])
        case _         => Option.empty

  protected inline def entry(n: String): T =
    n.asInstanceOf[T]
end StringEnum

trait UIntEnum[T](using ev: T =:= RuntimeBase.uinteger):
  private val intCodec = upickle.default.readwriter[Int]

  given reader: Reader[T] = intCodec.asInstanceOf[Reader[T]]
  given writer: Writer[T] = intCodec.asInstanceOf[Writer[T]]

  given Typeable[T] with
    def unapply(s: Any): Option[s.type & T] =
      s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & T])
        case _                       => Option.empty

  protected inline def entry(n: Int): T =
    RuntimeBase.uinteger(n).asInstanceOf[T]
end UIntEnum
