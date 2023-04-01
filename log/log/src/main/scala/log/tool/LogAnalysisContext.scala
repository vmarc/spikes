package log.tool

case class LogAnalysisContext(
  recordAnalysis: LogRecordAnalysis = LogRecordAnalysis(),
  values: Map[String, Int] = Map.empty,
  devices: Map[String, Int] = Map.empty,
  robotCounts: Map[String, Int] = Map.empty,
  acceptedHackerRequests: Seq[String] = Seq.empty,
  unknownDeviceRequests: Seq[String] = Seq.empty,
  unknownRequests: Seq[String] = Seq.empty,
) {

  def withValue(valueKey: String): LogAnalysisContext = {
    val newValues = values.updated(valueKey, values.getOrElse(valueKey, 0) + 1)
    copy(values = newValues)
  }

  def withDevice(deviceClass: String): LogAnalysisContext = {
    val newDevices = devices.updated(deviceClass, devices.getOrElse(deviceClass, 0) + 1)
    copy(devices = newDevices)
  }

  def withRobot(robotName: String): LogAnalysisContext = {
    val newRobotCounts = robotCounts.updated(robotName, robotCounts.getOrElse(robotName, 0) + 1)
    copy(robotCounts = newRobotCounts)
  }

  def newRecord(): LogAnalysisContext = {
    copy(recordAnalysis = LogRecordAnalysis())
  }

  def isRobot: Boolean = {
    recordAnalysis.deviceClass == "Robot" || recordAnalysis.deviceClass == "Robot Mobile"
  }

  def isHacker: Boolean = {
    recordAnalysis.deviceClass == "Hacker"
  }

}
