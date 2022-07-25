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
  object codecs:
    given upickle.default.Reader[ImplementationParams] = Pickle.macroR
  export codecs.{*, given}

case class Location(
  uri: RuntimeBase.DocumentUri,
  range: structures.Range
)
object Location:
  object codecs:
    given upickle.default.Reader[Location] = Pickle.macroR
  export codecs.{*, given}

case class ImplementationRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object ImplementationRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[ImplementationRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class TypeDefinitionParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object TypeDefinitionParams:
  object codecs:
    given upickle.default.Reader[TypeDefinitionParams] = Pickle.macroR
  export codecs.{*, given}

case class TypeDefinitionRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object TypeDefinitionRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[TypeDefinitionRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class WorkspaceFolder(
  uri: aliases.URI,
  name: String
)
object WorkspaceFolder:
  object codecs:
    given upickle.default.Reader[WorkspaceFolder] = Pickle.macroR
  export codecs.{*, given}

case class DidChangeWorkspaceFoldersParams(
  event: structures.WorkspaceFoldersChangeEvent
)
object DidChangeWorkspaceFoldersParams:
  object codecs:
    given upickle.default.Reader[DidChangeWorkspaceFoldersParams] = Pickle.macroR
  export codecs.{*, given}

case class ConfigurationParams(
  items: Vector[structures.ConfigurationItem]
)
object ConfigurationParams:
  object codecs:
    given upickle.default.Reader[ConfigurationParams] = Pickle.macroR
  export codecs.{*, given}

case class PartialResultParams(
  partialResultToken: aliases.ProgressToken
)
object PartialResultParams:
  object codecs:
    given upickle.default.Reader[PartialResultParams] = Pickle.macroR
  export codecs.{*, given}

case class DocumentColorParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DocumentColorParams:
  object codecs:
    given upickle.default.Reader[DocumentColorParams] = Pickle.macroR
  export codecs.{*, given}

case class ColorInformation(
  range: structures.Range,
  color: structures.Color
)
object ColorInformation:
  object codecs:
    given upickle.default.Reader[ColorInformation] = Pickle.macroR
  export codecs.{*, given}

case class DocumentColorRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object DocumentColorRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[DocumentColorRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class ColorPresentationParams(
  textDocument: structures.TextDocumentIdentifier,
  color: structures.Color,
  range: structures.Range,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object ColorPresentationParams:
  object codecs:
    given upickle.default.Reader[ColorPresentationParams] = Pickle.macroR
  export codecs.{*, given}

case class ColorPresentation(
  label: String,
  textEdit: structures.TextEdit,
  additionalTextEdits: Vector[structures.TextEdit]
)
object ColorPresentation:
  object codecs:
    given upickle.default.Reader[ColorPresentation] = Pickle.macroR
  export codecs.{*, given}

case class WorkDoneProgressOptions(
  workDoneProgress: Boolean
)
object WorkDoneProgressOptions:
  object codecs:
    given upickle.default.Reader[WorkDoneProgressOptions] = Pickle.macroR
  export codecs.{*, given}

case class TextDocumentRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object TextDocumentRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[TextDocumentRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class FoldingRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object FoldingRangeParams:
  object codecs:
    given upickle.default.Reader[FoldingRangeParams] = Pickle.macroR
  export codecs.{*, given}

case class FoldingRange(
  startLine: RuntimeBase.uinteger,
  startCharacter: RuntimeBase.uinteger,
  endLine: RuntimeBase.uinteger,
  endCharacter: RuntimeBase.uinteger,
  kind: enumerations.FoldingRangeKind,
  collapsedText: String
)
object FoldingRange:
  object codecs:
    given upickle.default.Reader[FoldingRange] = Pickle.macroR
  export codecs.{*, given}

case class FoldingRangeRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object FoldingRangeRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[FoldingRangeRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class DeclarationParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DeclarationParams:
  object codecs:
    given upickle.default.Reader[DeclarationParams] = Pickle.macroR
  export codecs.{*, given}

case class DeclarationRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object DeclarationRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[DeclarationRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class SelectionRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  positions: Vector[structures.Position],
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object SelectionRangeParams:
  object codecs:
    given upickle.default.Reader[SelectionRangeParams] = Pickle.macroR
  export codecs.{*, given}

case class SelectionRange(
  range: structures.Range,
  parent: structures.SelectionRange
)
object SelectionRange:
  object codecs:
    given upickle.default.Reader[SelectionRange] = Pickle.macroR
  export codecs.{*, given}

case class SelectionRangeRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object SelectionRangeRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[SelectionRangeRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class WorkDoneProgressCreateParams(
  token: aliases.ProgressToken
)
object WorkDoneProgressCreateParams:
  object codecs:
    given upickle.default.Reader[WorkDoneProgressCreateParams] = Pickle.macroR
  export codecs.{*, given}

case class WorkDoneProgressCancelParams(
  token: aliases.ProgressToken
)
object WorkDoneProgressCancelParams:
  object codecs:
    given upickle.default.Reader[WorkDoneProgressCancelParams] = Pickle.macroR
  export codecs.{*, given}

case class CallHierarchyPrepareParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object CallHierarchyPrepareParams:
  object codecs:
    given upickle.default.Reader[CallHierarchyPrepareParams] = Pickle.macroR
  export codecs.{*, given}

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
  object codecs:
    given upickle.default.Reader[CallHierarchyItem] = Pickle.macroR
  export codecs.{*, given}

case class CallHierarchyRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object CallHierarchyRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[CallHierarchyRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class CallHierarchyIncomingCallsParams(
  item: structures.CallHierarchyItem,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object CallHierarchyIncomingCallsParams:
  object codecs:
    given upickle.default.Reader[CallHierarchyIncomingCallsParams] = Pickle.macroR
  export codecs.{*, given}

case class CallHierarchyIncomingCall(
  from: structures.CallHierarchyItem,
  fromRanges: Vector[structures.Range]
)
object CallHierarchyIncomingCall:
  object codecs:
    given upickle.default.Reader[CallHierarchyIncomingCall] = Pickle.macroR
  export codecs.{*, given}

case class CallHierarchyOutgoingCallsParams(
  item: structures.CallHierarchyItem,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object CallHierarchyOutgoingCallsParams:
  object codecs:
    given upickle.default.Reader[CallHierarchyOutgoingCallsParams] = Pickle.macroR
  export codecs.{*, given}

case class CallHierarchyOutgoingCall(
  to: structures.CallHierarchyItem,
  fromRanges: Vector[structures.Range]
)
object CallHierarchyOutgoingCall:
  object codecs:
    given upickle.default.Reader[CallHierarchyOutgoingCall] = Pickle.macroR
  export codecs.{*, given}

case class SemanticTokensParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object SemanticTokensParams:
  object codecs:
    given upickle.default.Reader[SemanticTokensParams] = Pickle.macroR
  export codecs.{*, given}

case class SemanticTokens(
  resultId: String,
  data: Vector[RuntimeBase.uinteger]
)
object SemanticTokens:
  object codecs:
    given upickle.default.Reader[SemanticTokens] = Pickle.macroR
  export codecs.{*, given}

case class SemanticTokensPartialResult(
  data: Vector[RuntimeBase.uinteger]
)
object SemanticTokensPartialResult:
  object codecs:
    given upickle.default.Reader[SemanticTokensPartialResult] = Pickle.macroR
  export codecs.{*, given}

case class SemanticTokensRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  legend: structures.SemanticTokensLegend,
  range: (Boolean | SemanticTokensRegistrationOptions.S0),
  full: (Boolean | SemanticTokensRegistrationOptions.S1),
  id: String
)
object SemanticTokensRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given rd1: Reader[(Boolean | SemanticTokensRegistrationOptions.S0)] = {type T = (Boolean | SemanticTokensRegistrationOptions.S0); badMerge(reader[Boolean].widen[T], reader[SemanticTokensRegistrationOptions.S0].widen[T])}
    given rd2: Reader[(Boolean | SemanticTokensRegistrationOptions.S1)] = {type T = (Boolean | SemanticTokensRegistrationOptions.S1); badMerge(reader[Boolean].widen[T], reader[SemanticTokensRegistrationOptions.S1].widen[T])}
    given upickle.default.Reader[SemanticTokensRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}
  case class S0(
  )
  object S0:
    object codecs:
      given upickle.default.Reader[S0] = Pickle.macroR
    export codecs.{*, given}
  case class S1(
    delta: Boolean
  )
  object S1:
    object codecs:
      given upickle.default.Reader[S1] = Pickle.macroR
    export codecs.{*, given}

case class SemanticTokensDeltaParams(
  textDocument: structures.TextDocumentIdentifier,
  previousResultId: String,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object SemanticTokensDeltaParams:
  object codecs:
    given upickle.default.Reader[SemanticTokensDeltaParams] = Pickle.macroR
  export codecs.{*, given}

case class SemanticTokensDelta(
  resultId: String,
  edits: Vector[structures.SemanticTokensEdit]
)
object SemanticTokensDelta:
  object codecs:
    given upickle.default.Reader[SemanticTokensDelta] = Pickle.macroR
  export codecs.{*, given}

case class SemanticTokensDeltaPartialResult(
  edits: Vector[structures.SemanticTokensEdit]
)
object SemanticTokensDeltaPartialResult:
  object codecs:
    given upickle.default.Reader[SemanticTokensDeltaPartialResult] = Pickle.macroR
  export codecs.{*, given}

case class SemanticTokensRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object SemanticTokensRangeParams:
  object codecs:
    given upickle.default.Reader[SemanticTokensRangeParams] = Pickle.macroR
  export codecs.{*, given}

case class ShowDocumentParams(
  uri: aliases.URI,
  external: Boolean,
  takeFocus: Boolean,
  selection: structures.Range
)
object ShowDocumentParams:
  object codecs:
    given upickle.default.Reader[ShowDocumentParams] = Pickle.macroR
  export codecs.{*, given}

case class ShowDocumentResult(
  success: Boolean
)
object ShowDocumentResult:
  object codecs:
    given upickle.default.Reader[ShowDocumentResult] = Pickle.macroR
  export codecs.{*, given}

case class LinkedEditingRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object LinkedEditingRangeParams:
  object codecs:
    given upickle.default.Reader[LinkedEditingRangeParams] = Pickle.macroR
  export codecs.{*, given}

case class LinkedEditingRanges(
  ranges: Vector[structures.Range],
  wordPattern: String
)
object LinkedEditingRanges:
  object codecs:
    given upickle.default.Reader[LinkedEditingRanges] = Pickle.macroR
  export codecs.{*, given}

case class LinkedEditingRangeRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object LinkedEditingRangeRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[LinkedEditingRangeRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class CreateFilesParams(
  files: Vector[structures.FileCreate]
)
object CreateFilesParams:
  object codecs:
    given upickle.default.Reader[CreateFilesParams] = Pickle.macroR
  export codecs.{*, given}

case class WorkspaceEdit(
  changes: Map[RuntimeBase.DocumentUri, Vector[structures.TextEdit]],
  documentChanges: Vector[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)],
  changeAnnotations: Map[aliases.ChangeAnnotationIdentifier, structures.ChangeAnnotation]
)
object WorkspaceEdit:
  object codecs:
    given rd0: Reader[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)] = {type T = (structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile); badMerge(reader[structures.TextDocumentEdit].widen[T], reader[structures.CreateFile].widen[T], reader[structures.RenameFile].widen[T], reader[structures.DeleteFile].widen[T])}
    given upickle.default.Reader[WorkspaceEdit] = Pickle.macroR
  export codecs.{*, given}

case class FileOperationRegistrationOptions(
  filters: Vector[structures.FileOperationFilter]
)
object FileOperationRegistrationOptions:
  object codecs:
    given upickle.default.Reader[FileOperationRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class RenameFilesParams(
  files: Vector[structures.FileRename]
)
object RenameFilesParams:
  object codecs:
    given upickle.default.Reader[RenameFilesParams] = Pickle.macroR
  export codecs.{*, given}

case class DeleteFilesParams(
  files: Vector[structures.FileDelete]
)
object DeleteFilesParams:
  object codecs:
    given upickle.default.Reader[DeleteFilesParams] = Pickle.macroR
  export codecs.{*, given}

case class MonikerParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object MonikerParams:
  object codecs:
    given upickle.default.Reader[MonikerParams] = Pickle.macroR
  export codecs.{*, given}

case class Moniker(
  scheme: String,
  identifier: String,
  unique: enumerations.UniquenessLevel,
  kind: enumerations.MonikerKind
)
object Moniker:
  object codecs:
    given upickle.default.Reader[Moniker] = Pickle.macroR
  export codecs.{*, given}

case class MonikerRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object MonikerRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[MonikerRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class TypeHierarchyPrepareParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object TypeHierarchyPrepareParams:
  object codecs:
    given upickle.default.Reader[TypeHierarchyPrepareParams] = Pickle.macroR
  export codecs.{*, given}

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
  object codecs:
    given upickle.default.Reader[TypeHierarchyItem] = Pickle.macroR
  export codecs.{*, given}

case class TypeHierarchyRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object TypeHierarchyRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[TypeHierarchyRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class TypeHierarchySupertypesParams(
  item: structures.TypeHierarchyItem,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object TypeHierarchySupertypesParams:
  object codecs:
    given upickle.default.Reader[TypeHierarchySupertypesParams] = Pickle.macroR
  export codecs.{*, given}

case class TypeHierarchySubtypesParams(
  item: structures.TypeHierarchyItem,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object TypeHierarchySubtypesParams:
  object codecs:
    given upickle.default.Reader[TypeHierarchySubtypesParams] = Pickle.macroR
  export codecs.{*, given}

case class InlineValueParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  context: structures.InlineValueContext,
  workDoneToken: aliases.ProgressToken
)
object InlineValueParams:
  object codecs:
    given upickle.default.Reader[InlineValueParams] = Pickle.macroR
  export codecs.{*, given}

case class InlineValueRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object InlineValueRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[InlineValueRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class InlayHintParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  workDoneToken: aliases.ProgressToken
)
object InlayHintParams:
  object codecs:
    given upickle.default.Reader[InlayHintParams] = Pickle.macroR
  export codecs.{*, given}

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
  object codecs:
    given rd0: Reader[(String | Vector[structures.InlayHintLabelPart])] = {type T = (String | Vector[structures.InlayHintLabelPart]); badMerge(reader[String].widen[T], reader[Vector[structures.InlayHintLabelPart]].widen[T])}
    given rd1: Reader[(String | structures.MarkupContent)] = {type T = (String | structures.MarkupContent); badMerge(reader[String].widen[T], reader[structures.MarkupContent].widen[T])}
    given upickle.default.Reader[InlayHint] = Pickle.macroR
  export codecs.{*, given}

case class InlayHintRegistrationOptions(
  resolveProvider: Boolean,
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object InlayHintRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[InlayHintRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class DocumentDiagnosticParams(
  textDocument: structures.TextDocumentIdentifier,
  identifier: String,
  previousResultId: String,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DocumentDiagnosticParams:
  object codecs:
    given upickle.default.Reader[DocumentDiagnosticParams] = Pickle.macroR
  export codecs.{*, given}

case class DocumentDiagnosticReportPartialResult(
  relatedDocuments: Map[RuntimeBase.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)]
)
object DocumentDiagnosticReportPartialResult:
  object codecs:
    given rd0: Reader[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = {type T = (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport); badMerge(reader[structures.FullDocumentDiagnosticReport].widen[T], reader[structures.UnchangedDocumentDiagnosticReport].widen[T])}
    given upickle.default.Reader[DocumentDiagnosticReportPartialResult] = Pickle.macroR
  export codecs.{*, given}

case class DiagnosticServerCancellationData(
  retriggerRequest: Boolean
)
object DiagnosticServerCancellationData:
  object codecs:
    given upickle.default.Reader[DiagnosticServerCancellationData] = Pickle.macroR
  export codecs.{*, given}

case class DiagnosticRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  identifier: String,
  interFileDependencies: Boolean,
  workspaceDiagnostics: Boolean,
  id: String
)
object DiagnosticRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[DiagnosticRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class WorkspaceDiagnosticParams(
  identifier: String,
  previousResultIds: Vector[structures.PreviousResultId],
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object WorkspaceDiagnosticParams:
  object codecs:
    given upickle.default.Reader[WorkspaceDiagnosticParams] = Pickle.macroR
  export codecs.{*, given}

case class WorkspaceDiagnosticReport(
  items: Vector[aliases.WorkspaceDocumentDiagnosticReport]
)
object WorkspaceDiagnosticReport:
  object codecs:
    given upickle.default.Reader[WorkspaceDiagnosticReport] = Pickle.macroR
  export codecs.{*, given}

case class WorkspaceDiagnosticReportPartialResult(
  items: Vector[aliases.WorkspaceDocumentDiagnosticReport]
)
object WorkspaceDiagnosticReportPartialResult:
  object codecs:
    given upickle.default.Reader[WorkspaceDiagnosticReportPartialResult] = Pickle.macroR
  export codecs.{*, given}

case class DidOpenNotebookDocumentParams(
  notebookDocument: structures.NotebookDocument,
  cellTextDocuments: Vector[structures.TextDocumentItem]
)
object DidOpenNotebookDocumentParams:
  object codecs:
    given upickle.default.Reader[DidOpenNotebookDocumentParams] = Pickle.macroR
  export codecs.{*, given}

case class DidChangeNotebookDocumentParams(
  notebookDocument: structures.VersionedNotebookDocumentIdentifier,
  change: structures.NotebookDocumentChangeEvent
)
object DidChangeNotebookDocumentParams:
  object codecs:
    given upickle.default.Reader[DidChangeNotebookDocumentParams] = Pickle.macroR
  export codecs.{*, given}

case class DidSaveNotebookDocumentParams(
  notebookDocument: structures.NotebookDocumentIdentifier
)
object DidSaveNotebookDocumentParams:
  object codecs:
    given upickle.default.Reader[DidSaveNotebookDocumentParams] = Pickle.macroR
  export codecs.{*, given}

case class DidCloseNotebookDocumentParams(
  notebookDocument: structures.NotebookDocumentIdentifier,
  cellTextDocuments: Vector[structures.TextDocumentIdentifier]
)
object DidCloseNotebookDocumentParams:
  object codecs:
    given upickle.default.Reader[DidCloseNotebookDocumentParams] = Pickle.macroR
  export codecs.{*, given}

case class RegistrationParams(
  registrations: Vector[structures.Registration]
)
object RegistrationParams:
  object codecs:
    given upickle.default.Reader[RegistrationParams] = Pickle.macroR
  export codecs.{*, given}

case class UnregistrationParams(
  unregisterations: Vector[structures.Unregistration]
)
object UnregistrationParams:
  object codecs:
    given upickle.default.Reader[UnregistrationParams] = Pickle.macroR
  export codecs.{*, given}

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
  object codecs:
    given rd0: Reader[(Int | Null)] = {type T = (Int | Null); badMerge(reader[Int].widen[T], nullReadWriter.widen[T])}
    given rd1: Reader[(String | Null)] = {type T = (String | Null); badMerge(reader[String].widen[T], nullReadWriter.widen[T])}
    given rd2: Reader[(RuntimeBase.DocumentUri | Null)] = {type T = (RuntimeBase.DocumentUri | Null); badMerge(reader[RuntimeBase.DocumentUri].widen[T], nullReadWriter.widen[T])}
    given rd3: Reader[("off" | "messages" | "compact" | "verbose")] = {type T = ("off" | "messages" | "compact" | "verbose"); badMerge(reader["off"].widen[T], reader["messages"].widen[T], reader["compact"].widen[T], reader["verbose"].widen[T])}
    given rd4: Reader[(Vector[structures.WorkspaceFolder] | Null)] = {type T = (Vector[structures.WorkspaceFolder] | Null); badMerge(reader[Vector[structures.WorkspaceFolder]].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[InitializeParams] = Pickle.macroR
  export codecs.{*, given}
  case class ClientInfo(
    name: String,
    version: String
  )
  object ClientInfo:
    object codecs:
      given upickle.default.Reader[ClientInfo] = Pickle.macroR
    export codecs.{*, given}

case class InitializeResult(
  capabilities: structures.ServerCapabilities,
  serverInfo: InitializeResult.ServerInfo
)
object InitializeResult:
  object codecs:
    given upickle.default.Reader[InitializeResult] = Pickle.macroR
  export codecs.{*, given}
  case class ServerInfo(
    name: String,
    version: String
  )
  object ServerInfo:
    object codecs:
      given upickle.default.Reader[ServerInfo] = Pickle.macroR
    export codecs.{*, given}

case class InitializeError(
  retry: Boolean
)
object InitializeError:
  object codecs:
    given upickle.default.Reader[InitializeError] = Pickle.macroR
  export codecs.{*, given}

case class InitializedParams(
)
object InitializedParams:
  object codecs:
    given upickle.default.Reader[InitializedParams] = Pickle.macroR
  export codecs.{*, given}

case class DidChangeConfigurationParams(
  settings: aliases.LSPAny
)
object DidChangeConfigurationParams:
  object codecs:
    given upickle.default.Reader[DidChangeConfigurationParams] = Pickle.macroR
  export codecs.{*, given}

case class DidChangeConfigurationRegistrationOptions(
  section: (String | Vector[String])
)
object DidChangeConfigurationRegistrationOptions:
  object codecs:
    given rd0: Reader[(String | Vector[String])] = {type T = (String | Vector[String]); badMerge(reader[String].widen[T], reader[Vector[String]].widen[T])}
    given upickle.default.Reader[DidChangeConfigurationRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class ShowMessageParams(
  `type`: enumerations.MessageType,
  message: String
)
object ShowMessageParams:
  object codecs:
    given upickle.default.Reader[ShowMessageParams] = Pickle.macroR
  export codecs.{*, given}

case class ShowMessageRequestParams(
  `type`: enumerations.MessageType,
  message: String,
  actions: Vector[structures.MessageActionItem]
)
object ShowMessageRequestParams:
  object codecs:
    given upickle.default.Reader[ShowMessageRequestParams] = Pickle.macroR
  export codecs.{*, given}

case class MessageActionItem(
  title: String
)
object MessageActionItem:
  object codecs:
    given upickle.default.Reader[MessageActionItem] = Pickle.macroR
  export codecs.{*, given}

case class LogMessageParams(
  `type`: enumerations.MessageType,
  message: String
)
object LogMessageParams:
  object codecs:
    given upickle.default.Reader[LogMessageParams] = Pickle.macroR
  export codecs.{*, given}

case class DidOpenTextDocumentParams(
  textDocument: structures.TextDocumentItem
)
object DidOpenTextDocumentParams:
  object codecs:
    given upickle.default.Reader[DidOpenTextDocumentParams] = Pickle.macroR
  export codecs.{*, given}

case class DidChangeTextDocumentParams(
  textDocument: structures.VersionedTextDocumentIdentifier,
  contentChanges: Vector[aliases.TextDocumentContentChangeEvent]
)
object DidChangeTextDocumentParams:
  object codecs:
    given upickle.default.Reader[DidChangeTextDocumentParams] = Pickle.macroR
  export codecs.{*, given}

case class TextDocumentChangeRegistrationOptions(
  syncKind: enumerations.TextDocumentSyncKind,
  documentSelector: (aliases.DocumentSelector | Null)
)
object TextDocumentChangeRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[TextDocumentChangeRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class DidCloseTextDocumentParams(
  textDocument: structures.TextDocumentIdentifier
)
object DidCloseTextDocumentParams:
  object codecs:
    given upickle.default.Reader[DidCloseTextDocumentParams] = Pickle.macroR
  export codecs.{*, given}

case class DidSaveTextDocumentParams(
  textDocument: structures.TextDocumentIdentifier,
  text: String
)
object DidSaveTextDocumentParams:
  object codecs:
    given upickle.default.Reader[DidSaveTextDocumentParams] = Pickle.macroR
  export codecs.{*, given}

case class TextDocumentSaveRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  includeText: Boolean
)
object TextDocumentSaveRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[TextDocumentSaveRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class WillSaveTextDocumentParams(
  textDocument: structures.TextDocumentIdentifier,
  reason: enumerations.TextDocumentSaveReason
)
object WillSaveTextDocumentParams:
  object codecs:
    given upickle.default.Reader[WillSaveTextDocumentParams] = Pickle.macroR
  export codecs.{*, given}

case class TextEdit(
  range: structures.Range,
  newText: String
)
object TextEdit:
  object codecs:
    given upickle.default.Reader[TextEdit] = Pickle.macroR
  export codecs.{*, given}

case class DidChangeWatchedFilesParams(
  changes: Vector[structures.FileEvent]
)
object DidChangeWatchedFilesParams:
  object codecs:
    given upickle.default.Reader[DidChangeWatchedFilesParams] = Pickle.macroR
  export codecs.{*, given}

case class DidChangeWatchedFilesRegistrationOptions(
  watchers: Vector[structures.FileSystemWatcher]
)
object DidChangeWatchedFilesRegistrationOptions:
  object codecs:
    given upickle.default.Reader[DidChangeWatchedFilesRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class PublishDiagnosticsParams(
  uri: RuntimeBase.DocumentUri,
  version: Int,
  diagnostics: Vector[structures.Diagnostic]
)
object PublishDiagnosticsParams:
  object codecs:
    given upickle.default.Reader[PublishDiagnosticsParams] = Pickle.macroR
  export codecs.{*, given}

case class CompletionParams(
  context: structures.CompletionContext,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object CompletionParams:
  object codecs:
    given upickle.default.Reader[CompletionParams] = Pickle.macroR
  export codecs.{*, given}

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
  object codecs:
    given rd0: Reader[(String | structures.MarkupContent)] = {type T = (String | structures.MarkupContent); badMerge(reader[String].widen[T], reader[structures.MarkupContent].widen[T])}
    given rd1: Reader[(structures.TextEdit | structures.InsertReplaceEdit)] = {type T = (structures.TextEdit | structures.InsertReplaceEdit); badMerge(reader[structures.TextEdit].widen[T], reader[structures.InsertReplaceEdit].widen[T])}
    given upickle.default.Reader[CompletionItem] = Pickle.macroR
  export codecs.{*, given}

case class CompletionList(
  isIncomplete: Boolean,
  itemDefaults: CompletionList.ItemDefaults,
  items: Vector[structures.CompletionItem]
)
object CompletionList:
  object codecs:
    given upickle.default.Reader[CompletionList] = Pickle.macroR
  export codecs.{*, given}
  case class ItemDefaults(
    commitCharacters: Vector[String],
    editRange: (structures.Range | ItemDefaults.S0),
    insertTextFormat: enumerations.InsertTextFormat,
    insertTextMode: enumerations.InsertTextMode,
    data: aliases.LSPAny
  )
  object ItemDefaults:
    object codecs:
      given rd0: Reader[(structures.Range | ItemDefaults.S0)] = {type T = (structures.Range | ItemDefaults.S0); badMerge(reader[structures.Range].widen[T], reader[ItemDefaults.S0].widen[T])}
      given upickle.default.Reader[ItemDefaults] = Pickle.macroR
    export codecs.{*, given}
    case class S0(
      insert: structures.Range,
      replace: structures.Range
    )
    object S0:
      object codecs:
        given upickle.default.Reader[S0] = Pickle.macroR
      export codecs.{*, given}

case class CompletionRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  triggerCharacters: Vector[String],
  allCommitCharacters: Vector[String],
  resolveProvider: Boolean,
  completionItem: CompletionRegistrationOptions.CompletionItem
)
object CompletionRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[CompletionRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}
  case class CompletionItem(
    labelDetailsSupport: Boolean
  )
  object CompletionItem:
    object codecs:
      given upickle.default.Reader[CompletionItem] = Pickle.macroR
    export codecs.{*, given}

case class HoverParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object HoverParams:
  object codecs:
    given upickle.default.Reader[HoverParams] = Pickle.macroR
  export codecs.{*, given}

case class Hover(
  contents: (structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString]),
  range: structures.Range
)
object Hover:
  object codecs:
    given rd0: Reader[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])] = {type T = (structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString]); badMerge(reader[structures.MarkupContent].widen[T], reader[aliases.MarkedString].widen[T], reader[Vector[aliases.MarkedString]].widen[T])}
    given upickle.default.Reader[Hover] = Pickle.macroR
  export codecs.{*, given}

case class HoverRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object HoverRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[HoverRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class SignatureHelpParams(
  context: structures.SignatureHelpContext,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object SignatureHelpParams:
  object codecs:
    given upickle.default.Reader[SignatureHelpParams] = Pickle.macroR
  export codecs.{*, given}

case class SignatureHelp(
  signatures: Vector[structures.SignatureInformation],
  activeSignature: RuntimeBase.uinteger,
  activeParameter: RuntimeBase.uinteger
)
object SignatureHelp:
  object codecs:
    given upickle.default.Reader[SignatureHelp] = Pickle.macroR
  export codecs.{*, given}

case class SignatureHelpRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  triggerCharacters: Vector[String],
  retriggerCharacters: Vector[String]
)
object SignatureHelpRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[SignatureHelpRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class DefinitionParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DefinitionParams:
  object codecs:
    given upickle.default.Reader[DefinitionParams] = Pickle.macroR
  export codecs.{*, given}

case class DefinitionRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object DefinitionRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[DefinitionRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class ReferenceParams(
  context: structures.ReferenceContext,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object ReferenceParams:
  object codecs:
    given upickle.default.Reader[ReferenceParams] = Pickle.macroR
  export codecs.{*, given}

case class ReferenceRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object ReferenceRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[ReferenceRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class DocumentHighlightParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DocumentHighlightParams:
  object codecs:
    given upickle.default.Reader[DocumentHighlightParams] = Pickle.macroR
  export codecs.{*, given}

case class DocumentHighlight(
  range: structures.Range,
  kind: enumerations.DocumentHighlightKind
)
object DocumentHighlight:
  object codecs:
    given upickle.default.Reader[DocumentHighlight] = Pickle.macroR
  export codecs.{*, given}

case class DocumentHighlightRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object DocumentHighlightRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[DocumentHighlightRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class DocumentSymbolParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DocumentSymbolParams:
  object codecs:
    given upickle.default.Reader[DocumentSymbolParams] = Pickle.macroR
  export codecs.{*, given}

case class SymbolInformation(
  deprecated: Boolean,
  location: structures.Location,
  name: String,
  kind: enumerations.SymbolKind,
  tags: Vector[enumerations.SymbolTag],
  containerName: String
)
object SymbolInformation:
  object codecs:
    given upickle.default.Reader[SymbolInformation] = Pickle.macroR
  export codecs.{*, given}

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
  object codecs:
    given upickle.default.Reader[DocumentSymbol] = Pickle.macroR
  export codecs.{*, given}

case class DocumentSymbolRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  label: String
)
object DocumentSymbolRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[DocumentSymbolRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class CodeActionParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  context: structures.CodeActionContext,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object CodeActionParams:
  object codecs:
    given upickle.default.Reader[CodeActionParams] = Pickle.macroR
  export codecs.{*, given}

case class Command(
  title: String,
  command: String,
  arguments: Vector[aliases.LSPAny]
)
object Command:
  object codecs:
    given upickle.default.Reader[Command] = Pickle.macroR
  export codecs.{*, given}

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
  object codecs:
    given upickle.default.Reader[CodeAction] = Pickle.macroR
  export codecs.{*, given}
  case class Disabled(
    reason: String
  )
  object Disabled:
    object codecs:
      given upickle.default.Reader[Disabled] = Pickle.macroR
    export codecs.{*, given}

case class CodeActionRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  codeActionKinds: Vector[enumerations.CodeActionKind],
  resolveProvider: Boolean
)
object CodeActionRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[CodeActionRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class WorkspaceSymbolParams(
  query: String,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object WorkspaceSymbolParams:
  object codecs:
    given upickle.default.Reader[WorkspaceSymbolParams] = Pickle.macroR
  export codecs.{*, given}

case class WorkspaceSymbol(
  location: (structures.Location | WorkspaceSymbol.S0),
  data: aliases.LSPAny,
  name: String,
  kind: enumerations.SymbolKind,
  tags: Vector[enumerations.SymbolTag],
  containerName: String
)
object WorkspaceSymbol:
  object codecs:
    given rd0: Reader[(structures.Location | WorkspaceSymbol.S0)] = {type T = (structures.Location | WorkspaceSymbol.S0); badMerge(reader[structures.Location].widen[T], reader[WorkspaceSymbol.S0].widen[T])}
    given upickle.default.Reader[WorkspaceSymbol] = Pickle.macroR
  export codecs.{*, given}
  case class S0(
    uri: RuntimeBase.DocumentUri
  )
  object S0:
    object codecs:
      given upickle.default.Reader[S0] = Pickle.macroR
    export codecs.{*, given}

case class WorkspaceSymbolRegistrationOptions(
  resolveProvider: Boolean
)
object WorkspaceSymbolRegistrationOptions:
  object codecs:
    given upickle.default.Reader[WorkspaceSymbolRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class CodeLensParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object CodeLensParams:
  object codecs:
    given upickle.default.Reader[CodeLensParams] = Pickle.macroR
  export codecs.{*, given}

case class CodeLens(
  range: structures.Range,
  command: structures.Command,
  data: aliases.LSPAny
)
object CodeLens:
  object codecs:
    given upickle.default.Reader[CodeLens] = Pickle.macroR
  export codecs.{*, given}

case class CodeLensRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  resolveProvider: Boolean
)
object CodeLensRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[CodeLensRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class DocumentLinkParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DocumentLinkParams:
  object codecs:
    given upickle.default.Reader[DocumentLinkParams] = Pickle.macroR
  export codecs.{*, given}

case class DocumentLink(
  range: structures.Range,
  target: String,
  tooltip: String,
  data: aliases.LSPAny
)
object DocumentLink:
  object codecs:
    given upickle.default.Reader[DocumentLink] = Pickle.macroR
  export codecs.{*, given}

case class DocumentLinkRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  resolveProvider: Boolean
)
object DocumentLinkRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[DocumentLinkRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class DocumentFormattingParams(
  textDocument: structures.TextDocumentIdentifier,
  options: structures.FormattingOptions,
  workDoneToken: aliases.ProgressToken
)
object DocumentFormattingParams:
  object codecs:
    given upickle.default.Reader[DocumentFormattingParams] = Pickle.macroR
  export codecs.{*, given}

case class DocumentFormattingRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object DocumentFormattingRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[DocumentFormattingRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class DocumentRangeFormattingParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  options: structures.FormattingOptions,
  workDoneToken: aliases.ProgressToken
)
object DocumentRangeFormattingParams:
  object codecs:
    given upickle.default.Reader[DocumentRangeFormattingParams] = Pickle.macroR
  export codecs.{*, given}

case class DocumentRangeFormattingRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object DocumentRangeFormattingRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[DocumentRangeFormattingRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class DocumentOnTypeFormattingParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  ch: String,
  options: structures.FormattingOptions
)
object DocumentOnTypeFormattingParams:
  object codecs:
    given upickle.default.Reader[DocumentOnTypeFormattingParams] = Pickle.macroR
  export codecs.{*, given}

case class DocumentOnTypeFormattingRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  firstTriggerCharacter: String,
  moreTriggerCharacter: Vector[String]
)
object DocumentOnTypeFormattingRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[DocumentOnTypeFormattingRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class RenameParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  newName: String,
  workDoneToken: aliases.ProgressToken
)
object RenameParams:
  object codecs:
    given upickle.default.Reader[RenameParams] = Pickle.macroR
  export codecs.{*, given}

case class RenameRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  prepareProvider: Boolean
)
object RenameRegistrationOptions:
  object codecs:
    given rd0: Reader[(aliases.DocumentSelector | Null)] = {type T = (aliases.DocumentSelector | Null); badMerge(reader[aliases.DocumentSelector].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[RenameRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class PrepareRenameParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object PrepareRenameParams:
  object codecs:
    given upickle.default.Reader[PrepareRenameParams] = Pickle.macroR
  export codecs.{*, given}

case class ExecuteCommandParams(
  command: String,
  arguments: Vector[aliases.LSPAny],
  workDoneToken: aliases.ProgressToken
)
object ExecuteCommandParams:
  object codecs:
    given upickle.default.Reader[ExecuteCommandParams] = Pickle.macroR
  export codecs.{*, given}

case class ExecuteCommandRegistrationOptions(
  commands: Vector[String]
)
object ExecuteCommandRegistrationOptions:
  object codecs:
    given upickle.default.Reader[ExecuteCommandRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class ApplyWorkspaceEditParams(
  label: String,
  edit: structures.WorkspaceEdit
)
object ApplyWorkspaceEditParams:
  object codecs:
    given upickle.default.Reader[ApplyWorkspaceEditParams] = Pickle.macroR
  export codecs.{*, given}

case class ApplyWorkspaceEditResult(
  applied: Boolean,
  failureReason: String,
  failedChange: RuntimeBase.uinteger
)
object ApplyWorkspaceEditResult:
  object codecs:
    given upickle.default.Reader[ApplyWorkspaceEditResult] = Pickle.macroR
  export codecs.{*, given}

case class WorkDoneProgressBegin(
  kind: "begin",
  title: String,
  cancellable: Boolean,
  message: String,
  percentage: RuntimeBase.uinteger
)
object WorkDoneProgressBegin:
  object codecs:
    given upickle.default.Reader[WorkDoneProgressBegin] = Pickle.macroR
  export codecs.{*, given}

case class WorkDoneProgressReport(
  kind: "report",
  cancellable: Boolean,
  message: String,
  percentage: RuntimeBase.uinteger
)
object WorkDoneProgressReport:
  object codecs:
    given upickle.default.Reader[WorkDoneProgressReport] = Pickle.macroR
  export codecs.{*, given}

case class WorkDoneProgressEnd(
  kind: "end",
  message: String
)
object WorkDoneProgressEnd:
  object codecs:
    given upickle.default.Reader[WorkDoneProgressEnd] = Pickle.macroR
  export codecs.{*, given}

case class SetTraceParams(
  value: enumerations.TraceValues
)
object SetTraceParams:
  object codecs:
    given upickle.default.Reader[SetTraceParams] = Pickle.macroR
  export codecs.{*, given}

case class LogTraceParams(
  message: String,
  verbose: String
)
object LogTraceParams:
  object codecs:
    given upickle.default.Reader[LogTraceParams] = Pickle.macroR
  export codecs.{*, given}

case class CancelParams(
  id: (Int | String)
)
object CancelParams:
  object codecs:
    given rd0: Reader[(Int | String)] = {type T = (Int | String); badMerge(reader[Int].widen[T], reader[String].widen[T])}
    given upickle.default.Reader[CancelParams] = Pickle.macroR
  export codecs.{*, given}

case class ProgressParams(
  token: aliases.ProgressToken,
  value: aliases.LSPAny
)
object ProgressParams:
  object codecs:
    given upickle.default.Reader[ProgressParams] = Pickle.macroR
  export codecs.{*, given}

case class TextDocumentPositionParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position
)
object TextDocumentPositionParams:
  object codecs:
    given upickle.default.Reader[TextDocumentPositionParams] = Pickle.macroR
  export codecs.{*, given}

case class WorkDoneProgressParams(
  workDoneToken: aliases.ProgressToken
)
object WorkDoneProgressParams:
  object codecs:
    given upickle.default.Reader[WorkDoneProgressParams] = Pickle.macroR
  export codecs.{*, given}

case class LocationLink(
  originSelectionRange: structures.Range,
  targetUri: RuntimeBase.DocumentUri,
  targetRange: structures.Range,
  targetSelectionRange: structures.Range
)
object LocationLink:
  object codecs:
    given upickle.default.Reader[LocationLink] = Pickle.macroR
  export codecs.{*, given}

case class Range(
  start: structures.Position,
  end: structures.Position
)
object Range:
  object codecs:
    given upickle.default.Reader[Range] = Pickle.macroR
  export codecs.{*, given}

case class ImplementationOptions(
  workDoneProgress: Boolean
)
object ImplementationOptions:
  object codecs:
    given upickle.default.Reader[ImplementationOptions] = Pickle.macroR
  export codecs.{*, given}

case class StaticRegistrationOptions(
  id: String
)
object StaticRegistrationOptions:
  object codecs:
    given upickle.default.Reader[StaticRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}

case class TypeDefinitionOptions(
  workDoneProgress: Boolean
)
object TypeDefinitionOptions:
  object codecs:
    given upickle.default.Reader[TypeDefinitionOptions] = Pickle.macroR
  export codecs.{*, given}

case class WorkspaceFoldersChangeEvent(
  added: Vector[structures.WorkspaceFolder],
  removed: Vector[structures.WorkspaceFolder]
)
object WorkspaceFoldersChangeEvent:
  object codecs:
    given upickle.default.Reader[WorkspaceFoldersChangeEvent] = Pickle.macroR
  export codecs.{*, given}

case class ConfigurationItem(
  scopeUri: String,
  section: String
)
object ConfigurationItem:
  object codecs:
    given upickle.default.Reader[ConfigurationItem] = Pickle.macroR
  export codecs.{*, given}

case class TextDocumentIdentifier(
  uri: RuntimeBase.DocumentUri
)
object TextDocumentIdentifier:
  object codecs:
    given upickle.default.Reader[TextDocumentIdentifier] = Pickle.macroR
  export codecs.{*, given}

case class Color(
  red: Float,
  green: Float,
  blue: Float,
  alpha: Float
)
object Color:
  object codecs:
    given upickle.default.Reader[Color] = Pickle.macroR
  export codecs.{*, given}

case class DocumentColorOptions(
  workDoneProgress: Boolean
)
object DocumentColorOptions:
  object codecs:
    given upickle.default.Reader[DocumentColorOptions] = Pickle.macroR
  export codecs.{*, given}

case class FoldingRangeOptions(
  workDoneProgress: Boolean
)
object FoldingRangeOptions:
  object codecs:
    given upickle.default.Reader[FoldingRangeOptions] = Pickle.macroR
  export codecs.{*, given}

case class DeclarationOptions(
  workDoneProgress: Boolean
)
object DeclarationOptions:
  object codecs:
    given upickle.default.Reader[DeclarationOptions] = Pickle.macroR
  export codecs.{*, given}

case class Position(
  line: RuntimeBase.uinteger,
  character: RuntimeBase.uinteger
)
object Position:
  object codecs:
    given upickle.default.Reader[Position] = Pickle.macroR
  export codecs.{*, given}

case class SelectionRangeOptions(
  workDoneProgress: Boolean
)
object SelectionRangeOptions:
  object codecs:
    given upickle.default.Reader[SelectionRangeOptions] = Pickle.macroR
  export codecs.{*, given}

case class CallHierarchyOptions(
  workDoneProgress: Boolean
)
object CallHierarchyOptions:
  object codecs:
    given upickle.default.Reader[CallHierarchyOptions] = Pickle.macroR
  export codecs.{*, given}

case class SemanticTokensOptions(
  legend: structures.SemanticTokensLegend,
  range: (Boolean | SemanticTokensOptions.S0),
  full: (Boolean | SemanticTokensOptions.S1),
  workDoneProgress: Boolean
)
object SemanticTokensOptions:
  object codecs:
    given rd0: Reader[(Boolean | SemanticTokensOptions.S0)] = {type T = (Boolean | SemanticTokensOptions.S0); badMerge(reader[Boolean].widen[T], reader[SemanticTokensOptions.S0].widen[T])}
    given rd1: Reader[(Boolean | SemanticTokensOptions.S1)] = {type T = (Boolean | SemanticTokensOptions.S1); badMerge(reader[Boolean].widen[T], reader[SemanticTokensOptions.S1].widen[T])}
    given upickle.default.Reader[SemanticTokensOptions] = Pickle.macroR
  export codecs.{*, given}
  case class S0(
  )
  object S0:
    object codecs:
      given upickle.default.Reader[S0] = Pickle.macroR
    export codecs.{*, given}
  case class S1(
    delta: Boolean
  )
  object S1:
    object codecs:
      given upickle.default.Reader[S1] = Pickle.macroR
    export codecs.{*, given}

case class SemanticTokensEdit(
  start: RuntimeBase.uinteger,
  deleteCount: RuntimeBase.uinteger,
  data: Vector[RuntimeBase.uinteger]
)
object SemanticTokensEdit:
  object codecs:
    given upickle.default.Reader[SemanticTokensEdit] = Pickle.macroR
  export codecs.{*, given}

case class LinkedEditingRangeOptions(
  workDoneProgress: Boolean
)
object LinkedEditingRangeOptions:
  object codecs:
    given upickle.default.Reader[LinkedEditingRangeOptions] = Pickle.macroR
  export codecs.{*, given}

case class FileCreate(
  uri: String
)
object FileCreate:
  object codecs:
    given upickle.default.Reader[FileCreate] = Pickle.macroR
  export codecs.{*, given}

case class TextDocumentEdit(
  textDocument: structures.OptionalVersionedTextDocumentIdentifier,
  edits: Vector[(structures.TextEdit | structures.AnnotatedTextEdit)]
)
object TextDocumentEdit:
  object codecs:
    given rd0: Reader[(structures.TextEdit | structures.AnnotatedTextEdit)] = {type T = (structures.TextEdit | structures.AnnotatedTextEdit); badMerge(reader[structures.TextEdit].widen[T], reader[structures.AnnotatedTextEdit].widen[T])}
    given upickle.default.Reader[TextDocumentEdit] = Pickle.macroR
  export codecs.{*, given}

case class CreateFile(
  kind: "create",
  uri: RuntimeBase.DocumentUri,
  options: structures.CreateFileOptions,
  annotationId: aliases.ChangeAnnotationIdentifier
)
object CreateFile:
  object codecs:
    given upickle.default.Reader[CreateFile] = Pickle.macroR
  export codecs.{*, given}

case class RenameFile(
  kind: "rename",
  oldUri: RuntimeBase.DocumentUri,
  newUri: RuntimeBase.DocumentUri,
  options: structures.RenameFileOptions,
  annotationId: aliases.ChangeAnnotationIdentifier
)
object RenameFile:
  object codecs:
    given upickle.default.Reader[RenameFile] = Pickle.macroR
  export codecs.{*, given}

case class DeleteFile(
  kind: "delete",
  uri: RuntimeBase.DocumentUri,
  options: structures.DeleteFileOptions,
  annotationId: aliases.ChangeAnnotationIdentifier
)
object DeleteFile:
  object codecs:
    given upickle.default.Reader[DeleteFile] = Pickle.macroR
  export codecs.{*, given}

case class ChangeAnnotation(
  label: String,
  needsConfirmation: Boolean,
  description: String
)
object ChangeAnnotation:
  object codecs:
    given upickle.default.Reader[ChangeAnnotation] = Pickle.macroR
  export codecs.{*, given}

case class FileOperationFilter(
  scheme: String,
  pattern: structures.FileOperationPattern
)
object FileOperationFilter:
  object codecs:
    given upickle.default.Reader[FileOperationFilter] = Pickle.macroR
  export codecs.{*, given}

case class FileRename(
  oldUri: String,
  newUri: String
)
object FileRename:
  object codecs:
    given upickle.default.Reader[FileRename] = Pickle.macroR
  export codecs.{*, given}

case class FileDelete(
  uri: String
)
object FileDelete:
  object codecs:
    given upickle.default.Reader[FileDelete] = Pickle.macroR
  export codecs.{*, given}

case class MonikerOptions(
  workDoneProgress: Boolean
)
object MonikerOptions:
  object codecs:
    given upickle.default.Reader[MonikerOptions] = Pickle.macroR
  export codecs.{*, given}

case class TypeHierarchyOptions(
  workDoneProgress: Boolean
)
object TypeHierarchyOptions:
  object codecs:
    given upickle.default.Reader[TypeHierarchyOptions] = Pickle.macroR
  export codecs.{*, given}

case class InlineValueContext(
  frameId: Int,
  stoppedLocation: structures.Range
)
object InlineValueContext:
  object codecs:
    given upickle.default.Reader[InlineValueContext] = Pickle.macroR
  export codecs.{*, given}

case class InlineValueText(
  range: structures.Range,
  text: String
)
object InlineValueText:
  object codecs:
    given upickle.default.Reader[InlineValueText] = Pickle.macroR
  export codecs.{*, given}

case class InlineValueVariableLookup(
  range: structures.Range,
  variableName: String,
  caseSensitiveLookup: Boolean
)
object InlineValueVariableLookup:
  object codecs:
    given upickle.default.Reader[InlineValueVariableLookup] = Pickle.macroR
  export codecs.{*, given}

case class InlineValueEvaluatableExpression(
  range: structures.Range,
  expression: String
)
object InlineValueEvaluatableExpression:
  object codecs:
    given upickle.default.Reader[InlineValueEvaluatableExpression] = Pickle.macroR
  export codecs.{*, given}

case class InlineValueOptions(
  workDoneProgress: Boolean
)
object InlineValueOptions:
  object codecs:
    given upickle.default.Reader[InlineValueOptions] = Pickle.macroR
  export codecs.{*, given}

case class InlayHintLabelPart(
  value: String,
  tooltip: (String | structures.MarkupContent),
  location: structures.Location,
  command: structures.Command
)
object InlayHintLabelPart:
  object codecs:
    given rd0: Reader[(String | structures.MarkupContent)] = {type T = (String | structures.MarkupContent); badMerge(reader[String].widen[T], reader[structures.MarkupContent].widen[T])}
    given upickle.default.Reader[InlayHintLabelPart] = Pickle.macroR
  export codecs.{*, given}

case class MarkupContent(
  kind: enumerations.MarkupKind,
  value: String
)
object MarkupContent:
  object codecs:
    given upickle.default.Reader[MarkupContent] = Pickle.macroR
  export codecs.{*, given}

case class InlayHintOptions(
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object InlayHintOptions:
  object codecs:
    given upickle.default.Reader[InlayHintOptions] = Pickle.macroR
  export codecs.{*, given}

case class RelatedFullDocumentDiagnosticReport(
  relatedDocuments: Map[RuntimeBase.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)],
  kind: "full",
  resultId: String,
  items: Vector[structures.Diagnostic]
)
object RelatedFullDocumentDiagnosticReport:
  object codecs:
    given rd0: Reader[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = {type T = (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport); badMerge(reader[structures.FullDocumentDiagnosticReport].widen[T], reader[structures.UnchangedDocumentDiagnosticReport].widen[T])}
    given upickle.default.Reader[RelatedFullDocumentDiagnosticReport] = Pickle.macroR
  export codecs.{*, given}

case class RelatedUnchangedDocumentDiagnosticReport(
  relatedDocuments: Map[RuntimeBase.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)],
  kind: "unchanged",
  resultId: String
)
object RelatedUnchangedDocumentDiagnosticReport:
  object codecs:
    given rd0: Reader[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = {type T = (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport); badMerge(reader[structures.FullDocumentDiagnosticReport].widen[T], reader[structures.UnchangedDocumentDiagnosticReport].widen[T])}
    given upickle.default.Reader[RelatedUnchangedDocumentDiagnosticReport] = Pickle.macroR
  export codecs.{*, given}

case class FullDocumentDiagnosticReport(
  kind: "full",
  resultId: String,
  items: Vector[structures.Diagnostic]
)
object FullDocumentDiagnosticReport:
  object codecs:
    given upickle.default.Reader[FullDocumentDiagnosticReport] = Pickle.macroR
  export codecs.{*, given}

case class UnchangedDocumentDiagnosticReport(
  kind: "unchanged",
  resultId: String
)
object UnchangedDocumentDiagnosticReport:
  object codecs:
    given upickle.default.Reader[UnchangedDocumentDiagnosticReport] = Pickle.macroR
  export codecs.{*, given}

case class DiagnosticOptions(
  identifier: String,
  interFileDependencies: Boolean,
  workspaceDiagnostics: Boolean,
  workDoneProgress: Boolean
)
object DiagnosticOptions:
  object codecs:
    given upickle.default.Reader[DiagnosticOptions] = Pickle.macroR
  export codecs.{*, given}

case class PreviousResultId(
  uri: RuntimeBase.DocumentUri,
  value: String
)
object PreviousResultId:
  object codecs:
    given upickle.default.Reader[PreviousResultId] = Pickle.macroR
  export codecs.{*, given}

case class NotebookDocument(
  uri: aliases.URI,
  notebookType: String,
  version: Int,
  metadata: structures.LSPObject,
  cells: Vector[structures.NotebookCell]
)
object NotebookDocument:
  object codecs:
    given upickle.default.Reader[NotebookDocument] = Pickle.macroR
  export codecs.{*, given}

case class TextDocumentItem(
  uri: RuntimeBase.DocumentUri,
  languageId: String,
  version: Int,
  text: String
)
object TextDocumentItem:
  object codecs:
    given upickle.default.Reader[TextDocumentItem] = Pickle.macroR
  export codecs.{*, given}

case class VersionedNotebookDocumentIdentifier(
  version: Int,
  uri: aliases.URI
)
object VersionedNotebookDocumentIdentifier:
  object codecs:
    given upickle.default.Reader[VersionedNotebookDocumentIdentifier] = Pickle.macroR
  export codecs.{*, given}

case class NotebookDocumentChangeEvent(
  metadata: structures.LSPObject,
  cells: NotebookDocumentChangeEvent.Cells
)
object NotebookDocumentChangeEvent:
  object codecs:
    given upickle.default.Reader[NotebookDocumentChangeEvent] = Pickle.macroR
  export codecs.{*, given}
  case class Cells(
    structure: Cells.Structure,
    data: Vector[structures.NotebookCell],
    textContent: Vector[Cells.S0]
  )
  object Cells:
    object codecs:
      given upickle.default.Reader[Cells] = Pickle.macroR
    export codecs.{*, given}
    case class Structure(
      array: structures.NotebookCellArrayChange,
      didOpen: Vector[structures.TextDocumentItem],
      didClose: Vector[structures.TextDocumentIdentifier]
    )
    object Structure:
      object codecs:
        given upickle.default.Reader[Structure] = Pickle.macroR
      export codecs.{*, given}
    case class S0(
      document: structures.VersionedTextDocumentIdentifier,
      changes: Vector[aliases.TextDocumentContentChangeEvent]
    )
    object S0:
      object codecs:
        given upickle.default.Reader[S0] = Pickle.macroR
      export codecs.{*, given}

case class NotebookDocumentIdentifier(
  uri: aliases.URI
)
object NotebookDocumentIdentifier:
  object codecs:
    given upickle.default.Reader[NotebookDocumentIdentifier] = Pickle.macroR
  export codecs.{*, given}

case class Registration(
  id: String,
  method: String,
  registerOptions: aliases.LSPAny
)
object Registration:
  object codecs:
    given upickle.default.Reader[Registration] = Pickle.macroR
  export codecs.{*, given}

case class Unregistration(
  id: String,
  method: String
)
object Unregistration:
  object codecs:
    given upickle.default.Reader[Unregistration] = Pickle.macroR
  export codecs.{*, given}

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
  object codecs:
    given rd0: Reader[(Int | Null)] = {type T = (Int | Null); badMerge(reader[Int].widen[T], nullReadWriter.widen[T])}
    given rd1: Reader[(String | Null)] = {type T = (String | Null); badMerge(reader[String].widen[T], nullReadWriter.widen[T])}
    given rd2: Reader[(RuntimeBase.DocumentUri | Null)] = {type T = (RuntimeBase.DocumentUri | Null); badMerge(reader[RuntimeBase.DocumentUri].widen[T], nullReadWriter.widen[T])}
    given rd3: Reader[("off" | "messages" | "compact" | "verbose")] = {type T = ("off" | "messages" | "compact" | "verbose"); badMerge(reader["off"].widen[T], reader["messages"].widen[T], reader["compact"].widen[T], reader["verbose"].widen[T])}
    given upickle.default.Reader[_InitializeParams] = Pickle.macroR
  export codecs.{*, given}
  case class ClientInfo(
    name: String,
    version: String
  )
  object ClientInfo:
    object codecs:
      given upickle.default.Reader[ClientInfo] = Pickle.macroR
    export codecs.{*, given}

case class WorkspaceFoldersInitializeParams(
  workspaceFolders: (Vector[structures.WorkspaceFolder] | Null)
)
object WorkspaceFoldersInitializeParams:
  object codecs:
    given rd0: Reader[(Vector[structures.WorkspaceFolder] | Null)] = {type T = (Vector[structures.WorkspaceFolder] | Null); badMerge(reader[Vector[structures.WorkspaceFolder]].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[WorkspaceFoldersInitializeParams] = Pickle.macroR
  export codecs.{*, given}

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
  object codecs:
    given rd0: Reader[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)] = {type T = (structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind); badMerge(reader[structures.TextDocumentSyncOptions].widen[T], reader[enumerations.TextDocumentSyncKind].widen[T])}
    given rd1: Reader[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)] = {type T = (structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions); badMerge(reader[structures.NotebookDocumentSyncOptions].widen[T], reader[structures.NotebookDocumentSyncRegistrationOptions].widen[T])}
    given rd2: Reader[(Boolean | structures.HoverOptions)] = {type T = (Boolean | structures.HoverOptions); badMerge(reader[Boolean].widen[T], reader[structures.HoverOptions].widen[T])}
    given rd3: Reader[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)] = {type T = (Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions); badMerge(reader[Boolean].widen[T], reader[structures.DeclarationOptions].widen[T], reader[structures.DeclarationRegistrationOptions].widen[T])}
    given rd4: Reader[(Boolean | structures.DefinitionOptions)] = {type T = (Boolean | structures.DefinitionOptions); badMerge(reader[Boolean].widen[T], reader[structures.DefinitionOptions].widen[T])}
    given rd5: Reader[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)] = {type T = (Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions); badMerge(reader[Boolean].widen[T], reader[structures.TypeDefinitionOptions].widen[T], reader[structures.TypeDefinitionRegistrationOptions].widen[T])}
    given rd6: Reader[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)] = {type T = (Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions); badMerge(reader[Boolean].widen[T], reader[structures.ImplementationOptions].widen[T], reader[structures.ImplementationRegistrationOptions].widen[T])}
    given rd7: Reader[(Boolean | structures.ReferenceOptions)] = {type T = (Boolean | structures.ReferenceOptions); badMerge(reader[Boolean].widen[T], reader[structures.ReferenceOptions].widen[T])}
    given rd8: Reader[(Boolean | structures.DocumentHighlightOptions)] = {type T = (Boolean | structures.DocumentHighlightOptions); badMerge(reader[Boolean].widen[T], reader[structures.DocumentHighlightOptions].widen[T])}
    given rd9: Reader[(Boolean | structures.DocumentSymbolOptions)] = {type T = (Boolean | structures.DocumentSymbolOptions); badMerge(reader[Boolean].widen[T], reader[structures.DocumentSymbolOptions].widen[T])}
    given rd10: Reader[(Boolean | structures.CodeActionOptions)] = {type T = (Boolean | structures.CodeActionOptions); badMerge(reader[Boolean].widen[T], reader[structures.CodeActionOptions].widen[T])}
    given rd11: Reader[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)] = {type T = (Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions); badMerge(reader[Boolean].widen[T], reader[structures.DocumentColorOptions].widen[T], reader[structures.DocumentColorRegistrationOptions].widen[T])}
    given rd12: Reader[(Boolean | structures.WorkspaceSymbolOptions)] = {type T = (Boolean | structures.WorkspaceSymbolOptions); badMerge(reader[Boolean].widen[T], reader[structures.WorkspaceSymbolOptions].widen[T])}
    given rd13: Reader[(Boolean | structures.DocumentFormattingOptions)] = {type T = (Boolean | structures.DocumentFormattingOptions); badMerge(reader[Boolean].widen[T], reader[structures.DocumentFormattingOptions].widen[T])}
    given rd14: Reader[(Boolean | structures.DocumentRangeFormattingOptions)] = {type T = (Boolean | structures.DocumentRangeFormattingOptions); badMerge(reader[Boolean].widen[T], reader[structures.DocumentRangeFormattingOptions].widen[T])}
    given rd15: Reader[(Boolean | structures.RenameOptions)] = {type T = (Boolean | structures.RenameOptions); badMerge(reader[Boolean].widen[T], reader[structures.RenameOptions].widen[T])}
    given rd16: Reader[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)] = {type T = (Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions); badMerge(reader[Boolean].widen[T], reader[structures.FoldingRangeOptions].widen[T], reader[structures.FoldingRangeRegistrationOptions].widen[T])}
    given rd17: Reader[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)] = {type T = (Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions); badMerge(reader[Boolean].widen[T], reader[structures.SelectionRangeOptions].widen[T], reader[structures.SelectionRangeRegistrationOptions].widen[T])}
    given rd18: Reader[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)] = {type T = (Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions); badMerge(reader[Boolean].widen[T], reader[structures.CallHierarchyOptions].widen[T], reader[structures.CallHierarchyRegistrationOptions].widen[T])}
    given rd19: Reader[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)] = {type T = (Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions); badMerge(reader[Boolean].widen[T], reader[structures.LinkedEditingRangeOptions].widen[T], reader[structures.LinkedEditingRangeRegistrationOptions].widen[T])}
    given rd20: Reader[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)] = {type T = (structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions); badMerge(reader[structures.SemanticTokensOptions].widen[T], reader[structures.SemanticTokensRegistrationOptions].widen[T])}
    given rd21: Reader[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)] = {type T = (Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions); badMerge(reader[Boolean].widen[T], reader[structures.MonikerOptions].widen[T], reader[structures.MonikerRegistrationOptions].widen[T])}
    given rd22: Reader[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)] = {type T = (Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions); badMerge(reader[Boolean].widen[T], reader[structures.TypeHierarchyOptions].widen[T], reader[structures.TypeHierarchyRegistrationOptions].widen[T])}
    given rd23: Reader[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)] = {type T = (Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions); badMerge(reader[Boolean].widen[T], reader[structures.InlineValueOptions].widen[T], reader[structures.InlineValueRegistrationOptions].widen[T])}
    given rd24: Reader[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)] = {type T = (Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions); badMerge(reader[Boolean].widen[T], reader[structures.InlayHintOptions].widen[T], reader[structures.InlayHintRegistrationOptions].widen[T])}
    given rd25: Reader[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)] = {type T = (structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions); badMerge(reader[structures.DiagnosticOptions].widen[T], reader[structures.DiagnosticRegistrationOptions].widen[T])}
    given upickle.default.Reader[ServerCapabilities] = Pickle.macroR
  export codecs.{*, given}
  case class Workspace(
    workspaceFolders: structures.WorkspaceFoldersServerCapabilities,
    fileOperations: structures.FileOperationOptions
  )
  object Workspace:
    object codecs:
      given upickle.default.Reader[Workspace] = Pickle.macroR
    export codecs.{*, given}

case class VersionedTextDocumentIdentifier(
  version: Int,
  uri: RuntimeBase.DocumentUri
)
object VersionedTextDocumentIdentifier:
  object codecs:
    given upickle.default.Reader[VersionedTextDocumentIdentifier] = Pickle.macroR
  export codecs.{*, given}

case class SaveOptions(
  includeText: Boolean
)
object SaveOptions:
  object codecs:
    given upickle.default.Reader[SaveOptions] = Pickle.macroR
  export codecs.{*, given}

case class FileEvent(
  uri: RuntimeBase.DocumentUri,
  `type`: enumerations.FileChangeType
)
object FileEvent:
  object codecs:
    given upickle.default.Reader[FileEvent] = Pickle.macroR
  export codecs.{*, given}

case class FileSystemWatcher(
  globPattern: aliases.GlobPattern,
  kind: enumerations.WatchKind
)
object FileSystemWatcher:
  object codecs:
    given upickle.default.Reader[FileSystemWatcher] = Pickle.macroR
  export codecs.{*, given}

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
  object codecs:
    given rd0: Reader[(Int | String)] = {type T = (Int | String); badMerge(reader[Int].widen[T], reader[String].widen[T])}
    given upickle.default.Reader[Diagnostic] = Pickle.macroR
  export codecs.{*, given}

case class CompletionContext(
  triggerKind: enumerations.CompletionTriggerKind,
  triggerCharacter: String
)
object CompletionContext:
  object codecs:
    given upickle.default.Reader[CompletionContext] = Pickle.macroR
  export codecs.{*, given}

case class CompletionItemLabelDetails(
  detail: String,
  description: String
)
object CompletionItemLabelDetails:
  object codecs:
    given upickle.default.Reader[CompletionItemLabelDetails] = Pickle.macroR
  export codecs.{*, given}

case class InsertReplaceEdit(
  newText: String,
  insert: structures.Range,
  replace: structures.Range
)
object InsertReplaceEdit:
  object codecs:
    given upickle.default.Reader[InsertReplaceEdit] = Pickle.macroR
  export codecs.{*, given}

case class CompletionOptions(
  triggerCharacters: Vector[String],
  allCommitCharacters: Vector[String],
  resolveProvider: Boolean,
  completionItem: CompletionOptions.CompletionItem,
  workDoneProgress: Boolean
)
object CompletionOptions:
  object codecs:
    given upickle.default.Reader[CompletionOptions] = Pickle.macroR
  export codecs.{*, given}
  case class CompletionItem(
    labelDetailsSupport: Boolean
  )
  object CompletionItem:
    object codecs:
      given upickle.default.Reader[CompletionItem] = Pickle.macroR
    export codecs.{*, given}

case class HoverOptions(
  workDoneProgress: Boolean
)
object HoverOptions:
  object codecs:
    given upickle.default.Reader[HoverOptions] = Pickle.macroR
  export codecs.{*, given}

case class SignatureHelpContext(
  triggerKind: enumerations.SignatureHelpTriggerKind,
  triggerCharacter: String,
  isRetrigger: Boolean,
  activeSignatureHelp: structures.SignatureHelp
)
object SignatureHelpContext:
  object codecs:
    given upickle.default.Reader[SignatureHelpContext] = Pickle.macroR
  export codecs.{*, given}

case class SignatureInformation(
  label: String,
  documentation: (String | structures.MarkupContent),
  parameters: Vector[structures.ParameterInformation],
  activeParameter: RuntimeBase.uinteger
)
object SignatureInformation:
  object codecs:
    given rd0: Reader[(String | structures.MarkupContent)] = {type T = (String | structures.MarkupContent); badMerge(reader[String].widen[T], reader[structures.MarkupContent].widen[T])}
    given upickle.default.Reader[SignatureInformation] = Pickle.macroR
  export codecs.{*, given}

case class SignatureHelpOptions(
  triggerCharacters: Vector[String],
  retriggerCharacters: Vector[String],
  workDoneProgress: Boolean
)
object SignatureHelpOptions:
  object codecs:
    given upickle.default.Reader[SignatureHelpOptions] = Pickle.macroR
  export codecs.{*, given}

case class DefinitionOptions(
  workDoneProgress: Boolean
)
object DefinitionOptions:
  object codecs:
    given upickle.default.Reader[DefinitionOptions] = Pickle.macroR
  export codecs.{*, given}

case class ReferenceContext(
  includeDeclaration: Boolean
)
object ReferenceContext:
  object codecs:
    given upickle.default.Reader[ReferenceContext] = Pickle.macroR
  export codecs.{*, given}

case class ReferenceOptions(
  workDoneProgress: Boolean
)
object ReferenceOptions:
  object codecs:
    given upickle.default.Reader[ReferenceOptions] = Pickle.macroR
  export codecs.{*, given}

case class DocumentHighlightOptions(
  workDoneProgress: Boolean
)
object DocumentHighlightOptions:
  object codecs:
    given upickle.default.Reader[DocumentHighlightOptions] = Pickle.macroR
  export codecs.{*, given}

case class BaseSymbolInformation(
  name: String,
  kind: enumerations.SymbolKind,
  tags: Vector[enumerations.SymbolTag],
  containerName: String
)
object BaseSymbolInformation:
  object codecs:
    given upickle.default.Reader[BaseSymbolInformation] = Pickle.macroR
  export codecs.{*, given}

case class DocumentSymbolOptions(
  label: String,
  workDoneProgress: Boolean
)
object DocumentSymbolOptions:
  object codecs:
    given upickle.default.Reader[DocumentSymbolOptions] = Pickle.macroR
  export codecs.{*, given}

case class CodeActionContext(
  diagnostics: Vector[structures.Diagnostic],
  only: Vector[enumerations.CodeActionKind],
  triggerKind: enumerations.CodeActionTriggerKind
)
object CodeActionContext:
  object codecs:
    given upickle.default.Reader[CodeActionContext] = Pickle.macroR
  export codecs.{*, given}

case class CodeActionOptions(
  codeActionKinds: Vector[enumerations.CodeActionKind],
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object CodeActionOptions:
  object codecs:
    given upickle.default.Reader[CodeActionOptions] = Pickle.macroR
  export codecs.{*, given}

case class WorkspaceSymbolOptions(
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object WorkspaceSymbolOptions:
  object codecs:
    given upickle.default.Reader[WorkspaceSymbolOptions] = Pickle.macroR
  export codecs.{*, given}

case class CodeLensOptions(
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object CodeLensOptions:
  object codecs:
    given upickle.default.Reader[CodeLensOptions] = Pickle.macroR
  export codecs.{*, given}

case class DocumentLinkOptions(
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object DocumentLinkOptions:
  object codecs:
    given upickle.default.Reader[DocumentLinkOptions] = Pickle.macroR
  export codecs.{*, given}

case class FormattingOptions(
  tabSize: RuntimeBase.uinteger,
  insertSpaces: Boolean,
  trimTrailingWhitespace: Boolean,
  insertFinalNewline: Boolean,
  trimFinalNewlines: Boolean
)
object FormattingOptions:
  object codecs:
    given upickle.default.Reader[FormattingOptions] = Pickle.macroR
  export codecs.{*, given}

case class DocumentFormattingOptions(
  workDoneProgress: Boolean
)
object DocumentFormattingOptions:
  object codecs:
    given upickle.default.Reader[DocumentFormattingOptions] = Pickle.macroR
  export codecs.{*, given}

case class DocumentRangeFormattingOptions(
  workDoneProgress: Boolean
)
object DocumentRangeFormattingOptions:
  object codecs:
    given upickle.default.Reader[DocumentRangeFormattingOptions] = Pickle.macroR
  export codecs.{*, given}

case class DocumentOnTypeFormattingOptions(
  firstTriggerCharacter: String,
  moreTriggerCharacter: Vector[String]
)
object DocumentOnTypeFormattingOptions:
  object codecs:
    given upickle.default.Reader[DocumentOnTypeFormattingOptions] = Pickle.macroR
  export codecs.{*, given}

case class RenameOptions(
  prepareProvider: Boolean,
  workDoneProgress: Boolean
)
object RenameOptions:
  object codecs:
    given upickle.default.Reader[RenameOptions] = Pickle.macroR
  export codecs.{*, given}

case class ExecuteCommandOptions(
  commands: Vector[String],
  workDoneProgress: Boolean
)
object ExecuteCommandOptions:
  object codecs:
    given upickle.default.Reader[ExecuteCommandOptions] = Pickle.macroR
  export codecs.{*, given}

case class SemanticTokensLegend(
  tokenTypes: Vector[String],
  tokenModifiers: Vector[String]
)
object SemanticTokensLegend:
  object codecs:
    given upickle.default.Reader[SemanticTokensLegend] = Pickle.macroR
  export codecs.{*, given}

case class OptionalVersionedTextDocumentIdentifier(
  version: (Int | Null),
  uri: RuntimeBase.DocumentUri
)
object OptionalVersionedTextDocumentIdentifier:
  object codecs:
    given rd0: Reader[(Int | Null)] = {type T = (Int | Null); badMerge(reader[Int].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[OptionalVersionedTextDocumentIdentifier] = Pickle.macroR
  export codecs.{*, given}

case class AnnotatedTextEdit(
  annotationId: aliases.ChangeAnnotationIdentifier,
  range: structures.Range,
  newText: String
)
object AnnotatedTextEdit:
  object codecs:
    given upickle.default.Reader[AnnotatedTextEdit] = Pickle.macroR
  export codecs.{*, given}

case class ResourceOperation(
  kind: String,
  annotationId: aliases.ChangeAnnotationIdentifier
)
object ResourceOperation:
  object codecs:
    given upickle.default.Reader[ResourceOperation] = Pickle.macroR
  export codecs.{*, given}

case class CreateFileOptions(
  overwrite: Boolean,
  ignoreIfExists: Boolean
)
object CreateFileOptions:
  object codecs:
    given upickle.default.Reader[CreateFileOptions] = Pickle.macroR
  export codecs.{*, given}

case class RenameFileOptions(
  overwrite: Boolean,
  ignoreIfExists: Boolean
)
object RenameFileOptions:
  object codecs:
    given upickle.default.Reader[RenameFileOptions] = Pickle.macroR
  export codecs.{*, given}

case class DeleteFileOptions(
  recursive: Boolean,
  ignoreIfNotExists: Boolean
)
object DeleteFileOptions:
  object codecs:
    given upickle.default.Reader[DeleteFileOptions] = Pickle.macroR
  export codecs.{*, given}

case class FileOperationPattern(
  glob: String,
  matches: enumerations.FileOperationPatternKind,
  options: structures.FileOperationPatternOptions
)
object FileOperationPattern:
  object codecs:
    given upickle.default.Reader[FileOperationPattern] = Pickle.macroR
  export codecs.{*, given}

case class WorkspaceFullDocumentDiagnosticReport(
  uri: RuntimeBase.DocumentUri,
  version: (Int | Null),
  kind: "full",
  resultId: String,
  items: Vector[structures.Diagnostic]
)
object WorkspaceFullDocumentDiagnosticReport:
  object codecs:
    given rd0: Reader[(Int | Null)] = {type T = (Int | Null); badMerge(reader[Int].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[WorkspaceFullDocumentDiagnosticReport] = Pickle.macroR
  export codecs.{*, given}

case class WorkspaceUnchangedDocumentDiagnosticReport(
  uri: RuntimeBase.DocumentUri,
  version: (Int | Null),
  kind: "unchanged",
  resultId: String
)
object WorkspaceUnchangedDocumentDiagnosticReport:
  object codecs:
    given rd0: Reader[(Int | Null)] = {type T = (Int | Null); badMerge(reader[Int].widen[T], nullReadWriter.widen[T])}
    given upickle.default.Reader[WorkspaceUnchangedDocumentDiagnosticReport] = Pickle.macroR
  export codecs.{*, given}

case class LSPObject(
)
object LSPObject:
  object codecs:
    given upickle.default.Reader[LSPObject] = Pickle.macroR
  export codecs.{*, given}

case class NotebookCell(
  kind: enumerations.NotebookCellKind,
  document: RuntimeBase.DocumentUri,
  metadata: structures.LSPObject,
  executionSummary: structures.ExecutionSummary
)
object NotebookCell:
  object codecs:
    given upickle.default.Reader[NotebookCell] = Pickle.macroR
  export codecs.{*, given}

case class NotebookCellArrayChange(
  start: RuntimeBase.uinteger,
  deleteCount: RuntimeBase.uinteger,
  cells: Vector[structures.NotebookCell]
)
object NotebookCellArrayChange:
  object codecs:
    given upickle.default.Reader[NotebookCellArrayChange] = Pickle.macroR
  export codecs.{*, given}

case class ClientCapabilities(
  workspace: structures.WorkspaceClientCapabilities,
  textDocument: structures.TextDocumentClientCapabilities,
  notebookDocument: structures.NotebookDocumentClientCapabilities,
  window: structures.WindowClientCapabilities,
  general: structures.GeneralClientCapabilities,
  experimental: aliases.LSPAny
)
object ClientCapabilities:
  object codecs:
    given upickle.default.Reader[ClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class TextDocumentSyncOptions(
  openClose: Boolean,
  change: enumerations.TextDocumentSyncKind,
  willSave: Boolean,
  willSaveWaitUntil: Boolean,
  save: (Boolean | structures.SaveOptions)
)
object TextDocumentSyncOptions:
  object codecs:
    given rd0: Reader[(Boolean | structures.SaveOptions)] = {type T = (Boolean | structures.SaveOptions); badMerge(reader[Boolean].widen[T], reader[structures.SaveOptions].widen[T])}
    given upickle.default.Reader[TextDocumentSyncOptions] = Pickle.macroR
  export codecs.{*, given}

case class NotebookDocumentSyncOptions(
  notebookSelector: Vector[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)],
  save: Boolean
)
object NotebookDocumentSyncOptions:
  object codecs:
    given rd0: Reader[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)] = {type T = (NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1); badMerge(reader[NotebookDocumentSyncOptions.S0].widen[T], reader[NotebookDocumentSyncOptions.S1].widen[T])}
    given upickle.default.Reader[NotebookDocumentSyncOptions] = Pickle.macroR
  export codecs.{*, given}
  case class S0(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Vector[S0.S0]
  )
  object S0:
    object codecs:
      given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = {type T = (String | aliases.NotebookDocumentFilter); badMerge(reader[String].widen[T], reader[aliases.NotebookDocumentFilter].widen[T])}
      given upickle.default.Reader[NotebookDocumentSyncOptions.S0] = Pickle.macroR
    export codecs.{*, given}
    case class S0(
      language: String
    )
    object S0:
      object codecs:
        given upickle.default.Reader[S0] = Pickle.macroR
      export codecs.{*, given}
  case class S1(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Vector[S1.S0]
  )
  object S1:
    object codecs:
      given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = {type T = (String | aliases.NotebookDocumentFilter); badMerge(reader[String].widen[T], reader[aliases.NotebookDocumentFilter].widen[T])}
      given upickle.default.Reader[NotebookDocumentSyncOptions.S1] = Pickle.macroR
    export codecs.{*, given}
    case class S0(
      language: String
    )
    object S0:
      object codecs:
        given upickle.default.Reader[S0] = Pickle.macroR
      export codecs.{*, given}

case class NotebookDocumentSyncRegistrationOptions(
  notebookSelector: Vector[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)],
  save: Boolean,
  id: String
)
object NotebookDocumentSyncRegistrationOptions:
  object codecs:
    given rd0: Reader[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)] = {type T = (NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1); badMerge(reader[NotebookDocumentSyncRegistrationOptions.S0].widen[T], reader[NotebookDocumentSyncRegistrationOptions.S1].widen[T])}
    given upickle.default.Reader[NotebookDocumentSyncRegistrationOptions] = Pickle.macroR
  export codecs.{*, given}
  case class S0(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Vector[S0.S0]
  )
  object S0:
    object codecs:
      given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = {type T = (String | aliases.NotebookDocumentFilter); badMerge(reader[String].widen[T], reader[aliases.NotebookDocumentFilter].widen[T])}
      given upickle.default.Reader[NotebookDocumentSyncRegistrationOptions.S0] = Pickle.macroR
    export codecs.{*, given}
    case class S0(
      language: String
    )
    object S0:
      object codecs:
        given upickle.default.Reader[S0] = Pickle.macroR
      export codecs.{*, given}
  case class S1(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Vector[S1.S0]
  )
  object S1:
    object codecs:
      given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = {type T = (String | aliases.NotebookDocumentFilter); badMerge(reader[String].widen[T], reader[aliases.NotebookDocumentFilter].widen[T])}
      given upickle.default.Reader[NotebookDocumentSyncRegistrationOptions.S1] = Pickle.macroR
    export codecs.{*, given}
    case class S0(
      language: String
    )
    object S0:
      object codecs:
        given upickle.default.Reader[S0] = Pickle.macroR
      export codecs.{*, given}

case class WorkspaceFoldersServerCapabilities(
  supported: Boolean,
  changeNotifications: (String | Boolean)
)
object WorkspaceFoldersServerCapabilities:
  object codecs:
    given rd0: Reader[(String | Boolean)] = {type T = (String | Boolean); badMerge(reader[String].widen[T], reader[Boolean].widen[T])}
    given upickle.default.Reader[WorkspaceFoldersServerCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class FileOperationOptions(
  didCreate: structures.FileOperationRegistrationOptions,
  willCreate: structures.FileOperationRegistrationOptions,
  didRename: structures.FileOperationRegistrationOptions,
  willRename: structures.FileOperationRegistrationOptions,
  didDelete: structures.FileOperationRegistrationOptions,
  willDelete: structures.FileOperationRegistrationOptions
)
object FileOperationOptions:
  object codecs:
    given upickle.default.Reader[FileOperationOptions] = Pickle.macroR
  export codecs.{*, given}

case class T(
)
object T:
  object codecs:
    given upickle.default.Reader[T] = Pickle.macroR
  export codecs.{*, given}

case class CodeDescription(
  href: aliases.URI
)
object CodeDescription:
  object codecs:
    given upickle.default.Reader[CodeDescription] = Pickle.macroR
  export codecs.{*, given}

case class DiagnosticRelatedInformation(
  location: structures.Location,
  message: String
)
object DiagnosticRelatedInformation:
  object codecs:
    given upickle.default.Reader[DiagnosticRelatedInformation] = Pickle.macroR
  export codecs.{*, given}

case class ParameterInformation(
  label: (String | (RuntimeBase.uinteger, RuntimeBase.uinteger)),
  documentation: (String | structures.MarkupContent)
)
object ParameterInformation:
  object codecs:
    given rd0: Reader[(String | (RuntimeBase.uinteger, RuntimeBase.uinteger))] = {type T = (String | (RuntimeBase.uinteger, RuntimeBase.uinteger)); badMerge(reader[String].widen[T], reader[(RuntimeBase.uinteger, RuntimeBase.uinteger)].widen[T])}
    given rd1: Reader[(String | structures.MarkupContent)] = {type T = (String | structures.MarkupContent); badMerge(reader[String].widen[T], reader[structures.MarkupContent].widen[T])}
    given upickle.default.Reader[ParameterInformation] = Pickle.macroR
  export codecs.{*, given}

case class NotebookCellTextDocumentFilter(
  notebook: (String | aliases.NotebookDocumentFilter),
  language: String
)
object NotebookCellTextDocumentFilter:
  object codecs:
    given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = {type T = (String | aliases.NotebookDocumentFilter); badMerge(reader[String].widen[T], reader[aliases.NotebookDocumentFilter].widen[T])}
    given upickle.default.Reader[NotebookCellTextDocumentFilter] = Pickle.macroR
  export codecs.{*, given}

case class FileOperationPatternOptions(
  ignoreCase: Boolean
)
object FileOperationPatternOptions:
  object codecs:
    given upickle.default.Reader[FileOperationPatternOptions] = Pickle.macroR
  export codecs.{*, given}

case class ExecutionSummary(
  executionOrder: RuntimeBase.uinteger,
  success: Boolean
)
object ExecutionSummary:
  object codecs:
    given upickle.default.Reader[ExecutionSummary] = Pickle.macroR
  export codecs.{*, given}

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
  object codecs:
    given upickle.default.Reader[WorkspaceClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

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
  object codecs:
    given upickle.default.Reader[TextDocumentClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class NotebookDocumentClientCapabilities(
  synchronization: structures.NotebookDocumentSyncClientCapabilities
)
object NotebookDocumentClientCapabilities:
  object codecs:
    given upickle.default.Reader[NotebookDocumentClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class WindowClientCapabilities(
  workDoneProgress: Boolean,
  showMessage: structures.ShowMessageRequestClientCapabilities,
  showDocument: structures.ShowDocumentClientCapabilities
)
object WindowClientCapabilities:
  object codecs:
    given upickle.default.Reader[WindowClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class GeneralClientCapabilities(
  staleRequestSupport: GeneralClientCapabilities.StaleRequestSupport,
  regularExpressions: structures.RegularExpressionsClientCapabilities,
  markdown: structures.MarkdownClientCapabilities,
  positionEncodings: Vector[enumerations.PositionEncodingKind]
)
object GeneralClientCapabilities:
  object codecs:
    given upickle.default.Reader[GeneralClientCapabilities] = Pickle.macroR
  export codecs.{*, given}
  case class StaleRequestSupport(
    cancel: Boolean,
    retryOnContentModified: Vector[String]
  )
  object StaleRequestSupport:
    object codecs:
      given upickle.default.Reader[StaleRequestSupport] = Pickle.macroR
    export codecs.{*, given}

case class RelativePattern(
  baseUri: (structures.WorkspaceFolder | aliases.URI),
  pattern: aliases.Pattern
)
object RelativePattern:
  object codecs:
    given rd0: Reader[(structures.WorkspaceFolder | aliases.URI)] = {type T = (structures.WorkspaceFolder | aliases.URI); badMerge(reader[structures.WorkspaceFolder].widen[T], reader[aliases.URI].widen[T])}
    given upickle.default.Reader[RelativePattern] = Pickle.macroR
  export codecs.{*, given}

case class WorkspaceEditClientCapabilities(
  documentChanges: Boolean,
  resourceOperations: Vector[enumerations.ResourceOperationKind],
  failureHandling: enumerations.FailureHandlingKind,
  normalizesLineEndings: Boolean,
  changeAnnotationSupport: WorkspaceEditClientCapabilities.ChangeAnnotationSupport
)
object WorkspaceEditClientCapabilities:
  object codecs:
    given upickle.default.Reader[WorkspaceEditClientCapabilities] = Pickle.macroR
  export codecs.{*, given}
  case class ChangeAnnotationSupport(
    groupsOnLabel: Boolean
  )
  object ChangeAnnotationSupport:
    object codecs:
      given upickle.default.Reader[ChangeAnnotationSupport] = Pickle.macroR
    export codecs.{*, given}

case class DidChangeConfigurationClientCapabilities(
  dynamicRegistration: Boolean
)
object DidChangeConfigurationClientCapabilities:
  object codecs:
    given upickle.default.Reader[DidChangeConfigurationClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class DidChangeWatchedFilesClientCapabilities(
  dynamicRegistration: Boolean,
  relativePatternSupport: Boolean
)
object DidChangeWatchedFilesClientCapabilities:
  object codecs:
    given upickle.default.Reader[DidChangeWatchedFilesClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class WorkspaceSymbolClientCapabilities(
  dynamicRegistration: Boolean,
  symbolKind: WorkspaceSymbolClientCapabilities.SymbolKind,
  tagSupport: WorkspaceSymbolClientCapabilities.TagSupport,
  resolveSupport: WorkspaceSymbolClientCapabilities.ResolveSupport
)
object WorkspaceSymbolClientCapabilities:
  object codecs:
    given upickle.default.Reader[WorkspaceSymbolClientCapabilities] = Pickle.macroR
  export codecs.{*, given}
  case class SymbolKind(
    valueSet: Vector[enumerations.SymbolKind]
  )
  object SymbolKind:
    object codecs:
      given upickle.default.Reader[SymbolKind] = Pickle.macroR
    export codecs.{*, given}
  case class TagSupport(
    valueSet: Vector[enumerations.SymbolTag]
  )
  object TagSupport:
    object codecs:
      given upickle.default.Reader[TagSupport] = Pickle.macroR
    export codecs.{*, given}
  case class ResolveSupport(
    properties: Vector[String]
  )
  object ResolveSupport:
    object codecs:
      given upickle.default.Reader[ResolveSupport] = Pickle.macroR
    export codecs.{*, given}

case class ExecuteCommandClientCapabilities(
  dynamicRegistration: Boolean
)
object ExecuteCommandClientCapabilities:
  object codecs:
    given upickle.default.Reader[ExecuteCommandClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class SemanticTokensWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object SemanticTokensWorkspaceClientCapabilities:
  object codecs:
    given upickle.default.Reader[SemanticTokensWorkspaceClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class CodeLensWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object CodeLensWorkspaceClientCapabilities:
  object codecs:
    given upickle.default.Reader[CodeLensWorkspaceClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

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
  object codecs:
    given upickle.default.Reader[FileOperationClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class InlineValueWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object InlineValueWorkspaceClientCapabilities:
  object codecs:
    given upickle.default.Reader[InlineValueWorkspaceClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class InlayHintWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object InlayHintWorkspaceClientCapabilities:
  object codecs:
    given upickle.default.Reader[InlayHintWorkspaceClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class DiagnosticWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object DiagnosticWorkspaceClientCapabilities:
  object codecs:
    given upickle.default.Reader[DiagnosticWorkspaceClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class TextDocumentSyncClientCapabilities(
  dynamicRegistration: Boolean,
  willSave: Boolean,
  willSaveWaitUntil: Boolean,
  didSave: Boolean
)
object TextDocumentSyncClientCapabilities:
  object codecs:
    given upickle.default.Reader[TextDocumentSyncClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class CompletionClientCapabilities(
  dynamicRegistration: Boolean,
  completionItem: CompletionClientCapabilities.CompletionItem,
  completionItemKind: CompletionClientCapabilities.CompletionItemKind,
  insertTextMode: enumerations.InsertTextMode,
  contextSupport: Boolean,
  completionList: CompletionClientCapabilities.CompletionList
)
object CompletionClientCapabilities:
  object codecs:
    given upickle.default.Reader[CompletionClientCapabilities] = Pickle.macroR
  export codecs.{*, given}
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
    object codecs:
      given upickle.default.Reader[CompletionItem] = Pickle.macroR
    export codecs.{*, given}
    case class TagSupport(
      valueSet: Vector[enumerations.CompletionItemTag]
    )
    object TagSupport:
      object codecs:
        given upickle.default.Reader[TagSupport] = Pickle.macroR
      export codecs.{*, given}
    case class ResolveSupport(
      properties: Vector[String]
    )
    object ResolveSupport:
      object codecs:
        given upickle.default.Reader[ResolveSupport] = Pickle.macroR
      export codecs.{*, given}
    case class InsertTextModeSupport(
      valueSet: Vector[enumerations.InsertTextMode]
    )
    object InsertTextModeSupport:
      object codecs:
        given upickle.default.Reader[InsertTextModeSupport] = Pickle.macroR
      export codecs.{*, given}
  case class CompletionItemKind(
    valueSet: Vector[enumerations.CompletionItemKind]
  )
  object CompletionItemKind:
    object codecs:
      given upickle.default.Reader[CompletionItemKind] = Pickle.macroR
    export codecs.{*, given}
  case class CompletionList(
    itemDefaults: Vector[String]
  )
  object CompletionList:
    object codecs:
      given upickle.default.Reader[CompletionList] = Pickle.macroR
    export codecs.{*, given}

case class HoverClientCapabilities(
  dynamicRegistration: Boolean,
  contentFormat: Vector[enumerations.MarkupKind]
)
object HoverClientCapabilities:
  object codecs:
    given upickle.default.Reader[HoverClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class SignatureHelpClientCapabilities(
  dynamicRegistration: Boolean,
  signatureInformation: SignatureHelpClientCapabilities.SignatureInformation,
  contextSupport: Boolean
)
object SignatureHelpClientCapabilities:
  object codecs:
    given upickle.default.Reader[SignatureHelpClientCapabilities] = Pickle.macroR
  export codecs.{*, given}
  case class SignatureInformation(
    documentationFormat: Vector[enumerations.MarkupKind],
    parameterInformation: SignatureInformation.ParameterInformation,
    activeParameterSupport: Boolean
  )
  object SignatureInformation:
    object codecs:
      given upickle.default.Reader[SignatureInformation] = Pickle.macroR
    export codecs.{*, given}
    case class ParameterInformation(
      labelOffsetSupport: Boolean
    )
    object ParameterInformation:
      object codecs:
        given upickle.default.Reader[ParameterInformation] = Pickle.macroR
      export codecs.{*, given}

case class DeclarationClientCapabilities(
  dynamicRegistration: Boolean,
  linkSupport: Boolean
)
object DeclarationClientCapabilities:
  object codecs:
    given upickle.default.Reader[DeclarationClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class DefinitionClientCapabilities(
  dynamicRegistration: Boolean,
  linkSupport: Boolean
)
object DefinitionClientCapabilities:
  object codecs:
    given upickle.default.Reader[DefinitionClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class TypeDefinitionClientCapabilities(
  dynamicRegistration: Boolean,
  linkSupport: Boolean
)
object TypeDefinitionClientCapabilities:
  object codecs:
    given upickle.default.Reader[TypeDefinitionClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class ImplementationClientCapabilities(
  dynamicRegistration: Boolean,
  linkSupport: Boolean
)
object ImplementationClientCapabilities:
  object codecs:
    given upickle.default.Reader[ImplementationClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class ReferenceClientCapabilities(
  dynamicRegistration: Boolean
)
object ReferenceClientCapabilities:
  object codecs:
    given upickle.default.Reader[ReferenceClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class DocumentHighlightClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentHighlightClientCapabilities:
  object codecs:
    given upickle.default.Reader[DocumentHighlightClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class DocumentSymbolClientCapabilities(
  dynamicRegistration: Boolean,
  symbolKind: DocumentSymbolClientCapabilities.SymbolKind,
  hierarchicalDocumentSymbolSupport: Boolean,
  tagSupport: DocumentSymbolClientCapabilities.TagSupport,
  labelSupport: Boolean
)
object DocumentSymbolClientCapabilities:
  object codecs:
    given upickle.default.Reader[DocumentSymbolClientCapabilities] = Pickle.macroR
  export codecs.{*, given}
  case class SymbolKind(
    valueSet: Vector[enumerations.SymbolKind]
  )
  object SymbolKind:
    object codecs:
      given upickle.default.Reader[SymbolKind] = Pickle.macroR
    export codecs.{*, given}
  case class TagSupport(
    valueSet: Vector[enumerations.SymbolTag]
  )
  object TagSupport:
    object codecs:
      given upickle.default.Reader[TagSupport] = Pickle.macroR
    export codecs.{*, given}

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
  object codecs:
    given upickle.default.Reader[CodeActionClientCapabilities] = Pickle.macroR
  export codecs.{*, given}
  case class CodeActionLiteralSupport(
    codeActionKind: CodeActionLiteralSupport.CodeActionKind
  )
  object CodeActionLiteralSupport:
    object codecs:
      given upickle.default.Reader[CodeActionLiteralSupport] = Pickle.macroR
    export codecs.{*, given}
    case class CodeActionKind(
      valueSet: Vector[enumerations.CodeActionKind]
    )
    object CodeActionKind:
      object codecs:
        given upickle.default.Reader[CodeActionKind] = Pickle.macroR
      export codecs.{*, given}
  case class ResolveSupport(
    properties: Vector[String]
  )
  object ResolveSupport:
    object codecs:
      given upickle.default.Reader[ResolveSupport] = Pickle.macroR
    export codecs.{*, given}

case class CodeLensClientCapabilities(
  dynamicRegistration: Boolean
)
object CodeLensClientCapabilities:
  object codecs:
    given upickle.default.Reader[CodeLensClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class DocumentLinkClientCapabilities(
  dynamicRegistration: Boolean,
  tooltipSupport: Boolean
)
object DocumentLinkClientCapabilities:
  object codecs:
    given upickle.default.Reader[DocumentLinkClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class DocumentColorClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentColorClientCapabilities:
  object codecs:
    given upickle.default.Reader[DocumentColorClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class DocumentFormattingClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentFormattingClientCapabilities:
  object codecs:
    given upickle.default.Reader[DocumentFormattingClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class DocumentRangeFormattingClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentRangeFormattingClientCapabilities:
  object codecs:
    given upickle.default.Reader[DocumentRangeFormattingClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class DocumentOnTypeFormattingClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentOnTypeFormattingClientCapabilities:
  object codecs:
    given upickle.default.Reader[DocumentOnTypeFormattingClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class RenameClientCapabilities(
  dynamicRegistration: Boolean,
  prepareSupport: Boolean,
  prepareSupportDefaultBehavior: enumerations.PrepareSupportDefaultBehavior,
  honorsChangeAnnotations: Boolean
)
object RenameClientCapabilities:
  object codecs:
    given upickle.default.Reader[RenameClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class FoldingRangeClientCapabilities(
  dynamicRegistration: Boolean,
  rangeLimit: RuntimeBase.uinteger,
  lineFoldingOnly: Boolean,
  foldingRangeKind: FoldingRangeClientCapabilities.FoldingRangeKind,
  foldingRange: FoldingRangeClientCapabilities.FoldingRange
)
object FoldingRangeClientCapabilities:
  object codecs:
    given upickle.default.Reader[FoldingRangeClientCapabilities] = Pickle.macroR
  export codecs.{*, given}
  case class FoldingRangeKind(
    valueSet: Vector[enumerations.FoldingRangeKind]
  )
  object FoldingRangeKind:
    object codecs:
      given upickle.default.Reader[FoldingRangeKind] = Pickle.macroR
    export codecs.{*, given}
  case class FoldingRange(
    collapsedText: Boolean
  )
  object FoldingRange:
    object codecs:
      given upickle.default.Reader[FoldingRange] = Pickle.macroR
    export codecs.{*, given}

case class SelectionRangeClientCapabilities(
  dynamicRegistration: Boolean
)
object SelectionRangeClientCapabilities:
  object codecs:
    given upickle.default.Reader[SelectionRangeClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class PublishDiagnosticsClientCapabilities(
  relatedInformation: Boolean,
  tagSupport: PublishDiagnosticsClientCapabilities.TagSupport,
  versionSupport: Boolean,
  codeDescriptionSupport: Boolean,
  dataSupport: Boolean
)
object PublishDiagnosticsClientCapabilities:
  object codecs:
    given upickle.default.Reader[PublishDiagnosticsClientCapabilities] = Pickle.macroR
  export codecs.{*, given}
  case class TagSupport(
    valueSet: Vector[enumerations.DiagnosticTag]
  )
  object TagSupport:
    object codecs:
      given upickle.default.Reader[TagSupport] = Pickle.macroR
    export codecs.{*, given}

case class CallHierarchyClientCapabilities(
  dynamicRegistration: Boolean
)
object CallHierarchyClientCapabilities:
  object codecs:
    given upickle.default.Reader[CallHierarchyClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

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
  object codecs:
    given upickle.default.Reader[SemanticTokensClientCapabilities] = Pickle.macroR
  export codecs.{*, given}
  case class Requests(
    range: (Boolean | Requests.S0),
    full: (Boolean | Requests.S1)
  )
  object Requests:
    object codecs:
      given rd0: Reader[(Boolean | Requests.S0)] = {type T = (Boolean | Requests.S0); badMerge(reader[Boolean].widen[T], reader[Requests.S0].widen[T])}
      given rd1: Reader[(Boolean | Requests.S1)] = {type T = (Boolean | Requests.S1); badMerge(reader[Boolean].widen[T], reader[Requests.S1].widen[T])}
      given upickle.default.Reader[Requests] = Pickle.macroR
    export codecs.{*, given}
    case class S0(
    )
    object S0:
      object codecs:
        given upickle.default.Reader[S0] = Pickle.macroR
      export codecs.{*, given}
    case class S1(
      delta: Boolean
    )
    object S1:
      object codecs:
        given upickle.default.Reader[S1] = Pickle.macroR
      export codecs.{*, given}

case class LinkedEditingRangeClientCapabilities(
  dynamicRegistration: Boolean
)
object LinkedEditingRangeClientCapabilities:
  object codecs:
    given upickle.default.Reader[LinkedEditingRangeClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class MonikerClientCapabilities(
  dynamicRegistration: Boolean
)
object MonikerClientCapabilities:
  object codecs:
    given upickle.default.Reader[MonikerClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class TypeHierarchyClientCapabilities(
  dynamicRegistration: Boolean
)
object TypeHierarchyClientCapabilities:
  object codecs:
    given upickle.default.Reader[TypeHierarchyClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class InlineValueClientCapabilities(
  dynamicRegistration: Boolean
)
object InlineValueClientCapabilities:
  object codecs:
    given upickle.default.Reader[InlineValueClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class InlayHintClientCapabilities(
  dynamicRegistration: Boolean,
  resolveSupport: InlayHintClientCapabilities.ResolveSupport
)
object InlayHintClientCapabilities:
  object codecs:
    given upickle.default.Reader[InlayHintClientCapabilities] = Pickle.macroR
  export codecs.{*, given}
  case class ResolveSupport(
    properties: Vector[String]
  )
  object ResolveSupport:
    object codecs:
      given upickle.default.Reader[ResolveSupport] = Pickle.macroR
    export codecs.{*, given}

case class DiagnosticClientCapabilities(
  dynamicRegistration: Boolean,
  relatedDocumentSupport: Boolean
)
object DiagnosticClientCapabilities:
  object codecs:
    given upickle.default.Reader[DiagnosticClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class NotebookDocumentSyncClientCapabilities(
  dynamicRegistration: Boolean,
  executionSummarySupport: Boolean
)
object NotebookDocumentSyncClientCapabilities:
  object codecs:
    given upickle.default.Reader[NotebookDocumentSyncClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class ShowMessageRequestClientCapabilities(
  messageActionItem: ShowMessageRequestClientCapabilities.MessageActionItem
)
object ShowMessageRequestClientCapabilities:
  object codecs:
    given upickle.default.Reader[ShowMessageRequestClientCapabilities] = Pickle.macroR
  export codecs.{*, given}
  case class MessageActionItem(
    additionalPropertiesSupport: Boolean
  )
  object MessageActionItem:
    object codecs:
      given upickle.default.Reader[MessageActionItem] = Pickle.macroR
    export codecs.{*, given}

case class ShowDocumentClientCapabilities(
  support: Boolean
)
object ShowDocumentClientCapabilities:
  object codecs:
    given upickle.default.Reader[ShowDocumentClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class RegularExpressionsClientCapabilities(
  engine: String,
  version: String
)
object RegularExpressionsClientCapabilities:
  object codecs:
    given upickle.default.Reader[RegularExpressionsClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

case class MarkdownClientCapabilities(
  parser: String,
  version: String,
  allowedTags: Vector[String]
)
object MarkdownClientCapabilities:
  object codecs:
    given upickle.default.Reader[MarkdownClientCapabilities] = Pickle.macroR
  export codecs.{*, given}

