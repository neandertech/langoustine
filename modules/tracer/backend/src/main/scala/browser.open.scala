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

import scala.util.Try

import cats.effect.IO
import com.indoorvivants.detective.Platform
import com.indoorvivants.detective.Platform.OS.Linux
import com.indoorvivants.detective.Platform.OS.MacOS
import com.indoorvivants.detective.Platform.OS.Unknown
import com.indoorvivants.detective.Platform.OS.Windows
import org.http4s.Uri

def openBrowser(url: Uri): IO[Unit] =
  val stringUri = url.renderString

  def cmd(args: String*) =
    Try(sys.process.Process.apply(args).!!)

  val mac = cmd("open", stringUri)
  val linux =
    cmd("xdg-open", stringUri)
      .orElse(cmd("gnome-open", stringUri))
      .orElse(cmd("kde-open", stringUri))

  Platform.os match
    case Windows => Logging.error("Cannot open the browser on windows")
    case MacOS   => IO.fromTry(mac).void
    case Linux   => IO.fromTry(linux).void
    case Unknown =>
      Logging.error("Cannot open the browser on unknown platform")
end openBrowser

def canOpenBrowser = Platform.os != Platform.OS.Windows
