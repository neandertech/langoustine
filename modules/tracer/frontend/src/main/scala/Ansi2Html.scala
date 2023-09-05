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

import fansi.Str
import fansi.ErrorMode
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("Ansi2Html")
object Ansi2Html extends Function1[String, String]:
  private def transition(from: fansi.Attr, to: fansi.Attr) =
    import fansi.*
    (from, to) match
      case (Underlined.Off, Underlined.On) => "<u>"
      case (Underlined.On, Underlined.Off) => "</u>"
      case (Bold.Off, Bold.On)             => "<b>"
      case (Bold.On, Bold.Off)             => "</b>"
      case (col1, col2) if color.isDefinedAt(col2) =>
        val closing   = if color.isDefinedAt(col1) then "</span>" else ""
        val nextColor = color(col2)
        s"$closing<span style='color: $nextColor'>"
      case (col1, fansi.Color.Reset) if color.isDefinedAt(col1) =>
        "</span>"
      case _ => ""
    end match
  end transition

  private def color: PartialFunction[fansi.Attr, String] = {
    case fansi.Color.Black        => "black"
    case fansi.Color.Red          => "red"
    case fansi.Color.Green        => "green"
    case fansi.Color.Yellow       => "yellow"
    case fansi.Color.Blue         => "blue"
    case fansi.Color.Magenta      => "magenta"
    case fansi.Color.Cyan         => "cyan"
    case fansi.Color.LightGray    => "lightgray"
    case fansi.Color.DarkGray     => "darkgray"
    case fansi.Color.LightRed     => "lightred"
    case fansi.Color.LightGreen   => "lightgreen"
    case fansi.Color.LightYellow  => "lightyellow"
    case fansi.Color.LightBlue    => "lightblue"
    case fansi.Color.LightMagenta => "lightmagenta"
    case fansi.Color.LightCyan    => "lightcyan"
    case fansi.Color.White        => "white"
  }

  @JSExport("ansi_2_html")
  def apply(s: String): String =
    val colored            = fansi.Str(s, ErrorMode.Strip)
    var current: Str.State = 0L

    val categories = fansi.Attr.categories

    val sb = new StringBuilder

    colored.getChars.zip(colored.getColors).map { case (character, color) =>
      if current != color then
        categories.foreach { cat =>
          sb.append(
            transition(
              cat.lookupAttr(current & cat.mask),
              cat.lookupAttr(color & cat.mask)
            )
          )
        }

        current = color
      end if
      if character == ' ' then sb.append("&nbsp;")
      else if character == '\n' then sb.append("<br />")
      else if character != '\r' then sb.append(character)
    }

    if current != 0L then
      categories.foreach(cat =>
        sb.append(
          transition(
            cat.lookupAttr(current & cat.mask),
            cat.lookupAttr(0L & cat.mask)
          )
        )
      )
    end if

    sb.result()
  end apply
end Ansi2Html
