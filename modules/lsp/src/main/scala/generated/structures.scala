package langoustine.lsp
package structures

import langoustine.*
import upickle.default.*
import langoustine.lsp.json.{*, given}
// format: off

/**
 *  @param textDocument
 *    The text document.

 *  @param position
 *    The position inside the text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class ImplementationParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object ImplementationParams:
  given reader: Reader[structures.ImplementationParams] = Pickle.macroR
  given writer: Writer[structures.ImplementationParams] = upickle.default.macroW

/**
 *  Represents a location inside a resource, such as a line
 *  inside a text file.

 *  @param uri
 *  @param range
 */
case class Location(
  uri: RuntimeBase.DocumentUri,
  range: structures.Range
)
object Location:
  given reader: Reader[structures.Location] = Pickle.macroR
  given writer: Writer[structures.Location] = upickle.default.macroW

/**
 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class ImplementationRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object ImplementationRegistrationOptions:
  given reader: Reader[structures.ImplementationRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.ImplementationRegistrationOptions] = upickle.default.macroW

/**
 *  @param textDocument
 *    The text document.

 *  @param position
 *    The position inside the text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class TypeDefinitionParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object TypeDefinitionParams:
  given reader: Reader[structures.TypeDefinitionParams] = Pickle.macroR
  given writer: Writer[structures.TypeDefinitionParams] = upickle.default.macroW

/**
 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class TypeDefinitionRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object TypeDefinitionRegistrationOptions:
  given reader: Reader[structures.TypeDefinitionRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.TypeDefinitionRegistrationOptions] = upickle.default.macroW

/**
 *  A workspace folder inside a client.

 *  @param uri
 *    The associated URI for this workspace folder.

 *  @param name
 *    The name of the workspace folder. Used to refer to this
 *    workspace folder in the user interface.

 */
case class WorkspaceFolder(
  uri: aliases.URI,
  name: String
)
object WorkspaceFolder:
  given reader: Reader[structures.WorkspaceFolder] = Pickle.macroR
  given writer: Writer[structures.WorkspaceFolder] = upickle.default.macroW

/**
 *  The parameters of a `workspace/didChangeWorkspaceFolders` notification.

 *  @param event
 *    The actual workspace folder change event.

 */
case class DidChangeWorkspaceFoldersParams(
  event: structures.WorkspaceFoldersChangeEvent
)
object DidChangeWorkspaceFoldersParams:
  given reader: Reader[structures.DidChangeWorkspaceFoldersParams] = Pickle.macroR
  given writer: Writer[structures.DidChangeWorkspaceFoldersParams] = upickle.default.macroW

/**
 *  The parameters of a configuration request.

 *  @param items
 */
case class ConfigurationParams(
  items: Vector[structures.ConfigurationItem]
)
object ConfigurationParams:
  given reader: Reader[structures.ConfigurationParams] = Pickle.macroR
  given writer: Writer[structures.ConfigurationParams] = upickle.default.macroW

/**
 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class PartialResultParams(
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object PartialResultParams:
  given reader: Reader[structures.PartialResultParams] = Pickle.macroR
  given writer: Writer[structures.PartialResultParams] = upickle.default.macroW

/**
 *  Parameters for a [DocumentColorRequest](#DocumentColorRequest).

 *  @param textDocument
 *    The text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class DocumentColorParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object DocumentColorParams:
  given reader: Reader[structures.DocumentColorParams] = Pickle.macroR
  given writer: Writer[structures.DocumentColorParams] = upickle.default.macroW

/**
 *  Represents a color range from a document.

 *  @param range
 *    The range in the document where this color appears.

 *  @param color
 *    The actual color value for this color range.

 */
case class ColorInformation(
  range: structures.Range,
  color: structures.Color
)
object ColorInformation:
  given reader: Reader[structures.ColorInformation] = Pickle.macroR
  given writer: Writer[structures.ColorInformation] = upickle.default.macroW

/**
 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class DocumentColorRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object DocumentColorRegistrationOptions:
  given reader: Reader[structures.DocumentColorRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentColorRegistrationOptions] = upickle.default.macroW

/**
 *  Parameters for a [ColorPresentationRequest](#ColorPresentationRequest).

 *  @param textDocument
 *    The text document.

 *  @param color
 *    The color to request presentations for.

 *  @param range
 *    The range where the color would be inserted. Serves as a context.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class ColorPresentationParams(
  textDocument: structures.TextDocumentIdentifier,
  color: structures.Color,
  range: structures.Range,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object ColorPresentationParams:
  given reader: Reader[structures.ColorPresentationParams] = Pickle.macroR
  given writer: Writer[structures.ColorPresentationParams] = upickle.default.macroW

/**
 *  @param label
 *    The label of this color presentation. It will be shown on the color
 *    picker header. By default this is also the text that is inserted when selecting
 *    this color presentation.

 *  @param textEdit
 *    An [edit](#TextEdit) which is applied to a document when selecting
 *    this presentation for the color.  When `falsy` the [label](#ColorPresentation.label)
 *    is used.

 *  @param additionalTextEdits
 *    An optional array of additional [text edits](#TextEdit) that are applied when
 *    selecting this color presentation. Edits must not overlap with the main [edit](#ColorPresentation.textEdit) nor with themselves.

 */
case class ColorPresentation(
  label: String,
  textEdit: Opt[structures.TextEdit] = Opt.empty,
  additionalTextEdits: Opt[Vector[structures.TextEdit]] = Opt.empty
)
object ColorPresentation:
  given reader: Reader[structures.ColorPresentation] = Pickle.macroR
  given writer: Writer[structures.ColorPresentation] = upickle.default.macroW

case class WorkDoneProgressOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object WorkDoneProgressOptions:
  given reader: Reader[structures.WorkDoneProgressOptions] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressOptions] = upickle.default.macroW

/**
 *  General text document registration options.

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 */
case class TextDocumentRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector]
)
object TextDocumentRegistrationOptions:
  given reader: Reader[structures.TextDocumentRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.TextDocumentRegistrationOptions] = upickle.default.macroW

/**
 *  Parameters for a [FoldingRangeRequest](#FoldingRangeRequest).

 *  @param textDocument
 *    The text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class FoldingRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object FoldingRangeParams:
  given reader: Reader[structures.FoldingRangeParams] = Pickle.macroR
  given writer: Writer[structures.FoldingRangeParams] = upickle.default.macroW

/**
 *  Represents a folding range. To be valid, start and end line must be bigger than zero and smaller
 *  than the number of lines in the document. Clients are free to ignore invalid ranges.

 *  @param startLine
 *    The zero-based start line of the range to fold. The folded area starts after the line's last character.
 *    To be valid, the end must be zero or larger and smaller than the number of lines in the document.

 *  @param startCharacter
 *    The zero-based character offset from where the folded range starts. If not defined, defaults to the length of the start line.

 *  @param endLine
 *    The zero-based end line of the range to fold. The folded area ends with the line's last character.
 *    To be valid, the end must be zero or larger and smaller than the number of lines in the document.

 *  @param endCharacter
 *    The zero-based character offset before the folded range ends. If not defined, defaults to the length of the end line.

 *  @param kind
 *    Describes the kind of the folding range such as `comment' or 'region'. The kind
 *    is used to categorize folding ranges and used by commands like 'Fold all comments'.
 *    See [FoldingRangeKind](#FoldingRangeKind) for an enumeration of standardized kinds.

 *  @param collapsedText
 *    The text that the client should show when the specified range is
 *    collapsed. If not defined or not supported by the client, a default
 *    will be chosen by the client.
 *    
 *    @since 3.17.0

 */
case class FoldingRange(
  startLine: RuntimeBase.uinteger,
  startCharacter: Opt[RuntimeBase.uinteger] = Opt.empty,
  endLine: RuntimeBase.uinteger,
  endCharacter: Opt[RuntimeBase.uinteger] = Opt.empty,
  kind: Opt[enumerations.FoldingRangeKind] = Opt.empty,
  collapsedText: Opt[String] = Opt.empty
)
object FoldingRange:
  given reader: Reader[structures.FoldingRange] = Pickle.macroR
  given writer: Writer[structures.FoldingRange] = upickle.default.macroW

/**
 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class FoldingRangeRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object FoldingRangeRegistrationOptions:
  given reader: Reader[structures.FoldingRangeRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.FoldingRangeRegistrationOptions] = upickle.default.macroW

/**
 *  @param textDocument
 *    The text document.

 *  @param position
 *    The position inside the text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class DeclarationParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object DeclarationParams:
  given reader: Reader[structures.DeclarationParams] = Pickle.macroR
  given writer: Writer[structures.DeclarationParams] = upickle.default.macroW

/**
 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class DeclarationRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object DeclarationRegistrationOptions:
  given reader: Reader[structures.DeclarationRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DeclarationRegistrationOptions] = upickle.default.macroW

/**
 *  A parameter literal used in selection range requests.

 *  @param textDocument
 *    The text document.

 *  @param positions
 *    The positions inside the text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class SelectionRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  positions: Vector[structures.Position],
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object SelectionRangeParams:
  given reader: Reader[structures.SelectionRangeParams] = Pickle.macroR
  given writer: Writer[structures.SelectionRangeParams] = upickle.default.macroW

/**
 *  A selection range represents a part of a selection hierarchy. A selection range
 *  may have a parent selection range that contains it.

 *  @param range
 *    The [range](#Range) of this selection range.

 *  @param parent
 *    The parent selection range containing this range. Therefore `parent.range` must contain `this.range`.

 */
case class SelectionRange(
  range: structures.Range,
  parent: Opt[structures.SelectionRange] = Opt.empty
)
object SelectionRange:
  given reader: Reader[structures.SelectionRange] = Pickle.macroR
  given writer: Writer[structures.SelectionRange] = upickle.default.macroW

/**
 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class SelectionRangeRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object SelectionRangeRegistrationOptions:
  given reader: Reader[structures.SelectionRangeRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.SelectionRangeRegistrationOptions] = upickle.default.macroW

/**
 *  @param token
 *    The token to be used to report progress.

 */
case class WorkDoneProgressCreateParams(
  token: aliases.ProgressToken
)
object WorkDoneProgressCreateParams:
  given reader: Reader[structures.WorkDoneProgressCreateParams] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressCreateParams] = upickle.default.macroW

/**
 *  @param token
 *    The token to be used to report progress.

 */
case class WorkDoneProgressCancelParams(
  token: aliases.ProgressToken
)
object WorkDoneProgressCancelParams:
  given reader: Reader[structures.WorkDoneProgressCancelParams] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressCancelParams] = upickle.default.macroW

/**
 *  The parameter of a `textDocument/prepareCallHierarchy` request.
 *  
 *  @since 3.16.0

 *  @param textDocument
 *    The text document.

 *  @param position
 *    The position inside the text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 */
case class CallHierarchyPrepareParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object CallHierarchyPrepareParams:
  given reader: Reader[structures.CallHierarchyPrepareParams] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyPrepareParams] = upickle.default.macroW

/**
 *  Represents programming constructs like functions or constructors in the context
 *  of call hierarchy.
 *  
 *  @since 3.16.0

 *  @param name
 *    The name of this item.

 *  @param kind
 *    The kind of this item.

 *  @param tags
 *    Tags for this item.

 *  @param detail
 *    More detail for this item, e.g. the signature of a function.

 *  @param uri
 *    The resource identifier of this item.

 *  @param range
 *    The range enclosing this symbol not including leading/trailing whitespace but everything else, e.g. comments and code.

 *  @param selectionRange
 *    The range that should be selected and revealed when this symbol is being picked, e.g. the name of a function.
 *    Must be contained by the [`range`](#CallHierarchyItem.range).

 *  @param data
 *    A data entry field that is preserved between a call hierarchy prepare and
 *    incoming calls or outgoing calls requests.

 */
case class CallHierarchyItem(
  name: String,
  kind: enumerations.SymbolKind,
  tags: Opt[Vector[enumerations.SymbolTag]] = Opt.empty,
  detail: Opt[String] = Opt.empty,
  uri: RuntimeBase.DocumentUri,
  range: structures.Range,
  selectionRange: structures.Range,
  data: Opt[ujson.Value] = Opt.empty
)
object CallHierarchyItem:
  given reader: Reader[structures.CallHierarchyItem] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyItem] = upickle.default.macroW

/**
 *  Call hierarchy options used during static or dynamic registration.
 *  
 *  @since 3.16.0

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class CallHierarchyRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object CallHierarchyRegistrationOptions:
  given reader: Reader[structures.CallHierarchyRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyRegistrationOptions] = upickle.default.macroW

/**
 *  The parameter of a `callHierarchy/incomingCalls` request.
 *  
 *  @since 3.16.0

 *  @param item
 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class CallHierarchyIncomingCallsParams(
  item: structures.CallHierarchyItem,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object CallHierarchyIncomingCallsParams:
  given reader: Reader[structures.CallHierarchyIncomingCallsParams] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyIncomingCallsParams] = upickle.default.macroW

/**
 *  Represents an incoming call, e.g. a caller of a method or constructor.
 *  
 *  @since 3.16.0

 *  @param from
 *    The item that makes the call.

 *  @param fromRanges
 *    The ranges at which the calls appear. This is relative to the caller
 *    denoted by [`this.from`](#CallHierarchyIncomingCall.from).

 */
case class CallHierarchyIncomingCall(
  from: structures.CallHierarchyItem,
  fromRanges: Vector[structures.Range]
)
object CallHierarchyIncomingCall:
  given reader: Reader[structures.CallHierarchyIncomingCall] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyIncomingCall] = upickle.default.macroW

/**
 *  The parameter of a `callHierarchy/outgoingCalls` request.
 *  
 *  @since 3.16.0

 *  @param item
 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class CallHierarchyOutgoingCallsParams(
  item: structures.CallHierarchyItem,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object CallHierarchyOutgoingCallsParams:
  given reader: Reader[structures.CallHierarchyOutgoingCallsParams] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyOutgoingCallsParams] = upickle.default.macroW

/**
 *  Represents an outgoing call, e.g. calling a getter from a method or a method from a constructor etc.
 *  
 *  @since 3.16.0

 *  @param to
 *    The item that is called.

 *  @param fromRanges
 *    The range at which this item is called. This is the range relative to the caller, e.g the item
 *    passed to [`provideCallHierarchyOutgoingCalls`](#CallHierarchyItemProvider.provideCallHierarchyOutgoingCalls)
 *    and not [`this.to`](#CallHierarchyOutgoingCall.to).

 */
case class CallHierarchyOutgoingCall(
  to: structures.CallHierarchyItem,
  fromRanges: Vector[structures.Range]
)
object CallHierarchyOutgoingCall:
  given reader: Reader[structures.CallHierarchyOutgoingCall] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyOutgoingCall] = upickle.default.macroW

/**
 *  @since 3.16.0

 *  @param textDocument
 *    The text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class SemanticTokensParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object SemanticTokensParams:
  given reader: Reader[structures.SemanticTokensParams] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensParams] = upickle.default.macroW

/**
 *  @since 3.16.0

 *  @param resultId
 *    An optional result id. If provided and clients support delta updating
 *    the client will include the result id in the next semantic token request.
 *    A server can then instead of computing all semantic tokens again simply
 *    send a delta.

 *  @param data
 *    The actual tokens.

 */
case class SemanticTokens(
  resultId: Opt[String] = Opt.empty,
  data: Vector[RuntimeBase.uinteger]
)
object SemanticTokens:
  given reader: Reader[structures.SemanticTokens] = Pickle.macroR
  given writer: Writer[structures.SemanticTokens] = upickle.default.macroW

/**
 *  @since 3.16.0

 *  @param data
 */
case class SemanticTokensPartialResult(
  data: Vector[RuntimeBase.uinteger]
)
object SemanticTokensPartialResult:
  given reader: Reader[structures.SemanticTokensPartialResult] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensPartialResult] = upickle.default.macroW

/**
 *  @since 3.16.0

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param legend
 *    The legend used by the server

 *  @param range
 *    Server supports providing semantic tokens for a specific range
 *    of a document.

 *  @param full
 *    Server supports providing semantic tokens for a full document.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class SemanticTokensRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  legend: structures.SemanticTokensLegend,
  range: Opt[(Boolean | SemanticTokensRegistrationOptions.S0)] = Opt.empty,
  full: Opt[(Boolean | SemanticTokensRegistrationOptions.S1)] = Opt.empty,
  id: Opt[String] = Opt.empty
)
object SemanticTokensRegistrationOptions:
  private given rd1: Reader[(Boolean | SemanticTokensRegistrationOptions.S0)] = 
    badMerge[(Boolean | SemanticTokensRegistrationOptions.S0)](upickle.default.reader[Boolean], SemanticTokensRegistrationOptions.S0.reader)
  private given wt1: Writer[(Boolean | SemanticTokensRegistrationOptions.S0)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | SemanticTokensRegistrationOptions.S0)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: SemanticTokensRegistrationOptions.S0 => writeJs[SemanticTokensRegistrationOptions.S0](v)
    }
  private given rd2: Reader[(Boolean | SemanticTokensRegistrationOptions.S1)] = 
    badMerge[(Boolean | SemanticTokensRegistrationOptions.S1)](upickle.default.reader[Boolean], SemanticTokensRegistrationOptions.S1.reader)
  private given wt2: Writer[(Boolean | SemanticTokensRegistrationOptions.S1)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | SemanticTokensRegistrationOptions.S1)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: SemanticTokensRegistrationOptions.S1 => writeJs[SemanticTokensRegistrationOptions.S1](v)
    }
  given reader: Reader[structures.SemanticTokensRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensRegistrationOptions] = upickle.default.macroW
  case class S0(
  )
  object S0:
    given reader: Reader[structures.SemanticTokensRegistrationOptions.S0] = Pickle.macroR
    given writer: Writer[structures.SemanticTokensRegistrationOptions.S0] = upickle.default.macroW
  /**
   *  @param delta
   *    The server supports deltas for full documents.
  
   */
  case class S1(
    delta: Opt[Boolean] = Opt.empty
  )
  object S1:
    given reader: Reader[structures.SemanticTokensRegistrationOptions.S1] = Pickle.macroR
    given writer: Writer[structures.SemanticTokensRegistrationOptions.S1] = upickle.default.macroW

/**
 *  @since 3.16.0

 *  @param textDocument
 *    The text document.

 *  @param previousResultId
 *    The result id of a previous response. The result Id can either point to a full response
 *    or a delta response depending on what was received last.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class SemanticTokensDeltaParams(
  textDocument: structures.TextDocumentIdentifier,
  previousResultId: String,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object SemanticTokensDeltaParams:
  given reader: Reader[structures.SemanticTokensDeltaParams] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensDeltaParams] = upickle.default.macroW

/**
 *  @since 3.16.0

 *  @param resultId
 *  @param edits
 *    The semantic token edits to transform a previous result into a new result.

 */
case class SemanticTokensDelta(
  resultId: Opt[String] = Opt.empty,
  edits: Vector[structures.SemanticTokensEdit]
)
object SemanticTokensDelta:
  given reader: Reader[structures.SemanticTokensDelta] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensDelta] = upickle.default.macroW

/**
 *  @since 3.16.0

 *  @param edits
 */
case class SemanticTokensDeltaPartialResult(
  edits: Vector[structures.SemanticTokensEdit]
)
object SemanticTokensDeltaPartialResult:
  given reader: Reader[structures.SemanticTokensDeltaPartialResult] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensDeltaPartialResult] = upickle.default.macroW

/**
 *  @since 3.16.0

 *  @param textDocument
 *    The text document.

 *  @param range
 *    The range the semantic tokens are requested for.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class SemanticTokensRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object SemanticTokensRangeParams:
  given reader: Reader[structures.SemanticTokensRangeParams] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensRangeParams] = upickle.default.macroW

/**
 *  Params to show a document.
 *  
 *  @since 3.16.0

 *  @param uri
 *    The document uri to show.

 *  @param external
 *    Indicates to show the resource in an external program.
 *    To show for example `https://code.visualstudio.com/`
 *    in the default WEB browser set `external` to `true`.

 *  @param takeFocus
 *    An optional property to indicate whether the editor
 *    showing the document should take focus or not.
 *    Clients might ignore this property if an external
 *    program is started.

 *  @param selection
 *    An optional selection range if the document is a text
 *    document. Clients might ignore the property if an
 *    external program is started or the file is not a text
 *    file.

 */
case class ShowDocumentParams(
  uri: aliases.URI,
  external: Opt[Boolean] = Opt.empty,
  takeFocus: Opt[Boolean] = Opt.empty,
  selection: Opt[structures.Range] = Opt.empty
)
object ShowDocumentParams:
  given reader: Reader[structures.ShowDocumentParams] = Pickle.macroR
  given writer: Writer[structures.ShowDocumentParams] = upickle.default.macroW

/**
 *  The result of a showDocument request.
 *  
 *  @since 3.16.0

 *  @param success
 *    A boolean indicating if the show was successful.

 */
case class ShowDocumentResult(
  success: Boolean
)
object ShowDocumentResult:
  given reader: Reader[structures.ShowDocumentResult] = Pickle.macroR
  given writer: Writer[structures.ShowDocumentResult] = upickle.default.macroW

/**
 *  @param textDocument
 *    The text document.

 *  @param position
 *    The position inside the text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 */
case class LinkedEditingRangeParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object LinkedEditingRangeParams:
  given reader: Reader[structures.LinkedEditingRangeParams] = Pickle.macroR
  given writer: Writer[structures.LinkedEditingRangeParams] = upickle.default.macroW

/**
 *  The result of a linked editing range request.
 *  
 *  @since 3.16.0

 *  @param ranges
 *    A list of ranges that can be edited together. The ranges must have
 *    identical length and contain identical text content. The ranges cannot overlap.

 *  @param wordPattern
 *    An optional word pattern (regular expression) that describes valid contents for
 *    the given ranges. If no pattern is provided, the client configuration's word
 *    pattern will be used.

 */
case class LinkedEditingRanges(
  ranges: Vector[structures.Range],
  wordPattern: Opt[String] = Opt.empty
)
object LinkedEditingRanges:
  given reader: Reader[structures.LinkedEditingRanges] = Pickle.macroR
  given writer: Writer[structures.LinkedEditingRanges] = upickle.default.macroW

/**
 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class LinkedEditingRangeRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object LinkedEditingRangeRegistrationOptions:
  given reader: Reader[structures.LinkedEditingRangeRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.LinkedEditingRangeRegistrationOptions] = upickle.default.macroW

/**
 *  The parameters sent in notifications/requests for user-initiated creation of
 *  files.
 *  
 *  @since 3.16.0

 *  @param files
 *    An array of all files/folders created in this operation.

 */
case class CreateFilesParams(
  files: Vector[structures.FileCreate]
)
object CreateFilesParams:
  given reader: Reader[structures.CreateFilesParams] = Pickle.macroR
  given writer: Writer[structures.CreateFilesParams] = upickle.default.macroW

/**
 *  A workspace edit represents changes to many resources managed in the workspace. The edit
 *  should either provide `changes` or `documentChanges`. If documentChanges are present
 *  they are preferred over `changes` if the client can handle versioned document edits.
 *  
 *  Since version 3.13.0 a workspace edit can contain resource operations as well. If resource
 *  operations are present clients need to execute the operations in the order in which they
 *  are provided. So a workspace edit for example can consist of the following two changes:
 *  (1) a create file a.txt and (2) a text document edit which insert text into file a.txt.
 *  
 *  An invalid sequence (e.g. (1) delete file a.txt and (2) insert text into file a.txt) will
 *  cause failure of the operation. How the client recovers from the failure is described by
 *  the client capability: `workspace.workspaceEdit.failureHandling`

 *  @param changes
 *    Holds changes to existing resources.

 *  @param documentChanges
 *    Depending on the client capability `workspace.workspaceEdit.resourceOperations` document changes
 *    are either an array of `TextDocumentEdit`s to express changes to n different text documents
 *    where each text document edit addresses a specific version of a text document. Or it can contain
 *    above `TextDocumentEdit`s mixed with create, rename and delete file / folder operations.
 *    
 *    Whether a client supports versioned document edits is expressed via
 *    `workspace.workspaceEdit.documentChanges` client capability.
 *    
 *    If a client neither supports `documentChanges` nor `workspace.workspaceEdit.resourceOperations` then
 *    only plain `TextEdit`s using the `changes` property are supported.

 *  @param changeAnnotations
 *    A map of change annotations that can be referenced in `AnnotatedTextEdit`s or create, rename and
 *    delete file / folder operations.
 *    
 *    Whether clients honor this property depends on the client capability `workspace.changeAnnotationSupport`.
 *    
 *    @since 3.16.0

 */
case class WorkspaceEdit(
  changes: Opt[Map[RuntimeBase.DocumentUri, Vector[structures.TextEdit]]] = Opt.empty,
  documentChanges: Opt[Vector[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)]] = Opt.empty,
  changeAnnotations: Opt[Map[aliases.ChangeAnnotationIdentifier, structures.ChangeAnnotation]] = Opt.empty
)
object WorkspaceEdit:
  private given rd0: Reader[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)] = 
    badMerge[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)](structures.TextDocumentEdit.reader, structures.CreateFile.reader, structures.RenameFile.reader, structures.DeleteFile.reader)
  private given wt0: Writer[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)] = 
    upickle.default.writer[ujson.Value].comap[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)] { _v => 
      (_v: @unchecked) match 
        case v: structures.TextDocumentEdit => writeJs[structures.TextDocumentEdit](v)
        case v: structures.CreateFile => writeJs[structures.CreateFile](v)
        case v: structures.RenameFile => writeJs[structures.RenameFile](v)
        case v: structures.DeleteFile => writeJs[structures.DeleteFile](v)
    }
  given reader: Reader[structures.WorkspaceEdit] = Pickle.macroR
  given writer: Writer[structures.WorkspaceEdit] = upickle.default.macroW

/**
 *  The options to register for file operations.
 *  
 *  @since 3.16.0

 *  @param filters
 *    The actual filters.

 */
case class FileOperationRegistrationOptions(
  filters: Vector[structures.FileOperationFilter]
)
object FileOperationRegistrationOptions:
  given reader: Reader[structures.FileOperationRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.FileOperationRegistrationOptions] = upickle.default.macroW

/**
 *  The parameters sent in notifications/requests for user-initiated renames of
 *  files.
 *  
 *  @since 3.16.0

 *  @param files
 *    An array of all files/folders renamed in this operation. When a folder is renamed, only
 *    the folder will be included, and not its children.

 */
case class RenameFilesParams(
  files: Vector[structures.FileRename]
)
object RenameFilesParams:
  given reader: Reader[structures.RenameFilesParams] = Pickle.macroR
  given writer: Writer[structures.RenameFilesParams] = upickle.default.macroW

/**
 *  The parameters sent in notifications/requests for user-initiated deletes of
 *  files.
 *  
 *  @since 3.16.0

 *  @param files
 *    An array of all files/folders deleted in this operation.

 */
case class DeleteFilesParams(
  files: Vector[structures.FileDelete]
)
object DeleteFilesParams:
  given reader: Reader[structures.DeleteFilesParams] = Pickle.macroR
  given writer: Writer[structures.DeleteFilesParams] = upickle.default.macroW

/**
 *  @param textDocument
 *    The text document.

 *  @param position
 *    The position inside the text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class MonikerParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object MonikerParams:
  given reader: Reader[structures.MonikerParams] = Pickle.macroR
  given writer: Writer[structures.MonikerParams] = upickle.default.macroW

/**
 *  Moniker definition to match LSIF 0.5 moniker definition.
 *  
 *  @since 3.16.0

 *  @param scheme
 *    The scheme of the moniker. For example tsc or .Net

 *  @param identifier
 *    The identifier of the moniker. The value is opaque in LSIF however
 *    schema owners are allowed to define the structure if they want.

 *  @param unique
 *    The scope in which the moniker is unique

 *  @param kind
 *    The moniker kind if known.

 */
case class Moniker(
  scheme: String,
  identifier: String,
  unique: enumerations.UniquenessLevel,
  kind: Opt[enumerations.MonikerKind] = Opt.empty
)
object Moniker:
  given reader: Reader[structures.Moniker] = Pickle.macroR
  given writer: Writer[structures.Moniker] = upickle.default.macroW

/**
 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 */
case class MonikerRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector]
)
object MonikerRegistrationOptions:
  given reader: Reader[structures.MonikerRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.MonikerRegistrationOptions] = upickle.default.macroW

/**
 *  The parameter of a `textDocument/prepareTypeHierarchy` request.
 *  
 *  @since 3.17.0

 *  @param textDocument
 *    The text document.

 *  @param position
 *    The position inside the text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 */
case class TypeHierarchyPrepareParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object TypeHierarchyPrepareParams:
  given reader: Reader[structures.TypeHierarchyPrepareParams] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchyPrepareParams] = upickle.default.macroW

/**
 *  @since 3.17.0

 *  @param name
 *    The name of this item.

 *  @param kind
 *    The kind of this item.

 *  @param tags
 *    Tags for this item.

 *  @param detail
 *    More detail for this item, e.g. the signature of a function.

 *  @param uri
 *    The resource identifier of this item.

 *  @param range
 *    The range enclosing this symbol not including leading/trailing whitespace
 *    but everything else, e.g. comments and code.

 *  @param selectionRange
 *    The range that should be selected and revealed when this symbol is being
 *    picked, e.g. the name of a function. Must be contained by the
 *    [`range`](#TypeHierarchyItem.range).

 *  @param data
 *    A data entry field that is preserved between a type hierarchy prepare and
 *    supertypes or subtypes requests. It could also be used to identify the
 *    type hierarchy in the server, helping improve the performance on
 *    resolving supertypes and subtypes.

 */
case class TypeHierarchyItem(
  name: String,
  kind: enumerations.SymbolKind,
  tags: Opt[Vector[enumerations.SymbolTag]] = Opt.empty,
  detail: Opt[String] = Opt.empty,
  uri: RuntimeBase.DocumentUri,
  range: structures.Range,
  selectionRange: structures.Range,
  data: Opt[ujson.Value] = Opt.empty
)
object TypeHierarchyItem:
  given reader: Reader[structures.TypeHierarchyItem] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchyItem] = upickle.default.macroW

/**
 *  Type hierarchy options used during static or dynamic registration.
 *  
 *  @since 3.17.0

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class TypeHierarchyRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object TypeHierarchyRegistrationOptions:
  given reader: Reader[structures.TypeHierarchyRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchyRegistrationOptions] = upickle.default.macroW

/**
 *  The parameter of a `typeHierarchy/supertypes` request.
 *  
 *  @since 3.17.0

 *  @param item
 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class TypeHierarchySupertypesParams(
  item: structures.TypeHierarchyItem,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object TypeHierarchySupertypesParams:
  given reader: Reader[structures.TypeHierarchySupertypesParams] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchySupertypesParams] = upickle.default.macroW

/**
 *  The parameter of a `typeHierarchy/subtypes` request.
 *  
 *  @since 3.17.0

 *  @param item
 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class TypeHierarchySubtypesParams(
  item: structures.TypeHierarchyItem,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object TypeHierarchySubtypesParams:
  given reader: Reader[structures.TypeHierarchySubtypesParams] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchySubtypesParams] = upickle.default.macroW

/**
 *  A parameter literal used in inline value requests.
 *  
 *  @since 3.17.0

 *  @param textDocument
 *    The text document.

 *  @param range
 *    The document range for which inline values should be computed.

 *  @param context
 *    Additional information about the context in which inline values were
 *    requested.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 */
case class InlineValueParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  context: structures.InlineValueContext,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object InlineValueParams:
  given reader: Reader[structures.InlineValueParams] = Pickle.macroR
  given writer: Writer[structures.InlineValueParams] = upickle.default.macroW

/**
 *  Inline value options used during static or dynamic registration.
 *  
 *  @since 3.17.0

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class InlineValueRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object InlineValueRegistrationOptions:
  given reader: Reader[structures.InlineValueRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.InlineValueRegistrationOptions] = upickle.default.macroW

/**
 *  A parameter literal used in inlay hint requests.
 *  
 *  @since 3.17.0

 *  @param textDocument
 *    The text document.

 *  @param range
 *    The document range for which inlay hints should be computed.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 */
case class InlayHintParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object InlayHintParams:
  given reader: Reader[structures.InlayHintParams] = Pickle.macroR
  given writer: Writer[structures.InlayHintParams] = upickle.default.macroW

/**
 *  Inlay hint information.
 *  
 *  @since 3.17.0

 *  @param position
 *    The position of this hint.

 *  @param label
 *    The label of this hint. A human readable string or an array of
 *    InlayHintLabelPart label parts.
 *    
 *    *Note* that neither the string nor the label part can be empty.

 *  @param kind
 *    The kind of this hint. Can be omitted in which case the client
 *    should fall back to a reasonable default.

 *  @param textEdits
 *    Optional text edits that are performed when accepting this inlay hint.
 *    
 *    *Note* that edits are expected to change the document so that the inlay
 *    hint (or its nearest variant) is now part of the document and the inlay
 *    hint itself is now obsolete.

 *  @param tooltip
 *    The tooltip text when you hover over this item.

 *  @param paddingLeft
 *    Render padding before the hint.
 *    
 *    Note: Padding should use the editor's background color, not the
 *    background color of the hint itself. That means padding can be used
 *    to visually align/separate an inlay hint.

 *  @param paddingRight
 *    Render padding after the hint.
 *    
 *    Note: Padding should use the editor's background color, not the
 *    background color of the hint itself. That means padding can be used
 *    to visually align/separate an inlay hint.

 *  @param data
 *    A data entry field that is preserved on an inlay hint between
 *    a `textDocument/inlayHint` and a `inlayHint/resolve` request.

 */
case class InlayHint(
  position: structures.Position,
  label: (String | Vector[structures.InlayHintLabelPart]),
  kind: Opt[enumerations.InlayHintKind] = Opt.empty,
  textEdits: Opt[Vector[structures.TextEdit]] = Opt.empty,
  tooltip: Opt[(String | structures.MarkupContent)] = Opt.empty,
  paddingLeft: Opt[Boolean] = Opt.empty,
  paddingRight: Opt[Boolean] = Opt.empty,
  data: Opt[ujson.Value] = Opt.empty
)
object InlayHint:
  private given rd0: Reader[(String | Vector[structures.InlayHintLabelPart])] = 
    badMerge[(String | Vector[structures.InlayHintLabelPart])](stringCodec, upickle.default.reader[Vector[structures.InlayHintLabelPart]])
  private given wt0: Writer[(String | Vector[structures.InlayHintLabelPart])] = 
    upickle.default.writer[ujson.Value].comap[(String | Vector[structures.InlayHintLabelPart])] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: Vector[?] => writeJs[Vector[structures.InlayHintLabelPart]](v.asInstanceOf[Vector[structures.InlayHintLabelPart]])
    }
  private given rd1: Reader[(String | structures.MarkupContent)] = 
    badMerge[(String | structures.MarkupContent)](stringCodec, structures.MarkupContent.reader)
  private given wt1: Writer[(String | structures.MarkupContent)] = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: structures.MarkupContent => writeJs[structures.MarkupContent](v)
    }
  given reader: Reader[structures.InlayHint] = Pickle.macroR
  given writer: Writer[structures.InlayHint] = upickle.default.macroW

