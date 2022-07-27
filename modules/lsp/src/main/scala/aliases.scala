package langoustine.lsp

import langoustine.*
import langoustine.lsp.json.{*, given}
import upickle.default.*
import scala.reflect.*

object aliases: 
  opaque type Definition = (structures.Location | Vector[structures.Location])
  object Definition:
    inline def apply(v: structures.Location): Definition = v
    inline def apply(v: Vector[structures.Location]): Definition = v
    private val rd0 = badMerge(structures.Location.reader.widen[(structures.Location | Vector[structures.Location])], upickle.default.reader[Vector[structures.Location]].widen[(structures.Location | Vector[structures.Location])])
    private given reader_rd0: Reader[(structures.Location | Vector[structures.Location])] = rd0
    private val _reader: Reader[Definition] = badMerge(structures.Location.reader.widen[Definition], upickle.default.reader[Vector[structures.Location]].widen[Definition])
    given reader: Reader[Definition] = _reader
    given writer: Writer[Definition] =
      upickle.default.writer[ujson.Value].comap[Definition] { _v => 
        (_v: @unchecked) match 
          case v: structures.Location => writeJs[structures.Location](v)
          case v: Vector[?] => writeJs[Vector[structures.Location]](v.asInstanceOf[Vector[structures.Location]])
      }
    given Typeable[Definition] with
      def unapply(s: Any): Option[s.type & Definition] = 
        s match
        case c: structures.Location => Some(c.asInstanceOf[s.type & structures.Location])
        case c: Vector[?] => Some(c.asInstanceOf[s.type & Vector[structures.Location]])
        case _ => Option.empty
  
  opaque type DefinitionLink = structures.LocationLink
  object DefinitionLink:
    inline def apply(v: structures.LocationLink): DefinitionLink = v
    private val _reader: Reader[DefinitionLink] = structures.LocationLink.reader
    given reader: Reader[DefinitionLink] = _reader
    given writer: Writer[DefinitionLink] =
      structures.LocationLink.writer
    given Typeable[DefinitionLink] with
      def unapply(s: Any): Option[s.type & DefinitionLink] = 
        s match
        case c: structures.LocationLink => Some(c.asInstanceOf[s.type & structures.LocationLink])
        case _ => Option.empty
  
  opaque type Declaration = (structures.Location | Vector[structures.Location])
  object Declaration:
    inline def apply(v: structures.Location): Declaration = v
    inline def apply(v: Vector[structures.Location]): Declaration = v
    private val rd0 = badMerge(structures.Location.reader.widen[(structures.Location | Vector[structures.Location])], upickle.default.reader[Vector[structures.Location]].widen[(structures.Location | Vector[structures.Location])])
    private given reader_rd0: Reader[(structures.Location | Vector[structures.Location])] = rd0
    private val _reader: Reader[Declaration] = badMerge(structures.Location.reader.widen[Declaration], upickle.default.reader[Vector[structures.Location]].widen[Declaration])
    given reader: Reader[Declaration] = _reader
    given writer: Writer[Declaration] =
      upickle.default.writer[ujson.Value].comap[Declaration] { _v => 
        (_v: @unchecked) match 
          case v: structures.Location => writeJs[structures.Location](v)
          case v: Vector[?] => writeJs[Vector[structures.Location]](v.asInstanceOf[Vector[structures.Location]])
      }
    given Typeable[Declaration] with
      def unapply(s: Any): Option[s.type & Declaration] = 
        s match
        case c: structures.Location => Some(c.asInstanceOf[s.type & structures.Location])
        case c: Vector[?] => Some(c.asInstanceOf[s.type & Vector[structures.Location]])
        case _ => Option.empty
  
  opaque type DeclarationLink = structures.LocationLink
  object DeclarationLink:
    inline def apply(v: structures.LocationLink): DeclarationLink = v
    private val _reader: Reader[DeclarationLink] = structures.LocationLink.reader
    given reader: Reader[DeclarationLink] = _reader
    given writer: Writer[DeclarationLink] =
      structures.LocationLink.writer
    given Typeable[DeclarationLink] with
      def unapply(s: Any): Option[s.type & DeclarationLink] = 
        s match
        case c: structures.LocationLink => Some(c.asInstanceOf[s.type & structures.LocationLink])
        case _ => Option.empty
  
  opaque type InlineValue = (structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)
  object InlineValue:
    inline def apply(v: structures.InlineValueText): InlineValue = v
    inline def apply(v: structures.InlineValueVariableLookup): InlineValue = v
    inline def apply(v: structures.InlineValueEvaluatableExpression): InlineValue = v
    private val rd0 = badMerge(structures.InlineValueText.reader.widen[(structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)], structures.InlineValueVariableLookup.reader.widen[(structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)], structures.InlineValueEvaluatableExpression.reader.widen[(structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)])
    private given reader_rd0: Reader[(structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)] = rd0
    private val _reader: Reader[InlineValue] = badMerge(structures.InlineValueText.reader.widen[InlineValue], structures.InlineValueVariableLookup.reader.widen[InlineValue], structures.InlineValueEvaluatableExpression.reader.widen[InlineValue])
    given reader: Reader[InlineValue] = _reader
    given writer: Writer[InlineValue] =
      upickle.default.writer[ujson.Value].comap[InlineValue] { _v => 
        (_v: @unchecked) match 
          case v: structures.InlineValueText => writeJs[structures.InlineValueText](v)
          case v: structures.InlineValueVariableLookup => writeJs[structures.InlineValueVariableLookup](v)
          case v: structures.InlineValueEvaluatableExpression => writeJs[structures.InlineValueEvaluatableExpression](v)
      }
    given Typeable[InlineValue] with
      def unapply(s: Any): Option[s.type & InlineValue] = 
        s match
        case c: structures.InlineValueText => Some(c.asInstanceOf[s.type & structures.InlineValueText])
        case c: structures.InlineValueVariableLookup => Some(c.asInstanceOf[s.type & structures.InlineValueVariableLookup])
        case c: structures.InlineValueEvaluatableExpression => Some(c.asInstanceOf[s.type & structures.InlineValueEvaluatableExpression])
        case _ => Option.empty
  
  opaque type DocumentDiagnosticReport = (structures.RelatedFullDocumentDiagnosticReport | structures.RelatedUnchangedDocumentDiagnosticReport)
  object DocumentDiagnosticReport:
    inline def apply(v: structures.RelatedFullDocumentDiagnosticReport): DocumentDiagnosticReport = v
    inline def apply(v: structures.RelatedUnchangedDocumentDiagnosticReport): DocumentDiagnosticReport = v
    private val rd0 = badMerge(structures.RelatedFullDocumentDiagnosticReport.reader.widen[(structures.RelatedFullDocumentDiagnosticReport | structures.RelatedUnchangedDocumentDiagnosticReport)], structures.RelatedUnchangedDocumentDiagnosticReport.reader.widen[(structures.RelatedFullDocumentDiagnosticReport | structures.RelatedUnchangedDocumentDiagnosticReport)])
    private given reader_rd0: Reader[(structures.RelatedFullDocumentDiagnosticReport | structures.RelatedUnchangedDocumentDiagnosticReport)] = rd0
    private val _reader: Reader[DocumentDiagnosticReport] = badMerge(structures.RelatedFullDocumentDiagnosticReport.reader.widen[DocumentDiagnosticReport], structures.RelatedUnchangedDocumentDiagnosticReport.reader.widen[DocumentDiagnosticReport])
    given reader: Reader[DocumentDiagnosticReport] = _reader
    given writer: Writer[DocumentDiagnosticReport] =
      upickle.default.writer[ujson.Value].comap[DocumentDiagnosticReport] { _v => 
        (_v: @unchecked) match 
          case v: structures.RelatedFullDocumentDiagnosticReport => writeJs[structures.RelatedFullDocumentDiagnosticReport](v)
          case v: structures.RelatedUnchangedDocumentDiagnosticReport => writeJs[structures.RelatedUnchangedDocumentDiagnosticReport](v)
      }
    given Typeable[DocumentDiagnosticReport] with
      def unapply(s: Any): Option[s.type & DocumentDiagnosticReport] = 
        s match
        case c: structures.RelatedFullDocumentDiagnosticReport => Some(c.asInstanceOf[s.type & structures.RelatedFullDocumentDiagnosticReport])
        case c: structures.RelatedUnchangedDocumentDiagnosticReport => Some(c.asInstanceOf[s.type & structures.RelatedUnchangedDocumentDiagnosticReport])
        case _ => Option.empty
  
  opaque type PrepareRenameResult = (structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)
  object PrepareRenameResult:
    inline def apply(v: structures.Range): PrepareRenameResult = v
    inline def apply(v: PrepareRenameResult.S0): PrepareRenameResult = v
    inline def apply(v: PrepareRenameResult.S1): PrepareRenameResult = v
    private val rd0 = badMerge(structures.Range.reader.widen[(structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)], PrepareRenameResult.S0.reader.widen[(structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)], PrepareRenameResult.S1.reader.widen[(structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)])
    private given reader_rd0: Reader[(structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)] = rd0
    private val _reader: Reader[PrepareRenameResult] = badMerge(structures.Range.reader.widen[PrepareRenameResult], PrepareRenameResult.S0.reader.widen[PrepareRenameResult], PrepareRenameResult.S1.reader.widen[PrepareRenameResult])
    given reader: Reader[PrepareRenameResult] = _reader
    given writer: Writer[PrepareRenameResult] =
      upickle.default.writer[ujson.Value].comap[PrepareRenameResult] { _v => 
        (_v: @unchecked) match 
          case v: structures.Range => writeJs[structures.Range](v)
          case v: PrepareRenameResult.S0 => writeJs[PrepareRenameResult.S0](v)
          case v: PrepareRenameResult.S1 => writeJs[PrepareRenameResult.S1](v)
      }
    given Typeable[PrepareRenameResult] with
      def unapply(s: Any): Option[s.type & PrepareRenameResult] = 
        s match
        case c: structures.Range => Some(c.asInstanceOf[s.type & structures.Range])
        case c: PrepareRenameResult.S0 => Some(c.asInstanceOf[s.type & PrepareRenameResult.S0])
        case c: PrepareRenameResult.S1 => Some(c.asInstanceOf[s.type & PrepareRenameResult.S1])
        case _ => Option.empty
    case class S0(
      range: structures.Range,
      placeholder: String
    )
    object S0:
      given reader: Reader[aliases.PrepareRenameResult.S0] = Pickle.macroR
      given writer: Writer[aliases.PrepareRenameResult.S0] = upickle.default.macroW
    case class S1(
      defaultBehavior: Boolean
    )
    object S1:
      given reader: Reader[aliases.PrepareRenameResult.S1] = Pickle.macroR
      given writer: Writer[aliases.PrepareRenameResult.S1] = upickle.default.macroW
  
  opaque type URI = String
  object URI:
    inline def apply(v: String): URI = v
    private val _reader: Reader[URI] = stringCodec
    given reader: Reader[URI] = _reader
    given writer: Writer[URI] =
      stringCodec
    given Typeable[URI] with
      def unapply(s: Any): Option[s.type & URI] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
  
  opaque type ProgressToken = (Int | String)
  object ProgressToken:
    inline def apply(v: Int): ProgressToken = v
    inline def apply(v: String): ProgressToken = v
    private val rd0 = badMerge(intCodec.widen[(Int | String)], stringCodec.widen[(Int | String)])
    private given reader_rd0: Reader[(Int | String)] = rd0
    private val _reader: Reader[ProgressToken] = badMerge(intCodec.widen[ProgressToken], stringCodec.widen[ProgressToken])
    given reader: Reader[ProgressToken] = _reader
    given writer: Writer[ProgressToken] =
      upickle.default.writer[ujson.Value].comap[ProgressToken] { _v => 
        (_v: @unchecked) match 
          case v: Int => writeJs[Int](v)
          case v: String => writeJs[String](v)
      }
    given Typeable[ProgressToken] with
      def unapply(s: Any): Option[s.type & ProgressToken] = 
        s match
        case c: Int => Some(c.asInstanceOf[s.type & Int])
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
  
  opaque type DocumentSelector = Vector[(String | aliases.DocumentFilter)]
  object DocumentSelector:
    inline def apply(v: Vector[(String | aliases.DocumentFilter)]): DocumentSelector = v
    private val rd0 = badMerge(stringCodec.widen[(String | aliases.DocumentFilter)], aliases.DocumentFilter.reader.widen[(String | aliases.DocumentFilter)])
    private given reader_rd0: Reader[(String | aliases.DocumentFilter)] = rd0
    private val _reader: Reader[DocumentSelector] = upickle.default.reader[Vector[(String | aliases.DocumentFilter)]]
    given reader: Reader[DocumentSelector] = _reader
    given writer: Writer[DocumentSelector] =
      ??? /* arr */
    given Typeable[DocumentSelector] with
      def unapply(s: Any): Option[s.type & DocumentSelector] = 
        s match
        case c: Vector[?] => Some(c.asInstanceOf[s.type & Vector[(String | aliases.DocumentFilter)]])
        case _ => Option.empty
  
  opaque type ChangeAnnotationIdentifier = String
  object ChangeAnnotationIdentifier:
    inline def apply(v: String): ChangeAnnotationIdentifier = v
    private val _reader: Reader[ChangeAnnotationIdentifier] = stringCodec
    given reader: Reader[ChangeAnnotationIdentifier] = _reader
    given writer: Writer[ChangeAnnotationIdentifier] =
      stringCodec
    given Typeable[ChangeAnnotationIdentifier] with
      def unapply(s: Any): Option[s.type & ChangeAnnotationIdentifier] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
  
  opaque type WorkspaceDocumentDiagnosticReport = (structures.WorkspaceFullDocumentDiagnosticReport | structures.WorkspaceUnchangedDocumentDiagnosticReport)
  object WorkspaceDocumentDiagnosticReport:
    inline def apply(v: structures.WorkspaceFullDocumentDiagnosticReport): WorkspaceDocumentDiagnosticReport = v
    inline def apply(v: structures.WorkspaceUnchangedDocumentDiagnosticReport): WorkspaceDocumentDiagnosticReport = v
    private val rd0 = badMerge(structures.WorkspaceFullDocumentDiagnosticReport.reader.widen[(structures.WorkspaceFullDocumentDiagnosticReport | structures.WorkspaceUnchangedDocumentDiagnosticReport)], structures.WorkspaceUnchangedDocumentDiagnosticReport.reader.widen[(structures.WorkspaceFullDocumentDiagnosticReport | structures.WorkspaceUnchangedDocumentDiagnosticReport)])
    private given reader_rd0: Reader[(structures.WorkspaceFullDocumentDiagnosticReport | structures.WorkspaceUnchangedDocumentDiagnosticReport)] = rd0
    private val _reader: Reader[WorkspaceDocumentDiagnosticReport] = badMerge(structures.WorkspaceFullDocumentDiagnosticReport.reader.widen[WorkspaceDocumentDiagnosticReport], structures.WorkspaceUnchangedDocumentDiagnosticReport.reader.widen[WorkspaceDocumentDiagnosticReport])
    given reader: Reader[WorkspaceDocumentDiagnosticReport] = _reader
    given writer: Writer[WorkspaceDocumentDiagnosticReport] =
      upickle.default.writer[ujson.Value].comap[WorkspaceDocumentDiagnosticReport] { _v => 
        (_v: @unchecked) match 
          case v: structures.WorkspaceFullDocumentDiagnosticReport => writeJs[structures.WorkspaceFullDocumentDiagnosticReport](v)
          case v: structures.WorkspaceUnchangedDocumentDiagnosticReport => writeJs[structures.WorkspaceUnchangedDocumentDiagnosticReport](v)
      }
    given Typeable[WorkspaceDocumentDiagnosticReport] with
      def unapply(s: Any): Option[s.type & WorkspaceDocumentDiagnosticReport] = 
        s match
        case c: structures.WorkspaceFullDocumentDiagnosticReport => Some(c.asInstanceOf[s.type & structures.WorkspaceFullDocumentDiagnosticReport])
        case c: structures.WorkspaceUnchangedDocumentDiagnosticReport => Some(c.asInstanceOf[s.type & structures.WorkspaceUnchangedDocumentDiagnosticReport])
        case _ => Option.empty
  
  opaque type TextDocumentContentChangeEvent = (TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)
  object TextDocumentContentChangeEvent:
    inline def apply(v: TextDocumentContentChangeEvent.S0): TextDocumentContentChangeEvent = v
    inline def apply(v: TextDocumentContentChangeEvent.S1): TextDocumentContentChangeEvent = v
    private val rd0 = badMerge(TextDocumentContentChangeEvent.S0.reader.widen[(TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)], TextDocumentContentChangeEvent.S1.reader.widen[(TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)])
    private given reader_rd0: Reader[(TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)] = rd0
    private val _reader: Reader[TextDocumentContentChangeEvent] = badMerge(TextDocumentContentChangeEvent.S0.reader.widen[TextDocumentContentChangeEvent], TextDocumentContentChangeEvent.S1.reader.widen[TextDocumentContentChangeEvent])
    given reader: Reader[TextDocumentContentChangeEvent] = _reader
    given writer: Writer[TextDocumentContentChangeEvent] =
      upickle.default.writer[ujson.Value].comap[TextDocumentContentChangeEvent] { _v => 
        (_v: @unchecked) match 
          case v: TextDocumentContentChangeEvent.S0 => writeJs[TextDocumentContentChangeEvent.S0](v)
          case v: TextDocumentContentChangeEvent.S1 => writeJs[TextDocumentContentChangeEvent.S1](v)
      }
    given Typeable[TextDocumentContentChangeEvent] with
      def unapply(s: Any): Option[s.type & TextDocumentContentChangeEvent] = 
        s match
        case c: TextDocumentContentChangeEvent.S0 => Some(c.asInstanceOf[s.type & TextDocumentContentChangeEvent.S0])
        case c: TextDocumentContentChangeEvent.S1 => Some(c.asInstanceOf[s.type & TextDocumentContentChangeEvent.S1])
        case _ => Option.empty
    case class S0(
      range: structures.Range,
      rangeLength: Opt[RuntimeBase.uinteger] = Opt.empty,
      text: String
    )
    object S0:
      given reader: Reader[aliases.TextDocumentContentChangeEvent.S0] = Pickle.macroR
      given writer: Writer[aliases.TextDocumentContentChangeEvent.S0] = upickle.default.macroW
    case class S1(
      text: String
    )
    object S1:
      given reader: Reader[aliases.TextDocumentContentChangeEvent.S1] = Pickle.macroR
      given writer: Writer[aliases.TextDocumentContentChangeEvent.S1] = upickle.default.macroW
  
  opaque type MarkedString = (String | MarkedString.S0)
  object MarkedString:
    inline def apply(v: String): MarkedString = v
    inline def apply(v: MarkedString.S0): MarkedString = v
    private val rd0 = badMerge(stringCodec.widen[(String | MarkedString.S0)], MarkedString.S0.reader.widen[(String | MarkedString.S0)])
    private given reader_rd0: Reader[(String | MarkedString.S0)] = rd0
    private val _reader: Reader[MarkedString] = badMerge(stringCodec.widen[MarkedString], MarkedString.S0.reader.widen[MarkedString])
    given reader: Reader[MarkedString] = _reader
    given writer: Writer[MarkedString] =
      upickle.default.writer[ujson.Value].comap[MarkedString] { _v => 
        (_v: @unchecked) match 
          case v: String => writeJs[String](v)
          case v: MarkedString.S0 => writeJs[MarkedString.S0](v)
      }
    given Typeable[MarkedString] with
      def unapply(s: Any): Option[s.type & MarkedString] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case c: MarkedString.S0 => Some(c.asInstanceOf[s.type & MarkedString.S0])
        case _ => Option.empty
    case class S0(
      language: String,
      value: String
    )
    object S0:
      given reader: Reader[aliases.MarkedString.S0] = Pickle.macroR
      given writer: Writer[aliases.MarkedString.S0] = upickle.default.macroW
  
  opaque type DocumentFilter = (aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)
  object DocumentFilter:
    inline def apply(v: aliases.TextDocumentFilter): DocumentFilter = v
    inline def apply(v: structures.NotebookCellTextDocumentFilter): DocumentFilter = v
    private val rd0 = badMerge(aliases.TextDocumentFilter.reader.widen[(aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)], structures.NotebookCellTextDocumentFilter.reader.widen[(aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)])
    private given reader_rd0: Reader[(aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)] = rd0
    private val _reader: Reader[DocumentFilter] = badMerge(aliases.TextDocumentFilter.reader.widen[DocumentFilter], structures.NotebookCellTextDocumentFilter.reader.widen[DocumentFilter])
    given reader: Reader[DocumentFilter] = _reader
    given writer: Writer[DocumentFilter] =
      upickle.default.writer[ujson.Value].comap[DocumentFilter] { _v => 
        (_v: @unchecked) match 
          case v: aliases.TextDocumentFilter => writeJs[aliases.TextDocumentFilter](v)
          case v: structures.NotebookCellTextDocumentFilter => writeJs[structures.NotebookCellTextDocumentFilter](v)
      }
    given Typeable[DocumentFilter] with
      def unapply(s: Any): Option[s.type & DocumentFilter] = 
        s match
        case c: aliases.TextDocumentFilter => Some(c.asInstanceOf[s.type & aliases.TextDocumentFilter])
        case c: structures.NotebookCellTextDocumentFilter => Some(c.asInstanceOf[s.type & structures.NotebookCellTextDocumentFilter])
        case _ => Option.empty
  
  opaque type GlobPattern = (aliases.Pattern | structures.RelativePattern)
  object GlobPattern:
    inline def apply(v: aliases.Pattern): GlobPattern = v
    inline def apply(v: structures.RelativePattern): GlobPattern = v
    private val rd0 = badMerge(aliases.Pattern.reader.widen[(aliases.Pattern | structures.RelativePattern)], structures.RelativePattern.reader.widen[(aliases.Pattern | structures.RelativePattern)])
    private given reader_rd0: Reader[(aliases.Pattern | structures.RelativePattern)] = rd0
    private val _reader: Reader[GlobPattern] = badMerge(aliases.Pattern.reader.widen[GlobPattern], structures.RelativePattern.reader.widen[GlobPattern])
    given reader: Reader[GlobPattern] = _reader
    given writer: Writer[GlobPattern] =
      upickle.default.writer[ujson.Value].comap[GlobPattern] { _v => 
        (_v: @unchecked) match 
          case v: aliases.Pattern => writeJs[aliases.Pattern](v)
          case v: structures.RelativePattern => writeJs[structures.RelativePattern](v)
      }
    given Typeable[GlobPattern] with
      def unapply(s: Any): Option[s.type & GlobPattern] = 
        s match
        case c: aliases.Pattern => Some(c.asInstanceOf[s.type & aliases.Pattern])
        case c: structures.RelativePattern => Some(c.asInstanceOf[s.type & structures.RelativePattern])
        case _ => Option.empty
  
  opaque type TextDocumentFilter = (TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)
  object TextDocumentFilter:
    inline def apply(v: TextDocumentFilter.S0): TextDocumentFilter = v
    inline def apply(v: TextDocumentFilter.S1): TextDocumentFilter = v
    inline def apply(v: TextDocumentFilter.S2): TextDocumentFilter = v
    private val rd0 = badMerge(TextDocumentFilter.S0.reader.widen[(TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)], TextDocumentFilter.S1.reader.widen[(TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)], TextDocumentFilter.S2.reader.widen[(TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)])
    private given reader_rd0: Reader[(TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)] = rd0
    private val _reader: Reader[TextDocumentFilter] = badMerge(TextDocumentFilter.S0.reader.widen[TextDocumentFilter], TextDocumentFilter.S1.reader.widen[TextDocumentFilter], TextDocumentFilter.S2.reader.widen[TextDocumentFilter])
    given reader: Reader[TextDocumentFilter] = _reader
    given writer: Writer[TextDocumentFilter] =
      upickle.default.writer[ujson.Value].comap[TextDocumentFilter] { _v => 
        (_v: @unchecked) match 
          case v: TextDocumentFilter.S0 => writeJs[TextDocumentFilter.S0](v)
          case v: TextDocumentFilter.S1 => writeJs[TextDocumentFilter.S1](v)
          case v: TextDocumentFilter.S2 => writeJs[TextDocumentFilter.S2](v)
      }
    given Typeable[TextDocumentFilter] with
      def unapply(s: Any): Option[s.type & TextDocumentFilter] = 
        s match
        case c: TextDocumentFilter.S0 => Some(c.asInstanceOf[s.type & TextDocumentFilter.S0])
        case c: TextDocumentFilter.S1 => Some(c.asInstanceOf[s.type & TextDocumentFilter.S1])
        case c: TextDocumentFilter.S2 => Some(c.asInstanceOf[s.type & TextDocumentFilter.S2])
        case _ => Option.empty
    case class S0(
      language: String,
      scheme: Opt[String] = Opt.empty,
      pattern: Opt[String] = Opt.empty
    )
    object S0:
      given reader: Reader[aliases.TextDocumentFilter.S0] = Pickle.macroR
      given writer: Writer[aliases.TextDocumentFilter.S0] = upickle.default.macroW
    case class S1(
      language: Opt[String] = Opt.empty,
      scheme: String,
      pattern: Opt[String] = Opt.empty
    )
    object S1:
      given reader: Reader[aliases.TextDocumentFilter.S1] = Pickle.macroR
      given writer: Writer[aliases.TextDocumentFilter.S1] = upickle.default.macroW
    case class S2(
      language: Opt[String] = Opt.empty,
      scheme: Opt[String] = Opt.empty,
      pattern: String
    )
    object S2:
      given reader: Reader[aliases.TextDocumentFilter.S2] = Pickle.macroR
      given writer: Writer[aliases.TextDocumentFilter.S2] = upickle.default.macroW
  
  opaque type NotebookDocumentFilter = (NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 | NotebookDocumentFilter.S2)
  object NotebookDocumentFilter:
    inline def apply(v: NotebookDocumentFilter.S0): NotebookDocumentFilter = v
    inline def apply(v: NotebookDocumentFilter.S1): NotebookDocumentFilter = v
    inline def apply(v: NotebookDocumentFilter.S2): NotebookDocumentFilter = v
    private val rd0 = badMerge(NotebookDocumentFilter.S0.reader.widen[(NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 | NotebookDocumentFilter.S2)], NotebookDocumentFilter.S1.reader.widen[(NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 | NotebookDocumentFilter.S2)], NotebookDocumentFilter.S2.reader.widen[(NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 | NotebookDocumentFilter.S2)])
    private given reader_rd0: Reader[(NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 | NotebookDocumentFilter.S2)] = rd0
    private val _reader: Reader[NotebookDocumentFilter] = badMerge(NotebookDocumentFilter.S0.reader.widen[NotebookDocumentFilter], NotebookDocumentFilter.S1.reader.widen[NotebookDocumentFilter], NotebookDocumentFilter.S2.reader.widen[NotebookDocumentFilter])
    given reader: Reader[NotebookDocumentFilter] = _reader
    given writer: Writer[NotebookDocumentFilter] =
      upickle.default.writer[ujson.Value].comap[NotebookDocumentFilter] { _v => 
        (_v: @unchecked) match 
          case v: NotebookDocumentFilter.S0 => writeJs[NotebookDocumentFilter.S0](v)
          case v: NotebookDocumentFilter.S1 => writeJs[NotebookDocumentFilter.S1](v)
          case v: NotebookDocumentFilter.S2 => writeJs[NotebookDocumentFilter.S2](v)
      }
    given Typeable[NotebookDocumentFilter] with
      def unapply(s: Any): Option[s.type & NotebookDocumentFilter] = 
        s match
        case c: NotebookDocumentFilter.S0 => Some(c.asInstanceOf[s.type & NotebookDocumentFilter.S0])
        case c: NotebookDocumentFilter.S1 => Some(c.asInstanceOf[s.type & NotebookDocumentFilter.S1])
        case c: NotebookDocumentFilter.S2 => Some(c.asInstanceOf[s.type & NotebookDocumentFilter.S2])
        case _ => Option.empty
    case class S0(
      notebookType: String,
      scheme: Opt[String] = Opt.empty,
      pattern: Opt[String] = Opt.empty
    )
    object S0:
      given reader: Reader[aliases.NotebookDocumentFilter.S0] = Pickle.macroR
      given writer: Writer[aliases.NotebookDocumentFilter.S0] = upickle.default.macroW
    case class S1(
      notebookType: Opt[String] = Opt.empty,
      scheme: String,
      pattern: Opt[String] = Opt.empty
    )
    object S1:
      given reader: Reader[aliases.NotebookDocumentFilter.S1] = Pickle.macroR
      given writer: Writer[aliases.NotebookDocumentFilter.S1] = upickle.default.macroW
    case class S2(
      notebookType: Opt[String] = Opt.empty,
      scheme: Opt[String] = Opt.empty,
      pattern: String
    )
    object S2:
      given reader: Reader[aliases.NotebookDocumentFilter.S2] = Pickle.macroR
      given writer: Writer[aliases.NotebookDocumentFilter.S2] = upickle.default.macroW
  
  opaque type Pattern = String
  object Pattern:
    inline def apply(v: String): Pattern = v
    private val _reader: Reader[Pattern] = stringCodec
    given reader: Reader[Pattern] = _reader
    given writer: Writer[Pattern] =
      stringCodec
    given Typeable[Pattern] with
      def unapply(s: Any): Option[s.type & Pattern] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
  
