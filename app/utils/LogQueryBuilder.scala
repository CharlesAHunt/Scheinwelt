package utils

import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.DBObject


case class LogQueryBuilder(app: Option[String],env: Option[String],region: Option[String],level: Option[String],
                           exception: Option[String],message: Option[String],beforeDate: Option[String],afterDate: Option[String]) {

  def buildQuery(): DBObject = {

    val builder = MongoDBObject.newBuilder

    app match {
      case Some(value) => builder += "app" -> value
      case None => "what to do here"
    }


    builder += "app" -> app
    builder += "env" -> env
    builder += "region" -> region
    builder += "level" -> level
    builder += "exception" -> exception
    builder += "message" -> message
    builder += "beforeDate" -> beforeDate
    builder += "afterDate" -> afterDate

    builder.result()

  }

}
