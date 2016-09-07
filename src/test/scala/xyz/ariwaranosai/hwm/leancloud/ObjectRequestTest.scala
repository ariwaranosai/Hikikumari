package xyz.ariwaranosai.hwm.leancloud

import utest._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import xyz.ariwaranosai.hwm.leancloud.LeanRequest.LeanRequest.ObjectCreateRequest
import scalajs.concurrent.JSExecutionContext.Implicits.queue

/**
  * Created by sai on 2016/9/8.
  */

object ObjectRequestTest extends TestSuite {
  object Data {
    case class kancolle(name: String, id: Int)
    val data = kancolle("haruna", 151).asJson.noSpaces
  }

  val tests = this{
    'ObjectCreateRequest{
      val request = ObjectCreateRequest("kancolle")
      request.requestUrl == "https://api.leancloud.cn/1.1/classes/kancolle"
    }
    'ObjectCreateRun {
      val request = ObjectCreateRequest("kancolle").run(Data.data)
      request.onFailure {
        case x => throw new Exception
      }
    }
  }
}
