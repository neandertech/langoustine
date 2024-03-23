/*
 * Copyright 2022 Neandertech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package langoustine.lsp
package enumerations

import runtime.{*, given}
import json.{*, given}
import scala.reflect.Typeable
// format: off

private val stringCodec = upickle.default.readwriter[String]
private val intCodec = upickle.default.readwriter[Int]
import upickle.{default => up}

/**
 *  A set of predefined token types. This set is not fixed
 *  an clients can specify additional token types via the
 *  corresponding client capabilities.
 *  
 *  @since 3.16.0
 */
opaque type SemanticTokenTypes = String
object SemanticTokenTypes extends StringEnum[SemanticTokenTypes]:
  val namespace = entry("namespace")
  /**
   *  Represents a generic type. Acts as a fallback for types which can't be mapped to
   *  a specific type like class or enum.
   */
  val `type` = entry("type")
  val `class` = entry("class")
  val `enum` = entry("enum")
  val interface = entry("interface")
  val struct = entry("struct")
  val typeParameter = entry("typeParameter")
  val parameter = entry("parameter")
  val variable = entry("variable")
  val property = entry("property")
  val enumMember = entry("enumMember")
  val event = entry("event")
  val function = entry("function")
  val method = entry("method")
  val `macro` = entry("macro")
  val keyword = entry("keyword")
  val modifier = entry("modifier")
  val comment = entry("comment")
  val string = entry("string")
  val number = entry("number")
  val regexp = entry("regexp")
  val operator = entry("operator")
  /**
   *  @since 3.17.0
   */
  val decorator = entry("decorator")
  override def ALL = Set(
    namespace, `type`, `class`, `enum`, interface, struct, typeParameter, parameter, variable, property, enumMember, event, function, method, `macro`, keyword, modifier, comment, string, number, regexp, operator, decorator
  )

/**
 *  A set of predefined token modifiers. This set is not fixed
 *  an clients can specify additional token types via the
 *  corresponding client capabilities.
 *  
 *  @since 3.16.0
 */
opaque type SemanticTokenModifiers = String
object SemanticTokenModifiers extends StringEnum[SemanticTokenModifiers]:
  val declaration = entry("declaration")
  val definition = entry("definition")
  val readonly = entry("readonly")
  val static = entry("static")
  val deprecated = entry("deprecated")
  val `abstract` = entry("abstract")
  val async = entry("async")
  val modification = entry("modification")
  val documentation = entry("documentation")
  val defaultLibrary = entry("defaultLibrary")
  override def ALL = Set(
    declaration, definition, readonly, static, deprecated, `abstract`, async, modification, documentation, defaultLibrary
  )

/**
 *  The document diagnostic report kinds.
 *  
 *  @since 3.17.0
 */
opaque type DocumentDiagnosticReportKind = String
object DocumentDiagnosticReportKind extends StringEnum[DocumentDiagnosticReportKind]:
  /**
   *  A diagnostic report with a full
   *  set of problems.
   */
  val Full = entry("full")
  /**
   *  A report indicating that the last
   *  returned report is still accurate.
   */
  val Unchanged = entry("unchanged")
  override def ALL = Set(
    Full, Unchanged
  )

/**
 *  Predefined error codes.
 */
opaque type ErrorCodes = Int
object ErrorCodes extends IntEnum[ErrorCodes]:
  val ParseError = entry(-32700)
  val InvalidRequest = entry(-32600)
  val MethodNotFound = entry(-32601)
  val InvalidParams = entry(-32602)
  val InternalError = entry(-32603)
  /**
   *  Error code indicating that a server received a notification or
   *  request before the server has received the `initialize` request.
   */
  val ServerNotInitialized = entry(-32002)
  val UnknownErrorCode = entry(-32001)
  override def ALL = Set(
    ParseError, InvalidRequest, MethodNotFound, InvalidParams, InternalError, ServerNotInitialized, UnknownErrorCode
  )