/**
 *  Inlay hint options used during static or dynamic registration.
 *  
 *  @since 3.17.0

 *  @param resolveProvider
 *    The server provides support to resolve additional
 *    information for an inlay hint item.

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class InlayHintRegistrationOptions(
  resolveProvider: Opt[Boolean] = Opt.empty,
  documentSelector: Nullable[aliases.DocumentSelector],
  id: Opt[String] = Opt.empty
)
object InlayHintRegistrationOptions:
  given reader: Reader[structures.InlayHintRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.InlayHintRegistrationOptions] = upickle.default.macroW

/**
 *  Parameters of the document diagnostic request.
 *  
 *  @since 3.17.0

 *  @param textDocument
 *    The text document.

 *  @param identifier
 *    The additional identifier  provided during registration.

 *  @param previousResultId
 *    The result id of a previous response if provided.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class DocumentDiagnosticParams(
  textDocument: structures.TextDocumentIdentifier,
  identifier: Opt[String] = Opt.empty,
  previousResultId: Opt[String] = Opt.empty,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object DocumentDiagnosticParams:
  given reader: Reader[structures.DocumentDiagnosticParams] = Pickle.macroR
  given writer: Writer[structures.DocumentDiagnosticParams] = upickle.default.macroW

/**
 *  A partial result for a document diagnostic report.
 *  
 *  @since 3.17.0

 *  @param relatedDocuments
 */
case class DocumentDiagnosticReportPartialResult(
  relatedDocuments: Map[RuntimeBase.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)]
)
object DocumentDiagnosticReportPartialResult:
  private given rd0: Reader[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = 
    badMerge[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)](structures.FullDocumentDiagnosticReport.reader, structures.UnchangedDocumentDiagnosticReport.reader)
  private given wt0: Writer[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = 
    upickle.default.writer[ujson.Value].comap[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] { _v => 
      (_v: @unchecked) match 
        case v: structures.FullDocumentDiagnosticReport => writeJs[structures.FullDocumentDiagnosticReport](v)
        case v: structures.UnchangedDocumentDiagnosticReport => writeJs[structures.UnchangedDocumentDiagnosticReport](v)
    }
  given reader: Reader[structures.DocumentDiagnosticReportPartialResult] = Pickle.macroR
  given writer: Writer[structures.DocumentDiagnosticReportPartialResult] = upickle.default.macroW

/**
 *  Cancellation data returned from a diagnostic request.
 *  
 *  @since 3.17.0

 *  @param retriggerRequest
 */
case class DiagnosticServerCancellationData(
  retriggerRequest: Boolean
)
object DiagnosticServerCancellationData:
  given reader: Reader[structures.DiagnosticServerCancellationData] = Pickle.macroR
  given writer: Writer[structures.DiagnosticServerCancellationData] = upickle.default.macroW

/**
 *  Diagnostic registration options.
 *  
 *  @since 3.17.0

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param identifier
 *    An optional identifier under which the diagnostics are
 *    managed by the client.

 *  @param interFileDependencies
 *    Whether the language has inter file dependencies meaning that
 *    editing code in one file can result in a different diagnostic
 *    set in another file. Inter file dependencies are common for
 *    most programming languages and typically uncommon for linters.

 *  @param workspaceDiagnostics
 *    The server provides support for workspace diagnostics as well.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class DiagnosticRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  identifier: Opt[String] = Opt.empty,
  interFileDependencies: Boolean,
  workspaceDiagnostics: Boolean,
  id: Opt[String] = Opt.empty
)
object DiagnosticRegistrationOptions:
  given reader: Reader[structures.DiagnosticRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DiagnosticRegistrationOptions] = upickle.default.macroW

/**
 *  Parameters of the workspace diagnostic request.
 *  
 *  @since 3.17.0

 *  @param identifier
 *    The additional identifier provided during registration.

 *  @param previousResultIds
 *    The currently known diagnostic reports with their
 *    previous result ids.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class WorkspaceDiagnosticParams(
  identifier: Opt[String] = Opt.empty,
  previousResultIds: Vector[structures.PreviousResultId],
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object WorkspaceDiagnosticParams:
  given reader: Reader[structures.WorkspaceDiagnosticParams] = Pickle.macroR
  given writer: Writer[structures.WorkspaceDiagnosticParams] = upickle.default.macroW

/**
 *  A workspace diagnostic report.
 *  
 *  @since 3.17.0

 *  @param items
 */
case class WorkspaceDiagnosticReport(
  items: Vector[aliases.WorkspaceDocumentDiagnosticReport]
)
object WorkspaceDiagnosticReport:
  given reader: Reader[structures.WorkspaceDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.WorkspaceDiagnosticReport] = upickle.default.macroW

/**
 *  A partial result for a workspace diagnostic report.
 *  
 *  @since 3.17.0

 *  @param items
 */
case class WorkspaceDiagnosticReportPartialResult(
  items: Vector[aliases.WorkspaceDocumentDiagnosticReport]
)
object WorkspaceDiagnosticReportPartialResult:
  given reader: Reader[structures.WorkspaceDiagnosticReportPartialResult] = Pickle.macroR
  given writer: Writer[structures.WorkspaceDiagnosticReportPartialResult] = upickle.default.macroW

/**
 *  The params sent in an open notebook document notification.
 *  
 *  @since 3.17.0

 *  @param notebookDocument
 *    The notebook document that got opened.

 *  @param cellTextDocuments
 *    The text documents that represent the content
 *    of a notebook cell.

 */
case class DidOpenNotebookDocumentParams(
  notebookDocument: structures.NotebookDocument,
  cellTextDocuments: Vector[structures.TextDocumentItem]
)
object DidOpenNotebookDocumentParams:
  given reader: Reader[structures.DidOpenNotebookDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidOpenNotebookDocumentParams] = upickle.default.macroW

/**
 *  The params sent in a change notebook document notification.
 *  
 *  @since 3.17.0

 *  @param notebookDocument
 *    The notebook document that did change. The version number points
 *    to the version after all provided changes have been applied. If
 *    only the text document content of a cell changes the notebook version
 *    doesn't necessarily have to change.

 *  @param change
 *    The actual changes to the notebook document.
 *    
 *    The changes describe single state changes to the notebook document.
 *    So if there are two changes c1 (at array index 0) and c2 (at array
 *    index 1) for a notebook in state S then c1 moves the notebook from
 *    S to S' and c2 from S' to S''. So c1 is computed on the state S and
 *    c2 is computed on the state S'.
 *    
 *    To mirror the content of a notebook using change events use the following approach:
 *    - start with the same initial content
 *    - apply the 'notebookDocument/didChange' notifications in the order you receive them.
 *    - apply the `NotebookChangeEvent`s in a single notification in the order
 *      you receive them.

 */
case class DidChangeNotebookDocumentParams(
  notebookDocument: structures.VersionedNotebookDocumentIdentifier,
  change: structures.NotebookDocumentChangeEvent
)
object DidChangeNotebookDocumentParams:
  given reader: Reader[structures.DidChangeNotebookDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidChangeNotebookDocumentParams] = upickle.default.macroW

/**
 *  The params sent in a save notebook document notification.
 *  
 *  @since 3.17.0

 *  @param notebookDocument
 *    The notebook document that got saved.

 */
case class DidSaveNotebookDocumentParams(
  notebookDocument: structures.NotebookDocumentIdentifier
)
object DidSaveNotebookDocumentParams:
  given reader: Reader[structures.DidSaveNotebookDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidSaveNotebookDocumentParams] = upickle.default.macroW

/**
 *  The params sent in a close notebook document notification.
 *  
 *  @since 3.17.0

 *  @param notebookDocument
 *    The notebook document that got closed.

 *  @param cellTextDocuments
 *    The text documents that represent the content
 *    of a notebook cell that got closed.

 */
case class DidCloseNotebookDocumentParams(
  notebookDocument: structures.NotebookDocumentIdentifier,
  cellTextDocuments: Vector[structures.TextDocumentIdentifier]
)
object DidCloseNotebookDocumentParams:
  given reader: Reader[structures.DidCloseNotebookDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidCloseNotebookDocumentParams] = upickle.default.macroW

case class RegistrationParams(
  registrations: Vector[structures.Registration]
)
object RegistrationParams:
  given reader: Reader[structures.RegistrationParams] = Pickle.macroR
  given writer: Writer[structures.RegistrationParams] = upickle.default.macroW

case class UnregistrationParams(
  unregisterations: Vector[structures.Unregistration]
)
object UnregistrationParams:
  given reader: Reader[structures.UnregistrationParams] = Pickle.macroR
  given writer: Writer[structures.UnregistrationParams] = upickle.default.macroW

/**
 *  @param processId
 *    The process Id of the parent process that started
 *    the server.
 *    
 *    Is `null` if the process has not been started by another process.
 *    If the parent process is not alive then the server should exit.

 *  @param clientInfo
 *    Information about the client
 *    
 *    @since 3.15.0

 *  @param locale
 *    The locale the client is currently showing the user interface
 *    in. This must not necessarily be the locale of the operating
 *    system.
 *    
 *    Uses IETF language tags as the value's syntax
 *    (See https://en.wikipedia.org/wiki/IETF_language_tag)
 *    
 *    @since 3.16.0

 *  @param rootPath
 *    The rootPath of the workspace. Is null
 *    if no folder is open.
 *    
 *    @deprecated in favour of rootUri.

 *  @param rootUri
 *    The rootUri of the workspace. Is null if no
 *    folder is open. If both `rootPath` and `rootUri` are set
 *    `rootUri` wins.
 *    
 *    @deprecated in favour of workspaceFolders.

 *  @param capabilities
 *    The capabilities provided by the client (editor or tool)

 *  @param initializationOptions
 *    User provided initialization options.

 *  @param trace
 *    The initial trace setting. If omitted trace is disabled ('off').

 *  @param workspaceFolders
 *    The workspace folders configured in the client when the server starts.
 *    
 *    This property is only available if the client supports workspace folders.
 *    It can be `null` if the client supports workspace folders but none are
 *    configured.
 *    
 *    @since 3.6.0

 */
case class InitializeParams(
  processId: Nullable[Int],
  clientInfo: Opt[InitializeParams.ClientInfo] = Opt.empty,
  locale: Opt[String] = Opt.empty,
  rootPath: Opt[Nullable[String]] = Opt.empty,
  rootUri: Nullable[RuntimeBase.DocumentUri],
  capabilities: structures.ClientCapabilities,
  initializationOptions: Opt[ujson.Value] = Opt.empty,
  trace: Opt[("off" | "messages" | "compact" | "verbose")] = Opt.empty,
  workspaceFolders: Opt[Nullable[Vector[structures.WorkspaceFolder]]] = Opt.empty
)
object InitializeParams:
  private given rd3: Reader[("off" | "messages" | "compact" | "verbose")] = 
    badMerge[("off" | "messages" | "compact" | "verbose")](upickle.default.reader["off"], upickle.default.reader["messages"], upickle.default.reader["compact"], upickle.default.reader["verbose"])
  private given wt3: Writer[("off" | "messages" | "compact" | "verbose")] = 
    upickle.default.writer[ujson.Value].comap[("off" | "messages" | "compact" | "verbose")] { _v => 
      (_v: @unchecked) match 
        case v: "off" => writeJs["off"](v)
        case v: "messages" => writeJs["messages"](v)
        case v: "compact" => writeJs["compact"](v)
        case v: "verbose" => writeJs["verbose"](v)
    }
  given reader: Reader[structures.InitializeParams] = Pickle.macroR
  given writer: Writer[structures.InitializeParams] = upickle.default.macroW
  /**
   *  @param name
   *    The name of the client as defined by the client.
  
   *  @param version
   *    The client's version as defined by the client.
  
   */
  case class ClientInfo(
    name: String,
    version: Opt[String] = Opt.empty
  )
  object ClientInfo:
    given reader: Reader[structures.InitializeParams.ClientInfo] = Pickle.macroR
    given writer: Writer[structures.InitializeParams.ClientInfo] = upickle.default.macroW

/**
 *  The result returned from an initialize request.

 *  @param capabilities
 *    The capabilities the language server provides.

 *  @param serverInfo
 *    Information about the server.
 *    
 *    @since 3.15.0

 */
case class InitializeResult(
  capabilities: structures.ServerCapabilities,
  serverInfo: Opt[InitializeResult.ServerInfo] = Opt.empty
)
object InitializeResult:
  given reader: Reader[structures.InitializeResult] = Pickle.macroR
  given writer: Writer[structures.InitializeResult] = upickle.default.macroW
  /**
   *  @param name
   *    The name of the server as defined by the server.
  
   *  @param version
   *    The server's version as defined by the server.
  
   */
  case class ServerInfo(
    name: String,
    version: Opt[String] = Opt.empty
  )
  object ServerInfo:
    given reader: Reader[structures.InitializeResult.ServerInfo] = Pickle.macroR
    given writer: Writer[structures.InitializeResult.ServerInfo] = upickle.default.macroW

/**
 *  The data type of the ResponseError if the
 *  initialize request fails.

 *  @param retry
 *    Indicates whether the client execute the following retry logic:
 *    (1) show the message provided by the ResponseError to the user
 *    (2) user selects retry or cancel
 *    (3) if user selected retry the initialize method is sent again.

 */
case class InitializeError(
  retry: Boolean
)
object InitializeError:
  given reader: Reader[structures.InitializeError] = Pickle.macroR
  given writer: Writer[structures.InitializeError] = upickle.default.macroW

case class InitializedParams(
)
object InitializedParams:
  given reader: Reader[structures.InitializedParams] = Pickle.macroR
  given writer: Writer[structures.InitializedParams] = upickle.default.macroW

/**
 *  The parameters of a change configuration notification.

 *  @param settings
 *    The actual changed settings

 */
case class DidChangeConfigurationParams(
  settings: ujson.Value
)
object DidChangeConfigurationParams:
  given reader: Reader[structures.DidChangeConfigurationParams] = Pickle.macroR
  given writer: Writer[structures.DidChangeConfigurationParams] = upickle.default.macroW

case class DidChangeConfigurationRegistrationOptions(
  section: Opt[(String | Vector[String])] = Opt.empty
)
object DidChangeConfigurationRegistrationOptions:
  private given rd0: Reader[(String | Vector[String])] = 
    badMerge[(String | Vector[String])](stringCodec, upickle.default.reader[Vector[String]])
  private given wt0: Writer[(String | Vector[String])] = 
    upickle.default.writer[ujson.Value].comap[(String | Vector[String])] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: Vector[?] => writeJs[Vector[String]](v.asInstanceOf[Vector[String]])
    }
  given reader: Reader[structures.DidChangeConfigurationRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DidChangeConfigurationRegistrationOptions] = upickle.default.macroW

/**
 *  The parameters of a notification message.

 *  @param type
 *    The message type. See {@link MessageType}

 *  @param message
 *    The actual message.

 */
case class ShowMessageParams(
  `type`: enumerations.MessageType,
  message: String
)
object ShowMessageParams:
  given reader: Reader[structures.ShowMessageParams] = Pickle.macroR
  given writer: Writer[structures.ShowMessageParams] = upickle.default.macroW

/**
 *  @param type
 *    The message type. See {@link MessageType}

 *  @param message
 *    The actual message.

 *  @param actions
 *    The message action items to present.

 */
case class ShowMessageRequestParams(
  `type`: enumerations.MessageType,
  message: String,
  actions: Opt[Vector[structures.MessageActionItem]] = Opt.empty
)
object ShowMessageRequestParams:
  given reader: Reader[structures.ShowMessageRequestParams] = Pickle.macroR
  given writer: Writer[structures.ShowMessageRequestParams] = upickle.default.macroW

/**
 *  @param title
 *    A short title like 'Retry', 'Open Log' etc.

 */
case class MessageActionItem(
  title: String
)
object MessageActionItem:
  given reader: Reader[structures.MessageActionItem] = Pickle.macroR
  given writer: Writer[structures.MessageActionItem] = upickle.default.macroW

/**
 *  The log message parameters.

 *  @param type
 *    The message type. See {@link MessageType}

 *  @param message
 *    The actual message.

 */
case class LogMessageParams(
  `type`: enumerations.MessageType,
  message: String
)
object LogMessageParams:
  given reader: Reader[structures.LogMessageParams] = Pickle.macroR
  given writer: Writer[structures.LogMessageParams] = upickle.default.macroW

/**
 *  The parameters sent in an open text document notification

 *  @param textDocument
 *    The document that was opened.

 */
case class DidOpenTextDocumentParams(
  textDocument: structures.TextDocumentItem
)
object DidOpenTextDocumentParams:
  given reader: Reader[structures.DidOpenTextDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidOpenTextDocumentParams] = upickle.default.macroW

/**
 *  The change text document notification's parameters.

 *  @param textDocument
 *    The document that did change. The version number points
 *    to the version after all provided content changes have
 *    been applied.

 *  @param contentChanges
 *    The actual content changes. The content changes describe single state changes
 *    to the document. So if there are two content changes c1 (at array index 0) and
 *    c2 (at array index 1) for a document in state S then c1 moves the document from
 *    S to S' and c2 from S' to S''. So c1 is computed on the state S and c2 is computed
 *    on the state S'.
 *    
 *    To mirror the content of a document using change events use the following approach:
 *    - start with the same initial content
 *    - apply the 'textDocument/didChange' notifications in the order you receive them.
 *    - apply the `TextDocumentContentChangeEvent`s in a single notification in the order
 *      you receive them.

 */
case class DidChangeTextDocumentParams(
  textDocument: structures.VersionedTextDocumentIdentifier,
  contentChanges: Vector[aliases.TextDocumentContentChangeEvent]
)
object DidChangeTextDocumentParams:
  given reader: Reader[structures.DidChangeTextDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidChangeTextDocumentParams] = upickle.default.macroW

/**
 *  Describe options to be used when registered for text document change events.

 *  @param syncKind
 *    How documents are synced to the server.

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 */
case class TextDocumentChangeRegistrationOptions(
  syncKind: enumerations.TextDocumentSyncKind,
  documentSelector: Nullable[aliases.DocumentSelector]
)
object TextDocumentChangeRegistrationOptions:
  given reader: Reader[structures.TextDocumentChangeRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.TextDocumentChangeRegistrationOptions] = upickle.default.macroW

/**
 *  The parameters sent in a close text document notification

 *  @param textDocument
 *    The document that was closed.

 */
case class DidCloseTextDocumentParams(
  textDocument: structures.TextDocumentIdentifier
)
object DidCloseTextDocumentParams:
  given reader: Reader[structures.DidCloseTextDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidCloseTextDocumentParams] = upickle.default.macroW

/**
 *  The parameters sent in a save text document notification

 *  @param textDocument
 *    The document that was saved.

 *  @param text
 *    Optional the content when saved. Depends on the includeText value
 *    when the save notification was requested.

 */
case class DidSaveTextDocumentParams(
  textDocument: structures.TextDocumentIdentifier,
  text: Opt[String] = Opt.empty
)
object DidSaveTextDocumentParams:
  given reader: Reader[structures.DidSaveTextDocumentParams] = Pickle.macroR
  given writer: Writer[structures.DidSaveTextDocumentParams] = upickle.default.macroW

/**
 *  Save registration options.

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param includeText
 *    The client is supposed to include the content on save.

 */
case class TextDocumentSaveRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  includeText: Opt[Boolean] = Opt.empty
)
object TextDocumentSaveRegistrationOptions:
  given reader: Reader[structures.TextDocumentSaveRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.TextDocumentSaveRegistrationOptions] = upickle.default.macroW

/**
 *  The parameters sent in a will save text document notification.

 *  @param textDocument
 *    The document that will be saved.

 *  @param reason
 *    The 'TextDocumentSaveReason'.

 */
case class WillSaveTextDocumentParams(
  textDocument: structures.TextDocumentIdentifier,
  reason: enumerations.TextDocumentSaveReason
)
object WillSaveTextDocumentParams:
  given reader: Reader[structures.WillSaveTextDocumentParams] = Pickle.macroR
  given writer: Writer[structures.WillSaveTextDocumentParams] = upickle.default.macroW

/**
 *  A text edit applicable to a text document.

 *  @param range
 *    The range of the text document to be manipulated. To insert
 *    text into a document create a range where start === end.

 *  @param newText
 *    The string to be inserted. For delete operations use an
 *    empty string.

 */
case class TextEdit(
  range: structures.Range,
  newText: String
)
object TextEdit:
  given reader: Reader[structures.TextEdit] = Pickle.macroR
  given writer: Writer[structures.TextEdit] = upickle.default.macroW

/**
 *  The watched files change notification's parameters.

 *  @param changes
 *    The actual file events.

 */
case class DidChangeWatchedFilesParams(
  changes: Vector[structures.FileEvent]
)
object DidChangeWatchedFilesParams:
  given reader: Reader[structures.DidChangeWatchedFilesParams] = Pickle.macroR
  given writer: Writer[structures.DidChangeWatchedFilesParams] = upickle.default.macroW

/**
 *  Describe options to be used when registered for text document change events.

 *  @param watchers
 *    The watchers to register.

 */
case class DidChangeWatchedFilesRegistrationOptions(
  watchers: Vector[structures.FileSystemWatcher]
)
object DidChangeWatchedFilesRegistrationOptions:
  given reader: Reader[structures.DidChangeWatchedFilesRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DidChangeWatchedFilesRegistrationOptions] = upickle.default.macroW

/**
 *  The publish diagnostic notification's parameters.

 *  @param uri
 *    The URI for which diagnostic information is reported.

 *  @param version
 *    Optional the version number of the document the diagnostics are published for.
 *    
 *    @since 3.15.0

 *  @param diagnostics
 *    An array of diagnostic information items.

 */
case class PublishDiagnosticsParams(
  uri: RuntimeBase.DocumentUri,
  version: Opt[Int] = Opt.empty,
  diagnostics: Vector[structures.Diagnostic]
)
object PublishDiagnosticsParams:
  given reader: Reader[structures.PublishDiagnosticsParams] = Pickle.macroR
  given writer: Writer[structures.PublishDiagnosticsParams] = upickle.default.macroW

/**
 *  Completion parameters

 *  @param context
 *    The completion context. This is only available it the client specifies
 *    to send this using the client capability `textDocument.completion.contextSupport === true`

 *  @param textDocument
 *    The text document.

 *  @param position
 *    The position inside the text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class CompletionParams(
  context: Opt[structures.CompletionContext] = Opt.empty,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object CompletionParams:
  given reader: Reader[structures.CompletionParams] = Pickle.macroR
  given writer: Writer[structures.CompletionParams] = upickle.default.macroW

/**
 *  A completion item represents a text snippet that is
 *  proposed to complete text that is being typed.

 *  @param label
 *    The label of this completion item.
 *    
 *    The label property is also by default the text that
 *    is inserted when selecting this completion.
 *    
 *    If label details are provided the label itself should
 *    be an unqualified name of the completion item.

 *  @param labelDetails
 *    Additional details for the label
 *    
 *    @since 3.17.0

 *  @param kind
 *    The kind of this completion item. Based of the kind
 *    an icon is chosen by the editor.

 *  @param tags
 *    Tags for this completion item.
 *    
 *    @since 3.15.0

 *  @param detail
 *    A human-readable string with additional information
 *    about this item, like type or symbol information.

 *  @param documentation
 *    A human-readable string that represents a doc-comment.

 *  @param deprecated
 *    Indicates if this item is deprecated.
 *    @deprecated Use `tags` instead.

 *  @param preselect
 *    Select this item when showing.
 *    
 *    *Note* that only one completion item can be selected and that the
 *    tool / client decides which item that is. The rule is that the *first*
 *    item of those that match best is selected.

 *  @param sortText
 *    A string that should be used when comparing this item
 *    with other items. When `falsy` the [label](#CompletionItem.label)
 *    is used.

 *  @param filterText
 *    A string that should be used when filtering a set of
 *    completion items. When `falsy` the [label](#CompletionItem.label)
 *    is used.

 *  @param insertText
 *    A string that should be inserted into a document when selecting
 *    this completion. When `falsy` the [label](#CompletionItem.label)
 *    is used.
 *    
 *    The `insertText` is subject to interpretation by the client side.
 *    Some tools might not take the string literally. For example
 *    VS Code when code complete is requested in this example
 *    `con<cursor position>` and a completion item with an `insertText` of
 *    `console` is provided it will only insert `sole`. Therefore it is
 *    recommended to use `textEdit` instead since it avoids additional client
 *    side interpretation.

 *  @param insertTextFormat
 *    The format of the insert text. The format applies to both the
 *    `insertText` property and the `newText` property of a provided
 *    `textEdit`. If omitted defaults to `InsertTextFormat.PlainText`.
 *    
 *    Please note that the insertTextFormat doesn't apply to
 *    `additionalTextEdits`.

 *  @param insertTextMode
 *    How whitespace and indentation is handled during completion
 *    item insertion. If not provided the clients default value depends on
 *    the `textDocument.completion.insertTextMode` client capability.
 *    
 *    @since 3.16.0

 *  @param textEdit
 *    An [edit](#TextEdit) which is applied to a document when selecting
 *    this completion. When an edit is provided the value of
 *    [insertText](#CompletionItem.insertText) is ignored.
 *    
 *    Most editors support two different operations when accepting a completion
 *    item. One is to insert a completion text and the other is to replace an
 *    existing text with a completion text. Since this can usually not be
 *    predetermined by a server it can report both ranges. Clients need to
 *    signal support for `InsertReplaceEdits` via the
 *    `textDocument.completion.insertReplaceSupport` client capability
 *    property.
 *    
 *    *Note 1:* The text edit's range as well as both ranges from an insert
 *    replace edit must be a [single line] and they must contain the position
 *    at which completion has been requested.
 *    *Note 2:* If an `InsertReplaceEdit` is returned the edit's insert range
 *    must be a prefix of the edit's replace range, that means it must be
 *    contained and starting at the same position.
 *    
 *    @since 3.16.0 additional type `InsertReplaceEdit`

 *  @param textEditText
 *    The edit text used if the completion item is part of a CompletionList and
 *    CompletionList defines an item default for the text edit range.
 *    
 *    Clients will only honor this property if they opt into completion list
 *    item defaults using the capability `completionList.itemDefaults`.
 *    
 *    If not provided and a list's default range is provided the label
 *    property is used as a text.
 *    
 *    @since 3.17.0

 *  @param additionalTextEdits
 *    An optional array of additional [text edits](#TextEdit) that are applied when
 *    selecting this completion. Edits must not overlap (including the same insert position)
 *    with the main [edit](#CompletionItem.textEdit) nor with themselves.
 *    
 *    Additional text edits should be used to change text unrelated to the current cursor position
 *    (for example adding an import statement at the top of the file if the completion item will
 *    insert an unqualified type).

 *  @param commitCharacters
 *    An optional set of characters that when pressed while this completion is active will accept it first and
 *    then type that character. *Note* that all commit characters should have `length=1` and that superfluous
 *    characters will be ignored.

 *  @param command
 *    An optional [command](#Command) that is executed *after* inserting this completion. *Note* that
 *    additional modifications to the current document should be described with the
 *    [additionalTextEdits](#CompletionItem.additionalTextEdits)-property.

 *  @param data
 *    A data entry field that is preserved on a completion item between a
 *    [CompletionRequest](#CompletionRequest) and a [CompletionResolveRequest](#CompletionResolveRequest).

 */
case class CompletionItem(
  label: String,
  labelDetails: Opt[structures.CompletionItemLabelDetails] = Opt.empty,
  kind: Opt[enumerations.CompletionItemKind] = Opt.empty,
  tags: Opt[Vector[enumerations.CompletionItemTag]] = Opt.empty,
  detail: Opt[String] = Opt.empty,
  documentation: Opt[(String | structures.MarkupContent)] = Opt.empty,
  deprecated: Opt[Boolean] = Opt.empty,
  preselect: Opt[Boolean] = Opt.empty,
  sortText: Opt[String] = Opt.empty,
  filterText: Opt[String] = Opt.empty,
  insertText: Opt[String] = Opt.empty,
  insertTextFormat: Opt[enumerations.InsertTextFormat] = Opt.empty,
  insertTextMode: Opt[enumerations.InsertTextMode] = Opt.empty,
  textEdit: Opt[(structures.TextEdit | structures.InsertReplaceEdit)] = Opt.empty,
  textEditText: Opt[String] = Opt.empty,
  additionalTextEdits: Opt[Vector[structures.TextEdit]] = Opt.empty,
  commitCharacters: Opt[Vector[String]] = Opt.empty,
  command: Opt[structures.Command] = Opt.empty,
  data: Opt[ujson.Value] = Opt.empty
)
object CompletionItem:
  private given rd0: Reader[(String | structures.MarkupContent)] = 
    badMerge[(String | structures.MarkupContent)](stringCodec, structures.MarkupContent.reader)
  private given wt0: Writer[(String | structures.MarkupContent)] = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: structures.MarkupContent => writeJs[structures.MarkupContent](v)
    }
  private given rd1: Reader[(structures.TextEdit | structures.InsertReplaceEdit)] = 
    badMerge[(structures.TextEdit | structures.InsertReplaceEdit)](structures.TextEdit.reader, structures.InsertReplaceEdit.reader)
  private given wt1: Writer[(structures.TextEdit | structures.InsertReplaceEdit)] = 
    upickle.default.writer[ujson.Value].comap[(structures.TextEdit | structures.InsertReplaceEdit)] { _v => 
      (_v: @unchecked) match 
        case v: structures.TextEdit => writeJs[structures.TextEdit](v)
        case v: structures.InsertReplaceEdit => writeJs[structures.InsertReplaceEdit](v)
    }
  given reader: Reader[structures.CompletionItem] = Pickle.macroR
  given writer: Writer[structures.CompletionItem] = upickle.default.macroW

/**
 *  Represents a collection of [completion items](#CompletionItem) to be presented
 *  in the editor.

 *  @param isIncomplete
 *    This list it not complete. Further typing results in recomputing this list.
 *    
 *    Recomputed lists have all their items replaced (not appended) in the
 *    incomplete completion sessions.

 *  @param itemDefaults
 *    In many cases the items of an actual completion result share the same
 *    value for properties like `commitCharacters` or the range of a text
 *    edit. A completion list can therefore define item defaults which will
 *    be used if a completion item itself doesn't specify the value.
 *    
 *    If a completion list specifies a default value and a completion item
 *    also specifies a corresponding value the one from the item is used.
 *    
 *    Servers are only allowed to return default values if the client
 *    signals support for this via the `completionList.itemDefaults`
 *    capability.
 *    
 *    @since 3.17.0

 *  @param items
 *    The completion items.

 */
case class CompletionList(
  isIncomplete: Boolean,
  itemDefaults: Opt[CompletionList.ItemDefaults] = Opt.empty,
  items: Vector[structures.CompletionItem]
)
object CompletionList:
  given reader: Reader[structures.CompletionList] = Pickle.macroR
  given writer: Writer[structures.CompletionList] = upickle.default.macroW
  /**
   *  @param commitCharacters
   *    A default commit character set.
   *    
   *    @since 3.17.0
  
   *  @param editRange
   *    A default edit range.
   *    
   *    @since 3.17.0
  
   *  @param insertTextFormat
   *    A default insert text format.
   *    
   *    @since 3.17.0
  
   *  @param insertTextMode
   *    A default insert text mode.
   *    
   *    @since 3.17.0
  
   *  @param data
   *    A default data value.
   *    
   *    @since 3.17.0
  
   */
  case class ItemDefaults(
    commitCharacters: Opt[Vector[String]] = Opt.empty,
    editRange: Opt[(structures.Range | ItemDefaults.S0)] = Opt.empty,
    insertTextFormat: Opt[enumerations.InsertTextFormat] = Opt.empty,
    insertTextMode: Opt[enumerations.InsertTextMode] = Opt.empty,
    data: Opt[ujson.Value] = Opt.empty
  )
  object ItemDefaults:
    private given rd0: Reader[(structures.Range | ItemDefaults.S0)] = 
      badMerge[(structures.Range | ItemDefaults.S0)](structures.Range.reader, ItemDefaults.S0.reader)
    private given wt0: Writer[(structures.Range | ItemDefaults.S0)] = 
      upickle.default.writer[ujson.Value].comap[(structures.Range | ItemDefaults.S0)] { _v => 
        (_v: @unchecked) match 
          case v: structures.Range => writeJs[structures.Range](v)
          case v: ItemDefaults.S0 => writeJs[ItemDefaults.S0](v)
      }
    given reader: Reader[structures.CompletionList.ItemDefaults] = Pickle.macroR
    given writer: Writer[structures.CompletionList.ItemDefaults] = upickle.default.macroW
    case class S0(
      insert: structures.Range,
      replace: structures.Range
    )
    object S0:
      given reader: Reader[structures.CompletionList.ItemDefaults.S0] = Pickle.macroR
      given writer: Writer[structures.CompletionList.ItemDefaults.S0] = upickle.default.macroW

/**
 *  Registration options for a [CompletionRequest](#CompletionRequest).

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param triggerCharacters
 *    Most tools trigger completion request automatically without explicitly requesting
 *    it using a keyboard shortcut (e.g. Ctrl+Space). Typically they do so when the user
 *    starts to type an identifier. For example if the user types `c` in a JavaScript file
 *    code complete will automatically pop up present `console` besides others as a
 *    completion item. Characters that make up identifiers don't need to be listed here.
 *    
 *    If code complete should automatically be trigger on characters not being valid inside
 *    an identifier (for example `.` in JavaScript) list them in `triggerCharacters`.

 *  @param allCommitCharacters
 *    The list of all possible characters that commit a completion. This field can be used
 *    if clients don't support individual commit characters per completion item. See
 *    `ClientCapabilities.textDocument.completion.completionItem.commitCharactersSupport`
 *    
 *    If a server provides both `allCommitCharacters` and commit characters on an individual
 *    completion item the ones on the completion item win.
 *    
 *    @since 3.2.0

 *  @param resolveProvider
 *    The server provides support to resolve additional
 *    information for a completion item.

 *  @param completionItem
 *    The server supports the following `CompletionItem` specific
 *    capabilities.
 *    
 *    @since 3.17.0

 */
