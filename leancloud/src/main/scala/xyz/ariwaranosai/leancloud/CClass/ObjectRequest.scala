package xyz.ariwaranosai.leancloud.CClass

import xyz.ariwaranosai.leancloud.LeanRequest


/** Request about control Object store in leancloud.
  *
  */

trait ObjectRequest extends LeanRequest {
  override val cclass: String = "classes"
}

trait UserRequest extends LeanRequest {
  override val cclass: String = "users"
}
