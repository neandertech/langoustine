package langoustine
package lsp
package fs2

import _root_.fs2 as FS2

private[fs2] trait LangoustineAppPlatform:
  self: LangoustineApp.Config => 

  def in: FS2.Stream[cats.effect.IO, Byte] =
    FS2.io.stdin[cats.effect.IO]

