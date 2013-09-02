package utils

import com.mongodb.DB
import com.mongodb.MongoClient
import org.mongojack.JacksonDBCollection

import java.net.UnknownHostException

object DatabaseUtil {

    def getDB:DB = {

        try {
            val mongoClient = new MongoClient( )

            mongoClient.getDB("database")

        } catch {
          case uhe: UnknownHostException => return null
        }
    }

    def getCollection[T](name: String, entityType: Class[T]): JacksonDBCollection[T,java.lang.Object] = {

        try {

            JacksonDBCollection.wrap(getDB.getCollection(name), entityType)

        } catch {
            case e: Exception => {
              e.printStackTrace()
              return null
            }
        }

    }

    def getEntityById[T](collectionName: String, entityType: Class[T], id: String):Any = {

        try {
            def jacksonDBCollection: JacksonDBCollection[T,java.lang.Object] = getCollection(collectionName, entityType)
            jacksonDBCollection.findOneById(id)
        } catch {
          case e: Exception => {
            e.printStackTrace()
            return null
          }
        }

    }
}

