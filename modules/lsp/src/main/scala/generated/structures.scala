// format:off
package langoustine.lsp
package structures

import langoustine.*
import runtime.{*, given}

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
object AnnotatedTextEdit extends codecs.structures_AnnotatedTextEditCodec

/**
 *  The parameters passed via an apply workspace edit request.

 *  @param label
 *    An optional label of the workspace edit. This label is
 *    presented in the user interface for example on an undo
 *    stack to undo the workspace edit.

 *  @param edit
 *    The edits to apply.

 */
case class ApplyWorkspaceEditParams(
  label: Option[String] = None,
  edit: structures.WorkspaceEdit
)
object ApplyWorkspaceEditParams extends codecs.structures_ApplyWorkspaceEditParamsCodec

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
  failureReason: Option[String] = None,
  failedChange: Option[runtime.uinteger] = None
)
object ApplyWorkspaceEditResult extends codecs.structures_ApplyWorkspaceEditResultCodec

/**
 *  A base for all symbol information.

 *  @param name
 *    The name of this symbol.

 *  @param kind
 *    The kind of this symbol.

 *  @param tags
 *    Tags for this symbol.
 *    
 *    since 3.16.0

 *  @param containerName
 *    The name of the symbol containing this symbol. This information is for
 *    user interface purposes (e.g. to render a qualifier in the user interface
 *    if necessary). It can't be used to re-infer a hierarchy for the document
 *    symbols.

 */
case class BaseSymbolInformation(
  name: String,
  kind: enumerations.SymbolKind,
  tags: Option[Vector[enumerations.SymbolTag]] = None,
  containerName: Option[String] = None
)
object BaseSymbolInformation extends codecs.structures_BaseSymbolInformationCodec

/**
 *  @since 3.16.0

 *  @param dynamicRegistration
 *    Whether implementation supports dynamic registration. If this is set to `true`
 *    the client supports the new `(TextDocumentRegistrationOptions & StaticRegistrationOptions)`
 *    return value for the corresponding server capability as well.

 */
case class CallHierarchyClientCapabilities(
  dynamicRegistration: Option[Boolean] = None
)
object CallHierarchyClientCapabilities extends codecs.structures_CallHierarchyClientCapabilitiesCodec

/**
 *  Represents an incoming call, e.g. a caller of a method or constructor.
 *  
 *  @since 3.16.0

 *  @param from
 *    The item that makes the call.

 *  @param fromRanges
 *    The ranges at which the calls appear. This is relative to the caller
 *    denoted by {@link CallHierarchyIncomingCall.from `this.from`}.

 */
case class CallHierarchyIncomingCall(
  from: structures.CallHierarchyItem,
  fromRanges: Vector[structures.Range]
)
object CallHierarchyIncomingCall extends codecs.structures_CallHierarchyIncomingCallCodec

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object CallHierarchyIncomingCallsParams extends codecs.structures_CallHierarchyIncomingCallsParamsCodec

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
 *    Must be contained by the {@link CallHierarchyItem.range `range`}.

 *  @param data
 *    A data entry field that is preserved between a call hierarchy prepare and
 *    incoming calls or outgoing calls requests.

 */
case class CallHierarchyItem(
  name: String,
  kind: enumerations.SymbolKind,
  tags: Option[Vector[enumerations.SymbolTag]] = None,
  detail: Option[String] = None,
  uri: runtime.DocumentUri,
  range: structures.Range,
  selectionRange: structures.Range,
  data: Option[io.circe.Json] = None
)
object CallHierarchyItem extends codecs.structures_CallHierarchyItemCodec

/**
 *  Call hierarchy options used during static registration.
 *  
 *  @since 3.16.0

 *  @param workDoneProgress
 */
case class CallHierarchyOptions(
  workDoneProgress: Option[Boolean] = None
)
object CallHierarchyOptions extends codecs.structures_CallHierarchyOptionsCodec

/**
 *  Represents an outgoing call, e.g. calling a getter from a method or a method from a constructor etc.
 *  
 *  @since 3.16.0

 *  @param to
 *    The item that is called.

 *  @param fromRanges
 *    The range at which this item is called. This is the range relative to the caller, e.g the item
 *    passed to {@link CallHierarchyItemProvider.provideCallHierarchyOutgoingCalls `provideCallHierarchyOutgoingCalls`}
 *    and not {@link CallHierarchyOutgoingCall.to `this.to`}.

 */
case class CallHierarchyOutgoingCall(
  to: structures.CallHierarchyItem,
  fromRanges: Vector[structures.Range]
)
object CallHierarchyOutgoingCall extends codecs.structures_CallHierarchyOutgoingCallCodec

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object CallHierarchyOutgoingCallsParams extends codecs.structures_CallHierarchyOutgoingCallsParamsCodec

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
  workDoneToken: Option[aliases.ProgressToken] = None
)
object CallHierarchyPrepareParams extends codecs.structures_CallHierarchyPrepareParamsCodec

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
  documentSelector: Option[aliases.DocumentSelector] = None,
  id: Option[String] = None
)
object CallHierarchyRegistrationOptions extends codecs.structures_CallHierarchyRegistrationOptionsCodec

/**
 *  @param id
 *    The request id to cancel.

 */
case class CancelParams(
  id: (Int | String)
)
object CancelParams extends codecs.structures_CancelParamsCodec

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
  needsConfirmation: Option[Boolean] = None,
  description: Option[String] = None
)
object ChangeAnnotation extends codecs.structures_ChangeAnnotationCodec

/**
 *  Defines the capabilities provided by the client.

 *  @param workspace
 *    Workspace specific client capabilities.

 *  @param textDocument
 *    Text document specific client capabilities.

 *  @param notebookDocument
 *    Capabilities specific to the notebook document support.
 *    
 *    since 3.17.0

 *  @param window
 *    Window specific client capabilities.

 *  @param general
 *    General client capabilities.
 *    
 *    since 3.16.0

 *  @param experimental
 *    Experimental client capabilities.

 */
case class ClientCapabilities(
  workspace: Option[structures.WorkspaceClientCapabilities] = None,
  textDocument: Option[structures.TextDocumentClientCapabilities] = None,
  notebookDocument: Option[structures.NotebookDocumentClientCapabilities] = None,
  window: Option[structures.WindowClientCapabilities] = None,
  general: Option[structures.GeneralClientCapabilities] = None,
  experimental: Option[io.circe.Json] = None
)
object ClientCapabilities extends codecs.structures_ClientCapabilitiesCodec

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
 *    since 3.15.0

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
 *    since 3.16.0

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
 *    since 3.16.0

 */
case class CodeAction(
  title: String,
  kind: Option[enumerations.CodeActionKind] = None,
  diagnostics: Option[Vector[structures.Diagnostic]] = None,
  isPreferred: Option[Boolean] = None,
  disabled: Option[CodeAction.Disabled] = None,
  edit: Option[structures.WorkspaceEdit] = None,
  command: Option[structures.Command] = None,
  data: Option[io.circe.Json] = None
)
object CodeAction extends codecs.structures_CodeActionCodec:
  /**
   *  @param reason
   *    Human readable description of why the code action is currently disabled.
   *    
   *    This is displayed in the code actions UI.
  
   */
  case class Disabled(
    reason: String
  )
  object Disabled extends codecs.structures_CodeAction_DisabledCodec

/**
 *  The Client Capabilities of a {@link CodeActionRequest}.

 *  @param dynamicRegistration
 *    Whether code action supports dynamic registration.

 *  @param codeActionLiteralSupport
 *    The client support code action literals of type `CodeAction` as a valid
 *    response of the `textDocument/codeAction` request. If the property is not
 *    set the request can only return `Command` literals.
 *    
 *    since 3.8.0

 *  @param isPreferredSupport
 *    Whether code action supports the `isPreferred` property.
 *    
 *    since 3.15.0

 *  @param disabledSupport
 *    Whether code action supports the `disabled` property.
 *    
 *    since 3.16.0

 *  @param dataSupport
 *    Whether code action supports the `data` property which is
 *    preserved between a `textDocument/codeAction` and a
 *    `codeAction/resolve` request.
 *    
 *    since 3.16.0

 *  @param resolveSupport
 *    Whether the client supports resolving additional code action
 *    properties via a separate `codeAction/resolve` request.
 *    
 *    since 3.16.0

 *  @param honorsChangeAnnotations
 *    Whether the client honors the change annotations in
 *    text edits and resource operations returned via the
 *    `CodeAction#edit` property by for example presenting
 *    the workspace edit in the user interface and asking
 *    for confirmation.
 *    
 *    since 3.16.0

 */
case class CodeActionClientCapabilities(
  dynamicRegistration: Option[Boolean] = None,
  codeActionLiteralSupport: Option[CodeActionClientCapabilities.CodeActionLiteralSupport] = None,
  isPreferredSupport: Option[Boolean] = None,
  disabledSupport: Option[Boolean] = None,
  dataSupport: Option[Boolean] = None,
  resolveSupport: Option[CodeActionClientCapabilities.ResolveSupport] = None,
  honorsChangeAnnotations: Option[Boolean] = None
)
object CodeActionClientCapabilities extends codecs.structures_CodeActionClientCapabilitiesCodec:
  /**
   *  @param codeActionKind
   *    The code action kind is support with the following value
   *    set.
  
   */
  case class CodeActionLiteralSupport(
    codeActionKind: CodeActionLiteralSupport.CodeActionKind
  )
  object CodeActionLiteralSupport extends codecs.structures_CodeActionClientCapabilities_CodeActionLiteralSupportCodec:
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
    object CodeActionKind extends codecs.structures_CodeActionClientCapabilities_CodeActionLiteralSupport_CodeActionKindCodec
  /**
   *  @param properties
   *    The properties that a client can resolve lazily.
  
   */
  case class ResolveSupport(
    properties: Vector[String]
  )
  object ResolveSupport extends codecs.structures_CodeActionClientCapabilities_ResolveSupportCodec

/**
 *  Contains additional diagnostic information about the context in which
 *  a {@link CodeActionProvider.provideCodeActions code action} is run.

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
 *    since 3.17.0

 */
case class CodeActionContext(
  diagnostics: Vector[structures.Diagnostic],
  only: Option[Vector[enumerations.CodeActionKind]] = None,
  triggerKind: Option[enumerations.CodeActionTriggerKind] = None
)
object CodeActionContext extends codecs.structures_CodeActionContextCodec

/**
 *  Provider options for a {@link CodeActionRequest}.

 *  @param codeActionKinds
 *    CodeActionKinds that this server may return.
 *    
 *    The list of kinds may be generic, such as `CodeActionKind.Refactor`, or the server
 *    may list out every specific kind they provide.

 *  @param resolveProvider
 *    The server provides support to resolve additional
 *    information for a code action.
 *    
 *    since 3.16.0

 *  @param workDoneProgress
 */
case class CodeActionOptions(
  codeActionKinds: Option[Vector[enumerations.CodeActionKind]] = None,
  resolveProvider: Option[Boolean] = None,
  workDoneProgress: Option[Boolean] = None
)
object CodeActionOptions extends codecs.structures_CodeActionOptionsCodec

/**
 *  The parameters of a {@link CodeActionRequest}.

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object CodeActionParams extends codecs.structures_CodeActionParamsCodec

/**
 *  Registration options for a {@link CodeActionRequest}.

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
 *    since 3.16.0

 */
case class CodeActionRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None,
  codeActionKinds: Option[Vector[enumerations.CodeActionKind]] = None,
  resolveProvider: Option[Boolean] = None
)
object CodeActionRegistrationOptions extends codecs.structures_CodeActionRegistrationOptionsCodec

/**
 *  Structure to capture a description for an error code.
 *  
 *  @since 3.16.0

 *  @param href
 *    An URI to open with more information about the diagnostic error.

 */
case class CodeDescription(
  href: runtime.Uri
)
object CodeDescription extends codecs.structures_CodeDescriptionCodec

/**
 *  A code lens represents a {@link Command command} that should be shown along with
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
 *    a {@link CodeLensRequest} and a {@link CodeLensResolveRequest}

 */
case class CodeLens(
  range: structures.Range,
  command: Option[structures.Command] = None,
  data: Option[io.circe.Json] = None
)
object CodeLens extends codecs.structures_CodeLensCodec

/**
 *  The client capabilities  of a {@link CodeLensRequest}.

 *  @param dynamicRegistration
 *    Whether code lens supports dynamic registration.

 */
case class CodeLensClientCapabilities(
  dynamicRegistration: Option[Boolean] = None
)
object CodeLensClientCapabilities extends codecs.structures_CodeLensClientCapabilitiesCodec

/**
 *  Code Lens provider options of a {@link CodeLensRequest}.

 *  @param resolveProvider
 *    Code lens has a resolve provider as well.

 *  @param workDoneProgress
 */
case class CodeLensOptions(
  resolveProvider: Option[Boolean] = None,
  workDoneProgress: Option[Boolean] = None
)
object CodeLensOptions extends codecs.structures_CodeLensOptionsCodec

/**
 *  The parameters of a {@link CodeLensRequest}.

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object CodeLensParams extends codecs.structures_CodeLensParamsCodec

/**
 *  Registration options for a {@link CodeLensRequest}.

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param resolveProvider
 *    Code lens has a resolve provider as well.

 */
case class CodeLensRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None,
  resolveProvider: Option[Boolean] = None
)
object CodeLensRegistrationOptions extends codecs.structures_CodeLensRegistrationOptionsCodec

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
  refreshSupport: Option[Boolean] = None
)
object CodeLensWorkspaceClientCapabilities extends codecs.structures_CodeLensWorkspaceClientCapabilitiesCodec

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
object Color extends codecs.structures_ColorCodec

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
object ColorInformation extends codecs.structures_ColorInformationCodec

/**
 *  @param label
 *    The label of this color presentation. It will be shown on the color
 *    picker header. By default this is also the text that is inserted when selecting
 *    this color presentation.

 *  @param textEdit
 *    An {@link TextEdit edit} which is applied to a document when selecting
 *    this presentation for the color.  When `falsy` the {@link ColorPresentation.label label}
 *    is used.

 *  @param additionalTextEdits
 *    An optional array of additional {@link TextEdit text edits} that are applied when
 *    selecting this color presentation. Edits must not overlap with the main {@link ColorPresentation.textEdit edit} nor with themselves.

 */
case class ColorPresentation(
  label: String,
  textEdit: Option[structures.TextEdit] = None,
  additionalTextEdits: Option[Vector[structures.TextEdit]] = None
)
object ColorPresentation extends codecs.structures_ColorPresentationCodec

/**
 *  Parameters for a {@link ColorPresentationRequest}.

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object ColorPresentationParams extends codecs.structures_ColorPresentationParamsCodec

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
  arguments: Option[Vector[io.circe.Json]] = None
)
object Command extends codecs.structures_CommandCodec

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
 *    since 3.17.0

 *  @param contextSupport
 *    The client supports to send additional context information for a
 *    `textDocument/completion` request.

 *  @param completionList
 *    The client supports the following `CompletionList` specific
 *    capabilities.
 *    
 *    since 3.17.0

 */
