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
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object ImplementationRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.ImplementationRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.ImplementationRegistrationOptions] = upickle.default.macroW

case class TypeDefinitionParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object TypeDefinitionParams:
  given reader: Reader[structures.TypeDefinitionParams] = Pickle.macroR
  given writer: Writer[structures.TypeDefinitionParams] = upickle.default.macroW

case class TypeDefinitionRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object TypeDefinitionRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
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
  partialResultToken: aliases.ProgressToken
)
object PartialResultParams:
  given reader: Reader[structures.PartialResultParams] = Pickle.macroR
  given writer: Writer[structures.PartialResultParams] = upickle.default.macroW

case class DocumentColorParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
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
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object DocumentColorRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.DocumentColorRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentColorRegistrationOptions] = upickle.default.macroW

case class ColorPresentationParams(
  textDocument: structures.TextDocumentIdentifier,
  color: structures.Color,
  range: structures.Range,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object ColorPresentationParams:
  given reader: Reader[structures.ColorPresentationParams] = Pickle.macroR
  given writer: Writer[structures.ColorPresentationParams] = upickle.default.macroW

case class ColorPresentation(
  label: String,
  textEdit: structures.TextEdit,
  additionalTextEdits: Vector[structures.TextEdit]
)
object ColorPresentation:
  given reader: Reader[structures.ColorPresentation] = Pickle.macroR
  given writer: Writer[structures.ColorPresentation] = upickle.default.macroW

case class WorkDoneProgressOptions(
  workDoneProgress: Boolean
)
object WorkDoneProgressOptions:
  given reader: Reader[structures.WorkDoneProgressOptions] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressOptions] = upickle.default.macroW

case class TextDocumentRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object TextDocumentRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.TextDocumentRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.TextDocumentRegistrationOptions] = upickle.default.macroW

case class FoldingRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object FoldingRangeParams:
  given reader: Reader[structures.FoldingRangeParams] = Pickle.macroR
  given writer: Writer[structures.FoldingRangeParams] = upickle.default.macroW

case class FoldingRange(
  startLine: RuntimeBase.uinteger,
  startCharacter: RuntimeBase.uinteger,
  endLine: RuntimeBase.uinteger,
  endCharacter: RuntimeBase.uinteger,
  kind: enumerations.FoldingRangeKind,
  collapsedText: String
)
object FoldingRange:
  given reader: Reader[structures.FoldingRange] = Pickle.macroR
  given writer: Writer[structures.FoldingRange] = upickle.default.macroW

case class FoldingRangeRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object FoldingRangeRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.FoldingRangeRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.FoldingRangeRegistrationOptions] = upickle.default.macroW

case class DeclarationParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DeclarationParams:
  given reader: Reader[structures.DeclarationParams] = Pickle.macroR
  given writer: Writer[structures.DeclarationParams] = upickle.default.macroW

case class DeclarationRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object DeclarationRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.DeclarationRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DeclarationRegistrationOptions] = upickle.default.macroW

case class SelectionRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  positions: Vector[structures.Position],
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object SelectionRangeParams:
  given reader: Reader[structures.SelectionRangeParams] = Pickle.macroR
  given writer: Writer[structures.SelectionRangeParams] = upickle.default.macroW

case class SelectionRange(
  range: structures.Range,
  parent: structures.SelectionRange
)
object SelectionRange:
  given reader: Reader[structures.SelectionRange] = Pickle.macroR
  given writer: Writer[structures.SelectionRange] = upickle.default.macroW

case class SelectionRangeRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object SelectionRangeRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
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
  workDoneToken: aliases.ProgressToken
)
object CallHierarchyPrepareParams:
  given reader: Reader[structures.CallHierarchyPrepareParams] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyPrepareParams] = upickle.default.macroW

case class CallHierarchyItem(
  name: String,
  kind: enumerations.SymbolKind,
  tags: Vector[enumerations.SymbolTag],
  detail: String,
  uri: RuntimeBase.DocumentUri,
  range: structures.Range,
  selectionRange: structures.Range,
  data: ujson.Value
)
object CallHierarchyItem:
  given reader: Reader[structures.CallHierarchyItem] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyItem] = upickle.default.macroW

case class CallHierarchyRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object CallHierarchyRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.CallHierarchyRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyRegistrationOptions] = upickle.default.macroW

case class CallHierarchyIncomingCallsParams(
  item: structures.CallHierarchyItem,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
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
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
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
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object SemanticTokensParams:
  given reader: Reader[structures.SemanticTokensParams] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensParams] = upickle.default.macroW

case class SemanticTokens(
  resultId: String,
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
  documentSelector: (aliases.DocumentSelector | Null),
  legend: structures.SemanticTokensLegend,
  range: (Boolean | SemanticTokensRegistrationOptions.S0),
  full: (Boolean | SemanticTokensRegistrationOptions.S1),
  id: String
)
object SemanticTokensRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  private val rd1 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | SemanticTokensRegistrationOptions.S0)], SemanticTokensRegistrationOptions.S0.reader.widen[(Boolean | SemanticTokensRegistrationOptions.S0)])
  private given reader_rd1: Reader[(Boolean | SemanticTokensRegistrationOptions.S0)] = rd1
  private val wt1 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | SemanticTokensRegistrationOptions.S0)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: SemanticTokensRegistrationOptions.S0 => writeJs(v)
    }
  private given writer_wt1: Writer[(Boolean | SemanticTokensRegistrationOptions.S0)] = wt1
  private val rd2 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | SemanticTokensRegistrationOptions.S1)], SemanticTokensRegistrationOptions.S1.reader.widen[(Boolean | SemanticTokensRegistrationOptions.S1)])
  private given reader_rd2: Reader[(Boolean | SemanticTokensRegistrationOptions.S1)] = rd2
  private val wt2 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | SemanticTokensRegistrationOptions.S1)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: SemanticTokensRegistrationOptions.S1 => writeJs(v)
    }
  private given writer_wt2: Writer[(Boolean | SemanticTokensRegistrationOptions.S1)] = wt2
  given reader: Reader[structures.SemanticTokensRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensRegistrationOptions] = upickle.default.macroW
  case class S0(
  )
  object S0:
    given reader: Reader[structures.SemanticTokensRegistrationOptions.S0] = Pickle.macroR
    given writer: Writer[structures.SemanticTokensRegistrationOptions.S0] = upickle.default.macroW
  case class S1(
    delta: Boolean
  )
  object S1:
    given reader: Reader[structures.SemanticTokensRegistrationOptions.S1] = Pickle.macroR
    given writer: Writer[structures.SemanticTokensRegistrationOptions.S1] = upickle.default.macroW

case class SemanticTokensDeltaParams(
  textDocument: structures.TextDocumentIdentifier,
  previousResultId: String,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object SemanticTokensDeltaParams:
  given reader: Reader[structures.SemanticTokensDeltaParams] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensDeltaParams] = upickle.default.macroW

case class SemanticTokensDelta(
  resultId: String,
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
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object SemanticTokensRangeParams:
  given reader: Reader[structures.SemanticTokensRangeParams] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensRangeParams] = upickle.default.macroW

case class ShowDocumentParams(
  uri: aliases.URI,
  external: Boolean,
  takeFocus: Boolean,
  selection: structures.Range
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
  workDoneToken: aliases.ProgressToken
)
object LinkedEditingRangeParams:
  given reader: Reader[structures.LinkedEditingRangeParams] = Pickle.macroR
  given writer: Writer[structures.LinkedEditingRangeParams] = upickle.default.macroW

case class LinkedEditingRanges(
  ranges: Vector[structures.Range],
  wordPattern: String
)
object LinkedEditingRanges:
  given reader: Reader[structures.LinkedEditingRanges] = Pickle.macroR
  given writer: Writer[structures.LinkedEditingRanges] = upickle.default.macroW

case class LinkedEditingRangeRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object LinkedEditingRangeRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.LinkedEditingRangeRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.LinkedEditingRangeRegistrationOptions] = upickle.default.macroW

case class CreateFilesParams(
  files: Vector[structures.FileCreate]
)
object CreateFilesParams:
  given reader: Reader[structures.CreateFilesParams] = Pickle.macroR
  given writer: Writer[structures.CreateFilesParams] = upickle.default.macroW

case class WorkspaceEdit(
  changes: Map[RuntimeBase.DocumentUri, Vector[structures.TextEdit]],
  documentChanges: Vector[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)],
  changeAnnotations: Map[aliases.ChangeAnnotationIdentifier, structures.ChangeAnnotation]
)
object WorkspaceEdit:
  private val rd0 = badMerge(structures.TextDocumentEdit.reader.widen[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)], structures.CreateFile.reader.widen[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)], structures.RenameFile.reader.widen[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)], structures.DeleteFile.reader.widen[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)])
  private given reader_rd0: Reader[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)] { v => 
      (v: @unchecked) match 
        case v: structures.TextDocumentEdit => writeJs(v)
        case v: structures.CreateFile => writeJs(v)
        case v: structures.RenameFile => writeJs(v)
        case v: structures.DeleteFile => writeJs(v)
    }
  private given writer_wt0: Writer[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)] = wt0
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
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object MonikerParams:
  given reader: Reader[structures.MonikerParams] = Pickle.macroR
  given writer: Writer[structures.MonikerParams] = upickle.default.macroW

case class Moniker(
  scheme: String,
  identifier: String,
  unique: enumerations.UniquenessLevel,
  kind: enumerations.MonikerKind
)
object Moniker:
  given reader: Reader[structures.Moniker] = Pickle.macroR
  given writer: Writer[structures.Moniker] = upickle.default.macroW

case class MonikerRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object MonikerRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.MonikerRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.MonikerRegistrationOptions] = upickle.default.macroW

case class TypeHierarchyPrepareParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object TypeHierarchyPrepareParams:
  given reader: Reader[structures.TypeHierarchyPrepareParams] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchyPrepareParams] = upickle.default.macroW

case class TypeHierarchyItem(
  name: String,
  kind: enumerations.SymbolKind,
  tags: Vector[enumerations.SymbolTag],
  detail: String,
  uri: RuntimeBase.DocumentUri,
  range: structures.Range,
  selectionRange: structures.Range,
  data: ujson.Value
)
object TypeHierarchyItem:
  given reader: Reader[structures.TypeHierarchyItem] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchyItem] = upickle.default.macroW

case class TypeHierarchyRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object TypeHierarchyRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.TypeHierarchyRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchyRegistrationOptions] = upickle.default.macroW

case class TypeHierarchySupertypesParams(
  item: structures.TypeHierarchyItem,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object TypeHierarchySupertypesParams:
  given reader: Reader[structures.TypeHierarchySupertypesParams] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchySupertypesParams] = upickle.default.macroW

case class TypeHierarchySubtypesParams(
  item: structures.TypeHierarchyItem,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object TypeHierarchySubtypesParams:
  given reader: Reader[structures.TypeHierarchySubtypesParams] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchySubtypesParams] = upickle.default.macroW

case class InlineValueParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  context: structures.InlineValueContext,
  workDoneToken: aliases.ProgressToken
)
object InlineValueParams:
  given reader: Reader[structures.InlineValueParams] = Pickle.macroR
  given writer: Writer[structures.InlineValueParams] = upickle.default.macroW

case class InlineValueRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object InlineValueRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.InlineValueRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.InlineValueRegistrationOptions] = upickle.default.macroW

case class InlayHintParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  workDoneToken: aliases.ProgressToken
)
object InlayHintParams:
  given reader: Reader[structures.InlayHintParams] = Pickle.macroR
  given writer: Writer[structures.InlayHintParams] = upickle.default.macroW

case class InlayHint(
  position: structures.Position,
  label: (String | Vector[structures.InlayHintLabelPart]),
  kind: enumerations.InlayHintKind,
  textEdits: Vector[structures.TextEdit],
  tooltip: (String | structures.MarkupContent),
  paddingLeft: Boolean,
  paddingRight: Boolean,
  data: ujson.Value
)
object InlayHint:
  private val rd0 = badMerge(stringCodec.widen[(String | Vector[structures.InlayHintLabelPart])], upickle.default.reader[Vector[structures.InlayHintLabelPart]].widen[(String | Vector[structures.InlayHintLabelPart])])
  private given reader_rd0: Reader[(String | Vector[structures.InlayHintLabelPart])] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(String | Vector[structures.InlayHintLabelPart])] { v => 
      (v: @unchecked) match 
        case v: String => writeJs(v)
        case v: Vector[?] => writeJs[Vector[structures.InlayHintLabelPart]](v.asInstanceOf[Vector[structures.InlayHintLabelPart]])
    }
  private given writer_wt0: Writer[(String | Vector[structures.InlayHintLabelPart])] = wt0
  private val rd1 = badMerge(stringCodec.widen[(String | structures.MarkupContent)], structures.MarkupContent.reader.widen[(String | structures.MarkupContent)])
  private given reader_rd1: Reader[(String | structures.MarkupContent)] = rd1
  private val wt1 = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { v => 
      (v: @unchecked) match 
        case v: String => writeJs(v)
        case v: structures.MarkupContent => writeJs(v)
    }
  private given writer_wt1: Writer[(String | structures.MarkupContent)] = wt1
  given reader: Reader[structures.InlayHint] = Pickle.macroR
  given writer: Writer[structures.InlayHint] = upickle.default.macroW

