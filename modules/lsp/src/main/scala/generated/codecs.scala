// format:off
package langoustine.lsp
package codecs
import io.circe.{Decoder, Encoder}
import aliases.*
import enumerations.*
import structures.*
import runtime.{*, given}

private[lsp] trait requests_completionItem_resolve:
  import requests.completionItem.resolve.{In, Out}
  given inputFromJson: Decoder[In] = 
    structures.CompletionItem.fromJson
  
  given inputToJson: Encoder[In] = 
    structures.CompletionItem.toJson
  
  given outputToJson: Encoder[Out] =
    structures.CompletionItem.toJson
  
  given outputFromJson: Decoder[Out] =
    structures.CompletionItem.fromJson

private[lsp] trait structures_CommandCodec:
  import structures.*
  given fromJson: Decoder[Command] = 
    Dec.fromJsonObject: dec =>
      for
        title <- dec.get("title", Decoder.decodeString)
        command <- dec.get("command", Decoder.decodeString)
        arguments <- dec.getOpt("arguments", Decoder.decodeVector(Decoder.decodeJson))
      yield Command(
        title,
        command,
        arguments,
      )
  given toJson: Encoder[Command] = 
    Enc.toJsonObject: (enc, a) =>
      enc.field("title", a.title, Encoder.encodeString)
      enc.field("command", a.command, Encoder.encodeString)
      a.arguments.foreach: v =>
        enc.field("arguments", v, Encoder.encodeVector(Encoder.encodeJson))


private[lsp] trait structures_CompletionItemCodec:
  import structures.*
  given fromJson: Decoder[CompletionItem] = 
    Dec.fromJsonObject: dec =>
      for
        label <- dec.get("label", Decoder.decodeString)
        labelDetails <- dec.getOpt("labelDetails", structures.CompletionItemLabelDetails.fromJson)
        kind <- dec.getOpt("kind", enumerations.CompletionItemKind.fromJson)
        tags <- dec.getOpt("tags", Decoder.decodeVector(enumerations.CompletionItemTag.fromJson))
        detail <- dec.getOpt("detail", Decoder.decodeString)
        documentation <- dec.getOpt("documentation", Dec.merge[(String | structures.MarkupContent)](Decoder.decodeString, structures.MarkupContent.fromJson))
        deprecated <- dec.getOpt("deprecated", Decoder.decodeBoolean)
        preselect <- dec.getOpt("preselect", Decoder.decodeBoolean)
        sortText <- dec.getOpt("sortText", Decoder.decodeString)
        filterText <- dec.getOpt("filterText", Decoder.decodeString)
        insertText <- dec.getOpt("insertText", Decoder.decodeString)
        insertTextFormat <- dec.getOpt("insertTextFormat", enumerations.InsertTextFormat.fromJson)
        insertTextMode <- dec.getOpt("insertTextMode", enumerations.InsertTextMode.fromJson)
        textEdit <- dec.getOpt("textEdit", Dec.merge[(structures.TextEdit | structures.InsertReplaceEdit)](structures.TextEdit.fromJson, structures.InsertReplaceEdit.fromJson))
        textEditText <- dec.getOpt("textEditText", Decoder.decodeString)
        additionalTextEdits <- dec.getOpt("additionalTextEdits", Decoder.decodeVector(structures.TextEdit.fromJson))
        commitCharacters <- dec.getOpt("commitCharacters", Decoder.decodeVector(Decoder.decodeString))
        command <- dec.getOpt("command", structures.Command.fromJson)
        data <- dec.getOpt("data", Decoder.decodeJson)
      yield CompletionItem(
        label,
        labelDetails,
        kind,
        tags,
        detail,
        documentation,
        deprecated,
        preselect,
        sortText,
        filterText,
        insertText,
        insertTextFormat,
        insertTextMode,
        textEdit,
        textEditText,
        additionalTextEdits,
        commitCharacters,
        command,
        data,
      )
  given toJson: Encoder[CompletionItem] = 
    Enc.toJsonObject: (enc, a) =>
      enc.field("label", a.label, Encoder.encodeString)
      a.labelDetails.foreach: v =>
        enc.field("labelDetails", v, structures.CompletionItemLabelDetails.toJson)
      a.kind.foreach: v =>
        enc.field("kind", v, enumerations.CompletionItemKind.toJson)
      a.tags.foreach: v =>
        enc.field("tags", v, Encoder.encodeVector(enumerations.CompletionItemTag.toJson))
      a.detail.foreach: v =>
        enc.field("detail", v, Encoder.encodeString)
      a.documentation.foreach: v =>
        enc.union2[String, structures.MarkupContent]("documentation", v, Encoder.encodeString, structures.MarkupContent.toJson)
      a.deprecated.foreach: v =>
        enc.field("deprecated", v, Encoder.encodeBoolean)
      a.preselect.foreach: v =>
        enc.field("preselect", v, Encoder.encodeBoolean)
      a.sortText.foreach: v =>
        enc.field("sortText", v, Encoder.encodeString)
      a.filterText.foreach: v =>
        enc.field("filterText", v, Encoder.encodeString)
      a.insertText.foreach: v =>
        enc.field("insertText", v, Encoder.encodeString)
      a.insertTextFormat.foreach: v =>
        enc.field("insertTextFormat", v, enumerations.InsertTextFormat.toJson)
      a.insertTextMode.foreach: v =>
        enc.field("insertTextMode", v, enumerations.InsertTextMode.toJson)
      a.textEdit.foreach: v =>
        enc.union2[structures.TextEdit, structures.InsertReplaceEdit]("textEdit", v, structures.TextEdit.toJson, structures.InsertReplaceEdit.toJson)
      a.textEditText.foreach: v =>
        enc.field("textEditText", v, Encoder.encodeString)
      a.additionalTextEdits.foreach: v =>
        enc.field("additionalTextEdits", v, Encoder.encodeVector(structures.TextEdit.toJson))
      a.commitCharacters.foreach: v =>
        enc.field("commitCharacters", v, Encoder.encodeVector(Encoder.encodeString))
      a.command.foreach: v =>
        enc.field("command", v, structures.Command.toJson)
      a.data.foreach: v =>
        enc.field("data", v, Encoder.encodeJson)


