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

object Group {

  implicit object BSONReader extends BSONDocumentReader[Group] {
    def read( document: BSONDocument ): Group =
      Group(
        document.getAs[BSONObjectID]("_id"),
        document.getAs[String]("name").getOrElse( "" ),
        document.getAs[String]("token").getOrElse( "" ),
        document.getAs[DateTime]("creationDate"),
        document.getAs[DateTime]("updateDate") )
  }

  implicit object BSONWriter extends BSONDocumentWriter[Group] {
    def write( group: Group ): BSONDocument =
      BSONDocument(
        "_id" -> group.id.getOrElse( BSONObjectID.generate ),
        "name" -> group.name,
        "token" -> group.token,
        "creationDate" -> group.creationDate,
        "updateDate" -> group.updateDate )
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
