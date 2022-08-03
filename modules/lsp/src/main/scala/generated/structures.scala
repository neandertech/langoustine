package langoustine.lsp
package structures

import langoustine.*
import upickle.default.*
import langoustine.lsp.json.{*, given}
// format: off

case class ImplementationParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object ImplementationParams:
  given reader: Reader[structures.ImplementationParams] = Pickle.macroR
  given writer: Writer[structures.ImplementationParams] = upickle.default.macroW

case class Location(
  uri: RuntimeBase.DocumentUri,
  range: structures.Range
)
object Location:
  given reader: Reader[structures.Location] = Pickle.macroR
  given writer: Writer[structures.Location] = upickle.default.macroW

case class ImplementationRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object ImplementationRegistrationOptions:
  given reader: Reader[structures.ImplementationRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.ImplementationRegistrationOptions] = upickle.default.macroW

case class TypeDefinitionParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object TypeDefinitionParams:
  given reader: Reader[structures.TypeDefinitionParams] = Pickle.macroR
  given writer: Writer[structures.TypeDefinitionParams] = upickle.default.macroW

case class TypeDefinitionRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object TypeDefinitionRegistrationOptions:
  given reader: Reader[structures.TypeDefinitionRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.TypeDefinitionRegistrationOptions] = upickle.default.macroW

case class WorkspaceFolder(
  uri: aliases.URI,
  name: String
)
object WorkspaceFolder:
  given reader: Reader[structures.WorkspaceFolder] = Pickle.macroR
  given writer: Writer[structures.WorkspaceFolder] = upickle.default.macroW

case class DidChangeWorkspaceFoldersParams(
  event: structures.WorkspaceFoldersChangeEvent
)
object DidChangeWorkspaceFoldersParams:
  given reader: Reader[structures.DidChangeWorkspaceFoldersParams] = Pickle.macroR
  given writer: Writer[structures.DidChangeWorkspaceFoldersParams] = upickle.default.macroW

case class ConfigurationParams(
  items: Vector[structures.ConfigurationItem]
)
object ConfigurationParams:
  given reader: Reader[structures.ConfigurationParams] = Pickle.macroR
  given writer: Writer[structures.ConfigurationParams] = upickle.default.macroW

case class PartialResultParams(
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object PartialResultParams:
  given reader: Reader[structures.PartialResultParams] = Pickle.macroR
  given writer: Writer[structures.PartialResultParams] = upickle.default.macroW

case class DocumentColorParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object DocumentColorParams:
  given reader: Reader[structures.DocumentColorParams] = Pickle.macroR
  given writer: Writer[structures.DocumentColorParams] = upickle.default.macroW

case class ColorInformation(
  range: structures.Range,
  color: structures.Color
)
object ColorInformation:
  given reader: Reader[structures.ColorInformation] = Pickle.macroR
  given writer: Writer[structures.ColorInformation] = upickle.default.macroW

case class DocumentColorRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object DocumentColorRegistrationOptions:
  given reader: Reader[structures.DocumentColorRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentColorRegistrationOptions] = upickle.default.macroW

case class ColorPresentationParams(
  textDocument: structures.TextDocumentIdentifier,
  color: structures.Color,
  range: structures.Range,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object ColorPresentationParams:
  given reader: Reader[structures.ColorPresentationParams] = Pickle.macroR
  given writer: Writer[structures.ColorPresentationParams] = upickle.default.macroW

case class ColorPresentation(
  label: String,
  textEdit: Opt[structures.TextEdit] = Opt.empty,
  additionalTextEdits: Opt[Vector[structures.TextEdit]] = Opt.empty
)
object ColorPresentation:
  given reader: Reader[structures.ColorPresentation] = Pickle.macroR
  given writer: Writer[structures.ColorPresentation] = upickle.default.macroW

case class WorkDoneProgressOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object WorkDoneProgressOptions:
  given reader: Reader[structures.WorkDoneProgressOptions] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressOptions] = upickle.default.macroW

case class TextDocumentRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector]
)
object TextDocumentRegistrationOptions:
  given reader: Reader[structures.TextDocumentRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.TextDocumentRegistrationOptions] = upickle.default.macroW

case class FoldingRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object FoldingRangeParams:
  given reader: Reader[structures.FoldingRangeParams] = Pickle.macroR
  given writer: Writer[structures.FoldingRangeParams] = upickle.default.macroW

case class FoldingRange(
  startLine: RuntimeBase.uinteger,
  startCharacter: Opt[RuntimeBase.uinteger] = Opt.empty,
  endLine: RuntimeBase.uinteger,
  endCharacter: Opt[RuntimeBase.uinteger] = Opt.empty,
  kind: Opt[enumerations.FoldingRangeKind] = Opt.empty,
  collapsedText: Opt[String] = Opt.empty
)
object FoldingRange:
  given reader: Reader[structures.FoldingRange] = Pickle.macroR
  given writer: Writer[structures.FoldingRange] = upickle.default.macroW

case class FoldingRangeRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object FoldingRangeRegistrationOptions:
  given reader: Reader[structures.FoldingRangeRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.FoldingRangeRegistrationOptions] = upickle.default.macroW

case class DeclarationParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object DeclarationParams:
  given reader: Reader[structures.DeclarationParams] = Pickle.macroR
  given writer: Writer[structures.DeclarationParams] = upickle.default.macroW

case class DeclarationRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object DeclarationRegistrationOptions:
  given reader: Reader[structures.DeclarationRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DeclarationRegistrationOptions] = upickle.default.macroW

case class SelectionRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  positions: Vector[structures.Position],
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object SelectionRangeParams:
  given reader: Reader[structures.SelectionRangeParams] = Pickle.macroR
  given writer: Writer[structures.SelectionRangeParams] = upickle.default.macroW

case class SelectionRange(
  range: structures.Range,
  parent: Opt[structures.SelectionRange] = Opt.empty
)
object SelectionRange:
  given reader: Reader[structures.SelectionRange] = Pickle.macroR
  given writer: Writer[structures.SelectionRange] = upickle.default.macroW

case class SelectionRangeRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object SelectionRangeRegistrationOptions:
  given reader: Reader[structures.SelectionRangeRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.SelectionRangeRegistrationOptions] = upickle.default.macroW

case class WorkDoneProgressCreateParams(
  token: aliases.ProgressToken
)
object WorkDoneProgressCreateParams:
  given reader: Reader[structures.WorkDoneProgressCreateParams] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressCreateParams] = upickle.default.macroW

case class WorkDoneProgressCancelParams(
  token: aliases.ProgressToken
)
object WorkDoneProgressCancelParams:
  given reader: Reader[structures.WorkDoneProgressCancelParams] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressCancelParams] = upickle.default.macroW

case class CallHierarchyPrepareParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object CallHierarchyPrepareParams:
  given reader: Reader[structures.CallHierarchyPrepareParams] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyPrepareParams] = upickle.default.macroW

case class CallHierarchyItem(
  name: String,
  kind: enumerations.SymbolKind,
  tags: Opt[Vector[enumerations.SymbolTag]] = Opt.empty,
  detail: Opt[String] = Opt.empty,
  uri: RuntimeBase.DocumentUri,
  range: structures.Range,
  selectionRange: structures.Range,
  data: Opt[ujson.Value] = Opt.empty
)
object CallHierarchyItem:
  given reader: Reader[structures.CallHierarchyItem] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyItem] = upickle.default.macroW

case class CallHierarchyRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object CallHierarchyRegistrationOptions:
  given reader: Reader[structures.CallHierarchyRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyRegistrationOptions] = upickle.default.macroW

case class CallHierarchyIncomingCallsParams(
  item: structures.CallHierarchyItem,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object CallHierarchyIncomingCallsParams:
  given reader: Reader[structures.CallHierarchyIncomingCallsParams] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyIncomingCallsParams] = upickle.default.macroW

case class CallHierarchyIncomingCall(
  from: structures.CallHierarchyItem,
  fromRanges: Vector[structures.Range]
)
object CallHierarchyIncomingCall:
  given reader: Reader[structures.CallHierarchyIncomingCall] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyIncomingCall] = upickle.default.macroW

case class CallHierarchyOutgoingCallsParams(
  item: structures.CallHierarchyItem,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object CallHierarchyOutgoingCallsParams:
  given reader: Reader[structures.CallHierarchyOutgoingCallsParams] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyOutgoingCallsParams] = upickle.default.macroW

case class CallHierarchyOutgoingCall(
  to: structures.CallHierarchyItem,
  fromRanges: Vector[structures.Range]
)
object CallHierarchyOutgoingCall:
  given reader: Reader[structures.CallHierarchyOutgoingCall] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyOutgoingCall] = upickle.default.macroW

case class SemanticTokensParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object SemanticTokensParams:
  given reader: Reader[structures.SemanticTokensParams] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensParams] = upickle.default.macroW

case class SemanticTokens(
  resultId: Opt[String] = Opt.empty,
  data: Vector[RuntimeBase.uinteger]
)
object SemanticTokens:
  given reader: Reader[structures.SemanticTokens] = Pickle.macroR
  given writer: Writer[structures.SemanticTokens] = upickle.default.macroW

case class SemanticTokensPartialResult(
  data: Vector[RuntimeBase.uinteger]
)
object SemanticTokensPartialResult:
  given reader: Reader[structures.SemanticTokensPartialResult] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensPartialResult] = upickle.default.macroW

case class SemanticTokensRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  legend: structures.SemanticTokensLegend,
  range: Opt[(Boolean | SemanticTokensRegistrationOptions.S0)] = Opt.empty,
  full: Opt[(Boolean | SemanticTokensRegistrationOptions.S1)] = Opt.empty,
  id: Opt[String] = Opt.empty
)
object SemanticTokensRegistrationOptions:
  private given rd1: Reader[(Boolean | SemanticTokensRegistrationOptions.S0)] = 
    badMerge[(Boolean | SemanticTokensRegistrationOptions.S0)](upickle.default.reader[Boolean], SemanticTokensRegistrationOptions.S0.reader)
  private given wt1: Writer[(Boolean | SemanticTokensRegistrationOptions.S0)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | SemanticTokensRegistrationOptions.S0)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: SemanticTokensRegistrationOptions.S0 => writeJs[SemanticTokensRegistrationOptions.S0](v)
    }
  private given rd2: Reader[(Boolean | SemanticTokensRegistrationOptions.S1)] = 
    badMerge[(Boolean | SemanticTokensRegistrationOptions.S1)](upickle.default.reader[Boolean], SemanticTokensRegistrationOptions.S1.reader)
  private given wt2: Writer[(Boolean | SemanticTokensRegistrationOptions.S1)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | SemanticTokensRegistrationOptions.S1)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: SemanticTokensRegistrationOptions.S1 => writeJs[SemanticTokensRegistrationOptions.S1](v)
    }
  given reader: Reader[structures.SemanticTokensRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensRegistrationOptions] = upickle.default.macroW
  case class S0(
  )
  object S0:
    given reader: Reader[structures.SemanticTokensRegistrationOptions.S0] = Pickle.macroR
    given writer: Writer[structures.SemanticTokensRegistrationOptions.S0] = upickle.default.macroW
  case class S1(
    delta: Opt[Boolean] = Opt.empty
  )
  object S1:
    given reader: Reader[structures.SemanticTokensRegistrationOptions.S1] = Pickle.macroR
    given writer: Writer[structures.SemanticTokensRegistrationOptions.S1] = upickle.default.macroW

case class SemanticTokensDeltaParams(
  textDocument: structures.TextDocumentIdentifier,
  previousResultId: String,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object SemanticTokensDeltaParams:
  given reader: Reader[structures.SemanticTokensDeltaParams] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensDeltaParams] = upickle.default.macroW

case class SemanticTokensDelta(
  resultId: Opt[String] = Opt.empty,
  edits: Vector[structures.SemanticTokensEdit]
)
object SemanticTokensDelta:
  given reader: Reader[structures.SemanticTokensDelta] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensDelta] = upickle.default.macroW

case class SemanticTokensDeltaPartialResult(
  edits: Vector[structures.SemanticTokensEdit]
)
object SemanticTokensDeltaPartialResult:
  given reader: Reader[structures.SemanticTokensDeltaPartialResult] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensDeltaPartialResult] = upickle.default.macroW

case class SemanticTokensRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object SemanticTokensRangeParams:
  given reader: Reader[structures.SemanticTokensRangeParams] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensRangeParams] = upickle.default.macroW

case class ShowDocumentParams(
  uri: aliases.URI,
  external: Opt[Boolean] = Opt.empty,
  takeFocus: Opt[Boolean] = Opt.empty,
  selection: Opt[structures.Range] = Opt.empty
)
object ShowDocumentParams:
  given reader: Reader[structures.ShowDocumentParams] = Pickle.macroR
  given writer: Writer[structures.ShowDocumentParams] = upickle.default.macroW

case class ShowDocumentResult(
  success: Boolean
)
object ShowDocumentResult:
  given reader: Reader[structures.ShowDocumentResult] = Pickle.macroR
  given writer: Writer[structures.ShowDocumentResult] = upickle.default.macroW

case class LinkedEditingRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object LinkedEditingRangeParams:
  given reader: Reader[structures.LinkedEditingRangeParams] = Pickle.macroR
  given writer: Writer[structures.LinkedEditingRangeParams] = upickle.default.macroW

case class LinkedEditingRanges(
  ranges: Vector[structures.Range],
  wordPattern: Opt[String] = Opt.empty
)
object LinkedEditingRanges:
  given reader: Reader[structures.LinkedEditingRanges] = Pickle.macroR
  given writer: Writer[structures.LinkedEditingRanges] = upickle.default.macroW

case class LinkedEditingRangeRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object LinkedEditingRangeRegistrationOptions:
  given reader: Reader[structures.LinkedEditingRangeRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.LinkedEditingRangeRegistrationOptions] = upickle.default.macroW

case class CreateFilesParams(
  files: Vector[structures.FileCreate]
)
object CreateFilesParams:
  given reader: Reader[structures.CreateFilesParams] = Pickle.macroR
  given writer: Writer[structures.CreateFilesParams] = upickle.default.macroW

case class WorkspaceEdit(
  changes: Opt[Map[RuntimeBase.DocumentUri, Vector[structures.TextEdit]]] = Opt.empty,
  documentChanges: Opt[Vector[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)]] = Opt.empty,
  changeAnnotations: Opt[Map[aliases.ChangeAnnotationIdentifier, structures.ChangeAnnotation]] = Opt.empty
)
object WorkspaceEdit:
  private given rd0: Reader[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)] = 
    badMerge[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)](structures.TextDocumentEdit.reader, structures.CreateFile.reader, structures.RenameFile.reader, structures.DeleteFile.reader)
  private given wt0: Writer[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)] = 
    upickle.default.writer[ujson.Value].comap[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)] { _v => 
      (_v: @unchecked) match 
        case v: structures.TextDocumentEdit => writeJs[structures.TextDocumentEdit](v)
        case v: structures.CreateFile => writeJs[structures.CreateFile](v)
        case v: structures.RenameFile => writeJs[structures.RenameFile](v)
        case v: structures.DeleteFile => writeJs[structures.DeleteFile](v)
    }
  given reader: Reader[structures.WorkspaceEdit] = Pickle.macroR
  given writer: Writer[structures.WorkspaceEdit] = upickle.default.macroW

case class FileOperationRegistrationOptions(
  filters: Vector[structures.FileOperationFilter]
)
object FileOperationRegistrationOptions:
  given reader: Reader[structures.FileOperationRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.FileOperationRegistrationOptions] = upickle.default.macroW

case class RenameFilesParams(
  files: Vector[structures.FileRename]
)
object RenameFilesParams:
  given reader: Reader[structures.RenameFilesParams] = Pickle.macroR
  given writer: Writer[structures.RenameFilesParams] = upickle.default.macroW

case class DeleteFilesParams(
  files: Vector[structures.FileDelete]
)
object DeleteFilesParams:
  given reader: Reader[structures.DeleteFilesParams] = Pickle.macroR
  given writer: Writer[structures.DeleteFilesParams] = upickle.default.macroW

case class MonikerParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object MonikerParams:
  given reader: Reader[structures.MonikerParams] = Pickle.macroR
  given writer: Writer[structures.MonikerParams] = upickle.default.macroW

case class Moniker(
  scheme: String,
  identifier: String,
  unique: enumerations.UniquenessLevel,
  kind: Opt[enumerations.MonikerKind] = Opt.empty
)
object Moniker:
  given reader: Reader[structures.Moniker] = Pickle.macroR
  given writer: Writer[structures.Moniker] = upickle.default.macroW

case class MonikerRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector]
)
object MonikerRegistrationOptions:
  given reader: Reader[structures.MonikerRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.MonikerRegistrationOptions] = upickle.default.macroW

case class TypeHierarchyPrepareParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object TypeHierarchyPrepareParams:
  given reader: Reader[structures.TypeHierarchyPrepareParams] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchyPrepareParams] = upickle.default.macroW

case class TypeHierarchyItem(
  name: String,
  kind: enumerations.SymbolKind,
  tags: Opt[Vector[enumerations.SymbolTag]] = Opt.empty,
  detail: Opt[String] = Opt.empty,
  uri: RuntimeBase.DocumentUri,
  range: structures.Range,
  selectionRange: structures.Range,
  data: Opt[ujson.Value] = Opt.empty
)
object TypeHierarchyItem:
  given reader: Reader[structures.TypeHierarchyItem] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchyItem] = upickle.default.macroW

case class TypeHierarchyRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object TypeHierarchyRegistrationOptions:
  given reader: Reader[structures.TypeHierarchyRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchyRegistrationOptions] = upickle.default.macroW

case class TypeHierarchySupertypesParams(
  item: structures.TypeHierarchyItem,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object TypeHierarchySupertypesParams:
  given reader: Reader[structures.TypeHierarchySupertypesParams] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchySupertypesParams] = upickle.default.macroW

case class TypeHierarchySubtypesParams(
  item: structures.TypeHierarchyItem,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object TypeHierarchySubtypesParams:
  given reader: Reader[structures.TypeHierarchySubtypesParams] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchySubtypesParams] = upickle.default.macroW

case class InlineValueParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  context: structures.InlineValueContext,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object InlineValueParams:
  given reader: Reader[structures.InlineValueParams] = Pickle.macroR
  given writer: Writer[structures.InlineValueParams] = upickle.default.macroW

case class InlineValueRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object InlineValueRegistrationOptions:
  given reader: Reader[structures.InlineValueRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.InlineValueRegistrationOptions] = upickle.default.macroW

case class InlayHintParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object InlayHintParams:
  given reader: Reader[structures.InlayHintParams] = Pickle.macroR
  given writer: Writer[structures.InlayHintParams] = upickle.default.macroW

