package langoustine
package lsp
package fs2

import _root_.fs2 as FS2
import cats.effect.IO

private[fs2] trait LangoustineAppPlatform:
  self: LangoustineApp.Config =>

  def inBufferSize: Int = 512

  def in: FS2.Stream[cats.effect.IO, Byte] =
    FS2.io.stdin[IO](512)
