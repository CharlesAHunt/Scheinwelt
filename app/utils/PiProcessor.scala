package utils

import akka.actor._
import akka.routing.RoundRobinRouter
import scala.concurrent.duration._
import play.api.libs.concurrent.Execution.Implicits._

object PiProcessor {

  def calculatePiFor(start: Int, nrOfElements: Int): Double = {
    var acc = 0.0
    for (i ← start until (start + nrOfElements))
      acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1)
    acc
  }
}

class PiWorker extends Actor {

  def receive = {
    case PiWork(start, nrOfElements) ⇒
      sender ! PiResult(PiProcessor.calculatePiFor(start, nrOfElements)) // perform the work
  }
}

class PiMaster(nrOfWorkers: Int, nrOfMessages: Int, nrOfElements: Int, listener: ActorRef)
  extends Actor {

  var pi: Double = _
  var nrOfResults: Int = _
  val start: Long = System.currentTimeMillis

  val workerRouter = context.actorOf(
    Props[PiWorker].withRouter(RoundRobinRouter(nrOfWorkers)), name = "workerRouter")

  def receive = {
    case PiCalculate ⇒
      for (i ← 0 until nrOfMessages) workerRouter ! PiWork(i * nrOfElements, nrOfElements)
    case PiResult(value) ⇒
      pi += value
      nrOfResults += 1
      if (nrOfResults == nrOfMessages) {

        // Send the result to the listener
        listener ! PiApproximation(pi, duration = (System.currentTimeMillis - start).millis)

        // Stops this actor and all its supervised children
        context.stop(self)
      }
  }
}

class PiListener extends Actor {
  def receive = {
    case PiApproximation(pi, duration) ⇒
      println("\n\tPi approximation: \t\t%s\n\tCalculation time: \t%s"
        .format(pi, duration))
      context.system.shutdown()
  }
}

sealed trait PiMessage
case object PiCalculate extends PiMessage
case class PiWork(start: Int, nrOfElements: Int) extends PiMessage
case class PiResult(value: Double) extends PiMessage
case class PiApproximation(pi: Double, duration: Duration)
