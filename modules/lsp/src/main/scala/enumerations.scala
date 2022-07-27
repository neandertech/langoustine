package langoustine.lsp

import langoustine.*
import scala.reflect.Typeable

object enumerations: 
  private val stringCodec = upickle.default.readwriter[String]
  private val intCodec = upickle.default.readwriter[Int]
  import upickle.{default => up}
  opaque type SemanticTokenTypes = String
  object SemanticTokenTypes:
    given reader: up.Reader[SemanticTokenTypes] = stringCodec.asInstanceOf[up.Reader[SemanticTokenTypes]]
    given writer: up.Writer[SemanticTokenTypes] = stringCodec.asInstanceOf[up.Writer[SemanticTokenTypes]]
    given Typeable[SemanticTokenTypes] with
      def unapply(s: Any): Option[s.type & SemanticTokenTypes] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
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
    given reader: up.Reader[SemanticTokenModifiers] = stringCodec.asInstanceOf[up.Reader[SemanticTokenModifiers]]
    given writer: up.Writer[SemanticTokenModifiers] = stringCodec.asInstanceOf[up.Writer[SemanticTokenModifiers]]
    given Typeable[SemanticTokenModifiers] with
      def unapply(s: Any): Option[s.type & SemanticTokenModifiers] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
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
    given reader: up.Reader[ErrorCodes] = intCodec.asInstanceOf[up.Reader[ErrorCodes]]
    given writer: up.Writer[ErrorCodes] = intCodec.asInstanceOf[up.Writer[ErrorCodes]]
    given Typeable[ErrorCodes] with
      def unapply(s: Any): Option[s.type & ErrorCodes] = 
        s match
        case c: Int => Some(c.asInstanceOf[s.type & Int])
        case _ => Option.empty
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
    given reader: up.Reader[LSPErrorCodes] = intCodec.asInstanceOf[up.Reader[LSPErrorCodes]]
    given writer: up.Writer[LSPErrorCodes] = intCodec.asInstanceOf[up.Writer[LSPErrorCodes]]
    given Typeable[LSPErrorCodes] with
      def unapply(s: Any): Option[s.type & LSPErrorCodes] = 
        s match
        case c: Int => Some(c.asInstanceOf[s.type & Int])
        case _ => Option.empty
    inline def lspReservedErrorRangeStart = entry(-32899)
    inline def RequestFailed = entry(-32803)
    inline def ServerCancelled = entry(-32802)
    inline def ContentModified = entry(-32801)
    inline def RequestCancelled = entry(-32800)
    inline def lspReservedErrorRangeEnd = entry(-32800)
    
    private inline def entry(v: Int): LSPErrorCodes = v
  
  opaque type FoldingRangeKind = String
  object FoldingRangeKind:
    given reader: up.Reader[FoldingRangeKind] = stringCodec.asInstanceOf[up.Reader[FoldingRangeKind]]
    given writer: up.Writer[FoldingRangeKind] = stringCodec.asInstanceOf[up.Writer[FoldingRangeKind]]
    given Typeable[FoldingRangeKind] with
      def unapply(s: Any): Option[s.type & FoldingRangeKind] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
    inline def Comment = entry("comment")
    inline def Imports = entry("imports")
    inline def Region = entry("region")
    
    private inline def entry(v: String): FoldingRangeKind = v
  
  opaque type SymbolKind = RuntimeBase.uinteger
  object SymbolKind:
    given reader: up.Reader[SymbolKind] = intCodec.asInstanceOf[up.Reader[SymbolKind]]
    given writer: up.Writer[SymbolKind] = intCodec.asInstanceOf[up.Writer[SymbolKind]]
    given Typeable[SymbolKind] with
      def unapply(s: Any): Option[s.type & SymbolKind] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
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
    given reader: up.Reader[SymbolTag] = intCodec.asInstanceOf[up.Reader[SymbolTag]]
    given writer: up.Writer[SymbolTag] = intCodec.asInstanceOf[up.Writer[SymbolTag]]
    given Typeable[SymbolTag] with
      def unapply(s: Any): Option[s.type & SymbolTag] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
    inline def Deprecated = entry(1)
    
    private inline def entry(n: Int): SymbolTag = RuntimeBase.uinteger(n)
  
  opaque type UniquenessLevel = String
  object UniquenessLevel:
    given reader: up.Reader[UniquenessLevel] = stringCodec.asInstanceOf[up.Reader[UniquenessLevel]]
    given writer: up.Writer[UniquenessLevel] = stringCodec.asInstanceOf[up.Writer[UniquenessLevel]]
    given Typeable[UniquenessLevel] with
      def unapply(s: Any): Option[s.type & UniquenessLevel] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
    inline def document = entry("document")
    inline def project = entry("project")
    inline def group = entry("group")
    inline def scheme = entry("scheme")
    inline def global = entry("global")
    
    private inline def entry(v: String): UniquenessLevel = v
  
  opaque type MonikerKind = String
  object MonikerKind:
    given reader: up.Reader[MonikerKind] = stringCodec.asInstanceOf[up.Reader[MonikerKind]]
    given writer: up.Writer[MonikerKind] = stringCodec.asInstanceOf[up.Writer[MonikerKind]]
    given Typeable[MonikerKind] with
      def unapply(s: Any): Option[s.type & MonikerKind] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
    inline def `import` = entry("import")
    inline def `export` = entry("export")
    inline def local = entry("local")
    
    private inline def entry(v: String): MonikerKind = v
  
  opaque type InlayHintKind = RuntimeBase.uinteger
  object InlayHintKind:
    given reader: up.Reader[InlayHintKind] = intCodec.asInstanceOf[up.Reader[InlayHintKind]]
    given writer: up.Writer[InlayHintKind] = intCodec.asInstanceOf[up.Writer[InlayHintKind]]
    given Typeable[InlayHintKind] with
      def unapply(s: Any): Option[s.type & InlayHintKind] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
    inline def Type = entry(1)
    inline def Parameter = entry(2)
    
    private inline def entry(n: Int): InlayHintKind = RuntimeBase.uinteger(n)
  
  opaque type MessageType = RuntimeBase.uinteger
  object MessageType:
    given reader: up.Reader[MessageType] = intCodec.asInstanceOf[up.Reader[MessageType]]
    given writer: up.Writer[MessageType] = intCodec.asInstanceOf[up.Writer[MessageType]]
    given Typeable[MessageType] with
      def unapply(s: Any): Option[s.type & MessageType] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
    inline def Error = entry(1)
    inline def Warning = entry(2)
    inline def Info = entry(3)
    inline def Log = entry(4)
    
    private inline def entry(n: Int): MessageType = RuntimeBase.uinteger(n)
  
  opaque type TextDocumentSyncKind = RuntimeBase.uinteger
  object TextDocumentSyncKind:
    given reader: up.Reader[TextDocumentSyncKind] = intCodec.asInstanceOf[up.Reader[TextDocumentSyncKind]]
    given writer: up.Writer[TextDocumentSyncKind] = intCodec.asInstanceOf[up.Writer[TextDocumentSyncKind]]
    given Typeable[TextDocumentSyncKind] with
      def unapply(s: Any): Option[s.type & TextDocumentSyncKind] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
    inline def None = entry(0)
    inline def Full = entry(1)
    inline def Incremental = entry(2)
    
    private inline def entry(n: Int): TextDocumentSyncKind = RuntimeBase.uinteger(n)
  
  opaque type TextDocumentSaveReason = RuntimeBase.uinteger
  object TextDocumentSaveReason:
    given reader: up.Reader[TextDocumentSaveReason] = intCodec.asInstanceOf[up.Reader[TextDocumentSaveReason]]
    given writer: up.Writer[TextDocumentSaveReason] = intCodec.asInstanceOf[up.Writer[TextDocumentSaveReason]]
    given Typeable[TextDocumentSaveReason] with
      def unapply(s: Any): Option[s.type & TextDocumentSaveReason] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
    inline def Manual = entry(1)
    inline def AfterDelay = entry(2)
    inline def FocusOut = entry(3)
    
    private inline def entry(n: Int): TextDocumentSaveReason = RuntimeBase.uinteger(n)
  
  opaque type CompletionItemKind = RuntimeBase.uinteger
  object CompletionItemKind:
    given reader: up.Reader[CompletionItemKind] = intCodec.asInstanceOf[up.Reader[CompletionItemKind]]
    given writer: up.Writer[CompletionItemKind] = intCodec.asInstanceOf[up.Writer[CompletionItemKind]]
    given Typeable[CompletionItemKind] with
      def unapply(s: Any): Option[s.type & CompletionItemKind] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
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
    given reader: up.Reader[CompletionItemTag] = intCodec.asInstanceOf[up.Reader[CompletionItemTag]]
    given writer: up.Writer[CompletionItemTag] = intCodec.asInstanceOf[up.Writer[CompletionItemTag]]
    given Typeable[CompletionItemTag] with
      def unapply(s: Any): Option[s.type & CompletionItemTag] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
    inline def Deprecated = entry(1)
    
    private inline def entry(n: Int): CompletionItemTag = RuntimeBase.uinteger(n)
  
  opaque type InsertTextFormat = RuntimeBase.uinteger
  object InsertTextFormat:
    given reader: up.Reader[InsertTextFormat] = intCodec.asInstanceOf[up.Reader[InsertTextFormat]]
    given writer: up.Writer[InsertTextFormat] = intCodec.asInstanceOf[up.Writer[InsertTextFormat]]
    given Typeable[InsertTextFormat] with
      def unapply(s: Any): Option[s.type & InsertTextFormat] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
    inline def PlainText = entry(1)
    inline def Snippet = entry(2)
    
    private inline def entry(n: Int): InsertTextFormat = RuntimeBase.uinteger(n)
  
  opaque type InsertTextMode = RuntimeBase.uinteger
  object InsertTextMode:
    given reader: up.Reader[InsertTextMode] = intCodec.asInstanceOf[up.Reader[InsertTextMode]]
    given writer: up.Writer[InsertTextMode] = intCodec.asInstanceOf[up.Writer[InsertTextMode]]
    given Typeable[InsertTextMode] with
      def unapply(s: Any): Option[s.type & InsertTextMode] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
    inline def asIs = entry(1)
    inline def adjustIndentation = entry(2)
    
    private inline def entry(n: Int): InsertTextMode = RuntimeBase.uinteger(n)
  
  opaque type DocumentHighlightKind = RuntimeBase.uinteger
  object DocumentHighlightKind:
    given reader: up.Reader[DocumentHighlightKind] = intCodec.asInstanceOf[up.Reader[DocumentHighlightKind]]
    given writer: up.Writer[DocumentHighlightKind] = intCodec.asInstanceOf[up.Writer[DocumentHighlightKind]]
    given Typeable[DocumentHighlightKind] with
      def unapply(s: Any): Option[s.type & DocumentHighlightKind] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
    inline def Text = entry(1)
    inline def Read = entry(2)
    inline def Write = entry(3)
    
    private inline def entry(n: Int): DocumentHighlightKind = RuntimeBase.uinteger(n)
  
  opaque type CodeActionKind = String
  object CodeActionKind:
    given reader: up.Reader[CodeActionKind] = stringCodec.asInstanceOf[up.Reader[CodeActionKind]]
    given writer: up.Writer[CodeActionKind] = stringCodec.asInstanceOf[up.Writer[CodeActionKind]]
    given Typeable[CodeActionKind] with
      def unapply(s: Any): Option[s.type & CodeActionKind] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
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
    given reader: up.Reader[TraceValues] = stringCodec.asInstanceOf[up.Reader[TraceValues]]
    given writer: up.Writer[TraceValues] = stringCodec.asInstanceOf[up.Writer[TraceValues]]
    given Typeable[TraceValues] with
      def unapply(s: Any): Option[s.type & TraceValues] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
    inline def Off = entry("off")
    inline def Messages = entry("messages")
    inline def Verbose = entry("verbose")
    
    private inline def entry(v: String): TraceValues = v
  
  opaque type MarkupKind = String
  object MarkupKind:
    given reader: up.Reader[MarkupKind] = stringCodec.asInstanceOf[up.Reader[MarkupKind]]
    given writer: up.Writer[MarkupKind] = stringCodec.asInstanceOf[up.Writer[MarkupKind]]
    given Typeable[MarkupKind] with
      def unapply(s: Any): Option[s.type & MarkupKind] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
    inline def PlainText = entry("plaintext")
    inline def Markdown = entry("markdown")
    
    private inline def entry(v: String): MarkupKind = v
  
  opaque type PositionEncodingKind = String
  object PositionEncodingKind:
    given reader: up.Reader[PositionEncodingKind] = stringCodec.asInstanceOf[up.Reader[PositionEncodingKind]]
    given writer: up.Writer[PositionEncodingKind] = stringCodec.asInstanceOf[up.Writer[PositionEncodingKind]]
    given Typeable[PositionEncodingKind] with
      def unapply(s: Any): Option[s.type & PositionEncodingKind] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
    inline def UTF8 = entry("utf-8")
    inline def UTF16 = entry("utf-16")
    inline def UTF32 = entry("utf-32")
    
    private inline def entry(v: String): PositionEncodingKind = v
  
  opaque type FileChangeType = RuntimeBase.uinteger
  object FileChangeType:
    given reader: up.Reader[FileChangeType] = intCodec.asInstanceOf[up.Reader[FileChangeType]]
    given writer: up.Writer[FileChangeType] = intCodec.asInstanceOf[up.Writer[FileChangeType]]
    given Typeable[FileChangeType] with
      def unapply(s: Any): Option[s.type & FileChangeType] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
    inline def Created = entry(1)
    inline def Changed = entry(2)
    inline def Deleted = entry(3)
    
    private inline def entry(n: Int): FileChangeType = RuntimeBase.uinteger(n)
  
  opaque type WatchKind = RuntimeBase.uinteger
  object WatchKind:
    given reader: up.Reader[WatchKind] = intCodec.asInstanceOf[up.Reader[WatchKind]]
    given writer: up.Writer[WatchKind] = intCodec.asInstanceOf[up.Writer[WatchKind]]
    given Typeable[WatchKind] with
      def unapply(s: Any): Option[s.type & WatchKind] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
    inline def Create = entry(1)
    inline def Change = entry(2)
    inline def Delete = entry(4)
    
    private inline def entry(n: Int): WatchKind = RuntimeBase.uinteger(n)
  
  opaque type DiagnosticSeverity = RuntimeBase.uinteger
  object DiagnosticSeverity:
    given reader: up.Reader[DiagnosticSeverity] = intCodec.asInstanceOf[up.Reader[DiagnosticSeverity]]
    given writer: up.Writer[DiagnosticSeverity] = intCodec.asInstanceOf[up.Writer[DiagnosticSeverity]]
    given Typeable[DiagnosticSeverity] with
      def unapply(s: Any): Option[s.type & DiagnosticSeverity] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
    inline def Error = entry(1)
    inline def Warning = entry(2)
    inline def Information = entry(3)
    inline def Hint = entry(4)
    
    private inline def entry(n: Int): DiagnosticSeverity = RuntimeBase.uinteger(n)
  
  opaque type DiagnosticTag = RuntimeBase.uinteger
  object DiagnosticTag:
    given reader: up.Reader[DiagnosticTag] = intCodec.asInstanceOf[up.Reader[DiagnosticTag]]
    given writer: up.Writer[DiagnosticTag] = intCodec.asInstanceOf[up.Writer[DiagnosticTag]]
    given Typeable[DiagnosticTag] with
      def unapply(s: Any): Option[s.type & DiagnosticTag] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
    inline def Unnecessary = entry(1)
    inline def Deprecated = entry(2)
    
    private inline def entry(n: Int): DiagnosticTag = RuntimeBase.uinteger(n)
  
  opaque type CompletionTriggerKind = RuntimeBase.uinteger
  object CompletionTriggerKind:
    given reader: up.Reader[CompletionTriggerKind] = intCodec.asInstanceOf[up.Reader[CompletionTriggerKind]]
    given writer: up.Writer[CompletionTriggerKind] = intCodec.asInstanceOf[up.Writer[CompletionTriggerKind]]
    given Typeable[CompletionTriggerKind] with
      def unapply(s: Any): Option[s.type & CompletionTriggerKind] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
    inline def Invoked = entry(1)
    inline def TriggerCharacter = entry(2)
    inline def TriggerForIncompleteCompletions = entry(3)
    
    private inline def entry(n: Int): CompletionTriggerKind = RuntimeBase.uinteger(n)
  
  opaque type SignatureHelpTriggerKind = RuntimeBase.uinteger
  object SignatureHelpTriggerKind:
    given reader: up.Reader[SignatureHelpTriggerKind] = intCodec.asInstanceOf[up.Reader[SignatureHelpTriggerKind]]
    given writer: up.Writer[SignatureHelpTriggerKind] = intCodec.asInstanceOf[up.Writer[SignatureHelpTriggerKind]]
    given Typeable[SignatureHelpTriggerKind] with
      def unapply(s: Any): Option[s.type & SignatureHelpTriggerKind] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
    inline def Invoked = entry(1)
    inline def TriggerCharacter = entry(2)
    inline def ContentChange = entry(3)
    
    private inline def entry(n: Int): SignatureHelpTriggerKind = RuntimeBase.uinteger(n)
  
  opaque type CodeActionTriggerKind = RuntimeBase.uinteger
  object CodeActionTriggerKind:
    given reader: up.Reader[CodeActionTriggerKind] = intCodec.asInstanceOf[up.Reader[CodeActionTriggerKind]]
    given writer: up.Writer[CodeActionTriggerKind] = intCodec.asInstanceOf[up.Writer[CodeActionTriggerKind]]
    given Typeable[CodeActionTriggerKind] with
      def unapply(s: Any): Option[s.type & CodeActionTriggerKind] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
    inline def Invoked = entry(1)
    inline def Automatic = entry(2)
    
    private inline def entry(n: Int): CodeActionTriggerKind = RuntimeBase.uinteger(n)
  
  opaque type FileOperationPatternKind = String
  object FileOperationPatternKind:
    given reader: up.Reader[FileOperationPatternKind] = stringCodec.asInstanceOf[up.Reader[FileOperationPatternKind]]
    given writer: up.Writer[FileOperationPatternKind] = stringCodec.asInstanceOf[up.Writer[FileOperationPatternKind]]
    given Typeable[FileOperationPatternKind] with
      def unapply(s: Any): Option[s.type & FileOperationPatternKind] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
    inline def file = entry("file")
    inline def folder = entry("folder")
    
    private inline def entry(v: String): FileOperationPatternKind = v
  
  opaque type NotebookCellKind = RuntimeBase.uinteger
  object NotebookCellKind:
    given reader: up.Reader[NotebookCellKind] = intCodec.asInstanceOf[up.Reader[NotebookCellKind]]
    given writer: up.Writer[NotebookCellKind] = intCodec.asInstanceOf[up.Writer[NotebookCellKind]]
    given Typeable[NotebookCellKind] with
      def unapply(s: Any): Option[s.type & NotebookCellKind] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
    inline def Markup = entry(1)
    inline def Code = entry(2)
    
    private inline def entry(n: Int): NotebookCellKind = RuntimeBase.uinteger(n)
  
  opaque type ResourceOperationKind = String
  object ResourceOperationKind:
    given reader: up.Reader[ResourceOperationKind] = stringCodec.asInstanceOf[up.Reader[ResourceOperationKind]]
    given writer: up.Writer[ResourceOperationKind] = stringCodec.asInstanceOf[up.Writer[ResourceOperationKind]]
    given Typeable[ResourceOperationKind] with
      def unapply(s: Any): Option[s.type & ResourceOperationKind] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
    inline def Create = entry("create")
    inline def Rename = entry("rename")
    inline def Delete = entry("delete")
    
    private inline def entry(v: String): ResourceOperationKind = v
  
  opaque type FailureHandlingKind = String
  object FailureHandlingKind:
    given reader: up.Reader[FailureHandlingKind] = stringCodec.asInstanceOf[up.Reader[FailureHandlingKind]]
    given writer: up.Writer[FailureHandlingKind] = stringCodec.asInstanceOf[up.Writer[FailureHandlingKind]]
    given Typeable[FailureHandlingKind] with
      def unapply(s: Any): Option[s.type & FailureHandlingKind] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
    inline def Abort = entry("abort")
    inline def Transactional = entry("transactional")
    inline def TextOnlyTransactional = entry("textOnlyTransactional")
    inline def Undo = entry("undo")
    
    private inline def entry(v: String): FailureHandlingKind = v
  
  opaque type PrepareSupportDefaultBehavior = RuntimeBase.uinteger
  object PrepareSupportDefaultBehavior:
    given reader: up.Reader[PrepareSupportDefaultBehavior] = intCodec.asInstanceOf[up.Reader[PrepareSupportDefaultBehavior]]
    given writer: up.Writer[PrepareSupportDefaultBehavior] = intCodec.asInstanceOf[up.Writer[PrepareSupportDefaultBehavior]]
    given Typeable[PrepareSupportDefaultBehavior] with
      def unapply(s: Any): Option[s.type & PrepareSupportDefaultBehavior] = 
        s match
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case _ => Option.empty
    inline def Identifier = entry(1)
    
    private inline def entry(n: Int): PrepareSupportDefaultBehavior = RuntimeBase.uinteger(n)
  
  opaque type TokenFormat = String
  object TokenFormat:
    given reader: up.Reader[TokenFormat] = stringCodec.asInstanceOf[up.Reader[TokenFormat]]
    given writer: up.Writer[TokenFormat] = stringCodec.asInstanceOf[up.Writer[TokenFormat]]
    given Typeable[TokenFormat] with
      def unapply(s: Any): Option[s.type & TokenFormat] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
    inline def Relative = entry("relative")
    
    private inline def entry(v: String): TokenFormat = v
  
