package jsonrpclib

/** Errors that should not be sent back through the json rpc channel (such as invalid notifications)
  */
case class ErrorReport(method: String, payload: Payload, error: ProtocolError)
