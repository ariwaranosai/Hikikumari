package xyz.ariwaranosai.leancloud

/**
  * LeanException
  */

abstract class LeanException extends Exception

case class LeanInternalException(errorCode: Int, errMsg: String) extends LeanException

case class LeanNetWorkException() extends LeanException

case class LeanJsonParserException(origin: String, errMsg: String) extends LeanException

