package tests.core

import langoustine.lsp.runtime.*
import langoustine.lsp.Bijection
import langoustine.lsp.aliases.*
import langoustine.lsp.structures.*

import org.scalacheck.*
import shapeless3.deriving.*

given [A](using inst: => K0.ProductInstances[Arbitrary, A]): Arbitrary[A] =
  lazy val x: Gen[A] = Gen.delay(
    inst.construct([t] => (ma: Arbitrary[t]) => ma.arbitrary.sample.get)
  )

  Arbitrary(x)
end given

given optGen[A](using inst: => Arbitrary[A]): Arbitrary[Opt[A]] = Arbitrary(
  Gen
    .oneOf(true, false)
    .flatMap:
      case true  => inst.arbitrary.map(Opt.apply)
      case false => Gen.const(Opt.empty)
)

given Arbitrary[DocumentUri] = Arbitrary(
  Gen.oneOf("file1", "file2").map(DocumentUri.apply(_))
)

given Arbitrary[uinteger] = Arbitrary(Gen.posNum[Int].map(uinteger.apply))

given deriveStringEnum[A](using bi: Bijection[A, String]): Arbitrary[A] =
  Arbitrary(Gen.oneOf(bi.domain))

given deriveUintegerEnum[A](using bi: Bijection[A, uinteger]): Arbitrary[A] =
  Arbitrary(Gen.oneOf(bi.domain))

given deriveIntegerEnum[A](using bi: Bijection[A, Int]): Arbitrary[A] =
  Arbitrary(Gen.oneOf(bi.domain))

given Arbitrary[ProgressToken] =
  Arbitrary:
      for
        someString <- Gen.alphaNumStr.map(ProgressToken.apply)
        someInt    <- Arbitrary.arbitrary[Int].map(ProgressToken.apply)

        progressToken <- Gen.oneOf(someString, someInt)
      yield progressToken

given Arbitrary[ujson.Value] =
  import ujson.*
  Arbitrary(
    Gen.oneOf(
      Str("I'm json lol"),
      Arr(Str("yo")),
      Obj("a" -> Str("what"), "test" -> Obj("b" -> Arr(Num(1))))
    )
  )
end given

given Arbitrary[Vector[SymbolInformation] | Vector[DocumentSymbol]] =
  val l1 =
    Gen.listOfN(5, Arbitrary.arbitrary[SymbolInformation]).map(_.toVector)
  val l2 = Gen.listOfN(5, Arbitrary.arbitrary[DocumentSymbol]).map(_.toVector)
  val either: Gen[Vector[SymbolInformation] | Vector[DocumentSymbol]] =
    Gen.oneOf(l1, l2)
  Arbitrary(either)
