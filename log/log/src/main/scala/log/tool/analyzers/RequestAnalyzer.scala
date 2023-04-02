package log.tool.analyzers

import log.tool.LogAnalysisContext
import log.tool.LogRecord
import org.springframework.stereotype.Component

@Component
class RequestAnalyzer extends LogRecordAnalyzer {

  private val applicationPatterns1 = Seq(
    """GET / HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/ HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/(nl|be|de|fr|at|es) HTTP/1\.[01]""".r,
    """GET /(nl|en|fr|de)/ngsw\.json.* HTTP/1\.[01]""".r, // TODO investigate why this is issued by the app
  )

  private val applicationPatterns2 = Seq(
    """GET /(nl|en|fr|de)/.*\.js HTTP/1\.[01]""".r,
    """GET /(nl|en|fr|de)/.*\.js\.map HTTP/1\.[01]""".r,
    """GET /(nl|en|fr|de)/.*\.css HTTP/1\.[01]""".r,
    """GET /(nl|en|fr|de)/.*\.css\.map HTTP/1\.[01]""".r,
  )

  private val analysisPatterns = Seq(
    """GET /(nl|en|fr|de)/node/\d+ HTTP/1\.[01]""".r, // old urls
    """GET /(nl|en|fr|de)/route/\d+ HTTP/1\.[01]""".r, // old urls
    """GET /(nl|en|fr|de)/network/\d+ HTTP/1\.[01]""".r, // old urls
    """GET /(nl|en|fr|de)/network-map/\d+ HTTP/1\.[01]""".r, // old urls
    """GET /(nl|en|fr|de)/network-nodes/\d+ HTTP/1\.[01]""".r, // old urls
    """GET /(nl|en|fr|de)/network-facts/\d+ HTTP/1\.[01]""".r, // old urls
    """GET /(nl|en|fr|de)/network-routes/\d+ HTTP/1\.[01]""".r, // old urls
    """GET /(nl|en|fr|de)/networks/(nl|be|de|fr|at|es)/(rwn|rcn|rhn|rpn|rmn|rin) HTTP/1\.[01]""".r, // old urls
    """GET /(nl|en|fr|de)/changes HTTP/1\.[01]""".r, // old urls
    """GET /(nl|en|fr|de)/glossary HTTP/1\.[01]""".r, // old urls

    """(GET|HEAD) /(nl|en|fr|de)/analysis HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/analysis/overview HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/analysis/changes.* HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/analysis/node/\d+ HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/analysis/node/\d+/map HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/analysis/node/\d+/changes HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/analysis/route/\d+ HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/analysis/route/\d+/map HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/analysis/route/\d+/changes.* HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/analysis/network/\d+ HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/analysis/network/\d+/map.* HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/analysis/network/\d+/facts HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/analysis/network/\d+/nodes HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/analysis/network/\d+/routes HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/analysis/network/\d+/changes.* HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/analysis/(cycling|hiking|horse-riding|motorboat|canoe|inlineskating)/.* HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/analysis/changeset/\d+/\d+ HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/ HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/settings HTTP/1\.[01]""".r,
    """(GET|HEAD) /(nl|en|fr|de)/about HTTP/1\.[01]""".r,
    """(GET|HEAD) /robots.txt HTTP/1\.[01]""".r,

    """(GET|HEAD) /(nl|en|fr|de)/map.* HTTP/1\.[01]""".r, // planner
    """(GET|HEAD) /(nl|en|fr|de)/map/(cycling|hiking|horse-riding|motorboat|canoe|inline-skating).* HTTP/1\.[01]""".r, // planner

    """(GET|HEAD) /(nl|en|fr|de)/manifest.json HTTP/1\.[01]""".r,

    """GET /(nl|en|fr|de)/authenticate.* HTTP/1\.[01]""".r,
  )

  private val monitorPatterns = Seq(
    """(GET|POST) /(nl|en|fr|de)/monitor.* HTTP/1\.[01]""".r,
  )

