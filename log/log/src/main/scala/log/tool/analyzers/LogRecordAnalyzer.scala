package log.tool.analyzers

import log.tool.LogAnalysisContext
import log.tool.LogRecord

trait LogRecordAnalyzer {
  def analyze(record: LogRecord, context: LogAnalysisContext): LogAnalysisContext
}