opaque type LSPErrorCodes = Int
object LSPErrorCodes extends IntEnum[LSPErrorCodes]:
  /**
   *  A request failed but it was syntactically correct, e.g the
   *  method name was known and the parameters were valid. The error
   *  message should contain human readable information about why
   *  the request failed.
   *  
   *  @since 3.17.0
   */
  val RequestFailed = entry(-32803)
  /**
   *  The server cancelled the request. This error code should
   *  only be used for requests that explicitly support being
   *  server cancellable.
   *  
   *  @since 3.17.0
   */
  val ServerCancelled = entry(-32802)
  /**
   *  The server detected that the content of a document got
   *  modified outside normal conditions. A server should
   *  NOT send this error code if it detects a content change
   *  in it unprocessed messages. The result even computed
   *  on an older state might still be useful for the client.
   *  
   *  If a client decides that a result is not of any use anymore
   *  the client should cancel the request.
   */
  val ContentModified = entry(-32801)
  /**
   *  The client has canceled a request and a server as detected
   *  the cancel.
   */
  val RequestCancelled = entry(-32800)
  override def ALL = Set(
    RequestFailed, ServerCancelled, ContentModified, RequestCancelled
  )

/**
 *  A set of predefined range kinds.
 */
opaque type FoldingRangeKind = String
object FoldingRangeKind extends StringEnum[FoldingRangeKind]:
  /**
   *  Folding range for a comment
   */
  val Comment = entry("comment")
  /**
   *  Folding range for an import or include
   */
  val Imports = entry("imports")
  /**
   *  Folding range for a region (e.g. `#region`)
   */
  val Region = entry("region")
  override def ALL = Set(
    Comment, Imports, Region
  )

/**
 *  A symbol kind.
 */
opaque type SymbolKind = runtime.uinteger
object SymbolKind extends UIntEnum[SymbolKind]:
  val File = entry(1)
  val Module = entry(2)
  val Namespace = entry(3)
  val Package = entry(4)
  val Class = entry(5)
  val Method = entry(6)
  val Property = entry(7)
  val Field = entry(8)
  val Constructor = entry(9)
  val Enum = entry(10)
  val Interface = entry(11)
  val Function = entry(12)
  val Variable = entry(13)
  val Constant = entry(14)
  val String = entry(15)
  val Number = entry(16)
  val Boolean = entry(17)
  val Array = entry(18)
  val Object = entry(19)
  val Key = entry(20)
  val Null = entry(21)
  val EnumMember = entry(22)
  val Struct = entry(23)
  val Event = entry(24)
  val Operator = entry(25)
  val TypeParameter = entry(26)
  override def ALL = Set(
    File, Module, Namespace, Package, Class, Method, Property, Field, Constructor, Enum, Interface, Function, Variable, Constant, String, Number, Boolean, Array, Object, Key, Null, EnumMember, Struct, Event, Operator, TypeParameter
  )

/**
 *  Symbol tags are extra annotations that tweak the rendering of a symbol.
 *  
 *  @since 3.16
 */
opaque type SymbolTag = runtime.uinteger
object SymbolTag extends UIntEnum[SymbolTag]:
  /**
   *  Render a symbol as obsolete, usually using a strike-out.
   */
  val Deprecated = entry(1)
  override def ALL = Set(
    Deprecated
  )

/**
 *  Moniker uniqueness level to define scope of the moniker.
 *  
 *  @since 3.16.0
 */
opaque type UniquenessLevel = String
object UniquenessLevel extends StringEnum[UniquenessLevel]:
  /**
   *  The moniker is only unique inside a document
   */
  val document = entry("document")
  /**
   *  The moniker is unique inside a project for which a dump got created
   */
  val project = entry("project")
  /**
   *  The moniker is unique inside the group to which a project belongs
   */
  val group = entry("group")
  /**
   *  The moniker is unique inside the moniker scheme.
   */
  val scheme = entry("scheme")
  /**
   *  The moniker is globally unique
   */
  val global = entry("global")
  override def ALL = Set(
    document, project, group, scheme, global
  )

/**
 *  The moniker kind.
 *  
 *  @since 3.16.0
 */
opaque type MonikerKind = String
object MonikerKind extends StringEnum[MonikerKind]:
  /**
   *  The moniker represent a symbol that is imported into a project
   */
  val `import` = entry("import")
  /**
   *  The moniker represents a symbol that is exported from a project
   */
  val `export` = entry("export")
  /**
   *  The moniker represents a symbol that is local to a project (e.g. a local
   *  variable of a function, a class not visible outside the project, ...)
   */
  val local = entry("local")
  override def ALL = Set(
    `import`, `export`, local
  )

/**
 *  Inlay hint kinds.
 *  
 *  @since 3.17.0
 */
