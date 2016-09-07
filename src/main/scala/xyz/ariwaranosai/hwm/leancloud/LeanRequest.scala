package xyz.ariwaranosai.hwm.leancloud

import org.scalajs.dom.XMLHttpRequest

import scala.concurrent.Future
import org.scalajs.dom.ext.Ajax
import xyz.ariwaranosai.hwm._

/**
  * Created by ariwaranosai on 16/9/6.
  *
  */

abstract class LeanRequest {
  val requestUrl: Url
  val method: Method
  val data: String

  val requestHeaderBuilder: RequestHeaderBuilder

  def run() :Future[XMLHttpRequest] =
    Ajax(method.cmd, requestUrl, data, headers = requestHeaderBuilder.build(), timeout = 0, withCredentials = false, responseType = "")
}
