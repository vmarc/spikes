package log.tool.analyzers

import log.tool.LogAnalysisContext
import log.tool.LogRecord
import org.springframework.stereotype.Component

@Component
class HackerAnalyzer extends LogRecordAnalyzer {

  def analyze(record: LogRecord, context: LogAnalysisContext): LogAnalysisContext = {

    if (context.isHacker && record.status != "302" /*Moved Temporarily*/ && record.status != "400" /* Bad Request */ ) {
      val acceptedHackerRequests = context.acceptedHackerRequests :+ record.line
      context.copy(acceptedHackerRequests = acceptedHackerRequests)
    }
    else {
      context
    }
  }
}
