package xyz.ariwaranosai.leancloud

import xyz.ariwaranosai.leancloud.CClass.{ObjectRequest, UserRequest}
import xyz.ariwaranosai.leancloud.Command.{DataChangeCommand, DataCommand}
import xyz.ariwaranosai.leancloud.LeanModel.{UserCreateResponse, UserModel}
import xyz.ariwaranosai.leancloud.RequestMethod.POST
import xyz.ariwaranosai.leancloud.LeanModel.LeanModelImplicit._

import scala.concurrent.Future

/**
  * Created by ariwaranosai on 16/10/12.
  *
  */

object LeanCloudUser {

  object UserCreateRequest extends DataChangeCommand[UserCreateResponse]("", "")
    with UserRequest with RequestHeaderBuilder {
    override val method: Method = POST
    override implicit def string2T(s: String):Future[UserCreateResponse] =
      createModel[UserCreateResponse](s)
  }
}
