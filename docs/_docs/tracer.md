# Tracer

![2022-08-27 17 50 05](https://user-images.githubusercontent.com/1052965/187040059-f6e0c08b-7c76-4899-b370-0e8ada0c4819.gif)


A UI tool for capturing the LSP requests and responses that are exchanged between the client and the server.

It doesn't matter what the target LSP is implemented with, as we only intercept JSONRPC payloads sent to stdin and stdout.

Currently, primary method of distribution is via [Coursier](https://get-coursier.io/docs/overview).

To integrate with an LSP of your choosing, you need to have access to the CLI command that launches it.

The principle remains the same regardless of the editor:

```
cs launch tech.neander:langoustine-tracer_3:latest.release -- <lsp command>
```

where `<lsp command>` is passed as a list of arguments, **not as a string**. 

Once the tracer is launched, it'll bind itself to a random port (see below if you'd like to make it non-random) and send a message to the LSP client with the port bound. You can then reach the UI by going to `localhost:<port bound>` in your browser.

### Packaging with coursier

You can make your life considerably easier by using the bootstrap command that Coursier provides:

```
cs boostrap tech.neander:langoustine-tracer_3:latest.release -f -o langoustine-tracer

# now you can use ./langoustine-tracer,
# put it somewhere on your PATH so that it's globally avalable
# and use it like this:
langoustine-tracer my-awesome lsp --stdin true
```

Alternatively, if your system was setup with coursier (i.e. the path where it puts 
installed applications is in your system's `PATH`), then life is even easier,
hedonistic even:

```
cs install tech.neander:langoustine-tracer_3:latest.release
```

**From this point in the document we assume that you somehow installed this app in a global location**

For example, if your command to launch the LSP is `my-awesome lsp --stdin true`, then to trace it you need to use the following command:

```
langoustine-tracer my-awesome lsp --stdin true
```

## Tips

### Changing port

By default, it will start the server at random port, but you can change that using the `--port` argument:

```
langoustine-tracer --port 9911 my-awesome lsp --stdin true
```

### Local development

If you are working on the tracer itself, you can globally alias `langoustine-tracer` to a local installation:

```
alias langoustine-tracer=<path-to-langoustine>/modules/tracer/backend/target/jvm-3/universal/stage/bin/langoustine-tracer
```

That launcher is created by running `sbt tracer/stage`

If your focus is on the frontend, you can speed up your development
cycle considerably by doing the following:

1. Start your traced LSP somehow (doesn't matter if it's a coursier-installed one 
   or your local) 
2. Take note of the port number 
3. `cd modules/tracer/frontend`
4. `LANGOUSTINE_PORT=<port> npm run dev`
5. In a separate terminal, run `sbt ~tracerFrontendJS/fastLinkJS`

Now you can edit just the frontend code and Vite will automatically refresh 
the page - no need to restart the LSP to pick up changes.

