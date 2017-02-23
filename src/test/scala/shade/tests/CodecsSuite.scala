/*
 * Copyright (c) 2012-2017 by its authors. Some rights reserved.
 * See the project homepage at: https://github.com/monix/shade
 *
 * Licensed under the MIT License (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy
 * of the License at:
 *
 * https://github.com/monix/shade/blob/master/LICENSE.txt
 */

package shade.tests

import org.scalacheck.Arbitrary
import org.scalatest.FunSuite
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import shade.memcached.{ Codec, MemcachedCodecs }

class CodecsSuite extends FunSuite with MemcachedCodecs with GeneratorDrivenPropertyChecks {

  /**
   * Properties-based checking for a codec of type A
   */
  private def serdesCheck[A: Arbitrary](codec: Codec[A]): Unit = {
    forAll { n: A =>
      val serialised = codec.serialize(n, 0)
      val deserialised = codec.deserialize(serialised, 0)
      assert(deserialised == n)
    }
  }

  test("IntBinaryCodec") {
    serdesCheck(IntBinaryCodec)
  }

  test("DoubleBinaryCodec") {
    serdesCheck(DoubleBinaryCodec)
  }

  test("FloatBinaryCodec") {
    serdesCheck(FloatBinaryCodec)
  }

  test("LongBinaryCodec") {
    serdesCheck(LongBinaryCodec)
  }

  test("BooleanBinaryCodec") {
    serdesCheck(BooleanBinaryCodec)
  }

  test("CharBinaryCodec") {
    serdesCheck(CharBinaryCodec)
  }

  test("ShortBinaryCodec") {
    serdesCheck(ShortBinaryCodec)
  }

  test("StringBinaryCodec") {
    serdesCheck(StringBinaryCodec)
  }

  test("ArrayByteBinaryCodec") {
    serdesCheck(ArrayByteBinaryCodec)
  }

}