package xyz.ariwaranosai.leancloud.Command

import xyz.ariwaranosai.leancloud.LeanRequest

import scala.concurrent.Future

/**
  * Created by sai on 2016/9/7.
  */

abstract class DataCommand(name: String, data: String) extends LeanRequest {
  override def commond: String = if(data.length > 0) s"$name/" else s"$name/$data"
}

abstract class DataChangeCommand[T](name: String, data: String) extends DataCommand(name, data) {
  def string2T(s: String): Future[T]
  def run(data: String): Future[T] = super.run[T](data)(string2T)
}
