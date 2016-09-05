package xyz.ariwaranosai.hwm

import chrome.app.runtime.bindings.LaunchData
import chrome.app.window.Window
import chrome.app.window.bindings.CreateWindowOptions

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import org.scalajs.dom
import chrome.utils.ChromeApp

import scala.scalajs.js.JSApp

/**
  * Created by ariwaranosai on 16/9/5.
  *
  */

object HikikomoriMark extends JSApp {
  override def main(): Unit = {
    chrome.tabs.Tabs.onCreated.listen {
      tab => println(s"Tab created: ${tab.url}")
    }
  }
}
