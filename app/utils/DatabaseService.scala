package utils

import com.mongodb.casbah.Imports._


trait DatabaseService {

  //todo: move connection information into application.conf
//  val mongoClient = MongoClient("localhost", 27017)
  val mongoConn = MongoConnection("ds061238.mongolab.com", 61238)

  def getCollection(collectionName: String): MongoCollection = {
//    val db = mongoClient("logicdb")
    val db = mongoConn("heroku_app20997644")
    db.authenticate("logicuser", "philo123")
    // Gets a reference to the collection
    // By default, you get a BSONCollection.
    db(collectionName)
  }

  //    def getEntityById[T](collectionName: String, id : String):Any = {
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

