package models

import java.util.Date
import com.fasterxml.jackson.annotation.JsonProperty
import utils.DatabaseUtil
import play.api.libs.Crypto
import org.mongojack._

/**
 * User: Charles
 * Date: 7/17/13
 */

@MongoCollection(name = "groups")
class Group (

             @ObjectId @Id val id: String,
             @JsonProperty("date") val date: Date) {
  @ObjectId
  @Id def getId = id

  def name: String

  def token: String

  def isNameTaken[T](name: String): Boolean = {
    val collection: JacksonDBCollection[Group,_ <: AnyRef] = DatabaseUtil.getCollection("groups", classOf[Group])

    val cursorDoc: DBCursor[Group]  = collection.find(DBQuery.is("name", name))

    cursorDoc.count() > 0
  }

  def findByName(name: String): Group = {
    val collection: JacksonDBCollection[Group,_ <: AnyRef] = DatabaseUtil.getCollection("groups", classOf[Group])

    val cursorDoc: DBCursor[Group] = collection.find(DBQuery.is("name", name))

    if (cursorDoc.count() > 0)
      cursorDoc.next()
    else
      null
  }

  def isPasswordCorrect(name: String, passAttempt: String): Boolean = {
    val collection: JacksonDBCollection[Group,_ <: AnyRef] = DatabaseUtil.getCollection("groups", classOf[Group])

    val cursorDoc: DBCursor[Group] = collection.find(DBQuery.is("name", name))

    val group: Group = cursorDoc.next()

    if (group != null) {
      if(Crypto.decryptAES(group.token) == passAttempt)
        true
      else
        false
    }

    false
  }


}
