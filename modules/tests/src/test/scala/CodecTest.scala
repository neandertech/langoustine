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

    def sampleN[T](n: Int, arb: Arbitrary[T]) =
      Gen
        .listOfN(5, arb.arbitrary)
        .sample
        .toList
        .flatten

    test(x.requestMethod + " request snapshot") {
      val bld      = StringBuilder()
      val requests = sampleN(5, arbReq).distinct

      forEach(requests.zipWithIndex): (request, idx) =>
        val json         = write(request)
        val printed      = safeToString(request)
        val snapshotName = s"request($idx): " + x.requestMethod

        expectSnapshot(snapshotName, json) &&
        assertSnapshotContents(
          snapshotName,
          contents => expect.same(read[x.In](contents), request)
        )
    }

    test(x.requestMethod + " response snapshot") {
      val bld      = StringBuilder()
      val requests = sampleN(5, arbResp).distinct

      forEach(requests.zipWithIndex): (response, idx) =>
        val json         = write(response)
        val printed      = safeToString(response)
        val snapshotName = s"response($idx): " + x.requestMethod

        expectSnapshot(snapshotName, json) &&
        assertSnapshotContents(
          snapshotName,
          contents => expect.same(read[x.Out](contents), response)
        )
    }
  end requestSnapshotTest

end CodecTest
