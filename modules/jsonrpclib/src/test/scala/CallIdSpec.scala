package jsonrpclib

import weaver.*
import com.github.plokhotnyuk.jsoniter_scala.core.*

object CallIdSpec extends FunSuite:
  test("json parsing") {
    val strJson = """ "25" """.trim

    val intJson = "25"

    val longJson = Long.MaxValue.toString

    val nullJson = "null"
    expect.same(readFromString[CallId](strJson), CallId.StringId("25")) &&
    expect.same(readFromString[CallId](intJson), CallId.NumberId(25)) &&
    expect.same(
      readFromString[CallId](longJson),
      CallId.NumberId(Long.MaxValue)
    ) &&
    expect.same(readFromString[CallId](nullJson), CallId.NullId)
  }
end CallIdSpec