case class CompletionRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  triggerCharacters: Opt[Vector[String]] = Opt.empty,
  allCommitCharacters: Opt[Vector[String]] = Opt.empty,
  resolveProvider: Opt[Boolean] = Opt.empty,
  completionItem: Opt[CompletionRegistrationOptions.CompletionItem] = Opt.empty
)
object CompletionRegistrationOptions:
  given reader: Reader[structures.CompletionRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.CompletionRegistrationOptions] = upickle.default.macroW
  /**
   *  @param labelDetailsSupport
   *    The server has support for completion item label
   *    details (see also `CompletionItemLabelDetails`) when
   *    receiving a completion item in a resolve call.
   *    
   *    @since 3.17.0
  
   */
  case class CompletionItem(
    labelDetailsSupport: Opt[Boolean] = Opt.empty
  )
  object CompletionItem:
    given reader: Reader[structures.CompletionRegistrationOptions.CompletionItem] = Pickle.macroR
    given writer: Writer[structures.CompletionRegistrationOptions.CompletionItem] = upickle.default.macroW

/**
 *  Parameters for a [HoverRequest](#HoverRequest).

 *  @param textDocument
 *    The text document.

 *  @param position
 *    The position inside the text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 */
case class HoverParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object HoverParams:
  given reader: Reader[structures.HoverParams] = Pickle.macroR
  given writer: Writer[structures.HoverParams] = upickle.default.macroW

/**
 *  The result of a hover request.

 *  @param contents
 *    The hover's content

 *  @param range
 *    An optional range inside the text document that is used to
 *    visualize the hover, e.g. by changing the background color.

 */
case class Hover(
  contents: (structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString]),
  range: Opt[structures.Range] = Opt.empty
)
object Hover:
  private given rd0: Reader[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])] = 
    badMerge[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])](structures.MarkupContent.reader, aliases.MarkedString.reader, upickle.default.reader[Vector[aliases.MarkedString]])
  private given wt0: Writer[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])] = 
    upickle.default.writer[ujson.Value].comap[(structures.MarkupContent | aliases.MarkedString | Vector[aliases.MarkedString])] { _v => 
      (_v: @unchecked) match 
        case v: structures.MarkupContent => writeJs[structures.MarkupContent](v)
        case v: aliases.MarkedString => writeJs[aliases.MarkedString](v)
        case v: Vector[?] => writeJs[Vector[aliases.MarkedString]](v.asInstanceOf[Vector[aliases.MarkedString]])
    }
  given reader: Reader[structures.Hover] = Pickle.macroR
  given writer: Writer[structures.Hover] = upickle.default.macroW

/**
 *  Registration options for a [HoverRequest](#HoverRequest).

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 */
case class HoverRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector]
)
object HoverRegistrationOptions:
  given reader: Reader[structures.HoverRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.HoverRegistrationOptions] = upickle.default.macroW

/**
 *  Parameters for a [SignatureHelpRequest](#SignatureHelpRequest).

 *  @param context
 *    The signature help context. This is only available if the client specifies
 *    to send this using the client capability `textDocument.signatureHelp.contextSupport === true`
 *    
 *    @since 3.15.0

 *  @param textDocument
 *    The text document.

 *  @param position
 *    The position inside the text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 */
case class SignatureHelpParams(
  context: Opt[structures.SignatureHelpContext] = Opt.empty,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object SignatureHelpParams:
  given reader: Reader[structures.SignatureHelpParams] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpParams] = upickle.default.macroW

/**
 *  Signature help represents the signature of something
 *  callable. There can be multiple signature but only one
 *  active and only one active parameter.

 *  @param signatures
 *    One or more signatures.

 *  @param activeSignature
 *    The active signature. If omitted or the value lies outside the
 *    range of `signatures` the value defaults to zero or is ignored if
 *    the `SignatureHelp` has no signatures.
 *    
 *    Whenever possible implementors should make an active decision about
 *    the active signature and shouldn't rely on a default value.
 *    
 *    In future version of the protocol this property might become
 *    mandatory to better express this.

 *  @param activeParameter
 *    The active parameter of the active signature. If omitted or the value
 *    lies outside the range of `signatures[activeSignature].parameters`
 *    defaults to 0 if the active signature has parameters. If
 *    the active signature has no parameters it is ignored.
 *    In future version of the protocol this property might become
 *    mandatory to better express the active parameter if the
 *    active signature does have any.

 */
case class SignatureHelp(
  signatures: Vector[structures.SignatureInformation],
  activeSignature: Opt[RuntimeBase.uinteger] = Opt.empty,
  activeParameter: Opt[RuntimeBase.uinteger] = Opt.empty
)
object SignatureHelp:
  given reader: Reader[structures.SignatureHelp] = Pickle.macroR
  given writer: Writer[structures.SignatureHelp] = upickle.default.macroW

/**
 *  Registration options for a [SignatureHelpRequest](#SignatureHelpRequest).

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param triggerCharacters
 *    List of characters that trigger signature help automatically.

 *  @param retriggerCharacters
 *    List of characters that re-trigger signature help.
 *    
 *    These trigger characters are only active when signature help is already showing. All trigger characters
 *    are also counted as re-trigger characters.
 *    
 *    @since 3.15.0

 */
case class SignatureHelpRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  triggerCharacters: Opt[Vector[String]] = Opt.empty,
  retriggerCharacters: Opt[Vector[String]] = Opt.empty
)
object SignatureHelpRegistrationOptions:
  given reader: Reader[structures.SignatureHelpRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpRegistrationOptions] = upickle.default.macroW

/**
 *  Parameters for a [DefinitionRequest](#DefinitionRequest).

 *  @param textDocument
 *    The text document.

 *  @param position
 *    The position inside the text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class DefinitionParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object DefinitionParams:
  given reader: Reader[structures.DefinitionParams] = Pickle.macroR
  given writer: Writer[structures.DefinitionParams] = upickle.default.macroW

/**
 *  Registration options for a [DefinitionRequest](#DefinitionRequest).

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 */
case class DefinitionRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector]
)
object DefinitionRegistrationOptions:
  given reader: Reader[structures.DefinitionRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DefinitionRegistrationOptions] = upickle.default.macroW

/**
 *  Parameters for a [ReferencesRequest](#ReferencesRequest).

 *  @param context
 *  @param textDocument
 *    The text document.

 *  @param position
 *    The position inside the text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class ReferenceParams(
  context: structures.ReferenceContext,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object ReferenceParams:
  given reader: Reader[structures.ReferenceParams] = Pickle.macroR
  given writer: Writer[structures.ReferenceParams] = upickle.default.macroW

/**
 *  Registration options for a [ReferencesRequest](#ReferencesRequest).

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 */
case class ReferenceRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector]
)
object ReferenceRegistrationOptions:
  given reader: Reader[structures.ReferenceRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.ReferenceRegistrationOptions] = upickle.default.macroW

/**
 *  Parameters for a [DocumentHighlightRequest](#DocumentHighlightRequest).

 *  @param textDocument
 *    The text document.

 *  @param position
 *    The position inside the text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class DocumentHighlightParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object DocumentHighlightParams:
  given reader: Reader[structures.DocumentHighlightParams] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlightParams] = upickle.default.macroW

/**
 *  A document highlight is a range inside a text document which deserves
 *  special attention. Usually a document highlight is visualized by changing
 *  the background color of its range.

 *  @param range
 *    The range this highlight applies to.

 *  @param kind
 *    The highlight kind, default is [text](#DocumentHighlightKind.Text).

 */
case class DocumentHighlight(
  range: structures.Range,
  kind: Opt[enumerations.DocumentHighlightKind] = Opt.empty
)
object DocumentHighlight:
  given reader: Reader[structures.DocumentHighlight] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlight] = upickle.default.macroW

/**
 *  Registration options for a [DocumentHighlightRequest](#DocumentHighlightRequest).

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 */
case class DocumentHighlightRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector]
)
object DocumentHighlightRegistrationOptions:
  given reader: Reader[structures.DocumentHighlightRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlightRegistrationOptions] = upickle.default.macroW

/**
 *  Parameters for a [DocumentSymbolRequest](#DocumentSymbolRequest).

 *  @param textDocument
 *    The text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class DocumentSymbolParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object DocumentSymbolParams:
  given reader: Reader[structures.DocumentSymbolParams] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbolParams] = upickle.default.macroW

/**
 *  Represents information about programming constructs like variables, classes,
 *  interfaces etc.

 *  @param deprecated
 *    Indicates if this symbol is deprecated.
 *    
 *    @deprecated Use tags instead

 *  @param location
 *    The location of this symbol. The location's range is used by a tool
 *    to reveal the location in the editor. If the symbol is selected in the
 *    tool the range's start information is used to position the cursor. So
 *    the range usually spans more than the actual symbol's name and does
 *    normally include things like visibility modifiers.
 *    
 *    The range doesn't have to denote a node range in the sense of an abstract
 *    syntax tree. It can therefore not be used to re-construct a hierarchy of
 *    the symbols.

 *  @param name
 *    The name of this symbol.

 *  @param kind
 *    The kind of this symbol.

 *  @param tags
 *    Tags for this symbol.
 *    
 *    @since 3.16.0

 *  @param containerName
 *    The name of the symbol containing this symbol. This information is for
 *    user interface purposes (e.g. to render a qualifier in the user interface
 *    if necessary). It can't be used to re-infer a hierarchy for the document
 *    symbols.

 */
case class SymbolInformation(
  deprecated: Opt[Boolean] = Opt.empty,
  location: structures.Location,
  name: String,
  kind: enumerations.SymbolKind,
  tags: Opt[Vector[enumerations.SymbolTag]] = Opt.empty,
  containerName: Opt[String] = Opt.empty
)
object SymbolInformation:
  given reader: Reader[structures.SymbolInformation] = Pickle.macroR
  given writer: Writer[structures.SymbolInformation] = upickle.default.macroW

/**
 *  Represents programming constructs like variables, classes, interfaces etc.
 *  that appear in a document. Document symbols can be hierarchical and they
 *  have two ranges: one that encloses its definition and one that points to
 *  its most interesting range, e.g. the range of an identifier.

 *  @param name
 *    The name of this symbol. Will be displayed in the user interface and therefore must not be
 *    an empty string or a string only consisting of white spaces.

 *  @param detail
 *    More detail for this symbol, e.g the signature of a function.

 *  @param kind
 *    The kind of this symbol.

 *  @param tags
 *    Tags for this document symbol.
 *    
 *    @since 3.16.0

 *  @param deprecated
 *    Indicates if this symbol is deprecated.
 *    
 *    @deprecated Use tags instead

 *  @param range
 *    The range enclosing this symbol not including leading/trailing whitespace but everything else
 *    like comments. This information is typically used to determine if the clients cursor is
 *    inside the symbol to reveal in the symbol in the UI.

 *  @param selectionRange
 *    The range that should be selected and revealed when this symbol is being picked, e.g the name of a function.
 *    Must be contained by the `range`.

 *  @param children
 *    Children of this symbol, e.g. properties of a class.

 */
case class DocumentSymbol(
  name: String,
  detail: Opt[String] = Opt.empty,
  kind: enumerations.SymbolKind,
  tags: Opt[Vector[enumerations.SymbolTag]] = Opt.empty,
  deprecated: Opt[Boolean] = Opt.empty,
  range: structures.Range,
  selectionRange: structures.Range,
  children: Opt[Vector[structures.DocumentSymbol]] = Opt.empty
)
object DocumentSymbol:
  given reader: Reader[structures.DocumentSymbol] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbol] = upickle.default.macroW

/**
 *  Registration options for a [DocumentSymbolRequest](#DocumentSymbolRequest).

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param label
 *    A human-readable string that is shown when multiple outlines trees
 *    are shown for the same document.
 *    
 *    @since 3.16.0

 */
case class DocumentSymbolRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  label: Opt[String] = Opt.empty
)
object DocumentSymbolRegistrationOptions:
  given reader: Reader[structures.DocumentSymbolRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbolRegistrationOptions] = upickle.default.macroW

/**
 *  The parameters of a [CodeActionRequest](#CodeActionRequest).

 *  @param textDocument
 *    The document in which the command was invoked.

 *  @param range
 *    The range for which the command was invoked.

 *  @param context
 *    Context carrying additional information.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class CodeActionParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  context: structures.CodeActionContext,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object CodeActionParams:
  given reader: Reader[structures.CodeActionParams] = Pickle.macroR
  given writer: Writer[structures.CodeActionParams] = upickle.default.macroW

/**
 *  Represents a reference to a command. Provides a title which
 *  will be used to represent a command in the UI and, optionally,
 *  an array of arguments which will be passed to the command handler
 *  function when invoked.

 *  @param title
 *    Title of the command, like `save`.

 *  @param command
 *    The identifier of the actual command handler.

 *  @param arguments
 *    Arguments that the command handler should be
 *    invoked with.

 */
case class Command(
  title: String,
  command: String,
  arguments: Opt[Vector[ujson.Value]] = Opt.empty
)
object Command:
  given reader: Reader[structures.Command] = Pickle.macroR
  given writer: Writer[structures.Command] = upickle.default.macroW

/**
 *  A code action represents a change that can be performed in code, e.g. to fix a problem or
 *  to refactor code.
 *  
 *  A CodeAction must set either `edit` and/or a `command`. If both are supplied, the `edit` is applied first, then the `command` is executed.

 *  @param title
 *    A short, human-readable, title for this code action.

 *  @param kind
 *    The kind of the code action.
 *    
 *    Used to filter code actions.

 *  @param diagnostics
 *    The diagnostics that this code action resolves.

 *  @param isPreferred
 *    Marks this as a preferred action. Preferred actions are used by the `auto fix` command and can be targeted
 *    by keybindings.
 *    
 *    A quick fix should be marked preferred if it properly addresses the underlying error.
 *    A refactoring should be marked preferred if it is the most reasonable choice of actions to take.
 *    
 *    @since 3.15.0

 *  @param disabled
 *    Marks that the code action cannot currently be applied.
 *    
 *    Clients should follow the following guidelines regarding disabled code actions:
 *    
 *      - Disabled code actions are not shown in automatic [lightbulbs](https://code.visualstudio.com/docs/editor/editingevolved#_code-action)
 *        code action menus.
 *    
 *      - Disabled actions are shown as faded out in the code action menu when the user requests a more specific type
 *        of code action, such as refactorings.
 *    
 *      - If the user has a [keybinding](https://code.visualstudio.com/docs/editor/refactoring#_keybindings-for-code-actions)
 *        that auto applies a code action and only disabled code actions are returned, the client should show the user an
 *        error message with `reason` in the editor.
 *    
 *    @since 3.16.0

 *  @param edit
 *    The workspace edit this code action performs.

 *  @param command
 *    A command this code action executes. If a code action
 *    provides an edit and a command, first the edit is
 *    executed and then the command.

 *  @param data
 *    A data entry field that is preserved on a code action between
 *    a `textDocument/codeAction` and a `codeAction/resolve` request.
 *    
 *    @since 3.16.0

 */
case class CodeAction(
  title: String,
  kind: Opt[enumerations.CodeActionKind] = Opt.empty,
  diagnostics: Opt[Vector[structures.Diagnostic]] = Opt.empty,
  isPreferred: Opt[Boolean] = Opt.empty,
  disabled: Opt[CodeAction.Disabled] = Opt.empty,
  edit: Opt[structures.WorkspaceEdit] = Opt.empty,
  command: Opt[structures.Command] = Opt.empty,
  data: Opt[ujson.Value] = Opt.empty
)
object CodeAction:
  given reader: Reader[structures.CodeAction] = Pickle.macroR
  given writer: Writer[structures.CodeAction] = upickle.default.macroW
  /**
   *  @param reason
   *    Human readable description of why the code action is currently disabled.
   *    
   *    This is displayed in the code actions UI.
  
   */
  case class Disabled(
    reason: String
  )
  object Disabled:
    given reader: Reader[structures.CodeAction.Disabled] = Pickle.macroR
    given writer: Writer[structures.CodeAction.Disabled] = upickle.default.macroW

/**
 *  Registration options for a [CodeActionRequest](#CodeActionRequest).

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param codeActionKinds
 *    CodeActionKinds that this server may return.
 *    
 *    The list of kinds may be generic, such as `CodeActionKind.Refactor`, or the server
 *    may list out every specific kind they provide.

 *  @param resolveProvider
 *    The server provides support to resolve additional
 *    information for a code action.
 *    
 *    @since 3.16.0

 */
case class CodeActionRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  codeActionKinds: Opt[Vector[enumerations.CodeActionKind]] = Opt.empty,
  resolveProvider: Opt[Boolean] = Opt.empty
)
object CodeActionRegistrationOptions:
  given reader: Reader[structures.CodeActionRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.CodeActionRegistrationOptions] = upickle.default.macroW

/**
 *  The parameters of a [WorkspaceSymbolRequest](#WorkspaceSymbolRequest).

 *  @param query
 *    A query string to filter symbols by. Clients may send an empty
 *    string here to request all symbols.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class WorkspaceSymbolParams(
  query: String,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object WorkspaceSymbolParams:
  given reader: Reader[structures.WorkspaceSymbolParams] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbolParams] = upickle.default.macroW

/**
 *  A special workspace symbol that supports locations without a range.
 *  
 *  See also SymbolInformation.
 *  
 *  @since 3.17.0

 *  @param location
 *    The location of the symbol. Whether a server is allowed to
 *    return a location without a range depends on the client
 *    capability `workspace.symbol.resolveSupport`.
 *    
 *    See SymbolInformation#location for more details.

 *  @param data
 *    A data entry field that is preserved on a workspace symbol between a
 *    workspace symbol request and a workspace symbol resolve request.

 *  @param name
 *    The name of this symbol.

 *  @param kind
 *    The kind of this symbol.

 *  @param tags
 *    Tags for this symbol.
 *    
 *    @since 3.16.0

 *  @param containerName
 *    The name of the symbol containing this symbol. This information is for
 *    user interface purposes (e.g. to render a qualifier in the user interface
 *    if necessary). It can't be used to re-infer a hierarchy for the document
 *    symbols.

 */
case class WorkspaceSymbol(
  location: (structures.Location | WorkspaceSymbol.S0),
  data: Opt[ujson.Value] = Opt.empty,
  name: String,
  kind: enumerations.SymbolKind,
  tags: Opt[Vector[enumerations.SymbolTag]] = Opt.empty,
  containerName: Opt[String] = Opt.empty
)
object WorkspaceSymbol:
  private given rd0: Reader[(structures.Location | WorkspaceSymbol.S0)] = 
    badMerge[(structures.Location | WorkspaceSymbol.S0)](structures.Location.reader, WorkspaceSymbol.S0.reader)
  private given wt0: Writer[(structures.Location | WorkspaceSymbol.S0)] = 
    upickle.default.writer[ujson.Value].comap[(structures.Location | WorkspaceSymbol.S0)] { _v => 
      (_v: @unchecked) match 
        case v: structures.Location => writeJs[structures.Location](v)
        case v: WorkspaceSymbol.S0 => writeJs[WorkspaceSymbol.S0](v)
    }
  given reader: Reader[structures.WorkspaceSymbol] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbol] = upickle.default.macroW
  case class S0(
    uri: RuntimeBase.DocumentUri
  )
  object S0:
    given reader: Reader[structures.WorkspaceSymbol.S0] = Pickle.macroR
    given writer: Writer[structures.WorkspaceSymbol.S0] = upickle.default.macroW

/**
 *  Registration options for a [WorkspaceSymbolRequest](#WorkspaceSymbolRequest).

 *  @param resolveProvider
 *    The server provides support to resolve additional
 *    information for a workspace symbol.
 *    
 *    @since 3.17.0

 */
case class WorkspaceSymbolRegistrationOptions(
  resolveProvider: Opt[Boolean] = Opt.empty
)
object WorkspaceSymbolRegistrationOptions:
  given reader: Reader[structures.WorkspaceSymbolRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbolRegistrationOptions] = upickle.default.macroW

/**
 *  The parameters of a [CodeLensRequest](#CodeLensRequest).

 *  @param textDocument
 *    The document to request code lens for.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class CodeLensParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object CodeLensParams:
  given reader: Reader[structures.CodeLensParams] = Pickle.macroR
  given writer: Writer[structures.CodeLensParams] = upickle.default.macroW

/**
 *  A code lens represents a [command](#Command) that should be shown along with
 *  source text, like the number of references, a way to run tests, etc.
 *  
 *  A code lens is _unresolved_ when no command is associated to it. For performance
 *  reasons the creation of a code lens and resolving should be done in two stages.

 *  @param range
 *    The range in which this code lens is valid. Should only span a single line.

 *  @param command
 *    The command this code lens represents.

 *  @param data
 *    A data entry field that is preserved on a code lens item between
 *    a [CodeLensRequest](#CodeLensRequest) and a [CodeLensResolveRequest]
 *    (#CodeLensResolveRequest)

 */
case class CodeLens(
  range: structures.Range,
  command: Opt[structures.Command] = Opt.empty,
  data: Opt[ujson.Value] = Opt.empty
)
object CodeLens:
  given reader: Reader[structures.CodeLens] = Pickle.macroR
  given writer: Writer[structures.CodeLens] = upickle.default.macroW

/**
 *  Registration options for a [CodeLensRequest](#CodeLensRequest).

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param resolveProvider
 *    Code lens has a resolve provider as well.

 */
case class CodeLensRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  resolveProvider: Opt[Boolean] = Opt.empty
)
object CodeLensRegistrationOptions:
  given reader: Reader[structures.CodeLensRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.CodeLensRegistrationOptions] = upickle.default.macroW

/**
 *  The parameters of a [DocumentLinkRequest](#DocumentLinkRequest).

 *  @param textDocument
 *    The document to provide document links for.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class DocumentLinkParams(
  textDocument: structures.TextDocumentIdentifier,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty,
  partialResultToken: Opt[aliases.ProgressToken] = Opt.empty
)
object DocumentLinkParams:
  given reader: Reader[structures.DocumentLinkParams] = Pickle.macroR
  given writer: Writer[structures.DocumentLinkParams] = upickle.default.macroW

/**
 *  A document link is a range in a text document that links to an internal or external resource, like another
 *  text document or a web site.

 *  @param range
 *    The range this link applies to.

 *  @param target
 *    The uri this link points to. If missing a resolve request is sent later.

 *  @param tooltip
 *    The tooltip text when you hover over this link.
 *    
 *    If a tooltip is provided, is will be displayed in a string that includes instructions on how to
 *    trigger the link, such as `{0} (ctrl + click)`. The specific instructions vary depending on OS,
 *    user settings, and localization.
 *    
 *    @since 3.15.0

 *  @param data
 *    A data entry field that is preserved on a document link between a
 *    DocumentLinkRequest and a DocumentLinkResolveRequest.

 */
case class DocumentLink(
  range: structures.Range,
  target: Opt[String] = Opt.empty,
  tooltip: Opt[String] = Opt.empty,
  data: Opt[ujson.Value] = Opt.empty
)
object DocumentLink:
  given reader: Reader[structures.DocumentLink] = Pickle.macroR
  given writer: Writer[structures.DocumentLink] = upickle.default.macroW

/**
 *  Registration options for a [DocumentLinkRequest](#DocumentLinkRequest).

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param resolveProvider
 *    Document links have a resolve provider as well.

 */
case class DocumentLinkRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  resolveProvider: Opt[Boolean] = Opt.empty
)
object DocumentLinkRegistrationOptions:
  given reader: Reader[structures.DocumentLinkRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentLinkRegistrationOptions] = upickle.default.macroW

/**
 *  The parameters of a [DocumentFormattingRequest](#DocumentFormattingRequest).

 *  @param textDocument
 *    The document to format.

 *  @param options
 *    The format options.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 */
case class DocumentFormattingParams(
  textDocument: structures.TextDocumentIdentifier,
  options: structures.FormattingOptions,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object DocumentFormattingParams:
  given reader: Reader[structures.DocumentFormattingParams] = Pickle.macroR
  given writer: Writer[structures.DocumentFormattingParams] = upickle.default.macroW

/**
 *  Registration options for a [DocumentFormattingRequest](#DocumentFormattingRequest).

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 */
case class DocumentFormattingRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector]
)
object DocumentFormattingRegistrationOptions:
  given reader: Reader[structures.DocumentFormattingRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentFormattingRegistrationOptions] = upickle.default.macroW

/**
 *  The parameters of a [DocumentRangeFormattingRequest](#DocumentRangeFormattingRequest).

 *  @param textDocument
 *    The document to format.

 *  @param range
 *    The range to format

 *  @param options
 *    The format options

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 */
case class DocumentRangeFormattingParams(
  textDocument: structures.TextDocumentIdentifier,
  range: structures.Range,
  options: structures.FormattingOptions,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object DocumentRangeFormattingParams:
  given reader: Reader[structures.DocumentRangeFormattingParams] = Pickle.macroR
  given writer: Writer[structures.DocumentRangeFormattingParams] = upickle.default.macroW

/**
 *  Registration options for a [DocumentRangeFormattingRequest](#DocumentRangeFormattingRequest).

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 */
case class DocumentRangeFormattingRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector]
)
object DocumentRangeFormattingRegistrationOptions:
  given reader: Reader[structures.DocumentRangeFormattingRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentRangeFormattingRegistrationOptions] = upickle.default.macroW

/**
 *  The parameters of a [DocumentOnTypeFormattingRequest](#DocumentOnTypeFormattingRequest).

 *  @param textDocument
 *    The document to format.

 *  @param position
 *    The position around which the on type formatting should happen.
 *    This is not necessarily the exact position where the character denoted
 *    by the property `ch` got typed.

 *  @param ch
 *    The character that has been typed that triggered the formatting
 *    on type request. That is not necessarily the last character that
 *    got inserted into the document since the client could auto insert
 *    characters as well (e.g. like automatic brace completion).

 *  @param options
 *    The formatting options.

 */
case class DocumentOnTypeFormattingParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  ch: String,
  options: structures.FormattingOptions
)
object DocumentOnTypeFormattingParams:
  given reader: Reader[structures.DocumentOnTypeFormattingParams] = Pickle.macroR
  given writer: Writer[structures.DocumentOnTypeFormattingParams] = upickle.default.macroW

/**
 *  Registration options for a [DocumentOnTypeFormattingRequest](#DocumentOnTypeFormattingRequest).

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param firstTriggerCharacter
 *    A character on which formatting should be triggered, like `{`.

 *  @param moreTriggerCharacter
 *    More trigger characters.

 */
case class DocumentOnTypeFormattingRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  firstTriggerCharacter: String,
  moreTriggerCharacter: Opt[Vector[String]] = Opt.empty
)
object DocumentOnTypeFormattingRegistrationOptions:
  given reader: Reader[structures.DocumentOnTypeFormattingRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentOnTypeFormattingRegistrationOptions] = upickle.default.macroW

/**
 *  The parameters of a [RenameRequest](#RenameRequest).

 *  @param textDocument
 *    The document to rename.

 *  @param position
 *    The position at which this request was sent.

 *  @param newName
 *    The new name of the symbol. If the given name is not valid the
 *    request must return a [ResponseError](#ResponseError) with an
 *    appropriate message set.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 */
case class RenameParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  newName: String,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object RenameParams:
  given reader: Reader[structures.RenameParams] = Pickle.macroR
  given writer: Writer[structures.RenameParams] = upickle.default.macroW

/**
 *  Registration options for a [RenameRequest](#RenameRequest).

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param prepareProvider
 *    Renames should be checked and tested before being executed.
 *    
 *    @since version 3.12.0

 */
case class RenameRegistrationOptions(
  documentSelector: Nullable[aliases.DocumentSelector],
  prepareProvider: Opt[Boolean] = Opt.empty
)
object RenameRegistrationOptions:
  given reader: Reader[structures.RenameRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.RenameRegistrationOptions] = upickle.default.macroW

/**
 *  @param textDocument
 *    The text document.

 *  @param position
 *    The position inside the text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 */
case class PrepareRenameParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object PrepareRenameParams:
  given reader: Reader[structures.PrepareRenameParams] = Pickle.macroR
  given writer: Writer[structures.PrepareRenameParams] = upickle.default.macroW

/**
 *  The parameters of a [ExecuteCommandRequest](#ExecuteCommandRequest).

 *  @param command
 *    The identifier of the actual command handler.

 *  @param arguments
 *    Arguments that the command should be invoked with.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 */
case class ExecuteCommandParams(
  command: String,
  arguments: Opt[Vector[ujson.Value]] = Opt.empty,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object ExecuteCommandParams:
  given reader: Reader[structures.ExecuteCommandParams] = Pickle.macroR
  given writer: Writer[structures.ExecuteCommandParams] = upickle.default.macroW

/**
 *  Registration options for a [ExecuteCommandRequest](#ExecuteCommandRequest).

 *  @param commands
 *    The commands to be executed on the server

 */
case class ExecuteCommandRegistrationOptions(
  commands: Vector[String]
)
object ExecuteCommandRegistrationOptions:
  given reader: Reader[structures.ExecuteCommandRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.ExecuteCommandRegistrationOptions] = upickle.default.macroW

/**
 *  The parameters passed via a apply workspace edit request.

 *  @param label
 *    An optional label of the workspace edit. This label is
 *    presented in the user interface for example on an undo
 *    stack to undo the workspace edit.

 *  @param edit
 *    The edits to apply.

 */
case class ApplyWorkspaceEditParams(
  label: Opt[String] = Opt.empty,
  edit: structures.WorkspaceEdit
)
object ApplyWorkspaceEditParams:
  given reader: Reader[structures.ApplyWorkspaceEditParams] = Pickle.macroR
  given writer: Writer[structures.ApplyWorkspaceEditParams] = upickle.default.macroW

/**
 *  The result returned from the apply workspace edit request.
 *  
 *  @since 3.17 renamed from ApplyWorkspaceEditResponse

 *  @param applied
 *    Indicates whether the edit was applied or not.

 *  @param failureReason
 *    An optional textual description for why the edit was not applied.
 *    This may be used by the server for diagnostic logging or to provide
 *    a suitable error for a request that triggered the edit.

 *  @param failedChange
 *    Depending on the client's failure handling strategy `failedChange` might
 *    contain the index of the change that failed. This property is only available
 *    if the client signals a `failureHandlingStrategy` in its client capabilities.

 */
case class ApplyWorkspaceEditResult(
  applied: Boolean,
  failureReason: Opt[String] = Opt.empty,
  failedChange: Opt[RuntimeBase.uinteger] = Opt.empty
)
object ApplyWorkspaceEditResult:
  given reader: Reader[structures.ApplyWorkspaceEditResult] = Pickle.macroR
  given writer: Writer[structures.ApplyWorkspaceEditResult] = upickle.default.macroW

/**
 *  @param kind
 *  @param title
 *    Mandatory title of the progress operation. Used to briefly inform about
 *    the kind of operation being performed.
 *    
 *    Examples: "Indexing" or "Linking dependencies".

 *  @param cancellable
 *    Controls if a cancel button should show to allow the user to cancel the
 *    long running operation. Clients that don't support cancellation are allowed
 *    to ignore the setting.

 *  @param message
 *    Optional, more detailed associated progress message. Contains
 *    complementary information to the `title`.
 *    
 *    Examples: "3/25 files", "project/src/module2", "node_modules/some_dep".
 *    If unset, the previous progress message (if any) is still valid.

 *  @param percentage
 *    Optional progress percentage to display (value 100 is considered 100%).
 *    If not provided infinite progress is assumed and clients are allowed
 *    to ignore the `percentage` value in subsequent in report notifications.
 *    
 *    The value should be steadily rising. Clients are free to ignore values
 *    that are not following this rule. The value range is [0, 100].

 */
case class WorkDoneProgressBegin(
  kind: "begin",
  title: String,
  cancellable: Opt[Boolean] = Opt.empty,
  message: Opt[String] = Opt.empty,
  percentage: Opt[RuntimeBase.uinteger] = Opt.empty
)
object WorkDoneProgressBegin:
  given reader: Reader[structures.WorkDoneProgressBegin] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressBegin] = upickle.default.macroW

/**
 *  @param kind
 *  @param cancellable
 *    Controls enablement state of a cancel button.
 *    
 *    Clients that don't support cancellation or don't support controlling the button's
 *    enablement state are allowed to ignore the property.

 *  @param message
 *    Optional, more detailed associated progress message. Contains
 *    complementary information to the `title`.
 *    
 *    Examples: "3/25 files", "project/src/module2", "node_modules/some_dep".
 *    If unset, the previous progress message (if any) is still valid.

 *  @param percentage
 *    Optional progress percentage to display (value 100 is considered 100%).
 *    If not provided infinite progress is assumed and clients are allowed
 *    to ignore the `percentage` value in subsequent in report notifications.
 *    
 *    The value should be steadily rising. Clients are free to ignore values
 *    that are not following this rule. The value range is [0, 100]

 */
case class WorkDoneProgressReport(
  kind: "report",
  cancellable: Opt[Boolean] = Opt.empty,
  message: Opt[String] = Opt.empty,
  percentage: Opt[RuntimeBase.uinteger] = Opt.empty
)
object WorkDoneProgressReport:
  given reader: Reader[structures.WorkDoneProgressReport] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressReport] = upickle.default.macroW

/**
 *  @param kind
 *  @param message
 *    Optional, a final message indicating to for example indicate the outcome
 *    of the operation.

 */
case class WorkDoneProgressEnd(
  kind: "end",
  message: Opt[String] = Opt.empty
)
object WorkDoneProgressEnd:
  given reader: Reader[structures.WorkDoneProgressEnd] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressEnd] = upickle.default.macroW

case class SetTraceParams(
  value: enumerations.TraceValues
)
object SetTraceParams:
  given reader: Reader[structures.SetTraceParams] = Pickle.macroR
  given writer: Writer[structures.SetTraceParams] = upickle.default.macroW

case class LogTraceParams(
  message: String,
  verbose: Opt[String] = Opt.empty
)
object LogTraceParams:
  given reader: Reader[structures.LogTraceParams] = Pickle.macroR
  given writer: Writer[structures.LogTraceParams] = upickle.default.macroW

/**
 *  @param id
 *    The request id to cancel.

 */
case class CancelParams(
  id: (Int | String)
)
object CancelParams:
  private given rd0: Reader[(Int | String)] = 
    badMerge[(Int | String)](intCodec, stringCodec)
  private given wt0: Writer[(Int | String)] = 
    upickle.default.writer[ujson.Value].comap[(Int | String)] { _v => 
      (_v: @unchecked) match 
        case v: Int => writeJs[Int](v)
        case v: String => writeJs[String](v)
    }
  given reader: Reader[structures.CancelParams] = Pickle.macroR
  given writer: Writer[structures.CancelParams] = upickle.default.macroW

