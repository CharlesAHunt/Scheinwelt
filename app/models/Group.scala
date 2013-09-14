package models

import org.joda.time.DateTime
import reactivemongo.bson._

/**
 * User: Charles
 * Date: 7/17/13
 */

case class Group(
                    id: Option[BSONObjectID],
                    name: String,
                    token: String,
                    creationDate: Option[DateTime],
                    updateDate: Option[DateTime]
                    )


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
