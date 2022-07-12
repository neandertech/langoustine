package langoustine.lsp

import langoustine.*

object enumerations: 
  private val stringCodec = upickle.default.readwriter[String]
  private val intCodec = upickle.default.readwriter[Int]
  import upickle.{default => up}
  opaque type SemanticTokenTypes = String
  object SemanticTokenTypes:
    given up.ReadWriter[SemanticTokenTypes] = stringCodec.asInstanceOf[up.ReadWriter[SemanticTokenTypes]]
    inline val namespace = entry("namespace")
    inline val `type` = entry("type")
    inline val `class` = entry("class")
    inline val `enum` = entry("enum")
    inline val interface = entry("interface")
    inline val struct = entry("struct")
    inline val typeParameter = entry("typeParameter")
    inline val parameter = entry("parameter")
    inline val variable = entry("variable")
    inline val property = entry("property")
    inline val enumMember = entry("enumMember")
    inline val event = entry("event")
    inline val function = entry("function")
    inline val method = entry("method")
    inline val `macro` = entry("macro")
    inline val keyword = entry("keyword")
    inline val modifier = entry("modifier")
    inline val comment = entry("comment")
    inline val string = entry("string")
    inline val number = entry("number")
    inline val regexp = entry("regexp")
    inline val operator = entry("operator")
    inline val decorator = entry("decorator")
    
    private inline def entry(v: String): SemanticTokenTypes = v
  
  opaque type SemanticTokenModifiers = String
  object SemanticTokenModifiers:
    given up.ReadWriter[SemanticTokenModifiers] = stringCodec.asInstanceOf[up.ReadWriter[SemanticTokenModifiers]]
    inline val declaration = entry("declaration")
    inline val definition = entry("definition")
    inline val readonly = entry("readonly")
    inline val static = entry("static")
    inline val deprecated = entry("deprecated")
    inline val `abstract` = entry("abstract")
    inline val async = entry("async")
    inline val modification = entry("modification")
    inline val documentation = entry("documentation")
    inline val defaultLibrary = entry("defaultLibrary")
    
    private inline def entry(v: String): SemanticTokenModifiers = v
  
  opaque type ErrorCodes = Int
  object ErrorCodes:
    given up.ReadWriter[ErrorCodes] = intCodec.asInstanceOf[up.ReadWriter[ErrorCodes]]
    inline val ParseError = entry(-32700)
    inline val InvalidRequest = entry(-32600)
    inline val MethodNotFound = entry(-32601)
    inline val InvalidParams = entry(-32602)
    inline val InternalError = entry(-32603)
    inline val jsonrpcReservedErrorRangeStart = entry(-32099)
    inline val serverErrorStart = entry(-32099)
    inline val ServerNotInitialized = entry(-32002)
    inline val UnknownErrorCode = entry(-32001)
    inline val jsonrpcReservedErrorRangeEnd = entry(-32000)
    inline val serverErrorEnd = entry(-32000)
    
    private inline def entry(v: Int): ErrorCodes = v
  
  opaque type LSPErrorCodes = Int
  object LSPErrorCodes:
    given up.ReadWriter[LSPErrorCodes] = intCodec.asInstanceOf[up.ReadWriter[LSPErrorCodes]]
    inline val lspReservedErrorRangeStart = entry(-32899)
    inline val RequestFailed = entry(-32803)
    inline val ServerCancelled = entry(-32802)
    inline val ContentModified = entry(-32801)
    inline val RequestCancelled = entry(-32800)
    inline val lspReservedErrorRangeEnd = entry(-32800)
    
    private inline def entry(v: Int): LSPErrorCodes = v
  
  opaque type FoldingRangeKind = String
  object FoldingRangeKind:
    given up.ReadWriter[FoldingRangeKind] = stringCodec.asInstanceOf[up.ReadWriter[FoldingRangeKind]]
    inline val Comment = entry("comment")
    inline val Imports = entry("imports")
    inline val Region = entry("region")
    
    private inline def entry(v: String): FoldingRangeKind = v
  
  opaque type SymbolKind = RuntimeBase.uinteger
  object SymbolKind:
    given up.ReadWriter[SymbolKind] = intCodec.asInstanceOf[up.ReadWriter[SymbolKind]]
    inline val File = entry(1)
    inline val Module = entry(2)
    inline val Namespace = entry(3)
    inline val Package = entry(4)
    inline val Class = entry(5)
    inline val Method = entry(6)
    inline val Property = entry(7)
    inline val Field = entry(8)
    inline val Constructor = entry(9)
    inline val Enum = entry(10)
    inline val Interface = entry(11)
    inline val Function = entry(12)
    inline val Variable = entry(13)
    inline val Constant = entry(14)
    inline val String = entry(15)
    inline val Number = entry(16)
    inline val Boolean = entry(17)
    inline val Array = entry(18)
    inline val Object = entry(19)
    inline val Key = entry(20)
    inline val Null = entry(21)
    inline val EnumMember = entry(22)
    inline val Struct = entry(23)
    inline val Event = entry(24)
    inline val Operator = entry(25)
    inline val TypeParameter = entry(26)
    
    private inline def entry(n: Int): SymbolKind = RuntimeBase.uinteger(n)
  
  opaque type SymbolTag = RuntimeBase.uinteger
  object SymbolTag:
    given up.ReadWriter[SymbolTag] = intCodec.asInstanceOf[up.ReadWriter[SymbolTag]]
    inline val Deprecated = entry(1)
    
    private inline def entry(n: Int): SymbolTag = RuntimeBase.uinteger(n)
  
  opaque type UniquenessLevel = String
  object UniquenessLevel:
    given up.ReadWriter[UniquenessLevel] = stringCodec.asInstanceOf[up.ReadWriter[UniquenessLevel]]
    inline val document = entry("document")
    inline val project = entry("project")
    inline val group = entry("group")
    inline val scheme = entry("scheme")
    inline val global = entry("global")
    
    private inline def entry(v: String): UniquenessLevel = v
  
  opaque type MonikerKind = String
  object MonikerKind:
    given up.ReadWriter[MonikerKind] = stringCodec.asInstanceOf[up.ReadWriter[MonikerKind]]
    inline val `import` = entry("import")
    inline val `export` = entry("export")
    inline val local = entry("local")
    
    private inline def entry(v: String): MonikerKind = v
  
  opaque type InlayHintKind = RuntimeBase.uinteger
  object InlayHintKind:
    given up.ReadWriter[InlayHintKind] = intCodec.asInstanceOf[up.ReadWriter[InlayHintKind]]
    inline val Type = entry(1)
    inline val Parameter = entry(2)
    
    private inline def entry(n: Int): InlayHintKind = RuntimeBase.uinteger(n)
  
  opaque type MessageType = RuntimeBase.uinteger
  object MessageType:
    given up.ReadWriter[MessageType] = intCodec.asInstanceOf[up.ReadWriter[MessageType]]
    inline val Error = entry(1)
    inline val Warning = entry(2)
    inline val Info = entry(3)
    inline val Log = entry(4)
    
    private inline def entry(n: Int): MessageType = RuntimeBase.uinteger(n)
  
  opaque type TextDocumentSyncKind = RuntimeBase.uinteger
  object TextDocumentSyncKind:
    given up.ReadWriter[TextDocumentSyncKind] = intCodec.asInstanceOf[up.ReadWriter[TextDocumentSyncKind]]
    inline val None = entry(0)
    inline val Full = entry(1)
    inline val Incremental = entry(2)
    
    private inline def entry(n: Int): TextDocumentSyncKind = RuntimeBase.uinteger(n)
  
  opaque type TextDocumentSaveReason = RuntimeBase.uinteger
  object TextDocumentSaveReason:
    given up.ReadWriter[TextDocumentSaveReason] = intCodec.asInstanceOf[up.ReadWriter[TextDocumentSaveReason]]
    inline val Manual = entry(1)
    inline val AfterDelay = entry(2)
    inline val FocusOut = entry(3)
    
    private inline def entry(n: Int): TextDocumentSaveReason = RuntimeBase.uinteger(n)
  
  opaque type CompletionItemKind = RuntimeBase.uinteger
  object CompletionItemKind:
    given up.ReadWriter[CompletionItemKind] = intCodec.asInstanceOf[up.ReadWriter[CompletionItemKind]]
    inline val Text = entry(1)
    inline val Method = entry(2)
    inline val Function = entry(3)
    inline val Constructor = entry(4)
    inline val Field = entry(5)
    inline val Variable = entry(6)
    inline val Class = entry(7)
    inline val Interface = entry(8)
    inline val Module = entry(9)
    inline val Property = entry(10)
    inline val Unit = entry(11)
    inline val Value = entry(12)
    inline val Enum = entry(13)
    inline val Keyword = entry(14)
    inline val Snippet = entry(15)
    inline val Color = entry(16)
    inline val File = entry(17)
    inline val Reference = entry(18)
    inline val Folder = entry(19)
    inline val EnumMember = entry(20)
    inline val Constant = entry(21)
    inline val Struct = entry(22)
    inline val Event = entry(23)
    inline val Operator = entry(24)
    inline val TypeParameter = entry(25)
    
    private inline def entry(n: Int): CompletionItemKind = RuntimeBase.uinteger(n)
  
  opaque type CompletionItemTag = RuntimeBase.uinteger
  object CompletionItemTag:
    given up.ReadWriter[CompletionItemTag] = intCodec.asInstanceOf[up.ReadWriter[CompletionItemTag]]
    inline val Deprecated = entry(1)
    
    private inline def entry(n: Int): CompletionItemTag = RuntimeBase.uinteger(n)
  
  opaque type InsertTextFormat = RuntimeBase.uinteger
  object InsertTextFormat:
    given up.ReadWriter[InsertTextFormat] = intCodec.asInstanceOf[up.ReadWriter[InsertTextFormat]]
    inline val PlainText = entry(1)
    inline val Snippet = entry(2)
    
    private inline def entry(n: Int): InsertTextFormat = RuntimeBase.uinteger(n)
  
  opaque type InsertTextMode = RuntimeBase.uinteger
  object InsertTextMode:
    given up.ReadWriter[InsertTextMode] = intCodec.asInstanceOf[up.ReadWriter[InsertTextMode]]
    inline val asIs = entry(1)
    inline val adjustIndentation = entry(2)
    
    private inline def entry(n: Int): InsertTextMode = RuntimeBase.uinteger(n)
  
  opaque type DocumentHighlightKind = RuntimeBase.uinteger
  object DocumentHighlightKind:
    given up.ReadWriter[DocumentHighlightKind] = intCodec.asInstanceOf[up.ReadWriter[DocumentHighlightKind]]
    inline val Text = entry(1)
    inline val Read = entry(2)
    inline val Write = entry(3)
    
    private inline def entry(n: Int): DocumentHighlightKind = RuntimeBase.uinteger(n)
  
  opaque type CodeActionKind = String
  object CodeActionKind:
    given up.ReadWriter[CodeActionKind] = stringCodec.asInstanceOf[up.ReadWriter[CodeActionKind]]
    inline val Empty = entry("")
    inline val QuickFix = entry("quickfix")
    inline val Refactor = entry("refactor")
    inline val RefactorExtract = entry("refactor.extract")
    inline val RefactorInline = entry("refactor.inline")
    inline val RefactorRewrite = entry("refactor.rewrite")
    inline val Source = entry("source")
    inline val SourceOrganizeImports = entry("source.organizeImports")
    inline val SourceFixAll = entry("source.fixAll")
    
    private inline def entry(v: String): CodeActionKind = v
  
  opaque type TraceValues = String
  object TraceValues:
    given up.ReadWriter[TraceValues] = stringCodec.asInstanceOf[up.ReadWriter[TraceValues]]
    inline val Off = entry("off")
    inline val Messages = entry("messages")
    inline val Verbose = entry("verbose")
    
    private inline def entry(v: String): TraceValues = v
  
  opaque type MarkupKind = String
  object MarkupKind:
    given up.ReadWriter[MarkupKind] = stringCodec.asInstanceOf[up.ReadWriter[MarkupKind]]
    inline val PlainText = entry("plaintext")
    inline val Markdown = entry("markdown")
    
    private inline def entry(v: String): MarkupKind = v
  
  opaque type PositionEncodingKind = String
  object PositionEncodingKind:
    given up.ReadWriter[PositionEncodingKind] = stringCodec.asInstanceOf[up.ReadWriter[PositionEncodingKind]]
    inline val UTF8 = entry("utf-8")
    inline val UTF16 = entry("utf-16")
    inline val UTF32 = entry("utf-32")
    
    private inline def entry(v: String): PositionEncodingKind = v
  
  opaque type FileChangeType = RuntimeBase.uinteger
  object FileChangeType:
    given up.ReadWriter[FileChangeType] = intCodec.asInstanceOf[up.ReadWriter[FileChangeType]]
    inline val Created = entry(1)
    inline val Changed = entry(2)
    inline val Deleted = entry(3)
    
    private inline def entry(n: Int): FileChangeType = RuntimeBase.uinteger(n)
  
  opaque type WatchKind = RuntimeBase.uinteger
  object WatchKind:
    given up.ReadWriter[WatchKind] = intCodec.asInstanceOf[up.ReadWriter[WatchKind]]
    inline val Create = entry(1)
    inline val Change = entry(2)
    inline val Delete = entry(4)
    
    private inline def entry(n: Int): WatchKind = RuntimeBase.uinteger(n)
  
  opaque type DiagnosticSeverity = RuntimeBase.uinteger
  object DiagnosticSeverity:
    given up.ReadWriter[DiagnosticSeverity] = intCodec.asInstanceOf[up.ReadWriter[DiagnosticSeverity]]
    inline val Error = entry(1)
    inline val Warning = entry(2)
    inline val Information = entry(3)
    inline val Hint = entry(4)
    
    private inline def entry(n: Int): DiagnosticSeverity = RuntimeBase.uinteger(n)
  
  opaque type DiagnosticTag = RuntimeBase.uinteger
  object DiagnosticTag:
    given up.ReadWriter[DiagnosticTag] = intCodec.asInstanceOf[up.ReadWriter[DiagnosticTag]]
    inline val Unnecessary = entry(1)
    inline val Deprecated = entry(2)
    
    private inline def entry(n: Int): DiagnosticTag = RuntimeBase.uinteger(n)
  
  opaque type CompletionTriggerKind = RuntimeBase.uinteger
  object CompletionTriggerKind:
    given up.ReadWriter[CompletionTriggerKind] = intCodec.asInstanceOf[up.ReadWriter[CompletionTriggerKind]]
    inline val Invoked = entry(1)
    inline val TriggerCharacter = entry(2)
    inline val TriggerForIncompleteCompletions = entry(3)
    
    private inline def entry(n: Int): CompletionTriggerKind = RuntimeBase.uinteger(n)
  
  opaque type SignatureHelpTriggerKind = RuntimeBase.uinteger
  object SignatureHelpTriggerKind:
    given up.ReadWriter[SignatureHelpTriggerKind] = intCodec.asInstanceOf[up.ReadWriter[SignatureHelpTriggerKind]]
    inline val Invoked = entry(1)
    inline val TriggerCharacter = entry(2)
    inline val ContentChange = entry(3)
    
    private inline def entry(n: Int): SignatureHelpTriggerKind = RuntimeBase.uinteger(n)
  
  opaque type CodeActionTriggerKind = RuntimeBase.uinteger
  object CodeActionTriggerKind:
    given up.ReadWriter[CodeActionTriggerKind] = intCodec.asInstanceOf[up.ReadWriter[CodeActionTriggerKind]]
    inline val Invoked = entry(1)
    inline val Automatic = entry(2)
    
    private inline def entry(n: Int): CodeActionTriggerKind = RuntimeBase.uinteger(n)
  
  opaque type FileOperationPatternKind = String
  object FileOperationPatternKind:
    given up.ReadWriter[FileOperationPatternKind] = stringCodec.asInstanceOf[up.ReadWriter[FileOperationPatternKind]]
    inline val file = entry("file")
    inline val folder = entry("folder")
    
    private inline def entry(v: String): FileOperationPatternKind = v
  
  opaque type NotebookCellKind = RuntimeBase.uinteger
  object NotebookCellKind:
    given up.ReadWriter[NotebookCellKind] = intCodec.asInstanceOf[up.ReadWriter[NotebookCellKind]]
    inline val Markup = entry(1)
    inline val Code = entry(2)
    
    private inline def entry(n: Int): NotebookCellKind = RuntimeBase.uinteger(n)
  
  opaque type ResourceOperationKind = String
  object ResourceOperationKind:
    given up.ReadWriter[ResourceOperationKind] = stringCodec.asInstanceOf[up.ReadWriter[ResourceOperationKind]]
    inline val Create = entry("create")
    inline val Rename = entry("rename")
    inline val Delete = entry("delete")
    
    private inline def entry(v: String): ResourceOperationKind = v
  
  opaque type FailureHandlingKind = String
  object FailureHandlingKind:
    given up.ReadWriter[FailureHandlingKind] = stringCodec.asInstanceOf[up.ReadWriter[FailureHandlingKind]]
    inline val Abort = entry("abort")
    inline val Transactional = entry("transactional")
    inline val TextOnlyTransactional = entry("textOnlyTransactional")
    inline val Undo = entry("undo")
    
    private inline def entry(v: String): FailureHandlingKind = v
  
  opaque type PrepareSupportDefaultBehavior = RuntimeBase.uinteger
  object PrepareSupportDefaultBehavior:
    given up.ReadWriter[PrepareSupportDefaultBehavior] = intCodec.asInstanceOf[up.ReadWriter[PrepareSupportDefaultBehavior]]
    inline val Identifier = entry(1)
    
    private inline def entry(n: Int): PrepareSupportDefaultBehavior = RuntimeBase.uinteger(n)
  
  opaque type TokenFormat = String
  object TokenFormat:
    given up.ReadWriter[TokenFormat] = stringCodec.asInstanceOf[up.ReadWriter[TokenFormat]]
    inline val Relative = entry("relative")
    
    private inline def entry(v: String): TokenFormat = v
  
