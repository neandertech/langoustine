/*
 * Copyright 2022 Neandertech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