case class InlayHint(
  position: structures.Position,
  label: (String | Vector[structures.InlayHintLabelPart]),
  kind: Opt[enumerations.InlayHintKind] = Opt.empty,
  textEdits: Opt[Vector[structures.TextEdit]] = Opt.empty,
  tooltip: Opt[(String | structures.MarkupContent)] = Opt.empty,
  paddingLeft: Opt[Boolean] = Opt.empty,
  paddingRight: Opt[Boolean] = Opt.empty,
  data: Opt[ujson.Value] = Opt.empty
)
object InlayHint:
  private given rd0: Reader[(String | Vector[structures.InlayHintLabelPart])] = 
    badMerge[(String | Vector[structures.InlayHintLabelPart])](stringCodec, upickle.default.reader[Vector[structures.InlayHintLabelPart]])
  private given wt0: Writer[(String | Vector[structures.InlayHintLabelPart])] = 
    upickle.default.writer[ujson.Value].comap[(String | Vector[structures.InlayHintLabelPart])] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: Vector[?] => writeJs[Vector[structures.InlayHintLabelPart]](v.asInstanceOf[Vector[structures.InlayHintLabelPart]])
    }
  private given rd1: Reader[(String | structures.MarkupContent)] = 
    badMerge[(String | structures.MarkupContent)](stringCodec, structures.MarkupContent.reader)
  private given wt1: Writer[(String | structures.MarkupContent)] = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: structures.MarkupContent => writeJs[structures.MarkupContent](v)
    }
  given reader: Reader[structures.InlayHint] = Pickle.macroR
  given writer: Writer[structures.InlayHint] = upickle.default.macroW

case class InlayHintRegistrationOptions(
  resolveProvider: Opt[Boolean] = Opt.empty,
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object InlayHintRegistrationOptions:
  given reader: Reader[structures.InlayHintRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.InlayHintRegistrationOptions] = upickle.default.macroW

case class DocumentDiagnosticParams(
  textDocument: structures.TextDocumentIdentifier,
  identifier: Opt[String] = Opt.empty,
  previousResultId: Opt[String] = Opt.empty,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object DocumentDiagnosticParams:
  given reader: Reader[structures.DocumentDiagnosticParams] = Pickle.macroR
  given writer: Writer[structures.DocumentDiagnosticParams] = upickle.default.macroW

case class DocumentDiagnosticReportPartialResult(
  relatedDocuments: Map[RuntimeBase.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)]
)
object DocumentDiagnosticReportPartialResult:
  private given rd0: Reader[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = 
    badMerge[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)](structures.FullDocumentDiagnosticReport.reader, structures.UnchangedDocumentDiagnosticReport.reader)
  private given wt0: Writer[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = 
    upickle.default.writer[ujson.Value].comap[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] { _v => 
      (_v: @unchecked) match 
        case v: structures.FullDocumentDiagnosticReport => writeJs[structures.FullDocumentDiagnosticReport](v)
        case v: structures.UnchangedDocumentDiagnosticReport => writeJs[structures.UnchangedDocumentDiagnosticReport](v)
    }
  given reader: Reader[structures.DocumentDiagnosticReportPartialResult] = Pickle.macroR
  given writer: Writer[structures.DocumentDiagnosticReportPartialResult] = upickle.default.macroW

case class DiagnosticServerCancellationData(
  retriggerRequest: Boolean
)
object DiagnosticServerCancellationData:
  given reader: Reader[structures.DiagnosticServerCancellationData] = Pickle.macroR
  given writer: Writer[structures.DiagnosticServerCancellationData] = upickle.default.macroW

case class DiagnosticRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  identifier: Opt[String] = Opt.empty,
  interFileDependencies: Boolean,
  workspaceDiagnostics: Boolean,
  id: Opt[String] = Opt.empty
)
object DiagnosticRegistrationOptions:
  given reader: Reader[structures.DiagnosticRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DiagnosticRegistrationOptions] = upickle.default.macroW

case class WorkspaceDiagnosticParams(
  identifier: Opt[String] = Opt.empty,
  previousResultIds: Vector[structures.PreviousResultId],
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object WorkspaceDiagnosticParams:
  given reader: Reader[structures.WorkspaceDiagnosticParams] = Pickle.macroR
  given writer: Writer[structures.WorkspaceDiagnosticParams] = upickle.default.macroW

case class WorkspaceDiagnosticReport(
  items: Vector[aliases.WorkspaceDocumentDiagnosticReport]
)
object WorkspaceDiagnosticReport:
  given reader: Reader[structures.WorkspaceDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.WorkspaceDiagnosticReport] = upickle.default.macroW

case class WorkspaceDiagnosticReportPartialResult(
  items: Vector[aliases.WorkspaceDocumentDiagnosticReport]
)
object WorkspaceDiagnosticReportPartialResult:
  given reader: Reader[structures.WorkspaceDiagnosticReportPartialResult] = Pickle.macroR
  given writer: Writer[structures.WorkspaceDiagnosticReportPartialResult] = upickle.default.macroW

case class DidOpenNotebookDocumentParams(
  notebookDocument: structures.NotebookDocument,
  cellTextDocuments: Vector[structures.TextDocumentItem]
)
object DidOpenNotebookDocumentParams:
  given reader: Reader[structures.DidOpenNotebookDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidOpenNotebookDocumentParams] = upickle.default.macroW

case class DidChangeNotebookDocumentParams(
  notebookDocument: structures.VersionedNotebookDocumentIdentifier,
  change: structures.NotebookDocumentChangeEvent
)
object DidChangeNotebookDocumentParams:
  given reader: Reader[structures.DidChangeNotebookDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidChangeNotebookDocumentParams] = upickle.default.macroW

case class DidSaveNotebookDocumentParams(
  notebookDocument: structures.NotebookDocumentIdentifier
)
object DidSaveNotebookDocumentParams:
  given reader: Reader[structures.DidSaveNotebookDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidSaveNotebookDocumentParams] = upickle.default.macroW

case class DidCloseNotebookDocumentParams(
  notebookDocument: structures.NotebookDocumentIdentifier,
  cellTextDocuments: Vector[structures.TextDocumentIdentifier]
)
object DidCloseNotebookDocumentParams:
  given reader: Reader[structures.DidCloseNotebookDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidCloseNotebookDocumentParams] = upickle.default.macroW

case class RegistrationParams(
  registrations: Vector[structures.Registration]
)
object RegistrationParams:
  given reader: Reader[structures.RegistrationParams] = Pickle.macroR
  given writer: Writer[structures.RegistrationParams] = upickle.default.macroW

case class UnregistrationParams(
  unregisterations: Vector[structures.Unregistration]
)
object UnregistrationParams:
  given reader: Reader[structures.UnregistrationParams] = Pickle.macroR
  given writer: Writer[structures.UnregistrationParams] = upickle.default.macroW

case class InitializeParams(
  processId: Nullable[Int],
  clientInfo: Opt[InitializeParams.ClientInfo] = Opt.empty,
  locale: Opt[String] = Opt.empty,
  rootPath: Opt[Nullable[String]] = Opt.empty,
  rootUri: Nullable[RuntimeBase.DocumentUri],
  capabilities: structures.ClientCapabilities,
  initializationOptions: Opt[ujson.Value] = Opt.empty,
  trace: Opt[("off" | "messages" | "compact" | "verbose")] = Opt.empty,
  workspaceFolders: Opt[Nullable[Vector[structures.WorkspaceFolder]]] = Opt.empty
)
object InitializeParams:
  private given rd3: Reader[("off" | "messages" | "compact" | "verbose")] = 
    badMerge[("off" | "messages" | "compact" | "verbose")](upickle.default.reader["off"], upickle.default.reader["messages"], upickle.default.reader["compact"], upickle.default.reader["verbose"])
  private given wt3: Writer[("off" | "messages" | "compact" | "verbose")] = 
    upickle.default.writer[ujson.Value].comap[("off" | "messages" | "compact" | "verbose")] { _v => 
      (_v: @unchecked) match 
        case v: "off" => writeJs["off"](v)
        case v: "messages" => writeJs["messages"](v)
        case v: "compact" => writeJs["compact"](v)
        case v: "verbose" => writeJs["verbose"](v)
    }
  given reader: Reader[structures.InitializeParams] = Pickle.macroR
  given writer: Writer[structures.InitializeParams] = upickle.default.macroW
  case class ClientInfo(
    name: String,
    version: Opt[String] = Opt.empty
  )
  object ClientInfo:
    given reader: Reader[structures.InitializeParams.ClientInfo] = Pickle.macroR
    given writer: Writer[structures.InitializeParams.ClientInfo] = upickle.default.macroW

case class InitializeResult(
  capabilities: structures.ServerCapabilities,
  serverInfo: Opt[InitializeResult.ServerInfo] = Opt.empty
)
object InitializeResult:
  given reader: Reader[structures.InitializeResult] = Pickle.macroR
  given writer: Writer[structures.InitializeResult] = upickle.default.macroW
  case class ServerInfo(
    name: String,
    version: Opt[String] = Opt.empty
  )
  object ServerInfo:
    given reader: Reader[structures.InitializeResult.ServerInfo] = Pickle.macroR
    given writer: Writer[structures.InitializeResult.ServerInfo] = upickle.default.macroW

case class InitializeError(
  retry: Boolean
)
object InitializeError:
  given reader: Reader[structures.InitializeError] = Pickle.macroR
  given writer: Writer[structures.InitializeError] = upickle.default.macroW

case class InitializedParams(
)
object InitializedParams:
  given reader: Reader[structures.InitializedParams] = Pickle.macroR
  given writer: Writer[structures.InitializedParams] = upickle.default.macroW

case class DidChangeConfigurationParams(
  settings: ujson.Value
)
object DidChangeConfigurationParams:
  given reader: Reader[structures.DidChangeConfigurationParams] = Pickle.macroR
  given writer: Writer[structures.DidChangeConfigurationParams] = upickle.default.macroW

case class DidChangeConfigurationRegistrationOptions(
  section: Opt[(String | Vector[String])] = Opt.empty
)
object DidChangeConfigurationRegistrationOptions:
  private given rd0: Reader[(String | Vector[String])] = 
    badMerge[(String | Vector[String])](stringCodec, upickle.default.reader[Vector[String]])
  private given wt0: Writer[(String | Vector[String])] = 
    upickle.default.writer[ujson.Value].comap[(String | Vector[String])] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: Vector[?] => writeJs[Vector[String]](v.asInstanceOf[Vector[String]])
    }
  given reader: Reader[structures.DidChangeConfigurationRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DidChangeConfigurationRegistrationOptions] = upickle.default.macroW

case class ShowMessageParams(
  `type`: enumerations.MessageType,
  message: String
)
object ShowMessageParams:
  given reader: Reader[structures.ShowMessageParams] = Pickle.macroR
  given writer: Writer[structures.ShowMessageParams] = upickle.default.macroW

case class ShowMessageRequestParams(
  `type`: enumerations.MessageType,
  message: String,
  actions: Opt[Vector[structures.MessageActionItem]] = Opt.empty
)
object ShowMessageRequestParams:
  given reader: Reader[structures.ShowMessageRequestParams] = Pickle.macroR
  given writer: Writer[structures.ShowMessageRequestParams] = upickle.default.macroW

case class MessageActionItem(
  title: String
)
object MessageActionItem:
  given reader: Reader[structures.MessageActionItem] = Pickle.macroR
  given writer: Writer[structures.MessageActionItem] = upickle.default.macroW

case class LogMessageParams(
  `type`: enumerations.MessageType,
  message: String
)
object LogMessageParams:
  given reader: Reader[structures.LogMessageParams] = Pickle.macroR
  given writer: Writer[structures.LogMessageParams] = upickle.default.macroW

case class DidOpenTextDocumentParams(
  textDocument: structures.TextDocumentItem
)
object DidOpenTextDocumentParams:
  given reader: Reader[structures.DidOpenTextDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidOpenTextDocumentParams] = upickle.default.macroW

case class DidChangeTextDocumentParams(
  textDocument: structures.VersionedTextDocumentIdentifier,
  contentChanges: Vector[aliases.TextDocumentContentChangeEvent]
)
object DidChangeTextDocumentParams:
  given reader: Reader[structures.DidChangeTextDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidChangeTextDocumentParams] = upickle.default.macroW

case class TextDocumentChangeRegistrationOptions(
  syncKind: enumerations.TextDocumentSyncKind,
  documentSelector: Nullable[aliases.DocumentSelector]
)
object TextDocumentChangeRegistrationOptions:
  given reader: Reader[structures.TextDocumentChangeRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.TextDocumentChangeRegistrationOptions] = upickle.default.macroW

case class DidCloseTextDocumentParams(
  textDocument: structures.TextDocumentIdentifier
)
object DidCloseTextDocumentParams:
  given reader: Reader[structures.DidCloseTextDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidCloseTextDocumentParams] = upickle.default.macroW

case class DidSaveTextDocumentParams(
  textDocument: structures.TextDocumentIdentifier,
  text: Opt[String] = Opt.empty
)
object DidSaveTextDocumentParams:
  given reader: Reader[structures.DidSaveTextDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidSaveTextDocumentParams] = upickle.default.macroW

case class TextDocumentSaveRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  includeText: Opt[Boolean] = Opt.empty
)
object TextDocumentSaveRegistrationOptions:
  given reader: Reader[structures.TextDocumentSaveRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.TextDocumentSaveRegistrationOptions] = upickle.default.macroW

case class WillSaveTextDocumentParams(
  textDocument: structures.TextDocumentIdentifier,
  reason: enumerations.TextDocumentSaveReason
)
object WillSaveTextDocumentParams:
  given reader: Reader[structures.WillSaveTextDocumentParams] = Pickle.macroR
  given writer: Writer[structures.WillSaveTextDocumentParams] = upickle.default.macroW

case class TextEdit(
  range: structures.Range,
  newText: String
)
object TextEdit:
  given reader: Reader[structures.TextEdit] = Pickle.macroR
  given writer: Writer[structures.TextEdit] = upickle.default.macroW

case class DidChangeWatchedFilesParams(
  changes: Vector[structures.FileEvent]
)
object DidChangeWatchedFilesParams:
  given reader: Reader[structures.DidChangeWatchedFilesParams] = Pickle.macroR
  given writer: Writer[structures.DidChangeWatchedFilesParams] = upickle.default.macroW

case class DidChangeWatchedFilesRegistrationOptions(
  watchers: Vector[structures.FileSystemWatcher]
)
object DidChangeWatchedFilesRegistrationOptions:
  given reader: Reader[structures.DidChangeWatchedFilesRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DidChangeWatchedFilesRegistrationOptions] = upickle.default.macroW

case class PublishDiagnosticsParams(
  uri: RuntimeBase.DocumentUri,
  version: Opt[Int] = Opt.empty,
  diagnostics: Vector[structures.Diagnostic]
)
object PublishDiagnosticsParams:
  given reader: Reader[structures.PublishDiagnosticsParams] = Pickle.macroR
  given writer: Writer[structures.PublishDiagnosticsParams] = upickle.default.macroW

case class CompletionParams(
  context: Opt[structures.CompletionContext] = Opt.empty,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object CompletionParams:
  given reader: Reader[structures.CompletionParams] = Pickle.macroR
  given writer: Writer[structures.CompletionParams] = upickle.default.macroW

case class CompletionItem(
  label: String,
  labelDetails: Opt[structures.CompletionItemLabelDetails] = Opt.empty,
  kind: Opt[enumerations.CompletionItemKind] = Opt.empty,
  tags: Opt[Vector[enumerations.CompletionItemTag]] = Opt.empty,
  detail: Opt[String] = Opt.empty,
  documentation: Opt[(String | structures.MarkupContent)] = Opt.empty,
  deprecated: Opt[Boolean] = Opt.empty,
  preselect: Opt[Boolean] = Opt.empty,
  sortText: Opt[String] = Opt.empty,
  filterText: Opt[String] = Opt.empty,
  insertText: Opt[String] = Opt.empty,
  insertTextFormat: Opt[enumerations.InsertTextFormat] = Opt.empty,
  insertTextMode: Opt[enumerations.InsertTextMode] = Opt.empty,
  textEdit: Opt[(structures.TextEdit | structures.InsertReplaceEdit)] = Opt.empty,
  textEditText: Opt[String] = Opt.empty,
  additionalTextEdits: Opt[Vector[structures.TextEdit]] = Opt.empty,
  commitCharacters: Opt[Vector[String]] = Opt.empty,
  command: Opt[structures.Command] = Opt.empty,
  data: Opt[ujson.Value] = Opt.empty
)
object CompletionItem:
  private given rd0: Reader[(String | structures.MarkupContent)] = 
    badMerge[(String | structures.MarkupContent)](stringCodec, structures.MarkupContent.reader)
  private given wt0: Writer[(String | structures.MarkupContent)] = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: structures.MarkupContent => writeJs[structures.MarkupContent](v)
    }
  private given rd1: Reader[(structures.TextEdit | structures.InsertReplaceEdit)] = 
    badMerge[(structures.TextEdit | structures.InsertReplaceEdit)](structures.TextEdit.reader, structures.InsertReplaceEdit.reader)
  private given wt1: Writer[(structures.TextEdit | structures.InsertReplaceEdit)] = 
    upickle.default.writer[ujson.Value].comap[(structures.TextEdit | structures.InsertReplaceEdit)] { _v => 
      (_v: @unchecked) match 
        case v: structures.TextEdit => writeJs[structures.TextEdit](v)
        case v: structures.InsertReplaceEdit => writeJs[structures.InsertReplaceEdit](v)
    }
  given reader: Reader[structures.CompletionItem] = Pickle.macroR
  given writer: Writer[structures.CompletionItem] = upickle.default.macroW

case class CompletionList(
  isIncomplete: Boolean,
  itemDefaults: Opt[CompletionList.ItemDefaults] = Opt.empty,
  items: Vector[structures.CompletionItem]
)
object CompletionList:
  given reader: Reader[structures.CompletionList] = Pickle.macroR
  given writer: Writer[structures.CompletionList] = upickle.default.macroW
  case class ItemDefaults(
    commitCharacters: Opt[Vector[String]] = Opt.empty,
    editRange: Opt[(structures.Range | ItemDefaults.S0)] = Opt.empty,
    insertTextFormat: Opt[enumerations.InsertTextFormat] = Opt.empty,
    insertTextMode: Opt[enumerations.InsertTextMode] = Opt.empty,
    data: Opt[ujson.Value] = Opt.empty
  )
  object ItemDefaults:
    private given rd0: Reader[(structures.Range | ItemDefaults.S0)] = 
      badMerge[(structures.Range | ItemDefaults.S0)](structures.Range.reader, ItemDefaults.S0.reader)
    private given wt0: Writer[(structures.Range | ItemDefaults.S0)] = 
      upickle.default.writer[ujson.Value].comap[(structures.Range | ItemDefaults.S0)] { _v => 
        (_v: @unchecked) match 
          case v: structures.Range => writeJs[structures.Range](v)
          case v: ItemDefaults.S0 => writeJs[ItemDefaults.S0](v)
      }
    given reader: Reader[structures.CompletionList.ItemDefaults] = Pickle.macroR
    given writer: Writer[structures.CompletionList.ItemDefaults] = upickle.default.macroW
    case class S0(
      insert: structures.Range,
      replace: structures.Range
    )
    object S0:
      given reader: Reader[structures.CompletionList.ItemDefaults.S0] = Pickle.macroR
      given writer: Writer[structures.CompletionList.ItemDefaults.S0] = upickle.default.macroW

