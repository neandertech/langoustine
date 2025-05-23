/*
 * Copyright 2022 Neandertech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package langoustine.lsp

import langoustine.*
import json.{*, given}
import runtime.{*, given}
import upickle.default.*
import scala.reflect.*

object aliases:
  opaque type ChangeAnnotationIdentifier = String
  object ChangeAnnotationIdentifier
      extends codecs.aliases_ChangeAnnotationIdentifier:
    inline def apply(v: String): ChangeAnnotationIdentifier = v

    given Typeable[ChangeAnnotationIdentifier] with
      def unapply(s: Any): Option[s.type & ChangeAnnotationIdentifier] =
        s match
          case c: String => Some(c.asInstanceOf[s.type & String])
          case _         => Option.empty

  opaque type Declaration = (structures.Location | Vector[structures.Location])
  object Declaration extends codecs.aliases_Declaration:
    inline def apply(v: structures.Location): Declaration         = v
    inline def apply(v: Vector[structures.Location]): Declaration = v

    given Typeable[Declaration] with
      def unapply(s: Any): Option[s.type & Declaration] =
        s match
          case c: structures.Location =>
            Some(c.asInstanceOf[s.type & structures.Location])
          case c: Vector[?] =>
            Some(c.asInstanceOf[s.type & Vector[structures.Location]])
          case _ => Option.empty
  end Declaration

  opaque type DeclarationLink = structures.LocationLink
  object DeclarationLink extends codecs.aliases_DeclarationLink:
    inline def apply(v: structures.LocationLink): DeclarationLink = v

    given Typeable[DeclarationLink] with
      def unapply(s: Any): Option[s.type & DeclarationLink] =
        s match
          case c: structures.LocationLink =>
            Some(c.asInstanceOf[s.type & structures.LocationLink])
          case _ => Option.empty

  opaque type Definition = (structures.Location | Vector[structures.Location])
  object Definition extends codecs.aliases_Definition:
    inline def apply(v: structures.Location): Definition         = v
    inline def apply(v: Vector[structures.Location]): Definition = v

    given Typeable[Definition] with
      def unapply(s: Any): Option[s.type & Definition] =
        s match
          case c: structures.Location =>
            Some(c.asInstanceOf[s.type & structures.Location])
          case c: Vector[?] =>
            Some(c.asInstanceOf[s.type & Vector[structures.Location]])
          case _ => Option.empty
  end Definition

  opaque type DefinitionLink = structures.LocationLink
  object DefinitionLink extends codecs.aliases_DefinitionLink:
    inline def apply(v: structures.LocationLink): DefinitionLink = v

    given Typeable[DefinitionLink] with
      def unapply(s: Any): Option[s.type & DefinitionLink] =
        s match
          case c: structures.LocationLink =>
            Some(c.asInstanceOf[s.type & structures.LocationLink])
          case _ => Option.empty

  opaque type DocumentDiagnosticReport =
    (structures.RelatedFullDocumentDiagnosticReport |
      structures.RelatedUnchangedDocumentDiagnosticReport)
  object DocumentDiagnosticReport
      extends codecs.aliases_DocumentDiagnosticReport:
    inline def apply(
        v: structures.RelatedFullDocumentDiagnosticReport
    ): DocumentDiagnosticReport = v
    inline def apply(
        v: structures.RelatedUnchangedDocumentDiagnosticReport
    ): DocumentDiagnosticReport = v

    given Typeable[DocumentDiagnosticReport] with
      def unapply(s: Any): Option[s.type & DocumentDiagnosticReport] =
        s match
          case c: structures.RelatedFullDocumentDiagnosticReport =>
            Some(
              c.asInstanceOf[
                s.type & structures.RelatedFullDocumentDiagnosticReport
              ]
            )
          case c: structures.RelatedUnchangedDocumentDiagnosticReport =>
            Some(
              c.asInstanceOf[
                s.type & structures.RelatedUnchangedDocumentDiagnosticReport
              ]
            )
          case _ => Option.empty
    end given
  end DocumentDiagnosticReport

  opaque type DocumentFilter =
    (aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)
  object DocumentFilter extends codecs.aliases_DocumentFilter:
    inline def apply(v: aliases.TextDocumentFilter): DocumentFilter = v
    inline def apply(
        v: structures.NotebookCellTextDocumentFilter
    ): DocumentFilter = v

    given Typeable[DocumentFilter] with
      def unapply(s: Any): Option[s.type & DocumentFilter] =
        s match
          case c: aliases.TextDocumentFilter =>
            Some(c.asInstanceOf[s.type & aliases.TextDocumentFilter])
          case c: structures.NotebookCellTextDocumentFilter =>
            Some(
              c.asInstanceOf[s.type & structures.NotebookCellTextDocumentFilter]
            )
          case _ => Option.empty
    end given
  end DocumentFilter

  opaque type DocumentSelector = Vector[aliases.DocumentFilter]
  object DocumentSelector extends codecs.aliases_DocumentSelector:
    inline def apply(v: Vector[aliases.DocumentFilter]): DocumentSelector = v

    given Typeable[DocumentSelector] with
      def unapply(s: Any): Option[s.type & DocumentSelector] =
        s match
          case c: Vector[?] =>
            Some(c.asInstanceOf[s.type & Vector[aliases.DocumentFilter]])
          case _ => Option.empty

  opaque type GlobPattern = (aliases.Pattern | structures.RelativePattern)
  object GlobPattern extends codecs.aliases_GlobPattern:
    inline def apply(v: aliases.Pattern): GlobPattern            = v
    inline def apply(v: structures.RelativePattern): GlobPattern = v

    given Typeable[GlobPattern] with
      def unapply(s: Any): Option[s.type & GlobPattern] =
        s match
          case c: aliases.Pattern =>
            Some(c.asInstanceOf[s.type & aliases.Pattern])
          case c: structures.RelativePattern =>
            Some(c.asInstanceOf[s.type & structures.RelativePattern])
          case _ => Option.empty
  end GlobPattern

  opaque type InlineValue =
    (structures.InlineValueText | structures.InlineValueVariableLookup |
      structures.InlineValueEvaluatableExpression)
  object InlineValue extends codecs.aliases_InlineValue:
    inline def apply(v: structures.InlineValueText): InlineValue           = v
    inline def apply(v: structures.InlineValueVariableLookup): InlineValue = v
    inline def apply(
        v: structures.InlineValueEvaluatableExpression
    ): InlineValue = v

    given Typeable[InlineValue] with
      def unapply(s: Any): Option[s.type & InlineValue] =
        s match
          case c: structures.InlineValueText =>
            Some(c.asInstanceOf[s.type & structures.InlineValueText])
          case c: structures.InlineValueVariableLookup =>
            Some(c.asInstanceOf[s.type & structures.InlineValueVariableLookup])
          case c: structures.InlineValueEvaluatableExpression =>
            Some(
              c.asInstanceOf[
                s.type & structures.InlineValueEvaluatableExpression
              ]
            )
          case _ => Option.empty
    end given
  end InlineValue

  opaque type LSPObject = Map[String, ujson.Value]
  object LSPObject extends codecs.aliases_LSPObject:
    inline def apply(v: Map[String, ujson.Value]): LSPObject = v

    given Typeable[LSPObject] with
      def unapply(s: Any): Option[s.type & LSPObject] =
        s match
          case c: Map[String, ujson.Value] =>
            Some(c.asInstanceOf[s.type & Map[String, ujson.Value]])
          case _ => Option.empty

  opaque type MarkedString = (String | MarkedString.MarkdownString)
  object MarkedString extends codecs.aliases_MarkedString:
    inline def apply(v: String): MarkedString                      = v
    inline def apply(v: MarkedString.MarkdownString): MarkedString = v

    given Typeable[MarkedString] with
      def unapply(s: Any): Option[s.type & MarkedString] =
        s match
          case c: String => Some(c.asInstanceOf[s.type & String])
          case c: MarkedString.MarkdownString =>
            Some(c.asInstanceOf[s.type & MarkedString.MarkdownString])
          case _ => Option.empty
    case class MarkdownString(
        language: String,
        value: String
    )
    object MarkdownString
        extends codecs.aliases_MarkedString_MarkdownStringCodec
  end MarkedString

  opaque type NotebookDocumentFilter =
    (NotebookDocumentFilter.ByType | NotebookDocumentFilter.ByScheme |
      NotebookDocumentFilter.ByPattern)
  object NotebookDocumentFilter extends codecs.aliases_NotebookDocumentFilter:
    inline def apply(v: NotebookDocumentFilter.ByType): NotebookDocumentFilter =
      v
    inline def apply(
        v: NotebookDocumentFilter.ByScheme
    ): NotebookDocumentFilter = v
    inline def apply(
        v: NotebookDocumentFilter.ByPattern
    ): NotebookDocumentFilter = v

    given Typeable[NotebookDocumentFilter] with
      def unapply(s: Any): Option[s.type & NotebookDocumentFilter] =
        s match
          case c: NotebookDocumentFilter.ByType =>
            Some(c.asInstanceOf[s.type & NotebookDocumentFilter.ByType])
          case c: NotebookDocumentFilter.ByScheme =>
            Some(c.asInstanceOf[s.type & NotebookDocumentFilter.ByScheme])
          case c: NotebookDocumentFilter.ByPattern =>
            Some(c.asInstanceOf[s.type & NotebookDocumentFilter.ByPattern])
          case _ => Option.empty
    end given

    /** @param notebookType
      *   The type of the enclosing notebook.
      *
      * @param scheme
      *   A Uri {@link Uri.scheme scheme}, like `file` or `untitled`.
      *
      * @param pattern
      *   A glob pattern.
      */
    case class ByType(
        notebookType: String,
        scheme: Opt[String] = Opt.empty,
        pattern: Opt[String] = Opt.empty
    )
    object ByType extends codecs.aliases_NotebookDocumentFilter_ByTypeCodec

    /** @param notebookType
      *   The type of the enclosing notebook.
      *
      * @param scheme
      *   A Uri {@link Uri.scheme scheme}, like `file` or `untitled`.
      *
      * @param pattern
      *   A glob pattern.
      */
    case class ByScheme(
        notebookType: Opt[String] = Opt.empty,
        scheme: String,
        pattern: Opt[String] = Opt.empty
    )
    object ByScheme extends codecs.aliases_NotebookDocumentFilter_BySchemeCodec

    /** @param notebookType
      *   The type of the enclosing notebook.
      *
      * @param scheme
      *   A Uri {@link Uri.scheme scheme}, like `file` or `untitled`.
      *
      * @param pattern
      *   A glob pattern.
      */
    case class ByPattern(
        notebookType: Opt[String] = Opt.empty,
        scheme: Opt[String] = Opt.empty,
        pattern: String
    )
    object ByPattern
        extends codecs.aliases_NotebookDocumentFilter_ByPatternCodec
  end NotebookDocumentFilter

  opaque type Pattern = String
  object Pattern extends codecs.aliases_Pattern:
    inline def apply(v: String): Pattern = v

    given Typeable[Pattern] with
      def unapply(s: Any): Option[s.type & Pattern] =
        s match
          case c: String => Some(c.asInstanceOf[s.type & String])
          case _         => Option.empty

  opaque type PrepareRenameResult =
    (structures.Range | PrepareRenameResult.RangeAndPlaceholder |
      PrepareRenameResult.DefaultBehavior)
  object PrepareRenameResult extends codecs.aliases_PrepareRenameResult:
    inline def apply(v: structures.Range): PrepareRenameResult = v
    inline def apply(
        v: PrepareRenameResult.RangeAndPlaceholder
    ): PrepareRenameResult = v
    inline def apply(
        v: PrepareRenameResult.DefaultBehavior
    ): PrepareRenameResult = v

    given Typeable[PrepareRenameResult] with
      def unapply(s: Any): Option[s.type & PrepareRenameResult] =
        s match
          case c: structures.Range =>
            Some(c.asInstanceOf[s.type & structures.Range])
          case c: PrepareRenameResult.RangeAndPlaceholder =>
            Some(
              c.asInstanceOf[s.type & PrepareRenameResult.RangeAndPlaceholder]
            )
          case c: PrepareRenameResult.DefaultBehavior =>
            Some(c.asInstanceOf[s.type & PrepareRenameResult.DefaultBehavior])
          case _ => Option.empty
    end given
    case class RangeAndPlaceholder(
        range: structures.Range,
        placeholder: String
    )
    object RangeAndPlaceholder
        extends codecs.aliases_PrepareRenameResult_RangeAndPlaceholderCodec
    case class DefaultBehavior(
        defaultBehavior: Boolean
    )
    object DefaultBehavior
        extends codecs.aliases_PrepareRenameResult_DefaultBehaviorCodec
  end PrepareRenameResult

  opaque type ProgressToken = (Int | String)
  object ProgressToken extends codecs.aliases_ProgressToken:
    inline def apply(v: Int): ProgressToken    = v
    inline def apply(v: String): ProgressToken = v

    given Typeable[ProgressToken] with
      def unapply(s: Any): Option[s.type & ProgressToken] =
        s match
          case c: Int    => Some(c.asInstanceOf[s.type & Int])
          case c: String => Some(c.asInstanceOf[s.type & String])
          case _         => Option.empty
  end ProgressToken

  opaque type TextDocumentContentChangeEvent =
    (TextDocumentContentChangeEvent.Partial |
      TextDocumentContentChangeEvent.Full)
  object TextDocumentContentChangeEvent
      extends codecs.aliases_TextDocumentContentChangeEvent:
    inline def apply(
        v: TextDocumentContentChangeEvent.Partial
    ): TextDocumentContentChangeEvent = v
    inline def apply(
        v: TextDocumentContentChangeEvent.Full
    ): TextDocumentContentChangeEvent = v

    given Typeable[TextDocumentContentChangeEvent] with
      def unapply(s: Any): Option[s.type & TextDocumentContentChangeEvent] =
        s match
          case c: TextDocumentContentChangeEvent.Partial =>
            Some(
              c.asInstanceOf[s.type & TextDocumentContentChangeEvent.Partial]
            )
          case c: TextDocumentContentChangeEvent.Full =>
            Some(c.asInstanceOf[s.type & TextDocumentContentChangeEvent.Full])
          case _ => Option.empty
    end given

    /** @param range
      *   The range of the document that changed.
      *
      * @param rangeLength
      *   The optional length of the range that got replaced.
      *
      * @deprecated
      *   use range instead.
      *
      * @param text
      *   The new text for the provided range.
      */
    case class Partial(
        range: structures.Range,
        rangeLength: Opt[runtime.uinteger] = Opt.empty,
        text: String
    )
    object Partial
        extends codecs.aliases_TextDocumentContentChangeEvent_PartialCodec

    /** @param text
      *   The new text of the whole document.
      */
    case class Full(
        text: String
    )
    object Full extends codecs.aliases_TextDocumentContentChangeEvent_FullCodec
  end TextDocumentContentChangeEvent

  opaque type TextDocumentFilter =
    (TextDocumentFilter.ByLanguage | TextDocumentFilter.ByScheme |
      TextDocumentFilter.ByPattern)
  object TextDocumentFilter extends codecs.aliases_TextDocumentFilter:
    inline def apply(v: TextDocumentFilter.ByLanguage): TextDocumentFilter = v
    inline def apply(v: TextDocumentFilter.ByScheme): TextDocumentFilter   = v
    inline def apply(v: TextDocumentFilter.ByPattern): TextDocumentFilter  = v

    given Typeable[TextDocumentFilter] with
      def unapply(s: Any): Option[s.type & TextDocumentFilter] =
        s match
          case c: TextDocumentFilter.ByLanguage =>
            Some(c.asInstanceOf[s.type & TextDocumentFilter.ByLanguage])
          case c: TextDocumentFilter.ByScheme =>
            Some(c.asInstanceOf[s.type & TextDocumentFilter.ByScheme])
          case c: TextDocumentFilter.ByPattern =>
            Some(c.asInstanceOf[s.type & TextDocumentFilter.ByPattern])
          case _ => Option.empty
    end given

    /** @param language
      *   A language id, like `typescript`.
      *
      * @param scheme
      *   A Uri {@link Uri.scheme scheme}, like `file` or `untitled`.
      *
      * @param pattern
      *   A glob pattern, like **​.{ts,js}. See TextDocumentFilter for examples.
      */
    case class ByLanguage(
        language: String,
        scheme: Opt[String] = Opt.empty,
        pattern: Opt[String] = Opt.empty
    )
    object ByLanguage extends codecs.aliases_TextDocumentFilter_ByLanguageCodec

    /** @param language
      *   A language id, like `typescript`.
      *
      * @param scheme
      *   A Uri {@link Uri.scheme scheme}, like `file` or `untitled`.
      *
      * @param pattern
      *   A glob pattern, like **​.{ts,js}. See TextDocumentFilter for examples.
      */
    case class ByScheme(
        language: Opt[String] = Opt.empty,
        scheme: String,
        pattern: Opt[String] = Opt.empty
    )
    object ByScheme extends codecs.aliases_TextDocumentFilter_BySchemeCodec

    /** @param language
      *   A language id, like `typescript`.
      *
      * @param scheme
      *   A Uri {@link Uri.scheme scheme}, like `file` or `untitled`.
      *
      * @param pattern
      *   A glob pattern, like **​.{ts,js}. See TextDocumentFilter for examples.
      */
    case class ByPattern(
        language: Opt[String] = Opt.empty,
        scheme: Opt[String] = Opt.empty,
        pattern: String
    )
    object ByPattern extends codecs.aliases_TextDocumentFilter_ByPatternCodec
  end TextDocumentFilter

  opaque type WorkspaceDocumentDiagnosticReport =
    (structures.WorkspaceFullDocumentDiagnosticReport |
      structures.WorkspaceUnchangedDocumentDiagnosticReport)
  object WorkspaceDocumentDiagnosticReport
      extends codecs.aliases_WorkspaceDocumentDiagnosticReport:
    inline def apply(
        v: structures.WorkspaceFullDocumentDiagnosticReport
    ): WorkspaceDocumentDiagnosticReport = v
    inline def apply(
        v: structures.WorkspaceUnchangedDocumentDiagnosticReport
    ): WorkspaceDocumentDiagnosticReport = v

    given Typeable[WorkspaceDocumentDiagnosticReport] with
      def unapply(s: Any): Option[s.type & WorkspaceDocumentDiagnosticReport] =
        s match
          case c: structures.WorkspaceFullDocumentDiagnosticReport =>
            Some(
              c.asInstanceOf[
                s.type & structures.WorkspaceFullDocumentDiagnosticReport
              ]
            )
          case c: structures.WorkspaceUnchangedDocumentDiagnosticReport =>
            Some(
              c.asInstanceOf[
                s.type & structures.WorkspaceUnchangedDocumentDiagnosticReport
              ]
            )
          case _ => Option.empty
    end given
  end WorkspaceDocumentDiagnosticReport
end aliases
