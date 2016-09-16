package xyz.ariwaranosai.leancloud

import io.circe.generic.JsonCodec
import utest._
import xyz.ariwaranosai.leancloud.LeanRequest.{ObjectCreateRequest, ObjectDeleteRequest, ObjectGetRequest, ObjectUpdateRequest}
import io.circe.syntax._

import scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.concurrent.Future
import scala.util.{Failure, Success}

/**
  * Created by ariwaranosai on 16/9/13.
  *
  */

object ObjectRequestAsyncTest extends TestSuite {

  @JsonCodec case class kancolle(name: String, kid: String)

  val data = kancolle("Murasame", 81.toString)

  val tests = this {
    'ObjectCreateAndGet - {
      for {
        response <- ObjectCreateRequest("kancolle").run(data.asJson.noSpaces)
        kan <- ObjectGetRequest("kancolle", response.objectId).get[kancolle]("")
        _ <- Future {
          assert(kan.name == "Murasame")
        }
        _ <- {
          val newData = kancolle("Murasameka", "81b")
          ObjectUpdateRequest("kancolle", response.objectId)
            .run(newData.asJson.noSpaces)
        }
        nkan <- ObjectGetRequest("kancolle", response.objectId).get[kancolle]("")
        _ <- Future {
          assert(nkan.name == "Murasameka")
        }

      } yield ()
    }

    'ObjectCreateAndDelete - {
      for {
        response <- ObjectCreateRequest("kancolle").run(data.asJson.noSpaces)
        kan <- ObjectGetRequest("kancolle", response.objectId).get[kancolle]("")

        _ <- Future {
          assert(kan.name == "Murasame")
        }

        ans <- ObjectDeleteRequest("kancolle", response.objectId).run()

        _ <- Future {
          assert(ans.asJson.noSpaces == "{}")
        }
      } yield ()
    }
  }
}
