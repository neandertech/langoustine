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

import runtime.*

import upickle.default.*
import scala.reflect.*

trait Bijection[A, T]:
  def apply(a: A): T
  def reverse(a: T): A
  def domain: Set[A]

private[lsp] trait IntEnum[T](using ev: T =:= Int):
  private val intCodec    = upickle.default.readwriter[Int]
  given reader: Reader[T] = intCodec.asInstanceOf[Reader[T]]
  given writer: Writer[T] = intCodec.asInstanceOf[Writer[T]]

  protected def ALL: Set[T]

  given Bijection[T, Int] with
    def apply(a: T): Int = ev.apply(a)
    def reverse(a: Int): T =
      ev.flip.apply(a)
    lazy val domain = ALL

  given Typeable[T] with
    def unapply(s: Any): Option[s.type & T] =
      s match
        case c: Int => Some(c.asInstanceOf[s.type & T])
        case _      => Option.empty

  protected inline def entry(n: Int): T =
    n.asInstanceOf[T]

  extension (t: T) inline def raw: Int = t.asInstanceOf[Int]
end IntEnum

private[lsp] trait StringEnum[T](using ev: T =:= String):
  private val stringCodec = upickle.default.readwriter[String]
  given reader: Reader[T] = stringCodec.asInstanceOf[Reader[T]]
  given writer: Writer[T] = stringCodec.asInstanceOf[Writer[T]]
  protected def ALL: Set[T]

  given Bijection[T, String] with
    def apply(a: T): String   = ev.apply(a)
    def reverse(a: String): T = ev.flip.apply(a)
    lazy val domain           = ALL

  given Typeable[T] with
    def unapply(s: Any): Option[s.type & T] =
      s match
        case c: String => Some(c.asInstanceOf[s.type & T])
        case _         => Option.empty

  protected inline def entry(n: String): T =
    n.asInstanceOf[T]

  extension (t: T) inline def raw: String = t.asInstanceOf[String]
end StringEnum

private[lsp] trait UIntEnum[T](using ev: T =:= uinteger):
  private val intCodec = upickle.default.readwriter[Int]

  given reader: Reader[T] = intCodec.asInstanceOf[Reader[T]]
  given writer: Writer[T] = intCodec.asInstanceOf[Writer[T]]

  protected def ALL: Set[T]

  given Bijection[T, uinteger] with
    def apply(a: T): uinteger = ev.apply(a)
    def reverse(a: uinteger): T =
      ev.flip.apply(a)
    lazy val domain = ALL

  given Typeable[T] with
    def unapply(s: Any): Option[s.type & T] =
      s match
        case c: uinteger => Some(c.asInstanceOf[s.type & T])
        case _           => Option.empty

  protected inline def entry(n: Int): T =
    uinteger(n).asInstanceOf[T]

  extension (t: T)
    inline def raw: uinteger = t.asInstanceOf[uinteger]
    inline def rawInt: Int   = t.asInstanceOf[Int]
end UIntEnum