case class InlayHintRegistrationOptions(
  resolveProvider: Boolean,
  documentSelector: (aliases.DocumentSelector | Null),
  id: String
)
object InlayHintRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.InlayHintRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.InlayHintRegistrationOptions] = upickle.default.macroW

case class DocumentDiagnosticParams(
  textDocument: structures.TextDocumentIdentifier,
  identifier: String,
  previousResultId: String,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DocumentDiagnosticParams:
  given reader: Reader[structures.DocumentDiagnosticParams] = Pickle.macroR
  given writer: Writer[structures.DocumentDiagnosticParams] = upickle.default.macroW

case class DocumentDiagnosticReportPartialResult(
  relatedDocuments: Map[RuntimeBase.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)]
)
object DocumentDiagnosticReportPartialResult:
  private val rd0 = badMerge(structures.FullDocumentDiagnosticReport.reader.widen[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)], structures.UnchangedDocumentDiagnosticReport.reader.widen[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)])
  private given reader_rd0: Reader[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] { v => 
      (v: @unchecked) match 
        case v: structures.FullDocumentDiagnosticReport => writeJs(v)
        case v: structures.UnchangedDocumentDiagnosticReport => writeJs(v)
    }
  private given writer_wt0: Writer[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = wt0
  given reader: Reader[structures.DocumentDiagnosticReportPartialResult] = Pickle.macroR
  given writer: Writer[structures.DocumentDiagnosticReportPartialResult] = upickle.default.macroW

case class DiagnosticServerCancellationData(
  retriggerRequest: Boolean
)
object DiagnosticServerCancellationData:
  given reader: Reader[structures.DiagnosticServerCancellationData] = Pickle.macroR
  given writer: Writer[structures.DiagnosticServerCancellationData] = upickle.default.macroW

case class DiagnosticRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  identifier: String,
  interFileDependencies: Boolean,
  workspaceDiagnostics: Boolean,
  id: String
)
object DiagnosticRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.DiagnosticRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DiagnosticRegistrationOptions] = upickle.default.macroW

case class WorkspaceDiagnosticParams(
  identifier: String,
  previousResultIds: Vector[structures.PreviousResultId],
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
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
  processId: (Int | Null),
  clientInfo: InitializeParams.ClientInfo,
  locale: String,
  rootPath: (String | Null),
  rootUri: (RuntimeBase.DocumentUri | Null),
  capabilities: structures.ClientCapabilities,
  initializationOptions: ujson.Value,
  trace: ("off" | "messages" | "compact" | "verbose"),
  workspaceFolders: (Vector[structures.WorkspaceFolder] | Null)
)
object InitializeParams:
  private val rd0 = badMerge(intCodec.widen[(Int | Null)], nullReadWriter.widen[(Int | Null)])
  private given reader_rd0: Reader[(Int | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(Int | Null)] { v => 
      (v: @unchecked) match 
        case v: Int => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(Int | Null)] = wt0
  private val rd1 = badMerge(stringCodec.widen[(String | Null)], nullReadWriter.widen[(String | Null)])
  private given reader_rd1: Reader[(String | Null)] = rd1
  private val wt1 = 
    upickle.default.writer[ujson.Value].comap[(String | Null)] { v => 
      (v: @unchecked) match 
        case v: String => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt1: Writer[(String | Null)] = wt1
  private val rd2 = badMerge(upickle.default.reader[RuntimeBase.DocumentUri].widen[(RuntimeBase.DocumentUri | Null)], nullReadWriter.widen[(RuntimeBase.DocumentUri | Null)])
  private given reader_rd2: Reader[(RuntimeBase.DocumentUri | Null)] = rd2
  private val wt2 = 
    upickle.default.writer[ujson.Value].comap[(RuntimeBase.DocumentUri | Null)] { v => 
      (v: @unchecked) match 
        case v: RuntimeBase.DocumentUri => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt2: Writer[(RuntimeBase.DocumentUri | Null)] = wt2
  private val rd3 = badMerge(upickle.default.reader["off"].widen[("off" | "messages" | "compact" | "verbose")], upickle.default.reader["messages"].widen[("off" | "messages" | "compact" | "verbose")], upickle.default.reader["compact"].widen[("off" | "messages" | "compact" | "verbose")], upickle.default.reader["verbose"].widen[("off" | "messages" | "compact" | "verbose")])
  private given reader_rd3: Reader[("off" | "messages" | "compact" | "verbose")] = rd3
  private val wt3 = 
    upickle.default.writer[ujson.Value].comap[("off" | "messages" | "compact" | "verbose")] { v => 
      (v: @unchecked) match 
        case v: "off" => writeJs(v)
        case v: "messages" => writeJs(v)
        case v: "compact" => writeJs(v)
        case v: "verbose" => writeJs(v)
    }
  private given writer_wt3: Writer[("off" | "messages" | "compact" | "verbose")] = wt3
  private val rd4 = badMerge(upickle.default.reader[Vector[structures.WorkspaceFolder]].widen[(Vector[structures.WorkspaceFolder] | Null)], nullReadWriter.widen[(Vector[structures.WorkspaceFolder] | Null)])
  private given reader_rd4: Reader[(Vector[structures.WorkspaceFolder] | Null)] = rd4
  private val wt4 = 
    upickle.default.writer[ujson.Value].comap[(Vector[structures.WorkspaceFolder] | Null)] { v => 
      (v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.WorkspaceFolder]](v.asInstanceOf[Vector[structures.WorkspaceFolder]])
        case null => ujson.Null
    }
  private given writer_wt4: Writer[(Vector[structures.WorkspaceFolder] | Null)] = wt4
  given reader: Reader[structures.InitializeParams] = Pickle.macroR
  given writer: Writer[structures.InitializeParams] = upickle.default.macroW
  case class ClientInfo(
    name: String,
    version: String
  )
  object ClientInfo:
    given reader: Reader[structures.InitializeParams.ClientInfo] = Pickle.macroR
    given writer: Writer[structures.InitializeParams.ClientInfo] = upickle.default.macroW

case class InitializeResult(
  capabilities: structures.ServerCapabilities,
  serverInfo: InitializeResult.ServerInfo
)
object InitializeResult:
  given reader: Reader[structures.InitializeResult] = Pickle.macroR
  given writer: Writer[structures.InitializeResult] = upickle.default.macroW
  case class ServerInfo(
    name: String,
    version: String
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
  section: (String | Vector[String])
)
object DidChangeConfigurationRegistrationOptions:
  private val rd0 = badMerge(stringCodec.widen[(String | Vector[String])], upickle.default.reader[Vector[String]].widen[(String | Vector[String])])
  private given reader_rd0: Reader[(String | Vector[String])] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(String | Vector[String])] { v => 
      (v: @unchecked) match 
        case v: String => writeJs(v)
        case v: Vector[?] => writeJs[Vector[String]](v.asInstanceOf[Vector[String]])
    }
  private given writer_wt0: Writer[(String | Vector[String])] = wt0
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
  actions: Vector[structures.MessageActionItem]
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
  documentSelector: (aliases.DocumentSelector | Null)
)
object TextDocumentChangeRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
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
  text: String
)
object DidSaveTextDocumentParams:
  given reader: Reader[structures.DidSaveTextDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidSaveTextDocumentParams] = upickle.default.macroW

case class TextDocumentSaveRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  includeText: Boolean
)
object TextDocumentSaveRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
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
  version: Int,
  diagnostics: Vector[structures.Diagnostic]
)
object PublishDiagnosticsParams:
  given reader: Reader[structures.PublishDiagnosticsParams] = Pickle.macroR
  given writer: Writer[structures.PublishDiagnosticsParams] = upickle.default.macroW

case class CompletionParams(
  context: structures.CompletionContext,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object CompletionParams:
  given reader: Reader[structures.CompletionParams] = Pickle.macroR
  given writer: Writer[structures.CompletionParams] = upickle.default.macroW

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
  data: ujson.Value
)
object CompletionItem:
  private val rd0 = badMerge(stringCodec.widen[(String | structures.MarkupContent)], structures.MarkupContent.reader.widen[(String | structures.MarkupContent)])
  private given reader_rd0: Reader[(String | structures.MarkupContent)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { v => 
      (v: @unchecked) match 
        case v: String => writeJs(v)
        case v: structures.MarkupContent => writeJs(v)
    }
  private given writer_wt0: Writer[(String | structures.MarkupContent)] = wt0
  private val rd1 = badMerge(structures.TextEdit.reader.widen[(structures.TextEdit | structures.InsertReplaceEdit)], structures.InsertReplaceEdit.reader.widen[(structures.TextEdit | structures.InsertReplaceEdit)])
  private given reader_rd1: Reader[(structures.TextEdit | structures.InsertReplaceEdit)] = rd1
  private val wt1 = 
    upickle.default.writer[ujson.Value].comap[(structures.TextEdit | structures.InsertReplaceEdit)] { v => 
      (v: @unchecked) match 
        case v: structures.TextEdit => writeJs(v)
        case v: structures.InsertReplaceEdit => writeJs(v)
    }
  private given writer_wt1: Writer[(structures.TextEdit | structures.InsertReplaceEdit)] = wt1
  given reader: Reader[structures.CompletionItem] = Pickle.macroR
  given writer: Writer[structures.CompletionItem] = upickle.default.macroW

case class CompletionList(
  isIncomplete: Boolean,
  itemDefaults: CompletionList.ItemDefaults,
  items: Vector[structures.CompletionItem]
)
object CompletionList:
  given reader: Reader[structures.CompletionList] = Pickle.macroR
  given writer: Writer[structures.CompletionList] = upickle.default.macroW
  case class ItemDefaults(
    commitCharacters: Vector[String],
    editRange: (structures.Range | ItemDefaults.S0),
    insertTextFormat: enumerations.InsertTextFormat,
    insertTextMode: enumerations.InsertTextMode,
    data: ujson.Value
  )
  object ItemDefaults:
    private val rd0 = badMerge(structures.Range.reader.widen[(structures.Range | ItemDefaults.S0)], ItemDefaults.S0.reader.widen[(structures.Range | ItemDefaults.S0)])
    private given reader_rd0: Reader[(structures.Range | ItemDefaults.S0)] = rd0
    private val wt0 = 
      upickle.default.writer[ujson.Value].comap[(structures.Range | ItemDefaults.S0)] { v => 
        (v: @unchecked) match 
          case v: structures.Range => writeJs(v)
          case v: ItemDefaults.S0 => writeJs(v)
      }
    private given writer_wt0: Writer[(structures.Range | ItemDefaults.S0)] = wt0
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
  documentSelector: (aliases.DocumentSelector | Null),
  triggerCharacters: Vector[String],
  allCommitCharacters: Vector[String],
  resolveProvider: Boolean,
  completionItem: CompletionRegistrationOptions.CompletionItem
)
object CompletionRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.CompletionRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.CompletionRegistrationOptions] = upickle.default.macroW
  case class CompletionItem(
    labelDetailsSupport: Boolean
  )
  object CompletionItem:
    given reader: Reader[structures.CompletionRegistrationOptions.CompletionItem] = Pickle.macroR
    given writer: Writer[structures.CompletionRegistrationOptions.CompletionItem] = upickle.default.macroW

case class HoverParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object HoverParams:
  given reader: Reader[structures.HoverParams] = Pickle.macroR
  given writer: Writer[structures.HoverParams] = upickle.default.macroW

case class Hover(
  contents: (structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString]),
  range: structures.Range
)
object Hover:
  private val rd0 = badMerge(structures.MarkupContent.reader.widen[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])], aliases.MarkedString.reader.widen[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])], upickle.default.reader[Vector[aliases.MarkedString]].widen[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])])
  private given reader_rd0: Reader[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])] { v => 
      (v: @unchecked) match 
        case v: structures.MarkupContent => writeJs(v)
        case v: aliases.MarkedString => writeJs(v)
        case v: Vector[?] => writeJs[Vector[aliases.MarkedString]](v.asInstanceOf[Vector[aliases.MarkedString]])
    }
  private given writer_wt0: Writer[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])] = wt0
  given reader: Reader[structures.Hover] = Pickle.macroR
  given writer: Writer[structures.Hover] = upickle.default.macroW

case class HoverRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object HoverRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.HoverRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.HoverRegistrationOptions] = upickle.default.macroW

case class SignatureHelpParams(
  context: structures.SignatureHelpContext,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object SignatureHelpParams:
  given reader: Reader[structures.SignatureHelpParams] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpParams] = upickle.default.macroW

case class SignatureHelp(
  signatures: Vector[structures.SignatureInformation],
  activeSignature: RuntimeBase.uinteger,
  activeParameter: RuntimeBase.uinteger
)
object SignatureHelp:
  given reader: Reader[structures.SignatureHelp] = Pickle.macroR
  given writer: Writer[structures.SignatureHelp] = upickle.default.macroW

