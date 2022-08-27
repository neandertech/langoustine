# Tracer

A UI tool for capturing the LSP requests and responses that are exchanged between the client and the server.

It doesn't matter what the target LSP is implemented with, as we only intercept JSONRPC payloads sent to stdin and stdout.

Currently, primary method of distribution is via [Coursier](https://get-coursier.io/docs/overview).

To integrate with an LSP of your choosing, you need to have access to the CLI command that launches is.

The principle remains the same regardless of the editor:

```
cs launch tech.neander:langoustine-tracer_3:latest.release -- <lsp command>
```

where `<lsp command>` is passed as a list of arguments, **not as a string**.

### Packaging with coursier

You can make your life considerably easier by using the bootstrap command that Coursier provides:

```
cs boostrap tech.neander:langoustine-tracer_3:latest.release -f -o langoustine-tracer

# now you can use ./langoustine-tracer,
# put it somewhere on your PATH so that it's globally avalable
# and use it like this:
langoustine-tracer --port 9911 my-awesome lsp --stdin true
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

Tracer will interpret everything after the **first `--`** as the command that launches your LSP.
Everything **before first `--`** will be used to configure the tracer itself.

## Tips

### Changing port

By default, it will start the server at the port **9977** but you can change that using the `--port` argument:

```
langoustine-tracer --port 9911 my-awesome lsp --stdin true
```

### Local development

If you are working on the tracer itself, you can globally alias `langoustine-tracer` to a local installation:

```
alias langoustine-tracer=<path-to-langoustine>/modules/tracer/backend/target/jvm-3/universal/stage/bin/langoustine-tracer
```

That launcher is created by running `sbt tracer/stage`
