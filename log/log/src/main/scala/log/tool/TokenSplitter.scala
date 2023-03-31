package log.tool

import scala.collection.mutable.ListBuffer

object TokenSplitter {

  def split(input: String): Seq[String] = {
    val tokens = ListBuffer[String]()
    var startPosition = 0
    var isInQuotes = false
    var isInBrackets = false
    var currentPosition = 0
    while (currentPosition < input.length) {
      if (!isInQuotes && input(currentPosition) == '[') {
        isInBrackets = true
        startPosition = currentPosition + 1
      }
      else if (!isInQuotes && input(currentPosition) == ']') {
        isInBrackets = false
        tokens += input.substring(startPosition, currentPosition)
        currentPosition = currentPosition + 1
        startPosition = currentPosition + 1
      }
      else if (input(currentPosition) == '\"' && isInQuotes) {
        tokens += input.substring(startPosition + 1, currentPosition)
        currentPosition += 1
        startPosition = currentPosition + 1
        isInQuotes = false
      }
      else if (input(currentPosition) == '\"' && !isInQuotes) {
        isInQuotes = true
      }
      else if ((input(currentPosition) == ' ') && !isInQuotes && !isInBrackets) {
        tokens += input.substring(startPosition, currentPosition)
        startPosition = currentPosition + 1
      }
      currentPosition += 1
    }
//    tokens += input.substring(startPosition, currentPosition)
    tokens.toSeq
  }
}
