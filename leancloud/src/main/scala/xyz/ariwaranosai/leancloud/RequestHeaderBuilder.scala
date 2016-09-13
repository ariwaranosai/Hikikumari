package xyz.ariwaranosai.leancloud

import xyz.ariwaranosai.hashes.Implicit.MD5Ops

/**
  * RequestHeaderBuilder to build request headers.
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


/**
  * RequestBuilder with md5.
  */
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
