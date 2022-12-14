# LSP Toolkit & Testkit

Most LSP servers reach a point where they perform basically the same operations,
because of the general nature of them. Things like location map (go from offset to line + character and back efficiently), maintaining a in-memory representation of latest document changes, concurrent state for versioned documents, etc.

Eventual goal of Langoustine is to provide performant implementations of whatever common tasks arise. The goal is eventual, as we need to get the protocol and developer experience basics right first, so keep an eye on this page.

## Semantic tokens encoder

LSP protocol supports [Semantic tokens](https://microsoft.github.io/language-server-protocol/specifications/lsp/3.17/specification/#textDocument_semanticTokens) but 
their usage comes with a particular encoding that the LSP servers must use.

This encoding consists of two parts:

1. First the server negotiates the semantic tokens "legend" with the client:
   
   The legend consists of two arrays where positions are important:

   - [Token types](/langoustine/api/langoustine/lsp/enumerations$$SemanticTokenTypes$.html)
   - [Token modifiers](/langoustine/api/langoustine/lsp/enumerations$$SemanticTokenModifiers$.html#)

    The positions in those arrays are important - their numeric indices will be used 
    in the encoding of semantic tokens for a document.

2. Then, the server requests the semantic tokens for a particular document.

   Encoding this result naively can lead to gigantic payloads passed back and forth 
   between client and server.

   To avoid that, a sort of delta encoding is used: 

   - line numbers and character offsets are recorded as deltas from the token previous in the list

   - token types are encoded as integer indices, refering to the location of the token in the array negotiated as part of the (1)

   - token modifiers are encoded as a single integer, with bit number `N` set to `1`
     if the current token has a modifier with position `N` in the array negotiated 
     as part of (1)

This encoding is the only one you can use semantic tokens in the current version of 
LSP specification.

To that end, Langoustine provides a utility that will encode the tokens for you. Enter [SemanticTokensEncoder](/langoustine/api/langoustine/lsp/tools/SemanticTokensEncoder.html).

```scala 
import langoustine.lsp.tools.*
import langoustine.lsp.all.*

val encoder = SemanticTokensEncoder(
    tokenTypes = Vector(SemanticTokenTypes.variable, SemanticTokenTypes.`class`),
    modifiers = Vector(SemanticTokenModifiers.definition, SemanticTokenModifiers.static)
)
```

Using that `encoder`, you can produce the `SemanticTokensLegend` required as as part of the server capabilities response:

```scala sc:nocompile
ServerCapabilities(
  //...
  semanticTokensProvider = Opt(
    SemanticTokensOptions(
      legend = encoder.legend,
      full = Opt(true)
    )
  ),
  //...
)
```

And encode the tokens directly into a `SemanticTokens`:

```scala 

import langoustine.lsp.tools.*
import langoustine.lsp.all.*

val encoder = SemanticTokensEncoder(
  tokenTypes = Vector(SemanticTokenTypes.variable, SemanticTokenTypes.`class`),
  modifiers = Vector(SemanticTokenModifiers.definition, SemanticTokenModifiers.static)
)

val payload: Either[SemanticTokensEncoder.Error, SemanticTokens] = 
  encoder.encode(
    Vector(
        SemanticToken.fromRange(
            Range(Position(0, 0), Position(0, 5)),
            SemanticTokenTypes.variable
        ),
        SemanticToken.fromRange(
            Range(Position(3, 25), Position(3, 27)),
            SemanticTokenTypes.`class`
        )
    )
  )

assert(payload.map(_.data) == Right(Vector(0, 0, 5, 0, 0, 3, 25, 2, 1, 0)))
```

The `encode` method returns `Left(...)` in case you used a token type or modifier that this encoder wasn't created to handle. 

One current limitation is that there is no facility to produce a delta response - which is basically a list of edits from one array to another.
