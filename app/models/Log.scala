package models

import utils.DatabaseService
import org.bson.types.ObjectId
import com.novus.salat.dao.SalatDAO
import utils.LogicContext._

case class Log (_id: ObjectId = new ObjectId,
              application: String,
              exception: String,
              message: String,
              level: String,
              trace: String,
              timeStamp: String)

object LogDAO extends SalatDAO[Log, ObjectId] (
  collection = DatabaseService.getCollection("logs"))(manifest[Log],manifest[ObjectId],ctx)