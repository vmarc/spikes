package log.tool

case class LogRecord(
  line: String,
  date: String,
  time: String,
  status: String,
  path: String,
  userAgent: String
)