case class CompletionRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  triggerCharacters: Opt[Vector[String]] = Opt.empty,
  allCommitCharacters: Opt[Vector[String]] = Opt.empty,
  resolveProvider: Opt[Boolean] = Opt.empty,
  completionItem: Opt[CompletionRegistrationOptions.CompletionItem] = Opt.empty
)
object CompletionRegistrationOptions:
  given reader: Reader[structures.CompletionRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.CompletionRegistrationOptions] = upickle.default.macroW
  case class CompletionItem(
    labelDetailsSupport: Opt[Boolean] = Opt.empty
  )
  object CompletionItem:
    given reader: Reader[structures.CompletionRegistrationOptions.CompletionItem] = Pickle.macroR
    given writer: Writer[structures.CompletionRegistrationOptions.CompletionItem] = upickle.default.macroW

case class HoverParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object HoverParams:
  given reader: Reader[structures.HoverParams] = Pickle.macroR
  given writer: Writer[structures.HoverParams] = upickle.default.macroW

case class Hover(
  contents: (structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString]),
  range: Opt[structures.Range] = Opt.empty
)
object Hover:
  private given rd0: Reader[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])] = 
    badMerge[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])](structures.MarkupContent.reader, aliases.MarkedString.reader, upickle.default.reader[Vector[aliases.MarkedString]])
  private given wt0: Writer[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])] = 
    upickle.default.writer[ujson.Value].comap[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])] { _v => 
      (_v: @unchecked) match 
        case v: structures.MarkupContent => writeJs[structures.MarkupContent](v)
        case v: aliases.MarkedString => writeJs[aliases.MarkedString](v)
        case v: Vector[?] => writeJs[Vector[aliases.MarkedString]](v.asInstanceOf[Vector[aliases.MarkedString]])
    }
  given reader: Reader[structures.Hover] = Pickle.macroR
  given writer: Writer[structures.Hover] = upickle.default.macroW

case class HoverRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector]
)
object HoverRegistrationOptions:
  given reader: Reader[structures.HoverRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.HoverRegistrationOptions] = upickle.default.macroW

case class SignatureHelpParams(
  context: Opt[structures.SignatureHelpContext] = Opt.empty,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object SignatureHelpParams:
  given reader: Reader[structures.SignatureHelpParams] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpParams] = upickle.default.macroW

case class SignatureHelp(
  signatures: Vector[structures.SignatureInformation],
  activeSignature: Opt[RuntimeBase.uinteger] = Opt.empty,
  activeParameter: Opt[RuntimeBase.uinteger] = Opt.empty
)
object SignatureHelp:
  given reader: Reader[structures.SignatureHelp] = Pickle.macroR
  given writer: Writer[structures.SignatureHelp] = upickle.default.macroW

case class SignatureHelpRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  triggerCharacters: Opt[Vector[String]] = Opt.empty,
  retriggerCharacters: Opt[Vector[String]] = Opt.empty
)
object SignatureHelpRegistrationOptions:
  given reader: Reader[structures.SignatureHelpRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpRegistrationOptions] = upickle.default.macroW

case class DefinitionParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object DefinitionParams:
  given reader: Reader[structures.DefinitionParams] = Pickle.macroR
  given writer: Writer[structures.DefinitionParams] = upickle.default.macroW

case class DefinitionRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector]
)
object DefinitionRegistrationOptions:
  given reader: Reader[structures.DefinitionRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DefinitionRegistrationOptions] = upickle.default.macroW

case class ReferenceParams(
  context: structures.ReferenceContext,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object ReferenceParams:
  given reader: Reader[structures.ReferenceParams] = Pickle.macroR
  given writer: Writer[structures.ReferenceParams] = upickle.default.macroW

case class ReferenceRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector]
)
object ReferenceRegistrationOptions:
  given reader: Reader[structures.ReferenceRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.ReferenceRegistrationOptions] = upickle.default.macroW

case class DocumentHighlightParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object DocumentHighlightParams:
  given reader: Reader[structures.DocumentHighlightParams] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlightParams] = upickle.default.macroW

case class DocumentHighlight(
  range: structures.Range,
  kind: Opt[enumerations.DocumentHighlightKind] = Opt.empty
)
object DocumentHighlight:
  given reader: Reader[structures.DocumentHighlight] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlight] = upickle.default.macroW

case class DocumentHighlightRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector]
)
object DocumentHighlightRegistrationOptions:
  given reader: Reader[structures.DocumentHighlightRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlightRegistrationOptions] = upickle.default.macroW

case class DocumentSymbolParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object DocumentSymbolParams:
  given reader: Reader[structures.DocumentSymbolParams] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbolParams] = upickle.default.macroW

case class SymbolInformation(
  deprecated: Opt[Boolean] = Opt.empty,
  location: structures.Location,
  name: String,
  kind: enumerations.SymbolKind,
  tags: Opt[Vector[enumerations.SymbolTag]] = Opt.empty,
  containerName: Opt[String] = Opt.empty
)
object SymbolInformation:
  given reader: Reader[structures.SymbolInformation] = Pickle.macroR
  given writer: Writer[structures.SymbolInformation] = upickle.default.macroW

case class DocumentSymbol(
  name: String,
  detail: Opt[String] = Opt.empty,
  kind: enumerations.SymbolKind,
  tags: Opt[Vector[enumerations.SymbolTag]] = Opt.empty,
  deprecated: Opt[Boolean] = Opt.empty,
  range: structures.Range,
  selectionRange: structures.Range,
  children: Opt[Vector[structures.DocumentSymbol]] = Opt.empty
)
object DocumentSymbol:
  given reader: Reader[structures.DocumentSymbol] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbol] = upickle.default.macroW

case class DocumentSymbolRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  label: Opt[String] = Opt.empty
)
object DocumentSymbolRegistrationOptions:
  given reader: Reader[structures.DocumentSymbolRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbolRegistrationOptions] = upickle.default.macroW

case class CodeActionParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  context: structures.CodeActionContext,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object CodeActionParams:
  given reader: Reader[structures.CodeActionParams] = Pickle.macroR
  given writer: Writer[structures.CodeActionParams] = upickle.default.macroW

case class Command(
  title: String,
  command: String,
  arguments: Opt[Vector[ujson.Value]] = Opt.empty
)
object Command:
  given reader: Reader[structures.Command] = Pickle.macroR
  given writer: Writer[structures.Command] = upickle.default.macroW

case class CodeAction(
  title: String,
  kind: Opt[enumerations.CodeActionKind] = Opt.empty,
  diagnostics: Opt[Vector[structures.Diagnostic]] = Opt.empty,
  isPreferred: Opt[Boolean] = Opt.empty,
  disabled: Opt[CodeAction.Disabled] = Opt.empty,
  edit: Opt[structures.WorkspaceEdit] = Opt.empty,
  command: Opt[structures.Command] = Opt.empty,
  data: Opt[ujson.Value] = Opt.empty
)
object CodeAction:
  given reader: Reader[structures.CodeAction] = Pickle.macroR
  given writer: Writer[structures.CodeAction] = upickle.default.macroW
  case class Disabled(
    reason: String
  )
  object Disabled:
    given reader: Reader[structures.CodeAction.Disabled] = Pickle.macroR
    given writer: Writer[structures.CodeAction.Disabled] = upickle.default.macroW

case class CodeActionRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  codeActionKinds: Opt[Vector[enumerations.CodeActionKind]] = Opt.empty,
  resolveProvider: Opt[Boolean] = Opt.empty
)
object CodeActionRegistrationOptions:
  given reader: Reader[structures.CodeActionRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.CodeActionRegistrationOptions] = upickle.default.macroW

case class WorkspaceSymbolParams(
  query: String,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object WorkspaceSymbolParams:
  given reader: Reader[structures.WorkspaceSymbolParams] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbolParams] = upickle.default.macroW

case class WorkspaceSymbol(
  location: (structures.Location | WorkspaceSymbol.S0),
  data: Opt[ujson.Value] = Opt.empty,
  name: String,
  kind: enumerations.SymbolKind,
  tags: Opt[Vector[enumerations.SymbolTag]] = Opt.empty,
  containerName: Opt[String] = Opt.empty
)
object WorkspaceSymbol:
  private given rd0: Reader[(structures.Location | WorkspaceSymbol.S0)] = 
    badMerge[(structures.Location | WorkspaceSymbol.S0)](structures.Location.reader, WorkspaceSymbol.S0.reader)
  private given wt0: Writer[(structures.Location | WorkspaceSymbol.S0)] = 
    upickle.default.writer[ujson.Value].comap[(structures.Location | WorkspaceSymbol.S0)] { _v => 
      (_v: @unchecked) match 
        case v: structures.Location => writeJs[structures.Location](v)
        case v: WorkspaceSymbol.S0 => writeJs[WorkspaceSymbol.S0](v)
    }
  given reader: Reader[structures.WorkspaceSymbol] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbol] = upickle.default.macroW
  case class S0(
    uri: RuntimeBase.DocumentUri
  )
  object S0:
    given reader: Reader[structures.WorkspaceSymbol.S0] = Pickle.macroR
    given writer: Writer[structures.WorkspaceSymbol.S0] = upickle.default.macroW

case class WorkspaceSymbolRegistrationOptions(
  resolveProvider: Opt[Boolean] = Opt.empty
)
object WorkspaceSymbolRegistrationOptions:
  given reader: Reader[structures.WorkspaceSymbolRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbolRegistrationOptions] = upickle.default.macroW

case class CodeLensParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object CodeLensParams:
  given reader: Reader[structures.CodeLensParams] = Pickle.macroR
  given writer: Writer[structures.CodeLensParams] = upickle.default.macroW

case class CodeLens(
  range: structures.Range,
  command: Opt[structures.Command] = Opt.empty,
  data: Opt[ujson.Value] = Opt.empty
)
object CodeLens:
  given reader: Reader[structures.CodeLens] = Pickle.macroR
  given writer: Writer[structures.CodeLens] = upickle.default.macroW

case class CodeLensRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  resolveProvider: Opt[Boolean] = Opt.empty
)
object CodeLensRegistrationOptions:
  given reader: Reader[structures.CodeLensRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.CodeLensRegistrationOptions] = upickle.default.macroW

case class DocumentLinkParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object DocumentLinkParams:
  given reader: Reader[structures.DocumentLinkParams] = Pickle.macroR
  given writer: Writer[structures.DocumentLinkParams] = upickle.default.macroW

case class DocumentLink(
  range: structures.Range,
  target: Opt[String] = Opt.empty,
  tooltip: Opt[String] = Opt.empty,
  data: Opt[ujson.Value] = Opt.empty
)
object DocumentLink:
  given reader: Reader[structures.DocumentLink] = Pickle.macroR
  given writer: Writer[structures.DocumentLink] = upickle.default.macroW

case class DocumentLinkRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  resolveProvider: Opt[Boolean] = Opt.empty
)
object DocumentLinkRegistrationOptions:
  given reader: Reader[structures.DocumentLinkRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentLinkRegistrationOptions] = upickle.default.macroW

case class DocumentFormattingParams(
  textDocument: structures.TextDocumentIdentifier,
  options: structures.FormattingOptions,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object DocumentFormattingParams:
  given reader: Reader[structures.DocumentFormattingParams] = Pickle.macroR
  given writer: Writer[structures.DocumentFormattingParams] = upickle.default.macroW

case class DocumentFormattingRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector]
)
object DocumentFormattingRegistrationOptions:
  given reader: Reader[structures.DocumentFormattingRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentFormattingRegistrationOptions] = upickle.default.macroW

case class DocumentRangeFormattingParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  options: structures.FormattingOptions,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object DocumentRangeFormattingParams:
  given reader: Reader[structures.DocumentRangeFormattingParams] = Pickle.macroR
  given writer: Writer[structures.DocumentRangeFormattingParams] = upickle.default.macroW

case class DocumentRangeFormattingRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector]
)
object DocumentRangeFormattingRegistrationOptions:
  given reader: Reader[structures.DocumentRangeFormattingRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentRangeFormattingRegistrationOptions] = upickle.default.macroW

case class DocumentOnTypeFormattingParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  ch: String,
  options: structures.FormattingOptions
)
object DocumentOnTypeFormattingParams:
  given reader: Reader[structures.DocumentOnTypeFormattingParams] = Pickle.macroR
  given writer: Writer[structures.DocumentOnTypeFormattingParams] = upickle.default.macroW

case class DocumentOnTypeFormattingRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  firstTriggerCharacter: String,
  moreTriggerCharacter: Opt[Vector[String]] = Opt.empty
)
object DocumentOnTypeFormattingRegistrationOptions:
  given reader: Reader[structures.DocumentOnTypeFormattingRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentOnTypeFormattingRegistrationOptions] = upickle.default.macroW

case class RenameParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  newName: String,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object RenameParams:
  given reader: Reader[structures.RenameParams] = Pickle.macroR
  given writer: Writer[structures.RenameParams] = upickle.default.macroW

case class RenameRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  prepareProvider: Opt[Boolean] = Opt.empty
)
object RenameRegistrationOptions:
  given reader: Reader[structures.RenameRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.RenameRegistrationOptions] = upickle.default.macroW

case class PrepareRenameParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object PrepareRenameParams:
  given reader: Reader[structures.PrepareRenameParams] = Pickle.macroR
  given writer: Writer[structures.PrepareRenameParams] = upickle.default.macroW

case class ExecuteCommandParams(
  command: String,
  arguments: Opt[Vector[ujson.Value]] = Opt.empty,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object ExecuteCommandParams:
  given reader: Reader[structures.ExecuteCommandParams] = Pickle.macroR
  given writer: Writer[structures.ExecuteCommandParams] = upickle.default.macroW

case class ExecuteCommandRegistrationOptions(
  commands: Vector[String]
)
object ExecuteCommandRegistrationOptions:
  given reader: Reader[structures.ExecuteCommandRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.ExecuteCommandRegistrationOptions] = upickle.default.macroW

case class ApplyWorkspaceEditParams(
  label: Opt[String] = Opt.empty,
  edit: structures.WorkspaceEdit
)
object ApplyWorkspaceEditParams:
  given reader: Reader[structures.ApplyWorkspaceEditParams] = Pickle.macroR
  given writer: Writer[structures.ApplyWorkspaceEditParams] = upickle.default.macroW

case class ApplyWorkspaceEditResult(
  applied: Boolean,
  failureReason: Opt[String] = Opt.empty,
  failedChange: Opt[RuntimeBase.uinteger] = Opt.empty
)
object ApplyWorkspaceEditResult:
  given reader: Reader[structures.ApplyWorkspaceEditResult] = Pickle.macroR
  given writer: Writer[structures.ApplyWorkspaceEditResult] = upickle.default.macroW

case class WorkDoneProgressBegin(
  kind: "begin",
  title: String,
  cancellable: Opt[Boolean] = Opt.empty,
  message: Opt[String] = Opt.empty,
  percentage: Opt[RuntimeBase.uinteger] = Opt.empty
)
object WorkDoneProgressBegin:
  given reader: Reader[structures.WorkDoneProgressBegin] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressBegin] = upickle.default.macroW

case class WorkDoneProgressReport(
  kind: "report",
  cancellable: Opt[Boolean] = Opt.empty,
  message: Opt[String] = Opt.empty,
  percentage: Opt[RuntimeBase.uinteger] = Opt.empty
)
object WorkDoneProgressReport:
  given reader: Reader[structures.WorkDoneProgressReport] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressReport] = upickle.default.macroW

case class WorkDoneProgressEnd(
  kind: "end",
  message: Opt[String] = Opt.empty
)
object WorkDoneProgressEnd:
  given reader: Reader[structures.WorkDoneProgressEnd] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressEnd] = upickle.default.macroW

case class SetTraceParams(
  value: enumerations.TraceValues
)
object SetTraceParams:
  given reader: Reader[structures.SetTraceParams] = Pickle.macroR
  given writer: Writer[structures.SetTraceParams] = upickle.default.macroW

case class LogTraceParams(
  message: String,
  verbose: Opt[String] = Opt.empty
)
object LogTraceParams:
  given reader: Reader[structures.LogTraceParams] = Pickle.macroR
  given writer: Writer[structures.LogTraceParams] = upickle.default.macroW

case class CancelParams(
  id: (Int | String)
)
object CancelParams:
  private given rd0: Reader[(Int | String)] = 
    badMerge[(Int | String)](intCodec, stringCodec)
  private given wt0: Writer[(Int | String)] = 
    upickle.default.writer[ujson.Value].comap[(Int | String)] { _v => 
      (_v: @unchecked) match 
        case v: Int => writeJs[Int](v)
        case v: String => writeJs[String](v)
    }
  given reader: Reader[structures.CancelParams] = Pickle.macroR
  given writer: Writer[structures.CancelParams] = upickle.default.macroW

case class ProgressParams(
  token: aliases.ProgressToken,
  value: ujson.Value
)
object ProgressParams:
  given reader: Reader[structures.ProgressParams] = Pickle.macroR
  given writer: Writer[structures.ProgressParams] = upickle.default.macroW

case class TextDocumentPositionParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position
)
object TextDocumentPositionParams:
  given reader: Reader[structures.TextDocumentPositionParams] = Pickle.macroR
  given writer: Writer[structures.TextDocumentPositionParams] = upickle.default.macroW

case class WorkDoneProgressParams(
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object WorkDoneProgressParams:
  given reader: Reader[structures.WorkDoneProgressParams] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressParams] = upickle.default.macroW

case class LocationLink(
  originSelectionRange: Opt[structures.Range] = Opt.empty,
  targetUri: RuntimeBase.DocumentUri,
  targetRange: structures.Range,
  targetSelectionRange: structures.Range
)
object LocationLink:
  given reader: Reader[structures.LocationLink] = Pickle.macroR
  given writer: Writer[structures.LocationLink] = upickle.default.macroW

case class Range(
  start: structures.Position,
  end: structures.Position
)
object Range:
  given reader: Reader[structures.Range] = Pickle.macroR
  given writer: Writer[structures.Range] = upickle.default.macroW

case class ImplementationOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object ImplementationOptions:
  given reader: Reader[structures.ImplementationOptions] = Pickle.macroR
  given writer: Writer[structures.ImplementationOptions] = upickle.default.macroW

