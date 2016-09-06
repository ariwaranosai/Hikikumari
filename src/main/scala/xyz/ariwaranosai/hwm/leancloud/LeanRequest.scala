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
  // ---------- design -------------- //

  val requestUrl: Url //需要拼装
  val requestType: Method //指定
  val data: String // 注入，dsl

  val requestHeaderBuilder: Map[String, String] //需要拼装

  def run(url: Url, method: Method, data: String, headers: RequestHeader) :Future[XMLHttpRequest] =
    Ajax(method.cmd, url, data, headers = headers, timeout = 0, withCredentials = false, responseType = "")
}