case class CompletionClientCapabilities(
  dynamicRegistration: Option[Boolean] = None,
  completionItem: Option[CompletionClientCapabilities.CompletionItem] = None,
  completionItemKind: Option[CompletionClientCapabilities.CompletionItemKind] = None,
  insertTextMode: Option[enumerations.InsertTextMode] = None,
  contextSupport: Option[Boolean] = None,
  completionList: Option[CompletionClientCapabilities.CompletionList] = None
)
object CompletionClientCapabilities extends codecs.structures_CompletionClientCapabilitiesCodec:
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
   *    since 3.15.0
  
   *  @param insertReplaceSupport
   *    Client support insert replace edit to control different behavior if a
   *    completion item is inserted in the text or should replace text.
   *    
   *    since 3.16.0
  
   *  @param resolveSupport
   *    Indicates which properties a client can resolve lazily on a completion
   *    item. Before version 3.16.0 only the predefined properties `documentation`
   *    and `details` could be resolved lazily.
   *    
   *    since 3.16.0
  
   *  @param insertTextModeSupport
   *    The client supports the `insertTextMode` property on
   *    a completion item to override the whitespace handling mode
   *    as defined by the client (see `insertTextMode`).
   *    
   *    since 3.16.0
  
   *  @param labelDetailsSupport
   *    The client has support for completion item label
   *    details (see also `CompletionItemLabelDetails`).
   *    
   *    since 3.17.0
  
   */
  case class CompletionItem(
    snippetSupport: Option[Boolean] = None,
    commitCharactersSupport: Option[Boolean] = None,
    documentationFormat: Option[Vector[enumerations.MarkupKind]] = None,
    deprecatedSupport: Option[Boolean] = None,
    preselectSupport: Option[Boolean] = None,
    tagSupport: Option[CompletionItem.TagSupport] = None,
    insertReplaceSupport: Option[Boolean] = None,
    resolveSupport: Option[CompletionItem.ResolveSupport] = None,
    insertTextModeSupport: Option[CompletionItem.InsertTextModeSupport] = None,
    labelDetailsSupport: Option[Boolean] = None
  )
  object CompletionItem extends codecs.structures_CompletionClientCapabilities_CompletionItemCodec:
    /**
     *  @param valueSet
     *    The tags supported by the client.
    
     */
    case class TagSupport(
      valueSet: Vector[enumerations.CompletionItemTag]
    )
    object TagSupport extends codecs.structures_CompletionClientCapabilities_CompletionItem_TagSupportCodec
    /**
     *  @param properties
     *    The properties that a client can resolve lazily.
    
     */
    case class ResolveSupport(
      properties: Vector[String]
    )
    object ResolveSupport extends codecs.structures_CompletionClientCapabilities_CompletionItem_ResolveSupportCodec
    case class InsertTextModeSupport(
      valueSet: Vector[enumerations.InsertTextMode]
    )
    object InsertTextModeSupport extends codecs.structures_CompletionClientCapabilities_CompletionItem_InsertTextModeSupportCodec
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
    valueSet: Option[Vector[enumerations.CompletionItemKind]] = None
  )
  object CompletionItemKind extends codecs.structures_CompletionClientCapabilities_CompletionItemKindCodec
  /**
   *  @param itemDefaults
   *    The client supports the following itemDefaults on
   *    a completion list.
   *    
   *    The value lists the supported property names of the
   *    `CompletionList.itemDefaults` object. If omitted
   *    no properties are supported.
   *    
   *    since 3.17.0
  
   */
  case class CompletionList(
    itemDefaults: Option[Vector[String]] = None
  )
  object CompletionList extends codecs.structures_CompletionClientCapabilities_CompletionListCodec

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
  triggerCharacter: Option[String] = None
)
object CompletionContext extends codecs.structures_CompletionContextCodec

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
 *    since 3.17.0

 *  @param kind
 *    The kind of this completion item. Based of the kind
 *    an icon is chosen by the editor.

 *  @param tags
 *    Tags for this completion item.
 *    
 *    since 3.15.0

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
 *    with other items. When `falsy` the {@link CompletionItem.label label}
 *    is used.

 *  @param filterText
 *    A string that should be used when filtering a set of
 *    completion items. When `falsy` the {@link CompletionItem.label label}
 *    is used.

 *  @param insertText
 *    A string that should be inserted into a document when selecting
 *    this completion. When `falsy` the {@link CompletionItem.label label}
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
 *    since 3.16.0

 *  @param textEdit
 *    An {@link TextEdit edit} which is applied to a document when selecting
 *    this completion. When an edit is provided the value of
 *    {@link CompletionItem.insertText insertText} is ignored.
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
 *    since 3.16.0 additional type `InsertReplaceEdit`

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
 *    since 3.17.0

 *  @param additionalTextEdits
 *    An optional array of additional {@link TextEdit text edits} that are applied when
 *    selecting this completion. Edits must not overlap (including the same insert position)
 *    with the main {@link CompletionItem.textEdit edit} nor with themselves.
 *    
 *    Additional text edits should be used to change text unrelated to the current cursor position
 *    (for example adding an import statement at the top of the file if the completion item will
 *    insert an unqualified type).

 *  @param commitCharacters
 *    An optional set of characters that when pressed while this completion is active will accept it first and
 *    then type that character. *Note* that all commit characters should have `length=1` and that superfluous
 *    characters will be ignored.

 *  @param command
 *    An optional {@link Command command} that is executed *after* inserting this completion. *Note* that
 *    additional modifications to the current document should be described with the
 *    {@link CompletionItem.additionalTextEdits additionalTextEdits}-property.

 *  @param data
 *    A data entry field that is preserved on a completion item between a
 *    {@link CompletionRequest} and a {@link CompletionResolveRequest}.

 */
case class CompletionItem(
  label: String,
  labelDetails: Option[structures.CompletionItemLabelDetails] = None,
  kind: Option[enumerations.CompletionItemKind] = None,
  tags: Option[Vector[enumerations.CompletionItemTag]] = None,
  detail: Option[String] = None,
  documentation: Option[(String | structures.MarkupContent)] = None,
  deprecated: Option[Boolean] = None,
  preselect: Option[Boolean] = None,
  sortText: Option[String] = None,
  filterText: Option[String] = None,
  insertText: Option[String] = None,
  insertTextFormat: Option[enumerations.InsertTextFormat] = None,
  insertTextMode: Option[enumerations.InsertTextMode] = None,
  textEdit: Option[(structures.TextEdit | structures.InsertReplaceEdit)] = None,
  textEditText: Option[String] = None,
  additionalTextEdits: Option[Vector[structures.TextEdit]] = None,
  commitCharacters: Option[Vector[String]] = None,
  command: Option[structures.Command] = None,
  data: Option[io.circe.Json] = None
)
object CompletionItem extends codecs.structures_CompletionItemCodec

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
  detail: Option[String] = None,
  description: Option[String] = None
)
object CompletionItemLabelDetails extends codecs.structures_CompletionItemLabelDetailsCodec

/**
 *  Represents a collection of {@link CompletionItem completion items} to be presented
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
 *    since 3.17.0

 *  @param items
 *    The completion items.

 */
case class CompletionList(
  isIncomplete: Boolean,
  itemDefaults: Option[CompletionList.ItemDefaults] = None,
  items: Vector[structures.CompletionItem]
)
object CompletionList extends codecs.structures_CompletionListCodec:
  /**
   *  @param commitCharacters
   *    A default commit character set.
   *    
   *    since 3.17.0
  
   *  @param editRange
   *    A default edit range.
   *    
   *    since 3.17.0
  
   *  @param insertTextFormat
   *    A default insert text format.
   *    
   *    since 3.17.0
  
   *  @param insertTextMode
   *    A default insert text mode.
   *    
   *    since 3.17.0
  
   *  @param data
   *    A default data value.
   *    
   *    since 3.17.0
  
   */
  case class ItemDefaults(
    commitCharacters: Option[Vector[String]] = None,
    editRange: Option[(structures.Range | ItemDefaults.S0)] = None,
    insertTextFormat: Option[enumerations.InsertTextFormat] = None,
    insertTextMode: Option[enumerations.InsertTextMode] = None,
    data: Option[io.circe.Json] = None
  )
  object ItemDefaults extends codecs.structures_CompletionList_ItemDefaultsCodec:
    case class S0(
      insert: structures.Range,
      replace: structures.Range
    )
    object S0 extends codecs.structures_CompletionList_ItemDefaults_S0Codec

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
 *    since 3.2.0

 *  @param resolveProvider
 *    The server provides support to resolve additional
 *    information for a completion item.

 *  @param completionItem
 *    The server supports the following `CompletionItem` specific
 *    capabilities.
 *    
 *    since 3.17.0

 *  @param workDoneProgress
 */
case class CompletionOptions(
  triggerCharacters: Option[Vector[String]] = None,
  allCommitCharacters: Option[Vector[String]] = None,
  resolveProvider: Option[Boolean] = None,
  completionItem: Option[CompletionOptions.CompletionItem] = None,
  workDoneProgress: Option[Boolean] = None
)
object CompletionOptions extends codecs.structures_CompletionOptionsCodec:
  /**
   *  @param labelDetailsSupport
   *    The server has support for completion item label
   *    details (see also `CompletionItemLabelDetails`) when
   *    receiving a completion item in a resolve call.
   *    
   *    since 3.17.0
  
   */
  case class CompletionItem(
    labelDetailsSupport: Option[Boolean] = None
  )
  object CompletionItem extends codecs.structures_CompletionOptions_CompletionItemCodec

/**
 *  Completion parameters

 *  @param context
 *    The completion context. This is only available if the client specifies
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
  context: Option[structures.CompletionContext] = None,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object CompletionParams extends codecs.structures_CompletionParamsCodec

/**
 *  Registration options for a {@link CompletionRequest}.

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
 *    since 3.2.0

 *  @param resolveProvider
 *    The server provides support to resolve additional
 *    information for a completion item.

 *  @param completionItem
 *    The server supports the following `CompletionItem` specific
 *    capabilities.
 *    
 *    since 3.17.0

 */
case class CompletionRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None,
  triggerCharacters: Option[Vector[String]] = None,
  allCommitCharacters: Option[Vector[String]] = None,
  resolveProvider: Option[Boolean] = None,
  completionItem: Option[CompletionRegistrationOptions.CompletionItem] = None
)
object CompletionRegistrationOptions extends codecs.structures_CompletionRegistrationOptionsCodec:
  /**
   *  @param labelDetailsSupport
   *    The server has support for completion item label
   *    details (see also `CompletionItemLabelDetails`) when
   *    receiving a completion item in a resolve call.
   *    
   *    since 3.17.0
  
   */
  case class CompletionItem(
    labelDetailsSupport: Option[Boolean] = None
  )
  object CompletionItem extends codecs.structures_CompletionRegistrationOptions_CompletionItemCodec

/**
 *  @param scopeUri
 *    The scope to get the configuration section for.

 *  @param section
 *    The configuration section asked for.

 */
case class ConfigurationItem(
  scopeUri: Option[runtime.Uri] = None,
  section: Option[String] = None
)
object ConfigurationItem extends codecs.structures_ConfigurationItemCodec

/**
 *  The parameters of a configuration request.

 *  @param items
 */
case class ConfigurationParams(
  items: Vector[structures.ConfigurationItem]
)
object ConfigurationParams extends codecs.structures_ConfigurationParamsCodec

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
 *    since 3.16.0

 */
case class CreateFile(
  kind: "create",
  uri: runtime.DocumentUri,
  options: Option[structures.CreateFileOptions] = None,
  annotationId: Option[aliases.ChangeAnnotationIdentifier] = None
)
object CreateFile extends codecs.structures_CreateFileCodec

/**
 *  Options to create a file.

 *  @param overwrite
 *    Overwrite existing file. Overwrite wins over `ignoreIfExists`

 *  @param ignoreIfExists
 *    Ignore if exists.

 */
case class CreateFileOptions(
  overwrite: Option[Boolean] = None,
  ignoreIfExists: Option[Boolean] = None
)
object CreateFileOptions extends codecs.structures_CreateFileOptionsCodec

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
object CreateFilesParams extends codecs.structures_CreateFilesParamsCodec

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
  dynamicRegistration: Option[Boolean] = None,
  linkSupport: Option[Boolean] = None
)
object DeclarationClientCapabilities extends codecs.structures_DeclarationClientCapabilitiesCodec

case class DeclarationOptions(
  workDoneProgress: Option[Boolean] = None
)
object DeclarationOptions extends codecs.structures_DeclarationOptionsCodec

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object DeclarationParams extends codecs.structures_DeclarationParamsCodec

/**
 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class DeclarationRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None,
  id: Option[String] = None
)
object DeclarationRegistrationOptions extends codecs.structures_DeclarationRegistrationOptionsCodec

/**
 *  Client Capabilities for a {@link DefinitionRequest}.

 *  @param dynamicRegistration
 *    Whether definition supports dynamic registration.

 *  @param linkSupport
 *    The client supports additional metadata in the form of definition links.
 *    
 *    since 3.14.0

 */
case class DefinitionClientCapabilities(
  dynamicRegistration: Option[Boolean] = None,
  linkSupport: Option[Boolean] = None
)
object DefinitionClientCapabilities extends codecs.structures_DefinitionClientCapabilitiesCodec

/**
 *  Server Capabilities for a {@link DefinitionRequest}.

 *  @param workDoneProgress
 */
case class DefinitionOptions(
  workDoneProgress: Option[Boolean] = None
)
object DefinitionOptions extends codecs.structures_DefinitionOptionsCodec

/**
 *  Parameters for a {@link DefinitionRequest}.

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object DefinitionParams extends codecs.structures_DefinitionParamsCodec

/**
 *  Registration options for a {@link DefinitionRequest}.

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 */
case class DefinitionRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None
)
object DefinitionRegistrationOptions extends codecs.structures_DefinitionRegistrationOptionsCodec

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
 *    since 3.16.0

 */
case class DeleteFile(
  kind: "delete",
  uri: runtime.DocumentUri,
  options: Option[structures.DeleteFileOptions] = None,
  annotationId: Option[aliases.ChangeAnnotationIdentifier] = None
)
object DeleteFile extends codecs.structures_DeleteFileCodec

/**
 *  Delete file options

 *  @param recursive
 *    Delete the content recursively if a folder is denoted.

 *  @param ignoreIfNotExists
 *    Ignore the operation if the file doesn't exist.

 */
case class DeleteFileOptions(
  recursive: Option[Boolean] = None,
  ignoreIfNotExists: Option[Boolean] = None
)
object DeleteFileOptions extends codecs.structures_DeleteFileOptionsCodec

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
object DeleteFilesParams extends codecs.structures_DeleteFilesParamsCodec

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
 *    since 3.16.0

 *  @param source
 *    A human-readable string describing the source of this
 *    diagnostic, e.g. 'typescript' or 'super lint'. It usually
 *    appears in the user interface.

 *  @param message
 *    The diagnostic's message. It usually appears in the user interface

 *  @param tags
 *    Additional metadata about the diagnostic.
 *    
 *    since 3.15.0

 *  @param relatedInformation
 *    An array of related diagnostic information, e.g. when symbol-names within
 *    a scope collide all definitions can be marked via this property.

 *  @param data
 *    A data entry field that is preserved between a `textDocument/publishDiagnostics`
 *    notification and `textDocument/codeAction` request.
 *    
 *    since 3.16.0

 */
case class Diagnostic(
  range: structures.Range,
  severity: Option[enumerations.DiagnosticSeverity] = None,
  code: Option[(Int | String)] = None,
  codeDescription: Option[structures.CodeDescription] = None,
  source: Option[String] = None,
  message: String,
  tags: Option[Vector[enumerations.DiagnosticTag]] = None,
  relatedInformation: Option[Vector[structures.DiagnosticRelatedInformation]] = None,
  data: Option[io.circe.Json] = None
)
object Diagnostic extends codecs.structures_DiagnosticCodec

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
  dynamicRegistration: Option[Boolean] = None,
  relatedDocumentSupport: Option[Boolean] = None
)
object DiagnosticClientCapabilities extends codecs.structures_DiagnosticClientCapabilitiesCodec

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
  identifier: Option[String] = None,
  interFileDependencies: Boolean,
  workspaceDiagnostics: Boolean,
  workDoneProgress: Option[Boolean] = None
)
object DiagnosticOptions extends codecs.structures_DiagnosticOptionsCodec

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
  documentSelector: Option[aliases.DocumentSelector] = None,
  identifier: Option[String] = None,
  interFileDependencies: Boolean,
  workspaceDiagnostics: Boolean,
  id: Option[String] = None
)
object DiagnosticRegistrationOptions extends codecs.structures_DiagnosticRegistrationOptionsCodec

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
object DiagnosticRelatedInformation extends codecs.structures_DiagnosticRelatedInformationCodec

/**
 *  Cancellation data returned from a diagnostic request.
 *  
 *  @since 3.17.0

 *  @param retriggerRequest
 */
