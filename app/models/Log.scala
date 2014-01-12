package models

import org.bson.types.BSONTimestamp
import utils.DatabaseService
import org.bson.types.ObjectId
import com.novus.salat.dao.SalatDAO
import utils.LogicContext._

case class Log (_id: ObjectId = new ObjectId,
              exception: String,
              message: String,
              level: String,
              trace: String,
              timeStamp: Option[BSONTimestamp] = None)

object LogDAO extends SalatDAO[Log, ObjectId] (
  collection = DatabaseService.getCollection("log"))(manifest[Log],manifest[ObjectId],ctx)