package models

import com.novus.salat._
import com.novus.salat.global._
import java.util.Date
import org.bson.types.ObjectId
import com.novus.salat.dao.{SalatDAO, ModelCompanion}
import com.mongodb.casbah.commons.MongoDBObject
import utils.DatabaseService

case class User(
                 id: ObjectId = new ObjectId,
                 username: String,
                 password: String,
                 added: Date = new Date()
                 )

object UserDAO extends ModelCompanion[User, ObjectId] with DatabaseService {
  val dao = new SalatDAO[User, ObjectId](collection = getCollection("users")) {}

  def findOneByUsername(username: String): Option[User] = dao.findOne(MongoDBObject("username" -> username))
}