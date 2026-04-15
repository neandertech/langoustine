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
import io.circe.*

opaque type DocumentUri = String
object DocumentUri extends OpaqueString[DocumentUri]:
  private val codec: Codec[DocumentUri] =
    Codec.from(Decoder.decodeString, Encoder.encodeString)

  given fromJson: Decoder[DocumentUri] =
    Decoder.decodeString.map(DocumentUri.apply)

  given toJson: Encoder[DocumentUri] =
    Encoder.encodeString.contramap(DocumentUri.unapply)

  given TypeTest[Any, DocumentUri] with
    def unapply(i: Any) =
      if i.isInstanceOf[String] then Some(i.asInstanceOf[i.type & DocumentUri])
      else None
end DocumentUri

opaque type Uri = String
object Uri extends OpaqueString[Uri]:
  private val codec: Codec[Uri] =
    Codec.from(Decoder.decodeString, Encoder.encodeString)
  given fromJson: Decoder[Uri] = Decoder.decodeString.map(Uri.apply)
  given toJson: Encoder[Uri]   = Encoder.encodeString.contramap(Uri.unapply)

  given TypeTest[Any, Uri] with
    def unapply(i: Any) =
      if i.isInstanceOf[String] then Some(i.asInstanceOf[i.type & Uri])
      else None
end Uri

opaque type uinteger = Int
object uinteger extends OpaqueInt[uinteger]:
  private val codec: Codec[uinteger] =
    Codec.from(Decoder.decodeInt, Encoder.encodeInt)

  given fromJson: Decoder[uinteger] = Decoder.decodeInt.map(uinteger.apply)
  given toJson: Encoder[uinteger]   =
    Encoder.encodeInt.contramap(uinteger.unapply)

  given TypeTest[Any, uinteger] with
    def unapply(i: Any) =
      if i.isInstanceOf[Int] then Some(i.asInstanceOf[i.type & uinteger])
      else None
end uinteger
