/**
  * '''leancloud''': Leancloud RESTful api binding for scalajs in static-type.
  *
  * This package, [[xyz.ariwaranosai.leancloud]], contains:
  *  - request class for data CRUD in leancloud.
  *  - class for build-in type for json object returned by leancloud.
  *  - related exception
  */

package xyz.ariwaranosai

package object leancloud {
  val API_URL = "https://api.leancloud.cn"
  val API_VERSION = 1.1

  type Url = String
  type Method = RequestMethod
  type RequestHeader = Map[String, String]
}