case class SignatureHelpRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  triggerCharacters: Vector[String],
  retriggerCharacters: Vector[String]
)
object SignatureHelpRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.SignatureHelpRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpRegistrationOptions] = upickle.default.macroW

case class DefinitionParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DefinitionParams:
  given reader: Reader[structures.DefinitionParams] = Pickle.macroR
  given writer: Writer[structures.DefinitionParams] = upickle.default.macroW

case class DefinitionRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object DefinitionRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.DefinitionRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DefinitionRegistrationOptions] = upickle.default.macroW

case class ReferenceParams(
  context: structures.ReferenceContext,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object ReferenceParams:
  given reader: Reader[structures.ReferenceParams] = Pickle.macroR
  given writer: Writer[structures.ReferenceParams] = upickle.default.macroW

case class ReferenceRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object ReferenceRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.ReferenceRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.ReferenceRegistrationOptions] = upickle.default.macroW

case class DocumentHighlightParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DocumentHighlightParams:
  given reader: Reader[structures.DocumentHighlightParams] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlightParams] = upickle.default.macroW

case class DocumentHighlight(
  range: structures.Range,
  kind: enumerations.DocumentHighlightKind
)
object DocumentHighlight:
  given reader: Reader[structures.DocumentHighlight] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlight] = upickle.default.macroW

case class DocumentHighlightRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object DocumentHighlightRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.DocumentHighlightRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlightRegistrationOptions] = upickle.default.macroW

case class DocumentSymbolParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DocumentSymbolParams:
  given reader: Reader[structures.DocumentSymbolParams] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbolParams] = upickle.default.macroW

case class SymbolInformation(
  deprecated: Boolean,
  location: structures.Location,
  name: String,
  kind: enumerations.SymbolKind,
  tags: Vector[enumerations.SymbolTag],
  containerName: String
)
object SymbolInformation:
  given reader: Reader[structures.SymbolInformation] = Pickle.macroR
  given writer: Writer[structures.SymbolInformation] = upickle.default.macroW

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
  given reader: Reader[structures.DocumentSymbol] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbol] = upickle.default.macroW

case class DocumentSymbolRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  label: String
)
object DocumentSymbolRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.DocumentSymbolRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbolRegistrationOptions] = upickle.default.macroW

case class CodeActionParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  context: structures.CodeActionContext,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object CodeActionParams:
  given reader: Reader[structures.CodeActionParams] = Pickle.macroR
  given writer: Writer[structures.CodeActionParams] = upickle.default.macroW

case class Command(
  title: String,
  command: String,
  arguments: Vector[ujson.Value]
)
object Command:
  given reader: Reader[structures.Command] = Pickle.macroR
  given writer: Writer[structures.Command] = upickle.default.macroW

case class CodeAction(
  title: String,
  kind: enumerations.CodeActionKind,
  diagnostics: Vector[structures.Diagnostic],
  isPreferred: Boolean,
  disabled: CodeAction.Disabled,
  edit: structures.WorkspaceEdit,
  command: structures.Command,
  data: ujson.Value
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
  documentSelector: (aliases.DocumentSelector | Null),
  codeActionKinds: Vector[enumerations.CodeActionKind],
  resolveProvider: Boolean
)
object CodeActionRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.CodeActionRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.CodeActionRegistrationOptions] = upickle.default.macroW

case class WorkspaceSymbolParams(
  query: String,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object WorkspaceSymbolParams:
  given reader: Reader[structures.WorkspaceSymbolParams] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbolParams] = upickle.default.macroW

case class WorkspaceSymbol(
  location: (structures.Location | WorkspaceSymbol.S0),
  data: ujson.Value,
  name: String,
  kind: enumerations.SymbolKind,
  tags: Vector[enumerations.SymbolTag],
  containerName: String
)
object WorkspaceSymbol:
  private val rd0 = badMerge(structures.Location.reader.widen[(structures.Location | WorkspaceSymbol.S0)], WorkspaceSymbol.S0.reader.widen[(structures.Location | WorkspaceSymbol.S0)])
  private given reader_rd0: Reader[(structures.Location | WorkspaceSymbol.S0)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(structures.Location | WorkspaceSymbol.S0)] { v => 
      (v: @unchecked) match 
        case v: structures.Location => writeJs(v)
        case v: WorkspaceSymbol.S0 => writeJs(v)
    }
  private given writer_wt0: Writer[(structures.Location | WorkspaceSymbol.S0)] = wt0
  given reader: Reader[structures.WorkspaceSymbol] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbol] = upickle.default.macroW
  case class S0(
    uri: RuntimeBase.DocumentUri
  )
  object S0:
    given reader: Reader[structures.WorkspaceSymbol.S0] = Pickle.macroR
    given writer: Writer[structures.WorkspaceSymbol.S0] = upickle.default.macroW

case class WorkspaceSymbolRegistrationOptions(
  resolveProvider: Boolean
)
object WorkspaceSymbolRegistrationOptions:
  given reader: Reader[structures.WorkspaceSymbolRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbolRegistrationOptions] = upickle.default.macroW

case class CodeLensParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object CodeLensParams:
  given reader: Reader[structures.CodeLensParams] = Pickle.macroR
  given writer: Writer[structures.CodeLensParams] = upickle.default.macroW

case class CodeLens(
  range: structures.Range,
  command: structures.Command,
  data: ujson.Value
)
object CodeLens:
  given reader: Reader[structures.CodeLens] = Pickle.macroR
  given writer: Writer[structures.CodeLens] = upickle.default.macroW

case class CodeLensRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  resolveProvider: Boolean
)
object CodeLensRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.CodeLensRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.CodeLensRegistrationOptions] = upickle.default.macroW

case class DocumentLinkParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: aliases.ProgressToken,
  partialResultToken: aliases.ProgressToken
)
object DocumentLinkParams:
  given reader: Reader[structures.DocumentLinkParams] = Pickle.macroR
  given writer: Writer[structures.DocumentLinkParams] = upickle.default.macroW

case class DocumentLink(
  range: structures.Range,
  target: String,
  tooltip: String,
  data: ujson.Value
)
object DocumentLink:
  given reader: Reader[structures.DocumentLink] = Pickle.macroR
  given writer: Writer[structures.DocumentLink] = upickle.default.macroW

case class DocumentLinkRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  resolveProvider: Boolean
)
object DocumentLinkRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.DocumentLinkRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentLinkRegistrationOptions] = upickle.default.macroW

case class DocumentFormattingParams(
  textDocument: structures.TextDocumentIdentifier,
  options: structures.FormattingOptions,
  workDoneToken: aliases.ProgressToken
)
object DocumentFormattingParams:
  given reader: Reader[structures.DocumentFormattingParams] = Pickle.macroR
  given writer: Writer[structures.DocumentFormattingParams] = upickle.default.macroW

case class DocumentFormattingRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object DocumentFormattingRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.DocumentFormattingRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentFormattingRegistrationOptions] = upickle.default.macroW

case class DocumentRangeFormattingParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  options: structures.FormattingOptions,
  workDoneToken: aliases.ProgressToken
)
object DocumentRangeFormattingParams:
  given reader: Reader[structures.DocumentRangeFormattingParams] = Pickle.macroR
  given writer: Writer[structures.DocumentRangeFormattingParams] = upickle.default.macroW

case class DocumentRangeFormattingRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null)
)
object DocumentRangeFormattingRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
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
  documentSelector: (aliases.DocumentSelector | Null),
  firstTriggerCharacter: String,
  moreTriggerCharacter: Vector[String]
)
object DocumentOnTypeFormattingRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.DocumentOnTypeFormattingRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentOnTypeFormattingRegistrationOptions] = upickle.default.macroW

case class RenameParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  newName: String,
  workDoneToken: aliases.ProgressToken
)
object RenameParams:
  given reader: Reader[structures.RenameParams] = Pickle.macroR
  given writer: Writer[structures.RenameParams] = upickle.default.macroW

case class RenameRegistrationOptions(
  documentSelector: (aliases.DocumentSelector | Null),
  prepareProvider: Boolean
)
object RenameRegistrationOptions:
  private val rd0 = badMerge(aliases.DocumentSelector.reader.widen[(aliases.DocumentSelector | Null)], nullReadWriter.widen[(aliases.DocumentSelector | Null)])
  private given reader_rd0: Reader[(aliases.DocumentSelector | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(aliases.DocumentSelector | Null)] { v => 
      (v: @unchecked) match 
        case v: aliases.DocumentSelector => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(aliases.DocumentSelector | Null)] = wt0
  given reader: Reader[structures.RenameRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.RenameRegistrationOptions] = upickle.default.macroW

case class PrepareRenameParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: aliases.ProgressToken
)
object PrepareRenameParams:
  given reader: Reader[structures.PrepareRenameParams] = Pickle.macroR
  given writer: Writer[structures.PrepareRenameParams] = upickle.default.macroW

case class ExecuteCommandParams(
  command: String,
  arguments: Vector[ujson.Value],
  workDoneToken: aliases.ProgressToken
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
  label: String,
  edit: structures.WorkspaceEdit
)
object ApplyWorkspaceEditParams:
  given reader: Reader[structures.ApplyWorkspaceEditParams] = Pickle.macroR
  given writer: Writer[structures.ApplyWorkspaceEditParams] = upickle.default.macroW

case class ApplyWorkspaceEditResult(
  applied: Boolean,
  failureReason: String,
  failedChange: RuntimeBase.uinteger
)
object ApplyWorkspaceEditResult:
  given reader: Reader[structures.ApplyWorkspaceEditResult] = Pickle.macroR
  given writer: Writer[structures.ApplyWorkspaceEditResult] = upickle.default.macroW

case class WorkDoneProgressBegin(
  kind: "begin",
  title: String,
  cancellable: Boolean,
  message: String,
  percentage: RuntimeBase.uinteger
)
object WorkDoneProgressBegin:
  given reader: Reader[structures.WorkDoneProgressBegin] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressBegin] = upickle.default.macroW

case class WorkDoneProgressReport(
  kind: "report",
  cancellable: Boolean,
  message: String,
  percentage: RuntimeBase.uinteger
)
object WorkDoneProgressReport:
  given reader: Reader[structures.WorkDoneProgressReport] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressReport] = upickle.default.macroW

case class WorkDoneProgressEnd(
  kind: "end",
  message: String
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
  verbose: String
)
object LogTraceParams:
  given reader: Reader[structures.LogTraceParams] = Pickle.macroR
  given writer: Writer[structures.LogTraceParams] = upickle.default.macroW

case class CancelParams(
  id: (Int | String)
)
object CancelParams:
  private val rd0 = badMerge(intCodec.widen[(Int | String)], stringCodec.widen[(Int | String)])
  private given reader_rd0: Reader[(Int | String)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(Int | String)] { v => 
      (v: @unchecked) match 
        case v: Int => writeJs(v)
        case v: String => writeJs(v)
    }
  private given writer_wt0: Writer[(Int | String)] = wt0
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
  workDoneToken: aliases.ProgressToken
)
object WorkDoneProgressParams:
  given reader: Reader[structures.WorkDoneProgressParams] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressParams] = upickle.default.macroW

case class LocationLink(
  originSelectionRange: structures.Range,
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
  workDoneProgress: Boolean
)
object ImplementationOptions:
  given reader: Reader[structures.ImplementationOptions] = Pickle.macroR
  given writer: Writer[structures.ImplementationOptions] = upickle.default.macroW

case class StaticRegistrationOptions(
  id: String
)
object StaticRegistrationOptions:
  given reader: Reader[structures.StaticRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.StaticRegistrationOptions] = upickle.default.macroW

case class TypeDefinitionOptions(
  workDoneProgress: Boolean
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
  scopeUri: String,
  section: String
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
  workDoneProgress: Boolean
)
object DocumentColorOptions:
  given reader: Reader[structures.DocumentColorOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentColorOptions] = upickle.default.macroW

case class FoldingRangeOptions(
  workDoneProgress: Boolean
)
object FoldingRangeOptions:
  given reader: Reader[structures.FoldingRangeOptions] = Pickle.macroR
  given writer: Writer[structures.FoldingRangeOptions] = upickle.default.macroW

case class DeclarationOptions(
  workDoneProgress: Boolean
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
  workDoneProgress: Boolean
)
object SelectionRangeOptions:
  given reader: Reader[structures.SelectionRangeOptions] = Pickle.macroR
  given writer: Writer[structures.SelectionRangeOptions] = upickle.default.macroW

case class CallHierarchyOptions(
  workDoneProgress: Boolean
)
object CallHierarchyOptions:
  given reader: Reader[structures.CallHierarchyOptions] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyOptions] = upickle.default.macroW

