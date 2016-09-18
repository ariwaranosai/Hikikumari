package xyz.ariwaranosai.leancloud.Command

import xyz.ariwaranosai.leancloud.LeanRequest

/**
  * Created by sai on 2016/9/18.
  */

/** A Trival Command means requests do not need parameters
  *
  */
abstract class TrivalCommand extends LeanRequest {

  /** do not need parameters
    *
    * @return
    */
  override def command: String = ""
}
