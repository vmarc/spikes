package log.tool

case class LogRecord(
  date: String,
  time: String,
  status: String,
  path: String,
  userAgent: String
) {
  def timestamp: String = date + " " + time

  def key: String = timestamp.substring(0, "YYYY-MM-DD HH:M".length) + "0"


}
