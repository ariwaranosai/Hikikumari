package xyz.ariwaranosai.hwm.leancloud.LeanRequest.Command

import xyz.ariwaranosai.hwm.leancloud.LeanRequest.LeanRequest

/**
  * Created by sai on 2016/9/7.
  */

abstract class SearchCommond(name: String) extends LeanRequest {
  override def commond: String = name
}
