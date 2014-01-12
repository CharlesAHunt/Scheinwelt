package models

import utils.DatabaseService
import org.bson.types.ObjectId
import com.novus.salat.dao.SalatDAO
import utils.LogicContext._

case class Environment (_id: ObjectId = new ObjectId,
                         name: String
                        )

object EnvironmentDAO extends SalatDAO[Environment, ObjectId] (
  collection = DatabaseService.getCollection("environments"))(manifest[Environment],manifest[ObjectId],ctx)
