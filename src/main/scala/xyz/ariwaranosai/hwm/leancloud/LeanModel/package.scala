package xyz.ariwaranosai.hwm.leancloud

import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import cats.data.Xor._

import scala.concurrent.Future
import scalajs.concurrent.JSExecutionContext.Implicits.queue


/**
  * Created by sai on 2016/9/7.
  */
package object LeanModel {
  object LeanModelImplicit {
    implicit def idResponse(s: String): Future[String] = Future {s}

    implicit def createResponse(s: String): Future[CreateResponse] = Future {
      decode[CreateResponse](s) match {
        case Right(x) => x
        case Left(x) => throw LeanJsonParserException(s, x.toString)
      }
    }
  }
}
