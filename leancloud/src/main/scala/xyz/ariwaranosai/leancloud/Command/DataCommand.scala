package xyz.ariwaranosai.leancloud.Command

import xyz.ariwaranosai.leancloud._

import scala.concurrent.Future

/** A abstract command about input and output.
  *
  * A http call is used as RPC, parameters are name and data.
  * use run function to get result.
  */
abstract class DataCommand(name: String, data: String) extends LeanRequest {

  /** use parametes from class to get url encode
    *
    * @return the url of rpc command
    */
  override def command: String = if(data.isEmpty) s"$name/" else s"$name/$data"
}


/** Command aims to change data.
  *
  * @param name class name or id of data
  * @param data new data
  * @tparam T data type of reply
  */
abstract class DataChangeCommand[T](name: String, data: String) extends DataCommand(name, data) {
  implicit def string2T(s: String): Future[T]
  def run(data: String = ""): Future[T] = run[T](data)(string2T)
}
