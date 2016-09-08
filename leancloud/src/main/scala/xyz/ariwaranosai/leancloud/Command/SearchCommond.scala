package xyz.ariwaranosai.leancloud.Command

import xyz.ariwaranosai.leancloud.LeanRequest


/**
  * Created by sai on 2016/9/7.
  */

abstract class SearchCommond(name: String) extends LeanRequest {
  override def commond: String = name
}
