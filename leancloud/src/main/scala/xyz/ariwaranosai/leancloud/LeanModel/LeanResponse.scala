package xyz.ariwaranosai.leancloud.LeanModel

import xyz.ariwaranosai.leancloud.LeanInternalException

/**
  * Error message model.
  */

case class LeanErrorResponse(code: Int, error: String) {
  def isSuccess: Boolean = code > 399
  def throwException: Exception = LeanInternalException(code, error)
}

case class CreateResponse(createdAt: String, objectId: String)