/**
 *  @param token
 *    The progress token provided by the client or server.

 *  @param value
 *    The progress data.

 */
case class ProgressParams(
  token: aliases.ProgressToken,
  value: ujson.Value
)
object ProgressParams:
  given reader: Reader[structures.ProgressParams] = Pickle.macroR
  given writer: Writer[structures.ProgressParams] = upickle.default.macroW

/**
 *  A parameter literal used in requests to pass a text document and a position inside that
 *  document.

 *  @param textDocument
 *    The text document.

 *  @param position
 *    The position inside the text document.

 */
case class TextDocumentPositionParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position
)
object TextDocumentPositionParams:
  given reader: Reader[structures.TextDocumentPositionParams] = Pickle.macroR
  given writer: Writer[structures.TextDocumentPositionParams] = upickle.default.macroW

/**
 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 */
case class WorkDoneProgressParams(
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object WorkDoneProgressParams:
  given reader: Reader[structures.WorkDoneProgressParams] = Pickle.macroR
  given writer: Writer[structures.WorkDoneProgressParams] = upickle.default.macroW

/**
 *  Represents the connection of two locations. Provides additional metadata over normal [locations](#Location),
 *  including an origin range.

 *  @param originSelectionRange
 *    Span of the origin of this link.
 *    
 *    Used as the underlined span for mouse interaction. Defaults to the word range at
 *    the definition position.

 *  @param targetUri
 *    The target resource identifier of this link.

 *  @param targetRange
 *    The full target range of this link. If the target for example is a symbol then target range is the
 *    range enclosing this symbol not including leading/trailing whitespace but everything else
 *    like comments. This information is typically used to highlight the range in the editor.

 *  @param targetSelectionRange
 *    The range that should be selected and revealed when this link is being followed, e.g the name of a function.
 *    Must be contained by the `targetRange`. See also `DocumentSymbol#range`

 */
case class LocationLink(
  originSelectionRange: Opt[structures.Range] = Opt.empty,
  targetUri: RuntimeBase.DocumentUri,
  targetRange: structures.Range,
  targetSelectionRange: structures.Range
)
object LocationLink:
  given reader: Reader[structures.LocationLink] = Pickle.macroR
  given writer: Writer[structures.LocationLink] = upickle.default.macroW

/**
 *  A range in a text document expressed as (zero-based) start and end positions.
 *  
 *  If you want to specify a range that contains a line including the line ending
 *  character(s) then use an end position denoting the start of the next line.
 *  For example:
 *  ```ts
 *  {
 *      start: { line: 5, character: 23 }
 *      end : { line 6, character : 0 }
 *  }
 *  ```

 *  @param start
 *    The range's start position.

 *  @param end
 *    The range's end position.

 */
case class Range(
  start: structures.Position,
  end: structures.Position
)
object Range:
  given reader: Reader[structures.Range] = Pickle.macroR
  given writer: Writer[structures.Range] = upickle.default.macroW

case class ImplementationOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object ImplementationOptions:
  given reader: Reader[structures.ImplementationOptions] = Pickle.macroR
  given writer: Writer[structures.ImplementationOptions] = upickle.default.macroW

/**
 *  Static registration options to be returned in the initialize
 *  request.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class StaticRegistrationOptions(
  id: Opt[String] = Opt.empty
)
object StaticRegistrationOptions:
  given reader: Reader[structures.StaticRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.StaticRegistrationOptions] = upickle.default.macroW

case class TypeDefinitionOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object TypeDefinitionOptions:
  given reader: Reader[structures.TypeDefinitionOptions] = Pickle.macroR
  given writer: Writer[structures.TypeDefinitionOptions] = upickle.default.macroW

/**
 *  The workspace folder change event.

 *  @param added
 *    The array of added workspace folders

 *  @param removed
 *    The array of the removed workspace folders

 */
case class WorkspaceFoldersChangeEvent(
  added: Vector[structures.WorkspaceFolder],
  removed: Vector[structures.WorkspaceFolder]
)
object WorkspaceFoldersChangeEvent:
  given reader: Reader[structures.WorkspaceFoldersChangeEvent] = Pickle.macroR
  given writer: Writer[structures.WorkspaceFoldersChangeEvent] = upickle.default.macroW

/**
 *  @param scopeUri
 *    The scope to get the configuration section for.

 *  @param section
 *    The configuration section asked for.

 */
case class ConfigurationItem(
  scopeUri: Opt[String] = Opt.empty,
  section: Opt[String] = Opt.empty
)
object ConfigurationItem:
  given reader: Reader[structures.ConfigurationItem] = Pickle.macroR
  given writer: Writer[structures.ConfigurationItem] = upickle.default.macroW

/**
 *  A literal to identify a text document in the client.

 *  @param uri
 *    The text document's uri.

 */
case class TextDocumentIdentifier(
  uri: RuntimeBase.DocumentUri
)
object TextDocumentIdentifier:
  given reader: Reader[structures.TextDocumentIdentifier] = Pickle.macroR
  given writer: Writer[structures.TextDocumentIdentifier] = upickle.default.macroW

/**
 *  Represents a color in RGBA space.

 *  @param red
 *    The red component of this color in the range [0-1].

 *  @param green
 *    The green component of this color in the range [0-1].

 *  @param blue
 *    The blue component of this color in the range [0-1].

 *  @param alpha
 *    The alpha component of this color in the range [0-1].

 */
case class Color(
  red: Float,
  green: Float,
  blue: Float,
  alpha: Float
)
object Color:
  given reader: Reader[structures.Color] = Pickle.macroR
  given writer: Writer[structures.Color] = upickle.default.macroW

case class DocumentColorOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object DocumentColorOptions:
  given reader: Reader[structures.DocumentColorOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentColorOptions] = upickle.default.macroW

case class FoldingRangeOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object FoldingRangeOptions:
  given reader: Reader[structures.FoldingRangeOptions] = Pickle.macroR
  given writer: Writer[structures.FoldingRangeOptions] = upickle.default.macroW

case class DeclarationOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object DeclarationOptions:
  given reader: Reader[structures.DeclarationOptions] = Pickle.macroR
  given writer: Writer[structures.DeclarationOptions] = upickle.default.macroW

/**
 *  Position in a text document expressed as zero-based line and character
 *  offset. Prior to 3.17 the offsets were always based on a UTF-16 string
 *  representation. So a string of the form `a𐐀b` the character offset of the
 *  character `a` is 0, the character offset of `𐐀` is 1 and the character
 *  offset of b is 3 since `𐐀` is represented using two code units in UTF-16.
 *  Since 3.17 clients and servers can agree on a different string encoding
 *  representation (e.g. UTF-8). The client announces it's supported encoding
 *  via the client capability [`general.positionEncodings`](#clientCapabilities).
 *  The value is an array of position encodings the client supports, with
 *  decreasing preference (e.g. the encoding at index `0` is the most preferred
 *  one). To stay backwards compatible the only mandatory encoding is UTF-16
 *  represented via the string `utf-16`. The server can pick one of the
 *  encodings offered by the client and signals that encoding back to the
 *  client via the initialize result's property
 *  [`capabilities.positionEncoding`](#serverCapabilities). If the string value
 *  `utf-16` is missing from the client's capability `general.positionEncodings`
 *  servers can safely assume that the client supports UTF-16. If the server
 *  omits the position encoding in its initialize result the encoding defaults
 *  to the string value `utf-16`. Implementation considerations: since the
 *  conversion from one encoding into another requires the content of the
 *  file / line the conversion is best done where the file is read which is
 *  usually on the server side.
 *  
 *  Positions are line end character agnostic. So you can not specify a position
 *  that denotes `\r|\n` or `\n|` where `|` represents the character offset.
 *  
 *  @since 3.17.0 - support for negotiated position encoding.

 *  @param line
 *    Line position in a document (zero-based).
 *    
 *    If a line number is greater than the number of lines in a document, it defaults back to the number of lines in the document.
 *    If a line number is negative, it defaults to 0.

 *  @param character
 *    Character offset on a line in a document (zero-based).
 *    
 *    The meaning of this offset is determined by the negotiated
 *    `PositionEncodingKind`.
 *    
 *    If the character value is greater than the line length it defaults back to the
 *    line length.

 */
case class Position(
  line: RuntimeBase.uinteger,
  character: RuntimeBase.uinteger
)
object Position:
  given reader: Reader[structures.Position] = Pickle.macroR
  given writer: Writer[structures.Position] = upickle.default.macroW

case class SelectionRangeOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object SelectionRangeOptions:
  given reader: Reader[structures.SelectionRangeOptions] = Pickle.macroR
  given writer: Writer[structures.SelectionRangeOptions] = upickle.default.macroW

/**
 *  Call hierarchy options used during static registration.
 *  
 *  @since 3.16.0

 *  @param workDoneProgress
 */
case class CallHierarchyOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object CallHierarchyOptions:
  given reader: Reader[structures.CallHierarchyOptions] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyOptions] = upickle.default.macroW

/**
 *  @since 3.16.0

 *  @param legend
 *    The legend used by the server

 *  @param range
 *    Server supports providing semantic tokens for a specific range
 *    of a document.

 *  @param full
 *    Server supports providing semantic tokens for a full document.

 *  @param workDoneProgress
 */
case class SemanticTokensOptions(
  legend: structures.SemanticTokensLegend,
  range: Opt[(Boolean | SemanticTokensOptions.S0)] = Opt.empty,
  full: Opt[(Boolean | SemanticTokensOptions.S1)] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object SemanticTokensOptions:
  private given rd0: Reader[(Boolean | SemanticTokensOptions.S0)] = 
    badMerge[(Boolean | SemanticTokensOptions.S0)](upickle.default.reader[Boolean], SemanticTokensOptions.S0.reader)
  private given wt0: Writer[(Boolean | SemanticTokensOptions.S0)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | SemanticTokensOptions.S0)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: SemanticTokensOptions.S0 => writeJs[SemanticTokensOptions.S0](v)
    }
  private given rd1: Reader[(Boolean | SemanticTokensOptions.S1)] = 
    badMerge[(Boolean | SemanticTokensOptions.S1)](upickle.default.reader[Boolean], SemanticTokensOptions.S1.reader)
  private given wt1: Writer[(Boolean | SemanticTokensOptions.S1)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | SemanticTokensOptions.S1)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: SemanticTokensOptions.S1 => writeJs[SemanticTokensOptions.S1](v)
    }
  given reader: Reader[structures.SemanticTokensOptions] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensOptions] = upickle.default.macroW
  case class S0(
  )
  object S0:
    given reader: Reader[structures.SemanticTokensOptions.S0] = Pickle.macroR
    given writer: Writer[structures.SemanticTokensOptions.S0] = upickle.default.macroW
  /**
   *  @param delta
   *    The server supports deltas for full documents.
  
   */
  case class S1(
    delta: Opt[Boolean] = Opt.empty
  )
  object S1:
    given reader: Reader[structures.SemanticTokensOptions.S1] = Pickle.macroR
    given writer: Writer[structures.SemanticTokensOptions.S1] = upickle.default.macroW

/**
 *  @since 3.16.0

 *  @param start
 *    The start offset of the edit.

 *  @param deleteCount
 *    The count of elements to remove.

 *  @param data
 *    The elements to insert.

 */
case class SemanticTokensEdit(
  start: RuntimeBase.uinteger,
  deleteCount: RuntimeBase.uinteger,
  data: Opt[Vector[RuntimeBase.uinteger]] = Opt.empty
)
object SemanticTokensEdit:
  given reader: Reader[structures.SemanticTokensEdit] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensEdit] = upickle.default.macroW

case class LinkedEditingRangeOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object LinkedEditingRangeOptions:
  given reader: Reader[structures.LinkedEditingRangeOptions] = Pickle.macroR
  given writer: Writer[structures.LinkedEditingRangeOptions] = upickle.default.macroW

/**
 *  Represents information on a file/folder create.
 *  
 *  @since 3.16.0

 *  @param uri
 *    A file:// URI for the location of the file/folder being created.

 */
case class FileCreate(
  uri: String
)
object FileCreate:
  given reader: Reader[structures.FileCreate] = Pickle.macroR
  given writer: Writer[structures.FileCreate] = upickle.default.macroW

/**
 *  Describes textual changes on a text document. A TextDocumentEdit describes all changes
 *  on a document version Si and after they are applied move the document to version Si+1.
 *  So the creator of a TextDocumentEdit doesn't need to sort the array of edits or do any
 *  kind of ordering. However the edits must be non overlapping.

 *  @param textDocument
 *    The text document to change.

 *  @param edits
 *    The edits to be applied.
 *    
 *    @since 3.16.0 - support for AnnotatedTextEdit. This is guarded using a
 *    client capability.

 */
case class TextDocumentEdit(
  textDocument: structures.OptionalVersionedTextDocumentIdentifier,
  edits: Vector[(structures.TextEdit | structures.AnnotatedTextEdit)]
)
object TextDocumentEdit:
  private given rd0: Reader[(structures.TextEdit | structures.AnnotatedTextEdit)] = 
    badMerge[(structures.TextEdit | structures.AnnotatedTextEdit)](structures.TextEdit.reader, structures.AnnotatedTextEdit.reader)
  private given wt0: Writer[(structures.TextEdit | structures.AnnotatedTextEdit)] = 
    upickle.default.writer[ujson.Value].comap[(structures.TextEdit | structures.AnnotatedTextEdit)] { _v => 
      (_v: @unchecked) match 
        case v: structures.TextEdit => writeJs[structures.TextEdit](v)
        case v: structures.AnnotatedTextEdit => writeJs[structures.AnnotatedTextEdit](v)
    }
  given reader: Reader[structures.TextDocumentEdit] = Pickle.macroR
  given writer: Writer[structures.TextDocumentEdit] = upickle.default.macroW

/**
 *  Create file operation.

 *  @param kind
 *    A create

 *  @param uri
 *    The resource to create.

 *  @param options
 *    Additional options

 *  @param kind
 *    The resource operation kind.

 *  @param annotationId
 *    An optional annotation identifier describing the operation.
 *    
 *    @since 3.16.0

 */
case class CreateFile(
  kind: "create",
  uri: RuntimeBase.DocumentUri,
  options: Opt[structures.CreateFileOptions] = Opt.empty,
  annotationId: Opt[aliases.ChangeAnnotationIdentifier] = Opt.empty
)
object CreateFile:
  given reader: Reader[structures.CreateFile] = Pickle.macroR
  given writer: Writer[structures.CreateFile] = upickle.default.macroW

/**
 *  Rename file operation

 *  @param kind
 *    A rename

 *  @param oldUri
 *    The old (existing) location.

 *  @param newUri
 *    The new location.

 *  @param options
 *    Rename options.

 *  @param kind
 *    The resource operation kind.

 *  @param annotationId
 *    An optional annotation identifier describing the operation.
 *    
 *    @since 3.16.0

 */
case class RenameFile(
  kind: "rename",
  oldUri: RuntimeBase.DocumentUri,
  newUri: RuntimeBase.DocumentUri,
  options: Opt[structures.RenameFileOptions] = Opt.empty,
  annotationId: Opt[aliases.ChangeAnnotationIdentifier] = Opt.empty
)
object RenameFile:
  given reader: Reader[structures.RenameFile] = Pickle.macroR
  given writer: Writer[structures.RenameFile] = upickle.default.macroW

/**
 *  Delete file operation

 *  @param kind
 *    A delete

 *  @param uri
 *    The file to delete.

 *  @param options
 *    Delete options.

 *  @param kind
 *    The resource operation kind.

 *  @param annotationId
 *    An optional annotation identifier describing the operation.
 *    
 *    @since 3.16.0

 */
case class DeleteFile(
  kind: "delete",
  uri: RuntimeBase.DocumentUri,
  options: Opt[structures.DeleteFileOptions] = Opt.empty,
  annotationId: Opt[aliases.ChangeAnnotationIdentifier] = Opt.empty
)
object DeleteFile:
  given reader: Reader[structures.DeleteFile] = Pickle.macroR
  given writer: Writer[structures.DeleteFile] = upickle.default.macroW

/**
 *  Additional information that describes document changes.
 *  
 *  @since 3.16.0

 *  @param label
 *    A human-readable string describing the actual change. The string
 *    is rendered prominent in the user interface.

 *  @param needsConfirmation
 *    A flag which indicates that user confirmation is needed
 *    before applying the change.

 *  @param description
 *    A human-readable string which is rendered less prominent in
 *    the user interface.

 */
case class ChangeAnnotation(
  label: String,
  needsConfirmation: Opt[Boolean] = Opt.empty,
  description: Opt[String] = Opt.empty
)
object ChangeAnnotation:
  given reader: Reader[structures.ChangeAnnotation] = Pickle.macroR
  given writer: Writer[structures.ChangeAnnotation] = upickle.default.macroW

/**
 *  A filter to describe in which file operation requests or notifications
 *  the server is interested in receiving.
 *  
 *  @since 3.16.0

 *  @param scheme
 *    A Uri scheme like `file` or `untitled`.

 *  @param pattern
 *    The actual file operation pattern.

 */
case class FileOperationFilter(
  scheme: Opt[String] = Opt.empty,
  pattern: structures.FileOperationPattern
)
object FileOperationFilter:
  given reader: Reader[structures.FileOperationFilter] = Pickle.macroR
  given writer: Writer[structures.FileOperationFilter] = upickle.default.macroW

/**
 *  Represents information on a file/folder rename.
 *  
 *  @since 3.16.0

 *  @param oldUri
 *    A file:// URI for the original location of the file/folder being renamed.

 *  @param newUri
 *    A file:// URI for the new location of the file/folder being renamed.

 */
case class FileRename(
  oldUri: String,
  newUri: String
)
object FileRename:
  given reader: Reader[structures.FileRename] = Pickle.macroR
  given writer: Writer[structures.FileRename] = upickle.default.macroW

/**
 *  Represents information on a file/folder delete.
 *  
 *  @since 3.16.0

 *  @param uri
 *    A file:// URI for the location of the file/folder being deleted.

 */
case class FileDelete(
  uri: String
)
object FileDelete:
  given reader: Reader[structures.FileDelete] = Pickle.macroR
  given writer: Writer[structures.FileDelete] = upickle.default.macroW

case class MonikerOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object MonikerOptions:
  given reader: Reader[structures.MonikerOptions] = Pickle.macroR
  given writer: Writer[structures.MonikerOptions] = upickle.default.macroW

/**
 *  Type hierarchy options used during static registration.
 *  
 *  @since 3.17.0

 *  @param workDoneProgress
 */
case class TypeHierarchyOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object TypeHierarchyOptions:
  given reader: Reader[structures.TypeHierarchyOptions] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchyOptions] = upickle.default.macroW

/**
 *  @since 3.17.0

 *  @param frameId
 *    The stack frame (as a DAP Id) where the execution has stopped.

 *  @param stoppedLocation
 *    The document range where execution has stopped.
 *    Typically the end position of the range denotes the line where the inline values are shown.

 */
case class InlineValueContext(
  frameId: Int,
  stoppedLocation: structures.Range
)
object InlineValueContext:
  given reader: Reader[structures.InlineValueContext] = Pickle.macroR
  given writer: Writer[structures.InlineValueContext] = upickle.default.macroW

/**
 *  Provide inline value as text.
 *  
 *  @since 3.17.0

 *  @param range
 *    The document range for which the inline value applies.

 *  @param text
 *    The text of the inline value.

 */
case class InlineValueText(
  range: structures.Range,
  text: String
)
object InlineValueText:
  given reader: Reader[structures.InlineValueText] = Pickle.macroR
  given writer: Writer[structures.InlineValueText] = upickle.default.macroW

/**
 *  Provide inline value through a variable lookup.
 *  If only a range is specified, the variable name will be extracted from the underlying document.
 *  An optional variable name can be used to override the extracted name.
 *  
 *  @since 3.17.0

 *  @param range
 *    The document range for which the inline value applies.
 *    The range is used to extract the variable name from the underlying document.

 *  @param variableName
 *    If specified the name of the variable to look up.

 *  @param caseSensitiveLookup
 *    How to perform the lookup.

 */
case class InlineValueVariableLookup(
  range: structures.Range,
  variableName: Opt[String] = Opt.empty,
  caseSensitiveLookup: Boolean
)
object InlineValueVariableLookup:
  given reader: Reader[structures.InlineValueVariableLookup] = Pickle.macroR
  given writer: Writer[structures.InlineValueVariableLookup] = upickle.default.macroW

/**
 *  Provide an inline value through an expression evaluation.
 *  If only a range is specified, the expression will be extracted from the underlying document.
 *  An optional expression can be used to override the extracted expression.
 *  
 *  @since 3.17.0

 *  @param range
 *    The document range for which the inline value applies.
 *    The range is used to extract the evaluatable expression from the underlying document.

 *  @param expression
 *    If specified the expression overrides the extracted expression.

 */
case class InlineValueEvaluatableExpression(
  range: structures.Range,
  expression: Opt[String] = Opt.empty
)
object InlineValueEvaluatableExpression:
  given reader: Reader[structures.InlineValueEvaluatableExpression] = Pickle.macroR
  given writer: Writer[structures.InlineValueEvaluatableExpression] = upickle.default.macroW

/**
 *  Inline value options used during static registration.
 *  
 *  @since 3.17.0

 *  @param workDoneProgress
 */
case class InlineValueOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object InlineValueOptions:
  given reader: Reader[structures.InlineValueOptions] = Pickle.macroR
  given writer: Writer[structures.InlineValueOptions] = upickle.default.macroW

/**
 *  An inlay hint label part allows for interactive and composite labels
 *  of inlay hints.
 *  
 *  @since 3.17.0

 *  @param value
 *    The value of this label part.

 *  @param tooltip
 *    The tooltip text when you hover over this label part. Depending on
 *    the client capability `inlayHint.resolveSupport` clients might resolve
 *    this property late using the resolve request.

 *  @param location
 *    An optional source code location that represents this
 *    label part.
 *    
 *    The editor will use this location for the hover and for code navigation
 *    features: This part will become a clickable link that resolves to the
 *    definition of the symbol at the given location (not necessarily the
 *    location itself), it shows the hover that shows at the given location,
 *    and it shows a context menu with further code navigation commands.
 *    
 *    Depending on the client capability `inlayHint.resolveSupport` clients
 *    might resolve this property late using the resolve request.

 *  @param command
 *    An optional command for this label part.
 *    
 *    Depending on the client capability `inlayHint.resolveSupport` clients
 *    might resolve this property late using the resolve request.

 */
case class InlayHintLabelPart(
  value: String,
  tooltip: Opt[(String | structures.MarkupContent)] = Opt.empty,
  location: Opt[structures.Location] = Opt.empty,
  command: Opt[structures.Command] = Opt.empty
)
object InlayHintLabelPart:
  private given rd0: Reader[(String | structures.MarkupContent)] = 
    badMerge[(String | structures.MarkupContent)](stringCodec, structures.MarkupContent.reader)
  private given wt0: Writer[(String | structures.MarkupContent)] = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: structures.MarkupContent => writeJs[structures.MarkupContent](v)
    }
  given reader: Reader[structures.InlayHintLabelPart] = Pickle.macroR
  given writer: Writer[structures.InlayHintLabelPart] = upickle.default.macroW

/**
 *  A `MarkupContent` literal represents a string value which content is interpreted base on its
 *  kind flag. Currently the protocol supports `plaintext` and `markdown` as markup kinds.
 *  
 *  If the kind is `markdown` then the value can contain fenced code blocks like in GitHub issues.
 *  See https://help.github.com/articles/creating-and-highlighting-code-blocks/#syntax-highlighting
 *  
 *  Here is an example how such a string can be constructed using JavaScript / TypeScript:
 *  ```ts
 *  let markdown: MarkdownContent = {
 *   kind: MarkupKind.Markdown,
 *   value: [
 *     '# Header',
 *     'Some text',
 *     '```typescript',
 *     'someCode();',
 *     '```'
 *   ].join('\n')
 *  };
 *  ```
 *  
 *  *Please Note* that clients might sanitize the return markdown. A client could decide to
 *  remove HTML from the markdown to avoid script execution.

 *  @param kind
 *    The type of the Markup

 *  @param value
 *    The content itself

 */
case class MarkupContent(
  kind: enumerations.MarkupKind,
  value: String
)
object MarkupContent:
  given reader: Reader[structures.MarkupContent] = Pickle.macroR
  given writer: Writer[structures.MarkupContent] = upickle.default.macroW

/**
 *  Inlay hint options used during static registration.
 *  
 *  @since 3.17.0

 *  @param resolveProvider
 *    The server provides support to resolve additional
 *    information for an inlay hint item.

 *  @param workDoneProgress
 */
case class InlayHintOptions(
  resolveProvider: Opt[Boolean] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object InlayHintOptions:
  given reader: Reader[structures.InlayHintOptions] = Pickle.macroR
  given writer: Writer[structures.InlayHintOptions] = upickle.default.macroW

/**
 *  A full diagnostic report with a set of related documents.
 *  
 *  @since 3.17.0

 *  @param relatedDocuments
 *    Diagnostics of related documents. This information is useful
 *    in programming languages where code in a file A can generate
 *    diagnostics in a file B which A depends on. An example of
 *    such a language is C/C++ where marco definitions in a file
 *    a.cpp and result in errors in a header file b.hpp.
 *    
 *    @since 3.17.0

 *  @param kind
 *    A full document diagnostic report.

 *  @param resultId
 *    An optional result id. If provided it will
 *    be sent on the next diagnostic request for the
 *    same document.

 *  @param items
 *    The actual items.

 */
case class RelatedFullDocumentDiagnosticReport(
  relatedDocuments: Opt[Map[RuntimeBase.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)]] = Opt.empty,
  kind: "full",
  resultId: Opt[String] = Opt.empty,
  items: Vector[structures.Diagnostic]
)
object RelatedFullDocumentDiagnosticReport:
  private given rd0: Reader[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = 
    badMerge[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)](structures.FullDocumentDiagnosticReport.reader, structures.UnchangedDocumentDiagnosticReport.reader)
  private given wt0: Writer[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = 
    upickle.default.writer[ujson.Value].comap[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] { _v => 
      (_v: @unchecked) match 
        case v: structures.FullDocumentDiagnosticReport => writeJs[structures.FullDocumentDiagnosticReport](v)
        case v: structures.UnchangedDocumentDiagnosticReport => writeJs[structures.UnchangedDocumentDiagnosticReport](v)
    }
  given reader: Reader[structures.RelatedFullDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.RelatedFullDocumentDiagnosticReport] = upickle.default.macroW

/**
 *  An unchanged diagnostic report with a set of related documents.
 *  
 *  @since 3.17.0

 *  @param relatedDocuments
 *    Diagnostics of related documents. This information is useful
 *    in programming languages where code in a file A can generate
 *    diagnostics in a file B which A depends on. An example of
 *    such a language is C/C++ where marco definitions in a file
 *    a.cpp and result in errors in a header file b.hpp.
 *    
 *    @since 3.17.0

 *  @param kind
 *    A document diagnostic report indicating
 *    no changes to the last result. A server can
 *    only return `unchanged` if result ids are
 *    provided.

 *  @param resultId
 *    A result id which will be sent on the next
 *    diagnostic request for the same document.

 */
case class RelatedUnchangedDocumentDiagnosticReport(
  relatedDocuments: Opt[Map[RuntimeBase.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)]] = Opt.empty,
  kind: "unchanged",
  resultId: String
)
object RelatedUnchangedDocumentDiagnosticReport:
  private given rd0: Reader[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = 
    badMerge[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)](structures.FullDocumentDiagnosticReport.reader, structures.UnchangedDocumentDiagnosticReport.reader)
  private given wt0: Writer[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] = 
    upickle.default.writer[ujson.Value].comap[(structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)] { _v => 
      (_v: @unchecked) match 
        case v: structures.FullDocumentDiagnosticReport => writeJs[structures.FullDocumentDiagnosticReport](v)
        case v: structures.UnchangedDocumentDiagnosticReport => writeJs[structures.UnchangedDocumentDiagnosticReport](v)
    }
  given reader: Reader[structures.RelatedUnchangedDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.RelatedUnchangedDocumentDiagnosticReport] = upickle.default.macroW

/**
 *  A diagnostic report with a full set of problems.
 *  
 *  @since 3.17.0

 *  @param kind
 *    A full document diagnostic report.

 *  @param resultId
 *    An optional result id. If provided it will
 *    be sent on the next diagnostic request for the
 *    same document.

 *  @param items
 *    The actual items.

 */
case class FullDocumentDiagnosticReport(
  kind: "full",
  resultId: Opt[String] = Opt.empty,
  items: Vector[structures.Diagnostic]
)
object FullDocumentDiagnosticReport:
  given reader: Reader[structures.FullDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.FullDocumentDiagnosticReport] = upickle.default.macroW

/**
 *  A diagnostic report indicating that the last returned
 *  report is still accurate.
 *  
 *  @since 3.17.0

 *  @param kind
 *    A document diagnostic report indicating
 *    no changes to the last result. A server can
 *    only return `unchanged` if result ids are
 *    provided.

 *  @param resultId
 *    A result id which will be sent on the next
 *    diagnostic request for the same document.

 */
case class UnchangedDocumentDiagnosticReport(
  kind: "unchanged",
  resultId: String
)
object UnchangedDocumentDiagnosticReport:
  given reader: Reader[structures.UnchangedDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.UnchangedDocumentDiagnosticReport] = upickle.default.macroW

/**
 *  Diagnostic options.
 *  
 *  @since 3.17.0

 *  @param identifier
 *    An optional identifier under which the diagnostics are
 *    managed by the client.

 *  @param interFileDependencies
 *    Whether the language has inter file dependencies meaning that
 *    editing code in one file can result in a different diagnostic
 *    set in another file. Inter file dependencies are common for
 *    most programming languages and typically uncommon for linters.

 *  @param workspaceDiagnostics
 *    The server provides support for workspace diagnostics as well.

 *  @param workDoneProgress
 */
case class DiagnosticOptions(
  identifier: Opt[String] = Opt.empty,
  interFileDependencies: Boolean,
  workspaceDiagnostics: Boolean,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object DiagnosticOptions:
  given reader: Reader[structures.DiagnosticOptions] = Pickle.macroR
  given writer: Writer[structures.DiagnosticOptions] = upickle.default.macroW

/**
 *  A previous result id in a workspace pull request.
 *  
 *  @since 3.17.0

 *  @param uri
 *    The URI for which the client knows a
 *    result id.

 *  @param value
 *    The value of the previous result id.

 */
case class PreviousResultId(
  uri: RuntimeBase.DocumentUri,
  value: String
)
object PreviousResultId:
  given reader: Reader[structures.PreviousResultId] = Pickle.macroR
  given writer: Writer[structures.PreviousResultId] = upickle.default.macroW

/**
 *  A notebook document.
 *  
 *  @since 3.17.0

 *  @param uri
 *    The notebook document's uri.

 *  @param notebookType
 *    The type of the notebook.

 *  @param version
 *    The version number of this document (it will increase after each
 *    change, including undo/redo).

 *  @param metadata
 *    Additional metadata stored with the notebook
 *    document.
 *    
 *    Note: should always be an object literal (e.g. LSPObject)

 *  @param cells
 *    The cells of a notebook.

 */
case class NotebookDocument(
  uri: aliases.URI,
  notebookType: String,
  version: Int,
  metadata: Opt[structures.LSPObject] = Opt.empty,
  cells: Vector[structures.NotebookCell]
)
object NotebookDocument:
  given reader: Reader[structures.NotebookDocument] = Pickle.macroR
  given writer: Writer[structures.NotebookDocument] = upickle.default.macroW

/**
 *  An item to transfer a text document from the client to the
 *  server.

 *  @param uri
 *    The text document's uri.

 *  @param languageId
 *    The text document's language identifier.

 *  @param version
 *    The version number of this document (it will increase after each
 *    change, including undo/redo).

 *  @param text
 *    The content of the opened text document.

 */
case class TextDocumentItem(
  uri: RuntimeBase.DocumentUri,
  languageId: String,
  version: Int,
  text: String
)
object TextDocumentItem:
  given reader: Reader[structures.TextDocumentItem] = Pickle.macroR
  given writer: Writer[structures.TextDocumentItem] = upickle.default.macroW

/**
 *  A versioned notebook document identifier.
 *  
 *  @since 3.17.0

 *  @param version
 *    The version number of this notebook document.

 *  @param uri
 *    The notebook document's uri.

 */
case class VersionedNotebookDocumentIdentifier(
  version: Int,
  uri: aliases.URI
)
object VersionedNotebookDocumentIdentifier:
  given reader: Reader[structures.VersionedNotebookDocumentIdentifier] = Pickle.macroR
  given writer: Writer[structures.VersionedNotebookDocumentIdentifier] = upickle.default.macroW

/**
 *  A change event for a notebook document.
 *  
 *  @since 3.17.0

 *  @param metadata
 *    The changed meta data if any.
 *    
 *    Note: should always be an object literal (e.g. LSPObject)

 *  @param cells
 *    Changes to cells

 */
case class NotebookDocumentChangeEvent(
  metadata: Opt[structures.LSPObject] = Opt.empty,
  cells: Opt[NotebookDocumentChangeEvent.Cells] = Opt.empty
)
object NotebookDocumentChangeEvent:
  given reader: Reader[structures.NotebookDocumentChangeEvent] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentChangeEvent] = upickle.default.macroW
  /**
   *  @param structure
   *    Changes to the cell structure to add or
   *    remove cells.
  
   *  @param data
   *    Changes to notebook cells properties like its
   *    kind, execution summary or metadata.
  
   *  @param textContent
   *    Changes to the text content of notebook cells.
  
   */
  case class Cells(
    structure: Opt[Cells.Structure] = Opt.empty,
    data: Opt[Vector[structures.NotebookCell]] = Opt.empty,
    textContent: Opt[Vector[Cells.S0]] = Opt.empty
  )
  object Cells:
    given reader: Reader[structures.NotebookDocumentChangeEvent.Cells] = Pickle.macroR
    given writer: Writer[structures.NotebookDocumentChangeEvent.Cells] = upickle.default.macroW
    /**
     *  @param array
     *    The change to the cell array.
    
     *  @param didOpen
     *    Additional opened cell text documents.
    
     *  @param didClose
     *    Additional closed cell text documents.
    
     */
    case class Structure(
      array: structures.NotebookCellArrayChange,
      didOpen: Opt[Vector[structures.TextDocumentItem]] = Opt.empty,
      didClose: Opt[Vector[structures.TextDocumentIdentifier]] = Opt.empty
    )
    object Structure:
      given reader: Reader[structures.NotebookDocumentChangeEvent.Cells.Structure] = Pickle.macroR
      given writer: Writer[structures.NotebookDocumentChangeEvent.Cells.Structure] = upickle.default.macroW
    case class S0(
      document: structures.VersionedTextDocumentIdentifier,
      changes: Vector[aliases.TextDocumentContentChangeEvent]
    )
    object S0:
      given reader: Reader[structures.NotebookDocumentChangeEvent.Cells.S0] = Pickle.macroR
      given writer: Writer[structures.NotebookDocumentChangeEvent.Cells.S0] = upickle.default.macroW

