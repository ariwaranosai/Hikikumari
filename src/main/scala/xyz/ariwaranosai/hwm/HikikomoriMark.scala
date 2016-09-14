/**
  * Created by ariwaranosai on 16/9/5.
  *
  */

package xyz.ariwaranosai.hwm

import chrome.notifications.Notifications
import chrome.notifications.bindings.NotificationOptions
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

  @JsonCodec case class Tabs(urls: Option[List[String]])

  def inputHandler = { event: Event => objectId := event.currentTarget.asInstanceOf[Input].value }

  @dom
  def input: Binding[Input] =
      <input type="text" oninput={inputHandler} class="text-input" id="token" placeholder="Enter Token" value={objectId.bind} style="width: calc(100% - 20px)"/>

  @dom
  def buttons: Binding[Div] =
    <div class="card" data:z="1">
      <button class="button raised bg-blue-500 color-white" onclick={
              event: Event =>
                (for {
                  urls <- chrome.tabs.Tabs.query(TabQuery())
                  response <- ObjectCreateRequest("chrome").run(Tabs(Some(urls.map(_.url.toString).toList)).asJson.noSpaces)
                } yield response).onComplete {
                  case Success(x) => objectId := x.objectId
                  case Failure(x) => println("failed")
                }
              }> Save
      </button>
      <button class="button raised bg-green-500 color-white" style="margin-left: 25px;" onclick={
              event: Event => {
                val searchId = objectId.get
                ObjectGetRequest("chrome", searchId.toString).get[Tabs]().onComplete {
                  case Success(x) => x.urls match {
                    case Some(urls) => urls.foreach(url =>chrome.tabs.Tabs.create(TabCreateProperties(url = url)))
                    case None => {
                      objectId := "Error Token"
                    }
                  }
                  case Failure(x) => println(x.toString)
                }
              }}> Load
      </button>
    </div>

  @dom
  def mDiv: Binding[Div] =
    <div class="card-container">
      <div class="toolbar header bg-blue-500 color-white">
        <label class="toolbar-label">Hikikumori</label>
      </div>
      <div class="card" data:z="1">
        {input.bind }
      </div>
      { buttons.bind }
    </div>

  override def main(): Unit = {
    dom.render(document.body, mDiv)
  }
}
