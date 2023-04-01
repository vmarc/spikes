package log.tool.analyzers

import log.tool.LogAnalysisContext
import log.tool.LogRecord
import org.springframework.stereotype.Component

@Component
class RobotAnalyzer extends LogRecordAnalyzer {

  def analyze(record: LogRecord, context: LogAnalysisContext): LogAnalysisContext = {

    if (context.isRobot) {
      context.withRobot(context.recordAnalysis.deviceName)
    }
    else {
      context
    }
  }
}