opaque type InlayHintKind = runtime.uinteger
object InlayHintKind extends UIntEnum[InlayHintKind]:
  /**
   *  An inlay hint that for a type annotation.
   */
  val Type = entry(1)
  /**
   *  An inlay hint that is for a parameter.
   */
  val Parameter = entry(2)
  override def ALL = Set(
    Type, Parameter
  )

/**
 *  The message type
 */
opaque type MessageType = runtime.uinteger
object MessageType extends UIntEnum[MessageType]:
  /**
   *  An error message.
   */
  val Error = entry(1)
  /**
   *  A warning message.
   */
  val Warning = entry(2)
  /**
   *  An information message.
   */
  val Info = entry(3)
  /**
   *  A log message.
   */
  val Log = entry(4)
  /**
   *  A debug message.
   *  
   *  @since 3.18.0
   */
  val Debug = entry(5)
  override def ALL = Set(
    Error, Warning, Info, Log, Debug
  )

/**
 *  Defines how the host (editor) should sync
 *  document changes to the language server.
 */
opaque type TextDocumentSyncKind = runtime.uinteger
object TextDocumentSyncKind extends UIntEnum[TextDocumentSyncKind]:
  /**
   *  Documents should not be synced at all.
   */
  val None = entry(0)
  /**
   *  Documents are synced by always sending the full content
   *  of the document.
   */
  val Full = entry(1)
  /**
   *  Documents are synced by sending the full content on open.
   *  After that only incremental updates to the document are
   *  send.
   */
  val Incremental = entry(2)
  override def ALL = Set(
    None, Full, Incremental
  )

/**
 *  Represents reasons why a text document is saved.
 */
opaque type TextDocumentSaveReason = runtime.uinteger
object TextDocumentSaveReason extends UIntEnum[TextDocumentSaveReason]:
  /**
   *  Manually triggered, e.g. by the user pressing save, by starting debugging,
   *  or by an API call.
   */
  val Manual = entry(1)
  /**
   *  Automatic after a delay.
   */
  val AfterDelay = entry(2)
  /**
   *  When the editor lost focus.
   */
  val FocusOut = entry(3)
  override def ALL = Set(
    Manual, AfterDelay, FocusOut
  )

/**
 *  The kind of a completion entry.
 */
opaque type CompletionItemKind = runtime.uinteger
object CompletionItemKind extends UIntEnum[CompletionItemKind]:
  val Text = entry(1)
  val Method = entry(2)
  val Function = entry(3)
  val Constructor = entry(4)
  val Field = entry(5)
  val Variable = entry(6)
  val Class = entry(7)
  val Interface = entry(8)
  val Module = entry(9)
  val Property = entry(10)
  val Unit = entry(11)
  val Value = entry(12)
  val Enum = entry(13)
  val Keyword = entry(14)
  val Snippet = entry(15)
  val Color = entry(16)
  val File = entry(17)
  val Reference = entry(18)
  val Folder = entry(19)
  val EnumMember = entry(20)
  val Constant = entry(21)
  val Struct = entry(22)
  val Event = entry(23)
  val Operator = entry(24)
  val TypeParameter = entry(25)
  override def ALL = Set(
    Text, Method, Function, Constructor, Field, Variable, Class, Interface, Module, Property, Unit, Value, Enum, Keyword, Snippet, Color, File, Reference, Folder, EnumMember, Constant, Struct, Event, Operator, TypeParameter
  )

/**
 *  Completion item tags are extra annotations that tweak the rendering of a completion
 *  item.
 *  
 *  @since 3.15.0
 */
opaque type CompletionItemTag = runtime.uinteger
object CompletionItemTag extends UIntEnum[CompletionItemTag]:
  /**
   *  Render a completion as obsolete, usually using a strike-out.
   */
  val Deprecated = entry(1)
  override def ALL = Set(
    Deprecated
  )

/**
 *  Defines whether the insert text in a completion item should be interpreted as
 *  plain text or a snippet.
 */
opaque type InsertTextFormat = runtime.uinteger
object InsertTextFormat extends UIntEnum[InsertTextFormat]:
  /**
   *  The primary text to be inserted is treated as a plain string.
   */
  val PlainText = entry(1)
  /**
   *  The primary text to be inserted is treated as a snippet.
   *  
   *  A snippet can define tab stops and placeholders with `$1`, `$2`
   *  and `${3:foo}`. `$0` defines the final tab stop, it defaults to
   *  the end of the snippet. Placeholders with equal identifiers are linked,
   *  that is typing in one will update others too.
   *  
   *  See also: https://microsoft.github.io/language-server-protocol/specifications/specification-current/#snippet_syntax
   */
  val Snippet = entry(2)
  override def ALL = Set(
    PlainText, Snippet
  )

