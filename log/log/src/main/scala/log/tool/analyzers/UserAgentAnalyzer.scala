package log.tool.analyzers

import log.tool.LogAnalysisContext
import log.tool.LogRecord
import org.springframework.stereotype.Component

@Component
class UserAgentAnalyzer extends LogRecordAnalyzer {

  private val userAgentAnalyzer = nl.basjes.parse.useragent.UserAgentAnalyzer.newBuilder
    .withCache(10000)
    .hideMatcherLoadStats
    .withField("DeviceClass")
    .withField("DeviceName")
    .build

  def analyze(record: LogRecord, context: LogAnalysisContext): LogAnalysisContext = {
    val result = userAgentAnalyzer.parse(record.userAgent)
    val deviceClass = result.get("DeviceClass").getValue
    val deviceName = result.get("DeviceName").getValue
    val recordAnalysis = context.recordAnalysis.copy(
      deviceClass = deviceClass,
      deviceName = deviceName
    )

    val updatedDeviceClass = if (deviceClass == "Unknown") {
      if (record.userAgent.startsWith("zerodium")) {
        "Hacker"
      }
      else if (record.userAgent == "cortex/1.0") {
        "Robot"
      }
      else {
        "Unkown"
      }
    }
    else {
      deviceClass
    }

    val unknownDeviceRequests = if (updatedDeviceClass == "Unknown") {
      context.unknownDeviceRequests :+ record.line
    }
    else {
      context.unknownDeviceRequests
    }

    context.withDevice(updatedDeviceClass).copy(
      recordAnalysis = recordAnalysis,
      unknownDeviceRequests = unknownDeviceRequests
    )
  }
}
