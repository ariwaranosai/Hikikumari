package xyz.ariwaranosai.leancloud

import xyz.ariwaranosai.hashes.Implicit.HashOps
import utest._

/**
  * Created by sai on 2016/9/7.
  */

object RequestHeaderBuilderTest extends TestSuite {


  val tests = this{
    object TrivalBuilder extends TrivalRequest with RequestHeaderBuilder
    object HashBuilder extends TrivalRequest with SignHeaderBuilder

    'TrivalBuilder{
      assert(TrivalBuilder.buildRequestHeaders() == Map[String, String](
        "X-LC-Id" -> Config.APP_ID,
        "X-LC-Key" -> Config.APP_KEY,
        "Content-Type" -> "application/json"
      ))
    }
    'HashBuilder{
      val header = HashBuilder.buildRequestHeaders()
      val signAndTime = header("X-LC-Sign").split(",")
      val newSign = (signAndTime(1) + Config.APP_KEY).MD5().hex

      assert(signAndTime(0) == newSign)
    }
  }
}
