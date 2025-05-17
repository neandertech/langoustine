package tests.core

import upickle.default.*
import langoustine.lsp.*

import langoustine.lsp.all.*

import org.scalacheck.*
import org.scalacheck.rng.Seed

object CodecTest extends weaver.FunSuite, WeaverSnapshotsIntegration:

  given Arbitrary[String] = Arbitrary(Gen.const("randomString"))

  val SEED = 120312937L

  requestSnapshotTest(langoustine.lsp.requests.textDocument.documentLink)
  requestSnapshotTest(langoustine.lsp.requests.textDocument.foldingRange)
  requestSnapshotTest(langoustine.lsp.requests.workspace.configuration)
  requestSnapshotTest(langoustine.lsp.requests.textDocument.references)
  // requestSnapshotTest(langoustine.lsp.requests.textDocument.rename)

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
        .listOfN(n, arb.arbitrary)
        .pureApply(Gen.Parameters.default, Seed(SEED))

    test(x.requestMethod + " request snapshot") {
      val bld      = StringBuilder()
      val requests = sampleN(5, arbReq).distinct

      forEach(requests.zipWithIndex): (request, idx) =>
        val json         = write(request, indent = 2)
        val printed      = safeToString(request)
        val snapshotName = x.requestMethod + s": request($idx)"

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
        val json         = write(response, indent = 2)
        val printed      = safeToString(response)
        val snapshotName = x.requestMethod + s": response($idx)"

        expectSnapshot(snapshotName, json) &&
        assertSnapshotContents(
          snapshotName,
          contents => expect.same(read[x.Out](contents), response)
        )
    }
  end requestSnapshotTest

end CodecTest
