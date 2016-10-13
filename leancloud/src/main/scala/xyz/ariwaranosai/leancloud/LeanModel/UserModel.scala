package xyz.ariwaranosai.leancloud.LeanModel

import io.circe.generic.JsonCodec

/**
  * Created by sai on 2016/9/28.
  */
@JsonCodec case class LeanUser(
                      username: String, password: String,
                      phone: Option[String] = None, mobilePhoneVerified: Option[Boolean] = None,
                      email: Option[String] = None, emailVerified: Option[Boolean] = None,
                      sessionToken: Option[String] = None, salt: Option[String] = None
                    )


@JsonCodec case class UserCreateResponse(
                         sessionToken: String,
                         createdAt: String,
                         objectId: String
                         )