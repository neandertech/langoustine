package langoustine.tracer

import scribe.Level

object Logging:
  private lazy val io =
    if sys.env.contains("LANGOUSTINE_TRACER_DEBUG") then
      scribe.Logger.root
        .clearHandlers()
        .withHandler(
          writer = scribe.writer.SystemErrWriter,
          outputFormat = scribe.output.format.ASCIIOutputFormat
        )
        .withMinimumLevel(Level.Debug)
        .replace()
    else
      scribe.Logger.root
        .clearHandlers()
        .withHandler(
          writer = scribe.writer.SystemErrWriter,
          outputFormat = scribe.output.format.ASCIIOutputFormat
        )
        .replace()
    end if

    scribe.cats.io
  end io

  export io.{debug, info, warn, error}

end Logging
