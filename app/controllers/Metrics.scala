package controllers

import play.api.mvc._
import com.mongodb.casbah.commons.MongoDBObject

object Metrics extends Controller with Access {

  def index = Action {  implicit request =>
    val solutions = errorMetrics()
    Ok(views.html.metrics(loginForm, registerForm, solutions.apply(0),solutions.apply(1)))
  }

  def errorMetrics():Seq[String] = {
    var solutions: Seq[String] = Seq()
    val coll = getCollection("logs")

    val results = coll.aggregate(
      List(  MongoDBObject("$project" -> MongoDBObject("_id" -> 0 ,"level" -> 1, "date" -> 1)),
        MongoDBObject("$match" -> MongoDBObject("level" -> "ERROR")),
        MongoDBObject("$sort" -> MongoDBObject("date" -> -1))
      )
    )

    val m = scala.collection.mutable.Map[Any,Int]()
    val increment = (x: Int) => x + 1

    results.results.foreach(x =>
      if(m.contains(x.get("date"))) {
        m.put(x.get("date"),increment(m.get(x.get("date")).get))
      }
      else m.put(x.get("date"),1)
    )

    val dates: StringBuilder = StringBuilder.newBuilder
    val numbers: StringBuilder = StringBuilder.newBuilder
    m.foreach{x =>
      dates.append(x._1.toString).append(",")
      numbers.append(x._2+ ",")
    }

    solutions = solutions :+ dates.deleteCharAt(dates.length-1).toString
    solutions = solutions :+ numbers.deleteCharAt(numbers.length-1).toString
    println(dates)
    println(numbers)
    solutions
  }

}
