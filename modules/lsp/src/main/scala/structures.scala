package langoustine.lsp
package structures

import langoustine.*
import upickle.default.*
import langoustine.lsp.json.{*, given}

case class ImplementationParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object ImplementationParams:
  given codec: Reader[structures.ImplementationParams] = Pickle.macroR

case class Location(
  uri: RuntimeBase.DocumentUri,
  range: structures.Range
)
object Location:
  given codec: Reader[structures.Location] = Pickle.macroR

case class ImplementationRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object ImplementationRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.ImplementationRegistrationOptions] = Pickle.macroR

case class TypeDefinitionParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object TypeDefinitionParams:
  given codec: Reader[structures.TypeDefinitionParams] = Pickle.macroR

case class TypeDefinitionRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object TypeDefinitionRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.TypeDefinitionRegistrationOptions] = Pickle.macroR

case class WorkspaceFolder(
  uri: aliases.URI,
  name: String
)
object WorkspaceFolder:
  given codec: Reader[structures.WorkspaceFolder] = Pickle.macroR

case class DidChangeWorkspaceFoldersParams(
  event: structures.WorkspaceFoldersChangeEvent
)
object DidChangeWorkspaceFoldersParams:
  given codec: Reader[structures.DidChangeWorkspaceFoldersParams] = Pickle.macroR

case class ConfigurationParams(
  items: Vector[structures.ConfigurationItem]
)
object ConfigurationParams:
  given codec: Reader[structures.ConfigurationParams] = Pickle.macroR

case class PartialResultParams(
  partialResultToken: aliases.ProgressToken
)
object PartialResultParams:
  given codec: Reader[structures.PartialResultParams] = Pickle.macroR

case class DocumentColorParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DocumentColorParams:
  given codec: Reader[structures.DocumentColorParams] = Pickle.macroR

case class ColorInformation(
  range: structures.Range,
  color: structures.Color
)
object ColorInformation:
  given codec: Reader[structures.ColorInformation] = Pickle.macroR

case class DocumentColorRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object DocumentColorRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.DocumentColorRegistrationOptions] = Pickle.macroR

case class ColorPresentationParams(
  textDocument: structures.TextDocumentIdentifier,
  color: structures.Color,
  range: structures.Range,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object ColorPresentationParams:
  given codec: Reader[structures.ColorPresentationParams] = Pickle.macroR

case class ColorPresentation(
  label: String,
  textEdit: structures.TextEdit,
  additionalTextEdits: Vector[structures.TextEdit]
)
object ColorPresentation:
  given codec: Reader[structures.ColorPresentation] = Pickle.macroR

case class WorkDoneProgressOptions(
  workDoneProgress: Boolean
)
object WorkDoneProgressOptions:
  given codec: Reader[structures.WorkDoneProgressOptions] = Pickle.macroR

case class TextDocumentRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object TextDocumentRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.TextDocumentRegistrationOptions] = Pickle.macroR

case class FoldingRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object FoldingRangeParams:
  given codec: Reader[structures.FoldingRangeParams] = Pickle.macroR

case class FoldingRange(
  startLine: RuntimeBase.uinteger,
  startCharacter: RuntimeBase.uinteger,
  endLine: RuntimeBase.uinteger,
  endCharacter: RuntimeBase.uinteger,
  kind: enumerations.FoldingRangeKind,
  collapsedText: String
)
object FoldingRange:
  given codec: Reader[structures.FoldingRange] = Pickle.macroR

case class FoldingRangeRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object FoldingRangeRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.FoldingRangeRegistrationOptions] = Pickle.macroR

case class DeclarationParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DeclarationParams:
  given codec: Reader[structures.DeclarationParams] = Pickle.macroR

case class DeclarationRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object DeclarationRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.DeclarationRegistrationOptions] = Pickle.macroR

case class SelectionRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  positions: Vector[structures.Position],
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object SelectionRangeParams:
  given codec: Reader[structures.SelectionRangeParams] = Pickle.macroR

case class SelectionRange(
  range: structures.Range,
  parent: structures.SelectionRange
)
object SelectionRange:
  given codec: Reader[structures.SelectionRange] = Pickle.macroR

case class SelectionRangeRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object SelectionRangeRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.SelectionRangeRegistrationOptions] = Pickle.macroR

case class WorkDoneProgressCreateParams(
  token: aliases.ProgressToken
)
object WorkDoneProgressCreateParams:
  given codec: Reader[structures.WorkDoneProgressCreateParams] = Pickle.macroR

case class WorkDoneProgressCancelParams(
  token: aliases.ProgressToken
)
object WorkDoneProgressCancelParams:
  given codec: Reader[structures.WorkDoneProgressCancelParams] = Pickle.macroR

case class CallHierarchyPrepareParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object CallHierarchyPrepareParams:
  given codec: Reader[structures.CallHierarchyPrepareParams] = Pickle.macroR

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
  given codec: Reader[structures.CallHierarchyItem] = Pickle.macroR

case class CallHierarchyRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object CallHierarchyRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.CallHierarchyRegistrationOptions] = Pickle.macroR

case class CallHierarchyIncomingCallsParams(
  item: structures.CallHierarchyItem,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object CallHierarchyIncomingCallsParams:
  given codec: Reader[structures.CallHierarchyIncomingCallsParams] = Pickle.macroR

case class CallHierarchyIncomingCall(
  from: structures.CallHierarchyItem,
  fromRanges: Vector[structures.Range]
)
object CallHierarchyIncomingCall:
  given codec: Reader[structures.CallHierarchyIncomingCall] = Pickle.macroR

case class CallHierarchyOutgoingCallsParams(
  item: structures.CallHierarchyItem,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object CallHierarchyOutgoingCallsParams:
  given codec: Reader[structures.CallHierarchyOutgoingCallsParams] = Pickle.macroR

case class CallHierarchyOutgoingCall(
  to: structures.CallHierarchyItem,
  fromRanges: Vector[structures.Range]
)
object CallHierarchyOutgoingCall:
  given codec: Reader[structures.CallHierarchyOutgoingCall] = Pickle.macroR

case class SemanticTokensParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object SemanticTokensParams:
  given codec: Reader[structures.SemanticTokensParams] = Pickle.macroR

case class SemanticTokens(
  resultId: String,
  data: Vector[RuntimeBase.uinteger]
)
object SemanticTokens:
  given codec: Reader[structures.SemanticTokens] = Pickle.macroR

case class SemanticTokensPartialResult(
  data: Vector[RuntimeBase.uinteger]
)
object SemanticTokensPartialResult:
  given codec: Reader[structures.SemanticTokensPartialResult] = Pickle.macroR

case class SemanticTokensRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  legend: structures.SemanticTokensLegend,
  range: (Boolean | SemanticTokensRegistrationOptions.S0),
  full: (Boolean | SemanticTokensRegistrationOptions.S1),
  id: String
)
object SemanticTokensRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val rd1 = badMerge(reader[Boolean].widen[(Boolean | SemanticTokensRegistrationOptions.S0)], SemanticTokensRegistrationOptions.S0.codec.widen[(Boolean | SemanticTokensRegistrationOptions.S0)])
  given reader_rd1: Reader[(Boolean | SemanticTokensRegistrationOptions.S0)] = rd1
  private val rd2 = badMerge(reader[Boolean].widen[(Boolean | SemanticTokensRegistrationOptions.S1)], SemanticTokensRegistrationOptions.S1.codec.widen[(Boolean | SemanticTokensRegistrationOptions.S1)])
  given reader_rd2: Reader[(Boolean | SemanticTokensRegistrationOptions.S1)] = rd2
  given codec: Reader[structures.SemanticTokensRegistrationOptions] = Pickle.macroR
  case class S0(
  )
  object S0:
    given codec: Reader[structures.SemanticTokensRegistrationOptions.S0] = Pickle.macroR
  case class S1(
    delta: Boolean
  )
  object S1:
    given codec: Reader[structures.SemanticTokensRegistrationOptions.S1] = Pickle.macroR

case class SemanticTokensDeltaParams(
  textDocument: structures.TextDocumentIdentifier,
  previousResultId: String,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object SemanticTokensDeltaParams:
  given codec: Reader[structures.SemanticTokensDeltaParams] = Pickle.macroR

case class SemanticTokensDelta(
  resultId: String,
  edits: Vector[structures.SemanticTokensEdit]
)
object SemanticTokensDelta:
  given codec: Reader[structures.SemanticTokensDelta] = Pickle.macroR

case class SemanticTokensDeltaPartialResult(
  edits: Vector[structures.SemanticTokensEdit]
)
object SemanticTokensDeltaPartialResult:
  given codec: Reader[structures.SemanticTokensDeltaPartialResult] = Pickle.macroR

case class SemanticTokensRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object SemanticTokensRangeParams:
  given codec: Reader[structures.SemanticTokensRangeParams] = Pickle.macroR

case class ShowDocumentParams(
  uri: aliases.URI,
  external: Boolean,
  takeFocus: Boolean,
  selection: structures.Range
)
object ShowDocumentParams:
  given codec: Reader[structures.ShowDocumentParams] = Pickle.macroR

case class ShowDocumentResult(
  success: Boolean
)
object ShowDocumentResult:
  given codec: Reader[structures.ShowDocumentResult] = Pickle.macroR

case class LinkedEditingRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object LinkedEditingRangeParams:
  given codec: Reader[structures.LinkedEditingRangeParams] = Pickle.macroR

case class LinkedEditingRanges(
  ranges: Vector[structures.Range],
  wordPattern: String
)
object LinkedEditingRanges:
  given codec: Reader[structures.LinkedEditingRanges] = Pickle.macroR

case class LinkedEditingRangeRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object LinkedEditingRangeRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.LinkedEditingRangeRegistrationOptions] = Pickle.macroR

case class CreateFilesParams(
  files: Vector[structures.FileCreate]
)
object CreateFilesParams:
  given codec: Reader[structures.CreateFilesParams] = Pickle.macroR

case class WorkspaceEdit(
  changes: Map[RuntimeBase.DocumentUri, Vector[structures.TextEdit]],
  documentChanges: Vector[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)],
  changeAnnotations: Map[aliases.ChangeAnnotationIdentifier, structures.ChangeAnnotation]
)
object WorkspaceEdit:
  private val rd0 = badMerge(structures.TextDocumentEdit.codec.widen[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)], structures.CreateFile.codec.widen[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)], structures.RenameFile.codec.widen[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)], structures.DeleteFile.codec.widen[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)])
  given reader_rd0: Reader[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)] = rd0
  given codec: Reader[structures.WorkspaceEdit] = Pickle.macroR

case class FileOperationRegistrationOptions(
  filters: Vector[structures.FileOperationFilter]
)
object FileOperationRegistrationOptions:
  given codec: Reader[structures.FileOperationRegistrationOptions] = Pickle.macroR

case class RenameFilesParams(
  files: Vector[structures.FileRename]
)
object RenameFilesParams:
  given codec: Reader[structures.RenameFilesParams] = Pickle.macroR

case class DeleteFilesParams(
  files: Vector[structures.FileDelete]
)
object DeleteFilesParams:
  given codec: Reader[structures.DeleteFilesParams] = Pickle.macroR

case class MonikerParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object MonikerParams:
  given codec: Reader[structures.MonikerParams] = Pickle.macroR

case class Moniker(
  scheme: String,
  identifier: String,
  unique: enumerations.UniquenessLevel,
  kind: enumerations.MonikerKind
)
object Moniker:
  given codec: Reader[structures.Moniker] = Pickle.macroR

case class MonikerRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object MonikerRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.MonikerRegistrationOptions] = Pickle.macroR

case class TypeHierarchyPrepareParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object TypeHierarchyPrepareParams:
  given codec: Reader[structures.TypeHierarchyPrepareParams] = Pickle.macroR

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
  given codec: Reader[structures.TypeHierarchyItem] = Pickle.macroR

case class TypeHierarchyRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object TypeHierarchyRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.TypeHierarchyRegistrationOptions] = Pickle.macroR

