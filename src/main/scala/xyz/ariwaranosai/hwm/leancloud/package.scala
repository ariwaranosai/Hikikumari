package xyz.ariwaranosai.hwm
import xyz.ariwaranosai.hwm.leancloud.LeanRequest.LeanRequest._

/**
  * Created by sai on 2016/9/5.
  */
package object leancloud {
  val API_URL = "https://api.leancloud.cn"
  val API_VERSION = 1.1

  type Url = String
  type Method = RequestMethod
  type RequestHeader = Map[String, String]
}

abstract class RequestMethod(val cmd: String)

object RequestMethod {
  case object GET extends RequestMethod("GET")
  case object PUT extends RequestMethod("PUT")
  case object DELETE extends RequestMethod("DELETE")
  case object POST extends RequestMethod("POST")
}