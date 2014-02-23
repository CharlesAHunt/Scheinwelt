package utils

import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.{BasicDBObject, DBObject}


case class LogQueryBuilder(appName: Option[String],env: Option[String],region: Option[String],level: Option[String],
                           exception: Option[String],message: Option[String],beforeDate: Option[String],afterDate: Option[String],appId: Option[String]) {

  def buildQuery(): DBObject = {

    val builder = MongoDBObject.newBuilder

    appName match {
      case Some(value) if !value.isEmpty => builder += "name" -> value
      case None =>
      case _ =>
    }

    appId match {
      case Some(value) if !value.isEmpty => builder += "application" -> value
      case None =>
      case _ =>
    }

    env match {
      case Some(value) if !value.isEmpty  => builder += "environment" -> value
      case None =>
      case _ =>
    }

    region match {
      case Some(value) if !value.isEmpty  => builder += "region" -> value
      case None =>
      case _ =>
    }

    level match {
      case Some(value) if !value.isEmpty  => builder += "level" -> value
      case None =>
      case _ =>
    }

    exception match {
      case Some(value) if !value.isEmpty  => builder += "exception" -> value
      case None =>
      case _ =>
    }

    message match {
      case Some(value) if !value.isEmpty  => builder += "message" -> new BasicDBObject( "$regex", ".*"+value+".*")
      case None =>
      case _ =>
    }

    beforeDate match {
      case Some(value) if !value.isEmpty  => builder += "beforeDate" -> value
      case None =>
      case _ =>
    }

    afterDate match {
      case Some(value) if !value.isEmpty  => builder += "afterDate" -> value
      case None =>
      case _ =>
    }

    builder.result()

  }

}