case class StaticRegistrationOptions(
  id: Opt[String] = Opt.empty
)
object StaticRegistrationOptions:
  given reader: Reader[structures.StaticRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.StaticRegistrationOptions] = upickle.default.macroW

case class TypeDefinitionOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object TypeDefinitionOptions:
  given reader: Reader[structures.TypeDefinitionOptions] = Pickle.macroR
  given writer: Writer[structures.TypeDefinitionOptions] = upickle.default.macroW

case class WorkspaceFoldersChangeEvent(
  added: Vector[structures.WorkspaceFolder],
  removed: Vector[structures.WorkspaceFolder]
)
object WorkspaceFoldersChangeEvent:
  given reader: Reader[structures.WorkspaceFoldersChangeEvent] = Pickle.macroR
  given writer: Writer[structures.WorkspaceFoldersChangeEvent] = upickle.default.macroW

case class ConfigurationItem(
  scopeUri: Opt[String] = Opt.empty,
  section: Opt[String] = Opt.empty
)
object ConfigurationItem:
  given reader: Reader[structures.ConfigurationItem] = Pickle.macroR
  given writer: Writer[structures.ConfigurationItem] = upickle.default.macroW

case class TextDocumentIdentifier(
  uri: RuntimeBase.DocumentUri
)
object TextDocumentIdentifier:
  given reader: Reader[structures.TextDocumentIdentifier] = Pickle.macroR
  given writer: Writer[structures.TextDocumentIdentifier] = upickle.default.macroW

case class Color(
  red: Float,
  green: Float,
  blue: Float,
  alpha: Float
)
object Color:
  given reader: Reader[structures.Color] = Pickle.macroR
  given writer: Writer[structures.Color] = upickle.default.macroW

case class DocumentColorOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object DocumentColorOptions:
  given reader: Reader[structures.DocumentColorOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentColorOptions] = upickle.default.macroW

case class FoldingRangeOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object FoldingRangeOptions:
  given reader: Reader[structures.FoldingRangeOptions] = Pickle.macroR
  given writer: Writer[structures.FoldingRangeOptions] = upickle.default.macroW

case class DeclarationOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object DeclarationOptions:
  given reader: Reader[structures.DeclarationOptions] = Pickle.macroR
  given writer: Writer[structures.DeclarationOptions] = upickle.default.macroW

case class Position(
  line: RuntimeBase.uinteger,
  character: RuntimeBase.uinteger
)
object Position:
  given reader: Reader[structures.Position] = Pickle.macroR
  given writer: Writer[structures.Position] = upickle.default.macroW

case class SelectionRangeOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object SelectionRangeOptions:
  given reader: Reader[structures.SelectionRangeOptions] = Pickle.macroR
  given writer: Writer[structures.SelectionRangeOptions] = upickle.default.macroW

case class CallHierarchyOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object CallHierarchyOptions:
  given reader: Reader[structures.CallHierarchyOptions] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyOptions] = upickle.default.macroW

case class SemanticTokensOptions(
  legend: structures.SemanticTokensLegend,
  range: Opt[(Boolean | SemanticTokensOptions.S0)] = Opt.empty,
  full: Opt[(Boolean | SemanticTokensOptions.S1)] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object SemanticTokensOptions:
  private given rd0: Reader[(Boolean | SemanticTokensOptions.S0)] = 
    badMerge[(Boolean | SemanticTokensOptions.S0)](upickle.default.reader[Boolean], SemanticTokensOptions.S0.reader)
  private given wt0: Writer[(Boolean | SemanticTokensOptions.S0)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | SemanticTokensOptions.S0)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: SemanticTokensOptions.S0 => writeJs[SemanticTokensOptions.S0](v)
    }
  private given rd1: Reader[(Boolean | SemanticTokensOptions.S1)] = 
    badMerge[(Boolean | SemanticTokensOptions.S1)](upickle.default.reader[Boolean], SemanticTokensOptions.S1.reader)
  private given wt1: Writer[(Boolean | SemanticTokensOptions.S1)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | SemanticTokensOptions.S1)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: SemanticTokensOptions.S1 => writeJs[SemanticTokensOptions.S1](v)
    }
  given reader: Reader[structures.SemanticTokensOptions] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensOptions] = upickle.default.macroW
  case class S0(
  )
  object S0:
    given reader: Reader[structures.SemanticTokensOptions.S0] = Pickle.macroR
    given writer: Writer[structures.SemanticTokensOptions.S0] = upickle.default.macroW
  case class S1(
    delta: Opt[Boolean] = Opt.empty
  )
  object S1:
    given reader: Reader[structures.SemanticTokensOptions.S1] = Pickle.macroR
    given writer: Writer[structures.SemanticTokensOptions.S1] = upickle.default.macroW

case class SemanticTokensEdit(
  start: RuntimeBase.uinteger,
  deleteCount: RuntimeBase.uinteger,
  data: Opt[Vector[RuntimeBase.uinteger]] = Opt.empty
)
object SemanticTokensEdit:
  given reader: Reader[structures.SemanticTokensEdit] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensEdit] = upickle.default.macroW

case class LinkedEditingRangeOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object LinkedEditingRangeOptions:
  given reader: Reader[structures.LinkedEditingRangeOptions] = Pickle.macroR
  given writer: Writer[structures.LinkedEditingRangeOptions] = upickle.default.macroW

case class FileCreate(
  uri: String
)
object FileCreate:
  given reader: Reader[structures.FileCreate] = Pickle.macroR
  given writer: Writer[structures.FileCreate] = upickle.default.macroW

case class TextDocumentEdit(
  textDocument: structures.OptionalVersionedTextDocumentIdentifier,
  edits: Vector[(structures.TextEdit | structures.AnnotatedTextEdit)]
)
object TextDocumentEdit:
  private given rd0: Reader[(structures.TextEdit | structures.AnnotatedTextEdit)] = 
    badMerge[(structures.TextEdit | structures.AnnotatedTextEdit)](structures.TextEdit.reader, structures.AnnotatedTextEdit.reader)
  private given wt0: Writer[(structures.TextEdit | structures.AnnotatedTextEdit)] = 
    upickle.default.writer[ujson.Value].comap[(structures.TextEdit | structures.AnnotatedTextEdit)] { _v => 
      (_v: @unchecked) match 
        case v: structures.TextEdit => writeJs[structures.TextEdit](v)
        case v: structures.AnnotatedTextEdit => writeJs[structures.AnnotatedTextEdit](v)
    }
  given reader: Reader[structures.TextDocumentEdit] = Pickle.macroR
  given writer: Writer[structures.TextDocumentEdit] = upickle.default.macroW

case class CreateFile(
  kind: "create",
  uri: RuntimeBase.DocumentUri,
  options: Opt[structures.CreateFileOptions] = Opt.empty,
  annotationId: Opt[aliases.ChangeAnnotationIdentifier] = Opt.empty
)
object CreateFile:
  given reader: Reader[structures.CreateFile] = Pickle.macroR
  given writer: Writer[structures.CreateFile] = upickle.default.macroW

case class RenameFile(
  kind: "rename",
  oldUri: RuntimeBase.DocumentUri,
  newUri: RuntimeBase.DocumentUri,
  options: Opt[structures.RenameFileOptions] = Opt.empty,
  annotationId: Opt[aliases.ChangeAnnotationIdentifier] = Opt.empty
)
object RenameFile:
  given reader: Reader[structures.RenameFile] = Pickle.macroR
  given writer: Writer[structures.RenameFile] = upickle.default.macroW

case class DeleteFile(
  kind: "delete",
  uri: RuntimeBase.DocumentUri,
  options: Opt[structures.DeleteFileOptions] = Opt.empty,
  annotationId: Opt[aliases.ChangeAnnotationIdentifier] = Opt.empty
)
object DeleteFile:
  given reader: Reader[structures.DeleteFile] = Pickle.macroR
  given writer: Writer[structures.DeleteFile] = upickle.default.macroW

case class ChangeAnnotation(
  label: String,
  needsConfirmation: Opt[Boolean] = Opt.empty,
  description: Opt[String] = Opt.empty
)
object ChangeAnnotation:
  given reader: Reader[structures.ChangeAnnotation] = Pickle.macroR
  given writer: Writer[structures.ChangeAnnotation] = upickle.default.macroW

case class FileOperationFilter(
  scheme: Opt[String] = Opt.empty,
  pattern: structures.FileOperationPattern
)
object FileOperationFilter:
  given reader: Reader[structures.FileOperationFilter] = Pickle.macroR
  given writer: Writer[structures.FileOperationFilter] = upickle.default.macroW

case class FileRename(
  oldUri: String,
  newUri: String
)
object FileRename:
  given reader: Reader[structures.FileRename] = Pickle.macroR
  given writer: Writer[structures.FileRename] = upickle.default.macroW

case class FileDelete(
  uri: String
)
object FileDelete:
  given reader: Reader[structures.FileDelete] = Pickle.macroR
  given writer: Writer[structures.FileDelete] = upickle.default.macroW

case class MonikerOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object MonikerOptions:
  given reader: Reader[structures.MonikerOptions] = Pickle.macroR
  given writer: Writer[structures.MonikerOptions] = upickle.default.macroW

case class TypeHierarchyOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object TypeHierarchyOptions:
  given reader: Reader[structures.TypeHierarchyOptions] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchyOptions] = upickle.default.macroW

case class InlineValueContext(
  frameId: Int,
  stoppedLocation: structures.Range
)
object InlineValueContext:
  given reader: Reader[structures.InlineValueContext] = Pickle.macroR
  given writer: Writer[structures.InlineValueContext] = upickle.default.macroW

case class InlineValueText(
  range: structures.Range,
  text: String
)
object InlineValueText:
  given reader: Reader[structures.InlineValueText] = Pickle.macroR
  given writer: Writer[structures.InlineValueText] = upickle.default.macroW

case class InlineValueVariableLookup(
  range: structures.Range,
  variableName: Opt[String] = Opt.empty,
  caseSensitiveLookup: Boolean
)
object InlineValueVariableLookup:
  given reader: Reader[structures.InlineValueVariableLookup] = Pickle.macroR
  given writer: Writer[structures.InlineValueVariableLookup] = upickle.default.macroW

case class InlineValueEvaluatableExpression(
  range: structures.Range,
  expression: Opt[String] = Opt.empty
)
object InlineValueEvaluatableExpression:
  given reader: Reader[structures.InlineValueEvaluatableExpression] = Pickle.macroR
  given writer: Writer[structures.InlineValueEvaluatableExpression] = upickle.default.macroW

case class InlineValueOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object InlineValueOptions:
  given reader: Reader[structures.InlineValueOptions] = Pickle.macroR
  given writer: Writer[structures.InlineValueOptions] = upickle.default.macroW

case class InlayHintLabelPart(
  value: String,
  tooltip: Opt[(String | structures.MarkupContent)] = Opt.empty,
  location: Opt[structures.Location] = Opt.empty,
  command: Opt[structures.Command] = Opt.empty
)
object InlayHintLabelPart:
  private given rd0: Reader[(String | structures.MarkupContent)] = 
    badMerge[(String | structures.MarkupContent)](stringCodec, structures.MarkupContent.reader)
  private given wt0: Writer[(String | structures.MarkupContent)] = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: structures.MarkupContent => writeJs[structures.MarkupContent](v)
    }
  given reader: Reader[structures.InlayHintLabelPart] = Pickle.macroR
  given writer: Writer[structures.InlayHintLabelPart] = upickle.default.macroW

case class MarkupContent(
  kind: enumerations.MarkupKind,
  value: String
)
object MarkupContent:
  given reader: Reader[structures.MarkupContent] = Pickle.macroR
  given writer: Writer[structures.MarkupContent] = upickle.default.macroW

case class InlayHintOptions(
  resolveProvider: Opt[Boolean] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object InlayHintOptions:
  given reader: Reader[structures.InlayHintOptions] = Pickle.macroR
  given writer: Writer[structures.InlayHintOptions] = upickle.default.macroW

case class RelatedFullDocumentDiagnosticReport(
  relatedDocuments: Opt[Map[RuntimeBase.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)]] = Opt.empty,
  kind: "full",
  resultId: Opt[String] = Opt.empty,
  items: Vector[structures.Diagnostic]
)
object RelatedFullDocumentDiagnosticReport:
  private given rd0: Reader[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = 
    badMerge[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)](structures.FullDocumentDiagnosticReport.reader, structures.UnchangedDocumentDiagnosticReport.reader)
  private given wt0: Writer[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = 
    upickle.default.writer[ujson.Value].comap[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] { _v => 
      (_v: @unchecked) match 
        case v: structures.FullDocumentDiagnosticReport => writeJs[structures.FullDocumentDiagnosticReport](v)
        case v: structures.UnchangedDocumentDiagnosticReport => writeJs[structures.UnchangedDocumentDiagnosticReport](v)
    }
  given reader: Reader[structures.RelatedFullDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.RelatedFullDocumentDiagnosticReport] = upickle.default.macroW

case class RelatedUnchangedDocumentDiagnosticReport(
  relatedDocuments: Opt[Map[RuntimeBase.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)]] = Opt.empty,
  kind: "unchanged",
  resultId: String
)
object RelatedUnchangedDocumentDiagnosticReport:
  private given rd0: Reader[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = 
    badMerge[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)](structures.FullDocumentDiagnosticReport.reader, structures.UnchangedDocumentDiagnosticReport.reader)
  private given wt0: Writer[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = 
    upickle.default.writer[ujson.Value].comap[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] { _v => 
      (_v: @unchecked) match 
        case v: structures.FullDocumentDiagnosticReport => writeJs[structures.FullDocumentDiagnosticReport](v)
        case v: structures.UnchangedDocumentDiagnosticReport => writeJs[structures.UnchangedDocumentDiagnosticReport](v)
    }
  given reader: Reader[structures.RelatedUnchangedDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.RelatedUnchangedDocumentDiagnosticReport] = upickle.default.macroW

case class FullDocumentDiagnosticReport(
  kind: "full",
  resultId: Opt[String] = Opt.empty,
  items: Vector[structures.Diagnostic]
)
object FullDocumentDiagnosticReport:
  given reader: Reader[structures.FullDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.FullDocumentDiagnosticReport] = upickle.default.macroW

case class UnchangedDocumentDiagnosticReport(
  kind: "unchanged",
  resultId: String
)
object UnchangedDocumentDiagnosticReport:
  given reader: Reader[structures.UnchangedDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.UnchangedDocumentDiagnosticReport] = upickle.default.macroW

case class DiagnosticOptions(
  identifier: Opt[String] = Opt.empty,
  interFileDependencies: Boolean,
  workspaceDiagnostics: Boolean,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object DiagnosticOptions:
  given reader: Reader[structures.DiagnosticOptions] = Pickle.macroR
  given writer: Writer[structures.DiagnosticOptions] = upickle.default.macroW

case class PreviousResultId(
  uri: RuntimeBase.DocumentUri,
  value: String
)
object PreviousResultId:
  given reader: Reader[structures.PreviousResultId] = Pickle.macroR
  given writer: Writer[structures.PreviousResultId] = upickle.default.macroW

case class NotebookDocument(
  uri: aliases.URI,
  notebookType: String,
  version: Int,
  metadata: Opt[structures.LSPObject] = Opt.empty,
  cells: Vector[structures.NotebookCell]
)
object NotebookDocument:
  given reader: Reader[structures.NotebookDocument] = Pickle.macroR
  given writer: Writer[structures.NotebookDocument] = upickle.default.macroW

case class TextDocumentItem(
  uri: RuntimeBase.DocumentUri,
  languageId: String,
  version: Int,
  text: String
)
object TextDocumentItem:
  given reader: Reader[structures.TextDocumentItem] = Pickle.macroR
  given writer: Writer[structures.TextDocumentItem] = upickle.default.macroW

case class VersionedNotebookDocumentIdentifier(
  version: Int,
  uri: aliases.URI
)
object VersionedNotebookDocumentIdentifier:
  given reader: Reader[structures.VersionedNotebookDocumentIdentifier] = Pickle.macroR
  given writer: Writer[structures.VersionedNotebookDocumentIdentifier] = upickle.default.macroW

case class NotebookDocumentChangeEvent(
  metadata: Opt[structures.LSPObject] = Opt.empty,
  cells: Opt[NotebookDocumentChangeEvent.Cells] = Opt.empty
)
object NotebookDocumentChangeEvent:
  given reader: Reader[structures.NotebookDocumentChangeEvent] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentChangeEvent] = upickle.default.macroW
  case class Cells(
    structure: Opt[Cells.Structure] = Opt.empty,
    data: Opt[Vector[structures.NotebookCell]] = Opt.empty,
    textContent: Opt[Vector[Cells.S0]] = Opt.empty
  )
  object Cells:
    given reader: Reader[structures.NotebookDocumentChangeEvent.Cells] = Pickle.macroR
    given writer: Writer[structures.NotebookDocumentChangeEvent.Cells] = upickle.default.macroW
    case class Structure(
      array: structures.NotebookCellArrayChange,
      didOpen: Opt[Vector[structures.TextDocumentItem]] = Opt.empty,
      didClose: Opt[Vector[structures.TextDocumentIdentifier]] = Opt.empty
    )
    object Structure:
      given reader: Reader[structures.NotebookDocumentChangeEvent.Cells.Structure] = Pickle.macroR
      given writer: Writer[structures.NotebookDocumentChangeEvent.Cells.Structure] = upickle.default.macroW
    case class S0(
      document: structures.VersionedTextDocumentIdentifier,
      changes: Vector[aliases.TextDocumentContentChangeEvent]
    )
    object S0:
      given reader: Reader[structures.NotebookDocumentChangeEvent.Cells.S0] = Pickle.macroR
      given writer: Writer[structures.NotebookDocumentChangeEvent.Cells.S0] = upickle.default.macroW

case class NotebookDocumentIdentifier(
  uri: aliases.URI
)
object NotebookDocumentIdentifier:
  given reader: Reader[structures.NotebookDocumentIdentifier] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentIdentifier] = upickle.default.macroW

case class Registration(
  id: String,
  method: String,
  registerOptions: Opt[ujson.Value] = Opt.empty
)
object Registration:
  given reader: Reader[structures.Registration] = Pickle.macroR
  given writer: Writer[structures.Registration] = upickle.default.macroW

case class Unregistration(
  id: String,
  method: String
)
object Unregistration:
  given reader: Reader[structures.Unregistration] = Pickle.macroR
  given writer: Writer[structures.Unregistration] = upickle.default.macroW

