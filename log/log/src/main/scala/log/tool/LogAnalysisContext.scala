package log.tool

case class LogAnalysisContext(
  recordAnalysis: LogRecordAnalysis = LogRecordAnalysis(),
  values: Map[String, Int] = Map.empty,
  devices: Map[String, Int] = Map.empty,
  robotCounts: Map[String, Int] = Map.empty,
  acceptedHackerRequests: Seq[String] = Seq.empty,
  unknownDeviceRequests: Seq[String] = Seq.empty,
  unknownRequests: Seq[String] = Seq.empty,
  sessions: Map[String, Seq[String]] = Map.empty
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

  def withApi(session: String, request: String): LogAnalysisContext = {
    val newSessions = sessions.updated(session, sessions.getOrElse(session, Seq.empty) :+ request)
    copy(sessions = newSessions)
  }

  def isRobot: Boolean = {
    recordAnalysis.deviceClass == "Robot" || recordAnalysis.deviceClass == "Robot Mobile"
  }

  def isHacker: Boolean = {
    recordAnalysis.deviceClass == "Hacker"
  }
}
