package utils

import akka.actor._
import akka.routing.RoundRobinRouter
import scala.concurrent.duration._
import play.api.libs.concurrent.Execution.Implicits._
import com.mongodb.casbah.commons.MongoDBObject

object LogProcessor extends DatabaseService {

  val coll = getCollection("logs")
  val workLoad = 20

  val results = coll.aggregate(
    List(  MongoDBObject("$project" -> MongoDBObject("_id" -> 0 ,"level" -> 1, "date" -> 1)),
      MongoDBObject("$sort" -> MongoDBObject("date" -> -1))
    )
  ).results

  def calculateDanger(start: Int): Double = {
    var dangerRating = 0
    val resultForWorker = results.dropRight(results.size-start).drop(start-workLoad)

    resultForWorker.foreach { x =>
      if(x.get("level").toString == "WARN")
        dangerRating += 1
      if(x.get("level").toString == "ERROR")
        dangerRating += 2
      if(x.get("level").toString == "FATAL")
        dangerRating += 3
    }

    dangerRating
  }

}

class Worker extends Actor {

  def receive = {
    case Work(start) ⇒
      sender ! Result(LogProcessor.calculateDanger(start)) // perform the work
  }
}

class Master(nrOfWorkers: Int, nrOfMessages: Int, listener: ActorRef) extends Actor {

  var dangerRating: Double = _
  var nrOfResults: Int = _
  val start: Long = System.currentTimeMillis

  val workerRouter = context.actorOf( Props[Worker].withRouter(RoundRobinRouter(nrOfWorkers) ), name = "workerRouter")

  def receive = {
    case Calculate ⇒ {
      var inc = 0
      for (i ← 0 until nrOfMessages) {
        inc+=LogProcessor.workLoad
        workerRouter ! Work(inc)
      }
    }
    case Result(value) ⇒
      dangerRating += value
      nrOfResults += 1
      if (nrOfResults == nrOfMessages) {

        // Send the result to the listener
        listener ! DangerCalculation(dangerRating, duration = (System.currentTimeMillis - start).millis)

        // Stops this actor and all its supervised children
        context.stop(self)
      }
  }
}

class Listener extends Actor {
  def receive = {
    case DangerCalculation(dangerRating, duration) ⇒
      println("\n\tDanger Level approximation: \t\t%s\n\tCalculation time: \t%s"
        .format(dangerRating, duration))
      context.system.shutdown()
  }
}

sealed trait DangerMessage
case object Calculate extends DangerMessage
case class Work(start: Int) extends DangerMessage
case class Result(value: Double) extends DangerMessage
case class DangerCalculation(dangerRating: Double, duration: Duration)