/**
 *  A literal to identify a notebook document in the client.
 *  
 *  @since 3.17.0

 *  @param uri
 *    The notebook document's uri.

 */
case class NotebookDocumentIdentifier(
  uri: aliases.URI
)
object NotebookDocumentIdentifier:
  given reader: Reader[structures.NotebookDocumentIdentifier] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentIdentifier] = upickle.default.macroW

/**
 *  General parameters to to register for an notification or to register a provider.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again.

 *  @param method
 *    The method / capability to register for.

 *  @param registerOptions
 *    Options necessary for the registration.

 */
case class Registration(
  id: String,
  method: String,
  registerOptions: Opt[ujson.Value] = Opt.empty
)
object Registration:
  given reader: Reader[structures.Registration] = Pickle.macroR
  given writer: Writer[structures.Registration] = upickle.default.macroW

/**
 *  General parameters to unregister a request or notification.

 *  @param id
 *    The id used to unregister the request or notification. Usually an id
 *    provided during the register request.

 *  @param method
 *    The method to unregister for.

 */
case class Unregistration(
  id: String,
  method: String
)
object Unregistration:
  given reader: Reader[structures.Unregistration] = Pickle.macroR
  given writer: Writer[structures.Unregistration] = upickle.default.macroW

/**
 *  The initialize parameters

 *  @param processId
 *    The process Id of the parent process that started
 *    the server.
 *    
 *    Is `null` if the process has not been started by another process.
 *    If the parent process is not alive then the server should exit.

 *  @param clientInfo
 *    Information about the client
 *    
 *    @since 3.15.0

 *  @param locale
 *    The locale the client is currently showing the user interface
 *    in. This must not necessarily be the locale of the operating
 *    system.
 *    
 *    Uses IETF language tags as the value's syntax
 *    (See https://en.wikipedia.org/wiki/IETF_language_tag)
 *    
 *    @since 3.16.0

 *  @param rootPath
 *    The rootPath of the workspace. Is null
 *    if no folder is open.
 *    
 *    @deprecated in favour of rootUri.

 *  @param rootUri
 *    The rootUri of the workspace. Is null if no
 *    folder is open. If both `rootPath` and `rootUri` are set
 *    `rootUri` wins.
 *    
 *    @deprecated in favour of workspaceFolders.

 *  @param capabilities
 *    The capabilities provided by the client (editor or tool)

 *  @param initializationOptions
 *    User provided initialization options.

 *  @param trace
 *    The initial trace setting. If omitted trace is disabled ('off').

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 */
case class _InitializeParams(
  processId: Nullable[Int],
  clientInfo: Opt[_InitializeParams.ClientInfo] = Opt.empty,
  locale: Opt[String] = Opt.empty,
  rootPath: Opt[Nullable[String]] = Opt.empty,
  rootUri: Nullable[RuntimeBase.DocumentUri],
  capabilities: structures.ClientCapabilities,
  initializationOptions: Opt[ujson.Value] = Opt.empty,
  trace: Opt[("off" | "messages" | "compact" | "verbose")] = Opt.empty,
  workDoneToken: Opt[aliases.ProgressToken] = Opt.empty
)
object _InitializeParams:
  private given rd3: Reader[("off" | "messages" | "compact" | "verbose")] = 
    badMerge[("off" | "messages" | "compact" | "verbose")](upickle.default.reader["off"], upickle.default.reader["messages"], upickle.default.reader["compact"], upickle.default.reader["verbose"])
  private given wt3: Writer[("off" | "messages" | "compact" | "verbose")] = 
    upickle.default.writer[ujson.Value].comap[("off" | "messages" | "compact" | "verbose")] { _v => 
      (_v: @unchecked) match 
        case v: "off" => writeJs["off"](v)
        case v: "messages" => writeJs["messages"](v)
        case v: "compact" => writeJs["compact"](v)
        case v: "verbose" => writeJs["verbose"](v)
    }
  given reader: Reader[structures._InitializeParams] = Pickle.macroR
  given writer: Writer[structures._InitializeParams] = upickle.default.macroW
  /**
   *  @param name
   *    The name of the client as defined by the client.
  
   *  @param version
   *    The client's version as defined by the client.
  
   */
  case class ClientInfo(
    name: String,
    version: Opt[String] = Opt.empty
  )
  object ClientInfo:
    given reader: Reader[structures._InitializeParams.ClientInfo] = Pickle.macroR
    given writer: Writer[structures._InitializeParams.ClientInfo] = upickle.default.macroW

/**
 *  @param workspaceFolders
 *    The workspace folders configured in the client when the server starts.
 *    
 *    This property is only available if the client supports workspace folders.
 *    It can be `null` if the client supports workspace folders but none are
 *    configured.
 *    
 *    @since 3.6.0

 */
case class WorkspaceFoldersInitializeParams(
  workspaceFolders: Opt[Nullable[Vector[structures.WorkspaceFolder]]] = Opt.empty
)
object WorkspaceFoldersInitializeParams:
  given reader: Reader[structures.WorkspaceFoldersInitializeParams] = Pickle.macroR
  given writer: Writer[structures.WorkspaceFoldersInitializeParams] = upickle.default.macroW

/**
 *  Defines the capabilities provided by a language
 *  server.

 *  @param positionEncoding
 *    The position encoding the server picked from the encodings offered
 *    by the client via the client capability `general.positionEncodings`.
 *    
 *    If the client didn't provide any position encodings the only valid
 *    value that a server can return is 'utf-16'.
 *    
 *    If omitted it defaults to 'utf-16'.
 *    
 *    @since 3.17.0

 *  @param textDocumentSync
 *    Defines how text documents are synced. Is either a detailed structure
 *    defining each notification or for backwards compatibility the
 *    TextDocumentSyncKind number.

 *  @param notebookDocumentSync
 *    Defines how notebook documents are synced.
 *    
 *    @since 3.17.0

 *  @param completionProvider
 *    The server provides completion support.

 *  @param hoverProvider
 *    The server provides hover support.

 *  @param signatureHelpProvider
 *    The server provides signature help support.

 *  @param declarationProvider
 *    The server provides Goto Declaration support.

 *  @param definitionProvider
 *    The server provides goto definition support.

 *  @param typeDefinitionProvider
 *    The server provides Goto Type Definition support.

 *  @param implementationProvider
 *    The server provides Goto Implementation support.

 *  @param referencesProvider
 *    The server provides find references support.

 *  @param documentHighlightProvider
 *    The server provides document highlight support.

 *  @param documentSymbolProvider
 *    The server provides document symbol support.

 *  @param codeActionProvider
 *    The server provides code actions. CodeActionOptions may only be
 *    specified if the client states that it supports
 *    `codeActionLiteralSupport` in its initial `initialize` request.

 *  @param codeLensProvider
 *    The server provides code lens.

 *  @param documentLinkProvider
 *    The server provides document link support.

 *  @param colorProvider
 *    The server provides color provider support.

 *  @param workspaceSymbolProvider
 *    The server provides workspace symbol support.

 *  @param documentFormattingProvider
 *    The server provides document formatting.

 *  @param documentRangeFormattingProvider
 *    The server provides document range formatting.

 *  @param documentOnTypeFormattingProvider
 *    The server provides document formatting on typing.

 *  @param renameProvider
 *    The server provides rename support. RenameOptions may only be
 *    specified if the client states that it supports
 *    `prepareSupport` in its initial `initialize` request.

 *  @param foldingRangeProvider
 *    The server provides folding provider support.

 *  @param selectionRangeProvider
 *    The server provides selection range support.

 *  @param executeCommandProvider
 *    The server provides execute command support.

 *  @param callHierarchyProvider
 *    The server provides call hierarchy support.
 *    
 *    @since 3.16.0

 *  @param linkedEditingRangeProvider
 *    The server provides linked editing range support.
 *    
 *    @since 3.16.0

 *  @param semanticTokensProvider
 *    The server provides semantic tokens support.
 *    
 *    @since 3.16.0

 *  @param monikerProvider
 *    The server provides moniker support.
 *    
 *    @since 3.16.0

 *  @param typeHierarchyProvider
 *    The server provides type hierarchy support.
 *    
 *    @since 3.17.0

 *  @param inlineValueProvider
 *    The server provides inline values.
 *    
 *    @since 3.17.0

 *  @param inlayHintProvider
 *    The server provides inlay hints.
 *    
 *    @since 3.17.0

 *  @param diagnosticProvider
 *    The server has support for pull model diagnostics.
 *    
 *    @since 3.17.0

 *  @param workspace
 *    Workspace specific server capabilities.

 *  @param experimental
 *    Experimental server capabilities.

 */
case class ServerCapabilities(
  positionEncoding: Opt[enumerations.PositionEncodingKind] = Opt.empty,
  textDocumentSync: Opt[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)] = Opt.empty,
  notebookDocumentSync: Opt[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)] = Opt.empty,
  completionProvider: Opt[structures.CompletionOptions] = Opt.empty,
  hoverProvider: Opt[(Boolean | structures.HoverOptions)] = Opt.empty,
  signatureHelpProvider: Opt[structures.SignatureHelpOptions] = Opt.empty,
  declarationProvider: Opt[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)] = Opt.empty,
  definitionProvider: Opt[(Boolean | structures.DefinitionOptions)] = Opt.empty,
  typeDefinitionProvider: Opt[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)] = Opt.empty,
  implementationProvider: Opt[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)] = Opt.empty,
  referencesProvider: Opt[(Boolean | structures.ReferenceOptions)] = Opt.empty,
  documentHighlightProvider: Opt[(Boolean | structures.DocumentHighlightOptions)] = Opt.empty,
  documentSymbolProvider: Opt[(Boolean | structures.DocumentSymbolOptions)] = Opt.empty,
  codeActionProvider: Opt[(Boolean | structures.CodeActionOptions)] = Opt.empty,
  codeLensProvider: Opt[structures.CodeLensOptions] = Opt.empty,
  documentLinkProvider: Opt[structures.DocumentLinkOptions] = Opt.empty,
  colorProvider: Opt[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)] = Opt.empty,
  workspaceSymbolProvider: Opt[(Boolean | structures.WorkspaceSymbolOptions)] = Opt.empty,
  documentFormattingProvider: Opt[(Boolean | structures.DocumentFormattingOptions)] = Opt.empty,
  documentRangeFormattingProvider: Opt[(Boolean | structures.DocumentRangeFormattingOptions)] = Opt.empty,
  documentOnTypeFormattingProvider: Opt[structures.DocumentOnTypeFormattingOptions] = Opt.empty,
  renameProvider: Opt[(Boolean | structures.RenameOptions)] = Opt.empty,
  foldingRangeProvider: Opt[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)] = Opt.empty,
  selectionRangeProvider: Opt[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)] = Opt.empty,
  executeCommandProvider: Opt[structures.ExecuteCommandOptions] = Opt.empty,
  callHierarchyProvider: Opt[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)] = Opt.empty,
  linkedEditingRangeProvider: Opt[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)] = Opt.empty,
  semanticTokensProvider: Opt[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)] = Opt.empty,
  monikerProvider: Opt[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)] = Opt.empty,
  typeHierarchyProvider: Opt[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)] = Opt.empty,
  inlineValueProvider: Opt[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)] = Opt.empty,
  inlayHintProvider: Opt[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)] = Opt.empty,
  diagnosticProvider: Opt[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)] = Opt.empty,
  workspace: Opt[ServerCapabilities.Workspace] = Opt.empty,
  experimental: Opt[structures.T] = Opt.empty
)
object ServerCapabilities:
  private given rd0: Reader[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)] = 
    badMerge[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)](structures.TextDocumentSyncOptions.reader, enumerations.TextDocumentSyncKind.reader)
  private given wt0: Writer[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)] = 
    upickle.default.writer[ujson.Value].comap[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)] { _v => 
      (_v: @unchecked) match 
        case v: structures.TextDocumentSyncOptions => writeJs[structures.TextDocumentSyncOptions](v)
        case v: enumerations.TextDocumentSyncKind => writeJs[enumerations.TextDocumentSyncKind](v)
    }
  private given rd1: Reader[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)] = 
    badMerge[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)](structures.NotebookDocumentSyncOptions.reader, structures.NotebookDocumentSyncRegistrationOptions.reader)
  private given wt1: Writer[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: structures.NotebookDocumentSyncOptions => writeJs[structures.NotebookDocumentSyncOptions](v)
        case v: structures.NotebookDocumentSyncRegistrationOptions => writeJs[structures.NotebookDocumentSyncRegistrationOptions](v)
    }
  private given rd2: Reader[(Boolean | structures.HoverOptions)] = 
    badMerge[(Boolean | structures.HoverOptions)](upickle.default.reader[Boolean], structures.HoverOptions.reader)
  private given wt2: Writer[(Boolean | structures.HoverOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.HoverOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.HoverOptions => writeJs[structures.HoverOptions](v)
    }
  private given rd3: Reader[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)] = 
    badMerge[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)](upickle.default.reader[Boolean], structures.DeclarationOptions.reader, structures.DeclarationRegistrationOptions.reader)
  private given wt3: Writer[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DeclarationOptions => writeJs[structures.DeclarationOptions](v)
        case v: structures.DeclarationRegistrationOptions => writeJs[structures.DeclarationRegistrationOptions](v)
    }
  private given rd4: Reader[(Boolean | structures.DefinitionOptions)] = 
    badMerge[(Boolean | structures.DefinitionOptions)](upickle.default.reader[Boolean], structures.DefinitionOptions.reader)
  private given wt4: Writer[(Boolean | structures.DefinitionOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DefinitionOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DefinitionOptions => writeJs[structures.DefinitionOptions](v)
    }
  private given rd5: Reader[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)] = 
    badMerge[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)](upickle.default.reader[Boolean], structures.TypeDefinitionOptions.reader, structures.TypeDefinitionRegistrationOptions.reader)
  private given wt5: Writer[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.TypeDefinitionOptions => writeJs[structures.TypeDefinitionOptions](v)
        case v: structures.TypeDefinitionRegistrationOptions => writeJs[structures.TypeDefinitionRegistrationOptions](v)
    }
  private given rd6: Reader[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)] = 
    badMerge[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)](upickle.default.reader[Boolean], structures.ImplementationOptions.reader, structures.ImplementationRegistrationOptions.reader)
  private given wt6: Writer[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.ImplementationOptions => writeJs[structures.ImplementationOptions](v)
        case v: structures.ImplementationRegistrationOptions => writeJs[structures.ImplementationRegistrationOptions](v)
    }
  private given rd7: Reader[(Boolean | structures.ReferenceOptions)] = 
    badMerge[(Boolean | structures.ReferenceOptions)](upickle.default.reader[Boolean], structures.ReferenceOptions.reader)
  private given wt7: Writer[(Boolean | structures.ReferenceOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.ReferenceOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.ReferenceOptions => writeJs[structures.ReferenceOptions](v)
    }
  private given rd8: Reader[(Boolean | structures.DocumentHighlightOptions)] = 
    badMerge[(Boolean | structures.DocumentHighlightOptions)](upickle.default.reader[Boolean], structures.DocumentHighlightOptions.reader)
  private given wt8: Writer[(Boolean | structures.DocumentHighlightOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentHighlightOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DocumentHighlightOptions => writeJs[structures.DocumentHighlightOptions](v)
    }
  private given rd9: Reader[(Boolean | structures.DocumentSymbolOptions)] = 
    badMerge[(Boolean | structures.DocumentSymbolOptions)](upickle.default.reader[Boolean], structures.DocumentSymbolOptions.reader)
  private given wt9: Writer[(Boolean | structures.DocumentSymbolOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentSymbolOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DocumentSymbolOptions => writeJs[structures.DocumentSymbolOptions](v)
    }
  private given rd10: Reader[(Boolean | structures.CodeActionOptions)] = 
    badMerge[(Boolean | structures.CodeActionOptions)](upickle.default.reader[Boolean], structures.CodeActionOptions.reader)
  private given wt10: Writer[(Boolean | structures.CodeActionOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.CodeActionOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.CodeActionOptions => writeJs[structures.CodeActionOptions](v)
    }
  private given rd11: Reader[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)] = 
    badMerge[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)](upickle.default.reader[Boolean], structures.DocumentColorOptions.reader, structures.DocumentColorRegistrationOptions.reader)
  private given wt11: Writer[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DocumentColorOptions => writeJs[structures.DocumentColorOptions](v)
        case v: structures.DocumentColorRegistrationOptions => writeJs[structures.DocumentColorRegistrationOptions](v)
    }
  private given rd12: Reader[(Boolean | structures.WorkspaceSymbolOptions)] = 
    badMerge[(Boolean | structures.WorkspaceSymbolOptions)](upickle.default.reader[Boolean], structures.WorkspaceSymbolOptions.reader)
  private given wt12: Writer[(Boolean | structures.WorkspaceSymbolOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.WorkspaceSymbolOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.WorkspaceSymbolOptions => writeJs[structures.WorkspaceSymbolOptions](v)
    }
  private given rd13: Reader[(Boolean | structures.DocumentFormattingOptions)] = 
    badMerge[(Boolean | structures.DocumentFormattingOptions)](upickle.default.reader[Boolean], structures.DocumentFormattingOptions.reader)
  private given wt13: Writer[(Boolean | structures.DocumentFormattingOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentFormattingOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DocumentFormattingOptions => writeJs[structures.DocumentFormattingOptions](v)
    }
  private given rd14: Reader[(Boolean | structures.DocumentRangeFormattingOptions)] = 
    badMerge[(Boolean | structures.DocumentRangeFormattingOptions)](upickle.default.reader[Boolean], structures.DocumentRangeFormattingOptions.reader)
  private given wt14: Writer[(Boolean | structures.DocumentRangeFormattingOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.DocumentRangeFormattingOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.DocumentRangeFormattingOptions => writeJs[structures.DocumentRangeFormattingOptions](v)
    }
  private given rd15: Reader[(Boolean | structures.RenameOptions)] = 
    badMerge[(Boolean | structures.RenameOptions)](upickle.default.reader[Boolean], structures.RenameOptions.reader)
  private given wt15: Writer[(Boolean | structures.RenameOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.RenameOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.RenameOptions => writeJs[structures.RenameOptions](v)
    }
  private given rd16: Reader[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)] = 
    badMerge[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)](upickle.default.reader[Boolean], structures.FoldingRangeOptions.reader, structures.FoldingRangeRegistrationOptions.reader)
  private given wt16: Writer[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.FoldingRangeOptions => writeJs[structures.FoldingRangeOptions](v)
        case v: structures.FoldingRangeRegistrationOptions => writeJs[structures.FoldingRangeRegistrationOptions](v)
    }
  private given rd17: Reader[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)] = 
    badMerge[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)](upickle.default.reader[Boolean], structures.SelectionRangeOptions.reader, structures.SelectionRangeRegistrationOptions.reader)
  private given wt17: Writer[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.SelectionRangeOptions => writeJs[structures.SelectionRangeOptions](v)
        case v: structures.SelectionRangeRegistrationOptions => writeJs[structures.SelectionRangeRegistrationOptions](v)
    }
  private given rd18: Reader[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)] = 
    badMerge[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)](upickle.default.reader[Boolean], structures.CallHierarchyOptions.reader, structures.CallHierarchyRegistrationOptions.reader)
  private given wt18: Writer[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.CallHierarchyOptions => writeJs[structures.CallHierarchyOptions](v)
        case v: structures.CallHierarchyRegistrationOptions => writeJs[structures.CallHierarchyRegistrationOptions](v)
    }
  private given rd19: Reader[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)] = 
    badMerge[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)](upickle.default.reader[Boolean], structures.LinkedEditingRangeOptions.reader, structures.LinkedEditingRangeRegistrationOptions.reader)
  private given wt19: Writer[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.LinkedEditingRangeOptions => writeJs[structures.LinkedEditingRangeOptions](v)
        case v: structures.LinkedEditingRangeRegistrationOptions => writeJs[structures.LinkedEditingRangeRegistrationOptions](v)
    }
  private given rd20: Reader[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)] = 
    badMerge[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)](structures.SemanticTokensOptions.reader, structures.SemanticTokensRegistrationOptions.reader)
  private given wt20: Writer[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: structures.SemanticTokensOptions => writeJs[structures.SemanticTokensOptions](v)
        case v: structures.SemanticTokensRegistrationOptions => writeJs[structures.SemanticTokensRegistrationOptions](v)
    }
  private given rd21: Reader[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)] = 
    badMerge[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)](upickle.default.reader[Boolean], structures.MonikerOptions.reader, structures.MonikerRegistrationOptions.reader)
  private given wt21: Writer[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.MonikerOptions => writeJs[structures.MonikerOptions](v)
        case v: structures.MonikerRegistrationOptions => writeJs[structures.MonikerRegistrationOptions](v)
    }
  private given rd22: Reader[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)] = 
    badMerge[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)](upickle.default.reader[Boolean], structures.TypeHierarchyOptions.reader, structures.TypeHierarchyRegistrationOptions.reader)
  private given wt22: Writer[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.TypeHierarchyOptions => writeJs[structures.TypeHierarchyOptions](v)
        case v: structures.TypeHierarchyRegistrationOptions => writeJs[structures.TypeHierarchyRegistrationOptions](v)
    }
  private given rd23: Reader[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)] = 
    badMerge[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)](upickle.default.reader[Boolean], structures.InlineValueOptions.reader, structures.InlineValueRegistrationOptions.reader)
  private given wt23: Writer[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.InlineValueOptions => writeJs[structures.InlineValueOptions](v)
        case v: structures.InlineValueRegistrationOptions => writeJs[structures.InlineValueRegistrationOptions](v)
    }
  private given rd24: Reader[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)] = 
    badMerge[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)](upickle.default.reader[Boolean], structures.InlayHintOptions.reader, structures.InlayHintRegistrationOptions.reader)
  private given wt24: Writer[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.InlayHintOptions => writeJs[structures.InlayHintOptions](v)
        case v: structures.InlayHintRegistrationOptions => writeJs[structures.InlayHintRegistrationOptions](v)
    }
  private given rd25: Reader[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)] = 
    badMerge[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)](structures.DiagnosticOptions.reader, structures.DiagnosticRegistrationOptions.reader)
  private given wt25: Writer[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)] = 
    upickle.default.writer[ujson.Value].comap[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)] { _v => 
      (_v: @unchecked) match 
        case v: structures.DiagnosticOptions => writeJs[structures.DiagnosticOptions](v)
        case v: structures.DiagnosticRegistrationOptions => writeJs[structures.DiagnosticRegistrationOptions](v)
    }
  given reader: Reader[structures.ServerCapabilities] = Pickle.macroR
  given writer: Writer[structures.ServerCapabilities] = upickle.default.macroW
  /**
   *  @param workspaceFolders
   *    The server supports workspace folder.
   *    
   *    @since 3.6.0
  
   *  @param fileOperations
   *    The server is interested in notifications/requests for operations on files.
   *    
   *    @since 3.16.0
  
   */
  case class Workspace(
    workspaceFolders: Opt[structures.WorkspaceFoldersServerCapabilities] = Opt.empty,
    fileOperations: Opt[structures.FileOperationOptions] = Opt.empty
  )
  object Workspace:
    given reader: Reader[structures.ServerCapabilities.Workspace] = Pickle.macroR
    given writer: Writer[structures.ServerCapabilities.Workspace] = upickle.default.macroW

/**
 *  A text document identifier to denote a specific version of a text document.

 *  @param version
 *    The version number of this document.

 *  @param uri
 *    The text document's uri.

 */
case class VersionedTextDocumentIdentifier(
  version: Int,
  uri: RuntimeBase.DocumentUri
)
object VersionedTextDocumentIdentifier:
  given reader: Reader[structures.VersionedTextDocumentIdentifier] = Pickle.macroR
  given writer: Writer[structures.VersionedTextDocumentIdentifier] = upickle.default.macroW

/**
 *  Save options.

 *  @param includeText
 *    The client is supposed to include the content on save.

 */
case class SaveOptions(
  includeText: Opt[Boolean] = Opt.empty
)
object SaveOptions:
  given reader: Reader[structures.SaveOptions] = Pickle.macroR
  given writer: Writer[structures.SaveOptions] = upickle.default.macroW

/**
 *  An event describing a file change.

 *  @param uri
 *    The file's uri.

 *  @param type
 *    The change type.

 */
case class FileEvent(
  uri: RuntimeBase.DocumentUri,
  `type`: enumerations.FileChangeType
)
object FileEvent:
  given reader: Reader[structures.FileEvent] = Pickle.macroR
  given writer: Writer[structures.FileEvent] = upickle.default.macroW

/**
 *  @param globPattern
 *    The glob pattern to watch. See {@link GlobPattern glob pattern} for more detail.
 *    
 *    @since 3.17.0 support for relative patterns.

 *  @param kind
 *    The kind of events of interest. If omitted it defaults
 *    to WatchKind.Create | WatchKind.Change | WatchKind.Delete
 *    which is 7.

 */
case class FileSystemWatcher(
  globPattern: aliases.GlobPattern,
  kind: Opt[enumerations.WatchKind] = Opt.empty
)
object FileSystemWatcher:
  given reader: Reader[structures.FileSystemWatcher] = Pickle.macroR
  given writer: Writer[structures.FileSystemWatcher] = upickle.default.macroW

/**
 *  Represents a diagnostic, such as a compiler error or warning. Diagnostic objects
 *  are only valid in the scope of a resource.

 *  @param range
 *    The range at which the message applies

 *  @param severity
 *    The diagnostic's severity. Can be omitted. If omitted it is up to the
 *    client to interpret diagnostics as error, warning, info or hint.

 *  @param code
 *    The diagnostic's code, which usually appear in the user interface.

 *  @param codeDescription
 *    An optional property to describe the error code.
 *    Requires the code field (above) to be present/not null.
 *    
 *    @since 3.16.0

 *  @param source
 *    A human-readable string describing the source of this
 *    diagnostic, e.g. 'typescript' or 'super lint'. It usually
 *    appears in the user interface.

 *  @param message
 *    The diagnostic's message. It usually appears in the user interface

 *  @param tags
 *    Additional metadata about the diagnostic.
 *    
 *    @since 3.15.0

 *  @param relatedInformation
 *    An array of related diagnostic information, e.g. when symbol-names within
 *    a scope collide all definitions can be marked via this property.

 *  @param data
 *    A data entry field that is preserved between a `textDocument/publishDiagnostics`
 *    notification and `textDocument/codeAction` request.
 *    
 *    @since 3.16.0

 */
case class Diagnostic(
  range: structures.Range,
  severity: Opt[enumerations.DiagnosticSeverity] = Opt.empty,
  code: Opt[(Int | String)] = Opt.empty,
  codeDescription: Opt[structures.CodeDescription] = Opt.empty,
  source: Opt[String] = Opt.empty,
  message: String,
  tags: Opt[Vector[enumerations.DiagnosticTag]] = Opt.empty,
  relatedInformation: Opt[Vector[structures.DiagnosticRelatedInformation]] = Opt.empty,
  data: Opt[ujson.Value] = Opt.empty
)
object Diagnostic:
  private given rd0: Reader[(Int | String)] = 
    badMerge[(Int | String)](intCodec, stringCodec)
  private given wt0: Writer[(Int | String)] = 
    upickle.default.writer[ujson.Value].comap[(Int | String)] { _v => 
      (_v: @unchecked) match 
        case v: Int => writeJs[Int](v)
        case v: String => writeJs[String](v)
    }
  given reader: Reader[structures.Diagnostic] = Pickle.macroR
  given writer: Writer[structures.Diagnostic] = upickle.default.macroW

/**
 *  Contains additional information about the context in which a completion request is triggered.

 *  @param triggerKind
 *    How the completion was triggered.

 *  @param triggerCharacter
 *    The trigger character (a single character) that has trigger code complete.
 *    Is undefined if `triggerKind !== CompletionTriggerKind.TriggerCharacter`

 */
case class CompletionContext(
  triggerKind: enumerations.CompletionTriggerKind,
  triggerCharacter: Opt[String] = Opt.empty
)
object CompletionContext:
  given reader: Reader[structures.CompletionContext] = Pickle.macroR
  given writer: Writer[structures.CompletionContext] = upickle.default.macroW

/**
 *  Additional details for a completion item label.
 *  
 *  @since 3.17.0

 *  @param detail
 *    An optional string which is rendered less prominently directly after {@link CompletionItem.label label},
 *    without any spacing. Should be used for function signatures and type annotations.

 *  @param description
 *    An optional string which is rendered less prominently after {@link CompletionItem.detail}. Should be used
 *    for fully qualified names and file paths.

 */
case class CompletionItemLabelDetails(
  detail: Opt[String] = Opt.empty,
  description: Opt[String] = Opt.empty
)
object CompletionItemLabelDetails:
  given reader: Reader[structures.CompletionItemLabelDetails] = Pickle.macroR
  given writer: Writer[structures.CompletionItemLabelDetails] = upickle.default.macroW

/**
 *  A special text edit to provide an insert and a replace operation.
 *  
 *  @since 3.16.0

 *  @param newText
 *    The string to be inserted.

 *  @param insert
 *    The range if the insert is requested

 *  @param replace
 *    The range if the replace is requested.

 */
case class InsertReplaceEdit(
  newText: String,
  insert: structures.Range,
  replace: structures.Range
)
object InsertReplaceEdit:
  given reader: Reader[structures.InsertReplaceEdit] = Pickle.macroR
  given writer: Writer[structures.InsertReplaceEdit] = upickle.default.macroW

/**
 *  Completion options.

 *  @param triggerCharacters
 *    Most tools trigger completion request automatically without explicitly requesting
 *    it using a keyboard shortcut (e.g. Ctrl+Space). Typically they do so when the user
 *    starts to type an identifier. For example if the user types `c` in a JavaScript file
 *    code complete will automatically pop up present `console` besides others as a
 *    completion item. Characters that make up identifiers don't need to be listed here.
 *    
 *    If code complete should automatically be trigger on characters not being valid inside
 *    an identifier (for example `.` in JavaScript) list them in `triggerCharacters`.

 *  @param allCommitCharacters
 *    The list of all possible characters that commit a completion. This field can be used
 *    if clients don't support individual commit characters per completion item. See
 *    `ClientCapabilities.textDocument.completion.completionItem.commitCharactersSupport`
 *    
 *    If a server provides both `allCommitCharacters` and commit characters on an individual
 *    completion item the ones on the completion item win.
 *    
 *    @since 3.2.0

 *  @param resolveProvider
 *    The server provides support to resolve additional
 *    information for a completion item.

 *  @param completionItem
 *    The server supports the following `CompletionItem` specific
 *    capabilities.
 *    
 *    @since 3.17.0

 *  @param workDoneProgress
 */
case class CompletionOptions(
  triggerCharacters: Opt[Vector[String]] = Opt.empty,
  allCommitCharacters: Opt[Vector[String]] = Opt.empty,
  resolveProvider: Opt[Boolean] = Opt.empty,
  completionItem: Opt[CompletionOptions.CompletionItem] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object CompletionOptions:
  given reader: Reader[structures.CompletionOptions] = Pickle.macroR
  given writer: Writer[structures.CompletionOptions] = upickle.default.macroW
  /**
   *  @param labelDetailsSupport
   *    The server has support for completion item label
   *    details (see also `CompletionItemLabelDetails`) when
   *    receiving a completion item in a resolve call.
   *    
   *    @since 3.17.0
  
   */
  case class CompletionItem(
    labelDetailsSupport: Opt[Boolean] = Opt.empty
  )
  object CompletionItem:
    given reader: Reader[structures.CompletionOptions.CompletionItem] = Pickle.macroR
    given writer: Writer[structures.CompletionOptions.CompletionItem] = upickle.default.macroW

/**
 *  Hover options.

 *  @param workDoneProgress
 */
case class HoverOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object HoverOptions:
  given reader: Reader[structures.HoverOptions] = Pickle.macroR
  given writer: Writer[structures.HoverOptions] = upickle.default.macroW

/**
 *  Additional information about the context in which a signature help request was triggered.
 *  
 *  @since 3.15.0

 *  @param triggerKind
 *    Action that caused signature help to be triggered.

 *  @param triggerCharacter
 *    Character that caused signature help to be triggered.
 *    
 *    This is undefined when `triggerKind !== SignatureHelpTriggerKind.TriggerCharacter`

 *  @param isRetrigger
 *    `true` if signature help was already showing when it was triggered.
 *    
 *    Retriggers occurs when the signature help is already active and can be caused by actions such as
 *    typing a trigger character, a cursor move, or document content changes.

 *  @param activeSignatureHelp
 *    The currently active `SignatureHelp`.
 *    
 *    The `activeSignatureHelp` has its `SignatureHelp.activeSignature` field updated based on
 *    the user navigating through available signatures.

 */
case class SignatureHelpContext(
  triggerKind: enumerations.SignatureHelpTriggerKind,
  triggerCharacter: Opt[String] = Opt.empty,
  isRetrigger: Boolean,
  activeSignatureHelp: Opt[structures.SignatureHelp] = Opt.empty
)
object SignatureHelpContext:
  given reader: Reader[structures.SignatureHelpContext] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpContext] = upickle.default.macroW

/**
 *  Represents the signature of something callable. A signature
 *  can have a label, like a function-name, a doc-comment, and
 *  a set of parameters.

 *  @param label
 *    The label of this signature. Will be shown in
 *    the UI.

 *  @param documentation
 *    The human-readable doc-comment of this signature. Will be shown
 *    in the UI but can be omitted.

 *  @param parameters
 *    The parameters of this signature.

 *  @param activeParameter
 *    The index of the active parameter.
 *    
 *    If provided, this is used in place of `SignatureHelp.activeParameter`.
 *    
 *    @since 3.16.0

 */
