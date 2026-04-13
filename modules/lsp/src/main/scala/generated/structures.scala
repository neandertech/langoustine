// format:off
package langoustine.lsp
package structures

import langoustine.*
import upickle.default.*
import runtime.{*, given}

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