case class _InitializeParams(
  processId: Nullable[Int],
  clientInfo: Opt[_InitializeParams.ClientInfo] = Opt.empty,
  locale: Opt[String] = Opt.empty,
  rootPath: Opt[Nullable[String]] = Opt.empty,
  rootUri: Nullable[RuntimeBase.DocumentUri],
  capabilities: structures.ClientCapabilities,
  initializationOptions: Opt[ujson.Value] = Opt.empty,
  trace: Opt[("off" | "messages" | "compact" | "verbose")] = Opt.empty,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object _InitializeParams:
  private given rd3: Reader[("off" | "messages" | "compact" | "verbose")] = 
    badMerge[("off" | "messages" | "compact" | "verbose")](upickle.default.reader["off"], upickle.default.reader["messages"], upickle.default.reader["compact"], upickle.default.reader["verbose"])
  private given wt3: Writer[("off" | "messages" | "compact" | "verbose")] = 
    upickle.default.writer[ujson.Value].comap[("off" | "messages" | "compact" | "verbose")] { _v => 
      (_v: @unchecked) match 
        case v: "off" => writeJs["off"](v)
        case v: "messages" => writeJs["messages"](v)
        case v: "compact" => writeJs["compact"](v)
        case v: "verbose" => writeJs["verbose"](v)
    }
  given reader: Reader[structures._InitializeParams] = Pickle.macroR
  given writer: Writer[structures._InitializeParams] = upickle.default.macroW
  case class ClientInfo(
    name: String,
    version: Opt[String] = Opt.empty
  )
  object ClientInfo:
    given reader: Reader[structures._InitializeParams.ClientInfo] = Pickle.macroR
    given writer: Writer[structures._InitializeParams.ClientInfo] = upickle.default.macroW

case class WorkspaceFoldersInitializeParams(
  workspaceFolders: Opt[Nullable[Vector[structures.WorkspaceFolder]]] = Opt.empty
)
object WorkspaceFoldersInitializeParams:
  given reader: Reader[structures.WorkspaceFoldersInitializeParams] = Pickle.macroR
  given writer: Writer[structures.WorkspaceFoldersInitializeParams] = upickle.default.macroW

case class ServerCapabilities(
  positionEncoding: Opt[enumerations.PositionEncodingKind] = Opt.empty,
  textDocumentSync: Opt[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)] = Opt.empty,
  notebookDocumentSync: Opt[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)] = Opt.empty,
  completionProvider: Opt[structures.CompletionOptions] = Opt.empty,
  hoverProvider: Opt[(Boolean | structures.HoverOptions)] = Opt.empty,
  signatureHelpProvider: Opt[structures.SignatureHelpOptions] = Opt.empty,
  declarationProvider: Opt[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)] = Opt.empty,
  definitionProvider: Opt[(Boolean | structures.DefinitionOptions)] = Opt.empty,
  typeDefinitionProvider: Opt[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)] = Opt.empty,
  implementationProvider: Opt[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)] = Opt.empty,
  referencesProvider: Opt[(Boolean | structures.ReferenceOptions)] = Opt.empty,
  documentHighlightProvider: Opt[(Boolean | structures.DocumentHighlightOptions)] = Opt.empty,
  documentSymbolProvider: Opt[(Boolean | structures.DocumentSymbolOptions)] = Opt.empty,
  codeActionProvider: Opt[(Boolean | structures.CodeActionOptions)] = Opt.empty,
  codeLensProvider: Opt[structures.CodeLensOptions] = Opt.empty,
  documentLinkProvider: Opt[structures.DocumentLinkOptions] = Opt.empty,
  colorProvider: Opt[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)] = Opt.empty,
  workspaceSymbolProvider: Opt[(Boolean | structures.WorkspaceSymbolOptions)] = Opt.empty,
  documentFormattingProvider: Opt[(Boolean | structures.DocumentFormattingOptions)] = Opt.empty,
  documentRangeFormattingProvider: Opt[(Boolean | structures.DocumentRangeFormattingOptions)] = Opt.empty,
  documentOnTypeFormattingProvider: Opt[structures.DocumentOnTypeFormattingOptions] = Opt.empty,
  renameProvider: Opt[(Boolean | structures.RenameOptions)] = Opt.empty,
  foldingRangeProvider: Opt[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)] = Opt.empty,
  selectionRangeProvider: Opt[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)] = Opt.empty,
  executeCommandProvider: Opt[structures.ExecuteCommandOptions] = Opt.empty,
  callHierarchyProvider: Opt[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)] = Opt.empty,
  linkedEditingRangeProvider: Opt[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)] = Opt.empty,
  semanticTokensProvider: Opt[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)] = Opt.empty,
  monikerProvider: Opt[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)] = Opt.empty,
  typeHierarchyProvider: Opt[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)] = Opt.empty,
  inlineValueProvider: Opt[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)] = Opt.empty,
  inlayHintProvider: Opt[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)] = Opt.empty,
  diagnosticProvider: Opt[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)] = Opt.empty,
  workspace: Opt[ServerCapabilities.Workspace] = Opt.empty,
  experimental: Opt[structures.T] = Opt.empty
)
object ServerCapabilities:
  private given rd0: Reader[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)] = 
    badMerge[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)](structures.TextDocumentSyncOptions.reader, enumerations.TextDocumentSyncKind.reader)
  private given wt0: Writer[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)] = 
    upickle.default.writer[ujson.Value].comap[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)] { _v => 
      (_v: @unchecked) match 
        case v: structures.TextDocumentSyncOptions => writeJs[structures.TextDocumentSyncOptions](v)
        case v: enumerations.TextDocumentSyncKind => writeJs[enumerations.TextDocumentSyncKind](v)
    }
  private given rd1: Reader[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)] = 
    badMerge[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)](structures.NotebookDocumentSyncOptions.reader, structures.NotebookDocumentSyncRegistrationOptions.reader)
  private given wt1: Writer[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: structures.NotebookDocumentSyncOptions => writeJs[structures.NotebookDocumentSyncOptions](v)
        case v: structures.NotebookDocumentSyncRegistrationOptions => writeJs[structures.NotebookDocumentSyncRegistrationOptions](v)
    }
  private given rd2: Reader[(Boolean | structures.HoverOptions)] = 
    badMerge[(Boolean | structures.HoverOptions)](upickle.default.reader[Boolean], structures.HoverOptions.reader)
  private given wt2: Writer[(Boolean | structures.HoverOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.HoverOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.HoverOptions => writeJs[structures.HoverOptions](v)
    }
  private given rd3: Reader[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)] = 
    badMerge[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)](upickle.default.reader[Boolean], structures.DeclarationOptions.reader, structures.DeclarationRegistrationOptions.reader)
  private given wt3: Writer[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DeclarationOptions => writeJs[structures.DeclarationOptions](v)
        case v: structures.DeclarationRegistrationOptions => writeJs[structures.DeclarationRegistrationOptions](v)
    }
  private given rd4: Reader[(Boolean | structures.DefinitionOptions)] = 
    badMerge[(Boolean | structures.DefinitionOptions)](upickle.default.reader[Boolean], structures.DefinitionOptions.reader)
  private given wt4: Writer[(Boolean | structures.DefinitionOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DefinitionOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DefinitionOptions => writeJs[structures.DefinitionOptions](v)
    }
  private given rd5: Reader[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)] = 
    badMerge[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)](upickle.default.reader[Boolean], structures.TypeDefinitionOptions.reader, structures.TypeDefinitionRegistrationOptions.reader)
  private given wt5: Writer[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.TypeDefinitionOptions => writeJs[structures.TypeDefinitionOptions](v)
        case v: structures.TypeDefinitionRegistrationOptions => writeJs[structures.TypeDefinitionRegistrationOptions](v)
    }
  private given rd6: Reader[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)] = 
    badMerge[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)](upickle.default.reader[Boolean], structures.ImplementationOptions.reader, structures.ImplementationRegistrationOptions.reader)
  private given wt6: Writer[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.ImplementationOptions => writeJs[structures.ImplementationOptions](v)
        case v: structures.ImplementationRegistrationOptions => writeJs[structures.ImplementationRegistrationOptions](v)
    }
  private given rd7: Reader[(Boolean | structures.ReferenceOptions)] = 
    badMerge[(Boolean | structures.ReferenceOptions)](upickle.default.reader[Boolean], structures.ReferenceOptions.reader)
  private given wt7: Writer[(Boolean | structures.ReferenceOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.ReferenceOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.ReferenceOptions => writeJs[structures.ReferenceOptions](v)
    }
  private given rd8: Reader[(Boolean | structures.DocumentHighlightOptions)] = 
    badMerge[(Boolean | structures.DocumentHighlightOptions)](upickle.default.reader[Boolean], structures.DocumentHighlightOptions.reader)
  private given wt8: Writer[(Boolean | structures.DocumentHighlightOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentHighlightOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DocumentHighlightOptions => writeJs[structures.DocumentHighlightOptions](v)
    }
  private given rd9: Reader[(Boolean | structures.DocumentSymbolOptions)] = 
    badMerge[(Boolean | structures.DocumentSymbolOptions)](upickle.default.reader[Boolean], structures.DocumentSymbolOptions.reader)
  private given wt9: Writer[(Boolean | structures.DocumentSymbolOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentSymbolOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DocumentSymbolOptions => writeJs[structures.DocumentSymbolOptions](v)
    }
  private given rd10: Reader[(Boolean | structures.CodeActionOptions)] = 
    badMerge[(Boolean | structures.CodeActionOptions)](upickle.default.reader[Boolean], structures.CodeActionOptions.reader)
  private given wt10: Writer[(Boolean | structures.CodeActionOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.CodeActionOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.CodeActionOptions => writeJs[structures.CodeActionOptions](v)
    }
  private given rd11: Reader[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)] = 
    badMerge[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)](upickle.default.reader[Boolean], structures.DocumentColorOptions.reader, structures.DocumentColorRegistrationOptions.reader)
  private given wt11: Writer[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DocumentColorOptions => writeJs[structures.DocumentColorOptions](v)
        case v: structures.DocumentColorRegistrationOptions => writeJs[structures.DocumentColorRegistrationOptions](v)
    }
  private given rd12: Reader[(Boolean | structures.WorkspaceSymbolOptions)] = 
    badMerge[(Boolean | structures.WorkspaceSymbolOptions)](upickle.default.reader[Boolean], structures.WorkspaceSymbolOptions.reader)
  private given wt12: Writer[(Boolean | structures.WorkspaceSymbolOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.WorkspaceSymbolOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.WorkspaceSymbolOptions => writeJs[structures.WorkspaceSymbolOptions](v)
    }
  private given rd13: Reader[(Boolean | structures.DocumentFormattingOptions)] = 
    badMerge[(Boolean | structures.DocumentFormattingOptions)](upickle.default.reader[Boolean], structures.DocumentFormattingOptions.reader)
  private given wt13: Writer[(Boolean | structures.DocumentFormattingOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentFormattingOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DocumentFormattingOptions => writeJs[structures.DocumentFormattingOptions](v)
    }
  private given rd14: Reader[(Boolean | structures.DocumentRangeFormattingOptions)] = 
    badMerge[(Boolean | structures.DocumentRangeFormattingOptions)](upickle.default.reader[Boolean], structures.DocumentRangeFormattingOptions.reader)
  private given wt14: Writer[(Boolean | structures.DocumentRangeFormattingOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentRangeFormattingOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DocumentRangeFormattingOptions => writeJs[structures.DocumentRangeFormattingOptions](v)
    }
  private given rd15: Reader[(Boolean | structures.RenameOptions)] = 
    badMerge[(Boolean | structures.RenameOptions)](upickle.default.reader[Boolean], structures.RenameOptions.reader)
  private given wt15: Writer[(Boolean | structures.RenameOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.RenameOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.RenameOptions => writeJs[structures.RenameOptions](v)
    }
  private given rd16: Reader[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)] = 
    badMerge[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)](upickle.default.reader[Boolean], structures.FoldingRangeOptions.reader, structures.FoldingRangeRegistrationOptions.reader)
  private given wt16: Writer[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.FoldingRangeOptions => writeJs[structures.FoldingRangeOptions](v)
        case v: structures.FoldingRangeRegistrationOptions => writeJs[structures.FoldingRangeRegistrationOptions](v)
    }
  private given rd17: Reader[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)] = 
    badMerge[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)](upickle.default.reader[Boolean], structures.SelectionRangeOptions.reader, structures.SelectionRangeRegistrationOptions.reader)
  private given wt17: Writer[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.SelectionRangeOptions => writeJs[structures.SelectionRangeOptions](v)
        case v: structures.SelectionRangeRegistrationOptions => writeJs[structures.SelectionRangeRegistrationOptions](v)
    }
  private given rd18: Reader[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)] = 
    badMerge[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)](upickle.default.reader[Boolean], structures.CallHierarchyOptions.reader, structures.CallHierarchyRegistrationOptions.reader)
  private given wt18: Writer[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.CallHierarchyOptions => writeJs[structures.CallHierarchyOptions](v)
        case v: structures.CallHierarchyRegistrationOptions => writeJs[structures.CallHierarchyRegistrationOptions](v)
    }
  private given rd19: Reader[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)] = 
    badMerge[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)](upickle.default.reader[Boolean], structures.LinkedEditingRangeOptions.reader, structures.LinkedEditingRangeRegistrationOptions.reader)
  private given wt19: Writer[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.LinkedEditingRangeOptions => writeJs[structures.LinkedEditingRangeOptions](v)
        case v: structures.LinkedEditingRangeRegistrationOptions => writeJs[structures.LinkedEditingRangeRegistrationOptions](v)
    }
  private given rd20: Reader[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)] = 
    badMerge[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)](structures.SemanticTokensOptions.reader, structures.SemanticTokensRegistrationOptions.reader)
  private given wt20: Writer[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: structures.SemanticTokensOptions => writeJs[structures.SemanticTokensOptions](v)
        case v: structures.SemanticTokensRegistrationOptions => writeJs[structures.SemanticTokensRegistrationOptions](v)
    }
  private given rd21: Reader[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)] = 
    badMerge[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)](upickle.default.reader[Boolean], structures.MonikerOptions.reader, structures.MonikerRegistrationOptions.reader)
  private given wt21: Writer[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.MonikerOptions => writeJs[structures.MonikerOptions](v)
        case v: structures.MonikerRegistrationOptions => writeJs[structures.MonikerRegistrationOptions](v)
    }
  private given rd22: Reader[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)] = 
    badMerge[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)](upickle.default.reader[Boolean], structures.TypeHierarchyOptions.reader, structures.TypeHierarchyRegistrationOptions.reader)
  private given wt22: Writer[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.TypeHierarchyOptions => writeJs[structures.TypeHierarchyOptions](v)
        case v: structures.TypeHierarchyRegistrationOptions => writeJs[structures.TypeHierarchyRegistrationOptions](v)
    }
  private given rd23: Reader[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)] = 
    badMerge[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)](upickle.default.reader[Boolean], structures.InlineValueOptions.reader, structures.InlineValueRegistrationOptions.reader)
  private given wt23: Writer[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.InlineValueOptions => writeJs[structures.InlineValueOptions](v)
        case v: structures.InlineValueRegistrationOptions => writeJs[structures.InlineValueRegistrationOptions](v)
    }
  private given rd24: Reader[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)] = 
    badMerge[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)](upickle.default.reader[Boolean], structures.InlayHintOptions.reader, structures.InlayHintRegistrationOptions.reader)
  private given wt24: Writer[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.InlayHintOptions => writeJs[structures.InlayHintOptions](v)
        case v: structures.InlayHintRegistrationOptions => writeJs[structures.InlayHintRegistrationOptions](v)
    }
  private given rd25: Reader[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)] = 
    badMerge[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)](structures.DiagnosticOptions.reader, structures.DiagnosticRegistrationOptions.reader)
  private given wt25: Writer[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: structures.DiagnosticOptions => writeJs[structures.DiagnosticOptions](v)
        case v: structures.DiagnosticRegistrationOptions => writeJs[structures.DiagnosticRegistrationOptions](v)
    }
  given reader: Reader[structures.ServerCapabilities] = Pickle.macroR
  given writer: Writer[structures.ServerCapabilities] = upickle.default.macroW
  case class Workspace(
    workspaceFolders: Opt[structures.WorkspaceFoldersServerCapabilities] = Opt.empty,
    fileOperations: Opt[structures.FileOperationOptions] = Opt.empty
  )
  object Workspace:
    given reader: Reader[structures.ServerCapabilities.Workspace] = Pickle.macroR
    given writer: Writer[structures.ServerCapabilities.Workspace] = upickle.default.macroW

case class VersionedTextDocumentIdentifier(
  version: Int,
  uri: RuntimeBase.DocumentUri
)
object VersionedTextDocumentIdentifier:
  given reader: Reader[structures.VersionedTextDocumentIdentifier] = Pickle.macroR
  given writer: Writer[structures.VersionedTextDocumentIdentifier] = upickle.default.macroW

case class SaveOptions(
  includeText: Opt[Boolean] = Opt.empty
)
object SaveOptions:
  given reader: Reader[structures.SaveOptions] = Pickle.macroR
  given writer: Writer[structures.SaveOptions] = upickle.default.macroW

case class FileEvent(
  uri: RuntimeBase.DocumentUri,
  `type`: enumerations.FileChangeType
)
object FileEvent:
  given reader: Reader[structures.FileEvent] = Pickle.macroR
  given writer: Writer[structures.FileEvent] = upickle.default.macroW

case class FileSystemWatcher(
  globPattern: aliases.GlobPattern,
  kind: Opt[enumerations.WatchKind] = Opt.empty
)
object FileSystemWatcher:
  given reader: Reader[structures.FileSystemWatcher] = Pickle.macroR
  given writer: Writer[structures.FileSystemWatcher] = upickle.default.macroW

case class Diagnostic(
  range: structures.Range,
  severity: Opt[enumerations.DiagnosticSeverity] = Opt.empty,
  code: Opt[(Int | String)] = Opt.empty,
  codeDescription: Opt[structures.CodeDescription] = Opt.empty,
  source: Opt[String] = Opt.empty,
  message: String,
  tags: Opt[Vector[enumerations.DiagnosticTag]] = Opt.empty,
  relatedInformation: Opt[Vector[structures.DiagnosticRelatedInformation]] = Opt.empty,
  data: Opt[ujson.Value] = Opt.empty
)
object Diagnostic:
  private given rd0: Reader[(Int | String)] = 
    badMerge[(Int | String)](intCodec, stringCodec)
  private given wt0: Writer[(Int | String)] = 
    upickle.default.writer[ujson.Value].comap[(Int | String)] { _v => 
      (_v: @unchecked) match 
        case v: Int => writeJs[Int](v)
        case v: String => writeJs[String](v)
    }
  given reader: Reader[structures.Diagnostic] = Pickle.macroR
  given writer: Writer[structures.Diagnostic] = upickle.default.macroW

case class CompletionContext(
  triggerKind: enumerations.CompletionTriggerKind,
  triggerCharacter: Opt[String] = Opt.empty
)
object CompletionContext:
  given reader: Reader[structures.CompletionContext] = Pickle.macroR
  given writer: Writer[structures.CompletionContext] = upickle.default.macroW