private[lsp] trait structures_CompletionItemLabelDetailsCodec:
  import structures.*
  given fromJson: Decoder[CompletionItemLabelDetails] = 
    Dec.fromJsonObject: dec =>
      for
        detail <- dec.getOpt("detail", Decoder.decodeString)
        description <- dec.getOpt("description", Decoder.decodeString)
      yield CompletionItemLabelDetails(
        detail,
        description,
      )
  given toJson: Encoder[CompletionItemLabelDetails] = 
    Enc.toJsonObject: (enc, a) =>
      a.detail.foreach: v =>
        enc.field("detail", v, Encoder.encodeString)
      a.description.foreach: v =>
        enc.field("description", v, Encoder.encodeString)


private[lsp] trait structures_InlayHintCodec:
  import structures.*
  given fromJson: Decoder[InlayHint] = 
    Dec.fromJsonObject: dec =>
      for
        position <- dec.get("position", structures.Position.fromJson)
        label <- dec.get("label", Dec.merge[(String | Vector[structures.InlayHintLabelPart])](Decoder.decodeString, Decoder.decodeVector(structures.InlayHintLabelPart.fromJson)))
        kind <- dec.getOpt("kind", enumerations.InlayHintKind.fromJson)
        textEdits <- dec.getOpt("textEdits", Decoder.decodeVector(structures.TextEdit.fromJson))
        tooltip <- dec.getOpt("tooltip", Dec.merge[(String | structures.MarkupContent)](Decoder.decodeString, structures.MarkupContent.fromJson))
        paddingLeft <- dec.getOpt("paddingLeft", Decoder.decodeBoolean)
        paddingRight <- dec.getOpt("paddingRight", Decoder.decodeBoolean)
        data <- dec.getOpt("data", Decoder.decodeJson)
      yield InlayHint(
        position,
        label,
        kind,
        textEdits,
        tooltip,
        paddingLeft,
        paddingRight,
        data,
      )
  given toJson: Encoder[InlayHint] = 
    Enc.toJsonObject: (enc, a) =>
      enc.field("position", a.position, structures.Position.toJson)
      enc.union2[String, Vector[structures.InlayHintLabelPart]]("label", a.label, Encoder.encodeString, Encoder.encodeVector(structures.InlayHintLabelPart.toJson))
      a.kind.foreach: v =>
        enc.field("kind", v, enumerations.InlayHintKind.toJson)
      a.textEdits.foreach: v =>
        enc.field("textEdits", v, Encoder.encodeVector(structures.TextEdit.toJson))
      a.tooltip.foreach: v =>
        enc.union2[String, structures.MarkupContent]("tooltip", v, Encoder.encodeString, structures.MarkupContent.toJson)
      a.paddingLeft.foreach: v =>
        enc.field("paddingLeft", v, Encoder.encodeBoolean)
      a.paddingRight.foreach: v =>
        enc.field("paddingRight", v, Encoder.encodeBoolean)
      a.data.foreach: v =>
        enc.field("data", v, Encoder.encodeJson)


