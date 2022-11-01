/*
 * Copyright 2022 Neandertech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE2.0
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
// format: off

object aliases:
  opaque type Definition = (structures.Location | Vector[structures.Location])
  object Definition extends codecs.aliases_Definition:
    inline def apply(v: structures.Location): Definition = v
    inline def apply(v: Vector[structures.Location]): Definition = v

    given Typeable[Definition] with
      def unapply(s: Any): Option[s.type & Definition] = 
        s match
        case c: structures.Location => Some(c.asInstanceOf[s.type & structures.Location])
        case c: Vector[?] => Some(c.asInstanceOf[s.type & Vector[structures.Location]])
        case _ => Option.empty

  opaque type DefinitionLink = structures.LocationLink
  object DefinitionLink extends codecs.aliases_DefinitionLink:
    inline def apply(v: structures.LocationLink): DefinitionLink = v

    given Typeable[DefinitionLink] with
      def unapply(s: Any): Option[s.type & DefinitionLink] = 
        s match
        case c: structures.LocationLink => Some(c.asInstanceOf[s.type & structures.LocationLink])
        case _ => Option.empty

  opaque type Declaration = (structures.Location | Vector[structures.Location])
  object Declaration extends codecs.aliases_Declaration:
    inline def apply(v: structures.Location): Declaration = v
    inline def apply(v: Vector[structures.Location]): Declaration = v

    given Typeable[Declaration] with
      def unapply(s: Any): Option[s.type & Declaration] = 
        s match
        case c: structures.Location => Some(c.asInstanceOf[s.type & structures.Location])
        case c: Vector[?] => Some(c.asInstanceOf[s.type & Vector[structures.Location]])
        case _ => Option.empty

  opaque type DeclarationLink = structures.LocationLink
  object DeclarationLink extends codecs.aliases_DeclarationLink:
    inline def apply(v: structures.LocationLink): DeclarationLink = v

    given Typeable[DeclarationLink] with
      def unapply(s: Any): Option[s.type & DeclarationLink] = 
        s match
        case c: structures.LocationLink => Some(c.asInstanceOf[s.type & structures.LocationLink])
        case _ => Option.empty

  opaque type InlineValue = (structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)
  object InlineValue extends codecs.aliases_InlineValue:
    inline def apply(v: structures.InlineValueText): InlineValue = v
    inline def apply(v: structures.InlineValueVariableLookup): InlineValue = v
    inline def apply(v: structures.InlineValueEvaluatableExpression): InlineValue = v

    given Typeable[InlineValue] with
      def unapply(s: Any): Option[s.type & InlineValue] = 
        s match
        case c: structures.InlineValueText => Some(c.asInstanceOf[s.type & structures.InlineValueText])
        case c: structures.InlineValueVariableLookup => Some(c.asInstanceOf[s.type & structures.InlineValueVariableLookup])
        case c: structures.InlineValueEvaluatableExpression => Some(c.asInstanceOf[s.type & structures.InlineValueEvaluatableExpression])
        case _ => Option.empty

  opaque type DocumentDiagnosticReport = (structures.RelatedFullDocumentDiagnosticReport | structures.RelatedUnchangedDocumentDiagnosticReport)
  object DocumentDiagnosticReport extends codecs.aliases_DocumentDiagnosticReport:
    inline def apply(v: structures.RelatedFullDocumentDiagnosticReport): DocumentDiagnosticReport = v
    inline def apply(v: structures.RelatedUnchangedDocumentDiagnosticReport): DocumentDiagnosticReport = v

    given Typeable[DocumentDiagnosticReport] with
      def unapply(s: Any): Option[s.type & DocumentDiagnosticReport] = 
        s match
        case c: structures.RelatedFullDocumentDiagnosticReport => Some(c.asInstanceOf[s.type & structures.RelatedFullDocumentDiagnosticReport])
        case c: structures.RelatedUnchangedDocumentDiagnosticReport => Some(c.asInstanceOf[s.type & structures.RelatedUnchangedDocumentDiagnosticReport])
        case _ => Option.empty

  opaque type PrepareRenameResult = (structures.Range | PrepareRenameResult.S0 | PrepareRenameResult.S1)
  object PrepareRenameResult extends codecs.aliases_PrepareRenameResult:
    inline def apply(v: structures.Range): PrepareRenameResult = v
    inline def apply(v: PrepareRenameResult.S0): PrepareRenameResult = v
    inline def apply(v: PrepareRenameResult.S1): PrepareRenameResult = v

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
    object S0 extends codecs.aliases_PrepareRenameResult_S0
    case class S1(
      defaultBehavior: Boolean
    )
    object S1 extends codecs.aliases_PrepareRenameResult_S1

  opaque type URI = String
  object URI extends codecs.aliases_URI:
    inline def apply(v: String): URI = v

    given Typeable[URI] with
      def unapply(s: Any): Option[s.type & URI] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty

  opaque type ProgressToken = (Int | String)
  object ProgressToken extends codecs.aliases_ProgressToken:
    inline def apply(v: Int): ProgressToken = v
    inline def apply(v: String): ProgressToken = v

    given Typeable[ProgressToken] with
      def unapply(s: Any): Option[s.type & ProgressToken] = 
        s match
        case c: Int => Some(c.asInstanceOf[s.type & Int])
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty

  opaque type DocumentSelector = Vector[(String | aliases.DocumentFilter)]
  object DocumentSelector extends codecs.aliases_DocumentSelector:
    inline def apply(v: Vector[(String | aliases.DocumentFilter)]): DocumentSelector = v

    given Typeable[DocumentSelector] with
      def unapply(s: Any): Option[s.type & DocumentSelector] = 
        s match
        case c: Vector[?] => Some(c.asInstanceOf[s.type & Vector[(String | aliases.DocumentFilter)]])
        case _ => Option.empty

  opaque type ChangeAnnotationIdentifier = String
  object ChangeAnnotationIdentifier extends codecs.aliases_ChangeAnnotationIdentifier:
    inline def apply(v: String): ChangeAnnotationIdentifier = v

    given Typeable[ChangeAnnotationIdentifier] with
      def unapply(s: Any): Option[s.type & ChangeAnnotationIdentifier] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty

  opaque type WorkspaceDocumentDiagnosticReport = (structures.WorkspaceFullDocumentDiagnosticReport | structures.WorkspaceUnchangedDocumentDiagnosticReport)
  object WorkspaceDocumentDiagnosticReport extends codecs.aliases_WorkspaceDocumentDiagnosticReport:
    inline def apply(v: structures.WorkspaceFullDocumentDiagnosticReport): WorkspaceDocumentDiagnosticReport = v
    inline def apply(v: structures.WorkspaceUnchangedDocumentDiagnosticReport): WorkspaceDocumentDiagnosticReport = v

    given Typeable[WorkspaceDocumentDiagnosticReport] with
      def unapply(s: Any): Option[s.type & WorkspaceDocumentDiagnosticReport] = 
        s match
        case c: structures.WorkspaceFullDocumentDiagnosticReport => Some(c.asInstanceOf[s.type & structures.WorkspaceFullDocumentDiagnosticReport])
        case c: structures.WorkspaceUnchangedDocumentDiagnosticReport => Some(c.asInstanceOf[s.type & structures.WorkspaceUnchangedDocumentDiagnosticReport])
        case _ => Option.empty

  opaque type TextDocumentContentChangeEvent = (TextDocumentContentChangeEvent.S0 | TextDocumentContentChangeEvent.S1)
  object TextDocumentContentChangeEvent extends codecs.aliases_TextDocumentContentChangeEvent:
    inline def apply(v: TextDocumentContentChangeEvent.S0): TextDocumentContentChangeEvent = v
    inline def apply(v: TextDocumentContentChangeEvent.S1): TextDocumentContentChangeEvent = v

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
      rangeLength: Opt[runtime.uinteger] = Opt.empty,
      text: String
    )
    object S0 extends codecs.aliases_TextDocumentContentChangeEvent_S0
    /**
     *  @param text
     *    The new text of the whole document.
    
     */
    case class S1(
      text: String
    )
    object S1 extends codecs.aliases_TextDocumentContentChangeEvent_S1

  opaque type MarkedString = (String | MarkedString.S0)
  object MarkedString extends codecs.aliases_MarkedString:
    inline def apply(v: String): MarkedString = v
    inline def apply(v: MarkedString.S0): MarkedString = v

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
    object S0 extends codecs.aliases_MarkedString_S0

  opaque type DocumentFilter = (aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)
  object DocumentFilter extends codecs.aliases_DocumentFilter:
    inline def apply(v: aliases.TextDocumentFilter): DocumentFilter = v
    inline def apply(v: structures.NotebookCellTextDocumentFilter): DocumentFilter = v

    given Typeable[DocumentFilter] with
      def unapply(s: Any): Option[s.type & DocumentFilter] = 
        s match
        case c: aliases.TextDocumentFilter => Some(c.asInstanceOf[s.type & aliases.TextDocumentFilter])
        case c: structures.NotebookCellTextDocumentFilter => Some(c.asInstanceOf[s.type & structures.NotebookCellTextDocumentFilter])
        case _ => Option.empty

  opaque type GlobPattern = (aliases.Pattern | structures.RelativePattern)
  object GlobPattern extends codecs.aliases_GlobPattern:
    inline def apply(v: aliases.Pattern): GlobPattern = v
    inline def apply(v: structures.RelativePattern): GlobPattern = v

    given Typeable[GlobPattern] with
      def unapply(s: Any): Option[s.type & GlobPattern] = 
        s match
        case c: aliases.Pattern => Some(c.asInstanceOf[s.type & aliases.Pattern])
        case c: structures.RelativePattern => Some(c.asInstanceOf[s.type & structures.RelativePattern])
        case _ => Option.empty

  opaque type TextDocumentFilter = (TextDocumentFilter.S0 | TextDocumentFilter.S1 | TextDocumentFilter.S2)
  object TextDocumentFilter extends codecs.aliases_TextDocumentFilter:
    inline def apply(v: TextDocumentFilter.S0): TextDocumentFilter = v
    inline def apply(v: TextDocumentFilter.S1): TextDocumentFilter = v
    inline def apply(v: TextDocumentFilter.S2): TextDocumentFilter = v

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
    object S0 extends codecs.aliases_TextDocumentFilter_S0
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
    object S1 extends codecs.aliases_TextDocumentFilter_S1
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
    object S2 extends codecs.aliases_TextDocumentFilter_S2

  opaque type NotebookDocumentFilter = (NotebookDocumentFilter.S0 | NotebookDocumentFilter.S1 | NotebookDocumentFilter.S2)
  object NotebookDocumentFilter extends codecs.aliases_NotebookDocumentFilter:
    inline def apply(v: NotebookDocumentFilter.S0): NotebookDocumentFilter = v
    inline def apply(v: NotebookDocumentFilter.S1): NotebookDocumentFilter = v
    inline def apply(v: NotebookDocumentFilter.S2): NotebookDocumentFilter = v

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
    object S0 extends codecs.aliases_NotebookDocumentFilter_S0
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
    object S1 extends codecs.aliases_NotebookDocumentFilter_S1
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
    object S2 extends codecs.aliases_NotebookDocumentFilter_S2

  opaque type Pattern = String
  object Pattern extends codecs.aliases_Pattern:
    inline def apply(v: String): Pattern = v

    given Typeable[Pattern] with
      def unapply(s: Any): Option[s.type & Pattern] = 
        s match
        case c: String => Some(c.asInstanceOf[s.type & String])
        case _ => Option.empty

end aliases
