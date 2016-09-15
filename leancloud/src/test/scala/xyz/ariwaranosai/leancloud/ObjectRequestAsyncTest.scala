package xyz.ariwaranosai.leancloud

import io.circe.generic.JsonCodec
import utest._
import xyz.ariwaranosai.leancloud.LeanRequest.{ObjectCreateRequest, ObjectGetRequest}
import io.circe.syntax._
import xyz.ariwaranosai.leancloud.LeanModel.LeanResults

import scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.concurrent.Future

/**
  * Created by ariwaranosai on 16/9/13.
  *
  */

object ObjectRequestAsyncTest extends TestSuite {
  @JsonCodec case class kancolle(name: String, id: Int)
  val data = kancolle("Murasame", 81).asJson.noSpaces

  val tests = this {
    'ObjectCreateAndGet{
      (for {
        response <- ObjectCreateRequest("kancolle").run(data)
        kan <- ObjectGetRequest("kancolle", response.objectId).get[LeanResults[kancolle]]("")
        _ <- Future { assert(kan.results.exists(_.map(_.name).contains("Murasame")))}
      } yield ()).onFailure {
        case x => throw new Exception()
      }
    }
  }

}