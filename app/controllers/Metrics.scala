package controllers

import play.api.mvc._
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.{DBObject, AggregationOutput}
import play.libs.Json

object Metrics extends Controller with Access {

  def index = Action {  implicit request =>
    Ok(views.html.metrics(loginForm, registerForm))
  }

  def error = Action {
    Ok(aggregate("ERROR"))
  }

  def warn = Action {
    Ok(aggregate("WARN"))
  }

  def fatal = Action {
    Ok(aggregate("FATAL"))
  }

  def debug = Action {
    Ok(aggregate("DEBUG"))
  }

  def info = Action {
    Ok(aggregate("INFO"))
  }

  def aggregate(level: String): String = {
    val coll = getCollection("logs")

    val results = coll.aggregate(
      List(  MongoDBObject("$project" -> MongoDBObject("_id" -> 0 ,"level" -> 1, "date" -> 1)),
        MongoDBObject("$match" -> MongoDBObject("level" -> level)),
        MongoDBObject("$sort" -> MongoDBObject("date" -> -1))
      )
    )

    var solutions: Seq[String] = Seq()
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

    solutions = solutions :+ dates.deleteCharAt(dates.length-1).append(":").toString
    solutions = solutions :+ numbers.deleteCharAt(numbers.length-1).toString
    solutions.mkString
  }

}
