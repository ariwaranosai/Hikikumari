package xyz.ariwaranosai.leancloud.LeanModel

import xyz.ariwaranosai.leancloud.LeanInternalException

/**
  * Created by ariwaranosai on 16/9/8.
  *
  */

case class LeanResponse(code: Int, error: String) {
  def isSuccess: Boolean = code > 399
  def throwException: Exception = LeanInternalException(code, error)
}

case class CreateResponse(createdAt: String, objectId: String)
