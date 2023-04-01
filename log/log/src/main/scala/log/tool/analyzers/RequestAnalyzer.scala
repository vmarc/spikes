package log.tool.analyzers

import log.tool.LogAnalysisContext
import log.tool.LogRecord
import org.springframework.stereotype.Component

@Component
class RequestAnalyzer extends LogRecordAnalyzer {


  private val analysisPatterns = Seq(
    """GET /(nl|en|fr|de)/node/\d+ HTTP/1\.1""".r, // old urls
    """GET /(nl|en|fr|de)/route/\d+ HTTP/1\.1""".r, // old urls
    """GET /(nl|en|fr|de)/network/\d+ HTTP/1\.1""".r, // old urls
    """GET /(nl|en|fr|de)/network-map/\d+ HTTP/1\.1""".r, // old urls
    """GET /(nl|en|fr|de)/network-nodes/\d+ HTTP/1\.1""".r, // old urls
    """GET /(nl|en|fr|de)/network-routes/\d+ HTTP/1\.1""".r, // old urls
    """GET /(nl|en|fr|de)/glossary HTTP/1\.1""".r, // old urls

    """GET /(nl|en|fr|de)/analysis HTTP/1\.1""".r,
    """GET /(nl|en|fr|de)/analysis/overview HTTP/1\.1""".r,
    """GET /(nl|en|fr|de)/analysis/changes HTTP/1\.1""".r,
    """GET /(nl|en|fr|de)/analysis/node/\d+ HTTP/1\.1""".r,
    """GET /(nl|en|fr|de)/analysis/node/\d+/map HTTP/1\.1""".r,
    """GET /(nl|en|fr|de)/analysis/node/\d+/changes HTTP/1\.1""".r,
    """GET /(nl|en|fr|de)/analysis/route/\d+ HTTP/1\.1""".r,
    """GET /(nl|en|fr|de)/analysis/route/\d+/map HTTP/1\.1""".r,
    """GET /(nl|en|fr|de)/analysis/route/\d+/changes HTTP/1\.1""".r,
    """GET /(nl|en|fr|de)/analysis/network/\d+ HTTP/1\.1""".r,
    """GET /(nl|en|fr|de)/analysis/network/\d+/map HTTP/1\.1""".r,
    """GET /(nl|en|fr|de)/analysis/network/\d+/facts HTTP/1\.1""".r,
    """GET /(nl|en|fr|de)/analysis/network/\d+/nodes HTTP/1\.1""".r,
    """GET /(nl|en|fr|de)/analysis/network/\d+/routes HTTP/1\.1""".r,
    """GET /(nl|en|fr|de)/analysis/network/\d+/changes HTTP/1\.1""".r,
    """GET /(nl|en|fr|de)/analysis/(cycling|hiking|horseriding|motorboat|canoe|inlineskating)/.* HTTP/1\.1""".r,
    """GET /(nl|en|fr|de)/analysis/changeset/\d+/\d+ HTTP/1\.1""".r,
    """GET /(nl|en|fr|de)/ HTTP/1\.1""".r,
    """GET /(nl|en|fr|de)/settings HTTP/1\.1""".r,
    """GET /(nl|en|fr|de)/about HTTP/1\.1""".r,
    """GET /robots.txt HTTP/1\.1""".r,

    """GET /(nl|en|fr|de)/map.* HTTP/1\.1""".r, // planner
    """GET /(nl|en|fr|de)/map/(cycling|hiking|horse-riding|motorboat|canoe|inline-skating).* HTTP/1\.1""".r, // planner
  )

  private val monitorPatterns = Seq(
    """GET /(nl|en|fr|de)/monitor.* HTTP/1\.1""".r,
    """POST /(nl|en|fr|de)/monitor.* HTTP/1\.1""".r,
  )


  def analyze(record: LogRecord, context: LogAnalysisContext): LogAnalysisContext = {
    if (context.isRobot || context.isHacker || record.status == "302") {
      context
    }
    else {
      val requestType = if (record.path == "GET /" || record.path.endsWith(".js.map HTTP/1.1") || record.path.endsWith(".js HTTP/1.1") || record.path.endsWith(".css HTTP/1.1") || record.path.contains("ngsw.json")) {
        "application"
      }
      else if (analysisPatterns.exists(pattern => pattern.matches(record.path))) {
        "analysis"
      }
      else if (monitorPatterns.exists(pattern => pattern.matches(record.path))) {
        "monitor"
      }
      else if (record.path.startsWith("GET /api/") || record.path.startsWith("POST /api/") || record.path.startsWith("PUT /api/")) {
        "api"
      }
      else if (record.path.contains("/assets/") || record.path.endsWith("favicon.ico HTTP/1.1")) {
        "assets"
      }
      else if (record.path.startsWith("GET /tiles/") || record.path.startsWith("GET /tiles-history/")) {
        "tile"
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

      context.withValue(requestType).copy(
        unknownRequests = unknownRequests
      )
    }
  }
}
