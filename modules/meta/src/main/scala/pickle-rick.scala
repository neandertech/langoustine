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

import scala.deriving.Mirror

import upickle.core.*
import upickle.default.*
import upickle.implicits.macros

object Pickle:
  inline def macroR[T](using m: Mirror.Of[T]): Reader[T] = inline m match
    case m: Mirror.ProductOf[T] =>
      val labels: List[String] = macros.fieldLabels[T]
      val visitors: List[Visitor[?, ?]] =
        macros
          .summonList[Tuple.Map[m.MirroredElemTypes, Reader]]
          .asInstanceOf[List[upickle.core.Visitor[?, ?]]]
      val defaultParams: Map[String, AnyRef] = macros.getDefaultParams[T]

      val reader = new CaseClassReader[T]:
        override def visitorForKey(key: String) =
          labels.zip(visitors).toMap.get(key) match
            case None    => upickle.core.NoOpVisitor
            case Some(v) => v

        override def make(params: Map[String, Any]): T =
          val values      = collection.mutable.ListBuffer.empty[AnyRef]
          val missingKeys = collection.mutable.ListBuffer.empty[String]

          labels.zip(visitors).map { case (fieldName, _) =>
            params.get(fieldName) match
              case Some(value) => values += value.asInstanceOf[AnyRef]
              case None =>
                defaultParams.get(fieldName) match
                  case Some(fallback) => values += fallback.asInstanceOf[AnyRef]
                  case None           => missingKeys += fieldName
          }

          if !missingKeys.isEmpty then
            throw new upickle.core.Abort(
              "missing keys in dictionary: " + missingKeys.mkString(", ")
            )

          val valuesArray = values.toArray
          m.fromProduct(new Product:
            def canEqual(that: Any): Boolean = true
            def productArity: Int            = valuesArray.length
            def productElement(i: Int): Any  = valuesArray(i))
        end make

      reader

    // if macros.isMemberOfSealedHierarchy[T] then annotate(reader, macros.fullClassName[T])
    // else reader

    case m: Mirror.SumOf[T] =>
      inline compiletime.erasedValue[T] match
        case _: scala.reflect.Enum =>
          val valueOf     = macros.enumValueOf[T]
          val description = macros.enumDescription[T]
          new EnumReader[T](valueOf, description)
        case _ =>
          val readers: List[Reader[? <: T]] = macros
            .summonList[Tuple.Map[m.MirroredElemTypes, Reader]]
            .asInstanceOf[List[Reader[? <: T]]]
          Reader.merge[T](readers*)
      end match
end Pickle
