package langoustine.lsp

import langoustine.*

import upickle.default.*
import scala.reflect.TypeTest

object RuntimeBase:
  import langoustine.lsp.json.*

  opaque type DocumentUri = String
  object DocumentUri extends OpaqueString[DocumentUri]:
    given ReadWriter[DocumentUri] =
      stringCodec.asInstanceOf[ReadWriter[DocumentUri]]
    given TypeTest[Any, DocumentUri] with 
      def unapply(i: Any) = 
        if i.isInstanceOf[String] then Some(i.asInstanceOf[i.type & DocumentUri]) else None

  opaque type Uri = String
  object Uri extends OpaqueString[Uri]:
    given ReadWriter[Uri] = stringCodec.asInstanceOf[ReadWriter[Uri]]
    given TypeTest[Any, Uri] with 
      def unapply(i: Any) = 
        if i.isInstanceOf[String] then Some(i.asInstanceOf[i.type & Uri]) else None

  opaque type uinteger = Int
  object uinteger extends OpaqueInt[uinteger]:
    given ReadWriter[uinteger] = intCodec.asInstanceOf[ReadWriter[uinteger]]

    given TypeTest[Any, uinteger] with 
      def unapply(i: Any) = 
        if i.isInstanceOf[Int] then Some(i.asInstanceOf[i.type & uinteger]) else None

end RuntimeBase
