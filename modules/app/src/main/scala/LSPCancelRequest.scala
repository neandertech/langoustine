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