case class TypeHierarchySupertypesParams(
  item: structures.TypeHierarchyItem,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object TypeHierarchySupertypesParams:
  given codec: Reader[structures.TypeHierarchySupertypesParams] = Pickle.macroR

case class TypeHierarchySubtypesParams(
  item: structures.TypeHierarchyItem,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object TypeHierarchySubtypesParams:
  given codec: Reader[structures.TypeHierarchySubtypesParams] = Pickle.macroR

case class InlineValueParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  context: structures.InlineValueContext,
  workDoneToken: aliases.ProgressToken
)
object InlineValueParams:
  given codec: Reader[structures.InlineValueParams] = Pickle.macroR

case class InlineValueRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object InlineValueRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.InlineValueRegistrationOptions] = Pickle.macroR

case class InlayHintParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  workDoneToken: aliases.ProgressToken
)
object InlayHintParams:
  given codec: Reader[structures.InlayHintParams] = Pickle.macroR

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
  private val rd0 = badMerge(stringCodec.widen[(String | Vector[structures.InlayHintLabelPart])], reader[Vector[structures.InlayHintLabelPart]].widen[(String | Vector[structures.InlayHintLabelPart])])
  given reader_rd0: Reader[(String | Vector[structures.InlayHintLabelPart])] = rd0
  private val rd1 = badMerge(stringCodec.widen[(String | structures.MarkupContent)], structures.MarkupContent.codec.widen[(String | structures.MarkupContent)])
  given reader_rd1: Reader[(String | structures.MarkupContent)] = rd1
  given codec: Reader[structures.InlayHint] = Pickle.macroR

case class InlayHintRegistrationOptions(
  resolveProvider: Boolean,
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object InlayHintRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.InlayHintRegistrationOptions] = Pickle.macroR

case class DocumentDiagnosticParams(
  textDocument: structures.TextDocumentIdentifier,
  identifier: String,
  previousResultId: String,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DocumentDiagnosticParams:
  given codec: Reader[structures.DocumentDiagnosticParams] = Pickle.macroR

case class DocumentDiagnosticReportPartialResult(
  relatedDocuments: Map[RuntimeBase.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)]
)
object DocumentDiagnosticReportPartialResult:
  private val rd0 = badMerge(structures.FullDocumentDiagnosticReport.codec.widen[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)], structures.UnchangedDocumentDiagnosticReport.codec.widen[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)])
  given reader_rd0: Reader[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = rd0
  given codec: Reader[structures.DocumentDiagnosticReportPartialResult] = Pickle.macroR

case class DiagnosticServerCancellationData(
  retriggerRequest: Boolean
)
object DiagnosticServerCancellationData:
  given codec: Reader[structures.DiagnosticServerCancellationData] = Pickle.macroR

case class DiagnosticRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  identifier: String,
  interFileDependencies: Boolean,
  workspaceDiagnostics: Boolean,
  id: String
)
object DiagnosticRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.DiagnosticRegistrationOptions] = Pickle.macroR

case class WorkspaceDiagnosticParams(
  identifier: String,
  previousResultIds: Vector[structures.PreviousResultId],
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object WorkspaceDiagnosticParams:
  given codec: Reader[structures.WorkspaceDiagnosticParams] = Pickle.macroR

case class WorkspaceDiagnosticReport(
  items: Vector[aliases.WorkspaceDocumentDiagnosticReport]
)
object WorkspaceDiagnosticReport:
  given codec: Reader[structures.WorkspaceDiagnosticReport] = Pickle.macroR

case class WorkspaceDiagnosticReportPartialResult(
  items: Vector[aliases.WorkspaceDocumentDiagnosticReport]
)
object WorkspaceDiagnosticReportPartialResult:
  given codec: Reader[structures.WorkspaceDiagnosticReportPartialResult] = Pickle.macroR

case class DidOpenNotebookDocumentParams(
  notebookDocument: structures.NotebookDocument,
  cellTextDocuments: Vector[structures.TextDocumentItem]
)
object DidOpenNotebookDocumentParams:
  given codec: Reader[structures.DidOpenNotebookDocumentParams] = Pickle.macroR

case class DidChangeNotebookDocumentParams(
  notebookDocument: structures.VersionedNotebookDocumentIdentifier,
  change: structures.NotebookDocumentChangeEvent
)
object DidChangeNotebookDocumentParams:
  given codec: Reader[structures.DidChangeNotebookDocumentParams] = Pickle.macroR

case class DidSaveNotebookDocumentParams(
  notebookDocument: structures.NotebookDocumentIdentifier
)
object DidSaveNotebookDocumentParams:
  given codec: Reader[structures.DidSaveNotebookDocumentParams] = Pickle.macroR

case class DidCloseNotebookDocumentParams(
  notebookDocument: structures.NotebookDocumentIdentifier,
  cellTextDocuments: Vector[structures.TextDocumentIdentifier]
)
object DidCloseNotebookDocumentParams:
  given codec: Reader[structures.DidCloseNotebookDocumentParams] = Pickle.macroR

case class RegistrationParams(
  registrations: Vector[structures.Registration]
)
object RegistrationParams:
  given codec: Reader[structures.RegistrationParams] = Pickle.macroR

case class UnregistrationParams(
  unregisterations: Vector[structures.Unregistration]
)
object UnregistrationParams:
  given codec: Reader[structures.UnregistrationParams] = Pickle.macroR

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
  private val rd0 = badMerge(intCodec.widen[(Int | Null)], nullReadWriter.widen[(Int | Null)])
  given reader_rd0: Reader[(Int | Null)] = rd0
  private val rd1 = badMerge(stringCodec.widen[(String | Null)], nullReadWriter.widen[(String | Null)])
  given reader_rd1: Reader[(String | Null)] = rd1
  private val rd2 = badMerge(reader[RuntimeBase.DocumentUri].widen[(RuntimeBase.DocumentUri | Null)], nullReadWriter.widen[(RuntimeBase.DocumentUri | Null)])
  given reader_rd2: Reader[(RuntimeBase.DocumentUri | Null)] = rd2
  private val rd3 = badMerge(reader["off"].widen[("off" | "messages" | "compact" | "verbose")], reader["messages"].widen[("off" | "messages" | "compact" | "verbose")], reader["compact"].widen[("off" | "messages" | "compact" | "verbose")], reader["verbose"].widen[("off" | "messages" | "compact" | "verbose")])
  given reader_rd3: Reader[("off" | "messages" | "compact" | "verbose")] = rd3
  private val rd4 = badMerge(reader[Vector[structures.WorkspaceFolder]].widen[(Vector[structures.WorkspaceFolder] | Null)], nullReadWriter.widen[(Vector[structures.WorkspaceFolder] | Null)])
  given reader_rd4: Reader[(Vector[structures.WorkspaceFolder] | Null)] = rd4
  given codec: Reader[structures.InitializeParams] = Pickle.macroR
  case class ClientInfo(
    name: String,
    version: String
  )
  object ClientInfo:
    given codec: Reader[structures.InitializeParams.ClientInfo] = Pickle.macroR

case class InitializeResult(
  capabilities: structures.ServerCapabilities,
  serverInfo: InitializeResult.ServerInfo
)
object InitializeResult:
  given codec: Reader[structures.InitializeResult] = Pickle.macroR
  case class ServerInfo(
    name: String,
    version: String
  )
  object ServerInfo:
    given codec: Reader[structures.InitializeResult.ServerInfo] = Pickle.macroR

case class InitializeError(
  retry: Boolean
)
object InitializeError:
  given codec: Reader[structures.InitializeError] = Pickle.macroR

case class InitializedParams(
)
object InitializedParams:
  given codec: Reader[structures.InitializedParams] = Pickle.macroR

case class DidChangeConfigurationParams(
  settings: aliases.LSPAny
)
object DidChangeConfigurationParams:
  given codec: Reader[structures.DidChangeConfigurationParams] = Pickle.macroR

case class DidChangeConfigurationRegistrationOptions(
  section: (String | Vector[String])
)
object DidChangeConfigurationRegistrationOptions:
  private val rd0 = badMerge(stringCodec.widen[(String | Vector[String])], reader[Vector[String]].widen[(String | Vector[String])])
  given reader_rd0: Reader[(String | Vector[String])] = rd0
  given codec: Reader[structures.DidChangeConfigurationRegistrationOptions] = Pickle.macroR

case class ShowMessageParams(
  `type`: enumerations.MessageType,
  message: String
)
object ShowMessageParams:
  given codec: Reader[structures.ShowMessageParams] = Pickle.macroR

case class ShowMessageRequestParams(
  `type`: enumerations.MessageType,
  message: String,
  actions: Vector[structures.MessageActionItem]
)
object ShowMessageRequestParams:
  given codec: Reader[structures.ShowMessageRequestParams] = Pickle.macroR

case class MessageActionItem(
  title: String
)
object MessageActionItem:
  given codec: Reader[structures.MessageActionItem] = Pickle.macroR

case class LogMessageParams(
  `type`: enumerations.MessageType,
  message: String
)
object LogMessageParams:
  given codec: Reader[structures.LogMessageParams] = Pickle.macroR

case class DidOpenTextDocumentParams(
  textDocument: structures.TextDocumentItem
)
object DidOpenTextDocumentParams:
  given codec: Reader[structures.DidOpenTextDocumentParams] = Pickle.macroR

case class DidChangeTextDocumentParams(
  textDocument: structures.VersionedTextDocumentIdentifier,
  contentChanges: Vector[aliases.TextDocumentContentChangeEvent]
)
object DidChangeTextDocumentParams:
  given codec: Reader[structures.DidChangeTextDocumentParams] = Pickle.macroR

case class TextDocumentChangeRegistrationOptions(
  syncKind: enumerations.TextDocumentSyncKind,
  documentSelector: (aliases.DocumentSelector | Null)
)
object TextDocumentChangeRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.TextDocumentChangeRegistrationOptions] = Pickle.macroR

case class DidCloseTextDocumentParams(
  textDocument: structures.TextDocumentIdentifier
)
object DidCloseTextDocumentParams:
  given codec: Reader[structures.DidCloseTextDocumentParams] = Pickle.macroR

case class DidSaveTextDocumentParams(
  textDocument: structures.TextDocumentIdentifier,
  text: String
)
object DidSaveTextDocumentParams:
  given codec: Reader[structures.DidSaveTextDocumentParams] = Pickle.macroR

case class TextDocumentSaveRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  includeText: Boolean
)
object TextDocumentSaveRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.TextDocumentSaveRegistrationOptions] = Pickle.macroR

case class WillSaveTextDocumentParams(
  textDocument: structures.TextDocumentIdentifier,
  reason: enumerations.TextDocumentSaveReason
)
object WillSaveTextDocumentParams:
  given codec: Reader[structures.WillSaveTextDocumentParams] = Pickle.macroR

case class TextEdit(
  range: structures.Range,
  newText: String
)
object TextEdit:
  given codec: Reader[structures.TextEdit] = Pickle.macroR

case class DidChangeWatchedFilesParams(
  changes: Vector[structures.FileEvent]
)
object DidChangeWatchedFilesParams:
  given codec: Reader[structures.DidChangeWatchedFilesParams] = Pickle.macroR

case class DidChangeWatchedFilesRegistrationOptions(
  watchers: Vector[structures.FileSystemWatcher]
)
object DidChangeWatchedFilesRegistrationOptions:
  given codec: Reader[structures.DidChangeWatchedFilesRegistrationOptions] = Pickle.macroR

case class PublishDiagnosticsParams(
  uri: RuntimeBase.DocumentUri,
  version: Int,
  diagnostics: Vector[structures.Diagnostic]
)
object PublishDiagnosticsParams:
  given codec: Reader[structures.PublishDiagnosticsParams] = Pickle.macroR