/**
 *  How whitespace and indentation is handled during completion
 *  item insertion.
 *  
 *  @since 3.16.0
 */
opaque type InsertTextMode = runtime.uinteger
object InsertTextMode extends UIntEnum[InsertTextMode]:
  /**
   *  The insertion or replace strings is taken as it is. If the
   *  value is multi line the lines below the cursor will be
   *  inserted using the indentation defined in the string value.
   *  The client will not apply any kind of adjustments to the
   *  string.
   */
  val asIs = entry(1)
  /**
   *  The editor adjusts leading whitespace of new lines so that
   *  they match the indentation up to the cursor of the line for
   *  which the item is accepted.
   *  
   *  Consider a line like this: <2tabs><cursor><3tabs>foo. Accepting a
   *  multi line completion item is indented using 2 tabs and all
   *  following lines inserted will be indented using 2 tabs as well.
   */
  val adjustIndentation = entry(2)
  override def ALL = Set(
    asIs, adjustIndentation
  )

/**
 *  A document highlight kind.
 */
opaque type DocumentHighlightKind = runtime.uinteger
object DocumentHighlightKind extends UIntEnum[DocumentHighlightKind]:
  /**
   *  A textual occurrence.
   */
  val Text = entry(1)
  /**
   *  Read-access of a symbol, like reading a variable.
   */
  val Read = entry(2)
  /**
   *  Write-access of a symbol, like writing to a variable.
   */
  val Write = entry(3)
  override def ALL = Set(
    Text, Read, Write
  )

/**
 *  A set of predefined code action kinds
 */
opaque type CodeActionKind = String
object CodeActionKind extends StringEnum[CodeActionKind]:
  /**
   *  Empty kind.
   */
  val Empty = entry("")
  /**
   *  Base kind for quickfix actions: 'quickfix'
   */
  val QuickFix = entry("quickfix")
  /**
   *  Base kind for refactoring actions: 'refactor'
   */
  val Refactor = entry("refactor")
  /**
   *  Base kind for refactoring extraction actions: 'refactor.extract'
   *  
   *  Example extract actions:
   *  
   *  - Extract method
   *  - Extract function
   *  - Extract variable
   *  - Extract interface from class
   *  - ...
   */
  val RefactorExtract = entry("refactor.extract")
  /**
   *  Base kind for refactoring inline actions: 'refactor.inline'
   *  
   *  Example inline actions:
   *  
   *  - Inline function
   *  - Inline variable
   *  - Inline constant
   *  - ...
   */
  val RefactorInline = entry("refactor.inline")
  /**
   *  Base kind for refactoring rewrite actions: 'refactor.rewrite'
   *  
   *  Example rewrite actions:
   *  
   *  - Convert JavaScript function to class
   *  - Add or remove parameter
   *  - Encapsulate field
   *  - Make method static
   *  - Move method to base class
   *  - ...
   */
  val RefactorRewrite = entry("refactor.rewrite")
  /**
   *  Base kind for source actions: `source`
   *  
   *  Source code actions apply to the entire file.
   */
  val Source = entry("source")
  /**
   *  Base kind for an organize imports source action: `source.organizeImports`
   */
  val SourceOrganizeImports = entry("source.organizeImports")
  /**
   *  Base kind for auto-fix source actions: `source.fixAll`.
   *  
   *  Fix all actions automatically fix errors that have a clear fix that do not require user input.
   *  They should not suppress errors or perform unsafe fixes such as generating new types or classes.
   *  
   *  @since 3.15.0
   */
  val SourceFixAll = entry("source.fixAll")
  override def ALL = Set(
    Empty, QuickFix, Refactor, RefactorExtract, RefactorInline, RefactorRewrite, Source, SourceOrganizeImports, SourceFixAll
  )

opaque type TraceValues = String
object TraceValues extends StringEnum[TraceValues]:
  /**
   *  Turn tracing off.
   */
  val Off = entry("off")
  /**
   *  Trace messages only.
   */
  val Messages = entry("messages")
  /**
   *  Verbose message tracing.
   */
  val Verbose = entry("verbose")
  override def ALL = Set(
    Off, Messages, Verbose
  )

