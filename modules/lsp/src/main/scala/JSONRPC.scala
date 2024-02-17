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

import jsonrpclib.*
import scala.util.Try

import upickle.default.*

import util.chaining.*

private[langoustine] object jsonrpcIntegration:
  given codec[T: Reader: Writer]: Codec[T] =
    new Codec[T]:
      override def decode(
          payload: Option[Payload]
      ): Either[ProtocolError, T] =
        payload
          .map:
            case Payload.Data(array) => array
            case Payload.NullPayload => "null".getBytes
          .flatMap { arr =>
            Try(read[T](arr, trace = true)).toOption
          }
          .toRight(ProtocolError.InvalidParams("oopsie daisy"))
      end decode

      override def encode(a: T): Payload =
        Payload(write(a).getBytes)

  def handlerToEndpoint[F[_]: Monadic, T <: requests.LSPRequest](req: T)(
      f: req.In => F[req.Out]
  ): Endpoint[F] =
    Endpoint(req.requestMethod).simple(f)

  def handlerToNotification[F[_]: Monadic, T <: requests.LSPNotification](
      req: T
  )(
      f: req.In => F[Unit]
  ): Endpoint[F] = Endpoint(req.notificationMethod).notification(f)
end jsonrpcIntegration
