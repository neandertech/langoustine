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

package langoustine.tracer

import com.raquo.laminar.api.L.*
import com.github.plokhotnyuk.jsoniter_scala.core.*

import jsonrpclib.*
import scala.scalajs.js.JSON
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker

def jsonViewer(showing: Var[Option[Message]]) =
  div(
    flexGrow := 0,
    Styles.commandTracer.jsonViewer,
    display <-- showing.signal
      .map(_.isDefined)
      .map(if (_) then "block" else "none"),
    child <-- showing.signal.flatMap {
      case None =>
        Signal.fromValue(
          i("Select any operation on the right to see its result")
        )

      case Some(req: Message.Request) =>
        Signal
          .fromFuture(Api.request(cid(req.id)))
          .map(_.flatten)
          .map { raw =>
            displayPayload("Params", raw.flatMap(_.params))
          }

      case Some(req: Message.Response) =>
        Signal
          .fromFuture(Api.response(cid(req.id)))
          .map(_.flatten)
          .map {
            case None => i("...")
            case Some(raw) =>
              def makeResult(
                  rm: RawMessage
              ): Either[ErrorPayload, Option[Payload]] =
                rm.error match
                  case None     => Right(rm.result)
                  case Some(er) => Left(er)
              makeResult(raw) match
                case Left(ep) =>
                  displayErr(ep)
                case Right(op) =>
                  displayPayload("Result", op)
          }

      case Some(cm: Message.Notification) =>
        Signal
          .fromFuture(Api.notification(cid(cm.generatedId)))
          .map(_.flatten)
          .map {
            case None => i("...")
            case Some(raw) =>
              (raw.params, raw.error) match
                case (None, None) => i("no error or payload")
                case (_, Some(err)) =>
                  displayErr(err)
                case (p, None) =>
                  displayPayload("Params", p)
          }
    }
  )

def displayJson[T: JsonValueCodec](rmsg: T) =
  val js =
    writeToString(
      rmsg,
      WriterConfig.withIndentionStep(4)
    )

  pre(
    code(
      className := "language-json",
      JSON.stringify(JSON.parse(js), space = 2),
      onMountCallback(ctx => hljs.highlightAll())
    )
  )
end displayJson

import jsonrpclib.{ErrorPayload, Payload}

def displayErr(ep: ErrorPayload) =
  div(b(color := "pink", "Error"), displayJson(ep))

given JsonValueCodec[Option[Payload]] = JsonCodecMaker.make

def displayPayload(name: String, op: Option[Payload]) =
  div(b(color := "lightgreen", name, displayJson(op)))
