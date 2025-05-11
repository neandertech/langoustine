/*
 * Copyright 2022 Neandertech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package langoustine.lsp
package runtime

import scala.reflect.TypeTest

import langoustine.*
import langoustine.lsp.json.*
import upickle.default.*

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
