# Contributing guide

Notes:

1. Typos and small fix PRs are always welcome, no matter how small!
2. For larger changes please create an issue to discuss it first

## Dev process

1. Run tests with `test`
2. Re-generate LSP definitions with `generateLSP` (if you changed any code in `meta` or 
   `generate` modules, see below)
3. Generate docs with `generateDocs`, you should see the website in `./website/` folder
4. **Before pushing** make sure you run `preCI` which will 
   
   - Reformat the code 
   - Run Scalafix 
   - Ensure your changes don't get tripped up by the boring, automatable 
     steps of the build (like the ones mentioned above)

## Structure

The project consists of 3 main modules

### `meta` - which defines the meta-model of LSP specification

This module was essentially hand-crafted from the `metaModel.schema.json` file 
which you can find in the repository.

It defines what is a `Request`, `Structure`, `OrType`, etc. which are then used in the LSP
specification itself.

It also defines json codecs and helper types to be able to parse the actual LSP 
specification from JSON.

### `generate` - which converts LSP specification to Scala code

This module does all the heavy lifting required to massage the LSP spec into 
Scala-compatible domain model and subsequently Scala 3 code:

1. Apply type transformations 
   
   For example, convert anonymous structures into a named structure in the companion 
   object

2. Define how types are rendered
   
   i.e. `OrType` is usually rendered as a Scala 3 Union, whereas a union with a `Null`
   component will be rendered as `Opt[A]` where `A` is a union type of remaining 
   components

3. Lay out JSON codecs to match both the `given` semantics of the language, and 
   the API methods available in uPickle

4. Render enumerations and type aliases
5. Selectively remove some types 

   For example, `LSPAny` is basically a JSON structure and there's 
   no real reason to massage it (it's recursive, which makes things hard) 
   so we need to replace it with `ujson.Value` everywhere.

This module also defines a main class, which accepts a target path 
as an argument and, when run, generates all the LSP code in that location.

### `lsp` - rendered LSP specification and runtime classes

The `generated` folder contains all the code generated by the `generate` module described above.
Generated code is **excluded from Scalafmt**.

The rest of the code contains 

- runtime datastructures required for LSP to run, 
- `LSPBuilder` to define Language Servers 
- integration with [jsonrpclib]
- runtime enumeration definitions
- some basic types (like `DocumentUri` and `uinteger`)
- specialised JSON codecs which are used as building blocks 
  for more complex codecs in the generated code   

[jsonrpclib]: https://github.com/neandertech/jsonrpclib