case class SemanticTokensOptions(
  legend: structures.SemanticTokensLegend,
  range: (Boolean | SemanticTokensOptions.S0),
  full: (Boolean | SemanticTokensOptions.S1),
  workDoneProgress: Boolean
)
object SemanticTokensOptions:
  private val rd0 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | SemanticTokensOptions.S0)], SemanticTokensOptions.S0.reader.widen[(Boolean | SemanticTokensOptions.S0)])
  private given reader_rd0: Reader[(Boolean | SemanticTokensOptions.S0)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | SemanticTokensOptions.S0)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: SemanticTokensOptions.S0 => writeJs(v)
    }
  private given writer_wt0: Writer[(Boolean | SemanticTokensOptions.S0)] = wt0
  private val rd1 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | SemanticTokensOptions.S1)], SemanticTokensOptions.S1.reader.widen[(Boolean | SemanticTokensOptions.S1)])
  private given reader_rd1: Reader[(Boolean | SemanticTokensOptions.S1)] = rd1
  private val wt1 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | SemanticTokensOptions.S1)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: SemanticTokensOptions.S1 => writeJs(v)
    }
  private given writer_wt1: Writer[(Boolean | SemanticTokensOptions.S1)] = wt1
  given reader: Reader[structures.SemanticTokensOptions] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensOptions] = upickle.default.macroW
  case class S0(
  )
  object S0:
    given reader: Reader[structures.SemanticTokensOptions.S0] = Pickle.macroR
    given writer: Writer[structures.SemanticTokensOptions.S0] = upickle.default.macroW
  case class S1(
    delta: Boolean
  )
  object S1:
    given reader: Reader[structures.SemanticTokensOptions.S1] = Pickle.macroR
    given writer: Writer[structures.SemanticTokensOptions.S1] = upickle.default.macroW

case class SemanticTokensEdit(
  start: RuntimeBase.uinteger,
  deleteCount: RuntimeBase.uinteger,
  data: Vector[RuntimeBase.uinteger]
)
object SemanticTokensEdit:
  given reader: Reader[structures.SemanticTokensEdit] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensEdit] = upickle.default.macroW