case class CompletionItemLabelDetails(
  detail: Opt[String] = Opt.empty,
  description: Opt[String] = Opt.empty
)
object CompletionItemLabelDetails:
  given reader: Reader[structures.CompletionItemLabelDetails] = Pickle.macroR
  given writer: Writer[structures.CompletionItemLabelDetails] = upickle.default.macroW

case class InsertReplaceEdit(
  newText: String,
  insert: structures.Range,
  replace: structures.Range
)
object InsertReplaceEdit:
  given reader: Reader[structures.InsertReplaceEdit] = Pickle.macroR
  given writer: Writer[structures.InsertReplaceEdit] = upickle.default.macroW

case class CompletionOptions(
  triggerCharacters: Opt[Vector[String]] = Opt.empty,
  allCommitCharacters: Opt[Vector[String]] = Opt.empty,
  resolveProvider: Opt[Boolean] = Opt.empty,
  completionItem: Opt[CompletionOptions.CompletionItem] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object CompletionOptions:
  given reader: Reader[structures.CompletionOptions] = Pickle.macroR
  given writer: Writer[structures.CompletionOptions] = upickle.default.macroW
  case class CompletionItem(
    labelDetailsSupport: Opt[Boolean] = Opt.empty
  )
  object CompletionItem:
    given reader: Reader[structures.CompletionOptions.CompletionItem] = Pickle.macroR
    given writer: Writer[structures.CompletionOptions.CompletionItem] = upickle.default.macroW

case class HoverOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object HoverOptions:
  given reader: Reader[structures.HoverOptions] = Pickle.macroR
  given writer: Writer[structures.HoverOptions] = upickle.default.macroW

case class SignatureHelpContext(
  triggerKind: enumerations.SignatureHelpTriggerKind,
  triggerCharacter: Opt[String] = Opt.empty,
  isRetrigger: Boolean,
  activeSignatureHelp: Opt[structures.SignatureHelp] = Opt.empty
)
object SignatureHelpContext:
  given reader: Reader[structures.SignatureHelpContext] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpContext] = upickle.default.macroW

case class SignatureInformation(
  label: String,
  documentation: Opt[(String | structures.MarkupContent)] = Opt.empty,
  parameters: Opt[Vector[structures.ParameterInformation]] = Opt.empty,
  activeParameter: Opt[RuntimeBase.uinteger] = Opt.empty
)
object SignatureInformation:
  private given rd0: Reader[(String | structures.MarkupContent)] = 
    badMerge[(String | structures.MarkupContent)](stringCodec, structures.MarkupContent.reader)
  private given wt0: Writer[(String | structures.MarkupContent)] = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: structures.MarkupContent => writeJs[structures.MarkupContent](v)
    }
  given reader: Reader[structures.SignatureInformation] = Pickle.macroR
  given writer: Writer[structures.SignatureInformation] = upickle.default.macroW

case class SignatureHelpOptions(
  triggerCharacters: Opt[Vector[String]] = Opt.empty,
  retriggerCharacters: Opt[Vector[String]] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object SignatureHelpOptions:
  given reader: Reader[structures.SignatureHelpOptions] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpOptions] = upickle.default.macroW

case class DefinitionOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object DefinitionOptions:
  given reader: Reader[structures.DefinitionOptions] = Pickle.macroR
  given writer: Writer[structures.DefinitionOptions] = upickle.default.macroW

case class ReferenceContext(
  includeDeclaration: Boolean
)
object ReferenceContext:
  given reader: Reader[structures.ReferenceContext] = Pickle.macroR
  given writer: Writer[structures.ReferenceContext] = upickle.default.macroW

case class ReferenceOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object ReferenceOptions:
  given reader: Reader[structures.ReferenceOptions] = Pickle.macroR
  given writer: Writer[structures.ReferenceOptions] = upickle.default.macroW

case class DocumentHighlightOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object DocumentHighlightOptions:
  given reader: Reader[structures.DocumentHighlightOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlightOptions] = upickle.default.macroW

case class BaseSymbolInformation(
  name: String,
  kind: enumerations.SymbolKind,
  tags: Opt[Vector[enumerations.SymbolTag]] = Opt.empty,
  containerName: Opt[String] = Opt.empty
)
object BaseSymbolInformation:
  given reader: Reader[structures.BaseSymbolInformation] = Pickle.macroR
  given writer: Writer[structures.BaseSymbolInformation] = upickle.default.macroW

case class DocumentSymbolOptions(
  label: Opt[String] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object DocumentSymbolOptions:
  given reader: Reader[structures.DocumentSymbolOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbolOptions] = upickle.default.macroW

case class CodeActionContext(
  diagnostics: Vector[structures.Diagnostic],
  only: Opt[Vector[enumerations.CodeActionKind]] = Opt.empty,
  triggerKind: Opt[enumerations.CodeActionTriggerKind] = Opt.empty
)
object CodeActionContext:
  given reader: Reader[structures.CodeActionContext] = Pickle.macroR
  given writer: Writer[structures.CodeActionContext] = upickle.default.macroW

case class CodeActionOptions(
  codeActionKinds: Opt[Vector[enumerations.CodeActionKind]] = Opt.empty,
  resolveProvider: Opt[Boolean] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object CodeActionOptions:
  given reader: Reader[structures.CodeActionOptions] = Pickle.macroR
  given writer: Writer[structures.CodeActionOptions] = upickle.default.macroW

case class WorkspaceSymbolOptions(
  resolveProvider: Opt[Boolean] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object WorkspaceSymbolOptions:
  given reader: Reader[structures.WorkspaceSymbolOptions] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbolOptions] = upickle.default.macroW

case class CodeLensOptions(
  resolveProvider: Opt[Boolean] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object CodeLensOptions:
  given reader: Reader[structures.CodeLensOptions] = Pickle.macroR
  given writer: Writer[structures.CodeLensOptions] = upickle.default.macroW

case class DocumentLinkOptions(
  resolveProvider: Opt[Boolean] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object DocumentLinkOptions:
  given reader: Reader[structures.DocumentLinkOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentLinkOptions] = upickle.default.macroW

case class FormattingOptions(
  tabSize: RuntimeBase.uinteger,
  insertSpaces: Boolean,
  trimTrailingWhitespace: Opt[Boolean] = Opt.empty,
  insertFinalNewline: Opt[Boolean] = Opt.empty,
  trimFinalNewlines: Opt[Boolean] = Opt.empty
)
object FormattingOptions:
  given reader: Reader[structures.FormattingOptions] = Pickle.macroR
  given writer: Writer[structures.FormattingOptions] = upickle.default.macroW

case class DocumentFormattingOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object DocumentFormattingOptions:
  given reader: Reader[structures.DocumentFormattingOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentFormattingOptions] = upickle.default.macroW

case class DocumentRangeFormattingOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object DocumentRangeFormattingOptions:
  given reader: Reader[structures.DocumentRangeFormattingOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentRangeFormattingOptions] = upickle.default.macroW

case class DocumentOnTypeFormattingOptions(
  firstTriggerCharacter: String,
  moreTriggerCharacter: Opt[Vector[String]] = Opt.empty
)
object DocumentOnTypeFormattingOptions:
  given reader: Reader[structures.DocumentOnTypeFormattingOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentOnTypeFormattingOptions] = upickle.default.macroW

case class RenameOptions(
  prepareProvider: Opt[Boolean] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object RenameOptions:
  given reader: Reader[structures.RenameOptions] = Pickle.macroR
  given writer: Writer[structures.RenameOptions] = upickle.default.macroW

case class ExecuteCommandOptions(
  commands: Vector[String],
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object ExecuteCommandOptions:
  given reader: Reader[structures.ExecuteCommandOptions] = Pickle.macroR
  given writer: Writer[structures.ExecuteCommandOptions] = upickle.default.macroW

case class SemanticTokensLegend(
  tokenTypes: Vector[String],
  tokenModifiers: Vector[String]
)
object SemanticTokensLegend:
  given reader: Reader[structures.SemanticTokensLegend] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensLegend] = upickle.default.macroW

case class OptionalVersionedTextDocumentIdentifier(
  version: Nullable[Int],
  uri: RuntimeBase.DocumentUri
)
object OptionalVersionedTextDocumentIdentifier:
  given reader: Reader[structures.OptionalVersionedTextDocumentIdentifier] = Pickle.macroR
  given writer: Writer[structures.OptionalVersionedTextDocumentIdentifier] = upickle.default.macroW

case class AnnotatedTextEdit(
  annotationId: aliases.ChangeAnnotationIdentifier,
  range: structures.Range,
  newText: String
)
object AnnotatedTextEdit:
  given reader: Reader[structures.AnnotatedTextEdit] = Pickle.macroR
  given writer: Writer[structures.AnnotatedTextEdit] = upickle.default.macroW

case class ResourceOperation(
  kind: String,
  annotationId: Opt[aliases.ChangeAnnotationIdentifier] = Opt.empty
)
object ResourceOperation:
  given reader: Reader[structures.ResourceOperation] = Pickle.macroR
  given writer: Writer[structures.ResourceOperation] = upickle.default.macroW

case class CreateFileOptions(
  overwrite: Opt[Boolean] = Opt.empty,
  ignoreIfExists: Opt[Boolean] = Opt.empty
)
object CreateFileOptions:
  given reader: Reader[structures.CreateFileOptions] = Pickle.macroR
  given writer: Writer[structures.CreateFileOptions] = upickle.default.macroW

case class RenameFileOptions(
  overwrite: Opt[Boolean] = Opt.empty,
  ignoreIfExists: Opt[Boolean] = Opt.empty
)
object RenameFileOptions:
  given reader: Reader[structures.RenameFileOptions] = Pickle.macroR
  given writer: Writer[structures.RenameFileOptions] = upickle.default.macroW

case class DeleteFileOptions(
  recursive: Opt[Boolean] = Opt.empty,
  ignoreIfNotExists: Opt[Boolean] = Opt.empty
)
object DeleteFileOptions:
  given reader: Reader[structures.DeleteFileOptions] = Pickle.macroR
  given writer: Writer[structures.DeleteFileOptions] = upickle.default.macroW

case class FileOperationPattern(
  glob: String,
  matches: Opt[enumerations.FileOperationPatternKind] = Opt.empty,
  options: Opt[structures.FileOperationPatternOptions] = Opt.empty
)
object FileOperationPattern:
  given reader: Reader[structures.FileOperationPattern] = Pickle.macroR
  given writer: Writer[structures.FileOperationPattern] = upickle.default.macroW

case class WorkspaceFullDocumentDiagnosticReport(
  uri: RuntimeBase.DocumentUri,
  version: Nullable[Int],
  kind: "full",
  resultId: Opt[String] = Opt.empty,
  items: Vector[structures.Diagnostic]
)
object WorkspaceFullDocumentDiagnosticReport:
  given reader: Reader[structures.WorkspaceFullDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.WorkspaceFullDocumentDiagnosticReport] = upickle.default.macroW

case class WorkspaceUnchangedDocumentDiagnosticReport(
  uri: RuntimeBase.DocumentUri,
  version: Nullable[Int],
  kind: "unchanged",
  resultId: String
)
object WorkspaceUnchangedDocumentDiagnosticReport:
  given reader: Reader[structures.WorkspaceUnchangedDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.WorkspaceUnchangedDocumentDiagnosticReport] = upickle.default.macroW

case class LSPObject(
)
object LSPObject:
  given reader: Reader[structures.LSPObject] = Pickle.macroR
  given writer: Writer[structures.LSPObject] = upickle.default.macroW

case class NotebookCell(
  kind: enumerations.NotebookCellKind,
  document: RuntimeBase.DocumentUri,
  metadata: Opt[structures.LSPObject] = Opt.empty,
  executionSummary: Opt[structures.ExecutionSummary] = Opt.empty
)
object NotebookCell:
  given reader: Reader[structures.NotebookCell] = Pickle.macroR
  given writer: Writer[structures.NotebookCell] = upickle.default.macroW

case class NotebookCellArrayChange(
  start: RuntimeBase.uinteger,
  deleteCount: RuntimeBase.uinteger,
  cells: Opt[Vector[structures.NotebookCell]] = Opt.empty
)
object NotebookCellArrayChange:
  given reader: Reader[structures.NotebookCellArrayChange] = Pickle.macroR
  given writer: Writer[structures.NotebookCellArrayChange] = upickle.default.macroW

case class ClientCapabilities(
  workspace: Opt[structures.WorkspaceClientCapabilities] = Opt.empty,
  textDocument: Opt[structures.TextDocumentClientCapabilities] = Opt.empty,
  notebookDocument: Opt[structures.NotebookDocumentClientCapabilities] = Opt.empty,
  window: Opt[structures.WindowClientCapabilities] = Opt.empty,
  general: Opt[structures.GeneralClientCapabilities] = Opt.empty,
  experimental: Opt[ujson.Value] = Opt.empty
)
object ClientCapabilities:
  given reader: Reader[structures.ClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ClientCapabilities] = upickle.default.macroW

case class TextDocumentSyncOptions(
  openClose: Opt[Boolean] = Opt.empty,
  change: Opt[enumerations.TextDocumentSyncKind] = Opt.empty,
  willSave: Opt[Boolean] = Opt.empty,
  willSaveWaitUntil: Opt[Boolean] = Opt.empty,
  save: Opt[(Boolean | structures.SaveOptions)] = Opt.empty
)
object TextDocumentSyncOptions:
  private given rd0: Reader[(Boolean | structures.SaveOptions)] = 
    badMerge[(Boolean | structures.SaveOptions)](upickle.default.reader[Boolean], structures.SaveOptions.reader)
  private given wt0: Writer[(Boolean | structures.SaveOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.SaveOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.SaveOptions => writeJs[structures.SaveOptions](v)
    }
  given reader: Reader[structures.TextDocumentSyncOptions] = Pickle.macroR
  given writer: Writer[structures.TextDocumentSyncOptions] = upickle.default.macroW

case class NotebookDocumentSyncOptions(
  notebookSelector: Vector[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)],
  save: Opt[Boolean] = Opt.empty
)
object NotebookDocumentSyncOptions:
  private given rd0: Reader[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)] = 
    badMerge[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)](NotebookDocumentSyncOptions.S0.reader, NotebookDocumentSyncOptions.S1.reader)
  private given wt0: Writer[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)] = 
    upickle.default.writer[ujson.Value].comap[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)] { _v => 
      (_v: @unchecked) match 
        case v: NotebookDocumentSyncOptions.S0 => writeJs[NotebookDocumentSyncOptions.S0](v)
        case v: NotebookDocumentSyncOptions.S1 => writeJs[NotebookDocumentSyncOptions.S1](v)
    }
  given reader: Reader[structures.NotebookDocumentSyncOptions] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncOptions] = upickle.default.macroW
  case class S0(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Opt[Vector[S0.S0]] = Opt.empty
  )
  object S0:
    private given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = 
      badMerge[(String | aliases.NotebookDocumentFilter)](stringCodec, aliases.NotebookDocumentFilter.reader)
    private given wt0: Writer[(String | aliases.NotebookDocumentFilter)] = 
      upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { _v => 
        (_v: @unchecked) match 
          case v: String => writeJs[String](v)
          case v: aliases.NotebookDocumentFilter => writeJs[aliases.NotebookDocumentFilter](v)
      }
    given reader: Reader[structures.NotebookDocumentSyncOptions.S0] = Pickle.macroR
    given writer: Writer[structures.NotebookDocumentSyncOptions.S0] = upickle.default.macroW
    case class S0(
      language: String
    )
    object S0:
      given reader: Reader[structures.NotebookDocumentSyncOptions.S0.S0] = Pickle.macroR
      given writer: Writer[structures.NotebookDocumentSyncOptions.S0.S0] = upickle.default.macroW
  case class S1(
    notebook: Opt[(String | aliases.NotebookDocumentFilter)] = Opt.empty,
    cells: Vector[S1.S0]
  )
  object S1:
    private given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = 
      badMerge[(String | aliases.NotebookDocumentFilter)](stringCodec, aliases.NotebookDocumentFilter.reader)
    private given wt0: Writer[(String | aliases.NotebookDocumentFilter)] = 
      upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { _v => 
        (_v: @unchecked) match 
          case v: String => writeJs[String](v)
          case v: aliases.NotebookDocumentFilter => writeJs[aliases.NotebookDocumentFilter](v)
      }
    given reader: Reader[structures.NotebookDocumentSyncOptions.S1] = Pickle.macroR
    given writer: Writer[structures.NotebookDocumentSyncOptions.S1] = upickle.default.macroW
    case class S0(
      language: String
    )
    object S0:
      given reader: Reader[structures.NotebookDocumentSyncOptions.S1.S0] = Pickle.macroR
      given writer: Writer[structures.NotebookDocumentSyncOptions.S1.S0] = upickle.default.macroW

case class NotebookDocumentSyncRegistrationOptions(
  notebookSelector: Vector[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)],
  save: Opt[Boolean] = Opt.empty,
  id: Opt[String] = Opt.empty
)
object NotebookDocumentSyncRegistrationOptions:
  private given rd0: Reader[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)] = 
    badMerge[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)](NotebookDocumentSyncRegistrationOptions.S0.reader, NotebookDocumentSyncRegistrationOptions.S1.reader)
  private given wt0: Writer[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)] = 
    upickle.default.writer[ujson.Value].comap[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)] { _v => 
      (_v: @unchecked) match 
        case v: NotebookDocumentSyncRegistrationOptions.S0 => writeJs[NotebookDocumentSyncRegistrationOptions.S0](v)
        case v: NotebookDocumentSyncRegistrationOptions.S1 => writeJs[NotebookDocumentSyncRegistrationOptions.S1](v)
    }
  given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions] = upickle.default.macroW
  case class S0(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Opt[Vector[S0.S0]] = Opt.empty
  )
  object S0:
    private given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = 
      badMerge[(String | aliases.NotebookDocumentFilter)](stringCodec, aliases.NotebookDocumentFilter.reader)
    private given wt0: Writer[(String | aliases.NotebookDocumentFilter)] = 
      upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { _v => 
        (_v: @unchecked) match 
          case v: String => writeJs[String](v)
          case v: aliases.NotebookDocumentFilter => writeJs[aliases.NotebookDocumentFilter](v)
      }
    given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions.S0] = Pickle.macroR
    given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions.S0] = upickle.default.macroW
    case class S0(
      language: String
    )
    object S0:
      given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions.S0.S0] = Pickle.macroR
      given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions.S0.S0] = upickle.default.macroW
  case class S1(
    notebook: Opt[(String | aliases.NotebookDocumentFilter)] = Opt.empty,
    cells: Vector[S1.S0]
  )
  object S1:
    private given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = 
      badMerge[(String | aliases.NotebookDocumentFilter)](stringCodec, aliases.NotebookDocumentFilter.reader)
    private given wt0: Writer[(String | aliases.NotebookDocumentFilter)] = 
      upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { _v => 
        (_v: @unchecked) match 
          case v: String => writeJs[String](v)
          case v: aliases.NotebookDocumentFilter => writeJs[aliases.NotebookDocumentFilter](v)
      }
    given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions.S1] = Pickle.macroR
    given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions.S1] = upickle.default.macroW
    case class S0(
      language: String
    )
    object S0:
      given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions.S1.S0] = Pickle.macroR
      given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions.S1.S0] = upickle.default.macroW

