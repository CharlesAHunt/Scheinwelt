package utils

import reactivemongo.api._
import scala.concurrent.ExecutionContext.Implicits.global
import reactivemongo.api.collections.default.BSONCollection

trait DatabaseService {

  val driver = new MongoDriver()
  //todo: move connection information into application.conf
  val connection = driver.connection( List("localhost:27017") )

  def getCollection(collectionName: String): BSONCollection  = {
    val db = connection("scheinwelt")

    // Gets a reference to the collection
    // By default, you get a BSONCollection.
    db[BSONCollection](collectionName)

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

