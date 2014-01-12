package models

import utils.DatabaseService
import org.bson.types.ObjectId
import com.novus.salat.dao.SalatDAO
import utils.LogicContext._

case class Logs (_id: ObjectId = new ObjectId,
                 name: String,
                 logList: List[Log]
                 )

object LogsDAO extends SalatDAO[Logs, ObjectId] (
  collection = DatabaseService.getCollection("logs"))(manifest[Logs],manifest[ObjectId],ctx)