private[lsp] trait structures_InlayHintLabelPartCodec:
  import structures.*
  given fromJson: Decoder[InlayHintLabelPart] = 
    Dec.fromJsonObject: dec =>
      for
        value <- dec.get("value", Decoder.decodeString)
        tooltip <- dec.getOpt("tooltip", Dec.merge[(String | structures.MarkupContent)](Decoder.decodeString, structures.MarkupContent.fromJson))
        location <- dec.getOpt("location", structures.Location.fromJson)
        command <- dec.getOpt("command", structures.Command.fromJson)
      yield InlayHintLabelPart(
        value,
        tooltip,
        location,
        command,
      )
  given toJson: Encoder[InlayHintLabelPart] = 
    Enc.toJsonObject: (enc, a) =>
      enc.field("value", a.value, Encoder.encodeString)
      a.tooltip.foreach: v =>
        enc.union2[String, structures.MarkupContent]("tooltip", v, Encoder.encodeString, structures.MarkupContent.toJson)
      a.location.foreach: v =>
        enc.field("location", v, structures.Location.toJson)
      a.command.foreach: v =>
        enc.field("command", v, structures.Command.toJson)


private[lsp] trait structures_InsertReplaceEditCodec:
  import structures.*
  given fromJson: Decoder[InsertReplaceEdit] = 
    Dec.fromJsonObject: dec =>
      for
        newText <- dec.get("newText", Decoder.decodeString)
        insert <- dec.get("insert", structures.Range.fromJson)
        replace <- dec.get("replace", structures.Range.fromJson)
      yield InsertReplaceEdit(
        newText,
        insert,
        replace,
      )
  given toJson: Encoder[InsertReplaceEdit] = 
    Enc.toJsonObject: (enc, a) =>
      enc.field("newText", a.newText, Encoder.encodeString)
      enc.field("insert", a.insert, structures.Range.toJson)
      enc.field("replace", a.replace, structures.Range.toJson)


private[lsp] trait structures_LocationCodec:
  import structures.*
  given fromJson: Decoder[Location] = 
    Dec.fromJsonObject: dec =>
      for
        uri <- dec.get("uri", DocumentUri.fromJson)
        range <- dec.get("range", structures.Range.fromJson)
      yield Location(
        uri,
        range,
      )
  given toJson: Encoder[Location] = 
    Enc.toJsonObject: (enc, a) =>
      enc.field("uri", a.uri, DocumentUri.toJson)
      enc.field("range", a.range, structures.Range.toJson)


private[lsp] trait structures_MarkupContentCodec:
  import structures.*
  given fromJson: Decoder[MarkupContent] = 
    Dec.fromJsonObject: dec =>
      for
        kind <- dec.get("kind", enumerations.MarkupKind.fromJson)
        value <- dec.get("value", Decoder.decodeString)
      yield MarkupContent(
        kind,
        value,
      )
  given toJson: Encoder[MarkupContent] = 
    Enc.toJsonObject: (enc, a) =>
      enc.field("kind", a.kind, enumerations.MarkupKind.toJson)
      enc.field("value", a.value, Encoder.encodeString)


