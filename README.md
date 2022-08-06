# Langoustine - write Language Servers in Scala 3

... and use them with Javascript, JVM, or even native libraries

**Status as of August 3rd, 2022**: active, but very unstable, use (don't) at your own risk

## What is it? 

It's a clean room implementation of the LSP protocol definitions.

By "clean room" we mean

1. Using only Scala libraries 
2. Idiomatic Scala code
3. Using Scala 3 features

Most of the code is generated directly from the recently published LSP specification in JSON format.

See the [API documentation](https://neandertech.github.io/langoustine/api/index.html) for requests that are supported.

## Is there a simple example?

Introducing [Quickmaffs](https://github.com/neandertech/quickmaffs), a primitive language with a LSP, REPL, and an interpreter, designed specifically
to demonstrate how easy it is to build Language Servers with Langoustine.

![GIF demonstrating operations in the editor with the made up Quickmaffs language](https://raw.githubusercontent.com/neandertech/quickmaffs/main/docs/lsp.gif)

## Should I use it?

Please refer to this helpful diagram:

```text 
┌──────────────┐                                                     
│  YAS KWEEEN  │                                                     
│     area     │                           .───────────.             
└──────────────┘                       _.─'             `──.         
        │                           ,─'                     '─.      
        │                          ╱                           ╲     
        │                        ,'      People who enjoy       `.   
        │                       ╱          using Scala 3          ╲  
        │       .───────────.  ;                                   : 
        │   _.─'             `─;.                                  : 
        └────────────────────────────┐                              :
        ╱                     │     ╲│     .───────────.            │
      ,'                      │      │._.─'             `──.        │
     ╱                        :     ,┼'╲                    '─.     ;
    ;                          :   ╱ │  :                      ╲   ; 
    ;                          : ,'  │  :                       `. ; 
   ;                            ╱    │   :                        ╲  
   │                           ; ╲   ▼   │                       ╱ : 
   │     People who enjoy      ;  `.     │                     ,'  : 
   :     creating Language    ;     ╲    ;                    ╱     :
    :         Servers         │      '─.;                  ,─'      │
    :                         │         `──.           _.─'         │
     ╲                        :        ╱    `─────────'             ;
      ╲                        :      ╱                            ; 
       `.                      :    ,'      People who are         ; 
         ╲                      ╲  ╱           ready for          ╱  
          '─.                   ,╲'         disappointment       ╱   
             `──.           _.─'  `.                           ,'    
                 `─────────'        ╲                         ╱      
                                     '─.                   ,─'       
                                        `──.           _.─'          
                                            `─────────'              
```

If you are in the YAS KWEEN area - welcome and let's have some fun!

## What can I use it for? 

Writing a language server for:

1.  your own toy language
   
2.  already existing language but with specific requirements
    
    For example, see [Grammar.js LSP](https://github.com/keynmol/grammar-js-lsp) - written specifically 
    for the `grammar.js` files in the Tree Sitter grammars.

    It uses the Scala.js artifact of this project, because it's easier to parse JavaScript using a 
    JavaScript library and package the whole server as a Node.js application.

3. markup languages and protocol files, think
   1. Certain YAML files (LSP with verification for Github Actions YAML files!)
   2. Avro files
   3. Protobuf files
   4. Smithy files ([**jk** a great one already exists](https://github.com/disneystreaming/smithy-language-server))
   5. LLVM IR text files (for all those compiler engineers!)
   6. Scala Native's NIR files
   7. loads and loads more
   
Even basic Go To Definition implementation for the files you work with for hours on a daily basis can have 
an immeasurable impact on your productivity.
