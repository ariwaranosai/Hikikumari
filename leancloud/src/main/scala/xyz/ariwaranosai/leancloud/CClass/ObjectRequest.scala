package xyz.ariwaranosai.leancloud.CClass

import xyz.ariwaranosai.leancloud.LeanRequest


/**
  * Created by sai on 2016/9/7.
  */

trait ObjectRequest extends LeanRequest {
  override val cclass: String = "classes"
}