case class WorkspaceFoldersServerCapabilities(
  supported: Opt[Boolean] = Opt.empty,
  changeNotifications: Opt[(String | Boolean)] = Opt.empty
)
object WorkspaceFoldersServerCapabilities:
  private given rd0: Reader[(String | Boolean)] = 
    badMerge[(String | Boolean)](stringCodec, upickle.default.reader[Boolean])
  private given wt0: Writer[(String | Boolean)] = 
    upickle.default.writer[ujson.Value].comap[(String | Boolean)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: Boolean => writeJs[Boolean](v)
    }
  given reader: Reader[structures.WorkspaceFoldersServerCapabilities] = Pickle.macroR
  given writer: Writer[structures.WorkspaceFoldersServerCapabilities] = upickle.default.macroW

case class FileOperationOptions(
  didCreate: Opt[structures.FileOperationRegistrationOptions] = Opt.empty,
  willCreate: Opt[structures.FileOperationRegistrationOptions] = Opt.empty,
  didRename: Opt[structures.FileOperationRegistrationOptions] = Opt.empty,
  willRename: Opt[structures.FileOperationRegistrationOptions] = Opt.empty,
  didDelete: Opt[structures.FileOperationRegistrationOptions] = Opt.empty,
  willDelete: Opt[structures.FileOperationRegistrationOptions] = Opt.empty
)
object FileOperationOptions:
  given reader: Reader[structures.FileOperationOptions] = Pickle.macroR
  given writer: Writer[structures.FileOperationOptions] = upickle.default.macroW

case class T(
)
object T:
  given reader: Reader[structures.T] = Pickle.macroR
  given writer: Writer[structures.T] = upickle.default.macroW

case class CodeDescription(
  href: aliases.URI
)
object CodeDescription:
  given reader: Reader[structures.CodeDescription] = Pickle.macroR
  given writer: Writer[structures.CodeDescription] = upickle.default.macroW

case class DiagnosticRelatedInformation(
  location: structures.Location,
  message: String
)
object DiagnosticRelatedInformation:
  given reader: Reader[structures.DiagnosticRelatedInformation] = Pickle.macroR
  given writer: Writer[structures.DiagnosticRelatedInformation] = upickle.default.macroW

case class ParameterInformation(
  label: (String | (RuntimeBase.uinteger, RuntimeBase.uinteger)),
  documentation: Opt[(String | structures.MarkupContent)] = Opt.empty
)
object ParameterInformation:
  private given rd0: Reader[(String | (RuntimeBase.uinteger, RuntimeBase.uinteger))] = 
    badMerge[(String | (RuntimeBase.uinteger, RuntimeBase.uinteger))](stringCodec, upickle.default.reader[(RuntimeBase.uinteger, RuntimeBase.uinteger)])
  private given wt0: Writer[(String | (RuntimeBase.uinteger, RuntimeBase.uinteger))] = 
    upickle.default.writer[ujson.Value].comap[(String | (RuntimeBase.uinteger, RuntimeBase.uinteger))] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: (RuntimeBase.uinteger, RuntimeBase.uinteger) => writeJs[(RuntimeBase.uinteger, RuntimeBase.uinteger)](v)
    }
  private given rd1: Reader[(String | structures.MarkupContent)] = 
    badMerge[(String | structures.MarkupContent)](stringCodec, structures.MarkupContent.reader)
  private given wt1: Writer[(String | structures.MarkupContent)] = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: structures.MarkupContent => writeJs[structures.MarkupContent](v)
    }
  given reader: Reader[structures.ParameterInformation] = Pickle.macroR
  given writer: Writer[structures.ParameterInformation] = upickle.default.macroW

case class NotebookCellTextDocumentFilter(
  notebook: (String | aliases.NotebookDocumentFilter),
  language: Opt[String] = Opt.empty
)
object NotebookCellTextDocumentFilter:
  private given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = 
    badMerge[(String | aliases.NotebookDocumentFilter)](stringCodec, aliases.NotebookDocumentFilter.reader)
  private given wt0: Writer[(String | aliases.NotebookDocumentFilter)] = 
    upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: aliases.NotebookDocumentFilter => writeJs[aliases.NotebookDocumentFilter](v)
    }
  given reader: Reader[structures.NotebookCellTextDocumentFilter] = Pickle.macroR
  given writer: Writer[structures.NotebookCellTextDocumentFilter] = upickle.default.macroW

case class FileOperationPatternOptions(
  ignoreCase: Opt[Boolean] = Opt.empty
)
object FileOperationPatternOptions:
  given reader: Reader[structures.FileOperationPatternOptions] = Pickle.macroR
  given writer: Writer[structures.FileOperationPatternOptions] = upickle.default.macroW

case class ExecutionSummary(
  executionOrder: RuntimeBase.uinteger,
  success: Opt[Boolean] = Opt.empty
)
object ExecutionSummary:
  given reader: Reader[structures.ExecutionSummary] = Pickle.macroR
  given writer: Writer[structures.ExecutionSummary] = upickle.default.macroW

case class WorkspaceClientCapabilities(
  applyEdit: Opt[Boolean] = Opt.empty,
  workspaceEdit: Opt[structures.WorkspaceEditClientCapabilities] = Opt.empty,
  didChangeConfiguration: Opt[structures.DidChangeConfigurationClientCapabilities] = Opt.empty,
  didChangeWatchedFiles: Opt[structures.DidChangeWatchedFilesClientCapabilities] = Opt.empty,
  symbol: Opt[structures.WorkspaceSymbolClientCapabilities] = Opt.empty,
  executeCommand: Opt[structures.ExecuteCommandClientCapabilities] = Opt.empty,
  workspaceFolders: Opt[Boolean] = Opt.empty,
  configuration: Opt[Boolean] = Opt.empty,
  semanticTokens: Opt[structures.SemanticTokensWorkspaceClientCapabilities] = Opt.empty,
  codeLens: Opt[structures.CodeLensWorkspaceClientCapabilities] = Opt.empty,
  fileOperations: Opt[structures.FileOperationClientCapabilities] = Opt.empty,
  inlineValue: Opt[structures.InlineValueWorkspaceClientCapabilities] = Opt.empty,
  inlayHint: Opt[structures.InlayHintWorkspaceClientCapabilities] = Opt.empty,
  diagnostics: Opt[structures.DiagnosticWorkspaceClientCapabilities] = Opt.empty
)
object WorkspaceClientCapabilities:
  given reader: Reader[structures.WorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.WorkspaceClientCapabilities] = upickle.default.macroW

case class TextDocumentClientCapabilities(
  synchronization: Opt[structures.TextDocumentSyncClientCapabilities] = Opt.empty,
  completion: Opt[structures.CompletionClientCapabilities] = Opt.empty,
  hover: Opt[structures.HoverClientCapabilities] = Opt.empty,
  signatureHelp: Opt[structures.SignatureHelpClientCapabilities] = Opt.empty,
  declaration: Opt[structures.DeclarationClientCapabilities] = Opt.empty,
  definition: Opt[structures.DefinitionClientCapabilities] = Opt.empty,
  typeDefinition: Opt[structures.TypeDefinitionClientCapabilities] = Opt.empty,
  implementation: Opt[structures.ImplementationClientCapabilities] = Opt.empty,
  references: Opt[structures.ReferenceClientCapabilities] = Opt.empty,
  documentHighlight: Opt[structures.DocumentHighlightClientCapabilities] = Opt.empty,
  documentSymbol: Opt[structures.DocumentSymbolClientCapabilities] = Opt.empty,
  codeAction: Opt[structures.CodeActionClientCapabilities] = Opt.empty,
  codeLens: Opt[structures.CodeLensClientCapabilities] = Opt.empty,
  documentLink: Opt[structures.DocumentLinkClientCapabilities] = Opt.empty,
  colorProvider: Opt[structures.DocumentColorClientCapabilities] = Opt.empty,
  formatting: Opt[structures.DocumentFormattingClientCapabilities] = Opt.empty,
  rangeFormatting: Opt[structures.DocumentRangeFormattingClientCapabilities] = Opt.empty,
  onTypeFormatting: Opt[structures.DocumentOnTypeFormattingClientCapabilities] = Opt.empty,
  rename: Opt[structures.RenameClientCapabilities] = Opt.empty,
  foldingRange: Opt[structures.FoldingRangeClientCapabilities] = Opt.empty,
  selectionRange: Opt[structures.SelectionRangeClientCapabilities] = Opt.empty,
  publishDiagnostics: Opt[structures.PublishDiagnosticsClientCapabilities] = Opt.empty,
  callHierarchy: Opt[structures.CallHierarchyClientCapabilities] = Opt.empty,
  semanticTokens: Opt[structures.SemanticTokensClientCapabilities] = Opt.empty,
  linkedEditingRange: Opt[structures.LinkedEditingRangeClientCapabilities] = Opt.empty,
  moniker: Opt[structures.MonikerClientCapabilities] = Opt.empty,
  typeHierarchy: Opt[structures.TypeHierarchyClientCapabilities] = Opt.empty,
  inlineValue: Opt[structures.InlineValueClientCapabilities] = Opt.empty,
  inlayHint: Opt[structures.InlayHintClientCapabilities] = Opt.empty,
  diagnostic: Opt[structures.DiagnosticClientCapabilities] = Opt.empty
)
object TextDocumentClientCapabilities:
  given reader: Reader[structures.TextDocumentClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.TextDocumentClientCapabilities] = upickle.default.macroW

case class NotebookDocumentClientCapabilities(
  synchronization: structures.NotebookDocumentSyncClientCapabilities
)
object NotebookDocumentClientCapabilities:
  given reader: Reader[structures.NotebookDocumentClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentClientCapabilities] = upickle.default.macroW

case class WindowClientCapabilities(
  workDoneProgress: Opt[Boolean] = Opt.empty,
  showMessage: Opt[structures.ShowMessageRequestClientCapabilities] = Opt.empty,
  showDocument: Opt[structures.ShowDocumentClientCapabilities] = Opt.empty
)
object WindowClientCapabilities:
  given reader: Reader[structures.WindowClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.WindowClientCapabilities] = upickle.default.macroW

case class GeneralClientCapabilities(
  staleRequestSupport: Opt[GeneralClientCapabilities.StaleRequestSupport] = Opt.empty,
  regularExpressions: Opt[structures.RegularExpressionsClientCapabilities] = Opt.empty,
  markdown: Opt[structures.MarkdownClientCapabilities] = Opt.empty,
  positionEncodings: Opt[Vector[enumerations.PositionEncodingKind]] = Opt.empty
)
object GeneralClientCapabilities:
  given reader: Reader[structures.GeneralClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.GeneralClientCapabilities] = upickle.default.macroW
  case class StaleRequestSupport(
    cancel: Boolean,
    retryOnContentModified: Vector[String]
  )
  object StaleRequestSupport:
    given reader: Reader[structures.GeneralClientCapabilities.StaleRequestSupport] = Pickle.macroR
    given writer: Writer[structures.GeneralClientCapabilities.StaleRequestSupport] = upickle.default.macroW

case class RelativePattern(
  baseUri: (structures.WorkspaceFolder | aliases.URI),
  pattern: aliases.Pattern
)
object RelativePattern:
  private given rd0: Reader[(structures.WorkspaceFolder | aliases.URI)] = 
    badMerge[(structures.WorkspaceFolder | aliases.URI)](structures.WorkspaceFolder.reader, aliases.URI.reader)
  private given wt0: Writer[(structures.WorkspaceFolder | aliases.URI)] = 
    upickle.default.writer[ujson.Value].comap[(structures.WorkspaceFolder | aliases.URI)] { _v => 
      (_v: @unchecked) match 
        case v: structures.WorkspaceFolder => writeJs[structures.WorkspaceFolder](v)
        case v: aliases.URI => writeJs[aliases.URI](v)
    }
  given reader: Reader[structures.RelativePattern] = Pickle.macroR
  given writer: Writer[structures.RelativePattern] = upickle.default.macroW

case class WorkspaceEditClientCapabilities(
  documentChanges: Opt[Boolean] = Opt.empty,
  resourceOperations: Opt[Vector[enumerations.ResourceOperationKind]] = Opt.empty,
  failureHandling: Opt[enumerations.FailureHandlingKind] = Opt.empty,
  normalizesLineEndings: Opt[Boolean] = Opt.empty,
  changeAnnotationSupport: Opt[WorkspaceEditClientCapabilities.ChangeAnnotationSupport] = Opt.empty
)
object WorkspaceEditClientCapabilities:
  given reader: Reader[structures.WorkspaceEditClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.WorkspaceEditClientCapabilities] = upickle.default.macroW
  case class ChangeAnnotationSupport(
    groupsOnLabel: Opt[Boolean] = Opt.empty
  )
  object ChangeAnnotationSupport:
    given reader: Reader[structures.WorkspaceEditClientCapabilities.ChangeAnnotationSupport] = Pickle.macroR
    given writer: Writer[structures.WorkspaceEditClientCapabilities.ChangeAnnotationSupport] = upickle.default.macroW

case class DidChangeConfigurationClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object DidChangeConfigurationClientCapabilities:
  given reader: Reader[structures.DidChangeConfigurationClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DidChangeConfigurationClientCapabilities] = upickle.default.macroW

case class DidChangeWatchedFilesClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  relativePatternSupport: Opt[Boolean] = Opt.empty
)
object DidChangeWatchedFilesClientCapabilities:
  given reader: Reader[structures.DidChangeWatchedFilesClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DidChangeWatchedFilesClientCapabilities] = upickle.default.macroW

case class WorkspaceSymbolClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  symbolKind: Opt[WorkspaceSymbolClientCapabilities.SymbolKind] = Opt.empty,
  tagSupport: Opt[WorkspaceSymbolClientCapabilities.TagSupport] = Opt.empty,
  resolveSupport: Opt[WorkspaceSymbolClientCapabilities.ResolveSupport] = Opt.empty
)
object WorkspaceSymbolClientCapabilities:
  given reader: Reader[structures.WorkspaceSymbolClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbolClientCapabilities] = upickle.default.macroW
  case class SymbolKind(
    valueSet: Opt[Vector[enumerations.SymbolKind]] = Opt.empty
  )
  object SymbolKind:
    given reader: Reader[structures.WorkspaceSymbolClientCapabilities.SymbolKind] = Pickle.macroR
    given writer: Writer[structures.WorkspaceSymbolClientCapabilities.SymbolKind] = upickle.default.macroW
  case class TagSupport(
    valueSet: Vector[enumerations.SymbolTag]
  )
  object TagSupport:
    given reader: Reader[structures.WorkspaceSymbolClientCapabilities.TagSupport] = Pickle.macroR
    given writer: Writer[structures.WorkspaceSymbolClientCapabilities.TagSupport] = upickle.default.macroW
  case class ResolveSupport(
    properties: Vector[String]
  )
  object ResolveSupport:
    given reader: Reader[structures.WorkspaceSymbolClientCapabilities.ResolveSupport] = Pickle.macroR
    given writer: Writer[structures.WorkspaceSymbolClientCapabilities.ResolveSupport] = upickle.default.macroW

case class ExecuteCommandClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object ExecuteCommandClientCapabilities:
  given reader: Reader[structures.ExecuteCommandClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ExecuteCommandClientCapabilities] = upickle.default.macroW

case class SemanticTokensWorkspaceClientCapabilities(
  refreshSupport: Opt[Boolean] = Opt.empty
)
object SemanticTokensWorkspaceClientCapabilities:
  given reader: Reader[structures.SemanticTokensWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensWorkspaceClientCapabilities] = upickle.default.macroW

case class CodeLensWorkspaceClientCapabilities(
  refreshSupport: Opt[Boolean] = Opt.empty
)
object CodeLensWorkspaceClientCapabilities:
  given reader: Reader[structures.CodeLensWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.CodeLensWorkspaceClientCapabilities] = upickle.default.macroW

case class FileOperationClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  didCreate: Opt[Boolean] = Opt.empty,
  willCreate: Opt[Boolean] = Opt.empty,
  didRename: Opt[Boolean] = Opt.empty,
  willRename: Opt[Boolean] = Opt.empty,
  didDelete: Opt[Boolean] = Opt.empty,
  willDelete: Opt[Boolean] = Opt.empty
)
object FileOperationClientCapabilities:
  given reader: Reader[structures.FileOperationClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.FileOperationClientCapabilities] = upickle.default.macroW

case class InlineValueWorkspaceClientCapabilities(
  refreshSupport: Opt[Boolean] = Opt.empty
)
object InlineValueWorkspaceClientCapabilities:
  given reader: Reader[structures.InlineValueWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.InlineValueWorkspaceClientCapabilities] = upickle.default.macroW

case class InlayHintWorkspaceClientCapabilities(
  refreshSupport: Opt[Boolean] = Opt.empty
)
object InlayHintWorkspaceClientCapabilities:
  given reader: Reader[structures.InlayHintWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.InlayHintWorkspaceClientCapabilities] = upickle.default.macroW

case class DiagnosticWorkspaceClientCapabilities(
  refreshSupport: Opt[Boolean] = Opt.empty
)
object DiagnosticWorkspaceClientCapabilities:
  given reader: Reader[structures.DiagnosticWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DiagnosticWorkspaceClientCapabilities] = upickle.default.macroW

case class TextDocumentSyncClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  willSave: Opt[Boolean] = Opt.empty,
  willSaveWaitUntil: Opt[Boolean] = Opt.empty,
  didSave: Opt[Boolean] = Opt.empty
)
object TextDocumentSyncClientCapabilities:
  given reader: Reader[structures.TextDocumentSyncClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.TextDocumentSyncClientCapabilities] = upickle.default.macroW

