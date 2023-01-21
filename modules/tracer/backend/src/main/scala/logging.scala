package langoustine.tracer

object Logging:
  private lazy val io =
    scribe.Logger.root
      .clearHandlers()
      .withHandler(writer = scribe.writer.SystemErrWriter)
      .replace()

    scribe.cats.io

  export io.{debug, info, warn, error}
end Logging