case class CompletionParams(
  context: structures.CompletionContext,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object CompletionParams:
  given codec: Reader[structures.CompletionParams] = Pickle.macroR

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
  private val rd0 = badMerge(stringCodec.widen[(String | structures.MarkupContent)], structures.MarkupContent.codec.widen[(String | structures.MarkupContent)])
  given reader_rd0: Reader[(String | structures.MarkupContent)] = rd0
  private val rd1 = badMerge(structures.TextEdit.codec.widen[(structures.TextEdit | structures.InsertReplaceEdit)], structures.InsertReplaceEdit.codec.widen[(structures.TextEdit | structures.InsertReplaceEdit)])
  given reader_rd1: Reader[(structures.TextEdit | structures.InsertReplaceEdit)] = rd1
  given codec: Reader[structures.CompletionItem] = Pickle.macroR

case class CompletionList(
  isIncomplete: Boolean,
  itemDefaults: CompletionList.ItemDefaults,
  items: Vector[structures.CompletionItem]
)
object CompletionList:
  given codec: Reader[structures.CompletionList] = Pickle.macroR
  case class ItemDefaults(
    commitCharacters: Vector[String],
    editRange: (structures.Range | ItemDefaults.S0),
    insertTextFormat: enumerations.InsertTextFormat,
    insertTextMode: enumerations.InsertTextMode,
    data: aliases.LSPAny
  )
  object ItemDefaults:
    private val rd0 = badMerge(structures.Range.codec.widen[(structures.Range | ItemDefaults.S0)], ItemDefaults.S0.codec.widen[(structures.Range | ItemDefaults.S0)])
    given reader_rd0: Reader[(structures.Range | ItemDefaults.S0)] = rd0
    given codec: Reader[structures.CompletionList.ItemDefaults] = Pickle.macroR
    case class S0(
      insert: structures.Range,
      replace: structures.Range
    )
    object S0:
      given codec: Reader[structures.CompletionList.ItemDefaults.S0] = Pickle.macroR

case class CompletionRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  triggerCharacters: Vector[String],
  allCommitCharacters: Vector[String],
  resolveProvider: Boolean,
  completionItem: CompletionRegistrationOptions.CompletionItem
)
object CompletionRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.CompletionRegistrationOptions] = Pickle.macroR
  case class CompletionItem(
    labelDetailsSupport: Boolean
  )
  object CompletionItem:
    given codec: Reader[structures.CompletionRegistrationOptions.CompletionItem] = Pickle.macroR

case class HoverParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object HoverParams:
  given codec: Reader[structures.HoverParams] = Pickle.macroR

case class Hover(
  contents: (structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString]),
  range: structures.Range
)
object Hover:
  private val rd0 = badMerge(structures.MarkupContent.codec.widen[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])], aliases.MarkedString.codec.widen[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])], reader[Vector[aliases.MarkedString]].widen[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])])
  given reader_rd0: Reader[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])] = rd0
  given codec: Reader[structures.Hover] = Pickle.macroR

case class HoverRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object HoverRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.HoverRegistrationOptions] = Pickle.macroR

case class SignatureHelpParams(
  context: structures.SignatureHelpContext,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object SignatureHelpParams:
  given codec: Reader[structures.SignatureHelpParams] = Pickle.macroR

case class SignatureHelp(
  signatures: Vector[structures.SignatureInformation],
  activeSignature: RuntimeBase.uinteger,
  activeParameter: RuntimeBase.uinteger
)
object SignatureHelp:
  given codec: Reader[structures.SignatureHelp] = Pickle.macroR

case class SignatureHelpRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  triggerCharacters: Vector[String],
  retriggerCharacters: Vector[String]
)
object SignatureHelpRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.SignatureHelpRegistrationOptions] = Pickle.macroR

case class DefinitionParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DefinitionParams:
  given codec: Reader[structures.DefinitionParams] = Pickle.macroR

case class DefinitionRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object DefinitionRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.DefinitionRegistrationOptions] = Pickle.macroR

case class ReferenceParams(
  context: structures.ReferenceContext,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object ReferenceParams:
  given codec: Reader[structures.ReferenceParams] = Pickle.macroR

case class ReferenceRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object ReferenceRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.ReferenceRegistrationOptions] = Pickle.macroR

case class DocumentHighlightParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DocumentHighlightParams:
  given codec: Reader[structures.DocumentHighlightParams] = Pickle.macroR

case class DocumentHighlight(
  range: structures.Range,
  kind: enumerations.DocumentHighlightKind
)
object DocumentHighlight:
  given codec: Reader[structures.DocumentHighlight] = Pickle.macroR

case class DocumentHighlightRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object DocumentHighlightRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.DocumentHighlightRegistrationOptions] = Pickle.macroR

case class DocumentSymbolParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DocumentSymbolParams:
  given codec: Reader[structures.DocumentSymbolParams] = Pickle.macroR

case class SymbolInformation(
  deprecated: Boolean,
  location: structures.Location,
  name: String,
  kind: enumerations.SymbolKind,
  tags: Vector[enumerations.SymbolTag],
  containerName: String
)
object SymbolInformation:
  given codec: Reader[structures.SymbolInformation] = Pickle.macroR

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
  given codec: Reader[structures.DocumentSymbol] = Pickle.macroR

case class DocumentSymbolRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  label: String
)
object DocumentSymbolRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.DocumentSymbolRegistrationOptions] = Pickle.macroR

case class CodeActionParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  context: structures.CodeActionContext,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object CodeActionParams:
  given codec: Reader[structures.CodeActionParams] = Pickle.macroR

case class Command(
  title: String,
  command: String,
  arguments: Vector[aliases.LSPAny]
)
object Command:
  given codec: Reader[structures.Command] = Pickle.macroR

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
  given codec: Reader[structures.CodeAction] = Pickle.macroR
  case class Disabled(
    reason: String
  )
  object Disabled:
    given codec: Reader[structures.CodeAction.Disabled] = Pickle.macroR

case class CodeActionRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  codeActionKinds: Vector[enumerations.CodeActionKind],
  resolveProvider: Boolean
)
object CodeActionRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.CodeActionRegistrationOptions] = Pickle.macroR

case class WorkspaceSymbolParams(
  query: String,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object WorkspaceSymbolParams:
  given codec: Reader[structures.WorkspaceSymbolParams] = Pickle.macroR

case class WorkspaceSymbol(
  location: (structures.Location | WorkspaceSymbol.S0),
  data: aliases.LSPAny,
  name: String,
  kind: enumerations.SymbolKind,
  tags: Vector[enumerations.SymbolTag],
  containerName: String
)
object WorkspaceSymbol:
  private val rd0 = badMerge(structures.Location.codec.widen[(structures.Location | WorkspaceSymbol.S0)], WorkspaceSymbol.S0.codec.widen[(structures.Location | WorkspaceSymbol.S0)])
  given reader_rd0: Reader[(structures.Location | WorkspaceSymbol.S0)] = rd0
  given codec: Reader[structures.WorkspaceSymbol] = Pickle.macroR
  case class S0(
    uri: RuntimeBase.DocumentUri
  )
  object S0:
    given codec: Reader[structures.WorkspaceSymbol.S0] = Pickle.macroR

case class WorkspaceSymbolRegistrationOptions(
  resolveProvider: Boolean
)
object WorkspaceSymbolRegistrationOptions:
  given codec: Reader[structures.WorkspaceSymbolRegistrationOptions] = Pickle.macroR

case class CodeLensParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object CodeLensParams:
  given codec: Reader[structures.CodeLensParams] = Pickle.macroR

case class CodeLens(
  range: structures.Range,
  command: structures.Command,
  data: aliases.LSPAny
)
object CodeLens:
  given codec: Reader[structures.CodeLens] = Pickle.macroR

case class CodeLensRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  resolveProvider: Boolean
)
object CodeLensRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.CodeLensRegistrationOptions] = Pickle.macroR

case class DocumentLinkParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DocumentLinkParams:
  given codec: Reader[structures.DocumentLinkParams] = Pickle.macroR

case class DocumentLink(
  range: structures.Range,
  target: String,
  tooltip: String,
  data: aliases.LSPAny
)
object DocumentLink:
  given codec: Reader[structures.DocumentLink] = Pickle.macroR

case class DocumentLinkRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  resolveProvider: Boolean
)
object DocumentLinkRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.DocumentLinkRegistrationOptions] = Pickle.macroR

case class DocumentFormattingParams(
  textDocument: structures.TextDocumentIdentifier,
  options: structures.FormattingOptions,
  workDoneToken: aliases.ProgressToken
)
object DocumentFormattingParams:
  given codec: Reader[structures.DocumentFormattingParams] = Pickle.macroR

case class DocumentFormattingRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object DocumentFormattingRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.DocumentFormattingRegistrationOptions] = Pickle.macroR

case class DocumentRangeFormattingParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  options: structures.FormattingOptions,
  workDoneToken: aliases.ProgressToken
)
object DocumentRangeFormattingParams:
  given codec: Reader[structures.DocumentRangeFormattingParams] = Pickle.macroR

case class DocumentRangeFormattingRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object DocumentRangeFormattingRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.DocumentRangeFormattingRegistrationOptions] = Pickle.macroR

case class DocumentOnTypeFormattingParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  ch: String,
  options: structures.FormattingOptions
)
object DocumentOnTypeFormattingParams:
  given codec: Reader[structures.DocumentOnTypeFormattingParams] = Pickle.macroR

case class DocumentOnTypeFormattingRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  firstTriggerCharacter: String,
  moreTriggerCharacter: Vector[String]
)
object DocumentOnTypeFormattingRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.DocumentOnTypeFormattingRegistrationOptions] = Pickle.macroR

case class RenameParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  newName: String,
  workDoneToken: aliases.ProgressToken
)
object RenameParams:
  given codec: Reader[structures.RenameParams] = Pickle.macroR

case class RenameRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  prepareProvider: Boolean
)
object RenameRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.codec.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  given codec: Reader[structures.RenameRegistrationOptions] = Pickle.macroR

case class PrepareRenameParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object PrepareRenameParams:
  given codec: Reader[structures.PrepareRenameParams] = Pickle.macroR

case class ExecuteCommandParams(
  command: String,
  arguments: Vector[aliases.LSPAny],
  workDoneToken: aliases.ProgressToken
)
object ExecuteCommandParams:
  given codec: Reader[structures.ExecuteCommandParams] = Pickle.macroR

case class ExecuteCommandRegistrationOptions(
  commands: Vector[String]
)
object ExecuteCommandRegistrationOptions:
  given codec: Reader[structures.ExecuteCommandRegistrationOptions] = Pickle.macroR

case class ApplyWorkspaceEditParams(
  label: String,
  edit: structures.WorkspaceEdit
)
object ApplyWorkspaceEditParams:
  given codec: Reader[structures.ApplyWorkspaceEditParams] = Pickle.macroR

case class ApplyWorkspaceEditResult(
  applied: Boolean,
  failureReason: String,
  failedChange: RuntimeBase.uinteger
)
object ApplyWorkspaceEditResult:
  given codec: Reader[structures.ApplyWorkspaceEditResult] = Pickle.macroR

case class WorkDoneProgressBegin(
  kind: "begin",
  title: String,
  cancellable: Boolean,
  message: String,
  percentage: RuntimeBase.uinteger
)
object WorkDoneProgressBegin:
  given codec: Reader[structures.WorkDoneProgressBegin] = Pickle.macroR

case class WorkDoneProgressReport(
  kind: "report",
  cancellable: Boolean,
  message: String,
  percentage: RuntimeBase.uinteger
)
object WorkDoneProgressReport:
  given codec: Reader[structures.WorkDoneProgressReport] = Pickle.macroR

case class WorkDoneProgressEnd(
  kind: "end",
  message: String
)
object WorkDoneProgressEnd:
  given codec: Reader[structures.WorkDoneProgressEnd] = Pickle.macroR

case class SetTraceParams(
  value: enumerations.TraceValues
)
object SetTraceParams:
  given codec: Reader[structures.SetTraceParams] = Pickle.macroR

case class LogTraceParams(
  message: String,
  verbose: String
)
object LogTraceParams:
  given codec: Reader[structures.LogTraceParams] = Pickle.macroR

case class CancelParams(
  id: (Int | String)
)
object CancelParams:
  private val rd0 = badMerge(intCodec.widen[(Int | String)], stringCodec.widen[(Int | String)])
  given reader_rd0: Reader[(Int | String)] = rd0
  given codec: Reader[structures.CancelParams] = Pickle.macroR

case class ProgressParams(
  token: aliases.ProgressToken,
  value: aliases.LSPAny
)
object ProgressParams:
  given codec: Reader[structures.ProgressParams] = Pickle.macroR

case class TextDocumentPositionParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position
)
object TextDocumentPositionParams:
  given codec: Reader[structures.TextDocumentPositionParams] = Pickle.macroR

case class WorkDoneProgressParams(
  workDoneToken: aliases.ProgressToken
)
object WorkDoneProgressParams:
  given codec: Reader[structures.WorkDoneProgressParams] = Pickle.macroR

