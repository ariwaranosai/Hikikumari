package xyz.ariwaranosai.leancloud

import org.scalajs.dom.ext.Ajax

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

/**
  * Created by ariwaranosai on 16/9/6.
  *
  */

abstract class LeanRequest {

  def requestUrl: Url = {
    s"$API_URL/${API_VERSION.toString}/$cclass/$command".stripSuffix("/")
  }

  def command: String = ""
  def cclass: String = ""

  val method: Method

  def buildRequestHeaders(): RequestHeader

  def run[T](data: String)(implicit f: String => Future[T]) :Future[T] = {
    Ajax(method.cmd, requestUrl, data,
      headers = buildRequestHeaders(),
      timeout = 0, withCredentials = false, responseType = "")
        .flatMap(x => f(x.responseText))
  }
}

trait TrivalRequest extends LeanRequest {
  override def requestUrl: Url = ""
  override val method: Method = RequestMethod.GET
}


object LeanRequest {
  import xyz.ariwaranosai.leancloud.Command._
  import xyz.ariwaranosai.leancloud.LeanModel._
  import xyz.ariwaranosai.leancloud.RequestMethod._
  import xyz.ariwaranosai.leancloud.CClass._
  import xyz.ariwaranosai.leancloud.LeanModel.LeanModelImplicit._

  class ObjectCreateRequest(className: String)
    extends DataChangeCommand[CreateResponse](className, "")
      with ObjectRequest with RequestHeaderBuilder {
    override val method: Method = POST
    override implicit def string2T(s: String): Future[CreateResponse] = createResponse(s)
  }

  object ObjectCreateRequest {
    def apply(className: String): ObjectCreateRequest = new ObjectCreateRequest(className)
  }
}
