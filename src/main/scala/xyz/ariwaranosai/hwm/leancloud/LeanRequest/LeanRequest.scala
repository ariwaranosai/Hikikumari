package xyz.ariwaranosai.hwm.leancloud.LeanRequest

import org.scalajs.dom.ext.Ajax
import xyz.ariwaranosai.hwm.RequestMethod
import xyz.ariwaranosai.hwm.RequestMethod.POST
import xyz.ariwaranosai.hwm.leancloud.LeanModel.{CreateResponse, LeanResponse}
import xyz.ariwaranosai.hwm.leancloud.LeanRequest.CClass.ObjectRequest
import xyz.ariwaranosai.hwm.leancloud.LeanRequest.Command.DataChangeCommand
import xyz.ariwaranosai.hwm.leancloud._

import scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

/**
  * Created by ariwaranosai on 16/9/6.
  *
  */

abstract class LeanRequest {

  def requestUrl: Url = {
    s"$API_URL/${API_VERSION.toString}/$cclass/$commond".stripSuffix("/")
  }

  def commond: String = ""
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
  class ObjectCreateRequest(className: String)
    extends DataChangeCommand[CreateResponse](className, "")
      with ObjectRequest with RequestHeaderBuilder {
    import LeanModel.LeanModelImplicit._
    override val method: Method = POST

    override def string2T(s: String): Future[CreateResponse] = createResponse(s)
  }

  object ObjectCreateRequest {
    def apply(className: String): ObjectCreateRequest = new ObjectCreateRequest(className)
  }
}
