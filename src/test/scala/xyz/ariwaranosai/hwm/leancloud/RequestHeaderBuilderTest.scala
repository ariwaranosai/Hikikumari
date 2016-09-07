package xyz.ariwaranosai.hwm.leancloud

import org.scalatest.{FlatSpec, Matchers}
import xyz.ariwaranosai.hashes.Implicit.HashOps

/**
  * Created by sai on 2016/9/7.
  */

class RequestHeaderBuilderTest extends FlatSpec with Matchers {
  object TrivalBuilder extends RequestHeaderBuilder
  object HashBuilder extends SignHeaderBuilder

  "RequestBuilder" must "work" in {
    TrivalBuilder.build() shouldBe Map[String, String](
      "X-LC-Id" -> Config.APP_ID,
      "X-LC-Key" -> Config.APP_KEY,
      "Content-Type" -> "application/json"
    )
  }

  "SignHeaderBuilder" must "calc right hash" in {
    val header = HashBuilder.build()
    val signAndTime = header("X-LC-Sign").split(",")
    val newSign = (signAndTime(1) + Config.APP_KEY).MD5().hex()

    signAndTime(0) shouldBe newSign

  }
}
