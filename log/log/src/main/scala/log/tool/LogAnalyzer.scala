package log.tool

import log.tool.analyzers.ApiAnalyzer
import log.tool.analyzers.HackerAnalyzer
import log.tool.analyzers.LogRecordAnalyzer
import log.tool.analyzers.RequestAnalyzer
import log.tool.analyzers.RobotAnalyzer
import log.tool.analyzers.UserAgentAnalyzer
import org.springframework.stereotype.Component

import scala.annotation.tailrec
import scala.io.Source

@Component
class LogAnalyzer(
  userAgentAnalyzer: UserAgentAnalyzer,
  robotAnalyzer: RobotAnalyzer,
  hackerAnalyzer: HackerAnalyzer,
  requestAnalyzer: RequestAnalyzer,
  apiAnalyzer: ApiAnalyzer
) {

  def analyze(filename: String): LogAnalysisContext = {
    val parser = new LogParser
    val source = Source.fromFile(filename)
    var context: LogAnalysisContext = LogAnalysisContext()
    source.getLines().foreach { line =>
      val record = parser.parse(line)
      context = analyze(record, context.copy(recordAnalysis = LogRecordAnalysis()))
    }
    source.close()
    context
  }

  private def analyze(record: LogRecord, context: LogAnalysisContext): LogAnalysisContext = {
    val analyzers = List(
      userAgentAnalyzer,
      robotAnalyzer,
      hackerAnalyzer,
      requestAnalyzer,
      apiAnalyzer
    )
    doAnalyze(analyzers, record, context)
  }

  @tailrec
  private def doAnalyze(analyzers: List[LogRecordAnalyzer], record: LogRecord, context: LogAnalysisContext): LogAnalysisContext = {
    if (analyzers.isEmpty) {
      context
    }
    else {
      val newContext = analyzers.head.analyze(record, context)
      doAnalyze(analyzers.tail, record, newContext)
    }
  }
}
