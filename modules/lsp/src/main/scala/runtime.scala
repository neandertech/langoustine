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

import langoustine.*

import upickle.default.*
import scala.reflect.TypeTest

import langoustine.lsp.json.*

opaque type DocumentUri = String
object DocumentUri extends OpaqueString[DocumentUri]:
  given ReadWriter[DocumentUri] =
    stringCodec.asInstanceOf[ReadWriter[DocumentUri]]
  given TypeTest[Any, DocumentUri] with
    def unapply(i: Any) =
      if i.isInstanceOf[String] then Some(i.asInstanceOf[i.type & DocumentUri])
      else None

opaque type Uri = String
object Uri extends OpaqueString[Uri]:
  given ReadWriter[Uri] = stringCodec.asInstanceOf[ReadWriter[Uri]]
  given TypeTest[Any, Uri] with
    def unapply(i: Any) =
      if i.isInstanceOf[String] then Some(i.asInstanceOf[i.type & Uri])
      else None

opaque type uinteger = Int
object uinteger extends OpaqueInt[uinteger]:
  given ReadWriter[uinteger] = intCodec.asInstanceOf[ReadWriter[uinteger]]

  given TypeTest[Any, uinteger] with
    def unapply(i: Any) =
      if i.isInstanceOf[Int] then Some(i.asInstanceOf[i.type & uinteger])
      else None

opaque type Opt[+A] = A | Null
object Opt:
  inline def empty: Opt[Nothing]    = null
  inline def apply[A](a: A): Opt[A] = a

  extension [A](opt: Opt[A])
    def toOption: Option[A] =
      if opt != null then Some(opt.asInstanceOf[A]) else None

    def fold[B](onNull: => B)(onValue: A => B): B =
      if opt != null then onValue(opt.asInstanceOf[A]) else onNull
  end extension

  given [A]: CanEqual[Opt[A], Null] = CanEqual.canEqualAny

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
