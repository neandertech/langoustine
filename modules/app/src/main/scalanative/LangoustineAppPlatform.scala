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

  def in: Stream[IO, Byte] =
    def go(in: Stream[IO, Byte]): Pull[IO, Byte, Unit] =
      in.pull.uncons.attempt.flatMap {
        case Right(None) => Pull.done
        case Right(Some((hd, t))) =>
          Pull.output(hd) >> go(in)
        case Left(i: java.io.IOException) =>
          Pull.eval(IO.sleep(stdinDebounceRate)) >> go(in)
        case Left(otherErr) =>
          Pull.raiseError(otherErr)
      }

    Stream.eval(enableNonBlocking) >>
      go(fs2.io.stdin[IO](inBufferSize)).stream
  end in

end LangoustineAppPlatform
