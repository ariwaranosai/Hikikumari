package xyz.ariwaranosai.hwm

import chrome.tabs.bindings.TabQuery
import com.thoughtworks.binding._
import org.scalajs.dom.{Event, document}
import org.scalajs.dom.html.{Button, Div}

import scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js.JSApp
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

import scala.util.{Failure, Success}


/**
  * Created by ariwaranosai on 16/9/5.
  *
  */

import leancloud.LeanRequest.LeanRequest.ObjectCreateRequest

object Data {
  case class kancolle(name: String, id: Int)
  val data = kancolle("haruna", 151).asJson.noSpaces
}

object HikikomoriMark extends JSApp {
  @dom
  def clickButton: Binding[Div] = {
    <div>
    <button onclick={event: Event =>
      ObjectCreateRequest("kancolle").run(Data.data)
      .onComplete {
        case Success(x) => println(x.objectId)
        case Failure(x) => println(x.toString)
      }
    }>click</button></div>
  }

  override def main(): Unit = {
    dom.render(document.body, clickButton)
  }
}
