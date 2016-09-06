package xyz.ariwaranosai.hwm.leancloud

/**
  * Created by ariwaranosai on 16/9/6.
  *
  */

trait RequestHeaderBuilder {
  def build(): RequestHeader = {
    Map[String, String](
      "X-LC-Id" -> Config.API_ID,
      "X-LC-Key" -> Config.API_KEY,
      "Content-Type" -> "application/json"
    )
  }
}


trait SignHeaderBuilder extends RequestHeaderBuilder {
}