case class LinkedEditingRangeOptions(
  workDoneProgress: Boolean
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
  private val rd0 = badMerge(structures.TextEdit.reader.widen[(structures.TextEdit | structures.AnnotatedTextEdit)], structures.AnnotatedTextEdit.reader.widen[(structures.TextEdit | structures.AnnotatedTextEdit)])
  private given reader_rd0: Reader[(structures.TextEdit | structures.AnnotatedTextEdit)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(structures.TextEdit | structures.AnnotatedTextEdit)] { v => 
      (v: @unchecked) match 
        case v: structures.TextEdit => writeJs(v)
        case v: structures.AnnotatedTextEdit => writeJs(v)
    }
  private given writer_wt0: Writer[(structures.TextEdit | structures.AnnotatedTextEdit)] = wt0
  given reader: Reader[structures.TextDocumentEdit] = Pickle.macroR
  given writer: Writer[structures.TextDocumentEdit] = upickle.default.macroW

case class CreateFile(
  kind: "create",
  uri: RuntimeBase.DocumentUri,
  options: structures.CreateFileOptions,
  annotationId: aliases.ChangeAnnotationIdentifier
)
object CreateFile:
  given reader: Reader[structures.CreateFile] = Pickle.macroR
  given writer: Writer[structures.CreateFile] = upickle.default.macroW

case class RenameFile(
  kind: "rename",
  oldUri: RuntimeBase.DocumentUri,
  newUri: RuntimeBase.DocumentUri,
  options: structures.RenameFileOptions,
  annotationId: aliases.ChangeAnnotationIdentifier
)
object RenameFile:
  given reader: Reader[structures.RenameFile] = Pickle.macroR
  given writer: Writer[structures.RenameFile] = upickle.default.macroW

case class DeleteFile(
  kind: "delete",
  uri: RuntimeBase.DocumentUri,
  options: structures.DeleteFileOptions,
  annotationId: aliases.ChangeAnnotationIdentifier
)
object DeleteFile:
  given reader: Reader[structures.DeleteFile] = Pickle.macroR
  given writer: Writer[structures.DeleteFile] = upickle.default.macroW

case class ChangeAnnotation(
  label: String,
  needsConfirmation: Boolean,
  description: String
)
object ChangeAnnotation:
  given reader: Reader[structures.ChangeAnnotation] = Pickle.macroR
  given writer: Writer[structures.ChangeAnnotation] = upickle.default.macroW

case class FileOperationFilter(
  scheme: String,
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
  workDoneProgress: Boolean
)
object MonikerOptions:
  given reader: Reader[structures.MonikerOptions] = Pickle.macroR
  given writer: Writer[structures.MonikerOptions] = upickle.default.macroW

case class TypeHierarchyOptions(
  workDoneProgress: Boolean
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
  variableName: String,
  caseSensitiveLookup: Boolean
)
object InlineValueVariableLookup:
  given reader: Reader[structures.InlineValueVariableLookup] = Pickle.macroR
  given writer: Writer[structures.InlineValueVariableLookup] = upickle.default.macroW

case class InlineValueEvaluatableExpression(
  range: structures.Range,
  expression: String
)
object InlineValueEvaluatableExpression:
  given reader: Reader[structures.InlineValueEvaluatableExpression] = Pickle.macroR
  given writer: Writer[structures.InlineValueEvaluatableExpression] = upickle.default.macroW

case class InlineValueOptions(
  workDoneProgress: Boolean
)
object InlineValueOptions:
  given reader: Reader[structures.InlineValueOptions] = Pickle.macroR
  given writer: Writer[structures.InlineValueOptions] = upickle.default.macroW

case class InlayHintLabelPart(
  value: String,
  tooltip: (String | structures.MarkupContent),
  location: structures.Location,
  command: structures.Command
)
object InlayHintLabelPart:
  private val rd0 = badMerge(stringCodec.widen[(String | structures.MarkupContent)], structures.MarkupContent.reader.widen[(String | structures.MarkupContent)])
  private given reader_rd0: Reader[(String | structures.MarkupContent)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { v => 
      (v: @unchecked) match 
        case v: String => writeJs(v)
        case v: structures.MarkupContent => writeJs(v)
    }
  private given writer_wt0: Writer[(String | structures.MarkupContent)] = wt0
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
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object InlayHintOptions:
  given reader: Reader[structures.InlayHintOptions] = Pickle.macroR
  given writer: Writer[structures.InlayHintOptions] = upickle.default.macroW

case class RelatedFullDocumentDiagnosticReport(
  relatedDocuments: Map[RuntimeBase.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)],
  kind: "full",
  resultId: String,
  items: Vector[structures.Diagnostic]
)
object RelatedFullDocumentDiagnosticReport:
  private val rd0 = badMerge(structures.FullDocumentDiagnosticReport.reader.widen[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)], structures.UnchangedDocumentDiagnosticReport.reader.widen[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)])
  private given reader_rd0: Reader[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] { v => 
      (v: @unchecked) match 
        case v: structures.FullDocumentDiagnosticReport => writeJs(v)
        case v: structures.UnchangedDocumentDiagnosticReport => writeJs(v)
    }
  private given writer_wt0: Writer[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = wt0
  given reader: Reader[structures.RelatedFullDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.RelatedFullDocumentDiagnosticReport] = upickle.default.macroW

case class RelatedUnchangedDocumentDiagnosticReport(
  relatedDocuments: Map[RuntimeBase.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)],
  kind: "unchanged",
  resultId: String
)
object RelatedUnchangedDocumentDiagnosticReport:
  private val rd0 = badMerge(structures.FullDocumentDiagnosticReport.reader.widen[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)], structures.UnchangedDocumentDiagnosticReport.reader.widen[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)])
  private given reader_rd0: Reader[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] { v => 
      (v: @unchecked) match 
        case v: structures.FullDocumentDiagnosticReport => writeJs(v)
        case v: structures.UnchangedDocumentDiagnosticReport => writeJs(v)
    }
  private given writer_wt0: Writer[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = wt0
  given reader: Reader[structures.RelatedUnchangedDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.RelatedUnchangedDocumentDiagnosticReport] = upickle.default.macroW

case class FullDocumentDiagnosticReport(
  kind: "full",
  resultId: String,
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
  identifier: String,
  interFileDependencies: Boolean,
  workspaceDiagnostics: Boolean,
  workDoneProgress: Boolean
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
  metadata: structures.LSPObject,
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
  metadata: structures.LSPObject,
  cells: NotebookDocumentChangeEvent.Cells
)
object NotebookDocumentChangeEvent:
  given reader: Reader[structures.NotebookDocumentChangeEvent] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentChangeEvent] = upickle.default.macroW
  case class Cells(
    structure: Cells.Structure,
    data: Vector[structures.NotebookCell],
    textContent: Vector[Cells.S0]
  )
  object Cells:
    given reader: Reader[structures.NotebookDocumentChangeEvent.Cells] = Pickle.macroR
    given writer: Writer[structures.NotebookDocumentChangeEvent.Cells] = upickle.default.macroW
    case class Structure(
      array: structures.NotebookCellArrayChange,
      didOpen: Vector[structures.TextDocumentItem],
      didClose: Vector[structures.TextDocumentIdentifier]
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
  registerOptions: ujson.Value
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
  processId: (Int | Null),
  clientInfo: _InitializeParams.ClientInfo,
  locale: String,
  rootPath: (String | Null),
  rootUri: (RuntimeBase.DocumentUri | Null),
  capabilities: structures.ClientCapabilities,
  initializationOptions: ujson.Value,
  trace: ("off" | "messages" | "compact" | "verbose"),
  workDoneToken: aliases.ProgressToken
)
object _InitializeParams:
  private val rd0 = badMerge(intCodec.widen[(Int | Null)], nullReadWriter.widen[(Int | Null)])
  private given reader_rd0: Reader[(Int | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(Int | Null)] { v => 
      (v: @unchecked) match 
        case v: Int => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(Int | Null)] = wt0
  private val rd1 = badMerge(stringCodec.widen[(String | Null)], nullReadWriter.widen[(String | Null)])
  private given reader_rd1: Reader[(String | Null)] = rd1
  private val wt1 = 
    upickle.default.writer[ujson.Value].comap[(String | Null)] { v => 
      (v: @unchecked) match 
        case v: String => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt1: Writer[(String | Null)] = wt1
  private val rd2 = badMerge(upickle.default.reader[RuntimeBase.DocumentUri].widen[(RuntimeBase.DocumentUri | Null)], nullReadWriter.widen[(RuntimeBase.DocumentUri | Null)])
  private given reader_rd2: Reader[(RuntimeBase.DocumentUri | Null)] = rd2
  private val wt2 = 
    upickle.default.writer[ujson.Value].comap[(RuntimeBase.DocumentUri | Null)] { v => 
      (v: @unchecked) match 
        case v: RuntimeBase.DocumentUri => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt2: Writer[(RuntimeBase.DocumentUri | Null)] = wt2
  private val rd3 = badMerge(upickle.default.reader["off"].widen[("off" | "messages" | "compact" | "verbose")], upickle.default.reader["messages"].widen[("off" | "messages" | "compact" | "verbose")], upickle.default.reader["compact"].widen[("off" | "messages" | "compact" | "verbose")], upickle.default.reader["verbose"].widen[("off" | "messages" | "compact" | "verbose")])
  private given reader_rd3: Reader[("off" | "messages" | "compact" | "verbose")] = rd3
  private val wt3 = 
    upickle.default.writer[ujson.Value].comap[("off" | "messages" | "compact" | "verbose")] { v => 
      (v: @unchecked) match 
        case v: "off" => writeJs(v)
        case v: "messages" => writeJs(v)
        case v: "compact" => writeJs(v)
        case v: "verbose" => writeJs(v)
    }
  private given writer_wt3: Writer[("off" | "messages" | "compact" | "verbose")] = wt3
  given reader: Reader[structures._InitializeParams] = Pickle.macroR
  given writer: Writer[structures._InitializeParams] = upickle.default.macroW
  case class ClientInfo(
    name: String,
    version: String
  )
  object ClientInfo:
    given reader: Reader[structures._InitializeParams.ClientInfo] = Pickle.macroR
    given writer: Writer[structures._InitializeParams.ClientInfo] = upickle.default.macroW

case class WorkspaceFoldersInitializeParams(
  workspaceFolders: (Vector[structures.WorkspaceFolder] | Null)
)
object WorkspaceFoldersInitializeParams:
  private val rd0 = badMerge(upickle.default.reader[Vector[structures.WorkspaceFolder]].widen[(Vector[structures.WorkspaceFolder] | Null)], nullReadWriter.widen[(Vector[structures.WorkspaceFolder] | Null)])
  private given reader_rd0: Reader[(Vector[structures.WorkspaceFolder] | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(Vector[structures.WorkspaceFolder] | Null)] { v => 
      (v: @unchecked) match 
        case v: Vector[?] => writeJs[Vector[structures.WorkspaceFolder]](v.asInstanceOf[Vector[structures.WorkspaceFolder]])
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(Vector[structures.WorkspaceFolder] | Null)] = wt0
  given reader: Reader[structures.WorkspaceFoldersInitializeParams] = Pickle.macroR
  given writer: Writer[structures.WorkspaceFoldersInitializeParams] = upickle.default.macroW

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
  private val rd0 = badMerge(structures.TextDocumentSyncOptions.reader.widen[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)], enumerations.TextDocumentSyncKind.reader.widen[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)])
  private given reader_rd0: Reader[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)] { v => 
      (v: @unchecked) match 
        case v: structures.TextDocumentSyncOptions => writeJs(v)
        case v: enumerations.TextDocumentSyncKind => writeJs(v)
    }
  private given writer_wt0: Writer[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)] = wt0
  private val rd1 = badMerge(structures.NotebookDocumentSyncOptions.reader.widen[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)], structures.NotebookDocumentSyncRegistrationOptions.reader.widen[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)])
  private given reader_rd1: Reader[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)] = rd1
  private val wt1 = 
    upickle.default.writer[ujson.Value].comap[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)] { v => 
      (v: @unchecked) match 
        case v: structures.NotebookDocumentSyncOptions => writeJs(v)
        case v: structures.NotebookDocumentSyncRegistrationOptions => writeJs(v)
    }
  private given writer_wt1: Writer[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)] = wt1
  private val rd2 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.HoverOptions)], structures.HoverOptions.reader.widen[(Boolean | structures.HoverOptions)])
  private given reader_rd2: Reader[(Boolean | structures.HoverOptions)] = rd2
  private val wt2 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.HoverOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.HoverOptions => writeJs(v)
    }
  private given writer_wt2: Writer[(Boolean | structures.HoverOptions)] = wt2
  private val rd3 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)], structures.DeclarationOptions.reader.widen[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)], structures.DeclarationRegistrationOptions.reader.widen[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)])
  private given reader_rd3: Reader[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)] = rd3
  private val wt3 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.DeclarationOptions => writeJs(v)
        case v: structures.DeclarationRegistrationOptions => writeJs(v)
    }
  private given writer_wt3: Writer[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)] = wt3
  private val rd4 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.DefinitionOptions)], structures.DefinitionOptions.reader.widen[(Boolean | structures.DefinitionOptions)])
  private given reader_rd4: Reader[(Boolean | structures.DefinitionOptions)] = rd4
  private val wt4 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DefinitionOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.DefinitionOptions => writeJs(v)
    }
  private given writer_wt4: Writer[(Boolean | structures.DefinitionOptions)] = wt4
  private val rd5 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)], structures.TypeDefinitionOptions.reader.widen[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)], structures.TypeDefinitionRegistrationOptions.reader.widen[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)])
  private given reader_rd5: Reader[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)] = rd5
  private val wt5 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.TypeDefinitionOptions => writeJs(v)
        case v: structures.TypeDefinitionRegistrationOptions => writeJs(v)
    }
  private given writer_wt5: Writer[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)] = wt5
  private val rd6 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)], structures.ImplementationOptions.reader.widen[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)], structures.ImplementationRegistrationOptions.reader.widen[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)])
  private given reader_rd6: Reader[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)] = rd6
  private val wt6 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.ImplementationOptions => writeJs(v)
        case v: structures.ImplementationRegistrationOptions => writeJs(v)
    }
  private given writer_wt6: Writer[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)] = wt6
  private val rd7 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.ReferenceOptions)], structures.ReferenceOptions.reader.widen[(Boolean | structures.ReferenceOptions)])
  private given reader_rd7: Reader[(Boolean | structures.ReferenceOptions)] = rd7
  private val wt7 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.ReferenceOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.ReferenceOptions => writeJs(v)
    }
  private given writer_wt7: Writer[(Boolean | structures.ReferenceOptions)] = wt7
  private val rd8 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.DocumentHighlightOptions)], structures.DocumentHighlightOptions.reader.widen[(Boolean | structures.DocumentHighlightOptions)])
  private given reader_rd8: Reader[(Boolean | structures.DocumentHighlightOptions)] = rd8
  private val wt8 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentHighlightOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.DocumentHighlightOptions => writeJs(v)
    }
  private given writer_wt8: Writer[(Boolean | structures.DocumentHighlightOptions)] = wt8
  private val rd9 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.DocumentSymbolOptions)], structures.DocumentSymbolOptions.reader.widen[(Boolean | structures.DocumentSymbolOptions)])
  private given reader_rd9: Reader[(Boolean | structures.DocumentSymbolOptions)] = rd9
  private val wt9 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentSymbolOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.DocumentSymbolOptions => writeJs(v)
    }
  private given writer_wt9: Writer[(Boolean | structures.DocumentSymbolOptions)] = wt9
  private val rd10 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.CodeActionOptions)], structures.CodeActionOptions.reader.widen[(Boolean | structures.CodeActionOptions)])
  private given reader_rd10: Reader[(Boolean | structures.CodeActionOptions)] = rd10
  private val wt10 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.CodeActionOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.CodeActionOptions => writeJs(v)
    }
  private given writer_wt10: Writer[(Boolean | structures.CodeActionOptions)] = wt10
  private val rd11 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)], structures.DocumentColorOptions.reader.widen[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)], structures.DocumentColorRegistrationOptions.reader.widen[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)])
  private given reader_rd11: Reader[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)] = rd11
  private val wt11 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.DocumentColorOptions => writeJs(v)
        case v: structures.DocumentColorRegistrationOptions => writeJs(v)
    }
  private given writer_wt11: Writer[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)] = wt11
  private val rd12 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.WorkspaceSymbolOptions)], structures.WorkspaceSymbolOptions.reader.widen[(Boolean | structures.WorkspaceSymbolOptions)])
  private given reader_rd12: Reader[(Boolean | structures.WorkspaceSymbolOptions)] = rd12
  private val wt12 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.WorkspaceSymbolOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.WorkspaceSymbolOptions => writeJs(v)
    }
  private given writer_wt12: Writer[(Boolean | structures.WorkspaceSymbolOptions)] = wt12
  private val rd13 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.DocumentFormattingOptions)], structures.DocumentFormattingOptions.reader.widen[(Boolean | structures.DocumentFormattingOptions)])
  private given reader_rd13: Reader[(Boolean | structures.DocumentFormattingOptions)] = rd13
  private val wt13 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentFormattingOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.DocumentFormattingOptions => writeJs(v)
    }
  private given writer_wt13: Writer[(Boolean | structures.DocumentFormattingOptions)] = wt13
  private val rd14 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.DocumentRangeFormattingOptions)], structures.DocumentRangeFormattingOptions.reader.widen[(Boolean | structures.DocumentRangeFormattingOptions)])
  private given reader_rd14: Reader[(Boolean | structures.DocumentRangeFormattingOptions)] = rd14
  private val wt14 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentRangeFormattingOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.DocumentRangeFormattingOptions => writeJs(v)
    }
  private given writer_wt14: Writer[(Boolean | structures.DocumentRangeFormattingOptions)] = wt14
  private val rd15 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.RenameOptions)], structures.RenameOptions.reader.widen[(Boolean | structures.RenameOptions)])
  private given reader_rd15: Reader[(Boolean | structures.RenameOptions)] = rd15
  private val wt15 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.RenameOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.RenameOptions => writeJs(v)
    }
  private given writer_wt15: Writer[(Boolean | structures.RenameOptions)] = wt15
  private val rd16 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)], structures.FoldingRangeOptions.reader.widen[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)], structures.FoldingRangeRegistrationOptions.reader.widen[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)])
  private given reader_rd16: Reader[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)] = rd16
  private val wt16 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.FoldingRangeOptions => writeJs(v)
        case v: structures.FoldingRangeRegistrationOptions => writeJs(v)
    }
  private given writer_wt16: Writer[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)] = wt16
  private val rd17 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)], structures.SelectionRangeOptions.reader.widen[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)], structures.SelectionRangeRegistrationOptions.reader.widen[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)])
  private given reader_rd17: Reader[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)] = rd17
  private val wt17 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.SelectionRangeOptions => writeJs(v)
        case v: structures.SelectionRangeRegistrationOptions => writeJs(v)
    }
  private given writer_wt17: Writer[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)] = wt17
  private val rd18 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)], structures.CallHierarchyOptions.reader.widen[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)], structures.CallHierarchyRegistrationOptions.reader.widen[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)])
  private given reader_rd18: Reader[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)] = rd18
  private val wt18 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.CallHierarchyOptions => writeJs(v)
        case v: structures.CallHierarchyRegistrationOptions => writeJs(v)
    }
  private given writer_wt18: Writer[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)] = wt18
  private val rd19 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)], structures.LinkedEditingRangeOptions.reader.widen[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)], structures.LinkedEditingRangeRegistrationOptions.reader.widen[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)])
  private given reader_rd19: Reader[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)] = rd19
  private val wt19 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.LinkedEditingRangeOptions => writeJs(v)
        case v: structures.LinkedEditingRangeRegistrationOptions => writeJs(v)
    }
  private given writer_wt19: Writer[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)] = wt19
  private val rd20 = badMerge(structures.SemanticTokensOptions.reader.widen[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)], structures.SemanticTokensRegistrationOptions.reader.widen[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)])
  private given reader_rd20: Reader[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)] = rd20
  private val wt20 = 
    upickle.default.writer[ujson.Value].comap[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)] { v => 
      (v: @unchecked) match 
        case v: structures.SemanticTokensOptions => writeJs(v)
        case v: structures.SemanticTokensRegistrationOptions => writeJs(v)
    }
  private given writer_wt20: Writer[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)] = wt20
  private val rd21 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)], structures.MonikerOptions.reader.widen[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)], structures.MonikerRegistrationOptions.reader.widen[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)])
  private given reader_rd21: Reader[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)] = rd21
  private val wt21 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.MonikerOptions => writeJs(v)
        case v: structures.MonikerRegistrationOptions => writeJs(v)
    }
  private given writer_wt21: Writer[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)] = wt21
  private val rd22 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)], structures.TypeHierarchyOptions.reader.widen[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)], structures.TypeHierarchyRegistrationOptions.reader.widen[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)])
  private given reader_rd22: Reader[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)] = rd22
  private val wt22 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.TypeHierarchyOptions => writeJs(v)
        case v: structures.TypeHierarchyRegistrationOptions => writeJs(v)
    }
  private given writer_wt22: Writer[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)] = wt22
  private val rd23 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)], structures.InlineValueOptions.reader.widen[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)], structures.InlineValueRegistrationOptions.reader.widen[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)])
  private given reader_rd23: Reader[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)] = rd23
  private val wt23 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.InlineValueOptions => writeJs(v)
        case v: structures.InlineValueRegistrationOptions => writeJs(v)
    }
  private given writer_wt23: Writer[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)] = wt23
  private val rd24 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)], structures.InlayHintOptions.reader.widen[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)], structures.InlayHintRegistrationOptions.reader.widen[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)])
  private given reader_rd24: Reader[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)] = rd24
  private val wt24 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.InlayHintOptions => writeJs(v)
        case v: structures.InlayHintRegistrationOptions => writeJs(v)
    }
  private given writer_wt24: Writer[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)] = wt24
  private val rd25 = badMerge(structures.DiagnosticOptions.reader.widen[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)], structures.DiagnosticRegistrationOptions.reader.widen[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)])
  private given reader_rd25: Reader[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)] = rd25
  private val wt25 = 
    upickle.default.writer[ujson.Value].comap[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)] { v => 
      (v: @unchecked) match 
        case v: structures.DiagnosticOptions => writeJs(v)
        case v: structures.DiagnosticRegistrationOptions => writeJs(v)
    }
  private given writer_wt25: Writer[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)] = wt25
  given reader: Reader[structures.ServerCapabilities] = Pickle.macroR
  given writer: Writer[structures.ServerCapabilities] = upickle.default.macroW
  case class Workspace(
    workspaceFolders: structures.WorkspaceFoldersServerCapabilities,
    fileOperations: structures.FileOperationOptions
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
  includeText: Boolean
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
  kind: enumerations.WatchKind
)
object FileSystemWatcher:
  given reader: Reader[structures.FileSystemWatcher] = Pickle.macroR
  given writer: Writer[structures.FileSystemWatcher] = upickle.default.macroW

case class Diagnostic(
  range: structures.Range,
  severity: enumerations.DiagnosticSeverity,
  code: (Int | String),
  codeDescription: structures.CodeDescription,
  source: String,
  message: String,
  tags: Vector[enumerations.DiagnosticTag],
  relatedInformation: Vector[structures.DiagnosticRelatedInformation],
  data: ujson.Value
)
object Diagnostic:
  private val rd0 = badMerge(intCodec.widen[(Int | String)], stringCodec.widen[(Int | String)])
  private given reader_rd0: Reader[(Int | String)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(Int | String)] { v => 
      (v: @unchecked) match 
        case v: Int => writeJs(v)
        case v: String => writeJs(v)
    }
  private given writer_wt0: Writer[(Int | String)] = wt0
  given reader: Reader[structures.Diagnostic] = Pickle.macroR
  given writer: Writer[structures.Diagnostic] = upickle.default.macroW

case class CompletionContext(
  triggerKind: enumerations.CompletionTriggerKind,
  triggerCharacter: String
)
object CompletionContext:
  given reader: Reader[structures.CompletionContext] = Pickle.macroR
  given writer: Writer[structures.CompletionContext] = upickle.default.macroW

