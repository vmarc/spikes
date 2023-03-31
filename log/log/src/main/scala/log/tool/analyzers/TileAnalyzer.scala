package log.tool.analyzers

import log.tool.LogAnalysisContext
import log.tool.LogRecord
import org.springframework.stereotype.Component

@Component
class TileAnalyzer extends LogRecordAnalyzer {

  def analyze(record: LogRecord, context: LogAnalysisContext): LogAnalysisContext = {
    if (record.path.startsWith("/tiles/")) {
      val key = if (context.recordAnalysis.robot) "tile-robot" else "tile"
      context.withValue(key).copy(recordAnalysis = context.recordAnalysis.copy(tile = true))
    }
    else {
      context
    }
  }

}
