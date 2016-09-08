package xyz.ariwaranosai.leancloud

import utest._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import LeanRequest.ObjectCreateRequest

import scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.concurrent.duration._
import scala.util.{Failure, Success}

/**
  * Created by sai on 2016/9/8.
  */

object ObjectRequestTest extends TestSuite {

  val tests = this{
    object Data {
      case class kancolle(name: String, id: Int)
      val data = kancolle("Murasame", 81).asJson.noSpaces
    }

    'ObjectCreateRequestUrl{
      val request = ObjectCreateRequest("kancolle")
      assert(request.requestUrl == "https://api.leancloud.cn/1.1/classes/kancolle")
    }
    'ObjectCreateRun {
      ObjectCreateRequest("kancolle").run(Data.data)
        .onComplete {
          case Success(x) => println(x.objectId)
          case Failure(x) => println(x.toString)
        }
    }
  }
}
