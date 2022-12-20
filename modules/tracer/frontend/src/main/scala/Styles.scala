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

object Styles:
  val staticContainer =
    Seq(
      display.flex,
      flexDirection.row,
      justifyContent.center,
      alignItems.center,
      width := "100%"
    )

  object modal:
    val container = Seq(
      position.fixed,
      zIndex := 1,
      left   := "0",
      top    := "0",
      width  := "100%",
      height := "100%",
      overflow.auto,
      backgroundColor := "rgba(0,0,0,0.8)"
    )

    val content = Seq(
      fontFamily      := "sans-serif",
      backgroundColor := "#fefefe",
      margin          := "15% auto",
      padding         := "20px",
      border          := "1px solid #888",
      width           := "80%"
    )

    val btn = Seq(
      backgroundColor := "darkgreen",
      fontWeight.bold,
      color.white,
      padding := "10px"
    )
  end modal

  val dynamicContainer =
    Seq(
      fontFamily      := "sans-serif",
      margin          := "0px",
      padding         := "0px",
      height          := "100%",
      backgroundColor := "#e3e3e3",
      padding         := "15px",
      maxWidth        := "1500px",
      width           := "100%"
    )

  val filterBox = Seq(
    margin       := "auto",
    padding      := "5px",
    fontSize     := "1.3rem",
    placeholder  := "filter...",
    borderRadius := "2px",
    border       := "0 solid lightgrey",
    position     := "sticky",
    top          := "0"
  )

  object commandTracer:
    val container = Seq(
      display.flex,
      alignItems.flexStart,
      columnGap       := "15px",
      backgroundColor := "white",
      padding         := "10px",
      borderRadius    := "10px"
    )

    val jsonViewer = Seq(
      width           := "70%",
      borderRadius    := "5px",
      backgroundColor := "#0d1117",
      color.white,
      fontSize := "1.1rem",
      padding  := "10px",
      position := "sticky",
      top      := "0",
      overflowX.scroll,
      overflowY.scroll,
      maxHeight := "80vh"
    )

    object modeBar:
      val container = Seq(
        display.flex,
        flexDirection.row,
        justifyContent.spaceBetween,
        columnGap := "15px"
      )
      val butt = Seq(
        fontSize := "0.9rem",
        color.white
      )
    end modeBar

    val viewHeader = Seq(
      display.flex,
      flexDirection.row,
      justifyContent.spaceBetween,
      width           := "100%",
      position        := "sticky",
      top             := "0",
      backgroundColor := "#0d1117"
    )

    object interactive:
      val bool     = Seq(color.aqua)
      val str      = Seq(color.lime)
      val num      = Seq(color := "pink", fontWeight.bold)
      val special  = Seq(color := "red", fontStyle.italic)
      val showMore = Seq(textDecoration.none, fontWeight.bold, color.aqua)

      val listElement = Seq(padding := "2px", margin := "0px")
  end commandTracer

  object logTracer:
    val container =
      Seq(
        borderRadius    := "5px",
        backgroundColor := "black",
        color.white,
        fontSize := "1rem",
        padding  := "10px",
        overflow.auto
      )
  end logTracer

  object summaryPage:
    val container =
      Seq(
        borderRadius    := "5px",
        backgroundColor := "white",
        padding         := "10px"
      )

  object timeline:
    val requestButton = Seq(
      padding         := "3px",
      backgroundColor := "lightgreen",
      fontSize        := "1.1rem",
      border          := "1px solid darkgrey"
    )

    val notificationButton =
      Seq(
        padding         := "3px",
        backgroundColor := "yellow",
        fontSize        := "1.1rem",
        border          := "1px solid darkgrey"
      )

    val row = Seq(
      borderBottom := "1px dotted lightgrey",
      width        := "100%"
    )

    val seeLink = Seq(
      color.black,
      textDecoration.none,
      borderBottom := "1px dotted black"
    )
    val clientServerHeader = Seq(
      width := "100%",
      display.flex,
      alignContent.spaceBetween,
      borderBottom := "1px solid lightgrey"
    )
  end timeline

  val downloadLink = Seq(
    fontSize := "1.2rem",
    padding  := "10px"
  )

  object pageSwitcher:
    private val layout = Seq(
      display.flex,
      alignItems.center
    )

    val focused = Seq(
      fontSize        := "1.2rem",
      padding         := "10px",
      borderLeft      := "4px solid maroon",
      backgroundColor := "white"
    ) ++ layout

    val unfocused = Seq(
      fontSize   := "1.2rem",
      padding    := "10px",
      borderLeft := "4px solid blue",
      textDecoration.none
    ) ++ layout

    val link = Seq(
      textDecoration.none,
      color.black,
      margin := "0px"
    )
  end pageSwitcher
end Styles
