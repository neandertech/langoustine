// format:off
package langoustine.lsp
package requests

import langoustine.*
import upickle.default.*
import io.circe.{Decoder, Encoder}
import runtime.{*, given}
import structures.*

sealed abstract class LSPRequest(val requestMethod: String):
  type In
  type Out

  given inputFromJson: Decoder[In]
  given inputToJson: Encoder[In]
  given outputToJson: Encoder[Out]
  given outputFromJson: Decoder[Out]

  def apply(in: In): PreparedRequest[this.type] = PreparedRequest(this,in)

abstract class CustomRequest[I, O](method: String)(using ifr: Decoder[I], ito: Encoder[I], ofr: Decoder[O], oto: Encoder[O]) extends LSPRequest(method):
   override type In = I
   override type Out = O

   override given inputFromJson: Decoder[In] = ifr

   override given inputToJson: Encoder[In] = ito

   override given outputToJson: Encoder[Out] = oto

   override given outputFromJson: Decoder[Out] = ofr

abstract class CustomNotification[I](method: String)(using ifr: Decoder[I], ito: Encoder[I]) extends LSPNotification(method):
   override type In = I

   override given inputFromJson: Decoder[In] = ifr

   override given inputToJson: Encoder[In] = ito

sealed abstract class LSPNotification(val notificationMethod: String):
  type In

  given inputFromJson: Decoder[In]
  given inputToJson: Encoder[In]

  def apply(in: In): PreparedNotification[this.type] = PreparedNotification(this,in)

object completionItem:
  /**
   *  Request to resolve additional information for a given completion item.The request's
   *  parameter is of type {@link CompletionItem} the response
   *  is of type {@link CompletionItem} or a Thenable that resolves to such.
   */
  object resolve extends LSPRequest("completionItem/resolve") with codecs.requests_completionItem_resolve:
    type In = structures.CompletionItem
    type Out = structures.CompletionItem
    
    override def apply(in: structures.CompletionItem): PreparedRequest[this.type] = super.apply(in)
  
