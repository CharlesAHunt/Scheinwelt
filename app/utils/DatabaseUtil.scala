package utils

import com.mongodb.casbah.Imports._

object DatabaseUtil {

    def getCollection(collectionName: String) = {

      val mongoClient = MongoClient()
      val db = mongoClient("database")
      db(collectionName)

    }

//    def getEntityById[T](collectionName: String, id: String):Any = {
//
//        try {
//            val coll = db(collectionName)
//            coll.findOneById(id)
//        } catch {
//          case e: Exception => e.printStackTrace()
//        }
//
//    }
}