case class CompletionClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  completionItem: Opt[CompletionClientCapabilities.CompletionItem] = Opt.empty,
  completionItemKind: Opt[CompletionClientCapabilities.CompletionItemKind] = Opt.empty,
  insertTextMode: Opt[enumerations.InsertTextMode] = Opt.empty,
  contextSupport: Opt[Boolean] = Opt.empty,
  completionList: Opt[CompletionClientCapabilities.CompletionList] = Opt.empty
)
object CompletionClientCapabilities:
  given reader: Reader[structures.CompletionClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.CompletionClientCapabilities] = upickle.default.macroW
  case class CompletionItem(
    snippetSupport: Opt[Boolean] = Opt.empty,
    commitCharactersSupport: Opt[Boolean] = Opt.empty,
    documentationFormat: Opt[Vector[enumerations.MarkupKind]] = Opt.empty,
    deprecatedSupport: Opt[Boolean] = Opt.empty,
    preselectSupport: Opt[Boolean] = Opt.empty,
    tagSupport: Opt[CompletionItem.TagSupport] = Opt.empty,
    insertReplaceSupport: Opt[Boolean] = Opt.empty,
    resolveSupport: Opt[CompletionItem.ResolveSupport] = Opt.empty,
    insertTextModeSupport: Opt[CompletionItem.InsertTextModeSupport] = Opt.empty,
    labelDetailsSupport: Opt[Boolean] = Opt.empty
  )
  object CompletionItem:
    given reader: Reader[structures.CompletionClientCapabilities.CompletionItem] = Pickle.macroR
    given writer: Writer[structures.CompletionClientCapabilities.CompletionItem] = upickle.default.macroW
    case class TagSupport(
      valueSet: Vector[enumerations.CompletionItemTag]
    )
    object TagSupport:
      given reader: Reader[structures.CompletionClientCapabilities.CompletionItem.TagSupport] = Pickle.macroR
      given writer: Writer[structures.CompletionClientCapabilities.CompletionItem.TagSupport] = upickle.default.macroW
    case class ResolveSupport(
      properties: Vector[String]
    )
    object ResolveSupport:
      given reader: Reader[structures.CompletionClientCapabilities.CompletionItem.ResolveSupport] = Pickle.macroR
      given writer: Writer[structures.CompletionClientCapabilities.CompletionItem.ResolveSupport] = upickle.default.macroW
    case class InsertTextModeSupport(
      valueSet: Vector[enumerations.InsertTextMode]
    )
    object InsertTextModeSupport:
      given reader: Reader[structures.CompletionClientCapabilities.CompletionItem.InsertTextModeSupport] = Pickle.macroR
      given writer: Writer[structures.CompletionClientCapabilities.CompletionItem.InsertTextModeSupport] = upickle.default.macroW
  case class CompletionItemKind(
    valueSet: Opt[Vector[enumerations.CompletionItemKind]] = Opt.empty
  )
  object CompletionItemKind:
    given reader: Reader[structures.CompletionClientCapabilities.CompletionItemKind] = Pickle.macroR
    given writer: Writer[structures.CompletionClientCapabilities.CompletionItemKind] = upickle.default.macroW
  case class CompletionList(
    itemDefaults: Opt[Vector[String]] = Opt.empty
  )
  object CompletionList:
    given reader: Reader[structures.CompletionClientCapabilities.CompletionList] = Pickle.macroR
    given writer: Writer[structures.CompletionClientCapabilities.CompletionList] = upickle.default.macroW

case class HoverClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  contentFormat: Opt[Vector[enumerations.MarkupKind]] = Opt.empty
)
object HoverClientCapabilities:
  given reader: Reader[structures.HoverClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.HoverClientCapabilities] = upickle.default.macroW

case class SignatureHelpClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  signatureInformation: Opt[SignatureHelpClientCapabilities.SignatureInformation] = Opt.empty,
  contextSupport: Opt[Boolean] = Opt.empty
)
object SignatureHelpClientCapabilities:
  given reader: Reader[structures.SignatureHelpClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpClientCapabilities] = upickle.default.macroW
  case class SignatureInformation(
    documentationFormat: Opt[Vector[enumerations.MarkupKind]] = Opt.empty,
    parameterInformation: Opt[SignatureInformation.ParameterInformation] = Opt.empty,
    activeParameterSupport: Opt[Boolean] = Opt.empty
  )
  object SignatureInformation:
    given reader: Reader[structures.SignatureHelpClientCapabilities.SignatureInformation] = Pickle.macroR
    given writer: Writer[structures.SignatureHelpClientCapabilities.SignatureInformation] = upickle.default.macroW
    case class ParameterInformation(
      labelOffsetSupport: Opt[Boolean] = Opt.empty
    )
    object ParameterInformation:
      given reader: Reader[structures.SignatureHelpClientCapabilities.SignatureInformation.ParameterInformation] = Pickle.macroR
      given writer: Writer[structures.SignatureHelpClientCapabilities.SignatureInformation.ParameterInformation] = upickle.default.macroW

case class DeclarationClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  linkSupport: Opt[Boolean] = Opt.empty
)
object DeclarationClientCapabilities:
  given reader: Reader[structures.DeclarationClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DeclarationClientCapabilities] = upickle.default.macroW

case class DefinitionClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  linkSupport: Opt[Boolean] = Opt.empty
)
object DefinitionClientCapabilities:
  given reader: Reader[structures.DefinitionClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DefinitionClientCapabilities] = upickle.default.macroW

case class TypeDefinitionClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  linkSupport: Opt[Boolean] = Opt.empty
)
object TypeDefinitionClientCapabilities:
  given reader: Reader[structures.TypeDefinitionClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.TypeDefinitionClientCapabilities] = upickle.default.macroW

case class ImplementationClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  linkSupport: Opt[Boolean] = Opt.empty
)
object ImplementationClientCapabilities:
  given reader: Reader[structures.ImplementationClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ImplementationClientCapabilities] = upickle.default.macroW

case class ReferenceClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object ReferenceClientCapabilities:
  given reader: Reader[structures.ReferenceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ReferenceClientCapabilities] = upickle.default.macroW

case class DocumentHighlightClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object DocumentHighlightClientCapabilities:
  given reader: Reader[structures.DocumentHighlightClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlightClientCapabilities] = upickle.default.macroW

case class DocumentSymbolClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  symbolKind: Opt[DocumentSymbolClientCapabilities.SymbolKind] = Opt.empty,
  hierarchicalDocumentSymbolSupport: Opt[Boolean] = Opt.empty,
  tagSupport: Opt[DocumentSymbolClientCapabilities.TagSupport] = Opt.empty,
  labelSupport: Opt[Boolean] = Opt.empty
)
object DocumentSymbolClientCapabilities:
  given reader: Reader[structures.DocumentSymbolClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbolClientCapabilities] = upickle.default.macroW
  case class SymbolKind(
    valueSet: Opt[Vector[enumerations.SymbolKind]] = Opt.empty
  )
  object SymbolKind:
    given reader: Reader[structures.DocumentSymbolClientCapabilities.SymbolKind] = Pickle.macroR
    given writer: Writer[structures.DocumentSymbolClientCapabilities.SymbolKind] = upickle.default.macroW
  case class TagSupport(
    valueSet: Vector[enumerations.SymbolTag]
  )
  object TagSupport:
    given reader: Reader[structures.DocumentSymbolClientCapabilities.TagSupport] = Pickle.macroR
    given writer: Writer[structures.DocumentSymbolClientCapabilities.TagSupport] = upickle.default.macroW

case class CodeActionClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  codeActionLiteralSupport: Opt[CodeActionClientCapabilities.CodeActionLiteralSupport] = Opt.empty,
  isPreferredSupport: Opt[Boolean] = Opt.empty,
  disabledSupport: Opt[Boolean] = Opt.empty,
  dataSupport: Opt[Boolean] = Opt.empty,
  resolveSupport: Opt[CodeActionClientCapabilities.ResolveSupport] = Opt.empty,
  honorsChangeAnnotations: Opt[Boolean] = Opt.empty
)
object CodeActionClientCapabilities:
  given reader: Reader[structures.CodeActionClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.CodeActionClientCapabilities] = upickle.default.macroW
  case class CodeActionLiteralSupport(
    codeActionKind: CodeActionLiteralSupport.CodeActionKind
  )
  object CodeActionLiteralSupport:
    given reader: Reader[structures.CodeActionClientCapabilities.CodeActionLiteralSupport] = Pickle.macroR
    given writer: Writer[structures.CodeActionClientCapabilities.CodeActionLiteralSupport] = upickle.default.macroW
    case class CodeActionKind(
      valueSet: Vector[enumerations.CodeActionKind]
    )
    object CodeActionKind:
      given reader: Reader[structures.CodeActionClientCapabilities.CodeActionLiteralSupport.CodeActionKind] = Pickle.macroR
      given writer: Writer[structures.CodeActionClientCapabilities.CodeActionLiteralSupport.CodeActionKind] = upickle.default.macroW
  case class ResolveSupport(
    properties: Vector[String]
  )
  object ResolveSupport:
    given reader: Reader[structures.CodeActionClientCapabilities.ResolveSupport] = Pickle.macroR
    given writer: Writer[structures.CodeActionClientCapabilities.ResolveSupport] = upickle.default.macroW

case class CodeLensClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object CodeLensClientCapabilities:
  given reader: Reader[structures.CodeLensClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.CodeLensClientCapabilities] = upickle.default.macroW

case class DocumentLinkClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  tooltipSupport: Opt[Boolean] = Opt.empty
)
object DocumentLinkClientCapabilities:
  given reader: Reader[structures.DocumentLinkClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentLinkClientCapabilities] = upickle.default.macroW

case class DocumentColorClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object DocumentColorClientCapabilities:
  given reader: Reader[structures.DocumentColorClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentColorClientCapabilities] = upickle.default.macroW

case class DocumentFormattingClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object DocumentFormattingClientCapabilities:
  given reader: Reader[structures.DocumentFormattingClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentFormattingClientCapabilities] = upickle.default.macroW

case class DocumentRangeFormattingClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object DocumentRangeFormattingClientCapabilities:
  given reader: Reader[structures.DocumentRangeFormattingClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentRangeFormattingClientCapabilities] = upickle.default.macroW

case class DocumentOnTypeFormattingClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object DocumentOnTypeFormattingClientCapabilities:
  given reader: Reader[structures.DocumentOnTypeFormattingClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentOnTypeFormattingClientCapabilities] = upickle.default.macroW

case class RenameClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  prepareSupport: Opt[Boolean] = Opt.empty,
  prepareSupportDefaultBehavior: Opt[enumerations.PrepareSupportDefaultBehavior] = Opt.empty,
  honorsChangeAnnotations: Opt[Boolean] = Opt.empty
)
object RenameClientCapabilities:
  given reader: Reader[structures.RenameClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.RenameClientCapabilities] = upickle.default.macroW

case class FoldingRangeClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  rangeLimit: Opt[RuntimeBase.uinteger] = Opt.empty,
  lineFoldingOnly: Opt[Boolean] = Opt.empty,
  foldingRangeKind: Opt[FoldingRangeClientCapabilities.FoldingRangeKind] = Opt.empty,
  foldingRange: Opt[FoldingRangeClientCapabilities.FoldingRange] = Opt.empty
)
object FoldingRangeClientCapabilities:
  given reader: Reader[structures.FoldingRangeClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.FoldingRangeClientCapabilities] = upickle.default.macroW
  case class FoldingRangeKind(
    valueSet: Opt[Vector[enumerations.FoldingRangeKind]] = Opt.empty
  )
  object FoldingRangeKind:
    given reader: Reader[structures.FoldingRangeClientCapabilities.FoldingRangeKind] = Pickle.macroR
    given writer: Writer[structures.FoldingRangeClientCapabilities.FoldingRangeKind] = upickle.default.macroW
  case class FoldingRange(
    collapsedText: Opt[Boolean] = Opt.empty
  )
  object FoldingRange:
    given reader: Reader[structures.FoldingRangeClientCapabilities.FoldingRange] = Pickle.macroR
    given writer: Writer[structures.FoldingRangeClientCapabilities.FoldingRange] = upickle.default.macroW

case class SelectionRangeClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object SelectionRangeClientCapabilities:
  given reader: Reader[structures.SelectionRangeClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.SelectionRangeClientCapabilities] = upickle.default.macroW

case class PublishDiagnosticsClientCapabilities(
  relatedInformation: Opt[Boolean] = Opt.empty,
  tagSupport: Opt[PublishDiagnosticsClientCapabilities.TagSupport] = Opt.empty,
  versionSupport: Opt[Boolean] = Opt.empty,
  codeDescriptionSupport: Opt[Boolean] = Opt.empty,
  dataSupport: Opt[Boolean] = Opt.empty
)
object PublishDiagnosticsClientCapabilities:
  given reader: Reader[structures.PublishDiagnosticsClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.PublishDiagnosticsClientCapabilities] = upickle.default.macroW
  case class TagSupport(
    valueSet: Vector[enumerations.DiagnosticTag]
  )
  object TagSupport:
    given reader: Reader[structures.PublishDiagnosticsClientCapabilities.TagSupport] = Pickle.macroR
    given writer: Writer[structures.PublishDiagnosticsClientCapabilities.TagSupport] = upickle.default.macroW

case class CallHierarchyClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object CallHierarchyClientCapabilities:
  given reader: Reader[structures.CallHierarchyClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyClientCapabilities] = upickle.default.macroW

case class SemanticTokensClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  requests: SemanticTokensClientCapabilities.Requests,
  tokenTypes: Vector[String],
  tokenModifiers: Vector[String],
  formats: Vector[enumerations.TokenFormat],
  overlappingTokenSupport: Opt[Boolean] = Opt.empty,
  multilineTokenSupport: Opt[Boolean] = Opt.empty,
  serverCancelSupport: Opt[Boolean] = Opt.empty,
  augmentsSyntaxTokens: Opt[Boolean] = Opt.empty
)
object SemanticTokensClientCapabilities:
  given reader: Reader[structures.SemanticTokensClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensClientCapabilities] = upickle.default.macroW
  case class Requests(
    range: Opt[(Boolean | Requests.S0)] = Opt.empty,
    full: Opt[(Boolean | Requests.S1)] = Opt.empty
  )
  object Requests:
    private given rd0: Reader[(Boolean | Requests.S0)] = 
      badMerge[(Boolean | Requests.S0)](upickle.default.reader[Boolean], Requests.S0.reader)
    private given wt0: Writer[(Boolean | Requests.S0)] = 
      upickle.default.writer[ujson.Value].comap[(Boolean | Requests.S0)] { _v => 
        (_v: @unchecked) match 
          case v: Boolean => writeJs[Boolean](v)
          case v: Requests.S0 => writeJs[Requests.S0](v)
      }
    private given rd1: Reader[(Boolean | Requests.S1)] = 
      badMerge[(Boolean | Requests.S1)](upickle.default.reader[Boolean], Requests.S1.reader)
    private given wt1: Writer[(Boolean | Requests.S1)] = 
      upickle.default.writer[ujson.Value].comap[(Boolean | Requests.S1)] { _v => 
        (_v: @unchecked) match 
          case v: Boolean => writeJs[Boolean](v)
          case v: Requests.S1 => writeJs[Requests.S1](v)
      }
    given reader: Reader[structures.SemanticTokensClientCapabilities.Requests] = Pickle.macroR
    given writer: Writer[structures.SemanticTokensClientCapabilities.Requests] = upickle.default.macroW
    case class S0(
    )
    object S0:
      given reader: Reader[structures.SemanticTokensClientCapabilities.Requests.S0] = Pickle.macroR
      given writer: Writer[structures.SemanticTokensClientCapabilities.Requests.S0] = upickle.default.macroW
    case class S1(
      delta: Opt[Boolean] = Opt.empty
    )
    object S1:
      given reader: Reader[structures.SemanticTokensClientCapabilities.Requests.S1] = Pickle.macroR
      given writer: Writer[structures.SemanticTokensClientCapabilities.Requests.S1] = upickle.default.macroW

case class LinkedEditingRangeClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object LinkedEditingRangeClientCapabilities:
  given reader: Reader[structures.LinkedEditingRangeClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.LinkedEditingRangeClientCapabilities] = upickle.default.macroW

case class MonikerClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object MonikerClientCapabilities:
  given reader: Reader[structures.MonikerClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.MonikerClientCapabilities] = upickle.default.macroW

case class TypeHierarchyClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object TypeHierarchyClientCapabilities:
  given reader: Reader[structures.TypeHierarchyClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchyClientCapabilities] = upickle.default.macroW

case class InlineValueClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object InlineValueClientCapabilities:
  given reader: Reader[structures.InlineValueClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.InlineValueClientCapabilities] = upickle.default.macroW

case class InlayHintClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  resolveSupport: Opt[InlayHintClientCapabilities.ResolveSupport] = Opt.empty
)
object InlayHintClientCapabilities:
  given reader: Reader[structures.InlayHintClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.InlayHintClientCapabilities] = upickle.default.macroW
  case class ResolveSupport(
    properties: Vector[String]
  )
  object ResolveSupport:
    given reader: Reader[structures.InlayHintClientCapabilities.ResolveSupport] = Pickle.macroR
    given writer: Writer[structures.InlayHintClientCapabilities.ResolveSupport] = upickle.default.macroW

case class DiagnosticClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  relatedDocumentSupport: Opt[Boolean] = Opt.empty
)
object DiagnosticClientCapabilities:
  given reader: Reader[structures.DiagnosticClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DiagnosticClientCapabilities] = upickle.default.macroW

case class NotebookDocumentSyncClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  executionSummarySupport: Opt[Boolean] = Opt.empty
)
object NotebookDocumentSyncClientCapabilities:
  given reader: Reader[structures.NotebookDocumentSyncClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncClientCapabilities] = upickle.default.macroW

case class ShowMessageRequestClientCapabilities(
  messageActionItem: Opt[ShowMessageRequestClientCapabilities.MessageActionItem] = Opt.empty
)
object ShowMessageRequestClientCapabilities:
  given reader: Reader[structures.ShowMessageRequestClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ShowMessageRequestClientCapabilities] = upickle.default.macroW
  case class MessageActionItem(
    additionalPropertiesSupport: Opt[Boolean] = Opt.empty
  )
  object MessageActionItem:
    given reader: Reader[structures.ShowMessageRequestClientCapabilities.MessageActionItem] = Pickle.macroR
    given writer: Writer[structures.ShowMessageRequestClientCapabilities.MessageActionItem] = upickle.default.macroW

case class ShowDocumentClientCapabilities(
  support: Boolean
)
object ShowDocumentClientCapabilities:
  given reader: Reader[structures.ShowDocumentClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ShowDocumentClientCapabilities] = upickle.default.macroW

case class RegularExpressionsClientCapabilities(
  engine: String,
  version: Opt[String] = Opt.empty
)
object RegularExpressionsClientCapabilities:
  given reader: Reader[structures.RegularExpressionsClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.RegularExpressionsClientCapabilities] = upickle.default.macroW

case class MarkdownClientCapabilities(
  parser: String,
  version: Opt[String] = Opt.empty,
  allowedTags: Opt[Vector[String]] = Opt.empty
)
object MarkdownClientCapabilities:
  given reader: Reader[structures.MarkdownClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.MarkdownClientCapabilities] = upickle.default.macroW

