package langoustine.generate

import langoustine.meta.*


opaque type LineBuilder = StringBuilder
object LineBuilder:
  private val SEP          = System.lineSeparator()
  def apply(): LineBuilder = new StringBuilder
  extension (lb: LineBuilder)
    def result: String                                = lb.result
    def appendLine(s: String): LineBuilder            = lb.append(s + SEP)
    def emptyLine: LineBuilder                        = lb.append(SEP)
    def emptyLines(n: Int): LineBuilder               = lb.append(SEP * n)
    def append(s: String): LineBuilder                = lb.append(s)
    def topLevel(f: Config ?=> Unit)(using c: Config) =
      f(using c.copy(indents = Indentation(0)))
    def appender: Config ?=> Appender = to(lb)
end LineBuilder

case class Config(indents: Indentation, indentSize: IndentationSize)

opaque type IndentationSize = Int
object IndentationSize extends OpaqueNum[IndentationSize]

opaque type Indentation = Int
object Indentation extends OpaqueNum[Indentation]

import IndentationSize.*

def indent(using c: Config): String =
  (" " * (c.indentSize.value * c.indents.value))

def nest(f: Config ?=> Unit)(using config: Config) =
  f(using config.copy(indents = config.indents.map(_ + 1)))

def deep(count: Int)(f: Config ?=> Unit)(using config: Config) =
  f(using config.copy(indents = config.indents.map(_ + count)))

def to(sb: LineBuilder)(using config: Config): Appender =
  import LineBuilder.*
  line => sb.appendLine(indent(using config) + line)

type Appender = Config ?=> String => Unit