case class DiagnosticServerCancellationData(
  retriggerRequest: Boolean
)
object DiagnosticServerCancellationData extends codecs.structures_DiagnosticServerCancellationDataCodec

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
  refreshSupport: Option[Boolean] = None
)
object DiagnosticWorkspaceClientCapabilities extends codecs.structures_DiagnosticWorkspaceClientCapabilitiesCodec

/**
 *  @param dynamicRegistration
 *    Did change configuration notification supports dynamic registration.

 */
case class DidChangeConfigurationClientCapabilities(
  dynamicRegistration: Option[Boolean] = None
)
object DidChangeConfigurationClientCapabilities extends codecs.structures_DidChangeConfigurationClientCapabilitiesCodec

/**
 *  The parameters of a change configuration notification.

 *  @param settings
 *    The actual changed settings

 */
case class DidChangeConfigurationParams(
  settings: io.circe.Json
)
object DidChangeConfigurationParams extends codecs.structures_DidChangeConfigurationParamsCodec

case class DidChangeConfigurationRegistrationOptions(
  section: Option[(String | Vector[String])] = None
)
object DidChangeConfigurationRegistrationOptions extends codecs.structures_DidChangeConfigurationRegistrationOptionsCodec

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
object DidChangeNotebookDocumentParams extends codecs.structures_DidChangeNotebookDocumentParamsCodec

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
object DidChangeTextDocumentParams extends codecs.structures_DidChangeTextDocumentParamsCodec

/**
 *  @param dynamicRegistration
 *    Did change watched files notification supports dynamic registration. Please note
 *    that the current protocol doesn't support static configuration for file changes
 *    from the server side.

 *  @param relativePatternSupport
 *    Whether the client has support for {@link  RelativePattern relative pattern}
 *    or not.
 *    
 *    since 3.17.0

 */
case class DidChangeWatchedFilesClientCapabilities(
  dynamicRegistration: Option[Boolean] = None,
  relativePatternSupport: Option[Boolean] = None
)
object DidChangeWatchedFilesClientCapabilities extends codecs.structures_DidChangeWatchedFilesClientCapabilitiesCodec

/**
 *  The watched files change notification's parameters.

 *  @param changes
 *    The actual file events.

 */
case class DidChangeWatchedFilesParams(
  changes: Vector[structures.FileEvent]
)
object DidChangeWatchedFilesParams extends codecs.structures_DidChangeWatchedFilesParamsCodec

/**
 *  Describe options to be used when registered for text document change events.

 *  @param watchers
 *    The watchers to register.

 */
case class DidChangeWatchedFilesRegistrationOptions(
  watchers: Vector[structures.FileSystemWatcher]
)
object DidChangeWatchedFilesRegistrationOptions extends codecs.structures_DidChangeWatchedFilesRegistrationOptionsCodec

/**
 *  The parameters of a `workspace/didChangeWorkspaceFolders` notification.

 *  @param event
 *    The actual workspace folder change event.

 */
case class DidChangeWorkspaceFoldersParams(
  event: structures.WorkspaceFoldersChangeEvent
)
object DidChangeWorkspaceFoldersParams extends codecs.structures_DidChangeWorkspaceFoldersParamsCodec

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
object DidCloseNotebookDocumentParams extends codecs.structures_DidCloseNotebookDocumentParamsCodec

/**
 *  The parameters sent in a close text document notification

 *  @param textDocument
 *    The document that was closed.

 */
case class DidCloseTextDocumentParams(
  textDocument: structures.TextDocumentIdentifier
)
object DidCloseTextDocumentParams extends codecs.structures_DidCloseTextDocumentParamsCodec

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
object DidOpenNotebookDocumentParams extends codecs.structures_DidOpenNotebookDocumentParamsCodec

/**
 *  The parameters sent in an open text document notification

 *  @param textDocument
 *    The document that was opened.

 */
case class DidOpenTextDocumentParams(
  textDocument: structures.TextDocumentItem
)
object DidOpenTextDocumentParams extends codecs.structures_DidOpenTextDocumentParamsCodec

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
object DidSaveNotebookDocumentParams extends codecs.structures_DidSaveNotebookDocumentParamsCodec

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
  text: Option[String] = None
)
object DidSaveTextDocumentParams extends codecs.structures_DidSaveTextDocumentParamsCodec

/**
 *  @param dynamicRegistration
 *    Whether implementation supports dynamic registration. If this is set to `true`
 *    the client supports the new `DocumentColorRegistrationOptions` return value
 *    for the corresponding server capability as well.

 */
case class DocumentColorClientCapabilities(
  dynamicRegistration: Option[Boolean] = None
)
object DocumentColorClientCapabilities extends codecs.structures_DocumentColorClientCapabilitiesCodec

case class DocumentColorOptions(
  workDoneProgress: Option[Boolean] = None
)
object DocumentColorOptions extends codecs.structures_DocumentColorOptionsCodec

/**
 *  Parameters for a {@link DocumentColorRequest}.

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object DocumentColorParams extends codecs.structures_DocumentColorParamsCodec

/**
 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class DocumentColorRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None,
  id: Option[String] = None
)
object DocumentColorRegistrationOptions extends codecs.structures_DocumentColorRegistrationOptionsCodec

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
  identifier: Option[String] = None,
  previousResultId: Option[String] = None,
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object DocumentDiagnosticParams extends codecs.structures_DocumentDiagnosticParamsCodec

/**
 *  A partial result for a document diagnostic report.
 *  
 *  @since 3.17.0

 *  @param relatedDocuments
 */
case class DocumentDiagnosticReportPartialResult(
  relatedDocuments: Map[runtime.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)]
)
object DocumentDiagnosticReportPartialResult extends codecs.structures_DocumentDiagnosticReportPartialResultCodec

/**
 *  Client capabilities of a {@link DocumentFormattingRequest}.

 *  @param dynamicRegistration
 *    Whether formatting supports dynamic registration.

 */
case class DocumentFormattingClientCapabilities(
  dynamicRegistration: Option[Boolean] = None
)
object DocumentFormattingClientCapabilities extends codecs.structures_DocumentFormattingClientCapabilitiesCodec

/**
 *  Provider options for a {@link DocumentFormattingRequest}.

 *  @param workDoneProgress
 */
case class DocumentFormattingOptions(
  workDoneProgress: Option[Boolean] = None
)
object DocumentFormattingOptions extends codecs.structures_DocumentFormattingOptionsCodec

/**
 *  The parameters of a {@link DocumentFormattingRequest}.

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
  workDoneToken: Option[aliases.ProgressToken] = None
)
object DocumentFormattingParams extends codecs.structures_DocumentFormattingParamsCodec

/**
 *  Registration options for a {@link DocumentFormattingRequest}.

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 */
case class DocumentFormattingRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None
)
object DocumentFormattingRegistrationOptions extends codecs.structures_DocumentFormattingRegistrationOptionsCodec

/**
 *  A document highlight is a range inside a text document which deserves
 *  special attention. Usually a document highlight is visualized by changing
 *  the background color of its range.

 *  @param range
 *    The range this highlight applies to.

 *  @param kind
 *    The highlight kind, default is {@link DocumentHighlightKind.Text text}.

 */
case class DocumentHighlight(
  range: structures.Range,
  kind: Option[enumerations.DocumentHighlightKind] = None
)
object DocumentHighlight extends codecs.structures_DocumentHighlightCodec

/**
 *  Client Capabilities for a {@link DocumentHighlightRequest}.

 *  @param dynamicRegistration
 *    Whether document highlight supports dynamic registration.

 */
case class DocumentHighlightClientCapabilities(
  dynamicRegistration: Option[Boolean] = None
)
object DocumentHighlightClientCapabilities extends codecs.structures_DocumentHighlightClientCapabilitiesCodec

/**
 *  Provider options for a {@link DocumentHighlightRequest}.

 *  @param workDoneProgress
 */
case class DocumentHighlightOptions(
  workDoneProgress: Option[Boolean] = None
)
object DocumentHighlightOptions extends codecs.structures_DocumentHighlightOptionsCodec

/**
 *  Parameters for a {@link DocumentHighlightRequest}.

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object DocumentHighlightParams extends codecs.structures_DocumentHighlightParamsCodec

/**
 *  Registration options for a {@link DocumentHighlightRequest}.

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 */
case class DocumentHighlightRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None
)
object DocumentHighlightRegistrationOptions extends codecs.structures_DocumentHighlightRegistrationOptionsCodec

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
 *    since 3.15.0

 *  @param data
 *    A data entry field that is preserved on a document link between a
 *    DocumentLinkRequest and a DocumentLinkResolveRequest.

 */
case class DocumentLink(
  range: structures.Range,
  target: Option[runtime.Uri] = None,
  tooltip: Option[String] = None,
  data: Option[io.circe.Json] = None
)
object DocumentLink extends codecs.structures_DocumentLinkCodec

/**
 *  The client capabilities of a {@link DocumentLinkRequest}.

 *  @param dynamicRegistration
 *    Whether document link supports dynamic registration.

 *  @param tooltipSupport
 *    Whether the client supports the `tooltip` property on `DocumentLink`.
 *    
 *    since 3.15.0

 */
case class DocumentLinkClientCapabilities(
  dynamicRegistration: Option[Boolean] = None,
  tooltipSupport: Option[Boolean] = None
)
object DocumentLinkClientCapabilities extends codecs.structures_DocumentLinkClientCapabilitiesCodec

/**
 *  Provider options for a {@link DocumentLinkRequest}.

 *  @param resolveProvider
 *    Document links have a resolve provider as well.

 *  @param workDoneProgress
 */
case class DocumentLinkOptions(
  resolveProvider: Option[Boolean] = None,
  workDoneProgress: Option[Boolean] = None
)
object DocumentLinkOptions extends codecs.structures_DocumentLinkOptionsCodec

/**
 *  The parameters of a {@link DocumentLinkRequest}.

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object DocumentLinkParams extends codecs.structures_DocumentLinkParamsCodec

/**
 *  Registration options for a {@link DocumentLinkRequest}.

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param resolveProvider
 *    Document links have a resolve provider as well.

 */
case class DocumentLinkRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None,
  resolveProvider: Option[Boolean] = None
)
object DocumentLinkRegistrationOptions extends codecs.structures_DocumentLinkRegistrationOptionsCodec

/**
 *  Client capabilities of a {@link DocumentOnTypeFormattingRequest}.

 *  @param dynamicRegistration
 *    Whether on type formatting supports dynamic registration.

 */
case class DocumentOnTypeFormattingClientCapabilities(
  dynamicRegistration: Option[Boolean] = None
)
object DocumentOnTypeFormattingClientCapabilities extends codecs.structures_DocumentOnTypeFormattingClientCapabilitiesCodec

/**
 *  Provider options for a {@link DocumentOnTypeFormattingRequest}.

 *  @param firstTriggerCharacter
 *    A character on which formatting should be triggered, like `{`.

 *  @param moreTriggerCharacter
 *    More trigger characters.

 */
case class DocumentOnTypeFormattingOptions(
  firstTriggerCharacter: String,
  moreTriggerCharacter: Option[Vector[String]] = None
)
object DocumentOnTypeFormattingOptions extends codecs.structures_DocumentOnTypeFormattingOptionsCodec

/**
 *  The parameters of a {@link DocumentOnTypeFormattingRequest}.

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
object DocumentOnTypeFormattingParams extends codecs.structures_DocumentOnTypeFormattingParamsCodec

/**
 *  Registration options for a {@link DocumentOnTypeFormattingRequest}.

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param firstTriggerCharacter
 *    A character on which formatting should be triggered, like `{`.

 *  @param moreTriggerCharacter
 *    More trigger characters.

 */
case class DocumentOnTypeFormattingRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None,
  firstTriggerCharacter: String,
  moreTriggerCharacter: Option[Vector[String]] = None
)
object DocumentOnTypeFormattingRegistrationOptions extends codecs.structures_DocumentOnTypeFormattingRegistrationOptionsCodec

/**
 *  Client capabilities of a {@link DocumentRangeFormattingRequest}.

 *  @param dynamicRegistration
 *    Whether range formatting supports dynamic registration.

 */
case class DocumentRangeFormattingClientCapabilities(
  dynamicRegistration: Option[Boolean] = None
)
object DocumentRangeFormattingClientCapabilities extends codecs.structures_DocumentRangeFormattingClientCapabilitiesCodec

/**
 *  Provider options for a {@link DocumentRangeFormattingRequest}.

 *  @param workDoneProgress
 */
case class DocumentRangeFormattingOptions(
  workDoneProgress: Option[Boolean] = None
)
object DocumentRangeFormattingOptions extends codecs.structures_DocumentRangeFormattingOptionsCodec

/**
 *  The parameters of a {@link DocumentRangeFormattingRequest}.

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
  workDoneToken: Option[aliases.ProgressToken] = None
)
object DocumentRangeFormattingParams extends codecs.structures_DocumentRangeFormattingParamsCodec

/**
 *  Registration options for a {@link DocumentRangeFormattingRequest}.

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 */
case class DocumentRangeFormattingRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None
)
object DocumentRangeFormattingRegistrationOptions extends codecs.structures_DocumentRangeFormattingRegistrationOptionsCodec

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
 *    since 3.16.0

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
  detail: Option[String] = None,
  kind: enumerations.SymbolKind,
  tags: Option[Vector[enumerations.SymbolTag]] = None,
  deprecated: Option[Boolean] = None,
  range: structures.Range,
  selectionRange: structures.Range,
  children: Option[Vector[structures.DocumentSymbol]] = None
)
object DocumentSymbol extends codecs.structures_DocumentSymbolCodec

/**
 *  Client Capabilities for a {@link DocumentSymbolRequest}.

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
 *    since 3.16.0

 *  @param labelSupport
 *    The client supports an additional label presented in the UI when
 *    registering a document symbol provider.
 *    
 *    since 3.16.0

 */
case class DocumentSymbolClientCapabilities(
  dynamicRegistration: Option[Boolean] = None,
  symbolKind: Option[DocumentSymbolClientCapabilities.SymbolKind] = None,
  hierarchicalDocumentSymbolSupport: Option[Boolean] = None,
  tagSupport: Option[DocumentSymbolClientCapabilities.TagSupport] = None,
  labelSupport: Option[Boolean] = None
)
object DocumentSymbolClientCapabilities extends codecs.structures_DocumentSymbolClientCapabilitiesCodec:
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
    valueSet: Option[Vector[enumerations.SymbolKind]] = None
  )
  object SymbolKind extends codecs.structures_DocumentSymbolClientCapabilities_SymbolKindCodec
  /**
   *  @param valueSet
   *    The tags supported by the client.
  
   */
  case class TagSupport(
    valueSet: Vector[enumerations.SymbolTag]
  )
  object TagSupport extends codecs.structures_DocumentSymbolClientCapabilities_TagSupportCodec

/**
 *  Provider options for a {@link DocumentSymbolRequest}.

 *  @param label
 *    A human-readable string that is shown when multiple outlines trees
 *    are shown for the same document.
 *    
 *    since 3.16.0

 *  @param workDoneProgress
 */
case class DocumentSymbolOptions(
  label: Option[String] = None,
  workDoneProgress: Option[Boolean] = None
)
object DocumentSymbolOptions extends codecs.structures_DocumentSymbolOptionsCodec

/**
 *  Parameters for a {@link DocumentSymbolRequest}.

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object DocumentSymbolParams extends codecs.structures_DocumentSymbolParamsCodec

/**
 *  Registration options for a {@link DocumentSymbolRequest}.

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param label
 *    A human-readable string that is shown when multiple outlines trees
 *    are shown for the same document.
 *    
 *    since 3.16.0

 */
case class DocumentSymbolRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None,
  label: Option[String] = None
)
object DocumentSymbolRegistrationOptions extends codecs.structures_DocumentSymbolRegistrationOptionsCodec

/**
 *  The client capabilities of a {@link ExecuteCommandRequest}.

 *  @param dynamicRegistration
 *    Execute command supports dynamic registration.

 */
case class ExecuteCommandClientCapabilities(
  dynamicRegistration: Option[Boolean] = None
)
object ExecuteCommandClientCapabilities extends codecs.structures_ExecuteCommandClientCapabilitiesCodec

