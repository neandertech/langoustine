package langoustine.lsp

import langoustine.*
import langoustine.lsp.json.{*, given}
import upickle.default.*

object aliases: 
  opaque type Definition = (structures.Location | Vector[structures.Location])
  object Definition:
    private val rd0 = badMerge(structures.Location.codec.widen[(structures.Location | Vector[structures.Location])], reader[Vector[structures.Location]].widen[(structures.Location | Vector[structures.Location])])
    private given reader_rd0: Reader[(structures.Location | Vector[structures.Location])] = rd0
    private val _codec: Reader[Definition] = badMerge(structures.Location.codec.widen[Definition], reader[Vector[structures.Location]].widen[Definition])
    given codec: Reader[Definition] = _codec
  opaque type DefinitionLink = structures.LocationLink
  object DefinitionLink:
    private val _codec: Reader[DefinitionLink] = structures.LocationLink.codec
    given codec: Reader[DefinitionLink] = _codec
  case class LSPArray(elements: Vector[aliases.LSPAny])
  object LSPArray:
    given codec: Reader[LSPArray] = reader[Vector[aliases.LSPAny]].map(LSPArray.apply)
  opaque type LSPAny = (structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)
  object LSPAny:
    private val rd0 = badMerge(structures.LSPObject.codec.widen[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)], aliases.LSPArray.codec.widen[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)], stringCodec.widen[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)], intCodec.widen[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)], reader[RuntimeBase.uinteger].widen[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)], reader[Float].widen[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)], reader[Boolean].widen[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)], nullReadWriter.widen[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)])
    private given reader_rd0: Reader[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)] = rd0
    private val _codec: Reader[LSPAny] = badMerge(structures.LSPObject.codec.widen[LSPAny], aliases.LSPArray.codec.widen[LSPAny], stringCodec.widen[LSPAny], intCodec.widen[LSPAny], reader[RuntimeBase.uinteger].widen[LSPAny], reader[Float].widen[LSPAny], reader[Boolean].widen[LSPAny], nullReadWriter.widen[LSPAny])
    given codec: Reader[LSPAny] = _codec
  opaque type Declaration = (structures.Location | Vector[structures.Location])
  object Declaration:
    private val rd0 = badMerge(structures.Location.codec.widen[(structures.Location | Vector[structures.Location])], reader[Vector[structures.Location]].widen[(structures.Location | Vector[structures.Location])])
    private given reader_rd0: Reader[(structures.Location | Vector[structures.Location])] = rd0
    private val _codec: Reader[Declaration] = badMerge(structures.Location.codec.widen[Declaration], reader[Vector[structures.Location]].widen[Declaration])
    given codec: Reader[Declaration] = _codec
  opaque type DeclarationLink = structures.LocationLink
  object DeclarationLink:
    private val _codec: Reader[DeclarationLink] = structures.LocationLink.codec
    given codec: Reader[DeclarationLink] = _codec
  opaque type InlineValue = (structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)
  object InlineValue:
    private val rd0 = badMerge(structures.InlineValueText.codec.widen[(structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)], structures.InlineValueVariableLookup.codec.widen[(structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)], structures.InlineValueEvaluatableExpression.codec.widen[(structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)])
    private given reader_rd0: Reader[(structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)] = rd0
    private val _codec: Reader[InlineValue] = badMerge(structures.InlineValueText.codec.widen[InlineValue], structures.InlineValueVariableLookup.codec.widen[InlineValue], structures.InlineValueEvaluatableExpression.codec.widen[InlineValue])
    given codec: Reader[InlineValue] = _codec
  opaque type DocumentDiagnosticReport = (structures.RelatedFullDocumentDiagnosticReport | structures.RelatedUnchangedDocumentDiagnosticReport)
  object DocumentDiagnosticReport:
    private val rd0 = badMerge(structures.RelatedFullDocumentDiagnosticReport.codec.widen[(structures.RelatedFullDocumentDiagnosticReport | structures.RelatedUnchangedDocumentDiagnosticReport)], structures.RelatedUnchangedDocumentDiagnosticReport.codec.widen[(structures.RelatedFullDocumentDiagnosticReport | structures.RelatedUnchangedDocumentDiagnosticReport)])
    private given reader_rd0: Reader[(structures.RelatedFullDocumentDiagnosticReport | structures.RelatedUnchangedDocumentDiagnosticReport)] = rd0
    private val _codec: Reader[DocumentDiagnosticReport] = badMerge(structures.RelatedFullDocumentDiagnosticReport.codec.widen[DocumentDiagnosticReport], structures.RelatedUnchangedDocumentDiagnosticReport.codec.widen[DocumentDiagnosticReport])
    given codec: Reader[DocumentDiagnosticReport] = _codec
  opaque type PrepareRenameResult = (structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)
  object PrepareRenameResult:
    private val rd0 = badMerge(structures.Range.codec.widen[(structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)], PrepareRenameResult.S0.codec.widen[(structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)], PrepareRenameResult.S1.codec.widen[(structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)])
    private given reader_rd0: Reader[(structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)] = rd0
    private val _codec: Reader[PrepareRenameResult] = badMerge(structures.Range.codec.widen[PrepareRenameResult], PrepareRenameResult.S0.codec.widen[PrepareRenameResult], PrepareRenameResult.S1.codec.widen[PrepareRenameResult])
    given codec: Reader[PrepareRenameResult] = _codec
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
    private val _codec: Reader[URI] = stringCodec
    given codec: Reader[URI] = _codec
  opaque type ProgressToken = (Int | String)
  object ProgressToken:
    private val rd0 = badMerge(intCodec.widen[(Int | String)], stringCodec.widen[(Int | String)])
    private given reader_rd0: Reader[(Int | String)] = rd0
    private val _codec: Reader[ProgressToken] = badMerge(intCodec.widen[ProgressToken], stringCodec.widen[ProgressToken])
    given codec: Reader[ProgressToken] = _codec
  opaque type DocumentSelector = Vector[(String | aliases.DocumentFilter)]
  object DocumentSelector:
    private val rd0 = badMerge(stringCodec.widen[(String | aliases.DocumentFilter)], aliases.DocumentFilter.codec.widen[(String | aliases.DocumentFilter)])
    private given reader_rd0: Reader[(String | aliases.DocumentFilter)] = rd0
    private val _codec: Reader[DocumentSelector] = reader[Vector[(String | aliases.DocumentFilter)]]
    given codec: Reader[DocumentSelector] = _codec
  opaque type ChangeAnnotationIdentifier = String
  object ChangeAnnotationIdentifier:
    private val _codec: Reader[ChangeAnnotationIdentifier] = stringCodec
    given codec: Reader[ChangeAnnotationIdentifier] = _codec
  opaque type WorkspaceDocumentDiagnosticReport = (structures.WorkspaceFullDocumentDiagnosticReport | structures.WorkspaceUnchangedDocumentDiagnosticReport)
  object WorkspaceDocumentDiagnosticReport:
    private val rd0 = badMerge(structures.WorkspaceFullDocumentDiagnosticReport.codec.widen[(structures.WorkspaceFullDocumentDiagnosticReport | structures.WorkspaceUnchangedDocumentDiagnosticReport)], structures.WorkspaceUnchangedDocumentDiagnosticReport.codec.widen[(structures.WorkspaceFullDocumentDiagnosticReport | structures.WorkspaceUnchangedDocumentDiagnosticReport)])
    private given reader_rd0: Reader[(structures.WorkspaceFullDocumentDiagnosticReport | structures.WorkspaceUnchangedDocumentDiagnosticReport)] = rd0
    private val _codec: Reader[WorkspaceDocumentDiagnosticReport] = badMerge(structures.WorkspaceFullDocumentDiagnosticReport.codec.widen[WorkspaceDocumentDiagnosticReport], structures.WorkspaceUnchangedDocumentDiagnosticReport.codec.widen[WorkspaceDocumentDiagnosticReport])
    given codec: Reader[WorkspaceDocumentDiagnosticReport] = _codec
  opaque type TextDocumentContentChangeEvent = (TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)
  object TextDocumentContentChangeEvent:
    private val rd0 = badMerge(TextDocumentContentChangeEvent.S0.codec.widen[(TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)], TextDocumentContentChangeEvent.S1.codec.widen[(TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)])
    private given reader_rd0: Reader[(TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)] = rd0
    private val _codec: Reader[TextDocumentContentChangeEvent] = badMerge(TextDocumentContentChangeEvent.S0.codec.widen[TextDocumentContentChangeEvent], TextDocumentContentChangeEvent.S1.codec.widen[TextDocumentContentChangeEvent])
    given codec: Reader[TextDocumentContentChangeEvent] = _codec
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
    private val rd0 = badMerge(stringCodec.widen[(String | MarkedString.S0)], MarkedString.S0.codec.widen[(String | MarkedString.S0)])
    private given reader_rd0: Reader[(String | MarkedString.S0)] = rd0
    private val _codec: Reader[MarkedString] = badMerge(stringCodec.widen[MarkedString], MarkedString.S0.codec.widen[MarkedString])
    given codec: Reader[MarkedString] = _codec
    case class S0(
      language: String,
      value: String
    )
    object S0:
      given codec: Reader[aliases.MarkedString.S0] = Pickle.macroR
  opaque type DocumentFilter = (aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)
  object DocumentFilter:
    private val rd0 = badMerge(aliases.TextDocumentFilter.codec.widen[(aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)], structures.NotebookCellTextDocumentFilter.codec.widen[(aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)])
    private given reader_rd0: Reader[(aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)] = rd0
    private val _codec: Reader[DocumentFilter] = badMerge(aliases.TextDocumentFilter.codec.widen[DocumentFilter], structures.NotebookCellTextDocumentFilter.codec.widen[DocumentFilter])
    given codec: Reader[DocumentFilter] = _codec
  opaque type GlobPattern = (aliases.Pattern | structures.RelativePattern)
  object GlobPattern:
    private val rd0 = badMerge(aliases.Pattern.codec.widen[(aliases.Pattern | structures.RelativePattern)], structures.RelativePattern.codec.widen[(aliases.Pattern | structures.RelativePattern)])
    private given reader_rd0: Reader[(aliases.Pattern | structures.RelativePattern)] = rd0
    private val _codec: Reader[GlobPattern] = badMerge(aliases.Pattern.codec.widen[GlobPattern], structures.RelativePattern.codec.widen[GlobPattern])
    given codec: Reader[GlobPattern] = _codec
  opaque type TextDocumentFilter = (TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)
  object TextDocumentFilter:
    private val rd0 = badMerge(TextDocumentFilter.S0.codec.widen[(TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)], TextDocumentFilter.S1.codec.widen[(TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)], TextDocumentFilter.S2.codec.widen[(TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)])
    private given reader_rd0: Reader[(TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)] = rd0
    private val _codec: Reader[TextDocumentFilter] = badMerge(TextDocumentFilter.S0.codec.widen[TextDocumentFilter], TextDocumentFilter.S1.codec.widen[TextDocumentFilter], TextDocumentFilter.S2.codec.widen[TextDocumentFilter])
    given codec: Reader[TextDocumentFilter] = _codec
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
    private val rd0 = badMerge(NotebookDocumentFilter.S0.codec.widen[(NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 | NotebookDocumentFilter.S2)], NotebookDocumentFilter.S1.codec.widen[(NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 | NotebookDocumentFilter.S2)], NotebookDocumentFilter.S2.codec.widen[(NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 | NotebookDocumentFilter.S2)])
    private given reader_rd0: Reader[(NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 | NotebookDocumentFilter.S2)] = rd0
    private val _codec: Reader[NotebookDocumentFilter] = badMerge(NotebookDocumentFilter.S0.codec.widen[NotebookDocumentFilter], NotebookDocumentFilter.S1.codec.widen[NotebookDocumentFilter], NotebookDocumentFilter.S2.codec.widen[NotebookDocumentFilter])
    given codec: Reader[NotebookDocumentFilter] = _codec
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
    private val _codec: Reader[Pattern] = stringCodec
    given codec: Reader[Pattern] = _codec
