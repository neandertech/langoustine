package langoustine.lsp

import langoustine.*
import langoustine.lsp.json.{*, given}
import upickle.default.*

object aliases:
  opaque type Definition = (structures.Location | Vector[structures.Location])
  object Definition:
    private val rd0 = badMerge(
      structures.Location.reader
        .widen[(structures.Location | Vector[structures.Location])],
      upickle.default
        .reader[Vector[structures.Location]]
        .widen[(structures.Location | Vector[structures.Location])]
    )
    private given reader_rd0
        : Reader[(structures.Location | Vector[structures.Location])] = rd0
    private val wt0 = upickle.default
      .writer[ujson.Value]
      .comap[(structures.Location | Vector[structures.Location])] {
        case v: structures.Location =>
          write(v)(using structures.Location.writer);
        case v: Vector[structures.Location] =>
          write(v)(using upickle.default.writer[Vector[structures.Location]]);
      }
    private given writer_wt0
        : Writer[(structures.Location | Vector[structures.Location])] = wt0
    private val _reader: Reader[Definition] = badMerge(
      structures.Location.reader.widen[Definition],
      upickle.default.reader[Vector[structures.Location]].widen[Definition]
    )
    private val _writer: Writer[Definition] =
      upickle.default.writer[ujson.Value].comap[Definition] {
        case v: structures.Location =>
          write(v)(using structures.Location.writer);
        case v: Vector[structures.Location] =>
          write(v)(using upickle.default.writer[Vector[structures.Location]]);
      }
    given reader: Reader[Definition] = _reader
    given writer: Writer[Definition] = _writer
  end Definition

  opaque type DefinitionLink = structures.LocationLink
  object DefinitionLink:
    private val _reader: Reader[DefinitionLink] = structures.LocationLink.reader
    private val _writer: Writer[DefinitionLink] = structures.LocationLink.writer
    given reader: Reader[DefinitionLink]        = _reader
    given writer: Writer[DefinitionLink]        = _writer

  case class LSPArray(elements: Vector[aliases.LSPAny])
  object LSPArray:
    import LSPAny.given
    given reader: Reader[LSPArray] =
      upickle.default.reader[Vector[aliases.LSPAny]].map(LSPArray.apply)
    given writer: Writer[LSPArray] =
      upickle.default.writer[Vector[aliases.LSPAny]].comap(_.elements)

  opaque type LSPAny =
    (structures.LSPObject | aliases.LSPArray | String | Int |
      RuntimeBase.uinteger | Float | Boolean | Null)
  object LSPAny:
    private val rd0 = badMerge(
      structures.LSPObject.reader.widen[
        (structures.LSPObject | aliases.LSPArray | String | Int |
          RuntimeBase.uinteger | Float | Boolean | Null)
      ],
      aliases.LSPArray.reader.widen[
        (structures.LSPObject | aliases.LSPArray | String | Int |
          RuntimeBase.uinteger | Float | Boolean | Null)
      ],
      stringCodec.widen[
        (structures.LSPObject | aliases.LSPArray | String | Int |
          RuntimeBase.uinteger | Float | Boolean | Null)
      ],
      intCodec.widen[
        (structures.LSPObject | aliases.LSPArray | String | Int |
          RuntimeBase.uinteger | Float | Boolean | Null)
      ],
      upickle.default
        .reader[RuntimeBase.uinteger]
        .widen[
          (structures.LSPObject | aliases.LSPArray | String | Int |
            RuntimeBase.uinteger | Float | Boolean | Null)
        ],
      upickle.default
        .reader[Float]
        .widen[
          (structures.LSPObject | aliases.LSPArray | String | Int |
            RuntimeBase.uinteger | Float | Boolean | Null)
        ],
      upickle.default
        .reader[Boolean]
        .widen[
          (structures.LSPObject | aliases.LSPArray | String | Int |
            RuntimeBase.uinteger | Float | Boolean | Null)
        ],
      nullReadWriter.widen[
        (structures.LSPObject | aliases.LSPArray | String | Int |
          RuntimeBase.uinteger | Float | Boolean | Null)
      ]
    )
    private given reader_rd0: Reader[
      (structures.LSPObject | aliases.LSPArray | String | Int |
        RuntimeBase.uinteger | Float | Boolean | Null)
    ] = rd0
    private val wt0 = upickle.default
      .writer[ujson.Value]
      .comap[
        (structures.LSPObject | aliases.LSPArray | String | Int |
          RuntimeBase.uinteger | Float | Boolean | Null)
      ] {
        case v: structures.LSPObject =>
          write(v)(using structures.LSPObject.writer);
        case v: aliases.LSPArray => write(v)(using aliases.LSPArray.writer);
        case v: String           => write(v)(using stringCodec);
        case v: Int              => write(v)(using intCodec);
        case v: RuntimeBase.uinteger =>
          write(v)(using upickle.default.writer[RuntimeBase.uinteger]);
        case v: Float   => write(v)(using upickle.default.writer[Float]);
        case v: Boolean => write(v)(using upickle.default.writer[Boolean]);
        case null       => ujson.Null; case _ => ???
      }
    private given writer_wt0: Writer[
      (structures.LSPObject | aliases.LSPArray | String | Int |
        RuntimeBase.uinteger | Float | Boolean | Null)
    ] = wt0
    private val _reader: Reader[LSPAny] = badMerge(
      structures.LSPObject.reader.widen[LSPAny],
      aliases.LSPArray.reader.widen[LSPAny],
      stringCodec.widen[LSPAny],
      intCodec.widen[LSPAny],
      upickle.default.reader[RuntimeBase.uinteger].widen[LSPAny],
      upickle.default.reader[Float].widen[LSPAny],
      upickle.default.reader[Boolean].widen[LSPAny],
      nullReadWriter.widen[LSPAny]
    )
    private val _writer: Writer[LSPAny] =
      upickle.default.writer[ujson.Value].comap[LSPAny] {
        case v: structures.LSPObject =>
          write(v)(using structures.LSPObject.writer);
        case v: aliases.LSPArray => write(v)(using aliases.LSPArray.writer);
        case v: String           => write(v)(using stringCodec);
        case v: Int              => write(v)(using intCodec);
        case v: RuntimeBase.uinteger =>
          write(v)(using upickle.default.writer[RuntimeBase.uinteger]);
        case v: Float   => write(v)(using upickle.default.writer[Float]);
        case v: Boolean => write(v)(using upickle.default.writer[Boolean]);
        case null       => ujson.Null; case _ => ???
      }
    given reader: Reader[LSPAny] = _reader
    given writer: Writer[LSPAny] = _writer
  end LSPAny

  opaque type Declaration = (structures.Location | Vector[structures.Location])
  object Declaration:
    private val rd0 = badMerge(
      structures.Location.reader
        .widen[(structures.Location | Vector[structures.Location])],
      upickle.default
        .reader[Vector[structures.Location]]
        .widen[(structures.Location | Vector[structures.Location])]
    )
    private given reader_rd0
        : Reader[(structures.Location | Vector[structures.Location])] = rd0
    private val wt0 = upickle.default
      .writer[ujson.Value]
      .comap[(structures.Location | Vector[structures.Location])] {
        case v: structures.Location =>
          write(v)(using structures.Location.writer);
        case v: Vector[structures.Location] =>
          write(v)(using upickle.default.writer[Vector[structures.Location]]);
      }
    private given writer_wt0
        : Writer[(structures.Location | Vector[structures.Location])] = wt0
    private val _reader: Reader[Declaration] = badMerge(
      structures.Location.reader.widen[Declaration],
      upickle.default.reader[Vector[structures.Location]].widen[Declaration]
    )
    private val _writer: Writer[Declaration] =
      upickle.default.writer[ujson.Value].comap[Declaration] {
        case v: structures.Location =>
          write(v)(using structures.Location.writer);
        case v: Vector[structures.Location] =>
          write(v)(using upickle.default.writer[Vector[structures.Location]]);
      }
    given reader: Reader[Declaration] = _reader
    given writer: Writer[Declaration] = _writer
  end Declaration

  opaque type DeclarationLink = structures.LocationLink
  object DeclarationLink:
    private val _reader: Reader[DeclarationLink] =
      structures.LocationLink.reader
    private val _writer: Writer[DeclarationLink] =
      structures.LocationLink.writer
    given reader: Reader[DeclarationLink] = _reader
    given writer: Writer[DeclarationLink] = _writer

  opaque type InlineValue =
    (structures.InlineValueText | structures.InlineValueVariableLookup |
      structures.InlineValueEvaluatableExpression)
  object InlineValue:
    private val rd0 = badMerge(
      structures.InlineValueText.reader.widen[
        (structures.InlineValueText | structures.InlineValueVariableLookup |
          structures.InlineValueEvaluatableExpression)
      ],
      structures.InlineValueVariableLookup.reader.widen[
        (structures.InlineValueText | structures.InlineValueVariableLookup |
          structures.InlineValueEvaluatableExpression)
      ],
      structures.InlineValueEvaluatableExpression.reader.widen[
        (structures.InlineValueText | structures.InlineValueVariableLookup |
          structures.InlineValueEvaluatableExpression)
      ]
    )
    private given reader_rd0: Reader[
      (structures.InlineValueText | structures.InlineValueVariableLookup |
        structures.InlineValueEvaluatableExpression)
    ] = rd0
    private val wt0 = upickle.default
      .writer[ujson.Value]
      .comap[
        (structures.InlineValueText | structures.InlineValueVariableLookup |
          structures.InlineValueEvaluatableExpression)
      ] {
        case v: structures.InlineValueText =>
          write(v)(using structures.InlineValueText.writer);
        case v: structures.InlineValueVariableLookup =>
          write(v)(using structures.InlineValueVariableLookup.writer);
        case v: structures.InlineValueEvaluatableExpression =>
          write(v)(using structures.InlineValueEvaluatableExpression.writer);
      }
    private given writer_wt0: Writer[
      (structures.InlineValueText | structures.InlineValueVariableLookup |
        structures.InlineValueEvaluatableExpression)
    ] = wt0
    private val _reader: Reader[InlineValue] = badMerge(
      structures.InlineValueText.reader.widen[InlineValue],
      structures.InlineValueVariableLookup.reader.widen[InlineValue],
      structures.InlineValueEvaluatableExpression.reader.widen[InlineValue]
    )
    private val _writer: Writer[InlineValue] =
      upickle.default.writer[ujson.Value].comap[InlineValue] {
        case v: structures.InlineValueText =>
          write(v)(using structures.InlineValueText.writer);
        case v: structures.InlineValueVariableLookup =>
          write(v)(using structures.InlineValueVariableLookup.writer);
        case v: structures.InlineValueEvaluatableExpression =>
          write(v)(using structures.InlineValueEvaluatableExpression.writer);
      }
    given reader: Reader[InlineValue] = _reader
    given writer: Writer[InlineValue] = _writer
  end InlineValue

  opaque type DocumentDiagnosticReport =
    (structures.RelatedFullDocumentDiagnosticReport |
      structures.RelatedUnchangedDocumentDiagnosticReport)
  object DocumentDiagnosticReport:
    private val rd0 = badMerge(
      structures.RelatedFullDocumentDiagnosticReport.reader.widen[
        (structures.RelatedFullDocumentDiagnosticReport |
          structures.RelatedUnchangedDocumentDiagnosticReport)
      ],
      structures.RelatedUnchangedDocumentDiagnosticReport.reader.widen[
        (structures.RelatedFullDocumentDiagnosticReport |
          structures.RelatedUnchangedDocumentDiagnosticReport)
      ]
    )
    private given reader_rd0: Reader[
      (structures.RelatedFullDocumentDiagnosticReport |
        structures.RelatedUnchangedDocumentDiagnosticReport)
    ] = rd0
    private val wt0 = upickle.default
      .writer[ujson.Value]
      .comap[
        (structures.RelatedFullDocumentDiagnosticReport |
          structures.RelatedUnchangedDocumentDiagnosticReport)
      ] {
        case v: structures.RelatedFullDocumentDiagnosticReport =>
          write(v)(using structures.RelatedFullDocumentDiagnosticReport.writer);
        case v: structures.RelatedUnchangedDocumentDiagnosticReport =>
          write(v)(using
            structures.RelatedUnchangedDocumentDiagnosticReport.writer
          );
      }
    private given writer_wt0: Writer[
      (structures.RelatedFullDocumentDiagnosticReport |
        structures.RelatedUnchangedDocumentDiagnosticReport)
    ] = wt0
    private val _reader: Reader[DocumentDiagnosticReport] = badMerge(
      structures.RelatedFullDocumentDiagnosticReport.reader
        .widen[DocumentDiagnosticReport],
      structures.RelatedUnchangedDocumentDiagnosticReport.reader
        .widen[DocumentDiagnosticReport]
    )
    private val _writer: Writer[DocumentDiagnosticReport] =
      upickle.default.writer[ujson.Value].comap[DocumentDiagnosticReport] {
        case v: structures.RelatedFullDocumentDiagnosticReport =>
          write(v)(using structures.RelatedFullDocumentDiagnosticReport.writer);
        case v: structures.RelatedUnchangedDocumentDiagnosticReport =>
          write(v)(using
            structures.RelatedUnchangedDocumentDiagnosticReport.writer
          );
      }
    given reader: Reader[DocumentDiagnosticReport] = _reader
    given writer: Writer[DocumentDiagnosticReport] = _writer
  end DocumentDiagnosticReport

  opaque type PrepareRenameResult =
    (structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)
  object PrepareRenameResult:
    private val rd0 = badMerge(
      structures.Range.reader.widen[
        (structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)
      ],
      PrepareRenameResult.S0.reader.widen[
        (structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)
      ],
      PrepareRenameResult.S1.reader.widen[
        (structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)
      ]
    )
    private given reader_rd0: Reader[
      (structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)
    ] = rd0
    private val wt0 = upickle.default
      .writer[ujson.Value]
      .comap[
        (structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)
      ] {
        case v: structures.Range => write(v)(using structures.Range.writer);
        case v: PrepareRenameResult.S0 =>
          write(v)(using PrepareRenameResult.S0.writer);
        case v: PrepareRenameResult.S1 =>
          write(v)(using PrepareRenameResult.S1.writer);
      }
    private given writer_wt0: Writer[
      (structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)
    ] = wt0
    private val _reader: Reader[PrepareRenameResult] = badMerge(
      structures.Range.reader.widen[PrepareRenameResult],
      PrepareRenameResult.S0.reader.widen[PrepareRenameResult],
      PrepareRenameResult.S1.reader.widen[PrepareRenameResult]
    )
    private val _writer: Writer[PrepareRenameResult] =
      upickle.default.writer[ujson.Value].comap[PrepareRenameResult] {
        case v: structures.Range => write(v)(using structures.Range.writer);
        case v: PrepareRenameResult.S0 =>
          write(v)(using PrepareRenameResult.S0.writer);
        case v: PrepareRenameResult.S1 =>
          write(v)(using PrepareRenameResult.S1.writer);
      }
    given reader: Reader[PrepareRenameResult] = _reader
    given writer: Writer[PrepareRenameResult] = _writer
    case class S0(
        range: structures.Range,
        placeholder: String
    )
    object S0:
      given reader: Reader[aliases.PrepareRenameResult.S0] = Pickle.macroR
      given writer: Writer[aliases.PrepareRenameResult.S0] =
        upickle.default.macroW
    case class S1(
        defaultBehavior: Boolean
    )
    object S1:
      given reader: Reader[aliases.PrepareRenameResult.S1] = Pickle.macroR
      given writer: Writer[aliases.PrepareRenameResult.S1] =
        upickle.default.macroW
  end PrepareRenameResult

  opaque type URI = String
  object URI:
    private val _reader: Reader[URI] = stringCodec
    private val _writer: Writer[URI] = stringCodec
    given reader: Reader[URI]        = _reader
    given writer: Writer[URI]        = _writer

  opaque type ProgressToken = (Int | String)
  object ProgressToken:
    private val rd0 = badMerge(
      intCodec.widen[(Int | String)],
      stringCodec.widen[(Int | String)]
    )
    private given reader_rd0: Reader[(Int | String)] = rd0
    private val wt0 =
      upickle.default.writer[ujson.Value].comap[(Int | String)] {
        case v: Int    => write(v)(using intCodec);
        case v: String => write(v)(using stringCodec);
      }
    private given writer_wt0: Writer[(Int | String)] = wt0
    private val _reader: Reader[ProgressToken] =
      badMerge(intCodec.widen[ProgressToken], stringCodec.widen[ProgressToken])
    private val _writer: Writer[ProgressToken] =
      upickle.default.writer[ujson.Value].comap[ProgressToken] {
        case v: Int    => write(v)(using intCodec);
        case v: String => write(v)(using stringCodec);
      }
    given reader: Reader[ProgressToken] = _reader
    given writer: Writer[ProgressToken] = _writer
  end ProgressToken

  opaque type DocumentSelector = Vector[(String | aliases.DocumentFilter)]
  object DocumentSelector:
    private val rd0 = badMerge(
      stringCodec.widen[(String | aliases.DocumentFilter)],
      aliases.DocumentFilter.reader.widen[(String | aliases.DocumentFilter)]
    )
    private given reader_rd0: Reader[(String | aliases.DocumentFilter)] = rd0
    private val wt0 = upickle.default
      .writer[ujson.Value]
      .comap[(String | aliases.DocumentFilter)] {
        case v: String => write(v)(using stringCodec);
        case v: aliases.DocumentFilter =>
          write(v)(using aliases.DocumentFilter.writer);
      }
    private given writer_wt0: Writer[(String | aliases.DocumentFilter)] = wt0
    private val _reader: Reader[DocumentSelector] =
      upickle.default.reader[Vector[(String | aliases.DocumentFilter)]]
    private val _writer: Writer[DocumentSelector] =
      upickle.default.writer[Vector[(String | aliases.DocumentFilter)]]
    given reader: Reader[DocumentSelector] = _reader
    given writer: Writer[DocumentSelector] = _writer
  end DocumentSelector

  opaque type ChangeAnnotationIdentifier = String
  object ChangeAnnotationIdentifier:
    private val _reader: Reader[ChangeAnnotationIdentifier] = stringCodec
    private val _writer: Writer[ChangeAnnotationIdentifier] = stringCodec
    given reader: Reader[ChangeAnnotationIdentifier]        = _reader
    given writer: Writer[ChangeAnnotationIdentifier]        = _writer

  opaque type WorkspaceDocumentDiagnosticReport =
    (structures.WorkspaceFullDocumentDiagnosticReport |
      structures.WorkspaceUnchangedDocumentDiagnosticReport)
  object WorkspaceDocumentDiagnosticReport:
    private val rd0 = badMerge(
      structures.WorkspaceFullDocumentDiagnosticReport.reader.widen[
        (structures.WorkspaceFullDocumentDiagnosticReport |
          structures.WorkspaceUnchangedDocumentDiagnosticReport)
      ],
      structures.WorkspaceUnchangedDocumentDiagnosticReport.reader.widen[
        (structures.WorkspaceFullDocumentDiagnosticReport |
          structures.WorkspaceUnchangedDocumentDiagnosticReport)
      ]
    )
    private given reader_rd0: Reader[
      (structures.WorkspaceFullDocumentDiagnosticReport |
        structures.WorkspaceUnchangedDocumentDiagnosticReport)
    ] = rd0
    private val wt0 = upickle.default
      .writer[ujson.Value]
      .comap[
        (structures.WorkspaceFullDocumentDiagnosticReport |
          structures.WorkspaceUnchangedDocumentDiagnosticReport)
      ] {
        case v: structures.WorkspaceFullDocumentDiagnosticReport =>
          write(v)(using
            structures.WorkspaceFullDocumentDiagnosticReport.writer
          );
        case v: structures.WorkspaceUnchangedDocumentDiagnosticReport =>
          write(v)(using
            structures.WorkspaceUnchangedDocumentDiagnosticReport.writer
          );
      }
    private given writer_wt0: Writer[
      (structures.WorkspaceFullDocumentDiagnosticReport |
        structures.WorkspaceUnchangedDocumentDiagnosticReport)
    ] = wt0
    private val _reader: Reader[WorkspaceDocumentDiagnosticReport] = badMerge(
      structures.WorkspaceFullDocumentDiagnosticReport.reader
        .widen[WorkspaceDocumentDiagnosticReport],
      structures.WorkspaceUnchangedDocumentDiagnosticReport.reader
        .widen[WorkspaceDocumentDiagnosticReport]
    )
    private val _writer: Writer[WorkspaceDocumentDiagnosticReport] =
      upickle.default
        .writer[ujson.Value]
        .comap[WorkspaceDocumentDiagnosticReport] {
          case v: structures.WorkspaceFullDocumentDiagnosticReport =>
            write(v)(using
              structures.WorkspaceFullDocumentDiagnosticReport.writer
            );
          case v: structures.WorkspaceUnchangedDocumentDiagnosticReport =>
            write(v)(using
              structures.WorkspaceUnchangedDocumentDiagnosticReport.writer
            );
        }
    given reader: Reader[WorkspaceDocumentDiagnosticReport] = _reader
    given writer: Writer[WorkspaceDocumentDiagnosticReport] = _writer
  end WorkspaceDocumentDiagnosticReport

  opaque type TextDocumentContentChangeEvent =
    (TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)
  object TextDocumentContentChangeEvent:
    private val rd0 = badMerge(
      TextDocumentContentChangeEvent.S0.reader.widen[
        (TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)
      ],
      TextDocumentContentChangeEvent.S1.reader.widen[
        (TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)
      ]
    )
    private given reader_rd0: Reader[
      (TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)
    ] = rd0
    private val wt0 = upickle.default
      .writer[ujson.Value]
      .comap[
        (TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)
      ] {
        case v: TextDocumentContentChangeEvent.S0 =>
          write(v)(using TextDocumentContentChangeEvent.S0.writer);
        case v: TextDocumentContentChangeEvent.S1 =>
          write(v)(using TextDocumentContentChangeEvent.S1.writer);
      }
    private given writer_wt0: Writer[
      (TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)
    ] = wt0
    private val _reader: Reader[TextDocumentContentChangeEvent] = badMerge(
      TextDocumentContentChangeEvent.S0.reader
        .widen[TextDocumentContentChangeEvent],
      TextDocumentContentChangeEvent.S1.reader
        .widen[TextDocumentContentChangeEvent]
    )
    private val _writer: Writer[TextDocumentContentChangeEvent] =
      upickle.default
        .writer[ujson.Value]
        .comap[TextDocumentContentChangeEvent] {
          case v: TextDocumentContentChangeEvent.S0 =>
            write(v)(using TextDocumentContentChangeEvent.S0.writer);
          case v: TextDocumentContentChangeEvent.S1 =>
            write(v)(using TextDocumentContentChangeEvent.S1.writer);
        }
    given reader: Reader[TextDocumentContentChangeEvent] = _reader
    given writer: Writer[TextDocumentContentChangeEvent] = _writer
    case class S0(
        range: structures.Range,
        rangeLength: RuntimeBase.uinteger,
        text: String
    )
    object S0:
      given reader: Reader[aliases.TextDocumentContentChangeEvent.S0] =
        Pickle.macroR
      given writer: Writer[aliases.TextDocumentContentChangeEvent.S0] =
        upickle.default.macroW
    case class S1(
        text: String
    )
    object S1:
      given reader: Reader[aliases.TextDocumentContentChangeEvent.S1] =
        Pickle.macroR
      given writer: Writer[aliases.TextDocumentContentChangeEvent.S1] =
        upickle.default.macroW
  end TextDocumentContentChangeEvent

  opaque type MarkedString = (String | MarkedString.S0)
  object MarkedString:
    private val rd0 = badMerge(
      stringCodec.widen[(String | MarkedString.S0)],
      MarkedString.S0.reader.widen[(String | MarkedString.S0)]
    )
    private given reader_rd0: Reader[(String | MarkedString.S0)] = rd0
    private val wt0 =
      upickle.default.writer[ujson.Value].comap[(String | MarkedString.S0)] {
        case v: String          => write(v)(using stringCodec);
        case v: MarkedString.S0 => write(v)(using MarkedString.S0.writer);
      }
    private given writer_wt0: Writer[(String | MarkedString.S0)] = wt0
    private val _reader: Reader[MarkedString] = badMerge(
      stringCodec.widen[MarkedString],
      MarkedString.S0.reader.widen[MarkedString]
    )
    private val _writer: Writer[MarkedString] =
      upickle.default.writer[ujson.Value].comap[MarkedString] {
        case v: String          => write(v)(using stringCodec);
        case v: MarkedString.S0 => write(v)(using MarkedString.S0.writer);
      }
    given reader: Reader[MarkedString] = _reader
    given writer: Writer[MarkedString] = _writer
    case class S0(
        language: String,
        value: String
    )
    object S0:
      given reader: Reader[aliases.MarkedString.S0] = Pickle.macroR
      given writer: Writer[aliases.MarkedString.S0] = upickle.default.macroW
  end MarkedString

  opaque type DocumentFilter =
    (aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)
  object DocumentFilter:
    private val rd0 = badMerge(
      aliases.TextDocumentFilter.reader.widen[
        (aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)
      ],
      structures.NotebookCellTextDocumentFilter.reader.widen[
        (aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)
      ]
    )
    private given reader_rd0: Reader[
      (aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)
    ] = rd0
    private val wt0 = upickle.default
      .writer[ujson.Value]
      .comap[
        (aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)
      ] {
        case v: aliases.TextDocumentFilter =>
          write(v)(using aliases.TextDocumentFilter.writer);
        case v: structures.NotebookCellTextDocumentFilter =>
          write(v)(using structures.NotebookCellTextDocumentFilter.writer);
      }
    private given writer_wt0: Writer[
      (aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)
    ] = wt0
    private val _reader: Reader[DocumentFilter] = badMerge(
      aliases.TextDocumentFilter.reader.widen[DocumentFilter],
      structures.NotebookCellTextDocumentFilter.reader.widen[DocumentFilter]
    )
    private val _writer: Writer[DocumentFilter] =
      upickle.default.writer[ujson.Value].comap[DocumentFilter] {
        case v: aliases.TextDocumentFilter =>
          write(v)(using aliases.TextDocumentFilter.writer);
        case v: structures.NotebookCellTextDocumentFilter =>
          write(v)(using structures.NotebookCellTextDocumentFilter.writer);
      }
    given reader: Reader[DocumentFilter] = _reader
    given writer: Writer[DocumentFilter] = _writer
  end DocumentFilter

  opaque type GlobPattern = (aliases.Pattern | structures.RelativePattern)
  object GlobPattern:
    private val rd0 = badMerge(
      aliases.Pattern.reader
        .widen[(aliases.Pattern | structures.RelativePattern)],
      structures.RelativePattern.reader
        .widen[(aliases.Pattern | structures.RelativePattern)]
    )
    private given reader_rd0
        : Reader[(aliases.Pattern | structures.RelativePattern)] = rd0
    private val wt0 = upickle.default
      .writer[ujson.Value]
      .comap[(aliases.Pattern | structures.RelativePattern)] {
        case v: aliases.Pattern => write(v)(using aliases.Pattern.writer);
        case v: structures.RelativePattern =>
          write(v)(using structures.RelativePattern.writer);
      }
    private given writer_wt0
        : Writer[(aliases.Pattern | structures.RelativePattern)] = wt0
    private val _reader: Reader[GlobPattern] = badMerge(
      aliases.Pattern.reader.widen[GlobPattern],
      structures.RelativePattern.reader.widen[GlobPattern]
    )
    private val _writer: Writer[GlobPattern] =
      upickle.default.writer[ujson.Value].comap[GlobPattern] {
        case v: aliases.Pattern => write(v)(using aliases.Pattern.writer);
        case v: structures.RelativePattern =>
          write(v)(using structures.RelativePattern.writer);
      }
    given reader: Reader[GlobPattern] = _reader
    given writer: Writer[GlobPattern] = _writer
  end GlobPattern

  opaque type TextDocumentFilter =
    (TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)
  object TextDocumentFilter:
    private val rd0 = badMerge(
      TextDocumentFilter.S0.reader.widen[
        (TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)
      ],
      TextDocumentFilter.S1.reader.widen[
        (TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)
      ],
      TextDocumentFilter.S2.reader.widen[
        (TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)
      ]
    )
    private given reader_rd0: Reader[
      (TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)
    ] = rd0
    private val wt0 = upickle.default
      .writer[ujson.Value]
      .comap[
        (TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)
      ] {
        case v: TextDocumentFilter.S0 =>
          write(v)(using TextDocumentFilter.S0.writer);
        case v: TextDocumentFilter.S1 =>
          write(v)(using TextDocumentFilter.S1.writer);
        case v: TextDocumentFilter.S2 =>
          write(v)(using TextDocumentFilter.S2.writer);
      }
    private given writer_wt0: Writer[
      (TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)
    ] = wt0
    private val _reader: Reader[TextDocumentFilter] = badMerge(
      TextDocumentFilter.S0.reader.widen[TextDocumentFilter],
      TextDocumentFilter.S1.reader.widen[TextDocumentFilter],
      TextDocumentFilter.S2.reader.widen[TextDocumentFilter]
    )
    private val _writer: Writer[TextDocumentFilter] =
      upickle.default.writer[ujson.Value].comap[TextDocumentFilter] {
        case v: TextDocumentFilter.S0 =>
          write(v)(using TextDocumentFilter.S0.writer);
        case v: TextDocumentFilter.S1 =>
          write(v)(using TextDocumentFilter.S1.writer);
        case v: TextDocumentFilter.S2 =>
          write(v)(using TextDocumentFilter.S2.writer);
      }
    given reader: Reader[TextDocumentFilter] = _reader
    given writer: Writer[TextDocumentFilter] = _writer
    case class S0(
        language: String,
        scheme: String,
        pattern: String
    )
    object S0:
      given reader: Reader[aliases.TextDocumentFilter.S0] = Pickle.macroR
      given writer: Writer[aliases.TextDocumentFilter.S0] =
        upickle.default.macroW
    case class S1(
        language: String,
        scheme: String,
        pattern: String
    )
    object S1:
      given reader: Reader[aliases.TextDocumentFilter.S1] = Pickle.macroR
      given writer: Writer[aliases.TextDocumentFilter.S1] =
        upickle.default.macroW
    case class S2(
        language: String,
        scheme: String,
        pattern: String
    )
    object S2:
      given reader: Reader[aliases.TextDocumentFilter.S2] = Pickle.macroR
      given writer: Writer[aliases.TextDocumentFilter.S2] =
        upickle.default.macroW
  end TextDocumentFilter

  opaque type NotebookDocumentFilter =
    (NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 |
      NotebookDocumentFilter.S2)
  object NotebookDocumentFilter:
    private val rd0 = badMerge(
      NotebookDocumentFilter.S0.reader.widen[
        (NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 |
          NotebookDocumentFilter.S2)
      ],
      NotebookDocumentFilter.S1.reader.widen[
        (NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 |
          NotebookDocumentFilter.S2)
      ],
      NotebookDocumentFilter.S2.reader.widen[
        (NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 |
          NotebookDocumentFilter.S2)
      ]
    )
    private given reader_rd0: Reader[
      (NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 |
        NotebookDocumentFilter.S2)
    ] = rd0
    private val wt0 = upickle.default
      .writer[ujson.Value]
      .comap[
        (NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 |
          NotebookDocumentFilter.S2)
      ] {
        case v: NotebookDocumentFilter.S0 =>
          write(v)(using NotebookDocumentFilter.S0.writer);
        case v: NotebookDocumentFilter.S1 =>
          write(v)(using NotebookDocumentFilter.S1.writer);
        case v: NotebookDocumentFilter.S2 =>
          write(v)(using NotebookDocumentFilter.S2.writer);
      }
    private given writer_wt0: Writer[
      (NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 |
        NotebookDocumentFilter.S2)
    ] = wt0
    private val _reader: Reader[NotebookDocumentFilter] = badMerge(
      NotebookDocumentFilter.S0.reader.widen[NotebookDocumentFilter],
      NotebookDocumentFilter.S1.reader.widen[NotebookDocumentFilter],
      NotebookDocumentFilter.S2.reader.widen[NotebookDocumentFilter]
    )
    private val _writer: Writer[NotebookDocumentFilter] =
      upickle.default.writer[ujson.Value].comap[NotebookDocumentFilter] {
        case v: NotebookDocumentFilter.S0 =>
          write(v)(using NotebookDocumentFilter.S0.writer);
        case v: NotebookDocumentFilter.S1 =>
          write(v)(using NotebookDocumentFilter.S1.writer);
        case v: NotebookDocumentFilter.S2 =>
          write(v)(using NotebookDocumentFilter.S2.writer);
      }
    given reader: Reader[NotebookDocumentFilter] = _reader
    given writer: Writer[NotebookDocumentFilter] = _writer
    case class S0(
        notebookType: String,
        scheme: String,
        pattern: String
    )
    object S0:
      given reader: Reader[aliases.NotebookDocumentFilter.S0] = Pickle.macroR
      given writer: Writer[aliases.NotebookDocumentFilter.S0] =
        upickle.default.macroW
    case class S1(
        notebookType: String,
        scheme: String,
        pattern: String
    )
    object S1:
      given reader: Reader[aliases.NotebookDocumentFilter.S1] = Pickle.macroR
      given writer: Writer[aliases.NotebookDocumentFilter.S1] =
        upickle.default.macroW
    case class S2(
        notebookType: String,
        scheme: String,
        pattern: String
    )
    object S2:
      given reader: Reader[aliases.NotebookDocumentFilter.S2] = Pickle.macroR
      given writer: Writer[aliases.NotebookDocumentFilter.S2] =
        upickle.default.macroW
  end NotebookDocumentFilter

  opaque type Pattern = String
  object Pattern:
    private val _reader: Reader[Pattern] = stringCodec
    private val _writer: Writer[Pattern] = stringCodec
    given reader: Reader[Pattern]        = _reader
    given writer: Writer[Pattern]        = _writer
end aliases
