import weaver.*
import cats.effect.*

import jsonrpclib.*
import java.util.Base64
import langoustine.tracer.RawMessage

object ProtocolSpec extends SimpleIOSuite:

  pureTest(
    "confirm no changes for https://github.com/neandertech/jsonrpclib/issues/16"
  ) {
    // There is a bug in jsonrpclib - we keep these tests around to make sure
    // the bug is still there.
    // Once the bug disappears, it might be a good opportunity to remove any code that was
    // put as a prevention mechanism
    val regStr1 =
      """{"jsonrpc":"2.0","id":"1","method":"workspace/configuration","params":{"items":[{"section":"metals"}]}}"""

    val regStr2 =
      """{"id":"1","result":[{"serverVersion":"latest.snapshot","showImplicitArguments":true,"showInferredType":true,""" +
        """"excludedPackages":["akka.actor.typed.javadsl","com.github.swagger.akka.javadsl","akka.stream.javadsl"],"superMethodLensesEnabled":true}],"jsonrpc":"2.0"}"""

    expect(
      Codec
        .decode[RawMessage](Some(Payload(regStr1.getBytes)))
        .isLeft
    ) and
      expect(
        Codec
          .decode[RawMessage](Some(Payload(regStr2.getBytes)))
          .isLeft
      )

  }
end ProtocolSpec