case class LocationLink(
  originSelectionRange: structures.Range,
  targetUri: RuntimeBase.DocumentUri,
  targetRange: structures.Range,
  targetSelectionRange: structures.Range
)
object LocationLink:
  given codec: Reader[structures.LocationLink] = Pickle.macroR

case class Range(
  start: structures.Position,
  end: structures.Position
)
object Range:
  given codec: Reader[structures.Range] = Pickle.macroR

case class ImplementationOptions(
  workDoneProgress: Boolean
)
object ImplementationOptions:
  given codec: Reader[structures.ImplementationOptions] = Pickle.macroR

case class StaticRegistrationOptions(
  id: String
)
object StaticRegistrationOptions:
  given codec: Reader[structures.StaticRegistrationOptions] = Pickle.macroR

case class TypeDefinitionOptions(
  workDoneProgress: Boolean
)
object TypeDefinitionOptions:
  given codec: Reader[structures.TypeDefinitionOptions] = Pickle.macroR

case class WorkspaceFoldersChangeEvent(
  added: Vector[structures.WorkspaceFolder],
  removed: Vector[structures.WorkspaceFolder]
)
object WorkspaceFoldersChangeEvent:
  given codec: Reader[structures.WorkspaceFoldersChangeEvent] = Pickle.macroR

case class ConfigurationItem(
  scopeUri: String,
  section: String
)
object ConfigurationItem:
  given codec: Reader[structures.ConfigurationItem] = Pickle.macroR

case class TextDocumentIdentifier(
  uri: RuntimeBase.DocumentUri
)
object TextDocumentIdentifier:
  given codec: Reader[structures.TextDocumentIdentifier] = Pickle.macroR

case class Color(
  red: Float,
  green: Float,
  blue: Float,
  alpha: Float
)
object Color:
  given codec: Reader[structures.Color] = Pickle.macroR

case class DocumentColorOptions(
  workDoneProgress: Boolean
)
object DocumentColorOptions:
  given codec: Reader[structures.DocumentColorOptions] = Pickle.macroR

case class FoldingRangeOptions(
  workDoneProgress: Boolean
)
object FoldingRangeOptions:
  given codec: Reader[structures.FoldingRangeOptions] = Pickle.macroR

case class DeclarationOptions(
  workDoneProgress: Boolean
)
object DeclarationOptions:
  given codec: Reader[structures.DeclarationOptions] = Pickle.macroR

case class Position(
  line: RuntimeBase.uinteger,
  character: RuntimeBase.uinteger
)
object Position:
  given codec: Reader[structures.Position] = Pickle.macroR

case class SelectionRangeOptions(
  workDoneProgress: Boolean
)
object SelectionRangeOptions:
  given codec: Reader[structures.SelectionRangeOptions] = Pickle.macroR

case class CallHierarchyOptions(
  workDoneProgress: Boolean
)
object CallHierarchyOptions:
  given codec: Reader[structures.CallHierarchyOptions] = Pickle.macroR

case class SemanticTokensOptions(
  legend: structures.SemanticTokensLegend,
  range: (Boolean | SemanticTokensOptions.S0),
  full: (Boolean | SemanticTokensOptions.S1),
  workDoneProgress: Boolean
)
object SemanticTokensOptions:
  private val rd0 = badMerge(reader[Boolean].widen[(Boolean | SemanticTokensOptions.S0)], SemanticTokensOptions.S0.codec.widen[(Boolean | SemanticTokensOptions.S0)])
  given reader_rd0: Reader[(Boolean | SemanticTokensOptions.S0)] = rd0
  private val rd1 = badMerge(reader[Boolean].widen[(Boolean | SemanticTokensOptions.S1)], SemanticTokensOptions.S1.codec.widen[(Boolean | SemanticTokensOptions.S1)])
  given reader_rd1: Reader[(Boolean | SemanticTokensOptions.S1)] = rd1
  given codec: Reader[structures.SemanticTokensOptions] = Pickle.macroR
  case class S0(
  )
  object S0:
    given codec: Reader[structures.SemanticTokensOptions.S0] = Pickle.macroR
  case class S1(
    delta: Boolean
  )
  object S1:
    given codec: Reader[structures.SemanticTokensOptions.S1] = Pickle.macroR

case class SemanticTokensEdit(
  start: RuntimeBase.uinteger,
  deleteCount: RuntimeBase.uinteger,
  data: Vector[RuntimeBase.uinteger]
)
object SemanticTokensEdit:
  given codec: Reader[structures.SemanticTokensEdit] = Pickle.macroR

case class LinkedEditingRangeOptions(
  workDoneProgress: Boolean
)
object LinkedEditingRangeOptions:
  given codec: Reader[structures.LinkedEditingRangeOptions] = Pickle.macroR

case class FileCreate(
  uri: String
)
object FileCreate:
  given codec: Reader[structures.FileCreate] = Pickle.macroR

case class TextDocumentEdit(
  textDocument: structures.OptionalVersionedTextDocumentIdentifier,
  edits: Vector[(structures.TextEdit | structures.AnnotatedTextEdit)]
)
object TextDocumentEdit:
  private val rd0 = badMerge(structures.TextEdit.codec.widen[(structures.TextEdit | structures.AnnotatedTextEdit)], structures.AnnotatedTextEdit.codec.widen[(structures.TextEdit | structures.AnnotatedTextEdit)])
  given reader_rd0: Reader[(structures.TextEdit | structures.AnnotatedTextEdit)] = rd0
  given codec: Reader[structures.TextDocumentEdit] = Pickle.macroR

case class CreateFile(
  kind: "create",
  uri: RuntimeBase.DocumentUri,
  options: structures.CreateFileOptions,
  annotationId: aliases.ChangeAnnotationIdentifier
)
object CreateFile:
  given codec: Reader[structures.CreateFile] = Pickle.macroR

case class RenameFile(
  kind: "rename",
  oldUri: RuntimeBase.DocumentUri,
  newUri: RuntimeBase.DocumentUri,
  options: structures.RenameFileOptions,
  annotationId: aliases.ChangeAnnotationIdentifier
)
object RenameFile:
  given codec: Reader[structures.RenameFile] = Pickle.macroR

case class DeleteFile(
  kind: "delete",
  uri: RuntimeBase.DocumentUri,
  options: structures.DeleteFileOptions,
  annotationId: aliases.ChangeAnnotationIdentifier
)
object DeleteFile:
  given codec: Reader[structures.DeleteFile] = Pickle.macroR

case class ChangeAnnotation(
  label: String,
  needsConfirmation: Boolean,
  description: String
)
object ChangeAnnotation:
  given codec: Reader[structures.ChangeAnnotation] = Pickle.macroR

case class FileOperationFilter(
  scheme: String,
  pattern: structures.FileOperationPattern
)
object FileOperationFilter:
  given codec: Reader[structures.FileOperationFilter] = Pickle.macroR

case class FileRename(
  oldUri: String,
  newUri: String
)
object FileRename:
  given codec: Reader[structures.FileRename] = Pickle.macroR

case class FileDelete(
  uri: String
)
object FileDelete:
  given codec: Reader[structures.FileDelete] = Pickle.macroR

case class MonikerOptions(
  workDoneProgress: Boolean
)
object MonikerOptions:
  given codec: Reader[structures.MonikerOptions] = Pickle.macroR

case class TypeHierarchyOptions(
  workDoneProgress: Boolean
)
object TypeHierarchyOptions:
  given codec: Reader[structures.TypeHierarchyOptions] = Pickle.macroR

case class InlineValueContext(
  frameId: Int,
  stoppedLocation: structures.Range
)
object InlineValueContext:
  given codec: Reader[structures.InlineValueContext] = Pickle.macroR

case class InlineValueText(
  range: structures.Range,
  text: String
)
object InlineValueText:
  given codec: Reader[structures.InlineValueText] = Pickle.macroR

case class InlineValueVariableLookup(
  range: structures.Range,
  variableName: String,
  caseSensitiveLookup: Boolean
)
object InlineValueVariableLookup:
  given codec: Reader[structures.InlineValueVariableLookup] = Pickle.macroR

case class InlineValueEvaluatableExpression(
  range: structures.Range,
  expression: String
)
object InlineValueEvaluatableExpression:
  given codec: Reader[structures.InlineValueEvaluatableExpression] = Pickle.macroR

case class InlineValueOptions(
  workDoneProgress: Boolean
)
object InlineValueOptions:
  given codec: Reader[structures.InlineValueOptions] = Pickle.macroR

case class InlayHintLabelPart(
  value: String,
  tooltip: (String | structures.MarkupContent),
  location: structures.Location,
  command: structures.Command
)
object InlayHintLabelPart:
  private val rd0 = badMerge(stringCodec.widen[(String | structures.MarkupContent)], structures.MarkupContent.codec.widen[(String | structures.MarkupContent)])
  given reader_rd0: Reader[(String | structures.MarkupContent)] = rd0
  given codec: Reader[structures.InlayHintLabelPart] = Pickle.macroR

case class MarkupContent(
  kind: enumerations.MarkupKind,
  value: String
)
object MarkupContent:
  given codec: Reader[structures.MarkupContent] = Pickle.macroR

