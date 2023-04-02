package log.tool

case class LogRecord(
  line: String,
  remoteAddress: String,
  date: String,
  time: String,
  status: String,
  bodyBytesSent: String,
  request: String,
  userAgent: String
)