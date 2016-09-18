package xyz.ariwaranosai.leancloud.CClass

import xyz.ariwaranosai.leancloud.LeanRequest

/**
  * Created by sai on 2016/9/18.
  */
trait CloudQueryRequest extends LeanRequest {
  override val cclass = "cloudQuery"
}
