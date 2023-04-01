package log.tool.analyzers

import log.tool.LogAnalysisContext
import log.tool.LogRecord
import org.springframework.stereotype.Component

@Component
class TileAnalyzer extends LogRecordAnalyzer {

  def analyze(record: LogRecord, context: LogAnalysisContext): LogAnalysisContext = {
    if (record.path.startsWith("GET /tiles/") || record.path.startsWith("GET /tiles-history/")) {
      context.withValue("tile")
    }
    else {
      context
    }
  }

}
