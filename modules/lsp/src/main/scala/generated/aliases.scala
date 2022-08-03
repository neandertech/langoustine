package langoustine.lsp

import langoustine.*
import langoustine.lsp.json.{*, given}
import upickle.default.*
import scala.reflect.*
// format: off

object aliases: 
  opaque type Definition = (structures.Location | Vector[structures.Location])
  object Definition:
    inline def apply(v: structures.Location): Definition = v
    inline def apply(v: Vector[structures.Location]): Definition = v
    
    given reader: Reader[Definition] = 
      badMerge[Definition](structures.Location.reader, upickle.default.reader[Vector[structures.Location]])
    
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
    
    given reader: Reader[DefinitionLink] = 
      structures.LocationLink.reader
    
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
    
    given reader: Reader[Declaration] = 
      badMerge[Declaration](structures.Location.reader, upickle.default.reader[Vector[structures.Location]])
    
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
    
    given reader: Reader[DeclarationLink] = 
      structures.LocationLink.reader
    
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
    
    given reader: Reader[InlineValue] = 
      badMerge[InlineValue](structures.InlineValueText.reader, structures.InlineValueVariableLookup.reader, structures.InlineValueEvaluatableExpression.reader)
    
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
    
    given reader: Reader[DocumentDiagnosticReport] = 
      badMerge[DocumentDiagnosticReport](structures.RelatedFullDocumentDiagnosticReport.reader, structures.RelatedUnchangedDocumentDiagnosticReport.reader)
    
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
    
    given reader: Reader[PrepareRenameResult] = 
      badMerge[PrepareRenameResult](structures.Range.reader, PrepareRenameResult.S0.reader, PrepareRenameResult.S1.reader)
    
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
    
    given reader: Reader[URI] = 
      stringCodec
    
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
    
    given reader: Reader[ProgressToken] = 
      badMerge[ProgressToken](intCodec, stringCodec)
    
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
    
    given reader: Reader[DocumentSelector] = 
      given Reader[(String | aliases.DocumentFilter)] = 
        badMerge[(String | aliases.DocumentFilter)](stringCodec, aliases.DocumentFilter.reader)
      vectorReader[(String | aliases.DocumentFilter)]
    
    given writer: Writer[DocumentSelector] =
      given Writer[(String | aliases.DocumentFilter)] = 
        upickle.default.writer[ujson.Value].comap { _v => 
          (_v: @unchecked) match 
            case v: String => writeJs[String](v)
            case v: aliases.DocumentFilter => writeJs[aliases.DocumentFilter](v)
        }
      vectorWriter[(String | aliases.DocumentFilter)]
    
    given Typeable[DocumentSelector] with
      def unapply(s: Any): Option[s.type & DocumentSelector] = 
        s match
        case c: Vector[?] => Some(c.asInstanceOf[s.type & Vector[(String | aliases.DocumentFilter)]])
        case _ => Option.empty
  
  opaque type ChangeAnnotationIdentifier = String
  object ChangeAnnotationIdentifier:
    inline def apply(v: String): ChangeAnnotationIdentifier = v
    
    given reader: Reader[ChangeAnnotationIdentifier] = 
      stringCodec
    
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
    
    given reader: Reader[WorkspaceDocumentDiagnosticReport] = 
      badMerge[WorkspaceDocumentDiagnosticReport](structures.WorkspaceFullDocumentDiagnosticReport.reader, structures.WorkspaceUnchangedDocumentDiagnosticReport.reader)
    
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
    
    given reader: Reader[TextDocumentContentChangeEvent] = 
      badMerge[TextDocumentContentChangeEvent](TextDocumentContentChangeEvent.S0.reader, TextDocumentContentChangeEvent.S1.reader)
    
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
    /**
     *  @param range
     *    The range of the document that changed.
    
     *  @param rangeLength
     *    The optional length of the range that got replaced.
     *    
     *    @deprecated use range instead.
    
     *  @param text
     *    The new text for the provided range.
    
     */
    case class S0(
      range: structures.Range,
      rangeLength: Opt[RuntimeBase.uinteger] = Opt.empty,
      text: String
    )
    object S0:
      given reader: Reader[aliases.TextDocumentContentChangeEvent.S0] = Pickle.macroR
      given writer: Writer[aliases.TextDocumentContentChangeEvent.S0] = upickle.default.macroW
    /**
     *  @param text
     *    The new text of the whole document.
    
     */
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
    
    given reader: Reader[MarkedString] = 
      badMerge[MarkedString](stringCodec, MarkedString.S0.reader)
    
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
    
    given reader: Reader[DocumentFilter] = 
      badMerge[DocumentFilter](aliases.TextDocumentFilter.reader, structures.NotebookCellTextDocumentFilter.reader)
    
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
    
    given reader: Reader[GlobPattern] = 
      badMerge[GlobPattern](aliases.Pattern.reader, structures.RelativePattern.reader)
    
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
    
    given reader: Reader[TextDocumentFilter] = 
      badMerge[TextDocumentFilter](TextDocumentFilter.S0.reader, TextDocumentFilter.S1.reader, TextDocumentFilter.S2.reader)
    
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
    /**
     *  @param language
     *    A language id, like `typescript`. 
    
     *  @param scheme
     *    A Uri [scheme](#Uri.scheme), like `file` or `untitled`. 
    
     *  @param pattern
     *    A glob pattern, like `*.{ts,js}`. 
    
     */
    case class S0(
      language: String,
      scheme: Opt[String] = Opt.empty,
      pattern: Opt[String] = Opt.empty
    )
    object S0:
      given reader: Reader[aliases.TextDocumentFilter.S0] = Pickle.macroR
      given writer: Writer[aliases.TextDocumentFilter.S0] = upickle.default.macroW
    /**
     *  @param language
     *    A language id, like `typescript`. 
    
     *  @param scheme
     *    A Uri [scheme](#Uri.scheme), like `file` or `untitled`. 
    
     *  @param pattern
     *    A glob pattern, like `*.{ts,js}`. 
    
     */
    case class S1(
      language: Opt[String] = Opt.empty,
      scheme: String,
      pattern: Opt[String] = Opt.empty
    )
    object S1:
      given reader: Reader[aliases.TextDocumentFilter.S1] = Pickle.macroR
      given writer: Writer[aliases.TextDocumentFilter.S1] = upickle.default.macroW
    /**
     *  @param language
     *    A language id, like `typescript`. 
    
     *  @param scheme
     *    A Uri [scheme](#Uri.scheme), like `file` or `untitled`. 
    
     *  @param pattern
     *    A glob pattern, like `*.{ts,js}`. 
    
     */
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
    
    given reader: Reader[NotebookDocumentFilter] = 
      badMerge[NotebookDocumentFilter](NotebookDocumentFilter.S0.reader, NotebookDocumentFilter.S1.reader, NotebookDocumentFilter.S2.reader)
    
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
    /**
     *  @param notebookType
     *    The type of the enclosing notebook. 
    
     *  @param scheme
     *    A Uri [scheme](#Uri.scheme), like `file` or `untitled`. 
    
     *  @param pattern
     *    A glob pattern. 
    
     */
    case class S0(
      notebookType: String,
      scheme: Opt[String] = Opt.empty,
      pattern: Opt[String] = Opt.empty
    )
    object S0:
      given reader: Reader[aliases.NotebookDocumentFilter.S0] = Pickle.macroR
      given writer: Writer[aliases.NotebookDocumentFilter.S0] = upickle.default.macroW
    /**
     *  @param notebookType
     *    The type of the enclosing notebook. 
    
     *  @param scheme
     *    A Uri [scheme](#Uri.scheme), like `file` or `untitled`.
    
     *  @param pattern
     *    A glob pattern. 
    
     */
    case class S1(
      notebookType: Opt[String] = Opt.empty,
      scheme: String,
      pattern: Opt[String] = Opt.empty
    )
    object S1:
      given reader: Reader[aliases.NotebookDocumentFilter.S1] = Pickle.macroR
      given writer: Writer[aliases.NotebookDocumentFilter.S1] = upickle.default.macroW
    /**
     *  @param notebookType
     *    The type of the enclosing notebook. 
    
     *  @param scheme
     *    A Uri [scheme](#Uri.scheme), like `file` or `untitled`. 
    
     *  @param pattern
     *    A glob pattern. 
    
     */
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
    
    given reader: Reader[Pattern] = 
      stringCodec
    
    given writer: Writer[Pattern] =
      stringCodec
    
    given Typeable[Pattern] with
      def unapply(s: Any): Option[s.type & Pattern] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty
  
