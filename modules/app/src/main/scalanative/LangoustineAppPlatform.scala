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
    * available. On Scala Native it's _very_ important to have non-zero wait
    * time otherwise the application will block and not send any output out.
    *
    * A common symptom of issues with this value is application hanging after
    * receiving first payload.
    *
    * @return
    *   duration of pause when non-blocking STDIN raises an error
    */
  def stdinDebounceRate: FiniteDuration = 50.millis

  private enum State:
    case Chunk(bytes: Array[Byte])
    case Skip, Over
    case Error(err: Int)

  private def reader(bufSize: Int) =
    posix.fcntl.fcntl(0, posix.fcntl.F_SETFL, posix.fcntl.O_NONBLOCK)
    var keepGoing = true
    val buf       = new Array[Byte](bufSize)
    val bufPtr    = buf.asInstanceOf[ByteArray].at(0)
    val bi        = bufSize.toUInt

    fs2.Stream.repeatEval {
      IO(unistd.read(0, bufPtr, bi)).flatMap { nread =>
        System.err.println(nread)
        if nread < 0 then
          if libc.errno.errno == posix.errno.EAGAIN then
            IO.sleep(stdinDebounceRate).as(State.Skip)
          else IO.pure(State.Error(libc.errno.errno))
        else if nread == 0 then IO.pure(State.Over)
        else IO(State.Chunk(buf.take(nread)))
      }
    }
  end reader

  override def in = reader(inBufferSize).collectWhile {
    case State.Chunk(ar) =>
      fs2.Chunk.array(ar)
    case State.Skip => fs2.Chunk.empty
  }.unchunks

end LangoustineAppPlatform
