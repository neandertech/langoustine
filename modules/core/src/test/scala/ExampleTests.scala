package com.indoorvivants.library

class ExampleTests extends munit.FunSuite {
  test("test1") {
    assert(implicitly[TypeClass[Int]].isPrimitive == true)
    assert(implicitly[TypeClass[Boolean]].isPrimitive == true)
  }
}
