package utils

import com.mongodb.casbah.Imports._


trait DatabaseService {

  //todo: move connection information into application.conf
  val mongoClient = MongoClient("localhost", 27017)

  def getCollection(collectionName: String): BSONCollection = {
    val db = mongoClient("scheinwelt")
    // Gets a reference to the collection
    // By default, you get a BSONCollection.
    val coll = db(collectionName)

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