case class SignatureInformation(
  label: String,
  documentation: Opt[(String | structures.MarkupContent)] = Opt.empty,
  parameters: Opt[Vector[structures.ParameterInformation]] = Opt.empty,
  activeParameter: Opt[RuntimeBase.uinteger] = Opt.empty
)
object SignatureInformation:
  private given rd0: Reader[(String | structures.MarkupContent)] = 
    badMerge[(String | structures.MarkupContent)](stringCodec, structures.MarkupContent.reader)
  private given wt0: Writer[(String | structures.MarkupContent)] = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: structures.MarkupContent => writeJs[structures.MarkupContent](v)
    }
  given reader: Reader[structures.SignatureInformation] = Pickle.macroR
  given writer: Writer[structures.SignatureInformation] = upickle.default.macroW

/**
 *  Server Capabilities for a [SignatureHelpRequest](#SignatureHelpRequest).

 *  @param triggerCharacters
 *    List of characters that trigger signature help automatically.

 *  @param retriggerCharacters
 *    List of characters that re-trigger signature help.
 *    
 *    These trigger characters are only active when signature help is already showing. All trigger characters
 *    are also counted as re-trigger characters.
 *    
 *    @since 3.15.0

 *  @param workDoneProgress
 */
case class SignatureHelpOptions(
  triggerCharacters: Opt[Vector[String]] = Opt.empty,
  retriggerCharacters: Opt[Vector[String]] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object SignatureHelpOptions:
  given reader: Reader[structures.SignatureHelpOptions] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpOptions] = upickle.default.macroW

/**
 *  Server Capabilities for a [DefinitionRequest](#DefinitionRequest).

 *  @param workDoneProgress
 */
case class DefinitionOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object DefinitionOptions:
  given reader: Reader[structures.DefinitionOptions] = Pickle.macroR
  given writer: Writer[structures.DefinitionOptions] = upickle.default.macroW

/**
 *  Value-object that contains additional information when
 *  requesting references.

 *  @param includeDeclaration
 *    Include the declaration of the current symbol.

 */
case class ReferenceContext(
  includeDeclaration: Boolean
)
object ReferenceContext:
  given reader: Reader[structures.ReferenceContext] = Pickle.macroR
  given writer: Writer[structures.ReferenceContext] = upickle.default.macroW

/**
 *  Reference options.

 *  @param workDoneProgress
 */
case class ReferenceOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object ReferenceOptions:
  given reader: Reader[structures.ReferenceOptions] = Pickle.macroR
  given writer: Writer[structures.ReferenceOptions] = upickle.default.macroW

/**
 *  Provider options for a [DocumentHighlightRequest](#DocumentHighlightRequest).

 *  @param workDoneProgress
 */
case class DocumentHighlightOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object DocumentHighlightOptions:
  given reader: Reader[structures.DocumentHighlightOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlightOptions] = upickle.default.macroW

/**
 *  A base for all symbol information.

 *  @param name
 *    The name of this symbol.

 *  @param kind
 *    The kind of this symbol.

 *  @param tags
 *    Tags for this symbol.
 *    
 *    @since 3.16.0

 *  @param containerName
 *    The name of the symbol containing this symbol. This information is for
 *    user interface purposes (e.g. to render a qualifier in the user interface
 *    if necessary). It can't be used to re-infer a hierarchy for the document
 *    symbols.

 */
case class BaseSymbolInformation(
  name: String,
  kind: enumerations.SymbolKind,
  tags: Opt[Vector[enumerations.SymbolTag]] = Opt.empty,
  containerName: Opt[String] = Opt.empty
)
object BaseSymbolInformation:
  given reader: Reader[structures.BaseSymbolInformation] = Pickle.macroR
  given writer: Writer[structures.BaseSymbolInformation] = upickle.default.macroW

/**
 *  Provider options for a [DocumentSymbolRequest](#DocumentSymbolRequest).

 *  @param label
 *    A human-readable string that is shown when multiple outlines trees
 *    are shown for the same document.
 *    
 *    @since 3.16.0

 *  @param workDoneProgress
 */
case class DocumentSymbolOptions(
  label: Opt[String] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object DocumentSymbolOptions:
  given reader: Reader[structures.DocumentSymbolOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbolOptions] = upickle.default.macroW

/**
 *  Contains additional diagnostic information about the context in which
 *  a [code action](#CodeActionProvider.provideCodeActions) is run.

 *  @param diagnostics
 *    An array of diagnostics known on the client side overlapping the range provided to the
 *    `textDocument/codeAction` request. They are provided so that the server knows which
 *    errors are currently presented to the user for the given range. There is no guarantee
 *    that these accurately reflect the error state of the resource. The primary parameter
 *    to compute code actions is the provided range.

 *  @param only
 *    Requested kind of actions to return.
 *    
 *    Actions not of this kind are filtered out by the client before being shown. So servers
 *    can omit computing them.

 *  @param triggerKind
 *    The reason why code actions were requested.
 *    
 *    @since 3.17.0

 */
case class CodeActionContext(
  diagnostics: Vector[structures.Diagnostic],
  only: Opt[Vector[enumerations.CodeActionKind]] = Opt.empty,
  triggerKind: Opt[enumerations.CodeActionTriggerKind] = Opt.empty
)
object CodeActionContext:
  given reader: Reader[structures.CodeActionContext] = Pickle.macroR
  given writer: Writer[structures.CodeActionContext] = upickle.default.macroW

/**
 *  Provider options for a [CodeActionRequest](#CodeActionRequest).

 *  @param codeActionKinds
 *    CodeActionKinds that this server may return.
 *    
 *    The list of kinds may be generic, such as `CodeActionKind.Refactor`, or the server
 *    may list out every specific kind they provide.

 *  @param resolveProvider
 *    The server provides support to resolve additional
 *    information for a code action.
 *    
 *    @since 3.16.0

 *  @param workDoneProgress
 */
case class CodeActionOptions(
  codeActionKinds: Opt[Vector[enumerations.CodeActionKind]] = Opt.empty,
  resolveProvider: Opt[Boolean] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object CodeActionOptions:
  given reader: Reader[structures.CodeActionOptions] = Pickle.macroR
  given writer: Writer[structures.CodeActionOptions] = upickle.default.macroW

/**
 *  Server capabilities for a [WorkspaceSymbolRequest](#WorkspaceSymbolRequest).

 *  @param resolveProvider
 *    The server provides support to resolve additional
 *    information for a workspace symbol.
 *    
 *    @since 3.17.0

 *  @param workDoneProgress
 */
case class WorkspaceSymbolOptions(
  resolveProvider: Opt[Boolean] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object WorkspaceSymbolOptions:
  given reader: Reader[structures.WorkspaceSymbolOptions] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbolOptions] = upickle.default.macroW

/**
 *  Code Lens provider options of a [CodeLensRequest](#CodeLensRequest).

 *  @param resolveProvider
 *    Code lens has a resolve provider as well.

 *  @param workDoneProgress
 */
case class CodeLensOptions(
  resolveProvider: Opt[Boolean] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object CodeLensOptions:
  given reader: Reader[structures.CodeLensOptions] = Pickle.macroR
  given writer: Writer[structures.CodeLensOptions] = upickle.default.macroW

/**
 *  Provider options for a [DocumentLinkRequest](#DocumentLinkRequest).

 *  @param resolveProvider
 *    Document links have a resolve provider as well.

 *  @param workDoneProgress
 */
case class DocumentLinkOptions(
  resolveProvider: Opt[Boolean] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object DocumentLinkOptions:
  given reader: Reader[structures.DocumentLinkOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentLinkOptions] = upickle.default.macroW

/**
 *  Value-object describing what options formatting should use.

 *  @param tabSize
 *    Size of a tab in spaces.

 *  @param insertSpaces
 *    Prefer spaces over tabs.

 *  @param trimTrailingWhitespace
 *    Trim trailing whitespace on a line.
 *    
 *    @since 3.15.0

 *  @param insertFinalNewline
 *    Insert a newline character at the end of the file if one does not exist.
 *    
 *    @since 3.15.0

 *  @param trimFinalNewlines
 *    Trim all newlines after the final newline at the end of the file.
 *    
 *    @since 3.15.0

 */
case class FormattingOptions(
  tabSize: RuntimeBase.uinteger,
  insertSpaces: Boolean,
  trimTrailingWhitespace: Opt[Boolean] = Opt.empty,
  insertFinalNewline: Opt[Boolean] = Opt.empty,
  trimFinalNewlines: Opt[Boolean] = Opt.empty
)
object FormattingOptions:
  given reader: Reader[structures.FormattingOptions] = Pickle.macroR
  given writer: Writer[structures.FormattingOptions] = upickle.default.macroW

/**
 *  Provider options for a [DocumentFormattingRequest](#DocumentFormattingRequest).

 *  @param workDoneProgress
 */
case class DocumentFormattingOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object DocumentFormattingOptions:
  given reader: Reader[structures.DocumentFormattingOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentFormattingOptions] = upickle.default.macroW

/**
 *  Provider options for a [DocumentRangeFormattingRequest](#DocumentRangeFormattingRequest).

 *  @param workDoneProgress
 */
case class DocumentRangeFormattingOptions(
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object DocumentRangeFormattingOptions:
  given reader: Reader[structures.DocumentRangeFormattingOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentRangeFormattingOptions] = upickle.default.macroW

/**
 *  Provider options for a [DocumentOnTypeFormattingRequest](#DocumentOnTypeFormattingRequest).

 *  @param firstTriggerCharacter
 *    A character on which formatting should be triggered, like `{`.

 *  @param moreTriggerCharacter
 *    More trigger characters.

 */
case class DocumentOnTypeFormattingOptions(
  firstTriggerCharacter: String,
  moreTriggerCharacter: Opt[Vector[String]] = Opt.empty
)
object DocumentOnTypeFormattingOptions:
  given reader: Reader[structures.DocumentOnTypeFormattingOptions] = Pickle.macroR
  given writer: Writer[structures.DocumentOnTypeFormattingOptions] = upickle.default.macroW

/**
 *  Provider options for a [RenameRequest](#RenameRequest).

 *  @param prepareProvider
 *    Renames should be checked and tested before being executed.
 *    
 *    @since version 3.12.0

 *  @param workDoneProgress
 */
case class RenameOptions(
  prepareProvider: Opt[Boolean] = Opt.empty,
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object RenameOptions:
  given reader: Reader[structures.RenameOptions] = Pickle.macroR
  given writer: Writer[structures.RenameOptions] = upickle.default.macroW

/**
 *  The server capabilities of a [ExecuteCommandRequest](#ExecuteCommandRequest).

 *  @param commands
 *    The commands to be executed on the server

 *  @param workDoneProgress
 */
case class ExecuteCommandOptions(
  commands: Vector[String],
  workDoneProgress: Opt[Boolean] = Opt.empty
)
object ExecuteCommandOptions:
  given reader: Reader[structures.ExecuteCommandOptions] = Pickle.macroR
  given writer: Writer[structures.ExecuteCommandOptions] = upickle.default.macroW

/**
 *  @since 3.16.0

 *  @param tokenTypes
 *    The token types a server uses.

 *  @param tokenModifiers
 *    The token modifiers a server uses.

 */
case class SemanticTokensLegend(
  tokenTypes: Vector[String],
  tokenModifiers: Vector[String]
)
object SemanticTokensLegend:
  given reader: Reader[structures.SemanticTokensLegend] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensLegend] = upickle.default.macroW

/**
 *  A text document identifier to optionally denote a specific version of a text document.

 *  @param version
 *    The version number of this document. If a versioned text document identifier
 *    is sent from the server to the client and the file is not open in the editor
 *    (the server has not received an open notification before) the server can send
 *    `null` to indicate that the version is unknown and the content on disk is the
 *    truth (as specified with document content ownership).

 *  @param uri
 *    The text document's uri.

 */
case class OptionalVersionedTextDocumentIdentifier(
  version: Nullable[Int],
  uri: RuntimeBase.DocumentUri
)
object OptionalVersionedTextDocumentIdentifier:
  given reader: Reader[structures.OptionalVersionedTextDocumentIdentifier] = Pickle.macroR
  given writer: Writer[structures.OptionalVersionedTextDocumentIdentifier] = upickle.default.macroW

/**
 *  A special text edit with an additional change annotation.
 *  
 *  @since 3.16.0.

 *  @param annotationId
 *    The actual identifier of the change annotation

 *  @param range
 *    The range of the text document to be manipulated. To insert
 *    text into a document create a range where start === end.

 *  @param newText
 *    The string to be inserted. For delete operations use an
 *    empty string.

 */
case class AnnotatedTextEdit(
  annotationId: aliases.ChangeAnnotationIdentifier,
  range: structures.Range,
  newText: String
)
object AnnotatedTextEdit:
  given reader: Reader[structures.AnnotatedTextEdit] = Pickle.macroR
  given writer: Writer[structures.AnnotatedTextEdit] = upickle.default.macroW

/**
 *  A generic resource operation.

 *  @param kind
 *    The resource operation kind.

 *  @param annotationId
 *    An optional annotation identifier describing the operation.
 *    
 *    @since 3.16.0

 */
case class ResourceOperation(
  kind: String,
  annotationId: Opt[aliases.ChangeAnnotationIdentifier] = Opt.empty
)
object ResourceOperation:
  given reader: Reader[structures.ResourceOperation] = Pickle.macroR
  given writer: Writer[structures.ResourceOperation] = upickle.default.macroW

/**
 *  Options to create a file.

 *  @param overwrite
 *    Overwrite existing file. Overwrite wins over `ignoreIfExists`

 *  @param ignoreIfExists
 *    Ignore if exists.

 */
case class CreateFileOptions(
  overwrite: Opt[Boolean] = Opt.empty,
  ignoreIfExists: Opt[Boolean] = Opt.empty
)
object CreateFileOptions:
  given reader: Reader[structures.CreateFileOptions] = Pickle.macroR
  given writer: Writer[structures.CreateFileOptions] = upickle.default.macroW

/**
 *  Rename file options

 *  @param overwrite
 *    Overwrite target if existing. Overwrite wins over `ignoreIfExists`

 *  @param ignoreIfExists
 *    Ignores if target exists.

 */
case class RenameFileOptions(
  overwrite: Opt[Boolean] = Opt.empty,
  ignoreIfExists: Opt[Boolean] = Opt.empty
)
object RenameFileOptions:
  given reader: Reader[structures.RenameFileOptions] = Pickle.macroR
  given writer: Writer[structures.RenameFileOptions] = upickle.default.macroW

/**
 *  Delete file options

 *  @param recursive
 *    Delete the content recursively if a folder is denoted.

 *  @param ignoreIfNotExists
 *    Ignore the operation if the file doesn't exist.

 */
case class DeleteFileOptions(
  recursive: Opt[Boolean] = Opt.empty,
  ignoreIfNotExists: Opt[Boolean] = Opt.empty
)
object DeleteFileOptions:
  given reader: Reader[structures.DeleteFileOptions] = Pickle.macroR
  given writer: Writer[structures.DeleteFileOptions] = upickle.default.macroW

/**
 *  A pattern to describe in which file operation requests or notifications
 *  the server is interested in receiving.
 *  
 *  @since 3.16.0

 *  @param glob
 *    The glob pattern to match. Glob patterns can have the following syntax:
 *    - `*` to match one or more characters in a path segment
 *    - `?` to match on one character in a path segment
 *    - `**` to match any number of path segments, including none
 *    - `{}` to group sub patterns into an OR expression. (e.g. `**​.{ts,js}` matches all TypeScript and JavaScript files)
 *    - `[]` to declare a range of characters to match in a path segment (e.g., `example.[0-9]` to match on `example.0`, `example.1`, …)
 *    - `[!...]` to negate a range of characters to match in a path segment (e.g., `example.[!0-9]` to match on `example.a`, `example.b`, but not `example.0`)

 *  @param matches
 *    Whether to match files or folders with this pattern.
 *    
 *    Matches both if undefined.

 *  @param options
 *    Additional options used during matching.

 */
case class FileOperationPattern(
  glob: String,
  matches: Opt[enumerations.FileOperationPatternKind] = Opt.empty,
  options: Opt[structures.FileOperationPatternOptions] = Opt.empty
)
object FileOperationPattern:
  given reader: Reader[structures.FileOperationPattern] = Pickle.macroR
  given writer: Writer[structures.FileOperationPattern] = upickle.default.macroW

/**
 *  A full document diagnostic report for a workspace diagnostic result.
 *  
 *  @since 3.17.0

 *  @param uri
 *    The URI for which diagnostic information is reported.

 *  @param version
 *    The version number for which the diagnostics are reported.
 *    If the document is not marked as open `null` can be provided.

 *  @param kind
 *    A full document diagnostic report.

 *  @param resultId
 *    An optional result id. If provided it will
 *    be sent on the next diagnostic request for the
 *    same document.

 *  @param items
 *    The actual items.

 */
case class WorkspaceFullDocumentDiagnosticReport(
  uri: RuntimeBase.DocumentUri,
  version: Nullable[Int],
  kind: "full",
  resultId: Opt[String] = Opt.empty,
  items: Vector[structures.Diagnostic]
)
object WorkspaceFullDocumentDiagnosticReport:
  given reader: Reader[structures.WorkspaceFullDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.WorkspaceFullDocumentDiagnosticReport] = upickle.default.macroW

/**
 *  An unchanged document diagnostic report for a workspace diagnostic result.
 *  
 *  @since 3.17.0

 *  @param uri
 *    The URI for which diagnostic information is reported.

 *  @param version
 *    The version number for which the diagnostics are reported.
 *    If the document is not marked as open `null` can be provided.

 *  @param kind
 *    A document diagnostic report indicating
 *    no changes to the last result. A server can
 *    only return `unchanged` if result ids are
 *    provided.

 *  @param resultId
 *    A result id which will be sent on the next
 *    diagnostic request for the same document.

 */
case class WorkspaceUnchangedDocumentDiagnosticReport(
  uri: RuntimeBase.DocumentUri,
  version: Nullable[Int],
  kind: "unchanged",
  resultId: String
)
object WorkspaceUnchangedDocumentDiagnosticReport:
  given reader: Reader[structures.WorkspaceUnchangedDocumentDiagnosticReport] = Pickle.macroR
  given writer: Writer[structures.WorkspaceUnchangedDocumentDiagnosticReport] = upickle.default.macroW

/**
 *  LSP object definition.
 *  @since 3.17.0

 */
case class LSPObject(
)
object LSPObject:
  given reader: Reader[structures.LSPObject] = Pickle.macroR
  given writer: Writer[structures.LSPObject] = upickle.default.macroW

/**
 *  A notebook cell.
 *  
 *  A cell's document URI must be unique across ALL notebook
 *  cells and can therefore be used to uniquely identify a
 *  notebook cell or the cell's text document.
 *  
 *  @since 3.17.0

 *  @param kind
 *    The cell's kind

 *  @param document
 *    The URI of the cell's text document
 *    content.

 *  @param metadata
 *    Additional metadata stored with the cell.
 *    
 *    Note: should always be an object literal (e.g. LSPObject)

 *  @param executionSummary
 *    Additional execution summary information
 *    if supported by the client.

 */
case class NotebookCell(
  kind: enumerations.NotebookCellKind,
  document: RuntimeBase.DocumentUri,
  metadata: Opt[structures.LSPObject] = Opt.empty,
  executionSummary: Opt[structures.ExecutionSummary] = Opt.empty
)
object NotebookCell:
  given reader: Reader[structures.NotebookCell] = Pickle.macroR
  given writer: Writer[structures.NotebookCell] = upickle.default.macroW

/**
 *  A change describing how to move a `NotebookCell`
 *  array from state S to S'.
 *  
 *  @since 3.17.0

 *  @param start
 *    The start oftest of the cell that changed.

 *  @param deleteCount
 *    The deleted cells

 *  @param cells
 *    The new cells, if any

 */
case class NotebookCellArrayChange(
  start: RuntimeBase.uinteger,
  deleteCount: RuntimeBase.uinteger,
  cells: Opt[Vector[structures.NotebookCell]] = Opt.empty
)
object NotebookCellArrayChange:
  given reader: Reader[structures.NotebookCellArrayChange] = Pickle.macroR
  given writer: Writer[structures.NotebookCellArrayChange] = upickle.default.macroW

/**
 *  Defines the capabilities provided by the client.

 *  @param workspace
 *    Workspace specific client capabilities.

 *  @param textDocument
 *    Text document specific client capabilities.

 *  @param notebookDocument
 *    Capabilities specific to the notebook document support.
 *    
 *    @since 3.17.0

 *  @param window
 *    Window specific client capabilities.

 *  @param general
 *    General client capabilities.
 *    
 *    @since 3.16.0

 *  @param experimental
 *    Experimental client capabilities.

 */
case class ClientCapabilities(
  workspace: Opt[structures.WorkspaceClientCapabilities] = Opt.empty,
  textDocument: Opt[structures.TextDocumentClientCapabilities] = Opt.empty,
  notebookDocument: Opt[structures.NotebookDocumentClientCapabilities] = Opt.empty,
  window: Opt[structures.WindowClientCapabilities] = Opt.empty,
  general: Opt[structures.GeneralClientCapabilities] = Opt.empty,
  experimental: Opt[ujson.Value] = Opt.empty
)
object ClientCapabilities:
  given reader: Reader[structures.ClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ClientCapabilities] = upickle.default.macroW

/**
 *  @param openClose
 *    Open and close notifications are sent to the server. If omitted open close notifications should not
 *    be sent.

 *  @param change
 *    Change notifications are sent to the server. See TextDocumentSyncKind.None, TextDocumentSyncKind.Full
 *    and TextDocumentSyncKind.Incremental. If omitted it defaults to TextDocumentSyncKind.None.

 *  @param willSave
 *    If present will save notifications are sent to the server. If omitted the notification should not be
 *    sent.

 *  @param willSaveWaitUntil
 *    If present will save wait until requests are sent to the server. If omitted the request should not be
 *    sent.

 *  @param save
 *    If present save notifications are sent to the server. If omitted the notification should not be
 *    sent.

 */
case class TextDocumentSyncOptions(
  openClose: Opt[Boolean] = Opt.empty,
  change: Opt[enumerations.TextDocumentSyncKind] = Opt.empty,
  willSave: Opt[Boolean] = Opt.empty,
  willSaveWaitUntil: Opt[Boolean] = Opt.empty,
  save: Opt[(Boolean | structures.SaveOptions)] = Opt.empty
)
object TextDocumentSyncOptions:
  private given rd0: Reader[(Boolean | structures.SaveOptions)] = 
    badMerge[(Boolean | structures.SaveOptions)](upickle.default.reader[Boolean], structures.SaveOptions.reader)
  private given wt0: Writer[(Boolean | structures.SaveOptions)] = 
    upickle.default.writer[ujson.Value].comap[(Boolean | structures.SaveOptions)] { _v => 
      (_v: @unchecked) match 
        case v: Boolean => writeJs[Boolean](v)
        case v: structures.SaveOptions => writeJs[structures.SaveOptions](v)
    }
  given reader: Reader[structures.TextDocumentSyncOptions] = Pickle.macroR
  given writer: Writer[structures.TextDocumentSyncOptions] = upickle.default.macroW

/**
 *  Options specific to a notebook plus its cells
 *  to be synced to the server.
 *  
 *  If a selector provides a notebook document
 *  filter but no cell selector all cells of a
 *  matching notebook document will be synced.
 *  
 *  If a selector provides no notebook document
 *  filter but only a cell selector all notebook
 *  document that contain at least one matching
 *  cell will be synced.
 *  
 *  @since 3.17.0

 *  @param notebookSelector
 *    The notebooks to be synced

 *  @param save
 *    Whether save notification should be forwarded to
 *    the server. Will only be honored if mode === `notebook`.

 */
case class NotebookDocumentSyncOptions(
  notebookSelector: Vector[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)],
  save: Opt[Boolean] = Opt.empty
)
object NotebookDocumentSyncOptions:
  private given rd0: Reader[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)] = 
    badMerge[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)](NotebookDocumentSyncOptions.S0.reader, NotebookDocumentSyncOptions.S1.reader)
  private given wt0: Writer[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)] = 
    upickle.default.writer[ujson.Value].comap[(NotebookDocumentSyncOptions.S0 | NotebookDocumentSyncOptions.S1)] { _v => 
      (_v: @unchecked) match 
        case v: NotebookDocumentSyncOptions.S0 => writeJs[NotebookDocumentSyncOptions.S0](v)
        case v: NotebookDocumentSyncOptions.S1 => writeJs[NotebookDocumentSyncOptions.S1](v)
    }
  given reader: Reader[structures.NotebookDocumentSyncOptions] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncOptions] = upickle.default.macroW
  /**
   *  @param notebook
   *    The notebook to be synced If a string
   *    value is provided it matches against the
   *    notebook type. '*' matches every notebook.
  
   *  @param cells
   *    The cells of the matching notebook to be synced.
  
   */
  case class S0(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Opt[Vector[S0.S0]] = Opt.empty
  )
  object S0:
    private given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = 
      badMerge[(String | aliases.NotebookDocumentFilter)](stringCodec, aliases.NotebookDocumentFilter.reader)
    private given wt0: Writer[(String | aliases.NotebookDocumentFilter)] = 
      upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { _v => 
        (_v: @unchecked) match 
          case v: String => writeJs[String](v)
          case v: aliases.NotebookDocumentFilter => writeJs[aliases.NotebookDocumentFilter](v)
      }
    given reader: Reader[structures.NotebookDocumentSyncOptions.S0] = Pickle.macroR
    given writer: Writer[structures.NotebookDocumentSyncOptions.S0] = upickle.default.macroW
    case class S0(
      language: String
    )
    object S0:
      given reader: Reader[structures.NotebookDocumentSyncOptions.S0.S0] = Pickle.macroR
      given writer: Writer[structures.NotebookDocumentSyncOptions.S0.S0] = upickle.default.macroW
  /**
   *  @param notebook
   *    The notebook to be synced If a string
   *    value is provided it matches against the
   *    notebook type. '*' matches every notebook.
  
   *  @param cells
   *    The cells of the matching notebook to be synced.
  
   */
  case class S1(
    notebook: Opt[(String | aliases.NotebookDocumentFilter)] = Opt.empty,
    cells: Vector[S1.S0]
  )
  object S1:
    private given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = 
      badMerge[(String | aliases.NotebookDocumentFilter)](stringCodec, aliases.NotebookDocumentFilter.reader)
    private given wt0: Writer[(String | aliases.NotebookDocumentFilter)] = 
      upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { _v => 
        (_v: @unchecked) match 
          case v: String => writeJs[String](v)
          case v: aliases.NotebookDocumentFilter => writeJs[aliases.NotebookDocumentFilter](v)
      }
    given reader: Reader[structures.NotebookDocumentSyncOptions.S1] = Pickle.macroR
    given writer: Writer[structures.NotebookDocumentSyncOptions.S1] = upickle.default.macroW
    case class S0(
      language: String
    )
    object S0:
      given reader: Reader[structures.NotebookDocumentSyncOptions.S1.S0] = Pickle.macroR
      given writer: Writer[structures.NotebookDocumentSyncOptions.S1.S0] = upickle.default.macroW

/**
 *  Registration options specific to a notebook.
 *  
 *  @since 3.17.0

 *  @param notebookSelector
 *    The notebooks to be synced

 *  @param save
 *    Whether save notification should be forwarded to
 *    the server. Will only be honored if mode === `notebook`.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class NotebookDocumentSyncRegistrationOptions(
  notebookSelector: Vector[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)],
  save: Opt[Boolean] = Opt.empty,
  id: Opt[String] = Opt.empty
)
object NotebookDocumentSyncRegistrationOptions:
  private given rd0: Reader[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)] = 
    badMerge[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)](NotebookDocumentSyncRegistrationOptions.S0.reader, NotebookDocumentSyncRegistrationOptions.S1.reader)
  private given wt0: Writer[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)] = 
    upickle.default.writer[ujson.Value].comap[(NotebookDocumentSyncRegistrationOptions.S0 | NotebookDocumentSyncRegistrationOptions.S1)] { _v => 
      (_v: @unchecked) match 
        case v: NotebookDocumentSyncRegistrationOptions.S0 => writeJs[NotebookDocumentSyncRegistrationOptions.S0](v)
        case v: NotebookDocumentSyncRegistrationOptions.S1 => writeJs[NotebookDocumentSyncRegistrationOptions.S1](v)
    }
  given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions] = upickle.default.macroW
  /**
   *  @param notebook
   *    The notebook to be synced If a string
   *    value is provided it matches against the
   *    notebook type. '*' matches every notebook.
  
   *  @param cells
   *    The cells of the matching notebook to be synced.
  
   */
  case class S0(
    notebook: (String | aliases.NotebookDocumentFilter),
    cells: Opt[Vector[S0.S0]] = Opt.empty
  )
  object S0:
    private given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = 
      badMerge[(String | aliases.NotebookDocumentFilter)](stringCodec, aliases.NotebookDocumentFilter.reader)
    private given wt0: Writer[(String | aliases.NotebookDocumentFilter)] = 
      upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { _v => 
        (_v: @unchecked) match 
          case v: String => writeJs[String](v)
          case v: aliases.NotebookDocumentFilter => writeJs[aliases.NotebookDocumentFilter](v)
      }
    given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions.S0] = Pickle.macroR
    given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions.S0] = upickle.default.macroW
    case class S0(
      language: String
    )
    object S0:
      given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions.S0.S0] = Pickle.macroR
      given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions.S0.S0] = upickle.default.macroW
  /**
   *  @param notebook
   *    The notebook to be synced If a string
   *    value is provided it matches against the
   *    notebook type. '*' matches every notebook.
  
   *  @param cells
   *    The cells of the matching notebook to be synced.
  
   */
  case class S1(
    notebook: Opt[(String | aliases.NotebookDocumentFilter)] = Opt.empty,
    cells: Vector[S1.S0]
  )
  object S1:
    private given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = 
      badMerge[(String | aliases.NotebookDocumentFilter)](stringCodec, aliases.NotebookDocumentFilter.reader)
    private given wt0: Writer[(String | aliases.NotebookDocumentFilter)] = 
      upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { _v => 
        (_v: @unchecked) match 
          case v: String => writeJs[String](v)
          case v: aliases.NotebookDocumentFilter => writeJs[aliases.NotebookDocumentFilter](v)
      }
    given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions.S1] = Pickle.macroR
    given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions.S1] = upickle.default.macroW
    case class S0(
      language: String
    )
    object S0:
      given reader: Reader[structures.NotebookDocumentSyncRegistrationOptions.S1.S0] = Pickle.macroR
      given writer: Writer[structures.NotebookDocumentSyncRegistrationOptions.S1.S0] = upickle.default.macroW

/**
 *  @param supported
 *    The server has support for workspace folders

 *  @param changeNotifications
 *    Whether the server wants to receive workspace folder
 *    change notifications.
 *    
 *    If a string is provided the string is treated as an ID
 *    under which the notification is registered on the client
 *    side. The ID can be used to unregister for these events
 *    using the `client/unregisterCapability` request.

 */
case class WorkspaceFoldersServerCapabilities(
  supported: Opt[Boolean] = Opt.empty,
  changeNotifications: Opt[(String | Boolean)] = Opt.empty
)
object WorkspaceFoldersServerCapabilities:
  private given rd0: Reader[(String | Boolean)] = 
    badMerge[(String | Boolean)](stringCodec, upickle.default.reader[Boolean])
  private given wt0: Writer[(String | Boolean)] = 
    upickle.default.writer[ujson.Value].comap[(String | Boolean)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: Boolean => writeJs[Boolean](v)
    }
  given reader: Reader[structures.WorkspaceFoldersServerCapabilities] = Pickle.macroR
  given writer: Writer[structures.WorkspaceFoldersServerCapabilities] = upickle.default.macroW

/**
 *  Options for notifications/requests for user operations on files.
 *  
 *  @since 3.16.0

 *  @param didCreate
 *    The server is interested in receiving didCreateFiles notifications.

 *  @param willCreate
 *    The server is interested in receiving willCreateFiles requests.

 *  @param didRename
 *    The server is interested in receiving didRenameFiles notifications.

 *  @param willRename
 *    The server is interested in receiving willRenameFiles requests.

 *  @param didDelete
 *    The server is interested in receiving didDeleteFiles file notifications.

 *  @param willDelete
 *    The server is interested in receiving willDeleteFiles file requests.

 */
case class FileOperationOptions(
  didCreate: Opt[structures.FileOperationRegistrationOptions] = Opt.empty,
  willCreate: Opt[structures.FileOperationRegistrationOptions] = Opt.empty,
  didRename: Opt[structures.FileOperationRegistrationOptions] = Opt.empty,
  willRename: Opt[structures.FileOperationRegistrationOptions] = Opt.empty,
  didDelete: Opt[structures.FileOperationRegistrationOptions] = Opt.empty,
  willDelete: Opt[structures.FileOperationRegistrationOptions] = Opt.empty
)
object FileOperationOptions:
  given reader: Reader[structures.FileOperationOptions] = Pickle.macroR
  given writer: Writer[structures.FileOperationOptions] = upickle.default.macroW

case class T(
)
object T:
  given reader: Reader[structures.T] = Pickle.macroR
  given writer: Writer[structures.T] = upickle.default.macroW

/**
 *  Structure to capture a description for an error code.
 *  
 *  @since 3.16.0

 *  @param href
 *    An URI to open with more information about the diagnostic error.

 */
case class CodeDescription(
  href: aliases.URI
)
object CodeDescription:
  given reader: Reader[structures.CodeDescription] = Pickle.macroR
  given writer: Writer[structures.CodeDescription] = upickle.default.macroW

/**
 *  Represents a related message and source code location for a diagnostic. This should be
 *  used to point to code locations that cause or related to a diagnostics, e.g when duplicating
 *  a symbol in a scope.

 *  @param location
 *    The location of this related diagnostic information.

 *  @param message
 *    The message of this related diagnostic information.

 */
case class DiagnosticRelatedInformation(
  location: structures.Location,
  message: String
)
object DiagnosticRelatedInformation:
  given reader: Reader[structures.DiagnosticRelatedInformation] = Pickle.macroR
  given writer: Writer[structures.DiagnosticRelatedInformation] = upickle.default.macroW

/**
 *  Represents a parameter of a callable-signature. A parameter can
 *  have a label and a doc-comment.

 *  @param label
 *    The label of this parameter information.
 *    
 *    Either a string or an inclusive start and exclusive end offsets within its containing
 *    signature label. (see SignatureInformation.label). The offsets are based on a UTF-16
 *    string representation as `Position` and `Range` does.
 *    
 *    *Note*: a label of type string should be a substring of its containing signature label.
 *    Its intended use case is to highlight the parameter label part in the `SignatureInformation.label`.

 *  @param documentation
 *    The human-readable doc-comment of this parameter. Will be shown
 *    in the UI but can be omitted.

 */
