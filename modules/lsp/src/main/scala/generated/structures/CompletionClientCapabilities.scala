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

// format:off
package langoustine.lsp
package structures

import langoustine.*
import runtime.{*, given}

/** Completion client capabilities
  *
  * @param dynamicRegistration
  *   Whether completion supports dynamic registration.
  *
  * @param completionItem
  *   The client supports the following `CompletionItem` specific capabilities.
  *
  * @param completionItemKind
  * @param insertTextMode
  *   Defines how the client handles whitespace and indentation when accepting a
  *   completion item that uses multi line text in either `insertText` or
  *   `textEdit`.
  *
  * since 3.17.0
  *
  * @param contextSupport
  *   The client supports to send additional context information for a
  *   `textDocument/completion` request.
  *
  * @param completionList
  *   The client supports the following `CompletionList` specific capabilities.
  *
  * since 3.17.0
  */
case class CompletionClientCapabilities(
    dynamicRegistration: Option[Boolean] = None,
    completionItem: Option[CompletionClientCapabilities.CompletionItem] = None,
    completionItemKind: Option[
      CompletionClientCapabilities.CompletionItemKind
    ] = None,
    insertTextMode: Option[enumerations.InsertTextMode] = None,
    contextSupport: Option[Boolean] = None,
    completionList: Option[CompletionClientCapabilities.CompletionList] = None
)
object CompletionClientCapabilities
    extends codecs.structures_CompletionClientCapabilitiesCodec:
  /** @param snippetSupport
    *   Client supports snippets as insert text.
    *
    * A snippet can define tab stops and placeholders with `$1`, `$2` and
    * `${3:foo}`. `$0` defines the final tab stop, it defaults to the end of the
    * snippet. Placeholders with equal identifiers are linked, that is typing in
    * one will update others too.
    *
    * @param commitCharactersSupport
    *   Client supports commit characters on a completion item.
    *
    * @param documentationFormat
    *   Client supports the following content formats for the documentation
    *   property. The order describes the preferred format of the client.
    *
    * @param deprecatedSupport
    *   Client supports the deprecated property on a completion item.
    *
    * @param preselectSupport
    *   Client supports the preselect property on a completion item.
    *
    * @param tagSupport
    *   Client supports the tag property on a completion item. Clients
    *   supporting tags have to handle unknown tags gracefully. Clients
    *   especially need to preserve unknown tags when sending a completion item
    *   back to the server in a resolve call.
    *
    * since 3.15.0
    *
    * @param insertReplaceSupport
    *   Client support insert replace edit to control different behavior if a
    *   completion item is inserted in the text or should replace text.
    *
    * since 3.16.0
    *
    * @param resolveSupport
    *   Indicates which properties a client can resolve lazily on a completion
    *   item. Before version 3.16.0 only the predefined properties
    *   `documentation` and `details` could be resolved lazily.
    *
    * since 3.16.0
    *
    * @param insertTextModeSupport
    *   The client supports the `insertTextMode` property on a completion item
    *   to override the whitespace handling mode as defined by the client (see
    *   `insertTextMode`).
    *
    * since 3.16.0
    *
    * @param labelDetailsSupport
    *   The client has support for completion item label details (see also
    *   `CompletionItemLabelDetails`).
    *
    * since 3.17.0
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
      insertTextModeSupport: Option[CompletionItem.InsertTextModeSupport] =
        None,
      labelDetailsSupport: Option[Boolean] = None
  )
  object CompletionItem
      extends codecs.structures_CompletionClientCapabilities_CompletionItemCodec:
    /** @param valueSet
      *   The tags supported by the client.
      */
    case class TagSupport(
        valueSet: Vector[enumerations.CompletionItemTag]
    )
    object TagSupport
        extends codecs.structures_CompletionClientCapabilities_CompletionItem_TagSupportCodec

    /** @param properties
      *   The properties that a client can resolve lazily.
      */
    case class ResolveSupport(
        properties: Vector[String]
    )
    object ResolveSupport
        extends codecs.structures_CompletionClientCapabilities_CompletionItem_ResolveSupportCodec
    case class InsertTextModeSupport(
        valueSet: Vector[enumerations.InsertTextMode]
    )
    object InsertTextModeSupport
        extends codecs.structures_CompletionClientCapabilities_CompletionItem_InsertTextModeSupportCodec
  end CompletionItem

  /** @param valueSet
    *   The completion item kind values the client supports. When this property
    *   exists the client also guarantees that it will handle values outside its
    *   set gracefully and falls back to a default value when unknown.
    *
    * If this property is not present the client only supports the completion
    * items kinds from `Text` to `Reference` as defined in the initial version
    * of the protocol.
    */
  case class CompletionItemKind(
      valueSet: Option[Vector[enumerations.CompletionItemKind]] = None
  )
  object CompletionItemKind
      extends codecs.structures_CompletionClientCapabilities_CompletionItemKindCodec

  /** @param itemDefaults
    *   The client supports the following itemDefaults on a completion list.
    *
    * The value lists the supported property names of the
    * `CompletionList.itemDefaults` object. If omitted no properties are
    * supported.
    *
    * since 3.17.0
    */
  case class CompletionList(
      itemDefaults: Option[Vector[String]] = None
  )
  object CompletionList
      extends codecs.structures_CompletionClientCapabilities_CompletionListCodec
end CompletionClientCapabilities
