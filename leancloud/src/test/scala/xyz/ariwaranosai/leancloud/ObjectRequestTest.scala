package xyz.ariwaranosai.leancloud

import utest._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import LeanRequest.{ObjectCreateRequest, ObjectGetRequest, ObjectUpdateRequest}
import io.circe.generic.JsonCodec
import xyz.ariwaranosai.leancloud.RequestMethod.{GET, POST, PUT}

import scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.util.{Failure, Success}

/**
  * Created by sai on 2016/9/8.
  */

object ObjectRequestTest extends TestSuite {


  val tests = this {

    'ObjectCreateRequestUrl {
      val request = ObjectCreateRequest("kancolle")
      assert(request.method == POST)
      assert(request.requestUrl == "https://api.leancloud.cn/1.1/classes/kancolle")
    }

    'ObjectGetRequestUrl {
      val request = ObjectGetRequest("kancolle", "57d199c4816dfa00543027f9")
      assert(request.method == GET)
      assert(request.requestUrl == "https://api.leancloud.cn/1.1/classes/kancolle/57d199c4816dfa00543027f9")
    }

    'ObjectUpdateRequestUrl {
      val request = ObjectUpdateRequest("kancolle", "57dad190128fe10064b8bd16")
      assert(request.method == PUT)
      assert(request.requestUrl == "https://api.leancloud.cn/1.1/classes/kancolle/57dad190128fe10064b8bd16")
    }

  }

}
