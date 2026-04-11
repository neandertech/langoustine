package jsonrpclib

import weaver.*
import jsonrpclib.internals.*
import com.github.plokhotnyuk.jsoniter_scala.core.*
import jsonrpclib.CallId.NumberId
import jsonrpclib.OutputMessage.ResponseMessage

object RawMessageSpec extends FunSuite:
  test("json parsing with null result") {
    // This is a perfectly valid response object, as result field has to be present,
    // but can be null: https://www.jsonrpc.org/specification#response_object
    val rawMessage = readFromString[RawMessage](
      """ {"jsonrpc":"2.0","result":null,"id":3} """.trim
    )

    // This, on the other hand, is an invalid response message, as result field is missing
    val invalidRawMessage =
      readFromString[RawMessage](""" {"jsonrpc":"2.0","id":3} """.trim)

    expect.same(
      rawMessage,
      RawMessage(jsonrpc = "2.0", result = Some(None), id = Some(NumberId(3)))
    ) &&
    expect.same(
      rawMessage.toMessage,
      Right(ResponseMessage(NumberId(3), Payload.NullPayload))
    ) &&
    expect.same(
      invalidRawMessage,
      RawMessage(jsonrpc = "2.0", result = None, id = Some(NumberId(3)))
    ) &&
    expect(
      invalidRawMessage.toMessage.isLeft,
      invalidRawMessage.toMessage.toString
    )
  }
end RawMessageSpec
