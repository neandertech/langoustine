package langoustine.lsp
package structures

import langoustine.*

case class ImplementationParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object ImplementationParams:
  given upickle.default.Reader[ImplementationParams] = Pickle.macroR

case class Location(
  uri: RuntimeBase.DocumentUri,
  range: structures.Range
)
object Location:
  given upickle.default.Reader[Location] = Pickle.macroR

case class ImplementationRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object ImplementationRegistrationOptions:
  given upickle.default.Reader[ImplementationRegistrationOptions] = Pickle.macroR

case class TypeDefinitionParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object TypeDefinitionParams:
  given upickle.default.Reader[TypeDefinitionParams] = Pickle.macroR

case class TypeDefinitionRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object TypeDefinitionRegistrationOptions:
  given upickle.default.Reader[TypeDefinitionRegistrationOptions] = Pickle.macroR

case class WorkspaceFolder(
  uri: aliases.URI,
  name: String
)
object WorkspaceFolder:
  given upickle.default.Reader[WorkspaceFolder] = Pickle.macroR

case class DidChangeWorkspaceFoldersParams(
  event: structures.WorkspaceFoldersChangeEvent
)
object DidChangeWorkspaceFoldersParams:
  given upickle.default.Reader[DidChangeWorkspaceFoldersParams] = Pickle.macroR

case class ConfigurationParams(
  items: Vector[structures.ConfigurationItem]
)
object ConfigurationParams:
  given upickle.default.Reader[ConfigurationParams] = Pickle.macroR

case class PartialResultParams(
  partialResultToken: aliases.ProgressToken
)
object PartialResultParams:
  given upickle.default.Reader[PartialResultParams] = Pickle.macroR

case class DocumentColorParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DocumentColorParams:
  given upickle.default.Reader[DocumentColorParams] = Pickle.macroR

case class ColorInformation(
  range: structures.Range,
  color: structures.Color
)
object ColorInformation:
  given upickle.default.Reader[ColorInformation] = Pickle.macroR

case class DocumentColorRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object DocumentColorRegistrationOptions:
  given upickle.default.Reader[DocumentColorRegistrationOptions] = Pickle.macroR

