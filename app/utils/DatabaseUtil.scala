package utils

import reactivemongo.api._
import scala.concurrent.ExecutionContext.Implicits.global

object DatabaseUtil {

  def connect(): reactivemongo.api.MongoConnection = {
    // gets an instance of the driver
    // (creates an actor system)
    val driver = new MongoDriver
    driver.connection(List("localhost"))
  }

  def getCollection(collectionName: String) = {

    // Gets a reference to the database "plugin"
    val connection = connect()

    // Gets a reference to the database "plugin"
    val db = connection("scheinwelt")

    // Gets a reference to the collection "acoll"
    // By default, you get a BSONCollection.
    val collection = db(collectionName)

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

