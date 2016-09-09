package xyz.ariwaranosai.hwm

import chrome.tabs.bindings.TabQuery
import com.thoughtworks.binding._
import org.scalajs.dom.{Event, document}
import org.scalajs.dom.html.{Button, Div}

import scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js.JSApp
import xyz.ariwaranosai.leancloud.LeanRequest.{ObjectCreateRequest, ObjectGetRequest}
import io.circe._
import io.circe.generic.JsonCodec
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import xyz.ariwaranosai.leancloud.LeanJsonParserException

import scala.concurrent.Future
import scala.util.{Failure, Success}


/**
  * Created by ariwaranosai on 16/9/5.
  *
  */



object HikikomoriMark extends JSApp {
  @JsonCodec case class kancolle(name: String, id: Int)
  val data = kancolle("haruna", 151).asJson.noSpaces

  @dom
  def clickButton: Binding[Div] = {
    <div>
    <button onclick={event: Event =>
    ObjectCreateRequest("kancolle")
      .run(data)
      .onComplete {
        case Success(x) => println(x.objectId)
        case Failure(x) => println(x.toString)
      }

      ObjectGetRequest("kancolle", "57d199c4816dfa00543027f9")
          .get[kancolle]("").onComplete {
            case Success(x) => println(x.name)
            case Failure(x) => println(x.asInstanceOf[LeanJsonParserException].origin)
          }
    }>click</button>
    </div>
  }

  override def main(): Unit = {
    dom.render(document.body, clickButton)
  }
}
