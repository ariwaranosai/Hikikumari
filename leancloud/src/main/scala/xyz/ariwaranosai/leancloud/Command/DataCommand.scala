package xyz.ariwaranosai.leancloud.Command

import xyz.ariwaranosai.leancloud._

import scala.concurrent.Future

/**
  * Created by sai on 2016/9/7.
  */

abstract class DataCommand(name: String, data: String) extends LeanRequest {
  override def command: String = if(data.isEmpty) s"$name/" else s"$name/$data"
}

abstract class DataChangeCommand[T](name: String, data: String) extends DataCommand(name, data) {
  implicit def string2T(s: String): Future[T]
  def run(data: String): Future[T] = run[T](data)(string2T)
}
