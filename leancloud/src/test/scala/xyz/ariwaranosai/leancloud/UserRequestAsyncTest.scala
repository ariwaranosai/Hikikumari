package xyz.ariwaranosai.leancloud

import xyz.ariwaranosai.leancloud.LeanCloudUser.UserCreateRequest
import scalajs.concurrent.JSExecutionContext.Implicits.queue
import utest._

import scala.concurrent.Future

/**
  * Created by sai on 2016/10/14.
  */

object UserRequestAsyncTest extends TestSuite {
  val name = "Amatsukaze"
  val password = "Yui Ogura"
  val tests = this {
    'UserCreateRequest - {
      for {
        response <- UserCreateRequest().createUser(name, password)
        _ <- Future { response.objectId.nonEmpty }
      } yield ()
    }
  }
}