case class CompletionItemLabelDetails(
  detail: String,
  description: String
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
  triggerCharacters: Vector[String],
  allCommitCharacters: Vector[String],
  resolveProvider: Boolean,
  completionItem: CompletionOptions.CompletionItem,
  workDoneProgress: Boolean
)
object CompletionOptions:
  given reader: Reader[structures.CompletionOptions] = Pickle.macroR
  given writer: Writer[structures.CompletionOptions] = upickle.default.macroW
  case class CompletionItem(
    labelDetailsSupport: Boolean
  )
  object CompletionItem:
    given reader: Reader[structures.CompletionOptions.CompletionItem] = Pickle.macroR
    given writer: Writer[structures.CompletionOptions.CompletionItem] = upickle.default.macroW

case class HoverOptions(
  workDoneProgress: Boolean
)
object HoverOptions:
  given reader: Reader[structures.HoverOptions] = Pickle.macroR
  given writer: Writer[structures.HoverOptions] = upickle.default.macroW

case class SignatureHelpContext(
  triggerKind: enumerations.SignatureHelpTriggerKind,
  triggerCharacter: String,
  isRetrigger: Boolean,
  activeSignatureHelp: structures.SignatureHelp
)
object SignatureHelpContext:
  given reader: Reader[structures.SignatureHelpContext] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpContext] = upickle.default.macroW

case class SignatureInformation(
  label: String,
  documentation: (String | structures.MarkupContent),
  parameters: Vector[structures.ParameterInformation],
  activeParameter: RuntimeBase.uinteger
)
object SignatureInformation:
  private val rd0 = badMerge(stringCodec.widen[(String | structures.MarkupContent)], structures.MarkupContent.reader.widen[(String | structures.MarkupContent)])
  private given reader_rd0: Reader[(String | structures.MarkupContent)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { v => 
      (v: @unchecked) match 
        case v: String => writeJs(v)
        case v: structures.MarkupContent => writeJs(v)
    }
  private given writer_wt0: Writer[(String | structures.MarkupContent)] = wt0
  given reader: Reader[structures.SignatureInformation] = Pickle.macroR
  given writer: Writer[structures.SignatureInformation] = upickle.default.macroW

case class SignatureHelpOptions(
  triggerCharacters: Vector[String],
  retriggerCharacters: Vector[String],
  workDoneProgress: Boolean
)
object SignatureHelpOptions:
  given reader: Reader[structures.SignatureHelpOptions] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpOptions] = upickle.default.macroW

case class DefinitionOptions(
  workDoneProgress: Boolean
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
  workDoneProgress: Boolean
)
object ReferenceOptions:
  given reader: Reader[structures.ReferenceOptions] = Pickle.macroR
  given writer: Writer[structures.ReferenceOptions] = upickle.default.macroW

case class DocumentHighlightOptions(
  workDoneProgress: Boolean
)
object DocumentHighlightOptions:
  given reader: Reader[structures.DocumentHighlightOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlightOptions] = upickle.default.macroW

case class BaseSymbolInformation(
  name: String,
  kind: enumerations.SymbolKind,
  tags: Vector[enumerations.SymbolTag],
  containerName: String
)
object BaseSymbolInformation:
  given reader: Reader[structures.BaseSymbolInformation] = Pickle.macroR
  given writer: Writer[structures.BaseSymbolInformation] = upickle.default.macroW

case class DocumentSymbolOptions(
  label: String,
  workDoneProgress: Boolean
)
object DocumentSymbolOptions:
  given reader: Reader[structures.DocumentSymbolOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbolOptions] = upickle.default.macroW

case class CodeActionContext(
  diagnostics: Vector[structures.Diagnostic],
  only: Vector[enumerations.CodeActionKind],
  triggerKind: enumerations.CodeActionTriggerKind
)
object CodeActionContext:
  given reader: Reader[structures.CodeActionContext] = Pickle.macroR
  given writer: Writer[structures.CodeActionContext] = upickle.default.macroW

case class CodeActionOptions(
  codeActionKinds: Vector[enumerations.CodeActionKind],
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object CodeActionOptions:
  given reader: Reader[structures.CodeActionOptions] = Pickle.macroR
  given writer: Writer[structures.CodeActionOptions] = upickle.default.macroW

case class WorkspaceSymbolOptions(
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object WorkspaceSymbolOptions:
  given reader: Reader[structures.WorkspaceSymbolOptions] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbolOptions] = upickle.default.macroW

case class CodeLensOptions(
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object CodeLensOptions:
  given reader: Reader[structures.CodeLensOptions] = Pickle.macroR
  given writer: Writer[structures.CodeLensOptions] = upickle.default.macroW

case class DocumentLinkOptions(
  resolveProvider: Boolean,
  workDoneProgress: Boolean
)
object DocumentLinkOptions:
  given reader: Reader[structures.DocumentLinkOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentLinkOptions] = upickle.default.macroW

case class FormattingOptions(
  tabSize: RuntimeBase.uinteger,
  insertSpaces: Boolean,
  trimTrailingWhitespace: Boolean,
  insertFinalNewline: Boolean,
  trimFinalNewlines: Boolean
)
object FormattingOptions:
  given reader: Reader[structures.FormattingOptions] = Pickle.macroR
  given writer: Writer[structures.FormattingOptions] = upickle.default.macroW

case class DocumentFormattingOptions(
  workDoneProgress: Boolean
)
object DocumentFormattingOptions:
  given reader: Reader[structures.DocumentFormattingOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentFormattingOptions] = upickle.default.macroW

case class DocumentRangeFormattingOptions(
  workDoneProgress: Boolean
)
object DocumentRangeFormattingOptions:
  given reader: Reader[structures.DocumentRangeFormattingOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentRangeFormattingOptions] = upickle.default.macroW

case class DocumentOnTypeFormattingOptions(
  firstTriggerCharacter: String,
  moreTriggerCharacter: Vector[String]
)
object DocumentOnTypeFormattingOptions:
  given reader: Reader[structures.DocumentOnTypeFormattingOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentOnTypeFormattingOptions] = upickle.default.macroW

case class RenameOptions(
  prepareProvider: Boolean,
  workDoneProgress: Boolean
)
object RenameOptions:
  given reader: Reader[structures.RenameOptions] = Pickle.macroR
  given writer: Writer[structures.RenameOptions] = upickle.default.macroW

case class ExecuteCommandOptions(
  commands: Vector[String],
  workDoneProgress: Boolean
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
  version: (Int | Null),
  uri: RuntimeBase.DocumentUri
)
object OptionalVersionedTextDocumentIdentifier:
  private val rd0 = badMerge(intCodec.widen[(Int | Null)], nullReadWriter.widen[(Int | Null)])
  private given reader_rd0: Reader[(Int | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(Int | Null)] { v => 
      (v: @unchecked) match 
        case v: Int => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(Int | Null)] = wt0
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
  annotationId: aliases.ChangeAnnotationIdentifier
)
object ResourceOperation:
  given reader: Reader[structures.ResourceOperation] = Pickle.macroR
  given writer: Writer[structures.ResourceOperation] = upickle.default.macroW

case class CreateFileOptions(
  overwrite: Boolean,
  ignoreIfExists: Boolean
)
object CreateFileOptions:
  given reader: Reader[structures.CreateFileOptions] = Pickle.macroR
  given writer: Writer[structures.CreateFileOptions] = upickle.default.macroW

case class RenameFileOptions(
  overwrite: Boolean,
  ignoreIfExists: Boolean
)
object RenameFileOptions:
  given reader: Reader[structures.RenameFileOptions] = Pickle.macroR
  given writer: Writer[structures.RenameFileOptions] = upickle.default.macroW

case class DeleteFileOptions(
  recursive: Boolean,
  ignoreIfNotExists: Boolean
)
object DeleteFileOptions:
  given reader: Reader[structures.DeleteFileOptions] = Pickle.macroR
  given writer: Writer[structures.DeleteFileOptions] = upickle.default.macroW

case class FileOperationPattern(
  glob: String,
  matches: enumerations.FileOperationPatternKind,
  options: structures.FileOperationPatternOptions
)
object FileOperationPattern:
  given reader: Reader[structures.FileOperationPattern] = Pickle.macroR
  given writer: Writer[structures.FileOperationPattern] = upickle.default.macroW

case class WorkspaceFullDocumentDiagnosticReport(
  uri: RuntimeBase.DocumentUri,
  version: (Int | Null),
  kind: "full",
  resultId: String,
  items: Vector[structures.Diagnostic]
)
object WorkspaceFullDocumentDiagnosticReport:
  private val rd0 = badMerge(intCodec.widen[(Int | Null)], nullReadWriter.widen[(Int | Null)])
  private given reader_rd0: Reader[(Int | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(Int | Null)] { v => 
      (v: @unchecked) match 
        case v: Int => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(Int | Null)] = wt0
  given reader: Reader[structures.WorkspaceFullDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.WorkspaceFullDocumentDiagnosticReport] = upickle.default.macroW

case class WorkspaceUnchangedDocumentDiagnosticReport(
  uri: RuntimeBase.DocumentUri,
  version: (Int | Null),
  kind: "unchanged",
  resultId: String
)
object WorkspaceUnchangedDocumentDiagnosticReport:
  private val rd0 = badMerge(intCodec.widen[(Int | Null)], nullReadWriter.widen[(Int | Null)])
  private given reader_rd0: Reader[(Int | Null)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(Int | Null)] { v => 
      (v: @unchecked) match 
        case v: Int => writeJs(v)
        case null => ujson.Null
    }
  private given writer_wt0: Writer[(Int | Null)] = wt0
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
  metadata: structures.LSPObject,
  executionSummary: structures.ExecutionSummary
)
object NotebookCell:
  given reader: Reader[structures.NotebookCell] = Pickle.macroR
  given writer: Writer[structures.NotebookCell] = upickle.default.macroW

case class NotebookCellArrayChange(
  start: RuntimeBase.uinteger,
  deleteCount: RuntimeBase.uinteger,
  cells: Vector[structures.NotebookCell]
)
object NotebookCellArrayChange:
  given reader: Reader[structures.NotebookCellArrayChange] = Pickle.macroR
  given writer: Writer[structures.NotebookCellArrayChange] = upickle.default.macroW

case class ClientCapabilities(
  workspace: structures.WorkspaceClientCapabilities,
  textDocument: structures.TextDocumentClientCapabilities,
  notebookDocument: structures.NotebookDocumentClientCapabilities,
  window: structures.WindowClientCapabilities,
  general: structures.GeneralClientCapabilities,
  experimental: ujson.Value
)
object ClientCapabilities:
  given reader: Reader[structures.ClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ClientCapabilities] = upickle.default.macroW

case class TextDocumentSyncOptions(
  openClose: Boolean,
  change: enumerations.TextDocumentSyncKind,
  willSave: Boolean,
  willSaveWaitUntil: Boolean,
  save: (Boolean | structures.SaveOptions)
)
object TextDocumentSyncOptions:
  private val rd0 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | structures.SaveOptions)], structures.SaveOptions.reader.widen[(Boolean | structures.SaveOptions)])
  private given reader_rd0: Reader[(Boolean | structures.SaveOptions)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.SaveOptions)] { v => 
      (v: @unchecked) match 
        case v: Boolean => writeJs(v)
        case v: structures.SaveOptions => writeJs(v)
    }
  private given writer_wt0: Writer[(Boolean | structures.SaveOptions)] = wt0
  given reader: Reader[structures.TextDocumentSyncOptions] = Pickle.macroR
  given writer: Writer[structures.TextDocumentSyncOptions] = upickle.default.macroW

