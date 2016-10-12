package xyz.ariwaranosai.leancloud

import utest._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import LeanRequest.{ObjectCreateRequest, ObjectDeleteRequest, ObjectGetRequest, ObjectUpdateRequest}
import io.circe.generic.JsonCodec
import xyz.ariwaranosai.leancloud.RequestMethod.{DELETE, GET, POST, PUT}
import LeanCloudUser._

import scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.util.{Failure, Success}

/**
  * Created by ariwaranosai on 16/10/12.
  *
  */

object UserRequestTest extends TestSuite {

  val tests = this {
    'UserCreateRequestUrl {
      val request = UserCreateRequest
      assert(request.requestUrl  == "https://api.leancloud.cn/1.1/users")
    }
  }
}
