package log.tool.analyzers

import log.tool.LogAnalysisContext
import log.tool.LogRecord
import org.springframework.stereotype.Component

@Component
class ApiAnalyzer extends LogRecordAnalyzer {

  def analyze(record: LogRecord, context: LogAnalysisContext): LogAnalysisContext = {
    if (record.path.startsWith("/api/")) {
      val key = if (context.recordAnalysis.robot) "api-robot" else "api"
      context.withValue(key).copy(recordAnalysis = context.recordAnalysis.copy(api = true))
    }
    else {
      context
    }
  }
}
