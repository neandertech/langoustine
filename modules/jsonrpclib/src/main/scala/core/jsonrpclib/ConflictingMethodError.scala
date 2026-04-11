package jsonrpclib

final case class ConflictingMethodError(name: String) extends Exception {
  override def getMessage(): String = s"Method $name is already handled"
}
