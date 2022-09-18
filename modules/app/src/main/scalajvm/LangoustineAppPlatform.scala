package langoustine.lsp.app

import _root_.fs2 as FS2
import cats.effect.IO

private[app] trait LangoustineAppPlatform:
  self: LangoustineApp.Config =>

  def inBufferSize: Int = 512

  def in(
      shutdown: LangoustineApp.Shutdown
  ): fs2.Stream[IO, Byte] =
    FS2.io.stdin[IO](inBufferSize) ++
      fs2.Stream
        .eval(shutdown.initiate)
        .flatMap(_ => fs2.Stream.empty)
end LangoustineAppPlatform