  private val assetsPatterns = Seq(
    """GET /assets/.* HTTP/1\.[01]""".r,
    """GET /(nl|en|fr|de)/assets/.* HTTP/1\.[01]""".r,
    """GET /(nl|en|fr|de)/favicon.ico HTTP/1\.[01]""".r,
    """GET /videos/(nl|en|fr|de)/.* HTTP/1\.[01]""".r,
    """GET .*apple-touch-icon.* HTTP/1\.[01]""".r, // TODO investigate if avoidable in app
  )

  private val hackerPatterns = Seq(
    """.*\.sql HTTP/1\.[01]""".r,
    """.*\.php HTTP/1\.[01]""".r,
    """.*\.php~ HTTP/1\.[01]""".r,
    """.*\.php.bak HTTP/1\.[01]""".r,
    """.*\.php.old HTTP/1\.[01]""".r,
    """.*\.php.swp HTTP/1\.[01]""".r,
    """.*\.php.save HTTP/1\.[01]""".r,
    """.*\.php\?.* HTTP/1\.[01]""".r,
    """.*\.gz HTTP/1\.[01]""".r,
    """.*\.zip HTTP/1\.[01]""".r,
    """.*\.7z HTTP/1\.[01]""".r,
    """.*\.rar HTTP/1\.[01]""".r,
    """.*\.txt HTTP/1\.[01]""".r,
    """.*\.xml HTTP/1\.[01]""".r,
    """.*\.swp HTTP/1\.[01]""".r,
    """.*\.db HTTP/1\.[01]""".r,
    """.*\.dmp HTTP/1\.[01]""".r,
    """.*\.dump HTTP/1\.[01]""".r,
    """.*default\.html.* HTTP/1\.[01]""".r,
    """.*Entries.* HTTP/1\.[01]""".r,
    """.*_ignition.* HTTP/1\.[01]""".r,
    """.*forum.old.* HTTP/1\.[01]""".r,
    """.*forums.old.* HTTP/1\.[01]""".r,
    """.*forums.bak.* HTTP/1\.[01]""".r,
    """.*ftp.old.* HTTP/1\.[01]""".r,
    """.*sign_in.* HTTP/1\.[01]""".r,
    """.*/admin/.* HTTP/1\.[01]""".r,
    """.*/hms/.* HTTP/1\.[01]""".r,
    """.*/wd/.* HTTP/1\.[01]""".r,
    """.*media/system.* HTTP/1\.[01]""".r,
    """.*wp-includes.* HTTP/1\.[01]""".r,
    """.*cgi-bin.* HTTP/1\.[01]""".r,
    """.*sysShell.* HTTP/1\.[01]""".r,
    """.*cmd=echo.* HTTP/1\.[01]""".r,
    """.*do.call.* HTTP/1\.[01]""".r,
    """.*select\(.* HTTP/1\.[01]""".r,
    //    """.*.* HTTP/1\.[01]""".r,
  )

  def analyze(record: LogRecord, context: LogAnalysisContext): LogAnalysisContext = {
    if (context.isRobot || context.isHacker || record.status == "302") {
      context
    }
    else {
      val requestType = if (applicationPatterns1.exists(pattern => pattern.matches(record.path))
        || (applicationPatterns2.exists(pattern => pattern.matches(record.path))
        && record.bodyBytesSent != "2421")
      ) {
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
      else if (assetsPatterns.exists(pattern => pattern.matches(record.path))) {
        "assets"
      }
      else if (record.path.startsWith("GET /tiles/") || record.path.startsWith("GET /tiles-history/")) {
        "tile"
      }
      else if (hackerPatterns.exists(pattern => pattern.matches(record.path)) && (record.bodyBytesSent == "2421" || record.bodyBytesSent == "8768") || record.status == "304" || record.status == "404" || record.status == "405") {
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
}