/**
 *  The server capabilities of a {@link ExecuteCommandRequest}.

 *  @param commands
 *    The commands to be executed on the server

 *  @param workDoneProgress
 */
case class ExecuteCommandOptions(
  commands: Vector[String],
  workDoneProgress: Option[Boolean] = None
)
object ExecuteCommandOptions extends codecs.structures_ExecuteCommandOptionsCodec

/**
 *  The parameters of a {@link ExecuteCommandRequest}.

 *  @param command
 *    The identifier of the actual command handler.

 *  @param arguments
 *    Arguments that the command should be invoked with.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 */
case class ExecuteCommandParams(
  command: String,
  arguments: Option[Vector[io.circe.Json]] = None,
  workDoneToken: Option[aliases.ProgressToken] = None
)
object ExecuteCommandParams extends codecs.structures_ExecuteCommandParamsCodec

/**
 *  Registration options for a {@link ExecuteCommandRequest}.

 *  @param commands
 *    The commands to be executed on the server

 */
case class ExecuteCommandRegistrationOptions(
  commands: Vector[String]
)
object ExecuteCommandRegistrationOptions extends codecs.structures_ExecuteCommandRegistrationOptionsCodec

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
  executionOrder: runtime.uinteger,
  success: Option[Boolean] = None
)
object ExecutionSummary extends codecs.structures_ExecutionSummaryCodec

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
object FileCreate extends codecs.structures_FileCreateCodec

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
object FileDelete extends codecs.structures_FileDeleteCodec

/**
 *  An event describing a file change.

 *  @param uri
 *    The file's uri.

 *  @param type
 *    The change type.

 */
case class FileEvent(
  uri: runtime.DocumentUri,
  `type`: enumerations.FileChangeType
)
object FileEvent extends codecs.structures_FileEventCodec

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
  dynamicRegistration: Option[Boolean] = None,
  didCreate: Option[Boolean] = None,
  willCreate: Option[Boolean] = None,
  didRename: Option[Boolean] = None,
  willRename: Option[Boolean] = None,
  didDelete: Option[Boolean] = None,
  willDelete: Option[Boolean] = None
)
object FileOperationClientCapabilities extends codecs.structures_FileOperationClientCapabilitiesCodec

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
  scheme: Option[String] = None,
  pattern: structures.FileOperationPattern
)
object FileOperationFilter extends codecs.structures_FileOperationFilterCodec

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
  didCreate: Option[structures.FileOperationRegistrationOptions] = None,
  willCreate: Option[structures.FileOperationRegistrationOptions] = None,
  didRename: Option[structures.FileOperationRegistrationOptions] = None,
  willRename: Option[structures.FileOperationRegistrationOptions] = None,
  didDelete: Option[structures.FileOperationRegistrationOptions] = None,
  willDelete: Option[structures.FileOperationRegistrationOptions] = None
)
object FileOperationOptions extends codecs.structures_FileOperationOptionsCodec

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
  matches: Option[enumerations.FileOperationPatternKind] = None,
  options: Option[structures.FileOperationPatternOptions] = None
)
object FileOperationPattern extends codecs.structures_FileOperationPatternCodec

/**
 *  Matching options for the file operation pattern.
 *  
 *  @since 3.16.0

 *  @param ignoreCase
 *    The pattern should be matched ignoring casing.

 */
case class FileOperationPatternOptions(
  ignoreCase: Option[Boolean] = None
)
object FileOperationPatternOptions extends codecs.structures_FileOperationPatternOptionsCodec

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
object FileOperationRegistrationOptions extends codecs.structures_FileOperationRegistrationOptionsCodec

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
object FileRename extends codecs.structures_FileRenameCodec

/**
 *  @param globPattern
 *    The glob pattern to watch. See {@link GlobPattern glob pattern} for more detail.
 *    
 *    since 3.17.0 support for relative patterns.

 *  @param kind
 *    The kind of events of interest. If omitted it defaults
 *    to WatchKind.Create | WatchKind.Change | WatchKind.Delete
 *    which is 7.

 */
case class FileSystemWatcher(
  globPattern: aliases.GlobPattern,
  kind: Option[enumerations.WatchKind] = None
)
object FileSystemWatcher extends codecs.structures_FileSystemWatcherCodec

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
 *    See {@link FoldingRangeKind} for an enumeration of standardized kinds.

 *  @param collapsedText
 *    The text that the client should show when the specified range is
 *    collapsed. If not defined or not supported by the client, a default
 *    will be chosen by the client.
 *    
 *    since 3.17.0

 */
case class FoldingRange(
  startLine: runtime.uinteger,
  startCharacter: Option[runtime.uinteger] = None,
  endLine: runtime.uinteger,
  endCharacter: Option[runtime.uinteger] = None,
  kind: Option[enumerations.FoldingRangeKind] = None,
  collapsedText: Option[String] = None
)
object FoldingRange extends codecs.structures_FoldingRangeCodec

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
 *    since 3.17.0

 *  @param foldingRange
 *    Specific options for the folding range.
 *    
 *    since 3.17.0

 */
case class FoldingRangeClientCapabilities(
  dynamicRegistration: Option[Boolean] = None,
  rangeLimit: Option[runtime.uinteger] = None,
  lineFoldingOnly: Option[Boolean] = None,
  foldingRangeKind: Option[FoldingRangeClientCapabilities.FoldingRangeKind] = None,
  foldingRange: Option[FoldingRangeClientCapabilities.FoldingRange] = None
)
object FoldingRangeClientCapabilities extends codecs.structures_FoldingRangeClientCapabilitiesCodec:
  /**
   *  @param valueSet
   *    The folding range kind values the client supports. When this
   *    property exists the client also guarantees that it will
   *    handle values outside its set gracefully and falls back
   *    to a default value when unknown.
  
   */
  case class FoldingRangeKind(
    valueSet: Option[Vector[enumerations.FoldingRangeKind]] = None
  )
  object FoldingRangeKind extends codecs.structures_FoldingRangeClientCapabilities_FoldingRangeKindCodec
  /**
   *  @param collapsedText
   *    If set, the client signals that it supports setting collapsedText on
   *    folding ranges to display custom labels instead of the default text.
   *    
   *    since 3.17.0
  
   */
  case class FoldingRange(
    collapsedText: Option[Boolean] = None
  )
  object FoldingRange extends codecs.structures_FoldingRangeClientCapabilities_FoldingRangeCodec

case class FoldingRangeOptions(
  workDoneProgress: Option[Boolean] = None
)
object FoldingRangeOptions extends codecs.structures_FoldingRangeOptionsCodec

/**
 *  Parameters for a {@link FoldingRangeRequest}.

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object FoldingRangeParams extends codecs.structures_FoldingRangeParamsCodec

/**
 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class FoldingRangeRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None,
  id: Option[String] = None
)
object FoldingRangeRegistrationOptions extends codecs.structures_FoldingRangeRegistrationOptionsCodec

/**
 *  Value-object describing what options formatting should use.

 *  @param tabSize
 *    Size of a tab in spaces.

 *  @param insertSpaces
 *    Prefer spaces over tabs.

 *  @param trimTrailingWhitespace
 *    Trim trailing whitespace on a line.
 *    
 *    since 3.15.0

 *  @param insertFinalNewline
 *    Insert a newline character at the end of the file if one does not exist.
 *    
 *    since 3.15.0

 *  @param trimFinalNewlines
 *    Trim all newlines after the final newline at the end of the file.
 *    
 *    since 3.15.0

 */
case class FormattingOptions(
  tabSize: runtime.uinteger,
  insertSpaces: Boolean,
  trimTrailingWhitespace: Option[Boolean] = None,
  insertFinalNewline: Option[Boolean] = None,
  trimFinalNewlines: Option[Boolean] = None
)
object FormattingOptions extends codecs.structures_FormattingOptionsCodec

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
  resultId: Option[String] = None,
  items: Vector[structures.Diagnostic]
)
object FullDocumentDiagnosticReport extends codecs.structures_FullDocumentDiagnosticReportCodec

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
 *    since 3.17.0

 *  @param regularExpressions
 *    Client capabilities specific to regular expressions.
 *    
 *    since 3.16.0

 *  @param markdown
 *    Client capabilities specific to the client's markdown parser.
 *    
 *    since 3.16.0

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
 *    since 3.17.0

 */
case class GeneralClientCapabilities(
  staleRequestSupport: Option[GeneralClientCapabilities.StaleRequestSupport] = None,
  regularExpressions: Option[structures.RegularExpressionsClientCapabilities] = None,
  markdown: Option[structures.MarkdownClientCapabilities] = None,
  positionEncodings: Option[Vector[enumerations.PositionEncodingKind]] = None
)
object GeneralClientCapabilities extends codecs.structures_GeneralClientCapabilitiesCodec:
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
  object StaleRequestSupport extends codecs.structures_GeneralClientCapabilities_StaleRequestSupportCodec

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
  range: Option[structures.Range] = None
)
object Hover extends codecs.structures_HoverCodec

/**
 *  @param dynamicRegistration
 *    Whether hover supports dynamic registration.

 *  @param contentFormat
 *    Client supports the following content formats for the content
 *    property. The order describes the preferred format of the client.

 */
case class HoverClientCapabilities(
  dynamicRegistration: Option[Boolean] = None,
  contentFormat: Option[Vector[enumerations.MarkupKind]] = None
)
object HoverClientCapabilities extends codecs.structures_HoverClientCapabilitiesCodec

/**
 *  Hover options.

 *  @param workDoneProgress
 */
case class HoverOptions(
  workDoneProgress: Option[Boolean] = None
)
object HoverOptions extends codecs.structures_HoverOptionsCodec

/**
 *  Parameters for a {@link HoverRequest}.

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
  workDoneToken: Option[aliases.ProgressToken] = None
)
object HoverParams extends codecs.structures_HoverParamsCodec

/**
 *  Registration options for a {@link HoverRequest}.

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 */
case class HoverRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None
)
object HoverRegistrationOptions extends codecs.structures_HoverRegistrationOptionsCodec

/**
 *  @since 3.6.0

 *  @param dynamicRegistration
 *    Whether implementation supports dynamic registration. If this is set to `true`
 *    the client supports the new `ImplementationRegistrationOptions` return value
 *    for the corresponding server capability as well.

 *  @param linkSupport
 *    The client supports additional metadata in the form of definition links.
 *    
 *    since 3.14.0

 */
case class ImplementationClientCapabilities(
  dynamicRegistration: Option[Boolean] = None,
  linkSupport: Option[Boolean] = None
)
object ImplementationClientCapabilities extends codecs.structures_ImplementationClientCapabilitiesCodec

case class ImplementationOptions(
  workDoneProgress: Option[Boolean] = None
)
object ImplementationOptions extends codecs.structures_ImplementationOptionsCodec

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object ImplementationParams extends codecs.structures_ImplementationParamsCodec

/**
 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class ImplementationRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None,
  id: Option[String] = None
)
object ImplementationRegistrationOptions extends codecs.structures_ImplementationRegistrationOptionsCodec

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
object InitializeError extends codecs.structures_InitializeErrorCodec

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
 *    since 3.15.0

 *  @param locale
 *    The locale the client is currently showing the user interface
 *    in. This must not necessarily be the locale of the operating
 *    system.
 *    
 *    Uses IETF language tags as the value's syntax
 *    (See https://en.wikipedia.org/wiki/IETF_language_tag)
 *    
 *    since 3.16.0

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
 *    since 3.6.0

 */
case class InitializeParams(
  processId: Option[Int] = None,
  clientInfo: Option[InitializeParams.ClientInfo] = None,
  locale: Option[String] = None,
  rootPath: Option[String] = None,
  rootUri: Option[runtime.DocumentUri] = None,
  capabilities: structures.ClientCapabilities,
  initializationOptions: Option[io.circe.Json] = None,
  trace: Option[enumerations.TraceValues] = None,
  workspaceFolders: Option[Vector[structures.WorkspaceFolder]] = None
)
object InitializeParams extends codecs.structures_InitializeParamsCodec:
  /**
   *  @param name
   *    The name of the client as defined by the client.
  
   *  @param version
   *    The client's version as defined by the client.
  
   */
  case class ClientInfo(
    name: String,
    version: Option[String] = None
  )
  object ClientInfo extends codecs.structures_InitializeParams_ClientInfoCodec

/**
 *  The result returned from an initialize request.

 *  @param capabilities
 *    The capabilities the language server provides.

 *  @param serverInfo
 *    Information about the server.
 *    
 *    since 3.15.0

 */
case class InitializeResult(
  capabilities: structures.ServerCapabilities,
  serverInfo: Option[InitializeResult.ServerInfo] = None
)
object InitializeResult extends codecs.structures_InitializeResultCodec:
  /**
   *  @param name
   *    The name of the server as defined by the server.
  
   *  @param version
   *    The server's version as defined by the server.
  
   */
  case class ServerInfo(
    name: String,
    version: Option[String] = None
  )
  object ServerInfo extends codecs.structures_InitializeResult_ServerInfoCodec

case class InitializedParams(
)
object InitializedParams extends codecs.structures_InitializedParamsCodec

/**
 *  Inlay hint information.
 *  
 *  @since 3.17.0

 *  @param position
 *    The position of this hint.
 *    
 *    If multiple hints have the same position, they will be shown in the order
 *    they appear in the response.

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
  kind: Option[enumerations.InlayHintKind] = None,
  textEdits: Option[Vector[structures.TextEdit]] = None,
  tooltip: Option[(String | structures.MarkupContent)] = None,
  paddingLeft: Option[Boolean] = None,
  paddingRight: Option[Boolean] = None,
  data: Option[io.circe.Json] = None
)
object InlayHint extends codecs.structures_InlayHintCodec

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
  dynamicRegistration: Option[Boolean] = None,
  resolveSupport: Option[InlayHintClientCapabilities.ResolveSupport] = None
)
object InlayHintClientCapabilities extends codecs.structures_InlayHintClientCapabilitiesCodec:
  /**
   *  @param properties
   *    The properties that a client can resolve lazily.
  
   */
  case class ResolveSupport(
    properties: Vector[String]
  )
  object ResolveSupport extends codecs.structures_InlayHintClientCapabilities_ResolveSupportCodec

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
  tooltip: Option[(String | structures.MarkupContent)] = None,
  location: Option[structures.Location] = None,
  command: Option[structures.Command] = None
)
object InlayHintLabelPart extends codecs.structures_InlayHintLabelPartCodec

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
  resolveProvider: Option[Boolean] = None,
  workDoneProgress: Option[Boolean] = None
)
object InlayHintOptions extends codecs.structures_InlayHintOptionsCodec

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
  workDoneToken: Option[aliases.ProgressToken] = None
)
object InlayHintParams extends codecs.structures_InlayHintParamsCodec

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
  resolveProvider: Option[Boolean] = None,
  documentSelector: Option[aliases.DocumentSelector] = None,
  id: Option[String] = None
)
object InlayHintRegistrationOptions extends codecs.structures_InlayHintRegistrationOptionsCodec

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
  refreshSupport: Option[Boolean] = None
)
object InlayHintWorkspaceClientCapabilities extends codecs.structures_InlayHintWorkspaceClientCapabilitiesCodec

/**
 *  Client capabilities specific to inline values.
 *  
 *  @since 3.17.0

 *  @param dynamicRegistration
 *    Whether implementation supports dynamic registration for inline value providers.

 */
case class InlineValueClientCapabilities(
  dynamicRegistration: Option[Boolean] = None
)
object InlineValueClientCapabilities extends codecs.structures_InlineValueClientCapabilitiesCodec

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
object InlineValueContext extends codecs.structures_InlineValueContextCodec

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
  expression: Option[String] = None
)
object InlineValueEvaluatableExpression extends codecs.structures_InlineValueEvaluatableExpressionCodec

/**
 *  Inline value options used during static registration.
 *  
 *  @since 3.17.0

 *  @param workDoneProgress
 */
