package langoustine.tracer

object Logging:
  lazy val io =
    scribe.Logger.root
      .clearHandlers()
      .withHandler(writer = scribe.writer.SystemErrWriter)
      .replace()

    scribe.cats.io
