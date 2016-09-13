package xyz.ariwaranosai.leancloud.Command

import xyz.ariwaranosai.leancloud.LeanRequest


/** Command to search data
  *
  * @param name class name or id of data to search
  */
abstract class SearchCommand(name: String) extends LeanRequest {
  override def command: String = name
}
