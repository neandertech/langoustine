package langoustine.lsp.app

import cats.effect.IO
import scala.concurrent.duration.*
import fs2.{Chunk, Stream}

import scalanative.posix
import fs2.Pull

private[app] trait LangoustineAppPlatform:
  self: LangoustineApp.Config =>

  /** The size (in bytes) of the input buffer
    *
    * @return
    */
  def inBufferSize: Int = 512

  /** Overridable duration to wait if the input stream doesn't have bytes
    * available On Scala Native it's important to have non-zero wait time
    * otherwise the application will block and not send any output out.
    *
    * A common symptom is of issues with this value is application hanging
    *
    * @return
    *   duration of pause when non-blocking STDIN raises an error
    */
  def stdinDebounceRate: FiniteDuration = 50.millis

  private val enableNonBlocking =
    IO(posix.fcntl.fcntl(0, posix.fcntl.F_SETFL, posix.fcntl.O_NONBLOCK).toByte)

  def in: fs2.Stream[cats.effect.IO, Byte] =
    fs2.Stream.eval(enableNonBlocking) >>
      fs2.io
        .stdin[IO](inBufferSize)
        .chunks
        .attempts(fs2.Stream.constant(stdinDebounceRate))
        .collect {
          case Right(ch) => ch
        }
        .unchunks
end LangoustineAppPlatform