case class ColorPresentationParams(
  textDocument: structures.TextDocumentIdentifier,
  color: structures.Color,
  range: structures.Range,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object ColorPresentationParams:
  given upickle.default.Reader[ColorPresentationParams] = Pickle.macroR

case class ColorPresentation(
  label: String,
  textEdit: structures.TextEdit,
  additionalTextEdits: Vector[structures.TextEdit]
)
object ColorPresentation:
  given upickle.default.Reader[ColorPresentation] = Pickle.macroR

case class WorkDoneProgressOptions(
  workDoneProgress: Boolean
)
object WorkDoneProgressOptions:
  given upickle.default.Reader[WorkDoneProgressOptions] = Pickle.macroR

case class TextDocumentRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object TextDocumentRegistrationOptions:
  given upickle.default.Reader[TextDocumentRegistrationOptions] = Pickle.macroR

case class FoldingRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object FoldingRangeParams:
  given upickle.default.Reader[FoldingRangeParams] = Pickle.macroR

case class FoldingRange(
  startLine: RuntimeBase.uinteger,
  startCharacter: RuntimeBase.uinteger,
  endLine: RuntimeBase.uinteger,
  endCharacter: RuntimeBase.uinteger,
  kind: enumerations.FoldingRangeKind,
  collapsedText: String
)
object FoldingRange:
  given upickle.default.Reader[FoldingRange] = Pickle.macroR

case class FoldingRangeRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object FoldingRangeRegistrationOptions:
  given upickle.default.Reader[FoldingRangeRegistrationOptions] = Pickle.macroR

case class DeclarationParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DeclarationParams:
  given upickle.default.Reader[DeclarationParams] = Pickle.macroR

case class DeclarationRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object DeclarationRegistrationOptions:
  given upickle.default.Reader[DeclarationRegistrationOptions] = Pickle.macroR

case class SelectionRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  positions: Vector[structures.Position],
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object SelectionRangeParams:
  given upickle.default.Reader[SelectionRangeParams] = Pickle.macroR

case class SelectionRange(
  range: structures.Range,
  parent: structures.SelectionRange
)
object SelectionRange:
  given upickle.default.Reader[SelectionRange] = Pickle.macroR

case class SelectionRangeRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object SelectionRangeRegistrationOptions:
  given upickle.default.Reader[SelectionRangeRegistrationOptions] = Pickle.macroR

case class WorkDoneProgressCreateParams(
  token: aliases.ProgressToken
)
object WorkDoneProgressCreateParams:
  given upickle.default.Reader[WorkDoneProgressCreateParams] = Pickle.macroR

case class WorkDoneProgressCancelParams(
  token: aliases.ProgressToken
)
object WorkDoneProgressCancelParams:
  given upickle.default.Reader[WorkDoneProgressCancelParams] = Pickle.macroR

case class CallHierarchyPrepareParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object CallHierarchyPrepareParams:
  given upickle.default.Reader[CallHierarchyPrepareParams] = Pickle.macroR

case class CallHierarchyItem(
  name: String,
  kind: enumerations.SymbolKind,
  tags: Vector[enumerations.SymbolTag],
  detail: String,
  uri: RuntimeBase.DocumentUri,
  range: structures.Range,
  selectionRange: structures.Range,
  data: aliases.LSPAny
)
object CallHierarchyItem:
  given upickle.default.Reader[CallHierarchyItem] = Pickle.macroR

case class CallHierarchyRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object CallHierarchyRegistrationOptions:
  given upickle.default.Reader[CallHierarchyRegistrationOptions] = Pickle.macroR

case class CallHierarchyIncomingCallsParams(
  item: structures.CallHierarchyItem,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object CallHierarchyIncomingCallsParams:
  given upickle.default.Reader[CallHierarchyIncomingCallsParams] = Pickle.macroR

case class CallHierarchyIncomingCall(
  from: structures.CallHierarchyItem,
  fromRanges: Vector[structures.Range]
)
object CallHierarchyIncomingCall:
  given upickle.default.Reader[CallHierarchyIncomingCall] = Pickle.macroR

case class CallHierarchyOutgoingCallsParams(
  item: structures.CallHierarchyItem,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object CallHierarchyOutgoingCallsParams:
  given upickle.default.Reader[CallHierarchyOutgoingCallsParams] = Pickle.macroR

case class CallHierarchyOutgoingCall(
  to: structures.CallHierarchyItem,
  fromRanges: Vector[structures.Range]
)
object CallHierarchyOutgoingCall:
  given upickle.default.Reader[CallHierarchyOutgoingCall] = Pickle.macroR

case class SemanticTokensParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object SemanticTokensParams:
  given upickle.default.Reader[SemanticTokensParams] = Pickle.macroR

case class SemanticTokens(
  resultId: String,
  data: Vector[RuntimeBase.uinteger]
)
object SemanticTokens:
  given upickle.default.Reader[SemanticTokens] = Pickle.macroR

case class SemanticTokensPartialResult(
  data: Vector[RuntimeBase.uinteger]
)
object SemanticTokensPartialResult:
  given upickle.default.Reader[SemanticTokensPartialResult] = Pickle.macroR

case class SemanticTokensRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  legend: structures.SemanticTokensLegend,
  range: (Boolean | SemanticTokensRegistrationOptions.S0),
  full: (Boolean | SemanticTokensRegistrationOptions.S1),
  id: String
)
object SemanticTokensRegistrationOptions:
  given upickle.default.Reader[SemanticTokensRegistrationOptions] = Pickle.macroR
  case class S0(
  )
  object S0:
    given upickle.default.Reader[S0] = Pickle.macroR
  case class S1(
    delta: Boolean
  )
  object S1:
    given upickle.default.Reader[S1] = Pickle.macroR

case class SemanticTokensDeltaParams(
  textDocument: structures.TextDocumentIdentifier,
  previousResultId: String,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object SemanticTokensDeltaParams:
  given upickle.default.Reader[SemanticTokensDeltaParams] = Pickle.macroR

case class SemanticTokensDelta(
  resultId: String,
  edits: Vector[structures.SemanticTokensEdit]
)
object SemanticTokensDelta:
  given upickle.default.Reader[SemanticTokensDelta] = Pickle.macroR

case class SemanticTokensDeltaPartialResult(
  edits: Vector[structures.SemanticTokensEdit]
)
object SemanticTokensDeltaPartialResult:
  given upickle.default.Reader[SemanticTokensDeltaPartialResult] = Pickle.macroR

case class SemanticTokensRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object SemanticTokensRangeParams:
  given upickle.default.Reader[SemanticTokensRangeParams] = Pickle.macroR

case class ShowDocumentParams(
  uri: aliases.URI,
  external: Boolean,
  takeFocus: Boolean,
  selection: structures.Range
)
object ShowDocumentParams:
  given upickle.default.Reader[ShowDocumentParams] = Pickle.macroR

case class ShowDocumentResult(
  success: Boolean
)
object ShowDocumentResult:
  given upickle.default.Reader[ShowDocumentResult] = Pickle.macroR

case class LinkedEditingRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object LinkedEditingRangeParams:
  given upickle.default.Reader[LinkedEditingRangeParams] = Pickle.macroR

case class LinkedEditingRanges(
  ranges: Vector[structures.Range],
  wordPattern: String
)
object LinkedEditingRanges:
  given upickle.default.Reader[LinkedEditingRanges] = Pickle.macroR

case class LinkedEditingRangeRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object LinkedEditingRangeRegistrationOptions:
  given upickle.default.Reader[LinkedEditingRangeRegistrationOptions] = Pickle.macroR

case class CreateFilesParams(
  files: Vector[structures.FileCreate]
)
object CreateFilesParams:
  given upickle.default.Reader[CreateFilesParams] = Pickle.macroR

case class WorkspaceEdit(
  changes: Map[RuntimeBase.DocumentUri, Vector[structures.TextEdit]],
  documentChanges: Vector[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)],
  changeAnnotations: Map[aliases.ChangeAnnotationIdentifier, structures.ChangeAnnotation]
)
object WorkspaceEdit:
  given upickle.default.Reader[WorkspaceEdit] = Pickle.macroR

case class FileOperationRegistrationOptions(
  filters: Vector[structures.FileOperationFilter]
)
object FileOperationRegistrationOptions:
  given upickle.default.Reader[FileOperationRegistrationOptions] = Pickle.macroR

case class RenameFilesParams(
  files: Vector[structures.FileRename]
)
object RenameFilesParams:
  given upickle.default.Reader[RenameFilesParams] = Pickle.macroR

case class DeleteFilesParams(
  files: Vector[structures.FileDelete]
)
object DeleteFilesParams:
  given upickle.default.Reader[DeleteFilesParams] = Pickle.macroR

case class MonikerParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object MonikerParams:
  given upickle.default.Reader[MonikerParams] = Pickle.macroR

case class Moniker(
  scheme: String,
  identifier: String,
  unique: enumerations.UniquenessLevel,
  kind: enumerations.MonikerKind
)
object Moniker:
  given upickle.default.Reader[Moniker] = Pickle.macroR

case class MonikerRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object MonikerRegistrationOptions:
  given upickle.default.Reader[MonikerRegistrationOptions] = Pickle.macroR

case class TypeHierarchyPrepareParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object TypeHierarchyPrepareParams:
  given upickle.default.Reader[TypeHierarchyPrepareParams] = Pickle.macroR

case class TypeHierarchyItem(
  name: String,
  kind: enumerations.SymbolKind,
  tags: Vector[enumerations.SymbolTag],
  detail: String,
  uri: RuntimeBase.DocumentUri,
  range: structures.Range,
  selectionRange: structures.Range,
  data: aliases.LSPAny
)
object TypeHierarchyItem:
  given upickle.default.Reader[TypeHierarchyItem] = Pickle.macroR

case class TypeHierarchyRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object TypeHierarchyRegistrationOptions:
  given upickle.default.Reader[TypeHierarchyRegistrationOptions] = Pickle.macroR

case class TypeHierarchySupertypesParams(
  item: structures.TypeHierarchyItem,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object TypeHierarchySupertypesParams:
  given upickle.default.Reader[TypeHierarchySupertypesParams] = Pickle.macroR

case class TypeHierarchySubtypesParams(
  item: structures.TypeHierarchyItem,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object TypeHierarchySubtypesParams:
  given upickle.default.Reader[TypeHierarchySubtypesParams] = Pickle.macroR

case class InlineValueParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  context: structures.InlineValueContext,
  workDoneToken: aliases.ProgressToken
)
object InlineValueParams:
  given upickle.default.Reader[InlineValueParams] = Pickle.macroR

case class InlineValueRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object InlineValueRegistrationOptions:
  given upickle.default.Reader[InlineValueRegistrationOptions] = Pickle.macroR

case class InlayHintParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  workDoneToken: aliases.ProgressToken
)
object InlayHintParams:
  given upickle.default.Reader[InlayHintParams] = Pickle.macroR

case class InlayHint(
  position: structures.Position,
  label: (String | Vector[structures.InlayHintLabelPart]),
  kind: enumerations.InlayHintKind,
  textEdits: Vector[structures.TextEdit],
  tooltip: (String | structures.MarkupContent),
  paddingLeft: Boolean,
  paddingRight: Boolean,
  data: aliases.LSPAny
)
object InlayHint:
  given upickle.default.Reader[InlayHint] = Pickle.macroR

case class InlayHintRegistrationOptions(
  resolveProvider: Boolean,
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object InlayHintRegistrationOptions:
  given upickle.default.Reader[InlayHintRegistrationOptions] = Pickle.macroR

case class DocumentDiagnosticParams(
  textDocument: structures.TextDocumentIdentifier,
  identifier: String,
  previousResultId: String,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DocumentDiagnosticParams:
  given upickle.default.Reader[DocumentDiagnosticParams] = Pickle.macroR

case class DocumentDiagnosticReportPartialResult(
  relatedDocuments: Map[RuntimeBase.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)]
)
object DocumentDiagnosticReportPartialResult:
  given upickle.default.Reader[DocumentDiagnosticReportPartialResult] = Pickle.macroR

case class DiagnosticServerCancellationData(
  retriggerRequest: Boolean
)
object DiagnosticServerCancellationData:
  given upickle.default.Reader[DiagnosticServerCancellationData] = Pickle.macroR

case class DiagnosticRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  identifier: String,
  interFileDependencies: Boolean,
  workspaceDiagnostics: Boolean,
  id: String
)
object DiagnosticRegistrationOptions:
  given upickle.default.Reader[DiagnosticRegistrationOptions] = Pickle.macroR

case class WorkspaceDiagnosticParams(
  identifier: String,
  previousResultIds: Vector[structures.PreviousResultId],
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object WorkspaceDiagnosticParams:
  given upickle.default.Reader[WorkspaceDiagnosticParams] = Pickle.macroR

case class WorkspaceDiagnosticReport(
  items: Vector[aliases.WorkspaceDocumentDiagnosticReport]
)
object WorkspaceDiagnosticReport:
  given upickle.default.Reader[WorkspaceDiagnosticReport] = Pickle.macroR

case class WorkspaceDiagnosticReportPartialResult(
  items: Vector[aliases.WorkspaceDocumentDiagnosticReport]
)
object WorkspaceDiagnosticReportPartialResult:
  given upickle.default.Reader[WorkspaceDiagnosticReportPartialResult] = Pickle.macroR

case class DidOpenNotebookDocumentParams(
  notebookDocument: structures.NotebookDocument,
  cellTextDocuments: Vector[structures.TextDocumentItem]
)
object DidOpenNotebookDocumentParams:
  given upickle.default.Reader[DidOpenNotebookDocumentParams] = Pickle.macroR

case class DidChangeNotebookDocumentParams(
  notebookDocument: structures.VersionedNotebookDocumentIdentifier,
  change: structures.NotebookDocumentChangeEvent
)
object DidChangeNotebookDocumentParams:
  given upickle.default.Reader[DidChangeNotebookDocumentParams] = Pickle.macroR

case class DidSaveNotebookDocumentParams(
  notebookDocument: structures.NotebookDocumentIdentifier
)
object DidSaveNotebookDocumentParams:
  given upickle.default.Reader[DidSaveNotebookDocumentParams] = Pickle.macroR

case class DidCloseNotebookDocumentParams(
  notebookDocument: structures.NotebookDocumentIdentifier,
  cellTextDocuments: Vector[structures.TextDocumentIdentifier]
)
object DidCloseNotebookDocumentParams:
  given upickle.default.Reader[DidCloseNotebookDocumentParams] = Pickle.macroR

case class RegistrationParams(
  registrations: Vector[structures.Registration]
)
object RegistrationParams:
  given upickle.default.Reader[RegistrationParams] = Pickle.macroR

case class UnregistrationParams(
  unregisterations: Vector[structures.Unregistration]
)
object UnregistrationParams:
  given upickle.default.Reader[UnregistrationParams] = Pickle.macroR

case class InitializeParams(
  processId: (Int | Null),
  clientInfo: InitializeParams.ClientInfo,
  locale: String,
  rootPath: (String | Null),
  rootUri: (RuntimeBase.DocumentUri | Null),
  capabilities: structures.ClientCapabilities,
  initializationOptions: aliases.LSPAny,
  trace: ("off" | "messages" | "compact" | "verbose"),
  workspaceFolders: (Vector[structures.WorkspaceFolder] | Null)
)
object InitializeParams:
  given upickle.default.Reader[InitializeParams] = Pickle.macroR
  case class ClientInfo(
    name: String,
    version: String
  )
  object ClientInfo:
    given upickle.default.Reader[ClientInfo] = Pickle.macroR

case class InitializeResult(
  capabilities: structures.ServerCapabilities,
  serverInfo: InitializeResult.ServerInfo
)
object InitializeResult:
  given upickle.default.Reader[InitializeResult] = Pickle.macroR
  case class ServerInfo(
    name: String,
    version: String
  )
  object ServerInfo:
    given upickle.default.Reader[ServerInfo] = Pickle.macroR

case class InitializeError(
  retry: Boolean
)
object InitializeError:
  given upickle.default.Reader[InitializeError] = Pickle.macroR

case class InitializedParams(
)
object InitializedParams:
  given upickle.default.Reader[InitializedParams] = Pickle.macroR

case class DidChangeConfigurationParams(
  settings: aliases.LSPAny
)
object DidChangeConfigurationParams:
  given upickle.default.Reader[DidChangeConfigurationParams] = Pickle.macroR

case class DidChangeConfigurationRegistrationOptions(
  section: (String | Vector[String])
)
object DidChangeConfigurationRegistrationOptions:
  given upickle.default.Reader[DidChangeConfigurationRegistrationOptions] = Pickle.macroR

case class ShowMessageParams(
  `type`: enumerations.MessageType,
  message: String
)
object ShowMessageParams:
  given upickle.default.Reader[ShowMessageParams] = Pickle.macroR

case class ShowMessageRequestParams(
  `type`: enumerations.MessageType,
  message: String,
  actions: Vector[structures.MessageActionItem]
)
object ShowMessageRequestParams:
  given upickle.default.Reader[ShowMessageRequestParams] = Pickle.macroR

case class MessageActionItem(
  title: String
)
object MessageActionItem:
  given upickle.default.Reader[MessageActionItem] = Pickle.macroR

case class LogMessageParams(
  `type`: enumerations.MessageType,
  message: String
)
object LogMessageParams:
  given upickle.default.Reader[LogMessageParams] = Pickle.macroR

case class DidOpenTextDocumentParams(
  textDocument: structures.TextDocumentItem
)
object DidOpenTextDocumentParams:
  given upickle.default.Reader[DidOpenTextDocumentParams] = Pickle.macroR

case class DidChangeTextDocumentParams(
  textDocument: structures.VersionedTextDocumentIdentifier,
  contentChanges: Vector[aliases.TextDocumentContentChangeEvent]
)
object DidChangeTextDocumentParams:
  given upickle.default.Reader[DidChangeTextDocumentParams] = Pickle.macroR

case class TextDocumentChangeRegistrationOptions(
  syncKind: enumerations.TextDocumentSyncKind,
  documentSelector: (aliases.DocumentSelector | Null)
)
object TextDocumentChangeRegistrationOptions:
  given upickle.default.Reader[TextDocumentChangeRegistrationOptions] = Pickle.macroR

case class DidCloseTextDocumentParams(
  textDocument: structures.TextDocumentIdentifier
)
object DidCloseTextDocumentParams:
  given upickle.default.Reader[DidCloseTextDocumentParams] = Pickle.macroR

case class DidSaveTextDocumentParams(
  textDocument: structures.TextDocumentIdentifier,
  text: String
)
object DidSaveTextDocumentParams:
  given upickle.default.Reader[DidSaveTextDocumentParams] = Pickle.macroR

case class TextDocumentSaveRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  includeText: Boolean
)
object TextDocumentSaveRegistrationOptions:
  given upickle.default.Reader[TextDocumentSaveRegistrationOptions] = Pickle.macroR

case class WillSaveTextDocumentParams(
  textDocument: structures.TextDocumentIdentifier,
  reason: enumerations.TextDocumentSaveReason
)
object WillSaveTextDocumentParams:
  given upickle.default.Reader[WillSaveTextDocumentParams] = Pickle.macroR

case class TextEdit(
  range: structures.Range,
  newText: String
)
object TextEdit:
  given upickle.default.Reader[TextEdit] = Pickle.macroR

case class DidChangeWatchedFilesParams(
  changes: Vector[structures.FileEvent]
)
object DidChangeWatchedFilesParams:
  given upickle.default.Reader[DidChangeWatchedFilesParams] = Pickle.macroR

case class DidChangeWatchedFilesRegistrationOptions(
  watchers: Vector[structures.FileSystemWatcher]
)
object DidChangeWatchedFilesRegistrationOptions:
  given upickle.default.Reader[DidChangeWatchedFilesRegistrationOptions] = Pickle.macroR

case class PublishDiagnosticsParams(
  uri: RuntimeBase.DocumentUri,
  version: Int,
  diagnostics: Vector[structures.Diagnostic]
)
object PublishDiagnosticsParams:
  given upickle.default.Reader[PublishDiagnosticsParams] = Pickle.macroR

case class CompletionParams(
  context: structures.CompletionContext,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object CompletionParams:
  given upickle.default.Reader[CompletionParams] = Pickle.macroR

case class CompletionItem(
  label: String,
  labelDetails: structures.CompletionItemLabelDetails,
  kind: enumerations.CompletionItemKind,
  tags: Vector[enumerations.CompletionItemTag],
  detail: String,
  documentation: (String | structures.MarkupContent),
  deprecated: Boolean,
  preselect: Boolean,
  sortText: String,
  filterText: String,
  insertText: String,
  insertTextFormat: enumerations.InsertTextFormat,
  insertTextMode: enumerations.InsertTextMode,
  textEdit: (structures.TextEdit | structures.InsertReplaceEdit),
  textEditText: String,
  additionalTextEdits: Vector[structures.TextEdit],
  commitCharacters: Vector[String],
  command: structures.Command,
  data: aliases.LSPAny
)
object CompletionItem:
  given upickle.default.Reader[CompletionItem] = Pickle.macroR

case class CompletionList(
  isIncomplete: Boolean,
  itemDefaults: CompletionList.ItemDefaults,
  items: Vector[structures.CompletionItem]
)
object CompletionList:
  given upickle.default.Reader[CompletionList] = Pickle.macroR
  case class ItemDefaults(
    commitCharacters: Vector[String],
    editRange: (structures.Range | ItemDefaults.S0),
    insertTextFormat: enumerations.InsertTextFormat,
    insertTextMode: enumerations.InsertTextMode,
    data: aliases.LSPAny
  )
  object ItemDefaults:
    given upickle.default.Reader[ItemDefaults] = Pickle.macroR
    case class S0(
      insert: structures.Range,
      replace: structures.Range
    )
    object S0:
      given upickle.default.Reader[S0] = Pickle.macroR

case class CompletionRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  triggerCharacters: Vector[String],
  allCommitCharacters: Vector[String],
  resolveProvider: Boolean,
  completionItem: CompletionRegistrationOptions.CompletionItem
)
object CompletionRegistrationOptions:
  given upickle.default.Reader[CompletionRegistrationOptions] = Pickle.macroR
  case class CompletionItem(
    labelDetailsSupport: Boolean
  )
  object CompletionItem:
    given upickle.default.Reader[CompletionItem] = Pickle.macroR

case class HoverParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object HoverParams:
  given upickle.default.Reader[HoverParams] = Pickle.macroR

case class Hover(
  contents: (structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString]),
  range: structures.Range
)
object Hover:
  given upickle.default.Reader[Hover] = Pickle.macroR

case class HoverRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object HoverRegistrationOptions:
  given upickle.default.Reader[HoverRegistrationOptions] = Pickle.macroR

case class SignatureHelpParams(
  context: structures.SignatureHelpContext,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object SignatureHelpParams:
  given upickle.default.Reader[SignatureHelpParams] = Pickle.macroR

case class SignatureHelp(
  signatures: Vector[structures.SignatureInformation],
  activeSignature: RuntimeBase.uinteger,
  activeParameter: RuntimeBase.uinteger
)
object SignatureHelp:
  given upickle.default.Reader[SignatureHelp] = Pickle.macroR

case class SignatureHelpRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  triggerCharacters: Vector[String],
  retriggerCharacters: Vector[String]
)
object SignatureHelpRegistrationOptions:
  given upickle.default.Reader[SignatureHelpRegistrationOptions] = Pickle.macroR

case class DefinitionParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DefinitionParams:
  given upickle.default.Reader[DefinitionParams] = Pickle.macroR

case class DefinitionRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object DefinitionRegistrationOptions:
  given upickle.default.Reader[DefinitionRegistrationOptions] = Pickle.macroR

case class ReferenceParams(
  context: structures.ReferenceContext,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object ReferenceParams:
  given upickle.default.Reader[ReferenceParams] = Pickle.macroR

case class ReferenceRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object ReferenceRegistrationOptions:
  given upickle.default.Reader[ReferenceRegistrationOptions] = Pickle.macroR

case class DocumentHighlightParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DocumentHighlightParams:
  given upickle.default.Reader[DocumentHighlightParams] = Pickle.macroR

case class DocumentHighlight(
  range: structures.Range,
  kind: enumerations.DocumentHighlightKind
)
object DocumentHighlight:
  given upickle.default.Reader[DocumentHighlight] = Pickle.macroR

case class DocumentHighlightRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object DocumentHighlightRegistrationOptions:
  given upickle.default.Reader[DocumentHighlightRegistrationOptions] = Pickle.macroR

case class DocumentSymbolParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DocumentSymbolParams:
  given upickle.default.Reader[DocumentSymbolParams] = Pickle.macroR

case class SymbolInformation(
  deprecated: Boolean,
  location: structures.Location,
  name: String,
  kind: enumerations.SymbolKind,
  tags: Vector[enumerations.SymbolTag],
  containerName: String
)
object SymbolInformation:
  given upickle.default.Reader[SymbolInformation] = Pickle.macroR

case class DocumentSymbol(
  name: String,
  detail: String,
  kind: enumerations.SymbolKind,
  tags: Vector[enumerations.SymbolTag],
  deprecated: Boolean,
  range: structures.Range,
  selectionRange: structures.Range,
  children: Vector[structures.DocumentSymbol]
)
object DocumentSymbol:
  given upickle.default.Reader[DocumentSymbol] = Pickle.macroR

case class DocumentSymbolRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  label: String
)
object DocumentSymbolRegistrationOptions:
  given upickle.default.Reader[DocumentSymbolRegistrationOptions] = Pickle.macroR

case class CodeActionParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  context: structures.CodeActionContext,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object CodeActionParams:
  given upickle.default.Reader[CodeActionParams] = Pickle.macroR

case class Command(
  title: String,
  command: String,
  arguments: Vector[aliases.LSPAny]
)
object Command:
  given upickle.default.Reader[Command] = Pickle.macroR

case class CodeAction(
  title: String,
  kind: enumerations.CodeActionKind,
  diagnostics: Vector[structures.Diagnostic],
  isPreferred: Boolean,
  disabled: CodeAction.Disabled,
  edit: structures.WorkspaceEdit,
  command: structures.Command,
  data: aliases.LSPAny
)
object CodeAction:
  given upickle.default.Reader[CodeAction] = Pickle.macroR
  case class Disabled(
    reason: String
  )
  object Disabled:
    given upickle.default.Reader[Disabled] = Pickle.macroR

case class CodeActionRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  codeActionKinds: Vector[enumerations.CodeActionKind],
  resolveProvider: Boolean
)
object CodeActionRegistrationOptions:
  given upickle.default.Reader[CodeActionRegistrationOptions] = Pickle.macroR

