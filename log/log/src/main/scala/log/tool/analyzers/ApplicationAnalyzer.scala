package log.tool.analyzers

import log.tool.LogAnalysisContext
import log.tool.LogRecord

object ApplicationAnalyzer extends LogRecordAnalyzer {

  def analyze(record: LogRecord, context: LogAnalysisContext): LogAnalysisContext = {
    if (record.path == "/" || record.path.endsWith(".js.map") || record.path.endsWith(".js") || record.path.endsWith(".css") || record.path.endsWith("ngsw.json")) {
      context.withValue("application").copy(recordAnalysis = context.recordAnalysis.copy(application = true))
    }
    else {
      context
    }
  }
}
