package langoustine.generate

import langoustine.meta.*, Type.*, Types.*

def circeEncoderReference(
    t: Type,
    refTypeOverride: ReferenceType => Option[String] = _ => None
)(using context: Context): String =
  t match
    case BaseType(BaseTypes.string)      => "Encoder.encodeString"
    case BaseType(BaseTypes.boolean)     => "Encoder.encodeBoolean"
    case BaseType(BaseTypes.uinteger)    => "uinteger.toJson"
    case BaseType(BaseTypes.integer)     => "Encoder.encodeInt"
    case BaseType(BaseTypes.decimal)     => "Encoder.encodeFloat"
    case BaseType(BaseTypes.Uri)         => "Uri.toJson"
    case BaseType(BaseTypes.DocumentUri) => "DocumentUri.toJson"
    case BaseType(BaseTypes.NULL) => "Encoder.instance[Null](_ => Json.Null)"
    case TupleType(items)         =>
      s"Encoder.encodeTuple${items.length}(${items.map(circeEncoderReference(_, refTypeOverride)).mkString(", ")})"

    case StringLiteralType(value) =>
      s"Encoder.encodeLiteralString[\"$value\"]"
    case ReferenceType(tn) if tn.value == "LSPAny" =>
      s"Encoder.encodeJson"
    case rt: ReferenceType =>
      refTypeOverride(rt).getOrElse(
        s"${context.resolve(rt).value}.toJson"
      )
    case OrType(ts) =>
      val (isOption, simplified) =
        val (nullType, rest) =
          ts.partition(_ == BaseType(BaseTypes.NULL))
        (nullType.nonEmpty, rest)

      val len         = simplified.length
      val encoders    = simplified.map(circeEncoderReference(_, refTypeOverride))
      val types       = simplified.map(renderType(_)).mkString(", ")
      val encodersStr =
        if encoders.tail.nonEmpty then encoders.tail.mkString(",", ", ", "")
        else ""

      s"Enc.union$len[$types](${encoders.head}$encodersStr)"
    case MapType(
          BaseType(BaseTypes.string),
          ReferenceType(TypeName("LSPAny"))
        ) =>
      s"Encoder.encodeMap(KeyEncoder.encodeKeyString, Encoder.encodeJson)"
    case MapType(rt: ReferenceType, tn) =>
      s"Encoder.encodeMap(KeyEncoder.encodeKeyString.contramap(_.value), ${circeEncoderReference(tn, refTypeOverride)})"
    case MapType(BaseType(BaseTypes.DocumentUri), tn) =>
      s"Encoder.encodeMap(KeyEncoder.encodeKeyString.contramap(_.value), ${circeEncoderReference(tn, refTypeOverride)})"

    case ArrayType(tpe) =>
      s"Encoder.encodeVector(${circeEncoderReference(tpe, refTypeOverride)})"
    case _ => s"??? /*$t*/"
  end match
end circeEncoderReference