case class WorkspaceSymbolParams(
  query: String,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object WorkspaceSymbolParams:
  given upickle.default.Reader[WorkspaceSymbolParams] = Pickle.macroR

case class WorkspaceSymbol(
  location: (structures.Location | WorkspaceSymbol.S0),
  data: aliases.LSPAny,
  name: String,
  kind: enumerations.SymbolKind,
  tags: Vector[enumerations.SymbolTag],
  containerName: String
)
object WorkspaceSymbol:
  given upickle.default.Reader[WorkspaceSymbol] = Pickle.macroR
  case class S0(
    uri: RuntimeBase.DocumentUri
  )
  object S0:
    given upickle.default.Reader[S0] = Pickle.macroR

case class WorkspaceSymbolRegistrationOptions(
  resolveProvider: Boolean
)
object WorkspaceSymbolRegistrationOptions:
  given upickle.default.Reader[WorkspaceSymbolRegistrationOptions] = Pickle.macroR

case class CodeLensParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object CodeLensParams:
  given upickle.default.Reader[CodeLensParams] = Pickle.macroR

case class CodeLens(
  range: structures.Range,
  command: structures.Command,
  data: aliases.LSPAny
)
object CodeLens:
  given upickle.default.Reader[CodeLens] = Pickle.macroR

case class CodeLensRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  resolveProvider: Boolean
)
object CodeLensRegistrationOptions:
  given upickle.default.Reader[CodeLensRegistrationOptions] = Pickle.macroR

case class DocumentLinkParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DocumentLinkParams:
  given upickle.default.Reader[DocumentLinkParams] = Pickle.macroR

case class DocumentLink(
  range: structures.Range,
  target: String,
  tooltip: String,
  data: aliases.LSPAny
)
object DocumentLink:
  given upickle.default.Reader[DocumentLink] = Pickle.macroR

case class DocumentLinkRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  resolveProvider: Boolean
)
object DocumentLinkRegistrationOptions:
  given upickle.default.Reader[DocumentLinkRegistrationOptions] = Pickle.macroR

case class DocumentFormattingParams(
  textDocument: structures.TextDocumentIdentifier,
  options: structures.FormattingOptions,
  workDoneToken: aliases.ProgressToken
)
object DocumentFormattingParams:
  given upickle.default.Reader[DocumentFormattingParams] = Pickle.macroR

case class DocumentFormattingRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object DocumentFormattingRegistrationOptions:
  given upickle.default.Reader[DocumentFormattingRegistrationOptions] = Pickle.macroR

case class DocumentRangeFormattingParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  options: structures.FormattingOptions,
  workDoneToken: aliases.ProgressToken
)
object DocumentRangeFormattingParams:
  given upickle.default.Reader[DocumentRangeFormattingParams] = Pickle.macroR

case class DocumentRangeFormattingRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object DocumentRangeFormattingRegistrationOptions:
  given upickle.default.Reader[DocumentRangeFormattingRegistrationOptions] = Pickle.macroR

case class DocumentOnTypeFormattingParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  ch: String,
  options: structures.FormattingOptions
)
object DocumentOnTypeFormattingParams:
  given upickle.default.Reader[DocumentOnTypeFormattingParams] = Pickle.macroR

case class DocumentOnTypeFormattingRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  firstTriggerCharacter: String,
  moreTriggerCharacter: Vector[String]
)
object DocumentOnTypeFormattingRegistrationOptions:
  given upickle.default.Reader[DocumentOnTypeFormattingRegistrationOptions] = Pickle.macroR

case class RenameParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  newName: String,
  workDoneToken: aliases.ProgressToken
)
object RenameParams:
  given upickle.default.Reader[RenameParams] = Pickle.macroR

case class RenameRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  prepareProvider: Boolean
)
object RenameRegistrationOptions:
  given upickle.default.Reader[RenameRegistrationOptions] = Pickle.macroR

case class PrepareRenameParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object PrepareRenameParams:
  given upickle.default.Reader[PrepareRenameParams] = Pickle.macroR

case class ExecuteCommandParams(
  command: String,
  arguments: Vector[aliases.LSPAny],
  workDoneToken: aliases.ProgressToken
)
object ExecuteCommandParams:
  given upickle.default.Reader[ExecuteCommandParams] = Pickle.macroR

