/*
 * Copyright 2020 Anton Sviridov
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

package com.indoorvivants.library

object Main {
  def main(args: Array[String]) = {
    println(implicitly[TypeClass[(Boolean, Int)]].isPrimitive)
  }
}

trait TypeClass[T] {
  def isPrimitive: Boolean
}

object TypeClass {

  def apply[A](implicit tc: TypeClass[A]): TypeClass[A] = tc

  implicit val boolPrimitive: TypeClass[Boolean] = new TypeClass[Boolean] {
    def isPrimitive: Boolean = true
  }

  implicit val intPrimitive: TypeClass[Int] = new TypeClass[Int] {
    def isPrimitive: Boolean = true
  }

  implicit def tupled[A: TypeClass, B: TypeClass]: TypeClass[(A, B)] =
    new TypeClass[(A, B)] {
      def isPrimitive: Boolean =
        TypeClass[A].isPrimitive && TypeClass[B].isPrimitive
    }

}