case class InlineValueOptions(
  workDoneProgress: Option[Boolean] = None
)
object InlineValueOptions extends codecs.structures_InlineValueOptionsCodec

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
  workDoneToken: Option[aliases.ProgressToken] = None
)
object InlineValueParams extends codecs.structures_InlineValueParamsCodec

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
  documentSelector: Option[aliases.DocumentSelector] = None,
  id: Option[String] = None
)
object InlineValueRegistrationOptions extends codecs.structures_InlineValueRegistrationOptionsCodec

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
object InlineValueText extends codecs.structures_InlineValueTextCodec

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
  variableName: Option[String] = None,
  caseSensitiveLookup: Boolean
)
object InlineValueVariableLookup extends codecs.structures_InlineValueVariableLookupCodec

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
  refreshSupport: Option[Boolean] = None
)
object InlineValueWorkspaceClientCapabilities extends codecs.structures_InlineValueWorkspaceClientCapabilitiesCodec

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
object InsertReplaceEdit extends codecs.structures_InsertReplaceEditCodec

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
  dynamicRegistration: Option[Boolean] = None
)
object LinkedEditingRangeClientCapabilities extends codecs.structures_LinkedEditingRangeClientCapabilitiesCodec

case class LinkedEditingRangeOptions(
  workDoneProgress: Option[Boolean] = None
)
object LinkedEditingRangeOptions extends codecs.structures_LinkedEditingRangeOptionsCodec

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
  workDoneToken: Option[aliases.ProgressToken] = None
)
object LinkedEditingRangeParams extends codecs.structures_LinkedEditingRangeParamsCodec

/**
 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class LinkedEditingRangeRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None,
  id: Option[String] = None
)
object LinkedEditingRangeRegistrationOptions extends codecs.structures_LinkedEditingRangeRegistrationOptionsCodec

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
  wordPattern: Option[String] = None
)
object LinkedEditingRanges extends codecs.structures_LinkedEditingRangesCodec

/**
 *  Represents a location inside a resource, such as a line
 *  inside a text file.

 *  @param uri
 *  @param range
 */
case class Location(
  uri: runtime.DocumentUri,
  range: structures.Range
)
object Location extends codecs.structures_LocationCodec

/**
 *  Represents the connection of two locations. Provides additional metadata over normal {@link Location locations},
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
  originSelectionRange: Option[structures.Range] = None,
  targetUri: runtime.DocumentUri,
  targetRange: structures.Range,
  targetSelectionRange: structures.Range
)
object LocationLink extends codecs.structures_LocationLinkCodec

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
object LogMessageParams extends codecs.structures_LogMessageParamsCodec

case class LogTraceParams(
  message: String,
  verbose: Option[String] = None
)
object LogTraceParams extends codecs.structures_LogTraceParamsCodec

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
 *    since 3.17.0

 */
case class MarkdownClientCapabilities(
  parser: String,
  version: Option[String] = None,
  allowedTags: Option[Vector[String]] = None
)
object MarkdownClientCapabilities extends codecs.structures_MarkdownClientCapabilitiesCodec

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
object MarkupContent extends codecs.structures_MarkupContentCodec

/**
 *  @param title
 *    A short title like 'Retry', 'Open Log' etc.

 */
case class MessageActionItem(
  title: String
)
object MessageActionItem extends codecs.structures_MessageActionItemCodec

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
  kind: Option[enumerations.MonikerKind] = None
)
object Moniker extends codecs.structures_MonikerCodec

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
  dynamicRegistration: Option[Boolean] = None
)
object MonikerClientCapabilities extends codecs.structures_MonikerClientCapabilitiesCodec

case class MonikerOptions(
  workDoneProgress: Option[Boolean] = None
)
object MonikerOptions extends codecs.structures_MonikerOptionsCodec

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object MonikerParams extends codecs.structures_MonikerParamsCodec

/**
 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 */
case class MonikerRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None
)
object MonikerRegistrationOptions extends codecs.structures_MonikerRegistrationOptionsCodec

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
  document: runtime.DocumentUri,
  metadata: Option[aliases.LSPObject] = None,
  executionSummary: Option[structures.ExecutionSummary] = None
)
object NotebookCell extends codecs.structures_NotebookCellCodec

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
  start: runtime.uinteger,
  deleteCount: runtime.uinteger,
  cells: Option[Vector[structures.NotebookCell]] = None
)
object NotebookCellArrayChange extends codecs.structures_NotebookCellArrayChangeCodec

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
  language: Option[String] = None
)
object NotebookCellTextDocumentFilter extends codecs.structures_NotebookCellTextDocumentFilterCodec

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
  uri: runtime.Uri,
  notebookType: String,
  version: Int,
  metadata: Option[aliases.LSPObject] = None,
  cells: Vector[structures.NotebookCell]
)
object NotebookDocument extends codecs.structures_NotebookDocumentCodec

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
  metadata: Option[aliases.LSPObject] = None,
  cells: Option[NotebookDocumentChangeEvent.Cells] = None
)
object NotebookDocumentChangeEvent extends codecs.structures_NotebookDocumentChangeEventCodec:
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
    structure: Option[Cells.Structure] = None,
    data: Option[Vector[structures.NotebookCell]] = None,
    textContent: Option[Vector[Cells.S0]] = None
  )
  object Cells extends codecs.structures_NotebookDocumentChangeEvent_CellsCodec:
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
      didOpen: Option[Vector[structures.TextDocumentItem]] = None,
      didClose: Option[Vector[structures.TextDocumentIdentifier]] = None
    )
    object Structure extends codecs.structures_NotebookDocumentChangeEvent_Cells_StructureCodec
    case class S0(
      document: structures.VersionedTextDocumentIdentifier,
      changes: Vector[aliases.TextDocumentContentChangeEvent]
    )
    object S0 extends codecs.structures_NotebookDocumentChangeEvent_Cells_S0Codec

/**
 *  Capabilities specific to the notebook document support.
 *  
 *  @since 3.17.0

 *  @param synchronization
 *    Capabilities specific to notebook document synchronization
 *    
 *    since 3.17.0

 */
case class NotebookDocumentClientCapabilities(
  synchronization: structures.NotebookDocumentSyncClientCapabilities
)
object NotebookDocumentClientCapabilities extends codecs.structures_NotebookDocumentClientCapabilitiesCodec

/**
 *  A literal to identify a notebook document in the client.
 *  
 *  @since 3.17.0

 *  @param uri
 *    The notebook document's uri.

 */
case class NotebookDocumentIdentifier(
  uri: runtime.Uri
)
object NotebookDocumentIdentifier extends codecs.structures_NotebookDocumentIdentifierCodec

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
  dynamicRegistration: Option[Boolean] = None,
  executionSummarySupport: Option[Boolean] = None
)
object NotebookDocumentSyncClientCapabilities extends codecs.structures_NotebookDocumentSyncClientCapabilitiesCodec

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
  save: Option[Boolean] = None
)
object NotebookDocumentSyncOptions extends codecs.structures_NotebookDocumentSyncOptionsCodec:
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
    cells: Option[Vector[S0.S0]] = None
  )
  object S0 extends codecs.structures_NotebookDocumentSyncOptions_S0Codec:
    case class S0(
      language: String
    )
    object S0 extends codecs.structures_NotebookDocumentSyncOptions_S0_S0Codec
  /**
   *  @param notebook
   *    The notebook to be synced If a string
   *    value is provided it matches against the
   *    notebook type. '*' matches every notebook.
  
   *  @param cells
   *    The cells of the matching notebook to be synced.
  
   */
  case class S1(
    notebook: Option[(String | aliases.NotebookDocumentFilter)] = None,
    cells: Vector[S1.S0]
  )
  object S1 extends codecs.structures_NotebookDocumentSyncOptions_S1Codec:
    case class S0(
      language: String
    )
    object S0 extends codecs.structures_NotebookDocumentSyncOptions_S1_S0Codec

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
  save: Option[Boolean] = None,
  id: Option[String] = None
)
object NotebookDocumentSyncRegistrationOptions extends codecs.structures_NotebookDocumentSyncRegistrationOptionsCodec:
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
    cells: Option[Vector[S0.S0]] = None
  )
  object S0 extends codecs.structures_NotebookDocumentSyncRegistrationOptions_S0Codec:
    case class S0(
      language: String
    )
    object S0 extends codecs.structures_NotebookDocumentSyncRegistrationOptions_S0_S0Codec
  /**
   *  @param notebook
   *    The notebook to be synced If a string
   *    value is provided it matches against the
   *    notebook type. '*' matches every notebook.
  
   *  @param cells
   *    The cells of the matching notebook to be synced.
  
   */
  case class S1(
    notebook: Option[(String | aliases.NotebookDocumentFilter)] = None,
    cells: Vector[S1.S0]
  )
  object S1 extends codecs.structures_NotebookDocumentSyncRegistrationOptions_S1Codec:
    case class S0(
      language: String
    )
    object S0 extends codecs.structures_NotebookDocumentSyncRegistrationOptions_S1_S0Codec

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
  version: Option[Int] = None,
  uri: runtime.DocumentUri
)
object OptionalVersionedTextDocumentIdentifier extends codecs.structures_OptionalVersionedTextDocumentIdentifierCodec

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
  label: (String | (runtime.uinteger, runtime.uinteger)),
  documentation: Option[(String | structures.MarkupContent)] = None
)
object ParameterInformation extends codecs.structures_ParameterInformationCodec

/**
 *  @param partialResultToken
 *    An optional token that a server can use to report partial results (e.g. streaming) to
 *    the client.

 */
case class PartialResultParams(
  partialResultToken: Option[aliases.ProgressToken] = None
)
object PartialResultParams extends codecs.structures_PartialResultParamsCodec

/**
 *  Position in a text document expressed as zero-based line and character
 *  offset. Prior to 3.17 the offsets were always based on a UTF-16 string
 *  representation. So a string of the form `a𐐀b` the character offset of the
 *  character `a` is 0, the character offset of `𐐀` is 1 and the character
 *  offset of b is 3 since `𐐀` is represented using two code units in UTF-16.
 *  Since 3.17 clients and servers can agree on a different string encoding
 *  representation (e.g. UTF-8). The client announces it's supported encoding
 *  via the client capability [`general.positionEncodings`](https://microsoft.github.io/language-server-protocol/specifications/specification-current/#clientCapabilities).
 *  The value is an array of position encodings the client supports, with
 *  decreasing preference (e.g. the encoding at index `0` is the most preferred
 *  one). To stay backwards compatible the only mandatory encoding is UTF-16
 *  represented via the string `utf-16`. The server can pick one of the
 *  encodings offered by the client and signals that encoding back to the
 *  client via the initialize result's property
 *  [`capabilities.positionEncoding`](https://microsoft.github.io/language-server-protocol/specifications/specification-current/#serverCapabilities). If the string value
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
  line: runtime.uinteger,
  character: runtime.uinteger
)
object Position extends codecs.structures_PositionCodec with extensions.PositionSyntax

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
  workDoneToken: Option[aliases.ProgressToken] = None
)
object PrepareRenameParams extends codecs.structures_PrepareRenameParamsCodec

/**
 *  A previous result id in a workspace pull request.
 *  
 *  @since 3.17.0

 *  @param uri
 *    The URI for which the client knowns a
 *    result id.

 *  @param value
 *    The value of the previous result id.

 */
case class PreviousResultId(
  uri: runtime.DocumentUri,
  value: String
)
object PreviousResultId extends codecs.structures_PreviousResultIdCodec

/**
 *  @param token
 *    The progress token provided by the client or server.

 *  @param value
 *    The progress data.

 */
case class ProgressParams(
  token: aliases.ProgressToken,
  value: io.circe.Json
)
object ProgressParams extends codecs.structures_ProgressParamsCodec

/**
 *  The publish diagnostic client capabilities.

 *  @param relatedInformation
 *    Whether the clients accepts diagnostics with related information.

 *  @param tagSupport
 *    Client supports the tag property to provide meta data about a diagnostic.
 *    Clients supporting tags have to handle unknown tags gracefully.
 *    
 *    since 3.15.0

 *  @param versionSupport
 *    Whether the client interprets the version property of the
 *    `textDocument/publishDiagnostics` notification's parameter.
 *    
 *    since 3.15.0

 *  @param codeDescriptionSupport
 *    Client supports a codeDescription property
 *    
 *    since 3.16.0

 *  @param dataSupport
 *    Whether code action supports the `data` property which is
 *    preserved between a `textDocument/publishDiagnostics` and
 *    `textDocument/codeAction` request.
 *    
 *    since 3.16.0

 */
case class PublishDiagnosticsClientCapabilities(
  relatedInformation: Option[Boolean] = None,
  tagSupport: Option[PublishDiagnosticsClientCapabilities.TagSupport] = None,
  versionSupport: Option[Boolean] = None,
  codeDescriptionSupport: Option[Boolean] = None,
  dataSupport: Option[Boolean] = None
)
object PublishDiagnosticsClientCapabilities extends codecs.structures_PublishDiagnosticsClientCapabilitiesCodec:
  /**
   *  @param valueSet
   *    The tags supported by the client.
  
   */
  case class TagSupport(
    valueSet: Vector[enumerations.DiagnosticTag]
  )
  object TagSupport extends codecs.structures_PublishDiagnosticsClientCapabilities_TagSupportCodec

/**
 *  The publish diagnostic notification's parameters.

 *  @param uri
 *    The URI for which diagnostic information is reported.

 *  @param version
 *    Optional the version number of the document the diagnostics are published for.
 *    
 *    since 3.15.0

 *  @param diagnostics
 *    An array of diagnostic information items.

 */
case class PublishDiagnosticsParams(
  uri: runtime.DocumentUri,
  version: Option[Int] = None,
  diagnostics: Vector[structures.Diagnostic]
)
object PublishDiagnosticsParams extends codecs.structures_PublishDiagnosticsParamsCodec

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
  `end`: structures.Position
)
object Range extends codecs.structures_RangeCodec

/**
 *  Client Capabilities for a {@link ReferencesRequest}.

 *  @param dynamicRegistration
 *    Whether references supports dynamic registration.

 */
case class ReferenceClientCapabilities(
  dynamicRegistration: Option[Boolean] = None
)
object ReferenceClientCapabilities extends codecs.structures_ReferenceClientCapabilitiesCodec

/**
 *  Value-object that contains additional information when
 *  requesting references.

 *  @param includeDeclaration
 *    Include the declaration of the current symbol.

 */
case class ReferenceContext(
  includeDeclaration: Boolean
)
object ReferenceContext extends codecs.structures_ReferenceContextCodec

/**
 *  Reference options.

 *  @param workDoneProgress
 */
case class ReferenceOptions(
  workDoneProgress: Option[Boolean] = None
)
object ReferenceOptions extends codecs.structures_ReferenceOptionsCodec

/**
 *  Parameters for a {@link ReferencesRequest}.

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object ReferenceParams extends codecs.structures_ReferenceParamsCodec

/**
 *  Registration options for a {@link ReferencesRequest}.

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 */
case class ReferenceRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None
)
object ReferenceRegistrationOptions extends codecs.structures_ReferenceRegistrationOptionsCodec

/**
 *  General parameters to register for a notification or to register a provider.

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
  registerOptions: Option[io.circe.Json] = None
)
object Registration extends codecs.structures_RegistrationCodec

case class RegistrationParams(
  registrations: Vector[structures.Registration]
)
object RegistrationParams extends codecs.structures_RegistrationParamsCodec

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
  version: Option[String] = None
)
object RegularExpressionsClientCapabilities extends codecs.structures_RegularExpressionsClientCapabilitiesCodec

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
 *    since 3.17.0

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
  relatedDocuments: Option[Map[runtime.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)]] = None,
  kind: "full",
  resultId: Option[String] = None,
  items: Vector[structures.Diagnostic]
)
object RelatedFullDocumentDiagnosticReport extends codecs.structures_RelatedFullDocumentDiagnosticReportCodec

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
 *    since 3.17.0

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
  relatedDocuments: Option[Map[runtime.DocumentUri, (structures.FullDocumentDiagnosticReport | structures.UnchangedDocumentDiagnosticReport)]] = None,
  kind: "unchanged",
  resultId: String
)
object RelatedUnchangedDocumentDiagnosticReport extends codecs.structures_RelatedUnchangedDocumentDiagnosticReportCodec

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
  baseUri: (structures.WorkspaceFolder | runtime.Uri),
  pattern: aliases.Pattern
)
object RelativePattern extends codecs.structures_RelativePatternCodec

/**
 *  @param dynamicRegistration
 *    Whether rename supports dynamic registration.

 *  @param prepareSupport
 *    Client supports testing for validity of rename operations
 *    before execution.
 *    
 *    since 3.12.0

 *  @param prepareSupportDefaultBehavior
 *    Client supports the default behavior result.
 *    
 *    The value indicates the default behavior used by the
 *    client.
 *    
 *    since 3.16.0

 *  @param honorsChangeAnnotations
 *    Whether the client honors the change annotations in
 *    text edits and resource operations returned via the
 *    rename request's workspace edit by for example presenting
 *    the workspace edit in the user interface and asking
 *    for confirmation.
 *    
 *    since 3.16.0

 */