case class InlayHintOptions(
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object InlayHintOptions:
  given codec: Reader[structures.InlayHintOptions] = Pickle.macroR

case class RelatedFullDocumentDiagnosticReport(
  relatedDocuments: Map[RuntimeBase.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)],
  kind: "full",
  resultId: String,
  items: Vector[structures.Diagnostic]
)
object RelatedFullDocumentDiagnosticReport:
  private val rd0 = badMerge(structures.FullDocumentDiagnosticReport.codec.widen[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)], structures.UnchangedDocumentDiagnosticReport.codec.widen[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)])
  given reader_rd0: Reader[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = rd0
  given codec: Reader[structures.RelatedFullDocumentDiagnosticReport] = Pickle.macroR

case class RelatedUnchangedDocumentDiagnosticReport(
  relatedDocuments: Map[RuntimeBase.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)],
  kind: "unchanged",
  resultId: String
)
object RelatedUnchangedDocumentDiagnosticReport:
  private val rd0 = badMerge(structures.FullDocumentDiagnosticReport.codec.widen[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)], structures.UnchangedDocumentDiagnosticReport.codec.widen[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)])
  given reader_rd0: Reader[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = rd0
  given codec: Reader[structures.RelatedUnchangedDocumentDiagnosticReport] = Pickle.macroR

case class FullDocumentDiagnosticReport(
  kind: "full",
  resultId: String,
  items: Vector[structures.Diagnostic]
)
object FullDocumentDiagnosticReport:
  given codec: Reader[structures.FullDocumentDiagnosticReport] = Pickle.macroR

case class UnchangedDocumentDiagnosticReport(
  kind: "unchanged",
  resultId: String
)
object UnchangedDocumentDiagnosticReport:
  given codec: Reader[structures.UnchangedDocumentDiagnosticReport] = Pickle.macroR

case class DiagnosticOptions(
  identifier: String,
  interFileDependencies: Boolean,
  workspaceDiagnostics: Boolean,
  workDoneProgress: Boolean
)
object DiagnosticOptions:
  given codec: Reader[structures.DiagnosticOptions] = Pickle.macroR

case class PreviousResultId(
  uri: RuntimeBase.DocumentUri,
  value: String
)
object PreviousResultId:
  given codec: Reader[structures.PreviousResultId] = Pickle.macroR

case class NotebookDocument(
  uri: aliases.URI,
  notebookType: String,
  version: Int,
  metadata: structures.LSPObject,
  cells: Vector[structures.NotebookCell]
)
object NotebookDocument:
  given codec: Reader[structures.NotebookDocument] = Pickle.macroR

case class TextDocumentItem(
  uri: RuntimeBase.DocumentUri,
  languageId: String,
  version: Int,
  text: String
)
object TextDocumentItem:
  given codec: Reader[structures.TextDocumentItem] = Pickle.macroR

case class VersionedNotebookDocumentIdentifier(
  version: Int,
  uri: aliases.URI
)
object VersionedNotebookDocumentIdentifier:
  given codec: Reader[structures.VersionedNotebookDocumentIdentifier] = Pickle.macroR

case class NotebookDocumentChangeEvent(
  metadata: structures.LSPObject,
  cells: NotebookDocumentChangeEvent.Cells
)
object NotebookDocumentChangeEvent:
  given codec: Reader[structures.NotebookDocumentChangeEvent] = Pickle.macroR
  case class Cells(
    structure: Cells.Structure,
    data: Vector[structures.NotebookCell],
    textContent: Vector[Cells.S0]
  )
  object Cells:
    given codec: Reader[structures.NotebookDocumentChangeEvent.Cells] = Pickle.macroR
    case class Structure(
      array: structures.NotebookCellArrayChange,
      didOpen: Vector[structures.TextDocumentItem],
      didClose: Vector[structures.TextDocumentIdentifier]
    )
    object Structure:
      given codec: Reader[structures.NotebookDocumentChangeEvent.Cells.Structure] = Pickle.macroR
    case class S0(
      document: structures.VersionedTextDocumentIdentifier,
      changes: Vector[aliases.TextDocumentContentChangeEvent]
    )
    object S0:
      given codec: Reader[structures.NotebookDocumentChangeEvent.Cells.S0] = Pickle.macroR

case class NotebookDocumentIdentifier(
  uri: aliases.URI
)
object NotebookDocumentIdentifier:
  given codec: Reader[structures.NotebookDocumentIdentifier] = Pickle.macroR

case class Registration(
  id: String,
  method: String,
  registerOptions: aliases.LSPAny
)
object Registration:
  given codec: Reader[structures.Registration] = Pickle.macroR

case class Unregistration(
  id: String,
  method: String
)
object Unregistration:
  given codec: Reader[structures.Unregistration] = Pickle.macroR

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
  private val rd0 = badMerge(intCodec.widen[(Int | Null)], nullReadWriter.widen[(Int | Null)])
  given reader_rd0: Reader[(Int | Null)] = rd0
  private val rd1 = badMerge(stringCodec.widen[(String | Null)], nullReadWriter.widen[(String | Null)])
  given reader_rd1: Reader[(String | Null)] = rd1
  private val rd2 = badMerge(reader[RuntimeBase.DocumentUri].widen[(RuntimeBase.DocumentUri | Null)], nullReadWriter.widen[(RuntimeBase.DocumentUri | Null)])
  given reader_rd2: Reader[(RuntimeBase.DocumentUri | Null)] = rd2
  private val rd3 = badMerge(reader["off"].widen[("off" | "messages" | "compact" | "verbose")], reader["messages"].widen[("off" | "messages" | "compact" | "verbose")], reader["compact"].widen[("off" | "messages" | "compact" | "verbose")], reader["verbose"].widen[("off" | "messages" | "compact" | "verbose")])
  given reader_rd3: Reader[("off" | "messages" | "compact" | "verbose")] = rd3
  given codec: Reader[structures._InitializeParams] = Pickle.macroR
  case class ClientInfo(
    name: String,
    version: String
  )
  object ClientInfo:
    given codec: Reader[structures._InitializeParams.ClientInfo] = Pickle.macroR

case class WorkspaceFoldersInitializeParams(
  workspaceFolders: (Vector[structures.WorkspaceFolder] | Null)
)
object WorkspaceFoldersInitializeParams:
  private val rd0 = badMerge(reader[Vector[structures.WorkspaceFolder]].widen[(Vector[structures.WorkspaceFolder] | Null)], nullReadWriter.widen[(Vector[structures.WorkspaceFolder] | Null)])
  given reader_rd0: Reader[(Vector[structures.WorkspaceFolder] | Null)] = rd0
  given codec: Reader[structures.WorkspaceFoldersInitializeParams] = Pickle.macroR

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
  private val rd0 = badMerge(structures.TextDocumentSyncOptions.codec.widen[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)], enumerations.TextDocumentSyncKind.codec.widen[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)])
  given reader_rd0: Reader[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)] = rd0
  private val rd1 = badMerge(structures.NotebookDocumentSyncOptions.codec.widen[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)], structures.NotebookDocumentSyncRegistrationOptions.codec.widen[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)])
  given reader_rd1: Reader[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)] = rd1
  private val rd2 = badMerge(reader[Boolean].widen[(Boolean | structures.HoverOptions)], structures.HoverOptions.codec.widen[(Boolean | structures.HoverOptions)])
  given reader_rd2: Reader[(Boolean | structures.HoverOptions)] = rd2
  private val rd3 = badMerge(reader[Boolean].widen[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)], structures.DeclarationOptions.codec.widen[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)], structures.DeclarationRegistrationOptions.codec.widen[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)])
  given reader_rd3: Reader[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)] = rd3
  private val rd4 = badMerge(reader[Boolean].widen[(Boolean | structures.DefinitionOptions)], structures.DefinitionOptions.codec.widen[(Boolean | structures.DefinitionOptions)])
  given reader_rd4: Reader[(Boolean | structures.DefinitionOptions)] = rd4
  private val rd5 = badMerge(reader[Boolean].widen[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)], structures.TypeDefinitionOptions.codec.widen[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)], structures.TypeDefinitionRegistrationOptions.codec.widen[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)])
  given reader_rd5: Reader[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)] = rd5
  private val rd6 = badMerge(reader[Boolean].widen[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)], structures.ImplementationOptions.codec.widen[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)], structures.ImplementationRegistrationOptions.codec.widen[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)])
  given reader_rd6: Reader[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)] = rd6
  private val rd7 = badMerge(reader[Boolean].widen[(Boolean | structures.ReferenceOptions)], structures.ReferenceOptions.codec.widen[(Boolean | structures.ReferenceOptions)])
  given reader_rd7: Reader[(Boolean | structures.ReferenceOptions)] = rd7
  private val rd8 = badMerge(reader[Boolean].widen[(Boolean | structures.DocumentHighlightOptions)], structures.DocumentHighlightOptions.codec.widen[(Boolean | structures.DocumentHighlightOptions)])
  given reader_rd8: Reader[(Boolean | structures.DocumentHighlightOptions)] = rd8
  private val rd9 = badMerge(reader[Boolean].widen[(Boolean | structures.DocumentSymbolOptions)], structures.DocumentSymbolOptions.codec.widen[(Boolean | structures.DocumentSymbolOptions)])
  given reader_rd9: Reader[(Boolean | structures.DocumentSymbolOptions)] = rd9
  private val rd10 = badMerge(reader[Boolean].widen[(Boolean | structures.CodeActionOptions)], structures.CodeActionOptions.codec.widen[(Boolean | structures.CodeActionOptions)])
  given reader_rd10: Reader[(Boolean | structures.CodeActionOptions)] = rd10
  private val rd11 = badMerge(reader[Boolean].widen[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)], structures.DocumentColorOptions.codec.widen[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)], structures.DocumentColorRegistrationOptions.codec.widen[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)])
  given reader_rd11: Reader[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)] = rd11
  private val rd12 = badMerge(reader[Boolean].widen[(Boolean | structures.WorkspaceSymbolOptions)], structures.WorkspaceSymbolOptions.codec.widen[(Boolean | structures.WorkspaceSymbolOptions)])
  given reader_rd12: Reader[(Boolean | structures.WorkspaceSymbolOptions)] = rd12
  private val rd13 = badMerge(reader[Boolean].widen[(Boolean | structures.DocumentFormattingOptions)], structures.DocumentFormattingOptions.codec.widen[(Boolean | structures.DocumentFormattingOptions)])
  given reader_rd13: Reader[(Boolean | structures.DocumentFormattingOptions)] = rd13
  private val rd14 = badMerge(reader[Boolean].widen[(Boolean | structures.DocumentRangeFormattingOptions)], structures.DocumentRangeFormattingOptions.codec.widen[(Boolean | structures.DocumentRangeFormattingOptions)])
  given reader_rd14: Reader[(Boolean | structures.DocumentRangeFormattingOptions)] = rd14
  private val rd15 = badMerge(reader[Boolean].widen[(Boolean | structures.RenameOptions)], structures.RenameOptions.codec.widen[(Boolean | structures.RenameOptions)])
  given reader_rd15: Reader[(Boolean | structures.RenameOptions)] = rd15
  private val rd16 = badMerge(reader[Boolean].widen[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)], structures.FoldingRangeOptions.codec.widen[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)], structures.FoldingRangeRegistrationOptions.codec.widen[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)])
  given reader_rd16: Reader[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)] = rd16
  private val rd17 = badMerge(reader[Boolean].widen[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)], structures.SelectionRangeOptions.codec.widen[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)], structures.SelectionRangeRegistrationOptions.codec.widen[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)])
  given reader_rd17: Reader[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)] = rd17
  private val rd18 = badMerge(reader[Boolean].widen[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)], structures.CallHierarchyOptions.codec.widen[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)], structures.CallHierarchyRegistrationOptions.codec.widen[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)])
  given reader_rd18: Reader[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)] = rd18
  private val rd19 = badMerge(reader[Boolean].widen[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)], structures.LinkedEditingRangeOptions.codec.widen[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)], structures.LinkedEditingRangeRegistrationOptions.codec.widen[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)])
  given reader_rd19: Reader[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)] = rd19
  private val rd20 = badMerge(structures.SemanticTokensOptions.codec.widen[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)], structures.SemanticTokensRegistrationOptions.codec.widen[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)])
  given reader_rd20: Reader[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)] = rd20
  private val rd21 = badMerge(reader[Boolean].widen[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)], structures.MonikerOptions.codec.widen[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)], structures.MonikerRegistrationOptions.codec.widen[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)])
  given reader_rd21: Reader[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)] = rd21
  private val rd22 = badMerge(reader[Boolean].widen[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)], structures.TypeHierarchyOptions.codec.widen[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)], structures.TypeHierarchyRegistrationOptions.codec.widen[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)])
  given reader_rd22: Reader[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)] = rd22
  private val rd23 = badMerge(reader[Boolean].widen[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)], structures.InlineValueOptions.codec.widen[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)], structures.InlineValueRegistrationOptions.codec.widen[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)])
  given reader_rd23: Reader[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)] = rd23
  private val rd24 = badMerge(reader[Boolean].widen[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)], structures.InlayHintOptions.codec.widen[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)], structures.InlayHintRegistrationOptions.codec.widen[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)])
  given reader_rd24: Reader[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)] = rd24
  private val rd25 = badMerge(structures.DiagnosticOptions.codec.widen[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)], structures.DiagnosticRegistrationOptions.codec.widen[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)])
  given reader_rd25: Reader[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)] = rd25
  given codec: Reader[structures.ServerCapabilities] = Pickle.macroR
  case class Workspace(
    workspaceFolders: structures.WorkspaceFoldersServerCapabilities,
    fileOperations: structures.FileOperationOptions
  )
  object Workspace:
    given codec: Reader[structures.ServerCapabilities.Workspace] = Pickle.macroR

case class VersionedTextDocumentIdentifier(
  version: Int,
  uri: RuntimeBase.DocumentUri
)
object VersionedTextDocumentIdentifier:
  given codec: Reader[structures.VersionedTextDocumentIdentifier] = Pickle.macroR

case class SaveOptions(
  includeText: Boolean
)
object SaveOptions:
  given codec: Reader[structures.SaveOptions] = Pickle.macroR

case class FileEvent(
  uri: RuntimeBase.DocumentUri,
  `type`: enumerations.FileChangeType
)
object FileEvent:
  given codec: Reader[structures.FileEvent] = Pickle.macroR

case class FileSystemWatcher(
  globPattern: aliases.GlobPattern,
  kind: enumerations.WatchKind
)
object FileSystemWatcher:
  given codec: Reader[structures.FileSystemWatcher] = Pickle.macroR

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
  private val rd0 = badMerge(intCodec.widen[(Int | String)], stringCodec.widen[(Int | String)])
  given reader_rd0: Reader[(Int | String)] = rd0
  given codec: Reader[structures.Diagnostic] = Pickle.macroR

case class CompletionContext(
  triggerKind: enumerations.CompletionTriggerKind,
  triggerCharacter: String
)
object CompletionContext:
  given codec: Reader[structures.CompletionContext] = Pickle.macroR

case class CompletionItemLabelDetails(
  detail: String,
  description: String
)
object CompletionItemLabelDetails:
  given codec: Reader[structures.CompletionItemLabelDetails] = Pickle.macroR

case class InsertReplaceEdit(
  newText: String,
  insert: structures.Range,
  replace: structures.Range
)
object InsertReplaceEdit:
  given codec: Reader[structures.InsertReplaceEdit] = Pickle.macroR

