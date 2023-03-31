package log.tool.analyzers

import log.tool.LogAnalysisContext
import log.tool.LogRecord

object AssetAnalyzer extends LogRecordAnalyzer {

  def analyze(record: LogRecord, context: LogAnalysisContext): LogAnalysisContext = {
    if (record.path.contains("/assets/") || record.path.endsWith("favicon.ico")) {
      val key = if (context.recordAnalysis.robot) "asset-robot" else "asset"
      context.withValue(key).copy(recordAnalysis = context.recordAnalysis.copy(asset = true))
    }
    else {
      context
    }
  }

}