case class RenameClientCapabilities(
  dynamicRegistration: Option[Boolean] = None,
  prepareSupport: Option[Boolean] = None,
  prepareSupportDefaultBehavior: Option[enumerations.PrepareSupportDefaultBehavior] = None,
  honorsChangeAnnotations: Option[Boolean] = None
)
object RenameClientCapabilities extends codecs.structures_RenameClientCapabilitiesCodec

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
 *    since 3.16.0

 */
case class RenameFile(
  kind: "rename",
  oldUri: runtime.DocumentUri,
  newUri: runtime.DocumentUri,
  options: Option[structures.RenameFileOptions] = None,
  annotationId: Option[aliases.ChangeAnnotationIdentifier] = None
)
object RenameFile extends codecs.structures_RenameFileCodec

/**
 *  Rename file options

 *  @param overwrite
 *    Overwrite target if existing. Overwrite wins over `ignoreIfExists`

 *  @param ignoreIfExists
 *    Ignores if target exists.

 */
case class RenameFileOptions(
  overwrite: Option[Boolean] = None,
  ignoreIfExists: Option[Boolean] = None
)
object RenameFileOptions extends codecs.structures_RenameFileOptionsCodec

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
object RenameFilesParams extends codecs.structures_RenameFilesParamsCodec

/**
 *  Provider options for a {@link RenameRequest}.

 *  @param prepareProvider
 *    Renames should be checked and tested before being executed.
 *    
 *    since version 3.12.0

 *  @param workDoneProgress
 */
case class RenameOptions(
  prepareProvider: Option[Boolean] = None,
  workDoneProgress: Option[Boolean] = None
)
object RenameOptions extends codecs.structures_RenameOptionsCodec

/**
 *  The parameters of a {@link RenameRequest}.

 *  @param textDocument
 *    The document to rename.

 *  @param position
 *    The position at which this request was sent.

 *  @param newName
 *    The new name of the symbol. If the given name is not valid the
 *    request must return a {@link ResponseError} with an
 *    appropriate message set.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 */
case class RenameParams(
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  newName: String,
  workDoneToken: Option[aliases.ProgressToken] = None
)
object RenameParams extends codecs.structures_RenameParamsCodec

/**
 *  Registration options for a {@link RenameRequest}.

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param prepareProvider
 *    Renames should be checked and tested before being executed.
 *    
 *    since version 3.12.0

 */
case class RenameRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None,
  prepareProvider: Option[Boolean] = None
)
object RenameRegistrationOptions extends codecs.structures_RenameRegistrationOptionsCodec

/**
 *  A generic resource operation.

 *  @param kind
 *    The resource operation kind.

 *  @param annotationId
 *    An optional annotation identifier describing the operation.
 *    
 *    since 3.16.0

 */
case class ResourceOperation(
  kind: String,
  annotationId: Option[aliases.ChangeAnnotationIdentifier] = None
)
object ResourceOperation extends codecs.structures_ResourceOperationCodec

/**
 *  Save options.

 *  @param includeText
 *    The client is supposed to include the content on save.

 */
case class SaveOptions(
  includeText: Option[Boolean] = None
)
object SaveOptions extends codecs.structures_SaveOptionsCodec

/**
 *  A selection range represents a part of a selection hierarchy. A selection range
 *  may have a parent selection range that contains it.

 *  @param range
 *    The {@link Range range} of this selection range.

 *  @param parent
 *    The parent selection range containing this range. Therefore `parent.range` must contain `this.range`.

 */
case class SelectionRange(
  range: structures.Range,
  parent: Option[structures.SelectionRange] = None
)
object SelectionRange extends codecs.structures_SelectionRangeCodec

/**
 *  @param dynamicRegistration
 *    Whether implementation supports dynamic registration for selection range providers. If this is set to `true`
 *    the client supports the new `SelectionRangeRegistrationOptions` return value for the corresponding server
 *    capability as well.

 */
case class SelectionRangeClientCapabilities(
  dynamicRegistration: Option[Boolean] = None
)
object SelectionRangeClientCapabilities extends codecs.structures_SelectionRangeClientCapabilitiesCodec

case class SelectionRangeOptions(
  workDoneProgress: Option[Boolean] = None
)
object SelectionRangeOptions extends codecs.structures_SelectionRangeOptionsCodec

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object SelectionRangeParams extends codecs.structures_SelectionRangeParamsCodec

/**
 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class SelectionRangeRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None,
  id: Option[String] = None
)
object SelectionRangeRegistrationOptions extends codecs.structures_SelectionRangeRegistrationOptionsCodec

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
  resultId: Option[String] = None,
  data: Vector[runtime.uinteger]
)
object SemanticTokens extends codecs.structures_SemanticTokensCodec

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
 *    since 3.17.0

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
 *    since 3.17.0

 */
case class SemanticTokensClientCapabilities(
  dynamicRegistration: Option[Boolean] = None,
  requests: SemanticTokensClientCapabilities.Requests,
  tokenTypes: Vector[String],
  tokenModifiers: Vector[String],
  formats: Vector[enumerations.TokenFormat],
  overlappingTokenSupport: Option[Boolean] = None,
  multilineTokenSupport: Option[Boolean] = None,
  serverCancelSupport: Option[Boolean] = None,
  augmentsSyntaxTokens: Option[Boolean] = None
)
object SemanticTokensClientCapabilities extends codecs.structures_SemanticTokensClientCapabilitiesCodec:
  /**
   *  @param range
   *    The client will send the `textDocument/semanticTokens/range` request if
   *    the server provides a corresponding handler.
  
   *  @param full
   *    The client will send the `textDocument/semanticTokens/full` request if
   *    the server provides a corresponding handler.
  
   */
  case class Requests(
    range: Option[(Boolean | Requests.S0)] = None,
    full: Option[(Boolean | Requests.S1)] = None
  )
  object Requests extends codecs.structures_SemanticTokensClientCapabilities_RequestsCodec:
    case class S0(
    )
    object S0 extends codecs.structures_SemanticTokensClientCapabilities_Requests_S0Codec
    /**
     *  @param delta
     *    The client will send the `textDocument/semanticTokens/full/delta` request if
     *    the server provides a corresponding handler.
    
     */
    case class S1(
      delta: Option[Boolean] = None
    )
    object S1 extends codecs.structures_SemanticTokensClientCapabilities_Requests_S1Codec

/**
 *  @since 3.16.0

 *  @param resultId
 *  @param edits
 *    The semantic token edits to transform a previous result into a new result.

 */
case class SemanticTokensDelta(
  resultId: Option[String] = None,
  edits: Vector[structures.SemanticTokensEdit]
)
object SemanticTokensDelta extends codecs.structures_SemanticTokensDeltaCodec

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object SemanticTokensDeltaParams extends codecs.structures_SemanticTokensDeltaParamsCodec

/**
 *  @since 3.16.0

 *  @param edits
 */
case class SemanticTokensDeltaPartialResult(
  edits: Vector[structures.SemanticTokensEdit]
)
object SemanticTokensDeltaPartialResult extends codecs.structures_SemanticTokensDeltaPartialResultCodec

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
  start: runtime.uinteger,
  deleteCount: runtime.uinteger,
  data: Option[Vector[runtime.uinteger]] = None
)
object SemanticTokensEdit extends codecs.structures_SemanticTokensEditCodec

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
object SemanticTokensLegend extends codecs.structures_SemanticTokensLegendCodec

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
  range: Option[(Boolean | SemanticTokensOptions.S0)] = None,
  full: Option[(Boolean | SemanticTokensOptions.S1)] = None,
  workDoneProgress: Option[Boolean] = None
)
object SemanticTokensOptions extends codecs.structures_SemanticTokensOptionsCodec:
  case class S0(
  )
  object S0 extends codecs.structures_SemanticTokensOptions_S0Codec
  /**
   *  @param delta
   *    The server supports deltas for full documents.
  
   */
  case class S1(
    delta: Option[Boolean] = None
  )
  object S1 extends codecs.structures_SemanticTokensOptions_S1Codec

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object SemanticTokensParams extends codecs.structures_SemanticTokensParamsCodec

/**
 *  @since 3.16.0

 *  @param data
 */
case class SemanticTokensPartialResult(
  data: Vector[runtime.uinteger]
)
object SemanticTokensPartialResult extends codecs.structures_SemanticTokensPartialResultCodec

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object SemanticTokensRangeParams extends codecs.structures_SemanticTokensRangeParamsCodec

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
  documentSelector: Option[aliases.DocumentSelector] = None,
  legend: structures.SemanticTokensLegend,
  range: Option[(Boolean | SemanticTokensRegistrationOptions.S0)] = None,
  full: Option[(Boolean | SemanticTokensRegistrationOptions.S1)] = None,
  id: Option[String] = None
)
object SemanticTokensRegistrationOptions extends codecs.structures_SemanticTokensRegistrationOptionsCodec:
  case class S0(
  )
  object S0 extends codecs.structures_SemanticTokensRegistrationOptions_S0Codec
  /**
   *  @param delta
   *    The server supports deltas for full documents.
  
   */
  case class S1(
    delta: Option[Boolean] = None
  )
  object S1 extends codecs.structures_SemanticTokensRegistrationOptions_S1Codec

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
  refreshSupport: Option[Boolean] = None
)
object SemanticTokensWorkspaceClientCapabilities extends codecs.structures_SemanticTokensWorkspaceClientCapabilitiesCodec

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
 *    since 3.17.0

 *  @param textDocumentSync
 *    Defines how text documents are synced. Is either a detailed structure
 *    defining each notification or for backwards compatibility the
 *    TextDocumentSyncKind number.

 *  @param notebookDocumentSync
 *    Defines how notebook documents are synced.
 *    
 *    since 3.17.0

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
 *    since 3.16.0

 *  @param linkedEditingRangeProvider
 *    The server provides linked editing range support.
 *    
 *    since 3.16.0

 *  @param semanticTokensProvider
 *    The server provides semantic tokens support.
 *    
 *    since 3.16.0

 *  @param monikerProvider
 *    The server provides moniker support.
 *    
 *    since 3.16.0

 *  @param typeHierarchyProvider
 *    The server provides type hierarchy support.
 *    
 *    since 3.17.0

 *  @param inlineValueProvider
 *    The server provides inline values.
 *    
 *    since 3.17.0

 *  @param inlayHintProvider
 *    The server provides inlay hints.
 *    
 *    since 3.17.0

 *  @param diagnosticProvider
 *    The server has support for pull model diagnostics.
 *    
 *    since 3.17.0

 *  @param workspace
 *    Workspace specific server capabilities.

 *  @param experimental
 *    Experimental server capabilities.

 */
case class ServerCapabilities(
  positionEncoding: Option[enumerations.PositionEncodingKind] = None,
  textDocumentSync: Option[(structures.TextDocumentSyncOptions | enumerations.TextDocumentSyncKind)] = None,
  notebookDocumentSync: Option[(structures.NotebookDocumentSyncOptions | structures.NotebookDocumentSyncRegistrationOptions)] = None,
  completionProvider: Option[structures.CompletionOptions] = None,
  hoverProvider: Option[(Boolean | structures.HoverOptions)] = None,
  signatureHelpProvider: Option[structures.SignatureHelpOptions] = None,
  declarationProvider: Option[(Boolean | structures.DeclarationOptions | structures.DeclarationRegistrationOptions)] = None,
  definitionProvider: Option[(Boolean | structures.DefinitionOptions)] = None,
  typeDefinitionProvider: Option[(Boolean | structures.TypeDefinitionOptions | structures.TypeDefinitionRegistrationOptions)] = None,
  implementationProvider: Option[(Boolean | structures.ImplementationOptions | structures.ImplementationRegistrationOptions)] = None,
  referencesProvider: Option[(Boolean | structures.ReferenceOptions)] = None,
  documentHighlightProvider: Option[(Boolean | structures.DocumentHighlightOptions)] = None,
  documentSymbolProvider: Option[(Boolean | structures.DocumentSymbolOptions)] = None,
  codeActionProvider: Option[(Boolean | structures.CodeActionOptions)] = None,
  codeLensProvider: Option[structures.CodeLensOptions] = None,
  documentLinkProvider: Option[structures.DocumentLinkOptions] = None,
  colorProvider: Option[(Boolean | structures.DocumentColorOptions | structures.DocumentColorRegistrationOptions)] = None,
  workspaceSymbolProvider: Option[(Boolean | structures.WorkspaceSymbolOptions)] = None,
  documentFormattingProvider: Option[(Boolean | structures.DocumentFormattingOptions)] = None,
  documentRangeFormattingProvider: Option[(Boolean | structures.DocumentRangeFormattingOptions)] = None,
  documentOnTypeFormattingProvider: Option[structures.DocumentOnTypeFormattingOptions] = None,
  renameProvider: Option[(Boolean | structures.RenameOptions)] = None,
  foldingRangeProvider: Option[(Boolean | structures.FoldingRangeOptions | structures.FoldingRangeRegistrationOptions)] = None,
  selectionRangeProvider: Option[(Boolean | structures.SelectionRangeOptions | structures.SelectionRangeRegistrationOptions)] = None,
  executeCommandProvider: Option[structures.ExecuteCommandOptions] = None,
  callHierarchyProvider: Option[(Boolean | structures.CallHierarchyOptions | structures.CallHierarchyRegistrationOptions)] = None,
  linkedEditingRangeProvider: Option[(Boolean | structures.LinkedEditingRangeOptions | structures.LinkedEditingRangeRegistrationOptions)] = None,
  semanticTokensProvider: Option[(structures.SemanticTokensOptions | structures.SemanticTokensRegistrationOptions)] = None,
  monikerProvider: Option[(Boolean | structures.MonikerOptions | structures.MonikerRegistrationOptions)] = None,
  typeHierarchyProvider: Option[(Boolean | structures.TypeHierarchyOptions | structures.TypeHierarchyRegistrationOptions)] = None,
  inlineValueProvider: Option[(Boolean | structures.InlineValueOptions | structures.InlineValueRegistrationOptions)] = None,
  inlayHintProvider: Option[(Boolean | structures.InlayHintOptions | structures.InlayHintRegistrationOptions)] = None,
  diagnosticProvider: Option[(structures.DiagnosticOptions | structures.DiagnosticRegistrationOptions)] = None,
  workspace: Option[ServerCapabilities.Workspace] = None,
  experimental: Option[io.circe.Json] = None
)
object ServerCapabilities extends codecs.structures_ServerCapabilitiesCodec:
  /**
   *  @param workspaceFolders
   *    The server supports workspace folder.
   *    
   *    since 3.6.0
  
   *  @param fileOperations
   *    The server is interested in notifications/requests for operations on files.
   *    
   *    since 3.16.0
  
   */
  case class Workspace(
    workspaceFolders: Option[structures.WorkspaceFoldersServerCapabilities] = None,
    fileOperations: Option[structures.FileOperationOptions] = None
  )
  object Workspace extends codecs.structures_ServerCapabilities_WorkspaceCodec

case class SetTraceParams(
  value: enumerations.TraceValues
)
object SetTraceParams extends codecs.structures_SetTraceParamsCodec

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
object ShowDocumentClientCapabilities extends codecs.structures_ShowDocumentClientCapabilitiesCodec

