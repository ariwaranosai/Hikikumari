package xyz.ariwaranosai.hwm.leancloud
import xyz.ariwaranosai.hashes.Implicit.MD5Ops

/**
  * Created by ariwaranosai on 16/9/6.
  *
  */

trait RequestHeaderBuilder {
  def build(): RequestHeader = {
    Map[String, String](
      "X-LC-Id" -> Config.APP_ID,
      "X-LC-Key" -> Config.APP_KEY,
      "Content-Type" -> "application/json"
    )
  }
}


trait SignHeaderBuilder extends RequestHeaderBuilder {
  override def build(): RequestHeader = {
    val timestamp: String = (new scalajs.js.Date().getTime() / 1000).toInt.toString

    val sign:String = (timestamp + Config.APP_KEY).hex

    Map[String, String](
      "X-LC-Id" -> Config.APP_ID,
      "X-LC-Sign" -> (sign + "," + timestamp),
      "Content-Type" -> "application/json"
    )
  }

}
