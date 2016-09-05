package xyz.ariwaranosai.hwm

import chrome.tabs.bindings.TabQuery
import com.thoughtworks.binding._
import org.scalajs.dom.{Event, document}
import org.scalajs.dom.html.{Button, Div}

import scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js.JSApp

/**
  * Created by ariwaranosai on 16/9/5.
  *
  */

object HikikomoriMark extends JSApp {
  @dom
  def clickButton: Binding[Div] = {
    <div>
    <button onclick={event: Event =>
      chrome.tabs.Tabs.query(TabQuery()).foreach(tabs => {
        tabs.map(_.url.toString).filter(_.startsWith("http")).foreach(println(_))
      })}>click</button></div>
  }

  override def main(): Unit = {
    println("hello world")
    dom.render(document.body, clickButton)
  }
}
