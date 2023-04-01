package log

import log.tool.LogAnalyzer
import log.tool.LogReport
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext

object LogTool {
  def main(args: Array[String]): Unit = {
    val app: Array[Class[_]] = Array(classOf[LogTool])
    SpringApplication.run(app, args)
  }
}

@SpringBootApplication
class LogTool(applicationContext: ApplicationContext) extends CommandLineRunner {
  override def run(args: String*): Unit = {
    val logAnalyzer = applicationContext.getBean[LogAnalyzer](classOf[LogAnalyzer])
    val logReport = applicationContext.getBean[LogReport](classOf[LogReport])
    val context = logAnalyzer.analyze("/kpn/logs/nl-access.log")
    logReport.report(context)
  }
}
