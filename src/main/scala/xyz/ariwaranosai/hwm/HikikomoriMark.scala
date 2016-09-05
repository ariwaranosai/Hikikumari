package xyz.ariwaranosai.hwm

import chrome.app.runtime.bindings.LaunchData
import chrome.app.window.Window
import chrome.app.window.bindings.CreateWindowOptions
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import org.scalajs.dom
import utils.ChromeApp

/**
  * Created by ariwaranosai on 16/9/5.
  *
  */

class HikikomoriMark extends ChromeApp {
  override def onLaunched(launchData: LaunchData): Unit = {
    val options = CreateWindowOptions(id="MainWindow")

    Window.create("assets/html/App.html", options).foreach {
      window => window.contentWindow.document.body
    }
  }

}
