package langoustine.lsp.app

import _root_.fs2 as FS2

private[app] trait LangoustineAppPlatform:
  self: LangoustineApp.Config =>

  def in: FS2.Stream[cats.effect.IO, Byte] =
    FS2.io.stdin[cats.effect.IO]
