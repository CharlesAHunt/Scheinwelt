package utils

import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.DBObject


case class LogQueryBuilder(app: Option[String],env: Option[String],region: Option[String],level: Option[String],
                           exception: Option[String],message: Option[String],beforeDate: Option[String],afterDate: Option[String]) {

  def buildQuery(): DBObject = {

    val builder = MongoDBObject.newBuilder

    app match {
      case Some(value) if value != "#" => builder += "app" -> value
      case None =>
      case _ =>
    }

    env match {
      case Some(value) if value != "#" => builder += "env" -> value
      case None =>
      case _ =>
    }

    region match {
      case Some(value) if value != "#" => builder += "region" -> value
      case None =>
      case _ =>
    }

    level match {
      case Some(value) if value != "#" => builder += "level" -> value
      case None =>
      case _ =>
    }

    exception match {
      case Some(value) if value != "#" => builder += "exception" -> value
      case None =>
      case _ =>
    }

    message match {
      case Some(value) if value != "#" => builder += "message" -> value
      case None =>
      case _ =>
    }

    beforeDate match {
      case Some(value) if value != "#" => builder += "beforeDate" -> value
      case None =>
      case _ =>
    }

    afterDate match {
      case Some(value) if value != "#" => builder += "afterDate" -> value
      case None =>
      case _ =>
    }

    builder.result()

  }

}
