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

import langoustine.lsp.runtime.*
import langoustine.lsp.Bijection
import langoustine.lsp.aliases.*
import langoustine.lsp.structures.*
import langoustine.lsp.enumerations.*

import org.scalacheck.*
import shapeless3.deriving.*

given Arbitrary[String] = Arbitrary(Gen.const("randomString"))

given [A](using inst: => K0.ProductInstances[Arbitrary, A]): Arbitrary[A] =
  val x: Gen[A] = Gen.delay(
    inst.constructA([t] => (ma: Arbitrary[t]) => ma.arbitrary)(
      [a] => (a: a) => Gen.const(a),
      [a, b] => (a: Gen[a], f: a => b) => a.map(f),
      [a, b] => (f: Gen[a => b], a: Gen[a]) => a.flatMap(a => f.map(_.apply(a)))
    )
  )

  Arbitrary.apply(x)
end given

given optionGen[A](using inst: => Arbitrary[A]): Arbitrary[Option[A]] =
  Arbitrary(
    Gen
      .oneOf(true, false)
      .flatMap:
        case true  => inst.arbitrary.map(Option.apply)
        case false => Gen.const(Option.empty)
  )
// given genMarkupContent: Arbitrary[MarkupContent] =
//   Arbitrary(
//     for
//       someString <- Gen.const("newText")
//       someKind   <- Gen.oneOf(MarkupKind.PlainText, MarkupKind.Markdown)
//     yield MarkupContent(someKind, someString)
//   )

val constStringGen: Arbitrary[String] = Arbitrary(Gen.const("my-string"))

given Arbitrary[String | MarkupContent] = genUnion2[String, MarkupContent]

given Arbitrary[DocumentUri] = Arbitrary(
  Gen.oneOf("file1", "file2").map(DocumentUri.apply(_))
)

given Arbitrary[Uri] = Arbitrary(
  Gen.oneOf("uri1", "uri2").map(Uri.apply(_))
)

given Arbitrary[uinteger] = Arbitrary(Gen.posNum[Int].map(uinteger.apply))

given deriveStringEnum[A](using bi: Bijection[A, String]): Arbitrary[A] =
  Arbitrary(Gen.oneOf(bi.domain))

given deriveUintegerEnum[A](using bi: Bijection[A, uinteger]): Arbitrary[A] =
  Arbitrary(Gen.oneOf(bi.domain))

given deriveIntegerEnum[A](using bi: Bijection[A, Int]): Arbitrary[A] =
  Arbitrary(Gen.oneOf(bi.domain))

given Arbitrary[io.circe.Json] = Arbitrary(
  Gen.const(io.circe.Json.obj("hello" -> io.circe.Json.fromString("yo")))
)

given genEdit: Arbitrary[TextEdit] =
  Arbitrary:
    for
      someString <- Gen.const("newText")
      someRange  <- Arbitrary.arbitrary[Range]
    yield TextEdit(someRange, someString)

given genInsertReplaceEdit: Arbitrary[InsertReplaceEdit] =
  Arbitrary:
    for
      someString     <- Gen.const("newText")
      someRange      <- Arbitrary.arbitrary[Range]
      someOtherRange <- Arbitrary.arbitrary[Range]
    yield InsertReplaceEdit(someString, someRange, someOtherRange)

given gen1: Arbitrary[
  langoustine.lsp.structures.TextDocumentEdit |
    langoustine.lsp.structures.CreateFile |
    langoustine.lsp.structures.RenameFile |
    langoustine.lsp.structures.DeleteFile
] =
  genUnion4[
    langoustine.lsp.structures.TextDocumentEdit,
    langoustine.lsp.structures.CreateFile,
    langoustine.lsp.structures.RenameFile,
    langoustine.lsp.structures.DeleteFile
  ]

def genUnion4[T1: Arbitrary, T2: Arbitrary, T3: Arbitrary, T4: Arbitrary]
    : Arbitrary[T1 | T2 | T3 | T4] =
  Arbitrary:
    for
      t1   <- Arbitrary.arbitrary[T1]
      t2   <- Arbitrary.arbitrary[T2]
      t3   <- Arbitrary.arbitrary[T3]
      t4   <- Arbitrary.arbitrary[T4]
      edit <- Gen.oneOf(t1, t2, t3, t4)
    yield edit

def genUnion2[T1: Arbitrary, T2: Arbitrary]: Arbitrary[T1 | T2] =
  Arbitrary:
    for
      t1   <- Arbitrary.arbitrary[T1]
      t2   <- Arbitrary.arbitrary[T2]
      edit <- Gen.oneOf(t1, t2)
    yield edit

given genEditUnion: Arbitrary[TextEdit | InsertReplaceEdit] =
  genUnion2[TextEdit, InsertReplaceEdit]

given genEditBla: Arbitrary[TextEdit | AnnotatedTextEdit] =
  genUnion2[TextEdit, AnnotatedTextEdit]

given [L <: String](using ValueOf[L]): Arbitrary[L] =
  Arbitrary(Gen.const(valueOf[L]))

given genVector[T](using Arbitrary[T]): Arbitrary[Vector[T]] =
  Arbitrary:
    for
      length <- Gen.chooseNum(0, 5)
      vector <- Gen.listOfN(length, Arbitrary.arbitrary[T])
    yield vector.toVector

given Arbitrary[ChangeAnnotationIdentifier] = Arbitrary(
  Arbitrary.arbitrary[String].map(ChangeAnnotationIdentifier.apply)
)

given genMap[K, V](using Arbitrary[K], Arbitrary[V]): Arbitrary[Map[K, V]] =
  Arbitrary:
    for
      length <- Gen.chooseNum(0, 5)
      keys   <- Gen.listOfN(length, Arbitrary.arbitrary[K])
      values <- Gen.listOfN(length, Arbitrary.arbitrary[V])
    yield keys.zip(values).toMap

// given Arbitrary[ProgressToken] =
//   Arbitrary:
//     for
//       someString <- Gen.const("stringToken").map(ProgressToken.apply)
//       someInt    <- Gen.const(25).map(ProgressToken.apply)

//       progressToken <- Gen.oneOf(someString, someInt)
//     yield progressToken

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

// given Arbitrary[Vector[SymbolInformation] | Vector[DocumentSymbol]] =
//   val l1 =
//     Gen.listOfN(5, Arbitrary.arbitrary[SymbolInformation]).map(_.toVector)
//   val l2 = Gen.listOfN(5, Arbitrary.arbitrary[DocumentSymbol]).map(_.toVector)
//   val either: Gen[Vector[SymbolInformation] | Vector[DocumentSymbol]] =
//     Gen.oneOf(l1, l2)
//   Arbitrary(either)

import io.github.irevive.union.derivation.{IsUnion, UnionDerivation}
import langoustine.lsp.enumerations.MarkupKind

inline given derivedUnion[A](using IsUnion[A]): Arbitrary[A] =
  UnionDerivation.derive[Arbitrary, A]
