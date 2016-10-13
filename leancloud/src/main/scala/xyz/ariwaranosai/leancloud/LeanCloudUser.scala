package xyz.ariwaranosai.leancloud

import xyz.ariwaranosai.leancloud.CClass.{ObjectRequest, UserRequest}
import xyz.ariwaranosai.leancloud.Command.{DataChangeCommand, DataCommand}
import xyz.ariwaranosai.leancloud.LeanModel.{LeanUser, UserCreateResponse}
import xyz.ariwaranosai.leancloud.RequestMethod.POST
import xyz.ariwaranosai.leancloud.LeanModel.LeanModelImplicit._
import io.circe.syntax._

import scala.concurrent.Future

/**
  * Created by ariwaranosai on 16/10/12.
  *
  */

object LeanCloudUser {

  class UserCreateRequest extends DataChangeCommand[UserCreateResponse]("", "")
    with UserRequest with RequestHeaderBuilder {
    override val method: Method = POST
    override implicit def string2T(s: String):Future[UserCreateResponse] =
      createModel[UserCreateResponse](s)

    def createUser(name: String, password: Password) = {
      val user = LeanUser(name, password)
      run(user.asJson.noSpaces)
    }
  }

  object UserCreateRequest {
    def apply(): UserCreateRequest = new UserCreateRequest()
  }
}
