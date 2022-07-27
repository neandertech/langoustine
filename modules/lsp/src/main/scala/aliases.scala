package langoustine.lsp

import langoustine.*
import langoustine.lsp.json.{*, given}
import upickle.default.*
import scala.reflect.*

object aliases: 
  opaque type Definition = (structures.Location | Vector[structures.Location])
  object Definition:
    private val rd0 = badMerge(structures.Location.reader.widen[(structures.Location | Vector[structures.Location])], upickle.default.reader[Vector[structures.Location]].widen[(structures.Location | Vector[structures.Location])])
    private given reader_rd0: Reader[(structures.Location | Vector[structures.Location])] = rd0
    private val wt0 =
      upickle.default.writer[ujson.Value].comap[(structures.Location | Vector[structures.Location])] { v => 
        (v: @unchecked) match 
          case v: structures.Location => writeJs(v)
          case v: Vector[?] => writeJs[Vector[structures.Location]](v.asInstanceOf[Vector[structures.Location]])
      }
    private given writer_wt0: Writer[(structures.Location | Vector[structures.Location])] = wt0
    private val _reader: Reader[Definition] = badMerge(structures.Location.reader.widen[Definition], upickle.default.reader[Vector[structures.Location]].widen[Definition])
    private val _writer: Writer[Definition] = 
      upickle.default.writer[ujson.Value].comap[Definition] { v => 
        (v: @unchecked) match 
          case v: structures.Location => writeJs(v)
          case v: Vector[?] => writeJs[Vector[structures.Location]](v.asInstanceOf[Vector[structures.Location]])
      }
    given reader: Reader[Definition] = _reader
    given writer: Writer[Definition] = _writer
    given Typeable[Definition] with
      def unapply(s: Any): Option[s.type & Definition] = 
        s match
        case c: structures.Location => Some(c.asInstanceOf[s.type & structures.Location])
        case c: Vector[?] => Some(c.asInstanceOf[s.type & Vector[structures.Location]])
        case _ => Option.empty
  
  opaque type DefinitionLink = structures.LocationLink
  object DefinitionLink:
    private val _reader: Reader[DefinitionLink] = structures.LocationLink.reader
    private val _writer: Writer[DefinitionLink] = 
      structures.LocationLink.writer
    given reader: Reader[DefinitionLink] = _reader
    given writer: Writer[DefinitionLink] = _writer
    given Typeable[DefinitionLink] with
      def unapply(s: Any): Option[s.type & DefinitionLink] = 
        s match
        case c: structures.LocationLink => Some(c.asInstanceOf[s.type & structures.LocationLink])
        case _ => Option.empty
  
  case class LSPArray(elements: Vector[ujson.Value])
  object LSPArray:
    import LSPAny.given
    given reader: Reader[LSPArray] = upickle.default.reader[Vector[ujson.Value]].map(LSPArray.apply)
    given writer: Writer[LSPArray] = upickle.default.writer[Vector[ujson.Value]].comap(_.elements)
  
  opaque type LSPAny = (structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)
  object LSPAny:
    private val rd0 = badMerge(structures.LSPObject.reader.widen[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)], aliases.LSPArray.reader.widen[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)], stringCodec.widen[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)], intCodec.widen[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)], upickle.default.reader[RuntimeBase.uinteger].widen[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)], upickle.default.reader[Float].widen[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)], upickle.default.reader[Boolean].widen[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)], nullReadWriter.widen[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)])
    private given reader_rd0: Reader[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)] = rd0
    private val wt0 =
      upickle.default.writer[ujson.Value].comap[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)] { v => 
        (v: @unchecked) match 
          case v: structures.LSPObject => writeJs(v)
          case v: aliases.LSPArray => writeJs(v)
          case v: String => writeJs(v)
          case v: Int => writeJs(v)
          case v: RuntimeBase.uinteger => writeJs(v)
          case v: Float => writeJs(v)
          case v: Boolean => writeJs(v)
          case null => ujson.Null
      }
    private given writer_wt0: Writer[(structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)] = wt0
    private val _reader: Reader[LSPAny] = badMerge(structures.LSPObject.reader.widen[LSPAny], aliases.LSPArray.reader.widen[LSPAny], stringCodec.widen[LSPAny], intCodec.widen[LSPAny], upickle.default.reader[RuntimeBase.uinteger].widen[LSPAny], upickle.default.reader[Float].widen[LSPAny], upickle.default.reader[Boolean].widen[LSPAny], nullReadWriter.widen[LSPAny])
    private val _writer: Writer[LSPAny] = 
      upickle.default.writer[ujson.Value].comap[LSPAny] { v => 
        (v: @unchecked) match 
          case v: structures.LSPObject => writeJs(v)
          case v: aliases.LSPArray => writeJs(v)
          case v: String => writeJs(v)
          case v: Int => writeJs(v)
          case v: RuntimeBase.uinteger => writeJs(v)
          case v: Float => writeJs(v)
          case v: Boolean => writeJs(v)
          case null => ujson.Null
      }
    given reader: Reader[LSPAny] = _reader
    given writer: Writer[LSPAny] = _writer
    given Typeable[LSPAny] with
      def unapply(s: Any): Option[s.type & LSPAny] = 
        s match
        case c: structures.LSPObject => Some(c.asInstanceOf[s.type & structures.LSPObject])
        case c: aliases.LSPArray => Some(c.asInstanceOf[s.type & aliases.LSPArray])
        case c: String => Some(c.asInstanceOf[s.type & String])
        case c: Int => Some(c.asInstanceOf[s.type & Int])
        case c: RuntimeBase.uinteger => Some(c.asInstanceOf[s.type & RuntimeBase.uinteger])
        case c: Float => Some(c.asInstanceOf[s.type & Float])
        case c: Boolean => Some(c.asInstanceOf[s.type & Boolean])
        case null => Some(null.asInstanceOf[s.type & Null])
        case _ => Option.empty
  
  opaque type Declaration = (structures.Location | Vector[structures.Location])
  object Declaration:
    private val rd0 = badMerge(structures.Location.reader.widen[(structures.Location | Vector[structures.Location])], upickle.default.reader[Vector[structures.Location]].widen[(structures.Location | Vector[structures.Location])])
    private given reader_rd0: Reader[(structures.Location | Vector[structures.Location])] = rd0
    private val wt0 =
      upickle.default.writer[ujson.Value].comap[(structures.Location | Vector[structures.Location])] { v => 
        (v: @unchecked) match 
          case v: structures.Location => writeJs(v)
          case v: Vector[?] => writeJs[Vector[structures.Location]](v.asInstanceOf[Vector[structures.Location]])
      }
    private given writer_wt0: Writer[(structures.Location | Vector[structures.Location])] = wt0
    private val _reader: Reader[Declaration] = badMerge(structures.Location.reader.widen[Declaration], upickle.default.reader[Vector[structures.Location]].widen[Declaration])
    private val _writer: Writer[Declaration] = 
      upickle.default.writer[ujson.Value].comap[Declaration] { v => 
        (v: @unchecked) match 
          case v: structures.Location => writeJs(v)
          case v: Vector[?] => writeJs[Vector[structures.Location]](v.asInstanceOf[Vector[structures.Location]])
      }
    given reader: Reader[Declaration] = _reader
    given writer: Writer[Declaration] = _writer
    given Typeable[Declaration] with
      def unapply(s: Any): Option[s.type & Declaration] = 
        s match
        case c: structures.Location => Some(c.asInstanceOf[s.type & structures.Location])
        case c: Vector[?] => Some(c.asInstanceOf[s.type & Vector[structures.Location]])
        case _ => Option.empty
  
  opaque type DeclarationLink = structures.LocationLink
  object DeclarationLink:
    private val _reader: Reader[DeclarationLink] = structures.LocationLink.reader
    private val _writer: Writer[DeclarationLink] = 
      structures.LocationLink.writer
    given reader: Reader[DeclarationLink] = _reader
    given writer: Writer[DeclarationLink] = _writer
    given Typeable[DeclarationLink] with
      def unapply(s: Any): Option[s.type & DeclarationLink] = 
        s match
        case c: structures.LocationLink => Some(c.asInstanceOf[s.type & structures.LocationLink])
        case _ => Option.empty
  
  opaque type InlineValue = (structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)
  object InlineValue:
    private val rd0 = badMerge(structures.InlineValueText.reader.widen[(structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)], structures.InlineValueVariableLookup.reader.widen[(structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)], structures.InlineValueEvaluatableExpression.reader.widen[(structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)])
    private given reader_rd0: Reader[(structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)] = rd0
    private val wt0 =
      upickle.default.writer[ujson.Value].comap[(structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)] { v => 
        (v: @unchecked) match 
          case v: structures.InlineValueText => writeJs(v)
          case v: structures.InlineValueVariableLookup => writeJs(v)
          case v: structures.InlineValueEvaluatableExpression => writeJs(v)
      }
    private given writer_wt0: Writer[(structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)] = wt0
    private val _reader: Reader[InlineValue] = badMerge(structures.InlineValueText.reader.widen[InlineValue], structures.InlineValueVariableLookup.reader.widen[InlineValue], structures.InlineValueEvaluatableExpression.reader.widen[InlineValue])
    private val _writer: Writer[InlineValue] = 
      upickle.default.writer[ujson.Value].comap[InlineValue] { v => 
        (v: @unchecked) match 
          case v: structures.InlineValueText => writeJs(v)
          case v: structures.InlineValueVariableLookup => writeJs(v)
          case v: structures.InlineValueEvaluatableExpression => writeJs(v)
      }
    given reader: Reader[InlineValue] = _reader
    given writer: Writer[InlineValue] = _writer
    given Typeable[InlineValue] with
      def unapply(s: Any): Option[s.type & InlineValue] = 
        s match
        case c: structures.InlineValueText => Some(c.asInstanceOf[s.type & structures.InlineValueText])
        case c: structures.InlineValueVariableLookup => Some(c.asInstanceOf[s.type & structures.InlineValueVariableLookup])
        case c: structures.InlineValueEvaluatableExpression => Some(c.asInstanceOf[s.type & structures.InlineValueEvaluatableExpression])
        case _ => Option.empty
  
  opaque type DocumentDiagnosticReport = (structures.RelatedFullDocumentDiagnosticReport | structures.RelatedUnchangedDocumentDiagnosticReport)
  object DocumentDiagnosticReport:
    private val rd0 = badMerge(structures.RelatedFullDocumentDiagnosticReport.reader.widen[(structures.RelatedFullDocumentDiagnosticReport | structures.RelatedUnchangedDocumentDiagnosticReport)], structures.RelatedUnchangedDocumentDiagnosticReport.reader.widen[(structures.RelatedFullDocumentDiagnosticReport | structures.RelatedUnchangedDocumentDiagnosticReport)])
    private given reader_rd0: Reader[(structures.RelatedFullDocumentDiagnosticReport | structures.RelatedUnchangedDocumentDiagnosticReport)] = rd0
    private val wt0 =
      upickle.default.writer[ujson.Value].comap[(structures.RelatedFullDocumentDiagnosticReport | structures.RelatedUnchangedDocumentDiagnosticReport)] { v => 
        (v: @unchecked) match 
          case v: structures.RelatedFullDocumentDiagnosticReport => writeJs(v)
          case v: structures.RelatedUnchangedDocumentDiagnosticReport => writeJs(v)
      }
    private given writer_wt0: Writer[(structures.RelatedFullDocumentDiagnosticReport | structures.RelatedUnchangedDocumentDiagnosticReport)] = wt0
    private val _reader: Reader[DocumentDiagnosticReport] = badMerge(structures.RelatedFullDocumentDiagnosticReport.reader.widen[DocumentDiagnosticReport], structures.RelatedUnchangedDocumentDiagnosticReport.reader.widen[DocumentDiagnosticReport])
    private val _writer: Writer[DocumentDiagnosticReport] = 
      upickle.default.writer[ujson.Value].comap[DocumentDiagnosticReport] { v => 
        (v: @unchecked) match 
          case v: structures.RelatedFullDocumentDiagnosticReport => writeJs(v)
          case v: structures.RelatedUnchangedDocumentDiagnosticReport => writeJs(v)
      }
    given reader: Reader[DocumentDiagnosticReport] = _reader
    given writer: Writer[DocumentDiagnosticReport] = _writer
    given Typeable[DocumentDiagnosticReport] with
      def unapply(s: Any): Option[s.type & DocumentDiagnosticReport] = 
        s match
        case c: structures.RelatedFullDocumentDiagnosticReport => Some(c.asInstanceOf[s.type & structures.RelatedFullDocumentDiagnosticReport])
        case c: structures.RelatedUnchangedDocumentDiagnosticReport => Some(c.asInstanceOf[s.type & structures.RelatedUnchangedDocumentDiagnosticReport])
        case _ => Option.empty
  
  opaque type PrepareRenameResult = (structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)
  object PrepareRenameResult:
    private val rd0 = badMerge(structures.Range.reader.widen[(structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)], PrepareRenameResult.S0.reader.widen[(structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)], PrepareRenameResult.S1.reader.widen[(structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)])
    private given reader_rd0: Reader[(structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)] = rd0
    private val wt0 =
      upickle.default.writer[ujson.Value].comap[(structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)] { v => 
        (v: @unchecked) match 
          case v: structures.Range => writeJs(v)
          case v: PrepareRenameResult.S0 => writeJs(v)
          case v: PrepareRenameResult.S1 => writeJs(v)
      }
    private given writer_wt0: Writer[(structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)] = wt0
    private val _reader: Reader[PrepareRenameResult] = badMerge(structures.Range.reader.widen[PrepareRenameResult], PrepareRenameResult.S0.reader.widen[PrepareRenameResult], PrepareRenameResult.S1.reader.widen[PrepareRenameResult])
    private val _writer: Writer[PrepareRenameResult] = 
      upickle.default.writer[ujson.Value].comap[PrepareRenameResult] { v => 
        (v: @unchecked) match 
          case v: structures.Range => writeJs(v)
          case v: PrepareRenameResult.S0 => writeJs(v)
          case v: PrepareRenameResult.S1 => writeJs(v)
      }
    given reader: Reader[PrepareRenameResult] = _reader
    given writer: Writer[PrepareRenameResult] = _writer
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
    private val _reader: Reader[URI] = stringCodec
    private val _writer: Writer[URI] = 
      stringCodec
    given reader: Reader[URI] = _reader
    given writer: Writer[URI] = _writer
    given Typeable[URI] with
      def unapply(s: Any): Option[s.type & URI] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
  
  opaque type ProgressToken = (Int | String)
  object ProgressToken:
    private val rd0 = badMerge(intCodec.widen[(Int | String)], stringCodec.widen[(Int | String)])
    private given reader_rd0: Reader[(Int | String)] = rd0
    private val wt0 =
      upickle.default.writer[ujson.Value].comap[(Int | String)] { v => 
        (v: @unchecked) match 
          case v: Int => writeJs(v)
          case v: String => writeJs(v)
      }
    private given writer_wt0: Writer[(Int | String)] = wt0
    private val _reader: Reader[ProgressToken] = badMerge(intCodec.widen[ProgressToken], stringCodec.widen[ProgressToken])
    private val _writer: Writer[ProgressToken] = 
      upickle.default.writer[ujson.Value].comap[ProgressToken] { v => 
        (v: @unchecked) match 
          case v: Int => writeJs(v)
          case v: String => writeJs(v)
      }
    given reader: Reader[ProgressToken] = _reader
    given writer: Writer[ProgressToken] = _writer
    given Typeable[ProgressToken] with
      def unapply(s: Any): Option[s.type & ProgressToken] = 
        s match
        case c: Int => Some(c.asInstanceOf[s.type & Int])
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
  
  opaque type DocumentSelector = Vector[(String | aliases.DocumentFilter)]
  object DocumentSelector:
    private val rd0 = badMerge(stringCodec.widen[(String | aliases.DocumentFilter)], aliases.DocumentFilter.reader.widen[(String | aliases.DocumentFilter)])
    private given reader_rd0: Reader[(String | aliases.DocumentFilter)] = rd0
    private val wt0 =
      upickle.default.writer[ujson.Value].comap[(String | aliases.DocumentFilter)] { v => 
        (v: @unchecked) match 
          case v: String => writeJs(v)
          case v: aliases.DocumentFilter => writeJs(v)
      }
    private given writer_wt0: Writer[(String | aliases.DocumentFilter)] = wt0
    private val _reader: Reader[DocumentSelector] = upickle.default.reader[Vector[(String | aliases.DocumentFilter)]]
    private val _writer: Writer[DocumentSelector] = 
      ??? /* arr */
    given reader: Reader[DocumentSelector] = _reader
    given writer: Writer[DocumentSelector] = _writer
    given Typeable[DocumentSelector] with
      def unapply(s: Any): Option[s.type & DocumentSelector] = 
        s match
        case c: Vector[?] => Some(c.asInstanceOf[s.type & Vector[(String | aliases.DocumentFilter)]])
        case _ => Option.empty
  
  opaque type ChangeAnnotationIdentifier = String
  object ChangeAnnotationIdentifier:
    private val _reader: Reader[ChangeAnnotationIdentifier] = stringCodec
    private val _writer: Writer[ChangeAnnotationIdentifier] = 
      stringCodec
    given reader: Reader[ChangeAnnotationIdentifier] = _reader
    given writer: Writer[ChangeAnnotationIdentifier] = _writer
    given Typeable[ChangeAnnotationIdentifier] with
      def unapply(s: Any): Option[s.type & ChangeAnnotationIdentifier] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
  
  opaque type WorkspaceDocumentDiagnosticReport = (structures.WorkspaceFullDocumentDiagnosticReport | structures.WorkspaceUnchangedDocumentDiagnosticReport)
  object WorkspaceDocumentDiagnosticReport:
    private val rd0 = badMerge(structures.WorkspaceFullDocumentDiagnosticReport.reader.widen[(structures.WorkspaceFullDocumentDiagnosticReport | structures.WorkspaceUnchangedDocumentDiagnosticReport)], structures.WorkspaceUnchangedDocumentDiagnosticReport.reader.widen[(structures.WorkspaceFullDocumentDiagnosticReport | structures.WorkspaceUnchangedDocumentDiagnosticReport)])
    private given reader_rd0: Reader[(structures.WorkspaceFullDocumentDiagnosticReport | structures.WorkspaceUnchangedDocumentDiagnosticReport)] = rd0
    private val wt0 =
      upickle.default.writer[ujson.Value].comap[(structures.WorkspaceFullDocumentDiagnosticReport | structures.WorkspaceUnchangedDocumentDiagnosticReport)] { v => 
        (v: @unchecked) match 
          case v: structures.WorkspaceFullDocumentDiagnosticReport => writeJs(v)
          case v: structures.WorkspaceUnchangedDocumentDiagnosticReport => writeJs(v)
      }
    private given writer_wt0: Writer[(structures.WorkspaceFullDocumentDiagnosticReport | structures.WorkspaceUnchangedDocumentDiagnosticReport)] = wt0
    private val _reader: Reader[WorkspaceDocumentDiagnosticReport] = badMerge(structures.WorkspaceFullDocumentDiagnosticReport.reader.widen[WorkspaceDocumentDiagnosticReport], structures.WorkspaceUnchangedDocumentDiagnosticReport.reader.widen[WorkspaceDocumentDiagnosticReport])
    private val _writer: Writer[WorkspaceDocumentDiagnosticReport] = 
      upickle.default.writer[ujson.Value].comap[WorkspaceDocumentDiagnosticReport] { v => 
        (v: @unchecked) match 
          case v: structures.WorkspaceFullDocumentDiagnosticReport => writeJs(v)
          case v: structures.WorkspaceUnchangedDocumentDiagnosticReport => writeJs(v)
      }
    given reader: Reader[WorkspaceDocumentDiagnosticReport] = _reader
    given writer: Writer[WorkspaceDocumentDiagnosticReport] = _writer
    given Typeable[WorkspaceDocumentDiagnosticReport] with
      def unapply(s: Any): Option[s.type & WorkspaceDocumentDiagnosticReport] = 
        s match
        case c: structures.WorkspaceFullDocumentDiagnosticReport => Some(c.asInstanceOf[s.type & structures.WorkspaceFullDocumentDiagnosticReport])
        case c: structures.WorkspaceUnchangedDocumentDiagnosticReport => Some(c.asInstanceOf[s.type & structures.WorkspaceUnchangedDocumentDiagnosticReport])
        case _ => Option.empty
  
  opaque type TextDocumentContentChangeEvent = (TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)
  object TextDocumentContentChangeEvent:
    private val rd0 = badMerge(TextDocumentContentChangeEvent.S0.reader.widen[(TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)], TextDocumentContentChangeEvent.S1.reader.widen[(TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)])
    private given reader_rd0: Reader[(TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)] = rd0
    private val wt0 =
      upickle.default.writer[ujson.Value].comap[(TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)] { v => 
        (v: @unchecked) match 
          case v: TextDocumentContentChangeEvent.S0 => writeJs(v)
          case v: TextDocumentContentChangeEvent.S1 => writeJs(v)
      }
    private given writer_wt0: Writer[(TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)] = wt0
    private val _reader: Reader[TextDocumentContentChangeEvent] = badMerge(TextDocumentContentChangeEvent.S0.reader.widen[TextDocumentContentChangeEvent], TextDocumentContentChangeEvent.S1.reader.widen[TextDocumentContentChangeEvent])
    private val _writer: Writer[TextDocumentContentChangeEvent] = 
      upickle.default.writer[ujson.Value].comap[TextDocumentContentChangeEvent] { v => 
        (v: @unchecked) match 
          case v: TextDocumentContentChangeEvent.S0 => writeJs(v)
          case v: TextDocumentContentChangeEvent.S1 => writeJs(v)
      }
    given reader: Reader[TextDocumentContentChangeEvent] = _reader
    given writer: Writer[TextDocumentContentChangeEvent] = _writer
    given Typeable[TextDocumentContentChangeEvent] with
      def unapply(s: Any): Option[s.type & TextDocumentContentChangeEvent] = 
        s match
        case c: TextDocumentContentChangeEvent.S0 => Some(c.asInstanceOf[s.type & TextDocumentContentChangeEvent.S0])
        case c: TextDocumentContentChangeEvent.S1 => Some(c.asInstanceOf[s.type & TextDocumentContentChangeEvent.S1])
        case _ => Option.empty
    case class S0(
      range: structures.Range,
      rangeLength: RuntimeBase.uinteger,
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
    private val rd0 = badMerge(stringCodec.widen[(String | MarkedString.S0)], MarkedString.S0.reader.widen[(String | MarkedString.S0)])
    private given reader_rd0: Reader[(String | MarkedString.S0)] = rd0
    private val wt0 =
      upickle.default.writer[ujson.Value].comap[(String | MarkedString.S0)] { v => 
        (v: @unchecked) match 
          case v: String => writeJs(v)
          case v: MarkedString.S0 => writeJs(v)
      }
    private given writer_wt0: Writer[(String | MarkedString.S0)] = wt0
    private val _reader: Reader[MarkedString] = badMerge(stringCodec.widen[MarkedString], MarkedString.S0.reader.widen[MarkedString])
    private val _writer: Writer[MarkedString] = 
      upickle.default.writer[ujson.Value].comap[MarkedString] { v => 
        (v: @unchecked) match 
          case v: String => writeJs(v)
          case v: MarkedString.S0 => writeJs(v)
      }
    given reader: Reader[MarkedString] = _reader
    given writer: Writer[MarkedString] = _writer
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
    private val rd0 = badMerge(aliases.TextDocumentFilter.reader.widen[(aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)], structures.NotebookCellTextDocumentFilter.reader.widen[(aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)])
    private given reader_rd0: Reader[(aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)] = rd0
    private val wt0 =
      upickle.default.writer[ujson.Value].comap[(aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)] { v => 
        (v: @unchecked) match 
          case v: aliases.TextDocumentFilter => writeJs(v)
          case v: structures.NotebookCellTextDocumentFilter => writeJs(v)
      }
    private given writer_wt0: Writer[(aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)] = wt0
    private val _reader: Reader[DocumentFilter] = badMerge(aliases.TextDocumentFilter.reader.widen[DocumentFilter], structures.NotebookCellTextDocumentFilter.reader.widen[DocumentFilter])
    private val _writer: Writer[DocumentFilter] = 
      upickle.default.writer[ujson.Value].comap[DocumentFilter] { v => 
        (v: @unchecked) match 
          case v: aliases.TextDocumentFilter => writeJs(v)
          case v: structures.NotebookCellTextDocumentFilter => writeJs(v)
      }
    given reader: Reader[DocumentFilter] = _reader
    given writer: Writer[DocumentFilter] = _writer
    given Typeable[DocumentFilter] with
      def unapply(s: Any): Option[s.type & DocumentFilter] = 
        s match
        case c: aliases.TextDocumentFilter => Some(c.asInstanceOf[s.type & aliases.TextDocumentFilter])
        case c: structures.NotebookCellTextDocumentFilter => Some(c.asInstanceOf[s.type & structures.NotebookCellTextDocumentFilter])
        case _ => Option.empty
  
  opaque type GlobPattern = (aliases.Pattern | structures.RelativePattern)
  object GlobPattern:
    private val rd0 = badMerge(aliases.Pattern.reader.widen[(aliases.Pattern | structures.RelativePattern)], structures.RelativePattern.reader.widen[(aliases.Pattern | structures.RelativePattern)])
    private given reader_rd0: Reader[(aliases.Pattern | structures.RelativePattern)] = rd0
    private val wt0 =
      upickle.default.writer[ujson.Value].comap[(aliases.Pattern | structures.RelativePattern)] { v => 
        (v: @unchecked) match 
          case v: aliases.Pattern => writeJs(v)
          case v: structures.RelativePattern => writeJs(v)
      }
    private given writer_wt0: Writer[(aliases.Pattern | structures.RelativePattern)] = wt0
    private val _reader: Reader[GlobPattern] = badMerge(aliases.Pattern.reader.widen[GlobPattern], structures.RelativePattern.reader.widen[GlobPattern])
    private val _writer: Writer[GlobPattern] = 
      upickle.default.writer[ujson.Value].comap[GlobPattern] { v => 
        (v: @unchecked) match 
          case v: aliases.Pattern => writeJs(v)
          case v: structures.RelativePattern => writeJs(v)
      }
    given reader: Reader[GlobPattern] = _reader
    given writer: Writer[GlobPattern] = _writer
    given Typeable[GlobPattern] with
      def unapply(s: Any): Option[s.type & GlobPattern] = 
        s match
        case c: aliases.Pattern => Some(c.asInstanceOf[s.type & aliases.Pattern])
        case c: structures.RelativePattern => Some(c.asInstanceOf[s.type & structures.RelativePattern])
        case _ => Option.empty
  
  opaque type TextDocumentFilter = (TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)
  object TextDocumentFilter:
    private val rd0 = badMerge(TextDocumentFilter.S0.reader.widen[(TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)], TextDocumentFilter.S1.reader.widen[(TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)], TextDocumentFilter.S2.reader.widen[(TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)])
    private given reader_rd0: Reader[(TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)] = rd0
    private val wt0 =
      upickle.default.writer[ujson.Value].comap[(TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)] { v => 
        (v: @unchecked) match 
          case v: TextDocumentFilter.S0 => writeJs(v)
          case v: TextDocumentFilter.S1 => writeJs(v)
          case v: TextDocumentFilter.S2 => writeJs(v)
      }
    private given writer_wt0: Writer[(TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)] = wt0
    private val _reader: Reader[TextDocumentFilter] = badMerge(TextDocumentFilter.S0.reader.widen[TextDocumentFilter], TextDocumentFilter.S1.reader.widen[TextDocumentFilter], TextDocumentFilter.S2.reader.widen[TextDocumentFilter])
    private val _writer: Writer[TextDocumentFilter] = 
      upickle.default.writer[ujson.Value].comap[TextDocumentFilter] { v => 
        (v: @unchecked) match 
          case v: TextDocumentFilter.S0 => writeJs(v)
          case v: TextDocumentFilter.S1 => writeJs(v)
          case v: TextDocumentFilter.S2 => writeJs(v)
      }
    given reader: Reader[TextDocumentFilter] = _reader
    given writer: Writer[TextDocumentFilter] = _writer
    given Typeable[TextDocumentFilter] with
      def unapply(s: Any): Option[s.type & TextDocumentFilter] = 
        s match
        case c: TextDocumentFilter.S0 => Some(c.asInstanceOf[s.type & TextDocumentFilter.S0])
        case c: TextDocumentFilter.S1 => Some(c.asInstanceOf[s.type & TextDocumentFilter.S1])
        case c: TextDocumentFilter.S2 => Some(c.asInstanceOf[s.type & TextDocumentFilter.S2])
        case _ => Option.empty
    case class S0(
      language: String,
      scheme: String,
      pattern: String
    )
    object S0:
      given reader: Reader[aliases.TextDocumentFilter.S0] = Pickle.macroR
      given writer: Writer[aliases.TextDocumentFilter.S0] = upickle.default.macroW
    case class S1(
      language: String,
      scheme: String,
      pattern: String
    )
    object S1:
      given reader: Reader[aliases.TextDocumentFilter.S1] = Pickle.macroR
      given writer: Writer[aliases.TextDocumentFilter.S1] = upickle.default.macroW
    case class S2(
      language: String,
      scheme: String,
      pattern: String
    )
    object S2:
      given reader: Reader[aliases.TextDocumentFilter.S2] = Pickle.macroR
      given writer: Writer[aliases.TextDocumentFilter.S2] = upickle.default.macroW
  
  opaque type NotebookDocumentFilter = (NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 | NotebookDocumentFilter.S2)
  object NotebookDocumentFilter:
    private val rd0 = badMerge(NotebookDocumentFilter.S0.reader.widen[(NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 | NotebookDocumentFilter.S2)], NotebookDocumentFilter.S1.reader.widen[(NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 | NotebookDocumentFilter.S2)], NotebookDocumentFilter.S2.reader.widen[(NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 | NotebookDocumentFilter.S2)])
    private given reader_rd0: Reader[(NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 | NotebookDocumentFilter.S2)] = rd0
    private val wt0 =
      upickle.default.writer[ujson.Value].comap[(NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 | NotebookDocumentFilter.S2)] { v => 
        (v: @unchecked) match 
          case v: NotebookDocumentFilter.S0 => writeJs(v)
          case v: NotebookDocumentFilter.S1 => writeJs(v)
          case v: NotebookDocumentFilter.S2 => writeJs(v)
      }
    private given writer_wt0: Writer[(NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 | NotebookDocumentFilter.S2)] = wt0
    private val _reader: Reader[NotebookDocumentFilter] = badMerge(NotebookDocumentFilter.S0.reader.widen[NotebookDocumentFilter], NotebookDocumentFilter.S1.reader.widen[NotebookDocumentFilter], NotebookDocumentFilter.S2.reader.widen[NotebookDocumentFilter])
    private val _writer: Writer[NotebookDocumentFilter] = 
      upickle.default.writer[ujson.Value].comap[NotebookDocumentFilter] { v => 
        (v: @unchecked) match 
          case v: NotebookDocumentFilter.S0 => writeJs(v)
          case v: NotebookDocumentFilter.S1 => writeJs(v)
          case v: NotebookDocumentFilter.S2 => writeJs(v)
      }
    given reader: Reader[NotebookDocumentFilter] = _reader
    given writer: Writer[NotebookDocumentFilter] = _writer
    given Typeable[NotebookDocumentFilter] with
      def unapply(s: Any): Option[s.type & NotebookDocumentFilter] = 
        s match
        case c: NotebookDocumentFilter.S0 => Some(c.asInstanceOf[s.type & NotebookDocumentFilter.S0])
        case c: NotebookDocumentFilter.S1 => Some(c.asInstanceOf[s.type & NotebookDocumentFilter.S1])
        case c: NotebookDocumentFilter.S2 => Some(c.asInstanceOf[s.type & NotebookDocumentFilter.S2])
        case _ => Option.empty
    case class S0(
      notebookType: String,
      scheme: String,
      pattern: String
    )
    object S0:
      given reader: Reader[aliases.NotebookDocumentFilter.S0] = Pickle.macroR
      given writer: Writer[aliases.NotebookDocumentFilter.S0] = upickle.default.macroW
    case class S1(
      notebookType: String,
      scheme: String,
      pattern: String
    )
    object S1:
      given reader: Reader[aliases.NotebookDocumentFilter.S1] = Pickle.macroR
      given writer: Writer[aliases.NotebookDocumentFilter.S1] = upickle.default.macroW
    case class S2(
      notebookType: String,
      scheme: String,
      pattern: String
    )
    object S2:
      given reader: Reader[aliases.NotebookDocumentFilter.S2] = Pickle.macroR
      given writer: Writer[aliases.NotebookDocumentFilter.S2] = upickle.default.macroW
  
  opaque type Pattern = String
  object Pattern:
    private val _reader: Reader[Pattern] = stringCodec
    private val _writer: Writer[Pattern] = 
      stringCodec
    given reader: Reader[Pattern] = _reader
    given writer: Writer[Pattern] = _writer
    given Typeable[Pattern] with
      def unapply(s: Any): Option[s.type & Pattern] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
  
