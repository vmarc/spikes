package log.tool.analyzers

import log.tool.LogAnalysisContext
import log.tool.LogRecord
import org.springframework.stereotype.Component

@Component
class ApiAnalyzer extends LogRecordAnalyzer {

  def analyze(record: LogRecord, context: LogAnalysisContext): LogAnalysisContext = {
    if (context.recordAnalysis.requestType == "api") {
      val session = record.remoteAddress + " " + record.userAgent
      context.withApi(session, record.time + " " + record.request)
    }
    else {
      context
    }
  }
}