case class ExecuteCommandRegistrationOptions(
  commands: Vector[String]
)
object ExecuteCommandRegistrationOptions:
  given upickle.default.Reader[ExecuteCommandRegistrationOptions] = Pickle.macroR

case class ApplyWorkspaceEditParams(
  label: String,
  edit: structures.WorkspaceEdit
)
object ApplyWorkspaceEditParams:
  given upickle.default.Reader[ApplyWorkspaceEditParams] = Pickle.macroR

case class ApplyWorkspaceEditResult(
  applied: Boolean,
  failureReason: String,
  failedChange: RuntimeBase.uinteger
)
object ApplyWorkspaceEditResult:
  given upickle.default.Reader[ApplyWorkspaceEditResult] = Pickle.macroR

case class WorkDoneProgressBegin(
  kind: "begin",
  title: String,
  cancellable: Boolean,
  message: String,
  percentage: RuntimeBase.uinteger
)
object WorkDoneProgressBegin:
  given upickle.default.Reader[WorkDoneProgressBegin] = Pickle.macroR

case class WorkDoneProgressReport(
  kind: "report",
  cancellable: Boolean,
  message: String,
  percentage: RuntimeBase.uinteger
)
object WorkDoneProgressReport:
  given upickle.default.Reader[WorkDoneProgressReport] = Pickle.macroR

case class WorkDoneProgressEnd(
  kind: "end",
  message: String
)
object WorkDoneProgressEnd:
  given upickle.default.Reader[WorkDoneProgressEnd] = Pickle.macroR

case class SetTraceParams(
  value: enumerations.TraceValues
)
object SetTraceParams:
  given upickle.default.Reader[SetTraceParams] = Pickle.macroR

case class LogTraceParams(
  message: String,
  verbose: String
)
object LogTraceParams:
  given upickle.default.Reader[LogTraceParams] = Pickle.macroR

case class CancelParams(
  id: (Int | String)
)
object CancelParams:
  given upickle.default.Reader[CancelParams] = Pickle.macroR

case class ProgressParams(
  token: aliases.ProgressToken,
  value: aliases.LSPAny
)
object ProgressParams:
  given upickle.default.Reader[ProgressParams] = Pickle.macroR

case class TextDocumentPositionParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position
)
object TextDocumentPositionParams:
  given upickle.default.Reader[TextDocumentPositionParams] = Pickle.macroR

case class WorkDoneProgressParams(
  workDoneToken: aliases.ProgressToken
)
object WorkDoneProgressParams:
  given upickle.default.Reader[WorkDoneProgressParams] = Pickle.macroR

case class LocationLink(
  originSelectionRange: structures.Range,
  targetUri: RuntimeBase.DocumentUri,
  targetRange: structures.Range,
  targetSelectionRange: structures.Range
)
object LocationLink:
  given upickle.default.Reader[LocationLink] = Pickle.macroR

case class Range(
  start: structures.Position,
  end: structures.Position
)
object Range:
  given upickle.default.Reader[Range] = Pickle.macroR

case class ImplementationOptions(
  workDoneProgress: Boolean
)
object ImplementationOptions:
  given upickle.default.Reader[ImplementationOptions] = Pickle.macroR

case class StaticRegistrationOptions(
  id: String
)
object StaticRegistrationOptions:
  given upickle.default.Reader[StaticRegistrationOptions] = Pickle.macroR

case class TypeDefinitionOptions(
  workDoneProgress: Boolean
)
object TypeDefinitionOptions:
  given upickle.default.Reader[TypeDefinitionOptions] = Pickle.macroR

case class WorkspaceFoldersChangeEvent(
  added: Vector[structures.WorkspaceFolder],
  removed: Vector[structures.WorkspaceFolder]
)
object WorkspaceFoldersChangeEvent:
  given upickle.default.Reader[WorkspaceFoldersChangeEvent] = Pickle.macroR

case class ConfigurationItem(
  scopeUri: String,
  section: String
)
object ConfigurationItem:
  given upickle.default.Reader[ConfigurationItem] = Pickle.macroR

case class TextDocumentIdentifier(
  uri: RuntimeBase.DocumentUri
)
object TextDocumentIdentifier:
  given upickle.default.Reader[TextDocumentIdentifier] = Pickle.macroR

case class Color(
  red: Float,
  green: Float,
  blue: Float,
  alpha: Float
)
object Color:
  given upickle.default.Reader[Color] = Pickle.macroR

case class DocumentColorOptions(
  workDoneProgress: Boolean
)
object DocumentColorOptions:
  given upickle.default.Reader[DocumentColorOptions] = Pickle.macroR

case class FoldingRangeOptions(
  workDoneProgress: Boolean
)
object FoldingRangeOptions:
  given upickle.default.Reader[FoldingRangeOptions] = Pickle.macroR

case class DeclarationOptions(
  workDoneProgress: Boolean
)
object DeclarationOptions:
  given upickle.default.Reader[DeclarationOptions] = Pickle.macroR

case class Position(
  line: RuntimeBase.uinteger,
  character: RuntimeBase.uinteger
)
object Position:
  given upickle.default.Reader[Position] = Pickle.macroR

case class SelectionRangeOptions(
  workDoneProgress: Boolean
)
object SelectionRangeOptions:
  given upickle.default.Reader[SelectionRangeOptions] = Pickle.macroR

case class CallHierarchyOptions(
  workDoneProgress: Boolean
)
object CallHierarchyOptions:
  given upickle.default.Reader[CallHierarchyOptions] = Pickle.macroR

case class SemanticTokensOptions(
  legend: structures.SemanticTokensLegend,
  range: (Boolean | SemanticTokensOptions.S0),
  full: (Boolean | SemanticTokensOptions.S1),
  workDoneProgress: Boolean
)
object SemanticTokensOptions:
  given upickle.default.Reader[SemanticTokensOptions] = Pickle.macroR
  case class S0(
  )
  object S0:
    given upickle.default.Reader[S0] = Pickle.macroR
  case class S1(
    delta: Boolean
  )
  object S1:
    given upickle.default.Reader[S1] = Pickle.macroR

case class SemanticTokensEdit(
  start: RuntimeBase.uinteger,
  deleteCount: RuntimeBase.uinteger,
  data: Vector[RuntimeBase.uinteger]
)
object SemanticTokensEdit:
  given upickle.default.Reader[SemanticTokensEdit] = Pickle.macroR

case class LinkedEditingRangeOptions(
  workDoneProgress: Boolean
)
object LinkedEditingRangeOptions:
  given upickle.default.Reader[LinkedEditingRangeOptions] = Pickle.macroR

case class FileCreate(
  uri: String
)
object FileCreate:
  given upickle.default.Reader[FileCreate] = Pickle.macroR

case class TextDocumentEdit(
  textDocument: structures.OptionalVersionedTextDocumentIdentifier,
  edits: Vector[(structures.TextEdit | structures.AnnotatedTextEdit)]
)
object TextDocumentEdit:
  given upickle.default.Reader[TextDocumentEdit] = Pickle.macroR

case class CreateFile(
  kind: "create",
  uri: RuntimeBase.DocumentUri,
  options: structures.CreateFileOptions,
  kind: String,
  annotationId: aliases.ChangeAnnotationIdentifier
)
object CreateFile:
  given upickle.default.Reader[CreateFile] = Pickle.macroR

case class RenameFile(
  kind: "rename",
  oldUri: RuntimeBase.DocumentUri,
  newUri: RuntimeBase.DocumentUri,
  options: structures.RenameFileOptions,
  kind: String,
  annotationId: aliases.ChangeAnnotationIdentifier
)
object RenameFile:
  given upickle.default.Reader[RenameFile] = Pickle.macroR

case class DeleteFile(
  kind: "delete",
  uri: RuntimeBase.DocumentUri,
  options: structures.DeleteFileOptions,
  kind: String,
  annotationId: aliases.ChangeAnnotationIdentifier
)
object DeleteFile:
  given upickle.default.Reader[DeleteFile] = Pickle.macroR

case class ChangeAnnotation(
  label: String,
  needsConfirmation: Boolean,
  description: String
)
object ChangeAnnotation:
  given upickle.default.Reader[ChangeAnnotation] = Pickle.macroR

case class FileOperationFilter(
  scheme: String,
  pattern: structures.FileOperationPattern
)
object FileOperationFilter:
  given upickle.default.Reader[FileOperationFilter] = Pickle.macroR

case class FileRename(
  oldUri: String,
  newUri: String
)
object FileRename:
  given upickle.default.Reader[FileRename] = Pickle.macroR

case class FileDelete(
  uri: String
)
object FileDelete:
  given upickle.default.Reader[FileDelete] = Pickle.macroR

case class MonikerOptions(
  workDoneProgress: Boolean
)
object MonikerOptions:
  given upickle.default.Reader[MonikerOptions] = Pickle.macroR

case class TypeHierarchyOptions(
  workDoneProgress: Boolean
)
object TypeHierarchyOptions:
  given upickle.default.Reader[TypeHierarchyOptions] = Pickle.macroR

case class InlineValueContext(
  frameId: Int,
  stoppedLocation: structures.Range
)
object InlineValueContext:
  given upickle.default.Reader[InlineValueContext] = Pickle.macroR

case class InlineValueText(
  range: structures.Range,
  text: String
)
object InlineValueText:
  given upickle.default.Reader[InlineValueText] = Pickle.macroR

case class InlineValueVariableLookup(
  range: structures.Range,
  variableName: String,
  caseSensitiveLookup: Boolean
)
object InlineValueVariableLookup:
  given upickle.default.Reader[InlineValueVariableLookup] = Pickle.macroR

case class InlineValueEvaluatableExpression(
  range: structures.Range,
  expression: String
)
object InlineValueEvaluatableExpression:
  given upickle.default.Reader[InlineValueEvaluatableExpression] = Pickle.macroR

case class InlineValueOptions(
  workDoneProgress: Boolean
)
object InlineValueOptions:
  given upickle.default.Reader[InlineValueOptions] = Pickle.macroR

case class InlayHintLabelPart(
  value: String,
  tooltip: (String | structures.MarkupContent),
  location: structures.Location,
  command: structures.Command
)
object InlayHintLabelPart:
  given upickle.default.Reader[InlayHintLabelPart] = Pickle.macroR

case class MarkupContent(
  kind: enumerations.MarkupKind,
  value: String
)
object MarkupContent:
  given upickle.default.Reader[MarkupContent] = Pickle.macroR

case class InlayHintOptions(
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object InlayHintOptions:
  given upickle.default.Reader[InlayHintOptions] = Pickle.macroR

case class RelatedFullDocumentDiagnosticReport(
  relatedDocuments: Map[RuntimeBase.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)],
  kind: "full",
  resultId: String,
  items: Vector[structures.Diagnostic]
)
object RelatedFullDocumentDiagnosticReport:
  given upickle.default.Reader[RelatedFullDocumentDiagnosticReport] = Pickle.macroR