/**
 *  Params to show a resource in the UI.
 *  
 *  @since 3.16.0

 *  @param uri
 *    The uri to show.

 *  @param external
 *    Indicates to show the resource in an external program.
 *    To show, for example, `https://code.visualstudio.com/`
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
  uri: runtime.Uri,
  external: Option[Boolean] = None,
  takeFocus: Option[Boolean] = None,
  selection: Option[structures.Range] = None
)
object ShowDocumentParams extends codecs.structures_ShowDocumentParamsCodec

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
object ShowDocumentResult extends codecs.structures_ShowDocumentResultCodec

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
object ShowMessageParams extends codecs.structures_ShowMessageParamsCodec

/**
 *  Show message request client capabilities

 *  @param messageActionItem
 *    Capabilities specific to the `MessageActionItem` type.

 */
case class ShowMessageRequestClientCapabilities(
  messageActionItem: Option[ShowMessageRequestClientCapabilities.MessageActionItem] = None
)
object ShowMessageRequestClientCapabilities extends codecs.structures_ShowMessageRequestClientCapabilitiesCodec:
  /**
   *  @param additionalPropertiesSupport
   *    Whether the client supports additional attributes which
   *    are preserved and send back to the server in the
   *    request's response.
  
   */
  case class MessageActionItem(
    additionalPropertiesSupport: Option[Boolean] = None
  )
  object MessageActionItem extends codecs.structures_ShowMessageRequestClientCapabilities_MessageActionItemCodec

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
  actions: Option[Vector[structures.MessageActionItem]] = None
)
object ShowMessageRequestParams extends codecs.structures_ShowMessageRequestParamsCodec

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
  activeSignature: Option[runtime.uinteger] = None,
  activeParameter: Option[runtime.uinteger] = None
)
object SignatureHelp extends codecs.structures_SignatureHelpCodec

/**
 *  Client Capabilities for a {@link SignatureHelpRequest}.

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
 *    since 3.15.0

 */
case class SignatureHelpClientCapabilities(
  dynamicRegistration: Option[Boolean] = None,
  signatureInformation: Option[SignatureHelpClientCapabilities.SignatureInformation] = None,
  contextSupport: Option[Boolean] = None
)
object SignatureHelpClientCapabilities extends codecs.structures_SignatureHelpClientCapabilitiesCodec:
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
   *    since 3.16.0
  
   */
  case class SignatureInformation(
    documentationFormat: Option[Vector[enumerations.MarkupKind]] = None,
    parameterInformation: Option[SignatureInformation.ParameterInformation] = None,
    activeParameterSupport: Option[Boolean] = None
  )
  object SignatureInformation extends codecs.structures_SignatureHelpClientCapabilities_SignatureInformationCodec:
    /**
     *  @param labelOffsetSupport
     *    The client supports processing label offsets instead of a
     *    simple label string.
     *    
     *    since 3.14.0
    
     */
    case class ParameterInformation(
      labelOffsetSupport: Option[Boolean] = None
    )
    object ParameterInformation extends codecs.structures_SignatureHelpClientCapabilities_SignatureInformation_ParameterInformationCodec

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
  triggerCharacter: Option[String] = None,
  isRetrigger: Boolean,
  activeSignatureHelp: Option[structures.SignatureHelp] = None
)
object SignatureHelpContext extends codecs.structures_SignatureHelpContextCodec

/**
 *  Server Capabilities for a {@link SignatureHelpRequest}.

 *  @param triggerCharacters
 *    List of characters that trigger signature help automatically.

 *  @param retriggerCharacters
 *    List of characters that re-trigger signature help.
 *    
 *    These trigger characters are only active when signature help is already showing. All trigger characters
 *    are also counted as re-trigger characters.
 *    
 *    since 3.15.0

 *  @param workDoneProgress
 */
case class SignatureHelpOptions(
  triggerCharacters: Option[Vector[String]] = None,
  retriggerCharacters: Option[Vector[String]] = None,
  workDoneProgress: Option[Boolean] = None
)
object SignatureHelpOptions extends codecs.structures_SignatureHelpOptionsCodec

/**
 *  Parameters for a {@link SignatureHelpRequest}.

 *  @param context
 *    The signature help context. This is only available if the client specifies
 *    to send this using the client capability `textDocument.signatureHelp.contextSupport === true`
 *    
 *    since 3.15.0

 *  @param textDocument
 *    The text document.

 *  @param position
 *    The position inside the text document.

 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 */
case class SignatureHelpParams(
  context: Option[structures.SignatureHelpContext] = None,
  textDocument: structures.TextDocumentIdentifier,
  position: structures.Position,
  workDoneToken: Option[aliases.ProgressToken] = None
)
object SignatureHelpParams extends codecs.structures_SignatureHelpParamsCodec

/**
 *  Registration options for a {@link SignatureHelpRequest}.

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
 *    since 3.15.0

 */
case class SignatureHelpRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None,
  triggerCharacters: Option[Vector[String]] = None,
  retriggerCharacters: Option[Vector[String]] = None
)
object SignatureHelpRegistrationOptions extends codecs.structures_SignatureHelpRegistrationOptionsCodec

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
 *    since 3.16.0

 */
case class SignatureInformation(
  label: String,
  documentation: Option[(String | structures.MarkupContent)] = None,
  parameters: Option[Vector[structures.ParameterInformation]] = None,
  activeParameter: Option[runtime.uinteger] = None
)
object SignatureInformation extends codecs.structures_SignatureInformationCodec

/**
 *  Static registration options to be returned in the initialize
 *  request.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class StaticRegistrationOptions(
  id: Option[String] = None
)
object StaticRegistrationOptions extends codecs.structures_StaticRegistrationOptionsCodec

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
 *    since 3.16.0

 *  @param containerName
 *    The name of the symbol containing this symbol. This information is for
 *    user interface purposes (e.g. to render a qualifier in the user interface
 *    if necessary). It can't be used to re-infer a hierarchy for the document
 *    symbols.

 */
case class SymbolInformation(
  deprecated: Option[Boolean] = None,
  location: structures.Location,
  name: String,
  kind: enumerations.SymbolKind,
  tags: Option[Vector[enumerations.SymbolTag]] = None,
  containerName: Option[String] = None
)
object SymbolInformation extends codecs.structures_SymbolInformationCodec

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
  documentSelector: Option[aliases.DocumentSelector] = None
)
object TextDocumentChangeRegistrationOptions extends codecs.structures_TextDocumentChangeRegistrationOptionsCodec

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
 *    since 3.14.0

 *  @param definition
 *    Capabilities specific to the `textDocument/definition` request.

 *  @param typeDefinition
 *    Capabilities specific to the `textDocument/typeDefinition` request.
 *    
 *    since 3.6.0

 *  @param implementation
 *    Capabilities specific to the `textDocument/implementation` request.
 *    
 *    since 3.6.0

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
 *    since 3.6.0

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
 *    since 3.10.0

 *  @param selectionRange
 *    Capabilities specific to the `textDocument/selectionRange` request.
 *    
 *    since 3.15.0

 *  @param publishDiagnostics
 *    Capabilities specific to the `textDocument/publishDiagnostics` notification.

 *  @param callHierarchy
 *    Capabilities specific to the various call hierarchy requests.
 *    
 *    since 3.16.0

 *  @param semanticTokens
 *    Capabilities specific to the various semantic token request.
 *    
 *    since 3.16.0

 *  @param linkedEditingRange
 *    Capabilities specific to the `textDocument/linkedEditingRange` request.
 *    
 *    since 3.16.0

 *  @param moniker
 *    Client capabilities specific to the `textDocument/moniker` request.
 *    
 *    since 3.16.0

 *  @param typeHierarchy
 *    Capabilities specific to the various type hierarchy requests.
 *    
 *    since 3.17.0

 *  @param inlineValue
 *    Capabilities specific to the `textDocument/inlineValue` request.
 *    
 *    since 3.17.0

 *  @param inlayHint
 *    Capabilities specific to the `textDocument/inlayHint` request.
 *    
 *    since 3.17.0

 *  @param diagnostic
 *    Capabilities specific to the diagnostic pull model.
 *    
 *    since 3.17.0

 */
case class TextDocumentClientCapabilities(
  synchronization: Option[structures.TextDocumentSyncClientCapabilities] = None,
  completion: Option[structures.CompletionClientCapabilities] = None,
  hover: Option[structures.HoverClientCapabilities] = None,
  signatureHelp: Option[structures.SignatureHelpClientCapabilities] = None,
  declaration: Option[structures.DeclarationClientCapabilities] = None,
  definition: Option[structures.DefinitionClientCapabilities] = None,
  typeDefinition: Option[structures.TypeDefinitionClientCapabilities] = None,
  implementation: Option[structures.ImplementationClientCapabilities] = None,
  references: Option[structures.ReferenceClientCapabilities] = None,
  documentHighlight: Option[structures.DocumentHighlightClientCapabilities] = None,
  documentSymbol: Option[structures.DocumentSymbolClientCapabilities] = None,
  codeAction: Option[structures.CodeActionClientCapabilities] = None,
  codeLens: Option[structures.CodeLensClientCapabilities] = None,
  documentLink: Option[structures.DocumentLinkClientCapabilities] = None,
  colorProvider: Option[structures.DocumentColorClientCapabilities] = None,
  formatting: Option[structures.DocumentFormattingClientCapabilities] = None,
  rangeFormatting: Option[structures.DocumentRangeFormattingClientCapabilities] = None,
  onTypeFormatting: Option[structures.DocumentOnTypeFormattingClientCapabilities] = None,
  rename: Option[structures.RenameClientCapabilities] = None,
  foldingRange: Option[structures.FoldingRangeClientCapabilities] = None,
  selectionRange: Option[structures.SelectionRangeClientCapabilities] = None,
  publishDiagnostics: Option[structures.PublishDiagnosticsClientCapabilities] = None,
  callHierarchy: Option[structures.CallHierarchyClientCapabilities] = None,
  semanticTokens: Option[structures.SemanticTokensClientCapabilities] = None,
  linkedEditingRange: Option[structures.LinkedEditingRangeClientCapabilities] = None,
  moniker: Option[structures.MonikerClientCapabilities] = None,
  typeHierarchy: Option[structures.TypeHierarchyClientCapabilities] = None,
  inlineValue: Option[structures.InlineValueClientCapabilities] = None,
  inlayHint: Option[structures.InlayHintClientCapabilities] = None,
  diagnostic: Option[structures.DiagnosticClientCapabilities] = None
)
object TextDocumentClientCapabilities extends codecs.structures_TextDocumentClientCapabilitiesCodec

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
 *    since 3.16.0 - support for AnnotatedTextEdit. This is guarded using a
 *    client capability.

 */
case class TextDocumentEdit(
  textDocument: structures.OptionalVersionedTextDocumentIdentifier,
  edits: Vector[(structures.TextEdit | structures.AnnotatedTextEdit)]
)
object TextDocumentEdit extends codecs.structures_TextDocumentEditCodec

/**
 *  A literal to identify a text document in the client.

 *  @param uri
 *    The text document's uri.

 */
case class TextDocumentIdentifier(
  uri: runtime.DocumentUri
)
object TextDocumentIdentifier extends codecs.structures_TextDocumentIdentifierCodec

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
  uri: runtime.DocumentUri,
  languageId: String,
  version: Int,
  text: String
)
object TextDocumentItem extends codecs.structures_TextDocumentItemCodec

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
object TextDocumentPositionParams extends codecs.structures_TextDocumentPositionParamsCodec

/**
 *  General text document registration options.

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 */
case class TextDocumentRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None
)
object TextDocumentRegistrationOptions extends codecs.structures_TextDocumentRegistrationOptionsCodec

/**
 *  Save registration options.

 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param includeText
 *    The client is supposed to include the content on save.

 */
case class TextDocumentSaveRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None,
  includeText: Option[Boolean] = None
)
object TextDocumentSaveRegistrationOptions extends codecs.structures_TextDocumentSaveRegistrationOptionsCodec

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
  dynamicRegistration: Option[Boolean] = None,
  willSave: Option[Boolean] = None,
  willSaveWaitUntil: Option[Boolean] = None,
  didSave: Option[Boolean] = None
)
object TextDocumentSyncClientCapabilities extends codecs.structures_TextDocumentSyncClientCapabilitiesCodec

/**
 *  @param openClose
 *    Open and close notifications are sent to the server. If omitted open close notification should not
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
  openClose: Option[Boolean] = None,
  change: Option[enumerations.TextDocumentSyncKind] = None,
  willSave: Option[Boolean] = None,
  willSaveWaitUntil: Option[Boolean] = None,
  save: Option[(Boolean | structures.SaveOptions)] = None
)
object TextDocumentSyncOptions extends codecs.structures_TextDocumentSyncOptionsCodec

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
object TextEdit extends codecs.structures_TextEditCodec

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
  dynamicRegistration: Option[Boolean] = None,
  linkSupport: Option[Boolean] = None
)
object TypeDefinitionClientCapabilities extends codecs.structures_TypeDefinitionClientCapabilitiesCodec

case class TypeDefinitionOptions(
  workDoneProgress: Option[Boolean] = None
)
object TypeDefinitionOptions extends codecs.structures_TypeDefinitionOptionsCodec

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object TypeDefinitionParams extends codecs.structures_TypeDefinitionParamsCodec

/**
 *  @param documentSelector
 *    A document selector to identify the scope of the registration. If set to null
 *    the document selector provided on the client side will be used.

 *  @param id
 *    The id used to register the request. The id can be used to deregister
 *    the request again. See also Registration#id.

 */
case class TypeDefinitionRegistrationOptions(
  documentSelector: Option[aliases.DocumentSelector] = None,
  id: Option[String] = None
)
object TypeDefinitionRegistrationOptions extends codecs.structures_TypeDefinitionRegistrationOptionsCodec

/**
 *  @since 3.17.0

 *  @param dynamicRegistration
 *    Whether implementation supports dynamic registration. If this is set to `true`
 *    the client supports the new `(TextDocumentRegistrationOptions & StaticRegistrationOptions)`
 *    return value for the corresponding server capability as well.

 */
case class TypeHierarchyClientCapabilities(
  dynamicRegistration: Option[Boolean] = None
)
object TypeHierarchyClientCapabilities extends codecs.structures_TypeHierarchyClientCapabilitiesCodec

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
 *    {@link TypeHierarchyItem.range `range`}.

 *  @param data
 *    A data entry field that is preserved between a type hierarchy prepare and
 *    supertypes or subtypes requests. It could also be used to identify the
 *    type hierarchy in the server, helping improve the performance on
 *    resolving supertypes and subtypes.

 */
case class TypeHierarchyItem(
  name: String,
  kind: enumerations.SymbolKind,
  tags: Option[Vector[enumerations.SymbolTag]] = None,
  detail: Option[String] = None,
  uri: runtime.DocumentUri,
  range: structures.Range,
  selectionRange: structures.Range,
  data: Option[io.circe.Json] = None
)
object TypeHierarchyItem extends codecs.structures_TypeHierarchyItemCodec

/**
 *  Type hierarchy options used during static registration.
 *  
 *  @since 3.17.0

 *  @param workDoneProgress
 */
case class TypeHierarchyOptions(
  workDoneProgress: Option[Boolean] = None
)
object TypeHierarchyOptions extends codecs.structures_TypeHierarchyOptionsCodec

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
  workDoneToken: Option[aliases.ProgressToken] = None
)
object TypeHierarchyPrepareParams extends codecs.structures_TypeHierarchyPrepareParamsCodec

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
  documentSelector: Option[aliases.DocumentSelector] = None,
  id: Option[String] = None
)
object TypeHierarchyRegistrationOptions extends codecs.structures_TypeHierarchyRegistrationOptionsCodec

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object TypeHierarchySubtypesParams extends codecs.structures_TypeHierarchySubtypesParamsCodec

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object TypeHierarchySupertypesParams extends codecs.structures_TypeHierarchySupertypesParamsCodec

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
object UnchangedDocumentDiagnosticReport extends codecs.structures_UnchangedDocumentDiagnosticReportCodec

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
object Unregistration extends codecs.structures_UnregistrationCodec