private[lsp] trait structures_PositionCodec:
  import structures.*
  given fromJson: Decoder[Position] = 
    Dec.fromJsonObject: dec =>
      for
        line <- dec.get("line", uinteger.fromJson)
        character <- dec.get("character", uinteger.fromJson)
      yield Position(
        line,
        character,
      )
  given toJson: Encoder[Position] = 
    Enc.toJsonObject: (enc, a) =>
      enc.field("line", a.line, uinteger.toJson)
      enc.field("character", a.character, uinteger.toJson)


private[lsp] trait structures_RangeCodec:
  import structures.*
  given fromJson: Decoder[Range] = 
    Dec.fromJsonObject: dec =>
      for
        start <- dec.get("start", structures.Position.fromJson)
        `end` <- dec.get("end", structures.Position.fromJson)
      yield Range(
        start,
        `end`,
      )
  given toJson: Encoder[Range] = 
    Enc.toJsonObject: (enc, a) =>
      enc.field("start", a.start, structures.Position.toJson)
      enc.field("end", a.end, structures.Position.toJson)


private[lsp] trait structures_SemanticTokensCodec:
  import structures.*
  given fromJson: Decoder[SemanticTokens] = 
    Dec.fromJsonObject: dec =>
      for
        resultId <- dec.getOpt("resultId", Decoder.decodeString)
        data <- dec.get("data", Decoder.decodeVector(uinteger.fromJson))
      yield SemanticTokens(
        resultId,
        data,
      )
  given toJson: Encoder[SemanticTokens] = 
    Enc.toJsonObject: (enc, a) =>
      a.resultId.foreach: v =>
        enc.field("resultId", v, Encoder.encodeString)
      enc.field("data", a.data, Encoder.encodeVector(uinteger.toJson))


private[lsp] trait structures_SemanticTokensLegendCodec:
  import structures.*
  given fromJson: Decoder[SemanticTokensLegend] = 
    Dec.fromJsonObject: dec =>
      for
        tokenTypes <- dec.get("tokenTypes", Decoder.decodeVector(Decoder.decodeString))
        tokenModifiers <- dec.get("tokenModifiers", Decoder.decodeVector(Decoder.decodeString))
      yield SemanticTokensLegend(
        tokenTypes,
        tokenModifiers,
      )
  given toJson: Encoder[SemanticTokensLegend] = 
    Enc.toJsonObject: (enc, a) =>
      enc.field("tokenTypes", a.tokenTypes, Encoder.encodeVector(Encoder.encodeString))
      enc.field("tokenModifiers", a.tokenModifiers, Encoder.encodeVector(Encoder.encodeString))


private[lsp] trait structures_TextEditCodec:
  import structures.*
  given fromJson: Decoder[TextEdit] = 
    Dec.fromJsonObject: dec =>
      for
        range <- dec.get("range", structures.Range.fromJson)
        newText <- dec.get("newText", Decoder.decodeString)
      yield TextEdit(
        range,
        newText,
      )
  given toJson: Encoder[TextEdit] = 
    Enc.toJsonObject: (enc, a) =>
      enc.field("range", a.range, structures.Range.toJson)
      enc.field("newText", a.newText, Encoder.encodeString)


private[lsp] trait structures_WorkspaceFolderCodec:
  import structures.*
  given fromJson: Decoder[WorkspaceFolder] = 
    Dec.fromJsonObject: dec =>
      for
        uri <- dec.get("uri", Uri.fromJson)
        name <- dec.get("name", Decoder.decodeString)
      yield WorkspaceFolder(
        uri,
        name,
      )
  given toJson: Encoder[WorkspaceFolder] = 
    Enc.toJsonObject: (enc, a) =>
      enc.field("uri", a.uri, Uri.toJson)
      enc.field("name", a.name, Encoder.encodeString)

