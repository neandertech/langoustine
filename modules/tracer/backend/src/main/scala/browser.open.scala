package langoustine.tracer

import org.http4s.Uri
import com.indoorvivants.detective.Platform
import scala.util.Try
import cats.effect.IO
import com.indoorvivants.detective.Platform.OS.Windows
import com.indoorvivants.detective.Platform.OS.MacOS
import com.indoorvivants.detective.Platform.OS.Linux
import com.indoorvivants.detective.Platform.OS.Unknown
import scala.util.Failure

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
