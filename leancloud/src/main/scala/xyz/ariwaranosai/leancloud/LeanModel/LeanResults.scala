package xyz.ariwaranosai.leancloud.LeanModel

import io.circe.generic.JsonCodec

/**
  * Created by sai on 2016/9/14.
  */

@JsonCodec case class LeanResults[T](results: Option[List[T]])

@JsonCodec case class NoneResult()