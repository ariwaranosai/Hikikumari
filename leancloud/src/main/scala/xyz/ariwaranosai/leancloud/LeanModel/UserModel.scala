package xyz.ariwaranosai.leancloud.LeanModel

/**
  * Created by sai on 2016/9/28.
  */
case class UserModel(
                      username: String, password: String,
                      phone: Option[String], mobilePhoneVerified: Boolean = false,
                      email: Option[String], emailVerified: Boolean = false,
                      sessionToken: Option[String], salt: Option[String]
                    )
