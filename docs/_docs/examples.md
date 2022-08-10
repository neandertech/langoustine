# Examples

LSP servers are not easy to demonstrate succintly, because the only way to confirm it 
actually works is to use it with a LSP client - usually an IDE.

Additionally, various editors require various degrees of effort. Simplest 
one I found so far is in Neovim Nightly, see https://www.vikasraj.dev/blog/lsp-neovim-retrospective#into-the-future.

## LSP for Tree Sitter grammars

[Grammar.js LSP](https://github.com/keynmol/grammar-js-lsp)

Designed to make my life easier when working with [Tree Sitter grammars](https://github.com/tree-sitter/tree-sitter-scala/),
this LSP parses a subset of `JavaScript` the [acorn](https://github.com/acornjs/acorn)
library, builds an index of rules and their reductions, and makes it easier 
to preview rules and use things like go-to-definition.

Because this LSP needs to parse JS using a JS library, the entire server is packaged 
using Scala.js as a Node.js application.

## LSP for a toy language

[Quickmaffs](https://github.com/neandertech/quickmaffs)

Designed to serve as _the_ testbed for Langoustine changes, it's a small primitive
arithmetic language with associated set of tooling and built-in LSP.

This particular example runs on the JVM and uses Scala CLI exclusively for packaging.
