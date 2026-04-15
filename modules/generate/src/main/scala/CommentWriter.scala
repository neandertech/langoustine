package langoustine.generate

case class CommentWriter(commentLine: String => Unit)

def commentWriter(b: LineBuilder)(f: CommentWriter => Unit)(using Config) =
  inline def line: Config ?=> Appender = to(b)
  line("/**")
  f(CommentWriter { s =>
    val lines = s.linesIterator
    lines.foreach { l =>
      line(" *  " + l.replace("*/", "").replace("/*", "")) // TAKE THAT
    }
  })
  line(" */")
end commentWriter