/**
 *  Describes the content type that a client supports in various
 *  result literals like `Hover`, `ParameterInfo` or `CompletionItem`.
 *  
 *  Please note that `MarkupKinds` must not start with a `$`. This kinds
 *  are reserved for internal usage.
 */
opaque type MarkupKind = String
object MarkupKind extends StringEnum[MarkupKind]:
  /**
   *  Plain text is supported as a content format
   */
  val PlainText = entry("plaintext")
  /**
   *  Markdown is supported as a content format
   */
  val Markdown = entry("markdown")
  override def ALL = Set(
    PlainText, Markdown
  )

/**
 *  A set of predefined position encoding kinds.
 *  
 *  @since 3.17.0
 */
opaque type PositionEncodingKind = String
object PositionEncodingKind extends StringEnum[PositionEncodingKind]:
  /**
   *  Character offsets count UTF-8 code units (e.g. bytes).
   */
  val UTF8 = entry("utf-8")
  /**
   *  Character offsets count UTF-16 code units.
   *  
   *  This is the default and must always be supported
   *  by servers
   */
  val UTF16 = entry("utf-16")
  /**
   *  Character offsets count UTF-32 code units.
   *  
   *  Implementation note: these are the same as Unicode codepoints,
   *  so this `PositionEncodingKind` may also be used for an
   *  encoding-agnostic representation of character offsets.
   */
  val UTF32 = entry("utf-32")
  override def ALL = Set(
    UTF8, UTF16, UTF32
  )

/**
 *  The file event type
 */
opaque type FileChangeType = runtime.uinteger
object FileChangeType extends UIntEnum[FileChangeType]:
  /**
   *  The file got created.
   */
  val Created = entry(1)
  /**
   *  The file got changed.
   */
  val Changed = entry(2)
  /**
   *  The file got deleted.
   */
  val Deleted = entry(3)
  override def ALL = Set(
    Created, Changed, Deleted
  )

opaque type WatchKind = runtime.uinteger
object WatchKind extends UIntEnum[WatchKind]:
  /**
   *  Interested in create events.
   */
  val Create = entry(1)
  /**
   *  Interested in change events
   */
  val Change = entry(2)
  /**
   *  Interested in delete events
   */
  val Delete = entry(4)
  override def ALL = Set(
    Create, Change, Delete
  )

/**
 *  The diagnostic's severity.
 */
opaque type DiagnosticSeverity = runtime.uinteger
object DiagnosticSeverity extends UIntEnum[DiagnosticSeverity]:
  /**
   *  Reports an error.
   */
  val Error = entry(1)
  /**
   *  Reports a warning.
   */
  val Warning = entry(2)
  /**
   *  Reports an information.
   */
  val Information = entry(3)
  /**
   *  Reports a hint.
   */
  val Hint = entry(4)
  override def ALL = Set(
    Error, Warning, Information, Hint
  )

/**
 *  The diagnostic tags.
 *  
 *  @since 3.15.0
 */
opaque type DiagnosticTag = runtime.uinteger
object DiagnosticTag extends UIntEnum[DiagnosticTag]:
  /**
   *  Unused or unnecessary code.
   *  
   *  Clients are allowed to render diagnostics with this tag faded out instead of having
   *  an error squiggle.
   */
  val Unnecessary = entry(1)
  /**
   *  Deprecated or obsolete code.
   *  
   *  Clients are allowed to rendered diagnostics with this tag strike through.
   */
  val Deprecated = entry(2)
  override def ALL = Set(
    Unnecessary, Deprecated
  )

/**
 *  How a completion was triggered
 */
opaque type CompletionTriggerKind = runtime.uinteger
object CompletionTriggerKind extends UIntEnum[CompletionTriggerKind]:
  /**
   *  Completion was triggered by typing an identifier (24x7 code
   *  complete), manual invocation (e.g Ctrl+Space) or via API.
   */
  val Invoked = entry(1)
  /**
   *  Completion was triggered by a trigger character specified by
   *  the `triggerCharacters` properties of the `CompletionRegistrationOptions`.
   */
  val TriggerCharacter = entry(2)
  /**
   *  Completion was re-triggered as current completion list is incomplete
   */
  val TriggerForIncompleteCompletions = entry(3)
  override def ALL = Set(
    Invoked, TriggerCharacter, TriggerForIncompleteCompletions
  )

