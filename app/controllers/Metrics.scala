package controllers

import play.api.mvc._
import com.mongodb.casbah.commons.MongoDBObject

object Metrics extends Controller with Access {

  def index = Action {  implicit request =>
    Ok(views.html.metrics(loginForm, registerForm))
  }

  def errorMetrics() = {

    val coll = getCollection("logs")

    val results = coll.aggregate(
      List( MongoDBObject("$match" -> MongoDBObject("level" -> "ERROR")),
        MongoDBObject("$unwind" -> "$tags"),
        MongoDBObject("$group" -> MongoDBObject("_id" -> "$tags", "authors" -> MongoDBObject("$addToSet" -> "$author")))
      )
    )


  }

}
