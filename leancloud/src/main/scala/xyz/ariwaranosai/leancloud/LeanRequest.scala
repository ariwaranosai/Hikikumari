package xyz.ariwaranosai.leancloud

import cats.data.Xor.{Left, Right}
import io.circe.parser._
import org.scalajs.dom.ext.Ajax

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import io.circe.{Decoder, Error}
import org.scalajs.dom.XMLHttpRequest
import xyz.ariwaranosai.leancloud.Command._
import xyz.ariwaranosai.leancloud.LeanModel._
import xyz.ariwaranosai.leancloud.RequestMethod._
import xyz.ariwaranosai.leancloud.CClass._
import xyz.ariwaranosai.leancloud.LeanModel.LeanModelImplicit._

/** LeanRequest is abstract class for all RESTful request
  *
  * You can find more detail about Leancloud RESTful api from leancloud documents.
  * @see [[https://leancloud.cn/docs/rest_api.html]].
  */

abstract class LeanRequest {

  /** build url request
    * @return request url
    */
  def requestUrl: Url = {
    s"$API_URL/${API_VERSION.toString}/$cclass/$command".stripSuffix("/")
  }

  def command: String = ""
  def cclass: String = ""

  val method: Method

  def buildRequestHeaders(): RequestHeader

  /** use run to get request result.
    *
    * To get result class, implicit function is needed.
    * most of time, this function is provided in this lib. But, user
    * will implement their function to get their own class.
    *
    * @param data data for request to send
    * @param f implicit function from result string to expected object
    * @tparam T type of excepted class
    * @return Future of excepted class
    */
  def run[T](data: String)(implicit f: String => Future[T]) :Future[T] = {
    val nf = (x: XMLHttpRequest) => f(x.responseText)
    raw[T](data)(nf)
  }

  /** Raw is internal function to implement get and run
    * It can also used to implement user-define hook
    *
    * @param data
    * @param f
    * @tparam T
    * @return
    */

  def raw[T](data: String)(implicit f: XMLHttpRequest => Future[T]): Future[T] = {
     Ajax(method.cmd, requestUrl, data,
      headers = buildRequestHeaders(),
      timeout = 0, withCredentials = false, responseType = "")
       .flatMap(x => f(x))
  }

  /** Get is another run with implicit decoder.
    *
    * You can implement your decoder follow Circe lib.
    * @see [[https://travisbrown.github.io/circe/]]
    *
    * @param data data for request to send.
    * @param decoder decoder to parser json
    * @tparam T type of excepted class
    * @return Future of excepted class
    */
  def get[T](data: String)(implicit decoder: Decoder[T]) :Future[T] = {
    val nf = (rep: XMLHttpRequest) => Future {
      parse(rep.responseText).flatMap(decoder.decodeJson) match {
        case Right(x) => x
        case Left(x) => throw LeanJsonParserException(rep.responseText, x.toString)
      }
    }
    raw[T](data)(nf)
  }
}

trait TrivalRequest extends LeanRequest {
  override def requestUrl: Url = ""
  override val method: Method = RequestMethod.GET
}


object LeanRequest {

  class ObjectCreateRequest(className: String)
    extends DataChangeCommand[CreateResponse](className, "")
      with ObjectRequest with RequestHeaderBuilder {
    override val method: Method = POST
    override implicit def string2T(s: String): Future[CreateResponse] = createResponse(s)
  }

  object ObjectCreateRequest {
    def apply(className: String): ObjectCreateRequest = new ObjectCreateRequest(className)
  }

  class ObjectGetRequest(className: String, objectId: String)
    extends DataCommand(className, objectId)
      with ObjectRequest with RequestHeaderBuilder {
    override val method: Method = GET
  }

  object ObjectGetRequest {
    def apply(className: String, objectId: String): ObjectGetRequest = new ObjectGetRequest(className, objectId)
  }
}
