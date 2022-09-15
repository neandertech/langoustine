# Getting started

When writing a LSP server with langoustine, there is usually two main components:

1. Definition of endpoints (currently the only way to define them is using `LSPBuilder`)
   
   This is the meat and bones of your LSP server - all the logic regarding the processing
   of inputs, sending responses, publishing diagnostics, maintaining internal state, etc.
   lives here 

2. Transport layer integration
   
   While Langoustine gives you strongly typed data models to operate with,
   eventually they need to be converted to JSONRPC payloads and then to a stream of bytes 
   that are exchanged between the client (editor) and the server.

   This integration rarely needs to be modified and the hard part is provided by the excellent
   [jsonrpclib](https://github.com/neandertech/jsonrpclib) project.

To make it trivial to convert your server definition (1) into a runnable app which can be launched by  
the LSP client, we provide a `LangoustineApp`.

Let's take a look at a simple example.

## Example with Scala CLI

Let's build a small, single-file LSP server using the [Scala CLI](https://scala-cli.virtuslab.org/). 
Everything done here can be achieved with SBT or Mill, it's just easier to use Scala CLI for this demonstration.

The LSP will be very simple:

1. Only operates on files with `.langoustine` extension (this will become important only for editor integration)

2. It should send a window notification ([`window/showMessage`](https://microsoft.github.io/language-server-protocol/specifications/lsp/3.17/specification/#window_showMessage) when the server start processing a response to [initialize](https://microsoft.github.io/language-server-protocol/specifications/lsp/3.17/specification/#initialize) 
request 

3. Whenever a document is opened ([`textDocument/didOpen`](https://microsoft.github.io/language-server-protocol/specifications/lsp/3.17/specification/#textDocument_didOpen), we should track the full path of it in some in-memory state

   a. Once the document is persisted in memory, we should send a window notification with the total count of currently
      tracked documents

### Using Cats Effect (recommended)

In this example, we will be using [Cats Effect](https://typelevel.org/cats-effect/docs/getting-started) and the concurrency-safe
primitives it provides. While it is strongly recommended to use Langoustine with Cats Effect, it's not necessary. Read on if you want to see 
a version which uses `Future` instead.

Here's the entire code, as a single `lsp.definition.scala` file:

```scala 
//> using lib "tech.neander::langoustine-lsp::0.0.15"
//> using lib "tech.neander::langoustine-fs2::0.0.15"
//> using scala "3.2"

import langoustine.lsp.*
import langoustine.lsp.all.*
import langoustine.lsp.fs2.*
import jsonrpclib.fs2.*

import cats.effect.*

object MyServer extends LangoustineApp.Simple:
  override def server =
    IO.ref(Set.empty[String]).map(myLSP)

def myLSP(files: Ref[IO, Set[String]]) =
  LSPBuilder
    .create[IO]
    .handleRequest(initialize) { in =>
      sendMessage(in.toClient, "ready to initialise!") *>
        IO {
          InitializeResult(
            capabilities = ServerCapabilities(textDocumentSync =
              Opt(TextDocumentSyncKind.Full)
            ),
            serverInfo = Opt(InitializeResult.ServerInfo("My first LSP!"))
          )
        }
    }
    .handleNotification(textDocument.didOpen) { in =>
      val documentUri = in.params.textDocument.uri.value
      files.updateAndGet(_ + documentUri).map(_.size).flatMap { count =>
        sendMessage(in.toClient, s"In total, $count files registered!")
      }
    }

def sendMessage(back: Communicate[IO], msg: String) =
  back.notification(
    window.showMessage,
    ShowMessageParams(MessageType.Info, msg)
  )
```

1. `langoustine-fs2` dependency brings in `LangoustineApp` trait
    
    We are using the `LangoustineApp.Simple` variation, where server 
    definition does not depend on any of the command line arguments. 

    The design is heavily inspired by `IOApp` from Cats Effect.

2. `myLSP` is the definition of our LSP server - it only handles a `initialize` request and a 
   `textDocument/didOpen` notification.

   It carries internal state in the form of a `Ref[IO, Set[String]]` which is a thread-safe 
   storage of unique document URIs

3. When we respond to the `initialize` request, we also inform the client that 
   we're happy to receive document syncing notifications by setting `textDocumentSync = Opt(TextDocumentSyncKind.Full)`
   in the response.

   Without this, clients won't be sending `textDocument/didOpen` notifications that we need.

4. `sendMessage` is a small helper to send notifications back to the client, using the `window/showMessage` method.

### Using `Future`

If for whatever reason you find working with `Future` easier, we provide a `Langoustine.FromFuture` app builder - it behaves in the same way, but doesn't expect your LSP to 
use `IO`. 

Our app is therefore rewritten in this way:

```scala 
//> using lib "tech.neander::langoustine-lsp::0.0.15"
//> using lib "tech.neander::langoustine-fs2::0.0.15"
//> using scala "3.2"

import langoustine.lsp.*
import langoustine.lsp.all.*
import langoustine.lsp.fs2.*
import scala.concurrent.Future

import cats.instances.future._
import scala.concurrent.ExecutionContext
import scala.collection.concurrent.TrieMap

object MyFutureServer extends LangoustineApp.FromFuture.Simple:
  given ExecutionContext = ExecutionContext.global
  override def server = Future(myFutureLSP(TrieMap.empty))

def myFutureLSP(
    files: TrieMap[String, Boolean]
)(implicit ec: ExecutionContext) =
  LSPBuilder
    .create[Future]
    .handleRequest(initialize) { in =>
      sendMessage(in.toClient, "ready to initialise a future LSP!")
      Future {
        InitializeResult(
          capabilities = ServerCapabilities(textDocumentSync =
            Opt(TextDocumentSyncKind.Full)
          ),
          serverInfo = Opt(InitializeResult.ServerInfo("My first LSP!"))
        )
      }
    }
    .handleNotification(textDocument.didOpen) { in =>
      val documentUri = in.params.textDocument.uri.value
      files.update(documentUri, true)
      sendMessage(in.toClient, s"In total, ${files.size} files registered!")
    }

def sendMessage(back: Communicate[Future], msg: String) =
  back.notification(
    window.showMessage,
    ShowMessageParams(MessageType.Info, msg)
  )
```

Note that the LSP is almost identical! 

Only changes were: 

1. Using `TrieMap` to maintain our state in a thread safe manner 
2. Passing around ExecutionContext 
3. Not chaining `Future`s together when not necessary - they're eagerly evaluated 
   and will run in parallel

Note that it's the same dependencies and mostly the same imports - 
the underlying input/output machinery is still the same, we're just making the `IO` -> 
`Future` translation for you in a hopefully safe fashion.

## Packaging and distribution

The way 99% of LSP integrations works is by the editor launching 
a specified command, and connecting to it via STDIN/STDOUT.

As a first iteration, we can just use Scala CLI itself!

```
$ scala-cli run lsp.definition.scala
```

You could give this command to the editor of your choosing and it will launch the server for you.

Note that with Scala CLI you can package it even easier into a bootstrap jar:

```
$ scli package . -f -o LSP
```

and use `LSP` as the binary!

### Publishing and launching with Coursier

Another way is to publish our LSP server as a JVM app and use [Coursier](https://get-coursier.io/docs/overview) to bootstrap and launch it.

When we publish our application to Maven Central, it can be launched as easily as 

```
$ cs launch com.example::my-lsp:latest.release
```

You can even publish it to internal artifactory and modify the list of repositories 
Coursier uses.

One benefit of this model of distribution is that new versions will be automatically downloaded, and you don't need to do any extra packaging - coursier will download 
all the necessary dependencies.

A downside is that you need both th JVM and Coursier installed on user's machine.

### Packaging for Node.js and Pkg

Given that we are in the warm, molasses like, embrace of Scala, we have a secret weapon 
available to us - Scala.js.

If we are lucky enough to not use any Java dependencies and only use Scala libraries 
that have been cross-published for Scala.js, we have another way of packaging the app 
with some attractive features.

Sometimes it's even easier to write your LSP entirely targeting Scala.js - if you are using 
a JavaScript/TypeScript library for example.

To package our LSP into a single uber-JS file, we can run this command:

```
$ scli package . --js --js-module-kind common -f -o LSP.js
```

This LSP can be run with `node LSP.js` and behave exactly like the JVM version, 
if you don't count it being single threaded and being a completely different runtime..

But we can take it further - as JavaScript is an interpreted language, we can 
embed our generated JS file, along with the interpreter (Node.js) into a single, self-contained binary.

That's exactly what [Pkg](https://github.com/vercel/pkg) can do for us. You can install it by running `npm i -g pkg`.

After it's installed, all you need to do is point it at the location of your JS files:

```text
$ mkdir binaries

$ pkg LSP.js --out-path binaries
> pkg@5.8.0
> Targets not specified. Assuming:
  node16-linux-arm64, node16-macos-arm64, node16-win-arm64

$ l binaries --no-user
.rwxr-xr-x 52M 15 Sep 17:54  LSP-linux
.rwxr-xr-x 54M 15 Sep 17:55  LSP-macos
.rw-r--r-- 36M 15 Sep 17:55  LSP-win.exe
```

Note that because all Pkg is doing is embedding scripts into existing runtime, you can 
produce self-contained binaries for any platform! All it needs to do is download the 
correct distribution from Node.js' website.

This is a very interesting distribution mechanism and that's how an LSP for Tree Sitter grammars is distributed: https://github.com/keynmol/grammar-js-lsp/releases/tag/v0.0.3

## Editor integration

This section eagerly awaits your contributions!

### Neovim

In Neovim nightly, adding a custom LSP server is very simple. For a new filetype 
(like **.langoustine**) it's done in two steps:

1. Registering the filetype:

  ```lua
    vim.api.nvim_create_autocmd({ "BufRead", "BufNewFile" }, {
      pattern = { "*.langoustine" },
      callback = function() vim.cmd("setfiletype langoustine") end
    })
  ```

2. Adding a LSP configuration for that filetype. 

   This is where we should have selected a command to launch our server.
   Let's say in this example I chose to use the built jar:

  ```lua 
  local lsp = vim.api.nvim_create_augroup("LSP", { clear = true })

  vim.api.nvim_create_autocmd("FileType", {
    group = lsp,
    pattern = "langoustine",
    callback = function()
      vim.lsp.start({
        name = "Langoustine LSP",
        cmd = { '/path/to/LSP' }
      })
    end,
  })
  ```

  If I wanted to use coursier instead, I would need to use the following format, given 
  that the command is actually a list of arguments:

  ```lua
  cmd = { 'cs', 'launch', 'com.example::my-lsp:latest.release' }
  ```

  And that's it! Next time I open some `.langoustine` files I can see these messages:

  ```
  "[Prompt]" [Modified] 1 line --100%--
  "test.langoustine" 0L, 0B
  LSP[Langoustine LSP][Info] ready to initialise!
  LSP[Langoustine LSP][Info] In total, 1 files registered!
  "[Prompt]" [Modified] 1 line --100%--
  "hello.langoustine" 0L, 0B
  LSP[Langoustine LSP][Info] In total, 2 files registered!
  ```

