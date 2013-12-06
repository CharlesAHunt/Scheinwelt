package models

import com.novus.salat._
import com.novus.salat.global._
import java.util.Date
import org.bson.types.ObjectId
import com.novus.salat.dao.{SalatDAO, ModelCompanion}
import com.mongodb.casbah.commons.MongoDBObject
import utils.DatabaseService

case class Log(id: ObjectId = new ObjectId,
                 exception: String,
                 message: String,
                 level: String,
                 trace: String,
                 logDate: Option[Date] = None)

object LogDAO extends ModelCompanion[Log, ObjectId] with DatabaseService {

  val dao = new SalatDAO[Log, ObjectId](collection = getCollection("logs")) {}
  def findByException(exception: String) = dao.find(MongoDBObject("exception" -> exception))

}