/**
  * Created by ariwaranosai on 16/9/5.
  *
  */

package xyz.ariwaranosai.hwm

import chrome.tabs.bindings.{TabCreateProperties, TabQuery}
import com.thoughtworks.binding.Binding.Var
import com.thoughtworks.binding._
import org.scalajs.dom.{Event, document}
import org.scalajs.dom.html.{Button, Div, Input}

import scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js.JSApp
import xyz.ariwaranosai.leancloud.LeanRequest.{ObjectCreateRequest, ObjectGetRequest}
import io.circe._
import io.circe.generic.JsonCodec
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import xyz.ariwaranosai.leancloud.{LeanInternalException, LeanJsonParserException}
import xyz.ariwaranosai.leancloud.LeanModel.LeanResults

import scala.concurrent.Future
import scala.util.{Failure, Success}




object HikikomoriMark extends JSApp {
  val objectId = Var("")

  @JsonCodec case class Tabs(urls: List[String])

  def inputHandler = { event: Event => objectId := event.currentTarget.asInstanceOf[Input].value }

  @dom
  def input: Binding[Div] =
    <div>
      <input oninput={inputHandler} type="text" value={objectId.bind}/>
      <button onclick={
              event: Event =>
                (for {
                  urls <- chrome.tabs.Tabs.query(TabQuery())
                  response <- ObjectCreateRequest("chrome").run(Tabs(urls.map(_.url.toString).toList).asJson.noSpaces)
                } yield response).onComplete {
                  case Success(x) => objectId := x.objectId
                  case Failure(x) => println("failed")
                }
              }>
      </button>
      <button onclick={
              event: Event => {
                val searchId = objectId.get
                ObjectGetRequest("chrome", searchId.toString).get[Tabs]().onComplete {
                  case Success(x) => x.urls.foreach(x => {
                    chrome.tabs.Tabs.create(TabCreateProperties(url = x))
                  })
                  case x => println(x.toString)
                }
              }}>
      </button>
    </div>

  override def main(): Unit = {
    dom.render(document.body, input)
  }
}
