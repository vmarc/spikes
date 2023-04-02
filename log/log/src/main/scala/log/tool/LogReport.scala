package log.tool

import org.springframework.stereotype.Component

@Component
class LogReport() {

  def report(context: LogAnalysisContext): Unit = {
    println("\n# Log report\n")

    println("## Values\n")
    println(s"|Name|Count|")
    println(s"|----|-----|")
    context.values.foreachEntry { case (key, count) =>
      println(s"|$key|$count|")
    }
    println("")

    println("## Devices\n")
    println(s"|Name|Count|")
    println(s"|----|-----|")
    context.devices.toSeq.sortWith { case (a, b) =>
      if (a._2 == b._2) {
        a._1 < b._1
      }
      else {
        a._2 > b._2
      }
    }.foreach { a =>
      println(s"|${a._1}|${a._2}|")
    }
    println("")

    println("## Robots\n")
    println(s"|Name|Count|")
    println(s"|----|-----|")
    context.robotCounts.toSeq.sortWith { case (a, b) =>
      if (a._2 == b._2) {
        a._1 < b._1
      }
      else {
        a._2 > b._2
      }
    }.foreach { a =>
      println(s"|${a._1}|${a._2}|")
    }
    println("")

    println("## Requests\n")
    if (context.acceptedHackerRequests.isEmpty) {
      println("OK, no accepted hacker requests")
    }
    else {
      println("accepted hacker requests")
      context.acceptedHackerRequests.foreach { line =>
        println(s"    $line")
      }
      println("")
    }

    println("\n## Unknown device requests\n")
    if (context.unknownDeviceRequests.isEmpty) {
      println("OK, no unknown device requests")
    }
    else {
      println("unknown device requests:")
      context.unknownDeviceRequests.foreach { line =>
        println(s"    $line")
      }
      println("")
    }

    println("\n## Unknown requests\n")
    if (context.unknownRequests.isEmpty) {
      println("OK, no unknown requests")
    }
    else {
      println("unknown requests:")
      context.unknownRequests.foreach { line =>
        println(s"    $line")
      }
      println("")
    }

    println("\n## Session\n")
    if (context.sessions.isEmpty) {
      println("No sessions")
    }
    else {
      println(s"${context.sessions.size} sessions:")
      context.sessions.keys.toSeq.sorted.foreach { session =>
        println(s"\n  $session")
        context.sessions(session).foreach(request => println(s"    $request"))
      }
      println("")
    }
  }
}