case class RelatedUnchangedDocumentDiagnosticReport(
  relatedDocuments: Map[RuntimeBase.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)],
  kind: "unchanged",
  resultId: String
)
object RelatedUnchangedDocumentDiagnosticReport:
  given upickle.default.Reader[RelatedUnchangedDocumentDiagnosticReport] = Pickle.macroR

case class FullDocumentDiagnosticReport(
  kind: "full",
  resultId: String,
  items: Vector[structures.Diagnostic]
)
object FullDocumentDiagnosticReport:
  given upickle.default.Reader[FullDocumentDiagnosticReport] = Pickle.macroR

case class UnchangedDocumentDiagnosticReport(
  kind: "unchanged",
  resultId: String
)
object UnchangedDocumentDiagnosticReport:
  given upickle.default.Reader[UnchangedDocumentDiagnosticReport] = Pickle.macroR

case class DiagnosticOptions(
  identifier: String,
  interFileDependencies: Boolean,
  workspaceDiagnostics: Boolean,
  workDoneProgress: Boolean
)
object DiagnosticOptions:
  given upickle.default.Reader[DiagnosticOptions] = Pickle.macroR

case class PreviousResultId(
  uri: RuntimeBase.DocumentUri,
  value: String
)
object PreviousResultId:
  given upickle.default.Reader[PreviousResultId] = Pickle.macroR

case class NotebookDocument(
  uri: aliases.URI,
  notebookType: String,
  version: Int,
  metadata: structures.LSPObject,
  cells: Vector[structures.NotebookCell]
)
object NotebookDocument:
  given upickle.default.Reader[NotebookDocument] = Pickle.macroR

case class TextDocumentItem(
  uri: RuntimeBase.DocumentUri,
  languageId: String,
  version: Int,
  text: String
)
object TextDocumentItem:
  given upickle.default.Reader[TextDocumentItem] = Pickle.macroR

case class VersionedNotebookDocumentIdentifier(
  version: Int,
  uri: aliases.URI
)
object VersionedNotebookDocumentIdentifier:
  given upickle.default.Reader[VersionedNotebookDocumentIdentifier] = Pickle.macroR

case class NotebookDocumentChangeEvent(
  metadata: structures.LSPObject,
  cells: NotebookDocumentChangeEvent.Cells
)
object NotebookDocumentChangeEvent:
  given upickle.default.Reader[NotebookDocumentChangeEvent] = Pickle.macroR
  case class Cells(
    structure: Cells.Structure,
    data: Vector[structures.NotebookCell],
    textContent: Vector[Cells.S0]
  )
  object Cells:
    given upickle.default.Reader[Cells] = Pickle.macroR
    case class Structure(
      array: structures.NotebookCellArrayChange,
      didOpen: Vector[structures.TextDocumentItem],
      didClose: Vector[structures.TextDocumentIdentifier]
    )
    object Structure:
      given upickle.default.Reader[Structure] = Pickle.macroR
    case class S0(
      document: structures.VersionedTextDocumentIdentifier,
      changes: Vector[aliases.TextDocumentContentChangeEvent]
    )
    object S0:
      given upickle.default.Reader[S0] = Pickle.macroR

case class NotebookDocumentIdentifier(
  uri: aliases.URI
)
object NotebookDocumentIdentifier:
  given upickle.default.Reader[NotebookDocumentIdentifier] = Pickle.macroR

case class Registration(
  id: String,
  method: String,
  registerOptions: aliases.LSPAny
)
object Registration:
  given upickle.default.Reader[Registration] = Pickle.macroR

case class Unregistration(
  id: String,
  method: String
)
object Unregistration:
  given upickle.default.Reader[Unregistration] = Pickle.macroR

case class _InitializeParams(
  processId: (Int | Null),
  clientInfo: _InitializeParams.ClientInfo,
  locale: String,
  rootPath: (String | Null),
  rootUri: (RuntimeBase.DocumentUri | Null),
  capabilities: structures.ClientCapabilities,
  initializationOptions: aliases.LSPAny,
  trace: ("off" | "messages" | "compact" | "verbose"),
  workDoneToken: aliases.ProgressToken
)
object _InitializeParams:
  given upickle.default.Reader[_InitializeParams] = Pickle.macroR
  case class ClientInfo(
    name: String,
    version: String
  )
  object ClientInfo:
    given upickle.default.Reader[ClientInfo] = Pickle.macroR

case class WorkspaceFoldersInitializeParams(
  workspaceFolders: (Vector[structures.WorkspaceFolder] | Null)
)
object WorkspaceFoldersInitializeParams:
  given upickle.default.Reader[WorkspaceFoldersInitializeParams] = Pickle.macroR

case class ServerCapabilities(
  positionEncoding: enumerations.PositionEncodingKind,
  textDocumentSync: (structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind),
  notebookDocumentSync: (structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions),
  completionProvider: structures.CompletionOptions,
  hoverProvider: (Boolean | structures.HoverOptions),
  signatureHelpProvider: structures.SignatureHelpOptions,
  declarationProvider: (Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions),
  definitionProvider: (Boolean | structures.DefinitionOptions),
  typeDefinitionProvider: (Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions),
  implementationProvider: (Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions),
  referencesProvider: (Boolean | structures.ReferenceOptions),
  documentHighlightProvider: (Boolean | structures.DocumentHighlightOptions),
  documentSymbolProvider: (Boolean | structures.DocumentSymbolOptions),
  codeActionProvider: (Boolean | structures.CodeActionOptions),
  codeLensProvider: structures.CodeLensOptions,
  documentLinkProvider: structures.DocumentLinkOptions,
  colorProvider: (Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions),
  workspaceSymbolProvider: (Boolean | structures.WorkspaceSymbolOptions),
  documentFormattingProvider: (Boolean | structures.DocumentFormattingOptions),
  documentRangeFormattingProvider: (Boolean | structures.DocumentRangeFormattingOptions),
  documentOnTypeFormattingProvider: structures.DocumentOnTypeFormattingOptions,
  renameProvider: (Boolean | structures.RenameOptions),
  foldingRangeProvider: (Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions),
  selectionRangeProvider: (Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions),
  executeCommandProvider: structures.ExecuteCommandOptions,
  callHierarchyProvider: (Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions),
  linkedEditingRangeProvider: (Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions),
  semanticTokensProvider: (structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions),
  monikerProvider: (Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions),
  typeHierarchyProvider: (Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions),
  inlineValueProvider: (Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions),
  inlayHintProvider: (Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions),
  diagnosticProvider: (structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions),
  workspace: ServerCapabilities.Workspace,
  experimental: structures.T
)
object ServerCapabilities:
  given upickle.default.Reader[ServerCapabilities] = Pickle.macroR
  case class Workspace(
    workspaceFolders: structures.WorkspaceFoldersServerCapabilities,
    fileOperations: structures.FileOperationOptions
  )
  object Workspace:
    given upickle.default.Reader[Workspace] = Pickle.macroR

case class VersionedTextDocumentIdentifier(
  version: Int,
  uri: RuntimeBase.DocumentUri
)
object VersionedTextDocumentIdentifier:
  given upickle.default.Reader[VersionedTextDocumentIdentifier] = Pickle.macroR

case class SaveOptions(
  includeText: Boolean
)
object SaveOptions:
  given upickle.default.Reader[SaveOptions] = Pickle.macroR

case class FileEvent(
  uri: RuntimeBase.DocumentUri,
  `type`: enumerations.FileChangeType
)
object FileEvent:
  given upickle.default.Reader[FileEvent] = Pickle.macroR

case class FileSystemWatcher(
  globPattern: aliases.GlobPattern,
  kind: enumerations.WatchKind
)
object FileSystemWatcher:
  given upickle.default.Reader[FileSystemWatcher] = Pickle.macroR

case class Diagnostic(
  range: structures.Range,
  severity: enumerations.DiagnosticSeverity,
  code: (Int | String),
  codeDescription: structures.CodeDescription,
  source: String,
  message: String,
  tags: Vector[enumerations.DiagnosticTag],
  relatedInformation: Vector[structures.DiagnosticRelatedInformation],
  data: aliases.LSPAny
)
object Diagnostic:
  given upickle.default.Reader[Diagnostic] = Pickle.macroR

case class CompletionContext(
  triggerKind: enumerations.CompletionTriggerKind,
  triggerCharacter: String
)
object CompletionContext:
  given upickle.default.Reader[CompletionContext] = Pickle.macroR

case class CompletionItemLabelDetails(
  detail: String,
  description: String
)
object CompletionItemLabelDetails:
  given upickle.default.Reader[CompletionItemLabelDetails] = Pickle.macroR

case class InsertReplaceEdit(
  newText: String,
  insert: structures.Range,
  replace: structures.Range
)
object InsertReplaceEdit:
  given upickle.default.Reader[InsertReplaceEdit] = Pickle.macroR

case class CompletionOptions(
  triggerCharacters: Vector[String],
  allCommitCharacters: Vector[String],
  resolveProvider: Boolean,
  completionItem: CompletionOptions.CompletionItem,
  workDoneProgress: Boolean
)
object CompletionOptions:
  given upickle.default.Reader[CompletionOptions] = Pickle.macroR
  case class CompletionItem(
    labelDetailsSupport: Boolean
  )
  object CompletionItem:
    given upickle.default.Reader[CompletionItem] = Pickle.macroR

case class HoverOptions(
  workDoneProgress: Boolean
)
object HoverOptions:
  given upickle.default.Reader[HoverOptions] = Pickle.macroR

case class SignatureHelpContext(
  triggerKind: enumerations.SignatureHelpTriggerKind,
  triggerCharacter: String,
  isRetrigger: Boolean,
  activeSignatureHelp: structures.SignatureHelp
)
object SignatureHelpContext:
  given upickle.default.Reader[SignatureHelpContext] = Pickle.macroR

case class SignatureInformation(
  label: String,
  documentation: (String | structures.MarkupContent),
  parameters: Vector[structures.ParameterInformation],
  activeParameter: RuntimeBase.uinteger
)
object SignatureInformation:
  given upickle.default.Reader[SignatureInformation] = Pickle.macroR

case class SignatureHelpOptions(
  triggerCharacters: Vector[String],
  retriggerCharacters: Vector[String],
  workDoneProgress: Boolean
)
object SignatureHelpOptions:
  given upickle.default.Reader[SignatureHelpOptions] = Pickle.macroR

case class DefinitionOptions(
  workDoneProgress: Boolean
)
object DefinitionOptions:
  given upickle.default.Reader[DefinitionOptions] = Pickle.macroR

case class ReferenceContext(
  includeDeclaration: Boolean
)
object ReferenceContext:
  given upickle.default.Reader[ReferenceContext] = Pickle.macroR

case class ReferenceOptions(
  workDoneProgress: Boolean
)
object ReferenceOptions:
  given upickle.default.Reader[ReferenceOptions] = Pickle.macroR

case class DocumentHighlightOptions(
  workDoneProgress: Boolean
)
object DocumentHighlightOptions:
  given upickle.default.Reader[DocumentHighlightOptions] = Pickle.macroR

