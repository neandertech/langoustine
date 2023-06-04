package langoustine.lsp
package runtime

import langoustine.*

import upickle.default.*
import scala.reflect.TypeTest

import langoustine.lsp.json.*

opaque type Opt[+A] = A | Null
object Opt:
  val empty: Opt[Nothing] = null

  inline def apply[A](a: A): Opt[A] = a

  inline def fromOption[A](o: Option[A]): Opt[A] =
    o.orNull.asInstanceOf[Opt[A]]

  inline def unapply[A](o: Opt[A]): Option[A] =
    if o != null then Some(o.asInstanceOf[o.type & A])
    else None

  extension [A](o: Opt[A])
    inline def toOption = if o == empty then None else Some(o.asInstanceOf[A])

  given [A]: TypeTest[Opt[A], A] with
    inline def unapply(o: Opt[A]): Option[o.type & A] =
      if o != null then Some(o.asInstanceOf[o.type & A])
      else None

  given [A]: CanEqual[Opt[A], Opt[A]] = CanEqual.canEqualAny
  given [A]: CanEqual[Opt[A], Null]   = CanEqual.canEqualAny

  given [A](using
      rd: Reader[A]
  ): Reader[Opt[A]] =
    jsReader.map[Opt[A]] {
      case ujson.Null => empty
      case other      => upickle.default.read(other)(using rd)
    }

  given [A](using
      wt: Writer[A]
  ): Writer[Opt[A]] =
    jsWriter.comap[Opt[A]] {
      case other => upickle.default.writeJs(other.asInstanceOf[A])(using wt)
      case null  => ujson.Null
    }

end Opt
