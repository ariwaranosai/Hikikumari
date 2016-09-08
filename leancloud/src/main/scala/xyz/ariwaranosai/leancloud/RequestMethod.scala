package xyz.ariwaranosai.leancloud

/**
  * Created by ariwaranosai on 16/9/8.
  *
  */

abstract class RequestMethod(val cmd: String)

object RequestMethod {
  case object GET extends RequestMethod("GET")
  case object PUT extends RequestMethod("PUT")
  case object DELETE extends RequestMethod("DELETE")
  case object POST extends RequestMethod("POST")
}