case class CompletionOptions(
  triggerCharacters: Vector[String],
  allCommitCharacters: Vector[String],
  resolveProvider: Boolean,
  completionItem: CompletionOptions.CompletionItem,
  workDoneProgress: Boolean
)
object CompletionOptions:
  given codec: Reader[structures.CompletionOptions] = Pickle.macroR
  case class CompletionItem(
    labelDetailsSupport: Boolean
  )
  object CompletionItem:
    given codec: Reader[structures.CompletionOptions.CompletionItem] = Pickle.macroR

case class HoverOptions(
  workDoneProgress: Boolean
)
object HoverOptions:
  given codec: Reader[structures.HoverOptions] = Pickle.macroR

case class SignatureHelpContext(
  triggerKind: enumerations.SignatureHelpTriggerKind,
  triggerCharacter: String,
  isRetrigger: Boolean,
  activeSignatureHelp: structures.SignatureHelp
)
object SignatureHelpContext:
  given codec: Reader[structures.SignatureHelpContext] = Pickle.macroR

case class SignatureInformation(
  label: String,
  documentation: (String | structures.MarkupContent),
  parameters: Vector[structures.ParameterInformation],
  activeParameter: RuntimeBase.uinteger
)
object SignatureInformation:
  private val rd0 = badMerge(stringCodec.widen[(String | structures.MarkupContent)], structures.MarkupContent.codec.widen[(String | structures.MarkupContent)])
  given reader_rd0: Reader[(String | structures.MarkupContent)] = rd0
  given codec: Reader[structures.SignatureInformation] = Pickle.macroR

case class SignatureHelpOptions(
  triggerCharacters: Vector[String],
  retriggerCharacters: Vector[String],
  workDoneProgress: Boolean
)
object SignatureHelpOptions:
  given codec: Reader[structures.SignatureHelpOptions] = Pickle.macroR

case class DefinitionOptions(
  workDoneProgress: Boolean
)
object DefinitionOptions:
  given codec: Reader[structures.DefinitionOptions] = Pickle.macroR

case class ReferenceContext(
  includeDeclaration: Boolean
)
object ReferenceContext:
  given codec: Reader[structures.ReferenceContext] = Pickle.macroR

case class ReferenceOptions(
  workDoneProgress: Boolean
)
object ReferenceOptions:
  given codec: Reader[structures.ReferenceOptions] = Pickle.macroR

case class DocumentHighlightOptions(
  workDoneProgress: Boolean
)
object DocumentHighlightOptions:
  given codec: Reader[structures.DocumentHighlightOptions] = Pickle.macroR

case class BaseSymbolInformation(
  name: String,
  kind: enumerations.SymbolKind,
  tags: Vector[enumerations.SymbolTag],
  containerName: String
)
object BaseSymbolInformation:
  given codec: Reader[structures.BaseSymbolInformation] = Pickle.macroR

case class DocumentSymbolOptions(
  label: String,
  workDoneProgress: Boolean
)
object DocumentSymbolOptions:
  given codec: Reader[structures.DocumentSymbolOptions] = Pickle.macroR

case class CodeActionContext(
  diagnostics: Vector[structures.Diagnostic],
  only: Vector[enumerations.CodeActionKind],
  triggerKind: enumerations.CodeActionTriggerKind
)
object CodeActionContext:
  given codec: Reader[structures.CodeActionContext] = Pickle.macroR

case class CodeActionOptions(
  codeActionKinds: Vector[enumerations.CodeActionKind],
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object CodeActionOptions:
  given codec: Reader[structures.CodeActionOptions] = Pickle.macroR

case class WorkspaceSymbolOptions(
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object WorkspaceSymbolOptions:
  given codec: Reader[structures.WorkspaceSymbolOptions] = Pickle.macroR

case class CodeLensOptions(
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object CodeLensOptions:
  given codec: Reader[structures.CodeLensOptions] = Pickle.macroR

case class DocumentLinkOptions(
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object DocumentLinkOptions:
  given codec: Reader[structures.DocumentLinkOptions] = Pickle.macroR

case class FormattingOptions(
  tabSize: RuntimeBase.uinteger,
  insertSpaces: Boolean,
  trimTrailingWhitespace: Boolean,
  insertFinalNewline: Boolean,
  trimFinalNewlines: Boolean
)
object FormattingOptions:
  given codec: Reader[structures.FormattingOptions] = Pickle.macroR

case class DocumentFormattingOptions(
  workDoneProgress: Boolean
)
object DocumentFormattingOptions:
  given codec: Reader[structures.DocumentFormattingOptions] = Pickle.macroR

case class DocumentRangeFormattingOptions(
  workDoneProgress: Boolean
)
object DocumentRangeFormattingOptions:
  given codec: Reader[structures.DocumentRangeFormattingOptions] = Pickle.macroR

case class DocumentOnTypeFormattingOptions(
  firstTriggerCharacter: String,
  moreTriggerCharacter: Vector[String]
)
object DocumentOnTypeFormattingOptions:
  given codec: Reader[structures.DocumentOnTypeFormattingOptions] = Pickle.macroR

case class RenameOptions(
  prepareProvider: Boolean,
  workDoneProgress: Boolean
)
object RenameOptions:
  given codec: Reader[structures.RenameOptions] = Pickle.macroR

case class ExecuteCommandOptions(
  commands: Vector[String],
  workDoneProgress: Boolean
)
object ExecuteCommandOptions:
  given codec: Reader[structures.ExecuteCommandOptions] = Pickle.macroR

case class SemanticTokensLegend(
  tokenTypes: Vector[String],
  tokenModifiers: Vector[String]
)
object SemanticTokensLegend:
  given codec: Reader[structures.SemanticTokensLegend] = Pickle.macroR

case class OptionalVersionedTextDocumentIdentifier(
  version: (Int | Null),
  uri: RuntimeBase.DocumentUri
)
object OptionalVersionedTextDocumentIdentifier:
  private val rd0 = badMerge(intCodec.widen[(Int | Null)], nullReadWriter.widen[(Int | Null)])
  given reader_rd0: Reader[(Int | Null)] = rd0
  given codec: Reader[structures.OptionalVersionedTextDocumentIdentifier] = Pickle.macroR

case class AnnotatedTextEdit(
  annotationId: aliases.ChangeAnnotationIdentifier,
  range: structures.Range,
  newText: String
)
object AnnotatedTextEdit:
  given codec: Reader[structures.AnnotatedTextEdit] = Pickle.macroR

case class ResourceOperation(
  kind: String,
  annotationId: aliases.ChangeAnnotationIdentifier
)
object ResourceOperation:
  given codec: Reader[structures.ResourceOperation] = Pickle.macroR

case class CreateFileOptions(
  overwrite: Boolean,
  ignoreIfExists: Boolean
)
object CreateFileOptions:
  given codec: Reader[structures.CreateFileOptions] = Pickle.macroR

case class RenameFileOptions(
  overwrite: Boolean,
  ignoreIfExists: Boolean
)
object RenameFileOptions:
  given codec: Reader[structures.RenameFileOptions] = Pickle.macroR

case class DeleteFileOptions(
  recursive: Boolean,
  ignoreIfNotExists: Boolean
)
object DeleteFileOptions:
  given codec: Reader[structures.DeleteFileOptions] = Pickle.macroR

case class FileOperationPattern(
  glob: String,
  matches: enumerations.FileOperationPatternKind,
  options: structures.FileOperationPatternOptions
)
object FileOperationPattern:
  given codec: Reader[structures.FileOperationPattern] = Pickle.macroR

case class WorkspaceFullDocumentDiagnosticReport(
  uri: RuntimeBase.DocumentUri,
  version: (Int | Null),
  kind: "full",
  resultId: String,
  items: Vector[structures.Diagnostic]
)
object WorkspaceFullDocumentDiagnosticReport:
  private val rd0 = badMerge(intCodec.widen[(Int | Null)], nullReadWriter.widen[(Int | Null)])
  given reader_rd0: Reader[(Int | Null)] = rd0
  given codec: Reader[structures.WorkspaceFullDocumentDiagnosticReport] = Pickle.macroR

case class WorkspaceUnchangedDocumentDiagnosticReport(
  uri: RuntimeBase.DocumentUri,
  version: (Int | Null),
  kind: "unchanged",
  resultId: String
)
object WorkspaceUnchangedDocumentDiagnosticReport:
  private val rd0 = badMerge(intCodec.widen[(Int | Null)], nullReadWriter.widen[(Int | Null)])
  given reader_rd0: Reader[(Int | Null)] = rd0
  given codec: Reader[structures.WorkspaceUnchangedDocumentDiagnosticReport] = Pickle.macroR

case class LSPObject(
)
object LSPObject:
  given codec: Reader[structures.LSPObject] = Pickle.macroR

case class NotebookCell(
  kind: enumerations.NotebookCellKind,
  document: RuntimeBase.DocumentUri,
  metadata: structures.LSPObject,
  executionSummary: structures.ExecutionSummary
)
object NotebookCell:
  given codec: Reader[structures.NotebookCell] = Pickle.macroR

case class NotebookCellArrayChange(
  start: RuntimeBase.uinteger,
  deleteCount: RuntimeBase.uinteger,
  cells: Vector[structures.NotebookCell]
)
object NotebookCellArrayChange:
  given codec: Reader[structures.NotebookCellArrayChange] = Pickle.macroR

case class ClientCapabilities(
  workspace: structures.WorkspaceClientCapabilities,
  textDocument: structures.TextDocumentClientCapabilities,
  notebookDocument: structures.NotebookDocumentClientCapabilities,
  window: structures.WindowClientCapabilities,
  general: structures.GeneralClientCapabilities,
  experimental: aliases.LSPAny
)
object ClientCapabilities:
  given codec: Reader[structures.ClientCapabilities] = Pickle.macroR

case class TextDocumentSyncOptions(
  openClose: Boolean,
  change: enumerations.TextDocumentSyncKind,
  willSave: Boolean,
  willSaveWaitUntil: Boolean,
  save: (Boolean | structures.SaveOptions)
)
object TextDocumentSyncOptions:
  private val rd0 = badMerge(reader[Boolean].widen[(Boolean | structures.SaveOptions)], structures.SaveOptions.codec.widen[(Boolean | structures.SaveOptions)])
  given reader_rd0: Reader[(Boolean | structures.SaveOptions)] = rd0
  given codec: Reader[structures.TextDocumentSyncOptions] = Pickle.macroR

case class NotebookDocumentSyncOptions(
  notebookSelector: Vector[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)],
  save: Boolean
)
object NotebookDocumentSyncOptions:
  private val rd0 = badMerge(NotebookDocumentSyncOptions.S0.codec.widen[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)], NotebookDocumentSyncOptions.S1.codec.widen[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)])
  given reader_rd0: Reader[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)] = rd0
  given codec: Reader[structures.NotebookDocumentSyncOptions] = Pickle.macroR
  case class S0(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Vector[S0.S0]
  )
  object S0:
    private val rd0 = badMerge(stringCodec.widen[(String | aliases.NotebookDocumentFilter)], aliases.NotebookDocumentFilter.codec.widen[(String | aliases.NotebookDocumentFilter)])
    given reader_rd0: Reader[(String | aliases.NotebookDocumentFilter)] = rd0
    given codec: Reader[structures.NotebookDocumentSyncOptions.S0] = Pickle.macroR
    case class S0(
      language: String
    )
    object S0:
      given codec: Reader[structures.NotebookDocumentSyncOptions.S0.S0] = Pickle.macroR
  case class S1(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Vector[S1.S0]
  )
  object S1:
    private val rd0 = badMerge(stringCodec.widen[(String | aliases.NotebookDocumentFilter)], aliases.NotebookDocumentFilter.codec.widen[(String | aliases.NotebookDocumentFilter)])
    given reader_rd0: Reader[(String | aliases.NotebookDocumentFilter)] = rd0
    given codec: Reader[structures.NotebookDocumentSyncOptions.S1] = Pickle.macroR
    case class S0(
      language: String
    )
    object S0:
      given codec: Reader[structures.NotebookDocumentSyncOptions.S1.S0] = Pickle.macroR

