package langoustine.lsp

import langoustine.*

object enumerations: 
  private val stringCodec = upickle.default.readwriter[String]
  private val intCodec = upickle.default.readwriter[Int]
  import upickle.{default => up}
  opaque type SemanticTokenTypes = String
  object SemanticTokenTypes:
    given codec: up.ReadWriter[SemanticTokenTypes] = stringCodec.asInstanceOf[up.ReadWriter[SemanticTokenTypes]]
    inline def namespace = entry("namespace")
    inline def `type` = entry("type")
    inline def `class` = entry("class")
    inline def `enum` = entry("enum")
    inline def interface = entry("interface")
    inline def struct = entry("struct")
    inline def typeParameter = entry("typeParameter")
    inline def parameter = entry("parameter")
    inline def variable = entry("variable")
    inline def property = entry("property")
    inline def enumMember = entry("enumMember")
    inline def event = entry("event")
    inline def function = entry("function")
    inline def method = entry("method")
    inline def `macro` = entry("macro")
    inline def keyword = entry("keyword")
    inline def modifier = entry("modifier")
    inline def comment = entry("comment")
    inline def string = entry("string")
    inline def number = entry("number")
    inline def regexp = entry("regexp")
    inline def operator = entry("operator")
    inline def decorator = entry("decorator")
    
    private inline def entry(v: String): SemanticTokenTypes = v
  
  opaque type SemanticTokenModifiers = String
  object SemanticTokenModifiers:
    given codec: up.ReadWriter[SemanticTokenModifiers] = stringCodec.asInstanceOf[up.ReadWriter[SemanticTokenModifiers]]
    inline def declaration = entry("declaration")
    inline def definition = entry("definition")
    inline def readonly = entry("readonly")
    inline def static = entry("static")
    inline def deprecated = entry("deprecated")
    inline def `abstract` = entry("abstract")
    inline def async = entry("async")
    inline def modification = entry("modification")
    inline def documentation = entry("documentation")
    inline def defaultLibrary = entry("defaultLibrary")
    
    private inline def entry(v: String): SemanticTokenModifiers = v
  
  opaque type ErrorCodes = Int
  object ErrorCodes:
    given codec: up.ReadWriter[ErrorCodes] = intCodec.asInstanceOf[up.ReadWriter[ErrorCodes]]
    inline def ParseError = entry(-32700)
    inline def InvalidRequest = entry(-32600)
    inline def MethodNotFound = entry(-32601)
    inline def InvalidParams = entry(-32602)
    inline def InternalError = entry(-32603)
    inline def jsonrpcReservedErrorRangeStart = entry(-32099)
    inline def serverErrorStart = entry(-32099)
    inline def ServerNotInitialized = entry(-32002)
    inline def UnknownErrorCode = entry(-32001)
    inline def jsonrpcReservedErrorRangeEnd = entry(-32000)
    inline def serverErrorEnd = entry(-32000)
    
    private inline def entry(v: Int): ErrorCodes = v
  
  opaque type LSPErrorCodes = Int
  object LSPErrorCodes:
    given codec: up.ReadWriter[LSPErrorCodes] = intCodec.asInstanceOf[up.ReadWriter[LSPErrorCodes]]
    inline def lspReservedErrorRangeStart = entry(-32899)
    inline def RequestFailed = entry(-32803)
    inline def ServerCancelled = entry(-32802)
    inline def ContentModified = entry(-32801)
    inline def RequestCancelled = entry(-32800)
    inline def lspReservedErrorRangeEnd = entry(-32800)
    
    private inline def entry(v: Int): LSPErrorCodes = v
  
  opaque type FoldingRangeKind = String
  object FoldingRangeKind:
    given codec: up.ReadWriter[FoldingRangeKind] = stringCodec.asInstanceOf[up.ReadWriter[FoldingRangeKind]]
    inline def Comment = entry("comment")
    inline def Imports = entry("imports")
    inline def Region = entry("region")
    
    private inline def entry(v: String): FoldingRangeKind = v
  
  opaque type SymbolKind = RuntimeBase.uinteger
  object SymbolKind:
    given codec: up.ReadWriter[SymbolKind] = intCodec.asInstanceOf[up.ReadWriter[SymbolKind]]
    inline def File = entry(1)
    inline def Module = entry(2)
    inline def Namespace = entry(3)
    inline def Package = entry(4)
    inline def Class = entry(5)
    inline def Method = entry(6)
    inline def Property = entry(7)
    inline def Field = entry(8)
    inline def Constructor = entry(9)
    inline def Enum = entry(10)
    inline def Interface = entry(11)
    inline def Function = entry(12)
    inline def Variable = entry(13)
    inline def Constant = entry(14)
    inline def String = entry(15)
    inline def Number = entry(16)
    inline def Boolean = entry(17)
    inline def Array = entry(18)
    inline def Object = entry(19)
    inline def Key = entry(20)
    inline def Null = entry(21)
    inline def EnumMember = entry(22)
    inline def Struct = entry(23)
    inline def Event = entry(24)
    inline def Operator = entry(25)
    inline def TypeParameter = entry(26)
    
    private inline def entry(n: Int): SymbolKind = RuntimeBase.uinteger(n)
  
  opaque type SymbolTag = RuntimeBase.uinteger
  object SymbolTag:
    given codec: up.ReadWriter[SymbolTag] = intCodec.asInstanceOf[up.ReadWriter[SymbolTag]]
    inline def Deprecated = entry(1)
    
    private inline def entry(n: Int): SymbolTag = RuntimeBase.uinteger(n)
  
  opaque type UniquenessLevel = String
  object UniquenessLevel:
    given codec: up.ReadWriter[UniquenessLevel] = stringCodec.asInstanceOf[up.ReadWriter[UniquenessLevel]]
    inline def document = entry("document")
    inline def project = entry("project")
    inline def group = entry("group")
    inline def scheme = entry("scheme")
    inline def global = entry("global")
    
    private inline def entry(v: String): UniquenessLevel = v
  
  opaque type MonikerKind = String
  object MonikerKind:
    given codec: up.ReadWriter[MonikerKind] = stringCodec.asInstanceOf[up.ReadWriter[MonikerKind]]
    inline def `import` = entry("import")
    inline def `export` = entry("export")
    inline def local = entry("local")
    
    private inline def entry(v: String): MonikerKind = v
  
  opaque type InlayHintKind = RuntimeBase.uinteger
  object InlayHintKind:
    given codec: up.ReadWriter[InlayHintKind] = intCodec.asInstanceOf[up.ReadWriter[InlayHintKind]]
    inline def Type = entry(1)
    inline def Parameter = entry(2)
    
    private inline def entry(n: Int): InlayHintKind = RuntimeBase.uinteger(n)
  
  opaque type MessageType = RuntimeBase.uinteger
  object MessageType:
    given codec: up.ReadWriter[MessageType] = intCodec.asInstanceOf[up.ReadWriter[MessageType]]
    inline def Error = entry(1)
    inline def Warning = entry(2)
    inline def Info = entry(3)
    inline def Log = entry(4)
    
    private inline def entry(n: Int): MessageType = RuntimeBase.uinteger(n)
  
  opaque type TextDocumentSyncKind = RuntimeBase.uinteger
  object TextDocumentSyncKind:
    given codec: up.ReadWriter[TextDocumentSyncKind] = intCodec.asInstanceOf[up.ReadWriter[TextDocumentSyncKind]]
    inline def None = entry(0)
    inline def Full = entry(1)
    inline def Incremental = entry(2)
    
    private inline def entry(n: Int): TextDocumentSyncKind = RuntimeBase.uinteger(n)
  
  opaque type TextDocumentSaveReason = RuntimeBase.uinteger
  object TextDocumentSaveReason:
    given codec: up.ReadWriter[TextDocumentSaveReason] = intCodec.asInstanceOf[up.ReadWriter[TextDocumentSaveReason]]
    inline def Manual = entry(1)
    inline def AfterDelay = entry(2)
    inline def FocusOut = entry(3)
    
    private inline def entry(n: Int): TextDocumentSaveReason = RuntimeBase.uinteger(n)
  
  opaque type CompletionItemKind = RuntimeBase.uinteger
  object CompletionItemKind:
    given codec: up.ReadWriter[CompletionItemKind] = intCodec.asInstanceOf[up.ReadWriter[CompletionItemKind]]
    inline def Text = entry(1)
    inline def Method = entry(2)
    inline def Function = entry(3)
    inline def Constructor = entry(4)
    inline def Field = entry(5)
    inline def Variable = entry(6)
    inline def Class = entry(7)
    inline def Interface = entry(8)
    inline def Module = entry(9)
    inline def Property = entry(10)
    inline def Unit = entry(11)
    inline def Value = entry(12)
    inline def Enum = entry(13)
    inline def Keyword = entry(14)
    inline def Snippet = entry(15)
    inline def Color = entry(16)
    inline def File = entry(17)
    inline def Reference = entry(18)
    inline def Folder = entry(19)
    inline def EnumMember = entry(20)
    inline def Constant = entry(21)
    inline def Struct = entry(22)
    inline def Event = entry(23)
    inline def Operator = entry(24)
    inline def TypeParameter = entry(25)
    
    private inline def entry(n: Int): CompletionItemKind = RuntimeBase.uinteger(n)
  
  opaque type CompletionItemTag = RuntimeBase.uinteger
  object CompletionItemTag:
    given codec: up.ReadWriter[CompletionItemTag] = intCodec.asInstanceOf[up.ReadWriter[CompletionItemTag]]
    inline def Deprecated = entry(1)
    
    private inline def entry(n: Int): CompletionItemTag = RuntimeBase.uinteger(n)
  
  opaque type InsertTextFormat = RuntimeBase.uinteger
  object InsertTextFormat:
    given codec: up.ReadWriter[InsertTextFormat] = intCodec.asInstanceOf[up.ReadWriter[InsertTextFormat]]
    inline def PlainText = entry(1)
    inline def Snippet = entry(2)
    
    private inline def entry(n: Int): InsertTextFormat = RuntimeBase.uinteger(n)
  
  opaque type InsertTextMode = RuntimeBase.uinteger
  object InsertTextMode:
    given codec: up.ReadWriter[InsertTextMode] = intCodec.asInstanceOf[up.ReadWriter[InsertTextMode]]
    inline def asIs = entry(1)
    inline def adjustIndentation = entry(2)
    
    private inline def entry(n: Int): InsertTextMode = RuntimeBase.uinteger(n)
  
  opaque type DocumentHighlightKind = RuntimeBase.uinteger
  object DocumentHighlightKind:
    given codec: up.ReadWriter[DocumentHighlightKind] = intCodec.asInstanceOf[up.ReadWriter[DocumentHighlightKind]]
    inline def Text = entry(1)
    inline def Read = entry(2)
    inline def Write = entry(3)
    
    private inline def entry(n: Int): DocumentHighlightKind = RuntimeBase.uinteger(n)
  
  opaque type CodeActionKind = String
  object CodeActionKind:
    given codec: up.ReadWriter[CodeActionKind] = stringCodec.asInstanceOf[up.ReadWriter[CodeActionKind]]
    inline def Empty = entry("")
    inline def QuickFix = entry("quickfix")
    inline def Refactor = entry("refactor")
    inline def RefactorExtract = entry("refactor.extract")
    inline def RefactorInline = entry("refactor.inline")
    inline def RefactorRewrite = entry("refactor.rewrite")
    inline def Source = entry("source")
    inline def SourceOrganizeImports = entry("source.organizeImports")
    inline def SourceFixAll = entry("source.fixAll")
    
    private inline def entry(v: String): CodeActionKind = v
  
  opaque type TraceValues = String
  object TraceValues:
    given codec: up.ReadWriter[TraceValues] = stringCodec.asInstanceOf[up.ReadWriter[TraceValues]]
    inline def Off = entry("off")
    inline def Messages = entry("messages")
    inline def Verbose = entry("verbose")
    
    private inline def entry(v: String): TraceValues = v
  
  opaque type MarkupKind = String
  object MarkupKind:
    given codec: up.ReadWriter[MarkupKind] = stringCodec.asInstanceOf[up.ReadWriter[MarkupKind]]
    inline def PlainText = entry("plaintext")
    inline def Markdown = entry("markdown")
    
    private inline def entry(v: String): MarkupKind = v
  
  opaque type PositionEncodingKind = String
  object PositionEncodingKind:
    given codec: up.ReadWriter[PositionEncodingKind] = stringCodec.asInstanceOf[up.ReadWriter[PositionEncodingKind]]
    inline def UTF8 = entry("utf-8")
    inline def UTF16 = entry("utf-16")
    inline def UTF32 = entry("utf-32")
    
    private inline def entry(v: String): PositionEncodingKind = v
  
  opaque type FileChangeType = RuntimeBase.uinteger
  object FileChangeType:
    given codec: up.ReadWriter[FileChangeType] = intCodec.asInstanceOf[up.ReadWriter[FileChangeType]]
    inline def Created = entry(1)
    inline def Changed = entry(2)
    inline def Deleted = entry(3)
    
    private inline def entry(n: Int): FileChangeType = RuntimeBase.uinteger(n)
  
  opaque type WatchKind = RuntimeBase.uinteger
  object WatchKind:
    given codec: up.ReadWriter[WatchKind] = intCodec.asInstanceOf[up.ReadWriter[WatchKind]]
    inline def Create = entry(1)
    inline def Change = entry(2)
    inline def Delete = entry(4)
    
    private inline def entry(n: Int): WatchKind = RuntimeBase.uinteger(n)
  
  opaque type DiagnosticSeverity = RuntimeBase.uinteger
  object DiagnosticSeverity:
    given codec: up.ReadWriter[DiagnosticSeverity] = intCodec.asInstanceOf[up.ReadWriter[DiagnosticSeverity]]
    inline def Error = entry(1)
    inline def Warning = entry(2)
    inline def Information = entry(3)
    inline def Hint = entry(4)
    
    private inline def entry(n: Int): DiagnosticSeverity = RuntimeBase.uinteger(n)
  
  opaque type DiagnosticTag = RuntimeBase.uinteger
  object DiagnosticTag:
    given codec: up.ReadWriter[DiagnosticTag] = intCodec.asInstanceOf[up.ReadWriter[DiagnosticTag]]
    inline def Unnecessary = entry(1)
    inline def Deprecated = entry(2)
    
    private inline def entry(n: Int): DiagnosticTag = RuntimeBase.uinteger(n)
  
  opaque type CompletionTriggerKind = RuntimeBase.uinteger
  object CompletionTriggerKind:
    given codec: up.ReadWriter[CompletionTriggerKind] = intCodec.asInstanceOf[up.ReadWriter[CompletionTriggerKind]]
    inline def Invoked = entry(1)
    inline def TriggerCharacter = entry(2)
    inline def TriggerForIncompleteCompletions = entry(3)
    
    private inline def entry(n: Int): CompletionTriggerKind = RuntimeBase.uinteger(n)
  
  opaque type SignatureHelpTriggerKind = RuntimeBase.uinteger
  object SignatureHelpTriggerKind:
    given codec: up.ReadWriter[SignatureHelpTriggerKind] = intCodec.asInstanceOf[up.ReadWriter[SignatureHelpTriggerKind]]
    inline def Invoked = entry(1)
    inline def TriggerCharacter = entry(2)
    inline def ContentChange = entry(3)
    
    private inline def entry(n: Int): SignatureHelpTriggerKind = RuntimeBase.uinteger(n)
  
  opaque type CodeActionTriggerKind = RuntimeBase.uinteger
  object CodeActionTriggerKind:
    given codec: up.ReadWriter[CodeActionTriggerKind] = intCodec.asInstanceOf[up.ReadWriter[CodeActionTriggerKind]]
    inline def Invoked = entry(1)
    inline def Automatic = entry(2)
    
    private inline def entry(n: Int): CodeActionTriggerKind = RuntimeBase.uinteger(n)
  
  opaque type FileOperationPatternKind = String
  object FileOperationPatternKind:
    given codec: up.ReadWriter[FileOperationPatternKind] = stringCodec.asInstanceOf[up.ReadWriter[FileOperationPatternKind]]
    inline def file = entry("file")
    inline def folder = entry("folder")
    
    private inline def entry(v: String): FileOperationPatternKind = v
  
  opaque type NotebookCellKind = RuntimeBase.uinteger
  object NotebookCellKind:
    given codec: up.ReadWriter[NotebookCellKind] = intCodec.asInstanceOf[up.ReadWriter[NotebookCellKind]]
    inline def Markup = entry(1)
    inline def Code = entry(2)
    
    private inline def entry(n: Int): NotebookCellKind = RuntimeBase.uinteger(n)
  
  opaque type ResourceOperationKind = String
  object ResourceOperationKind:
    given codec: up.ReadWriter[ResourceOperationKind] = stringCodec.asInstanceOf[up.ReadWriter[ResourceOperationKind]]
    inline def Create = entry("create")
    inline def Rename = entry("rename")
    inline def Delete = entry("delete")
    
    private inline def entry(v: String): ResourceOperationKind = v
  
  opaque type FailureHandlingKind = String
  object FailureHandlingKind:
    given codec: up.ReadWriter[FailureHandlingKind] = stringCodec.asInstanceOf[up.ReadWriter[FailureHandlingKind]]
    inline def Abort = entry("abort")
    inline def Transactional = entry("transactional")
    inline def TextOnlyTransactional = entry("textOnlyTransactional")
    inline def Undo = entry("undo")
    
    private inline def entry(v: String): FailureHandlingKind = v
  
  opaque type PrepareSupportDefaultBehavior = RuntimeBase.uinteger
  object PrepareSupportDefaultBehavior:
    given codec: up.ReadWriter[PrepareSupportDefaultBehavior] = intCodec.asInstanceOf[up.ReadWriter[PrepareSupportDefaultBehavior]]
    inline def Identifier = entry(1)
    
    private inline def entry(n: Int): PrepareSupportDefaultBehavior = RuntimeBase.uinteger(n)
  
  opaque type TokenFormat = String
  object TokenFormat:
    given codec: up.ReadWriter[TokenFormat] = stringCodec.asInstanceOf[up.ReadWriter[TokenFormat]]
    inline def Relative = entry("relative")
    
    private inline def entry(v: String): TokenFormat = v
  
