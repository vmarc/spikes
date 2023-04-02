package log.tool.analyzers

import log.tool.LogAnalysisContext
import log.tool.LogRecord
import org.springframework.stereotype.Component

import scala.util.matching.Regex

@Component
class RequestAnalyzer extends LogRecordAnalyzer {


  def analyze(record: LogRecord, context: LogAnalysisContext): LogAnalysisContext = {
    if (context.isRobot || context.isHacker || record.status == "302") {
      context
    }
    else {
      val requestType = if (isApplication(record)) {
        "application"
      }
      else if (isAnalysis(record)) {
        "analysis"
      }
      else if (isMonitor(record)) {
        "monitor"
      }
      else if (isApi(record)) {
        "api"
      }
      else if (isAsset(record)) {
        "asset"
      }
      else if (isTile(record)) {
        "tile"
      }
      else if (isHacker(record)) {
        "hacker"
      }
      else {
        "unknown"
      }

      val unknownRequests = if (requestType == "unknown") {
        context.unknownRequests :+ record.line
      }
      else {
        context.unknownRequests
      }

      val recordAnalysis = context.recordAnalysis.copy(requestType = requestType)
      context.withValue(requestType).copy(
        recordAnalysis = recordAnalysis,
        unknownRequests = unknownRequests
      )
    }
  }

  private def patternMatches(record: LogRecord, patterns: Seq[Regex]): Boolean = {
    patterns.exists(pattern => pattern.matches(record.request))
  }

  private def isApplication(record: LogRecord): Boolean = {
    patternMatches(record, RequestPatterns.application1) ||
      (patternMatches(record, RequestPatterns.application2) && record.bodyBytesSent != "2421")
  }

  private def isAnalysis(record: LogRecord): Boolean = {
    patternMatches(record, RequestPatterns.analysis)
  }

  private def isMonitor(record: LogRecord): Boolean = {
    patternMatches(record, RequestPatterns.monitor)
  }

  private def isApi(record: LogRecord): Boolean = {
    patternMatches(record, RequestPatterns.api)
  }

  private def isAsset(record: LogRecord): Boolean = {
    patternMatches(record, RequestPatterns.asset)
  }

  private def isTile(record: LogRecord): Boolean = {
    patternMatches(record, RequestPatterns.tile)
  }

  private def isHacker(record: LogRecord): Boolean = {
    patternMatches(record, RequestPatterns.hacker) &&
      (record.bodyBytesSent == "2421" || record.bodyBytesSent == "8768") ||
      record.status == "304" ||
      record.status == "400" ||
      record.status == "404" ||
      record.status == "405"
  }
}
