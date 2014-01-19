package models

import utils.DatabaseService
import org.bson.types.ObjectId
import com.novus.salat.dao.SalatDAO
import utils.LogicContext.ctx

case class Application (_id: ObjectId = new ObjectId,
                          name: String,
                          environment: String,
                          region: String,
                          logList: List[String]
                         )

object ApplicationDAO extends SalatDAO[Application, ObjectId] (
  collection = DatabaseService.getCollection("applications"))(manifest[Application],manifest[ObjectId],ctx)