package langoustine.tracer

import fs2.Stream
import cats.effect.*
import cats.syntax.all.*
import scala.jdk.CollectionConverters.*
import java.io.OutputStream

trait ChildProcess[F[_]]:
  def stdin: fs2.Pipe[F, Byte, Unit]
  def stdout: Stream[F, Byte]
  def stderr: Stream[F, Byte]

object ChildProcess:

  def spawn[F[_]: Async](command: String*): Stream[F, ChildProcess[F]] =
    Stream.bracket(start[F](command))(_._2).map(_._1)

  val readBufferSize = 512
  private def start[F[_]: Async](command: Seq[String]) =
    Async[F].interruptible {
      val p =
        new java.lang.ProcessBuilder(command.asJava)
          .start() // .directory(new java.io.File(wd)).start()
      val done = Async[F].fromCompletableFuture(Sync[F].delay(p.onExit()))

      val terminate: F[Unit] = Sync[F].interruptible(p.destroy())

      import cats.*
      val onGlobal = new (F ~> F):
        def apply[A](fa: F[A]): F[A] =
          Async[F].evalOn(fa, scala.concurrent.ExecutionContext.global)

      val cp = new ChildProcess[F]:
        def stdin: fs2.Pipe[F, Byte, Unit] =
          writeOutputStreamFlushingChunks[F](
            Sync[F].interruptible(p.getOutputStream())
          )

        def stdout: fs2.Stream[F, Byte] = fs2.io
          .readInputStream[F](
            Sync[F].interruptible(p.getInputStream()),
            chunkSize = readBufferSize
          )
          .translate(onGlobal)

        def stderr: fs2.Stream[F, Byte] = fs2.io
          .readInputStream[F](
            Sync[F].blocking(p.getErrorStream()),
            chunkSize = readBufferSize
          )
          .translate(onGlobal)
          // Avoids broken pipe - we cut off when the program ends.
          // Users can decide what to do with the error logs using the exitCode value
          .interruptWhen(done.void.attempt)
      (cp, terminate)
    }

  /** Adds a flush after each chunk
    */
  def writeOutputStreamFlushingChunks[F[_]](
      fos: F[OutputStream],
      closeAfterUse: Boolean = true
  )(implicit F: Sync[F]): fs2.Pipe[F, Byte, Nothing] =
    s =>
      def useOs(os: OutputStream): Stream[F, Nothing] =
        s.chunks.foreach(c =>
          F.interruptible(os.write(c.toArray)) >> F.blocking(os.flush())
        )

      val os =
        if closeAfterUse then Stream.bracket(fos)(os => F.blocking(os.close()))
        else Stream.eval(fos)
      os.flatMap(os => useOs(os) ++ Stream.exec(F.blocking(os.flush())))
end ChildProcess