case class NotebookDocumentSyncOptions(
  notebookSelector: Vector[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)],
  save: Boolean
)
object NotebookDocumentSyncOptions:
  private val rd0 = badMerge(NotebookDocumentSyncOptions.S0.reader.widen[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)], NotebookDocumentSyncOptions.S1.reader.widen[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)])
  private given reader_rd0: Reader[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)] { v => 
      (v: @unchecked) match 
        case v: NotebookDocumentSyncOptions.S0 => writeJs(v)
        case v: NotebookDocumentSyncOptions.S1 => writeJs(v)
    }
  private given writer_wt0: Writer[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)] = wt0
  given reader: Reader[structures.NotebookDocumentSyncOptions] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncOptions] = upickle.default.macroW
  case class S0(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Vector[S0.S0]
  )
  object S0:
    private val rd0 = badMerge(stringCodec.widen[(String | aliases.NotebookDocumentFilter)], aliases.NotebookDocumentFilter.reader.widen[(String | aliases.NotebookDocumentFilter)])
    private given reader_rd0: Reader[(String | aliases.NotebookDocumentFilter)] = rd0
    private val wt0 = 
      upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { v => 
        (v: @unchecked) match 
          case v: String => writeJs(v)
          case v: aliases.NotebookDocumentFilter => writeJs(v)
      }
    private given writer_wt0: Writer[(String | aliases.NotebookDocumentFilter)] = wt0
    given reader: Reader[structures.NotebookDocumentSyncOptions.S0] = Pickle.macroR
    given writer: Writer[structures.NotebookDocumentSyncOptions.S0] = upickle.default.macroW
    case class S0(
      language: String
    )
    object S0:
      given reader: Reader[structures.NotebookDocumentSyncOptions.S0.S0] = Pickle.macroR
      given writer: Writer[structures.NotebookDocumentSyncOptions.S0.S0] = upickle.default.macroW
  case class S1(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Vector[S1.S0]
  )
  object S1:
    private val rd0 = badMerge(stringCodec.widen[(String | aliases.NotebookDocumentFilter)], aliases.NotebookDocumentFilter.reader.widen[(String | aliases.NotebookDocumentFilter)])
    private given reader_rd0: Reader[(String | aliases.NotebookDocumentFilter)] = rd0
    private val wt0 = 
      upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { v => 
        (v: @unchecked) match 
          case v: String => writeJs(v)
          case v: aliases.NotebookDocumentFilter => writeJs(v)
      }
    private given writer_wt0: Writer[(String | aliases.NotebookDocumentFilter)] = wt0
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
  save: Boolean,
  id: String
)
object NotebookDocumentSyncRegistrationOptions:
  private val rd0 = badMerge(NotebookDocumentSyncRegistrationOptions.S0.reader.widen[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)], NotebookDocumentSyncRegistrationOptions.S1.reader.widen[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)])
  private given reader_rd0: Reader[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)] { v => 
      (v: @unchecked) match 
        case v: NotebookDocumentSyncRegistrationOptions.S0 => writeJs(v)
        case v: NotebookDocumentSyncRegistrationOptions.S1 => writeJs(v)
    }
  private given writer_wt0: Writer[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)] = wt0
  given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions] = upickle.default.macroW
  case class S0(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Vector[S0.S0]
  )
  object S0:
    private val rd0 = badMerge(stringCodec.widen[(String | aliases.NotebookDocumentFilter)], aliases.NotebookDocumentFilter.reader.widen[(String | aliases.NotebookDocumentFilter)])
    private given reader_rd0: Reader[(String | aliases.NotebookDocumentFilter)] = rd0
    private val wt0 = 
      upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { v => 
        (v: @unchecked) match 
          case v: String => writeJs(v)
          case v: aliases.NotebookDocumentFilter => writeJs(v)
      }
    private given writer_wt0: Writer[(String | aliases.NotebookDocumentFilter)] = wt0
    given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions.S0] = Pickle.macroR
    given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions.S0] = upickle.default.macroW
    case class S0(
      language: String
    )
    object S0:
      given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions.S0.S0] = Pickle.macroR
      given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions.S0.S0] = upickle.default.macroW
  case class S1(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Vector[S1.S0]
  )
  object S1:
    private val rd0 = badMerge(stringCodec.widen[(String | aliases.NotebookDocumentFilter)], aliases.NotebookDocumentFilter.reader.widen[(String | aliases.NotebookDocumentFilter)])
    private given reader_rd0: Reader[(String | aliases.NotebookDocumentFilter)] = rd0
    private val wt0 = 
      upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { v => 
        (v: @unchecked) match 
          case v: String => writeJs(v)
          case v: aliases.NotebookDocumentFilter => writeJs(v)
      }
    private given writer_wt0: Writer[(String | aliases.NotebookDocumentFilter)] = wt0
    given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions.S1] = Pickle.macroR
    given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions.S1] = upickle.default.macroW
    case class S0(
      language: String
    )
    object S0:
      given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions.S1.S0] = Pickle.macroR
      given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions.S1.S0] = upickle.default.macroW

case class WorkspaceFoldersServerCapabilities(
  supported: Boolean,
  changeNotifications: (String | Boolean)
)
object WorkspaceFoldersServerCapabilities:
  private val rd0 = badMerge(stringCodec.widen[(String | Boolean)], upickle.default.reader[Boolean].widen[(String | Boolean)])
  private given reader_rd0: Reader[(String | Boolean)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(String | Boolean)] { v => 
      (v: @unchecked) match 
        case v: String => writeJs(v)
        case v: Boolean => writeJs(v)
    }
  private given writer_wt0: Writer[(String | Boolean)] = wt0
  given reader: Reader[structures.WorkspaceFoldersServerCapabilities] = Pickle.macroR
  given writer: Writer[structures.WorkspaceFoldersServerCapabilities] = upickle.default.macroW

case class FileOperationOptions(
  didCreate: structures.FileOperationRegistrationOptions,
  willCreate: structures.FileOperationRegistrationOptions,
  didRename: structures.FileOperationRegistrationOptions,
  willRename: structures.FileOperationRegistrationOptions,
  didDelete: structures.FileOperationRegistrationOptions,
  willDelete: structures.FileOperationRegistrationOptions
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
  documentation: (String | structures.MarkupContent)
)
object ParameterInformation:
  private val rd0 = badMerge(stringCodec.widen[(String | (RuntimeBase.uinteger, RuntimeBase.uinteger))], upickle.default.reader[(RuntimeBase.uinteger, RuntimeBase.uinteger)].widen[(String | (RuntimeBase.uinteger, RuntimeBase.uinteger))])
  private given reader_rd0: Reader[(String | (RuntimeBase.uinteger, RuntimeBase.uinteger))] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(String | (RuntimeBase.uinteger, RuntimeBase.uinteger))] { v => 
      (v: @unchecked) match 
        case v: String => writeJs(v)
        case v: (RuntimeBase.uinteger, RuntimeBase.uinteger) => writeJs(v)
    }
  private given writer_wt0: Writer[(String | (RuntimeBase.uinteger, RuntimeBase.uinteger))] = wt0
  private val rd1 = badMerge(stringCodec.widen[(String | structures.MarkupContent)], structures.MarkupContent.reader.widen[(String | structures.MarkupContent)])
  private given reader_rd1: Reader[(String | structures.MarkupContent)] = rd1
  private val wt1 = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { v => 
      (v: @unchecked) match 
        case v: String => writeJs(v)
        case v: structures.MarkupContent => writeJs(v)
    }
  private given writer_wt1: Writer[(String | structures.MarkupContent)] = wt1
  given reader: Reader[structures.ParameterInformation] = Pickle.macroR
  given writer: Writer[structures.ParameterInformation] = upickle.default.macroW

