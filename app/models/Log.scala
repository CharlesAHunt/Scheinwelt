package models

import org.bson.types.BSONTimestamp

case class Log(
              environment: String,
              region: String,
              application: String,
              exception: String,
              message: String,
              level: String,
              trace: String,
              timeStamp: Option[BSONTimestamp] = None)

