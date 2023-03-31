package log

import log.tool.LogAnalyzer
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


object LogApplication {
  def main(args: Array[String]): Unit = {
    val app: Array[Class[_]] = Array(classOf[LogApplication])
    SpringApplication.run(app, args)
  }
}

@SpringBootApplication
class LogApplication() extends CommandLineRunner {
  private val log = LoggerFactory.getLogger(classOf[LogApplication])

  override def run(args: String*): Unit = {
    log.info("start")
    new LogAnalyzer().analyze("/kpn/logs/nginx-access.log", "be")
  }
}