case class ParameterInformation(
  label: (String | (RuntimeBase.uinteger, RuntimeBase.uinteger)),
  documentation: Opt[(String | structures.MarkupContent)] = Opt.empty
)
object ParameterInformation:
  private given rd0: Reader[(String | (RuntimeBase.uinteger, RuntimeBase.uinteger))] = 
    badMerge[(String | (RuntimeBase.uinteger, RuntimeBase.uinteger))](stringCodec, upickle.default.reader[(RuntimeBase.uinteger, RuntimeBase.uinteger)])
  private given wt0: Writer[(String | (RuntimeBase.uinteger, RuntimeBase.uinteger))] = 
    upickle.default.writer[ujson.Value].comap[(String | (RuntimeBase.uinteger, RuntimeBase.uinteger))] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: (RuntimeBase.uinteger, RuntimeBase.uinteger) => writeJs[(RuntimeBase.uinteger, RuntimeBase.uinteger)](v)
    }
  private given rd1: Reader[(String | structures.MarkupContent)] = 
    badMerge[(String | structures.MarkupContent)](stringCodec, structures.MarkupContent.reader)
  private given wt1: Writer[(String | structures.MarkupContent)] = 
    upickle.default.writer[ujson.Value].comap[(String | structures.MarkupContent)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: structures.MarkupContent => writeJs[structures.MarkupContent](v)
    }
  given reader: Reader[structures.ParameterInformation] = Pickle.macroR
  given writer: Writer[structures.ParameterInformation] = upickle.default.macroW

/**
 *  A notebook cell text document filter denotes a cell text
 *  document by different properties.
 *  
 *  @since 3.17.0

 *  @param notebook
 *    A filter that matches against the notebook
 *    containing the notebook cell. If a string
 *    value is provided it matches against the
 *    notebook type. '*' matches every notebook.

 *  @param language
 *    A language id like `python`.
 *    
 *    Will be matched against the language id of the
 *    notebook cell document. '*' matches every language.

 */
case class NotebookCellTextDocumentFilter(
  notebook: (String | aliases.NotebookDocumentFilter),
  language: Opt[String] = Opt.empty
)
object NotebookCellTextDocumentFilter:
  private given rd0: Reader[(String | aliases.NotebookDocumentFilter)] = 
    badMerge[(String | aliases.NotebookDocumentFilter)](stringCodec, aliases.NotebookDocumentFilter.reader)
  private given wt0: Writer[(String | aliases.NotebookDocumentFilter)] = 
    upickle.default.writer[ujson.Value].comap[(String | aliases.NotebookDocumentFilter)] { _v => 
      (_v: @unchecked) match 
        case v: String => writeJs[String](v)
        case v: aliases.NotebookDocumentFilter => writeJs[aliases.NotebookDocumentFilter](v)
    }
  given reader: Reader[structures.NotebookCellTextDocumentFilter] = Pickle.macroR
  given writer: Writer[structures.NotebookCellTextDocumentFilter] = upickle.default.macroW

/**
 *  Matching options for the file operation pattern.
 *  
 *  @since 3.16.0

 *  @param ignoreCase
 *    The pattern should be matched ignoring casing.

 */
case class FileOperationPatternOptions(
  ignoreCase: Opt[Boolean] = Opt.empty
)
object FileOperationPatternOptions:
  given reader: Reader[structures.FileOperationPatternOptions] = Pickle.macroR
  given writer: Writer[structures.FileOperationPatternOptions] = upickle.default.macroW

/**
 *  @param executionOrder
 *    A strict monotonically increasing value
 *    indicating the execution order of a cell
 *    inside a notebook.

 *  @param success
 *    Whether the execution was successful or
 *    not if known by the client.

 */
case class ExecutionSummary(
  executionOrder: RuntimeBase.uinteger,
  success: Opt[Boolean] = Opt.empty
)
object ExecutionSummary:
  given reader: Reader[structures.ExecutionSummary] = Pickle.macroR
  given writer: Writer[structures.ExecutionSummary] = upickle.default.macroW

/**
 *  Workspace specific client capabilities.

 *  @param applyEdit
 *    The client supports applying batch edits
 *    to the workspace by supporting the request
 *    'workspace/applyEdit'

 *  @param workspaceEdit
 *    Capabilities specific to `WorkspaceEdit`s.

 *  @param didChangeConfiguration
 *    Capabilities specific to the `workspace/didChangeConfiguration` notification.

 *  @param didChangeWatchedFiles
 *    Capabilities specific to the `workspace/didChangeWatchedFiles` notification.

 *  @param symbol
 *    Capabilities specific to the `workspace/symbol` request.

 *  @param executeCommand
 *    Capabilities specific to the `workspace/executeCommand` request.

 *  @param workspaceFolders
 *    The client has support for workspace folders.
 *    
 *    @since 3.6.0

 *  @param configuration
 *    The client supports `workspace/configuration` requests.
 *    
 *    @since 3.6.0

 *  @param semanticTokens
 *    Capabilities specific to the semantic token requests scoped to the
 *    workspace.
 *    
 *    @since 3.16.0.

 *  @param codeLens
 *    Capabilities specific to the code lens requests scoped to the
 *    workspace.
 *    
 *    @since 3.16.0.

 *  @param fileOperations
 *    The client has support for file notifications/requests for user operations on files.
 *    
 *    Since 3.16.0

 *  @param inlineValue
 *    Capabilities specific to the inline values requests scoped to the
 *    workspace.
 *    
 *    @since 3.17.0.

 *  @param inlayHint
 *    Capabilities specific to the inlay hint requests scoped to the
 *    workspace.
 *    
 *    @since 3.17.0.

 *  @param diagnostics
 *    Capabilities specific to the diagnostic requests scoped to the
 *    workspace.
 *    
 *    @since 3.17.0.

 */
case class WorkspaceClientCapabilities(
  applyEdit: Opt[Boolean] = Opt.empty,
  workspaceEdit: Opt[structures.WorkspaceEditClientCapabilities] = Opt.empty,
  didChangeConfiguration: Opt[structures.DidChangeConfigurationClientCapabilities] = Opt.empty,
  didChangeWatchedFiles: Opt[structures.DidChangeWatchedFilesClientCapabilities] = Opt.empty,
  symbol: Opt[structures.WorkspaceSymbolClientCapabilities] = Opt.empty,
  executeCommand: Opt[structures.ExecuteCommandClientCapabilities] = Opt.empty,
  workspaceFolders: Opt[Boolean] = Opt.empty,
  configuration: Opt[Boolean] = Opt.empty,
  semanticTokens: Opt[structures.SemanticTokensWorkspaceClientCapabilities] = Opt.empty,
  codeLens: Opt[structures.CodeLensWorkspaceClientCapabilities] = Opt.empty,
  fileOperations: Opt[structures.FileOperationClientCapabilities] = Opt.empty,
  inlineValue: Opt[structures.InlineValueWorkspaceClientCapabilities] = Opt.empty,
  inlayHint: Opt[structures.InlayHintWorkspaceClientCapabilities] = Opt.empty,
  diagnostics: Opt[structures.DiagnosticWorkspaceClientCapabilities] = Opt.empty
)
object WorkspaceClientCapabilities:
  given reader: Reader[structures.WorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.WorkspaceClientCapabilities] = upickle.default.macroW

/**
 *  Text document specific client capabilities.

 *  @param synchronization
 *    Defines which synchronization capabilities the client supports.

 *  @param completion
 *    Capabilities specific to the `textDocument/completion` request.

 *  @param hover
 *    Capabilities specific to the `textDocument/hover` request.

 *  @param signatureHelp
 *    Capabilities specific to the `textDocument/signatureHelp` request.

 *  @param declaration
 *    Capabilities specific to the `textDocument/declaration` request.
 *    
 *    @since 3.14.0

 *  @param definition
 *    Capabilities specific to the `textDocument/definition` request.

 *  @param typeDefinition
 *    Capabilities specific to the `textDocument/typeDefinition` request.
 *    
 *    @since 3.6.0

 *  @param implementation
 *    Capabilities specific to the `textDocument/implementation` request.
 *    
 *    @since 3.6.0

 *  @param references
 *    Capabilities specific to the `textDocument/references` request.

 *  @param documentHighlight
 *    Capabilities specific to the `textDocument/documentHighlight` request.

 *  @param documentSymbol
 *    Capabilities specific to the `textDocument/documentSymbol` request.

 *  @param codeAction
 *    Capabilities specific to the `textDocument/codeAction` request.

 *  @param codeLens
 *    Capabilities specific to the `textDocument/codeLens` request.

 *  @param documentLink
 *    Capabilities specific to the `textDocument/documentLink` request.

 *  @param colorProvider
 *    Capabilities specific to the `textDocument/documentColor` and the
 *    `textDocument/colorPresentation` request.
 *    
 *    @since 3.6.0

 *  @param formatting
 *    Capabilities specific to the `textDocument/formatting` request.

 *  @param rangeFormatting
 *    Capabilities specific to the `textDocument/rangeFormatting` request.

 *  @param onTypeFormatting
 *    Capabilities specific to the `textDocument/onTypeFormatting` request.

 *  @param rename
 *    Capabilities specific to the `textDocument/rename` request.

 *  @param foldingRange
 *    Capabilities specific to the `textDocument/foldingRange` request.
 *    
 *    @since 3.10.0

 *  @param selectionRange
 *    Capabilities specific to the `textDocument/selectionRange` request.
 *    
 *    @since 3.15.0

 *  @param publishDiagnostics
 *    Capabilities specific to the `textDocument/publishDiagnostics` notification.

 *  @param callHierarchy
 *    Capabilities specific to the various call hierarchy requests.
 *    
 *    @since 3.16.0

 *  @param semanticTokens
 *    Capabilities specific to the various semantic token request.
 *    
 *    @since 3.16.0

 *  @param linkedEditingRange
 *    Capabilities specific to the `textDocument/linkedEditingRange` request.
 *    
 *    @since 3.16.0

 *  @param moniker
 *    Client capabilities specific to the `textDocument/moniker` request.
 *    
 *    @since 3.16.0

 *  @param typeHierarchy
 *    Capabilities specific to the various type hierarchy requests.
 *    
 *    @since 3.17.0

 *  @param inlineValue
 *    Capabilities specific to the `textDocument/inlineValue` request.
 *    
 *    @since 3.17.0

 *  @param inlayHint
 *    Capabilities specific to the `textDocument/inlayHint` request.
 *    
 *    @since 3.17.0

 *  @param diagnostic
 *    Capabilities specific to the diagnostic pull model.
 *    
 *    @since 3.17.0

 */
case class TextDocumentClientCapabilities(
  synchronization: Opt[structures.TextDocumentSyncClientCapabilities] = Opt.empty,
  completion: Opt[structures.CompletionClientCapabilities] = Opt.empty,
  hover: Opt[structures.HoverClientCapabilities] = Opt.empty,
  signatureHelp: Opt[structures.SignatureHelpClientCapabilities] = Opt.empty,
  declaration: Opt[structures.DeclarationClientCapabilities] = Opt.empty,
  definition: Opt[structures.DefinitionClientCapabilities] = Opt.empty,
  typeDefinition: Opt[structures.TypeDefinitionClientCapabilities] = Opt.empty,
  implementation: Opt[structures.ImplementationClientCapabilities] = Opt.empty,
  references: Opt[structures.ReferenceClientCapabilities] = Opt.empty,
  documentHighlight: Opt[structures.DocumentHighlightClientCapabilities] = Opt.empty,
  documentSymbol: Opt[structures.DocumentSymbolClientCapabilities] = Opt.empty,
  codeAction: Opt[structures.CodeActionClientCapabilities] = Opt.empty,
  codeLens: Opt[structures.CodeLensClientCapabilities] = Opt.empty,
  documentLink: Opt[structures.DocumentLinkClientCapabilities] = Opt.empty,
  colorProvider: Opt[structures.DocumentColorClientCapabilities] = Opt.empty,
  formatting: Opt[structures.DocumentFormattingClientCapabilities] = Opt.empty,
  rangeFormatting: Opt[structures.DocumentRangeFormattingClientCapabilities] = Opt.empty,
  onTypeFormatting: Opt[structures.DocumentOnTypeFormattingClientCapabilities] = Opt.empty,
  rename: Opt[structures.RenameClientCapabilities] = Opt.empty,
  foldingRange: Opt[structures.FoldingRangeClientCapabilities] = Opt.empty,
  selectionRange: Opt[structures.SelectionRangeClientCapabilities] = Opt.empty,
  publishDiagnostics: Opt[structures.PublishDiagnosticsClientCapabilities] = Opt.empty,
  callHierarchy: Opt[structures.CallHierarchyClientCapabilities] = Opt.empty,
  semanticTokens: Opt[structures.SemanticTokensClientCapabilities] = Opt.empty,
  linkedEditingRange: Opt[structures.LinkedEditingRangeClientCapabilities] = Opt.empty,
  moniker: Opt[structures.MonikerClientCapabilities] = Opt.empty,
  typeHierarchy: Opt[structures.TypeHierarchyClientCapabilities] = Opt.empty,
  inlineValue: Opt[structures.InlineValueClientCapabilities] = Opt.empty,
  inlayHint: Opt[structures.InlayHintClientCapabilities] = Opt.empty,
  diagnostic: Opt[structures.DiagnosticClientCapabilities] = Opt.empty
)
object TextDocumentClientCapabilities:
  given reader: Reader[structures.TextDocumentClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.TextDocumentClientCapabilities] = upickle.default.macroW

/**
 *  Capabilities specific to the notebook document support.
 *  
 *  @since 3.17.0

 *  @param synchronization
 *    Capabilities specific to notebook document synchronization
 *    
 *    @since 3.17.0

 */
case class NotebookDocumentClientCapabilities(
  synchronization: structures.NotebookDocumentSyncClientCapabilities
)
object NotebookDocumentClientCapabilities:
  given reader: Reader[structures.NotebookDocumentClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentClientCapabilities] = upickle.default.macroW

/**
 *  @param workDoneProgress
 *    It indicates whether the client supports server initiated
 *    progress using the `window/workDoneProgress/create` request.
 *    
 *    The capability also controls Whether client supports handling
 *    of progress notifications. If set servers are allowed to report a
 *    `workDoneProgress` property in the request specific server
 *    capabilities.
 *    
 *    @since 3.15.0

 *  @param showMessage
 *    Capabilities specific to the showMessage request.
 *    
 *    @since 3.16.0

 *  @param showDocument
 *    Capabilities specific to the showDocument request.
 *    
 *    @since 3.16.0

 */
case class WindowClientCapabilities(
  workDoneProgress: Opt[Boolean] = Opt.empty,
  showMessage: Opt[structures.ShowMessageRequestClientCapabilities] = Opt.empty,
  showDocument: Opt[structures.ShowDocumentClientCapabilities] = Opt.empty
)
object WindowClientCapabilities:
  given reader: Reader[structures.WindowClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.WindowClientCapabilities] = upickle.default.macroW

/**
 *  General client capabilities.
 *  
 *  @since 3.16.0

 *  @param staleRequestSupport
 *    Client capability that signals how the client
 *    handles stale requests (e.g. a request
 *    for which the client will not process the response
 *    anymore since the information is outdated).
 *    
 *    @since 3.17.0

 *  @param regularExpressions
 *    Client capabilities specific to regular expressions.
 *    
 *    @since 3.16.0

 *  @param markdown
 *    Client capabilities specific to the client's markdown parser.
 *    
 *    @since 3.16.0

 *  @param positionEncodings
 *    The position encodings supported by the client. Client and server
 *    have to agree on the same position encoding to ensure that offsets
 *    (e.g. character position in a line) are interpreted the same on both
 *    sides.
 *    
 *    To keep the protocol backwards compatible the following applies: if
 *    the value 'utf-16' is missing from the array of position encodings
 *    servers can assume that the client supports UTF-16. UTF-16 is
 *    therefore a mandatory encoding.
 *    
 *    If omitted it defaults to ['utf-16'].
 *    
 *    Implementation considerations: since the conversion from one encoding
 *    into another requires the content of the file / line the conversion
 *    is best done where the file is read which is usually on the server
 *    side.
 *    
 *    @since 3.17.0

 */
case class GeneralClientCapabilities(
  staleRequestSupport: Opt[GeneralClientCapabilities.StaleRequestSupport] = Opt.empty,
  regularExpressions: Opt[structures.RegularExpressionsClientCapabilities] = Opt.empty,
  markdown: Opt[structures.MarkdownClientCapabilities] = Opt.empty,
  positionEncodings: Opt[Vector[enumerations.PositionEncodingKind]] = Opt.empty
)
object GeneralClientCapabilities:
  given reader: Reader[structures.GeneralClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.GeneralClientCapabilities] = upickle.default.macroW
  /**
   *  @param cancel
   *    The client will actively cancel the request.
  
   *  @param retryOnContentModified
   *    The list of requests for which the client
   *    will retry the request if it receives a
   *    response with error code `ContentModified`
  
   */
  case class StaleRequestSupport(
    cancel: Boolean,
    retryOnContentModified: Vector[String]
  )
  object StaleRequestSupport:
    given reader: Reader[structures.GeneralClientCapabilities.StaleRequestSupport] = Pickle.macroR
    given writer: Writer[structures.GeneralClientCapabilities.StaleRequestSupport] = upickle.default.macroW

/**
 *  A relative pattern is a helper to construct glob patterns that are matched
 *  relatively to a base URI. The common value for a `baseUri` is a workspace
 *  folder root, but it can be another absolute URI as well.
 *  
 *  @since 3.17.0

 *  @param baseUri
 *    A workspace folder or a base URI to which this pattern will be matched
 *    against relatively.

 *  @param pattern
 *    The actual glob pattern;

 */
case class RelativePattern(
  baseUri: (structures.WorkspaceFolder | aliases.URI),
  pattern: aliases.Pattern
)
object RelativePattern:
  private given rd0: Reader[(structures.WorkspaceFolder | aliases.URI)] = 
    badMerge[(structures.WorkspaceFolder | aliases.URI)](structures.WorkspaceFolder.reader, aliases.URI.reader)
  private given wt0: Writer[(structures.WorkspaceFolder | aliases.URI)] = 
    upickle.default.writer[ujson.Value].comap[(structures.WorkspaceFolder | aliases.URI)] { _v => 
      (_v: @unchecked) match 
        case v: structures.WorkspaceFolder => writeJs[structures.WorkspaceFolder](v)
        case v: aliases.URI => writeJs[aliases.URI](v)
    }
  given reader: Reader[structures.RelativePattern] = Pickle.macroR
  given writer: Writer[structures.RelativePattern] = upickle.default.macroW

/**
 *  @param documentChanges
 *    The client supports versioned document changes in `WorkspaceEdit`s

 *  @param resourceOperations
 *    The resource operations the client supports. Clients should at least
 *    support 'create', 'rename' and 'delete' files and folders.
 *    
 *    @since 3.13.0

 *  @param failureHandling
 *    The failure handling strategy of a client if applying the workspace edit
 *    fails.
 *    
 *    @since 3.13.0

 *  @param normalizesLineEndings
 *    Whether the client normalizes line endings to the client specific
 *    setting.
 *    If set to `true` the client will normalize line ending characters
 *    in a workspace edit to the client-specified new line
 *    character.
 *    
 *    @since 3.16.0

 *  @param changeAnnotationSupport
 *    Whether the client in general supports change annotations on text edits,
 *    create file, rename file and delete file changes.
 *    
 *    @since 3.16.0

 */
case class WorkspaceEditClientCapabilities(
  documentChanges: Opt[Boolean] = Opt.empty,
  resourceOperations: Opt[Vector[enumerations.ResourceOperationKind]] = Opt.empty,
  failureHandling: Opt[enumerations.FailureHandlingKind] = Opt.empty,
  normalizesLineEndings: Opt[Boolean] = Opt.empty,
  changeAnnotationSupport: Opt[WorkspaceEditClientCapabilities.ChangeAnnotationSupport] = Opt.empty
)
object WorkspaceEditClientCapabilities:
  given reader: Reader[structures.WorkspaceEditClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.WorkspaceEditClientCapabilities] = upickle.default.macroW
  /**
   *  @param groupsOnLabel
   *    Whether the client groups edits with equal labels into tree nodes,
   *    for instance all edits labelled with "Changes in Strings" would
   *    be a tree node.
  
   */
  case class ChangeAnnotationSupport(
    groupsOnLabel: Opt[Boolean] = Opt.empty
  )
  object ChangeAnnotationSupport:
    given reader: Reader[structures.WorkspaceEditClientCapabilities.ChangeAnnotationSupport] = Pickle.macroR
    given writer: Writer[structures.WorkspaceEditClientCapabilities.ChangeAnnotationSupport] = upickle.default.macroW

/**
 *  @param dynamicRegistration
 *    Did change configuration notification supports dynamic registration.

 */
case class DidChangeConfigurationClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object DidChangeConfigurationClientCapabilities:
  given reader: Reader[structures.DidChangeConfigurationClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DidChangeConfigurationClientCapabilities] = upickle.default.macroW

/**
 *  @param dynamicRegistration
 *    Did change watched files notification supports dynamic registration. Please note
 *    that the current protocol doesn't support static configuration for file changes
 *    from the server side.

 *  @param relativePatternSupport
 *    Whether the client has support for {@link  RelativePattern relative pattern}
 *    or not.
 *    
 *    @since 3.17.0

 */
case class DidChangeWatchedFilesClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  relativePatternSupport: Opt[Boolean] = Opt.empty
)
object DidChangeWatchedFilesClientCapabilities:
  given reader: Reader[structures.DidChangeWatchedFilesClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DidChangeWatchedFilesClientCapabilities] = upickle.default.macroW

/**
 *  Client capabilities for a [WorkspaceSymbolRequest](#WorkspaceSymbolRequest).

 *  @param dynamicRegistration
 *    Symbol request supports dynamic registration.

 *  @param symbolKind
 *    Specific capabilities for the `SymbolKind` in the `workspace/symbol` request.

 *  @param tagSupport
 *    The client supports tags on `SymbolInformation`.
 *    Clients supporting tags have to handle unknown tags gracefully.
 *    
 *    @since 3.16.0

 *  @param resolveSupport
 *    The client support partial workspace symbols. The client will send the
 *    request `workspaceSymbol/resolve` to the server to resolve additional
 *    properties.
 *    
 *    @since 3.17.0

 */
case class WorkspaceSymbolClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  symbolKind: Opt[WorkspaceSymbolClientCapabilities.SymbolKind] = Opt.empty,
  tagSupport: Opt[WorkspaceSymbolClientCapabilities.TagSupport] = Opt.empty,
  resolveSupport: Opt[WorkspaceSymbolClientCapabilities.ResolveSupport] = Opt.empty
)
object WorkspaceSymbolClientCapabilities:
  given reader: Reader[structures.WorkspaceSymbolClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.WorkspaceSymbolClientCapabilities] = upickle.default.macroW
  /**
   *  @param valueSet
   *    The symbol kind values the client supports. When this
   *    property exists the client also guarantees that it will
   *    handle values outside its set gracefully and falls back
   *    to a default value when unknown.
   *    
   *    If this property is not present the client only supports
   *    the symbol kinds from `File` to `Array` as defined in
   *    the initial version of the protocol.
  
   */
  case class SymbolKind(
    valueSet: Opt[Vector[enumerations.SymbolKind]] = Opt.empty
  )
  object SymbolKind:
    given reader: Reader[structures.WorkspaceSymbolClientCapabilities.SymbolKind] = Pickle.macroR
    given writer: Writer[structures.WorkspaceSymbolClientCapabilities.SymbolKind] = upickle.default.macroW
  /**
   *  @param valueSet
   *    The tags supported by the client.
  
   */
  case class TagSupport(
    valueSet: Vector[enumerations.SymbolTag]
  )
  object TagSupport:
    given reader: Reader[structures.WorkspaceSymbolClientCapabilities.TagSupport] = Pickle.macroR
    given writer: Writer[structures.WorkspaceSymbolClientCapabilities.TagSupport] = upickle.default.macroW
  /**
   *  @param properties
   *    The properties that a client can resolve lazily. Usually
   *    `location.range`
  
   */
  case class ResolveSupport(
    properties: Vector[String]
  )
  object ResolveSupport:
    given reader: Reader[structures.WorkspaceSymbolClientCapabilities.ResolveSupport] = Pickle.macroR
    given writer: Writer[structures.WorkspaceSymbolClientCapabilities.ResolveSupport] = upickle.default.macroW

/**
 *  The client capabilities of a [ExecuteCommandRequest](#ExecuteCommandRequest).

 *  @param dynamicRegistration
 *    Execute command supports dynamic registration.

 */
case class ExecuteCommandClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object ExecuteCommandClientCapabilities:
  given reader: Reader[structures.ExecuteCommandClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ExecuteCommandClientCapabilities] = upickle.default.macroW

/**
 *  @since 3.16.0

 *  @param refreshSupport
 *    Whether the client implementation supports a refresh request sent from
 *    the server to the client.
 *    
 *    Note that this event is global and will force the client to refresh all
 *    semantic tokens currently shown. It should be used with absolute care
 *    and is useful for situation where a server for example detects a project
 *    wide change that requires such a calculation.

 */
case class SemanticTokensWorkspaceClientCapabilities(
  refreshSupport: Opt[Boolean] = Opt.empty
)
object SemanticTokensWorkspaceClientCapabilities:
  given reader: Reader[structures.SemanticTokensWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensWorkspaceClientCapabilities] = upickle.default.macroW

/**
 *  @since 3.16.0

 *  @param refreshSupport
 *    Whether the client implementation supports a refresh request sent from the
 *    server to the client.
 *    
 *    Note that this event is global and will force the client to refresh all
 *    code lenses currently shown. It should be used with absolute care and is
 *    useful for situation where a server for example detect a project wide
 *    change that requires such a calculation.

 */
case class CodeLensWorkspaceClientCapabilities(
  refreshSupport: Opt[Boolean] = Opt.empty
)
object CodeLensWorkspaceClientCapabilities:
  given reader: Reader[structures.CodeLensWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.CodeLensWorkspaceClientCapabilities] = upickle.default.macroW

/**
 *  Capabilities relating to events from file operations by the user in the client.
 *  
 *  These events do not come from the file system, they come from user operations
 *  like renaming a file in the UI.
 *  
 *  @since 3.16.0

 *  @param dynamicRegistration
 *    Whether the client supports dynamic registration for file requests/notifications.

 *  @param didCreate
 *    The client has support for sending didCreateFiles notifications.

 *  @param willCreate
 *    The client has support for sending willCreateFiles requests.

 *  @param didRename
 *    The client has support for sending didRenameFiles notifications.

 *  @param willRename
 *    The client has support for sending willRenameFiles requests.

 *  @param didDelete
 *    The client has support for sending didDeleteFiles notifications.

 *  @param willDelete
 *    The client has support for sending willDeleteFiles requests.

 */
case class FileOperationClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  didCreate: Opt[Boolean] = Opt.empty,
  willCreate: Opt[Boolean] = Opt.empty,
  didRename: Opt[Boolean] = Opt.empty,
  willRename: Opt[Boolean] = Opt.empty,
  didDelete: Opt[Boolean] = Opt.empty,
  willDelete: Opt[Boolean] = Opt.empty
)
object FileOperationClientCapabilities:
  given reader: Reader[structures.FileOperationClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.FileOperationClientCapabilities] = upickle.default.macroW

/**
 *  Client workspace capabilities specific to inline values.
 *  
 *  @since 3.17.0

 *  @param refreshSupport
 *    Whether the client implementation supports a refresh request sent from the
 *    server to the client.
 *    
 *    Note that this event is global and will force the client to refresh all
 *    inline values currently shown. It should be used with absolute care and is
 *    useful for situation where a server for example detects a project wide
 *    change that requires such a calculation.

 */
case class InlineValueWorkspaceClientCapabilities(
  refreshSupport: Opt[Boolean] = Opt.empty
)
object InlineValueWorkspaceClientCapabilities:
  given reader: Reader[structures.InlineValueWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.InlineValueWorkspaceClientCapabilities] = upickle.default.macroW

/**
 *  Client workspace capabilities specific to inlay hints.
 *  
 *  @since 3.17.0

 *  @param refreshSupport
 *    Whether the client implementation supports a refresh request sent from
 *    the server to the client.
 *    
 *    Note that this event is global and will force the client to refresh all
 *    inlay hints currently shown. It should be used with absolute care and
 *    is useful for situation where a server for example detects a project wide
 *    change that requires such a calculation.

 */
case class InlayHintWorkspaceClientCapabilities(
  refreshSupport: Opt[Boolean] = Opt.empty
)
object InlayHintWorkspaceClientCapabilities:
  given reader: Reader[structures.InlayHintWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.InlayHintWorkspaceClientCapabilities] = upickle.default.macroW

/**
 *  Workspace client capabilities specific to diagnostic pull requests.
 *  
 *  @since 3.17.0

 *  @param refreshSupport
 *    Whether the client implementation supports a refresh request sent from
 *    the server to the client.
 *    
 *    Note that this event is global and will force the client to refresh all
 *    pulled diagnostics currently shown. It should be used with absolute care and
 *    is useful for situation where a server for example detects a project wide
 *    change that requires such a calculation.

 */
case class DiagnosticWorkspaceClientCapabilities(
  refreshSupport: Opt[Boolean] = Opt.empty
)
object DiagnosticWorkspaceClientCapabilities:
  given reader: Reader[structures.DiagnosticWorkspaceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DiagnosticWorkspaceClientCapabilities] = upickle.default.macroW

/**
 *  @param dynamicRegistration
 *    Whether text document synchronization supports dynamic registration.

 *  @param willSave
 *    The client supports sending will save notifications.

 *  @param willSaveWaitUntil
 *    The client supports sending a will save request and
 *    waits for a response providing text edits which will
 *    be applied to the document before it is saved.

 *  @param didSave
 *    The client supports did save notifications.

 */
case class TextDocumentSyncClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  willSave: Opt[Boolean] = Opt.empty,
  willSaveWaitUntil: Opt[Boolean] = Opt.empty,
  didSave: Opt[Boolean] = Opt.empty
)
object TextDocumentSyncClientCapabilities:
  given reader: Reader[structures.TextDocumentSyncClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.TextDocumentSyncClientCapabilities] = upickle.default.macroW

/**
 *  Completion client capabilities

 *  @param dynamicRegistration
 *    Whether completion supports dynamic registration.

 *  @param completionItem
 *    The client supports the following `CompletionItem` specific
 *    capabilities.

 *  @param completionItemKind
 *  @param insertTextMode
 *    Defines how the client handles whitespace and indentation
 *    when accepting a completion item that uses multi line
 *    text in either `insertText` or `textEdit`.
 *    
 *    @since 3.17.0

 *  @param contextSupport
 *    The client supports to send additional context information for a
 *    `textDocument/completion` request.

 *  @param completionList
 *    The client supports the following `CompletionList` specific
 *    capabilities.
 *    
 *    @since 3.17.0

 */
case class CompletionClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  completionItem: Opt[CompletionClientCapabilities.CompletionItem] = Opt.empty,
  completionItemKind: Opt[CompletionClientCapabilities.CompletionItemKind] = Opt.empty,
  insertTextMode: Opt[enumerations.InsertTextMode] = Opt.empty,
  contextSupport: Opt[Boolean] = Opt.empty,
  completionList: Opt[CompletionClientCapabilities.CompletionList] = Opt.empty
)
object CompletionClientCapabilities:
  given reader: Reader[structures.CompletionClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.CompletionClientCapabilities] = upickle.default.macroW
  /**
   *  @param snippetSupport
   *    Client supports snippets as insert text.
   *    
   *    A snippet can define tab stops and placeholders with `$1`, `$2`
   *    and `${3:foo}`. `$0` defines the final tab stop, it defaults to
   *    the end of the snippet. Placeholders with equal identifiers are linked,
   *    that is typing in one will update others too.
  
   *  @param commitCharactersSupport
   *    Client supports commit characters on a completion item.
  
   *  @param documentationFormat
   *    Client supports the following content formats for the documentation
   *    property. The order describes the preferred format of the client.
  
   *  @param deprecatedSupport
   *    Client supports the deprecated property on a completion item.
  
   *  @param preselectSupport
   *    Client supports the preselect property on a completion item.
  
   *  @param tagSupport
   *    Client supports the tag property on a completion item. Clients supporting
   *    tags have to handle unknown tags gracefully. Clients especially need to
   *    preserve unknown tags when sending a completion item back to the server in
   *    a resolve call.
   *    
   *    @since 3.15.0
  
   *  @param insertReplaceSupport
   *    Client support insert replace edit to control different behavior if a
   *    completion item is inserted in the text or should replace text.
   *    
   *    @since 3.16.0
  
   *  @param resolveSupport
   *    Indicates which properties a client can resolve lazily on a completion
   *    item. Before version 3.16.0 only the predefined properties `documentation`
   *    and `details` could be resolved lazily.
   *    
   *    @since 3.16.0
  
   *  @param insertTextModeSupport
   *    The client supports the `insertTextMode` property on
   *    a completion item to override the whitespace handling mode
   *    as defined by the client (see `insertTextMode`).
   *    
   *    @since 3.16.0
  
   *  @param labelDetailsSupport
   *    The client has support for completion item label
   *    details (see also `CompletionItemLabelDetails`).
   *    
   *    @since 3.17.0
  
   */
  case class CompletionItem(
    snippetSupport: Opt[Boolean] = Opt.empty,
    commitCharactersSupport: Opt[Boolean] = Opt.empty,
    documentationFormat: Opt[Vector[enumerations.MarkupKind]] = Opt.empty,
    deprecatedSupport: Opt[Boolean] = Opt.empty,
    preselectSupport: Opt[Boolean] = Opt.empty,
    tagSupport: Opt[CompletionItem.TagSupport] = Opt.empty,
    insertReplaceSupport: Opt[Boolean] = Opt.empty,
    resolveSupport: Opt[CompletionItem.ResolveSupport] = Opt.empty,
    insertTextModeSupport: Opt[CompletionItem.InsertTextModeSupport] = Opt.empty,
    labelDetailsSupport: Opt[Boolean] = Opt.empty
  )
  object CompletionItem:
    given reader: Reader[structures.CompletionClientCapabilities.CompletionItem] = Pickle.macroR
    given writer: Writer[structures.CompletionClientCapabilities.CompletionItem] = upickle.default.macroW
    /**
     *  @param valueSet
     *    The tags supported by the client.
    
     */
    case class TagSupport(
      valueSet: Vector[enumerations.CompletionItemTag]
    )
    object TagSupport:
      given reader: Reader[structures.CompletionClientCapabilities.CompletionItem.TagSupport] = Pickle.macroR
      given writer: Writer[structures.CompletionClientCapabilities.CompletionItem.TagSupport] = upickle.default.macroW
    /**
     *  @param properties
     *    The properties that a client can resolve lazily.
    
     */
    case class ResolveSupport(
      properties: Vector[String]
    )
    object ResolveSupport:
      given reader: Reader[structures.CompletionClientCapabilities.CompletionItem.ResolveSupport] = Pickle.macroR
      given writer: Writer[structures.CompletionClientCapabilities.CompletionItem.ResolveSupport] = upickle.default.macroW
    case class InsertTextModeSupport(
      valueSet: Vector[enumerations.InsertTextMode]
    )
    object InsertTextModeSupport:
      given reader: Reader[structures.CompletionClientCapabilities.CompletionItem.InsertTextModeSupport] = Pickle.macroR
      given writer: Writer[structures.CompletionClientCapabilities.CompletionItem.InsertTextModeSupport] = upickle.default.macroW
  /**
   *  @param valueSet
   *    The completion item kind values the client supports. When this
   *    property exists the client also guarantees that it will
   *    handle values outside its set gracefully and falls back
   *    to a default value when unknown.
   *    
   *    If this property is not present the client only supports
   *    the completion items kinds from `Text` to `Reference` as defined in
   *    the initial version of the protocol.
  
   */
  case class CompletionItemKind(
    valueSet: Opt[Vector[enumerations.CompletionItemKind]] = Opt.empty
  )
  object CompletionItemKind:
    given reader: Reader[structures.CompletionClientCapabilities.CompletionItemKind] = Pickle.macroR
    given writer: Writer[structures.CompletionClientCapabilities.CompletionItemKind] = upickle.default.macroW
  /**
   *  @param itemDefaults
   *    The client supports the following itemDefaults on
   *    a completion list.
   *    
   *    The value lists the supported property names of the
   *    `CompletionList.itemDefaults` object. If omitted
   *    no properties are supported.
   *    
   *    @since 3.17.0
  
   */
  case class CompletionList(
    itemDefaults: Opt[Vector[String]] = Opt.empty
  )
  object CompletionList:
    given reader: Reader[structures.CompletionClientCapabilities.CompletionList] = Pickle.macroR
    given writer: Writer[structures.CompletionClientCapabilities.CompletionList] = upickle.default.macroW

