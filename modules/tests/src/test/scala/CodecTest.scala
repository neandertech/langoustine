package tests.core

import upickle.default.*
import langoustine.lsp.*
import jsonrpclib.Monadic
import scala.util.*
import cats.MonadThrow

import langoustine.lsp.all.*
import cats.Monad

import jsonrpclib.*
import org.scalacheck.*

object CodecTest extends weaver.FunSuite, WeaverSnapshotsIntegration:

  given Arbitrary[String] = Arbitrary(Gen.alphaNumStr)

  requestSnapshotTest(langoustine.lsp.requests.textDocument.documentLink)
  // requestSnapshotTest(langoustine.lsp.requests.textDocument.documentSymbol)
  requestSnapshotTest(langoustine.lsp.requests.textDocument.foldingRange)
  requestSnapshotTest(langoustine.lsp.requests.workspace.configuration)
  requestSnapshotTest(langoustine.lsp.requests.textDocument.references)

  def requestSnapshotTest[T <: LSPRequest](x: T)(using
      arbReq: Arbitrary[x.In],
      arbResp: Arbitrary[x.Out]
  ) =
    def safeToString[T](x: T | Null) =
      x match
        case Opt.empty => "Opt.empty"
        case _         => x.toString()

    test(x.requestMethod + " request roundtrip") {

      val requests = List.fill(5)(arbReq.arbitrary.sample).flatten

      forEach(requests): request =>
        val requestRoundtrip =
          read[x.In](write[x.In](request))

        expect.same(requestRoundtrip, request)
    }
    test(x.requestMethod + " response roundtrip") {

      val requests = List.fill(5)(arbReq.arbitrary.sample).flatten

      forEach(requests): request =>
        val requestRoundtrip =
          read[x.In](write[x.In](request))
        expect.same(requestRoundtrip, request)
    }

    def sampleN[T](n: Int, arb: Arbitrary[T]) =
      Gen
        .listOfN(5, arb.arbitrary)
        .sample
        .toList
        .flatten

    test(x.requestMethod + " request snapshot") {
      val bld = StringBuilder()

      sampleN(5, arbReq)
        .foreach: request =>
          val requestJson = write[x.In](request)
          val printed     = safeToString(request)
          bld.append(
            printed + "\n" + requestJson + "\n" + "---------------------" + "\n"
          )
      expectSnapshot("request: " + x.requestMethod, bld.result())
    }

    test(x.requestMethod + " response snapshot") {
      val bld = StringBuilder()

      sampleN(5, arbResp)
        .foreach: response =>
          val requestJson = write[x.Out](response)
          val printed     = safeToString(response)
          bld.append(
            printed + "\n" + requestJson + "\n" + "---------------------" + "\n"
          )
      expectSnapshot("response: " + x.requestMethod, bld.result())

    }
  end requestSnapshotTest

end CodecTest
