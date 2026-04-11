package jsonrpclib

sealed abstract class ProtocolError(val code: Int, message: String) extends Throwable {
  override def getMessage(): String = message
}

object ProtocolError {
  final case class ParseError(message: String) extends ProtocolError(-32700, message)
  final case class InvalidRequest(message: String) extends ProtocolError(-32600, message)
  final case class MethodNotFound(method: String) extends ProtocolError(-32601, s"Method $method not found")
  final case class InvalidParams(message: String) extends ProtocolError(-32602, message)
  final case class InternalError(message: String) extends ProtocolError(-32603, message)
  final case class ServerError(override val code: Int, message: String) extends ProtocolError(code, message)
}
