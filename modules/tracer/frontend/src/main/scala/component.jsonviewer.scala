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

def jsonViewer(
    showing: Var[Option[LspMessage]],
    mode: Var[JsonMode],
    modalBus: EventBus[ModalCommand]
) =
  def choose(value: JsonMode, whenSelected: String, whenNot: String) =
    mode.signal.map(v => if v == value then whenSelected else whenNot)

  def bar(title: Element) = div(
    Styles.commandTracer.viewHeader,
    div(title),
    div(
      Styles.commandTracer.modeBar.container,
      div(
        a(
          href := "#",
          Styles.commandTracer.modeBar.butt,
          textDecoration <-- choose(JsonMode.Raw, "underline", "none"),
          onClick.preventDefault.mapTo(JsonMode.Raw) --> mode.writer,
          "Raw JSON"
        )
      ),
      div(
        a(
          href := "#",
          Styles.commandTracer.modeBar.butt,
          onClick.preventDefault.mapTo(JsonMode.Details) --> mode.writer,
          textDecoration <-- choose(JsonMode.Details, "underline", "none"),
          "Interactive"
        )
      )
    )
  )

  def displayErr(ep: ErrorPayload) =
    div(
      bar(b(color := "pink", "Error")),
      displayJson(ep, mode.signal, modalBus)
    )

  given JsonValueCodec[Option[Payload]] = JsonCodecMaker.make

  def displayPayload(name: String, op: Option[Payload]) =
    div(
      bar(b(color := "lightgreen", name)),
      displayJson(op, mode.signal, modalBus)
    )

  div(
    flexGrow := 0,
    Styles.commandTracer.jsonViewer,
    display <-- showing.signal
      .map(_.isDefined)
      .map(if _ then "block" else "none"),
    child <-- showing.signal.flatMap {
      case Some(req: LspMessage.Request) =>
        Signal
          .fromFuture(Api.request(cid(req.id)))
          .map(_.flatten)
          .map { raw =>
            displayPayload("Params", raw.flatMap(_.params))
          }

      case Some(req: LspMessage.Response) =>
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

      case Some(cm: LspMessage.Notification) =>
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
      case None =>
        Signal.fromValue(
          i("Select any operation on the right to see its result")
        )

    }
  )
end jsonViewer

def displayJson[T: JsonValueCodec](
    rmsg: T,
    mode: Signal[JsonMode],
    modalBus: EventBus[ModalCommand]
) =
  val js = io.circe.scalajs
    .decodeJs[io.circe.Json](
      JSON.parse(
        writeToString[T](
          rmsg,
          WriterConfig.withIndentionStep(0)
        )
      )
    )
    .fold(throw _, identity)

  org.scalajs.dom.console.log(js)

  import io.circe.*

  val folder = new Json.Folder[Element]:
    val style = Styles.commandTracer.interactive

    override def onNull: Element = span(style.special, "null")

    override def onBoolean(value: Boolean): Element =
      span(style.bool, value.toString)

    override def onObject(value: JsonObject): Element =
      if value.size == 0 then span(style.special, "{empty object}")
      else
        ul(
          value.toList.map((key, json) =>
            val valueNode = key match
              case "uri" if json.isString =>
                json.asString.map {
                  case str if str.startsWith("file:") =>
                    a(href := s"vscode://$str", str, color.aqua)
                  case other =>
                    onString(other)
                }

              case _ => Some(json.foldWith(this))

            li(style.listElement, key, ": ", valueNode)
          )
        )
    override def onString(value: String): Element =
      if value.length > 150 then
        span(
          a(
            "📋",
            onClick.preventDefault.mapTo(value) --> { _ =>
              org.scalajs.dom.window.navigator.clipboard.writeText(value)
            },
            href := "#"
          ),
          " ",
          code(
            style.str,
            s""" "${value.take(150).replace("\n", "\\n")}" """.trim
          ),
          " ",
          a(
            style.showMore,
            href := "#",
            onClick.preventDefault.mapTo(
              ModalCommand.ShowCode(value)
            ) --> modalBus.writer,
            b(s"... show full (${value.length - 150} more characters)")
          )
        )
      else code(style.str, s""" "$value" """.trim)

    override def onNumber(value: JsonNumber): Element =
      code(style.num, value.toString)

    override def onArray(value: Vector[Json]): Element =
      if value.isEmpty then span(style.special, "[empty array]")
      else
        ol(
          value.map(json => li(style.listElement, json.foldWith(this)))
        )

  div(
    child <-- mode.map {
      case JsonMode.Raw =>
        pre(
          code(
            className := "language-json",
            js.spaces2SortKeys,
            onMountCallback(ctx => hljs.highlightAll())
          )
        )
      case JsonMode.Details =>
        code(js.foldWith(folder))
    }
  )

end displayJson

import jsonrpclib.{ErrorPayload, Payload}
import com.raquo.laminar.nodes.ReactiveHtmlElement