case class NotebookDocumentSyncRegistrationOptions(
  notebookSelector: Vector[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)],
  save: Boolean,
  id: String
)
object NotebookDocumentSyncRegistrationOptions:
  private val rd0 = badMerge(NotebookDocumentSyncRegistrationOptions.S0.codec.widen[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)], NotebookDocumentSyncRegistrationOptions.S1.codec.widen[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)])
  given reader_rd0: Reader[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)] = rd0
  given codec: Reader[structures.NotebookDocumentSyncRegistrationOptions] = Pickle.macroR
  case class S0(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Vector[S0.S0]
  )
  object S0:
    private val rd0 = badMerge(stringCodec.widen[(String | aliases.NotebookDocumentFilter)], aliases.NotebookDocumentFilter.codec.widen[(String | aliases.NotebookDocumentFilter)])
    given reader_rd0: Reader[(String | aliases.NotebookDocumentFilter)] = rd0
    given codec: Reader[structures.NotebookDocumentSyncRegistrationOptions.S0] = Pickle.macroR
    case class S0(
      language: String
    )
    object S0:
      given codec: Reader[structures.NotebookDocumentSyncRegistrationOptions.S0.S0] = Pickle.macroR
  case class S1(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Vector[S1.S0]
  )
  object S1:
    private val rd0 = badMerge(stringCodec.widen[(String | aliases.NotebookDocumentFilter)], aliases.NotebookDocumentFilter.codec.widen[(String | aliases.NotebookDocumentFilter)])
    given reader_rd0: Reader[(String | aliases.NotebookDocumentFilter)] = rd0
    given codec: Reader[structures.NotebookDocumentSyncRegistrationOptions.S1] = Pickle.macroR
    case class S0(
      language: String
    )
    object S0:
      given codec: Reader[structures.NotebookDocumentSyncRegistrationOptions.S1.S0] = Pickle.macroR

case class WorkspaceFoldersServerCapabilities(
  supported: Boolean,
  changeNotifications: (String | Boolean)
)
object WorkspaceFoldersServerCapabilities:
  private val rd0 = badMerge(stringCodec.widen[(String | Boolean)], reader[Boolean].widen[(String | Boolean)])
  given reader_rd0: Reader[(String | Boolean)] = rd0
  given codec: Reader[structures.WorkspaceFoldersServerCapabilities] = Pickle.macroR

case class FileOperationOptions(
  didCreate: structures.FileOperationRegistrationOptions,
  willCreate: structures.FileOperationRegistrationOptions,
  didRename: structures.FileOperationRegistrationOptions,
  willRename: structures.FileOperationRegistrationOptions,
  didDelete: structures.FileOperationRegistrationOptions,
  willDelete: structures.FileOperationRegistrationOptions
)
object FileOperationOptions:
  given codec: Reader[structures.FileOperationOptions] = Pickle.macroR

case class T(
)
object T:
  given codec: Reader[structures.T] = Pickle.macroR

case class CodeDescription(
  href: aliases.URI
)
object CodeDescription:
  given codec: Reader[structures.CodeDescription] = Pickle.macroR

case class DiagnosticRelatedInformation(
  location: structures.Location,
  message: String
)
object DiagnosticRelatedInformation:
  given codec: Reader[structures.DiagnosticRelatedInformation] = Pickle.macroR

case class ParameterInformation(
  label: (String | (RuntimeBase.uinteger, RuntimeBase.uinteger)),
  documentation: (String | structures.MarkupContent)
)
object ParameterInformation:
  private val rd0 = badMerge(stringCodec.widen[(String | (RuntimeBase.uinteger, RuntimeBase.uinteger))], reader[(RuntimeBase.uinteger, RuntimeBase.uinteger)].widen[(String | (RuntimeBase.uinteger, RuntimeBase.uinteger))])
  given reader_rd0: Reader[(String | (RuntimeBase.uinteger, RuntimeBase.uinteger))] = rd0
  private val rd1 = badMerge(stringCodec.widen[(String | structures.MarkupContent)], structures.MarkupContent.codec.widen[(String | structures.MarkupContent)])
  given reader_rd1: Reader[(String | structures.MarkupContent)] = rd1
  given codec: Reader[structures.ParameterInformation] = Pickle.macroR

case class NotebookCellTextDocumentFilter(
  notebook: (String | aliases.NotebookDocumentFilter),
  language: String
)
object NotebookCellTextDocumentFilter:
  private val rd0 = badMerge(stringCodec.widen[(String | aliases.NotebookDocumentFilter)], aliases.NotebookDocumentFilter.codec.widen[(String | aliases.NotebookDocumentFilter)])
  given reader_rd0: Reader[(String | aliases.NotebookDocumentFilter)] = rd0
  given codec: Reader[structures.NotebookCellTextDocumentFilter] = Pickle.macroR

case class FileOperationPatternOptions(
  ignoreCase: Boolean
)
object FileOperationPatternOptions:
  given codec: Reader[structures.FileOperationPatternOptions] = Pickle.macroR

case class ExecutionSummary(
  executionOrder: RuntimeBase.uinteger,
  success: Boolean
)
object ExecutionSummary:
  given codec: Reader[structures.ExecutionSummary] = Pickle.macroR

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
  given codec: Reader[structures.WorkspaceClientCapabilities] = Pickle.macroR

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
  given codec: Reader[structures.TextDocumentClientCapabilities] = Pickle.macroR

case class NotebookDocumentClientCapabilities(
  synchronization: structures.NotebookDocumentSyncClientCapabilities
)
object NotebookDocumentClientCapabilities:
  given codec: Reader[structures.NotebookDocumentClientCapabilities] = Pickle.macroR

case class WindowClientCapabilities(
  workDoneProgress: Boolean,
  showMessage: structures.ShowMessageRequestClientCapabilities,
  showDocument: structures.ShowDocumentClientCapabilities
)
object WindowClientCapabilities:
  given codec: Reader[structures.WindowClientCapabilities] = Pickle.macroR

case class GeneralClientCapabilities(
  staleRequestSupport: GeneralClientCapabilities.StaleRequestSupport,
  regularExpressions: structures.RegularExpressionsClientCapabilities,
  markdown: structures.MarkdownClientCapabilities,
  positionEncodings: Vector[enumerations.PositionEncodingKind]
)
object GeneralClientCapabilities:
  given codec: Reader[structures.GeneralClientCapabilities] = Pickle.macroR
  case class StaleRequestSupport(
    cancel: Boolean,
    retryOnContentModified: Vector[String]
  )
  object StaleRequestSupport:
    given codec: Reader[structures.GeneralClientCapabilities.StaleRequestSupport] = Pickle.macroR

case class RelativePattern(
  baseUri: (structures.WorkspaceFolder | aliases.URI),
  pattern: aliases.Pattern
)
object RelativePattern:
  private val rd0 = badMerge(structures.WorkspaceFolder.codec.widen[(structures.WorkspaceFolder | aliases.URI)], aliases.URI.codec.widen[(structures.WorkspaceFolder | aliases.URI)])
  given reader_rd0: Reader[(structures.WorkspaceFolder | aliases.URI)] = rd0
  given codec: Reader[structures.RelativePattern] = Pickle.macroR

case class WorkspaceEditClientCapabilities(
  documentChanges: Boolean,
  resourceOperations: Vector[enumerations.ResourceOperationKind],
  failureHandling: enumerations.FailureHandlingKind,
  normalizesLineEndings: Boolean,
  changeAnnotationSupport: WorkspaceEditClientCapabilities.ChangeAnnotationSupport
)
object WorkspaceEditClientCapabilities:
  given codec: Reader[structures.WorkspaceEditClientCapabilities] = Pickle.macroR
  case class ChangeAnnotationSupport(
    groupsOnLabel: Boolean
  )
  object ChangeAnnotationSupport:
    given codec: Reader[structures.WorkspaceEditClientCapabilities.ChangeAnnotationSupport] = Pickle.macroR

case class DidChangeConfigurationClientCapabilities(
  dynamicRegistration: Boolean
)
object DidChangeConfigurationClientCapabilities:
  given codec: Reader[structures.DidChangeConfigurationClientCapabilities] = Pickle.macroR

case class DidChangeWatchedFilesClientCapabilities(
  dynamicRegistration: Boolean,
  relativePatternSupport: Boolean
)
object DidChangeWatchedFilesClientCapabilities:
  given codec: Reader[structures.DidChangeWatchedFilesClientCapabilities] = Pickle.macroR

case class WorkspaceSymbolClientCapabilities(
  dynamicRegistration: Boolean,
  symbolKind: WorkspaceSymbolClientCapabilities.SymbolKind,
  tagSupport: WorkspaceSymbolClientCapabilities.TagSupport,
  resolveSupport: WorkspaceSymbolClientCapabilities.ResolveSupport
)
object WorkspaceSymbolClientCapabilities:
  given codec: Reader[structures.WorkspaceSymbolClientCapabilities] = Pickle.macroR
  case class SymbolKind(
    valueSet: Vector[enumerations.SymbolKind]
  )
  object SymbolKind:
    given codec: Reader[structures.WorkspaceSymbolClientCapabilities.SymbolKind] = Pickle.macroR
  case class TagSupport(
    valueSet: Vector[enumerations.SymbolTag]
  )
  object TagSupport:
    given codec: Reader[structures.WorkspaceSymbolClientCapabilities.TagSupport] = Pickle.macroR
  case class ResolveSupport(
    properties: Vector[String]
  )
  object ResolveSupport:
    given codec: Reader[structures.WorkspaceSymbolClientCapabilities.ResolveSupport] = Pickle.macroR

case class ExecuteCommandClientCapabilities(
  dynamicRegistration: Boolean
)
object ExecuteCommandClientCapabilities:
  given codec: Reader[structures.ExecuteCommandClientCapabilities] = Pickle.macroR

case class SemanticTokensWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object SemanticTokensWorkspaceClientCapabilities:
  given codec: Reader[structures.SemanticTokensWorkspaceClientCapabilities] = Pickle.macroR

case class CodeLensWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object CodeLensWorkspaceClientCapabilities:
  given codec: Reader[structures.CodeLensWorkspaceClientCapabilities] = Pickle.macroR

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
  given codec: Reader[structures.FileOperationClientCapabilities] = Pickle.macroR

case class InlineValueWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object InlineValueWorkspaceClientCapabilities:
  given codec: Reader[structures.InlineValueWorkspaceClientCapabilities] = Pickle.macroR

case class InlayHintWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object InlayHintWorkspaceClientCapabilities:
  given codec: Reader[structures.InlayHintWorkspaceClientCapabilities] = Pickle.macroR

case class DiagnosticWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object DiagnosticWorkspaceClientCapabilities:
  given codec: Reader[structures.DiagnosticWorkspaceClientCapabilities] = Pickle.macroR

case class TextDocumentSyncClientCapabilities(
  dynamicRegistration: Boolean,
  willSave: Boolean,
  willSaveWaitUntil: Boolean,
  didSave: Boolean
)
object TextDocumentSyncClientCapabilities:
  given codec: Reader[structures.TextDocumentSyncClientCapabilities] = Pickle.macroR

case class CompletionClientCapabilities(
  dynamicRegistration: Boolean,
  completionItem: CompletionClientCapabilities.CompletionItem,
  completionItemKind: CompletionClientCapabilities.CompletionItemKind,
  insertTextMode: enumerations.InsertTextMode,
  contextSupport: Boolean,
  completionList: CompletionClientCapabilities.CompletionList
)
object CompletionClientCapabilities:
  given codec: Reader[structures.CompletionClientCapabilities] = Pickle.macroR
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
    given codec: Reader[structures.CompletionClientCapabilities.CompletionItem] = Pickle.macroR
    case class TagSupport(
      valueSet: Vector[enumerations.CompletionItemTag]
    )
    object TagSupport:
      given codec: Reader[structures.CompletionClientCapabilities.CompletionItem.TagSupport] = Pickle.macroR
    case class ResolveSupport(
      properties: Vector[String]
    )
    object ResolveSupport:
      given codec: Reader[structures.CompletionClientCapabilities.CompletionItem.ResolveSupport] = Pickle.macroR
    case class InsertTextModeSupport(
      valueSet: Vector[enumerations.InsertTextMode]
    )
    object InsertTextModeSupport:
      given codec: Reader[structures.CompletionClientCapabilities.CompletionItem.InsertTextModeSupport] = Pickle.macroR
  case class CompletionItemKind(
    valueSet: Vector[enumerations.CompletionItemKind]
  )
  object CompletionItemKind:
    given codec: Reader[structures.CompletionClientCapabilities.CompletionItemKind] = Pickle.macroR
  case class CompletionList(
    itemDefaults: Vector[String]
  )
  object CompletionList:
    given codec: Reader[structures.CompletionClientCapabilities.CompletionList] = Pickle.macroR

