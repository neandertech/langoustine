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

package langoustine.meta

trait BasicallyTheSame[A, T]:
  def apply(a: A): T
  def reverse(t: T): A

trait TotalWrapper[Newtype, Impl](using ev: Newtype =:= Impl):
  def raw(a: Newtype): Impl   = ev.apply(a)
  def apply(s: Impl): Newtype = ev.flip.apply(s)

  inline def unapply(s: Impl): Newtype = apply(s)
  given BasicallyTheSame[Impl, Newtype] with
    def apply(a: Impl)      = ev.flip(a)
    def reverse(a: Newtype) = ev(a)

  extension (a: Newtype)
    inline def value = raw(a)
    inline def into[X](inline other: TotalWrapper[X, Impl]): X =
      other.apply(raw(a))
    inline def map(inline f: Impl => Impl): Newtype = apply(f(raw(a)))
end TotalWrapper

inline given [A, T](using
    bts: BasicallyTheSame[T, A],
    ord: Ordering[A]
): Ordering[T] =
  Ordering.by(bts.apply)

trait OpaqueString[A](using A =:= String) extends TotalWrapper[A, String]
trait OpaqueInt[A](using A =:= Int)       extends TotalWrapper[A, Int]

abstract class OpaqueNum[A](using A =:= Int) extends TotalWrapper[A, Int]

abstract class YesNo[A](using ev: Boolean =:= A):
  val Yes: A = ev.apply(true)
  val No: A  = ev.apply(false)
  given BasicallyTheSame[Boolean, A] with
    def apply(a: Boolean) = if a then Yes else No
    def reverse(a: A)     = a == Yes

  inline def apply(inline b: Boolean): A = ev.apply(b)

  extension (inline a: A) inline def value: Boolean = a == Yes
end YesNo
