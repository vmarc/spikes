package log.tool

class LogParser {

  def parse(line: String): LogRecord = {

    val tokens = TokenSplitter.split(line)

    // val remote_addr = tokens.head
    // val dash = tokens(1)
    // val remote_user = tokens(2)
    val time_local = tokens(3)
    val request = tokens(4)
    val status = tokens(5)
    // val body_bytes_sent = tokens(6)
    // val http_referer = tokens(7)
    val http_user_agent = tokens(8)

    LogRecord(
      line,
      time_local, // TODO date
      time_local, // TODO time
      status,
      request, // path,
      http_user_agent
    )
  }
}