/**
 *  How a signature help was triggered.
 *  
 *  @since 3.15.0
 */
opaque type SignatureHelpTriggerKind = runtime.uinteger
object SignatureHelpTriggerKind extends UIntEnum[SignatureHelpTriggerKind]:
  /**
   *  Signature help was invoked manually by the user or by a command.
   */
  val Invoked = entry(1)
  /**
   *  Signature help was triggered by a trigger character.
   */
  val TriggerCharacter = entry(2)
  /**
   *  Signature help was triggered by the cursor moving or by the document content changing.
   */
  val ContentChange = entry(3)
  override def ALL = Set(
    Invoked, TriggerCharacter, ContentChange
  )

/**
 *  The reason why code actions were requested.
 *  
 *  @since 3.17.0
 */
opaque type CodeActionTriggerKind = runtime.uinteger
object CodeActionTriggerKind extends UIntEnum[CodeActionTriggerKind]:
  /**
   *  Code actions were explicitly requested by the user or by an extension.
   */
  val Invoked = entry(1)
  /**
   *  Code actions were requested automatically.
   *  
   *  This typically happens when current selection in a file changes, but can
   *  also be triggered when file content changes.
   */
  val Automatic = entry(2)
  override def ALL = Set(
    Invoked, Automatic
  )

/**
 *  A pattern kind describing if a glob pattern matches a file a folder or
 *  both.
 *  
 *  @since 3.16.0
 */
opaque type FileOperationPatternKind = String
object FileOperationPatternKind extends StringEnum[FileOperationPatternKind]:
  /**
   *  The pattern matches a file only.
   */
  val file = entry("file")
  /**
   *  The pattern matches a folder only.
   */
  val folder = entry("folder")
  override def ALL = Set(
    file, folder
  )

/**
 *  A notebook cell kind.
 *  
 *  @since 3.17.0
 */
opaque type NotebookCellKind = runtime.uinteger
object NotebookCellKind extends UIntEnum[NotebookCellKind]:
  /**
   *  A markup-cell is formatted source that is used for display.
   */
  val Markup = entry(1)
  /**
   *  A code-cell is source code.
   */
  val Code = entry(2)
  override def ALL = Set(
    Markup, Code
  )

opaque type ResourceOperationKind = String
object ResourceOperationKind extends StringEnum[ResourceOperationKind]:
  /**
   *  Supports creating new files and folders.
   */
  val Create = entry("create")
  /**
   *  Supports renaming existing files and folders.
   */
  val Rename = entry("rename")
  /**
   *  Supports deleting existing files and folders.
   */
  val Delete = entry("delete")
  override def ALL = Set(
    Create, Rename, Delete
  )

opaque type FailureHandlingKind = String
object FailureHandlingKind extends StringEnum[FailureHandlingKind]:
  /**
   *  Applying the workspace change is simply aborted if one of the changes provided
   *  fails. All operations executed before the failing operation stay executed.
   */
  val Abort = entry("abort")
  /**
   *  All operations are executed transactional. That means they either all
   *  succeed or no changes at all are applied to the workspace.
   */
  val Transactional = entry("transactional")
  /**
   *  If the workspace edit contains only textual file changes they are executed transactional.
   *  If resource changes (create, rename or delete file) are part of the change the failure
   *  handling strategy is abort.
   */
  val TextOnlyTransactional = entry("textOnlyTransactional")
  /**
   *  The client tries to undo the operations already executed. But there is no
   *  guarantee that this is succeeding.
   */
  val Undo = entry("undo")
  override def ALL = Set(
    Abort, Transactional, TextOnlyTransactional, Undo
  )

opaque type PrepareSupportDefaultBehavior = runtime.uinteger
object PrepareSupportDefaultBehavior extends UIntEnum[PrepareSupportDefaultBehavior]:
  /**
   *  The client's default behavior is to select the identifier
   *  according the to language's syntax rule.
   */
  val Identifier = entry(1)
  override def ALL = Set(
    Identifier
  )

opaque type TokenFormat = String
object TokenFormat extends StringEnum[TokenFormat]:
  val Relative = entry("relative")
  override def ALL = Set(
    Relative
  )

