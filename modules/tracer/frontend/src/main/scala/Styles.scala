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
      fontSize := "1.3rem",
      padding  := "10px",
      position := "sticky",
      top      := "0",
      overflowX.scroll,
      overflowY.scroll,
      maxHeight := "80vh"
    )
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

  object pageSwitcher:
    val focused = Seq(
      fontSize        := "1.2rem",
      padding         := "10px",
      borderLeft      := "4px solid maroon",
      backgroundColor := "white"
    )
    val unfocused = Seq(
      fontSize   := "1.2rem",
      padding    := "10px",
      borderLeft := "4px solid blue",
      textDecoration.none
    )
    val link = Seq(
      textDecoration.none,
      color.black
    )
  end pageSwitcher
end Styles
