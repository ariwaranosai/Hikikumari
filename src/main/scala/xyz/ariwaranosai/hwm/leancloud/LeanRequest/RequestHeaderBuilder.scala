package xyz.ariwaranosai.hwm.leancloud.LeanRequest

import xyz.ariwaranosai.hashes.Implicit.MD5Ops
import xyz.ariwaranosai.hwm.leancloud.{Config, _}

/**
  * Created by ariwaranosai on 16/9/6.
  *
  */

trait RequestHeaderBuilder extends LeanRequest {
  override def buildRequestHeaders(): RequestHeader = {
    Map[String, String](
      "X-LC-Id" -> Config.APP_ID,
      "X-LC-Key" -> Config.APP_KEY,
      "Content-Type" -> "application/json"
    )
  }
}


trait SignHeaderBuilder extends RequestHeaderBuilder {
  override def buildRequestHeaders(): RequestHeader = {
    val timestamp: String = (new scalajs.js.Date().getTime() / 1000).toInt.toString

    val sign:String = (timestamp + Config.APP_KEY).hex

    Map[String, String](
      "X-LC-Id" -> Config.APP_ID,
      "X-LC-Sign" -> (sign + "," + timestamp),
      "Content-Type" -> "application/json"
    )
  }

}
