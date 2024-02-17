package tests.core

import org.scalacheck.*
import shapeless3.deriving.*

import langoustine.lsp.runtime.*
import langoustine.lsp.Bijection
import langoustine.lsp.aliases.*

given [A](using inst: K0.ProductInstances[Arbitrary, A]): Arbitrary[A] =
  Arbitrary(
    inst.construct([t] => (ma: Arbitrary[t]) => ma.arbitrary.sample.get)
  )

given [A](using inst: Arbitrary[A]): Arbitrary[Opt[A]] = Arbitrary(
  inst.arbitrary.flatMap: value =>
    Gen.oneOf(Opt.empty, Opt(value))
)

given Arbitrary[DocumentUri] = Arbitrary(
  Gen.oneOf("file1", "file2").map(DocumentUri.apply(_))
)

given Arbitrary[uinteger] = Arbitrary(Gen.posNum[Int].map(uinteger.apply))

// given bla[T](using bi: Bijection[S, String]) = (Gen.oneOf("what"))

given deriveStringEnum[A](using bi: Bijection[A, String]): Arbitrary[A] =
  Arbitrary(Gen.oneOf(bi.domain))

given deriveUintegerEnum[A](using bi: Bijection[A, uinteger]): Arbitrary[A] =
  Arbitrary(Gen.oneOf(bi.domain))

given deriveIntegerEnum[A](using bi: Bijection[A, Int]): Arbitrary[A] =
  Arbitrary(Gen.oneOf(bi.domain))


given Arbitrary[ProgressToken] = 
  Arbitrary:
    for 
      someString <- Arbitrary.arbitrary[String].map(ProgressToken.apply)
      someInt <- Arbitrary.arbitrary[Int].map(ProgressToken.apply)

      progressToken <- Gen.oneOf(someString, someInt)
    yield progressToken

given Arbitrary[ujson.Value] = Arbitrary(ujson.Str("I'm json lol"))
