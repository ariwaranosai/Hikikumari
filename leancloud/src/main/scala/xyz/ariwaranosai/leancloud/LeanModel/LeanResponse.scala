package xyz.ariwaranosai.leancloud.LeanModel

import io.circe.generic.JsonCodec
import xyz.ariwaranosai.leancloud.LeanInternalException

/**
  * Error message model.
  */

case class LeanErrorResponse(code: Int, error: String) {
  def isSuccess: Boolean = code > 399
  def throwException: Exception = LeanInternalException(code, error)
}

@JsonCodec case class CreateResponse(createdAt: String, objectId: String)

@JsonCodec case class UpdateResponse(updatedAt: String)
