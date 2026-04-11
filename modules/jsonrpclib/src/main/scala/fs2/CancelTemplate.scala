package jsonrpclib.fs2

import jsonrpclib.Codec
import jsonrpclib.CallId

/** A cancelation template that represents the RPC method by which cancelation
  *
  * @param template
  *   : the notification template for the cancelation method
  * @param fromCallId
  *   : a function to create a cancelation request out of a call id
  * @param toCallId
  *   : a function to extract a call id from a cancelation request
  */
trait CancelTemplate {
  type C
  def method: String
  def codec: Codec[C]
  def fromCallId(callId: CallId): C
  def toCallId(cancelRequest: C): CallId

}

object CancelTemplate {

  def make[CancelRequest: Codec](
      cancelMethod: String,
      toId: CancelRequest => CallId,
      fromId: CallId => CancelRequest
  ): CancelTemplate =
    new CancelTemplate {
      type C = CancelRequest

      def method: String = cancelMethod

      def codec: Codec[CancelRequest] = implicitly[Codec[CancelRequest]]

      def fromCallId(callId: CallId): CancelRequest = fromId(callId)

      def toCallId(cancelRequest: CancelRequest): CallId = toId(cancelRequest)
    }

}
