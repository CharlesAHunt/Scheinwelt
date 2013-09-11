package models

import java.util.Date
import utils.DatabaseUtil
import play.api.libs.Crypto
import com.novus.salat.global._
import com.mongodb.casbah.{MongoCollection, MongoConnection}
import com.novus.salat.dao.SalatDAO
import org.bson.types.ObjectId
import com.mongodb.casbah.commons.MongoDBObject
import com.novus.salat.annotations.Key

/**
 * User: Charles
 * Date: 7/17/13
 */

case class Group(@Key("_id") id: ObjectId = new ObjectId, name: String, token: String)

object GroupDAO extends SalatDAO[Group, ObjectId] (  collection = MongoConnection()("database")("group"))    {

  def isPasswordCorrect(name: String, passAttempt: String): Boolean = {

    val group = GroupDAO.findOne(MongoDBObject("name" -> name))

    if(group.isEmpty)
      false
    else {
      val groupToVerify: Group = group.get
      if(Crypto.decryptAES(groupToVerify.token) == passAttempt)
        true
      else
        false
    }
  }

}

//  def isNameTaken[T](name: String): Boolean = {
//    val collection: JacksonDBCollection[Group,_ <: AnyRef] = DatabaseUtil.getCollection("groups", classOf[Group])
//
//    val cursorDoc: DBCursor[Group]  = collection.find(DBQuery.is("name", name))
//
//    cursorDoc.count() > 0
//  }
//
//  def findByName(name: String): Group = {
//    val collection: JacksonDBCollection[Group,_ <: AnyRef] = DatabaseUtil.getCollection("groups", classOf[Group])
//
//    val cursorDoc: DBCursor[Group] = collection.find(DBQuery.is("name", name))
//
//    if (cursorDoc.count() > 0)
//      cursorDoc.next()
//    else
//      null
//  }
//

//
//}
