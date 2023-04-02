package log.tool

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class LogParserTest extends AnyFunSuite with Matchers {

  test("parse log record") {
    val parser = new LogParser
    val record = parser.parse("""84.30.75.137 - - [30/Mar/2023:20:21:58 +0200] "GET /nl/favicon.ico HTTP/1.1" 200 24838 "https://knooppuntnet.nl/nl/analysis/overview" "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/111.0"""")
    record.date should equal("30/Mar/2023:20:21:58 +0200")
    record.time should equal("30/Mar/2023:20:21:58 +0200")
    record.status should equal("200")
    record.request should equal("GET /nl/favicon.ico HTTP/1.1")
    record.userAgent should equal("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/111.0")
  }
}
