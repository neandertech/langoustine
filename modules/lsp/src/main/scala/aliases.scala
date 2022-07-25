package langoustine.lsp

import langoustine.*
import langoustine.lsp.json.{*, given}
import upickle.default.*

object aliases: 
  opaque type Definition = (structures.Location | Vector[structures.Location])
  object Definition:
    given codec: Reader[Definition] = badMerge(structures.Location.codec.widen[Definition], reader[Vector[structures.Location]].widen[Definition])
  opaque type DefinitionLink = structures.LocationLink
  object DefinitionLink:
    given codec: Reader[DefinitionLink] = structures.LocationLink.codec
  case class LSPArray(elements: Vector[aliases.LSPAny])
  object LSPArray:
    given codec: Reader[LSPArray] = reader[Vector[aliases.LSPAny]].map(LSPArray.apply)
  opaque type LSPAny = (structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)
  object LSPAny:
    given codec: Reader[LSPAny] = badMerge(structures.LSPObject.codec.widen[LSPAny], aliases.LSPArray.codec.widen[LSPAny], stringCodec.widen[LSPAny], intCodec.widen[LSPAny], reader[RuntimeBase.uinteger].widen[LSPAny], reader[Float].widen[LSPAny], reader[Boolean].widen[LSPAny], nullReadWriter.widen[LSPAny])
  opaque type Declaration = (structures.Location | Vector[structures.Location])
  object Declaration:
    given codec: Reader[Declaration] = badMerge(structures.Location.codec.widen[Declaration], reader[Vector[structures.Location]].widen[Declaration])
  opaque type DeclarationLink = structures.LocationLink
  object DeclarationLink:
    given codec: Reader[DeclarationLink] = structures.LocationLink.codec
  opaque type InlineValue = (structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)
  object InlineValue:
    given codec: Reader[InlineValue] = badMerge(structures.InlineValueText.codec.widen[InlineValue], structures.InlineValueVariableLookup.codec.widen[InlineValue], structures.InlineValueEvaluatableExpression.codec.widen[InlineValue])
  opaque type DocumentDiagnosticReport = (structures.RelatedFullDocumentDiagnosticReport | structures.RelatedUnchangedDocumentDiagnosticReport)
  object DocumentDiagnosticReport:
    given codec: Reader[DocumentDiagnosticReport] = badMerge(structures.RelatedFullDocumentDiagnosticReport.codec.widen[DocumentDiagnosticReport], structures.RelatedUnchangedDocumentDiagnosticReport.codec.widen[DocumentDiagnosticReport])
  opaque type PrepareRenameResult = (structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)
  object PrepareRenameResult:
    given codec: Reader[PrepareRenameResult] = badMerge(structures.Range.codec.widen[PrepareRenameResult], PrepareRenameResult.S0.codec.widen[PrepareRenameResult], PrepareRenameResult.S1.codec.widen[PrepareRenameResult])
    case class S0(
      range: structures.Range,
      placeholder: String
    )
    object S0:
      given codec: Reader[aliases.PrepareRenameResult.S0] = Pickle.macroR
    case class S1(
      defaultBehavior: Boolean
    )
    object S1:
      given codec: Reader[aliases.PrepareRenameResult.S1] = Pickle.macroR
  opaque type URI = String
  object URI:
    given codec: Reader[URI] = stringCodec
  opaque type ProgressToken = (Int | String)
  object ProgressToken:
    given codec: Reader[ProgressToken] = badMerge(intCodec.widen[ProgressToken], stringCodec.widen[ProgressToken])
  opaque type DocumentSelector = Vector[(String | aliases.DocumentFilter)]
  object DocumentSelector:
    given codec: Reader[DocumentSelector] = reader[Vector[(String | aliases.DocumentFilter)]]
  opaque type ChangeAnnotationIdentifier = String
  object ChangeAnnotationIdentifier:
    given codec: Reader[ChangeAnnotationIdentifier] = stringCodec
  opaque type WorkspaceDocumentDiagnosticReport = (structures.WorkspaceFullDocumentDiagnosticReport | structures.WorkspaceUnchangedDocumentDiagnosticReport)
  object WorkspaceDocumentDiagnosticReport:
    given codec: Reader[WorkspaceDocumentDiagnosticReport] = badMerge(structures.WorkspaceFullDocumentDiagnosticReport.codec.widen[WorkspaceDocumentDiagnosticReport], structures.WorkspaceUnchangedDocumentDiagnosticReport.codec.widen[WorkspaceDocumentDiagnosticReport])
  opaque type TextDocumentContentChangeEvent = (TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)
  object TextDocumentContentChangeEvent:
    given codec: Reader[TextDocumentContentChangeEvent] = badMerge(TextDocumentContentChangeEvent.S0.codec.widen[TextDocumentContentChangeEvent], TextDocumentContentChangeEvent.S1.codec.widen[TextDocumentContentChangeEvent])
    case class S0(
      range: structures.Range,
      rangeLength: RuntimeBase.uinteger,
      text: String
    )
    object S0:
      given codec: Reader[aliases.TextDocumentContentChangeEvent.S0] = Pickle.macroR
    case class S1(
      text: String
    )
    object S1:
      given codec: Reader[aliases.TextDocumentContentChangeEvent.S1] = Pickle.macroR
  opaque type MarkedString = (String | MarkedString.S0)
  object MarkedString:
    given codec: Reader[MarkedString] = badMerge(stringCodec.widen[MarkedString], MarkedString.S0.codec.widen[MarkedString])
    case class S0(
      language: String,
      value: String
    )
    object S0:
      given codec: Reader[aliases.MarkedString.S0] = Pickle.macroR
  opaque type DocumentFilter = (aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)
  object DocumentFilter:
    given codec: Reader[DocumentFilter] = badMerge(aliases.TextDocumentFilter.codec.widen[DocumentFilter], structures.NotebookCellTextDocumentFilter.codec.widen[DocumentFilter])
  opaque type GlobPattern = (aliases.Pattern | structures.RelativePattern)
  object GlobPattern:
    given codec: Reader[GlobPattern] = badMerge(aliases.Pattern.codec.widen[GlobPattern], structures.RelativePattern.codec.widen[GlobPattern])
  opaque type TextDocumentFilter = (TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)
  object TextDocumentFilter:
    given codec: Reader[TextDocumentFilter] = badMerge(TextDocumentFilter.S0.codec.widen[TextDocumentFilter], TextDocumentFilter.S1.codec.widen[TextDocumentFilter], TextDocumentFilter.S2.codec.widen[TextDocumentFilter])
    case class S0(
      language: String,
      scheme: String,
      pattern: String
    )
    object S0:
      given codec: Reader[aliases.TextDocumentFilter.S0] = Pickle.macroR
    case class S1(
      language: String,
      scheme: String,
      pattern: String
    )
    object S1:
      given codec: Reader[aliases.TextDocumentFilter.S1] = Pickle.macroR
    case class S2(
      language: String,
      scheme: String,
      pattern: String
    )
    object S2:
      given codec: Reader[aliases.TextDocumentFilter.S2] = Pickle.macroR
  opaque type NotebookDocumentFilter = (NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 | NotebookDocumentFilter.S2)
  object NotebookDocumentFilter:
    given codec: Reader[NotebookDocumentFilter] = badMerge(NotebookDocumentFilter.S0.codec.widen[NotebookDocumentFilter], NotebookDocumentFilter.S1.codec.widen[NotebookDocumentFilter], NotebookDocumentFilter.S2.codec.widen[NotebookDocumentFilter])
    case class S0(
      notebookType: String,
      scheme: String,
      pattern: String
    )
    object S0:
      given codec: Reader[aliases.NotebookDocumentFilter.S0] = Pickle.macroR
    case class S1(
      notebookType: String,
      scheme: String,
      pattern: String
    )
    object S1:
      given codec: Reader[aliases.NotebookDocumentFilter.S1] = Pickle.macroR
    case class S2(
      notebookType: String,
      scheme: String,
      pattern: String
    )
    object S2:
      given codec: Reader[aliases.NotebookDocumentFilter.S2] = Pickle.macroR
  opaque type Pattern = String
  object Pattern:
    given codec: Reader[Pattern] = stringCodec
