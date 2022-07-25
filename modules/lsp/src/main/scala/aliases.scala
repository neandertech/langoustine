package langoustine.lsp

import langoustine.*
import langoustine.lsp.json.{*, given}
import upickle.default.*

object aliases: 
  opaque type Definition = (structures.Location | Vector[structures.Location])
  object Definition:
    given upickle.default.Reader[Definition] = {type T = Definition; badMerge(reader[structures.Location].widen[T], reader[Vector[structures.Location]].widen[T])}
  opaque type DefinitionLink = structures.LocationLink
  object DefinitionLink:
    given upickle.default.Reader[DefinitionLink] = reader[structures.LocationLink]
  case class LSPArray(elements: Vector[aliases.LSPAny])
  object LSPArray:
    given Reader[LSPArray] = reader[Vector[aliases.LSPAny]].map(LSPArray.apply)
  opaque type LSPAny = (structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)
  object LSPAny:
    given upickle.default.Reader[LSPAny] = {type T = LSPAny; badMerge(reader[structures.LSPObject].widen[T], reader[aliases.LSPArray].widen[T], reader[String].widen[T], reader[Int].widen[T], reader[RuntimeBase.uinteger].widen[T], reader[Float].widen[T], reader[Boolean].widen[T], nullReadWriter.widen[T])}
  opaque type Declaration = (structures.Location | Vector[structures.Location])
  object Declaration:
    given upickle.default.Reader[Declaration] = {type T = Declaration; badMerge(reader[structures.Location].widen[T], reader[Vector[structures.Location]].widen[T])}
  opaque type DeclarationLink = structures.LocationLink
  object DeclarationLink:
    given upickle.default.Reader[DeclarationLink] = reader[structures.LocationLink]
  opaque type InlineValue = (structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)
  object InlineValue:
    given upickle.default.Reader[InlineValue] = {type T = InlineValue; badMerge(reader[structures.InlineValueText].widen[T], reader[structures.InlineValueVariableLookup].widen[T], reader[structures.InlineValueEvaluatableExpression].widen[T])}
  opaque type DocumentDiagnosticReport = (structures.RelatedFullDocumentDiagnosticReport | structures.RelatedUnchangedDocumentDiagnosticReport)
  object DocumentDiagnosticReport:
    given upickle.default.Reader[DocumentDiagnosticReport] = {type T = DocumentDiagnosticReport; badMerge(reader[structures.RelatedFullDocumentDiagnosticReport].widen[T], reader[structures.RelatedUnchangedDocumentDiagnosticReport].widen[T])}
  opaque type PrepareRenameResult = (structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)
  object PrepareRenameResult:
    given upickle.default.Reader[PrepareRenameResult] = {type T = PrepareRenameResult; badMerge(reader[structures.Range].widen[T], reader[PrepareRenameResult.S0].widen[T], reader[PrepareRenameResult.S1].widen[T])}
    case class S0(
      range: structures.Range,
      placeholder: String
    )
    object S0:
      object codecs:
        given upickle.default.Reader[S0] = Pickle.macroR
      export codecs.{*, given}
    case class S1(
      defaultBehavior: Boolean
    )
    object S1:
      object codecs:
        given upickle.default.Reader[S1] = Pickle.macroR
      export codecs.{*, given}
  opaque type URI = String
  object URI:
    given upickle.default.Reader[URI] = reader[String]
  opaque type ProgressToken = (Int | String)
  object ProgressToken:
    given upickle.default.Reader[ProgressToken] = {type T = ProgressToken; badMerge(reader[Int].widen[T], reader[String].widen[T])}
  opaque type DocumentSelector = Vector[(String | aliases.DocumentFilter)]
  object DocumentSelector:
    given upickle.default.Reader[DocumentSelector] = reader[Vector[(String | aliases.DocumentFilter)]]
  opaque type ChangeAnnotationIdentifier = String
  object ChangeAnnotationIdentifier:
    given upickle.default.Reader[ChangeAnnotationIdentifier] = reader[String]
  opaque type WorkspaceDocumentDiagnosticReport = (structures.WorkspaceFullDocumentDiagnosticReport | structures.WorkspaceUnchangedDocumentDiagnosticReport)
  object WorkspaceDocumentDiagnosticReport:
    given upickle.default.Reader[WorkspaceDocumentDiagnosticReport] = {type T = WorkspaceDocumentDiagnosticReport; badMerge(reader[structures.WorkspaceFullDocumentDiagnosticReport].widen[T], reader[structures.WorkspaceUnchangedDocumentDiagnosticReport].widen[T])}
  opaque type TextDocumentContentChangeEvent = (TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)
  object TextDocumentContentChangeEvent:
    given upickle.default.Reader[TextDocumentContentChangeEvent] = {type T = TextDocumentContentChangeEvent; badMerge(reader[TextDocumentContentChangeEvent.S0].widen[T], reader[TextDocumentContentChangeEvent.S1].widen[T])}
    case class S0(
      range: structures.Range,
      rangeLength: RuntimeBase.uinteger,
      text: String
    )
    object S0:
      object codecs:
        given upickle.default.Reader[S0] = Pickle.macroR
      export codecs.{*, given}
    case class S1(
      text: String
    )
    object S1:
      object codecs:
        given upickle.default.Reader[S1] = Pickle.macroR
      export codecs.{*, given}
  opaque type MarkedString = (String | MarkedString.S0)
  object MarkedString:
    given upickle.default.Reader[MarkedString] = {type T = MarkedString; badMerge(reader[String].widen[T], reader[MarkedString.S0].widen[T])}
    case class S0(
      language: String,
      value: String
    )
    object S0:
      object codecs:
        given upickle.default.Reader[S0] = Pickle.macroR
      export codecs.{*, given}
  opaque type DocumentFilter = (aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)
  object DocumentFilter:
    given upickle.default.Reader[DocumentFilter] = {type T = DocumentFilter; badMerge(reader[aliases.TextDocumentFilter].widen[T], reader[structures.NotebookCellTextDocumentFilter].widen[T])}
  opaque type GlobPattern = (aliases.Pattern | structures.RelativePattern)
  object GlobPattern:
    given upickle.default.Reader[GlobPattern] = {type T = GlobPattern; badMerge(reader[aliases.Pattern].widen[T], reader[structures.RelativePattern].widen[T])}
  opaque type TextDocumentFilter = (TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)
  object TextDocumentFilter:
    given upickle.default.Reader[TextDocumentFilter] = {type T = TextDocumentFilter; badMerge(reader[TextDocumentFilter.S0].widen[T], reader[TextDocumentFilter.S1].widen[T], reader[TextDocumentFilter.S2].widen[T])}
    case class S0(
      language: String,
      scheme: String,
      pattern: String
    )
    object S0:
      object codecs:
        given upickle.default.Reader[S0] = Pickle.macroR
      export codecs.{*, given}
    case class S1(
      language: String,
      scheme: String,
      pattern: String
    )
    object S1:
      object codecs:
        given upickle.default.Reader[S1] = Pickle.macroR
      export codecs.{*, given}
    case class S2(
      language: String,
      scheme: String,
      pattern: String
    )
    object S2:
      object codecs:
        given upickle.default.Reader[S2] = Pickle.macroR
      export codecs.{*, given}
  opaque type NotebookDocumentFilter = (NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 | NotebookDocumentFilter.S2)
  object NotebookDocumentFilter:
    given upickle.default.Reader[NotebookDocumentFilter] = {type T = NotebookDocumentFilter; badMerge(reader[NotebookDocumentFilter.S0].widen[T], reader[NotebookDocumentFilter.S1].widen[T], reader[NotebookDocumentFilter.S2].widen[T])}
    case class S0(
      notebookType: String,
      scheme: String,
      pattern: String
    )
    object S0:
      object codecs:
        given upickle.default.Reader[S0] = Pickle.macroR
      export codecs.{*, given}
    case class S1(
      notebookType: String,
      scheme: String,
      pattern: String
    )
    object S1:
      object codecs:
        given upickle.default.Reader[S1] = Pickle.macroR
      export codecs.{*, given}
    case class S2(
      notebookType: String,
      scheme: String,
      pattern: String
    )
    object S2:
      object codecs:
        given upickle.default.Reader[S2] = Pickle.macroR
      export codecs.{*, given}
  opaque type Pattern = String
  object Pattern:
    given upickle.default.Reader[Pattern] = reader[String]