/**
 *  @param dynamicRegistration
 *    Whether hover supports dynamic registration.

 *  @param contentFormat
 *    Client supports the following content formats for the content
 *    property. The order describes the preferred format of the client.

 */
case class HoverClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  contentFormat: Opt[Vector[enumerations.MarkupKind]] = Opt.empty
)
object HoverClientCapabilities:
  given reader: Reader[structures.HoverClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.HoverClientCapabilities] = upickle.default.macroW

/**
 *  Client Capabilities for a [SignatureHelpRequest](#SignatureHelpRequest).

 *  @param dynamicRegistration
 *    Whether signature help supports dynamic registration.

 *  @param signatureInformation
 *    The client supports the following `SignatureInformation`
 *    specific properties.

 *  @param contextSupport
 *    The client supports to send additional context information for a
 *    `textDocument/signatureHelp` request. A client that opts into
 *    contextSupport will also support the `retriggerCharacters` on
 *    `SignatureHelpOptions`.
 *    
 *    @since 3.15.0

 */
case class SignatureHelpClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  signatureInformation: Opt[SignatureHelpClientCapabilities.SignatureInformation] = Opt.empty,
  contextSupport: Opt[Boolean] = Opt.empty
)
object SignatureHelpClientCapabilities:
  given reader: Reader[structures.SignatureHelpClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.SignatureHelpClientCapabilities] = upickle.default.macroW
  /**
   *  @param documentationFormat
   *    Client supports the following content formats for the documentation
   *    property. The order describes the preferred format of the client.
  
   *  @param parameterInformation
   *    Client capabilities specific to parameter information.
  
   *  @param activeParameterSupport
   *    The client supports the `activeParameter` property on `SignatureInformation`
   *    literal.
   *    
   *    @since 3.16.0
  
   */
  case class SignatureInformation(
    documentationFormat: Opt[Vector[enumerations.MarkupKind]] = Opt.empty,
    parameterInformation: Opt[SignatureInformation.ParameterInformation] = Opt.empty,
    activeParameterSupport: Opt[Boolean] = Opt.empty
  )
  object SignatureInformation:
    given reader: Reader[structures.SignatureHelpClientCapabilities.SignatureInformation] = Pickle.macroR
    given writer: Writer[structures.SignatureHelpClientCapabilities.SignatureInformation] = upickle.default.macroW
    /**
     *  @param labelOffsetSupport
     *    The client supports processing label offsets instead of a
     *    simple label string.
     *    
     *    @since 3.14.0
    
     */
    case class ParameterInformation(
      labelOffsetSupport: Opt[Boolean] = Opt.empty
    )
    object ParameterInformation:
      given reader: Reader[structures.SignatureHelpClientCapabilities.SignatureInformation.ParameterInformation] = Pickle.macroR
      given writer: Writer[structures.SignatureHelpClientCapabilities.SignatureInformation.ParameterInformation] = upickle.default.macroW

/**
 *  @since 3.14.0

 *  @param dynamicRegistration
 *    Whether declaration supports dynamic registration. If this is set to `true`
 *    the client supports the new `DeclarationRegistrationOptions` return value
 *    for the corresponding server capability as well.

 *  @param linkSupport
 *    The client supports additional metadata in the form of declaration links.

 */
case class DeclarationClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  linkSupport: Opt[Boolean] = Opt.empty
)
object DeclarationClientCapabilities:
  given reader: Reader[structures.DeclarationClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DeclarationClientCapabilities] = upickle.default.macroW

/**
 *  Client Capabilities for a [DefinitionRequest](#DefinitionRequest).

 *  @param dynamicRegistration
 *    Whether definition supports dynamic registration.

 *  @param linkSupport
 *    The client supports additional metadata in the form of definition links.
 *    
 *    @since 3.14.0

 */
case class DefinitionClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  linkSupport: Opt[Boolean] = Opt.empty
)
object DefinitionClientCapabilities:
  given reader: Reader[structures.DefinitionClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DefinitionClientCapabilities] = upickle.default.macroW

/**
 *  Since 3.6.0

 *  @param dynamicRegistration
 *    Whether implementation supports dynamic registration. If this is set to `true`
 *    the client supports the new `TypeDefinitionRegistrationOptions` return value
 *    for the corresponding server capability as well.

 *  @param linkSupport
 *    The client supports additional metadata in the form of definition links.
 *    
 *    Since 3.14.0

 */
case class TypeDefinitionClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  linkSupport: Opt[Boolean] = Opt.empty
)
object TypeDefinitionClientCapabilities:
  given reader: Reader[structures.TypeDefinitionClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.TypeDefinitionClientCapabilities] = upickle.default.macroW

/**
 *  @since 3.6.0

 *  @param dynamicRegistration
 *    Whether implementation supports dynamic registration. If this is set to `true`
 *    the client supports the new `ImplementationRegistrationOptions` return value
 *    for the corresponding server capability as well.

 *  @param linkSupport
 *    The client supports additional metadata in the form of definition links.
 *    
 *    @since 3.14.0

 */
case class ImplementationClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  linkSupport: Opt[Boolean] = Opt.empty
)
object ImplementationClientCapabilities:
  given reader: Reader[structures.ImplementationClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ImplementationClientCapabilities] = upickle.default.macroW

/**
 *  Client Capabilities for a [ReferencesRequest](#ReferencesRequest).

 *  @param dynamicRegistration
 *    Whether references supports dynamic registration.

 */
case class ReferenceClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object ReferenceClientCapabilities:
  given reader: Reader[structures.ReferenceClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ReferenceClientCapabilities] = upickle.default.macroW

/**
 *  Client Capabilities for a [DocumentHighlightRequest](#DocumentHighlightRequest).

 *  @param dynamicRegistration
 *    Whether document highlight supports dynamic registration.

 */
case class DocumentHighlightClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object DocumentHighlightClientCapabilities:
  given reader: Reader[structures.DocumentHighlightClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentHighlightClientCapabilities] = upickle.default.macroW

/**
 *  Client Capabilities for a [DocumentSymbolRequest](#DocumentSymbolRequest).

 *  @param dynamicRegistration
 *    Whether document symbol supports dynamic registration.

 *  @param symbolKind
 *    Specific capabilities for the `SymbolKind` in the
 *    `textDocument/documentSymbol` request.

 *  @param hierarchicalDocumentSymbolSupport
 *    The client supports hierarchical document symbols.

 *  @param tagSupport
 *    The client supports tags on `SymbolInformation`. Tags are supported on
 *    `DocumentSymbol` if `hierarchicalDocumentSymbolSupport` is set to true.
 *    Clients supporting tags have to handle unknown tags gracefully.
 *    
 *    @since 3.16.0

 *  @param labelSupport
 *    The client supports an additional label presented in the UI when
 *    registering a document symbol provider.
 *    
 *    @since 3.16.0

 */
case class DocumentSymbolClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  symbolKind: Opt[DocumentSymbolClientCapabilities.SymbolKind] = Opt.empty,
  hierarchicalDocumentSymbolSupport: Opt[Boolean] = Opt.empty,
  tagSupport: Opt[DocumentSymbolClientCapabilities.TagSupport] = Opt.empty,
  labelSupport: Opt[Boolean] = Opt.empty
)
object DocumentSymbolClientCapabilities:
  given reader: Reader[structures.DocumentSymbolClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentSymbolClientCapabilities] = upickle.default.macroW
  /**
   *  @param valueSet
   *    The symbol kind values the client supports. When this
   *    property exists the client also guarantees that it will
   *    handle values outside its set gracefully and falls back
   *    to a default value when unknown.
   *    
   *    If this property is not present the client only supports
   *    the symbol kinds from `File` to `Array` as defined in
   *    the initial version of the protocol.
  
   */
  case class SymbolKind(
    valueSet: Opt[Vector[enumerations.SymbolKind]] = Opt.empty
  )
  object SymbolKind:
    given reader: Reader[structures.DocumentSymbolClientCapabilities.SymbolKind] = Pickle.macroR
    given writer: Writer[structures.DocumentSymbolClientCapabilities.SymbolKind] = upickle.default.macroW
  /**
   *  @param valueSet
   *    The tags supported by the client.
  
   */
  case class TagSupport(
    valueSet: Vector[enumerations.SymbolTag]
  )
  object TagSupport:
    given reader: Reader[structures.DocumentSymbolClientCapabilities.TagSupport] = Pickle.macroR
    given writer: Writer[structures.DocumentSymbolClientCapabilities.TagSupport] = upickle.default.macroW

/**
 *  The Client Capabilities of a [CodeActionRequest](#CodeActionRequest).

 *  @param dynamicRegistration
 *    Whether code action supports dynamic registration.

 *  @param codeActionLiteralSupport
 *    The client support code action literals of type `CodeAction` as a valid
 *    response of the `textDocument/codeAction` request. If the property is not
 *    set the request can only return `Command` literals.
 *    
 *    @since 3.8.0

 *  @param isPreferredSupport
 *    Whether code action supports the `isPreferred` property.
 *    
 *    @since 3.15.0

 *  @param disabledSupport
 *    Whether code action supports the `disabled` property.
 *    
 *    @since 3.16.0

 *  @param dataSupport
 *    Whether code action supports the `data` property which is
 *    preserved between a `textDocument/codeAction` and a
 *    `codeAction/resolve` request.
 *    
 *    @since 3.16.0

 *  @param resolveSupport
 *    Whether the client supports resolving additional code action
 *    properties via a separate `codeAction/resolve` request.
 *    
 *    @since 3.16.0

 *  @param honorsChangeAnnotations
 *    Whether the client honors the change annotations in
 *    text edits and resource operations returned via the
 *    `CodeAction#edit` property by for example presenting
 *    the workspace edit in the user interface and asking
 *    for confirmation.
 *    
 *    @since 3.16.0

 */
case class CodeActionClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  codeActionLiteralSupport: Opt[CodeActionClientCapabilities.CodeActionLiteralSupport] = Opt.empty,
  isPreferredSupport: Opt[Boolean] = Opt.empty,
  disabledSupport: Opt[Boolean] = Opt.empty,
  dataSupport: Opt[Boolean] = Opt.empty,
  resolveSupport: Opt[CodeActionClientCapabilities.ResolveSupport] = Opt.empty,
  honorsChangeAnnotations: Opt[Boolean] = Opt.empty
)
object CodeActionClientCapabilities:
  given reader: Reader[structures.CodeActionClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.CodeActionClientCapabilities] = upickle.default.macroW
  /**
   *  @param codeActionKind
   *    The code action kind is support with the following value
   *    set.
  
   */
  case class CodeActionLiteralSupport(
    codeActionKind: CodeActionLiteralSupport.CodeActionKind
  )
  object CodeActionLiteralSupport:
    given reader: Reader[structures.CodeActionClientCapabilities.CodeActionLiteralSupport] = Pickle.macroR
    given writer: Writer[structures.CodeActionClientCapabilities.CodeActionLiteralSupport] = upickle.default.macroW
    /**
     *  @param valueSet
     *    The code action kind values the client supports. When this
     *    property exists the client also guarantees that it will
     *    handle values outside its set gracefully and falls back
     *    to a default value when unknown.
    
     */
    case class CodeActionKind(
      valueSet: Vector[enumerations.CodeActionKind]
    )
    object CodeActionKind:
      given reader: Reader[structures.CodeActionClientCapabilities.CodeActionLiteralSupport.CodeActionKind] = Pickle.macroR
      given writer: Writer[structures.CodeActionClientCapabilities.CodeActionLiteralSupport.CodeActionKind] = upickle.default.macroW
  /**
   *  @param properties
   *    The properties that a client can resolve lazily.
  
   */
  case class ResolveSupport(
    properties: Vector[String]
  )
  object ResolveSupport:
    given reader: Reader[structures.CodeActionClientCapabilities.ResolveSupport] = Pickle.macroR
    given writer: Writer[structures.CodeActionClientCapabilities.ResolveSupport] = upickle.default.macroW

/**
 *  The client capabilities  of a [CodeLensRequest](#CodeLensRequest).

 *  @param dynamicRegistration
 *    Whether code lens supports dynamic registration.

 */
case class CodeLensClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object CodeLensClientCapabilities:
  given reader: Reader[structures.CodeLensClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.CodeLensClientCapabilities] = upickle.default.macroW

/**
 *  The client capabilities of a [DocumentLinkRequest](#DocumentLinkRequest).

 *  @param dynamicRegistration
 *    Whether document link supports dynamic registration.

 *  @param tooltipSupport
 *    Whether the client supports the `tooltip` property on `DocumentLink`.
 *    
 *    @since 3.15.0

 */
case class DocumentLinkClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  tooltipSupport: Opt[Boolean] = Opt.empty
)
object DocumentLinkClientCapabilities:
  given reader: Reader[structures.DocumentLinkClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentLinkClientCapabilities] = upickle.default.macroW

/**
 *  @param dynamicRegistration
 *    Whether implementation supports dynamic registration. If this is set to `true`
 *    the client supports the new `DocumentColorRegistrationOptions` return value
 *    for the corresponding server capability as well.

 */
case class DocumentColorClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object DocumentColorClientCapabilities:
  given reader: Reader[structures.DocumentColorClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentColorClientCapabilities] = upickle.default.macroW

/**
 *  Client capabilities of a [DocumentFormattingRequest](#DocumentFormattingRequest).

 *  @param dynamicRegistration
 *    Whether formatting supports dynamic registration.

 */
case class DocumentFormattingClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object DocumentFormattingClientCapabilities:
  given reader: Reader[structures.DocumentFormattingClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentFormattingClientCapabilities] = upickle.default.macroW

/**
 *  Client capabilities of a [DocumentRangeFormattingRequest](#DocumentRangeFormattingRequest).

 *  @param dynamicRegistration
 *    Whether range formatting supports dynamic registration.

 */
case class DocumentRangeFormattingClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object DocumentRangeFormattingClientCapabilities:
  given reader: Reader[structures.DocumentRangeFormattingClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentRangeFormattingClientCapabilities] = upickle.default.macroW

/**
 *  Client capabilities of a [DocumentOnTypeFormattingRequest](#DocumentOnTypeFormattingRequest).

 *  @param dynamicRegistration
 *    Whether on type formatting supports dynamic registration.

 */
case class DocumentOnTypeFormattingClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object DocumentOnTypeFormattingClientCapabilities:
  given reader: Reader[structures.DocumentOnTypeFormattingClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DocumentOnTypeFormattingClientCapabilities] = upickle.default.macroW

/**
 *  @param dynamicRegistration
 *    Whether rename supports dynamic registration.

 *  @param prepareSupport
 *    Client supports testing for validity of rename operations
 *    before execution.
 *    
 *    @since 3.12.0

 *  @param prepareSupportDefaultBehavior
 *    Client supports the default behavior result.
 *    
 *    The value indicates the default behavior used by the
 *    client.
 *    
 *    @since 3.16.0

 *  @param honorsChangeAnnotations
 *    Whether the client honors the change annotations in
 *    text edits and resource operations returned via the
 *    rename request's workspace edit by for example presenting
 *    the workspace edit in the user interface and asking
 *    for confirmation.
 *    
 *    @since 3.16.0

 */
case class RenameClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  prepareSupport: Opt[Boolean] = Opt.empty,
  prepareSupportDefaultBehavior: Opt[enumerations.PrepareSupportDefaultBehavior] = Opt.empty,
  honorsChangeAnnotations: Opt[Boolean] = Opt.empty
)
object RenameClientCapabilities:
  given reader: Reader[structures.RenameClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.RenameClientCapabilities] = upickle.default.macroW

/**
 *  @param dynamicRegistration
 *    Whether implementation supports dynamic registration for folding range
 *    providers. If this is set to `true` the client supports the new
 *    `FoldingRangeRegistrationOptions` return value for the corresponding
 *    server capability as well.

 *  @param rangeLimit
 *    The maximum number of folding ranges that the client prefers to receive
 *    per document. The value serves as a hint, servers are free to follow the
 *    limit.

 *  @param lineFoldingOnly
 *    If set, the client signals that it only supports folding complete lines.
 *    If set, client will ignore specified `startCharacter` and `endCharacter`
 *    properties in a FoldingRange.

 *  @param foldingRangeKind
 *    Specific options for the folding range kind.
 *    
 *    @since 3.17.0

 *  @param foldingRange
 *    Specific options for the folding range.
 *    
 *    @since 3.17.0

 */
case class FoldingRangeClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  rangeLimit: Opt[RuntimeBase.uinteger] = Opt.empty,
  lineFoldingOnly: Opt[Boolean] = Opt.empty,
  foldingRangeKind: Opt[FoldingRangeClientCapabilities.FoldingRangeKind] = Opt.empty,
  foldingRange: Opt[FoldingRangeClientCapabilities.FoldingRange] = Opt.empty
)
object FoldingRangeClientCapabilities:
  given reader: Reader[structures.FoldingRangeClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.FoldingRangeClientCapabilities] = upickle.default.macroW
  /**
   *  @param valueSet
   *    The folding range kind values the client supports. When this
   *    property exists the client also guarantees that it will
   *    handle values outside its set gracefully and falls back
   *    to a default value when unknown.
  
   */
  case class FoldingRangeKind(
    valueSet: Opt[Vector[enumerations.FoldingRangeKind]] = Opt.empty
  )
  object FoldingRangeKind:
    given reader: Reader[structures.FoldingRangeClientCapabilities.FoldingRangeKind] = Pickle.macroR
    given writer: Writer[structures.FoldingRangeClientCapabilities.FoldingRangeKind] = upickle.default.macroW
  /**
   *  @param collapsedText
   *    If set, the client signals that it supports setting collapsedText on
   *    folding ranges to display custom labels instead of the default text.
   *    
   *    @since 3.17.0
  
   */
  case class FoldingRange(
    collapsedText: Opt[Boolean] = Opt.empty
  )
  object FoldingRange:
    given reader: Reader[structures.FoldingRangeClientCapabilities.FoldingRange] = Pickle.macroR
    given writer: Writer[structures.FoldingRangeClientCapabilities.FoldingRange] = upickle.default.macroW

/**
 *  @param dynamicRegistration
 *    Whether implementation supports dynamic registration for selection range providers. If this is set to `true`
 *    the client supports the new `SelectionRangeRegistrationOptions` return value for the corresponding server
 *    capability as well.

 */
case class SelectionRangeClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object SelectionRangeClientCapabilities:
  given reader: Reader[structures.SelectionRangeClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.SelectionRangeClientCapabilities] = upickle.default.macroW

/**
 *  The publish diagnostic client capabilities.

 *  @param relatedInformation
 *    Whether the clients accepts diagnostics with related information.

 *  @param tagSupport
 *    Client supports the tag property to provide meta data about a diagnostic.
 *    Clients supporting tags have to handle unknown tags gracefully.
 *    
 *    @since 3.15.0

 *  @param versionSupport
 *    Whether the client interprets the version property of the
 *    `textDocument/publishDiagnostics` notification's parameter.
 *    
 *    @since 3.15.0

 *  @param codeDescriptionSupport
 *    Client supports a codeDescription property
 *    
 *    @since 3.16.0

 *  @param dataSupport
 *    Whether code action supports the `data` property which is
 *    preserved between a `textDocument/publishDiagnostics` and
 *    `textDocument/codeAction` request.
 *    
 *    @since 3.16.0

 */
case class PublishDiagnosticsClientCapabilities(
  relatedInformation: Opt[Boolean] = Opt.empty,
  tagSupport: Opt[PublishDiagnosticsClientCapabilities.TagSupport] = Opt.empty,
  versionSupport: Opt[Boolean] = Opt.empty,
  codeDescriptionSupport: Opt[Boolean] = Opt.empty,
  dataSupport: Opt[Boolean] = Opt.empty
)
object PublishDiagnosticsClientCapabilities:
  given reader: Reader[structures.PublishDiagnosticsClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.PublishDiagnosticsClientCapabilities] = upickle.default.macroW
  /**
   *  @param valueSet
   *    The tags supported by the client.
  
   */
  case class TagSupport(
    valueSet: Vector[enumerations.DiagnosticTag]
  )
  object TagSupport:
    given reader: Reader[structures.PublishDiagnosticsClientCapabilities.TagSupport] = Pickle.macroR
    given writer: Writer[structures.PublishDiagnosticsClientCapabilities.TagSupport] = upickle.default.macroW

/**
 *  @since 3.16.0

 *  @param dynamicRegistration
 *    Whether implementation supports dynamic registration. If this is set to `true`
 *    the client supports the new `(TextDocumentRegistrationOptions & StaticRegistrationOptions)`
 *    return value for the corresponding server capability as well.

 */
case class CallHierarchyClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object CallHierarchyClientCapabilities:
  given reader: Reader[structures.CallHierarchyClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.CallHierarchyClientCapabilities] = upickle.default.macroW

/**
 *  @since 3.16.0

 *  @param dynamicRegistration
 *    Whether implementation supports dynamic registration. If this is set to `true`
 *    the client supports the new `(TextDocumentRegistrationOptions & StaticRegistrationOptions)`
 *    return value for the corresponding server capability as well.

 *  @param requests
 *    Which requests the client supports and might send to the server
 *    depending on the server's capability. Please note that clients might not
 *    show semantic tokens or degrade some of the user experience if a range
 *    or full request is advertised by the client but not provided by the
 *    server. If for example the client capability `requests.full` and
 *    `request.range` are both set to true but the server only provides a
 *    range provider the client might not render a minimap correctly or might
 *    even decide to not show any semantic tokens at all.

 *  @param tokenTypes
 *    The token types that the client supports.

 *  @param tokenModifiers
 *    The token modifiers that the client supports.

 *  @param formats
 *    The token formats the clients supports.

 *  @param overlappingTokenSupport
 *    Whether the client supports tokens that can overlap each other.

 *  @param multilineTokenSupport
 *    Whether the client supports tokens that can span multiple lines.

 *  @param serverCancelSupport
 *    Whether the client allows the server to actively cancel a
 *    semantic token request, e.g. supports returning
 *    LSPErrorCodes.ServerCancelled. If a server does the client
 *    needs to retrigger the request.
 *    
 *    @since 3.17.0

 *  @param augmentsSyntaxTokens
 *    Whether the client uses semantic tokens to augment existing
 *    syntax tokens. If set to `true` client side created syntax
 *    tokens and semantic tokens are both used for colorization. If
 *    set to `false` the client only uses the returned semantic tokens
 *    for colorization.
 *    
 *    If the value is `undefined` then the client behavior is not
 *    specified.
 *    
 *    @since 3.17.0

 */
case class SemanticTokensClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  requests: SemanticTokensClientCapabilities.Requests,
  tokenTypes: Vector[String],
  tokenModifiers: Vector[String],
  formats: Vector[enumerations.TokenFormat],
  overlappingTokenSupport: Opt[Boolean] = Opt.empty,
  multilineTokenSupport: Opt[Boolean] = Opt.empty,
  serverCancelSupport: Opt[Boolean] = Opt.empty,
  augmentsSyntaxTokens: Opt[Boolean] = Opt.empty
)
object SemanticTokensClientCapabilities:
  given reader: Reader[structures.SemanticTokensClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.SemanticTokensClientCapabilities] = upickle.default.macroW
  /**
   *  @param range
   *    The client will send the `textDocument/semanticTokens/range` request if
   *    the server provides a corresponding handler.
  
   *  @param full
   *    The client will send the `textDocument/semanticTokens/full` request if
   *    the server provides a corresponding handler.
  
   */
  case class Requests(
    range: Opt[(Boolean | Requests.S0)] = Opt.empty,
    full: Opt[(Boolean | Requests.S1)] = Opt.empty
  )
  object Requests:
    private given rd0: Reader[(Boolean | Requests.S0)] = 
      badMerge[(Boolean | Requests.S0)](upickle.default.reader[Boolean], Requests.S0.reader)
    private given wt0: Writer[(Boolean | Requests.S0)] = 
      upickle.default.writer[ujson.Value].comap[(Boolean | Requests.S0)] { _v => 
        (_v: @unchecked) match 
          case v: Boolean => writeJs[Boolean](v)
          case v: Requests.S0 => writeJs[Requests.S0](v)
      }
    private given rd1: Reader[(Boolean | Requests.S1)] = 
      badMerge[(Boolean | Requests.S1)](upickle.default.reader[Boolean], Requests.S1.reader)
    private given wt1: Writer[(Boolean | Requests.S1)] = 
      upickle.default.writer[ujson.Value].comap[(Boolean | Requests.S1)] { _v => 
        (_v: @unchecked) match 
          case v: Boolean => writeJs[Boolean](v)
          case v: Requests.S1 => writeJs[Requests.S1](v)
      }
    given reader: Reader[structures.SemanticTokensClientCapabilities.Requests] = Pickle.macroR
    given writer: Writer[structures.SemanticTokensClientCapabilities.Requests] = upickle.default.macroW
    case class S0(
    )
    object S0:
      given reader: Reader[structures.SemanticTokensClientCapabilities.Requests.S0] = Pickle.macroR
      given writer: Writer[structures.SemanticTokensClientCapabilities.Requests.S0] = upickle.default.macroW
    /**
     *  @param delta
     *    The client will send the `textDocument/semanticTokens/full/delta` request if
     *    the server provides a corresponding handler.
    
     */
    case class S1(
      delta: Opt[Boolean] = Opt.empty
    )
    object S1:
      given reader: Reader[structures.SemanticTokensClientCapabilities.Requests.S1] = Pickle.macroR
      given writer: Writer[structures.SemanticTokensClientCapabilities.Requests.S1] = upickle.default.macroW

/**
 *  Client capabilities for the linked editing range request.
 *  
 *  @since 3.16.0

 *  @param dynamicRegistration
 *    Whether implementation supports dynamic registration. If this is set to `true`
 *    the client supports the new `(TextDocumentRegistrationOptions & StaticRegistrationOptions)`
 *    return value for the corresponding server capability as well.

 */
case class LinkedEditingRangeClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object LinkedEditingRangeClientCapabilities:
  given reader: Reader[structures.LinkedEditingRangeClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.LinkedEditingRangeClientCapabilities] = upickle.default.macroW

/**
 *  Client capabilities specific to the moniker request.
 *  
 *  @since 3.16.0

 *  @param dynamicRegistration
 *    Whether moniker supports dynamic registration. If this is set to `true`
 *    the client supports the new `MonikerRegistrationOptions` return value
 *    for the corresponding server capability as well.

 */
case class MonikerClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object MonikerClientCapabilities:
  given reader: Reader[structures.MonikerClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.MonikerClientCapabilities] = upickle.default.macroW

/**
 *  @since 3.17.0

 *  @param dynamicRegistration
 *    Whether implementation supports dynamic registration. If this is set to `true`
 *    the client supports the new `(TextDocumentRegistrationOptions & StaticRegistrationOptions)`
 *    return value for the corresponding server capability as well.

 */
case class TypeHierarchyClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object TypeHierarchyClientCapabilities:
  given reader: Reader[structures.TypeHierarchyClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.TypeHierarchyClientCapabilities] = upickle.default.macroW

/**
 *  Client capabilities specific to inline values.
 *  
 *  @since 3.17.0

 *  @param dynamicRegistration
 *    Whether implementation supports dynamic registration for inline value providers.

 */
case class InlineValueClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty
)
object InlineValueClientCapabilities:
  given reader: Reader[structures.InlineValueClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.InlineValueClientCapabilities] = upickle.default.macroW

/**
 *  Inlay hint client capabilities.
 *  
 *  @since 3.17.0

 *  @param dynamicRegistration
 *    Whether inlay hints support dynamic registration.

 *  @param resolveSupport
 *    Indicates which properties a client can resolve lazily on an inlay
 *    hint.

 */
case class InlayHintClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  resolveSupport: Opt[InlayHintClientCapabilities.ResolveSupport] = Opt.empty
)
object InlayHintClientCapabilities:
  given reader: Reader[structures.InlayHintClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.InlayHintClientCapabilities] = upickle.default.macroW
  /**
   *  @param properties
   *    The properties that a client can resolve lazily.
  
   */
  case class ResolveSupport(
    properties: Vector[String]
  )
  object ResolveSupport:
    given reader: Reader[structures.InlayHintClientCapabilities.ResolveSupport] = Pickle.macroR
    given writer: Writer[structures.InlayHintClientCapabilities.ResolveSupport] = upickle.default.macroW

/**
 *  Client capabilities specific to diagnostic pull requests.
 *  
 *  @since 3.17.0

 *  @param dynamicRegistration
 *    Whether implementation supports dynamic registration. If this is set to `true`
 *    the client supports the new `(TextDocumentRegistrationOptions & StaticRegistrationOptions)`
 *    return value for the corresponding server capability as well.

 *  @param relatedDocumentSupport
 *    Whether the clients supports related documents for document diagnostic pulls.

 */
case class DiagnosticClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  relatedDocumentSupport: Opt[Boolean] = Opt.empty
)
object DiagnosticClientCapabilities:
  given reader: Reader[structures.DiagnosticClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.DiagnosticClientCapabilities] = upickle.default.macroW

/**
 *  Notebook specific client capabilities.
 *  
 *  @since 3.17.0

 *  @param dynamicRegistration
 *    Whether implementation supports dynamic registration. If this is
 *    set to `true` the client supports the new
 *    `(TextDocumentRegistrationOptions & StaticRegistrationOptions)`
 *    return value for the corresponding server capability as well.

 *  @param executionSummarySupport
 *    The client supports sending execution summary data per cell.

 */
case class NotebookDocumentSyncClientCapabilities(
  dynamicRegistration: Opt[Boolean] = Opt.empty,
  executionSummarySupport: Opt[Boolean] = Opt.empty
)
object NotebookDocumentSyncClientCapabilities:
  given reader: Reader[structures.NotebookDocumentSyncClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.NotebookDocumentSyncClientCapabilities] = upickle.default.macroW

/**
 *  Show message request client capabilities

 *  @param messageActionItem
 *    Capabilities specific to the `MessageActionItem` type.

 */
case class ShowMessageRequestClientCapabilities(
  messageActionItem: Opt[ShowMessageRequestClientCapabilities.MessageActionItem] = Opt.empty
)
object ShowMessageRequestClientCapabilities:
  given reader: Reader[structures.ShowMessageRequestClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ShowMessageRequestClientCapabilities] = upickle.default.macroW
  /**
   *  @param additionalPropertiesSupport
   *    Whether the client supports additional attributes which
   *    are preserved and send back to the server in the
   *    request's response.
  
   */
  case class MessageActionItem(
    additionalPropertiesSupport: Opt[Boolean] = Opt.empty
  )
  object MessageActionItem:
    given reader: Reader[structures.ShowMessageRequestClientCapabilities.MessageActionItem] = Pickle.macroR
    given writer: Writer[structures.ShowMessageRequestClientCapabilities.MessageActionItem] = upickle.default.macroW

/**
 *  Client capabilities for the showDocument request.
 *  
 *  @since 3.16.0

 *  @param support
 *    The client has support for the showDocument
 *    request.

 */
case class ShowDocumentClientCapabilities(
  support: Boolean
)
object ShowDocumentClientCapabilities:
  given reader: Reader[structures.ShowDocumentClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.ShowDocumentClientCapabilities] = upickle.default.macroW

/**
 *  Client capabilities specific to regular expressions.
 *  
 *  @since 3.16.0

 *  @param engine
 *    The engine's name.

 *  @param version
 *    The engine's version.

 */
case class RegularExpressionsClientCapabilities(
  engine: String,
  version: Opt[String] = Opt.empty
)
object RegularExpressionsClientCapabilities:
  given reader: Reader[structures.RegularExpressionsClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.RegularExpressionsClientCapabilities] = upickle.default.macroW

/**
 *  Client capabilities specific to the used markdown parser.
 *  
 *  @since 3.16.0

 *  @param parser
 *    The name of the parser.

 *  @param version
 *    The version of the parser.

 *  @param allowedTags
 *    A list of HTML tags that the client allows / supports in
 *    Markdown.
 *    
 *    @since 3.17.0

 */
case class MarkdownClientCapabilities(
  parser: String,
  version: Opt[String] = Opt.empty,
  allowedTags: Opt[Vector[String]] = Opt.empty
)
object MarkdownClientCapabilities:
  given reader: Reader[structures.MarkdownClientCapabilities] = Pickle.macroR
  given writer: Writer[structures.MarkdownClientCapabilities] = upickle.default.macroW

