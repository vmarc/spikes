package log.tool

import log.tool.analyzers.AnalysisAnalyzer
import log.tool.analyzers.ApiAnalyzer
import log.tool.analyzers.ApplicationAnalyzer
import log.tool.analyzers.AssetAnalyzer
import log.tool.analyzers.LogRecordAnalyzer
import log.tool.analyzers.RobotAnalyzer
import log.tool.analyzers.TileAnalyzer

import scala.annotation.tailrec
import scala.io.Source

class LogAnalyzer() {

  def analyze(filename: String, logfile: String): Unit = {
    var robotRequestCount = 0
    var nonRobotRequestCount = 0
    val userAgents = scala.collection.mutable.Set[String]()
    val parser = new LogParser
    val source = Source.fromFile(filename)
    source.getLines().foreach { line =>
      val record = parser.parse(line)
      val context = analyze(record, LogAnalysisContext(logfile, record.key))
      if (context.recordAnalysis.robot) {
        robotRequestCount += 1
      }
      else {
        nonRobotRequestCount += 1
        userAgents += record.userAgent
        println(record.status + " " + record.path)
      }
    }
    source.close()
    userAgents.toSeq.foreach(println)

    println(s"robotRequestCount=$robotRequestCount")
    println(s"nonRobotRequestCount=$nonRobotRequestCount")
  }

  private def analyze(record: LogRecord, context: LogAnalysisContext): LogAnalysisContext = {
    val analyzers = List(
      RobotAnalyzer, // this has to be done first!
      AnalysisAnalyzer,
      ApiAnalyzer,
      ApplicationAnalyzer,
      AssetAnalyzer,
      TileAnalyzer
    )
    doAnalyze(analyzers, record, context)
  }

  @tailrec
  private def doAnalyze(analyzers: List[LogRecordAnalyzer], record: LogRecord, context: LogAnalysisContext): LogAnalysisContext = {
    if (analyzers.isEmpty) {
      context
    }
    else {
      val newContext = analyzers.head.analyze(record, context)
      doAnalyze(analyzers.tail, record, newContext)
    }
  }
}
