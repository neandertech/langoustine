package langoustine.lsp

import langoustine.*

object aliases: 
  type Definition = (structures.Location | Vector[structures.Location])
  type DefinitionLink = structures.LocationLink
  type LSPArray = Vector[aliases.LSPAny]
  type LSPAny = (structures.LSPObject | aliases.LSPArray | String | Int | RuntimeBase.uinteger | Float | Boolean | Null)
  type Declaration = (structures.Location | Vector[structures.Location])
  type DeclarationLink = structures.LocationLink
  type InlineValue = (structures.InlineValueText | structures.InlineValueVariableLookup | structures.InlineValueEvaluatableExpression)
  type DocumentDiagnosticReport = (structures.RelatedFullDocumentDiagnosticReport | structures.RelatedUnchangedDocumentDiagnosticReport)
  type PrepareRenameResult = (structures.Range | Any /*StructureLiteralType(literal,StructureLiteral(Vector(Property(range,false,ReferenceType(reference,Range)), Property(placeholder,false,BaseType(base,string)))))*/ | Any /*StructureLiteralType(literal,StructureLiteral(Vector(Property(defaultBehavior,false,BaseType(base,boolean)))))*/)
  type URI = String
  type ProgressToken = (Int | String)
  type DocumentSelector = Vector[(String | aliases.DocumentFilter)]
  type ChangeAnnotationIdentifier = String
  type WorkspaceDocumentDiagnosticReport = (structures.WorkspaceFullDocumentDiagnosticReport | structures.WorkspaceUnchangedDocumentDiagnosticReport)
  type TextDocumentContentChangeEvent = (Any /*StructureLiteralType(literal,StructureLiteral(Vector(Property(range,false,ReferenceType(reference,Range)), Property(rangeLength,true,BaseType(base,uinteger)), Property(text,false,BaseType(base,string)))))*/ | Any /*StructureLiteralType(literal,StructureLiteral(Vector(Property(text,false,BaseType(base,string)))))*/)
  type MarkedString = (String | Any /*StructureLiteralType(literal,StructureLiteral(Vector(Property(language,false,BaseType(base,string)), Property(value,false,BaseType(base,string)))))*/)
  type DocumentFilter = (aliases.TextDocumentFilter | structures.NotebookCellTextDocumentFilter)
  type GlobPattern = (aliases.Pattern | structures.RelativePattern)
  type TextDocumentFilter = (Any /*StructureLiteralType(literal,StructureLiteral(Vector(Property(language,false,BaseType(base,string)), Property(scheme,true,BaseType(base,string)), Property(pattern,true,BaseType(base,string)))))*/ | Any /*StructureLiteralType(literal,StructureLiteral(Vector(Property(language,true,BaseType(base,string)), Property(scheme,false,BaseType(base,string)), Property(pattern,true,BaseType(base,string)))))*/ | Any /*StructureLiteralType(literal,StructureLiteral(Vector(Property(language,true,BaseType(base,string)), Property(scheme,true,BaseType(base,string)), Property(pattern,false,BaseType(base,string)))))*/)
  type NotebookDocumentFilter = (Any /*StructureLiteralType(literal,StructureLiteral(Vector(Property(notebookType,false,BaseType(base,string)), Property(scheme,true,BaseType(base,string)), Property(pattern,true,BaseType(base,string)))))*/ | Any /*StructureLiteralType(literal,StructureLiteral(Vector(Property(notebookType,true,BaseType(base,string)), Property(scheme,false,BaseType(base,string)), Property(pattern,true,BaseType(base,string)))))*/ | Any /*StructureLiteralType(literal,StructureLiteral(Vector(Property(notebookType,true,BaseType(base,string)), Property(scheme,true,BaseType(base,string)), Property(pattern,false,BaseType(base,string)))))*/)
  type Pattern = String