case class BaseSymbolInformation(
  name: String,
  kind: enumerations.SymbolKind,
  tags: Vector[enumerations.SymbolTag],
  containerName: String
)
object BaseSymbolInformation:
  given upickle.default.Reader[BaseSymbolInformation] = Pickle.macroR

case class DocumentSymbolOptions(
  label: String,
  workDoneProgress: Boolean
)
object DocumentSymbolOptions:
  given upickle.default.Reader[DocumentSymbolOptions] = Pickle.macroR

case class CodeActionContext(
  diagnostics: Vector[structures.Diagnostic],
  only: Vector[enumerations.CodeActionKind],
  triggerKind: enumerations.CodeActionTriggerKind
)
object CodeActionContext:
  given upickle.default.Reader[CodeActionContext] = Pickle.macroR

case class CodeActionOptions(
  codeActionKinds: Vector[enumerations.CodeActionKind],
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object CodeActionOptions:
  given upickle.default.Reader[CodeActionOptions] = Pickle.macroR

case class WorkspaceSymbolOptions(
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object WorkspaceSymbolOptions:
  given upickle.default.Reader[WorkspaceSymbolOptions] = Pickle.macroR

case class CodeLensOptions(
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object CodeLensOptions:
  given upickle.default.Reader[CodeLensOptions] = Pickle.macroR

case class DocumentLinkOptions(
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object DocumentLinkOptions:
  given upickle.default.Reader[DocumentLinkOptions] = Pickle.macroR

case class FormattingOptions(
  tabSize: RuntimeBase.uinteger,
  insertSpaces: Boolean,
  trimTrailingWhitespace: Boolean,
  insertFinalNewline: Boolean,
  trimFinalNewlines: Boolean
)
object FormattingOptions:
  given upickle.default.Reader[FormattingOptions] = Pickle.macroR

case class DocumentFormattingOptions(
  workDoneProgress: Boolean
)
object DocumentFormattingOptions:
  given upickle.default.Reader[DocumentFormattingOptions] = Pickle.macroR

case class DocumentRangeFormattingOptions(
  workDoneProgress: Boolean
)
object DocumentRangeFormattingOptions:
  given upickle.default.Reader[DocumentRangeFormattingOptions] = Pickle.macroR

case class DocumentOnTypeFormattingOptions(
  firstTriggerCharacter: String,
  moreTriggerCharacter: Vector[String]
)
object DocumentOnTypeFormattingOptions:
  given upickle.default.Reader[DocumentOnTypeFormattingOptions] = Pickle.macroR

case class RenameOptions(
  prepareProvider: Boolean,
  workDoneProgress: Boolean
)
object RenameOptions:
  given upickle.default.Reader[RenameOptions] = Pickle.macroR

case class ExecuteCommandOptions(
  commands: Vector[String],
  workDoneProgress: Boolean
)
object ExecuteCommandOptions:
  given upickle.default.Reader[ExecuteCommandOptions] = Pickle.macroR

case class SemanticTokensLegend(
  tokenTypes: Vector[String],
  tokenModifiers: Vector[String]
)
object SemanticTokensLegend:
  given upickle.default.Reader[SemanticTokensLegend] = Pickle.macroR

case class OptionalVersionedTextDocumentIdentifier(
  version: (Int | Null),
  uri: RuntimeBase.DocumentUri
)
object OptionalVersionedTextDocumentIdentifier:
  given upickle.default.Reader[OptionalVersionedTextDocumentIdentifier] = Pickle.macroR

case class AnnotatedTextEdit(
  annotationId: aliases.ChangeAnnotationIdentifier,
  range: structures.Range,
  newText: String
)
object AnnotatedTextEdit:
  given upickle.default.Reader[AnnotatedTextEdit] = Pickle.macroR

case class ResourceOperation(
  kind: String,
  annotationId: aliases.ChangeAnnotationIdentifier
)
object ResourceOperation:
  given upickle.default.Reader[ResourceOperation] = Pickle.macroR

case class CreateFileOptions(
  overwrite: Boolean,
  ignoreIfExists: Boolean
)
object CreateFileOptions:
  given upickle.default.Reader[CreateFileOptions] = Pickle.macroR

case class RenameFileOptions(
  overwrite: Boolean,
  ignoreIfExists: Boolean
)
object RenameFileOptions:
  given upickle.default.Reader[RenameFileOptions] = Pickle.macroR

case class DeleteFileOptions(
  recursive: Boolean,
  ignoreIfNotExists: Boolean
)
object DeleteFileOptions:
  given upickle.default.Reader[DeleteFileOptions] = Pickle.macroR

case class FileOperationPattern(
  glob: String,
  matches: enumerations.FileOperationPatternKind,
  options: structures.FileOperationPatternOptions
)
object FileOperationPattern:
  given upickle.default.Reader[FileOperationPattern] = Pickle.macroR

case class WorkspaceFullDocumentDiagnosticReport(
  uri: RuntimeBase.DocumentUri,
  version: (Int | Null),
  kind: "full",
  resultId: String,
  items: Vector[structures.Diagnostic]
)
object WorkspaceFullDocumentDiagnosticReport:
  given upickle.default.Reader[WorkspaceFullDocumentDiagnosticReport] = Pickle.macroR

case class WorkspaceUnchangedDocumentDiagnosticReport(
  uri: RuntimeBase.DocumentUri,
  version: (Int | Null),
  kind: "unchanged",
  resultId: String
)
object WorkspaceUnchangedDocumentDiagnosticReport:
  given upickle.default.Reader[WorkspaceUnchangedDocumentDiagnosticReport] = Pickle.macroR

case class LSPObject(
)
object LSPObject:
  given upickle.default.Reader[LSPObject] = Pickle.macroR

case class NotebookCell(
  kind: enumerations.NotebookCellKind,
  document: RuntimeBase.DocumentUri,
  metadata: structures.LSPObject,
  executionSummary: structures.ExecutionSummary
)
object NotebookCell:
  given upickle.default.Reader[NotebookCell] = Pickle.macroR

case class NotebookCellArrayChange(
  start: RuntimeBase.uinteger,
  deleteCount: RuntimeBase.uinteger,
  cells: Vector[structures.NotebookCell]
)
object NotebookCellArrayChange:
  given upickle.default.Reader[NotebookCellArrayChange] = Pickle.macroR

case class ClientCapabilities(
  workspace: structures.WorkspaceClientCapabilities,
  textDocument: structures.TextDocumentClientCapabilities,
  notebookDocument: structures.NotebookDocumentClientCapabilities,
  window: structures.WindowClientCapabilities,
  general: structures.GeneralClientCapabilities,
  experimental: aliases.LSPAny
)
object ClientCapabilities:
  given upickle.default.Reader[ClientCapabilities] = Pickle.macroR

case class TextDocumentSyncOptions(
  openClose: Boolean,
  change: enumerations.TextDocumentSyncKind,
  willSave: Boolean,
  willSaveWaitUntil: Boolean,
  save: (Boolean | structures.SaveOptions)
)
object TextDocumentSyncOptions:
  given upickle.default.Reader[TextDocumentSyncOptions] = Pickle.macroR

case class NotebookDocumentSyncOptions(
  notebookSelector: Vector[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)],
  save: Boolean
)
object NotebookDocumentSyncOptions:
  given upickle.default.Reader[NotebookDocumentSyncOptions] = Pickle.macroR
  case class S0(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Vector[S0.S0]
  )
  object S0:
    given upickle.default.Reader[S0] = Pickle.macroR
    case class S0(
      language: String
    )
    object S0:
      given upickle.default.Reader[S0] = Pickle.macroR
  case class S1(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Vector[S1.S0]
  )
  object S1:
    given upickle.default.Reader[S1] = Pickle.macroR
    case class S0(
      language: String
    )
    object S0:
      given upickle.default.Reader[S0] = Pickle.macroR

case class NotebookDocumentSyncRegistrationOptions(
  notebookSelector: Vector[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)],
  save: Boolean,
  id: String
)
object NotebookDocumentSyncRegistrationOptions:
  given upickle.default.Reader[NotebookDocumentSyncRegistrationOptions] = Pickle.macroR
  case class S0(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Vector[S0.S0]
  )
  object S0:
    given upickle.default.Reader[S0] = Pickle.macroR
    case class S0(
      language: String
    )
    object S0:
      given upickle.default.Reader[S0] = Pickle.macroR
  case class S1(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Vector[S1.S0]
  )
  object S1:
    given upickle.default.Reader[S1] = Pickle.macroR
    case class S0(
      language: String
    )
    object S0:
      given upickle.default.Reader[S0] = Pickle.macroR

case class WorkspaceFoldersServerCapabilities(
  supported: Boolean,
  changeNotifications: (String | Boolean)
)
object WorkspaceFoldersServerCapabilities:
  given upickle.default.Reader[WorkspaceFoldersServerCapabilities] = Pickle.macroR

case class FileOperationOptions(
  didCreate: structures.FileOperationRegistrationOptions,
  willCreate: structures.FileOperationRegistrationOptions,
  didRename: structures.FileOperationRegistrationOptions,
  willRename: structures.FileOperationRegistrationOptions,
  didDelete: structures.FileOperationRegistrationOptions,
  willDelete: structures.FileOperationRegistrationOptions
)
object FileOperationOptions:
  given upickle.default.Reader[FileOperationOptions] = Pickle.macroR

case class T(
)
object T:
  given upickle.default.Reader[T] = Pickle.macroR

case class CodeDescription(
  href: aliases.URI
)
object CodeDescription:
  given upickle.default.Reader[CodeDescription] = Pickle.macroR

case class DiagnosticRelatedInformation(
  location: structures.Location,
  message: String
)
object DiagnosticRelatedInformation:
  given upickle.default.Reader[DiagnosticRelatedInformation] = Pickle.macroR

case class ParameterInformation(
  label: (String | (RuntimeBase.uinteger, RuntimeBase.uinteger)),
  documentation: (String | structures.MarkupContent)
)
object ParameterInformation:
  given upickle.default.Reader[ParameterInformation] = Pickle.macroR

case class NotebookCellTextDocumentFilter(
  notebook: (String | aliases.NotebookDocumentFilter),
  language: String
)
object NotebookCellTextDocumentFilter:
  given upickle.default.Reader[NotebookCellTextDocumentFilter] = Pickle.macroR

case class FileOperationPatternOptions(
  ignoreCase: Boolean
)
object FileOperationPatternOptions:
  given upickle.default.Reader[FileOperationPatternOptions] = Pickle.macroR

case class ExecutionSummary(
  executionOrder: RuntimeBase.uinteger,
  success: Boolean
)
object ExecutionSummary:
  given upickle.default.Reader[ExecutionSummary] = Pickle.macroR

case class WorkspaceClientCapabilities(
  applyEdit: Boolean,
  workspaceEdit: structures.WorkspaceEditClientCapabilities,
  didChangeConfiguration: structures.DidChangeConfigurationClientCapabilities,
  didChangeWatchedFiles: structures.DidChangeWatchedFilesClientCapabilities,
  symbol: structures.WorkspaceSymbolClientCapabilities,
  executeCommand: structures.ExecuteCommandClientCapabilities,
  workspaceFolders: Boolean,
  configuration: Boolean,
  semanticTokens: structures.SemanticTokensWorkspaceClientCapabilities,
  codeLens: structures.CodeLensWorkspaceClientCapabilities,
  fileOperations: structures.FileOperationClientCapabilities,
  inlineValue: structures.InlineValueWorkspaceClientCapabilities,
  inlayHint: structures.InlayHintWorkspaceClientCapabilities,
  diagnostics: structures.DiagnosticWorkspaceClientCapabilities
)
object WorkspaceClientCapabilities:
  given upickle.default.Reader[WorkspaceClientCapabilities] = Pickle.macroR

case class TextDocumentClientCapabilities(
  synchronization: structures.TextDocumentSyncClientCapabilities,
  completion: structures.CompletionClientCapabilities,
  hover: structures.HoverClientCapabilities,
  signatureHelp: structures.SignatureHelpClientCapabilities,
  declaration: structures.DeclarationClientCapabilities,
  definition: structures.DefinitionClientCapabilities,
  typeDefinition: structures.TypeDefinitionClientCapabilities,
  implementation: structures.ImplementationClientCapabilities,
  references: structures.ReferenceClientCapabilities,
  documentHighlight: structures.DocumentHighlightClientCapabilities,
  documentSymbol: structures.DocumentSymbolClientCapabilities,
  codeAction: structures.CodeActionClientCapabilities,
  codeLens: structures.CodeLensClientCapabilities,
  documentLink: structures.DocumentLinkClientCapabilities,
  colorProvider: structures.DocumentColorClientCapabilities,
  formatting: structures.DocumentFormattingClientCapabilities,
  rangeFormatting: structures.DocumentRangeFormattingClientCapabilities,
  onTypeFormatting: structures.DocumentOnTypeFormattingClientCapabilities,
  rename: structures.RenameClientCapabilities,
  foldingRange: structures.FoldingRangeClientCapabilities,
  selectionRange: structures.SelectionRangeClientCapabilities,
  publishDiagnostics: structures.PublishDiagnosticsClientCapabilities,
  callHierarchy: structures.CallHierarchyClientCapabilities,
  semanticTokens: structures.SemanticTokensClientCapabilities,
  linkedEditingRange: structures.LinkedEditingRangeClientCapabilities,
  moniker: structures.MonikerClientCapabilities,
  typeHierarchy: structures.TypeHierarchyClientCapabilities,
  inlineValue: structures.InlineValueClientCapabilities,
  inlayHint: structures.InlayHintClientCapabilities,
  diagnostic: structures.DiagnosticClientCapabilities
)
object TextDocumentClientCapabilities:
  given upickle.default.Reader[TextDocumentClientCapabilities] = Pickle.macroR

case class NotebookDocumentClientCapabilities(
  synchronization: structures.NotebookDocumentSyncClientCapabilities
)
object NotebookDocumentClientCapabilities:
  given upickle.default.Reader[NotebookDocumentClientCapabilities] = Pickle.macroR

case class WindowClientCapabilities(
  workDoneProgress: Boolean,
  showMessage: structures.ShowMessageRequestClientCapabilities,
  showDocument: structures.ShowDocumentClientCapabilities
)
object WindowClientCapabilities:
  given upickle.default.Reader[WindowClientCapabilities] = Pickle.macroR

case class GeneralClientCapabilities(
  staleRequestSupport: GeneralClientCapabilities.StaleRequestSupport,
  regularExpressions: structures.RegularExpressionsClientCapabilities,
  markdown: structures.MarkdownClientCapabilities,
  positionEncodings: Vector[enumerations.PositionEncodingKind]
)
object GeneralClientCapabilities:
  given upickle.default.Reader[GeneralClientCapabilities] = Pickle.macroR
  case class StaleRequestSupport(
    cancel: Boolean,
    retryOnContentModified: Vector[String]
  )
  object StaleRequestSupport:
    given upickle.default.Reader[StaleRequestSupport] = Pickle.macroR

case class RelativePattern(
  baseUri: (structures.WorkspaceFolder | aliases.URI),
  pattern: aliases.Pattern
)
object RelativePattern:
  given upickle.default.Reader[RelativePattern] = Pickle.macroR

case class WorkspaceEditClientCapabilities(
  documentChanges: Boolean,
  resourceOperations: Vector[enumerations.ResourceOperationKind],
  failureHandling: enumerations.FailureHandlingKind,
  normalizesLineEndings: Boolean,
  changeAnnotationSupport: WorkspaceEditClientCapabilities.ChangeAnnotationSupport
)
object WorkspaceEditClientCapabilities:
  given upickle.default.Reader[WorkspaceEditClientCapabilities] = Pickle.macroR
  case class ChangeAnnotationSupport(
    groupsOnLabel: Boolean
  )
  object ChangeAnnotationSupport:
    given upickle.default.Reader[ChangeAnnotationSupport] = Pickle.macroR

case class DidChangeConfigurationClientCapabilities(
  dynamicRegistration: Boolean
)
object DidChangeConfigurationClientCapabilities:
  given upickle.default.Reader[DidChangeConfigurationClientCapabilities] = Pickle.macroR

case class DidChangeWatchedFilesClientCapabilities(
  dynamicRegistration: Boolean,
  relativePatternSupport: Boolean
)
object DidChangeWatchedFilesClientCapabilities:
  given upickle.default.Reader[DidChangeWatchedFilesClientCapabilities] = Pickle.macroR

case class WorkspaceSymbolClientCapabilities(
  dynamicRegistration: Boolean,
  symbolKind: WorkspaceSymbolClientCapabilities.SymbolKind,
  tagSupport: WorkspaceSymbolClientCapabilities.TagSupport,
  resolveSupport: WorkspaceSymbolClientCapabilities.ResolveSupport
)
object WorkspaceSymbolClientCapabilities:
  given upickle.default.Reader[WorkspaceSymbolClientCapabilities] = Pickle.macroR
  case class SymbolKind(
    valueSet: Vector[enumerations.SymbolKind]
  )
  object SymbolKind:
    given upickle.default.Reader[SymbolKind] = Pickle.macroR
  case class TagSupport(
    valueSet: Vector[enumerations.SymbolTag]
  )
  object TagSupport:
    given upickle.default.Reader[TagSupport] = Pickle.macroR
  case class ResolveSupport(
    properties: Vector[String]
  )
  object ResolveSupport:
    given upickle.default.Reader[ResolveSupport] = Pickle.macroR

case class ExecuteCommandClientCapabilities(
  dynamicRegistration: Boolean
)
object ExecuteCommandClientCapabilities:
  given upickle.default.Reader[ExecuteCommandClientCapabilities] = Pickle.macroR

case class SemanticTokensWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object SemanticTokensWorkspaceClientCapabilities:
  given upickle.default.Reader[SemanticTokensWorkspaceClientCapabilities] = Pickle.macroR

case class CodeLensWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object CodeLensWorkspaceClientCapabilities:
  given upickle.default.Reader[CodeLensWorkspaceClientCapabilities] = Pickle.macroR

case class FileOperationClientCapabilities(
  dynamicRegistration: Boolean,
  didCreate: Boolean,
  willCreate: Boolean,
  didRename: Boolean,
  willRename: Boolean,
  didDelete: Boolean,
  willDelete: Boolean
)
object FileOperationClientCapabilities:
  given upickle.default.Reader[FileOperationClientCapabilities] = Pickle.macroR

case class InlineValueWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object InlineValueWorkspaceClientCapabilities:
  given upickle.default.Reader[InlineValueWorkspaceClientCapabilities] = Pickle.macroR

case class InlayHintWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object InlayHintWorkspaceClientCapabilities:
  given upickle.default.Reader[InlayHintWorkspaceClientCapabilities] = Pickle.macroR

case class DiagnosticWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object DiagnosticWorkspaceClientCapabilities:
  given upickle.default.Reader[DiagnosticWorkspaceClientCapabilities] = Pickle.macroR

case class TextDocumentSyncClientCapabilities(
  dynamicRegistration: Boolean,
  willSave: Boolean,
  willSaveWaitUntil: Boolean,
  didSave: Boolean
)
object TextDocumentSyncClientCapabilities:
  given upickle.default.Reader[TextDocumentSyncClientCapabilities] = Pickle.macroR

case class CompletionClientCapabilities(
  dynamicRegistration: Boolean,
  completionItem: CompletionClientCapabilities.CompletionItem,
  completionItemKind: CompletionClientCapabilities.CompletionItemKind,
  insertTextMode: enumerations.InsertTextMode,
  contextSupport: Boolean,
  completionList: CompletionClientCapabilities.CompletionList
)
object CompletionClientCapabilities:
  given upickle.default.Reader[CompletionClientCapabilities] = Pickle.macroR
  case class CompletionItem(
    snippetSupport: Boolean,
    commitCharactersSupport: Boolean,
    documentationFormat: Vector[enumerations.MarkupKind],
    deprecatedSupport: Boolean,
    preselectSupport: Boolean,
    tagSupport: CompletionItem.TagSupport,
    insertReplaceSupport: Boolean,
    resolveSupport: CompletionItem.ResolveSupport,
    insertTextModeSupport: CompletionItem.InsertTextModeSupport,
    labelDetailsSupport: Boolean
  )
  object CompletionItem:
    given upickle.default.Reader[CompletionItem] = Pickle.macroR
    case class TagSupport(
      valueSet: Vector[enumerations.CompletionItemTag]
    )
    object TagSupport:
      given upickle.default.Reader[TagSupport] = Pickle.macroR
    case class ResolveSupport(
      properties: Vector[String]
    )
    object ResolveSupport:
      given upickle.default.Reader[ResolveSupport] = Pickle.macroR
    case class InsertTextModeSupport(
      valueSet: Vector[enumerations.InsertTextMode]
    )
    object InsertTextModeSupport:
      given upickle.default.Reader[InsertTextModeSupport] = Pickle.macroR
  case class CompletionItemKind(
    valueSet: Vector[enumerations.CompletionItemKind]
  )
  object CompletionItemKind:
    given upickle.default.Reader[CompletionItemKind] = Pickle.macroR
  case class CompletionList(
    itemDefaults: Vector[String]
  )
  object CompletionList:
    given upickle.default.Reader[CompletionList] = Pickle.macroR

case class HoverClientCapabilities(
  dynamicRegistration: Boolean,
  contentFormat: Vector[enumerations.MarkupKind]
)
object HoverClientCapabilities:
  given upickle.default.Reader[HoverClientCapabilities] = Pickle.macroR

case class SignatureHelpClientCapabilities(
  dynamicRegistration: Boolean,
  signatureInformation: SignatureHelpClientCapabilities.SignatureInformation,
  contextSupport: Boolean
)
object SignatureHelpClientCapabilities:
  given upickle.default.Reader[SignatureHelpClientCapabilities] = Pickle.macroR
  case class SignatureInformation(
    documentationFormat: Vector[enumerations.MarkupKind],
    parameterInformation: SignatureInformation.ParameterInformation,
    activeParameterSupport: Boolean
  )
  object SignatureInformation:
    given upickle.default.Reader[SignatureInformation] = Pickle.macroR
    case class ParameterInformation(
      labelOffsetSupport: Boolean
    )
    object ParameterInformation:
      given upickle.default.Reader[ParameterInformation] = Pickle.macroR

case class DeclarationClientCapabilities(
  dynamicRegistration: Boolean,
  linkSupport: Boolean
)
object DeclarationClientCapabilities:
  given upickle.default.Reader[DeclarationClientCapabilities] = Pickle.macroR

case class DefinitionClientCapabilities(
  dynamicRegistration: Boolean,
  linkSupport: Boolean
)
object DefinitionClientCapabilities:
  given upickle.default.Reader[DefinitionClientCapabilities] = Pickle.macroR

case class TypeDefinitionClientCapabilities(
  dynamicRegistration: Boolean,
  linkSupport: Boolean
)
object TypeDefinitionClientCapabilities:
  given upickle.default.Reader[TypeDefinitionClientCapabilities] = Pickle.macroR

case class ImplementationClientCapabilities(
  dynamicRegistration: Boolean,
  linkSupport: Boolean
)
object ImplementationClientCapabilities:
  given upickle.default.Reader[ImplementationClientCapabilities] = Pickle.macroR

case class ReferenceClientCapabilities(
  dynamicRegistration: Boolean
)
object ReferenceClientCapabilities:
  given upickle.default.Reader[ReferenceClientCapabilities] = Pickle.macroR

case class DocumentHighlightClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentHighlightClientCapabilities:
  given upickle.default.Reader[DocumentHighlightClientCapabilities] = Pickle.macroR

case class DocumentSymbolClientCapabilities(
  dynamicRegistration: Boolean,
  symbolKind: DocumentSymbolClientCapabilities.SymbolKind,
  hierarchicalDocumentSymbolSupport: Boolean,
  tagSupport: DocumentSymbolClientCapabilities.TagSupport,
  labelSupport: Boolean
)
object DocumentSymbolClientCapabilities:
  given upickle.default.Reader[DocumentSymbolClientCapabilities] = Pickle.macroR
  case class SymbolKind(
    valueSet: Vector[enumerations.SymbolKind]
  )
  object SymbolKind:
    given upickle.default.Reader[SymbolKind] = Pickle.macroR
  case class TagSupport(
    valueSet: Vector[enumerations.SymbolTag]
  )
  object TagSupport:
    given upickle.default.Reader[TagSupport] = Pickle.macroR

case class CodeActionClientCapabilities(
  dynamicRegistration: Boolean,
  codeActionLiteralSupport: CodeActionClientCapabilities.CodeActionLiteralSupport,
  isPreferredSupport: Boolean,
  disabledSupport: Boolean,
  dataSupport: Boolean,
  resolveSupport: CodeActionClientCapabilities.ResolveSupport,
  honorsChangeAnnotations: Boolean
)
object CodeActionClientCapabilities:
  given upickle.default.Reader[CodeActionClientCapabilities] = Pickle.macroR
  case class CodeActionLiteralSupport(
    codeActionKind: CodeActionLiteralSupport.CodeActionKind
  )
  object CodeActionLiteralSupport:
    given upickle.default.Reader[CodeActionLiteralSupport] = Pickle.macroR
    case class CodeActionKind(
      valueSet: Vector[enumerations.CodeActionKind]
    )
    object CodeActionKind:
      given upickle.default.Reader[CodeActionKind] = Pickle.macroR
  case class ResolveSupport(
    properties: Vector[String]
  )
  object ResolveSupport:
    given upickle.default.Reader[ResolveSupport] = Pickle.macroR

case class CodeLensClientCapabilities(
  dynamicRegistration: Boolean
)
object CodeLensClientCapabilities:
  given upickle.default.Reader[CodeLensClientCapabilities] = Pickle.macroR

case class DocumentLinkClientCapabilities(
  dynamicRegistration: Boolean,
  tooltipSupport: Boolean
)
object DocumentLinkClientCapabilities:
  given upickle.default.Reader[DocumentLinkClientCapabilities] = Pickle.macroR

case class DocumentColorClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentColorClientCapabilities:
  given upickle.default.Reader[DocumentColorClientCapabilities] = Pickle.macroR

case class DocumentFormattingClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentFormattingClientCapabilities:
  given upickle.default.Reader[DocumentFormattingClientCapabilities] = Pickle.macroR

case class DocumentRangeFormattingClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentRangeFormattingClientCapabilities:
  given upickle.default.Reader[DocumentRangeFormattingClientCapabilities] = Pickle.macroR

case class DocumentOnTypeFormattingClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentOnTypeFormattingClientCapabilities:
  given upickle.default.Reader[DocumentOnTypeFormattingClientCapabilities] = Pickle.macroR

case class RenameClientCapabilities(
  dynamicRegistration: Boolean,
  prepareSupport: Boolean,
  prepareSupportDefaultBehavior: enumerations.PrepareSupportDefaultBehavior,
  honorsChangeAnnotations: Boolean
)
object RenameClientCapabilities:
  given upickle.default.Reader[RenameClientCapabilities] = Pickle.macroR

case class FoldingRangeClientCapabilities(
  dynamicRegistration: Boolean,
  rangeLimit: RuntimeBase.uinteger,
  lineFoldingOnly: Boolean,
  foldingRangeKind: FoldingRangeClientCapabilities.FoldingRangeKind,
  foldingRange: FoldingRangeClientCapabilities.FoldingRange
)
object FoldingRangeClientCapabilities:
  given upickle.default.Reader[FoldingRangeClientCapabilities] = Pickle.macroR
  case class FoldingRangeKind(
    valueSet: Vector[enumerations.FoldingRangeKind]
  )
  object FoldingRangeKind:
    given upickle.default.Reader[FoldingRangeKind] = Pickle.macroR
  case class FoldingRange(
    collapsedText: Boolean
  )
  object FoldingRange:
    given upickle.default.Reader[FoldingRange] = Pickle.macroR

case class SelectionRangeClientCapabilities(
  dynamicRegistration: Boolean
)
object SelectionRangeClientCapabilities:
  given upickle.default.Reader[SelectionRangeClientCapabilities] = Pickle.macroR

case class PublishDiagnosticsClientCapabilities(
  relatedInformation: Boolean,
  tagSupport: PublishDiagnosticsClientCapabilities.TagSupport,
  versionSupport: Boolean,
  codeDescriptionSupport: Boolean,
  dataSupport: Boolean
)
object PublishDiagnosticsClientCapabilities:
  given upickle.default.Reader[PublishDiagnosticsClientCapabilities] = Pickle.macroR
  case class TagSupport(
    valueSet: Vector[enumerations.DiagnosticTag]
  )
  object TagSupport:
    given upickle.default.Reader[TagSupport] = Pickle.macroR

case class CallHierarchyClientCapabilities(
  dynamicRegistration: Boolean
)
object CallHierarchyClientCapabilities:
  given upickle.default.Reader[CallHierarchyClientCapabilities] = Pickle.macroR

case class SemanticTokensClientCapabilities(
  dynamicRegistration: Boolean,
  requests: SemanticTokensClientCapabilities.Requests,
  tokenTypes: Vector[String],
  tokenModifiers: Vector[String],
  formats: Vector[enumerations.TokenFormat],
  overlappingTokenSupport: Boolean,
  multilineTokenSupport: Boolean,
  serverCancelSupport: Boolean,
  augmentsSyntaxTokens: Boolean
)
object SemanticTokensClientCapabilities:
  given upickle.default.Reader[SemanticTokensClientCapabilities] = Pickle.macroR
  case class Requests(
    range: (Boolean | Requests.S0),
    full: (Boolean | Requests.S1)
  )
  object Requests:
    given upickle.default.Reader[Requests] = Pickle.macroR
    case class S0(
    )
    object S0:
      given upickle.default.Reader[S0] = Pickle.macroR
    case class S1(
      delta: Boolean
    )
    object S1:
      given upickle.default.Reader[S1] = Pickle.macroR

case class LinkedEditingRangeClientCapabilities(
  dynamicRegistration: Boolean
)
object LinkedEditingRangeClientCapabilities:
  given upickle.default.Reader[LinkedEditingRangeClientCapabilities] = Pickle.macroR

case class MonikerClientCapabilities(
  dynamicRegistration: Boolean
)
object MonikerClientCapabilities:
  given upickle.default.Reader[MonikerClientCapabilities] = Pickle.macroR

case class TypeHierarchyClientCapabilities(
  dynamicRegistration: Boolean
)
object TypeHierarchyClientCapabilities:
  given upickle.default.Reader[TypeHierarchyClientCapabilities] = Pickle.macroR

case class InlineValueClientCapabilities(
  dynamicRegistration: Boolean
)
object InlineValueClientCapabilities:
  given upickle.default.Reader[InlineValueClientCapabilities] = Pickle.macroR

case class InlayHintClientCapabilities(
  dynamicRegistration: Boolean,
  resolveSupport: InlayHintClientCapabilities.ResolveSupport
)
object InlayHintClientCapabilities:
  given upickle.default.Reader[InlayHintClientCapabilities] = Pickle.macroR
  case class ResolveSupport(
    properties: Vector[String]
  )
  object ResolveSupport:
    given upickle.default.Reader[ResolveSupport] = Pickle.macroR

case class DiagnosticClientCapabilities(
  dynamicRegistration: Boolean,
  relatedDocumentSupport: Boolean
)
object DiagnosticClientCapabilities:
  given upickle.default.Reader[DiagnosticClientCapabilities] = Pickle.macroR

case class NotebookDocumentSyncClientCapabilities(
  dynamicRegistration: Boolean,
  executionSummarySupport: Boolean
)
object NotebookDocumentSyncClientCapabilities:
  given upickle.default.Reader[NotebookDocumentSyncClientCapabilities] = Pickle.macroR

case class ShowMessageRequestClientCapabilities(
  messageActionItem: ShowMessageRequestClientCapabilities.MessageActionItem
)
object ShowMessageRequestClientCapabilities:
  given upickle.default.Reader[ShowMessageRequestClientCapabilities] = Pickle.macroR
  case class MessageActionItem(
    additionalPropertiesSupport: Boolean
  )
  object MessageActionItem:
    given upickle.default.Reader[MessageActionItem] = Pickle.macroR

case class ShowDocumentClientCapabilities(
  support: Boolean
)
object ShowDocumentClientCapabilities:
  given upickle.default.Reader[ShowDocumentClientCapabilities] = Pickle.macroR

case class RegularExpressionsClientCapabilities(
  engine: String,
  version: String
)
object RegularExpressionsClientCapabilities:
  given upickle.default.Reader[RegularExpressionsClientCapabilities] = Pickle.macroR

case class MarkdownClientCapabilities(
  parser: String,
  version: String,
  allowedTags: Vector[String]
)
object MarkdownClientCapabilities:
  given upickle.default.Reader[MarkdownClientCapabilities] = Pickle.macroR