case class NotebookCellTextDocumentFilter(
  notebook: (String | aliases.NotebookDocumentFilter),
  language: String
)
object NotebookCellTextDocumentFilter:
  private val rd0 = badMerge(stringCodec.widen[(String | aliases.NotebookDocumentFilter)], aliases.NotebookDocumentFilter.reader.widen[(String | aliases.NotebookDocumentFilter)])
  private given reader_rd0: Reader[(String | aliases.NotebookDocumentFilter)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { v => 
      (v: @unchecked) match 
        case v: String => writeJs(v)
        case v: aliases.NotebookDocumentFilter => writeJs(v)
    }
  private given writer_wt0: Writer[(String | aliases.NotebookDocumentFilter)] = wt0
  given reader: Reader[structures.NotebookCellTextDocumentFilter] = Pickle.macroR
  given writer: Writer[structures.NotebookCellTextDocumentFilter] = upickle.default.macroW

case class FileOperationPatternOptions(
  ignoreCase: Boolean
)
object FileOperationPatternOptions:
  given reader: Reader[structures.FileOperationPatternOptions] = Pickle.macroR
  given writer: Writer[structures.FileOperationPatternOptions] = upickle.default.macroW

case class ExecutionSummary(
  executionOrder: RuntimeBase.uinteger,
  success: Boolean
)
object ExecutionSummary:
  given reader: Reader[structures.ExecutionSummary] = Pickle.macroR
  given writer: Writer[structures.ExecutionSummary] = upickle.default.macroW

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
  given reader: Reader[structures.WorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.WorkspaceClientCapabilities] = upickle.default.macroW

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
  given reader: Reader[structures.TextDocumentClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.TextDocumentClientCapabilities] = upickle.default.macroW

case class NotebookDocumentClientCapabilities(
  synchronization: structures.NotebookDocumentSyncClientCapabilities
)
object NotebookDocumentClientCapabilities:
  given reader: Reader[structures.NotebookDocumentClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentClientCapabilities] = upickle.default.macroW

case class WindowClientCapabilities(
  workDoneProgress: Boolean,
  showMessage: structures.ShowMessageRequestClientCapabilities,
  showDocument: structures.ShowDocumentClientCapabilities
)
object WindowClientCapabilities:
  given reader: Reader[structures.WindowClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.WindowClientCapabilities] = upickle.default.macroW

case class GeneralClientCapabilities(
  staleRequestSupport: GeneralClientCapabilities.StaleRequestSupport,
  regularExpressions: structures.RegularExpressionsClientCapabilities,
  markdown: structures.MarkdownClientCapabilities,
  positionEncodings: Vector[enumerations.PositionEncodingKind]
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
  private val rd0 = badMerge(structures.WorkspaceFolder.reader.widen[(structures.WorkspaceFolder | aliases.URI)], aliases.URI.reader.widen[(structures.WorkspaceFolder | aliases.URI)])
  private given reader_rd0: Reader[(structures.WorkspaceFolder | aliases.URI)] = rd0
  private val wt0 = 
    upickle.default.writer[ujson.Value].comap[(structures.WorkspaceFolder | aliases.URI)] { v => 
      (v: @unchecked) match 
        case v: structures.WorkspaceFolder => writeJs(v)
        case v: aliases.URI => writeJs(v)
    }
  private given writer_wt0: Writer[(structures.WorkspaceFolder | aliases.URI)] = wt0
  given reader: Reader[structures.RelativePattern] = Pickle.macroR
  given writer: Writer[structures.RelativePattern] = upickle.default.macroW

case class WorkspaceEditClientCapabilities(
  documentChanges: Boolean,
  resourceOperations: Vector[enumerations.ResourceOperationKind],
  failureHandling: enumerations.FailureHandlingKind,
  normalizesLineEndings: Boolean,
  changeAnnotationSupport: WorkspaceEditClientCapabilities.ChangeAnnotationSupport
)
object WorkspaceEditClientCapabilities:
  given reader: Reader[structures.WorkspaceEditClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.WorkspaceEditClientCapabilities] = upickle.default.macroW
  case class ChangeAnnotationSupport(
    groupsOnLabel: Boolean
  )
  object ChangeAnnotationSupport:
    given reader: Reader[structures.WorkspaceEditClientCapabilities.ChangeAnnotationSupport] = Pickle.macroR
    given writer: Writer[structures.WorkspaceEditClientCapabilities.ChangeAnnotationSupport] = upickle.default.macroW

case class DidChangeConfigurationClientCapabilities(
  dynamicRegistration: Boolean
)
object DidChangeConfigurationClientCapabilities:
  given reader: Reader[structures.DidChangeConfigurationClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DidChangeConfigurationClientCapabilities] = upickle.default.macroW

case class DidChangeWatchedFilesClientCapabilities(
  dynamicRegistration: Boolean,
  relativePatternSupport: Boolean
)
object DidChangeWatchedFilesClientCapabilities:
  given reader: Reader[structures.DidChangeWatchedFilesClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DidChangeWatchedFilesClientCapabilities] = upickle.default.macroW

case class WorkspaceSymbolClientCapabilities(
  dynamicRegistration: Boolean,
  symbolKind: WorkspaceSymbolClientCapabilities.SymbolKind,
  tagSupport: WorkspaceSymbolClientCapabilities.TagSupport,
  resolveSupport: WorkspaceSymbolClientCapabilities.ResolveSupport
)
object WorkspaceSymbolClientCapabilities:
  given reader: Reader[structures.WorkspaceSymbolClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbolClientCapabilities] = upickle.default.macroW
  case class SymbolKind(
    valueSet: Vector[enumerations.SymbolKind]
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
  dynamicRegistration: Boolean
)
object ExecuteCommandClientCapabilities:
  given reader: Reader[structures.ExecuteCommandClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ExecuteCommandClientCapabilities] = upickle.default.macroW

case class SemanticTokensWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object SemanticTokensWorkspaceClientCapabilities:
  given reader: Reader[structures.SemanticTokensWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensWorkspaceClientCapabilities] = upickle.default.macroW

case class CodeLensWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object CodeLensWorkspaceClientCapabilities:
  given reader: Reader[structures.CodeLensWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.CodeLensWorkspaceClientCapabilities] = upickle.default.macroW

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
  given reader: Reader[structures.FileOperationClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.FileOperationClientCapabilities] = upickle.default.macroW

case class InlineValueWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object InlineValueWorkspaceClientCapabilities:
  given reader: Reader[structures.InlineValueWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.InlineValueWorkspaceClientCapabilities] = upickle.default.macroW

case class InlayHintWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object InlayHintWorkspaceClientCapabilities:
  given reader: Reader[structures.InlayHintWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.InlayHintWorkspaceClientCapabilities] = upickle.default.macroW

case class DiagnosticWorkspaceClientCapabilities(
  refreshSupport: Boolean
)
object DiagnosticWorkspaceClientCapabilities:
  given reader: Reader[structures.DiagnosticWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DiagnosticWorkspaceClientCapabilities] = upickle.default.macroW

case class TextDocumentSyncClientCapabilities(
  dynamicRegistration: Boolean,
  willSave: Boolean,
  willSaveWaitUntil: Boolean,
  didSave: Boolean
)
object TextDocumentSyncClientCapabilities:
  given reader: Reader[structures.TextDocumentSyncClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.TextDocumentSyncClientCapabilities] = upickle.default.macroW

case class CompletionClientCapabilities(
  dynamicRegistration: Boolean,
  completionItem: CompletionClientCapabilities.CompletionItem,
  completionItemKind: CompletionClientCapabilities.CompletionItemKind,
  insertTextMode: enumerations.InsertTextMode,
  contextSupport: Boolean,
  completionList: CompletionClientCapabilities.CompletionList
)
object CompletionClientCapabilities:
  given reader: Reader[structures.CompletionClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.CompletionClientCapabilities] = upickle.default.macroW
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
    valueSet: Vector[enumerations.CompletionItemKind]
  )
  object CompletionItemKind:
    given reader: Reader[structures.CompletionClientCapabilities.CompletionItemKind] = Pickle.macroR
    given writer: Writer[structures.CompletionClientCapabilities.CompletionItemKind] = upickle.default.macroW
  case class CompletionList(
    itemDefaults: Vector[String]
  )
  object CompletionList:
    given reader: Reader[structures.CompletionClientCapabilities.CompletionList] = Pickle.macroR
    given writer: Writer[structures.CompletionClientCapabilities.CompletionList] = upickle.default.macroW

case class HoverClientCapabilities(
  dynamicRegistration: Boolean,
  contentFormat: Vector[enumerations.MarkupKind]
)
object HoverClientCapabilities:
  given reader: Reader[structures.HoverClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.HoverClientCapabilities] = upickle.default.macroW

case class SignatureHelpClientCapabilities(
  dynamicRegistration: Boolean,
  signatureInformation: SignatureHelpClientCapabilities.SignatureInformation,
  contextSupport: Boolean
)
object SignatureHelpClientCapabilities:
  given reader: Reader[structures.SignatureHelpClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpClientCapabilities] = upickle.default.macroW
  case class SignatureInformation(
    documentationFormat: Vector[enumerations.MarkupKind],
    parameterInformation: SignatureInformation.ParameterInformation,
    activeParameterSupport: Boolean
  )
  object SignatureInformation:
    given reader: Reader[structures.SignatureHelpClientCapabilities.SignatureInformation] = Pickle.macroR
    given writer: Writer[structures.SignatureHelpClientCapabilities.SignatureInformation] = upickle.default.macroW
    case class ParameterInformation(
      labelOffsetSupport: Boolean
    )
    object ParameterInformation:
      given reader: Reader[structures.SignatureHelpClientCapabilities.SignatureInformation.ParameterInformation] = Pickle.macroR
      given writer: Writer[structures.SignatureHelpClientCapabilities.SignatureInformation.ParameterInformation] = upickle.default.macroW

case class DeclarationClientCapabilities(
  dynamicRegistration: Boolean,
  linkSupport: Boolean
)
object DeclarationClientCapabilities:
  given reader: Reader[structures.DeclarationClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DeclarationClientCapabilities] = upickle.default.macroW

case class DefinitionClientCapabilities(
  dynamicRegistration: Boolean,
  linkSupport: Boolean
)
object DefinitionClientCapabilities:
  given reader: Reader[structures.DefinitionClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DefinitionClientCapabilities] = upickle.default.macroW

case class TypeDefinitionClientCapabilities(
  dynamicRegistration: Boolean,
  linkSupport: Boolean
)
object TypeDefinitionClientCapabilities:
  given reader: Reader[structures.TypeDefinitionClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.TypeDefinitionClientCapabilities] = upickle.default.macroW

case class ImplementationClientCapabilities(
  dynamicRegistration: Boolean,
  linkSupport: Boolean
)
object ImplementationClientCapabilities:
  given reader: Reader[structures.ImplementationClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ImplementationClientCapabilities] = upickle.default.macroW

case class ReferenceClientCapabilities(
  dynamicRegistration: Boolean
)
object ReferenceClientCapabilities:
  given reader: Reader[structures.ReferenceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ReferenceClientCapabilities] = upickle.default.macroW

case class DocumentHighlightClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentHighlightClientCapabilities:
  given reader: Reader[structures.DocumentHighlightClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlightClientCapabilities] = upickle.default.macroW

case class DocumentSymbolClientCapabilities(
  dynamicRegistration: Boolean,
  symbolKind: DocumentSymbolClientCapabilities.SymbolKind,
  hierarchicalDocumentSymbolSupport: Boolean,
  tagSupport: DocumentSymbolClientCapabilities.TagSupport,
  labelSupport: Boolean
)
object DocumentSymbolClientCapabilities:
  given reader: Reader[structures.DocumentSymbolClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbolClientCapabilities] = upickle.default.macroW
  case class SymbolKind(
    valueSet: Vector[enumerations.SymbolKind]
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
  dynamicRegistration: Boolean,
  codeActionLiteralSupport: CodeActionClientCapabilities.CodeActionLiteralSupport,
  isPreferredSupport: Boolean,
  disabledSupport: Boolean,
  dataSupport: Boolean,
  resolveSupport: CodeActionClientCapabilities.ResolveSupport,
  honorsChangeAnnotations: Boolean
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
  dynamicRegistration: Boolean
)
object CodeLensClientCapabilities:
  given reader: Reader[structures.CodeLensClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.CodeLensClientCapabilities] = upickle.default.macroW

case class DocumentLinkClientCapabilities(
  dynamicRegistration: Boolean,
  tooltipSupport: Boolean
)
object DocumentLinkClientCapabilities:
  given reader: Reader[structures.DocumentLinkClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentLinkClientCapabilities] = upickle.default.macroW

case class DocumentColorClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentColorClientCapabilities:
  given reader: Reader[structures.DocumentColorClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentColorClientCapabilities] = upickle.default.macroW

case class DocumentFormattingClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentFormattingClientCapabilities:
  given reader: Reader[structures.DocumentFormattingClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentFormattingClientCapabilities] = upickle.default.macroW

case class DocumentRangeFormattingClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentRangeFormattingClientCapabilities:
  given reader: Reader[structures.DocumentRangeFormattingClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentRangeFormattingClientCapabilities] = upickle.default.macroW

case class DocumentOnTypeFormattingClientCapabilities(
  dynamicRegistration: Boolean
)
object DocumentOnTypeFormattingClientCapabilities:
  given reader: Reader[structures.DocumentOnTypeFormattingClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentOnTypeFormattingClientCapabilities] = upickle.default.macroW

case class RenameClientCapabilities(
  dynamicRegistration: Boolean,
  prepareSupport: Boolean,
  prepareSupportDefaultBehavior: enumerations.PrepareSupportDefaultBehavior,
  honorsChangeAnnotations: Boolean
)
object RenameClientCapabilities:
  given reader: Reader[structures.RenameClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.RenameClientCapabilities] = upickle.default.macroW

case class FoldingRangeClientCapabilities(
  dynamicRegistration: Boolean,
  rangeLimit: RuntimeBase.uinteger,
  lineFoldingOnly: Boolean,
  foldingRangeKind: FoldingRangeClientCapabilities.FoldingRangeKind,
  foldingRange: FoldingRangeClientCapabilities.FoldingRange
)
object FoldingRangeClientCapabilities:
  given reader: Reader[structures.FoldingRangeClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.FoldingRangeClientCapabilities] = upickle.default.macroW
  case class FoldingRangeKind(
    valueSet: Vector[enumerations.FoldingRangeKind]
  )
  object FoldingRangeKind:
    given reader: Reader[structures.FoldingRangeClientCapabilities.FoldingRangeKind] = Pickle.macroR
    given writer: Writer[structures.FoldingRangeClientCapabilities.FoldingRangeKind] = upickle.default.macroW
  case class FoldingRange(
    collapsedText: Boolean
  )
  object FoldingRange:
    given reader: Reader[structures.FoldingRangeClientCapabilities.FoldingRange] = Pickle.macroR
    given writer: Writer[structures.FoldingRangeClientCapabilities.FoldingRange] = upickle.default.macroW

case class SelectionRangeClientCapabilities(
  dynamicRegistration: Boolean
)
object SelectionRangeClientCapabilities:
  given reader: Reader[structures.SelectionRangeClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.SelectionRangeClientCapabilities] = upickle.default.macroW

case class PublishDiagnosticsClientCapabilities(
  relatedInformation: Boolean,
  tagSupport: PublishDiagnosticsClientCapabilities.TagSupport,
  versionSupport: Boolean,
  codeDescriptionSupport: Boolean,
  dataSupport: Boolean
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
  dynamicRegistration: Boolean
)
object CallHierarchyClientCapabilities:
  given reader: Reader[structures.CallHierarchyClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyClientCapabilities] = upickle.default.macroW

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
  given reader: Reader[structures.SemanticTokensClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensClientCapabilities] = upickle.default.macroW
  case class Requests(
    range: (Boolean | Requests.S0),
    full: (Boolean | Requests.S1)
  )
  object Requests:
    private val rd0 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | Requests.S0)], Requests.S0.reader.widen[(Boolean | Requests.S0)])
    private given reader_rd0: Reader[(Boolean | Requests.S0)] = rd0
    private val wt0 = 
      upickle.default.writer[ujson.Value].comap[(Boolean | Requests.S0)] { v => 
        (v: @unchecked) match 
          case v: Boolean => writeJs(v)
          case v: Requests.S0 => writeJs(v)
      }
    private given writer_wt0: Writer[(Boolean | Requests.S0)] = wt0
    private val rd1 = badMerge(upickle.default.reader[Boolean].widen[(Boolean | Requests.S1)], Requests.S1.reader.widen[(Boolean | Requests.S1)])
    private given reader_rd1: Reader[(Boolean | Requests.S1)] = rd1
    private val wt1 = 
      upickle.default.writer[ujson.Value].comap[(Boolean | Requests.S1)] { v => 
        (v: @unchecked) match 
          case v: Boolean => writeJs(v)
          case v: Requests.S1 => writeJs(v)
      }
    private given writer_wt1: Writer[(Boolean | Requests.S1)] = wt1
    given reader: Reader[structures.SemanticTokensClientCapabilities.Requests] = Pickle.macroR
    given writer: Writer[structures.SemanticTokensClientCapabilities.Requests] = upickle.default.macroW
    case class S0(
    )
    object S0:
      given reader: Reader[structures.SemanticTokensClientCapabilities.Requests.S0] = Pickle.macroR
      given writer: Writer[structures.SemanticTokensClientCapabilities.Requests.S0] = upickle.default.macroW
    case class S1(
      delta: Boolean
    )
    object S1:
      given reader: Reader[structures.SemanticTokensClientCapabilities.Requests.S1] = Pickle.macroR
      given writer: Writer[structures.SemanticTokensClientCapabilities.Requests.S1] = upickle.default.macroW

case class LinkedEditingRangeClientCapabilities(
  dynamicRegistration: Boolean
)
object LinkedEditingRangeClientCapabilities:
  given reader: Reader[structures.LinkedEditingRangeClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.LinkedEditingRangeClientCapabilities] = upickle.default.macroW

case class MonikerClientCapabilities(
  dynamicRegistration: Boolean
)
object MonikerClientCapabilities:
  given reader: Reader[structures.MonikerClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.MonikerClientCapabilities] = upickle.default.macroW

case class TypeHierarchyClientCapabilities(
  dynamicRegistration: Boolean
)
object TypeHierarchyClientCapabilities:
  given reader: Reader[structures.TypeHierarchyClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchyClientCapabilities] = upickle.default.macroW

case class InlineValueClientCapabilities(
  dynamicRegistration: Boolean
)
object InlineValueClientCapabilities:
  given reader: Reader[structures.InlineValueClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.InlineValueClientCapabilities] = upickle.default.macroW

case class InlayHintClientCapabilities(
  dynamicRegistration: Boolean,
  resolveSupport: InlayHintClientCapabilities.ResolveSupport
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
  dynamicRegistration: Boolean,
  relatedDocumentSupport: Boolean
)
object DiagnosticClientCapabilities:
  given reader: Reader[structures.DiagnosticClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DiagnosticClientCapabilities] = upickle.default.macroW

case class NotebookDocumentSyncClientCapabilities(
  dynamicRegistration: Boolean,
  executionSummarySupport: Boolean
)
object NotebookDocumentSyncClientCapabilities:
  given reader: Reader[structures.NotebookDocumentSyncClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncClientCapabilities] = upickle.default.macroW

case class ShowMessageRequestClientCapabilities(
  messageActionItem: ShowMessageRequestClientCapabilities.MessageActionItem
)
object ShowMessageRequestClientCapabilities:
  given reader: Reader[structures.ShowMessageRequestClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ShowMessageRequestClientCapabilities] = upickle.default.macroW
  case class MessageActionItem(
    additionalPropertiesSupport: Boolean
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
  version: String
)
object RegularExpressionsClientCapabilities:
  given reader: Reader[structures.RegularExpressionsClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.RegularExpressionsClientCapabilities] = upickle.default.macroW

case class MarkdownClientCapabilities(
  parser: String,
  version: String,
  allowedTags: Vector[String]
)
object MarkdownClientCapabilities:
  given reader: Reader[structures.MarkdownClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.MarkdownClientCapabilities] = upickle.default.macroW

