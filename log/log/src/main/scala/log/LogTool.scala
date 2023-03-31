package log

import log.tool.LogAnalyzer
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
class LogTool(context: ApplicationContext) extends CommandLineRunner {
  override def run(args: String*): Unit = {
    context.getBean[LogAnalyzer](classOf[LogAnalyzer]).analyze("/kpn/logs/nginx-access.log", "be")
  }
}
