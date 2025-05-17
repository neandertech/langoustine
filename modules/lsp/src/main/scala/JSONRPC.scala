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

import scala.util.Try

import jsonrpclib.*
import upickle.core.TraceVisitor.TraceException
import upickle.default.*

import requests.*
import io.circe.Codec
import com.github.plokhotnyuk.jsoniter_scala.circe.JsoniterScalaCodec.*
import com.github.plokhotnyuk.jsoniter_scala.core.readFromString
import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.circe.Json
import io.circe.HCursor
import io.circe.Decoder.Result
import io.circe.DecodingFailure

object jsonrpcIntegration:
  val nullArray = "null".getBytes()
  given codec[T: Reader: Writer]: Codec[T] =
    new Codec[T]:

      override def apply(c: HCursor): Result[T] =
        Try(read[T](writeToString[Json](c.value))).toEither.left.map {
          case te: TraceException =>
            val e = te.getCause()
            DecodingFailure(
              DecodingFailure.Reason.CustomReason(
                s"invalid payload at ${te.jsonPath}: " + e.getMessage
              ),
              c
            )

          case e =>
            DecodingFailure(
              DecodingFailure.Reason.CustomReason(
                "oopsie daisy: " + e.getMessage
              ),
              c
            )
        }

      override def apply(a: T): Json =
        readFromString[Json](write(a))

  def handlerToEndpoint[F[_]: Monadic, T <: LSPRequest](req: T)(
      f: req.In => F[req.Out]
  ): Endpoint[F] =
    Endpoint(req.requestMethod).simple(f)

  def handlerToNotification[F[_]: Monadic, T <: LSPNotification](
      req: T
  )(
      f: req.In => F[Unit]
  ): Endpoint[F] = Endpoint(req.notificationMethod).notification(f)
end jsonrpcIntegration
