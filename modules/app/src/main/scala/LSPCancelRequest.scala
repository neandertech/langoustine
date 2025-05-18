package langoustine.lsp.app

import jsonrpclib.CallId
import jsonrpclib.Codec
import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import jsonrpclib.fs2.CancelTemplate

private case class LSPCancelRequest(id: CallId)

object LSPCancelRequest:
  import com.github.plokhotnyuk.jsoniter_scala.macros.*
  given JsonValueCodec[LSPCancelRequest] = JsonCodecMaker.make[LSPCancelRequest]
  given Codec[LSPCancelRequest]          = Codec.fromJsonCodec

  val cancelTemplate: CancelTemplate = CancelTemplate
    .make[LSPCancelRequest](
      "$/cancelRequest",
      _.id,
      LSPCancelRequest(_)
    )
end LSPCancelRequest