case class HoverClientCapabilities(
  dynamicRegistration: Boolean,
  contentFormat: Vector[enumerations.MarkupKind]
)
object HoverClientCapabilities:
  given codec: Reader[structures.HoverClientCapabilities] = Pickle.macroR

case class SignatureHelpClientCapabilities(
  dynamicRegistration: Boolean,
  signatureInformation: SignatureHelpClientCapabilities.SignatureInformation,
  contextSupport: Boolean
)
object SignatureHelpClientCapabilities:
  given codec: Reader[structures.SignatureHelpClientCapabilities] = Pickle.macroR
  case class SignatureInformation(
    documentationFormat: Vector[enumerations.MarkupKind],
    parameterInformation: SignatureInformation.ParameterInformation,
    activeParameterSupport: Boolean
  )
  object SignatureInformation:
    given codec: Reader[structures.SignatureHelpClientCapabilities.SignatureInformation] = Pickle.macroR
    case class ParameterInformation(
      labelOffsetSupport: Boolean
    )
    object ParameterInformation:
      given codec: Reader[structures.SignatureHelpClientCapabilities.SignatureInformation.ParameterInformation] = Pickle.macroR

case class DeclarationClientCapabilities(
  dynamicRegistration: Boolean,
  linkSupport: Boolean
)
object DeclarationClientCapabilities:
  given codec: Reader[structures.DeclarationClientCapabilities] = Pickle.macroR

case class DefinitionClientCapabilities(
  dynamicRegistration: Boolean,
  linkSupport: Boolean
)
object DefinitionClientCapabilities:
  given codec: Reader[structures.DefinitionClientCapabilities] = Pickle.macroR

case class TypeDefinitionClientCapabilities(
  dynamicRegistration: Boolean,
  linkSupport: Boolean
)
object TypeDefinitionClientCapabilities:
  given codec: Reader[structures.TypeDefinitionClientCapabilities] = Pickle.macroR

case class ImplementationClientCapabilities(
  dynamicRegistration: Boolean,
  linkSupport: Boolean
)
object ImplementationClientCapabilities:
  given codec: Reader[structures.ImplementationClientCapabilities] = Pickle.macroR

case class ReferenceClientCapabilities(
  dynamicRegistration: Boolean
)
object ReferenceClientCapabilities:
  given codec: Reader[structures.ReferenceClientCapabilities] = Pickle.macroR

case class DocumentHighlightClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentHighlightClientCapabilities:
  given codec: Reader[structures.DocumentHighlightClientCapabilities] = Pickle.macroR

case class DocumentSymbolClientCapabilities(
  dynamicRegistration: Boolean,
  symbolKind: DocumentSymbolClientCapabilities.SymbolKind,
  hierarchicalDocumentSymbolSupport: Boolean,
  tagSupport: DocumentSymbolClientCapabilities.TagSupport,
  labelSupport: Boolean
)
object DocumentSymbolClientCapabilities:
  given codec: Reader[structures.DocumentSymbolClientCapabilities] = Pickle.macroR
  case class SymbolKind(
    valueSet: Vector[enumerations.SymbolKind]
  )
  object SymbolKind:
    given codec: Reader[structures.DocumentSymbolClientCapabilities.SymbolKind] = Pickle.macroR
  case class TagSupport(
    valueSet: Vector[enumerations.SymbolTag]
  )
  object TagSupport:
    given codec: Reader[structures.DocumentSymbolClientCapabilities.TagSupport] = Pickle.macroR

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
  given codec: Reader[structures.CodeActionClientCapabilities] = Pickle.macroR
  case class CodeActionLiteralSupport(
    codeActionKind: CodeActionLiteralSupport.CodeActionKind
  )
  object CodeActionLiteralSupport:
    given codec: Reader[structures.CodeActionClientCapabilities.CodeActionLiteralSupport] = Pickle.macroR
    case class CodeActionKind(
      valueSet: Vector[enumerations.CodeActionKind]
    )
    object CodeActionKind:
      given codec: Reader[structures.CodeActionClientCapabilities.CodeActionLiteralSupport.CodeActionKind] = Pickle.macroR
  case class ResolveSupport(
    properties: Vector[String]
  )
  object ResolveSupport:
    given codec: Reader[structures.CodeActionClientCapabilities.ResolveSupport] = Pickle.macroR

case class CodeLensClientCapabilities(
  dynamicRegistration: Boolean
)
object CodeLensClientCapabilities:
  given codec: Reader[structures.CodeLensClientCapabilities] = Pickle.macroR

case class DocumentLinkClientCapabilities(
  dynamicRegistration: Boolean,
  tooltipSupport: Boolean
)
object DocumentLinkClientCapabilities:
  given codec: Reader[structures.DocumentLinkClientCapabilities] = Pickle.macroR

case class DocumentColorClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentColorClientCapabilities:
  given codec: Reader[structures.DocumentColorClientCapabilities] = Pickle.macroR

case class DocumentFormattingClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentFormattingClientCapabilities:
  given codec: Reader[structures.DocumentFormattingClientCapabilities] = Pickle.macroR

case class DocumentRangeFormattingClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentRangeFormattingClientCapabilities:
  given codec: Reader[structures.DocumentRangeFormattingClientCapabilities] = Pickle.macroR

case class DocumentOnTypeFormattingClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentOnTypeFormattingClientCapabilities:
  given codec: Reader[structures.DocumentOnTypeFormattingClientCapabilities] = Pickle.macroR

case class RenameClientCapabilities(
  dynamicRegistration: Boolean,
  prepareSupport: Boolean,
  prepareSupportDefaultBehavior: enumerations.PrepareSupportDefaultBehavior,
  honorsChangeAnnotations: Boolean
)
object RenameClientCapabilities:
  given codec: Reader[structures.RenameClientCapabilities] = Pickle.macroR

case class FoldingRangeClientCapabilities(
  dynamicRegistration: Boolean,
  rangeLimit: RuntimeBase.uinteger,
  lineFoldingOnly: Boolean,
  foldingRangeKind: FoldingRangeClientCapabilities.FoldingRangeKind,
  foldingRange: FoldingRangeClientCapabilities.FoldingRange
)
object FoldingRangeClientCapabilities:
  given codec: Reader[structures.FoldingRangeClientCapabilities] = Pickle.macroR
  case class FoldingRangeKind(
    valueSet: Vector[enumerations.FoldingRangeKind]
  )
  object FoldingRangeKind:
    given codec: Reader[structures.FoldingRangeClientCapabilities.FoldingRangeKind] = Pickle.macroR
  case class FoldingRange(
    collapsedText: Boolean
  )
  object FoldingRange:
    given codec: Reader[structures.FoldingRangeClientCapabilities.FoldingRange] = Pickle.macroR

case class SelectionRangeClientCapabilities(
  dynamicRegistration: Boolean
)
object SelectionRangeClientCapabilities:
  given codec: Reader[structures.SelectionRangeClientCapabilities] = Pickle.macroR

case class PublishDiagnosticsClientCapabilities(
  relatedInformation: Boolean,
  tagSupport: PublishDiagnosticsClientCapabilities.TagSupport,
  versionSupport: Boolean,
  codeDescriptionSupport: Boolean,
  dataSupport: Boolean
)
object PublishDiagnosticsClientCapabilities:
  given codec: Reader[structures.PublishDiagnosticsClientCapabilities] = Pickle.macroR
  case class TagSupport(
    valueSet: Vector[enumerations.DiagnosticTag]
  )
  object TagSupport:
    given codec: Reader[structures.PublishDiagnosticsClientCapabilities.TagSupport] = Pickle.macroR

case class CallHierarchyClientCapabilities(
  dynamicRegistration: Boolean
)
object CallHierarchyClientCapabilities:
  given codec: Reader[structures.CallHierarchyClientCapabilities] = Pickle.macroR

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
  given codec: Reader[structures.SemanticTokensClientCapabilities] = Pickle.macroR
  case class Requests(
    range: (Boolean | Requests.S0),
    full: (Boolean | Requests.S1)
  )
  object Requests:
    private val rd0 = badMerge(reader[Boolean].widen[(Boolean | Requests.S0)], Requests.S0.codec.widen[(Boolean | Requests.S0)])
    given reader_rd0: Reader[(Boolean | Requests.S0)] = rd0
    private val rd1 = badMerge(reader[Boolean].widen[(Boolean | Requests.S1)], Requests.S1.codec.widen[(Boolean | Requests.S1)])
    given reader_rd1: Reader[(Boolean | Requests.S1)] = rd1
    given codec: Reader[structures.SemanticTokensClientCapabilities.Requests] = Pickle.macroR
    case class S0(
    )
    object S0:
      given codec: Reader[structures.SemanticTokensClientCapabilities.Requests.S0] = Pickle.macroR
    case class S1(
      delta: Boolean
    )
    object S1:
      given codec: Reader[structures.SemanticTokensClientCapabilities.Requests.S1] = Pickle.macroR

case class LinkedEditingRangeClientCapabilities(
  dynamicRegistration: Boolean
)
object LinkedEditingRangeClientCapabilities:
  given codec: Reader[structures.LinkedEditingRangeClientCapabilities] = Pickle.macroR

case class MonikerClientCapabilities(
  dynamicRegistration: Boolean
)
object MonikerClientCapabilities:
  given codec: Reader[structures.MonikerClientCapabilities] = Pickle.macroR

case class TypeHierarchyClientCapabilities(
  dynamicRegistration: Boolean
)
object TypeHierarchyClientCapabilities:
  given codec: Reader[structures.TypeHierarchyClientCapabilities] = Pickle.macroR

case class InlineValueClientCapabilities(
  dynamicRegistration: Boolean
)
object InlineValueClientCapabilities:
  given codec: Reader[structures.InlineValueClientCapabilities] = Pickle.macroR

case class InlayHintClientCapabilities(
  dynamicRegistration: Boolean,
  resolveSupport: InlayHintClientCapabilities.ResolveSupport
)
object InlayHintClientCapabilities:
  given codec: Reader[structures.InlayHintClientCapabilities] = Pickle.macroR
  case class ResolveSupport(
    properties: Vector[String]
  )
  object ResolveSupport:
    given codec: Reader[structures.InlayHintClientCapabilities.ResolveSupport] = Pickle.macroR

case class DiagnosticClientCapabilities(
  dynamicRegistration: Boolean,
  relatedDocumentSupport: Boolean
)
object DiagnosticClientCapabilities:
  given codec: Reader[structures.DiagnosticClientCapabilities] = Pickle.macroR

case class NotebookDocumentSyncClientCapabilities(
  dynamicRegistration: Boolean,
  executionSummarySupport: Boolean
)
object NotebookDocumentSyncClientCapabilities:
  given codec: Reader[structures.NotebookDocumentSyncClientCapabilities] = Pickle.macroR

case class ShowMessageRequestClientCapabilities(
  messageActionItem: ShowMessageRequestClientCapabilities.MessageActionItem
)
object ShowMessageRequestClientCapabilities:
  given codec: Reader[structures.ShowMessageRequestClientCapabilities] = Pickle.macroR
  case class MessageActionItem(
    additionalPropertiesSupport: Boolean
  )
  object MessageActionItem:
    given codec: Reader[structures.ShowMessageRequestClientCapabilities.MessageActionItem] = Pickle.macroR

case class ShowDocumentClientCapabilities(
  support: Boolean
)
object ShowDocumentClientCapabilities:
  given codec: Reader[structures.ShowDocumentClientCapabilities] = Pickle.macroR

case class RegularExpressionsClientCapabilities(
  engine: String,
  version: String
)
object RegularExpressionsClientCapabilities:
  given codec: Reader[structures.RegularExpressionsClientCapabilities] = Pickle.macroR

case class MarkdownClientCapabilities(
  parser: String,
  version: String,
  allowedTags: Vector[String]
)
object MarkdownClientCapabilities:
  given codec: Reader[structures.MarkdownClientCapabilities] = Pickle.macroR

