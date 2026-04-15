package langoustine.generate

import langoustine.meta.*, Type.*, Types.*

def circeDecoderReference(
    t: Type,
    refTypeOverride: ReferenceType => Option[String] = _ => None
)(using context: Context): String =
  import Type.*, BaseTypes.*
  t match
    case BaseType(BaseTypes.string)      => "Decoder.decodeString"
    case BaseType(BaseTypes.boolean)     => "Decoder.decodeBoolean"
    case BaseType(BaseTypes.uinteger)    => "uinteger.fromJson"
    case BaseType(BaseTypes.integer)     => "Decoder.decodeInt"
    case BaseType(BaseTypes.Uri)         => "Uri.fromJson"
    case BaseType(BaseTypes.DocumentUri) => "DocumentUri.fromJson"
    case BaseType(BaseTypes.decimal)     => "Decoder.decodeFloat"
    case BaseType(BaseTypes.NULL)        => "Decoder.const[Null](null)"
    case TupleType(items)                =>
      s"Decoder.decodeTuple${items.length}(${items.map(circeDecoderReference(_, refTypeOverride)).mkString(", ")})"

    case StringLiteralType(value) =>
      s"Decoder.decodeLiteralString[\"$value\"]"
    case ReferenceType(tn) if tn.value == "LSPAny" =>
      s"Decoder.decodeJson"
    case rt: ReferenceType =>
      refTypeOverride(rt).getOrElse(
        s"${context.resolve(rt).value}.fromJson"
      )
    case MapType(
          BaseType(BaseTypes.string),
          ReferenceType(TypeName("LSPAny"))
        ) =>
      s"Decoder.decodeMap(KeyDecoder.decodeKeyString, Decoder.decodeJson)"
    case MapType(rt: ReferenceType, tn) =>
      s"Decoder.decodeMap(KeyDecoder.decodeKeyString.map(${context.resolve(rt).value}.apply), ${circeDecoderReference(tn, refTypeOverride)})"
    case MapType(BaseType(BaseTypes.DocumentUri), tn) =>
      s"Decoder.decodeMap(KeyDecoder.decodeKeyString.map(runtime.DocumentUri.apply), ${circeDecoderReference(tn, refTypeOverride)})"
    case OrType(ts) =>
      val (isOption, simplified) =
        val (nullType, rest) =
          ts.partition(_ == BaseType(BaseTypes.NULL))
        (nullType.nonEmpty, rest)

      val len         = simplified.length
      val decoders    = simplified.map(circeDecoderReference(_, refTypeOverride))
      val types       = simplified.map(renderType(_)).mkString(", ")
      val decodersStr =
        if decoders.tail.nonEmpty then decoders.tail.mkString(",", ", ", "")
        else ""

      s"Dec.union$len[$types](${decoders.head}$decodersStr)"

    case ArrayType(tpe) =>
      s"Decoder.decodeVector(${circeDecoderReference(tpe, refTypeOverride)})"
    case _ => s"??? /*$t*/"
  end match
end circeDecoderReference