case class UnregistrationParams(
  unregisterations: Vector[structures.Unregistration]
)
object UnregistrationParams extends codecs.structures_UnregistrationParamsCodec

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
  uri: runtime.Uri
)
object VersionedNotebookDocumentIdentifier extends codecs.structures_VersionedNotebookDocumentIdentifierCodec

/**
 *  A text document identifier to denote a specific version of a text document.

 *  @param version
 *    The version number of this document.

 *  @param uri
 *    The text document's uri.

 */
case class VersionedTextDocumentIdentifier(
  version: Int,
  uri: runtime.DocumentUri
)
object VersionedTextDocumentIdentifier extends codecs.structures_VersionedTextDocumentIdentifierCodec

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
object WillSaveTextDocumentParams extends codecs.structures_WillSaveTextDocumentParamsCodec

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
 *    since 3.15.0

 *  @param showMessage
 *    Capabilities specific to the showMessage request.
 *    
 *    since 3.16.0

 *  @param showDocument
 *    Capabilities specific to the showDocument request.
 *    
 *    since 3.16.0

 */
case class WindowClientCapabilities(
  workDoneProgress: Option[Boolean] = None,
  showMessage: Option[structures.ShowMessageRequestClientCapabilities] = None,
  showDocument: Option[structures.ShowDocumentClientCapabilities] = None
)
object WindowClientCapabilities extends codecs.structures_WindowClientCapabilitiesCodec

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
 *    to ignore the `percentage` value in subsequent report notifications.
 *    
 *    The value should be steadily rising. Clients are free to ignore values
 *    that are not following this rule. The value range is [0, 100].

 */
case class WorkDoneProgressBegin(
  kind: "begin",
  title: String,
  cancellable: Option[Boolean] = None,
  message: Option[String] = None,
  percentage: Option[runtime.uinteger] = None
)
object WorkDoneProgressBegin extends codecs.structures_WorkDoneProgressBeginCodec

/**
 *  @param token
 *    The token to be used to report progress.

 */
case class WorkDoneProgressCancelParams(
  token: aliases.ProgressToken
)
object WorkDoneProgressCancelParams extends codecs.structures_WorkDoneProgressCancelParamsCodec

/**
 *  @param token
 *    The token to be used to report progress.

 */
case class WorkDoneProgressCreateParams(
  token: aliases.ProgressToken
)
object WorkDoneProgressCreateParams extends codecs.structures_WorkDoneProgressCreateParamsCodec

/**
 *  @param kind
 *  @param message
 *    Optional, a final message indicating to for example indicate the outcome
 *    of the operation.

 */
case class WorkDoneProgressEnd(
  kind: "end",
  message: Option[String] = None
)
object WorkDoneProgressEnd extends codecs.structures_WorkDoneProgressEndCodec

case class WorkDoneProgressOptions(
  workDoneProgress: Option[Boolean] = None
)
object WorkDoneProgressOptions extends codecs.structures_WorkDoneProgressOptionsCodec

/**
 *  @param workDoneToken
 *    An optional token that a server can use to report work done progress.

 */
case class WorkDoneProgressParams(
  workDoneToken: Option[aliases.ProgressToken] = None
)
object WorkDoneProgressParams extends codecs.structures_WorkDoneProgressParamsCodec

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
 *    to ignore the `percentage` value in subsequent report notifications.
 *    
 *    The value should be steadily rising. Clients are free to ignore values
 *    that are not following this rule. The value range is [0, 100].

 */
case class WorkDoneProgressReport(
  kind: "report",
  cancellable: Option[Boolean] = None,
  message: Option[String] = None,
  percentage: Option[runtime.uinteger] = None
)
object WorkDoneProgressReport extends codecs.structures_WorkDoneProgressReportCodec

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
 *    since 3.6.0

 *  @param configuration
 *    The client supports `workspace/configuration` requests.
 *    
 *    since 3.6.0

 *  @param semanticTokens
 *    Capabilities specific to the semantic token requests scoped to the
 *    workspace.
 *    
 *    since 3.16.0.

 *  @param codeLens
 *    Capabilities specific to the code lens requests scoped to the
 *    workspace.
 *    
 *    since 3.16.0.

 *  @param fileOperations
 *    The client has support for file notifications/requests for user operations on files.
 *    
 *    Since 3.16.0

 *  @param inlineValue
 *    Capabilities specific to the inline values requests scoped to the
 *    workspace.
 *    
 *    since 3.17.0.

 *  @param inlayHint
 *    Capabilities specific to the inlay hint requests scoped to the
 *    workspace.
 *    
 *    since 3.17.0.

 *  @param diagnostics
 *    Capabilities specific to the diagnostic requests scoped to the
 *    workspace.
 *    
 *    since 3.17.0.

 */
case class WorkspaceClientCapabilities(
  applyEdit: Option[Boolean] = None,
  workspaceEdit: Option[structures.WorkspaceEditClientCapabilities] = None,
  didChangeConfiguration: Option[structures.DidChangeConfigurationClientCapabilities] = None,
  didChangeWatchedFiles: Option[structures.DidChangeWatchedFilesClientCapabilities] = None,
  symbol: Option[structures.WorkspaceSymbolClientCapabilities] = None,
  executeCommand: Option[structures.ExecuteCommandClientCapabilities] = None,
  workspaceFolders: Option[Boolean] = None,
  configuration: Option[Boolean] = None,
  semanticTokens: Option[structures.SemanticTokensWorkspaceClientCapabilities] = None,
  codeLens: Option[structures.CodeLensWorkspaceClientCapabilities] = None,
  fileOperations: Option[structures.FileOperationClientCapabilities] = None,
  inlineValue: Option[structures.InlineValueWorkspaceClientCapabilities] = None,
  inlayHint: Option[structures.InlayHintWorkspaceClientCapabilities] = None,
  diagnostics: Option[structures.DiagnosticWorkspaceClientCapabilities] = None
)
object WorkspaceClientCapabilities extends codecs.structures_WorkspaceClientCapabilitiesCodec

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
  identifier: Option[String] = None,
  previousResultIds: Vector[structures.PreviousResultId],
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object WorkspaceDiagnosticParams extends codecs.structures_WorkspaceDiagnosticParamsCodec

/**
 *  A workspace diagnostic report.
 *  
 *  @since 3.17.0

 *  @param items
 */
case class WorkspaceDiagnosticReport(
  items: Vector[aliases.WorkspaceDocumentDiagnosticReport]
)
object WorkspaceDiagnosticReport extends codecs.structures_WorkspaceDiagnosticReportCodec

/**
 *  A partial result for a workspace diagnostic report.
 *  
 *  @since 3.17.0

 *  @param items
 */
case class WorkspaceDiagnosticReportPartialResult(
  items: Vector[aliases.WorkspaceDocumentDiagnosticReport]
)
object WorkspaceDiagnosticReportPartialResult extends codecs.structures_WorkspaceDiagnosticReportPartialResultCodec

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
 *    since 3.16.0

 */
case class WorkspaceEdit(
  changes: Option[Map[runtime.DocumentUri, Vector[structures.TextEdit]]] = None,
  documentChanges: Option[Vector[(structures.TextDocumentEdit | structures.CreateFile | structures.RenameFile | structures.DeleteFile)]] = None,
  changeAnnotations: Option[Map[aliases.ChangeAnnotationIdentifier, structures.ChangeAnnotation]] = None
)
object WorkspaceEdit extends codecs.structures_WorkspaceEditCodec

/**
 *  @param documentChanges
 *    The client supports versioned document changes in `WorkspaceEdit`s

 *  @param resourceOperations
 *    The resource operations the client supports. Clients should at least
 *    support 'create', 'rename' and 'delete' files and folders.
 *    
 *    since 3.13.0

 *  @param failureHandling
 *    The failure handling strategy of a client if applying the workspace edit
 *    fails.
 *    
 *    since 3.13.0

 *  @param normalizesLineEndings
 *    Whether the client normalizes line endings to the client specific
 *    setting.
 *    If set to `true` the client will normalize line ending characters
 *    in a workspace edit to the client-specified new line
 *    character.
 *    
 *    since 3.16.0

 *  @param changeAnnotationSupport
 *    Whether the client in general supports change annotations on text edits,
 *    create file, rename file and delete file changes.
 *    
 *    since 3.16.0

 */
case class WorkspaceEditClientCapabilities(
  documentChanges: Option[Boolean] = None,
  resourceOperations: Option[Vector[enumerations.ResourceOperationKind]] = None,
  failureHandling: Option[enumerations.FailureHandlingKind] = None,
  normalizesLineEndings: Option[Boolean] = None,
  changeAnnotationSupport: Option[WorkspaceEditClientCapabilities.ChangeAnnotationSupport] = None
)
object WorkspaceEditClientCapabilities extends codecs.structures_WorkspaceEditClientCapabilitiesCodec:
  /**
   *  @param groupsOnLabel
   *    Whether the client groups edits with equal labels into tree nodes,
   *    for instance all edits labelled with "Changes in Strings" would
   *    be a tree node.
  
   */
  case class ChangeAnnotationSupport(
    groupsOnLabel: Option[Boolean] = None
  )
  object ChangeAnnotationSupport extends codecs.structures_WorkspaceEditClientCapabilities_ChangeAnnotationSupportCodec

/**
 *  A workspace folder inside a client.

 *  @param uri
 *    The associated URI for this workspace folder.

 *  @param name
 *    The name of the workspace folder. Used to refer to this
 *    workspace folder in the user interface.

 */
case class WorkspaceFolder(
  uri: runtime.Uri,
  name: String
)
object WorkspaceFolder extends codecs.structures_WorkspaceFolderCodec

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
object WorkspaceFoldersChangeEvent extends codecs.structures_WorkspaceFoldersChangeEventCodec

/**
 *  @param workspaceFolders
 *    The workspace folders configured in the client when the server starts.
 *    
 *    This property is only available if the client supports workspace folders.
 *    It can be `null` if the client supports workspace folders but none are
 *    configured.
 *    
 *    since 3.6.0

 */
case class WorkspaceFoldersInitializeParams(
  workspaceFolders: Option[Vector[structures.WorkspaceFolder]] = None
)
object WorkspaceFoldersInitializeParams extends codecs.structures_WorkspaceFoldersInitializeParamsCodec

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
  supported: Option[Boolean] = None,
  changeNotifications: Option[(String | Boolean)] = None
)
object WorkspaceFoldersServerCapabilities extends codecs.structures_WorkspaceFoldersServerCapabilitiesCodec

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
  uri: runtime.DocumentUri,
  version: Option[Int] = None,
  kind: "full",
  resultId: Option[String] = None,
  items: Vector[structures.Diagnostic]
)
object WorkspaceFullDocumentDiagnosticReport extends codecs.structures_WorkspaceFullDocumentDiagnosticReportCodec

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
 *    since 3.16.0

 *  @param containerName
 *    The name of the symbol containing this symbol. This information is for
 *    user interface purposes (e.g. to render a qualifier in the user interface
 *    if necessary). It can't be used to re-infer a hierarchy for the document
 *    symbols.

 */
case class WorkspaceSymbol(
  location: (structures.Location | WorkspaceSymbol.S0),
  data: Option[io.circe.Json] = None,
  name: String,
  kind: enumerations.SymbolKind,
  tags: Option[Vector[enumerations.SymbolTag]] = None,
  containerName: Option[String] = None
)
object WorkspaceSymbol extends codecs.structures_WorkspaceSymbolCodec:
  case class S0(
    uri: runtime.DocumentUri
  )
  object S0 extends codecs.structures_WorkspaceSymbol_S0Codec

/**
 *  Client capabilities for a {@link WorkspaceSymbolRequest}.

 *  @param dynamicRegistration
 *    Symbol request supports dynamic registration.

 *  @param symbolKind
 *    Specific capabilities for the `SymbolKind` in the `workspace/symbol` request.

 *  @param tagSupport
 *    The client supports tags on `SymbolInformation`.
 *    Clients supporting tags have to handle unknown tags gracefully.
 *    
 *    since 3.16.0

 *  @param resolveSupport
 *    The client support partial workspace symbols. The client will send the
 *    request `workspaceSymbol/resolve` to the server to resolve additional
 *    properties.
 *    
 *    since 3.17.0

 */
case class WorkspaceSymbolClientCapabilities(
  dynamicRegistration: Option[Boolean] = None,
  symbolKind: Option[WorkspaceSymbolClientCapabilities.SymbolKind] = None,
  tagSupport: Option[WorkspaceSymbolClientCapabilities.TagSupport] = None,
  resolveSupport: Option[WorkspaceSymbolClientCapabilities.ResolveSupport] = None
)
object WorkspaceSymbolClientCapabilities extends codecs.structures_WorkspaceSymbolClientCapabilitiesCodec:
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
    valueSet: Option[Vector[enumerations.SymbolKind]] = None
  )
  object SymbolKind extends codecs.structures_WorkspaceSymbolClientCapabilities_SymbolKindCodec
  /**
   *  @param valueSet
   *    The tags supported by the client.
  
   */
  case class TagSupport(
    valueSet: Vector[enumerations.SymbolTag]
  )
  object TagSupport extends codecs.structures_WorkspaceSymbolClientCapabilities_TagSupportCodec
  /**
   *  @param properties
   *    The properties that a client can resolve lazily. Usually
   *    `location.range`
  
   */
  case class ResolveSupport(
    properties: Vector[String]
  )
  object ResolveSupport extends codecs.structures_WorkspaceSymbolClientCapabilities_ResolveSupportCodec

/**
 *  Server capabilities for a {@link WorkspaceSymbolRequest}.

 *  @param resolveProvider
 *    The server provides support to resolve additional
 *    information for a workspace symbol.
 *    
 *    since 3.17.0

 *  @param workDoneProgress
 */
case class WorkspaceSymbolOptions(
  resolveProvider: Option[Boolean] = None,
  workDoneProgress: Option[Boolean] = None
)
object WorkspaceSymbolOptions extends codecs.structures_WorkspaceSymbolOptionsCodec

/**
 *  The parameters of a {@link WorkspaceSymbolRequest}.

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
  workDoneToken: Option[aliases.ProgressToken] = None,
  partialResultToken: Option[aliases.ProgressToken] = None
)
object WorkspaceSymbolParams extends codecs.structures_WorkspaceSymbolParamsCodec

/**
 *  Registration options for a {@link WorkspaceSymbolRequest}.

 *  @param resolveProvider
 *    The server provides support to resolve additional
 *    information for a workspace symbol.
 *    
 *    since 3.17.0

 */
case class WorkspaceSymbolRegistrationOptions(
  resolveProvider: Option[Boolean] = None
)
object WorkspaceSymbolRegistrationOptions extends codecs.structures_WorkspaceSymbolRegistrationOptionsCodec

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
  uri: runtime.DocumentUri,
  version: Option[Int] = None,
  kind: "unchanged",
  resultId: String
)
object WorkspaceUnchangedDocumentDiagnosticReport extends codecs.structures_WorkspaceUnchangedDocumentDiagnosticReportCodec

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
 *    since 3.15.0

 *  @param locale
 *    The locale the client is currently showing the user interface
 *    in. This must not necessarily be the locale of the operating
 *    system.
 *    
 *    Uses IETF language tags as the value's syntax
 *    (See https://en.wikipedia.org/wiki/IETF_language_tag)
 *    
 *    since 3.16.0

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
  processId: Option[Int] = None,
  clientInfo: Option[_InitializeParams.ClientInfo] = None,
  locale: Option[String] = None,
  rootPath: Option[String] = None,
  rootUri: Option[runtime.DocumentUri] = None,
  capabilities: structures.ClientCapabilities,
  initializationOptions: Option[io.circe.Json] = None,
  trace: Option[enumerations.TraceValues] = None,
  workDoneToken: Option[aliases.ProgressToken] = None
)
object _InitializeParams extends codecs.structures__InitializeParamsCodec:
  /**
   *  @param name
   *    The name of the client as defined by the client.
  
   *  @param version
   *    The client's version as defined by the client.
  
   */
  case class ClientInfo(
    name: String,
    version: Option[String] = None
  )
  object ClientInfo extends codecs.structures__InitializeParams_ClientInfoCodec

