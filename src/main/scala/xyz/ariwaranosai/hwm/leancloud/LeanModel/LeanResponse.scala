package xyz.ariwaranosai.hwm.leancloud.LeanModel

import xyz.ariwaranosai.hwm.leancloud.{LeanException, LeanInternalException}

/**
  * Created by sai on 2016/9/7.
  */

case class LeanResponse(code: Int, error: String) {
  def isSuccess: Boolean = code > 399
  def throwException: Exception = LeanInternalException(code, error)
}

case class CreateResponse(createdAt: String, objectId: String)
