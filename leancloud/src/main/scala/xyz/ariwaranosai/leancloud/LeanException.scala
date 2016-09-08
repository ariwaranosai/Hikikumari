package xyz.ariwaranosai.leancloud

/**
  * Created by sai on 2016/9/7.
  */

abstract class LeanException extends Exception

case class LeanInternalException(errorCode: Int, errMsg: String) extends LeanException

case class LeanNetWorkException() extends LeanException

case class LeanJsonParserException(origin: String, errMsg: String) extends LeanException

