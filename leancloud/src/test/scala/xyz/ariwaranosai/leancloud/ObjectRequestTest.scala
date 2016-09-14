package xyz.ariwaranosai.leancloud

import utest._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import LeanRequest.{ObjectCreateRequest, ObjectGetRequest}
import io.circe.generic.JsonCodec
import xyz.ariwaranosai.leancloud.RequestMethod.POST
import scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.util.{Failure, Success}

/**
  * Created by sai on 2016/9/8.
  */

object ObjectRequestTest extends TestSuite {

  @JsonCodec case class kancolle(name: String, id: Int)

  val data = kancolle("Murasame", 81).asJson.noSpaces

  val tests = this {

    'ObjectCreateRequestUrl {
      val request = ObjectCreateRequest("kancolle")
      assert(request.method == POST)
      assert(request.requestUrl == "https://api.leancloud.cn/1.1/classes/kancolle")
    }

    'ObjectCreateRun {
      ObjectCreateRequest("kancolle").run(data)
        .onComplete {
          case Success(x) => println(x.objectId)
          case Failure(x) => println(x.toString)
        }
    }

    'ObjectGetRequestUrl {
      val request = ObjectGetRequest("kancolle", "57d199c4816dfa00543027f9")
      assert(request.requestUrl == "https://api.leancloud.cn/1.1/classes/kancolle/57d199c4816dfa00543027f9")
    }

    'ObjectGetRequestRun {
      ObjectGetRequest("kancolle", "57d199c4816dfa00543027f9")
        .get[kancolle]("").onComplete {
        case Success(x) => println(x.results.isEmpty)
        case Failure(x) => println(x.asInstanceOf[LeanJsonParserException].origin)
      }
    }

  }

}
