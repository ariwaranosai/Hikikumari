package xyz.ariwaranosai.hwm.leancloud.LeanRequest.CClass

import xyz.ariwaranosai.hwm.leancloud.LeanRequest.LeanRequest

import scala.concurrent.Future
import scala.util.Try

/**
  * Created by sai on 2016/9/7.
  */

trait ObjectRequest extends LeanRequest {
  override val cclass: String = "classes"
}
