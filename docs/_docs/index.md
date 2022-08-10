# Langoustine 

A clean room implementation of the LSP protocol definitions.

By "clean room" we mean

1. Using only Scala libraries 
2. Idiomatic Scala code
3. Using Scala 3 features

Most of the code is generated directly from the recently published LSP specification in JSON format.

[![langoustine-lsp Scala version support](https://index.scala-lang.org/neandertech/langoustine/langoustine-lsp/latest.svg)](https://index.scala-lang.org/neandertech/langoustine/langoustine-lsp)

* **SBT:** `libraryDependencies += "tech.neander" %% "langoustine-lsp" % "<version>"`
* **Mill**: `ivy"tech.neander::langoustine-lsp::<version>"`
* [**Scala CLI**](https://scala-cli.virtuslab.org) `//> using lib "tech.neander::langoustine-lsp::<version>"`

In all of those, [don't forget to add an extra % or : if targeting a non-JVM artifact](https://youforgotapercentagesignoracolon.com)
